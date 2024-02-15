package org.apache.commons.lang3.concurrent.locks;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Supplier;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableFunction;

/* loaded from: classes2.dex */
public class LockingVisitors {

    /* loaded from: classes2.dex */
    public static class LockVisitor<O, L> {
        private final L lock;
        private final O object;
        private final Supplier<Lock> readLockSupplier;
        private final Supplier<Lock> writeLockSupplier;

        protected LockVisitor(O o, L l, Supplier<Lock> supplier, Supplier<Lock> supplier2) {
            this.object = (O) Objects.requireNonNull(o, "object");
            this.lock = (L) Objects.requireNonNull(l, "lock");
            this.readLockSupplier = (Supplier) Objects.requireNonNull(supplier, "readLockSupplier");
            this.writeLockSupplier = (Supplier) Objects.requireNonNull(supplier2, "writeLockSupplier");
        }

        public void acceptReadLocked(FailableConsumer<O, ?> failableConsumer) {
            lockAcceptUnlock(this.readLockSupplier, failableConsumer);
        }

        public void acceptWriteLocked(FailableConsumer<O, ?> failableConsumer) {
            lockAcceptUnlock(this.writeLockSupplier, failableConsumer);
        }

        public <T> T applyReadLocked(FailableFunction<O, T, ?> failableFunction) {
            return (T) lockApplyUnlock(this.readLockSupplier, failableFunction);
        }

        public <T> T applyWriteLocked(FailableFunction<O, T, ?> failableFunction) {
            return (T) lockApplyUnlock(this.writeLockSupplier, failableFunction);
        }

        public L getLock() {
            return this.lock;
        }

        public O getObject() {
            return this.object;
        }

        protected void lockAcceptUnlock(Supplier<Lock> supplier, FailableConsumer<O, ?> failableConsumer) {
            supplier.get().lock();
            try {
                failableConsumer.accept(this.object);
            } finally {
            }
        }

        protected <T> T lockApplyUnlock(Supplier<Lock> supplier, FailableFunction<O, T, ?> failableFunction) {
            supplier.get().lock();
            try {
                return failableFunction.apply(this.object);
            } finally {
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ReadWriteLockVisitor<O> extends LockVisitor<O, ReadWriteLock> {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected ReadWriteLockVisitor(O r3, final java.util.concurrent.locks.ReadWriteLock r4) {
            /*
                r2 = this;
                r4.getClass()
                org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReadWriteLockVisitor$$ExternalSyntheticLambda0 r0 = new org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReadWriteLockVisitor$$ExternalSyntheticLambda0
                r0.<init>()
                r4.getClass()
                org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReadWriteLockVisitor$$ExternalSyntheticLambda1 r1 = new org.apache.commons.lang3.concurrent.locks.LockingVisitors$ReadWriteLockVisitor$$ExternalSyntheticLambda1
                r1.<init>()
                r2.<init>(r3, r4, r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.concurrent.locks.LockingVisitors.ReadWriteLockVisitor.<init>(java.lang.Object, java.util.concurrent.locks.ReadWriteLock):void");
        }
    }

    /* loaded from: classes2.dex */
    public static class StampedLockVisitor<O> extends LockVisitor<O, StampedLock> {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected StampedLockVisitor(O r3, final java.util.concurrent.locks.StampedLock r4) {
            /*
                r2 = this;
                r4.getClass()
                org.apache.commons.lang3.concurrent.locks.LockingVisitors$StampedLockVisitor$$ExternalSyntheticLambda0 r0 = new org.apache.commons.lang3.concurrent.locks.LockingVisitors$StampedLockVisitor$$ExternalSyntheticLambda0
                r0.<init>()
                r4.getClass()
                org.apache.commons.lang3.concurrent.locks.LockingVisitors$StampedLockVisitor$$ExternalSyntheticLambda1 r1 = new org.apache.commons.lang3.concurrent.locks.LockingVisitors$StampedLockVisitor$$ExternalSyntheticLambda1
                r1.<init>()
                r2.<init>(r3, r4, r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.concurrent.locks.LockingVisitors.StampedLockVisitor.<init>(java.lang.Object, java.util.concurrent.locks.StampedLock):void");
        }
    }

    public static <O> ReadWriteLockVisitor<O> create(O o, ReadWriteLock readWriteLock) {
        return new ReadWriteLockVisitor<>(o, readWriteLock);
    }

    public static <O> ReadWriteLockVisitor<O> reentrantReadWriteLockVisitor(O o) {
        return create(o, new ReentrantReadWriteLock());
    }

    public static <O> StampedLockVisitor<O> stampedLockVisitor(O o) {
        return new StampedLockVisitor<>(o, new StampedLock());
    }
}
