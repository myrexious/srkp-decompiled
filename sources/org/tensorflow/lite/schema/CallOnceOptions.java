package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class CallOnceOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static CallOnceOptions getRootAsCallOnceOptions(ByteBuffer byteBuffer) {
        return getRootAsCallOnceOptions(byteBuffer, new CallOnceOptions());
    }

    public static CallOnceOptions getRootAsCallOnceOptions(ByteBuffer byteBuffer, CallOnceOptions callOnceOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return callOnceOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public CallOnceOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int initSubgraphIndex() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public static int createCallOnceOptions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        addInitSubgraphIndex(flatBufferBuilder, i);
        return endCallOnceOptions(flatBufferBuilder);
    }

    public static void startCallOnceOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addInitSubgraphIndex(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static int endCallOnceOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public CallOnceOptions get(int i) {
            return get(new CallOnceOptions(), i);
        }

        public CallOnceOptions get(CallOnceOptions callOnceOptions, int i) {
            return callOnceOptions.__assign(CallOnceOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
