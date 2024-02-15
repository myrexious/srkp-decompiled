package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.EnumNamingStrategy;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class EnumResolver implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Enum<?> _defaultValue;
    protected final Class<Enum<?>> _enumClass;
    protected final Enum<?>[] _enums;
    protected final HashMap<String, Enum<?>> _enumsById;
    protected final boolean _isFromIntValue;
    protected final boolean _isIgnoreCase;

    /* JADX WARN: Multi-variable type inference failed */
    protected static Class<Enum<?>> _enumClass(Class<?> cls) {
        return cls;
    }

    protected EnumResolver(Class<Enum<?>> cls, Enum<?>[] enumArr, HashMap<String, Enum<?>> hashMap, Enum<?> r4, boolean z, boolean z2) {
        this._enumClass = cls;
        this._enums = enumArr;
        this._enumsById = hashMap;
        this._defaultValue = r4;
        this._isIgnoreCase = z;
        this._isFromIntValue = z2;
    }

    public static EnumResolver constructFor(DeserializationConfig deserializationConfig, Class<?> cls) {
        return _constructFor(deserializationConfig, cls);
    }

    protected static EnumResolver _constructFor(DeserializationConfig deserializationConfig, Class<?> cls) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        boolean isEnabled = deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        Class<Enum<?>> _enumClass = _enumClass(cls);
        Enum<?>[] _enumConstants = _enumConstants(cls);
        String[] findEnumValues = annotationIntrospector.findEnumValues(_enumClass, _enumConstants, new String[_enumConstants.length]);
        String[][] strArr = new String[findEnumValues.length];
        annotationIntrospector.findEnumAliases(_enumClass, _enumConstants, strArr);
        HashMap hashMap = new HashMap();
        int length = _enumConstants.length;
        for (int i = 0; i < length; i++) {
            Enum<?> r8 = _enumConstants[i];
            String str = findEnumValues[i];
            if (str == null) {
                str = r8.name();
            }
            hashMap.put(str, r8);
            String[] strArr2 = strArr[i];
            if (strArr2 != null) {
                for (String str2 : strArr2) {
                    hashMap.putIfAbsent(str2, r8);
                }
            }
        }
        return new EnumResolver(_enumClass, _enumConstants, hashMap, _enumDefault(annotationIntrospector, _enumClass), isEnabled, false);
    }

    public static EnumResolver constructUsingToString(DeserializationConfig deserializationConfig, Class<?> cls) {
        return _constructUsingToString(deserializationConfig, cls);
    }

    public static EnumResolver constructUsingIndex(DeserializationConfig deserializationConfig, Class<Enum<?>> cls) {
        return _constructUsingIndex(deserializationConfig, cls);
    }

    private static EnumResolver _constructUsingIndex(DeserializationConfig deserializationConfig, Class<Enum<?>> cls) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        boolean isEnabled = deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        Class<Enum<?>> _enumClass = _enumClass(cls);
        Enum<?>[] _enumConstants = _enumConstants(cls);
        HashMap hashMap = new HashMap();
        int length = _enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                hashMap.put(String.valueOf(length), _enumConstants[length]);
            } else {
                return new EnumResolver(_enumClass, _enumConstants, hashMap, _enumDefault(annotationIntrospector, _enumClass), isEnabled, false);
            }
        }
    }

    public static EnumResolver constructUsingEnumNamingStrategy(DeserializationConfig deserializationConfig, Class<?> cls, EnumNamingStrategy enumNamingStrategy) {
        return _constructUsingEnumNamingStrategy(deserializationConfig, cls, enumNamingStrategy);
    }

    private static EnumResolver _constructUsingEnumNamingStrategy(DeserializationConfig deserializationConfig, Class<?> cls, EnumNamingStrategy enumNamingStrategy) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        boolean isEnabled = deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        Class<Enum<?>> _enumClass = _enumClass(cls);
        Enum<?>[] _enumConstants = _enumConstants(cls);
        HashMap hashMap = new HashMap();
        int length = _enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                Enum<?> r10 = _enumConstants[length];
                hashMap.put(enumNamingStrategy.convertEnumToExternalName(r10.name()), r10);
            } else {
                return new EnumResolver(_enumClass, _enumConstants, hashMap, _enumDefault(annotationIntrospector, _enumClass), isEnabled, false);
            }
        }
    }

    protected static EnumResolver _constructUsingToString(DeserializationConfig deserializationConfig, Class<?> cls) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        boolean isEnabled = deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        Class<Enum<?>> _enumClass = _enumClass(cls);
        Enum<?>[] _enumConstants = _enumConstants(cls);
        HashMap hashMap = new HashMap();
        String[][] strArr = new String[_enumConstants.length];
        if (annotationIntrospector != null) {
            annotationIntrospector.findEnumAliases(_enumClass, _enumConstants, strArr);
        }
        int length = _enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                Enum<?> r1 = _enumConstants[length];
                hashMap.put(r1.toString(), r1);
                String[] strArr2 = strArr[length];
                if (strArr2 != null) {
                    for (String str : strArr2) {
                        hashMap.putIfAbsent(str, r1);
                    }
                }
            } else {
                return new EnumResolver(_enumClass, _enumConstants, hashMap, _enumDefault(annotationIntrospector, _enumClass), isEnabled, false);
            }
        }
    }

    public static EnumResolver constructUsingMethod(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMember annotatedMember) {
        return _constructUsingMethod(deserializationConfig, cls, annotatedMember);
    }

    protected static EnumResolver _constructUsingMethod(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMember annotatedMember) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        boolean isEnabled = deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        Class<Enum<?>> _enumClass = _enumClass(cls);
        Enum<?>[] _enumConstants = _enumConstants(cls);
        HashMap hashMap = new HashMap();
        int length = _enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                Enum<?> r10 = _enumConstants[length];
                try {
                    Object value = annotatedMember.getValue(r10);
                    if (value != null) {
                        hashMap.put(value.toString(), r10);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + r10 + ": " + e.getMessage());
                }
            } else {
                return new EnumResolver(_enumClass, _enumConstants, hashMap, _enumDefault(annotationIntrospector, _enumClass), isEnabled, _isIntType(annotatedMember.getRawType()));
            }
        }
    }

    public CompactStringObjectMap constructLookup() {
        return CompactStringObjectMap.construct(this._enumsById);
    }

    protected static Enum<?>[] _enumConstants(Class<?> cls) {
        Enum<?>[] enumConstants = _enumClass(cls).getEnumConstants();
        if (enumConstants != null) {
            return enumConstants;
        }
        throw new IllegalArgumentException("No enum constants for class " + cls.getName());
    }

    protected static Enum<?> _enumDefault(AnnotationIntrospector annotationIntrospector, Class<?> cls) {
        if (annotationIntrospector != null) {
            return annotationIntrospector.findDefaultEnumValue(_enumClass(cls));
        }
        return null;
    }

    protected static boolean _isIntType(Class<?> cls) {
        if (cls.isPrimitive()) {
            cls = ClassUtil.wrapperType(cls);
        }
        return cls == Long.class || cls == Integer.class || cls == Short.class || cls == Byte.class;
    }

    @Deprecated
    protected EnumResolver(Class<Enum<?>> cls, Enum<?>[] enumArr, HashMap<String, Enum<?>> hashMap, Enum<?> r11, boolean z) {
        this(cls, enumArr, hashMap, r11, z, false);
    }

    public Enum<?> findEnum(String str) {
        Enum<?> r0 = this._enumsById.get(str);
        return (r0 == null && this._isIgnoreCase) ? _findEnumCaseInsensitive(str) : r0;
    }

    protected Enum<?> _findEnumCaseInsensitive(String str) {
        for (Map.Entry<String, Enum<?>> entry : this._enumsById.entrySet()) {
            if (str.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Enum<?> getEnum(int i) {
        if (i >= 0) {
            Enum<?>[] enumArr = this._enums;
            if (i >= enumArr.length) {
                return null;
            }
            return enumArr[i];
        }
        return null;
    }

    public Enum<?> getDefaultValue() {
        return this._defaultValue;
    }

    public Enum<?>[] getRawEnums() {
        return this._enums;
    }

    public List<Enum<?>> getEnums() {
        ArrayList arrayList = new ArrayList(this._enums.length);
        for (Enum<?> r4 : this._enums) {
            arrayList.add(r4);
        }
        return arrayList;
    }

    public Collection<String> getEnumIds() {
        return this._enumsById.keySet();
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }

    public boolean isFromIntValue() {
        return this._isFromIntValue;
    }
}
