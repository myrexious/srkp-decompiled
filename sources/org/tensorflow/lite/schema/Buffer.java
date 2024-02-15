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
public final class Buffer extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static Buffer getRootAsBuffer(ByteBuffer byteBuffer) {
        return getRootAsBuffer(byteBuffer, new Buffer());
    }

    public static Buffer getRootAsBuffer(ByteBuffer byteBuffer, Buffer buffer) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Buffer __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int data(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__vector(__offset) + (i * 1)) & UByte.MAX_VALUE;
        }
        return 0;
    }

    public int dataLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ByteVector dataVector() {
        return dataVector(new ByteVector());
    }

    public ByteVector dataVector(ByteVector byteVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return byteVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer dataAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer dataInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public static int createBuffer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addData(flatBufferBuilder, i);
        return endBuffer(flatBufferBuilder);
    }

    public static void startBuffer(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addData(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createDataVector(FlatBufferBuilder flatBufferBuilder, byte[] bArr) {
        return flatBufferBuilder.createByteVector(bArr);
    }

    public static int createDataVector(FlatBufferBuilder flatBufferBuilder, ByteBuffer byteBuffer) {
        return flatBufferBuilder.createByteVector(byteBuffer);
    }

    public static void startDataVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(1, i, 1);
    }

    public static int endBuffer(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Buffer get(int i) {
            return get(new Buffer(), i);
        }

        public Buffer get(Buffer buffer, int i) {
            return buffer.__assign(Buffer.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
