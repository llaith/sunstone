package org.llaith.sunstone.core.spi;

import org.llaith.sunstone.api.transfer.OrderItem;

import java.util.List;

/**
 *
 */
public interface OrderRepository {

    void storeOrderItem(OrderItem orderItem);

    List<OrderItem> orderItems(String product);

}
