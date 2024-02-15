package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ConcatenationOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ConcatenationOptions getRootAsConcatenationOptions(ByteBuffer byteBuffer) {
        return getRootAsConcatenationOptions(byteBuffer, new ConcatenationOptions());
    }

    public static ConcatenationOptions getRootAsConcatenationOptions(ByteBuffer byteBuffer, ConcatenationOptions concatenationOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return concatenationOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ConcatenationOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int axis() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public byte fusedActivationFunction() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public static int createConcatenationOptions(FlatBufferBuilder flatBufferBuilder, int i, byte b) {
        flatBufferBuilder.startTable(2);
        addAxis(flatBufferBuilder, i);
        addFusedActivationFunction(flatBufferBuilder, b);
        return endConcatenationOptions(flatBufferBuilder);
    }

    public static void startConcatenationOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addAxis(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(1, b, 0);
    }

    public static int endConcatenationOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ConcatenationOptions get(int i) {
            return get(new ConcatenationOptions(), i);
        }

        public ConcatenationOptions get(ConcatenationOptions concatenationOptions, int i) {
            return concatenationOptions.__assign(ConcatenationOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
