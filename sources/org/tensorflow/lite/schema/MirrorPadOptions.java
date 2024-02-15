package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class MirrorPadOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static MirrorPadOptions getRootAsMirrorPadOptions(ByteBuffer byteBuffer) {
        return getRootAsMirrorPadOptions(byteBuffer, new MirrorPadOptions());
    }

    public static MirrorPadOptions getRootAsMirrorPadOptions(ByteBuffer byteBuffer, MirrorPadOptions mirrorPadOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return mirrorPadOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public MirrorPadOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte mode() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public static int createMirrorPadOptions(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.startTable(1);
        addMode(flatBufferBuilder, b);
        return endMirrorPadOptions(flatBufferBuilder);
    }

    public static void startMirrorPadOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addMode(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static int endMirrorPadOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public MirrorPadOptions get(int i) {
            return get(new MirrorPadOptions(), i);
        }

        public MirrorPadOptions get(MirrorPadOptions mirrorPadOptions, int i) {
            return mirrorPadOptions.__assign(MirrorPadOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
