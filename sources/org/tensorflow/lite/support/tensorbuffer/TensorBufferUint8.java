package org.tensorflow.lite.support.tensorbuffer;

import kotlin.UByte;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes4.dex */
public final class TensorBufferUint8 extends TensorBuffer {
    private static final DataType DATA_TYPE = DataType.UINT8;

    public TensorBufferUint8(int[] shape) {
        super(shape);
    }

    public TensorBufferUint8() {
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public DataType getDataType() {
        return DATA_TYPE;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public float[] getFloatArray() {
        this.buffer.rewind();
        byte[] bArr = new byte[this.flatSize];
        this.buffer.get(bArr);
        float[] fArr = new float[this.flatSize];
        for (int i = 0; i < this.flatSize; i++) {
            fArr[i] = bArr[i] & UByte.MAX_VALUE;
        }
        return fArr;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public float getFloatValue(int index) {
        return this.buffer.get(index) & UByte.MAX_VALUE;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public int[] getIntArray() {
        this.buffer.rewind();
        byte[] bArr = new byte[this.flatSize];
        this.buffer.get(bArr);
        int[] iArr = new int[this.flatSize];
        for (int i = 0; i < this.flatSize; i++) {
            iArr[i] = bArr[i] & UByte.MAX_VALUE;
        }
        return iArr;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public int getIntValue(int index) {
        return this.buffer.get(index) & UByte.MAX_VALUE;
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public int getTypeSize() {
        return DATA_TYPE.byteSize();
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public void loadArray(float[] src, int[] shape) {
        SupportPreconditions.checkNotNull(src, "The array to be loaded cannot be null.");
        int i = 0;
        SupportPreconditions.checkArgument(src.length == computeFlatSize(shape), "The size of the array to be loaded does not match the specified shape.");
        copyByteBufferIfReadOnly();
        resize(shape);
        this.buffer.rewind();
        byte[] bArr = new byte[src.length];
        int length = src.length;
        int i2 = 0;
        while (i < length) {
            bArr[i2] = (byte) Math.max(Math.min(src[i], 255.0d), 0.0d);
            i++;
            i2++;
        }
        this.buffer.put(bArr);
    }

    @Override // org.tensorflow.lite.support.tensorbuffer.TensorBuffer
    public void loadArray(int[] src, int[] shape) {
        SupportPreconditions.checkNotNull(src, "The array to be loaded cannot be null.");
        int i = 0;
        SupportPreconditions.checkArgument(src.length == computeFlatSize(shape), "The size of the array to be loaded does not match the specified shape.");
        copyByteBufferIfReadOnly();
        resize(shape);
        this.buffer.rewind();
        byte[] bArr = new byte[src.length];
        int length = src.length;
        int i2 = 0;
        while (i < length) {
            bArr[i2] = (byte) Math.max(Math.min(src[i], 255.0f), 0.0f);
            i++;
            i2++;
        }
        this.buffer.put(bArr);
    }
}
