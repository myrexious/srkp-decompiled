package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class LocalResponseNormalizationOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static LocalResponseNormalizationOptions getRootAsLocalResponseNormalizationOptions(ByteBuffer byteBuffer) {
        return getRootAsLocalResponseNormalizationOptions(byteBuffer, new LocalResponseNormalizationOptions());
    }

    public static LocalResponseNormalizationOptions getRootAsLocalResponseNormalizationOptions(ByteBuffer byteBuffer, LocalResponseNormalizationOptions localResponseNormalizationOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return localResponseNormalizationOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public LocalResponseNormalizationOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int radius() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public float bias() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public float alpha() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public float beta() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public static int createLocalResponseNormalizationOptions(FlatBufferBuilder flatBufferBuilder, int i, float f, float f2, float f3) {
        flatBufferBuilder.startTable(4);
        addBeta(flatBufferBuilder, f3);
        addAlpha(flatBufferBuilder, f2);
        addBias(flatBufferBuilder, f);
        addRadius(flatBufferBuilder, i);
        return endLocalResponseNormalizationOptions(flatBufferBuilder);
    }

    public static void startLocalResponseNormalizationOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(4);
    }

    public static void addRadius(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addBias(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(1, f, 0.0d);
    }

    public static void addAlpha(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(2, f, 0.0d);
    }

    public static void addBeta(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(3, f, 0.0d);
    }

    public static int endLocalResponseNormalizationOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public LocalResponseNormalizationOptions get(int i) {
            return get(new LocalResponseNormalizationOptions(), i);
        }

        public LocalResponseNormalizationOptions get(LocalResponseNormalizationOptions localResponseNormalizationOptions, int i) {
            return localResponseNormalizationOptions.__assign(LocalResponseNormalizationOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
