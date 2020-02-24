package org.llaith.sunstone.adapter.easyrules;

import org.jeasy.rules.api.Action;
import org.llaith.sunstone.api.transfer.InventoryLevel;
import org.llaith.sunstone.api.transfer.ProductConfig;

import static org.llaith.sunstone.adapter.easyrules.OrderLogicFacts.inventoryLevel;
import static org.llaith.sunstone.adapter.easyrules.OrderLogicFacts.orderAmount;
import static org.llaith.sunstone.adapter.easyrules.OrderLogicFacts.productConfig;

/**
 *
 */
public class OrderLogicActions {

    private OrderLogicActions() {}

    public static Action blockProduct() {
        return facts -> {
            final OrderingAmount order = orderAmount(facts);
            order.setBlocked(true);
        };
    }

    public static Action orderAdditionalAmount() {
        return facts -> {
            final InventoryLevel inventory = inventoryLevel(facts);
            final OrderingAmount order = orderAmount(facts);

            order.setAdditionalQuantity(inventory.getAdditionalQuantity());
        };
    }

    public static Action reorderMinimumAmount() {
        return facts -> {
            final InventoryLevel inventory = inventoryLevel(facts);
            final ProductConfig config = productConfig(facts);
            final OrderingAmount order = orderAmount(facts);

            order.setBaseQuantity(config.getMinimumQuantity() - inventory.getRemainingQuantity());
        };

    }

}
