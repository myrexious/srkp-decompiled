package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SpaceToDepthOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SpaceToDepthOptions getRootAsSpaceToDepthOptions(ByteBuffer byteBuffer) {
        return getRootAsSpaceToDepthOptions(byteBuffer, new SpaceToDepthOptions());
    }

    public static SpaceToDepthOptions getRootAsSpaceToDepthOptions(ByteBuffer byteBuffer, SpaceToDepthOptions spaceToDepthOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return spaceToDepthOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SpaceToDepthOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int blockSize() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createSpaceToDepthOptions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addBlockSize(flatBufferBuilder, i);
        return endSpaceToDepthOptions(flatBufferBuilder);
    }

    public static void startSpaceToDepthOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addBlockSize(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static int endSpaceToDepthOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SpaceToDepthOptions get(int i) {
            return get(new SpaceToDepthOptions(), i);
        }

        public SpaceToDepthOptions get(SpaceToDepthOptions spaceToDepthOptions, int i) {
            return spaceToDepthOptions.__assign(SpaceToDepthOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
