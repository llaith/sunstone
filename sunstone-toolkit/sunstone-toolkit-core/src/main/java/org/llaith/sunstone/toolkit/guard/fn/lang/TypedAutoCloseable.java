package org.llaith.sunstone.toolkit.guard.fn.lang;

/**
 *
 */
public interface TypedAutoCloseable<T extends Exception> extends AutoCloseable {

    @Override
    void close() throws T;
    
}
