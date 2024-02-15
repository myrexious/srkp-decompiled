package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ScatterNdOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ScatterNdOptions getRootAsScatterNdOptions(ByteBuffer byteBuffer) {
        return getRootAsScatterNdOptions(byteBuffer, new ScatterNdOptions());
    }

    public static ScatterNdOptions getRootAsScatterNdOptions(ByteBuffer byteBuffer, ScatterNdOptions scatterNdOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return scatterNdOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ScatterNdOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startScatterNdOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endScatterNdOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ScatterNdOptions get(int i) {
            return get(new ScatterNdOptions(), i);
        }

        public ScatterNdOptions get(ScatterNdOptions scatterNdOptions, int i) {
            return scatterNdOptions.__assign(ScatterNdOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
