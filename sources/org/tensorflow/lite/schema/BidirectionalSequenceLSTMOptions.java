package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class BidirectionalSequenceLSTMOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static BidirectionalSequenceLSTMOptions getRootAsBidirectionalSequenceLSTMOptions(ByteBuffer byteBuffer) {
        return getRootAsBidirectionalSequenceLSTMOptions(byteBuffer, new BidirectionalSequenceLSTMOptions());
    }

    public static BidirectionalSequenceLSTMOptions getRootAsBidirectionalSequenceLSTMOptions(ByteBuffer byteBuffer, BidirectionalSequenceLSTMOptions bidirectionalSequenceLSTMOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return bidirectionalSequenceLSTMOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public BidirectionalSequenceLSTMOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public boolean mergeOutputs() {
        int __offset = __offset(10);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean timeMajor() {
        int __offset = __offset(12);
        return __offset == 0 || this.bb.get(__offset + this.bb_pos) != 0;
    }

    public boolean asymmetricQuantizeInputs() {
        int __offset = __offset(14);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createBidirectionalSequenceLSTMOptions(FlatBufferBuilder flatBufferBuilder, byte b, float f, float f2, boolean z, boolean z2, boolean z3) {
        flatBufferBuilder.startTable(6);
        addProjClip(flatBufferBuilder, f2);
        addCellClip(flatBufferBuilder, f);
        addAsymmetricQuantizeInputs(flatBufferBuilder, z3);
        addTimeMajor(flatBufferBuilder, z2);
        addMergeOutputs(flatBufferBuilder, z);
        addFusedActivationFunction(flatBufferBuilder, b);
        return endBidirectionalSequenceLSTMOptions(flatBufferBuilder);
    }

    public static void startBidirectionalSequenceLSTMOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(6);
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

    public static void addMergeOutputs(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(3, z, false);
    }

    public static void addTimeMajor(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(4, z, true);
    }

    public static void addAsymmetricQuantizeInputs(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(5, z, false);
    }

    public static int endBidirectionalSequenceLSTMOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public BidirectionalSequenceLSTMOptions get(int i) {
            return get(new BidirectionalSequenceLSTMOptions(), i);
        }

        public BidirectionalSequenceLSTMOptions get(BidirectionalSequenceLSTMOptions bidirectionalSequenceLSTMOptions, int i) {
            return bidirectionalSequenceLSTMOptions.__assign(BidirectionalSequenceLSTMOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
