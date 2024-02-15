package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class UnpackOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static UnpackOptions getRootAsUnpackOptions(ByteBuffer byteBuffer) {
        return getRootAsUnpackOptions(byteBuffer, new UnpackOptions());
    }

    public static UnpackOptions getRootAsUnpackOptions(ByteBuffer byteBuffer, UnpackOptions unpackOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return unpackOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public UnpackOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int num() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int axis() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createUnpackOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addAxis(flatBufferBuilder, i2);
        addNum(flatBufferBuilder, i);
        return endUnpackOptions(flatBufferBuilder);
    }

    public static void startUnpackOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addNum(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addAxis(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static int endUnpackOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public UnpackOptions get(int i) {
            return get(new UnpackOptions(), i);
        }

        public UnpackOptions get(UnpackOptions unpackOptions, int i) {
            return unpackOptions.__assign(UnpackOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
