package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.ByteVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UByte;

/* loaded from: classes4.dex */
public final class Uint8Vector extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static Uint8Vector getRootAsUint8Vector(ByteBuffer byteBuffer) {
        return getRootAsUint8Vector(byteBuffer, new Uint8Vector());
    }

    public static Uint8Vector getRootAsUint8Vector(ByteBuffer byteBuffer, Uint8Vector uint8Vector) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return uint8Vector.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Uint8Vector __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int values(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__vector(__offset) + (i * 1)) & UByte.MAX_VALUE;
        }
        return 0;
    }

    public int valuesLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ByteVector valuesVector() {
        return valuesVector(new ByteVector());
    }

    public ByteVector valuesVector(ByteVector byteVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return byteVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer valuesAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer valuesInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public static int createUint8Vector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addValues(flatBufferBuilder, i);
        return endUint8Vector(flatBufferBuilder);
    }

    public static void startUint8Vector(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addValues(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createValuesVector(FlatBufferBuilder flatBufferBuilder, byte[] bArr) {
        return flatBufferBuilder.createByteVector(bArr);
    }

    public static int createValuesVector(FlatBufferBuilder flatBufferBuilder, ByteBuffer byteBuffer) {
        return flatBufferBuilder.createByteVector(byteBuffer);
    }

    public static void startValuesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(1, i, 1);
    }

    public static int endUint8Vector(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Uint8Vector get(int i) {
            return get(new Uint8Vector(), i);
        }

        public Uint8Vector get(Uint8Vector uint8Vector, int i) {
            return uint8Vector.__assign(Uint8Vector.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
