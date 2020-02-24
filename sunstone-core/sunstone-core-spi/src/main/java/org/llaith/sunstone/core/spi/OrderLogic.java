package org.llaith.sunstone.core.spi;

import org.llaith.sunstone.api.transfer.InventoryLevel;
import org.llaith.sunstone.api.transfer.OrderItem;
import org.llaith.sunstone.api.transfer.ProductConfig;

/**
 *
 */
public interface OrderLogic {

    OrderItem reorderAmount(ProductConfig product, InventoryLevel inventory);

}
