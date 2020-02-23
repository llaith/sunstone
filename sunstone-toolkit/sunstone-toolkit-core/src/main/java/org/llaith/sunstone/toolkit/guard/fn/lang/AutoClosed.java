/*
 * Copyright (c) 2016.
 */

package org.llaith.sunstone.toolkit.guard.fn.lang;

/**
 *
 */
public interface AutoClosed extends TypedClosable<RuntimeException> {

    static AutoClosed of(final AutoClosed autoClosed) {
        return autoClosed;   
    }
    
}
