package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ReverseV2Options extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ReverseV2Options getRootAsReverseV2Options(ByteBuffer byteBuffer) {
        return getRootAsReverseV2Options(byteBuffer, new ReverseV2Options());
    }

    public static ReverseV2Options getRootAsReverseV2Options(ByteBuffer byteBuffer, ReverseV2Options reverseV2Options) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return reverseV2Options.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ReverseV2Options __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startReverseV2Options(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endReverseV2Options(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ReverseV2Options get(int i) {
            return get(new ReverseV2Options(), i);
        }

        public ReverseV2Options get(ReverseV2Options reverseV2Options, int i) {
            return reverseV2Options.__assign(ReverseV2Options.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
