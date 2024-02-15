package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes4.dex */
public final class ImageSize extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ImageSize getRootAsImageSize(ByteBuffer byteBuffer) {
        return getRootAsImageSize(byteBuffer, new ImageSize());
    }

    public static ImageSize getRootAsImageSize(ByteBuffer byteBuffer, ImageSize imageSize) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return imageSize.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ImageSize __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public long width() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos) & BodyPartID.bodyIdMax;
        }
        return 0L;
    }

    public long height() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos) & BodyPartID.bodyIdMax;
        }
        return 0L;
    }

    public static int createImageSize(FlatBufferBuilder flatBufferBuilder, long j, long j2) {
        flatBufferBuilder.startTable(2);
        addHeight(flatBufferBuilder, j2);
        addWidth(flatBufferBuilder, j);
        return endImageSize(flatBufferBuilder);
    }

    public static void startImageSize(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addWidth(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(0, (int) j, 0);
    }

    public static void addHeight(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(1, (int) j, 0);
    }

    public static int endImageSize(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ImageSize get(int i) {
            return get(new ImageSize(), i);
        }

        public ImageSize get(ImageSize imageSize, int i) {
            return imageSize.__assign(ImageSize.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
