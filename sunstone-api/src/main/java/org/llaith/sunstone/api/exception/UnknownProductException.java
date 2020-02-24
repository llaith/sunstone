package org.llaith.sunstone.api.exception;

import static java.util.Objects.requireNonNull;

/**
 *
 */
public class UnknownProductException extends RuntimeException {

    private final String name;

    public UnknownProductException(final String name) {
        super(String.format("The product %s is not known", requireNonNull(name)));
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
