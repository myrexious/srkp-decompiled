package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.ShortVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;

/* loaded from: classes4.dex */
public final class Uint16Vector extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static Uint16Vector getRootAsUint16Vector(ByteBuffer byteBuffer) {
        return getRootAsUint16Vector(byteBuffer, new Uint16Vector());
    }

    public static Uint16Vector getRootAsUint16Vector(ByteBuffer byteBuffer, Uint16Vector uint16Vector) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return uint16Vector.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Uint16Vector __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int values(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getShort(__vector(__offset) + (i * 2)) & UShort.MAX_VALUE;
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

    public ShortVector valuesVector() {
        return valuesVector(new ShortVector());
    }

    public ShortVector valuesVector(ShortVector shortVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return shortVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer valuesAsByteBuffer() {
        return __vector_as_bytebuffer(4, 2);
    }

    public ByteBuffer valuesInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 2);
    }

    public static int createUint16Vector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addValues(flatBufferBuilder, i);
        return endUint16Vector(flatBufferBuilder);
    }

    public static void startUint16Vector(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addValues(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createValuesVector(FlatBufferBuilder flatBufferBuilder, short[] sArr) {
        flatBufferBuilder.startVector(2, sArr.length, 2);
        for (int length = sArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addShort(sArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startValuesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(2, i, 2);
    }

    public static int endUint16Vector(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Uint16Vector get(int i) {
            return get(new Uint16Vector(), i);
        }

        public Uint16Vector get(Uint16Vector uint16Vector, int i) {
            return uint16Vector.__assign(Uint16Vector.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
