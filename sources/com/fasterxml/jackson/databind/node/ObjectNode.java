package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.JsonNodeFeature;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class ObjectNode extends ContainerNode<ObjectNode> implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Map<String, JsonNode> _children;

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonNode get(int i) {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public final boolean isObject() {
        return true;
    }

    public ObjectNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = new LinkedHashMap();
    }

    public ObjectNode(JsonNodeFactory jsonNodeFactory, Map<String, JsonNode> map) {
        super(jsonNodeFactory);
        this._children = map;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    protected JsonNode _at(JsonPointer jsonPointer) {
        return get(jsonPointer.getMatchingProperty());
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public ObjectNode deepCopy() {
        ObjectNode objectNode = new ObjectNode(this._nodeFactory);
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            objectNode._children.put(entry.getKey(), entry.getValue().deepCopy());
        }
        return objectNode;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    @Deprecated
    public ObjectNode with(String str) {
        JsonPointer _jsonPointerIfValid = _jsonPointerIfValid(str);
        if (_jsonPointerIfValid != null) {
            return withObject(_jsonPointerIfValid);
        }
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode != null) {
            if (jsonNode instanceof ObjectNode) {
                return (ObjectNode) jsonNode;
            }
            throw new UnsupportedOperationException("Property '" + str + "' has value that is not of type `ObjectNode` (but `" + jsonNode.getClass().getName() + "`)");
        }
        ObjectNode objectNode = objectNode();
        this._children.put(str, objectNode);
        return objectNode;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public ArrayNode withArray(String str) {
        JsonPointer _jsonPointerIfValid = _jsonPointerIfValid(str);
        if (_jsonPointerIfValid != null) {
            return withArray(_jsonPointerIfValid);
        }
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode != null) {
            if (jsonNode instanceof ArrayNode) {
                return (ArrayNode) jsonNode;
            }
            throw new UnsupportedOperationException("Property '" + str + "' has value that is not of type `ArrayNode` (but `" + jsonNode.getClass().getName() + "`)");
        }
        ArrayNode arrayNode = arrayNode();
        this._children.put(str, arrayNode);
        return arrayNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.node.BaseJsonNode
    public ObjectNode _withObject(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z) {
        if (jsonPointer2.matches()) {
            return this;
        }
        JsonNode _at = _at(jsonPointer2);
        if (_at != null && (_at instanceof BaseJsonNode)) {
            ObjectNode _withObject = ((BaseJsonNode) _at)._withObject(jsonPointer, jsonPointer2.tail(), overwriteMode, z);
            if (_withObject != null) {
                return _withObject;
            }
            _withXxxVerifyReplace(jsonPointer, jsonPointer2, overwriteMode, z, _at);
        }
        return _withObjectAddTailProperty(jsonPointer2, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode
    public ArrayNode _withArray(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z) {
        if (jsonPointer2.matches()) {
            return null;
        }
        JsonNode _at = _at(jsonPointer2);
        if (_at != null && (_at instanceof BaseJsonNode)) {
            ArrayNode _withArray = ((BaseJsonNode) _at)._withArray(jsonPointer, jsonPointer2.tail(), overwriteMode, z);
            if (_withArray != null) {
                return _withArray;
            }
            _withXxxVerifyReplace(jsonPointer, jsonPointer2, overwriteMode, z, _at);
        }
        return _withArrayAddTailProperty(jsonPointer2, z);
    }

    public ObjectNode _withObjectAddTailProperty(JsonPointer jsonPointer, boolean z) {
        String matchingProperty = jsonPointer.getMatchingProperty();
        JsonPointer tail = jsonPointer.tail();
        if (tail.matches()) {
            return putObject(matchingProperty);
        }
        if (z && tail.mayMatchElement()) {
            return putArray(matchingProperty)._withObjectAddTailElement(tail, z);
        }
        return putObject(matchingProperty)._withObjectAddTailProperty(tail, z);
    }

    public ArrayNode _withArrayAddTailProperty(JsonPointer jsonPointer, boolean z) {
        String matchingProperty = jsonPointer.getMatchingProperty();
        JsonPointer tail = jsonPointer.tail();
        if (tail.matches()) {
            return putArray(matchingProperty);
        }
        if (z && tail.mayMatchElement()) {
            return putArray(matchingProperty)._withArrayAddTailElement(tail, z);
        }
        return putObject(matchingProperty)._withArrayAddTailProperty(tail, z);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializable.Base
    public boolean isEmpty(SerializerProvider serializerProvider) {
        return this._children.isEmpty();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public JsonNodeType getNodeType() {
        return JsonNodeType.OBJECT;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonToken asToken() {
        return JsonToken.START_OBJECT;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public int size() {
        return this._children.size();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean isEmpty() {
        return this._children.isEmpty();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public Iterator<JsonNode> elements() {
        return this._children.values().iterator();
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode, com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonNode get(String str) {
        return this._children.get(str);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public Iterator<String> fieldNames() {
        return this._children.keySet().iterator();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonNode path(int i) {
        return MissingNode.getInstance();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.core.TreeNode
    public JsonNode path(String str) {
        JsonNode jsonNode = this._children.get(str);
        return jsonNode != null ? jsonNode : MissingNode.getInstance();
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonNode
    public JsonNode required(String str) {
        JsonNode jsonNode = this._children.get(str);
        return jsonNode != null ? jsonNode : (JsonNode) _reportRequiredViolation("No value for property '%s' of `ObjectNode`", str);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return this._children.entrySet().iterator();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public Set<Map.Entry<String, JsonNode>> properties() {
        return this._children.entrySet();
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0025  */
    @Override // com.fasterxml.jackson.databind.JsonNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.util.Comparator<com.fasterxml.jackson.databind.JsonNode> r5, com.fasterxml.jackson.databind.JsonNode r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.fasterxml.jackson.databind.node.ObjectNode
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            com.fasterxml.jackson.databind.node.ObjectNode r6 = (com.fasterxml.jackson.databind.node.ObjectNode) r6
            java.util.Map<java.lang.String, com.fasterxml.jackson.databind.JsonNode> r0 = r4._children
            java.util.Map<java.lang.String, com.fasterxml.jackson.databind.JsonNode> r6 = r6._children
            int r2 = r0.size()
            int r3 = r6.size()
            if (r3 == r2) goto L17
            return r1
        L17:
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L1f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L44
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r3 = r6.get(r3)
            com.fasterxml.jackson.databind.JsonNode r3 = (com.fasterxml.jackson.databind.JsonNode) r3
            if (r3 == 0) goto L43
            java.lang.Object r2 = r2.getValue()
            com.fasterxml.jackson.databind.JsonNode r2 = (com.fasterxml.jackson.databind.JsonNode) r2
            boolean r2 = r2.equals(r5, r3)
            if (r2 != 0) goto L1f
        L43:
            return r1
        L44:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.node.ObjectNode.equals(java.util.Comparator, com.fasterxml.jackson.databind.JsonNode):boolean");
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public JsonNode findValue(String str) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                return entry.getValue();
            }
            JsonNode findValue = entry.getValue().findValue(str);
            if (findValue != null) {
                return findValue;
            }
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(entry.getValue());
            } else {
                list = entry.getValue().findValues(str, list);
            }
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<String> findValuesAsText(String str, List<String> list) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(entry.getValue().asText());
            } else {
                list = entry.getValue().findValuesAsText(str, list);
            }
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public ObjectNode findParent(String str) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                return this;
            }
            JsonNode findParent = entry.getValue().findParent(str);
            if (findParent != null) {
                return (ObjectNode) findParent;
            }
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(this);
            } else {
                list = entry.getValue().findParents(str, list);
            }
        }
        return list;
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (serializerProvider != null) {
            boolean z = !serializerProvider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
            boolean z2 = !serializerProvider.isEnabled(JsonNodeFeature.WRITE_NULL_PROPERTIES);
            if (z || z2) {
                jsonGenerator.writeStartObject(this);
                serializeFilteredContents(jsonGenerator, serializerProvider, z, z2);
                jsonGenerator.writeEndObject();
                return;
            }
        }
        jsonGenerator.writeStartObject(this);
        for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            entry.getValue().serialize(jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        boolean z;
        boolean z2;
        if (serializerProvider != null) {
            z = !serializerProvider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
            z2 = !serializerProvider.isEnabled(JsonNodeFeature.WRITE_NULL_PROPERTIES);
        } else {
            z = false;
            z2 = false;
        }
        WritableTypeId writeTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(this, JsonToken.START_OBJECT));
        if (z || z2) {
            serializeFilteredContents(jsonGenerator, serializerProvider, z, z2);
        } else {
            for (Map.Entry<String, JsonNode> entry : this._children.entrySet()) {
                jsonGenerator.writeFieldName(entry.getKey());
                entry.getValue().serialize(jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, writeTypePrefix);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0031, code lost:
        if (r2.isNull() == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void serializeFilteredContents(com.fasterxml.jackson.core.JsonGenerator r5, com.fasterxml.jackson.databind.SerializerProvider r6, boolean r7, boolean r8) throws java.io.IOException {
        /*
            r4 = this;
            java.util.Map<java.lang.String, com.fasterxml.jackson.databind.JsonNode> r0 = r4._children
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        La:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L41
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getValue()
            com.fasterxml.jackson.databind.node.BaseJsonNode r2 = (com.fasterxml.jackson.databind.node.BaseJsonNode) r2
            if (r7 == 0) goto L2b
            boolean r3 = r2.isArray()
            if (r3 == 0) goto L2b
            boolean r3 = r2.isEmpty(r6)
            if (r3 == 0) goto L2b
            goto La
        L2b:
            if (r8 == 0) goto L34
            boolean r3 = r2.isNull()
            if (r3 == 0) goto L34
            goto La
        L34:
            java.lang.Object r1 = r1.getKey()
            java.lang.String r1 = (java.lang.String) r1
            r5.writeFieldName(r1)
            r2.serialize(r5, r6)
            goto La
        L41:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.node.ObjectNode.serializeFilteredContents(com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider, boolean, boolean):void");
    }

    public <T extends JsonNode> T set(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        this._children.put(str, jsonNode);
        return this;
    }

    public <T extends JsonNode> T setAll(Map<String, ? extends JsonNode> map) {
        for (Map.Entry<String, ? extends JsonNode> entry : map.entrySet()) {
            JsonNode value = entry.getValue();
            if (value == null) {
                value = nullNode();
            }
            this._children.put(entry.getKey(), value);
        }
        return this;
    }

    public <T extends JsonNode> T setAll(ObjectNode objectNode) {
        this._children.putAll(objectNode._children);
        return this;
    }

    public JsonNode replace(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return this._children.put(str, jsonNode);
    }

    public <T extends JsonNode> T without(String str) {
        this._children.remove(str);
        return this;
    }

    public <T extends JsonNode> T without(Collection<String> collection) {
        this._children.keySet().removeAll(collection);
        return this;
    }

    @Deprecated
    public JsonNode put(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return this._children.put(str, jsonNode);
    }

    public JsonNode putIfAbsent(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return this._children.putIfAbsent(str, jsonNode);
    }

    public JsonNode remove(String str) {
        return this._children.remove(str);
    }

    public ObjectNode remove(Collection<String> collection) {
        this._children.keySet().removeAll(collection);
        return this;
    }

    @Override // com.fasterxml.jackson.databind.node.ContainerNode
    public ObjectNode removeAll() {
        this._children.clear();
        return this;
    }

    @Deprecated
    public JsonNode putAll(Map<String, ? extends JsonNode> map) {
        return setAll(map);
    }

    @Deprecated
    public JsonNode putAll(ObjectNode objectNode) {
        return setAll(objectNode);
    }

    public ObjectNode retain(Collection<String> collection) {
        this._children.keySet().retainAll(collection);
        return this;
    }

    public ObjectNode retain(String... strArr) {
        return retain(Arrays.asList(strArr));
    }

    public ArrayNode putArray(String str) {
        ArrayNode arrayNode = arrayNode();
        _put(str, arrayNode);
        return arrayNode;
    }

    public ObjectNode putObject(String str) {
        ObjectNode objectNode = objectNode();
        _put(str, objectNode);
        return objectNode;
    }

    public ObjectNode putPOJO(String str, Object obj) {
        return _put(str, pojoNode(obj));
    }

    public ObjectNode putRawValue(String str, RawValue rawValue) {
        return _put(str, rawValueNode(rawValue));
    }

    public ObjectNode putNull(String str) {
        this._children.put(str, nullNode());
        return this;
    }

    public ObjectNode put(String str, short s) {
        return _put(str, numberNode(s));
    }

    public ObjectNode put(String str, Short sh) {
        JsonNode numberNode;
        if (sh == null) {
            numberNode = nullNode();
        } else {
            numberNode = numberNode(sh.shortValue());
        }
        return _put(str, numberNode);
    }

    public ObjectNode put(String str, int i) {
        return _put(str, numberNode(i));
    }

    public ObjectNode put(String str, Integer num) {
        JsonNode numberNode;
        if (num == null) {
            numberNode = nullNode();
        } else {
            numberNode = numberNode(num.intValue());
        }
        return _put(str, numberNode);
    }

    public ObjectNode put(String str, long j) {
        return _put(str, numberNode(j));
    }

    public ObjectNode put(String str, Long l) {
        JsonNode numberNode;
        if (l == null) {
            numberNode = nullNode();
        } else {
            numberNode = numberNode(l.longValue());
        }
        return _put(str, numberNode);
    }

    public ObjectNode put(String str, float f) {
        return _put(str, numberNode(f));
    }

    public ObjectNode put(String str, Float f) {
        JsonNode numberNode;
        if (f == null) {
            numberNode = nullNode();
        } else {
            numberNode = numberNode(f.floatValue());
        }
        return _put(str, numberNode);
    }

    public ObjectNode put(String str, double d) {
        return _put(str, numberNode(d));
    }

    public ObjectNode put(String str, Double d) {
        JsonNode numberNode;
        if (d == null) {
            numberNode = nullNode();
        } else {
            numberNode = numberNode(d.doubleValue());
        }
        return _put(str, numberNode);
    }

    public ObjectNode put(String str, BigDecimal bigDecimal) {
        ValueNode numberNode;
        if (bigDecimal == null) {
            numberNode = nullNode();
        } else {
            numberNode = numberNode(bigDecimal);
        }
        return _put(str, numberNode);
    }

    public ObjectNode put(String str, BigInteger bigInteger) {
        ValueNode numberNode;
        if (bigInteger == null) {
            numberNode = nullNode();
        } else {
            numberNode = numberNode(bigInteger);
        }
        return _put(str, numberNode);
    }

    public ObjectNode put(String str, String str2) {
        JsonNode textNode;
        if (str2 == null) {
            textNode = nullNode();
        } else {
            textNode = textNode(str2);
        }
        return _put(str, textNode);
    }

    public ObjectNode put(String str, boolean z) {
        return _put(str, booleanNode(z));
    }

    public ObjectNode put(String str, Boolean bool) {
        JsonNode booleanNode;
        if (bool == null) {
            booleanNode = nullNode();
        } else {
            booleanNode = booleanNode(bool.booleanValue());
        }
        return _put(str, booleanNode);
    }

    public ObjectNode put(String str, byte[] bArr) {
        JsonNode binaryNode;
        if (bArr == null) {
            binaryNode = nullNode();
        } else {
            binaryNode = binaryNode(bArr);
        }
        return _put(str, binaryNode);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof ObjectNode)) {
            return _childrenEqual((ObjectNode) obj);
        }
        return false;
    }

    protected boolean _childrenEqual(ObjectNode objectNode) {
        return this._children.equals(objectNode._children);
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode
    public int hashCode() {
        return this._children.hashCode();
    }

    protected ObjectNode _put(String str, JsonNode jsonNode) {
        this._children.put(str, jsonNode);
        return this;
    }
}
