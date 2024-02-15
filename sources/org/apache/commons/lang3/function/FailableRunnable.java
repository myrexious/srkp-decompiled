package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface FailableRunnable<E extends Throwable> {
    void run() throws Throwable;
}
