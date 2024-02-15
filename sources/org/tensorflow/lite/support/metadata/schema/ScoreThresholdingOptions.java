package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ScoreThresholdingOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ScoreThresholdingOptions getRootAsScoreThresholdingOptions(ByteBuffer byteBuffer) {
        return getRootAsScoreThresholdingOptions(byteBuffer, new ScoreThresholdingOptions());
    }

    public static ScoreThresholdingOptions getRootAsScoreThresholdingOptions(ByteBuffer byteBuffer, ScoreThresholdingOptions scoreThresholdingOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return scoreThresholdingOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ScoreThresholdingOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public float globalScoreThreshold() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public static int createScoreThresholdingOptions(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.startTable(1);
        addGlobalScoreThreshold(flatBufferBuilder, f);
        return endScoreThresholdingOptions(flatBufferBuilder);
    }

    public static void startScoreThresholdingOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(1);
    }

    public static void addGlobalScoreThreshold(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(0, f, 0.0d);
    }

    public static int endScoreThresholdingOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ScoreThresholdingOptions get(int i) {
            return get(new ScoreThresholdingOptions(), i);
        }

        public ScoreThresholdingOptions get(ScoreThresholdingOptions scoreThresholdingOptions, int i) {
            return scoreThresholdingOptions.__assign(ScoreThresholdingOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
