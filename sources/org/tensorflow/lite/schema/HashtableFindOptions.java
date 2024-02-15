package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class HashtableFindOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static HashtableFindOptions getRootAsHashtableFindOptions(ByteBuffer byteBuffer) {
        return getRootAsHashtableFindOptions(byteBuffer, new HashtableFindOptions());
    }

    public static HashtableFindOptions getRootAsHashtableFindOptions(ByteBuffer byteBuffer, HashtableFindOptions hashtableFindOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return hashtableFindOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public HashtableFindOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startHashtableFindOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endHashtableFindOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public HashtableFindOptions get(int i) {
            return get(new HashtableFindOptions(), i);
        }

        public HashtableFindOptions get(HashtableFindOptions hashtableFindOptions, int i) {
            return hashtableFindOptions.__assign(HashtableFindOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
