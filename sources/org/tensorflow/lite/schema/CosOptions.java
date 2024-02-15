package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class CosOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static CosOptions getRootAsCosOptions(ByteBuffer byteBuffer) {
        return getRootAsCosOptions(byteBuffer, new CosOptions());
    }

    public static CosOptions getRootAsCosOptions(ByteBuffer byteBuffer, CosOptions cosOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return cosOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public CosOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startCosOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endCosOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public CosOptions get(int i) {
            return get(new CosOptions(), i);
        }

        public CosOptions get(CosOptions cosOptions, int i) {
            return cosOptions.__assign(CosOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
