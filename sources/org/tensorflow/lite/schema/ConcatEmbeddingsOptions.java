package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ConcatEmbeddingsOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ConcatEmbeddingsOptions getRootAsConcatEmbeddingsOptions(ByteBuffer byteBuffer) {
        return getRootAsConcatEmbeddingsOptions(byteBuffer, new ConcatEmbeddingsOptions());
    }

    public static ConcatEmbeddingsOptions getRootAsConcatEmbeddingsOptions(ByteBuffer byteBuffer, ConcatEmbeddingsOptions concatEmbeddingsOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return concatEmbeddingsOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ConcatEmbeddingsOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int numChannels() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int numColumnsPerChannel(int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int numColumnsPerChannelLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector numColumnsPerChannelVector() {
        return numColumnsPerChannelVector(new IntVector());
    }

    public IntVector numColumnsPerChannelVector(IntVector intVector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer numColumnsPerChannelAsByteBuffer() {
        return __vector_as_bytebuffer(6, 4);
    }

    public ByteBuffer numColumnsPerChannelInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 4);
    }

    public int embeddingDimPerChannel(int i) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int embeddingDimPerChannelLength() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector embeddingDimPerChannelVector() {
        return embeddingDimPerChannelVector(new IntVector());
    }

    public IntVector embeddingDimPerChannelVector(IntVector intVector) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer embeddingDimPerChannelAsByteBuffer() {
        return __vector_as_bytebuffer(8, 4);
    }

    public ByteBuffer embeddingDimPerChannelInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 4);
    }

    public static int createConcatEmbeddingsOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3) {
        flatBufferBuilder.startTable(3);
        addEmbeddingDimPerChannel(flatBufferBuilder, i3);
        addNumColumnsPerChannel(flatBufferBuilder, i2);
        addNumChannels(flatBufferBuilder, i);
        return endConcatEmbeddingsOptions(flatBufferBuilder);
    }

    public static void startConcatEmbeddingsOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addNumChannels(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addNumColumnsPerChannel(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int createNumColumnsPerChannelVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startNumColumnsPerChannelVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addEmbeddingDimPerChannel(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static int createEmbeddingDimPerChannelVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startEmbeddingDimPerChannelVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endConcatEmbeddingsOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ConcatEmbeddingsOptions get(int i) {
            return get(new ConcatEmbeddingsOptions(), i);
        }

        public ConcatEmbeddingsOptions get(ConcatEmbeddingsOptions concatEmbeddingsOptions, int i) {
            return concatEmbeddingsOptions.__assign(ConcatEmbeddingsOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
