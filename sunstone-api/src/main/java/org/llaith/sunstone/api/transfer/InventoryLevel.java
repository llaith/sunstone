package org.llaith.sunstone.api.transfer;

/**
 *
 */
public class InventoryLevel {

    private final String name;
    private final int remaining;
    private final int additionalQuantity;

    public InventoryLevel(
            final String name,
            final int remaining,
            final int additionalQuantity) {

        this.name = name;
        this.remaining = expectPositive(remaining);
        this.additionalQuantity = expectPositive(additionalQuantity);

    }

    public String getName() {
        return name;
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
                "name='" + name + '\'' +
                ", remaining=" + remaining +
                ", additionalQuantity=" + additionalQuantity +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final InventoryLevel that = (InventoryLevel)o;

        if (remaining != that.remaining) return false;
        if (additionalQuantity != that.additionalQuantity) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + remaining;
        result = 31 * result + additionalQuantity;
        return result;
    }

    private int expectPositive(final int value) {
        if (value < 0) throw new IllegalArgumentException("Value must be positive");
        return value;
    }

}
