package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class TopKV2Options extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static TopKV2Options getRootAsTopKV2Options(ByteBuffer byteBuffer) {
        return getRootAsTopKV2Options(byteBuffer, new TopKV2Options());
    }

    public static TopKV2Options getRootAsTopKV2Options(ByteBuffer byteBuffer, TopKV2Options topKV2Options) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return topKV2Options.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public TopKV2Options __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startTopKV2Options(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endTopKV2Options(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public TopKV2Options get(int i) {
            return get(new TopKV2Options(), i);
        }

        public TopKV2Options get(TopKV2Options topKV2Options, int i) {
            return topKV2Options.__assign(TopKV2Options.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
