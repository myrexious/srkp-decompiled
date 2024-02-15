package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.impl.JDKValueInstantiators;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class SettableAnyProperty implements Serializable {
    private static final long serialVersionUID = 1;
    protected final KeyDeserializer _keyDeserializer;
    protected final BeanProperty _property;
    protected final AnnotatedMember _setter;
    protected final boolean _setterIsField;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    protected abstract void _set(Object obj, Object obj2, Object obj3) throws Exception;

    public abstract SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer);

    public SettableAnyProperty(BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        this._property = beanProperty;
        this._setter = annotatedMember;
        this._type = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._keyDeserializer = keyDeserializer;
        this._setterIsField = annotatedMember instanceof AnnotatedField;
    }

    public static SettableAnyProperty constructForMethod(DeserializationContext deserializationContext, BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        return new MethodAnyProperty(beanProperty, annotatedMember, javaType, keyDeserializer, jsonDeserializer, typeDeserializer);
    }

    public static SettableAnyProperty constructForMapField(DeserializationContext deserializationContext, BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        Class<?> rawType = annotatedMember.getRawType();
        if (rawType == Map.class) {
            rawType = LinkedHashMap.class;
        }
        return new MapFieldAnyProperty(beanProperty, annotatedMember, javaType, keyDeserializer, jsonDeserializer, typeDeserializer, JDKValueInstantiators.findStdValueInstantiator(deserializationContext.getConfig(), rawType));
    }

    public static SettableAnyProperty constructForJsonNodeField(DeserializationContext deserializationContext, BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        return new JsonNodeFieldAnyProperty(beanProperty, annotatedMember, javaType, jsonDeserializer, deserializationContext.getNodeFactory());
    }

    public void fixAccess(DeserializationConfig deserializationConfig) {
        this._setter.fixAccess(deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }

    Object readResolve() {
        AnnotatedMember annotatedMember = this._setter;
        if (annotatedMember == null || annotatedMember.getAnnotated() == null) {
            throw new IllegalArgumentException("Missing method/field (broken JDK (de)serialization?)");
        }
        return this;
    }

    public BeanProperty getProperty() {
        return this._property;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public JavaType getType() {
        return this._type;
    }

    public String getPropertyName() {
        return this._property.getName();
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        try {
            KeyDeserializer keyDeserializer = this._keyDeserializer;
            set(obj, keyDeserializer == null ? str : keyDeserializer.deserializeKey(str, deserializationContext), deserialize(jsonParser, deserializationContext));
        } catch (UnresolvedForwardReference e) {
            if (this._valueDeserializer.getObjectIdReader() == null) {
                throw JsonMappingException.from(jsonParser, "Unresolved forward reference but no identity info.", e);
            }
            e.getRoid().appendReferring(new AnySetterReferring(this, e, this._type.getRawClass(), obj, str));
        }
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return this._valueDeserializer.getNullValue(deserializationContext);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            return this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        return this._valueDeserializer.deserialize(jsonParser, deserializationContext);
    }

    public void set(Object obj, Object obj2, Object obj3) throws IOException {
        try {
            _set(obj, obj2, obj3);
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            _throwAsIOE(e2, obj2, obj3);
        }
    }

    protected void _throwAsIOE(Exception exc, Object obj, Object obj2) throws IOException {
        if (exc instanceof IllegalArgumentException) {
            String classNameOf = ClassUtil.classNameOf(obj2);
            StringBuilder append = new StringBuilder("Problem deserializing \"any-property\" '").append(obj);
            append.append("' of class " + getClassName() + " (expected type: ").append(this._type);
            append.append("; actual type: ").append(classNameOf).append(")");
            String exceptionMessage = ClassUtil.exceptionMessage(exc);
            if (exceptionMessage != null) {
                append.append(", problem: ").append(exceptionMessage);
            } else {
                append.append(" (no error message provided)");
            }
            throw new JsonMappingException((Closeable) null, append.toString(), exc);
        }
        ClassUtil.throwIfIOE(exc);
        ClassUtil.throwIfRTE(exc);
        Throwable rootCause = ClassUtil.getRootCause(exc);
        throw new JsonMappingException((Closeable) null, ClassUtil.exceptionMessage(rootCause), rootCause);
    }

    private String getClassName() {
        return ClassUtil.nameOf(this._setter.getDeclaringClass());
    }

    public String toString() {
        return "[any property on class " + getClassName() + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class AnySetterReferring extends ReadableObjectId.Referring {
        private final SettableAnyProperty _parent;
        private final Object _pojo;
        private final String _propName;

        public AnySetterReferring(SettableAnyProperty settableAnyProperty, UnresolvedForwardReference unresolvedForwardReference, Class<?> cls, Object obj, String str) {
            super(unresolvedForwardReference, cls);
            this._parent = settableAnyProperty;
            this._pojo = obj;
            this._propName = str;
        }

        @Override // com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring
        public void handleResolvedForwardReference(Object obj, Object obj2) throws IOException {
            if (!hasId(obj)) {
                throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj.toString() + "] that wasn't previously registered.");
            }
            this._parent.set(this._pojo, this._propName, obj2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class MethodAnyProperty extends SettableAnyProperty implements Serializable {
        private static final long serialVersionUID = 1;

        public MethodAnyProperty(BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
            super(beanProperty, annotatedMember, javaType, keyDeserializer, jsonDeserializer, typeDeserializer);
        }

        @Override // com.fasterxml.jackson.databind.deser.SettableAnyProperty
        protected void _set(Object obj, Object obj2, Object obj3) throws Exception {
            ((AnnotatedMethod) this._setter).callOnWith(obj, obj2, obj3);
        }

        @Override // com.fasterxml.jackson.databind.deser.SettableAnyProperty
        public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new MethodAnyProperty(this._property, this._setter, this._type, this._keyDeserializer, jsonDeserializer, this._valueTypeDeserializer);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class MapFieldAnyProperty extends SettableAnyProperty implements Serializable {
        private static final long serialVersionUID = 1;
        protected final ValueInstantiator _valueInstantiator;

        public MapFieldAnyProperty(BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator) {
            super(beanProperty, annotatedMember, javaType, keyDeserializer, jsonDeserializer, typeDeserializer);
            this._valueInstantiator = valueInstantiator;
        }

        @Override // com.fasterxml.jackson.databind.deser.SettableAnyProperty
        public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new MapFieldAnyProperty(this._property, this._setter, this._type, this._keyDeserializer, jsonDeserializer, this._valueTypeDeserializer, this._valueInstantiator);
        }

        @Override // com.fasterxml.jackson.databind.deser.SettableAnyProperty
        protected void _set(Object obj, Object obj2, Object obj3) throws Exception {
            AnnotatedField annotatedField = (AnnotatedField) this._setter;
            Map<Object, Object> map = (Map) annotatedField.getValue(obj);
            if (map == null) {
                map = _createAndSetMap(null, annotatedField, obj, obj2);
            }
            map.put(obj2, obj3);
        }

        protected Map<Object, Object> _createAndSetMap(DeserializationContext deserializationContext, AnnotatedField annotatedField, Object obj, Object obj2) throws IOException {
            ValueInstantiator valueInstantiator = this._valueInstantiator;
            if (valueInstantiator == null) {
                throw JsonMappingException.from(deserializationContext, String.format("Cannot create an instance of %s for use as \"any-setter\" '%s'", ClassUtil.nameOf(this._type.getRawClass()), this._property.getName()));
            }
            Map<Object, Object> map = (Map) valueInstantiator.createUsingDefault(deserializationContext);
            annotatedField.setValue(obj, map);
            return map;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class JsonNodeFieldAnyProperty extends SettableAnyProperty implements Serializable {
        private static final long serialVersionUID = 1;
        protected final JsonNodeFactory _nodeFactory;

        @Override // com.fasterxml.jackson.databind.deser.SettableAnyProperty
        public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return this;
        }

        public JsonNodeFieldAnyProperty(BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, JsonDeserializer<Object> jsonDeserializer, JsonNodeFactory jsonNodeFactory) {
            super(beanProperty, annotatedMember, javaType, null, jsonDeserializer, null);
            this._nodeFactory = jsonNodeFactory;
        }

        @Override // com.fasterxml.jackson.databind.deser.SettableAnyProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
            setProperty(obj, str, (JsonNode) deserialize(jsonParser, deserializationContext));
        }

        @Override // com.fasterxml.jackson.databind.deser.SettableAnyProperty
        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return this._valueDeserializer.deserialize(jsonParser, deserializationContext);
        }

        @Override // com.fasterxml.jackson.databind.deser.SettableAnyProperty
        protected void _set(Object obj, Object obj2, Object obj3) throws Exception {
            setProperty(obj, (String) obj2, (JsonNode) obj3);
        }

        protected void setProperty(Object obj, String str, JsonNode jsonNode) throws IOException {
            ObjectNode objectNode;
            AnnotatedField annotatedField = (AnnotatedField) this._setter;
            Object value = annotatedField.getValue(obj);
            if (value == null) {
                objectNode = this._nodeFactory.objectNode();
                annotatedField.setValue(obj, objectNode);
            } else if (!(value instanceof ObjectNode)) {
                throw JsonMappingException.from((DeserializationContext) null, String.format("Value \"any-setter\" '%s' not `ObjectNode` but %s", getPropertyName(), ClassUtil.nameOf(value.getClass())));
            } else {
                objectNode = (ObjectNode) value;
            }
            objectNode.set(str, jsonNode);
        }
    }
}
