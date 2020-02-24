package org.llaith.sunstone.core.service;

import org.llaith.sunstone.api.InventoryService;
import org.llaith.sunstone.api.OrderService;
import org.llaith.sunstone.api.ProductService;
import org.llaith.sunstone.api.transfer.OrderItem;
import org.llaith.sunstone.core.spi.OrderLogic;
import org.llaith.sunstone.core.spi.OrderRepository;

import static java.util.Objects.requireNonNull;

/**
 *
 */
public class OrderServiceImpl implements OrderService {

    private InventoryService inventoryService;
    private ProductService productService;
    private OrderRepository orderRepository;
    private OrderLogic orderLogic;

    public OrderServiceImpl(
            final InventoryService inventoryService,
            final ProductService productService,
            final OrderRepository orderRepository,
            final OrderLogic orderLogic) {

        this.inventoryService = requireNonNull(inventoryService);
        this.productService = requireNonNull(productService);
        this.orderRepository = requireNonNull(orderRepository);
        this.orderLogic = requireNonNull(orderLogic);

    }


    @Override
    public OrderItem reorderItem(final String name) {

        var product = this.productService.getProduct(name);

        var inventory = this.inventoryService.getInventory(name);

        var item = this.orderLogic.reorderAmount(product, inventory);

        this.orderRepository.storeOrderItem(item);

        return item;

    }

}
