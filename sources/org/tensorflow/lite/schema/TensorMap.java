package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes4.dex */
public final class TensorMap extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static TensorMap getRootAsTensorMap(ByteBuffer byteBuffer) {
        return getRootAsTensorMap(byteBuffer, new TensorMap());
    }

    public static TensorMap getRootAsTensorMap(ByteBuffer byteBuffer, TensorMap tensorMap) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return tensorMap.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public TensorMap __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String name() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer nameAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer nameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public long tensorIndex() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos) & BodyPartID.bodyIdMax;
        }
        return 0L;
    }

    public static int createTensorMap(FlatBufferBuilder flatBufferBuilder, int i, long j) {
        flatBufferBuilder.startTable(2);
        addTensorIndex(flatBufferBuilder, j);
        addName(flatBufferBuilder, i);
        return endTensorMap(flatBufferBuilder);
    }

    public static void startTensorMap(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addTensorIndex(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(1, (int) j, 0);
    }

    public static int endTensorMap(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public TensorMap get(int i) {
            return get(new TensorMap(), i);
        }

        public TensorMap get(TensorMap tensorMap, int i) {
            return tensorMap.__assign(TensorMap.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
