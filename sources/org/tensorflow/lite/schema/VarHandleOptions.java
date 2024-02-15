package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class VarHandleOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static VarHandleOptions getRootAsVarHandleOptions(ByteBuffer byteBuffer) {
        return getRootAsVarHandleOptions(byteBuffer, new VarHandleOptions());
    }

    public static VarHandleOptions getRootAsVarHandleOptions(ByteBuffer byteBuffer, VarHandleOptions varHandleOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return varHandleOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public VarHandleOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String container() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer containerAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer containerInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public String sharedName() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer sharedNameAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public ByteBuffer sharedNameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public static int createVarHandleOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addSharedName(flatBufferBuilder, i2);
        addContainer(flatBufferBuilder, i);
        return endVarHandleOptions(flatBufferBuilder);
    }

    public static void startVarHandleOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addContainer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addSharedName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int endVarHandleOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public VarHandleOptions get(int i) {
            return get(new VarHandleOptions(), i);
        }

        public VarHandleOptions get(VarHandleOptions varHandleOptions, int i) {
            return varHandleOptions.__assign(VarHandleOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
