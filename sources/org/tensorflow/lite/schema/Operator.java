package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.BooleanVector;
import com.google.flatbuffers.ByteVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UByte;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes4.dex */
public final class Operator extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static Operator getRootAsOperator(ByteBuffer byteBuffer) {
        return getRootAsOperator(byteBuffer, new Operator());
    }

    public static Operator getRootAsOperator(ByteBuffer byteBuffer, Operator operator) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return operator.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Operator __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public long opcodeIndex() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos) & BodyPartID.bodyIdMax;
        }
        return 0L;
    }

    public int inputs(int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int inputsLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector inputsVector() {
        return inputsVector(new IntVector());
    }

    public IntVector inputsVector(IntVector intVector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer inputsAsByteBuffer() {
        return __vector_as_bytebuffer(6, 4);
    }

    public ByteBuffer inputsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 4);
    }

    public int outputs(int i) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int outputsLength() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector outputsVector() {
        return outputsVector(new IntVector());
    }

    public IntVector outputsVector(IntVector intVector) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer outputsAsByteBuffer() {
        return __vector_as_bytebuffer(8, 4);
    }

    public ByteBuffer outputsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 4);
    }

    public byte builtinOptionsType() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public Table builtinOptions(Table table) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __union(table, __offset + this.bb_pos);
        }
        return null;
    }

    public int customOptions(int i) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.bb.get(__vector(__offset) + (i * 1)) & UByte.MAX_VALUE;
        }
        return 0;
    }

    public int customOptionsLength() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ByteVector customOptionsVector() {
        return customOptionsVector(new ByteVector());
    }

    public ByteVector customOptionsVector(ByteVector byteVector) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return byteVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer customOptionsAsByteBuffer() {
        return __vector_as_bytebuffer(14, 1);
    }

    public ByteBuffer customOptionsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 14, 1);
    }

    public byte customOptionsFormat() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public boolean mutatingVariableInputs(int i) {
        int __offset = __offset(18);
        return (__offset == 0 || this.bb.get(__vector(__offset) + (i * 1)) == 0) ? false : true;
    }

    public int mutatingVariableInputsLength() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public BooleanVector mutatingVariableInputsVector() {
        return mutatingVariableInputsVector(new BooleanVector());
    }

    public BooleanVector mutatingVariableInputsVector(BooleanVector booleanVector) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return booleanVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer mutatingVariableInputsAsByteBuffer() {
        return __vector_as_bytebuffer(18, 1);
    }

    public ByteBuffer mutatingVariableInputsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 18, 1);
    }

    public int intermediates(int i) {
        int __offset = __offset(20);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public int intermediatesLength() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IntVector intermediatesVector() {
        return intermediatesVector(new IntVector());
    }

    public IntVector intermediatesVector(IntVector intVector) {
        int __offset = __offset(20);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteBuffer intermediatesAsByteBuffer() {
        return __vector_as_bytebuffer(20, 4);
    }

    public ByteBuffer intermediatesInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 20, 4);
    }

    public static int createOperator(FlatBufferBuilder flatBufferBuilder, long j, int i, int i2, byte b, int i3, int i4, byte b2, int i5, int i6) {
        flatBufferBuilder.startTable(9);
        addIntermediates(flatBufferBuilder, i6);
        addMutatingVariableInputs(flatBufferBuilder, i5);
        addCustomOptions(flatBufferBuilder, i4);
        addBuiltinOptions(flatBufferBuilder, i3);
        addOutputs(flatBufferBuilder, i2);
        addInputs(flatBufferBuilder, i);
        addOpcodeIndex(flatBufferBuilder, j);
        addCustomOptionsFormat(flatBufferBuilder, b2);
        addBuiltinOptionsType(flatBufferBuilder, b);
        return endOperator(flatBufferBuilder);
    }

    public static void startOperator(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(9);
    }

    public static void addOpcodeIndex(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(0, (int) j, 0);
    }

    public static void addInputs(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int createInputsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startInputsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addOutputs(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static int createOutputsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startOutputsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addBuiltinOptionsType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(3, b, 0);
    }

    public static void addBuiltinOptions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static void addCustomOptions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static int createCustomOptionsVector(FlatBufferBuilder flatBufferBuilder, byte[] bArr) {
        return flatBufferBuilder.createByteVector(bArr);
    }

    public static int createCustomOptionsVector(FlatBufferBuilder flatBufferBuilder, ByteBuffer byteBuffer) {
        return flatBufferBuilder.createByteVector(byteBuffer);
    }

    public static void startCustomOptionsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(1, i, 1);
    }

    public static void addCustomOptionsFormat(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(6, b, 0);
    }

    public static void addMutatingVariableInputs(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static int createMutatingVariableInputsVector(FlatBufferBuilder flatBufferBuilder, boolean[] zArr) {
        flatBufferBuilder.startVector(1, zArr.length, 1);
        for (int length = zArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addBoolean(zArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startMutatingVariableInputsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(1, i, 1);
    }

    public static void addIntermediates(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(8, i, 0);
    }

    public static int createIntermediatesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startIntermediatesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endOperator(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Operator get(int i) {
            return get(new Operator(), i);
        }

        public Operator get(Operator operator, int i) {
            return operator.__assign(Operator.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
