/*
 * Copyright (c) 2016.
 */

package org.llaith.sunstone.toolkit.guard.core;

import com.google.common.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;


/**
 *
 */
public class Instances {

    @SuppressWarnings("unchecked")
    public static <X> X newInstance(final String name) {

        return (X)newInstance(forName(name));

    }

    public static <X> X newInstance(final Class<? extends X> klass) {

        try {

            return klass
                    .getConstructor()
                    .newInstance();

        } catch (Exception e) {

            throw UncheckedException.wrap(e);

        }
    }

    @SuppressWarnings("unchecked")
    public static <X> X newInstance(final TypeToken<X> token) {

        try {

            return (X)token
                    .getRawType()
                    .getConstructor()
                    .newInstance();

        } catch (InvocationTargetException | NoSuchMethodException
                | IllegalAccessException | InstantiationException e) {

            throw UncheckedException.wrap(e);

        }

    }

    @SuppressWarnings("unchecked")
    public static <X> Class<X> forName(final String name) {

        try {
            // happy to get a ClassCastException.
            return (Class<X>)Class.forName(name);

        } catch (ClassNotFoundException e) {

            throw UncheckedException.wrap(e);

        }

    }

    @SuppressWarnings("unchecked")
    public boolean[] newInstanceNativeBooleanArray(final int... dim) {

        return (boolean[])Array.newInstance(Boolean.TYPE, dim);

    }

    @SuppressWarnings("unchecked")
    public char[] newInstanceNativeCharArray(final int... dim) {

        return (char[])Array.newInstance(Character.TYPE, dim);

    }

    @SuppressWarnings("unchecked")
    public byte[] newInstanceNativeByteArray(final int... dim) {

        return (byte[])Array.newInstance(Byte.TYPE, dim);

    }

    @SuppressWarnings("unchecked")
    public short[] newInstanceNativeShortArray(final int... dim) {

        return (short[])Array.newInstance(Short.TYPE, dim);

    }

    @SuppressWarnings("unchecked")
    public int[] newInstanceNativeIntArray(final int... dim) {

        return (int[])Array.newInstance(Integer.TYPE, dim);

    }

    @SuppressWarnings("unchecked")
    public long[] newInstanceNativeLongArray(final int... dim) {

        return (long[])Array.newInstance(Long.TYPE, dim);

    }

    @SuppressWarnings("unchecked")
    public float[] newInstanceNativeFloatArray(final int... dim) {

        return (float[])Array.newInstance(Float.TYPE, dim);

    }

    @SuppressWarnings("unchecked")
    public double[] newInstanceNativeDoubleArray(final int... dim) {

        return (double[])Array.newInstance(Double.TYPE, dim);

    }

    private Instances() {}

}
