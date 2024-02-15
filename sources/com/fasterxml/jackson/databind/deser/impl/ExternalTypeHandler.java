package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ExternalTypeHandler {
    private final JavaType _beanType;
    private final Map<String, Object> _nameToPropertyIndex;
    private final ExtTypedProperty[] _properties;
    private final TokenBuffer[] _tokens;
    private final String[] _typeIds;

    protected ExternalTypeHandler(JavaType javaType, ExtTypedProperty[] extTypedPropertyArr, Map<String, Object> map, String[] strArr, TokenBuffer[] tokenBufferArr) {
        this._beanType = javaType;
        this._properties = extTypedPropertyArr;
        this._nameToPropertyIndex = map;
        this._typeIds = strArr;
        this._tokens = tokenBufferArr;
    }

    protected ExternalTypeHandler(ExternalTypeHandler externalTypeHandler) {
        this._beanType = externalTypeHandler._beanType;
        ExtTypedProperty[] extTypedPropertyArr = externalTypeHandler._properties;
        this._properties = extTypedPropertyArr;
        this._nameToPropertyIndex = externalTypeHandler._nameToPropertyIndex;
        int length = extTypedPropertyArr.length;
        this._typeIds = new String[length];
        this._tokens = new TokenBuffer[length];
    }

    public static Builder builder(JavaType javaType) {
        return new Builder(javaType);
    }

    public ExternalTypeHandler start() {
        return new ExternalTypeHandler(this);
    }

    public boolean handleTypePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) throws IOException {
        Object obj2 = this._nameToPropertyIndex.get(str);
        boolean z = false;
        if (obj2 == null) {
            return false;
        }
        String text = jsonParser.getText();
        if (obj2 instanceof List) {
            for (Integer num : (List) obj2) {
                if (_handleTypePropertyValue(jsonParser, deserializationContext, str, obj, text, num.intValue())) {
                    z = true;
                }
            }
            return z;
        }
        return _handleTypePropertyValue(jsonParser, deserializationContext, str, obj, text, ((Integer) obj2).intValue());
    }

    private final boolean _handleTypePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj, String str2, int i) throws IOException {
        boolean z = false;
        if (this._properties[i].hasTypePropertyName(str)) {
            if (obj != null && this._tokens[i] != null) {
                z = true;
            }
            if (z) {
                _deserializeAndSet(jsonParser, deserializationContext, obj, i, str2);
                this._tokens[i] = null;
            } else {
                this._typeIds[i] = str2;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0090, code lost:
        if (r10._tokens[r0] != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0092, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a2, code lost:
        if (r10._typeIds[r0] != null) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean handlePropertyValue(com.fasterxml.jackson.core.JsonParser r11, com.fasterxml.jackson.databind.DeserializationContext r12, java.lang.String r13, java.lang.Object r14) throws java.io.IOException {
        /*
            r10 = this;
            java.util.Map<java.lang.String, java.lang.Object> r0 = r10._nameToPropertyIndex
            java.lang.Object r0 = r0.get(r13)
            r1 = 0
            if (r0 != 0) goto La
            return r1
        La:
            boolean r2 = r0 instanceof java.util.List
            r3 = 1
            if (r2 == 0) goto L6f
            java.util.List r0 = (java.util.List) r0
            java.util.Iterator r14 = r0.iterator()
            java.lang.Object r0 = r14.next()
            java.lang.Integer r0 = (java.lang.Integer) r0
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r1 = r10._properties
            int r2 = r0.intValue()
            r1 = r1[r2]
            boolean r13 = r1.hasTypePropertyName(r13)
            if (r13 == 0) goto L4d
            java.lang.String r12 = r11.getText()
            r11.skipChildren()
            java.lang.String[] r11 = r10._typeIds
            int r13 = r0.intValue()
            r11[r13] = r12
        L38:
            boolean r11 = r14.hasNext()
            if (r11 == 0) goto L6e
            java.lang.String[] r11 = r10._typeIds
            java.lang.Object r13 = r14.next()
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r11[r13] = r12
            goto L38
        L4d:
            com.fasterxml.jackson.databind.util.TokenBuffer r11 = r12.bufferAsCopyOfValue(r11)
            com.fasterxml.jackson.databind.util.TokenBuffer[] r12 = r10._tokens
            int r13 = r0.intValue()
            r12[r13] = r11
        L59:
            boolean r12 = r14.hasNext()
            if (r12 == 0) goto L6e
            com.fasterxml.jackson.databind.util.TokenBuffer[] r12 = r10._tokens
            java.lang.Object r13 = r14.next()
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r12[r13] = r11
            goto L59
        L6e:
            return r3
        L6f:
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r2 = r10._properties
            r2 = r2[r0]
            boolean r13 = r2.hasTypePropertyName(r13)
            if (r13 == 0) goto L94
            java.lang.String[] r13 = r10._typeIds
            java.lang.String r2 = r11.getValueAsString()
            r13[r0] = r2
            r11.skipChildren()
            if (r14 == 0) goto La5
            com.fasterxml.jackson.databind.util.TokenBuffer[] r13 = r10._tokens
            r13 = r13[r0]
            if (r13 == 0) goto La5
        L92:
            r1 = r3
            goto La5
        L94:
            com.fasterxml.jackson.databind.util.TokenBuffer r13 = r12.bufferAsCopyOfValue(r11)
            com.fasterxml.jackson.databind.util.TokenBuffer[] r2 = r10._tokens
            r2[r0] = r13
            if (r14 == 0) goto La5
            java.lang.String[] r13 = r10._typeIds
            r13 = r13[r0]
            if (r13 == 0) goto La5
            goto L92
        La5:
            if (r1 == 0) goto Lba
            java.lang.String[] r13 = r10._typeIds
            r9 = r13[r0]
            r1 = 0
            r13[r0] = r1
            r4 = r10
            r5 = r11
            r6 = r12
            r7 = r14
            r8 = r0
            r4._deserializeAndSet(r5, r6, r7, r8, r9)
            com.fasterxml.jackson.databind.util.TokenBuffer[] r11 = r10._tokens
            r11[r0] = r1
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.handlePropertyValue(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, java.lang.String, java.lang.Object):boolean");
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        int length = this._properties.length;
        for (int i = 0; i < length; i++) {
            String str = this._typeIds[i];
            ExtTypedProperty extTypedProperty = this._properties[i];
            if (str == null) {
                TokenBuffer tokenBuffer = this._tokens[i];
                if (tokenBuffer != null) {
                    if (tokenBuffer.firstToken().isScalarValue()) {
                        JsonParser asParser = tokenBuffer.asParser(jsonParser);
                        asParser.nextToken();
                        SettableBeanProperty property = extTypedProperty.getProperty();
                        Object deserializeIfNatural = TypeDeserializer.deserializeIfNatural(asParser, deserializationContext, property.getType());
                        if (deserializeIfNatural != null) {
                            property.set(obj, deserializeIfNatural);
                        }
                    }
                    if (!extTypedProperty.hasDefaultType()) {
                        deserializationContext.reportPropertyInputMismatch(this._beanType, extTypedProperty.getProperty().getName(), "Missing external type id property '%s' (and no 'defaultImpl' specified)", extTypedProperty.getTypePropertyName());
                    } else {
                        str = extTypedProperty.getDefaultTypeId();
                        if (str == null) {
                            deserializationContext.reportPropertyInputMismatch(this._beanType, extTypedProperty.getProperty().getName(), "Invalid default type id for property '%s': `null` returned by TypeIdResolver", extTypedProperty.getTypePropertyName());
                        }
                    }
                }
            } else if (this._tokens[i] == null) {
                SettableBeanProperty property2 = extTypedProperty.getProperty();
                if (property2.isRequired() || deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
                    deserializationContext.reportPropertyInputMismatch(obj.getClass(), property2.getName(), "Missing property '%s' for external type id '%s'", property2.getName(), extTypedProperty.getTypePropertyName());
                }
                return obj;
            }
            _deserializeAndSet(jsonParser, deserializationContext, obj, i, str);
        }
        return obj;
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, PropertyValueBuffer propertyValueBuffer, PropertyBasedCreator propertyBasedCreator) throws IOException {
        int length = this._properties.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            String str = this._typeIds[i];
            ExtTypedProperty extTypedProperty = this._properties[i];
            String str2 = str;
            if (str == null) {
                TokenBuffer tokenBuffer = this._tokens[i];
                if (tokenBuffer != null && tokenBuffer.firstToken() != JsonToken.VALUE_NULL) {
                    if (!extTypedProperty.hasDefaultType()) {
                        deserializationContext.reportPropertyInputMismatch(this._beanType, extTypedProperty.getProperty().getName(), "Missing external type id property '%s'", extTypedProperty.getTypePropertyName());
                        str2 = str;
                    } else {
                        str2 = extTypedProperty.getDefaultTypeId();
                    }
                }
            }
            if (this._tokens[i] != null) {
                objArr[i] = _deserialize(jsonParser, deserializationContext, i, str2);
            } else {
                if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
                    SettableBeanProperty property = extTypedProperty.getProperty();
                    deserializationContext.reportPropertyInputMismatch(this._beanType, property.getName(), "Missing property '%s' for external type id '%s'", property.getName(), this._properties[i].getTypePropertyName());
                }
                objArr[i] = _deserializeMissingToken(jsonParser, deserializationContext, i, str2);
            }
            SettableBeanProperty property2 = extTypedProperty.getProperty();
            if (property2.getCreatorIndex() >= 0) {
                propertyValueBuffer.assignParameter(property2, objArr[i]);
                SettableBeanProperty typeProperty = extTypedProperty.getTypeProperty();
                if (typeProperty != null && typeProperty.getCreatorIndex() >= 0) {
                    Object obj = str2;
                    if (!typeProperty.getType().hasRawClass(String.class)) {
                        TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
                        bufferForInputBuffering.writeString(str2);
                        Object deserialize = typeProperty.getValueDeserializer().deserialize(bufferForInputBuffering.asParserOnFirstToken(), deserializationContext);
                        bufferForInputBuffering.close();
                        obj = deserialize;
                    }
                    propertyValueBuffer.assignParameter(typeProperty, obj);
                }
            }
        }
        Object build = propertyBasedCreator.build(deserializationContext, propertyValueBuffer);
        for (int i2 = 0; i2 < length; i2++) {
            SettableBeanProperty property3 = this._properties[i2].getProperty();
            if (property3.getCreatorIndex() < 0) {
                property3.set(build, objArr[i2]);
            }
        }
        return build;
    }

    protected final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, int i, String str) throws IOException {
        JsonParser asParser = this._tokens[i].asParser(jsonParser);
        if (asParser.nextToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
        bufferForInputBuffering.writeStartArray();
        bufferForInputBuffering.writeString(str);
        bufferForInputBuffering.copyCurrentStructure(asParser);
        bufferForInputBuffering.writeEndArray();
        JsonParser asParser2 = bufferForInputBuffering.asParser(jsonParser);
        asParser2.nextToken();
        return this._properties[i].getProperty().deserialize(asParser2, deserializationContext);
    }

    protected final Object _deserializeMissingToken(JsonParser jsonParser, DeserializationContext deserializationContext, int i, String str) throws IOException {
        TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
        bufferForInputBuffering.writeStartArray();
        bufferForInputBuffering.writeString(str);
        bufferForInputBuffering.writeEndArray();
        JsonParser asParser = bufferForInputBuffering.asParser(jsonParser);
        asParser.nextToken();
        return this._properties[i].getProperty().deserialize(asParser, deserializationContext);
    }

    protected final void _deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, int i, String str) throws IOException {
        if (str == null) {
            deserializationContext.reportInputMismatch(this._beanType, "Internal error in external Type Id handling: `null` type id passed", new Object[0]);
        }
        JsonParser asParser = this._tokens[i].asParser(jsonParser);
        if (asParser.nextToken() == JsonToken.VALUE_NULL) {
            this._properties[i].getProperty().set(obj, null);
            return;
        }
        TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
        bufferForInputBuffering.writeStartArray();
        bufferForInputBuffering.writeString(str);
        bufferForInputBuffering.copyCurrentStructure(asParser);
        bufferForInputBuffering.writeEndArray();
        JsonParser asParser2 = bufferForInputBuffering.asParser(jsonParser);
        asParser2.nextToken();
        this._properties[i].getProperty().deserializeAndSet(asParser2, deserializationContext, obj);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private final JavaType _beanType;
        private final List<ExtTypedProperty> _properties = new ArrayList();
        private final Map<String, Object> _nameToPropertyIndex = new HashMap();

        protected Builder(JavaType javaType) {
            this._beanType = javaType;
        }

        public void addExternal(SettableBeanProperty settableBeanProperty, TypeDeserializer typeDeserializer) {
            Integer valueOf = Integer.valueOf(this._properties.size());
            this._properties.add(new ExtTypedProperty(settableBeanProperty, typeDeserializer));
            _addPropertyIndex(settableBeanProperty.getName(), valueOf);
            _addPropertyIndex(typeDeserializer.getPropertyName(), valueOf);
        }

        private void _addPropertyIndex(String str, Integer num) {
            Object obj = this._nameToPropertyIndex.get(str);
            if (obj == null) {
                this._nameToPropertyIndex.put(str, num);
            } else if (obj instanceof List) {
                ((List) obj).add(num);
            } else {
                LinkedList linkedList = new LinkedList();
                linkedList.add(obj);
                linkedList.add(num);
                this._nameToPropertyIndex.put(str, linkedList);
            }
        }

        public ExternalTypeHandler build(BeanPropertyMap beanPropertyMap) {
            int size = this._properties.size();
            ExtTypedProperty[] extTypedPropertyArr = new ExtTypedProperty[size];
            for (int i = 0; i < size; i++) {
                ExtTypedProperty extTypedProperty = this._properties.get(i);
                SettableBeanProperty find = beanPropertyMap.find(extTypedProperty.getTypePropertyName());
                if (find != null) {
                    extTypedProperty.linkTypeProperty(find);
                }
                extTypedPropertyArr[i] = extTypedProperty;
            }
            return new ExternalTypeHandler(this._beanType, extTypedPropertyArr, this._nameToPropertyIndex, null, null);
        }
    }

    /* loaded from: classes.dex */
    public static final class ExtTypedProperty {
        private final SettableBeanProperty _property;
        private final TypeDeserializer _typeDeserializer;
        private SettableBeanProperty _typeProperty;
        private final String _typePropertyName;

        public ExtTypedProperty(SettableBeanProperty settableBeanProperty, TypeDeserializer typeDeserializer) {
            this._property = settableBeanProperty;
            this._typeDeserializer = typeDeserializer;
            this._typePropertyName = typeDeserializer.getPropertyName();
        }

        public void linkTypeProperty(SettableBeanProperty settableBeanProperty) {
            this._typeProperty = settableBeanProperty;
        }

        public boolean hasTypePropertyName(String str) {
            return str.equals(this._typePropertyName);
        }

        public boolean hasDefaultType() {
            return this._typeDeserializer.hasDefaultImpl();
        }

        public String getDefaultTypeId() {
            Class<?> defaultImpl = this._typeDeserializer.getDefaultImpl();
            if (defaultImpl == null) {
                return null;
            }
            return this._typeDeserializer.getTypeIdResolver().idFromValueAndType(null, defaultImpl);
        }

        public String getTypePropertyName() {
            return this._typePropertyName;
        }

        public SettableBeanProperty getProperty() {
            return this._property;
        }

        public SettableBeanProperty getTypeProperty() {
            return this._typeProperty;
        }
    }
}