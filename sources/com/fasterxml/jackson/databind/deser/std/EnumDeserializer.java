package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.CompactStringObjectMap;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@JacksonStdImpl
/* loaded from: classes.dex */
public class EnumDeserializer extends StdScalarDeserializer<Object> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final Boolean _caseInsensitive;
    private final Enum<?> _enumDefaultValue;
    protected Object[] _enumsByIndex;
    protected final boolean _isFromIntValue;
    protected final CompactStringObjectMap _lookupByEnumNaming;
    protected final CompactStringObjectMap _lookupByName;
    protected volatile CompactStringObjectMap _lookupByToString;
    private Boolean _useDefaultValueForUnknownEnum;
    private Boolean _useNullForUnknownEnum;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return true;
    }

    public EnumDeserializer(EnumResolver enumResolver, Boolean bool) {
        this(enumResolver, bool.booleanValue(), null);
    }

    public EnumDeserializer(EnumResolver enumResolver, boolean z, EnumResolver enumResolver2) {
        super(enumResolver.getEnumClass());
        this._lookupByName = enumResolver.constructLookup();
        this._enumsByIndex = enumResolver.getRawEnums();
        this._enumDefaultValue = enumResolver.getDefaultValue();
        this._caseInsensitive = Boolean.valueOf(z);
        this._isFromIntValue = enumResolver.isFromIntValue();
        this._lookupByEnumNaming = enumResolver2 == null ? null : enumResolver2.constructLookup();
    }

    protected EnumDeserializer(EnumDeserializer enumDeserializer, Boolean bool, Boolean bool2, Boolean bool3) {
        super(enumDeserializer);
        this._lookupByName = enumDeserializer._lookupByName;
        this._enumsByIndex = enumDeserializer._enumsByIndex;
        this._enumDefaultValue = enumDeserializer._enumDefaultValue;
        this._caseInsensitive = bool;
        this._isFromIntValue = enumDeserializer._isFromIntValue;
        this._useDefaultValueForUnknownEnum = bool2;
        this._useNullForUnknownEnum = bool3;
        this._lookupByEnumNaming = enumDeserializer._lookupByEnumNaming;
    }

    @Deprecated
    protected EnumDeserializer(EnumDeserializer enumDeserializer, Boolean bool) {
        this(enumDeserializer, bool, null, null);
    }

    @Deprecated
    public EnumDeserializer(EnumResolver enumResolver) {
        this(enumResolver, (Boolean) null);
    }

    @Deprecated
    public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMethod annotatedMethod) {
        return deserializerForCreator(deserializationConfig, cls, annotatedMethod, null, null);
    }

    public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMethod annotatedMethod, ValueInstantiator valueInstantiator, SettableBeanProperty[] settableBeanPropertyArr) {
        if (deserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getMember(), deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return new FactoryBasedEnumDeserializer(cls, annotatedMethod, annotatedMethod.getParameterType(0), valueInstantiator, settableBeanPropertyArr);
    }

    public static JsonDeserializer<?> deserializerForNoArgsCreator(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMethod annotatedMethod) {
        if (deserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getMember(), deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return new FactoryBasedEnumDeserializer(cls, annotatedMethod);
    }

    public EnumDeserializer withResolved(Boolean bool, Boolean bool2, Boolean bool3) {
        return (Objects.equals(this._caseInsensitive, bool) && Objects.equals(this._useDefaultValueForUnknownEnum, bool2) && Objects.equals(this._useNullForUnknownEnum, bool3)) ? this : new EnumDeserializer(this, bool, bool2, bool3);
    }

    @Deprecated
    public EnumDeserializer withResolved(Boolean bool) {
        return withResolved(bool, this._useDefaultValueForUnknownEnum, this._useNullForUnknownEnum);
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        return withResolved((Boolean) Optional.ofNullable(findFormatFeature(deserializationContext, beanProperty, handledType(), JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)).orElse(this._caseInsensitive), (Boolean) Optional.ofNullable(findFormatFeature(deserializationContext, beanProperty, handledType(), JsonFormat.Feature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)).orElse(this._useDefaultValueForUnknownEnum), (Boolean) Optional.ofNullable(findFormatFeature(deserializationContext, beanProperty, handledType(), JsonFormat.Feature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)).orElse(this._useNullForUnknownEnum));
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Enum;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return this._enumDefaultValue;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return _fromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            if (this._isFromIntValue) {
                return _fromString(jsonParser, deserializationContext, jsonParser.getText());
            }
            return _fromInteger(jsonParser, deserializationContext, jsonParser.getIntValue());
        } else if (jsonParser.isExpectedStartObjectToken()) {
            return _fromString(jsonParser, deserializationContext, deserializationContext.extractScalarFromObject(jsonParser, this, this._valueClass));
        } else {
            return _deserializeOther(jsonParser, deserializationContext);
        }
    }

    protected Object _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        Object find;
        CompactStringObjectMap _resolveCurrentLookup = _resolveCurrentLookup(deserializationContext);
        Object find2 = _resolveCurrentLookup.find(str);
        if (find2 == null) {
            String trim = str.trim();
            return (trim == str || (find = _resolveCurrentLookup.find(trim)) == null) ? _deserializeAltString(jsonParser, deserializationContext, _resolveCurrentLookup, trim) : find;
        }
        return find2;
    }

    private CompactStringObjectMap _resolveCurrentLookup(DeserializationContext deserializationContext) {
        CompactStringObjectMap compactStringObjectMap = this._lookupByEnumNaming;
        return compactStringObjectMap != null ? compactStringObjectMap : deserializationContext.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING) ? _getToStringLookup(deserializationContext) : this._lookupByName;
    }

    protected Object _fromInteger(JsonParser jsonParser, DeserializationContext deserializationContext, int i) throws IOException {
        CoercionAction findCoercionAction = deserializationContext.findCoercionAction(logicalType(), handledType(), CoercionInputShape.Integer);
        if (findCoercionAction == CoercionAction.Fail) {
            if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
                return deserializationContext.handleWeirdNumberValue(_enumClass(), Integer.valueOf(i), "not allowed to deserialize Enum value out of number: disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow", new Object[0]);
            }
            _checkCoercionFail(deserializationContext, findCoercionAction, handledType(), Integer.valueOf(i), "Integer value (" + i + ")");
        }
        int i2 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[findCoercionAction.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return getEmptyValue(deserializationContext);
            }
            if (i >= 0) {
                Object[] objArr = this._enumsByIndex;
                if (i < objArr.length) {
                    return objArr[i];
                }
            }
            if (useDefaultValueForUnknownEnum(deserializationContext)) {
                return this._enumDefaultValue;
            }
            if (useNullForUnknownEnum(deserializationContext)) {
                return null;
            }
            return deserializationContext.handleWeirdNumberValue(_enumClass(), Integer.valueOf(i), "index value outside legal index range [0..%s]", Integer.valueOf(this._enumsByIndex.length - 1));
        }
        return null;
    }

    /* renamed from: com.fasterxml.jackson.databind.deser.std.EnumDeserializer$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction;

        static {
            int[] iArr = new int[CoercionAction.values().length];
            $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction = iArr;
            try {
                iArr[CoercionAction.AsNull.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.AsEmpty.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.TryConvert.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private final Object _deserializeAltString(JsonParser jsonParser, DeserializationContext deserializationContext, CompactStringObjectMap compactStringObjectMap, String str) throws IOException {
        char charAt;
        Object findCaseInsensitive;
        CoercionAction _checkCoercionFail;
        String trim = str.trim();
        if (trim.isEmpty()) {
            if (useDefaultValueForUnknownEnum(deserializationContext)) {
                return this._enumDefaultValue;
            }
            if (useNullForUnknownEnum(deserializationContext)) {
                return null;
            }
            if (str.isEmpty()) {
                _checkCoercionFail = _checkCoercionFail(deserializationContext, _findCoercionFromEmptyString(deserializationContext), handledType(), str, "empty String (\"\")");
            } else {
                _checkCoercionFail = _checkCoercionFail(deserializationContext, _findCoercionFromBlankString(deserializationContext), handledType(), str, "blank String (all whitespace)");
            }
            int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[_checkCoercionFail.ordinal()];
            if (i == 2 || i == 3) {
                return getEmptyValue(deserializationContext);
            }
            return null;
        } else if (!Boolean.TRUE.equals(this._caseInsensitive) || (findCaseInsensitive = compactStringObjectMap.findCaseInsensitive(trim)) == null) {
            if (!deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS) && !this._isFromIntValue && (charAt = trim.charAt(0)) >= '0' && charAt <= '9') {
                try {
                    int parseInt = Integer.parseInt(trim);
                    if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
                        return deserializationContext.handleWeirdStringValue(_enumClass(), trim, "value looks like quoted Enum index, but `MapperFeature.ALLOW_COERCION_OF_SCALARS` prevents use", new Object[0]);
                    }
                    if (parseInt >= 0) {
                        Object[] objArr = this._enumsByIndex;
                        if (parseInt < objArr.length) {
                            return objArr[parseInt];
                        }
                    }
                } catch (NumberFormatException unused) {
                }
            }
            if (useDefaultValueForUnknownEnum(deserializationContext)) {
                return this._enumDefaultValue;
            }
            if (useNullForUnknownEnum(deserializationContext)) {
                return null;
            }
            return deserializationContext.handleWeirdStringValue(_enumClass(), trim, "not one of the values accepted for Enum class: %s", compactStringObjectMap.keys());
        } else {
            return findCaseInsensitive;
        }
    }

    protected Object _deserializeOther(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.START_ARRAY)) {
            return _deserializeFromArray(jsonParser, deserializationContext);
        }
        return deserializationContext.handleUnexpectedToken(_enumClass(), jsonParser);
    }

    protected Class<?> _enumClass() {
        return handledType();
    }

    protected CompactStringObjectMap _getToStringLookup(DeserializationContext deserializationContext) {
        CompactStringObjectMap compactStringObjectMap = this._lookupByToString;
        if (compactStringObjectMap == null) {
            synchronized (this) {
                compactStringObjectMap = this._lookupByToString;
                if (compactStringObjectMap == null) {
                    CompactStringObjectMap constructLookup = EnumResolver.constructUsingToString(deserializationContext.getConfig(), _enumClass()).constructLookup();
                    this._lookupByToString = constructLookup;
                    compactStringObjectMap = constructLookup;
                }
            }
        }
        return compactStringObjectMap;
    }

    protected boolean useNullForUnknownEnum(DeserializationContext deserializationContext) {
        return Boolean.TRUE.equals(this._useNullForUnknownEnum) || deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
    }

    protected boolean useDefaultValueForUnknownEnum(DeserializationContext deserializationContext) {
        return this._enumDefaultValue != null && (Boolean.TRUE.equals(this._useDefaultValueForUnknownEnum) || deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE));
    }
}
