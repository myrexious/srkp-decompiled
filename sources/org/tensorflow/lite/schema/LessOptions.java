package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class LessOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static LessOptions getRootAsLessOptions(ByteBuffer byteBuffer) {
        return getRootAsLessOptions(byteBuffer, new LessOptions());
    }

    public static LessOptions getRootAsLessOptions(ByteBuffer byteBuffer, LessOptions lessOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return lessOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public LessOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startLessOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endLessOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public LessOptions get(int i) {
            return get(new LessOptions(), i);
        }

        public LessOptions get(LessOptions lessOptions, int i) {
            return lessOptions.__assign(LessOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
