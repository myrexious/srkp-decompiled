package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SquareOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SquareOptions getRootAsSquareOptions(ByteBuffer byteBuffer) {
        return getRootAsSquareOptions(byteBuffer, new SquareOptions());
    }

    public static SquareOptions getRootAsSquareOptions(ByteBuffer byteBuffer, SquareOptions squareOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return squareOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SquareOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startSquareOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endSquareOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SquareOptions get(int i) {
            return get(new SquareOptions(), i);
        }

        public SquareOptions get(SquareOptions squareOptions, int i) {
            return squareOptions.__assign(SquareOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
