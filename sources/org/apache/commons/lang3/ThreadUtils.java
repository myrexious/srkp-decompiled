package org.apache.commons.lang3;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.ThreadUtils;
import org.apache.commons.lang3.function.FailableBiConsumer;
import org.apache.commons.lang3.time.DurationUtils;

/* loaded from: classes2.dex */
public class ThreadUtils {
    @Deprecated
    public static final AlwaysTruePredicate ALWAYS_TRUE_PREDICATE = new AlwaysTruePredicate();
    private static final Predicate<?> ALWAYS_TRUE = new Predicate() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda3
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return ThreadUtils.lambda$static$0(obj);
        }
    };

    @FunctionalInterface
    @Deprecated
    /* loaded from: classes2.dex */
    public interface ThreadGroupPredicate {
        boolean test(ThreadGroup threadGroup);
    }

    @FunctionalInterface
    @Deprecated
    /* loaded from: classes2.dex */
    public interface ThreadPredicate {
        boolean test(Thread thread);
    }

    public static /* synthetic */ boolean lambda$static$0(Object obj) {
        return true;
    }

    @Deprecated
    /* loaded from: classes2.dex */
    private static final class AlwaysTruePredicate implements ThreadPredicate, ThreadGroupPredicate {
        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return true;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadGroupPredicate
        public boolean test(ThreadGroup threadGroup) {
            return true;
        }

        private AlwaysTruePredicate() {
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public static class NamePredicate implements ThreadPredicate, ThreadGroupPredicate {
        private final String name;

        public NamePredicate(String str) {
            Objects.requireNonNull(str, "name");
            this.name = str;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return thread != null && thread.getName().equals(this.name);
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadGroupPredicate
        public boolean test(ThreadGroup threadGroup) {
            return threadGroup != null && threadGroup.getName().equals(this.name);
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public static class ThreadIdPredicate implements ThreadPredicate {
        private final long threadId;

        public ThreadIdPredicate(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("The thread id must be greater than zero");
            }
            this.threadId = j;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return thread != null && thread.getId() == this.threadId;
        }
    }

    private static <T> Predicate<T> alwaysTruePredicate() {
        return (Predicate<T>) ALWAYS_TRUE;
    }

    public static Thread findThreadById(final long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("The thread id must be greater than zero");
        }
        Collection<Thread> findThreads = findThreads(new Predicate() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ThreadUtils.lambda$findThreadById$1(j, (Thread) obj);
            }
        });
        if (findThreads.isEmpty()) {
            return null;
        }
        return findThreads.iterator().next();
    }

    public static /* synthetic */ boolean lambda$findThreadById$1(long j, Thread thread) {
        return thread != null && thread.getId() == j;
    }

    public static Thread findThreadById(long j, String str) {
        Objects.requireNonNull(str, "threadGroupName");
        Thread findThreadById = findThreadById(j);
        if (findThreadById == null || findThreadById.getThreadGroup() == null || !findThreadById.getThreadGroup().getName().equals(str)) {
            return null;
        }
        return findThreadById;
    }

    public static Thread findThreadById(long j, ThreadGroup threadGroup) {
        Objects.requireNonNull(threadGroup, "threadGroup");
        Thread findThreadById = findThreadById(j);
        if (findThreadById == null || !threadGroup.equals(findThreadById.getThreadGroup())) {
            return null;
        }
        return findThreadById;
    }

    public static Collection<ThreadGroup> findThreadGroups(Predicate<ThreadGroup> predicate) {
        return findThreadGroups(getSystemThreadGroup(), true, predicate);
    }

    public static Collection<ThreadGroup> findThreadGroups(ThreadGroup threadGroup, boolean z, Predicate<ThreadGroup> predicate) {
        Objects.requireNonNull(threadGroup, "threadGroup");
        Objects.requireNonNull(predicate, "predicate");
        int activeGroupCount = threadGroup.activeGroupCount();
        while (true) {
            int i = activeGroupCount + (activeGroupCount / 2) + 1;
            ThreadGroup[] threadGroupArr = new ThreadGroup[i];
            int enumerate = threadGroup.enumerate(threadGroupArr, z);
            if (enumerate < i) {
                return Collections.unmodifiableCollection((Collection) Stream.of((Object[]) threadGroupArr).filter(predicate).collect(Collectors.toList()));
            }
            activeGroupCount = enumerate;
        }
    }

    @Deprecated
    public static Collection<ThreadGroup> findThreadGroups(ThreadGroup threadGroup, boolean z, final ThreadGroupPredicate threadGroupPredicate) {
        threadGroupPredicate.getClass();
        return findThreadGroups(threadGroup, z, new Predicate() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda7
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ThreadUtils.ThreadGroupPredicate.this.test((ThreadGroup) obj);
            }
        });
    }

    @Deprecated
    public static Collection<ThreadGroup> findThreadGroups(ThreadGroupPredicate threadGroupPredicate) {
        return findThreadGroups(getSystemThreadGroup(), true, threadGroupPredicate);
    }

    public static Collection<ThreadGroup> findThreadGroupsByName(String str) {
        return findThreadGroups(predicateThreadGroup(str));
    }

    public static Collection<Thread> findThreads(Predicate<Thread> predicate) {
        return findThreads(getSystemThreadGroup(), true, predicate);
    }

    public static Collection<Thread> findThreads(ThreadGroup threadGroup, boolean z, Predicate<Thread> predicate) {
        Objects.requireNonNull(threadGroup, "The group must not be null");
        Objects.requireNonNull(predicate, "The predicate must not be null");
        int activeCount = threadGroup.activeCount();
        while (true) {
            int i = activeCount + (activeCount / 2) + 1;
            Thread[] threadArr = new Thread[i];
            int enumerate = threadGroup.enumerate(threadArr, z);
            if (enumerate < i) {
                return Collections.unmodifiableCollection((Collection) Stream.of((Object[]) threadArr).filter(predicate).collect(Collectors.toList()));
            }
            activeCount = enumerate;
        }
    }

    @Deprecated
    public static Collection<Thread> findThreads(ThreadGroup threadGroup, boolean z, final ThreadPredicate threadPredicate) {
        threadPredicate.getClass();
        return findThreads(threadGroup, z, new Predicate() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ThreadUtils.ThreadPredicate.this.test((Thread) obj);
            }
        });
    }

    @Deprecated
    public static Collection<Thread> findThreads(ThreadPredicate threadPredicate) {
        return findThreads(getSystemThreadGroup(), true, threadPredicate);
    }

    public static Collection<Thread> findThreadsByName(String str) {
        return findThreads(predicateThread(str));
    }

    public static Collection<Thread> findThreadsByName(final String str, String str2) {
        Objects.requireNonNull(str, "threadName");
        Objects.requireNonNull(str2, "threadGroupName");
        return Collections.unmodifiableCollection((Collection) findThreadGroups(predicateThreadGroup(str2)).stream().flatMap(new Function() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream stream;
                stream = ThreadUtils.findThreads((ThreadGroup) obj, false, ThreadUtils.predicateThread(str)).stream();
                return stream;
            }
        }).collect(Collectors.toList()));
    }

    public static Collection<Thread> findThreadsByName(String str, ThreadGroup threadGroup) {
        return findThreads(threadGroup, false, predicateThread(str));
    }

    public static Collection<ThreadGroup> getAllThreadGroups() {
        return findThreadGroups(alwaysTruePredicate());
    }

    public static Collection<Thread> getAllThreads() {
        return findThreads(alwaysTruePredicate());
    }

    public static ThreadGroup getSystemThreadGroup() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup != null && threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        return threadGroup;
    }

    public static void join(final Thread thread, Duration duration) throws InterruptedException {
        thread.getClass();
        DurationUtils.accept(new FailableBiConsumer() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableBiConsumer
            public final void accept(Object obj, Object obj2) {
                thread.join(((Long) obj).longValue(), ((Integer) obj2).intValue());
            }
        }, duration);
    }

    public static /* synthetic */ boolean lambda$namePredicate$3(Function function, String str, Object obj) {
        return obj != null && Objects.equals(function.apply(obj), Objects.requireNonNull(str));
    }

    private static <T> Predicate<T> namePredicate(final String str, final Function<T, String> function) {
        return new Predicate() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ThreadUtils.lambda$namePredicate$3(function, str, obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Predicate<Thread> predicateThread(String str) {
        return namePredicate(str, new Function() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String name;
                name = ((Thread) obj).getName();
                return name;
            }
        });
    }

    private static Predicate<ThreadGroup> predicateThreadGroup(String str) {
        return namePredicate(str, new Function() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String name;
                name = ((ThreadGroup) obj).getName();
                return name;
            }
        });
    }

    public static void sleep(Duration duration) throws InterruptedException {
        DurationUtils.accept(new FailableBiConsumer() { // from class: org.apache.commons.lang3.ThreadUtils$$ExternalSyntheticLambda6
            @Override // org.apache.commons.lang3.function.FailableBiConsumer
            public final void accept(Object obj, Object obj2) {
                Thread.sleep(((Long) obj).longValue(), ((Integer) obj2).intValue());
            }
        }, duration);
    }

    public static void sleepQuietly(Duration duration) {
        try {
            sleep(duration);
        } catch (InterruptedException unused) {
        }
    }
}
