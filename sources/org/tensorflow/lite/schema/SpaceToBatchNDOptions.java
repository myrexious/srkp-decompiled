package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SpaceToBatchNDOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SpaceToBatchNDOptions getRootAsSpaceToBatchNDOptions(ByteBuffer byteBuffer) {
        return getRootAsSpaceToBatchNDOptions(byteBuffer, new SpaceToBatchNDOptions());
    }

    public static SpaceToBatchNDOptions getRootAsSpaceToBatchNDOptions(ByteBuffer byteBuffer, SpaceToBatchNDOptions spaceToBatchNDOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return spaceToBatchNDOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SpaceToBatchNDOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startSpaceToBatchNDOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endSpaceToBatchNDOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SpaceToBatchNDOptions get(int i) {
            return get(new SpaceToBatchNDOptions(), i);
        }

        public SpaceToBatchNDOptions get(SpaceToBatchNDOptions spaceToBatchNDOptions, int i) {
            return spaceToBatchNDOptions.__assign(SpaceToBatchNDOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
