package org.llaith.sunstone.adapter.easyrules;

import org.llaith.sunstone.api.transfer.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class OrderingAmount {

    private static final Logger logger = LoggerFactory.getLogger(OrderingAmount.class);
    private final String product;
    private boolean blocked;
    private int baseQuantity;
    private int additionalQuantity;

    public OrderingAmount(final String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(final boolean blocked) {
        this.blocked = blocked;
    }

    public int getBaseQuantity() {
        return baseQuantity;
    }

    public void setBaseQuantity(final int baseQuantity) {
        this.baseQuantity = baseQuantity;
    }

    public int getAdditionalQuantity() {
        return additionalQuantity;
    }

    public void setAdditionalQuantity(final int additionalQuantity) {
        this.additionalQuantity = additionalQuantity;
    }

    public OrderItem toOrderItem() {

        if (this.additionalQuantity > 0 && this.blocked) logger.warn(
                "Additional quantity: {} ordered of a blocked product: {}",
                additionalQuantity,
                product);

        final var amount = this.blocked
                ? 0
                : this.baseQuantity + this.additionalQuantity;

        return new OrderItem(this.product, amount);

    }

}
