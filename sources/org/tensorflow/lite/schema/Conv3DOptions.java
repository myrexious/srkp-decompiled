package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class Conv3DOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static Conv3DOptions getRootAsConv3DOptions(ByteBuffer byteBuffer) {
        return getRootAsConv3DOptions(byteBuffer, new Conv3DOptions());
    }

    public static Conv3DOptions getRootAsConv3DOptions(ByteBuffer byteBuffer, Conv3DOptions conv3DOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return conv3DOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Conv3DOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public int strideD() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int strideW() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int strideH() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public byte fusedActivationFunction() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public int dilationDFactor() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 1;
    }

    public int dilationWFactor() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 1;
    }

    public int dilationHFactor() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 1;
    }

    public static int createConv3DOptions(FlatBufferBuilder flatBufferBuilder, byte b, int i, int i2, int i3, byte b2, int i4, int i5, int i6) {
        flatBufferBuilder.startTable(8);
        addDilationHFactor(flatBufferBuilder, i6);
        addDilationWFactor(flatBufferBuilder, i5);
        addDilationDFactor(flatBufferBuilder, i4);
        addStrideH(flatBufferBuilder, i3);
        addStrideW(flatBufferBuilder, i2);
        addStrideD(flatBufferBuilder, i);
        addFusedActivationFunction(flatBufferBuilder, b2);
        addPadding(flatBufferBuilder, b);
        return endConv3DOptions(flatBufferBuilder);
    }

    public static void startConv3DOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(8);
    }

    public static void addPadding(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addStrideD(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void addStrideW(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void addStrideH(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(4, b, 0);
    }

    public static void addDilationDFactor(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(5, i, 1);
    }

    public static void addDilationWFactor(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(6, i, 1);
    }

    public static void addDilationHFactor(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(7, i, 1);
    }

    public static int endConv3DOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Conv3DOptions get(int i) {
            return get(new Conv3DOptions(), i);
        }

        public Conv3DOptions get(Conv3DOptions conv3DOptions, int i) {
            return conv3DOptions.__assign(Conv3DOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
