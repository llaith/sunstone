package org.llaith.sunstone.api.service.ordering.transfer;

/**
 *
 */
public class InventoryLevel {

    private final String product;
    private final int remaining;
    private final int additionalQuantity;

    public InventoryLevel(
            final String product,
            final int remaining,
            final int additionalQuantity) {

        this.product = product;
        this.remaining = Math.min(remaining, 0);
        this.additionalQuantity = Math.min(additionalQuantity, 0);

    }

    public String getProduct() {
        return product;
    }

    public int getRemainingQuantity() {
        return remaining;
    }

    public int getAdditionalQuantity() {
        return additionalQuantity;
    }

    @Override
    public String toString() {
        return "StockLevel{" +
                "product='" + product + '\'' +
                ", remaining=" + remaining +
                ", additionalQuantity=" + additionalQuantity +
                '}';
    }

}
