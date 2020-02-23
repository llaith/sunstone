package org.llaith.sunstone.api.service.ordering;

import org.llaith.sunstone.api.service.UnknownProductException;
import org.llaith.sunstone.api.service.ordering.transfer.InventoryLevel;

/**
 *
 */
public interface InventoryService {

    InventoryLevel getInventoryLevel(String product) throws UnknownProductException;

    void setInventoryLevel(String product, int quantity) throws UnknownProductException;

}
