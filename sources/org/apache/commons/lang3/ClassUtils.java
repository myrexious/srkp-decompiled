package org.apache.commons.lang3;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import org.apache.commons.lang3.mutable.MutableObject;

/* loaded from: classes2.dex */
public class ClassUtils {
    public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
    public static final char PACKAGE_SEPARATOR_CHAR = '.';
    private static final Map<String, String> abbreviationMap;
    private static final Map<String, Class<?>> namePrimitiveMap;
    private static final Map<Class<?>, Class<?>> primitiveWrapperMap;
    private static final Map<String, String> reverseAbbreviationMap;
    private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap;
    private static final Comparator<Class<?>> COMPARATOR = new Comparator() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda4
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int compare;
            compare = Objects.compare(ClassUtils.getName((Class<?>) obj), ClassUtils.getName((Class<?>) obj2), new Comparator() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda2
                @Override // java.util.Comparator
                public final int compare(Object obj3, Object obj4) {
                    int compareTo;
                    compareTo = ((String) obj3).compareTo((String) obj4);
                    return compareTo;
                }
            });
            return compare;
        }
    };
    public static final String PACKAGE_SEPARATOR = String.valueOf('.');
    public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');

    /* loaded from: classes2.dex */
    public enum Interfaces {
        INCLUDE,
        EXCLUDE
    }

    private static boolean useFull(int i, int i2, int i3, int i4) {
        return i2 >= i3 || (i + i3) - i2 <= i4;
    }

    static {
        HashMap hashMap = new HashMap();
        namePrimitiveMap = hashMap;
        hashMap.put(TypedValues.Custom.S_BOOLEAN, Boolean.TYPE);
        hashMap.put("byte", Byte.TYPE);
        hashMap.put("char", Character.TYPE);
        hashMap.put("short", Short.TYPE);
        hashMap.put("int", Integer.TYPE);
        hashMap.put("long", Long.TYPE);
        hashMap.put("double", Double.TYPE);
        hashMap.put(TypedValues.Custom.S_FLOAT, Float.TYPE);
        hashMap.put("void", Void.TYPE);
        HashMap hashMap2 = new HashMap();
        primitiveWrapperMap = hashMap2;
        hashMap2.put(Boolean.TYPE, Boolean.class);
        hashMap2.put(Byte.TYPE, Byte.class);
        hashMap2.put(Character.TYPE, Character.class);
        hashMap2.put(Short.TYPE, Short.class);
        hashMap2.put(Integer.TYPE, Integer.class);
        hashMap2.put(Long.TYPE, Long.class);
        hashMap2.put(Double.TYPE, Double.class);
        hashMap2.put(Float.TYPE, Float.class);
        hashMap2.put(Void.TYPE, Void.TYPE);
        wrapperPrimitiveMap = new HashMap();
        hashMap2.forEach(new BiConsumer() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ClassUtils.lambda$static$1((Class) obj, (Class) obj2);
            }
        });
        HashMap hashMap3 = new HashMap();
        hashMap3.put("int", "I");
        hashMap3.put(TypedValues.Custom.S_BOOLEAN, "Z");
        hashMap3.put(TypedValues.Custom.S_FLOAT, "F");
        hashMap3.put("long", OperatorName.SET_LINE_CAPSTYLE);
        hashMap3.put("short", "S");
        hashMap3.put("byte", "B");
        hashMap3.put("double", "D");
        hashMap3.put("char", "C");
        abbreviationMap = Collections.unmodifiableMap(hashMap3);
        reverseAbbreviationMap = Collections.unmodifiableMap((Map) hashMap3.entrySet().stream().collect(Collectors.toMap(new Function() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (String) ((Map.Entry) obj).getValue();
            }
        }, new Function() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (String) ((Map.Entry) obj).getKey();
            }
        })));
    }

    public static /* synthetic */ void lambda$static$1(Class cls, Class cls2) {
        if (cls.equals(cls2)) {
            return;
        }
        wrapperPrimitiveMap.put(cls2, cls);
    }

    public static Comparator<Class<?>> comparator() {
        return COMPARATOR;
    }

    public static List<String> convertClassesToClassNames(List<Class<?>> list) {
        if (list == null) {
            return null;
        }
        return (List) list.stream().map(new Function() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String name;
                name = ClassUtils.getName((Class<?>) obj, (String) null);
                return name;
            }
        }).collect(Collectors.toList());
    }

    public static List<Class<?>> convertClassNamesToClasses(List<String> list) {
        if (list == null) {
            return null;
        }
        final ArrayList arrayList = new ArrayList(list.size());
        list.forEach(new Consumer() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ClassUtils.lambda$convertClassNamesToClasses$3(arrayList, (String) obj);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ void lambda$convertClassNamesToClasses$3(List list, String str) {
        try {
            list.add(Class.forName(str));
        } catch (Exception unused) {
            list.add(null);
        }
    }

    public static String getAbbreviatedName(Class<?> cls, int i) {
        return cls == null ? "" : getAbbreviatedName(cls.getName(), i);
    }

    public static String getAbbreviatedName(String str, int i) {
        char c;
        if (i > 0) {
            if (str == null) {
                return "";
            }
            if (str.length() <= i) {
                return str;
            }
            char[] charArray = str.toCharArray();
            int i2 = 0;
            int i3 = 0;
            while (i2 < charArray.length) {
                int i4 = i3;
                while (i2 < charArray.length && (c = charArray[i2]) != '.') {
                    i2++;
                    charArray[i4] = c;
                    i4++;
                }
                int i5 = i3 + 1;
                if (!useFull(i4, i2, charArray.length, i) && i5 <= i4) {
                    i4 = i5;
                }
                if (i2 < charArray.length) {
                    i3 = i4 + 1;
                    charArray[i4] = charArray[i2];
                    i2++;
                } else {
                    i3 = i4;
                }
            }
            return new String(charArray, 0, i3);
        }
        throw new IllegalArgumentException("len must be > 0");
    }

    public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        getAllInterfaces(cls, linkedHashSet);
        return new ArrayList(linkedHashSet);
    }

    private static void getAllInterfaces(Class<?> cls, HashSet<Class<?>> hashSet) {
        Class<?>[] interfaces;
        while (cls != null) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                if (hashSet.add(cls2)) {
                    getAllInterfaces(cls2, hashSet);
                }
            }
            cls = cls.getSuperclass();
        }
    }

    public static List<Class<?>> getAllSuperclasses(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            arrayList.add(superclass);
        }
        return arrayList;
    }

    public static String getCanonicalName(Class<?> cls) {
        return getCanonicalName(cls, "");
    }

    public static String getCanonicalName(Class<?> cls, String str) {
        String canonicalName;
        return (cls == null || (canonicalName = cls.getCanonicalName()) == null) ? str : canonicalName;
    }

    public static String getCanonicalName(Object obj) {
        return getCanonicalName(obj, "");
    }

    public static String getCanonicalName(Object obj, String str) {
        String canonicalName;
        return (obj == null || (canonicalName = obj.getClass().getCanonicalName()) == null) ? str : canonicalName;
    }

    private static String getCanonicalName(String str) {
        String deleteWhitespace = StringUtils.deleteWhitespace(str);
        if (deleteWhitespace == null) {
            return null;
        }
        int i = 0;
        while (deleteWhitespace.startsWith("[")) {
            i++;
            deleteWhitespace = deleteWhitespace.substring(1);
        }
        if (i < 1) {
            return deleteWhitespace;
        }
        if (deleteWhitespace.startsWith("L")) {
            deleteWhitespace = deleteWhitespace.substring(1, deleteWhitespace.endsWith(";") ? deleteWhitespace.length() - 1 : deleteWhitespace.length());
        } else if (!deleteWhitespace.isEmpty()) {
            deleteWhitespace = reverseAbbreviationMap.get(deleteWhitespace.substring(0, 1));
        }
        StringBuilder sb = new StringBuilder(deleteWhitespace);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("[]");
        }
        return sb.toString();
    }

    public static Class<?> getClass(ClassLoader classLoader, String str) throws ClassNotFoundException {
        return getClass(classLoader, str, true);
    }

    public static Class<?> getClass(ClassLoader classLoader, String str, boolean z) throws ClassNotFoundException {
        try {
            Class<?> cls = namePrimitiveMap.get(str);
            return cls != null ? cls : Class.forName(toCanonicalName(str), z, classLoader);
        } catch (ClassNotFoundException e) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf != -1) {
                try {
                    return getClass(classLoader, str.substring(0, lastIndexOf) + '$' + str.substring(lastIndexOf + 1), z);
                } catch (ClassNotFoundException unused) {
                    throw e;
                }
            }
            throw e;
        }
    }

    public static Class<?> getClass(String str) throws ClassNotFoundException {
        return getClass(str, true);
    }

    public static Class<?> getClass(String str, boolean z) throws ClassNotFoundException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            contextClassLoader = ClassUtils.class.getClassLoader();
        }
        return getClass(contextClassLoader, str, z);
    }

    public static <T> Class<T> getComponentType(Class<T[]> cls) {
        if (cls == null) {
            return null;
        }
        return (Class<T>) cls.getComponentType();
    }

    public static String getName(Class<?> cls) {
        return getName(cls, "");
    }

    public static String getName(Class<?> cls, String str) {
        return cls == null ? str : cls.getName();
    }

    public static String getName(Object obj) {
        return getName(obj, "");
    }

    public static String getName(Object obj, String str) {
        return obj == null ? str : obj.getClass().getName();
    }

    public static String getPackageCanonicalName(Class<?> cls) {
        return cls == null ? "" : getPackageCanonicalName(cls.getName());
    }

    public static String getPackageCanonicalName(Object obj, String str) {
        return obj == null ? str : getPackageCanonicalName(obj.getClass().getName());
    }

    public static String getPackageCanonicalName(String str) {
        return getPackageName(getCanonicalName(str));
    }

    public static String getPackageName(Class<?> cls) {
        return cls == null ? "" : getPackageName(cls.getName());
    }

    public static String getPackageName(Object obj, String str) {
        return obj == null ? str : getPackageName(obj.getClass());
    }

    public static String getPackageName(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        while (str.charAt(0) == '[') {
            str = str.substring(1);
        }
        if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
            str = str.substring(1);
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.reflect.Method getPublicMethod(java.lang.Class<?> r2, java.lang.String r3, java.lang.Class<?>... r4) throws java.lang.NoSuchMethodException {
        /*
            java.lang.reflect.Method r0 = r2.getMethod(r3, r4)
            java.lang.Class r1 = r0.getDeclaringClass()
            boolean r1 = isPublic(r1)
            if (r1 == 0) goto Lf
            return r0
        Lf:
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.List r1 = getAllInterfaces(r2)
            r0.<init>(r1)
            java.util.List r2 = getAllSuperclasses(r2)
            r0.addAll(r2)
            java.util.Iterator r2 = r0.iterator()
        L23:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L49
            java.lang.Object r0 = r2.next()
            java.lang.Class r0 = (java.lang.Class) r0
            boolean r1 = isPublic(r0)
            if (r1 != 0) goto L36
            goto L23
        L36:
            java.lang.reflect.Method r0 = r0.getMethod(r3, r4)     // Catch: java.lang.NoSuchMethodException -> L23
            java.lang.Class r1 = r0.getDeclaringClass()
            int r1 = r1.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isPublic(r1)
            if (r1 == 0) goto L23
            return r0
        L49:
            java.lang.NoSuchMethodException r2 = new java.lang.NoSuchMethodException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Can't find a public method for "
            r0.<init>(r1)
            java.lang.StringBuilder r3 = r0.append(r3)
            java.lang.String r0 = " "
            java.lang.StringBuilder r3 = r3.append(r0)
            java.lang.String r4 = org.apache.commons.lang3.ArrayUtils.toString(r4)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.ClassUtils.getPublicMethod(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    public static String getShortCanonicalName(Class<?> cls) {
        return cls == null ? "" : getShortCanonicalName(cls.getCanonicalName());
    }

    public static String getShortCanonicalName(Object obj, String str) {
        return obj == null ? str : getShortCanonicalName(obj.getClass().getCanonicalName());
    }

    public static String getShortCanonicalName(String str) {
        return getShortClassName(getCanonicalName(str));
    }

    public static String getShortClassName(Class<?> cls) {
        return cls == null ? "" : getShortClassName(cls.getName());
    }

    public static String getShortClassName(Object obj, String str) {
        return obj == null ? str : getShortClassName(obj.getClass());
    }

    public static String getShortClassName(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.startsWith("[")) {
            while (str.charAt(0) == '[') {
                str = str.substring(1);
                sb.append("[]");
            }
            if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
                str = str.substring(1, str.length() - 1);
            }
            Map<String, String> map = reverseAbbreviationMap;
            if (map.containsKey(str)) {
                str = map.get(str);
            }
        }
        int lastIndexOf = str.lastIndexOf(46);
        int indexOf = str.indexOf(36, lastIndexOf != -1 ? lastIndexOf + 1 : 0);
        String substring = str.substring(lastIndexOf + 1);
        if (indexOf != -1) {
            substring = substring.replace('$', '.');
        }
        return substring + ((Object) sb);
    }

    public static String getSimpleName(Class<?> cls) {
        return getSimpleName(cls, "");
    }

    public static String getSimpleName(Class<?> cls, String str) {
        return cls == null ? str : cls.getSimpleName();
    }

    public static String getSimpleName(Object obj) {
        return getSimpleName(obj, "");
    }

    public static String getSimpleName(Object obj, String str) {
        return obj == null ? str : obj.getClass().getSimpleName();
    }

    public static Iterable<Class<?>> hierarchy(Class<?> cls) {
        return hierarchy(cls, Interfaces.EXCLUDE);
    }

    public static Iterable<Class<?>> hierarchy(final Class<?> cls, Interfaces interfaces) {
        final Iterable<Class<?>> iterable = new Iterable() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda8
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return ClassUtils.lambda$hierarchy$4(cls);
            }
        };
        return interfaces != Interfaces.INCLUDE ? iterable : new Iterable() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda9
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return ClassUtils.lambda$hierarchy$5(iterable);
            }
        };
    }

    public static /* synthetic */ Iterator lambda$hierarchy$4(Class cls) {
        final MutableObject mutableObject = new MutableObject(cls);
        return new Iterator<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return mutableObject.getValue() != null;
            }

            @Override // java.util.Iterator
            public Class<?> next() {
                Class<?> cls2 = (Class) mutableObject.getValue();
                mutableObject.setValue(cls2.getSuperclass());
                return cls2;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static /* synthetic */ Iterator lambda$hierarchy$5(Iterable iterable) {
        final HashSet hashSet = new HashSet();
        final Iterator it = iterable.iterator();
        return new Iterator<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.2
            Iterator interfaces = Collections.emptyIterator();

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.interfaces.hasNext() || it.hasNext();
            }

            @Override // java.util.Iterator
            public Class<?> next() {
                if (this.interfaces.hasNext()) {
                    Class<?> cls = (Class) this.interfaces.next();
                    hashSet.add(cls);
                    return cls;
                }
                Class<?> cls2 = (Class) it.next();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                walkInterfaces(linkedHashSet, cls2);
                this.interfaces = linkedHashSet.iterator();
                return cls2;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            private void walkInterfaces(Set<Class<?>> set, Class<?> cls) {
                Class<?>[] interfaces;
                for (Class<?> cls2 : cls.getInterfaces()) {
                    if (!hashSet.contains(cls2)) {
                        set.add(cls2);
                    }
                    walkInterfaces(set, cls2);
                }
            }
        };
    }

    public static boolean isAssignable(Class<?> cls, Class<?> cls2) {
        return isAssignable(cls, cls2, true);
    }

    public static boolean isAssignable(Class<?> cls, Class<?> cls2, boolean z) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z) {
            if (cls.isPrimitive() && !cls2.isPrimitive() && (cls = primitiveToWrapper(cls)) == null) {
                return false;
            }
            if (cls2.isPrimitive() && !cls.isPrimitive() && (cls = wrapperToPrimitive(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (cls2.isPrimitive()) {
                if (Integer.TYPE.equals(cls)) {
                    return Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                } else if (Long.TYPE.equals(cls)) {
                    return Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                } else if (Boolean.TYPE.equals(cls) || Double.TYPE.equals(cls)) {
                    return false;
                } else {
                    if (Float.TYPE.equals(cls)) {
                        return Double.TYPE.equals(cls2);
                    }
                    if (Character.TYPE.equals(cls) || Short.TYPE.equals(cls)) {
                        return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                    } else if (Byte.TYPE.equals(cls)) {
                        return Short.TYPE.equals(cls2) || Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
        return cls2.isAssignableFrom(cls);
    }

    public static boolean isAssignable(Class<?>[] clsArr, Class<?>... clsArr2) {
        return isAssignable(clsArr, clsArr2, true);
    }

    public static boolean isAssignable(Class<?>[] clsArr, Class<?>[] clsArr2, boolean z) {
        if (ArrayUtils.isSameLength((Object[]) clsArr, (Object[]) clsArr2)) {
            if (clsArr == null) {
                clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
            }
            if (clsArr2 == null) {
                clsArr2 = ArrayUtils.EMPTY_CLASS_ARRAY;
            }
            for (int i = 0; i < clsArr.length; i++) {
                if (!isAssignable(clsArr[i], clsArr2[i], z)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isInnerClass(Class<?> cls) {
        return (cls == null || cls.getEnclosingClass() == null) ? false : true;
    }

    public static boolean isPublic(Class<?> cls) {
        return Modifier.isPublic(cls.getModifiers());
    }

    public static boolean isPrimitiveOrWrapper(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        return cls.isPrimitive() || isPrimitiveWrapper(cls);
    }

    public static boolean isPrimitiveWrapper(Class<?> cls) {
        return wrapperPrimitiveMap.containsKey(cls);
    }

    public static Class<?>[] primitivesToWrappers(final Class<?>... clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class<?>[] clsArr2 = new Class[clsArr.length];
        Arrays.setAll(clsArr2, new IntFunction() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Class primitiveToWrapper;
                primitiveToWrapper = ClassUtils.primitiveToWrapper(clsArr[i]);
                return primitiveToWrapper;
            }
        });
        return clsArr2;
    }

    public static Class<?> primitiveToWrapper(Class<?> cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : primitiveWrapperMap.get(cls);
    }

    private static String toCanonicalName(String str) {
        String deleteWhitespace = StringUtils.deleteWhitespace(str);
        Objects.requireNonNull(deleteWhitespace, "className");
        if (deleteWhitespace.endsWith("[]")) {
            StringBuilder sb = new StringBuilder();
            while (deleteWhitespace.endsWith("[]")) {
                deleteWhitespace = deleteWhitespace.substring(0, deleteWhitespace.length() - 2);
                sb.append("[");
            }
            String str2 = abbreviationMap.get(deleteWhitespace);
            if (str2 != null) {
                sb.append(str2);
            } else {
                sb.append("L").append(deleteWhitespace).append(";");
            }
            return sb.toString();
        }
        return deleteWhitespace;
    }

    public static Class<?>[] toClass(final Object... objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        Arrays.setAll(clsArr, new IntFunction() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda11
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ClassUtils.lambda$toClass$7(objArr, i);
            }
        });
        return clsArr;
    }

    public static /* synthetic */ Class lambda$toClass$7(Object[] objArr, int i) {
        Object obj = objArr[i];
        if (obj == null) {
            return null;
        }
        return obj.getClass();
    }

    public static Class<?>[] wrappersToPrimitives(final Class<?>... clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class<?>[] clsArr2 = new Class[clsArr.length];
        Arrays.setAll(clsArr2, new IntFunction() { // from class: org.apache.commons.lang3.ClassUtils$$ExternalSyntheticLambda10
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                Class wrapperToPrimitive;
                wrapperToPrimitive = ClassUtils.wrapperToPrimitive(clsArr[i]);
                return wrapperToPrimitive;
            }
        });
        return clsArr2;
    }

    public static Class<?> wrapperToPrimitive(Class<?> cls) {
        return wrapperPrimitiveMap.get(cls);
    }
}
