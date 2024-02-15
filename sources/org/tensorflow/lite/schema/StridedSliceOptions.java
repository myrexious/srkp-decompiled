package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class StridedSliceOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static StridedSliceOptions getRootAsStridedSliceOptions(ByteBuffer byteBuffer) {
        return getRootAsStridedSliceOptions(byteBuffer, new StridedSliceOptions());
    }

    public static StridedSliceOptions getRootAsStridedSliceOptions(ByteBuffer byteBuffer, StridedSliceOptions stridedSliceOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return stridedSliceOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public StridedSliceOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int beginMask() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int endMask() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int ellipsisMask() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int newAxisMask() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int shrinkAxisMask() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createStridedSliceOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5) {
        flatBufferBuilder.startTable(5);
        addShrinkAxisMask(flatBufferBuilder, i5);
        addNewAxisMask(flatBufferBuilder, i4);
        addEllipsisMask(flatBufferBuilder, i3);
        addEndMask(flatBufferBuilder, i2);
        addBeginMask(flatBufferBuilder, i);
        return endStridedSliceOptions(flatBufferBuilder);
    }

    public static void startStridedSliceOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(5);
    }

    public static void addBeginMask(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addEndMask(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void addEllipsisMask(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void addNewAxisMask(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static void addShrinkAxisMask(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(4, i, 0);
    }

    public static int endStridedSliceOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public StridedSliceOptions get(int i) {
            return get(new StridedSliceOptions(), i);
        }

        public StridedSliceOptions get(StridedSliceOptions stridedSliceOptions, int i) {
            return stridedSliceOptions.__assign(StridedSliceOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
