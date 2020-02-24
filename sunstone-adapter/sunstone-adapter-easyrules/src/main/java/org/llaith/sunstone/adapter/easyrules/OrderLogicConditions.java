package org.llaith.sunstone.adapter.easyrules;

import org.jeasy.rules.api.Condition;
import org.llaith.sunstone.api.transfer.InventoryLevel;
import org.llaith.sunstone.api.transfer.ProductConfig;

import static org.llaith.sunstone.adapter.easyrules.OrderLogicFacts.inventoryLevel;
import static org.llaith.sunstone.adapter.easyrules.OrderLogicFacts.productConfig;

/**
 *
 */
public class OrderLogicConditions {

    private OrderLogicConditions() {}

    public static Condition additionalStockIsRequested() {
        return facts -> {
            final InventoryLevel inventory = inventoryLevel(facts);

            return inventory.getAdditionalQuantity() > 0;
        };
    }

    public static Condition productIsBlocked() {
        return facts -> {
            final ProductConfig config = productConfig(facts);

            return config.isBlocked();
        };
    }

    public static Condition productNeedsReorder() {
        return facts -> {
            final InventoryLevel inventory = inventoryLevel(facts);
            final ProductConfig config = productConfig(facts);

            return inventory.getRemainingQuantity() < config.getMinimumQuantity();
        };
    }

}
