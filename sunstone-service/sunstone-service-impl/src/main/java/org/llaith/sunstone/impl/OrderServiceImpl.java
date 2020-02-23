package org.llaith.sunstone.impl;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.llaith.sunstone.api.service.UnknownProductException;
import org.llaith.sunstone.api.service.ordering.InventoryService;
import org.llaith.sunstone.api.service.ordering.OrderService;
import org.llaith.sunstone.api.service.ordering.ProductService;
import org.llaith.sunstone.api.service.ordering.transfer.OrderItem;
import org.llaith.sunstone.impl.rules.OrderAmount;
import org.llaith.sunstone.impl.spi.repository.OrderRepository;

import static java.util.Objects.requireNonNull;
import static org.llaith.sunstone.impl.rules.actions.BlockProductAction.blockProduct;
import static org.llaith.sunstone.impl.rules.actions.OrderAdditionalAction.orderAdditionalAmount;
import static org.llaith.sunstone.impl.rules.actions.ReorderMinimumAction.reorderMinimumAmount;
import static org.llaith.sunstone.impl.rules.conditions.AdditionalStockRequestedCondition.IsAdditionalStockRequested;
import static org.llaith.sunstone.impl.rules.conditions.BlockedProductCondition.IsBlockedProduct;
import static org.llaith.sunstone.impl.rules.conditions.LowQuantityCondition.IsLowQuantity;

/**
 *
 */
public class OrderServiceImpl implements OrderService {

    private InventoryService inventoryService;
    private ProductService productService;
    private OrderRepository orderRepository;

    private final RulesEngine rulesEngine = new InferenceRulesEngine();
    private final Rules orderRules;

    public OrderServiceImpl(
            final InventoryService inventoryService,
            final ProductService productService,
            final OrderRepository orderRepository) {

        this.inventoryService = requireNonNull(inventoryService);
        this.productService = requireNonNull(productService);
        this.orderRepository = requireNonNull(orderRepository);

        this.orderRules = initOrderRules();
    }

    private Rules initOrderRules() {

        final Rules rules = new Rules();

        rules.register(new RuleBuilder()
                               .name("block product rule")
                               .priority(0)
                               .when(IsBlockedProduct())
                               .then(blockProduct())
                               .build());

        rules.register(new RuleBuilder()
                               .name("reorder minimum amount")
                               .priority(10)
                               .when(IsLowQuantity())
                               .then(reorderMinimumAmount())
                               .build());

        rules.register(new RuleBuilder()
                               .name("reorder additional amount")
                               .priority(10)
                               .when(IsAdditionalStockRequested())
                               .then(orderAdditionalAmount())
                               .build());

        return rules;

    }

    @Override
    public OrderItem processOrderItem(final String product) throws UnknownProductException {

        final Facts facts = this.configureFacts(product);

        this.rulesEngine.fire(this.orderRules, facts);

        final OrderItem item = facts.get("order");

        this.orderRepository.storeOrderItem(item);

        return item;

    }


    private Facts configureFacts(final String product) {

        final Facts facts = new Facts();

        facts.put("config", this.productService.getProductConfig(product));
        facts.put("inventory", this.inventoryService.getInventoryLevel(product));
        facts.put("order", new OrderAmount(product));

        return facts;

    }

}
