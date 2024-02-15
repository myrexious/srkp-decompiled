package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class NegOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static NegOptions getRootAsNegOptions(ByteBuffer byteBuffer) {
        return getRootAsNegOptions(byteBuffer, new NegOptions());
    }

    public static NegOptions getRootAsNegOptions(ByteBuffer byteBuffer, NegOptions negOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return negOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public NegOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startNegOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endNegOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public NegOptions get(int i) {
            return get(new NegOptions(), i);
        }

        public NegOptions get(NegOptions negOptions, int i) {
            return negOptions.__assign(NegOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
