package org.informatika.sirekap.support;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppExecutors.kt */
@Singleton
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0017\u0018\u00002\u00020\u0001:\u0001\bB\u0007\b\u0017¢\u0006\u0002\u0010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/AppExecutors;", "", "()V", "diskIO", "Ljava/util/concurrent/Executor;", "networkIO", "mainThread", "(Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;)V", "MainThreadExecutor", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class AppExecutors {
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        Intrinsics.checkNotNullParameter(diskIO, "diskIO");
        Intrinsics.checkNotNullParameter(networkIO, "networkIO");
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @javax.inject.Inject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AppExecutors() {
        /*
            r3 = this;
            java.util.concurrent.ExecutorService r0 = java.util.concurrent.Executors.newSingleThreadExecutor()
            java.lang.String r1 = "newSingleThreadExecutor()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.util.concurrent.Executor r0 = (java.util.concurrent.Executor) r0
            r1 = 3
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newFixedThreadPool(r1)
            java.lang.String r2 = "newFixedThreadPool(3)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
            org.informatika.sirekap.support.AppExecutors$MainThreadExecutor r2 = new org.informatika.sirekap.support.AppExecutors$MainThreadExecutor
            r2.<init>()
            java.util.concurrent.Executor r2 = (java.util.concurrent.Executor) r2
            r3.<init>(r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.AppExecutors.<init>():void");
    }

    public final Executor diskIO() {
        return this.diskIO;
    }

    public final Executor networkIO() {
        return this.networkIO;
    }

    public final Executor mainThread() {
        return this.mainThread;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AppExecutors.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/informatika/sirekap/support/AppExecutors$MainThreadExecutor;", "Ljava/util/concurrent/Executor;", "()V", "mainThreadHandler", "Landroid/os/Handler;", "execute", "", "command", "Ljava/lang/Runnable;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public void execute(Runnable command) {
            Intrinsics.checkNotNullParameter(command, "command");
            this.mainThreadHandler.post(command);
        }
    }
}
