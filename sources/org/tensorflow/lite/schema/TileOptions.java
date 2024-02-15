package org.tensorflow.lite.schema;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes4.dex */
public final class TileOptions extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static TileOptions getRootAsTileOptions(ByteBuffer byteBuffer) {
        return getRootAsTileOptions(byteBuffer, new TileOptions());
    }

    public static TileOptions getRootAsTileOptions(ByteBuffer byteBuffer, TileOptions tileOptions) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return tileOptions.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public TileOptions __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startTileOptions(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(0);
    }

    public static int endTileOptions(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    /* loaded from: classes4.dex */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public TileOptions get(int i) {
            return get(new TileOptions(), i);
        }

        public TileOptions get(TileOptions tileOptions, int i) {
            return tileOptions.__assign(TileOptions.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
