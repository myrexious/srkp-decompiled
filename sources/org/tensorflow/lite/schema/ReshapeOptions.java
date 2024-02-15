package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ReshapeOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ReshapeOptions getRootAsReshapeOptions(ByteBuffer byteBuffer) {
        return getRootAsReshapeOptions(byteBuffer, new ReshapeOptions());
    }

    public static ReshapeOptions getRootAsReshapeOptions(ByteBuffer byteBuffer, ReshapeOptions reshapeOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return reshapeOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ReshapeOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int newShape(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int newShapeLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector newShapeVector() {
        return newShapeVector(new IntVector());
    }

    public IntVector newShapeVector(IntVector intVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer newShapeAsByteBuffer() {
        return __vector_as_bytebuffer(4, 4);
    }

    public ByteBuffer newShapeInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 4);
    }

    public static int createReshapeOptions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addNewShape(flatBufferBuilder, i);
        return endReshapeOptions(flatBufferBuilder);
    }

    public static void startReshapeOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addNewShape(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createNewShapeVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startNewShapeVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endReshapeOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ReshapeOptions get(int i) {
            return get(new ReshapeOptions(), i);
        }

        public ReshapeOptions get(ReshapeOptions reshapeOptions, int i) {
            return reshapeOptions.__assign(ReshapeOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
