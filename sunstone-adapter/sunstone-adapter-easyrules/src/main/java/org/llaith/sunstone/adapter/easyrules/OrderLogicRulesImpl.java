package org.llaith.sunstone.adapter.easyrules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.llaith.sunstone.api.transfer.InventoryLevel;
import org.llaith.sunstone.api.transfer.OrderItem;
import org.llaith.sunstone.api.transfer.ProductConfig;
import org.llaith.sunstone.core.spi.OrderLogic;

import static java.util.Objects.requireNonNull;

/**
 *
 */
public class OrderLogicRulesImpl implements OrderLogic {

    private final RulesEngine rulesEngine;

    private final Rules orderRules;

    public OrderLogicRulesImpl() {

        this.rulesEngine = new DefaultRulesEngine();

        this.orderRules = initOrderRules();

    }

    private Rules initOrderRules() {

        final Rules rules = new Rules();

        rules.register(new RuleBuilder()
                               .name("block product rule")
                               .priority(0)
                               .when(OrderLogicConditions.productIsBlocked())
                               .then(OrderLogicActions.blockProduct())
                               .build());

        rules.register(new RuleBuilder()
                               .name("reorder minimum amount")
                               .priority(100)
                               .when(OrderLogicConditions.productNeedsReorder())
                               .then(OrderLogicActions.reorderMinimumAmount())
                               .build());

        rules.register(new RuleBuilder()
                               .name("reorder additional amount")
                               .priority(101)
                               .when(OrderLogicConditions.additionalStockIsRequested())
                               .then(OrderLogicActions.orderAdditionalAmount())
                               .build());

        return rules;

    }

    @Override
    public OrderItem reorderAmount(final ProductConfig product, final InventoryLevel inventory) {

        final Facts facts = OrderLogicFacts.toFacts(
                requireNonNull(product),
                requireNonNull(inventory));

        this.rulesEngine.fire(this.orderRules, facts);

        return OrderLogicFacts
                .orderAmount(facts)
                .toOrderItem();

    }

}
