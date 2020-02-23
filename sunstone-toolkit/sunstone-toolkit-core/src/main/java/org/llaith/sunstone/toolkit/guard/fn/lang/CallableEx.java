/*
 * Copyright (c) 2016.
 */

package org.llaith.sunstone.toolkit.guard.fn.lang;

import java.util.concurrent.Callable;

/**
 *
 */
public interface CallableEx<V,E extends Exception> extends Callable<V> {

    @Override
    V call() throws E;
    
}
