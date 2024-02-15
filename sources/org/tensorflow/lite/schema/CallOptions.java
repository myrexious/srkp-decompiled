package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes4.dex */
public final class CallOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static CallOptions getRootAsCallOptions(ByteBuffer byteBuffer) {
        return getRootAsCallOptions(byteBuffer, new CallOptions());
    }

    public static CallOptions getRootAsCallOptions(ByteBuffer byteBuffer, CallOptions callOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return callOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public CallOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public long subgraph() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos) & BodyPartID.bodyIdMax;
        }
        return 0L;
    }

    public static int createCallOptions(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.startTable(1);
        addSubgraph(flatBufferBuilder, j);
        return endCallOptions(flatBufferBuilder);
    }

    public static void startCallOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addSubgraph(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(0, (int) j, 0);
    }

    public static int endCallOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public CallOptions get(int i) {
            return get(new CallOptions(), i);
        }

        public CallOptions get(CallOptions callOptions, int i) {
            return callOptions.__assign(CallOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
