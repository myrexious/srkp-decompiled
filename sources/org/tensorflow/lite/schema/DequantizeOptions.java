package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class DequantizeOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static DequantizeOptions getRootAsDequantizeOptions(ByteBuffer byteBuffer) {
        return getRootAsDequantizeOptions(byteBuffer, new DequantizeOptions());
    }

    public static DequantizeOptions getRootAsDequantizeOptions(ByteBuffer byteBuffer, DequantizeOptions dequantizeOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return dequantizeOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public DequantizeOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startDequantizeOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endDequantizeOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public DequantizeOptions get(int i) {
            return get(new DequantizeOptions(), i);
        }

        public DequantizeOptions get(DequantizeOptions dequantizeOptions, int i) {
            return dequantizeOptions.__assign(DequantizeOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
