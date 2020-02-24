package org.llaith.sunstone.adapter.easyrules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llaith.sunstone.api.transfer.InventoryLevel;
import org.llaith.sunstone.api.transfer.ProductConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 */
class OrderLogicRulesImplTest {

    private OrderLogicRulesImpl logic;

    @BeforeEach
    void setUp() {
        this.logic = new OrderLogicRulesImpl();
    }

    @Test
    void sanityCheckLogic() {

        var product = productOf("product1", 10, false);
        var inventory = inventoryOf("product1", 10, 0);

        var order = this.logic.reorderAmount(product, inventory);

        assertThat(order).isNotNull();
        assertThat(order.getId()).isNotEmpty();
        assertThat(order.getCreated()).isNotNull();
        assertThat(order.getName()).isEqualTo("product1");
        assertThat(order.getQuantity()).isEqualTo(0);

    }

    @Test
    void willReorderWhenBelowMinimum() {

        var product = productOf("product1", 10, false);
        var inventory = inventoryOf("product1", 5, 0);

        var order = this.logic.reorderAmount(product, inventory);

        assertThat(order.getQuantity()).isEqualTo(5);

    }

    @Test
    void willNotAllowBrokenValues() {

        assertThrows(IllegalArgumentException.class,
                     () -> productOf("product1", -1, false));

        assertThrows(IllegalArgumentException.class,
                     () -> inventoryOf("product1", -1, 1));

        assertThrows(IllegalArgumentException.class,
                     () -> inventoryOf("product1", 1, -1));

    }

    @Test
    void willReorderIncludingAdditionalAmounts() {

        var product = productOf("product1", 10, false);
        var inventory = inventoryOf("product1", 5, 10);

        var order = this.logic.reorderAmount(product, inventory);

        assertThat(order.getQuantity()).isEqualTo(15);

        var product2 = productOf("product2", 10, false);
        var inventory2 = inventoryOf("product2", 10, 10);

        var order2 = this.logic.reorderAmount(product2, inventory2);

        assertThat(order2.getQuantity()).isEqualTo(10);

    }

    @Test
    void willNotReorderWhenBlocked() {

        var product = productOf("product1", 10, true);
        var inventory = inventoryOf("product1", 5, 10);

        var order = this.logic.reorderAmount(product, inventory);

        assertThat(order.getQuantity()).isEqualTo(0);

    }

    private ProductConfig productOf(String name, int minAmount, boolean blocked) {
        return new ProductConfig(name, minAmount, blocked);
    }

    private InventoryLevel inventoryOf(String name, int currentAmount, int extraOrder) {
        return new InventoryLevel(name, currentAmount, extraOrder);
    }

}
