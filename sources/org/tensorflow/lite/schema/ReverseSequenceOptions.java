package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ReverseSequenceOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ReverseSequenceOptions getRootAsReverseSequenceOptions(ByteBuffer byteBuffer) {
        return getRootAsReverseSequenceOptions(byteBuffer, new ReverseSequenceOptions());
    }

    public static ReverseSequenceOptions getRootAsReverseSequenceOptions(ByteBuffer byteBuffer, ReverseSequenceOptions reverseSequenceOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return reverseSequenceOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ReverseSequenceOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int seqDim() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int batchDim() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createReverseSequenceOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addBatchDim(flatBufferBuilder, i2);
        addSeqDim(flatBufferBuilder, i);
        return endReverseSequenceOptions(flatBufferBuilder);
    }

    public static void startReverseSequenceOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addSeqDim(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addBatchDim(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static int endReverseSequenceOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ReverseSequenceOptions get(int i) {
            return get(new ReverseSequenceOptions(), i);
        }

        public ReverseSequenceOptions get(ReverseSequenceOptions reverseSequenceOptions, int i) {
            return reverseSequenceOptions.__assign(ReverseSequenceOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
