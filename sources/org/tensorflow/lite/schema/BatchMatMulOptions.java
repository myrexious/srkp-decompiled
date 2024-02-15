package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class BatchMatMulOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static BatchMatMulOptions getRootAsBatchMatMulOptions(ByteBuffer byteBuffer) {
        return getRootAsBatchMatMulOptions(byteBuffer, new BatchMatMulOptions());
    }

    public static BatchMatMulOptions getRootAsBatchMatMulOptions(ByteBuffer byteBuffer, BatchMatMulOptions batchMatMulOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return batchMatMulOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public BatchMatMulOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public boolean adjX() {
        int __offset = __offset(4);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean adjY() {
        int __offset = __offset(6);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean asymmetricQuantizeInputs() {
        int __offset = __offset(8);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createBatchMatMulOptions(FlatBufferBuilder flatBufferBuilder, boolean z, boolean z2, boolean z3) {
        flatBufferBuilder.startTable(3);
        addAsymmetricQuantizeInputs(flatBufferBuilder, z3);
        addAdjY(flatBufferBuilder, z2);
        addAdjX(flatBufferBuilder, z);
        return endBatchMatMulOptions(flatBufferBuilder);
    }

    public static void startBatchMatMulOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addAdjX(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(0, z, false);
    }

    public static void addAdjY(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(1, z, false);
    }

    public static void addAsymmetricQuantizeInputs(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(2, z, false);
    }

    public static int endBatchMatMulOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public BatchMatMulOptions get(int i) {
            return get(new BatchMatMulOptions(), i);
        }

        public BatchMatMulOptions get(BatchMatMulOptions batchMatMulOptions, int i) {
            return batchMatMulOptions.__assign(BatchMatMulOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
