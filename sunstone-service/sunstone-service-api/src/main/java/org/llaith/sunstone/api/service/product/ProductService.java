package org.llaith.sunstone.api.service.product;


import org.llaith.sunstone.api.service.UnknownProductException;
import org.llaith.sunstone.api.service.product.transfer.ProductConfig;

import java.util.Set;

/**
 *
 */
public interface ProductService {

    void checkProductExists(String name) throws UnknownProductException;

    ProductConfig configureProduct(String name, int minimumLevel, boolean blocked);

    ProductConfig getProductConfig(String name) throws UnknownProductException;

    Set<ProductConfig> getProducts();

}
