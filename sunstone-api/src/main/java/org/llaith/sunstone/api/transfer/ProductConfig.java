package org.llaith.sunstone.api.transfer;

import java.util.Objects;

/**
 *
 */
public class ProductConfig {

    private final String name;
    private final int minimumAmount;
    private final boolean blocked;

    public ProductConfig(final String name, final int minimumAmount, final boolean blocked) {
        this.name = Objects.requireNonNull(name);
        this.minimumAmount = expectPositive(minimumAmount);
        this.blocked = blocked;
    }

    public String getName() {
        return name;
    }

    public int getMinimumQuantity() {
        return minimumAmount;
    }

    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public String toString() {
        return "ProductConfig{" +
                "name='" + name + '\'' +
                ", minimumAmount=" + minimumAmount +
                ", blocked=" + blocked +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ProductConfig that = (ProductConfig)o;

        if (minimumAmount != that.minimumAmount) return false;
        if (blocked != that.blocked) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + minimumAmount;
        result = 31 * result + (blocked ? 1 : 0);
        return result;
    }

    private int expectPositive(final int value) {
        if (value < 0) throw new IllegalArgumentException("Value must be positive");
        return value;
    }

}
