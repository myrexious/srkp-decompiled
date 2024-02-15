package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class GeluOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static GeluOptions getRootAsGeluOptions(ByteBuffer byteBuffer) {
        return getRootAsGeluOptions(byteBuffer, new GeluOptions());
    }

    public static GeluOptions getRootAsGeluOptions(ByteBuffer byteBuffer, GeluOptions geluOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return geluOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public GeluOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public boolean approximate() {
        int __offset = __offset(4);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createGeluOptions(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.startTable(1);
        addApproximate(flatBufferBuilder, z);
        return endGeluOptions(flatBufferBuilder);
    }

    public static void startGeluOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addApproximate(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(0, z, false);
    }

    public static int endGeluOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public GeluOptions get(int i) {
            return get(new GeluOptions(), i);
        }

        public GeluOptions get(GeluOptions geluOptions, int i) {
            return geluOptions.__assign(GeluOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
