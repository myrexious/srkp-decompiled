package org.tensorflow.lite.support.image;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes4.dex */
class ImageConversions {
    public static Bitmap convertRgbTensorBufferToBitmap(TensorBuffer buffer) {
        int[] shape = buffer.getShape();
        ColorSpaceType colorSpaceType = ColorSpaceType.RGB;
        colorSpaceType.assertShape(shape);
        int height = colorSpaceType.getHeight(shape);
        int width = colorSpaceType.getWidth(shape);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, colorSpaceType.toBitmapConfig());
        int i = width * height;
        int[] iArr = new int[i];
        int[] intArray = buffer.getIntArray();
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            iArr[i2] = Color.rgb(intArray[i3], intArray[i4], intArray[i5]);
            i2++;
            i3 = i5 + 1;
        }
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    public static Bitmap convertGrayscaleTensorBufferToBitmap(TensorBuffer buffer) {
        if (buffer.getDataType() != DataType.UINT8) {
            buffer = TensorBuffer.createFrom(buffer, DataType.UINT8);
        }
        int[] shape = buffer.getShape();
        ColorSpaceType colorSpaceType = ColorSpaceType.GRAYSCALE;
        colorSpaceType.assertShape(shape);
        Bitmap createBitmap = Bitmap.createBitmap(colorSpaceType.getWidth(shape), colorSpaceType.getHeight(shape), colorSpaceType.toBitmapConfig());
        buffer.getBuffer().rewind();
        createBitmap.copyPixelsFromBuffer(buffer.getBuffer());
        return createBitmap;
    }

    public static void convertBitmapToTensorBuffer(Bitmap bitmap, TensorBuffer buffer) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int i2 = 0;
        int[] iArr2 = {height, width, 3};
        int i3 = AnonymousClass1.$SwitchMap$org$tensorflow$lite$DataType[buffer.getDataType().ordinal()];
        if (i3 != 1) {
            if (i3 == 2) {
                float[] fArr = new float[i * 3];
                int i4 = 0;
                while (i2 < i) {
                    int i5 = i4 + 1;
                    int i6 = iArr[i2];
                    fArr[i4] = (i6 >> 16) & 255;
                    int i7 = i5 + 1;
                    fArr[i5] = (i6 >> 8) & 255;
                    fArr[i7] = i6 & 255;
                    i2++;
                    i4 = i7 + 1;
                }
                buffer.loadArray(fArr, iArr2);
                return;
            }
            throw new IllegalStateException("The type of TensorBuffer, " + buffer.getBuffer() + ", is unsupported.");
        }
        byte[] bArr = new byte[i * 3];
        int i8 = 0;
        while (i2 < i) {
            int i9 = i8 + 1;
            int i10 = iArr[i2];
            bArr[i8] = (byte) ((i10 >> 16) & 255);
            int i11 = i9 + 1;
            bArr[i9] = (byte) ((i10 >> 8) & 255);
            bArr[i11] = (byte) (i10 & 255);
            i2++;
            i8 = i11 + 1;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.nativeOrder());
        buffer.loadBuffer(wrap, iArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.tensorflow.lite.support.image.ImageConversions$1 */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$tensorflow$lite$DataType;

        static {
            int[] iArr = new int[DataType.values().length];
            $SwitchMap$org$tensorflow$lite$DataType = iArr;
            try {
                iArr[DataType.UINT8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tensorflow$lite$DataType[DataType.FLOAT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private ImageConversions() {
    }
}
