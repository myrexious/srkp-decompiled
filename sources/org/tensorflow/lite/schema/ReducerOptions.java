package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ReducerOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ReducerOptions getRootAsReducerOptions(ByteBuffer byteBuffer) {
        return getRootAsReducerOptions(byteBuffer, new ReducerOptions());
    }

    public static ReducerOptions getRootAsReducerOptions(ByteBuffer byteBuffer, ReducerOptions reducerOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return reducerOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ReducerOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public boolean keepDims() {
        int __offset = __offset(4);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createReducerOptions(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.startTable(1);
        addKeepDims(flatBufferBuilder, z);
        return endReducerOptions(flatBufferBuilder);
    }

    public static void startReducerOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addKeepDims(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(0, z, false);
    }

    public static int endReducerOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ReducerOptions get(int i) {
            return get(new ReducerOptions(), i);
        }

        public ReducerOptions get(ReducerOptions reducerOptions, int i) {
            return reducerOptions.__assign(ReducerOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
