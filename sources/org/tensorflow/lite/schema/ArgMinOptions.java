package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ArgMinOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ArgMinOptions getRootAsArgMinOptions(ByteBuffer byteBuffer) {
        return getRootAsArgMinOptions(byteBuffer, new ArgMinOptions());
    }

    public static ArgMinOptions getRootAsArgMinOptions(ByteBuffer byteBuffer, ArgMinOptions argMinOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return argMinOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ArgMinOptions __assign(int i, ByteBuffer byteBuffer) {
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

    public static int createArgMinOptions(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.startTable(1);
        addOutputType(flatBufferBuilder, b);
        return endArgMinOptions(flatBufferBuilder);
    }

    public static void startArgMinOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addOutputType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static int endArgMinOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ArgMinOptions get(int i) {
            return get(new ArgMinOptions(), i);
        }

        public ArgMinOptions get(ArgMinOptions argMinOptions, int i) {
            return argMinOptions.__assign(ArgMinOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
