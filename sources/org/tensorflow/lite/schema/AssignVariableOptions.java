package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class AssignVariableOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static AssignVariableOptions getRootAsAssignVariableOptions(ByteBuffer byteBuffer) {
        return getRootAsAssignVariableOptions(byteBuffer, new AssignVariableOptions());
    }

    public static AssignVariableOptions getRootAsAssignVariableOptions(ByteBuffer byteBuffer, AssignVariableOptions assignVariableOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return assignVariableOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public AssignVariableOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startAssignVariableOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endAssignVariableOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public AssignVariableOptions get(int i) {
            return get(new AssignVariableOptions(), i);
        }

        public AssignVariableOptions get(AssignVariableOptions assignVariableOptions, int i) {
            return assignVariableOptions.__assign(AssignVariableOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
