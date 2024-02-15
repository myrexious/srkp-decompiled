package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class QuantizeOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static QuantizeOptions getRootAsQuantizeOptions(ByteBuffer byteBuffer) {
        return getRootAsQuantizeOptions(byteBuffer, new QuantizeOptions());
    }

    public static QuantizeOptions getRootAsQuantizeOptions(ByteBuffer byteBuffer, QuantizeOptions quantizeOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return quantizeOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public QuantizeOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startQuantizeOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endQuantizeOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public QuantizeOptions get(int i) {
            return get(new QuantizeOptions(), i);
        }

        public QuantizeOptions get(QuantizeOptions quantizeOptions, int i) {
            return quantizeOptions.__assign(QuantizeOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
