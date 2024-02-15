package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class CumsumOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static CumsumOptions getRootAsCumsumOptions(ByteBuffer byteBuffer) {
        return getRootAsCumsumOptions(byteBuffer, new CumsumOptions());
    }

    public static CumsumOptions getRootAsCumsumOptions(ByteBuffer byteBuffer, CumsumOptions cumsumOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return cumsumOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public CumsumOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public boolean exclusive() {
        int __offset = __offset(4);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean reverse() {
        int __offset = __offset(6);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createCumsumOptions(FlatBufferBuilder flatBufferBuilder, boolean z, boolean z2) {
        flatBufferBuilder.startTable(2);
        addReverse(flatBufferBuilder, z2);
        addExclusive(flatBufferBuilder, z);
        return endCumsumOptions(flatBufferBuilder);
    }

    public static void startCumsumOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addExclusive(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(0, z, false);
    }

    public static void addReverse(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(1, z, false);
    }

    public static int endCumsumOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public CumsumOptions get(int i) {
            return get(new CumsumOptions(), i);
        }

        public CumsumOptions get(CumsumOptions cumsumOptions, int i) {
            return cumsumOptions.__assign(CumsumOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
