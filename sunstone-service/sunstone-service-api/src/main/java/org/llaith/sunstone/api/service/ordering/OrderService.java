package org.llaith.sunstone.api.service.ordering;

import org.llaith.sunstone.api.service.UnknownProductException;
import org.llaith.sunstone.api.service.ordering.transfer.OrderItem;

/**
 *
 */
public interface OrderService {

    OrderItem processOrderItem(String product) throws UnknownProductException;

}
