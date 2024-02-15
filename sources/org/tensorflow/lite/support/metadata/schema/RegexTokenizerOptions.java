package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.tensorflow.lite.support.metadata.schema.AssociatedFile;

/* loaded from: classes4.dex */
public final class RegexTokenizerOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static RegexTokenizerOptions getRootAsRegexTokenizerOptions(ByteBuffer byteBuffer) {
        return getRootAsRegexTokenizerOptions(byteBuffer, new RegexTokenizerOptions());
    }

    public static RegexTokenizerOptions getRootAsRegexTokenizerOptions(ByteBuffer byteBuffer, RegexTokenizerOptions regexTokenizerOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return regexTokenizerOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public RegexTokenizerOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String delimRegexPattern() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer delimRegexPatternAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer delimRegexPatternInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public AssociatedFile vocabFile(int i) {
        return vocabFile(new AssociatedFile(), i);
    }

    public AssociatedFile vocabFile(AssociatedFile associatedFile, int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return associatedFile.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int vocabFileLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public AssociatedFile.Vector vocabFileVector() {
        return vocabFileVector(new AssociatedFile.Vector());
    }

    public AssociatedFile.Vector vocabFileVector(AssociatedFile.Vector vector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public static int createRegexTokenizerOptions(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addVocabFile(flatBufferBuilder, i2);
        addDelimRegexPattern(flatBufferBuilder, i);
        return endRegexTokenizerOptions(flatBufferBuilder);
    }

    public static void startRegexTokenizerOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addDelimRegexPattern(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addVocabFile(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int createVocabFileVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startVocabFileVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endRegexTokenizerOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public RegexTokenizerOptions get(int i) {
            return get(new RegexTokenizerOptions(), i);
        }

        public RegexTokenizerOptions get(RegexTokenizerOptions regexTokenizerOptions, int i) {
            return regexTokenizerOptions.__assign(RegexTokenizerOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
