package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ExpOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ExpOptions getRootAsExpOptions(ByteBuffer byteBuffer) {
        return getRootAsExpOptions(byteBuffer, new ExpOptions());
    }

    public static ExpOptions getRootAsExpOptions(ByteBuffer byteBuffer, ExpOptions expOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return expOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ExpOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startExpOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endExpOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ExpOptions get(int i) {
            return get(new ExpOptions(), i);
        }

        public ExpOptions get(ExpOptions expOptions, int i) {
            return expOptions.__assign(ExpOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}