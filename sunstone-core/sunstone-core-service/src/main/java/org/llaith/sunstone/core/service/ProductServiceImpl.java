package org.llaith.sunstone.core.service;

import org.llaith.sunstone.api.ProductService;
import org.llaith.sunstone.api.transfer.ProductConfig;
import org.llaith.sunstone.core.spi.ProductRepository;

import java.util.List;
import java.util.Objects;

/**
 *
 */
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(final ProductRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public ProductConfig storeProduct(final ProductConfig config) {
        this.repository.storeProduct(config);
        return this.repository.getProduct(config.getName());
    }

    @Override
    public ProductConfig getProduct(final String name) {
        return this.repository.getProduct(name);
    }

    @Override
    public List<ProductConfig> getAllProducts() {
        return this.repository.getAllProducts();
    }

}
