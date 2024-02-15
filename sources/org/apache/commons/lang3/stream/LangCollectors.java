package org.apache.commons.lang3.stream;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* loaded from: classes2.dex */
public final class LangCollectors {
    private static final Set<Collector.Characteristics> CH_NOID = Collections.emptySet();

    public static /* synthetic */ StringBuilder $r8$lambda$z4Sacp0dHgNB96bTlXZdt5SF0q4() {
        return new StringBuilder();
    }

    /* loaded from: classes2.dex */
    public static class SimpleCollector<T, A, R> implements Collector<T, A, R> {
        private final BiConsumer<A, T> accumulator;
        private final Set<Collector.Characteristics> characteristics;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Supplier<A> supplier;

        private SimpleCollector(Supplier<A> supplier, BiConsumer<A, T> biConsumer, BinaryOperator<A> binaryOperator, Function<A, R> function, Set<Collector.Characteristics> set) {
            this.supplier = supplier;
            this.accumulator = biConsumer;
            this.combiner = binaryOperator;
            this.finisher = function;
            this.characteristics = set;
        }

        @Override // java.util.stream.Collector
        public BiConsumer<A, T> accumulator() {
            return this.accumulator;
        }

        @Override // java.util.stream.Collector
        public Set<Collector.Characteristics> characteristics() {
            return this.characteristics;
        }

        @Override // java.util.stream.Collector
        public BinaryOperator<A> combiner() {
            return this.combiner;
        }

        @Override // java.util.stream.Collector
        public Function<A, R> finisher() {
            return this.finisher;
        }

        @Override // java.util.stream.Collector
        public Supplier<A> supplier() {
            return this.supplier;
        }
    }

    public static Collector<Object, ?, String> joining() {
        return new SimpleCollector(new Supplier() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return LangCollectors.$r8$lambda$z4Sacp0dHgNB96bTlXZdt5SF0q4();
            }
        }, new BiConsumer() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((StringBuilder) obj).append(obj2);
            }
        }, new BinaryOperator() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda7
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                StringBuilder append;
                append = ((StringBuilder) obj).append((CharSequence) ((StringBuilder) obj2));
                return append;
            }
        }, new Function() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String sb;
                sb = ((StringBuilder) obj).toString();
                return sb;
            }
        }, CH_NOID);
    }

    public static Collector<Object, ?, String> joining(CharSequence charSequence) {
        return joining(charSequence, "", "");
    }

    public static Collector<Object, ?, String> joining(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return joining(charSequence, charSequence2, charSequence3, new Function() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String objects;
                objects = Objects.toString(obj);
                return objects;
            }
        });
    }

    public static Collector<Object, ?, String> joining(final CharSequence charSequence, final CharSequence charSequence2, final CharSequence charSequence3, final Function<Object, String> function) {
        return new SimpleCollector(new Supplier() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return LangCollectors.lambda$joining$0(charSequence, charSequence2, charSequence3);
            }
        }, new BiConsumer() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((StringJoiner) obj).add((CharSequence) function.apply(obj2));
            }
        }, new BinaryOperator() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda2
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                StringJoiner merge;
                merge = ((StringJoiner) obj).merge((StringJoiner) obj2);
                return merge;
            }
        }, new Function() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String stringJoiner;
                stringJoiner = ((StringJoiner) obj).toString();
                return stringJoiner;
            }
        }, CH_NOID);
    }

    public static /* synthetic */ StringJoiner lambda$joining$0(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return new StringJoiner(charSequence, charSequence2, charSequence3);
    }

    private LangCollectors() {
    }
}
