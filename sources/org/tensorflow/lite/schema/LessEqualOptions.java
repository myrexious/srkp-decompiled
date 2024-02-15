package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class LessEqualOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static LessEqualOptions getRootAsLessEqualOptions(ByteBuffer byteBuffer) {
        return getRootAsLessEqualOptions(byteBuffer, new LessEqualOptions());
    }

    public static LessEqualOptions getRootAsLessEqualOptions(ByteBuffer byteBuffer, LessEqualOptions lessEqualOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return lessEqualOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public LessEqualOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startLessEqualOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endLessEqualOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public LessEqualOptions get(int i) {
            return get(new LessEqualOptions(), i);
        }

        public LessEqualOptions get(LessEqualOptions lessEqualOptions, int i) {
            return lessEqualOptions.__assign(LessEqualOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
