package org.llaith.sunstone.adapter.jdbi;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.llaith.sunstone.adapter.jdbi.dao.OrderItemDao;
import org.llaith.sunstone.adapter.jdbi.dao.OrderItemEntity;
import org.llaith.sunstone.api.transfer.OrderItem;
import org.llaith.sunstone.core.spi.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.llaith.sunstone.adapter.jdbi.dao.OrderItemEntity.fromOrderItem;

/**
 *
 */
public class OrderRepositoryJdbiImpl implements OrderRepository {

    public static final String TEST_MEMDB_JVM = "jdbc:h2:mem:order_test;DB_CLOSE_DELAY=-1";
    public static final String TEST_MEMDB_CONN = "jdbc:h2:mem:order_test";

    private final Jdbi jdbi;

    public OrderRepositoryJdbiImpl() {
        this(TEST_MEMDB_JVM);
    }

    public OrderRepositoryJdbiImpl(final String connectionString) {

        this.jdbi = Jdbi.create(connectionString);
        this.jdbi.installPlugin(new SqlObjectPlugin());

        this.jdbi.useExtension(OrderItemDao.class, OrderItemDao::createTable);

    }

    @Override
    public void storeOrderItem(final OrderItem orderItem) {

        this.jdbi.useExtension(
                OrderItemDao.class,
                dao -> dao.upsert(fromOrderItem(orderItem)));

    }

    @Override
    public List<OrderItem> orderItems(final String product) {

        return this.jdbi.withExtension(
                OrderItemDao.class,
                dao -> dao.listForProduct(product))
                        .stream()
                        .map(OrderItemEntity::toOrderItem)
                        .collect(Collectors.toList());

    }

    protected void resetDb() {
        this.jdbi.useExtension(
                OrderItemDao.class,
                OrderItemDao::clearTable);
    }

}
