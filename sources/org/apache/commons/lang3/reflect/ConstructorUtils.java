package org.apache.commons.lang3.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes2.dex */
public class ConstructorUtils {
    public static <T> T invokeConstructor(Class<T> cls, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return (T) invokeConstructor(cls, nullToEmpty, ClassUtils.toClass(nullToEmpty));
    }

    public static <T> T invokeConstructor(Class<T> cls, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Constructor matchingAccessibleConstructor = getMatchingAccessibleConstructor(cls, ArrayUtils.nullToEmpty(clsArr));
        if (matchingAccessibleConstructor == null) {
            throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
        }
        if (matchingAccessibleConstructor.isVarArgs()) {
            nullToEmpty = MethodUtils.getVarArgs(nullToEmpty, matchingAccessibleConstructor.getParameterTypes());
        }
        return (T) matchingAccessibleConstructor.newInstance(nullToEmpty);
    }

    public static <T> T invokeExactConstructor(Class<T> cls, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        return (T) invokeExactConstructor(cls, nullToEmpty, ClassUtils.toClass(nullToEmpty));
    }

    public static <T> T invokeExactConstructor(Class<T> cls, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] nullToEmpty = ArrayUtils.nullToEmpty(objArr);
        Constructor accessibleConstructor = getAccessibleConstructor(cls, ArrayUtils.nullToEmpty(clsArr));
        if (accessibleConstructor == null) {
            throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
        }
        return (T) accessibleConstructor.newInstance(nullToEmpty);
    }

    public static <T> Constructor<T> getAccessibleConstructor(Class<T> cls, Class<?>... clsArr) {
        Objects.requireNonNull(cls, "cls");
        try {
            return getAccessibleConstructor(cls.getConstructor(clsArr));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static <T> Constructor<T> getAccessibleConstructor(Constructor<T> constructor) {
        Objects.requireNonNull(constructor, "ctor");
        if (MemberUtils.isAccessible(constructor) && isAccessible(constructor.getDeclaringClass())) {
            return constructor;
        }
        return null;
    }

    public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... clsArr) {
        Constructor<?>[] constructors;
        Constructor<T> accessibleConstructor;
        Objects.requireNonNull(cls, "cls");
        try {
            return (Constructor) MemberUtils.setAccessibleWorkaround(cls.getConstructor(clsArr));
        } catch (NoSuchMethodException unused) {
            Constructor<T> constructor = null;
            for (Constructor<?> constructor2 : cls.getConstructors()) {
                if (MemberUtils.isMatchingConstructor(constructor2, clsArr) && (accessibleConstructor = getAccessibleConstructor(constructor2)) != null) {
                    MemberUtils.setAccessibleWorkaround(accessibleConstructor);
                    if (constructor == null || MemberUtils.compareConstructorFit(accessibleConstructor, constructor, clsArr) < 0) {
                        constructor = accessibleConstructor;
                    }
                }
            }
            return constructor;
        }
    }

    private static boolean isAccessible(Class<?> cls) {
        while (cls != null) {
            if (!ClassUtils.isPublic(cls)) {
                return false;
            }
            cls = cls.getEnclosingClass();
        }
        return true;
    }
}
