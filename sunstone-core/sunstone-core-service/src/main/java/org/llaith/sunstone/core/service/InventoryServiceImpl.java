package org.llaith.sunstone.core.service;

import org.llaith.sunstone.api.InventoryService;
import org.llaith.sunstone.api.exception.InsufficientInventoryException;
import org.llaith.sunstone.api.exception.UnknownProductException;
import org.llaith.sunstone.api.transfer.InventoryLevel;
import org.llaith.sunstone.core.spi.InventoryRepository;

import java.util.Objects;

/**
 *
 */
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    public InventoryServiceImpl(final InventoryRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public InventoryLevel getInventory(final String name) {
        return expect(name);
    }

    @Override
    public void withdrawInventory(final String name, final int quantity) {

        var existing = expect(name);

        if (quantity > existing.getRemainingQuantity())
            throw new InsufficientInventoryException(name, quantity, existing.getAdditionalQuantity());

        var current = new InventoryLevel(
                name,
                existing.getRemainingQuantity() - quantity,
                existing.getAdditionalQuantity());

        this.repository.storeInventory(current);

    }

    @Override
    public void depositInventory(final String name, final int quantity) {

        var existing = expect(name);

        var current = new InventoryLevel(
                name,
                existing.getRemainingQuantity() + quantity,
                existing.getAdditionalQuantity());

        this.repository.storeInventory(current);

    }

    @Override
    public void requestAdditionalOrder(final String name, final int quantity) {

        var existing = expect(name);

        var current = new InventoryLevel(
                name,
                existing.getRemainingQuantity(),
                existing.getAdditionalQuantity() + quantity);

        this.repository.storeInventory(current);

    }

    private InventoryLevel expect(final String name) {
        final var inventory = this.repository.getInventory(name);
        if (inventory == null) throw new UnknownProductException(name);
        return inventory;
    }

}
