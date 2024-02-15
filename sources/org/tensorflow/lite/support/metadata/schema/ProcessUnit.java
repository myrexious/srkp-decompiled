package org.tensorflow.lite.support.metadata.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class ProcessUnit extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static ProcessUnit getRootAsProcessUnit(ByteBuffer byteBuffer) {
        return getRootAsProcessUnit(byteBuffer, new ProcessUnit());
    }

    public static ProcessUnit getRootAsProcessUnit(ByteBuffer byteBuffer, ProcessUnit processUnit) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return processUnit.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ProcessUnit __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte optionsType() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public Table options(Table table) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __union(table, __offset + this.bb_pos);
        }
        return null;
    }

    public static int createProcessUnit(FlatBufferBuilder flatBufferBuilder, byte b, int i) {
        flatBufferBuilder.startTable(2);
        addOptions(flatBufferBuilder, i);
        addOptionsType(flatBufferBuilder, b);
        return endProcessUnit(flatBufferBuilder);
    }

    public static void startProcessUnit(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addOptionsType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addOptions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int endProcessUnit(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public ProcessUnit get(int i) {
            return get(new ProcessUnit(), i);
        }

        public ProcessUnit get(ProcessUnit processUnit, int i) {
            return processUnit.__assign(ProcessUnit.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
