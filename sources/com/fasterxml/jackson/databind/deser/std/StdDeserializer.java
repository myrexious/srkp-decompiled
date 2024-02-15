package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.xmpbox.type.BooleanType;

/* loaded from: classes.dex */
public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable, ValueInstantiator.Gettable {
    private static final long serialVersionUID = 1;
    protected final Class<?> _valueClass;
    protected final JavaType _valueType;
    protected static final int F_MASK_INT_COERCIONS = DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.getMask() | DeserializationFeature.USE_LONG_FOR_INTS.getMask();
    @Deprecated
    protected static final int F_MASK_ACCEPT_ARRAYS = DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS.getMask() | DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.getMask();

    public static final boolean _neitherNull(Object obj, Object obj2) {
        return (obj == null || obj2 == null) ? false : true;
    }

    public final boolean _byteOverflow(int i) {
        return i < -128 || i > 255;
    }

    protected final boolean _intOverflow(long j) {
        return j < -2147483648L || j > 2147483647L;
    }

    public final boolean _shortOverflow(int i) {
        return i < -32768 || i > 32767;
    }

    public ValueInstantiator getValueInstantiator() {
        return null;
    }

    public StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
        this._valueType = null;
    }

    public StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? Object.class : javaType.getRawClass();
        this._valueType = javaType;
    }

    public StdDeserializer(StdDeserializer<?> stdDeserializer) {
        this._valueClass = stdDeserializer._valueClass;
        this._valueType = stdDeserializer._valueType;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Class<?> handledType() {
        return this._valueClass;
    }

    @Deprecated
    public final Class<?> getValueClass() {
        return this._valueClass;
    }

    public JavaType getValueType() {
        return this._valueType;
    }

    public JavaType getValueType(DeserializationContext deserializationContext) {
        JavaType javaType = this._valueType;
        return javaType != null ? javaType : deserializationContext.constructType(this._valueClass);
    }

    public boolean isDefaultDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return ClassUtil.isJacksonStdImpl(jsonDeserializer);
    }

    public boolean isDefaultKeyDeserializer(KeyDeserializer keyDeserializer) {
        return ClassUtil.isJacksonStdImpl(keyDeserializer);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    public T _deserializeFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CoercionAction _findCoercionFromEmptyArray = _findCoercionFromEmptyArray(deserializationContext);
        boolean isEnabled = deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (isEnabled || _findCoercionFromEmptyArray != CoercionAction.Fail) {
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[_findCoercionFromEmptyArray.ordinal()];
                if (i == 1) {
                    return (T) getEmptyValue(deserializationContext);
                }
                if (i == 2 || i == 3) {
                    return getNullValue(deserializationContext);
                }
            } else if (isEnabled) {
                T _deserializeWrappedValue = _deserializeWrappedValue(jsonParser, deserializationContext);
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    handleMissingEndArrayForSingle(jsonParser, deserializationContext);
                }
                return _deserializeWrappedValue;
            }
        }
        return (T) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), JsonToken.START_ARRAY, jsonParser, (String) null, new Object[0]);
    }

    /* renamed from: com.fasterxml.jackson.databind.deser.std.StdDeserializer$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction;

        static {
            int[] iArr = new int[CoercionAction.values().length];
            $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction = iArr;
            try {
                iArr[CoercionAction.AsEmpty.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.AsNull.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.TryConvert.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.Fail.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Deprecated
    protected T _deserializeFromEmpty(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.START_ARRAY) && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                return null;
            }
            return (T) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
        }
        return (T) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
    }

    public T _deserializeFromString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ValueInstantiator valueInstantiator = getValueInstantiator();
        Class<?> handledType = handledType();
        String valueAsString = jsonParser.getValueAsString();
        if (valueInstantiator != null && valueInstantiator.canCreateFromString()) {
            return (T) valueInstantiator.createFromString(deserializationContext, valueAsString);
        }
        if (valueAsString.isEmpty()) {
            return (T) _deserializeFromEmptyString(jsonParser, deserializationContext, deserializationContext.findCoercionAction(logicalType(), handledType, CoercionInputShape.EmptyString), handledType, "empty String (\"\")");
        }
        if (_isBlank(valueAsString)) {
            return (T) _deserializeFromEmptyString(jsonParser, deserializationContext, deserializationContext.findCoercionFromBlankString(logicalType(), handledType, CoercionAction.Fail), handledType, "blank String (all whitespace)");
        }
        if (valueInstantiator != null) {
            valueAsString = valueAsString.trim();
            if (valueInstantiator.canCreateFromInt() && deserializationContext.findCoercionAction(LogicalType.Integer, Integer.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
                return (T) valueInstantiator.createFromInt(deserializationContext, _parseIntPrimitive(deserializationContext, valueAsString));
            }
            if (valueInstantiator.canCreateFromLong() && deserializationContext.findCoercionAction(LogicalType.Integer, Long.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
                return (T) valueInstantiator.createFromLong(deserializationContext, _parseLongPrimitive(deserializationContext, valueAsString));
            }
            if (valueInstantiator.canCreateFromBoolean() && deserializationContext.findCoercionAction(LogicalType.Boolean, Boolean.class, CoercionInputShape.String) == CoercionAction.TryConvert) {
                String trim = valueAsString.trim();
                if (BooleanUtils.TRUE.equals(trim)) {
                    return (T) valueInstantiator.createFromBoolean(deserializationContext, true);
                }
                if (BooleanUtils.FALSE.equals(trim)) {
                    return (T) valueInstantiator.createFromBoolean(deserializationContext, false);
                }
            }
        }
        return (T) deserializationContext.handleMissingInstantiator(handledType, valueInstantiator, deserializationContext.getParser(), "no String-argument constructor/factory method to deserialize from String value ('%s')", valueAsString);
    }

    public Object _deserializeFromEmptyString(JsonParser jsonParser, DeserializationContext deserializationContext, CoercionAction coercionAction, Class<?> cls, String str) throws IOException {
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[coercionAction.ordinal()];
        if (i != 1) {
            if (i != 4) {
                return null;
            }
            _checkCoercionFail(deserializationContext, coercionAction, cls, "", "empty String (\"\")");
            return null;
        }
        return getEmptyValue(deserializationContext);
    }

    protected T _deserializeWrappedValue(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.START_ARRAY)) {
            return (T) handleNestedArrayForSingle(jsonParser, deserializationContext);
        }
        return deserialize(jsonParser, deserializationContext);
    }

    @Deprecated
    protected final boolean _parseBooleanPrimitive(DeserializationContext deserializationContext, JsonParser jsonParser, Class<?> cls) throws IOException {
        return _parseBooleanPrimitive(jsonParser, deserializationContext);
    }

    public final boolean _parseBooleanPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Boolean.TYPE);
        } else {
            if (currentTokenId != 3) {
                if (currentTokenId != 6) {
                    if (currentTokenId == 7) {
                        return Boolean.TRUE.equals(_coerceBooleanFromInt(jsonParser, deserializationContext, Boolean.TYPE));
                    }
                    switch (currentTokenId) {
                        case 9:
                            return true;
                        case 10:
                            return false;
                        case 11:
                            _verifyNullForPrimitive(deserializationContext);
                            return false;
                    }
                }
                extractScalarFromObject = jsonParser.getText();
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                    return ((Boolean) handleNestedArrayForSingle(jsonParser, deserializationContext)).booleanValue();
                }
                boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseBooleanPrimitive;
            }
            return ((Boolean) deserializationContext.handleUnexpectedToken(Boolean.TYPE, jsonParser)).booleanValue();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Boolean, Boolean.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            _verifyNullForPrimitive(deserializationContext);
            return false;
        } else if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return false;
        } else {
            String trim = extractScalarFromObject.trim();
            int length = trim.length();
            if (length == 4) {
                if (_isTrue(trim)) {
                    return true;
                }
            } else if (length == 5 && _isFalse(trim)) {
                return false;
            }
            if (_hasTextualNull(trim)) {
                _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                return false;
            }
            return Boolean.TRUE.equals((Boolean) deserializationContext.handleWeirdStringValue(Boolean.TYPE, trim, "only \"true\"/\"True\"/\"TRUE\" or \"false\"/\"False\"/\"FALSE\" recognized", new Object[0]));
        }
    }

    protected boolean _isTrue(String str) {
        char charAt = str.charAt(0);
        if (charAt == 't') {
            return BooleanUtils.TRUE.equals(str);
        }
        if (charAt == 'T') {
            return "TRUE".equals(str) || BooleanType.TRUE.equals(str);
        }
        return false;
    }

    protected boolean _isFalse(String str) {
        char charAt = str.charAt(0);
        if (charAt == 'f') {
            return BooleanUtils.FALSE.equals(str);
        }
        if (charAt == 'F') {
            return "FALSE".equals(str) || BooleanType.FALSE.equals(str);
        }
        return false;
    }

    public final Boolean _parseBoolean(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else if (currentTokenId == 3) {
            return (Boolean) _deserializeFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId != 6) {
                if (currentTokenId == 7) {
                    return _coerceBooleanFromInt(jsonParser, deserializationContext, cls);
                }
                switch (currentTokenId) {
                    case 9:
                        return true;
                    case 10:
                        return false;
                    case 11:
                        return null;
                    default:
                        return (Boolean) deserializationContext.handleUnexpectedToken(cls, jsonParser);
                }
            }
            extractScalarFromObject = jsonParser.getText();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Boolean, cls);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            return null;
        }
        if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return false;
        }
        String trim = extractScalarFromObject.trim();
        int length = trim.length();
        if (length == 4) {
            if (_isTrue(trim)) {
                return true;
            }
        } else if (length == 5 && _isFalse(trim)) {
            return false;
        }
        if (_checkTextualNull(deserializationContext, trim)) {
            return null;
        }
        return (Boolean) deserializationContext.handleWeirdStringValue(cls, trim, "only \"true\" or \"false\" recognized", new Object[0]);
    }

    public final byte _parseBytePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Byte.TYPE);
        } else {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return (byte) 0;
                } else if (currentTokenId == 6) {
                    extractScalarFromObject = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getByteValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Byte.TYPE);
                        if (_checkFloatToIntCoercion == CoercionAction.AsNull || _checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                            return (byte) 0;
                        }
                        return jsonParser.getByteValue();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                    return ((Byte) handleNestedArrayForSingle(jsonParser, deserializationContext)).byteValue();
                }
                byte _parseBytePrimitive = _parseBytePrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseBytePrimitive;
            }
            return ((Byte) deserializationContext.handleUnexpectedToken(deserializationContext.constructType(Byte.TYPE), jsonParser)).byteValue();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Integer, Byte.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            _verifyNullForPrimitive(deserializationContext);
            return (byte) 0;
        } else if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return (byte) 0;
        } else {
            String trim = extractScalarFromObject.trim();
            if (_hasTextualNull(trim)) {
                _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                return (byte) 0;
            }
            try {
                int parseInt = NumberInput.parseInt(trim);
                return _byteOverflow(parseInt) ? ((Byte) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "overflow, value cannot be represented as 8-bit value", new Object[0])).byteValue() : (byte) parseInt;
            } catch (IllegalArgumentException unused) {
                return ((Byte) deserializationContext.handleWeirdStringValue(this._valueClass, trim, "not a valid `byte` value", new Object[0])).byteValue();
            }
        }
    }

    public final short _parseShortPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Short.TYPE);
        } else {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return (short) 0;
                } else if (currentTokenId == 6) {
                    extractScalarFromObject = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getShortValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Short.TYPE);
                        if (_checkFloatToIntCoercion == CoercionAction.AsNull || _checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                            return (short) 0;
                        }
                        return jsonParser.getShortValue();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                    return ((Short) handleNestedArrayForSingle(jsonParser, deserializationContext)).shortValue();
                }
                short _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseShortPrimitive;
            }
            return ((Short) deserializationContext.handleUnexpectedToken(deserializationContext.constructType(Short.TYPE), jsonParser)).shortValue();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Integer, Short.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            _verifyNullForPrimitive(deserializationContext);
            return (short) 0;
        } else if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return (short) 0;
        } else {
            String trim = extractScalarFromObject.trim();
            if (_hasTextualNull(trim)) {
                _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                return (short) 0;
            }
            try {
                int parseInt = NumberInput.parseInt(trim);
                return _shortOverflow(parseInt) ? ((Short) deserializationContext.handleWeirdStringValue(Short.TYPE, trim, "overflow, value cannot be represented as 16-bit value", new Object[0])).shortValue() : (short) parseInt;
            } catch (IllegalArgumentException unused) {
                return ((Short) deserializationContext.handleWeirdStringValue(Short.TYPE, trim, "not a valid `short` value", new Object[0])).shortValue();
            }
        }
    }

    public final int _parseIntPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Integer.TYPE);
        } else {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0;
                } else if (currentTokenId == 6) {
                    extractScalarFromObject = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getIntValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Integer.TYPE);
                        if (_checkFloatToIntCoercion == CoercionAction.AsNull || _checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                            return 0;
                        }
                        return jsonParser.getValueAsInt();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                    return ((Integer) handleNestedArrayForSingle(jsonParser, deserializationContext)).intValue();
                }
                int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseIntPrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken(Integer.TYPE, jsonParser)).intValue();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Integer, Integer.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            _verifyNullForPrimitive(deserializationContext);
            return 0;
        } else if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return 0;
        } else {
            String trim = extractScalarFromObject.trim();
            if (_hasTextualNull(trim)) {
                _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                return 0;
            }
            return _parseIntPrimitive(deserializationContext, trim);
        }
    }

    protected final int _parseIntPrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            if (str.length() > 9) {
                long parseLong = NumberInput.parseLong(str);
                return _intOverflow(parseLong) ? _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Integer.TYPE, str, "Overflow: numeric value (%s) out of range of int (%d -%d)", str, Integer.MIN_VALUE, Integer.MAX_VALUE)).intValue() : (int) parseLong;
            }
            return NumberInput.parseInt(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Integer.TYPE, str, "not a valid `int` value", new Object[0])).intValue();
        }
    }

    public final Integer _parseInteger(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else if (currentTokenId == 3) {
            return (Integer) _deserializeFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId == 11) {
                return (Integer) getNullValue(deserializationContext);
            }
            if (currentTokenId != 6) {
                if (currentTokenId != 7) {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, cls);
                        if (_checkFloatToIntCoercion == CoercionAction.AsNull) {
                            return (Integer) getNullValue(deserializationContext);
                        }
                        if (_checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                            return (Integer) getEmptyValue(deserializationContext);
                        }
                        return Integer.valueOf(jsonParser.getValueAsInt());
                    }
                    return (Integer) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                }
                return Integer.valueOf(jsonParser.getIntValue());
            }
            extractScalarFromObject = jsonParser.getText();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            return (Integer) getNullValue(deserializationContext);
        }
        if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return (Integer) getEmptyValue(deserializationContext);
        }
        String trim = extractScalarFromObject.trim();
        if (_checkTextualNull(deserializationContext, trim)) {
            return (Integer) getNullValue(deserializationContext);
        }
        return _parseInteger(deserializationContext, trim);
    }

    protected final Integer _parseInteger(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            if (str.length() > 9) {
                long parseLong = NumberInput.parseLong(str);
                if (_intOverflow(parseLong)) {
                    return (Integer) deserializationContext.handleWeirdStringValue(Integer.class, str, "Overflow: numeric value (%s) out of range of `java.lang.Integer` (%d -%d)", str, Integer.MIN_VALUE, Integer.MAX_VALUE);
                }
                return Integer.valueOf((int) parseLong);
            }
            return Integer.valueOf(NumberInput.parseInt(str));
        } catch (IllegalArgumentException unused) {
            return (Integer) deserializationContext.handleWeirdStringValue(Integer.class, str, "not a valid `java.lang.Integer` value", new Object[0]);
        }
    }

    public final long _parseLongPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, Long.TYPE);
        } else {
            if (currentTokenId != 3) {
                if (currentTokenId == 11) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0L;
                } else if (currentTokenId == 6) {
                    extractScalarFromObject = jsonParser.getText();
                } else if (currentTokenId == 7) {
                    return jsonParser.getLongValue();
                } else {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, Long.TYPE);
                        if (_checkFloatToIntCoercion == CoercionAction.AsNull || _checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                            return 0L;
                        }
                        return jsonParser.getValueAsLong();
                    }
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                    return ((Long) handleNestedArrayForSingle(jsonParser, deserializationContext)).longValue();
                }
                long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseLongPrimitive;
            }
            return ((Number) deserializationContext.handleUnexpectedToken(Long.TYPE, jsonParser)).longValue();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject, LogicalType.Integer, Long.TYPE);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            _verifyNullForPrimitive(deserializationContext);
            return 0L;
        } else if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return 0L;
        } else {
            String trim = extractScalarFromObject.trim();
            if (_hasTextualNull(trim)) {
                _verifyNullForPrimitiveCoercion(deserializationContext, trim);
                return 0L;
            }
            return _parseLongPrimitive(deserializationContext, trim);
        }
    }

    protected final long _parseLongPrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return NumberInput.parseLong(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Long.TYPE, str, "not a valid `long` value", new Object[0])).longValue();
        }
    }

    public final Long _parseLong(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        String extractScalarFromObject;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, cls);
        } else if (currentTokenId == 3) {
            return (Long) _deserializeFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId == 11) {
                return (Long) getNullValue(deserializationContext);
            }
            if (currentTokenId != 6) {
                if (currentTokenId != 7) {
                    if (currentTokenId == 8) {
                        CoercionAction _checkFloatToIntCoercion = _checkFloatToIntCoercion(jsonParser, deserializationContext, cls);
                        if (_checkFloatToIntCoercion == CoercionAction.AsNull) {
                            return (Long) getNullValue(deserializationContext);
                        }
                        if (_checkFloatToIntCoercion == CoercionAction.AsEmpty) {
                            return (Long) getEmptyValue(deserializationContext);
                        }
                        return Long.valueOf(jsonParser.getValueAsLong());
                    }
                    return (Long) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                }
                return Long.valueOf(jsonParser.getLongValue());
            }
            extractScalarFromObject = jsonParser.getText();
        }
        CoercionAction _checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, extractScalarFromObject);
        if (_checkFromStringCoercion == CoercionAction.AsNull) {
            return (Long) getNullValue(deserializationContext);
        }
        if (_checkFromStringCoercion == CoercionAction.AsEmpty) {
            return (Long) getEmptyValue(deserializationContext);
        }
        String trim = extractScalarFromObject.trim();
        if (_checkTextualNull(deserializationContext, trim)) {
            return (Long) getNullValue(deserializationContext);
        }
        return _parseLong(deserializationContext, trim);
    }

    protected final Long _parseLong(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return Long.valueOf(NumberInput.parseLong(str));
        } catch (IllegalArgumentException unused) {
            return (Long) deserializationContext.handleWeirdStringValue(Long.class, str, "not a valid `java.lang.Long` value", new Object[0]);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x0017, code lost:
        if (r0 != 8) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float _parseFloatPrimitive(com.fasterxml.jackson.core.JsonParser r5, com.fasterxml.jackson.databind.DeserializationContext r6) throws java.io.IOException {
        /*
            r4 = this;
            int r0 = r5.currentTokenId()
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L68
            r1 = 3
            if (r0 == r1) goto L38
            r1 = 11
            if (r0 == r1) goto L34
            r1 = 6
            if (r0 == r1) goto L2f
            r1 = 7
            if (r0 == r1) goto L1a
            r1 = 8
            if (r0 == r1) goto L2a
            goto L5b
        L1a:
            java.lang.Class r0 = java.lang.Float.TYPE
            com.fasterxml.jackson.databind.cfg.CoercionAction r6 = r4._checkIntToFloatCoercion(r5, r6, r0)
            com.fasterxml.jackson.databind.cfg.CoercionAction r0 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull
            if (r6 != r0) goto L25
            return r2
        L25:
            com.fasterxml.jackson.databind.cfg.CoercionAction r0 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty
            if (r6 != r0) goto L2a
            return r2
        L2a:
            float r5 = r5.getFloatValue()
            return r5
        L2f:
            java.lang.String r0 = r5.getText()
            goto L6e
        L34:
            r4._verifyNullForPrimitive(r6)
            return r2
        L38:
            com.fasterxml.jackson.databind.DeserializationFeature r0 = com.fasterxml.jackson.databind.DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS
            boolean r0 = r6.isEnabled(r0)
            if (r0 == 0) goto L5b
            com.fasterxml.jackson.core.JsonToken r0 = r5.nextToken()
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.START_ARRAY
            if (r0 != r1) goto L53
            java.lang.Object r5 = r4.handleNestedArrayForSingle(r5, r6)
            java.lang.Float r5 = (java.lang.Float) r5
            float r5 = r5.floatValue()
            return r5
        L53:
            float r0 = r4._parseFloatPrimitive(r5, r6)
            r4._verifyEndArrayForSingle(r5, r6)
            return r0
        L5b:
            java.lang.Class r0 = java.lang.Float.TYPE
            java.lang.Object r5 = r6.handleUnexpectedToken(r0, r5)
            java.lang.Number r5 = (java.lang.Number) r5
            float r5 = r5.floatValue()
            return r5
        L68:
            java.lang.Class r0 = java.lang.Float.TYPE
            java.lang.String r0 = r6.extractScalarFromObject(r5, r4, r0)
        L6e:
            java.lang.Float r1 = r4._checkFloatSpecialValue(r0)
            if (r1 == 0) goto L79
            float r5 = r1.floatValue()
            return r5
        L79:
            com.fasterxml.jackson.databind.type.LogicalType r1 = com.fasterxml.jackson.databind.type.LogicalType.Integer
            java.lang.Class r3 = java.lang.Float.TYPE
            com.fasterxml.jackson.databind.cfg.CoercionAction r1 = r4._checkFromStringCoercion(r6, r0, r1, r3)
            com.fasterxml.jackson.databind.cfg.CoercionAction r3 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull
            if (r1 != r3) goto L89
            r4._verifyNullForPrimitive(r6)
            return r2
        L89:
            com.fasterxml.jackson.databind.cfg.CoercionAction r3 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty
            if (r1 != r3) goto L8e
            return r2
        L8e:
            java.lang.String r0 = r0.trim()
            boolean r1 = r4._hasTextualNull(r0)
            if (r1 == 0) goto L9c
            r4._verifyNullForPrimitiveCoercion(r6, r0)
            return r2
        L9c:
            float r5 = r4._parseFloatPrimitive(r5, r6, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer._parseFloatPrimitive(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext):float");
    }

    protected final float _parseFloatPrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return NumberInput.parseFloat(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Float.TYPE, str, "not a valid `float` value", new Object[0])).floatValue();
        }
    }

    protected final float _parseFloatPrimitive(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return NumberInput.parseFloat(str, jsonParser.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER));
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Float.TYPE, str, "not a valid `float` value", new Object[0])).floatValue();
        }
    }

    public Float _checkFloatSpecialValue(String str) {
        if (str.isEmpty()) {
            return null;
        }
        char charAt = str.charAt(0);
        if (charAt == '-') {
            if (_isNegInf(str)) {
                return Float.valueOf(Float.NEGATIVE_INFINITY);
            }
            return null;
        } else if (charAt == 'I') {
            if (_isPosInf(str)) {
                return Float.valueOf(Float.POSITIVE_INFINITY);
            }
            return null;
        } else if (charAt == 'N' && _isNaN(str)) {
            return Float.valueOf(Float.NaN);
        } else {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x0018, code lost:
        if (r0 != 8) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double _parseDoublePrimitive(com.fasterxml.jackson.core.JsonParser r6, com.fasterxml.jackson.databind.DeserializationContext r7) throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r6.currentTokenId()
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L69
            r1 = 3
            if (r0 == r1) goto L39
            r1 = 11
            if (r0 == r1) goto L35
            r1 = 6
            if (r0 == r1) goto L30
            r1 = 7
            if (r0 == r1) goto L1b
            r1 = 8
            if (r0 == r1) goto L2b
            goto L5c
        L1b:
            java.lang.Class r0 = java.lang.Double.TYPE
            com.fasterxml.jackson.databind.cfg.CoercionAction r7 = r5._checkIntToFloatCoercion(r6, r7, r0)
            com.fasterxml.jackson.databind.cfg.CoercionAction r0 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull
            if (r7 != r0) goto L26
            return r2
        L26:
            com.fasterxml.jackson.databind.cfg.CoercionAction r0 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty
            if (r7 != r0) goto L2b
            return r2
        L2b:
            double r6 = r6.getDoubleValue()
            return r6
        L30:
            java.lang.String r0 = r6.getText()
            goto L6f
        L35:
            r5._verifyNullForPrimitive(r7)
            return r2
        L39:
            com.fasterxml.jackson.databind.DeserializationFeature r0 = com.fasterxml.jackson.databind.DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS
            boolean r0 = r7.isEnabled(r0)
            if (r0 == 0) goto L5c
            com.fasterxml.jackson.core.JsonToken r0 = r6.nextToken()
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.START_ARRAY
            if (r0 != r1) goto L54
            java.lang.Object r6 = r5.handleNestedArrayForSingle(r6, r7)
            java.lang.Double r6 = (java.lang.Double) r6
            double r6 = r6.doubleValue()
            return r6
        L54:
            double r0 = r5._parseDoublePrimitive(r6, r7)
            r5._verifyEndArrayForSingle(r6, r7)
            return r0
        L5c:
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.Object r6 = r7.handleUnexpectedToken(r0, r6)
            java.lang.Number r6 = (java.lang.Number) r6
            double r6 = r6.doubleValue()
            return r6
        L69:
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.String r0 = r7.extractScalarFromObject(r6, r5, r0)
        L6f:
            java.lang.Double r1 = r5._checkDoubleSpecialValue(r0)
            if (r1 == 0) goto L7a
            double r6 = r1.doubleValue()
            return r6
        L7a:
            com.fasterxml.jackson.databind.type.LogicalType r1 = com.fasterxml.jackson.databind.type.LogicalType.Integer
            java.lang.Class r4 = java.lang.Double.TYPE
            com.fasterxml.jackson.databind.cfg.CoercionAction r1 = r5._checkFromStringCoercion(r7, r0, r1, r4)
            com.fasterxml.jackson.databind.cfg.CoercionAction r4 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull
            if (r1 != r4) goto L8a
            r5._verifyNullForPrimitive(r7)
            return r2
        L8a:
            com.fasterxml.jackson.databind.cfg.CoercionAction r4 = com.fasterxml.jackson.databind.cfg.CoercionAction.AsEmpty
            if (r1 != r4) goto L8f
            return r2
        L8f:
            java.lang.String r0 = r0.trim()
            boolean r1 = r5._hasTextualNull(r0)
            if (r1 == 0) goto L9d
            r5._verifyNullForPrimitiveCoercion(r7, r0)
            return r2
        L9d:
            double r6 = r5._parseDoublePrimitive(r6, r7, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer._parseDoublePrimitive(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext):double");
    }

    protected final double _parseDoublePrimitive(DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return _parseDouble(str);
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Double.TYPE, str, "not a valid `double` value (as String to convert)", new Object[0])).doubleValue();
        }
    }

    protected final double _parseDoublePrimitive(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        try {
            return _parseDouble(str, jsonParser.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER));
        } catch (IllegalArgumentException unused) {
            return _nonNullNumber((Number) deserializationContext.handleWeirdStringValue(Double.TYPE, str, "not a valid `double` value (as String to convert)", new Object[0])).doubleValue();
        }
    }

    protected static final double _parseDouble(String str) throws NumberFormatException {
        return _parseDouble(str, false);
    }

    public static final double _parseDouble(String str, boolean z) throws NumberFormatException {
        return NumberInput.parseDouble(str, z);
    }

    public Double _checkDoubleSpecialValue(String str) {
        if (str.isEmpty()) {
            return null;
        }
        char charAt = str.charAt(0);
        if (charAt == '-') {
            if (_isNegInf(str)) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            return null;
        } else if (charAt == 'I') {
            if (_isPosInf(str)) {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            }
            return null;
        } else if (charAt == 'N' && _isNaN(str)) {
            return Double.valueOf(Double.NaN);
        } else {
            return null;
        }
    }

    public Date _parseDate(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String extractScalarFromObject;
        long longValue;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            extractScalarFromObject = deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
        } else if (currentTokenId == 3) {
            return _parseDateFromArray(jsonParser, deserializationContext);
        } else {
            if (currentTokenId == 11) {
                return (Date) getNullValue(deserializationContext);
            }
            if (currentTokenId != 6) {
                if (currentTokenId == 7) {
                    try {
                        longValue = jsonParser.getLongValue();
                    } catch (StreamReadException unused) {
                        longValue = ((Number) deserializationContext.handleWeirdNumberValue(this._valueClass, jsonParser.getNumberValue(), "not a valid 64-bit `long` for creating `java.util.Date`", new Object[0])).longValue();
                    }
                    return new Date(longValue);
                }
                return (Date) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
            }
            extractScalarFromObject = jsonParser.getText();
        }
        return _parseDate(extractScalarFromObject.trim(), deserializationContext);
    }

    protected Date _parseDateFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CoercionAction _findCoercionFromEmptyArray = _findCoercionFromEmptyArray(deserializationContext);
        boolean isEnabled = deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (isEnabled || _findCoercionFromEmptyArray != CoercionAction.Fail) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[_findCoercionFromEmptyArray.ordinal()];
                if (i == 1) {
                    return (Date) getEmptyValue(deserializationContext);
                }
                if (i == 2 || i == 3) {
                    return (Date) getNullValue(deserializationContext);
                }
            } else if (isEnabled) {
                if (nextToken == JsonToken.START_ARRAY) {
                    return (Date) handleNestedArrayForSingle(jsonParser, deserializationContext);
                }
                Date _parseDate = _parseDate(jsonParser, deserializationContext);
                _verifyEndArrayForSingle(jsonParser, deserializationContext);
                return _parseDate;
            }
        }
        return (Date) deserializationContext.handleUnexpectedToken(this._valueClass, JsonToken.START_ARRAY, jsonParser, (String) null, new Object[0]);
    }

    public Date _parseDate(String str, DeserializationContext deserializationContext) throws IOException {
        try {
            if (str.isEmpty()) {
                if (AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[_checkFromStringCoercion(deserializationContext, str).ordinal()] != 1) {
                    return null;
                }
                return new Date(0L);
            } else if (_hasTextualNull(str)) {
                return null;
            } else {
                return deserializationContext.parseDate(str);
            }
        } catch (IllegalArgumentException e) {
            return (Date) deserializationContext.handleWeirdStringValue(this._valueClass, str, "not a valid representation (error: %s)", ClassUtil.exceptionMessage(e));
        }
    }

    @Deprecated
    protected final String _parseString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return _parseString(jsonParser, deserializationContext, NullsConstantProvider.nuller());
    }

    public final String _parseString(JsonParser jsonParser, DeserializationContext deserializationContext, NullValueProvider nullValueProvider) throws IOException {
        String valueAsString;
        CoercionAction coercionAction = CoercionAction.TryConvert;
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 1) {
            if (currentTokenId != 12) {
                switch (currentTokenId) {
                    case 6:
                        return jsonParser.getText();
                    case 7:
                        coercionAction = _checkIntToStringCoercion(jsonParser, deserializationContext, String.class);
                        break;
                    case 8:
                        coercionAction = _checkFloatToStringCoercion(jsonParser, deserializationContext, String.class);
                        break;
                    case 9:
                    case 10:
                        coercionAction = _checkBooleanToStringCoercion(jsonParser, deserializationContext, String.class);
                        break;
                }
                if (coercionAction == CoercionAction.AsNull) {
                    return (String) nullValueProvider.getNullValue(deserializationContext);
                }
                return coercionAction == CoercionAction.AsEmpty ? "" : (!jsonParser.currentToken().isScalarValue() || (valueAsString = jsonParser.getValueAsString()) == null) ? (String) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser) : valueAsString;
            }
            Object embeddedObject = jsonParser.getEmbeddedObject();
            if (embeddedObject instanceof byte[]) {
                return deserializationContext.getBase64Variant().encode((byte[]) embeddedObject, false);
            }
            if (embeddedObject == null) {
                return null;
            }
            return embeddedObject.toString();
        }
        return deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass);
    }

    public boolean _hasTextualNull(String str) {
        return "null".equals(str);
    }

    public final boolean _isNegInf(String str) {
        return "-Infinity".equals(str) || "-INF".equals(str);
    }

    public final boolean _isPosInf(String str) {
        return "Infinity".equals(str) || "INF".equals(str);
    }

    public final boolean _isNaN(String str) {
        return "NaN".equals(str);
    }

    public static final boolean _isBlank(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > ' ') {
                return false;
            }
        }
        return true;
    }

    public CoercionAction _checkFromStringCoercion(DeserializationContext deserializationContext, String str) throws IOException {
        return _checkFromStringCoercion(deserializationContext, str, logicalType(), handledType());
    }

    protected CoercionAction _checkFromStringCoercion(DeserializationContext deserializationContext, String str, LogicalType logicalType, Class<?> cls) throws IOException {
        if (str.isEmpty()) {
            return _checkCoercionFail(deserializationContext, deserializationContext.findCoercionAction(logicalType, cls, CoercionInputShape.EmptyString), cls, str, "empty String (\"\")");
        }
        if (_isBlank(str)) {
            return _checkCoercionFail(deserializationContext, deserializationContext.findCoercionFromBlankString(logicalType, cls, CoercionAction.Fail), cls, str, "blank String (all whitespace)");
        }
        if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS)) {
            return CoercionAction.TryConvert;
        }
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(logicalType, cls, CoercionInputShape.String);
        if (findCoercionAction == CoercionAction.Fail) {
            deserializationContext.reportInputMismatch(this, "Cannot coerce String value (\"%s\") to %s (but might if coercion using `CoercionConfig` was enabled)", str, _coercedTypeDesc());
        }
        return findCoercionAction;
    }

    public CoercionAction _checkFloatToIntCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(LogicalType.Integer, cls, CoercionInputShape.Float);
        return findCoercionAction == CoercionAction.Fail ? _checkCoercionFail(deserializationContext, findCoercionAction, cls, jsonParser.getNumberValue(), "Floating-point value (" + jsonParser.getText() + ")") : findCoercionAction;
    }

    protected CoercionAction _checkIntToStringCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        return _checkToStringCoercion(jsonParser, deserializationContext, cls, jsonParser.getNumberValue(), CoercionInputShape.Integer);
    }

    protected CoercionAction _checkFloatToStringCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        return _checkToStringCoercion(jsonParser, deserializationContext, cls, jsonParser.getNumberValue(), CoercionInputShape.Float);
    }

    protected CoercionAction _checkBooleanToStringCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        return _checkToStringCoercion(jsonParser, deserializationContext, cls, Boolean.valueOf(jsonParser.getBooleanValue()), CoercionInputShape.Boolean);
    }

    protected CoercionAction _checkToStringCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls, Object obj, CoercionInputShape coercionInputShape) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(LogicalType.Textual, cls, coercionInputShape);
        return findCoercionAction == CoercionAction.Fail ? _checkCoercionFail(deserializationContext, findCoercionAction, cls, obj, coercionInputShape.name() + " value (" + jsonParser.getText() + ")") : findCoercionAction;
    }

    public CoercionAction _checkIntToFloatCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(LogicalType.Float, cls, CoercionInputShape.Integer);
        return findCoercionAction == CoercionAction.Fail ? _checkCoercionFail(deserializationContext, findCoercionAction, cls, jsonParser.getNumberValue(), "Integer value (" + jsonParser.getText() + ")") : findCoercionAction;
    }

    protected Boolean _coerceBooleanFromInt(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(LogicalType.Boolean, cls, CoercionInputShape.Integer);
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[findCoercionAction.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 4) {
                    _checkCoercionFail(deserializationContext, findCoercionAction, cls, jsonParser.getNumberValue(), "Integer value (" + jsonParser.getText() + ")");
                    return Boolean.FALSE;
                } else if (jsonParser.getNumberType() == JsonParser.NumberType.INT) {
                    return Boolean.valueOf(jsonParser.getIntValue() != 0);
                } else {
                    return Boolean.valueOf(!"0".equals(jsonParser.getText()));
                }
            }
            return null;
        }
        return Boolean.FALSE;
    }

    public CoercionAction _checkCoercionFail(DeserializationContext deserializationContext, CoercionAction coercionAction, Class<?> cls, Object obj, String str) throws IOException {
        if (coercionAction == CoercionAction.Fail) {
            deserializationContext.reportBadCoercion(this, cls, obj, "Cannot coerce %s to %s (but could if coercion was enabled using `CoercionConfig`)", str, _coercedTypeDesc(cls));
        }
        return coercionAction;
    }

    public boolean _checkTextualNull(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        if (_hasTextualNull(str)) {
            if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
                _reportFailedNullCoerce(deserializationContext, true, MapperFeature.ALLOW_COERCION_OF_SCALARS, "String \"null\"");
            }
            return true;
        }
        return false;
    }

    public Object _coerceIntegral(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
            return jsonParser.getBigIntegerValue();
        }
        if (deserializationContext.isEnabled(DeserializationFeature.USE_LONG_FOR_INTS)) {
            return Long.valueOf(jsonParser.getLongValue());
        }
        return jsonParser.getNumberValue();
    }

    public final void _verifyNullForPrimitive(DeserializationContext deserializationContext) throws JsonMappingException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            deserializationContext.reportInputMismatch(this, "Cannot coerce `null` to %s (disable `DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES` to allow)", _coercedTypeDesc());
        }
    }

    protected final void _verifyNullForPrimitiveCoercion(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        Enum<?> r0;
        boolean z;
        if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            r0 = MapperFeature.ALLOW_COERCION_OF_SCALARS;
            z = true;
        } else if (!deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            return;
        } else {
            r0 = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
            z = false;
        }
        _reportFailedNullCoerce(deserializationContext, z, r0, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
    }

    protected void _reportFailedNullCoerce(DeserializationContext deserializationContext, boolean z, Enum<?> r5, String str) throws JsonMappingException {
        deserializationContext.reportInputMismatch(this, "Cannot coerce %s to Null value as %s (%s `%s.%s` to allow)", str, _coercedTypeDesc(), z ? "enable" : "disable", r5.getDeclaringClass().getSimpleName(), r5.name());
    }

    public String _coercedTypeDesc() {
        boolean isCollectionMapOrArray;
        String classDescription;
        JavaType valueType = getValueType();
        if (valueType != null && !valueType.isPrimitive()) {
            isCollectionMapOrArray = valueType.isContainerType() || valueType.isReferenceType();
            classDescription = ClassUtil.getTypeDescription(valueType);
        } else {
            Class<?> handledType = handledType();
            isCollectionMapOrArray = ClassUtil.isCollectionMapOrArray(handledType);
            classDescription = ClassUtil.getClassDescription(handledType);
        }
        if (isCollectionMapOrArray) {
            return "element of " + classDescription;
        }
        return classDescription + " value";
    }

    protected String _coercedTypeDesc(Class<?> cls) {
        String classDescription = ClassUtil.getClassDescription(cls);
        if (ClassUtil.isCollectionMapOrArray(cls)) {
            return "element of " + classDescription;
        }
        return classDescription + " value";
    }

    @Deprecated
    protected boolean _parseBooleanFromInt(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        _verifyNumberForScalarCoercion(deserializationContext, jsonParser);
        return !"0".equals(jsonParser.getText());
    }

    @Deprecated
    protected void _verifyStringForScalarCoercion(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (deserializationContext.isEnabled(mapperFeature)) {
            return;
        }
        deserializationContext.reportInputMismatch(this, "Cannot coerce String \"%s\" to %s (enable `%s.%s` to allow)", str, _coercedTypeDesc(), mapperFeature.getDeclaringClass().getSimpleName(), mapperFeature.name());
    }

    @Deprecated
    protected Object _coerceEmptyString(DeserializationContext deserializationContext, boolean z) throws JsonMappingException {
        Enum<?> r4;
        boolean z2;
        if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            r4 = MapperFeature.ALLOW_COERCION_OF_SCALARS;
            z2 = true;
        } else if (z && deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            r4 = DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
            z2 = false;
        } else {
            return getNullValue(deserializationContext);
        }
        _reportFailedNullCoerce(deserializationContext, z2, r4, "empty String (\"\")");
        return null;
    }

    @Deprecated
    protected void _failDoubleToIntCoercion(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        deserializationContext.reportInputMismatch(handledType(), "Cannot coerce a floating-point value ('%s') into %s (enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow)", jsonParser.getValueAsString(), str);
    }

    @Deprecated
    protected final void _verifyNullForScalarCoercion(DeserializationContext deserializationContext, String str) throws JsonMappingException {
        if (deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            return;
        }
        _reportFailedNullCoerce(deserializationContext, true, MapperFeature.ALLOW_COERCION_OF_SCALARS, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
    }

    @Deprecated
    protected void _verifyNumberForScalarCoercion(DeserializationContext deserializationContext, JsonParser jsonParser) throws IOException {
        MapperFeature mapperFeature = MapperFeature.ALLOW_COERCION_OF_SCALARS;
        if (deserializationContext.isEnabled(mapperFeature)) {
            return;
        }
        deserializationContext.reportInputMismatch(this, "Cannot coerce Number (%s) to %s (enable `%s.%s` to allow)", jsonParser.getText(), _coercedTypeDesc(), mapperFeature.getDeclaringClass().getSimpleName(), mapperFeature.name());
    }

    @Deprecated
    protected Object _coerceNullToken(DeserializationContext deserializationContext, boolean z) throws JsonMappingException {
        if (z) {
            _verifyNullForPrimitive(deserializationContext);
        }
        return getNullValue(deserializationContext);
    }

    @Deprecated
    protected Object _coerceTextualNull(DeserializationContext deserializationContext, boolean z) throws JsonMappingException {
        if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            _reportFailedNullCoerce(deserializationContext, true, MapperFeature.ALLOW_COERCION_OF_SCALARS, "String \"null\"");
        }
        return getNullValue(deserializationContext);
    }

    @Deprecated
    protected boolean _isEmptyOrTextualNull(String str) {
        return str.isEmpty() || "null".equals(str);
    }

    public JsonDeserializer<Object> findDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return deserializationContext.findContextualValueDeserializer(javaType, beanProperty);
    }

    public final boolean _isIntNumber(String str) {
        int i;
        int length = str.length();
        if (length > 0) {
            char charAt = str.charAt(0);
            if (charAt != '-' && charAt != '+') {
                i = 0;
            } else if (length == 1) {
                return false;
            } else {
                i = 1;
            }
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 > '9' || charAt2 < '0') {
                    return false;
                }
                i++;
            }
            return true;
        }
        return false;
    }

    public JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        AnnotatedMember member;
        Object findDeserializationContentConverter;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (!_neitherNull(annotationIntrospector, beanProperty) || (member = beanProperty.getMember()) == null || (findDeserializationContentConverter = annotationIntrospector.findDeserializationContentConverter(member)) == null) {
            return jsonDeserializer;
        }
        Converter<Object, Object> converterInstance = deserializationContext.converterInstance(beanProperty.getMember(), findDeserializationContentConverter);
        JavaType inputType = converterInstance.getInputType(deserializationContext.getTypeFactory());
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(inputType, beanProperty);
        }
        return new StdDelegatingDeserializer(converterInstance, inputType, jsonDeserializer);
    }

    public JsonFormat.Value findFormatOverrides(DeserializationContext deserializationContext, BeanProperty beanProperty, Class<?> cls) {
        if (beanProperty != null) {
            return beanProperty.findPropertyFormat(deserializationContext.getConfig(), cls);
        }
        return deserializationContext.getDefaultPropertyFormat(cls);
    }

    public Boolean findFormatFeature(DeserializationContext deserializationContext, BeanProperty beanProperty, Class<?> cls, JsonFormat.Feature feature) {
        JsonFormat.Value findFormatOverrides = findFormatOverrides(deserializationContext, beanProperty, cls);
        if (findFormatOverrides != null) {
            return findFormatOverrides.getFeature(feature);
        }
        return null;
    }

    public final NullValueProvider findValueNullProvider(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty, PropertyMetadata propertyMetadata) throws JsonMappingException {
        if (settableBeanProperty != null) {
            return _findNullProvider(deserializationContext, settableBeanProperty, propertyMetadata.getValueNulls(), settableBeanProperty.getValueDeserializer());
        }
        return null;
    }

    public NullValueProvider findContentNullProvider(DeserializationContext deserializationContext, BeanProperty beanProperty, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Nulls findContentNullStyle = findContentNullStyle(deserializationContext, beanProperty);
        if (findContentNullStyle == Nulls.SKIP) {
            return NullsConstantProvider.skipper();
        }
        if (findContentNullStyle != Nulls.FAIL) {
            NullValueProvider _findNullProvider = _findNullProvider(deserializationContext, beanProperty, findContentNullStyle, jsonDeserializer);
            return _findNullProvider != null ? _findNullProvider : jsonDeserializer;
        } else if (beanProperty == null) {
            JavaType constructType = deserializationContext.constructType(jsonDeserializer.handledType());
            if (constructType.isContainerType()) {
                constructType = constructType.getContentType();
            }
            return NullsFailProvider.constructForRootValue(constructType);
        } else {
            return NullsFailProvider.constructForProperty(beanProperty, beanProperty.getType().getContentType());
        }
    }

    public Nulls findContentNullStyle(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            return beanProperty.getMetadata().getContentNulls();
        }
        return deserializationContext.getConfig().getDefaultSetterInfo().getContentNulls();
    }

    protected final NullValueProvider _findNullProvider(DeserializationContext deserializationContext, BeanProperty beanProperty, Nulls nulls, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (nulls == Nulls.FAIL) {
            if (beanProperty == null) {
                return NullsFailProvider.constructForRootValue(deserializationContext.constructType(jsonDeserializer == null ? Object.class : jsonDeserializer.handledType()));
            }
            return NullsFailProvider.constructForProperty(beanProperty);
        } else if (nulls != Nulls.AS_EMPTY) {
            if (nulls == Nulls.SKIP) {
                return NullsConstantProvider.skipper();
            }
            return null;
        } else if (jsonDeserializer == null) {
            return null;
        } else {
            if (jsonDeserializer instanceof BeanDeserializerBase) {
                BeanDeserializerBase beanDeserializerBase = (BeanDeserializerBase) jsonDeserializer;
                if (!beanDeserializerBase.getValueInstantiator().canCreateUsingDefault()) {
                    JavaType valueType = beanProperty == null ? beanDeserializerBase.getValueType() : beanProperty.getType();
                    return (NullValueProvider) deserializationContext.reportBadDefinition(valueType, String.format("Cannot create empty instance of %s, no default Creator", valueType));
                }
            }
            AccessPattern emptyAccessPattern = jsonDeserializer.getEmptyAccessPattern();
            if (emptyAccessPattern == AccessPattern.ALWAYS_NULL) {
                return NullsConstantProvider.nuller();
            }
            if (emptyAccessPattern == AccessPattern.CONSTANT) {
                return NullsConstantProvider.forValue(jsonDeserializer.getEmptyValue(deserializationContext));
            }
            return new NullsAsEmptyProvider(jsonDeserializer);
        }
    }

    public CoercionAction _findCoercionFromEmptyString(DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyString);
    }

    public CoercionAction _findCoercionFromEmptyArray(DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyArray);
    }

    public CoercionAction _findCoercionFromBlankString(DeserializationContext deserializationContext) {
        return deserializationContext.findCoercionFromBlankString(logicalType(), handledType(), CoercionAction.Fail);
    }

    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        if (obj == null) {
            obj = handledType();
        }
        if (deserializationContext.handleUnknownProperty(jsonParser, this, obj, str)) {
            return;
        }
        jsonParser.skipChildren();
    }

    public void handleMissingEndArrayForSingle(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        deserializationContext.reportWrongTokenException(this, JsonToken.END_ARRAY, "Attempted to unwrap '%s' value from an array (with `DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS`) but it contains more than one value", handledType().getName());
    }

    protected Object handleNestedArrayForSingle(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser.currentToken(), jsonParser, String.format("Cannot deserialize instance of %s out of %s token: nested Arrays not allowed with %s", ClassUtil.nameOf(this._valueClass), JsonToken.START_ARRAY, "DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS"), new Object[0]);
    }

    protected void _verifyEndArrayForSingle(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            handleMissingEndArrayForSingle(jsonParser, deserializationContext);
        }
    }

    protected Number _nonNullNumber(Number number) {
        if (number == null) {
            return 0;
        }
        return number;
    }
}
