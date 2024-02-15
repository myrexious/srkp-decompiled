package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes4.dex */
public final class AudioProperties extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static AudioProperties getRootAsAudioProperties(ByteBuffer byteBuffer) {
        return getRootAsAudioProperties(byteBuffer, new AudioProperties());
    }

    public static AudioProperties getRootAsAudioProperties(ByteBuffer byteBuffer, AudioProperties audioProperties) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return audioProperties.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public AudioProperties __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public long sampleRate() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos) & BodyPartID.bodyIdMax;
        }
        return 0L;
    }

    public long channels() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos) & BodyPartID.bodyIdMax;
        }
        return 0L;
    }

    public static int createAudioProperties(FlatBufferBuilder flatBufferBuilder, long j, long j2) {
        flatBufferBuilder.startTable(2);
        addChannels(flatBufferBuilder, j2);
        addSampleRate(flatBufferBuilder, j);
        return endAudioProperties(flatBufferBuilder);
    }

    public static void startAudioProperties(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addSampleRate(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(0, (int) j, 0);
    }

    public static void addChannels(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(1, (int) j, 0);
    }

    public static int endAudioProperties(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public AudioProperties get(int i) {
            return get(new AudioProperties(), i);
        }

        public AudioProperties get(AudioProperties audioProperties, int i) {
            return audioProperties.__assign(AudioProperties.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
