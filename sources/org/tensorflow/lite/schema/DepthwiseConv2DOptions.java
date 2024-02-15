package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class DepthwiseConv2DOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static DepthwiseConv2DOptions getRootAsDepthwiseConv2DOptions(ByteBuffer byteBuffer) {
        return getRootAsDepthwiseConv2DOptions(byteBuffer, new DepthwiseConv2DOptions());
    }

    public static DepthwiseConv2DOptions getRootAsDepthwiseConv2DOptions(ByteBuffer byteBuffer, DepthwiseConv2DOptions depthwiseConv2DOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return depthwiseConv2DOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public DepthwiseConv2DOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public int depthMultiplier() {
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

    public int dilationWFactor() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 1;
    }

    public int dilationHFactor() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 1;
    }

    public static int createDepthwiseConv2DOptions(FlatBufferBuilder flatBufferBuilder, byte b, int i, int i2, int i3, byte b2, int i4, int i5) {
        flatBufferBuilder.startTable(7);
        addDilationHFactor(flatBufferBuilder, i5);
        addDilationWFactor(flatBufferBuilder, i4);
        addDepthMultiplier(flatBufferBuilder, i3);
        addStrideH(flatBufferBuilder, i2);
        addStrideW(flatBufferBuilder, i);
        addFusedActivationFunction(flatBufferBuilder, b2);
        addPadding(flatBufferBuilder, b);
        return endDepthwiseConv2DOptions(flatBufferBuilder);
    }

    public static void startDepthwiseConv2DOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(7);
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

    public static void addDepthMultiplier(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(4, b, 0);
    }

    public static void addDilationWFactor(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(5, i, 1);
    }

    public static void addDilationHFactor(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(6, i, 1);
    }

    public static int endDepthwiseConv2DOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public DepthwiseConv2DOptions get(int i) {
            return get(new DepthwiseConv2DOptions(), i);
        }

        public DepthwiseConv2DOptions get(DepthwiseConv2DOptions depthwiseConv2DOptions, int i) {
            return depthwiseConv2DOptions.__assign(DepthwiseConv2DOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
