package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JacksonStdImpl
/* loaded from: classes.dex */
public final class UntypedObjectDeserializerNR extends StdDeserializer<Object> {
    private static final long serialVersionUID = 1;
    protected final boolean _nonMerging;
    protected static final Object[] NO_OBJECTS = new Object[0];
    public static final UntypedObjectDeserializerNR std = new UntypedObjectDeserializerNR();

    public UntypedObjectDeserializerNR() {
        this(false);
    }

    protected UntypedObjectDeserializerNR(boolean z) {
        super(Object.class);
        this._nonMerging = z;
    }

    public static UntypedObjectDeserializerNR instance(boolean z) {
        if (z) {
            return new UntypedObjectDeserializerNR(true);
        }
        return std;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        if (this._nonMerging) {
            return Boolean.FALSE;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        switch (jsonParser.currentTokenId()) {
            case 1:
                return _deserializeNR(jsonParser, deserializationContext, Scope.rootObjectScope(deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES)));
            case 2:
                return Scope.emptyMap();
            case 3:
                return _deserializeNR(jsonParser, deserializationContext, Scope.rootArrayScope());
            case 4:
            default:
                return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
            case 5:
                return _deserializeObjectAtName(jsonParser, deserializationContext);
            case 6:
                return jsonParser.getText();
            case 7:
                if (deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                    return _coerceIntegral(jsonParser, deserializationContext);
                }
                return jsonParser.getNumberValue();
            case 8:
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return jsonParser.getNumberValue();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return jsonParser.getEmbeddedObject();
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1 || currentTokenId == 3 || currentTokenId == 5) {
            return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
        }
        return _deserializeAnyScalar(jsonParser, deserializationContext, jsonParser.currentTokenId());
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x001a, code lost:
        if (r0 != 5) goto L29;
     */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object deserialize(com.fasterxml.jackson.core.JsonParser r5, com.fasterxml.jackson.databind.DeserializationContext r6, java.lang.Object r7) throws java.io.IOException {
        /*
            r4 = this;
            boolean r0 = r4._nonMerging
            if (r0 == 0) goto L9
            java.lang.Object r5 = r4.deserialize(r5, r6)
            return r5
        L9:
            int r0 = r5.currentTokenId()
            r1 = 1
            if (r0 == r1) goto L3d
            r1 = 2
            if (r0 == r1) goto L3c
            r1 = 3
            if (r0 == r1) goto L1d
            r1 = 4
            if (r0 == r1) goto L3c
            r1 = 5
            if (r0 == r1) goto L46
            goto L6f
        L1d:
            com.fasterxml.jackson.core.JsonToken r0 = r5.nextToken()
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.END_ARRAY
            if (r0 != r1) goto L26
            return r7
        L26:
            boolean r0 = r7 instanceof java.util.Collection
            if (r0 == 0) goto L6f
            r0 = r7
            java.util.Collection r0 = (java.util.Collection) r0
        L2d:
            java.lang.Object r1 = r4.deserialize(r5, r6)
            r0.add(r1)
            com.fasterxml.jackson.core.JsonToken r1 = r5.nextToken()
            com.fasterxml.jackson.core.JsonToken r2 = com.fasterxml.jackson.core.JsonToken.END_ARRAY
            if (r1 != r2) goto L2d
        L3c:
            return r7
        L3d:
            com.fasterxml.jackson.core.JsonToken r0 = r5.nextToken()
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.END_OBJECT
            if (r0 != r1) goto L46
            return r7
        L46:
            boolean r0 = r7 instanceof java.util.Map
            if (r0 == 0) goto L6f
            r0 = r7
            java.util.Map r0 = (java.util.Map) r0
            java.lang.String r1 = r5.currentName()
        L51:
            r5.nextToken()
            java.lang.Object r2 = r0.get(r1)
            if (r2 == 0) goto L5f
            java.lang.Object r3 = r4.deserialize(r5, r6, r2)
            goto L63
        L5f:
            java.lang.Object r3 = r4.deserialize(r5, r6)
        L63:
            if (r3 == r2) goto L68
            r0.put(r1, r3)
        L68:
            java.lang.String r1 = r5.nextFieldName()
            if (r1 != 0) goto L51
            return r7
        L6f:
            java.lang.Object r5 = r4.deserialize(r5, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR.deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, java.lang.Object):java.lang.Object");
    }

    private Object _deserializeObjectAtName(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object _deserializeNR;
        Scope rootObjectScope = Scope.rootObjectScope(deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES));
        String currentName = jsonParser.currentName();
        while (currentName != null) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == null) {
                nextToken = JsonToken.NOT_AVAILABLE;
            }
            int id2 = nextToken.id();
            if (id2 == 1) {
                _deserializeNR = _deserializeNR(jsonParser, deserializationContext, rootObjectScope.childObject());
            } else if (id2 == 2) {
                return rootObjectScope.finishRootObject();
            } else {
                if (id2 == 3) {
                    _deserializeNR = _deserializeNR(jsonParser, deserializationContext, rootObjectScope.childArray());
                } else {
                    _deserializeNR = _deserializeAnyScalar(jsonParser, deserializationContext, nextToken.id());
                }
            }
            rootObjectScope.putValue(currentName, _deserializeNR);
            currentName = jsonParser.nextFieldName();
        }
        return rootObjectScope.finishRootObject();
    }

    private Object _deserializeNR(JsonParser jsonParser, DeserializationContext deserializationContext, Scope scope) throws IOException {
        Object text;
        Object text2;
        boolean hasSomeOfFeatures = deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS);
        boolean isEnabled = deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY);
        Scope scope2 = scope;
        while (true) {
            if (scope2.isObject()) {
                String nextFieldName = jsonParser.nextFieldName();
                while (true) {
                    if (nextFieldName != null) {
                        JsonToken nextToken = jsonParser.nextToken();
                        if (nextToken == null) {
                            nextToken = JsonToken.NOT_AVAILABLE;
                        }
                        int id2 = nextToken.id();
                        if (id2 == 1) {
                            scope2 = scope2.childObject(nextFieldName);
                        } else if (id2 == 3) {
                            scope2 = scope2.childArray(nextFieldName);
                        } else {
                            switch (id2) {
                                case 6:
                                    text = jsonParser.getText();
                                    break;
                                case 7:
                                    if (!hasSomeOfFeatures) {
                                        text = jsonParser.getNumberValue();
                                        break;
                                    } else {
                                        text = _coerceIntegral(jsonParser, deserializationContext);
                                        break;
                                    }
                                case 8:
                                    if (!deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                                        text = jsonParser.getNumberValue();
                                        break;
                                    } else {
                                        text = jsonParser.getDecimalValue();
                                        break;
                                    }
                                case 9:
                                    text = Boolean.TRUE;
                                    break;
                                case 10:
                                    text = Boolean.FALSE;
                                    break;
                                case 11:
                                    text = null;
                                    break;
                                case 12:
                                    text = jsonParser.getEmbeddedObject();
                                    break;
                                default:
                                    return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                            }
                            scope2.putValue(nextFieldName, text);
                        }
                        nextFieldName = jsonParser.nextFieldName();
                    } else if (scope2 == scope) {
                        return scope2.finishRootObject();
                    } else {
                        scope2 = scope2.finishBranchObject();
                    }
                }
            } else {
                while (true) {
                    JsonToken nextToken2 = jsonParser.nextToken();
                    if (nextToken2 == null) {
                        nextToken2 = JsonToken.NOT_AVAILABLE;
                    }
                    switch (nextToken2.id()) {
                        case 1:
                            scope2 = scope2.childObject();
                            continue;
                        case 2:
                        case 5:
                        default:
                            return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                        case 3:
                            scope2 = scope2.childArray();
                            continue;
                        case 4:
                            if (scope2 == scope) {
                                return scope2.finishRootArray(isEnabled);
                            }
                            scope2 = scope2.finishBranchArray(isEnabled);
                            continue;
                        case 6:
                            text2 = jsonParser.getText();
                            scope2.addValue(text2);
                        case 7:
                            text2 = hasSomeOfFeatures ? _coerceIntegral(jsonParser, deserializationContext) : jsonParser.getNumberValue();
                            scope2.addValue(text2);
                        case 8:
                            text2 = deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) ? jsonParser.getDecimalValue() : jsonParser.getNumberValue();
                            scope2.addValue(text2);
                        case 9:
                            text2 = Boolean.TRUE;
                            scope2.addValue(text2);
                        case 10:
                            text2 = Boolean.FALSE;
                            scope2.addValue(text2);
                        case 11:
                            text2 = null;
                            scope2.addValue(text2);
                        case 12:
                            text2 = jsonParser.getEmbeddedObject();
                            scope2.addValue(text2);
                    }
                }
            }
        }
    }

    private Object _deserializeAnyScalar(JsonParser jsonParser, DeserializationContext deserializationContext, int i) throws IOException {
        switch (i) {
            case 6:
                return jsonParser.getText();
            case 7:
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser.getBigIntegerValue();
                }
                return jsonParser.getNumberValue();
            case 8:
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return jsonParser.getNumberValue();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return jsonParser.getEmbeddedObject();
            default:
                return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
        }
    }

    protected Object _mapObjectWithDups(JsonParser jsonParser, DeserializationContext deserializationContext, Map<String, Object> map, String str, Object obj, Object obj2, String str2) throws IOException {
        boolean isEnabled = deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
        if (isEnabled) {
            _squashDups(map, str, obj, obj2);
        }
        while (str2 != null) {
            jsonParser.nextToken();
            Object deserialize = deserialize(jsonParser, deserializationContext);
            Object put = map.put(str2, deserialize);
            if (put != null && isEnabled) {
                _squashDups(map, str2, put, deserialize);
            }
            str2 = jsonParser.nextFieldName();
        }
        return map;
    }

    private void _squashDups(Map<String, Object> map, String str, Object obj, Object obj2) {
        if (obj instanceof List) {
            ((List) obj).add(obj2);
            map.put(str, obj);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj);
        arrayList.add(obj2);
        map.put(str, arrayList);
    }

    /* loaded from: classes.dex */
    public static final class Scope {
        private Scope _child;
        private String _deferredKey;
        private boolean _isObject;
        private List<Object> _list;
        private Map<String, Object> _map;
        private final Scope _parent;
        private boolean _squashDups;

        private Scope(Scope scope) {
            this._parent = scope;
            this._isObject = false;
            this._squashDups = false;
        }

        private Scope(Scope scope, boolean z, boolean z2) {
            this._parent = scope;
            this._isObject = z;
            this._squashDups = z2;
        }

        public static Scope rootObjectScope(boolean z) {
            return new Scope(null, true, z);
        }

        public static Scope rootArrayScope() {
            return new Scope(null);
        }

        private Scope resetAsArray() {
            this._isObject = false;
            return this;
        }

        private Scope resetAsObject(boolean z) {
            this._isObject = true;
            this._squashDups = z;
            return this;
        }

        public Scope childObject() {
            Scope scope = this._child;
            if (scope == null) {
                return new Scope(this, true, this._squashDups);
            }
            return scope.resetAsObject(this._squashDups);
        }

        public Scope childObject(String str) {
            this._deferredKey = str;
            Scope scope = this._child;
            if (scope == null) {
                return new Scope(this, true, this._squashDups);
            }
            return scope.resetAsObject(this._squashDups);
        }

        public Scope childArray() {
            Scope scope = this._child;
            if (scope == null) {
                return new Scope(this);
            }
            return scope.resetAsArray();
        }

        public Scope childArray(String str) {
            this._deferredKey = str;
            Scope scope = this._child;
            if (scope == null) {
                return new Scope(this);
            }
            return scope.resetAsArray();
        }

        public boolean isObject() {
            return this._isObject;
        }

        public void putValue(String str, Object obj) {
            if (this._squashDups) {
                _putValueHandleDups(str, obj);
                return;
            }
            if (this._map == null) {
                this._map = new LinkedHashMap();
            }
            this._map.put(str, obj);
        }

        public Scope putDeferredValue(Object obj) {
            String str = (String) Objects.requireNonNull(this._deferredKey);
            this._deferredKey = null;
            if (this._squashDups) {
                _putValueHandleDups(str, obj);
                return this;
            }
            if (this._map == null) {
                this._map = new LinkedHashMap();
            }
            this._map.put(str, obj);
            return this;
        }

        public void addValue(Object obj) {
            if (this._list == null) {
                this._list = new ArrayList();
            }
            this._list.add(obj);
        }

        public Object finishRootObject() {
            Map<String, Object> map = this._map;
            return map == null ? emptyMap() : map;
        }

        public Scope finishBranchObject() {
            Object obj = this._map;
            if (obj == null) {
                obj = new LinkedHashMap();
            } else {
                this._map = null;
            }
            if (this._parent.isObject()) {
                return this._parent.putDeferredValue(obj);
            }
            this._parent.addValue(obj);
            return this._parent;
        }

        public Object finishRootArray(boolean z) {
            List<Object> list = this._list;
            if (list != null) {
                return z ? list.toArray(UntypedObjectDeserializerNR.NO_OBJECTS) : list;
            } else if (z) {
                return UntypedObjectDeserializerNR.NO_OBJECTS;
            } else {
                return emptyList();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Scope finishBranchArray(boolean z) {
            Object obj;
            List<Object> list = this._list;
            List<Object> list2 = list;
            if (list != null) {
                if (z) {
                    list2 = list.toArray(UntypedObjectDeserializerNR.NO_OBJECTS);
                }
                this._list = null;
                obj = list2;
            } else if (z) {
                obj = UntypedObjectDeserializerNR.NO_OBJECTS;
            } else {
                obj = emptyList();
            }
            if (this._parent.isObject()) {
                return this._parent.putDeferredValue(obj);
            }
            this._parent.addValue(obj);
            return this._parent;
        }

        private void _putValueHandleDups(String str, Object obj) {
            Map<String, Object> map = this._map;
            if (map == null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                this._map = linkedHashMap;
                linkedHashMap.put(str, obj);
                return;
            }
            Object put = map.put(str, obj);
            if (put != null) {
                if (put instanceof List) {
                    ((List) put).add(obj);
                    this._map.put(str, put);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(put);
                arrayList.add(obj);
                this._map.put(str, arrayList);
            }
        }

        public static Map<String, Object> emptyMap() {
            return new LinkedHashMap(2);
        }

        public static List<Object> emptyList() {
            return new ArrayList(2);
        }
    }
}
