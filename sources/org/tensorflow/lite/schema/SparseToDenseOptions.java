package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SparseToDenseOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SparseToDenseOptions getRootAsSparseToDenseOptions(ByteBuffer byteBuffer) {
        return getRootAsSparseToDenseOptions(byteBuffer, new SparseToDenseOptions());
    }

    public static SparseToDenseOptions getRootAsSparseToDenseOptions(ByteBuffer byteBuffer, SparseToDenseOptions sparseToDenseOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return sparseToDenseOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SparseToDenseOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public boolean validateIndices() {
        int __offset = __offset(4);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createSparseToDenseOptions(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.startTable(1);
        addValidateIndices(flatBufferBuilder, z);
        return endSparseToDenseOptions(flatBufferBuilder);
    }

    public static void startSparseToDenseOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addValidateIndices(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(0, z, false);
    }

    public static int endSparseToDenseOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SparseToDenseOptions get(int i) {
            return get(new SparseToDenseOptions(), i);
        }

        public SparseToDenseOptions get(SparseToDenseOptions sparseToDenseOptions, int i) {
            return sparseToDenseOptions.__assign(SparseToDenseOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
