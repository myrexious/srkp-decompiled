package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class FillOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static FillOptions getRootAsFillOptions(ByteBuffer byteBuffer) {
        return getRootAsFillOptions(byteBuffer, new FillOptions());
    }

    public static FillOptions getRootAsFillOptions(ByteBuffer byteBuffer, FillOptions fillOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return fillOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public FillOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startFillOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endFillOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public FillOptions get(int i) {
            return get(new FillOptions(), i);
        }

        public FillOptions get(FillOptions fillOptions, int i) {
            return fillOptions.__assign(FillOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
