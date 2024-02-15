package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.tensorflow.lite.schema.DimensionMetadata;

/* loaded from: classes4.dex */
public final class SparsityParameters extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SparsityParameters getRootAsSparsityParameters(ByteBuffer byteBuffer) {
        return getRootAsSparsityParameters(byteBuffer, new SparsityParameters());
    }

    public static SparsityParameters getRootAsSparsityParameters(ByteBuffer byteBuffer, SparsityParameters sparsityParameters) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return sparsityParameters.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SparsityParameters __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int traversalOrder(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int traversalOrderLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector traversalOrderVector() {
        return traversalOrderVector(new IntVector());
    }

    public IntVector traversalOrderVector(IntVector intVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer traversalOrderAsByteBuffer() {
        return __vector_as_bytebuffer(4, 4);
    }

    public ByteBuffer traversalOrderInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 4);
    }

    public int blockMap(int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int blockMapLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector blockMapVector() {
        return blockMapVector(new IntVector());
    }

    public IntVector blockMapVector(IntVector intVector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer blockMapAsByteBuffer() {
        return __vector_as_bytebuffer(6, 4);
    }

    public ByteBuffer blockMapInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 4);
    }

    public DimensionMetadata dimMetadata(int i) {
        return dimMetadata(new DimensionMetadata(), i);
    }

    public DimensionMetadata dimMetadata(DimensionMetadata dimensionMetadata, int i) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return dimensionMetadata.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int dimMetadataLength() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public DimensionMetadata.Vector dimMetadataVector() {
        return dimMetadataVector(new DimensionMetadata.Vector());
    }

    public DimensionMetadata.Vector dimMetadataVector(DimensionMetadata.Vector vector) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public static int createSparsityParameters(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3) {
        flatBufferBuilder.startTable(3);
        addDimMetadata(flatBufferBuilder, i3);
        addBlockMap(flatBufferBuilder, i2);
        addTraversalOrder(flatBufferBuilder, i);
        return endSparsityParameters(flatBufferBuilder);
    }

    public static void startSparsityParameters(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addTraversalOrder(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createTraversalOrderVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startTraversalOrderVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addBlockMap(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int createBlockMapVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startBlockMapVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addDimMetadata(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static int createDimMetadataVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startDimMetadataVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endSparsityParameters(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SparsityParameters get(int i) {
            return get(new SparsityParameters(), i);
        }

        public SparsityParameters get(SparsityParameters sparsityParameters, int i) {
            return sparsityParameters.__assign(SparsityParameters.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
