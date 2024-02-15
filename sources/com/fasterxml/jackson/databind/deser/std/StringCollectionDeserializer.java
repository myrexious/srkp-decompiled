package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

@JacksonStdImpl
/* loaded from: classes.dex */
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JsonDeserializer<String> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;

    public StringCollectionDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, ValueInstantiator valueInstantiator) {
        this(javaType, valueInstantiator, null, jsonDeserializer, jsonDeserializer, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected StringCollectionDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, NullValueProvider nullValueProvider, Boolean bool) {
        super(javaType, nullValueProvider, bool);
        this._valueDeserializer = jsonDeserializer2;
        this._valueInstantiator = valueInstantiator;
        this._delegateDeserializer = jsonDeserializer;
    }

    protected StringCollectionDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, NullValueProvider nullValueProvider, Boolean bool) {
        return (Objects.equals(this._unwrapSingle, bool) && this._nullProvider == nullValueProvider && this._valueDeserializer == jsonDeserializer2 && this._delegateDeserializer == jsonDeserializer) ? this : new StringCollectionDeserializer(this._containerType, this._valueInstantiator, jsonDeserializer, jsonDeserializer2, nullValueProvider, bool);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return this._valueDeserializer == null && this._delegateDeserializer == null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Collection;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x005e  */
    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.fasterxml.jackson.databind.JsonDeserializer<?> createContextual(com.fasterxml.jackson.databind.DeserializationContext r6, com.fasterxml.jackson.databind.BeanProperty r7) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r5 = this;
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r5._valueInstantiator
            r1 = 0
            if (r0 == 0) goto L31
            com.fasterxml.jackson.databind.introspect.AnnotatedWithParams r0 = r0.getArrayDelegateCreator()
            if (r0 == 0) goto L1a
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r5._valueInstantiator
            com.fasterxml.jackson.databind.DeserializationConfig r2 = r6.getConfig()
            com.fasterxml.jackson.databind.JavaType r0 = r0.getArrayDelegateType(r2)
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r5.findDeserializer(r6, r0, r7)
            goto L32
        L1a:
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r5._valueInstantiator
            com.fasterxml.jackson.databind.introspect.AnnotatedWithParams r0 = r0.getDelegateCreator()
            if (r0 == 0) goto L31
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r5._valueInstantiator
            com.fasterxml.jackson.databind.DeserializationConfig r2 = r6.getConfig()
            com.fasterxml.jackson.databind.JavaType r0 = r0.getDelegateType(r2)
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r5.findDeserializer(r6, r0, r7)
            goto L32
        L31:
            r0 = r1
        L32:
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r2 = r5._valueDeserializer
            com.fasterxml.jackson.databind.JavaType r3 = r5._containerType
            com.fasterxml.jackson.databind.JavaType r3 = r3.getContentType()
            if (r2 != 0) goto L47
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r5.findConvertingContentDeserializer(r6, r7, r2)
            if (r2 != 0) goto L4b
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r6.findContextualValueDeserializer(r3, r7)
            goto L4b
        L47:
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r6.handleSecondaryContextualization(r2, r7, r3)
        L4b:
            java.lang.Class<java.util.Collection> r3 = java.util.Collection.class
            com.fasterxml.jackson.annotation.JsonFormat$Feature r4 = com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY
            java.lang.Boolean r3 = r5.findFormatFeature(r6, r7, r3, r4)
            com.fasterxml.jackson.databind.deser.NullValueProvider r6 = r5.findContentNullProvider(r6, r7, r2)
            boolean r7 = r5.isDefaultDeserializer(r2)
            if (r7 == 0) goto L5e
            goto L5f
        L5e:
            r1 = r2
        L5f:
            com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer r6 = r5.withResolved(r0, r1, r6, r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer.createContextual(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable
    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        return deserialize(jsonParser, deserializationContext, (Collection) this._valueInstantiator.createUsingDefault(deserializationContext));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException {
        String _parseString;
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext, collection);
        }
        JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
        if (jsonDeserializer != null) {
            return deserializeUsingCustom(jsonParser, deserializationContext, collection, jsonDeserializer);
        }
        while (true) {
            try {
                String nextTextValue = jsonParser.nextTextValue();
                if (nextTextValue != null) {
                    collection.add(nextTextValue);
                } else {
                    JsonToken currentToken = jsonParser.currentToken();
                    if (currentToken == JsonToken.END_ARRAY) {
                        return collection;
                    }
                    if (currentToken == JsonToken.VALUE_NULL) {
                        if (!this._skipNullValues) {
                            _parseString = (String) this._nullProvider.getNullValue(deserializationContext);
                        }
                    } else {
                        _parseString = _parseString(jsonParser, deserializationContext, this._nullProvider);
                    }
                    collection.add(_parseString);
                }
            } catch (Exception e) {
                throw JsonMappingException.wrapWithPath(e, collection, collection.size());
            }
        }
    }

    private Collection<String> deserializeUsingCustom(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection, JsonDeserializer<String> jsonDeserializer) throws IOException {
        String deserialize;
        while (true) {
            try {
                if (jsonParser.nextTextValue() == null) {
                    JsonToken currentToken = jsonParser.currentToken();
                    if (currentToken == JsonToken.END_ARRAY) {
                        return collection;
                    }
                    if (currentToken == JsonToken.VALUE_NULL) {
                        if (!this._skipNullValues) {
                            deserialize = (String) this._nullProvider.getNullValue(deserializationContext);
                        }
                    } else {
                        deserialize = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    }
                } else {
                    deserialize = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                }
                collection.add(deserialize);
            } catch (Exception e) {
                throw JsonMappingException.wrapWithPath(e, collection, collection.size());
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    private final Collection<String> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException {
        String _parseString;
        CoercionAction findCoercionFromBlankString;
        if (!(this._unwrapSingle == Boolean.TRUE || (this._unwrapSingle == null && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)))) {
            if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
                return _deserializeFromString(jsonParser, deserializationContext);
            }
            return (Collection) deserializationContext.handleUnexpectedToken(this._containerType, jsonParser);
        }
        JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
        if (jsonParser.currentToken() == JsonToken.VALUE_NULL) {
            if (this._skipNullValues) {
                return collection;
            }
            _parseString = (String) this._nullProvider.getNullValue(deserializationContext);
        } else {
            if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
                String text = jsonParser.getText();
                if (text.isEmpty()) {
                    CoercionAction findCoercionAction = deserializationContext.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyString);
                    if (findCoercionAction != CoercionAction.Fail) {
                        return (Collection) _deserializeFromEmptyString(jsonParser, deserializationContext, findCoercionAction, handledType(), "empty String (\"\")");
                    }
                } else if (_isBlank(text) && (findCoercionFromBlankString = deserializationContext.findCoercionFromBlankString(logicalType(), handledType(), CoercionAction.Fail)) != CoercionAction.Fail) {
                    return (Collection) _deserializeFromEmptyString(jsonParser, deserializationContext, findCoercionFromBlankString, handledType(), "blank String (all whitespace)");
                }
            }
            try {
                _parseString = jsonDeserializer == null ? _parseString(jsonParser, deserializationContext, this._nullProvider) : jsonDeserializer.deserialize(jsonParser, deserializationContext);
            } catch (Exception e) {
                throw JsonMappingException.wrapWithPath(e, collection, collection.size());
            }
        }
        collection.add(_parseString);
        return collection;
    }
}
