package org.llaith.sunstone.adapter.mocks;

import org.llaith.sunstone.api.transfer.InventoryLevel;
import org.llaith.sunstone.core.spi.InventoryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class InventoryRepositoryMockImpl implements InventoryRepository {

    private final Map<String,InventoryLevel> db = new HashMap<>();

    @Override
    public void storeInventory(final InventoryLevel inventory) {
        this.db.put(inventory.getName(), inventory);
    }

    @Override
    public InventoryLevel getInventory(final String product) {
        return this.db.get(product);
    }

    @Override
    public List<InventoryLevel> getAllInventories(final String product) {
        return new ArrayList<>(this.db.values());
    }

}
