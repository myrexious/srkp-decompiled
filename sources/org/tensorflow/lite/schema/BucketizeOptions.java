package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.FloatVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class BucketizeOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static BucketizeOptions getRootAsBucketizeOptions(ByteBuffer byteBuffer) {
        return getRootAsBucketizeOptions(byteBuffer, new BucketizeOptions());
    }

    public static BucketizeOptions getRootAsBucketizeOptions(ByteBuffer byteBuffer, BucketizeOptions bucketizeOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return bucketizeOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public BucketizeOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public float boundaries(int i) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getFloat(__vector(__offset) + (i * 4));
        }
        return 0.0f;
    }

    public int boundariesLength() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public FloatVector boundariesVector() {
        return boundariesVector(new FloatVector());
    }

    public FloatVector boundariesVector(FloatVector floatVector) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return floatVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer boundariesAsByteBuffer() {
        return __vector_as_bytebuffer(4, 4);
    }

    public ByteBuffer boundariesInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 4);
    }

    public static int createBucketizeOptions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addBoundaries(flatBufferBuilder, i);
        return endBucketizeOptions(flatBufferBuilder);
    }

    public static void startBucketizeOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addBoundaries(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int createBoundariesVector(FlatBufferBuilder flatBufferBuilder, float[] fArr) {
        flatBufferBuilder.startVector(4, fArr.length, 4);
        for (int length = fArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addFloat(fArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startBoundariesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endBucketizeOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public BucketizeOptions get(int i) {
            return get(new BucketizeOptions(), i);
        }

        public BucketizeOptions get(BucketizeOptions bucketizeOptions, int i) {
            return bucketizeOptions.__assign(BucketizeOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
