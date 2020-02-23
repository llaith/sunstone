package org.llaith.sunstone.api.service.inventory;

import org.llaith.sunstone.api.service.UnknownProductException;
import org.llaith.sunstone.api.service.inventory.transfer.InventoryLevel;

/**
 *
 */
public interface InventoryService {

    InventoryLevel checkQuantity(String product) throws UnknownProductException;

    InventoryLevel withdrawQuantity(String product, int quantity) throws UnknownProductException;

    InventoryLevel addReorderRequest(String product, int quantity) throws UnknownProductException;

}
