package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.FloatVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class Stats extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static Stats getRootAsStats(ByteBuffer byteBuffer) {
        return getRootAsStats(byteBuffer, new Stats());
    }

    public static Stats getRootAsStats(ByteBuffer byteBuffer, Stats stats) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return stats.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Stats __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public float max(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getFloat(__vector(__offset) + (i * 4));
        }
        return 0.0f;
    }

    public int maxLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public FloatVector maxVector() {
        return maxVector(new FloatVector());
    }

    public FloatVector maxVector(FloatVector floatVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return floatVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer maxAsByteBuffer() {
        return __vector_as_bytebuffer(4, 4);
    }

    public ByteBuffer maxInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 4);
    }

    public float min(int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getFloat(__vector(__offset) + (i * 4));
        }
        return 0.0f;
    }

    public int minLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public FloatVector minVector() {
        return minVector(new FloatVector());
    }

    public FloatVector minVector(FloatVector floatVector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return floatVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer minAsByteBuffer() {
        return __vector_as_bytebuffer(6, 4);
    }

    public ByteBuffer minInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 4);
    }

    public static int createStats(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addMin(flatBufferBuilder, i2);
        addMax(flatBufferBuilder, i);
        return endStats(flatBufferBuilder);
    }

    public static void startStats(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addMax(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
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

    public static void addMin(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
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

    public static int endStats(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Stats get(int i) {
            return get(new Stats(), i);
        }

        public Stats get(Stats stats, int i) {
            return stats.__assign(Stats.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
