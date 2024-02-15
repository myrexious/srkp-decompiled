package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class FullyConnectedOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static FullyConnectedOptions getRootAsFullyConnectedOptions(ByteBuffer byteBuffer) {
        return getRootAsFullyConnectedOptions(byteBuffer, new FullyConnectedOptions());
    }

    public static FullyConnectedOptions getRootAsFullyConnectedOptions(ByteBuffer byteBuffer, FullyConnectedOptions fullyConnectedOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return fullyConnectedOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public FullyConnectedOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public byte weightsFormat() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public boolean keepNumDims() {
        int __offset = __offset(8);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean asymmetricQuantizeInputs() {
        int __offset = __offset(10);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createFullyConnectedOptions(FlatBufferBuilder flatBufferBuilder, byte b, byte b2, boolean z, boolean z2) {
        flatBufferBuilder.startTable(4);
        addAsymmetricQuantizeInputs(flatBufferBuilder, z2);
        addKeepNumDims(flatBufferBuilder, z);
        addWeightsFormat(flatBufferBuilder, b2);
        addFusedActivationFunction(flatBufferBuilder, b);
        return endFullyConnectedOptions(flatBufferBuilder);
    }

    public static void startFullyConnectedOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(4);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addWeightsFormat(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(1, b, 0);
    }

    public static void addKeepNumDims(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(2, z, false);
    }

    public static void addAsymmetricQuantizeInputs(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(3, z, false);
    }

    public static int endFullyConnectedOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public FullyConnectedOptions get(int i) {
            return get(new FullyConnectedOptions(), i);
        }

        public FullyConnectedOptions get(FullyConnectedOptions fullyConnectedOptions, int i) {
            return fullyConnectedOptions.__assign(FullyConnectedOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
