package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class IfOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static IfOptions getRootAsIfOptions(ByteBuffer byteBuffer) {
        return getRootAsIfOptions(byteBuffer, new IfOptions());
    }

    public static IfOptions getRootAsIfOptions(ByteBuffer byteBuffer, IfOptions ifOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return ifOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public IfOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int thenSubgraphIndex() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int elseSubgraphIndex() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createIfOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addElseSubgraphIndex(flatBufferBuilder, i2);
        addThenSubgraphIndex(flatBufferBuilder, i);
        return endIfOptions(flatBufferBuilder);
    }

    public static void startIfOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addThenSubgraphIndex(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addElseSubgraphIndex(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static int endIfOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public IfOptions get(int i) {
            return get(new IfOptions(), i);
        }

        public IfOptions get(IfOptions ifOptions, int i) {
            return ifOptions.__assign(IfOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
