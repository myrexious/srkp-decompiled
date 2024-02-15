package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class DynamicUpdateSliceOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static DynamicUpdateSliceOptions getRootAsDynamicUpdateSliceOptions(ByteBuffer byteBuffer) {
        return getRootAsDynamicUpdateSliceOptions(byteBuffer, new DynamicUpdateSliceOptions());
    }

    public static DynamicUpdateSliceOptions getRootAsDynamicUpdateSliceOptions(ByteBuffer byteBuffer, DynamicUpdateSliceOptions dynamicUpdateSliceOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return dynamicUpdateSliceOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public DynamicUpdateSliceOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startDynamicUpdateSliceOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endDynamicUpdateSliceOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public DynamicUpdateSliceOptions get(int i) {
            return get(new DynamicUpdateSliceOptions(), i);
        }

        public DynamicUpdateSliceOptions get(DynamicUpdateSliceOptions dynamicUpdateSliceOptions, int i) {
            return dynamicUpdateSliceOptions.__assign(DynamicUpdateSliceOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
