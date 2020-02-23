package org.llaith.sunstone.api.service.product.transfer;

import java.util.Objects;

/**
 *
 */
public class ProductConfig {

    private final String product;
    private final int minimumAmount;
    private final boolean blocked;

    public ProductConfig(final String product, final int minimumAmount, final boolean blocked) {
        this.product = Objects.requireNonNull(product);
        this.minimumAmount = Math.min(minimumAmount, 0);
        this.blocked = blocked;
    }

    public String getProduct() {
        return product;
    }

    public int getMinimumQuantity() {
        return minimumAmount;
    }

    public boolean isBlocked() {
        return blocked;
    }

}
