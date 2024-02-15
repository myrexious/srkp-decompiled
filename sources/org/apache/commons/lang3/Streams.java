package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import org.apache.commons.lang3.Functions;
import org.apache.commons.lang3.Streams;

@Deprecated
/* loaded from: classes2.dex */
public class Streams {

    @Deprecated
    /* loaded from: classes2.dex */
    public static class FailableStream<O> {
        private Stream<O> stream;
        private boolean terminated;

        public FailableStream(Stream<O> stream) {
            this.stream = stream;
        }

        protected void assertNotTerminated() {
            if (this.terminated) {
                throw new IllegalStateException("This stream is already terminated.");
            }
        }

        protected void makeTerminated() {
            assertNotTerminated();
            this.terminated = true;
        }

        public FailableStream<O> filter(Functions.FailablePredicate<O, ?> failablePredicate) {
            assertNotTerminated();
            this.stream = this.stream.filter(Functions.asPredicate(failablePredicate));
            return this;
        }

        public void forEach(Functions.FailableConsumer<O, ?> failableConsumer) {
            makeTerminated();
            stream().forEach(Functions.asConsumer(failableConsumer));
        }

        public <A, R> R collect(Collector<? super O, A, R> collector) {
            makeTerminated();
            return (R) stream().collect(collector);
        }

        public <A, R> R collect(Supplier<R> supplier, BiConsumer<R, ? super O> biConsumer, BiConsumer<R, R> biConsumer2) {
            makeTerminated();
            return (R) stream().collect(supplier, biConsumer, biConsumer2);
        }

        public O reduce(O o, BinaryOperator<O> binaryOperator) {
            makeTerminated();
            return stream().reduce(o, binaryOperator);
        }

        public <R> FailableStream<R> map(Functions.FailableFunction<O, R, ?> failableFunction) {
            assertNotTerminated();
            return new FailableStream<>(this.stream.map(Functions.asFunction(failableFunction)));
        }

        public Stream<O> stream() {
            return this.stream;
        }

        public boolean allMatch(Functions.FailablePredicate<O, ?> failablePredicate) {
            assertNotTerminated();
            return stream().allMatch(Functions.asPredicate(failablePredicate));
        }

        public boolean anyMatch(Functions.FailablePredicate<O, ?> failablePredicate) {
            assertNotTerminated();
            return stream().anyMatch(Functions.asPredicate(failablePredicate));
        }
    }

    public static <O> FailableStream<O> stream(Stream<O> stream) {
        return new FailableStream<>(stream);
    }

    public static <O> FailableStream<O> stream(Collection<O> collection) {
        return stream(collection.stream());
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public static class ArrayCollector<O> implements Collector<O, List<O>, O[]> {
        private static final Set<Collector.Characteristics> characteristics = Collections.emptySet();
        private final Class<O> elementType;

        public static /* synthetic */ ArrayList $r8$lambda$gDKEcGxnaIgU7sqXbIS8kj9NQtE() {
            return new ArrayList();
        }

        public ArrayCollector(Class<O> cls) {
            this.elementType = cls;
        }

        @Override // java.util.stream.Collector
        public Supplier<List<O>> supplier() {
            return new Supplier() { // from class: org.apache.commons.lang3.Streams$ArrayCollector$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Streams.ArrayCollector.$r8$lambda$gDKEcGxnaIgU7sqXbIS8kj9NQtE();
                }
            };
        }

        @Override // java.util.stream.Collector
        public BiConsumer<List<O>, O> accumulator() {
            return new Streams$ArrayCollector$$ExternalSyntheticLambda3();
        }

        @Override // java.util.stream.Collector
        public BinaryOperator<List<O>> combiner() {
            return new BinaryOperator() { // from class: org.apache.commons.lang3.Streams$ArrayCollector$$ExternalSyntheticLambda1
                /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                    jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: org.apache.commons.lang3.Streams.ArrayCollector.lambda$combiner$0(java.util.List, java.util.List):java.util.List
                    	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:76)
                    Caused by: java.lang.NullPointerException
                    	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
                    	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
                    	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
                    	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
                    	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
                    	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:65)
                    */
                @Override // java.util.function.BiFunction
                public final java.lang.Object apply(java.lang.Object r1, java.lang.Object r2) {
                    /*
                        r0 = this;
                        java.util.List r1 = (java.util.List) r1
                        java.util.List r2 = (java.util.List) r2
                        java.util.List r1 = org.apache.commons.lang3.Streams.ArrayCollector.lambda$combiner$0(r1, r2)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.Streams$ArrayCollector$$ExternalSyntheticLambda1.apply(java.lang.Object, java.lang.Object):java.lang.Object");
                }
            };
        }

        /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
            java.lang.NullPointerException
            */
        public static /* synthetic */ java.util.List lambda$combiner$0(java.util.List r0, java.util.List r1) {
            /*
                r0.addAll(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.Streams.ArrayCollector.lambda$combiner$0(java.util.List, java.util.List):java.util.List");
        }

        @Override // java.util.stream.Collector
        public Function<List<O>, O[]> finisher() {
            return new Function() { // from class: org.apache.commons.lang3.Streams$ArrayCollector$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Streams.ArrayCollector.this.m1847xfe0fe9ee((List) obj);
                }
            };
        }

        /* renamed from: lambda$finisher$1$org-apache-commons-lang3-Streams$ArrayCollector */
        public /* synthetic */ Object[] m1847xfe0fe9ee(List list) {
            return list.toArray(ArrayUtils.newInstance(this.elementType, list.size()));
        }

        @Override // java.util.stream.Collector
        public Set<Collector.Characteristics> characteristics() {
            return characteristics;
        }
    }

    public static <O> Collector<O, ?, O[]> toArray(Class<O> cls) {
        return new ArrayCollector(cls);
    }
}
