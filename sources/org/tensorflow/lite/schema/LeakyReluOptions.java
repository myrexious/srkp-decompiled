package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class LeakyReluOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static LeakyReluOptions getRootAsLeakyReluOptions(ByteBuffer byteBuffer) {
        return getRootAsLeakyReluOptions(byteBuffer, new LeakyReluOptions());
    }

    public static LeakyReluOptions getRootAsLeakyReluOptions(ByteBuffer byteBuffer, LeakyReluOptions leakyReluOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return leakyReluOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public LeakyReluOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public float alpha() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public static int createLeakyReluOptions(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.startTable(1);
        addAlpha(flatBufferBuilder, f);
        return endLeakyReluOptions(flatBufferBuilder);
    }

    public static void startLeakyReluOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addAlpha(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(0, f, 0.0d);
    }

    public static int endLeakyReluOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public LeakyReluOptions get(int i) {
            return get(new LeakyReluOptions(), i);
        }

        public LeakyReluOptions get(LeakyReluOptions leakyReluOptions, int i) {
            return leakyReluOptions.__assign(LeakyReluOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
