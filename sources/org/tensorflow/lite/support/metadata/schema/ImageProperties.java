package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ImageProperties extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ImageProperties getRootAsImageProperties(ByteBuffer byteBuffer) {
        return getRootAsImageProperties(byteBuffer, new ImageProperties());
    }

    public static ImageProperties getRootAsImageProperties(ByteBuffer byteBuffer, ImageProperties imageProperties) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return imageProperties.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ImageProperties __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte colorSpace() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public ImageSize defaultSize() {
        return defaultSize(new ImageSize());
    }

    public ImageSize defaultSize(ImageSize imageSize) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return imageSize.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public static int createImageProperties(FlatBufferBuilder flatBufferBuilder, byte b, int i) {
        flatBufferBuilder.startTable(2);
        addDefaultSize(flatBufferBuilder, i);
        addColorSpace(flatBufferBuilder, b);
        return endImageProperties(flatBufferBuilder);
    }

    public static void startImageProperties(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addColorSpace(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addDefaultSize(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int endImageProperties(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ImageProperties get(int i) {
            return get(new ImageProperties(), i);
        }

        public ImageProperties get(ImageProperties imageProperties, int i) {
            return imageProperties.__assign(ImageProperties.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
