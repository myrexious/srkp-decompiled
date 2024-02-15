package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SubOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SubOptions getRootAsSubOptions(ByteBuffer byteBuffer) {
        return getRootAsSubOptions(byteBuffer, new SubOptions());
    }

    public static SubOptions getRootAsSubOptions(ByteBuffer byteBuffer, SubOptions subOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return subOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SubOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte fusedActivationFunction() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public boolean potScaleInt16() {
        int __offset = __offset(6);
        return __offset == 0 || this.bb.get(__offset + this.bb_pos) != 0;
    }

    public static int createSubOptions(FlatBufferBuilder flatBufferBuilder, byte b, boolean z) {
        flatBufferBuilder.startTable(2);
        addPotScaleInt16(flatBufferBuilder, z);
        addFusedActivationFunction(flatBufferBuilder, b);
        return endSubOptions(flatBufferBuilder);
    }

    public static void startSubOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addPotScaleInt16(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(1, z, true);
    }

    public static int endSubOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SubOptions get(int i) {
            return get(new SubOptions(), i);
        }

        public SubOptions get(SubOptions subOptions, int i) {
            return subOptions.__assign(SubOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
