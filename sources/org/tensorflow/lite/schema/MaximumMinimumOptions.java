package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class MaximumMinimumOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static MaximumMinimumOptions getRootAsMaximumMinimumOptions(ByteBuffer byteBuffer) {
        return getRootAsMaximumMinimumOptions(byteBuffer, new MaximumMinimumOptions());
    }

    public static MaximumMinimumOptions getRootAsMaximumMinimumOptions(ByteBuffer byteBuffer, MaximumMinimumOptions maximumMinimumOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return maximumMinimumOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public MaximumMinimumOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startMaximumMinimumOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endMaximumMinimumOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public MaximumMinimumOptions get(int i) {
            return get(new MaximumMinimumOptions(), i);
        }

        public MaximumMinimumOptions get(MaximumMinimumOptions maximumMinimumOptions, int i) {
            return maximumMinimumOptions.__assign(MaximumMinimumOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}