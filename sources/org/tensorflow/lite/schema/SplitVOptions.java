package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SplitVOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SplitVOptions getRootAsSplitVOptions(ByteBuffer byteBuffer) {
        return getRootAsSplitVOptions(byteBuffer, new SplitVOptions());
    }

    public static SplitVOptions getRootAsSplitVOptions(ByteBuffer byteBuffer, SplitVOptions splitVOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return splitVOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SplitVOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int numSplits() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createSplitVOptions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addNumSplits(flatBufferBuilder, i);
        return endSplitVOptions(flatBufferBuilder);
    }

    public static void startSplitVOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addNumSplits(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static int endSplitVOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SplitVOptions get(int i) {
            return get(new SplitVOptions(), i);
        }

        public SplitVOptions get(SplitVOptions splitVOptions, int i) {
            return splitVOptions.__assign(SplitVOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
