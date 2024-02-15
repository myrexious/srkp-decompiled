package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class HardSwishOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static HardSwishOptions getRootAsHardSwishOptions(ByteBuffer byteBuffer) {
        return getRootAsHardSwishOptions(byteBuffer, new HardSwishOptions());
    }

    public static HardSwishOptions getRootAsHardSwishOptions(ByteBuffer byteBuffer, HardSwishOptions hardSwishOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return hardSwishOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public HardSwishOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startHardSwishOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endHardSwishOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public HardSwishOptions get(int i) {
            return get(new HardSwishOptions(), i);
        }

        public HardSwishOptions get(HardSwishOptions hardSwishOptions, int i) {
            return hardSwishOptions.__assign(HardSwishOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
