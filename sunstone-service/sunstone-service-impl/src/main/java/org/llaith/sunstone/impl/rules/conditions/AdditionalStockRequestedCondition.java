package org.llaith.sunstone.impl.rules.conditions;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;
import org.llaith.sunstone.api.service.ordering.transfer.InventoryLevel;

/**
 *
 */
public class AdditionalStockRequestedCondition implements Condition {

    public static AdditionalStockRequestedCondition IsAdditionalStockRequested() {
        return new AdditionalStockRequestedCondition();
    }

    @Override
    public boolean evaluate(final Facts facts) {

        final InventoryLevel inventory = facts.get("inventory");

        return inventory.getAdditionalQuantity() > 0;

    }

}
