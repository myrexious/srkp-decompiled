package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ZerosLikeOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ZerosLikeOptions getRootAsZerosLikeOptions(ByteBuffer byteBuffer) {
        return getRootAsZerosLikeOptions(byteBuffer, new ZerosLikeOptions());
    }

    public static ZerosLikeOptions getRootAsZerosLikeOptions(ByteBuffer byteBuffer, ZerosLikeOptions zerosLikeOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return zerosLikeOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ZerosLikeOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startZerosLikeOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endZerosLikeOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ZerosLikeOptions get(int i) {
            return get(new ZerosLikeOptions(), i);
        }

        public ZerosLikeOptions get(ZerosLikeOptions zerosLikeOptions, int i) {
            return zerosLikeOptions.__assign(ZerosLikeOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
