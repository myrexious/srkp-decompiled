package org.apache.commons.lang3.reflect;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes2.dex */
public class MethodUtils {
    private static final Comparator<Method> METHOD_BY_SIGNATURE = Comparator.comparing(new MethodUtils$$ExternalSyntheticLambda11());

    public static Object invokeMethod(Object obj, String str) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokeMethod(obj, str, ArrayUtils.EMPTY_OBJECT_ARRAY, (Class<?>[]) null);
    }

    public static Object invokeMethod(Object obj, boolean z, String str) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokeMethod(obj, z, str, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeMethod(Object obj, String str, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return invokeMethod(obj, str, nullToEmpty, ClassUtils.toClass(nullToEmpty));
    }

    public static Object invokeMethod(Object obj, boolean z, String str, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return invokeMethod(obj, z, str, nullToEmpty, ClassUtils.toClass(nullToEmpty));
    }

    public static Object invokeMethod(Object obj, boolean z, String str, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method matchingAccessibleMethod;
        String str2;
        Objects.requireNonNull(obj, "object");
        Class<?>[] nullToEmpty = ArrayUtils.nullToEmpty(clsArr);
        Object[] nullToEmpty2 = ArrayUtils.nullToEmpty(objArr);
        Class<?> cls = obj.getClass();
        if (z) {
            matchingAccessibleMethod = getMatchingMethod(cls, str, nullToEmpty);
            if (matchingAccessibleMethod != null && !matchingAccessibleMethod.isAccessible()) {
                matchingAccessibleMethod.setAccessible(true);
            }
            str2 = "No such method: ";
        } else {
            matchingAccessibleMethod = getMatchingAccessibleMethod(cls, str, nullToEmpty);
            str2 = "No such accessible method: ";
        }
        if (matchingAccessibleMethod == null) {
            throw new NoSuchMethodException(str2 + str + "() on object: " + cls.getName());
        }
        return matchingAccessibleMethod.invoke(obj, toVarArgs(matchingAccessibleMethod, nullToEmpty2));
    }

    public static Object invokeMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokeMethod(obj, false, str, objArr, clsArr);
    }

    public static Object invokeExactMethod(Object obj, String str) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokeExactMethod(obj, str, ArrayUtils.EMPTY_OBJECT_ARRAY, null);
    }

    public static Object invokeExactMethod(Object obj, String str, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return invokeExactMethod(obj, str, nullToEmpty, ClassUtils.toClass(nullToEmpty));
    }

    public static Object invokeExactMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Objects.requireNonNull(obj, "object");
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Class<?>[] nullToEmpty2 = ArrayUtils.nullToEmpty(clsArr);
        Class<?> cls = obj.getClass();
        Method accessibleMethod = getAccessibleMethod(cls, str, nullToEmpty2);
        if (accessibleMethod == null) {
            throw new NoSuchMethodException("No such accessible method: " + str + "() on object: " + cls.getName());
        }
        return accessibleMethod.invoke(obj, nullToEmpty);
    }

    public static Object invokeExactStaticMethod(Class<?> cls, String str, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Method accessibleMethod = getAccessibleMethod(cls, str, ArrayUtils.nullToEmpty(clsArr));
        if (accessibleMethod == null) {
            throw new NoSuchMethodException("No such accessible method: " + str + "() on class: " + cls.getName());
        }
        return accessibleMethod.invoke(null, nullToEmpty);
    }

    public static Object invokeStaticMethod(Class<?> cls, String str, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return invokeStaticMethod(cls, str, nullToEmpty, ClassUtils.toClass(nullToEmpty));
    }

    public static Object invokeStaticMethod(Class<?> cls, String str, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Method matchingAccessibleMethod = getMatchingAccessibleMethod(cls, str, ArrayUtils.nullToEmpty(clsArr));
        if (matchingAccessibleMethod == null) {
            throw new NoSuchMethodException("No such accessible method: " + str + "() on class: " + cls.getName());
        }
        return matchingAccessibleMethod.invoke(null, toVarArgs(matchingAccessibleMethod, nullToEmpty));
    }

    private static Object[] toVarArgs(Method method, Object[] objArr) {
        return method.isVarArgs() ? getVarArgs(objArr, method.getParameterTypes()) : objArr;
    }

    public static Object[] getVarArgs(Object[] objArr, Class<?>[] clsArr) {
        if (objArr.length == clsArr.length && (objArr[objArr.length - 1] == null || objArr[objArr.length - 1].getClass().equals(clsArr[clsArr.length - 1]))) {
            return objArr;
        }
        Object[] objArr2 = new Object[clsArr.length];
        System.arraycopy(objArr, 0, objArr2, 0, clsArr.length - 1);
        Class<?> componentType = clsArr[clsArr.length - 1].getComponentType();
        int length = (objArr.length - clsArr.length) + 1;
        Object newInstance = Array.newInstance(ClassUtils.primitiveToWrapper(componentType), length);
        System.arraycopy(objArr, clsArr.length - 1, newInstance, 0, length);
        if (componentType.isPrimitive()) {
            newInstance = ArrayUtils.toPrimitive(newInstance);
        }
        objArr2[clsArr.length - 1] = newInstance;
        return objArr2;
    }

    public static Object invokeExactStaticMethod(Class<?> cls, String str, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return invokeExactStaticMethod(cls, str, nullToEmpty, ClassUtils.toClass(nullToEmpty));
    }

    public static Method getAccessibleMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return getAccessibleMethod(cls.getMethod(str, clsArr));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Method getAccessibleMethod(Method method) {
        if (MemberUtils.isAccessible(method)) {
            Class<?> declaringClass = method.getDeclaringClass();
            if (ClassUtils.isPublic(declaringClass)) {
                return method;
            }
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            Method accessibleMethodFromInterfaceNest = getAccessibleMethodFromInterfaceNest(declaringClass, name, parameterTypes);
            return accessibleMethodFromInterfaceNest == null ? getAccessibleMethodFromSuperclass(declaringClass, name, parameterTypes) : accessibleMethodFromInterfaceNest;
        }
        return null;
    }

    private static Method getAccessibleMethodFromSuperclass(Class<?> cls, String str, Class<?>... clsArr) {
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            if (ClassUtils.isPublic(superclass)) {
                try {
                    return superclass.getMethod(str, clsArr);
                } catch (NoSuchMethodException unused) {
                    return null;
                }
            }
        }
        return null;
    }

    private static Method getAccessibleMethodFromInterfaceNest(Class<?> cls, String str, Class<?>... clsArr) {
        Class<?>[] interfaces;
        while (cls != null) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                if (ClassUtils.isPublic(cls2)) {
                    try {
                        return cls2.getDeclaredMethod(str, clsArr);
                    } catch (NoSuchMethodException unused) {
                        Method accessibleMethodFromInterfaceNest = getAccessibleMethodFromInterfaceNest(cls2, str, clsArr);
                        if (accessibleMethodFromInterfaceNest != null) {
                            return accessibleMethodFromInterfaceNest;
                        }
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    public static Method getMatchingAccessibleMethod(Class<?> cls, final String str, final Class<?>... clsArr) {
        Class<?>[] parameterTypes;
        try {
            return (Method) MemberUtils.setAccessibleWorkaround(cls.getMethod(str, clsArr));
        } catch (NoSuchMethodException unused) {
            List<Method> list = (List) Stream.of((Object[]) cls.getMethods()).filter(new Predicate() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return MethodUtils.lambda$getMatchingAccessibleMethod$0(str, clsArr, (Method) obj);
                }
            }).collect(Collectors.toList());
            list.sort(METHOD_BY_SIGNATURE);
            Method method = null;
            for (Method method2 : list) {
                Method accessibleMethod = getAccessibleMethod(method2);
                if (accessibleMethod != null && (method == null || MemberUtils.compareMethodFit(accessibleMethod, method, clsArr) < 0)) {
                    method = accessibleMethod;
                }
            }
            if (method != null) {
                MemberUtils.setAccessibleWorkaround(method);
            }
            if (method != null && method.isVarArgs() && method.getParameterTypes().length > 0 && clsArr.length > 0) {
                String name = ClassUtils.primitiveToWrapper(method.getParameterTypes()[parameterTypes.length - 1].getComponentType()).getName();
                Class<?> cls2 = clsArr[clsArr.length - 1];
                String name2 = cls2 == null ? null : cls2.getName();
                String name3 = cls2 == null ? null : cls2.getSuperclass().getName();
                if (name2 != null && name3 != null && !name.equals(name2) && !name.equals(name3)) {
                    return null;
                }
            }
            return method;
        }
    }

    public static /* synthetic */ boolean lambda$getMatchingAccessibleMethod$0(String str, Class[] clsArr, Method method) {
        return method.getName().equals(str) && MemberUtils.isMatchingMethod(method, clsArr);
    }

    public static Method getMatchingMethod(Class<?> cls, final String str, final Class<?>... clsArr) {
        Objects.requireNonNull(cls, "cls");
        Validate.notEmpty(str, "methodName", new Object[0]);
        List<Method> list = (List) Stream.of((Object[]) cls.getDeclaredMethods()).filter(new Predicate() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((Method) obj).getName().equals(str);
                return equals;
            }
        }).collect(Collectors.toList());
        Stream filter = ClassUtils.getAllSuperclasses(cls).stream().map(new Function() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Method[] declaredMethods;
                declaredMethods = ((Class) obj).getDeclaredMethods();
                return declaredMethods;
            }
        }).flatMap(new Function() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream of;
                of = Stream.of((Object[]) ((Method[]) obj));
                return of;
            }
        }).filter(new Predicate() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((Method) obj).getName().equals(str);
                return equals;
            }
        });
        list.getClass();
        filter.forEach(new MethodUtils$$ExternalSyntheticLambda7(list));
        for (Method method : list) {
            if (Arrays.deepEquals(method.getParameterTypes(), clsArr)) {
                return method;
            }
        }
        final TreeMap treeMap = new TreeMap();
        list.stream().filter(new Predicate() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isAssignable;
                isAssignable = ClassUtils.isAssignable((Class<?>[]) clsArr, ((Method) obj).getParameterTypes(), true);
                return isAssignable;
            }
        }).forEach(new Consumer() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((List) treeMap.computeIfAbsent(Integer.valueOf(MethodUtils.distance(clsArr, r3.getParameterTypes())), new Function() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda12
                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        return MethodUtils.lambda$null$4((Integer) obj2);
                    }
                })).add((Method) obj);
            }
        });
        if (treeMap.isEmpty()) {
            return null;
        }
        List list2 = (List) treeMap.values().iterator().next();
        if (list2.size() == 1 || !Objects.equals(((Method) list2.get(0)).getDeclaringClass(), ((Method) list2.get(1)).getDeclaringClass())) {
            return (Method) list2.get(0);
        }
        throw new IllegalStateException(String.format("Found multiple candidates for method %s on class %s : %s", str + ((String) Stream.of((Object[]) clsArr).map(new Function() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String valueOf;
                valueOf = String.valueOf((Class) obj);
                return valueOf;
            }
        }).collect(Collectors.joining(",", "(", ")"))), cls.getName(), list2.stream().map(new MethodUtils$$ExternalSyntheticLambda11()).collect(Collectors.joining(",", "[", "]"))));
    }

    public static /* synthetic */ List lambda$null$4(Integer num) {
        return new ArrayList();
    }

    public static int distance(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (ClassUtils.isAssignable(clsArr, clsArr2, true)) {
            int i = 0;
            for (int i2 = 0; i2 < clsArr.length; i2++) {
                Class<?> cls = clsArr[i2];
                Class<?> cls2 = clsArr2[i2];
                if (cls != null && !cls.equals(cls2)) {
                    i = (!ClassUtils.isAssignable(cls, cls2, true) || ClassUtils.isAssignable(cls, cls2, false)) ? i + 2 : i + 1;
                }
            }
            return i;
        }
        return -1;
    }

    public static Set<Method> getOverrideHierarchy(Method method, ClassUtils.Interfaces interfaces) {
        Objects.requireNonNull(method, FirebaseAnalytics.Param.METHOD);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(method);
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> declaringClass = method.getDeclaringClass();
        Iterator<Class<?>> it = ClassUtils.hierarchy(declaringClass, interfaces).iterator();
        it.next();
        while (it.hasNext()) {
            Method matchingAccessibleMethod = getMatchingAccessibleMethod(it.next(), method.getName(), parameterTypes);
            if (matchingAccessibleMethod != null) {
                if (Arrays.equals(matchingAccessibleMethod.getParameterTypes(), parameterTypes)) {
                    linkedHashSet.add(matchingAccessibleMethod);
                } else {
                    Map<TypeVariable<?>, Type> typeArguments = TypeUtils.getTypeArguments(declaringClass, matchingAccessibleMethod.getDeclaringClass());
                    int i = 0;
                    while (true) {
                        if (i < parameterTypes.length) {
                            if (!TypeUtils.equals(TypeUtils.unrollVariables(typeArguments, method.getGenericParameterTypes()[i]), TypeUtils.unrollVariables(typeArguments, matchingAccessibleMethod.getGenericParameterTypes()[i]))) {
                                break;
                            }
                            i++;
                        } else {
                            linkedHashSet.add(matchingAccessibleMethod);
                            break;
                        }
                    }
                }
            }
        }
        return linkedHashSet;
    }

    public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> cls2) {
        return getMethodsWithAnnotation(cls, cls2, false, false);
    }

    public static List<Method> getMethodsListWithAnnotation(Class<?> cls, Class<? extends Annotation> cls2) {
        return getMethodsListWithAnnotation(cls, cls2, false, false);
    }

    public static Method[] getMethodsWithAnnotation(Class<?> cls, Class<? extends Annotation> cls2, boolean z, boolean z2) {
        return (Method[]) getMethodsListWithAnnotation(cls, cls2, z, z2).toArray(ArrayUtils.EMPTY_METHOD_ARRAY);
    }

    public static List<Method> getMethodsListWithAnnotation(Class<?> cls, final Class<? extends Annotation> cls2, boolean z, final boolean z2) {
        Objects.requireNonNull(cls, "cls");
        Objects.requireNonNull(cls2, "annotationCls");
        List allSuperclassesAndInterfaces = z ? getAllSuperclassesAndInterfaces(cls) : new ArrayList();
        allSuperclassesAndInterfaces.add(0, cls);
        final ArrayList arrayList = new ArrayList();
        allSuperclassesAndInterfaces.forEach(new Consumer() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MethodUtils.lambda$getMethodsListWithAnnotation$7(z2, cls2, arrayList, (Class) obj);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ void lambda$getMethodsListWithAnnotation$7(boolean z, final Class cls, List list, Class cls2) {
        Stream filter = Stream.of((Object[]) (z ? cls2.getDeclaredMethods() : cls2.getMethods())).filter(new Predicate() { // from class: org.apache.commons.lang3.reflect.MethodUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isAnnotationPresent;
                isAnnotationPresent = ((Method) obj).isAnnotationPresent(cls);
                return isAnnotationPresent;
            }
        });
        list.getClass();
        filter.forEachOrdered(new MethodUtils$$ExternalSyntheticLambda7(list));
    }

    public static <A extends Annotation> A getAnnotation(Method method, Class<A> cls, boolean z, boolean z2) {
        Method matchingAccessibleMethod;
        Objects.requireNonNull(method, FirebaseAnalytics.Param.METHOD);
        Objects.requireNonNull(cls, "annotationCls");
        if (z2 || MemberUtils.isAccessible(method)) {
            A a = (A) method.getAnnotation(cls);
            if (a == null && z) {
                for (Class<?> cls2 : getAllSuperclassesAndInterfaces(method.getDeclaringClass())) {
                    if (z2) {
                        matchingAccessibleMethod = getMatchingMethod(cls2, method.getName(), method.getParameterTypes());
                    } else {
                        matchingAccessibleMethod = getMatchingAccessibleMethod(cls2, method.getName(), method.getParameterTypes());
                    }
                    if (matchingAccessibleMethod != null && (a = (A) matchingAccessibleMethod.getAnnotation(cls)) != null) {
                        break;
                    }
                }
            }
            return a;
        }
        return null;
    }

    private static List<Class<?>> getAllSuperclassesAndInterfaces(Class<?> cls) {
        Class<?> cls2;
        int i;
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<Class<?>> allSuperclasses = ClassUtils.getAllSuperclasses(cls);
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(cls);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= allInterfaces.size() && i3 >= allSuperclasses.size()) {
                return arrayList;
            }
            if (i2 >= allInterfaces.size()) {
                i = i3 + 1;
                cls2 = allSuperclasses.get(i3);
            } else if (i3 >= allSuperclasses.size() || i3 >= i2) {
                int i4 = i3;
                cls2 = allInterfaces.get(i2);
                i2++;
                i = i4;
            } else {
                i = i3 + 1;
                cls2 = allSuperclasses.get(i3);
            }
            arrayList.add(cls2);
            i3 = i;
        }
    }
}
