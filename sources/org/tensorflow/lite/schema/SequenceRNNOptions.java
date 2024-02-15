package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SequenceRNNOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SequenceRNNOptions getRootAsSequenceRNNOptions(ByteBuffer byteBuffer) {
        return getRootAsSequenceRNNOptions(byteBuffer, new SequenceRNNOptions());
    }

    public static SequenceRNNOptions getRootAsSequenceRNNOptions(ByteBuffer byteBuffer, SequenceRNNOptions sequenceRNNOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return sequenceRNNOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SequenceRNNOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public boolean asymmetricQuantizeInputs() {
        int __offset = __offset(8);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createSequenceRNNOptions(FlatBufferBuilder flatBufferBuilder, boolean z, byte b, boolean z2) {
        flatBufferBuilder.startTable(3);
        addAsymmetricQuantizeInputs(flatBufferBuilder, z2);
        addFusedActivationFunction(flatBufferBuilder, b);
        addTimeMajor(flatBufferBuilder, z);
        return endSequenceRNNOptions(flatBufferBuilder);
    }

    public static void startSequenceRNNOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addTimeMajor(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(0, z, false);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(1, b, 0);
    }

    public static void addAsymmetricQuantizeInputs(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(2, z, false);
    }

    public static int endSequenceRNNOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SequenceRNNOptions get(int i) {
            return get(new SequenceRNNOptions(), i);
        }

        public SequenceRNNOptions get(SequenceRNNOptions sequenceRNNOptions, int i) {
            return sequenceRNNOptions.__assign(SequenceRNNOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
