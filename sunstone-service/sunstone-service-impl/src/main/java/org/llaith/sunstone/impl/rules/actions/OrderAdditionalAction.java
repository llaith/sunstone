package org.llaith.sunstone.impl.rules.actions;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;
import org.llaith.sunstone.api.service.ordering.transfer.InventoryLevel;
import org.llaith.sunstone.impl.rules.OrderAmount;

/**
 *
 */
public class OrderAdditionalAction implements Action {

    public static OrderAdditionalAction orderAdditionalAmount() {
        return new OrderAdditionalAction();
    }

    @Override
    public void execute(final Facts facts) {

        final InventoryLevel inventory = facts.get("inventory");

        final OrderAmount order = facts.get("order");

        order.setAdditionalQuantity(inventory.getAdditionalQuantity());

    }

}
