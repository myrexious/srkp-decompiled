package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class RandomOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static RandomOptions getRootAsRandomOptions(ByteBuffer byteBuffer) {
        return getRootAsRandomOptions(byteBuffer, new RandomOptions());
    }

    public static RandomOptions getRootAsRandomOptions(ByteBuffer byteBuffer, RandomOptions randomOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return randomOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public RandomOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public long seed() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public long seed2() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public static int createRandomOptions(FlatBufferBuilder flatBufferBuilder, long j, long j2) {
        flatBufferBuilder.startTable(2);
        addSeed2(flatBufferBuilder, j2);
        addSeed(flatBufferBuilder, j);
        return endRandomOptions(flatBufferBuilder);
    }

    public static void startRandomOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addSeed(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(0, j, 0L);
    }

    public static void addSeed2(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(1, j, 0L);
    }

    public static int endRandomOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public RandomOptions get(int i) {
            return get(new RandomOptions(), i);
        }

        public RandomOptions get(RandomOptions randomOptions, int i) {
            return randomOptions.__assign(RandomOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
