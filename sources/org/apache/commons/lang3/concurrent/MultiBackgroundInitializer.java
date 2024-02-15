package org.apache.commons.lang3.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

/* loaded from: classes2.dex */
public class MultiBackgroundInitializer extends BackgroundInitializer<MultiBackgroundInitializerResults> {
    private final Map<String, BackgroundInitializer<?>> childInitializers;

    public MultiBackgroundInitializer() {
        this.childInitializers = new HashMap();
    }

    public MultiBackgroundInitializer(ExecutorService executorService) {
        super(executorService);
        this.childInitializers = new HashMap();
    }

    public void addInitializer(String str, BackgroundInitializer<?> backgroundInitializer) {
        Objects.requireNonNull(str, "name");
        Objects.requireNonNull(backgroundInitializer, "backgroundInitializer");
        synchronized (this) {
            if (isStarted()) {
                throw new IllegalStateException("addInitializer() must not be called after start()!");
            }
            this.childInitializers.put(str, backgroundInitializer);
        }
    }

    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer
    public int getTaskCount() {
        return this.childInitializers.values().stream().mapToInt(new ToIntFunction() { // from class: org.apache.commons.lang3.concurrent.MultiBackgroundInitializer$$ExternalSyntheticLambda2
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((BackgroundInitializer) obj).getTaskCount();
            }
        }).sum() + 1;
    }

    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer
    public MultiBackgroundInitializerResults initialize() throws Exception {
        HashMap hashMap;
        synchronized (this) {
            hashMap = new HashMap(this.childInitializers);
        }
        final ExecutorService activeExecutor = getActiveExecutor();
        hashMap.values().forEach(new Consumer() { // from class: org.apache.commons.lang3.concurrent.MultiBackgroundInitializer$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MultiBackgroundInitializer.lambda$initialize$0(activeExecutor, (BackgroundInitializer) obj);
            }
        });
        final HashMap hashMap2 = new HashMap();
        final HashMap hashMap3 = new HashMap();
        hashMap.forEach(new BiConsumer() { // from class: org.apache.commons.lang3.concurrent.MultiBackgroundInitializer$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                MultiBackgroundInitializer.lambda$initialize$1(hashMap2, hashMap3, (String) obj, (BackgroundInitializer) obj2);
            }
        });
        return new MultiBackgroundInitializerResults(hashMap, hashMap2, hashMap3);
    }

    public static /* synthetic */ void lambda$initialize$0(ExecutorService executorService, BackgroundInitializer backgroundInitializer) {
        if (backgroundInitializer.getExternalExecutor() == null) {
            backgroundInitializer.setExternalExecutor(executorService);
        }
        backgroundInitializer.start();
    }

    public static /* synthetic */ void lambda$initialize$1(Map map, Map map2, String str, BackgroundInitializer backgroundInitializer) {
        try {
            map.put(str, backgroundInitializer.get());
        } catch (ConcurrentException e) {
            map2.put(str, e);
        }
    }

    /* loaded from: classes2.dex */
    public static class MultiBackgroundInitializerResults {
        private final Map<String, ConcurrentException> exceptions;
        private final Map<String, BackgroundInitializer<?>> initializers;
        private final Map<String, Object> resultObjects;

        private MultiBackgroundInitializerResults(Map<String, BackgroundInitializer<?>> map, Map<String, Object> map2, Map<String, ConcurrentException> map3) {
            this.initializers = map;
            this.resultObjects = map2;
            this.exceptions = map3;
        }

        public BackgroundInitializer<?> getInitializer(String str) {
            return checkName(str);
        }

        public Object getResultObject(String str) {
            checkName(str);
            return this.resultObjects.get(str);
        }

        public boolean isException(String str) {
            checkName(str);
            return this.exceptions.containsKey(str);
        }

        public ConcurrentException getException(String str) {
            checkName(str);
            return this.exceptions.get(str);
        }

        public Set<String> initializerNames() {
            return Collections.unmodifiableSet(this.initializers.keySet());
        }

        public boolean isSuccessful() {
            return this.exceptions.isEmpty();
        }

        private BackgroundInitializer<?> checkName(String str) {
            BackgroundInitializer<?> backgroundInitializer = this.initializers.get(str);
            if (backgroundInitializer != null) {
                return backgroundInitializer;
            }
            throw new NoSuchElementException("No child initializer with name " + str);
        }
    }
}
