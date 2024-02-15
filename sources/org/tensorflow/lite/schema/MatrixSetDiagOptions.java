package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class MatrixSetDiagOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static MatrixSetDiagOptions getRootAsMatrixSetDiagOptions(ByteBuffer byteBuffer) {
        return getRootAsMatrixSetDiagOptions(byteBuffer, new MatrixSetDiagOptions());
    }

    public static MatrixSetDiagOptions getRootAsMatrixSetDiagOptions(ByteBuffer byteBuffer, MatrixSetDiagOptions matrixSetDiagOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return matrixSetDiagOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public MatrixSetDiagOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startMatrixSetDiagOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endMatrixSetDiagOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public MatrixSetDiagOptions get(int i) {
            return get(new MatrixSetDiagOptions(), i);
        }

        public MatrixSetDiagOptions get(MatrixSetDiagOptions matrixSetDiagOptions, int i) {
            return matrixSetDiagOptions.__assign(MatrixSetDiagOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
