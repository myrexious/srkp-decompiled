package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ResizeNearestNeighborOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ResizeNearestNeighborOptions getRootAsResizeNearestNeighborOptions(ByteBuffer byteBuffer) {
        return getRootAsResizeNearestNeighborOptions(byteBuffer, new ResizeNearestNeighborOptions());
    }

    public static ResizeNearestNeighborOptions getRootAsResizeNearestNeighborOptions(ByteBuffer byteBuffer, ResizeNearestNeighborOptions resizeNearestNeighborOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return resizeNearestNeighborOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ResizeNearestNeighborOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public boolean alignCorners() {
        int __offset = __offset(4);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean halfPixelCenters() {
        int __offset = __offset(6);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createResizeNearestNeighborOptions(FlatBufferBuilder flatBufferBuilder, boolean z, boolean z2) {
        flatBufferBuilder.startTable(2);
        addHalfPixelCenters(flatBufferBuilder, z2);
        addAlignCorners(flatBufferBuilder, z);
        return endResizeNearestNeighborOptions(flatBufferBuilder);
    }

    public static void startResizeNearestNeighborOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addAlignCorners(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(0, z, false);
    }

    public static void addHalfPixelCenters(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(1, z, false);
    }

    public static int endResizeNearestNeighborOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ResizeNearestNeighborOptions get(int i) {
            return get(new ResizeNearestNeighborOptions(), i);
        }

        public ResizeNearestNeighborOptions get(ResizeNearestNeighborOptions resizeNearestNeighborOptions, int i) {
            return resizeNearestNeighborOptions.__assign(ResizeNearestNeighborOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
