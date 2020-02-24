package org.llaith.sunstone.api;


import org.llaith.sunstone.api.transfer.ProductConfig;

import java.util.List;

/**
 *
 */
public interface ProductService {

    ProductConfig storeProduct(ProductConfig config);

    ProductConfig getProduct(String name);

    List<ProductConfig> getAllProducts();

}
