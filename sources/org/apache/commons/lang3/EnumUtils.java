package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* loaded from: classes2.dex */
public class EnumUtils {
    private static final String CANNOT_STORE_S_S_VALUES_IN_S_BITS = "Cannot store %s %s values in %s bits";
    private static final String ENUM_CLASS_MUST_BE_DEFINED = "EnumClass must be defined.";
    private static final String NULL_ELEMENTS_NOT_PERMITTED = "null elements not permitted";
    private static final String S_DOES_NOT_SEEM_TO_BE_AN_ENUM_TYPE = "%s does not seem to be an Enum type";

    private static <E extends Enum<E>> Class<E> asEnum(Class<E> cls) {
        Objects.requireNonNull(cls, ENUM_CLASS_MUST_BE_DEFINED);
        Validate.isTrue(cls.isEnum(), S_DOES_NOT_SEEM_TO_BE_AN_ENUM_TYPE, cls);
        return cls;
    }

    private static <E extends Enum<E>> Class<E> checkBitVectorable(Class<E> cls) {
        Enum[] enumArr = (Enum[]) asEnum(cls).getEnumConstants();
        Validate.isTrue(enumArr.length <= 64, CANNOT_STORE_S_S_VALUES_IN_S_BITS, Integer.valueOf(enumArr.length), cls.getSimpleName(), 64);
        return cls;
    }

    @SafeVarargs
    public static <E extends Enum<E>> long generateBitVector(Class<E> cls, E... eArr) {
        Validate.noNullElements(eArr);
        return generateBitVector(cls, Arrays.asList(eArr));
    }

    public static <E extends Enum<E>> long generateBitVector(Class<E> cls, Iterable<? extends E> iterable) {
        checkBitVectorable(cls);
        Objects.requireNonNull(iterable, "values");
        long j = 0;
        for (E e : iterable) {
            Objects.requireNonNull(e, NULL_ELEMENTS_NOT_PERMITTED);
            j |= 1 << e.ordinal();
        }
        return j;
    }

    @SafeVarargs
    public static <E extends Enum<E>> long[] generateBitVectors(Class<E> cls, E... eArr) {
        asEnum(cls);
        Validate.noNullElements(eArr);
        EnumSet noneOf = EnumSet.noneOf(cls);
        Collections.addAll(noneOf, eArr);
        long[] jArr = new long[((cls.getEnumConstants().length - 1) / 64) + 1];
        Iterator it = noneOf.iterator();
        while (it.hasNext()) {
            Enum r0 = (Enum) it.next();
            int ordinal = r0.ordinal() / 64;
            jArr[ordinal] = jArr[ordinal] | (1 << (r0.ordinal() % 64));
        }
        ArrayUtils.reverse(jArr);
        return jArr;
    }

    public static <E extends Enum<E>> long[] generateBitVectors(Class<E> cls, Iterable<? extends E> iterable) {
        asEnum(cls);
        Objects.requireNonNull(iterable, "values");
        final EnumSet noneOf = EnumSet.noneOf(cls);
        iterable.forEach(new Consumer() { // from class: org.apache.commons.lang3.EnumUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                noneOf.add(Objects.requireNonNull((Enum) obj, EnumUtils.NULL_ELEMENTS_NOT_PERMITTED));
            }
        });
        long[] jArr = new long[((cls.getEnumConstants().length - 1) / 64) + 1];
        Iterator it = noneOf.iterator();
        while (it.hasNext()) {
            Enum r0 = (Enum) it.next();
            int ordinal = r0.ordinal() / 64;
            jArr[ordinal] = jArr[ordinal] | (1 << (r0.ordinal() % 64));
        }
        ArrayUtils.reverse(jArr);
        return jArr;
    }

    public static <E extends Enum<E>> E getEnum(Class<E> cls, String str) {
        return (E) getEnum(cls, str, null);
    }

    public static <E extends Enum<E>> E getEnum(Class<E> cls, String str, E e) {
        if (str == null) {
            return e;
        }
        try {
            return (E) Enum.valueOf(cls, str);
        } catch (IllegalArgumentException unused) {
            return e;
        }
    }

    public static <E extends Enum<E>> E getEnumIgnoreCase(Class<E> cls, String str) {
        return (E) getEnumIgnoreCase(cls, str, null);
    }

    public static <E extends Enum<E>> E getEnumIgnoreCase(Class<E> cls, String str, E e) {
        return (E) getFirstEnumIgnoreCase(cls, str, new EnumUtils$$ExternalSyntheticLambda1(), e);
    }

    public static <E extends Enum<E>> List<E> getEnumList(Class<E> cls) {
        return new ArrayList(Arrays.asList(cls.getEnumConstants()));
    }

    public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> cls) {
        return getEnumMap(cls, new EnumUtils$$ExternalSyntheticLambda1());
    }

    public static <E extends Enum<E>, K> Map<K, E> getEnumMap(Class<E> cls, final Function<E, K> function) {
        Stream of = Stream.of((Object[]) cls.getEnumConstants());
        function.getClass();
        return (Map) of.collect(Collectors.toMap(new Function() { // from class: org.apache.commons.lang3.EnumUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return function.apply((Enum) obj);
            }
        }, Function.identity()));
    }

    public static <E extends Enum<E>> E getEnumSystemProperty(Class<E> cls, String str, E e) {
        return (cls == null || str == null) ? e : (E) getEnum(cls, System.getProperty(str), e);
    }

    public static <E extends Enum<E>> E getFirstEnumIgnoreCase(Class<E> cls, final String str, final Function<E, String> function, E e) {
        return (str == null || !cls.isEnum()) ? e : (E) Stream.of((Object[]) cls.getEnumConstants()).filter(new Predicate() { // from class: org.apache.commons.lang3.EnumUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equalsIgnoreCase;
                equalsIgnoreCase = str.equalsIgnoreCase((String) function.apply((Enum) obj));
                return equalsIgnoreCase;
            }
        }).findFirst().orElse(e);
    }

    public static <E extends Enum<E>> boolean isValidEnum(Class<E> cls, String str) {
        return getEnum(cls, str) != null;
    }

    public static <E extends Enum<E>> boolean isValidEnumIgnoreCase(Class<E> cls, String str) {
        return getEnumIgnoreCase(cls, str) != null;
    }

    public static <E extends Enum<E>> EnumSet<E> processBitVector(Class<E> cls, long j) {
        checkBitVectorable(cls).getEnumConstants();
        return processBitVectors(cls, j);
    }

    public static <E extends Enum<E>> EnumSet<E> processBitVectors(Class<E> cls, long... jArr) {
        E[] enumConstants;
        EnumSet<E> noneOf = EnumSet.noneOf(asEnum(cls));
        long[] clone = ArrayUtils.clone((long[]) Objects.requireNonNull(jArr, "values"));
        ArrayUtils.reverse(clone);
        for (E e : cls.getEnumConstants()) {
            int ordinal = e.ordinal() / 64;
            if (ordinal < clone.length && (clone[ordinal] & (1 << (e.ordinal() % 64))) != 0) {
                noneOf.add(e);
            }
        }
        return noneOf;
    }
}
