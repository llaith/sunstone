package org.llaith.sunstone.adapter.easyrules;

import org.jeasy.rules.api.Facts;
import org.llaith.sunstone.api.transfer.InventoryLevel;
import org.llaith.sunstone.api.transfer.ProductConfig;

/**
 *
 */
public class OrderLogicFacts {

    private static final String PRODUCT_ID = "product";
    private static final String INVENTORY_ID = "inventory";
    private static final String ORDER_ID = "order";

    private OrderLogicFacts() {}

    public static Facts toFacts(final ProductConfig product, final InventoryLevel inventory) {

        var facts = new Facts();

        facts.put(PRODUCT_ID, product);
        facts.put(INVENTORY_ID, inventory);
        facts.put(ORDER_ID, new OrderingAmount(product.getName()));

        return facts;

    }

    public static ProductConfig productConfig(final Facts facts) {
        return facts.get(PRODUCT_ID);
    }

    public static InventoryLevel inventoryLevel(final Facts facts) {
        return facts.get(INVENTORY_ID);
    }

    public static OrderingAmount orderAmount(final Facts facts) {
        return facts.get(ORDER_ID);
    }

}
