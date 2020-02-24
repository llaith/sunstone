package org.llaith.sunstone.core.restapi.server;

import io.javalin.Javalin;
import org.llaith.sunstone.adapter.easyrules.OrderLogicRulesImpl;
import org.llaith.sunstone.adapter.jdbi.OrderRepositoryJdbiImpl;
import org.llaith.sunstone.adapter.mocks.InventoryRepositoryMockImpl;
import org.llaith.sunstone.adapter.mocks.ProductRepositoryMockImpl;
import org.llaith.sunstone.api.exception.InsufficientInventoryException;
import org.llaith.sunstone.api.exception.UnknownProductException;
import org.llaith.sunstone.api.transfer.InventoryLevel;
import org.llaith.sunstone.api.transfer.ProductConfig;
import org.llaith.sunstone.core.service.InventoryServiceImpl;
import org.llaith.sunstone.core.service.OrderServiceImpl;
import org.llaith.sunstone.core.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.javalin.apibuilder.ApiBuilder.post;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

/**
 *
 */
public class MockOrderApi {

    private static final Logger logger = LoggerFactory.getLogger(MockOrderApi.class);

    /**
     * Example curl:
     * curl -i -X POST http://localhost:7000/inventory/withdrawls/product1?quantity=5
     * curl -i -X POST http://localhost:7000/order/reorders/product1
     *
     * @param args command-line args
     */
    public static void main(String[] args) {

        var inventoryRepo = new InventoryRepositoryMockImpl();
        var inventory = new InventoryServiceImpl(inventoryRepo);
        var productRepo = new ProductRepositoryMockImpl();
        var products = new ProductServiceImpl(productRepo);
        var repository = new OrderRepositoryJdbiImpl();
        var logic = new OrderLogicRulesImpl();

        productRepo.storeProduct(new ProductConfig("product1", 10, false));
        productRepo.storeProduct(new ProductConfig("product2", 20, true));
        productRepo.storeProduct(new ProductConfig("product3", 10, false));

        inventoryRepo.storeInventory(new InventoryLevel("product1", 10, 0));
        inventoryRepo.storeInventory(new InventoryLevel("product2", 5, 0));
        inventoryRepo.storeInventory(new InventoryLevel("product3", 10, 10));

        var service = new OrderServiceImpl(
                inventory,
                products,
                repository,
                logic);

        Javalin app = Javalin.create().start(7000);

        app.exception(UnknownProductException.class, (e, ctx) -> {
            logger.warn("Unknown product: {}", ctx.pathParam("name"), e);
            ctx.status(404);
        }).error(404, ctx -> ctx.result(format(
                "Unknown product: %s%n",
                ctx.pathParam("name"))));

        app.exception(InsufficientInventoryException.class, (e, ctx) -> {
            logger.warn("Insufficient quantity of product: {}", ctx.pathParam("name"), e);
            ctx.status(418);
        }).error(418, ctx -> ctx.result(format(
                "Insufficient teapot quantity of product: %s%n",
                ctx.pathParam("name"))));

        app.routes(() -> {

            post("/inventory/withdrawls/:name", ctx -> {
                var name = ctx.pathParam("name");
                var quantity = parseInt(requireNonNull(ctx.queryParam("quantity")));
                inventory.withdrawInventory(name, quantity);
                ctx.json(inventory.getInventory(name));
            });

            post("/order/reorders/:name", ctx -> {
                var item = service.reorderItem(ctx.pathParam("name"));
                ctx.json(item);
            });

        });

    }


}
