/*
 * Copyright (c) 2016.
 */

package org.llaith.sunstone.toolkit.guard.core;


/**
 * Used to turn a checked exception into a runtime one. If looking for the more
 * general unwrap routines that used to be ni ExceptionUtils, they aint here
 * either. Those were deleted in favour of Guavas propagate exception utils.
 * <p>
 * Design: we do not wrap() an exception and pass a msg because it may not
 * be used, which makes debugging weird. Choose either to create an UncheckedException
 * with a distinct message and use the ctors natively, or just to wrap() a
 * checked exception and if so, pass that without any more input.
 */
public class UncheckedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static RuntimeException wrap(final Throwable t) {
        return wrap(t.getMessage() + " (wrapped)", t);
    }

    public static RuntimeException wrap(final String message, final Throwable t) {
        if (t instanceof Error) throw (Error)t;
        if (t instanceof RuntimeException) return (RuntimeException)t;
        return new UncheckedException(message, t);
    }

    public UncheckedException(final String message) {
        super(message);
    }

    public UncheckedException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
