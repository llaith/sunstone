package org.llaith.sunstone.api.transfer;

import java.time.Instant;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

/**
 *
 */
public class OrderItem {

    private final String id;
    private final String name;
    private final int quantity;
    private final Instant created;

    public OrderItem(final String name, final int quantity) {
        this(UUID.randomUUID().toString(), name, quantity, Instant.now());
    }

    public OrderItem(final String id, final String name, final int quantity, final Instant created) {
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.quantity = expectPositive(quantity);
        this.created = requireNonNull(created);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Instant getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final OrderItem orderItem = (OrderItem)o;

        if (quantity != orderItem.quantity) return false;
        if (!id.equals(orderItem.id)) return false;
        if (!name.equals(orderItem.name)) return false;
        return created.equals(orderItem.created);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + created.hashCode();
        return result;
    }

    private int expectPositive(final int value) {
        if (value < 0) throw new IllegalArgumentException("Value must be positive");
        return value;
    }

}
