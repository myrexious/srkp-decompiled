package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class NonMaxSuppressionV5Options extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static NonMaxSuppressionV5Options getRootAsNonMaxSuppressionV5Options(ByteBuffer byteBuffer) {
        return getRootAsNonMaxSuppressionV5Options(byteBuffer, new NonMaxSuppressionV5Options());
    }

    public static NonMaxSuppressionV5Options getRootAsNonMaxSuppressionV5Options(ByteBuffer byteBuffer, NonMaxSuppressionV5Options nonMaxSuppressionV5Options) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return nonMaxSuppressionV5Options.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public NonMaxSuppressionV5Options __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startNonMaxSuppressionV5Options(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endNonMaxSuppressionV5Options(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public NonMaxSuppressionV5Options get(int i) {
            return get(new NonMaxSuppressionV5Options(), i);
        }

        public NonMaxSuppressionV5Options get(NonMaxSuppressionV5Options nonMaxSuppressionV5Options, int i) {
            return nonMaxSuppressionV5Options.__assign(NonMaxSuppressionV5Options.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
