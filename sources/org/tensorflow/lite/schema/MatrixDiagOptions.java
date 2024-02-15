package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class MatrixDiagOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static MatrixDiagOptions getRootAsMatrixDiagOptions(ByteBuffer byteBuffer) {
        return getRootAsMatrixDiagOptions(byteBuffer, new MatrixDiagOptions());
    }

    public static MatrixDiagOptions getRootAsMatrixDiagOptions(ByteBuffer byteBuffer, MatrixDiagOptions matrixDiagOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return matrixDiagOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public MatrixDiagOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startMatrixDiagOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endMatrixDiagOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public MatrixDiagOptions get(int i) {
            return get(new MatrixDiagOptions(), i);
        }

        public MatrixDiagOptions get(MatrixDiagOptions matrixDiagOptions, int i) {
            return matrixDiagOptions.__assign(MatrixDiagOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
