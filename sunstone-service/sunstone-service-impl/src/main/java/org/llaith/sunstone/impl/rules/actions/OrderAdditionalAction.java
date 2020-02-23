package org.llaith.sunstone.impl.rules.actions;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;
import org.llaith.sunstone.api.service.inventory.transfer.InventoryLevel;
import org.llaith.sunstone.impl.rules.OrderAmount;

/**
 *
 */
public class OrderAdditionalAction implements Action {

    @Override
    public void execute(final Facts facts) {

        final InventoryLevel inventory = facts.get("inventory");

        OrderAmount order = facts.get("order");

        order.setAdditionalQuantity(inventory.getAdditionalQuantity());

    }

}
