package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ExpandDimsOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ExpandDimsOptions getRootAsExpandDimsOptions(ByteBuffer byteBuffer) {
        return getRootAsExpandDimsOptions(byteBuffer, new ExpandDimsOptions());
    }

    public static ExpandDimsOptions getRootAsExpandDimsOptions(ByteBuffer byteBuffer, ExpandDimsOptions expandDimsOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return expandDimsOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ExpandDimsOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startExpandDimsOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endExpandDimsOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ExpandDimsOptions get(int i) {
            return get(new ExpandDimsOptions(), i);
        }

        public ExpandDimsOptions get(ExpandDimsOptions expandDimsOptions, int i) {
            return expandDimsOptions.__assign(ExpandDimsOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
