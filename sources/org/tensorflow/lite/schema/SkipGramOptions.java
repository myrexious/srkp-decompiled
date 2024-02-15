package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class SkipGramOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SkipGramOptions getRootAsSkipGramOptions(ByteBuffer byteBuffer) {
        return getRootAsSkipGramOptions(byteBuffer, new SkipGramOptions());
    }

    public static SkipGramOptions getRootAsSkipGramOptions(ByteBuffer byteBuffer, SkipGramOptions skipGramOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return skipGramOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SkipGramOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public int ngramSize() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int maxSkipSize() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public boolean includeAllNgrams() {
        int __offset = __offset(8);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public static int createSkipGramOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2, boolean z) {
        flatBufferBuilder.startTable(3);
        addMaxSkipSize(flatBufferBuilder, i2);
        addNgramSize(flatBufferBuilder, i);
        addIncludeAllNgrams(flatBufferBuilder, z);
        return endSkipGramOptions(flatBufferBuilder);
    }

    public static void startSkipGramOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static void addNgramSize(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addMaxSkipSize(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void addIncludeAllNgrams(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(2, z, false);
    }

    public static int endSkipGramOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SkipGramOptions get(int i) {
            return get(new SkipGramOptions(), i);
        }

        public SkipGramOptions get(SkipGramOptions skipGramOptions, int i) {
            return skipGramOptions.__assign(SkipGramOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
