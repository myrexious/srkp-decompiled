package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class DepthToSpaceOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static DepthToSpaceOptions getRootAsDepthToSpaceOptions(ByteBuffer byteBuffer) {
        return getRootAsDepthToSpaceOptions(byteBuffer, new DepthToSpaceOptions());
    }

    public static DepthToSpaceOptions getRootAsDepthToSpaceOptions(ByteBuffer byteBuffer, DepthToSpaceOptions depthToSpaceOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return depthToSpaceOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public DepthToSpaceOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public static int createDepthToSpaceOptions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addBlockSize(flatBufferBuilder, i);
        return endDepthToSpaceOptions(flatBufferBuilder);
    }

    public static void startDepthToSpaceOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addBlockSize(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static int endDepthToSpaceOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public DepthToSpaceOptions get(int i) {
            return get(new DepthToSpaceOptions(), i);
        }

        public DepthToSpaceOptions get(DepthToSpaceOptions depthToSpaceOptions, int i) {
            return depthToSpaceOptions.__assign(DepthToSpaceOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
