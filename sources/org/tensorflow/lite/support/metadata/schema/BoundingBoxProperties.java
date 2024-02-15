package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes4.dex */
public final class BoundingBoxProperties extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static BoundingBoxProperties getRootAsBoundingBoxProperties(ByteBuffer byteBuffer) {
        return getRootAsBoundingBoxProperties(byteBuffer, new BoundingBoxProperties());
    }

    public static BoundingBoxProperties getRootAsBoundingBoxProperties(ByteBuffer byteBuffer, BoundingBoxProperties boundingBoxProperties) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return boundingBoxProperties.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public BoundingBoxProperties __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public long index(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4)) & BodyPartID.bodyIdMax;
        }
        return 0L;
    }

    public int indexLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector indexVector() {
        return indexVector(new IntVector());
    }

    public IntVector indexVector(IntVector intVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer indexAsByteBuffer() {
        return __vector_as_bytebuffer(4, 4);
    }

    public ByteBuffer indexInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 4);
    }

    public byte type() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public byte coordinateType() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public static int createBoundingBoxProperties(FlatBufferBuilder flatBufferBuilder, int i, byte b, byte b2) {
        flatBufferBuilder.startTable(3);
        addIndex(flatBufferBuilder, i);
        addCoordinateType(flatBufferBuilder, b2);
        addType(flatBufferBuilder, b);
        return endBoundingBoxProperties(flatBufferBuilder);
    }

    public static void startBoundingBoxProperties(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addIndex(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createIndexVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startIndexVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(1, b, 0);
    }

    public static void addCoordinateType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(2, b, 0);
    }

    public static int endBoundingBoxProperties(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public BoundingBoxProperties get(int i) {
            return get(new BoundingBoxProperties(), i);
        }

        public BoundingBoxProperties get(BoundingBoxProperties boundingBoxProperties, int i) {
            return boundingBoxProperties.__assign(BoundingBoxProperties.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
