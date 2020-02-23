package org.llaith.sunstone.impl.rules.actions;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;
import org.llaith.sunstone.api.service.inventory.transfer.InventoryLevel;
import org.llaith.sunstone.api.service.product.transfer.ProductConfig;
import org.llaith.sunstone.impl.rules.OrderAmount;

/**
 *
 */
public class ReorderAction implements Action {

    @Override
    public void execute(final Facts facts) {

        final InventoryLevel inventory = facts.get("inventory");

        final ProductConfig config = facts.get("config");

        OrderAmount order = facts.get("order");

        order.setBaseQuantity(config.getMinimumQuantity() - inventory.getRemainingQuantity());

    }

}
