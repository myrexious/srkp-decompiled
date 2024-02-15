package org.apache.commons.lang3.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntFunction;
import org.apache.commons.lang3.ArraySorter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.stream.Streams;

/* loaded from: classes2.dex */
public class ReflectionToStringBuilder extends ToStringBuilder {
    private boolean appendStatics;
    private boolean appendTransients;
    protected String[] excludeFieldNames;
    private boolean excludeNullValues;
    protected String[] includeFieldNames;
    private Class<?> upToClass;

    public static String[] toNoNullStringArray(Collection<String> collection) {
        if (collection == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return toNoNullStringArray(collection.toArray());
    }

    public static /* synthetic */ String[] lambda$toNoNullStringArray$0(int i) {
        return new String[i];
    }

    public static String[] toNoNullStringArray(Object[] objArr) {
        return (String[]) Streams.nonNull(objArr).map(new Function() { // from class: org.apache.commons.lang3.builder.ReflectionToStringBuilder$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String objects;
                objects = Objects.toString(obj);
                return objects;
            }
        }).toArray(new IntFunction() { // from class: org.apache.commons.lang3.builder.ReflectionToStringBuilder$$ExternalSyntheticLambda2
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ReflectionToStringBuilder.lambda$toNoNullStringArray$0(i);
            }
        });
    }

    public static String toString(Object obj) {
        return toString(obj, null, false, false, null);
    }

    public static String toString(Object obj, ToStringStyle toStringStyle) {
        return toString(obj, toStringStyle, false, false, null);
    }

    public static String toString(Object obj, ToStringStyle toStringStyle, boolean z) {
        return toString(obj, toStringStyle, z, false, null);
    }

    public static String toString(Object obj, ToStringStyle toStringStyle, boolean z, boolean z2) {
        return toString(obj, toStringStyle, z, z2, null);
    }

    public static <T> String toString(T t, ToStringStyle toStringStyle, boolean z, boolean z2, boolean z3, Class<? super T> cls) {
        return new ReflectionToStringBuilder(t, toStringStyle, null, cls, z, z2, z3).toString();
    }

    public static <T> String toString(T t, ToStringStyle toStringStyle, boolean z, boolean z2, Class<? super T> cls) {
        return new ReflectionToStringBuilder(t, toStringStyle, null, cls, z, z2).toString();
    }

    public static String toStringExclude(Object obj, Collection<String> collection) {
        return toStringExclude(obj, toNoNullStringArray(collection));
    }

    public static String toStringExclude(Object obj, String... strArr) {
        return new ReflectionToStringBuilder(obj).setExcludeFieldNames(strArr).toString();
    }

    public static String toStringInclude(Object obj, Collection<String> collection) {
        return toStringInclude(obj, toNoNullStringArray(collection));
    }

    public static String toStringInclude(Object obj, String... strArr) {
        return new ReflectionToStringBuilder(obj).setIncludeFieldNames(strArr).toString();
    }

    public ReflectionToStringBuilder(Object obj) {
        super(Objects.requireNonNull(obj, "obj"));
    }

    public ReflectionToStringBuilder(Object obj, ToStringStyle toStringStyle) {
        super(Objects.requireNonNull(obj, "obj"), toStringStyle);
    }

    public ReflectionToStringBuilder(Object obj, ToStringStyle toStringStyle, StringBuffer stringBuffer) {
        super(Objects.requireNonNull(obj, "obj"), toStringStyle, stringBuffer);
    }

    public <T> ReflectionToStringBuilder(T t, ToStringStyle toStringStyle, StringBuffer stringBuffer, Class<? super T> cls, boolean z, boolean z2) {
        super(Objects.requireNonNull(t, "obj"), toStringStyle, stringBuffer);
        setUpToClass(cls);
        setAppendTransients(z);
        setAppendStatics(z2);
    }

    public <T> ReflectionToStringBuilder(T t, ToStringStyle toStringStyle, StringBuffer stringBuffer, Class<? super T> cls, boolean z, boolean z2, boolean z3) {
        super(Objects.requireNonNull(t, "obj"), toStringStyle, stringBuffer);
        setUpToClass(cls);
        setAppendTransients(z);
        setAppendStatics(z2);
        setExcludeNullValues(z3);
    }

    protected boolean accept(Field field) {
        if (field.getName().indexOf(36) != -1) {
            return false;
        }
        if (!Modifier.isTransient(field.getModifiers()) || isAppendTransients()) {
            if (!Modifier.isStatic(field.getModifiers()) || isAppendStatics()) {
                String[] strArr = this.excludeFieldNames;
                if (strArr == null || Arrays.binarySearch(strArr, field.getName()) < 0) {
                    if (ArrayUtils.isNotEmpty(this.includeFieldNames)) {
                        return Arrays.binarySearch(this.includeFieldNames, field.getName()) >= 0;
                    }
                    return !field.isAnnotationPresent(ToStringExclude.class);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    protected void appendFieldsIn(Class<?> cls) {
        if (cls.isArray()) {
            reflectionAppendArray(getObject());
            return;
        }
        Field[] fieldArr = (Field[]) ArraySorter.sort(cls.getDeclaredFields(), Comparator.comparing(new Function() { // from class: org.apache.commons.lang3.builder.ReflectionToStringBuilder$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String name;
                name = ((Field) obj).getName();
                return name;
            }
        }));
        AccessibleObject.setAccessible(fieldArr, true);
        for (Field field : fieldArr) {
            String name = field.getName();
            if (accept(field)) {
                Object unchecked = Reflection.getUnchecked(field, getObject());
                if (!this.excludeNullValues || unchecked != null) {
                    append(name, unchecked, !field.isAnnotationPresent(ToStringSummary.class));
                }
            }
        }
    }

    public String[] getExcludeFieldNames() {
        return (String[]) this.excludeFieldNames.clone();
    }

    public String[] getIncludeFieldNames() {
        return (String[]) this.includeFieldNames.clone();
    }

    public Class<?> getUpToClass() {
        return this.upToClass;
    }

    protected Object getValue(Field field) throws IllegalAccessException {
        return field.get(getObject());
    }

    public boolean isAppendStatics() {
        return this.appendStatics;
    }

    public boolean isAppendTransients() {
        return this.appendTransients;
    }

    public boolean isExcludeNullValues() {
        return this.excludeNullValues;
    }

    public ReflectionToStringBuilder reflectionAppendArray(Object obj) {
        getStyle().reflectionAppendArrayDetail(getStringBuffer(), null, obj);
        return this;
    }

    public void setAppendStatics(boolean z) {
        this.appendStatics = z;
    }

    public void setAppendTransients(boolean z) {
        this.appendTransients = z;
    }

    public ReflectionToStringBuilder setExcludeFieldNames(String... strArr) {
        if (strArr == null) {
            this.excludeFieldNames = null;
        } else {
            this.excludeFieldNames = (String[]) ArraySorter.sort(toNoNullStringArray(strArr));
        }
        return this;
    }

    public void setExcludeNullValues(boolean z) {
        this.excludeNullValues = z;
    }

    public ReflectionToStringBuilder setIncludeFieldNames(String... strArr) {
        if (strArr == null) {
            this.includeFieldNames = null;
        } else {
            this.includeFieldNames = (String[]) ArraySorter.sort(toNoNullStringArray(strArr));
        }
        return this;
    }

    public void setUpToClass(Class<?> cls) {
        Object object;
        if (cls != null && (object = getObject()) != null && !cls.isInstance(object)) {
            throw new IllegalArgumentException("Specified class is not a superclass of the object");
        }
        this.upToClass = cls;
    }

    @Override // org.apache.commons.lang3.builder.ToStringBuilder
    public String toString() {
        if (getObject() == null) {
            return getStyle().getNullText();
        }
        validate();
        Class<?> cls = getObject().getClass();
        appendFieldsIn(cls);
        while (cls.getSuperclass() != null && cls != getUpToClass()) {
            cls = cls.getSuperclass();
            appendFieldsIn(cls);
        }
        return super.toString();
    }

    private void validate() {
        if (ArrayUtils.containsAny(this.excludeFieldNames, this.includeFieldNames)) {
            ToStringStyle.unregister(getObject());
            throw new IllegalStateException("includeFieldNames and excludeFieldNames must not intersect");
        }
    }
}
