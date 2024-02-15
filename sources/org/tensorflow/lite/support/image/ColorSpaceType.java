package org.tensorflow.lite.support.image;

import android.graphics.Bitmap;
import java.util.Arrays;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes4.dex */
public enum ColorSpaceType {
    RGB(0) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.1
        private static final int CHANNEL_VALUE = 3;

        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int getChannelValue() {
            return 3;
        }

        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int getNumElements(int height, int width) {
            return height * width * 3;
        }

        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        String getShapeInfoMessage() {
            return "The shape of a RGB image should be (h, w, c) or (1, h, w, c), and channels representing R, G, B in order. ";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        public Bitmap convertTensorBufferToBitmap(TensorBuffer buffer) {
            return ImageConversions.convertRgbTensorBufferToBitmap(buffer);
        }

        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int[] getNormalizedShape(int[] shape) {
            int length = shape.length;
            if (length != 3) {
                if (length == 4) {
                    return shape;
                }
                throw new IllegalArgumentException(getShapeInfoMessage() + "The provided image shape is " + Arrays.toString(shape));
            }
            return ColorSpaceType.insertValue(shape, 0, 1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        public Bitmap.Config toBitmapConfig() {
            return Bitmap.Config.ARGB_8888;
        }
    },
    GRAYSCALE(1) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.2
        private static final int CHANNEL_VALUE = 1;

        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int getChannelValue() {
            return 1;
        }

        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int getNumElements(int height, int width) {
            return height * width;
        }

        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        String getShapeInfoMessage() {
            return "The shape of a grayscale image should be (h, w) or (1, h, w, 1). ";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        public Bitmap convertTensorBufferToBitmap(TensorBuffer buffer) {
            return ImageConversions.convertGrayscaleTensorBufferToBitmap(buffer);
        }

        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int[] getNormalizedShape(int[] shape) {
            int length = shape.length;
            if (length != 2) {
                if (length == 4) {
                    return shape;
                }
                throw new IllegalArgumentException(getShapeInfoMessage() + "The provided image shape is " + Arrays.toString(shape));
            }
            return ColorSpaceType.insertValue(ColorSpaceType.insertValue(shape, 0, 1), 3, 1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        public Bitmap.Config toBitmapConfig() {
            return Bitmap.Config.ALPHA_8;
        }
    },
    NV12(2) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.3
        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int getNumElements(int height, int width) {
            return ColorSpaceType.getYuv420NumElements(height, width);
        }
    },
    NV21(3) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.4
        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int getNumElements(int height, int width) {
            return ColorSpaceType.getYuv420NumElements(height, width);
        }
    },
    YV12(4) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.5
        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int getNumElements(int height, int width) {
            return ColorSpaceType.getYuv420NumElements(height, width);
        }
    },
    YV21(5) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.6
        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int getNumElements(int height, int width) {
            return ColorSpaceType.getYuv420NumElements(height, width);
        }
    },
    YUV_420_888(6) { // from class: org.tensorflow.lite.support.image.ColorSpaceType.7
        @Override // org.tensorflow.lite.support.image.ColorSpaceType
        int getNumElements(int height, int width) {
            return ColorSpaceType.getYuv420NumElements(height, width);
        }
    };
    
    private static final int BATCH_DIM = 0;
    private static final int BATCH_VALUE = 1;
    private static final int CHANNEL_DIM = 3;
    private static final int HEIGHT_DIM = 1;
    private static final int WIDTH_DIM = 2;
    private final int value;

    abstract int getNumElements(int height, int width);

    ColorSpaceType(int value) {
        this.value = value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.tensorflow.lite.support.image.ColorSpaceType$8 */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            $SwitchMap$android$graphics$Bitmap$Config = iArr;
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static ColorSpaceType fromBitmapConfig(Bitmap.Config config) {
        int i = AnonymousClass8.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return GRAYSCALE;
            }
            throw new IllegalArgumentException("Bitmap configuration: " + config + ", is not supported yet.");
        }
        return RGB;
    }

    public static ColorSpaceType fromImageFormat(int imageFormat) {
        if (imageFormat != 17) {
            if (imageFormat != 35) {
                if (imageFormat == 842094169) {
                    return YV12;
                }
                throw new IllegalArgumentException("ImageFormat: " + imageFormat + ", is not supported yet.");
            }
            return YUV_420_888;
        }
        return NV21;
    }

    public int getValue() {
        return this.value;
    }

    public void assertShape(int[] shape) {
        assertRgbOrGrayScale("assertShape()");
        SupportPreconditions.checkArgument(isValidNormalizedShape(getNormalizedShape(shape)), getShapeInfoMessage() + "The provided image shape is " + Arrays.toString(shape));
    }

    public void assertNumElements(int numElements, int height, int width) {
        SupportPreconditions.checkArgument(numElements >= getNumElements(height, width), String.format("The given number of elements (%d) does not match the image (%s) in %d x %d. The expected number of elements should be at least %d.", Integer.valueOf(numElements), name(), Integer.valueOf(height), Integer.valueOf(width), Integer.valueOf(getNumElements(height, width))));
    }

    public Bitmap convertTensorBufferToBitmap(TensorBuffer buffer) {
        throw new UnsupportedOperationException("convertTensorBufferToBitmap() is unsupported for the color space type " + name());
    }

    public int getWidth(int[] shape) {
        assertRgbOrGrayScale("getWidth()");
        assertShape(shape);
        return getNormalizedShape(shape)[2];
    }

    public int getHeight(int[] shape) {
        assertRgbOrGrayScale("getHeight()");
        assertShape(shape);
        return getNormalizedShape(shape)[1];
    }

    int getChannelValue() {
        throw new UnsupportedOperationException("getChannelValue() is unsupported for the color space type " + name());
    }

    int[] getNormalizedShape(int[] shape) {
        throw new UnsupportedOperationException("getNormalizedShape() is unsupported for the color space type " + name());
    }

    String getShapeInfoMessage() {
        throw new UnsupportedOperationException("getShapeInfoMessage() is unsupported for the color space type " + name());
    }

    public Bitmap.Config toBitmapConfig() {
        throw new UnsupportedOperationException("toBitmapConfig() is unsupported for the color space type " + name());
    }

    public static int getYuv420NumElements(int height, int width) {
        return (height * width) + (((height + 1) / 2) * ((width + 1) / 2) * 2);
    }

    public static int[] insertValue(int[] array, int pos, int value) {
        int length = array.length + 1;
        int[] iArr = new int[length];
        for (int i = 0; i < pos; i++) {
            iArr[i] = array[i];
        }
        iArr[pos] = value;
        while (true) {
            pos++;
            if (pos >= length) {
                return iArr;
            }
            iArr[pos] = array[pos - 1];
        }
    }

    protected boolean isValidNormalizedShape(int[] shape) {
        return shape[0] == 1 && shape[1] > 0 && shape[2] > 0 && shape[3] == getChannelValue();
    }

    private void assertRgbOrGrayScale(String unsupportedMethodName) {
        if (this != RGB && this != GRAYSCALE) {
            throw new UnsupportedOperationException(unsupportedMethodName + " only supports RGB and GRAYSCALE formats, but not " + name());
        }
    }
}
