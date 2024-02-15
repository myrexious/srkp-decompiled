package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class HashtableOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static HashtableOptions getRootAsHashtableOptions(ByteBuffer byteBuffer) {
        return getRootAsHashtableOptions(byteBuffer, new HashtableOptions());
    }

    public static HashtableOptions getRootAsHashtableOptions(ByteBuffer byteBuffer, HashtableOptions hashtableOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return hashtableOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public HashtableOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int tableId() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public byte keyDtype() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public byte valueDtype() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public static int createHashtableOptions(FlatBufferBuilder flatBufferBuilder, int i, byte b, byte b2) {
        flatBufferBuilder.startTable(3);
        addTableId(flatBufferBuilder, i);
        addValueDtype(flatBufferBuilder, b2);
        addKeyDtype(flatBufferBuilder, b);
        return endHashtableOptions(flatBufferBuilder);
    }

    public static void startHashtableOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addTableId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addKeyDtype(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(1, b, 0);
    }

    public static void addValueDtype(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(2, b, 0);
    }

    public static int endHashtableOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public HashtableOptions get(int i) {
            return get(new HashtableOptions(), i);
        }

        public HashtableOptions get(HashtableOptions hashtableOptions, int i) {
            return hashtableOptions.__assign(HashtableOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
