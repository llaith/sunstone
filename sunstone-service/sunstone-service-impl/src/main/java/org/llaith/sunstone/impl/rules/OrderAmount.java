package org.llaith.sunstone.impl.rules;

/**
 *
 */
public class OrderAmount {

    private final String product;

    private boolean blocked;

    private int baseQuantity;

    private int additionalQuantity;

    public OrderAmount(final String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public OrderAmount setBlocked(final boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public int getBaseQuantity() {
        return baseQuantity;
    }

    public OrderAmount setBaseQuantity(final int baseQuantity) {
        this.baseQuantity = baseQuantity;
        return this;
    }

    public int getAdditionalQuantity() {
        return additionalQuantity;
    }

    public OrderAmount setAdditionalQuantity(final int additionalQuantity) {
        this.additionalQuantity = additionalQuantity;
        return this;
    }
    
}
