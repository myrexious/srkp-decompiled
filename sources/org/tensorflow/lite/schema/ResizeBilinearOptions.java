package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ResizeBilinearOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ResizeBilinearOptions getRootAsResizeBilinearOptions(ByteBuffer byteBuffer) {
        return getRootAsResizeBilinearOptions(byteBuffer, new ResizeBilinearOptions());
    }

    public static ResizeBilinearOptions getRootAsResizeBilinearOptions(ByteBuffer byteBuffer, ResizeBilinearOptions resizeBilinearOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return resizeBilinearOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ResizeBilinearOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public boolean alignCorners() {
        int __offset = __offset(8);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean halfPixelCenters() {
        int __offset = __offset(10);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createResizeBilinearOptions(FlatBufferBuilder flatBufferBuilder, boolean z, boolean z2) {
        flatBufferBuilder.startTable(4);
        addHalfPixelCenters(flatBufferBuilder, z2);
        addAlignCorners(flatBufferBuilder, z);
        return endResizeBilinearOptions(flatBufferBuilder);
    }

    public static void startResizeBilinearOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(4);
    }

    public static void addAlignCorners(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(2, z, false);
    }

    public static void addHalfPixelCenters(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(3, z, false);
    }

    public static int endResizeBilinearOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ResizeBilinearOptions get(int i) {
            return get(new ResizeBilinearOptions(), i);
        }

        public ResizeBilinearOptions get(ResizeBilinearOptions resizeBilinearOptions, int i) {
            return resizeBilinearOptions.__assign(ResizeBilinearOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
