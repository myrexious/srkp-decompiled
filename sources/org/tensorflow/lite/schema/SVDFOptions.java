package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SVDFOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SVDFOptions getRootAsSVDFOptions(ByteBuffer byteBuffer) {
        return getRootAsSVDFOptions(byteBuffer, new SVDFOptions());
    }

    public static SVDFOptions getRootAsSVDFOptions(ByteBuffer byteBuffer, SVDFOptions sVDFOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return sVDFOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SVDFOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int rank() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public byte fusedActivationFunction() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public boolean asymmetricQuantizeInputs() {
        int __offset = __offset(8);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createSVDFOptions(FlatBufferBuilder flatBufferBuilder, int i, byte b, boolean z) {
        flatBufferBuilder.startTable(3);
        addRank(flatBufferBuilder, i);
        addAsymmetricQuantizeInputs(flatBufferBuilder, z);
        addFusedActivationFunction(flatBufferBuilder, b);
        return endSVDFOptions(flatBufferBuilder);
    }

    public static void startSVDFOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addRank(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addFusedActivationFunction(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(1, b, 0);
    }

    public static void addAsymmetricQuantizeInputs(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(2, z, false);
    }

    public static int endSVDFOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SVDFOptions get(int i) {
            return get(new SVDFOptions(), i);
        }

        public SVDFOptions get(SVDFOptions sVDFOptions, int i) {
            return sVDFOptions.__assign(SVDFOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
