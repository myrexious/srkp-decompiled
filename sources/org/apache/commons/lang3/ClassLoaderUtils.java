package org.apache.commons.lang3;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class ClassLoaderUtils {
    private static final URL[] EMPTY_URL_ARRAY = new URL[0];

    public static URL[] getSystemURLs() {
        return getURLs(ClassLoader.getSystemClassLoader());
    }

    public static URL[] getThreadURLs() {
        return getURLs(Thread.currentThread().getContextClassLoader());
    }

    private static URL[] getURLs(ClassLoader classLoader) {
        return classLoader instanceof URLClassLoader ? ((URLClassLoader) classLoader).getURLs() : EMPTY_URL_ARRAY;
    }

    public static String toString(ClassLoader classLoader) {
        if (classLoader instanceof URLClassLoader) {
            return toString((URLClassLoader) classLoader);
        }
        return classLoader.toString();
    }

    public static String toString(URLClassLoader uRLClassLoader) {
        return uRLClassLoader + Arrays.toString(uRLClassLoader.getURLs());
    }
}
