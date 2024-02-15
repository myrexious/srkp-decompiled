package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ShapeOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ShapeOptions getRootAsShapeOptions(ByteBuffer byteBuffer) {
        return getRootAsShapeOptions(byteBuffer, new ShapeOptions());
    }

    public static ShapeOptions getRootAsShapeOptions(ByteBuffer byteBuffer, ShapeOptions shapeOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return shapeOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ShapeOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte outType() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public static int createShapeOptions(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.startTable(1);
        addOutType(flatBufferBuilder, b);
        return endShapeOptions(flatBufferBuilder);
    }

    public static void startShapeOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addOutType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static int endShapeOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ShapeOptions get(int i) {
            return get(new ShapeOptions(), i);
        }

        public ShapeOptions get(ShapeOptions shapeOptions, int i) {
            return shapeOptions.__assign(ShapeOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
