package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ScoreCalibrationOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ScoreCalibrationOptions getRootAsScoreCalibrationOptions(ByteBuffer byteBuffer) {
        return getRootAsScoreCalibrationOptions(byteBuffer, new ScoreCalibrationOptions());
    }

    public static ScoreCalibrationOptions getRootAsScoreCalibrationOptions(ByteBuffer byteBuffer, ScoreCalibrationOptions scoreCalibrationOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return scoreCalibrationOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ScoreCalibrationOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte scoreTransformation() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public float defaultScore() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public static int createScoreCalibrationOptions(FlatBufferBuilder flatBufferBuilder, byte b, float f) {
        flatBufferBuilder.startTable(2);
        addDefaultScore(flatBufferBuilder, f);
        addScoreTransformation(flatBufferBuilder, b);
        return endScoreCalibrationOptions(flatBufferBuilder);
    }

    public static void startScoreCalibrationOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addScoreTransformation(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addDefaultScore(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(1, f, 0.0d);
    }

    public static int endScoreCalibrationOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ScoreCalibrationOptions get(int i) {
            return get(new ScoreCalibrationOptions(), i);
        }

        public ScoreCalibrationOptions get(ScoreCalibrationOptions scoreCalibrationOptions, int i) {
            return scoreCalibrationOptions.__assign(ScoreCalibrationOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
