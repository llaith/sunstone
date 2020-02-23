/*
 * Copyright (c) 2016.
 */

package org.llaith.sunstone.toolkit.guard.fn.lang;

import java.util.concurrent.Callable;

/**
 *
 */
public interface TypedCallable<V,E extends Exception> extends Callable<V> {

    V call() throws E;

}
