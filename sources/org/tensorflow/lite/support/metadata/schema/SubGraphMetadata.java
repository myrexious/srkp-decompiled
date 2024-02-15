package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.tensorflow.lite.support.metadata.schema.AssociatedFile;
import org.tensorflow.lite.support.metadata.schema.ProcessUnit;
import org.tensorflow.lite.support.metadata.schema.TensorGroup;
import org.tensorflow.lite.support.metadata.schema.TensorMetadata;

/* loaded from: classes4.dex */
public final class SubGraphMetadata extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static SubGraphMetadata getRootAsSubGraphMetadata(ByteBuffer byteBuffer) {
        return getRootAsSubGraphMetadata(byteBuffer, new SubGraphMetadata());
    }

    public static SubGraphMetadata getRootAsSubGraphMetadata(ByteBuffer byteBuffer, SubGraphMetadata subGraphMetadata) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return subGraphMetadata.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SubGraphMetadata __assign(int i, ByteBuffer byteBuffer) {
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

    public TensorMetadata inputTensorMetadata(int i) {
        return inputTensorMetadata(new TensorMetadata(), i);
    }

    public TensorMetadata inputTensorMetadata(TensorMetadata tensorMetadata, int i) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return tensorMetadata.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int inputTensorMetadataLength() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public TensorMetadata.Vector inputTensorMetadataVector() {
        return inputTensorMetadataVector(new TensorMetadata.Vector());
    }

    public TensorMetadata.Vector inputTensorMetadataVector(TensorMetadata.Vector vector) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public TensorMetadata outputTensorMetadata(int i) {
        return outputTensorMetadata(new TensorMetadata(), i);
    }

    public TensorMetadata outputTensorMetadata(TensorMetadata tensorMetadata, int i) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return tensorMetadata.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int outputTensorMetadataLength() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public TensorMetadata.Vector outputTensorMetadataVector() {
        return outputTensorMetadataVector(new TensorMetadata.Vector());
    }

    public TensorMetadata.Vector outputTensorMetadataVector(TensorMetadata.Vector vector) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public AssociatedFile associatedFiles(int i) {
        return associatedFiles(new AssociatedFile(), i);
    }

    public AssociatedFile associatedFiles(AssociatedFile associatedFile, int i) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return associatedFile.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int associatedFilesLength() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public AssociatedFile.Vector associatedFilesVector() {
        return associatedFilesVector(new AssociatedFile.Vector());
    }

    public AssociatedFile.Vector associatedFilesVector(AssociatedFile.Vector vector) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public ProcessUnit inputProcessUnits(int i) {
        return inputProcessUnits(new ProcessUnit(), i);
    }

    public ProcessUnit inputProcessUnits(ProcessUnit processUnit, int i) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return processUnit.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int inputProcessUnitsLength() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ProcessUnit.Vector inputProcessUnitsVector() {
        return inputProcessUnitsVector(new ProcessUnit.Vector());
    }

    public ProcessUnit.Vector inputProcessUnitsVector(ProcessUnit.Vector vector) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public ProcessUnit outputProcessUnits(int i) {
        return outputProcessUnits(new ProcessUnit(), i);
    }

    public ProcessUnit outputProcessUnits(ProcessUnit processUnit, int i) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return processUnit.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int outputProcessUnitsLength() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ProcessUnit.Vector outputProcessUnitsVector() {
        return outputProcessUnitsVector(new ProcessUnit.Vector());
    }

    public ProcessUnit.Vector outputProcessUnitsVector(ProcessUnit.Vector vector) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public TensorGroup inputTensorGroups(int i) {
        return inputTensorGroups(new TensorGroup(), i);
    }

    public TensorGroup inputTensorGroups(TensorGroup tensorGroup, int i) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return tensorGroup.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int inputTensorGroupsLength() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public TensorGroup.Vector inputTensorGroupsVector() {
        return inputTensorGroupsVector(new TensorGroup.Vector());
    }

    public TensorGroup.Vector inputTensorGroupsVector(TensorGroup.Vector vector) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public TensorGroup outputTensorGroups(int i) {
        return outputTensorGroups(new TensorGroup(), i);
    }

    public TensorGroup outputTensorGroups(TensorGroup tensorGroup, int i) {
        int __offset = __offset(20);
        if (__offset != 0) {
            return tensorGroup.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int outputTensorGroupsLength() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public TensorGroup.Vector outputTensorGroupsVector() {
        return outputTensorGroupsVector(new TensorGroup.Vector());
    }

    public TensorGroup.Vector outputTensorGroupsVector(TensorGroup.Vector vector) {
        int __offset = __offset(20);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public static int createSubGraphMetadata(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        flatBufferBuilder.startTable(9);
        addOutputTensorGroups(flatBufferBuilder, i9);
        addInputTensorGroups(flatBufferBuilder, i8);
        addOutputProcessUnits(flatBufferBuilder, i7);
        addInputProcessUnits(flatBufferBuilder, i6);
        addAssociatedFiles(flatBufferBuilder, i5);
        addOutputTensorMetadata(flatBufferBuilder, i4);
        addInputTensorMetadata(flatBufferBuilder, i3);
        addDescription(flatBufferBuilder, i2);
        addName(flatBufferBuilder, i);
        return endSubGraphMetadata(flatBufferBuilder);
    }

    public static void startSubGraphMetadata(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(9);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addDescription(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addInputTensorMetadata(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static int createInputTensorMetadataVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startInputTensorMetadataVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addOutputTensorMetadata(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static int createOutputTensorMetadataVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startOutputTensorMetadataVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addAssociatedFiles(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
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

    public static void addInputProcessUnits(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static int createInputProcessUnitsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startInputProcessUnitsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addOutputProcessUnits(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static int createOutputProcessUnitsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startOutputProcessUnitsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addInputTensorGroups(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static int createInputTensorGroupsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startInputTensorGroupsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addOutputTensorGroups(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(8, i, 0);
    }

    public static int createOutputTensorGroupsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startOutputTensorGroupsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endSubGraphMetadata(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public SubGraphMetadata get(int i) {
            return get(new SubGraphMetadata(), i);
        }

        public SubGraphMetadata get(SubGraphMetadata subGraphMetadata, int i) {
            return subGraphMetadata.__assign(SubGraphMetadata.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
