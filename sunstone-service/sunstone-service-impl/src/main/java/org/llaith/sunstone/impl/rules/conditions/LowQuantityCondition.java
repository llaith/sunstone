package org.llaith.sunstone.impl.rules.conditions;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;
import org.llaith.sunstone.api.service.ordering.transfer.InventoryLevel;
import org.llaith.sunstone.api.service.ordering.transfer.ProductConfig;

/**
 *
 */
public class LowQuantityCondition implements Condition {

    public static LowQuantityCondition IsLowQuantity() {
        return new LowQuantityCondition();
    }

    @Override
    public boolean evaluate(final Facts facts) {

        final InventoryLevel inventory = facts.get("inventory");

        final ProductConfig config = facts.get("config");

        return inventory.getRemainingQuantity() < config.getMinimumQuantity();

    }

}
