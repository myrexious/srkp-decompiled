package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class GatherNdOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static GatherNdOptions getRootAsGatherNdOptions(ByteBuffer byteBuffer) {
        return getRootAsGatherNdOptions(byteBuffer, new GatherNdOptions());
    }

    public static GatherNdOptions getRootAsGatherNdOptions(ByteBuffer byteBuffer, GatherNdOptions gatherNdOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return gatherNdOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public GatherNdOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startGatherNdOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endGatherNdOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public GatherNdOptions get(int i) {
            return get(new GatherNdOptions(), i);
        }

        public GatherNdOptions get(GatherNdOptions gatherNdOptions, int i) {
            return gatherNdOptions.__assign(GatherNdOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}