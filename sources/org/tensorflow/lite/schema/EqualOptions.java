package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class EqualOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static EqualOptions getRootAsEqualOptions(ByteBuffer byteBuffer) {
        return getRootAsEqualOptions(byteBuffer, new EqualOptions());
    }

    public static EqualOptions getRootAsEqualOptions(ByteBuffer byteBuffer, EqualOptions equalOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return equalOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public EqualOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startEqualOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endEqualOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public EqualOptions get(int i) {
            return get(new EqualOptions(), i);
        }

        public EqualOptions get(EqualOptions equalOptions, int i) {
            return equalOptions.__assign(EqualOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
