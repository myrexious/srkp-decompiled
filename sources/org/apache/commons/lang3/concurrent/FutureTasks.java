package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* loaded from: classes2.dex */
public class FutureTasks {
    private FutureTasks() {
    }

    public static <V> FutureTask<V> run(Callable<V> callable) {
        FutureTask<V> futureTask = new FutureTask<>(callable);
        futureTask.run();
        return futureTask;
    }
}
