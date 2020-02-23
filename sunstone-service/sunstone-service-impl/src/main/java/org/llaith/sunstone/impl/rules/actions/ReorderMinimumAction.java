package org.llaith.sunstone.impl.rules.actions;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;
import org.llaith.sunstone.api.service.ordering.transfer.InventoryLevel;
import org.llaith.sunstone.api.service.ordering.transfer.ProductConfig;
import org.llaith.sunstone.impl.rules.OrderAmount;

/**
 *
 */
public class ReorderMinimumAction implements Action {

    public static ReorderMinimumAction reorderMinimumAmount() {
        return new ReorderMinimumAction();
    }

    @Override
    public void execute(final Facts facts) {

        final InventoryLevel inventory = facts.get("inventory");

        final ProductConfig config = facts.get("config");

        final OrderAmount order = facts.get("order");

        order.setBaseQuantity(config.getMinimumQuantity() - inventory.getRemainingQuantity());

    }

}
