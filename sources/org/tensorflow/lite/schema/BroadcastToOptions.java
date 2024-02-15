package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class BroadcastToOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static BroadcastToOptions getRootAsBroadcastToOptions(ByteBuffer byteBuffer) {
        return getRootAsBroadcastToOptions(byteBuffer, new BroadcastToOptions());
    }

    public static BroadcastToOptions getRootAsBroadcastToOptions(ByteBuffer byteBuffer, BroadcastToOptions broadcastToOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return broadcastToOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public BroadcastToOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startBroadcastToOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endBroadcastToOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public BroadcastToOptions get(int i) {
            return get(new BroadcastToOptions(), i);
        }

        public BroadcastToOptions get(BroadcastToOptions broadcastToOptions, int i) {
            return broadcastToOptions.__assign(BroadcastToOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
