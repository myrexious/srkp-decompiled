package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class RNNOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static RNNOptions getRootAsRNNOptions(ByteBuffer byteBuffer) {
        return getRootAsRNNOptions(byteBuffer, new RNNOptions());
    }

    public static RNNOptions getRootAsRNNOptions(ByteBuffer byteBuffer, RNNOptions rNNOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return rNNOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public RNNOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public boolean asymmetricQuantizeInputs() {
        int __offset = __offset(6);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createRNNOptions(FlatBufferBuilder flatBufferBuilder, byte b, boolean z) {
        flatBufferBuilder.startTable(2);
        addAsymmetricQuantizeInputs(flatBufferBuilder, z);
        addFusedActivationFunction(flatBufferBuilder, b);
        return endRNNOptions(flatBufferBuilder);
    }

    public static void startRNNOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addAsymmetricQuantizeInputs(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(1, z, false);
    }

    public static int endRNNOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public RNNOptions get(int i) {
            return get(new RNNOptions(), i);
        }

        public RNNOptions get(RNNOptions rNNOptions, int i) {
            return rNNOptions.__assign(RNNOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
