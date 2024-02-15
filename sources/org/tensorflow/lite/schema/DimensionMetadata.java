package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class DimensionMetadata extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static DimensionMetadata getRootAsDimensionMetadata(ByteBuffer byteBuffer) {
        return getRootAsDimensionMetadata(byteBuffer, new DimensionMetadata());
    }

    public static DimensionMetadata getRootAsDimensionMetadata(ByteBuffer byteBuffer, DimensionMetadata dimensionMetadata) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return dimensionMetadata.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public DimensionMetadata __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte format() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public int denseSize() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public byte arraySegmentsType() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public Table arraySegments(Table table) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __union(table, __offset + this.bb_pos);
        }
        return null;
    }

    public byte arrayIndicesType() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public Table arrayIndices(Table table) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return __union(table, __offset + this.bb_pos);
        }
        return null;
    }

    public static int createDimensionMetadata(FlatBufferBuilder flatBufferBuilder, byte b, int i, byte b2, int i2, byte b3, int i3) {
        flatBufferBuilder.startTable(6);
        addArrayIndices(flatBufferBuilder, i3);
        addArraySegments(flatBufferBuilder, i2);
        addDenseSize(flatBufferBuilder, i);
        addArrayIndicesType(flatBufferBuilder, b3);
        addArraySegmentsType(flatBufferBuilder, b2);
        addFormat(flatBufferBuilder, b);
        return endDimensionMetadata(flatBufferBuilder);
    }

    public static void startDimensionMetadata(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(6);
    }

    public static void addFormat(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addDenseSize(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void addArraySegmentsType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(2, b, 0);
    }

    public static void addArraySegments(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void addArrayIndicesType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(4, b, 0);
    }

    public static void addArrayIndices(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static int endDimensionMetadata(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public DimensionMetadata get(int i) {
            return get(new DimensionMetadata(), i);
        }

        public DimensionMetadata get(DimensionMetadata dimensionMetadata, int i) {
            return dimensionMetadata.__assign(DimensionMetadata.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
