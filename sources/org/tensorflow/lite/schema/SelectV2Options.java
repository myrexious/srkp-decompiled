package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SelectV2Options extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SelectV2Options getRootAsSelectV2Options(ByteBuffer byteBuffer) {
        return getRootAsSelectV2Options(byteBuffer, new SelectV2Options());
    }

    public static SelectV2Options getRootAsSelectV2Options(ByteBuffer byteBuffer, SelectV2Options selectV2Options) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return selectV2Options.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SelectV2Options __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startSelectV2Options(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endSelectV2Options(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SelectV2Options get(int i) {
            return get(new SelectV2Options(), i);
        }

        public SelectV2Options get(SelectV2Options selectV2Options, int i) {
            return selectV2Options.__assign(SelectV2Options.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
