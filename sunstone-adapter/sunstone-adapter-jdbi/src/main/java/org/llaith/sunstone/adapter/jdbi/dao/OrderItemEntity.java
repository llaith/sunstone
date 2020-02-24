package org.llaith.sunstone.adapter.jdbi.dao;

import org.llaith.sunstone.api.transfer.OrderItem;

import java.time.Instant;

/**
 *
 */
public class OrderItemEntity {

    private String id;
    private String name;
    private int quantity;
    private Instant created;

    public static OrderItemEntity fromOrderItem(OrderItem item) {

        var entity = new OrderItemEntity();

        entity.setId(item.getId());
        entity.setName(item.getName());
        entity.setQuantity(item.getQuantity());
        entity.setCreated(item.getCreated());

        return entity;

    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public OrderItem toOrderItem() {
        return new OrderItem(this.id, this.name, this.quantity, this.created);
    }

}
