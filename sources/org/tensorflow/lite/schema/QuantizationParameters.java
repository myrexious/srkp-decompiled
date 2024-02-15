package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.FloatVector;
import com.google.flatbuffers.LongVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class QuantizationParameters extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static QuantizationParameters getRootAsQuantizationParameters(ByteBuffer byteBuffer) {
        return getRootAsQuantizationParameters(byteBuffer, new QuantizationParameters());
    }

    public static QuantizationParameters getRootAsQuantizationParameters(ByteBuffer byteBuffer, QuantizationParameters quantizationParameters) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return quantizationParameters.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public QuantizationParameters __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public float min(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getFloat(__vector(__offset) + (i * 4));
        }
        return 0.0f;
    }

    public int minLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public FloatVector minVector() {
        return minVector(new FloatVector());
    }

    public FloatVector minVector(FloatVector floatVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return floatVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer minAsByteBuffer() {
        return __vector_as_bytebuffer(4, 4);
    }

    public ByteBuffer minInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 4);
    }

    public float max(int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getFloat(__vector(__offset) + (i * 4));
        }
        return 0.0f;
    }

    public int maxLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public FloatVector maxVector() {
        return maxVector(new FloatVector());
    }

    public FloatVector maxVector(FloatVector floatVector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return floatVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer maxAsByteBuffer() {
        return __vector_as_bytebuffer(6, 4);
    }

    public ByteBuffer maxInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 4);
    }

    public float scale(int i) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getFloat(__vector(__offset) + (i * 4));
        }
        return 0.0f;
    }

    public int scaleLength() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public FloatVector scaleVector() {
        return scaleVector(new FloatVector());
    }

    public FloatVector scaleVector(FloatVector floatVector) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return floatVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer scaleAsByteBuffer() {
        return __vector_as_bytebuffer(8, 4);
    }

    public ByteBuffer scaleInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 4);
    }

    public long zeroPoint(int i) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getLong(__vector(__offset) + (i * 8));
        }
        return 0L;
    }

    public int zeroPointLength() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public LongVector zeroPointVector() {
        return zeroPointVector(new LongVector());
    }

    public LongVector zeroPointVector(LongVector longVector) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return longVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer zeroPointAsByteBuffer() {
        return __vector_as_bytebuffer(10, 8);
    }

    public ByteBuffer zeroPointInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 10, 8);
    }

    public byte detailsType() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public Table details(Table table) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return __union(table, __offset + this.bb_pos);
        }
        return null;
    }

    public int quantizedDimension() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createQuantizationParameters(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, byte b, int i5, int i6) {
        flatBufferBuilder.startTable(7);
        addQuantizedDimension(flatBufferBuilder, i6);
        addDetails(flatBufferBuilder, i5);
        addZeroPoint(flatBufferBuilder, i4);
        addScale(flatBufferBuilder, i3);
        addMax(flatBufferBuilder, i2);
        addMin(flatBufferBuilder, i);
        addDetailsType(flatBufferBuilder, b);
        return endQuantizationParameters(flatBufferBuilder);
    }

    public static void startQuantizationParameters(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(7);
    }

    public static void addMin(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createMinVector(FlatBufferBuilder flatBufferBuilder, float[] fArr) {
        flatBufferBuilder.startVector(4, fArr.length, 4);
        for (int length = fArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addFloat(fArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startMinVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addMax(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int createMaxVector(FlatBufferBuilder flatBufferBuilder, float[] fArr) {
        flatBufferBuilder.startVector(4, fArr.length, 4);
        for (int length = fArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addFloat(fArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startMaxVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addScale(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static int createScaleVector(FlatBufferBuilder flatBufferBuilder, float[] fArr) {
        flatBufferBuilder.startVector(4, fArr.length, 4);
        for (int length = fArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addFloat(fArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startScaleVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addZeroPoint(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static int createZeroPointVector(FlatBufferBuilder flatBufferBuilder, long[] jArr) {
        flatBufferBuilder.startVector(8, jArr.length, 8);
        for (int length = jArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addLong(jArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startZeroPointVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(8, i, 8);
    }

    public static void addDetailsType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(4, b, 0);
    }

    public static void addDetails(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void addQuantizedDimension(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(6, i, 0);
    }

    public static int endQuantizationParameters(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public QuantizationParameters get(int i) {
            return get(new QuantizationParameters(), i);
        }

        public QuantizationParameters get(QuantizationParameters quantizationParameters, int i) {
            return quantizationParameters.__assign(QuantizationParameters.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
