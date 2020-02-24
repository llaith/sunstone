package org.llaith.sunstone.adapter.mocks;

import org.llaith.sunstone.api.transfer.ProductConfig;
import org.llaith.sunstone.core.spi.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ProductRepositoryMockImpl implements ProductRepository {

    private final Map<String,ProductConfig> db = new HashMap<>();

    @Override
    public void storeProduct(final ProductConfig config) {
        this.db.put(config.getName(), config);
    }

    @Override
    public ProductConfig getProduct(final String name) {
        return this.db.get(name);
    }

    @Override
    public List<ProductConfig> getAllProducts() {
        return new ArrayList<>(this.db.values());
    }

}
