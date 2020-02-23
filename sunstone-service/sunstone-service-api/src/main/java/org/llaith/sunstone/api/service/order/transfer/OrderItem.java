package org.llaith.sunstone.api.service.order.transfer;

import java.util.Date;

import static java.util.Objects.requireNonNull;

/**
 *
 */
public class OrderItem {

    private final Date date;
    private final String product;
    private final int quantity;

    public OrderItem(final Date date, final String product, final int quantity) {
        this.date = requireNonNull(date);
        this.product = requireNonNull(product);
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

}
