package org.llaith.sunstone.api;

import org.llaith.sunstone.api.transfer.InventoryLevel;

/**
 *
 */
public interface InventoryService {

    InventoryLevel getInventory(String name);

    void withdrawInventory(String name, int quantity);

    void depositInventory(String name, int quantity);

    void requestAdditionalOrder(String name, int quantity);

}
