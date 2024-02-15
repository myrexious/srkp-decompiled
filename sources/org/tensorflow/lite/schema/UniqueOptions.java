package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class UniqueOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static UniqueOptions getRootAsUniqueOptions(ByteBuffer byteBuffer) {
        return getRootAsUniqueOptions(byteBuffer, new UniqueOptions());
    }

    public static UniqueOptions getRootAsUniqueOptions(ByteBuffer byteBuffer, UniqueOptions uniqueOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return uniqueOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public UniqueOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte idxOutType() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 2;
    }

    public static int createUniqueOptions(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.startTable(1);
        addIdxOutType(flatBufferBuilder, b);
        return endUniqueOptions(flatBufferBuilder);
    }

    public static void startUniqueOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addIdxOutType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 2);
    }

    public static int endUniqueOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public UniqueOptions get(int i) {
            return get(new UniqueOptions(), i);
        }

        public UniqueOptions get(UniqueOptions uniqueOptions, int i) {
            return uniqueOptions.__assign(UniqueOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
