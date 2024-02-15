package org.apache.commons.text.similarity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.commons.text.similarity.IntersectionSimilarity;

/* loaded from: classes2.dex */
public class IntersectionSimilarity<T> implements SimilarityScore<IntersectionResult> {
    private final Function<CharSequence, Collection<T>> converter;

    /* loaded from: classes2.dex */
    public static final class BagCount {
        private static final BagCount ZERO = new BagCount();
        int count;

        private BagCount() {
            this.count = 0;
        }
    }

    /* loaded from: classes2.dex */
    public class TinyBag {
        private final Map<T, BagCount> map;

        TinyBag(int i) {
            IntersectionSimilarity.this = r1;
            this.map = new HashMap(i);
        }

        public static /* synthetic */ BagCount lambda$add$0(Object obj) {
            return new BagCount();
        }

        public void add(T t) {
            this.map.computeIfAbsent(t, new Function() { // from class: org.apache.commons.text.similarity.IntersectionSimilarity$TinyBag$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return IntersectionSimilarity.TinyBag.lambda$add$0(obj);
                }
            }).count++;
        }

        Set<Map.Entry<T, BagCount>> entrySet() {
            return this.map.entrySet();
        }

        int getCount(Object obj) {
            return this.map.getOrDefault(obj, BagCount.ZERO).count;
        }

        int uniqueElementSize() {
            return this.map.size();
        }
    }

    private static <T> int getIntersection(Set<T> set, Set<T> set2) {
        int i = 0;
        for (T t : set) {
            if (set2.contains(t)) {
                i++;
            }
        }
        return i;
    }

    public IntersectionSimilarity(Function<CharSequence, Collection<T>> function) {
        if (function == null) {
            throw new IllegalArgumentException("Converter must not be null");
        }
        this.converter = function;
    }

    @Override // org.apache.commons.text.similarity.SimilarityScore
    public IntersectionResult apply(CharSequence charSequence, CharSequence charSequence2) {
        int intersection;
        if (charSequence == null || charSequence2 == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        Collection<T> apply = this.converter.apply(charSequence);
        Collection<T> apply2 = this.converter.apply(charSequence2);
        int size = apply.size();
        int size2 = apply2.size();
        if (Math.min(size, size2) == 0) {
            return new IntersectionResult(size, size2, 0);
        }
        if (!(apply instanceof Set) || !(apply2 instanceof Set)) {
            IntersectionSimilarity<T>.TinyBag bag = toBag(apply);
            IntersectionSimilarity<T>.TinyBag bag2 = toBag(apply2);
            if (bag.uniqueElementSize() < bag2.uniqueElementSize()) {
                intersection = getIntersection(bag, bag2);
            } else {
                intersection = getIntersection(bag2, bag);
            }
        } else if (size < size2) {
            intersection = getIntersection((Set) apply, (Set) apply2);
        } else {
            intersection = getIntersection((Set) apply2, (Set) apply);
        }
        return new IntersectionResult(size, size2, intersection);
    }

    private int getIntersection(IntersectionSimilarity<T>.TinyBag tinyBag, IntersectionSimilarity<T>.TinyBag tinyBag2) {
        int i = 0;
        for (Map.Entry<T, BagCount> entry : tinyBag.entrySet()) {
            i += Math.min(entry.getValue().count, tinyBag2.getCount(entry.getKey()));
        }
        return i;
    }

    private IntersectionSimilarity<T>.TinyBag toBag(Collection<T> collection) {
        final IntersectionSimilarity<T>.TinyBag tinyBag = new TinyBag(collection.size());
        Objects.requireNonNull(tinyBag);
        collection.forEach(new Consumer() { // from class: org.apache.commons.text.similarity.IntersectionSimilarity$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                IntersectionSimilarity.TinyBag.this.add(obj);
            }
        });
        return tinyBag;
    }
}
