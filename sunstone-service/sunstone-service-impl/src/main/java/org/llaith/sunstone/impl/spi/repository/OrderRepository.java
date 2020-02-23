package org.llaith.sunstone.impl.spi.repository;

import org.llaith.sunstone.api.service.ordering.transfer.OrderItem;

import java.util.List;

/**
 *
 */
public interface OrderRepository {

    void storeOrderItem(OrderItem orderItem);

    List<OrderItem> orderItems(String product);

}
