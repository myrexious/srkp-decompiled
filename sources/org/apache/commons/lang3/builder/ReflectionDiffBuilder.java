package org.apache.commons.lang3.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.apache.commons.lang3.ArraySorter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

/* loaded from: classes2.dex */
public class ReflectionDiffBuilder<T> implements Builder<DiffResult<T>> {
    private final DiffBuilder<T> diffBuilder;
    private String[] excludeFieldNames;
    private final T left;
    private final T right;

    public ReflectionDiffBuilder(T t, T t2, ToStringStyle toStringStyle) {
        this.left = t;
        this.right = t2;
        this.diffBuilder = new DiffBuilder<>(t, t2, toStringStyle);
    }

    public String[] getExcludeFieldNames() {
        return (String[]) this.excludeFieldNames.clone();
    }

    public ReflectionDiffBuilder<T> setExcludeFieldNames(String... strArr) {
        if (strArr == null) {
            this.excludeFieldNames = ArrayUtils.EMPTY_STRING_ARRAY;
        } else {
            this.excludeFieldNames = (String[]) ArraySorter.sort(ReflectionToStringBuilder.toNoNullStringArray(strArr));
        }
        return this;
    }

    @Override // org.apache.commons.lang3.builder.Builder
    public DiffResult<T> build() {
        if (this.left.equals(this.right)) {
            return this.diffBuilder.build();
        }
        appendFields(this.left.getClass());
        return this.diffBuilder.build();
    }

    private void appendFields(Class<?> cls) {
        Field[] allFields;
        for (Field field : FieldUtils.getAllFields(cls)) {
            if (accept(field)) {
                try {
                    this.diffBuilder.append(field.getName(), FieldUtils.readField(field, (Object) this.left, true), FieldUtils.readField(field, (Object) this.right, true));
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Unexpected IllegalAccessException: " + e.getMessage(), e);
                }
            }
        }
    }

    private boolean accept(Field field) {
        if (field.getName().indexOf(36) != -1 || Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
            return false;
        }
        String[] strArr = this.excludeFieldNames;
        if (strArr == null || Arrays.binarySearch(strArr, field.getName()) < 0) {
            return !field.isAnnotationPresent(DiffExclude.class);
        }
        return false;
    }
}
