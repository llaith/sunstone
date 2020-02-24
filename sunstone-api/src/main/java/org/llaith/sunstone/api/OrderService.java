package org.llaith.sunstone.api;

import org.llaith.sunstone.api.transfer.OrderItem;

/**
 *
 */
public interface OrderService {

    OrderItem reorderItem(String product);

}
