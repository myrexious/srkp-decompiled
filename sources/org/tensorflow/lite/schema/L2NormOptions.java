package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class L2NormOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static L2NormOptions getRootAsL2NormOptions(ByteBuffer byteBuffer) {
        return getRootAsL2NormOptions(byteBuffer, new L2NormOptions());
    }

    public static L2NormOptions getRootAsL2NormOptions(ByteBuffer byteBuffer, L2NormOptions l2NormOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return l2NormOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public L2NormOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte fusedActivationFunction() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public static int createL2NormOptions(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.startTable(1);
        addFusedActivationFunction(flatBufferBuilder, b);
        return endL2NormOptions(flatBufferBuilder);
    }

    public static void startL2NormOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static int endL2NormOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public L2NormOptions get(int i) {
            return get(new L2NormOptions(), i);
        }

        public L2NormOptions get(L2NormOptions l2NormOptions, int i) {
            return l2NormOptions.__assign(L2NormOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
