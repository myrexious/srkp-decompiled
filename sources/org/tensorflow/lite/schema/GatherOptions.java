package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class GatherOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static GatherOptions getRootAsGatherOptions(ByteBuffer byteBuffer) {
        return getRootAsGatherOptions(byteBuffer, new GatherOptions());
    }

    public static GatherOptions getRootAsGatherOptions(ByteBuffer byteBuffer, GatherOptions gatherOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return gatherOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public GatherOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public int batchDims() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createGatherOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addBatchDims(flatBufferBuilder, i2);
        addAxis(flatBufferBuilder, i);
        return endGatherOptions(flatBufferBuilder);
    }

    public static void startGatherOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addAxis(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addBatchDims(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static int endGatherOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public GatherOptions get(int i) {
            return get(new GatherOptions(), i);
        }

        public GatherOptions get(GatherOptions gatherOptions, int i) {
            return gatherOptions.__assign(GatherOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
