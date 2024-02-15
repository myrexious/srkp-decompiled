package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SelectOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SelectOptions getRootAsSelectOptions(ByteBuffer byteBuffer) {
        return getRootAsSelectOptions(byteBuffer, new SelectOptions());
    }

    public static SelectOptions getRootAsSelectOptions(ByteBuffer byteBuffer, SelectOptions selectOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return selectOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SelectOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startSelectOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endSelectOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SelectOptions get(int i) {
            return get(new SelectOptions(), i);
        }

        public SelectOptions get(SelectOptions selectOptions, int i) {
            return selectOptions.__assign(SelectOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
