package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.cfg.DatatypeFeatures;
import com.fasterxml.jackson.databind.cfg.JsonNodeFeature;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

/* compiled from: JsonNodeDeserializer.java */
/* loaded from: classes.dex */
abstract class BaseNodeDeserializer<T extends JsonNode> extends StdDeserializer<T> implements ContextualDeserializer {
    protected final boolean _mergeArrays;
    protected final boolean _mergeObjects;
    protected final Boolean _supportsUpdates;

    protected abstract JsonDeserializer<?> _createWithMerge(boolean z, boolean z2);

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return true;
    }

    public BaseNodeDeserializer(Class<T> cls, Boolean bool) {
        super((Class<?>) cls);
        this._supportsUpdates = bool;
        this._mergeArrays = true;
        this._mergeObjects = true;
    }

    public BaseNodeDeserializer(BaseNodeDeserializer<?> baseNodeDeserializer, boolean z, boolean z2) {
        super(baseNodeDeserializer);
        this._supportsUpdates = baseNodeDeserializer._supportsUpdates;
        this._mergeArrays = z;
        this._mergeObjects = z2;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._supportsUpdates;
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        Boolean defaultMergeable = config.getDefaultMergeable(ArrayNode.class);
        Boolean defaultMergeable2 = config.getDefaultMergeable(ObjectNode.class);
        Boolean defaultMergeable3 = config.getDefaultMergeable(JsonNode.class);
        boolean _shouldMerge = _shouldMerge(defaultMergeable, defaultMergeable3);
        boolean _shouldMerge2 = _shouldMerge(defaultMergeable2, defaultMergeable3);
        return (_shouldMerge == this._mergeArrays && _shouldMerge2 == this._mergeObjects) ? this : _createWithMerge(_shouldMerge, _shouldMerge2);
    }

    private static boolean _shouldMerge(Boolean bool, Boolean bool2) {
        if (bool != null) {
            return bool.booleanValue();
        }
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        return true;
    }

    protected void _handleDuplicateField(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) throws IOException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
            deserializationContext.reportInputMismatch(JsonNode.class, "Duplicate field '%s' for `ObjectNode`: not allowed when `DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY` enabled", str);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES)) {
            if (jsonNode.isArray()) {
                ((ArrayNode) jsonNode).add(jsonNode2);
                objectNode.replace(str, jsonNode);
                return;
            }
            ArrayNode arrayNode = jsonNodeFactory.arrayNode();
            arrayNode.add(jsonNode);
            arrayNode.add(jsonNode2);
            objectNode.replace(str, arrayNode);
        }
    }

    public final ObjectNode _deserializeObjectAtName(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, ContainerStack containerStack) throws IOException {
        JsonNode _deserializeContainerNoRecursion;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        String currentName = jsonParser.currentName();
        while (currentName != null) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == null) {
                nextToken = JsonToken.NOT_AVAILABLE;
            }
            int id2 = nextToken.id();
            if (id2 == 1) {
                _deserializeContainerNoRecursion = _deserializeContainerNoRecursion(jsonParser, deserializationContext, jsonNodeFactory, containerStack, jsonNodeFactory.objectNode());
            } else if (id2 == 3) {
                _deserializeContainerNoRecursion = _deserializeContainerNoRecursion(jsonParser, deserializationContext, jsonNodeFactory, containerStack, jsonNodeFactory.arrayNode());
            } else {
                _deserializeContainerNoRecursion = _deserializeAnyScalar(jsonParser, deserializationContext);
            }
            JsonNode jsonNode = _deserializeContainerNoRecursion;
            JsonNode replace = objectNode.replace(currentName, jsonNode);
            if (replace != null) {
                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, currentName, objectNode, replace, jsonNode);
            }
            currentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    public final JsonNode updateObject(JsonParser jsonParser, DeserializationContext deserializationContext, ObjectNode objectNode, ContainerStack containerStack) throws IOException {
        String currentName;
        JsonNode _deserializeContainerNoRecursion;
        if (jsonParser.isExpectedStartObjectToken()) {
            currentName = jsonParser.nextFieldName();
        } else if (!jsonParser.hasToken(JsonToken.FIELD_NAME)) {
            return (JsonNode) deserialize(jsonParser, deserializationContext);
        } else {
            currentName = jsonParser.currentName();
        }
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        while (currentName != null) {
            JsonToken nextToken = jsonParser.nextToken();
            JsonNode jsonNode = objectNode.get(currentName);
            if (jsonNode != null) {
                if (jsonNode instanceof ObjectNode) {
                    if (nextToken == JsonToken.START_OBJECT && this._mergeObjects) {
                        JsonNode updateObject = updateObject(jsonParser, deserializationContext, (ObjectNode) jsonNode, containerStack);
                        if (updateObject != jsonNode) {
                            objectNode.set(currentName, updateObject);
                        }
                    }
                } else if ((jsonNode instanceof ArrayNode) && nextToken == JsonToken.START_ARRAY && this._mergeArrays) {
                    _deserializeContainerNoRecursion(jsonParser, deserializationContext, nodeFactory, containerStack, (ArrayNode) jsonNode);
                }
                currentName = jsonParser.nextFieldName();
            }
            if (nextToken == null) {
                nextToken = JsonToken.NOT_AVAILABLE;
            }
            int id2 = nextToken.id();
            if (id2 == 1) {
                _deserializeContainerNoRecursion = _deserializeContainerNoRecursion(jsonParser, deserializationContext, nodeFactory, containerStack, nodeFactory.objectNode());
            } else if (id2 == 3) {
                _deserializeContainerNoRecursion = _deserializeContainerNoRecursion(jsonParser, deserializationContext, nodeFactory, containerStack, nodeFactory.arrayNode());
            } else if (id2 == 6) {
                _deserializeContainerNoRecursion = nodeFactory.textNode(jsonParser.getText());
            } else if (id2 == 7) {
                _deserializeContainerNoRecursion = _fromInt(jsonParser, deserializationContext, nodeFactory);
            } else {
                switch (id2) {
                    case 9:
                        _deserializeContainerNoRecursion = nodeFactory.booleanNode(true);
                        break;
                    case 10:
                        _deserializeContainerNoRecursion = nodeFactory.booleanNode(false);
                        break;
                    case 11:
                        if (!deserializationContext.isEnabled(JsonNodeFeature.READ_NULL_PROPERTIES)) {
                            break;
                        } else {
                            _deserializeContainerNoRecursion = nodeFactory.nullNode();
                            break;
                        }
                    default:
                        _deserializeContainerNoRecursion = _deserializeRareScalar(jsonParser, deserializationContext);
                        break;
                }
                currentName = jsonParser.nextFieldName();
            }
            objectNode.set(currentName, _deserializeContainerNoRecursion);
            currentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.fasterxml.jackson.databind.node.ContainerNode<?> _deserializeContainerNoRecursion(com.fasterxml.jackson.core.JsonParser r19, com.fasterxml.jackson.databind.DeserializationContext r20, com.fasterxml.jackson.databind.node.JsonNodeFactory r21, com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer.ContainerStack r22, com.fasterxml.jackson.databind.node.ContainerNode<?> r23) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer._deserializeContainerNoRecursion(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.node.JsonNodeFactory, com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer$ContainerStack, com.fasterxml.jackson.databind.node.ContainerNode):com.fasterxml.jackson.databind.node.ContainerNode");
    }

    public final JsonNode _deserializeAnyScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 2) {
            return nodeFactory.objectNode();
        }
        switch (currentTokenId) {
            case 6:
                return nodeFactory.textNode(jsonParser.getText());
            case 7:
                return _fromInt(jsonParser, deserializationContext, nodeFactory);
            case 8:
                return _fromFloat(jsonParser, deserializationContext, nodeFactory);
            case 9:
                return nodeFactory.booleanNode(true);
            case 10:
                return nodeFactory.booleanNode(false);
            case 11:
                return nodeFactory.nullNode();
            case 12:
                return _fromEmbedded(jsonParser, deserializationContext);
            default:
                return (JsonNode) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
        }
    }

    protected final JsonNode _deserializeRareScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId != 2) {
            if (currentTokenId != 8) {
                if (currentTokenId == 12) {
                    return _fromEmbedded(jsonParser, deserializationContext);
                }
                return (JsonNode) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
            }
            return _fromFloat(jsonParser, deserializationContext, deserializationContext.getNodeFactory());
        }
        return deserializationContext.getNodeFactory().objectNode();
    }

    protected final JsonNode _fromInt(JsonParser jsonParser, int i, JsonNodeFactory jsonNodeFactory) throws IOException {
        if (i != 0) {
            if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(i)) {
                return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
            }
            return jsonNodeFactory.numberNode(jsonParser.getLongValue());
        }
        JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (numberType == JsonParser.NumberType.INT) {
            return jsonNodeFactory.numberNode(jsonParser.getIntValue());
        }
        if (numberType == JsonParser.NumberType.LONG) {
            return jsonNodeFactory.numberNode(jsonParser.getLongValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
    }

    protected final JsonNode _fromInt(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType;
        int deserializationFeatures = deserializationContext.getDeserializationFeatures();
        if ((F_MASK_INT_COERCIONS & deserializationFeatures) != 0) {
            if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(deserializationFeatures)) {
                numberType = JsonParser.NumberType.BIG_INTEGER;
            } else if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(deserializationFeatures)) {
                numberType = JsonParser.NumberType.LONG;
            } else {
                numberType = jsonParser.getNumberType();
            }
        } else {
            numberType = jsonParser.getNumberType();
        }
        if (numberType == JsonParser.NumberType.INT) {
            return jsonNodeFactory.numberNode(jsonParser.getIntValue());
        }
        if (numberType == JsonParser.NumberType.LONG) {
            return jsonNodeFactory.numberNode(jsonParser.getLongValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
    }

    protected final JsonNode _fromFloat(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (numberType == JsonParser.NumberType.BIG_DECIMAL) {
            return _fromBigDecimal(deserializationContext, jsonNodeFactory, jsonParser.getDecimalValue());
        }
        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            if (jsonParser.isNaN()) {
                return jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
            }
            return _fromBigDecimal(deserializationContext, jsonNodeFactory, jsonParser.getDecimalValue());
        } else if (numberType == JsonParser.NumberType.FLOAT) {
            return jsonNodeFactory.numberNode(jsonParser.getFloatValue());
        } else {
            return jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
        }
    }

    protected final JsonNode _fromBigDecimal(DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, BigDecimal bigDecimal) {
        boolean willStripTrailingBigDecimalZeroes;
        DatatypeFeatures datatypeFeatures = deserializationContext.getDatatypeFeatures();
        if (datatypeFeatures.isExplicitlySet(JsonNodeFeature.STRIP_TRAILING_BIGDECIMAL_ZEROES)) {
            willStripTrailingBigDecimalZeroes = datatypeFeatures.isEnabled(JsonNodeFeature.STRIP_TRAILING_BIGDECIMAL_ZEROES);
        } else {
            willStripTrailingBigDecimalZeroes = jsonNodeFactory.willStripTrailingBigDecimalZeroes();
        }
        if (willStripTrailingBigDecimalZeroes) {
            try {
                bigDecimal = bigDecimal.stripTrailingZeros();
            } catch (ArithmeticException unused) {
            }
        }
        return jsonNodeFactory.numberNode(bigDecimal);
    }

    protected final JsonNode _fromEmbedded(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        Object embeddedObject = jsonParser.getEmbeddedObject();
        if (embeddedObject == null) {
            return nodeFactory.nullNode();
        }
        if (embeddedObject.getClass() == byte[].class) {
            return nodeFactory.binaryNode((byte[]) embeddedObject);
        }
        if (embeddedObject instanceof RawValue) {
            return nodeFactory.rawValueNode((RawValue) embeddedObject);
        }
        if (embeddedObject instanceof JsonNode) {
            return (JsonNode) embeddedObject;
        }
        return nodeFactory.pojoNode(embeddedObject);
    }

    /* compiled from: JsonNodeDeserializer.java */
    /* loaded from: classes.dex */
    public static final class ContainerStack {
        private int _end;
        private ContainerNode[] _stack;
        private int _top;

        public int size() {
            return this._top;
        }

        public void push(ContainerNode containerNode) {
            int i = this._top;
            int i2 = this._end;
            if (i < i2) {
                ContainerNode[] containerNodeArr = this._stack;
                this._top = i + 1;
                containerNodeArr[i] = containerNode;
                return;
            }
            if (this._stack == null) {
                this._end = 10;
                this._stack = new ContainerNode[10];
            } else {
                int min = i2 + Math.min((int) SerializerCache.DEFAULT_MAX_CACHED, Math.max(20, i2 >> 1));
                this._end = min;
                this._stack = (ContainerNode[]) Arrays.copyOf(this._stack, min);
            }
            ContainerNode[] containerNodeArr2 = this._stack;
            int i3 = this._top;
            this._top = i3 + 1;
            containerNodeArr2[i3] = containerNode;
        }

        public ContainerNode popOrNull() {
            int i = this._top;
            if (i == 0) {
                return null;
            }
            ContainerNode[] containerNodeArr = this._stack;
            int i2 = i - 1;
            this._top = i2;
            return containerNodeArr[i2];
        }
    }
}
