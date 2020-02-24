package org.llaith.sunstone.adapter.jdbi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llaith.sunstone.api.transfer.OrderItem;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.llaith.sunstone.adapter.jdbi.OrderRepositoryJdbiImpl.TEST_MEMDB_JVM;

/**
 *
 */
class OrderRepositoryJdbiImplTest {

    private static OrderRepositoryJdbiImpl repository =
            new OrderRepositoryJdbiImpl(TEST_MEMDB_JVM);

    @BeforeEach
    void setUp() {
        repository.resetDb();
    }

    @Test
    void sanityCheckRepository() {

        var order = new OrderItem("product1", 10);

        repository.storeOrderItem(order);

        List<OrderItem> items = repository.orderItems("product1");

        assertThat(items)
                .isNotNull()
                .isNotEmpty()
                .containsExactly(order);

    }

    @Test
    void repositoryStoresMultipleItemsWithoutConflict() {

        var order1a = new OrderItem("product1", 10);
        var order1b = new OrderItem("product1", 5);
        var order2 = new OrderItem("product2", 20);

        repository.storeOrderItem(order1a);
        repository.storeOrderItem(order1b);
        repository.storeOrderItem(order2);

        List<OrderItem> items = repository.orderItems("product1");

        assertThat(items)
                .isNotNull()
                .isNotEmpty()
                .contains(order1a, order1b);

        List<OrderItem> items2 = repository.orderItems("product2");

        assertThat(items2)
                .isNotNull()
                .isNotEmpty()
                .containsExactly(order2);

    }

}
