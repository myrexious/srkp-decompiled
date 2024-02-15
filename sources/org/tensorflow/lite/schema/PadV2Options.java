package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class PadV2Options extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static PadV2Options getRootAsPadV2Options(ByteBuffer byteBuffer) {
        return getRootAsPadV2Options(byteBuffer, new PadV2Options());
    }

    public static PadV2Options getRootAsPadV2Options(ByteBuffer byteBuffer, PadV2Options padV2Options) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return padV2Options.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public PadV2Options __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startPadV2Options(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endPadV2Options(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public PadV2Options get(int i) {
            return get(new PadV2Options(), i);
        }

        public PadV2Options get(PadV2Options padV2Options, int i) {
            return padV2Options.__assign(PadV2Options.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
