package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class TransposeConvOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static TransposeConvOptions getRootAsTransposeConvOptions(ByteBuffer byteBuffer) {
        return getRootAsTransposeConvOptions(byteBuffer, new TransposeConvOptions());
    }

    public static TransposeConvOptions getRootAsTransposeConvOptions(ByteBuffer byteBuffer, TransposeConvOptions transposeConvOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return transposeConvOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public TransposeConvOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte padding() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public int strideW() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int strideH() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createTransposeConvOptions(FlatBufferBuilder flatBufferBuilder, byte b, int i, int i2) {
        flatBufferBuilder.startTable(3);
        addStrideH(flatBufferBuilder, i2);
        addStrideW(flatBufferBuilder, i);
        addPadding(flatBufferBuilder, b);
        return endTransposeConvOptions(flatBufferBuilder);
    }

    public static void startTransposeConvOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addPadding(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addStrideW(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void addStrideH(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static int endTransposeConvOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public TransposeConvOptions get(int i) {
            return get(new TransposeConvOptions(), i);
        }

        public TransposeConvOptions get(TransposeConvOptions transposeConvOptions, int i) {
            return transposeConvOptions.__assign(TransposeConvOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
