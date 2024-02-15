package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class FakeQuantOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static FakeQuantOptions getRootAsFakeQuantOptions(ByteBuffer byteBuffer) {
        return getRootAsFakeQuantOptions(byteBuffer, new FakeQuantOptions());
    }

    public static FakeQuantOptions getRootAsFakeQuantOptions(ByteBuffer byteBuffer, FakeQuantOptions fakeQuantOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return fakeQuantOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public FakeQuantOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public float min() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public float max() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public int numBits() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public boolean narrowRange() {
        int __offset = __offset(10);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createFakeQuantOptions(FlatBufferBuilder flatBufferBuilder, float f, float f2, int i, boolean z) {
        flatBufferBuilder.startTable(4);
        addNumBits(flatBufferBuilder, i);
        addMax(flatBufferBuilder, f2);
        addMin(flatBufferBuilder, f);
        addNarrowRange(flatBufferBuilder, z);
        return endFakeQuantOptions(flatBufferBuilder);
    }

    public static void startFakeQuantOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(4);
    }

    public static void addMin(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(0, f, 0.0d);
    }

    public static void addMax(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(1, f, 0.0d);
    }

    public static void addNumBits(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void addNarrowRange(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(3, z, false);
    }

    public static int endFakeQuantOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public FakeQuantOptions get(int i) {
            return get(new FakeQuantOptions(), i);
        }

        public FakeQuantOptions get(FakeQuantOptions fakeQuantOptions, int i) {
            return fakeQuantOptions.__assign(FakeQuantOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
