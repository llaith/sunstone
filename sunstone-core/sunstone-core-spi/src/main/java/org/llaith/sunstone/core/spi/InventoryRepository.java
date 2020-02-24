package org.llaith.sunstone.core.spi;

import org.llaith.sunstone.api.transfer.InventoryLevel;

import java.util.List;

/**
 *
 */
public interface InventoryRepository {

    void storeInventory(InventoryLevel inventory);

    InventoryLevel getInventory(String product);

    List<InventoryLevel> getAllInventories(String product);

}
