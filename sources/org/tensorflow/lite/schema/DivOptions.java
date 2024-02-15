package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class DivOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static DivOptions getRootAsDivOptions(ByteBuffer byteBuffer) {
        return getRootAsDivOptions(byteBuffer, new DivOptions());
    }

    public static DivOptions getRootAsDivOptions(ByteBuffer byteBuffer, DivOptions divOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return divOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public DivOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public static int createDivOptions(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.startTable(1);
        addFusedActivationFunction(flatBufferBuilder, b);
        return endDivOptions(flatBufferBuilder);
    }

    public static void startDivOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static int endDivOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public DivOptions get(int i) {
            return get(new DivOptions(), i);
        }

        public DivOptions get(DivOptions divOptions, int i) {
            return divOptions.__assign(DivOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
