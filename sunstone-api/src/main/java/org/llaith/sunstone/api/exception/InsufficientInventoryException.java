package org.llaith.sunstone.api.exception;

import static java.util.Objects.requireNonNull;

/**
 *
 */
public class InsufficientInventoryException extends RuntimeException {

    private final String name;
    private final int requested;
    private final int balance;

    public InsufficientInventoryException(final String name, final int requested, final int balance) {

        super(String.format("The product %s has insufficient balance, %d requested but only %d in balance",
                            requireNonNull(name),
                            requested,
                            balance));

        this.name = name;
        this.requested = requested;
        this.balance = balance;

    }

    public String getName() {
        return name;
    }

    public int getRequested() {
        return requested;
    }

    public int getBalance() {
        return balance;
    }

}
