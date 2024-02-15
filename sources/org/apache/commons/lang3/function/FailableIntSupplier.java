package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface FailableIntSupplier<E extends Throwable> {
    int getAsInt() throws Throwable;
}
