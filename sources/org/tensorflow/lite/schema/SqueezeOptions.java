package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SqueezeOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SqueezeOptions getRootAsSqueezeOptions(ByteBuffer byteBuffer) {
        return getRootAsSqueezeOptions(byteBuffer, new SqueezeOptions());
    }

    public static SqueezeOptions getRootAsSqueezeOptions(ByteBuffer byteBuffer, SqueezeOptions squeezeOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return squeezeOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SqueezeOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int squeezeDims(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int squeezeDimsLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector squeezeDimsVector() {
        return squeezeDimsVector(new IntVector());
    }

    public IntVector squeezeDimsVector(IntVector intVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer squeezeDimsAsByteBuffer() {
        return __vector_as_bytebuffer(4, 4);
    }

    public ByteBuffer squeezeDimsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 4);
    }

    public static int createSqueezeOptions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addSqueezeDims(flatBufferBuilder, i);
        return endSqueezeOptions(flatBufferBuilder);
    }

    public static void startSqueezeOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addSqueezeDims(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createSqueezeDimsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startSqueezeDimsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endSqueezeOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SqueezeOptions get(int i) {
            return get(new SqueezeOptions(), i);
        }

        public SqueezeOptions get(SqueezeOptions squeezeOptions, int i) {
            return squeezeOptions.__assign(SqueezeOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
