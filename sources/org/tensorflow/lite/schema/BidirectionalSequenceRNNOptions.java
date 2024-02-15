package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class BidirectionalSequenceRNNOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static BidirectionalSequenceRNNOptions getRootAsBidirectionalSequenceRNNOptions(ByteBuffer byteBuffer) {
        return getRootAsBidirectionalSequenceRNNOptions(byteBuffer, new BidirectionalSequenceRNNOptions());
    }

    public static BidirectionalSequenceRNNOptions getRootAsBidirectionalSequenceRNNOptions(ByteBuffer byteBuffer, BidirectionalSequenceRNNOptions bidirectionalSequenceRNNOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return bidirectionalSequenceRNNOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public BidirectionalSequenceRNNOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public boolean timeMajor() {
        int __offset = __offset(4);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public byte fusedActivationFunction() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public boolean mergeOutputs() {
        int __offset = __offset(8);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean asymmetricQuantizeInputs() {
        int __offset = __offset(10);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createBidirectionalSequenceRNNOptions(FlatBufferBuilder flatBufferBuilder, boolean z, byte b, boolean z2, boolean z3) {
        flatBufferBuilder.startTable(4);
        addAsymmetricQuantizeInputs(flatBufferBuilder, z3);
        addMergeOutputs(flatBufferBuilder, z2);
        addFusedActivationFunction(flatBufferBuilder, b);
        addTimeMajor(flatBufferBuilder, z);
        return endBidirectionalSequenceRNNOptions(flatBufferBuilder);
    }

    public static void startBidirectionalSequenceRNNOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(4);
    }

    public static void addTimeMajor(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(0, z, false);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(1, b, 0);
    }

    public static void addMergeOutputs(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(2, z, false);
    }

    public static void addAsymmetricQuantizeInputs(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(3, z, false);
    }

    public static int endBidirectionalSequenceRNNOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public BidirectionalSequenceRNNOptions get(int i) {
            return get(new BidirectionalSequenceRNNOptions(), i);
        }

        public BidirectionalSequenceRNNOptions get(BidirectionalSequenceRNNOptions bidirectionalSequenceRNNOptions, int i) {
            return bidirectionalSequenceRNNOptions.__assign(BidirectionalSequenceRNNOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
