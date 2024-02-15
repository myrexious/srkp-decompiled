package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class LogicalOrOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static LogicalOrOptions getRootAsLogicalOrOptions(ByteBuffer byteBuffer) {
        return getRootAsLogicalOrOptions(byteBuffer, new LogicalOrOptions());
    }

    public static LogicalOrOptions getRootAsLogicalOrOptions(ByteBuffer byteBuffer, LogicalOrOptions logicalOrOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return logicalOrOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public LogicalOrOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startLogicalOrOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endLogicalOrOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public LogicalOrOptions get(int i) {
            return get(new LogicalOrOptions(), i);
        }

        public LogicalOrOptions get(LogicalOrOptions logicalOrOptions, int i) {
            return logicalOrOptions.__assign(LogicalOrOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
