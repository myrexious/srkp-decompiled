package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class OperatorCode extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static OperatorCode getRootAsOperatorCode(ByteBuffer byteBuffer) {
        return getRootAsOperatorCode(byteBuffer, new OperatorCode());
    }

    public static OperatorCode getRootAsOperatorCode(ByteBuffer byteBuffer, OperatorCode operatorCode) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return operatorCode.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public OperatorCode __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte deprecatedBuiltinCode() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public String customCode() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer customCodeAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public ByteBuffer customCodeInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public int version() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 1;
    }

    public int builtinCode() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createOperatorCode(FlatBufferBuilder flatBufferBuilder, byte b, int i, int i2, int i3) {
        flatBufferBuilder.startTable(4);
        addBuiltinCode(flatBufferBuilder, i3);
        addVersion(flatBufferBuilder, i2);
        addCustomCode(flatBufferBuilder, i);
        addDeprecatedBuiltinCode(flatBufferBuilder, b);
        return endOperatorCode(flatBufferBuilder);
    }

    public static void startOperatorCode(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(4);
    }

    public static void addDeprecatedBuiltinCode(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addCustomCode(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addVersion(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 1);
    }

    public static void addBuiltinCode(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static int endOperatorCode(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public OperatorCode get(int i) {
            return get(new OperatorCode(), i);
        }

        public OperatorCode get(OperatorCode operatorCode, int i) {
            return operatorCode.__assign(OperatorCode.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
