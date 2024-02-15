package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class EmbeddingLookupSparseOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static EmbeddingLookupSparseOptions getRootAsEmbeddingLookupSparseOptions(ByteBuffer byteBuffer) {
        return getRootAsEmbeddingLookupSparseOptions(byteBuffer, new EmbeddingLookupSparseOptions());
    }

    public static EmbeddingLookupSparseOptions getRootAsEmbeddingLookupSparseOptions(ByteBuffer byteBuffer, EmbeddingLookupSparseOptions embeddingLookupSparseOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return embeddingLookupSparseOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public EmbeddingLookupSparseOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte combiner() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public static int createEmbeddingLookupSparseOptions(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.startTable(1);
        addCombiner(flatBufferBuilder, b);
        return endEmbeddingLookupSparseOptions(flatBufferBuilder);
    }

    public static void startEmbeddingLookupSparseOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addCombiner(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static int endEmbeddingLookupSparseOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public EmbeddingLookupSparseOptions get(int i) {
            return get(new EmbeddingLookupSparseOptions(), i);
        }

        public EmbeddingLookupSparseOptions get(EmbeddingLookupSparseOptions embeddingLookupSparseOptions, int i) {
            return embeddingLookupSparseOptions.__assign(EmbeddingLookupSparseOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
