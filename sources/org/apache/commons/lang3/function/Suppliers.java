package org.apache.commons.lang3.function;

import java.util.function.Supplier;

/* loaded from: classes2.dex */
public class Suppliers {
    public static <T> T get(Supplier<T> supplier) {
        if (supplier == null) {
            return null;
        }
        return supplier.get();
    }
}
