package org.llaith.sunstone.api.service.order;

import org.llaith.sunstone.api.service.UnknownProductException;
import org.llaith.sunstone.api.service.order.transfer.OrderItem;

import java.util.List;

/**
 *
 */
public interface OrderService {

    void addOrderItem(OrderItem orderItem) throws UnknownProductException;

    List<OrderItem> ordersFor(String product) throws UnknownProductException;

}
