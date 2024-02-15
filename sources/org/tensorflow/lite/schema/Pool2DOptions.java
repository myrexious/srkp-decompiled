package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class Pool2DOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static Pool2DOptions getRootAsPool2DOptions(ByteBuffer byteBuffer) {
        return getRootAsPool2DOptions(byteBuffer, new Pool2DOptions());
    }

    public static Pool2DOptions getRootAsPool2DOptions(ByteBuffer byteBuffer, Pool2DOptions pool2DOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return pool2DOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Pool2DOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public int filterWidth() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int filterHeight() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public byte fusedActivationFunction() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public static int createPool2DOptions(FlatBufferBuilder flatBufferBuilder, byte b, int i, int i2, int i3, int i4, byte b2) {
        flatBufferBuilder.startTable(6);
        addFilterHeight(flatBufferBuilder, i4);
        addFilterWidth(flatBufferBuilder, i3);
        addStrideH(flatBufferBuilder, i2);
        addStrideW(flatBufferBuilder, i);
        addFusedActivationFunction(flatBufferBuilder, b2);
        addPadding(flatBufferBuilder, b);
        return endPool2DOptions(flatBufferBuilder);
    }

    public static void startPool2DOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(6);
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

    public static void addFilterWidth(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static void addFilterHeight(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(4, i, 0);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(5, b, 0);
    }

    public static int endPool2DOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Pool2DOptions get(int i) {
            return get(new Pool2DOptions(), i);
        }

        public Pool2DOptions get(Pool2DOptions pool2DOptions, int i) {
            return pool2DOptions.__assign(Pool2DOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
