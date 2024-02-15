package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class LSTMOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static LSTMOptions getRootAsLSTMOptions(ByteBuffer byteBuffer) {
        return getRootAsLSTMOptions(byteBuffer, new LSTMOptions());
    }

    public static LSTMOptions getRootAsLSTMOptions(ByteBuffer byteBuffer, LSTMOptions lSTMOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return lSTMOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public LSTMOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public float cellClip() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public float projClip() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public byte kernelType() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public boolean asymmetricQuantizeInputs() {
        int __offset = __offset(12);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createLSTMOptions(FlatBufferBuilder flatBufferBuilder, byte b, float f, float f2, byte b2, boolean z) {
        flatBufferBuilder.startTable(5);
        addProjClip(flatBufferBuilder, f2);
        addCellClip(flatBufferBuilder, f);
        addAsymmetricQuantizeInputs(flatBufferBuilder, z);
        addKernelType(flatBufferBuilder, b2);
        addFusedActivationFunction(flatBufferBuilder, b);
        return endLSTMOptions(flatBufferBuilder);
    }

    public static void startLSTMOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(5);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addCellClip(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(1, f, 0.0d);
    }

    public static void addProjClip(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(2, f, 0.0d);
    }

    public static void addKernelType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(3, b, 0);
    }

    public static void addAsymmetricQuantizeInputs(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(4, z, false);
    }

    public static int endLSTMOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public LSTMOptions get(int i) {
            return get(new LSTMOptions(), i);
        }

        public LSTMOptions get(LSTMOptions lSTMOptions, int i) {
            return lSTMOptions.__assign(LSTMOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
