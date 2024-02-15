package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class WhereOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static WhereOptions getRootAsWhereOptions(ByteBuffer byteBuffer) {
        return getRootAsWhereOptions(byteBuffer, new WhereOptions());
    }

    public static WhereOptions getRootAsWhereOptions(ByteBuffer byteBuffer, WhereOptions whereOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return whereOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public WhereOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startWhereOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endWhereOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public WhereOptions get(int i) {
            return get(new WhereOptions(), i);
        }

        public WhereOptions get(WhereOptions whereOptions, int i) {
            return whereOptions.__assign(WhereOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
