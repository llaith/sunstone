package org.llaith.sunstone.adapter.jdbi.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 *
 */
public interface OrderItemDao {

    @SqlUpdate("CREATE TABLE orders (" +
            "id VARCHAR PRIMARY KEY, " +
            "name VARCHAR, " +
            "quantity INT, " +
            "created TIMESTAMP)")
    void createTable();

    @SqlUpdate("MERGE INTO " +
            "orders (id, name, quantity, created) " +
            "VALUES (:id, :name, :quantity, :created)")
    void upsert(@BindBean OrderItemEntity item);

    @SqlQuery("SELECT * FROM orders where name = :name ORDER BY created")
    @RegisterBeanMapper(OrderItemEntity.class)
    List<OrderItemEntity> listForProduct(String name);

    @SqlUpdate("DELETE FROM orders")
    void clearTable();

}
