package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class WhileOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static WhileOptions getRootAsWhileOptions(ByteBuffer byteBuffer) {
        return getRootAsWhileOptions(byteBuffer, new WhileOptions());
    }

    public static WhileOptions getRootAsWhileOptions(ByteBuffer byteBuffer, WhileOptions whileOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return whileOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public WhileOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int condSubgraphIndex() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int bodySubgraphIndex() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createWhileOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addBodySubgraphIndex(flatBufferBuilder, i2);
        addCondSubgraphIndex(flatBufferBuilder, i);
        return endWhileOptions(flatBufferBuilder);
    }

    public static void startWhileOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addCondSubgraphIndex(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addBodySubgraphIndex(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static int endWhileOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public WhileOptions get(int i) {
            return get(new WhileOptions(), i);
        }

        public WhileOptions get(WhileOptions whileOptions, int i) {
            return whileOptions.__assign(WhileOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
