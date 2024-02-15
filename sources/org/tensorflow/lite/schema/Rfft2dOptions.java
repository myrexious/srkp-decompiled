package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class Rfft2dOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static Rfft2dOptions getRootAsRfft2dOptions(ByteBuffer byteBuffer) {
        return getRootAsRfft2dOptions(byteBuffer, new Rfft2dOptions());
    }

    public static Rfft2dOptions getRootAsRfft2dOptions(ByteBuffer byteBuffer, Rfft2dOptions rfft2dOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return rfft2dOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Rfft2dOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startRfft2dOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endRfft2dOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Rfft2dOptions get(int i) {
            return get(new Rfft2dOptions(), i);
        }

        public Rfft2dOptions get(Rfft2dOptions rfft2dOptions, int i) {
            return rfft2dOptions.__assign(Rfft2dOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
