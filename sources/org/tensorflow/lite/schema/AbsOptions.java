package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class AbsOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static AbsOptions getRootAsAbsOptions(ByteBuffer byteBuffer) {
        return getRootAsAbsOptions(byteBuffer, new AbsOptions());
    }

    public static AbsOptions getRootAsAbsOptions(ByteBuffer byteBuffer, AbsOptions absOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return absOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public AbsOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startAbsOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endAbsOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public AbsOptions get(int i) {
            return get(new AbsOptions(), i);
        }

        public AbsOptions get(AbsOptions absOptions, int i) {
            return absOptions.__assign(AbsOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
