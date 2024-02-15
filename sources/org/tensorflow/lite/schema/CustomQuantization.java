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
public final class CustomQuantization extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static CustomQuantization getRootAsCustomQuantization(ByteBuffer byteBuffer) {
        return getRootAsCustomQuantization(byteBuffer, new CustomQuantization());
    }

    public static CustomQuantization getRootAsCustomQuantization(ByteBuffer byteBuffer, CustomQuantization customQuantization) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return customQuantization.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public CustomQuantization __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int custom(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__vector(__offset) + (i * 1)) & UByte.MAX_VALUE;
        }
        return 0;
    }

    public int customLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ByteVector customVector() {
        return customVector(new ByteVector());
    }

    public ByteVector customVector(ByteVector byteVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return byteVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer customAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer customInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public static int createCustomQuantization(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addCustom(flatBufferBuilder, i);
        return endCustomQuantization(flatBufferBuilder);
    }

    public static void startCustomQuantization(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addCustom(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createCustomVector(FlatBufferBuilder flatBufferBuilder, byte[] bArr) {
        return flatBufferBuilder.createByteVector(bArr);
    }

    public static int createCustomVector(FlatBufferBuilder flatBufferBuilder, ByteBuffer byteBuffer) {
        return flatBufferBuilder.createByteVector(byteBuffer);
    }

    public static void startCustomVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(1, i, 1);
    }

    public static int endCustomQuantization(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public CustomQuantization get(int i) {
            return get(new CustomQuantization(), i);
        }

        public CustomQuantization get(CustomQuantization customQuantization, int i) {
            return customQuantization.__assign(CustomQuantization.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
