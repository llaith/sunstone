package org.llaith.sunstone.api.service.ordering;


import org.llaith.sunstone.api.service.UnknownProductException;
import org.llaith.sunstone.api.service.ordering.transfer.ProductConfig;

import java.util.Set;

/**
 *
 */
public interface ProductService {

    ProductConfig getProductConfig(String name);

    ProductConfig storeProduct(ProductConfig config) throws UnknownProductException;

    Set<ProductConfig> getAllProducts();

}
