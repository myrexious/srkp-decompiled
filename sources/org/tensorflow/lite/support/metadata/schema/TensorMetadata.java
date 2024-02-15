package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.tensorflow.lite.support.metadata.schema.AssociatedFile;
import org.tensorflow.lite.support.metadata.schema.ProcessUnit;

/* loaded from: classes4.dex */
public final class TensorMetadata extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static TensorMetadata getRootAsTensorMetadata(ByteBuffer byteBuffer) {
        return getRootAsTensorMetadata(byteBuffer, new TensorMetadata());
    }

    public static TensorMetadata getRootAsTensorMetadata(ByteBuffer byteBuffer, TensorMetadata tensorMetadata) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return tensorMetadata.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public TensorMetadata __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String name() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer nameAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer nameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public String description() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer descriptionAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public ByteBuffer descriptionInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public String dimensionNames(int i) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int dimensionNamesLength() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public StringVector dimensionNamesVector() {
        return dimensionNamesVector(new StringVector());
    }

    public StringVector dimensionNamesVector(StringVector stringVector) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public Content content() {
        return content(new Content());
    }

    public Content content(Content content) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return content.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public ProcessUnit processUnits(int i) {
        return processUnits(new ProcessUnit(), i);
    }

    public ProcessUnit processUnits(ProcessUnit processUnit, int i) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return processUnit.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int processUnitsLength() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ProcessUnit.Vector processUnitsVector() {
        return processUnitsVector(new ProcessUnit.Vector());
    }

    public ProcessUnit.Vector processUnitsVector(ProcessUnit.Vector vector) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public Stats stats() {
        return stats(new Stats());
    }

    public Stats stats(Stats stats) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return stats.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public AssociatedFile associatedFiles(int i) {
        return associatedFiles(new AssociatedFile(), i);
    }

    public AssociatedFile associatedFiles(AssociatedFile associatedFile, int i) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return associatedFile.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int associatedFilesLength() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public AssociatedFile.Vector associatedFilesVector() {
        return associatedFilesVector(new AssociatedFile.Vector());
    }

    public AssociatedFile.Vector associatedFilesVector(AssociatedFile.Vector vector) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public static int createTensorMetadata(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        flatBufferBuilder.startTable(7);
        addAssociatedFiles(flatBufferBuilder, i7);
        addStats(flatBufferBuilder, i6);
        addProcessUnits(flatBufferBuilder, i5);
        addContent(flatBufferBuilder, i4);
        addDimensionNames(flatBufferBuilder, i3);
        addDescription(flatBufferBuilder, i2);
        addName(flatBufferBuilder, i);
        return endTensorMetadata(flatBufferBuilder);
    }

    public static void startTensorMetadata(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(7);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addDescription(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addDimensionNames(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static int createDimensionNamesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startDimensionNamesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addContent(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void addProcessUnits(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static int createProcessUnitsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startProcessUnitsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addStats(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void addAssociatedFiles(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static int createAssociatedFilesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startAssociatedFilesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endTensorMetadata(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public TensorMetadata get(int i) {
            return get(new TensorMetadata(), i);
        }

        public TensorMetadata get(TensorMetadata tensorMetadata, int i) {
            return tensorMetadata.__assign(TensorMetadata.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
