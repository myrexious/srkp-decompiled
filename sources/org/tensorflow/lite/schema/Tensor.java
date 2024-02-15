package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes4.dex */
public final class Tensor extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static Tensor getRootAsTensor(ByteBuffer byteBuffer) {
        return getRootAsTensor(byteBuffer, new Tensor());
    }

    public static Tensor getRootAsTensor(ByteBuffer byteBuffer, Tensor tensor) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return tensor.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Tensor __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int shape(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int shapeLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector shapeVector() {
        return shapeVector(new IntVector());
    }

    public IntVector shapeVector(IntVector intVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer shapeAsByteBuffer() {
        return __vector_as_bytebuffer(4, 4);
    }

    public ByteBuffer shapeInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 4);
    }

    public byte type() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public long buffer() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos) & BodyPartID.bodyIdMax;
        }
        return 0L;
    }

    public String name() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer nameAsByteBuffer() {
        return __vector_as_bytebuffer(10, 1);
    }

    public ByteBuffer nameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 10, 1);
    }

    public QuantizationParameters quantization() {
        return quantization(new QuantizationParameters());
    }

    public QuantizationParameters quantization(QuantizationParameters quantizationParameters) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return quantizationParameters.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public boolean isVariable() {
        int __offset = __offset(14);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public SparsityParameters sparsity() {
        return sparsity(new SparsityParameters());
    }

    public SparsityParameters sparsity(SparsityParameters sparsityParameters) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return sparsityParameters.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public int shapeSignature(int i) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int shapeSignatureLength() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector shapeSignatureVector() {
        return shapeSignatureVector(new IntVector());
    }

    public IntVector shapeSignatureVector(IntVector intVector) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer shapeSignatureAsByteBuffer() {
        return __vector_as_bytebuffer(18, 4);
    }

    public ByteBuffer shapeSignatureInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 18, 4);
    }

    public static int createTensor(FlatBufferBuilder flatBufferBuilder, int i, byte b, long j, int i2, int i3, boolean z, int i4, int i5) {
        flatBufferBuilder.startTable(8);
        addShapeSignature(flatBufferBuilder, i5);
        addSparsity(flatBufferBuilder, i4);
        addQuantization(flatBufferBuilder, i3);
        addName(flatBufferBuilder, i2);
        addBuffer(flatBufferBuilder, j);
        addShape(flatBufferBuilder, i);
        addIsVariable(flatBufferBuilder, z);
        addType(flatBufferBuilder, b);
        return endTensor(flatBufferBuilder);
    }

    public static void startTensor(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(8);
    }

    public static void addShape(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createShapeVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startShapeVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(1, b, 0);
    }

    public static void addBuffer(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(2, (int) j, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void addQuantization(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static void addIsVariable(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(5, z, false);
    }

    public static void addSparsity(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void addShapeSignature(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static int createShapeSignatureVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startShapeSignatureVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endTensor(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Tensor get(int i) {
            return get(new Tensor(), i);
        }

        public Tensor get(Tensor tensor, int i) {
            return tensor.__assign(Tensor.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
