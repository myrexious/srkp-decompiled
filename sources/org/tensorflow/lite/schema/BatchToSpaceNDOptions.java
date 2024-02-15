package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class BatchToSpaceNDOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static BatchToSpaceNDOptions getRootAsBatchToSpaceNDOptions(ByteBuffer byteBuffer) {
        return getRootAsBatchToSpaceNDOptions(byteBuffer, new BatchToSpaceNDOptions());
    }

    public static BatchToSpaceNDOptions getRootAsBatchToSpaceNDOptions(ByteBuffer byteBuffer, BatchToSpaceNDOptions batchToSpaceNDOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return batchToSpaceNDOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public BatchToSpaceNDOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startBatchToSpaceNDOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endBatchToSpaceNDOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public BatchToSpaceNDOptions get(int i) {
            return get(new BatchToSpaceNDOptions(), i);
        }

        public BatchToSpaceNDOptions get(BatchToSpaceNDOptions batchToSpaceNDOptions, int i) {
            return batchToSpaceNDOptions.__assign(BatchToSpaceNDOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
