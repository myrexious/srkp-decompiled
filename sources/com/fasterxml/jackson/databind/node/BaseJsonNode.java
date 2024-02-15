package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.core.exc.StreamConstraintsException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.ExceptionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public abstract class BaseJsonNode extends JsonNode implements Serializable {
    private static final long serialVersionUID = 1;

    public ArrayNode _withArray(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z) {
        return null;
    }

    public ObjectNode _withObject(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z) {
        return null;
    }

    public abstract JsonToken asToken();

    public abstract int hashCode();

    @Override // com.fasterxml.jackson.core.TreeNode
    public JsonParser.NumberType numberType() {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializable
    public abstract void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    @Override // com.fasterxml.jackson.databind.JsonSerializable
    public abstract void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException;

    Object writeReplace() {
        return NodeSerialization.from(this);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public final JsonNode findPath(String str) {
        JsonNode findValue = findValue(str);
        return findValue == null ? MissingNode.getInstance() : findValue;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public JsonNode required(String str) {
        return (JsonNode) _reportRequiredViolation("Node of type `%s` has no fields", getClass().getSimpleName());
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public JsonNode required(int i) {
        return (JsonNode) _reportRequiredViolation("Node of type `%s` has no indexed values", getClass().getSimpleName());
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public JsonParser traverse() {
        return new TreeTraversingParser(this);
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public JsonParser traverse(ObjectCodec objectCodec) {
        return new TreeTraversingParser(this, objectCodec);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public ObjectNode withObject(JsonPointer jsonPointer, JsonNode.OverwriteMode overwriteMode, boolean z) {
        if (jsonPointer.matches()) {
            if (this instanceof ObjectNode) {
                return (ObjectNode) this;
            }
            _reportWrongNodeType("Can only call `withObject()` with empty JSON Pointer on `ObjectNode`, not `%s`", getClass().getName());
        }
        ObjectNode _withObject = _withObject(jsonPointer, jsonPointer, overwriteMode, z);
        if (_withObject == null) {
            _reportWrongNodeType("Cannot replace context node (of type `%s`) using `withObject()` with  JSON Pointer '%s'", getClass().getName(), jsonPointer);
        }
        return _withObject;
    }

    public void _withXxxVerifyReplace(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z, JsonNode jsonNode) {
        if (_withXxxMayReplace(jsonNode, overwriteMode)) {
            return;
        }
        _reportWrongNodeType("Cannot replace `JsonNode` of type `%s` for property \"%s\" in JSON Pointer \"%s\" (mode `OverwriteMode.%s`)", jsonNode.getClass().getName(), jsonPointer2.getMatchingProperty(), jsonPointer, overwriteMode);
    }

    /* renamed from: com.fasterxml.jackson.databind.node.BaseJsonNode$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode;

        static {
            int[] iArr = new int[JsonNode.OverwriteMode.values().length];
            $SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode = iArr;
            try {
                iArr[JsonNode.OverwriteMode.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode[JsonNode.OverwriteMode.NULLS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode[JsonNode.OverwriteMode.SCALARS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode[JsonNode.OverwriteMode.ALL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    protected boolean _withXxxMayReplace(JsonNode jsonNode, JsonNode.OverwriteMode overwriteMode) {
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode[overwriteMode.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return true;
                }
                return !jsonNode.isContainerNode();
            }
            return jsonNode.isNull();
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public ArrayNode withArray(JsonPointer jsonPointer, JsonNode.OverwriteMode overwriteMode, boolean z) {
        if (jsonPointer.matches()) {
            if (this instanceof ArrayNode) {
                return (ArrayNode) this;
            }
            _reportWrongNodeType("Can only call `withArray()` with empty JSON Pointer on `ArrayNode`, not `%s`", getClass().getName());
        }
        ArrayNode _withArray = _withArray(jsonPointer, jsonPointer, overwriteMode, z);
        if (_withArray == null) {
            _reportWrongNodeType("Cannot replace context node (of type `%s`) using `withArray()` with  JSON Pointer '%s'", getClass().getName(), jsonPointer);
        }
        return _withArray;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String toString() {
        return InternalNodeMapper.nodeToString(this);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String toPrettyString() {
        return InternalNodeMapper.nodeToPrettyString(this);
    }

    protected <T> T _reportWrongNodeType(String str, Object... objArr) {
        throw new UnsupportedOperationException(String.format(str, objArr));
    }

    public <T> T _reportWrongNodeOperation(String str, Object... objArr) {
        throw new UnsupportedOperationException(String.format(str, objArr));
    }

    public JsonPointer _jsonPointerIfValid(String str) {
        if (str.isEmpty() || str.charAt(0) == '/') {
            return JsonPointer.compile(str);
        }
        return null;
    }

    public BigInteger _bigIntFromBigDec(BigDecimal bigDecimal) {
        try {
            StreamReadConstraints.defaults().validateBigIntegerScale(bigDecimal.scale());
        } catch (StreamConstraintsException e) {
            ExceptionUtil.throwSneaky(e);
        }
        return bigDecimal.toBigInteger();
    }
}
