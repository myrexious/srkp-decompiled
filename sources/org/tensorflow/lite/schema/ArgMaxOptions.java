package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ArgMaxOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ArgMaxOptions getRootAsArgMaxOptions(ByteBuffer byteBuffer) {
        return getRootAsArgMaxOptions(byteBuffer, new ArgMaxOptions());
    }

    public static ArgMaxOptions getRootAsArgMaxOptions(ByteBuffer byteBuffer, ArgMaxOptions argMaxOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return argMaxOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ArgMaxOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte outputType() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public static int createArgMaxOptions(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.startTable(1);
        addOutputType(flatBufferBuilder, b);
        return endArgMaxOptions(flatBufferBuilder);
    }

    public static void startArgMaxOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addOutputType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static int endArgMaxOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ArgMaxOptions get(int i) {
            return get(new ArgMaxOptions(), i);
        }

        public ArgMaxOptions get(ArgMaxOptions argMaxOptions, int i) {
            return argMaxOptions.__assign(ArgMaxOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
