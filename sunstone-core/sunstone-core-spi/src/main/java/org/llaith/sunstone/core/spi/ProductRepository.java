package org.llaith.sunstone.core.spi;

import org.llaith.sunstone.api.transfer.ProductConfig;

import java.util.List;

/**
 *
 */
public interface ProductRepository {

    void storeProduct(ProductConfig product);

    ProductConfig getProduct(String name);

    List<ProductConfig> getAllProducts();

}
