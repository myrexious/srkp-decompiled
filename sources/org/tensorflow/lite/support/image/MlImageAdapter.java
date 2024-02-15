package org.tensorflow.lite.support.image;

import com.google.android.odml.image.BitmapExtractor;
import com.google.android.odml.image.ByteBufferExtractor;
import com.google.android.odml.image.MediaImageExtractor;
import com.google.android.odml.image.MlImage;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public class MlImageAdapter {

    /* loaded from: classes4.dex */
    public static abstract class ImageFormatProxy {
        public abstract ColorSpaceType getColorSpaceType();

        public abstract int getImageFormat();

        static ImageFormatProxy createFromImageFormat(int format) {
            switch (format) {
                case 0:
                case 1:
                case 9:
                    throw new IllegalArgumentException("Cannot create ColorSpaceType from MlImage format: " + format);
                case 2:
                    return new AutoValue_MlImageAdapter_ImageFormatProxy(ColorSpaceType.RGB, format);
                case 3:
                    return new AutoValue_MlImageAdapter_ImageFormatProxy(ColorSpaceType.NV12, format);
                case 4:
                    return new AutoValue_MlImageAdapter_ImageFormatProxy(ColorSpaceType.NV21, format);
                case 5:
                    return new AutoValue_MlImageAdapter_ImageFormatProxy(ColorSpaceType.YV12, format);
                case 6:
                    return new AutoValue_MlImageAdapter_ImageFormatProxy(ColorSpaceType.YV21, format);
                case 7:
                    return new AutoValue_MlImageAdapter_ImageFormatProxy(ColorSpaceType.YUV_420_888, format);
                case 8:
                    return new AutoValue_MlImageAdapter_ImageFormatProxy(ColorSpaceType.GRAYSCALE, format);
                default:
                    throw new AssertionError("Illegal @ImageFormat: " + format);
            }
        }
    }

    public static TensorImage createTensorImageFrom(MlImage mlImage) {
        com.google.android.odml.image.ImageProperties imageProperties = mlImage.getContainedImageProperties().get(0);
        int storageType = imageProperties.getStorageType();
        if (storageType != 1) {
            if (storageType != 2) {
                if (storageType == 3) {
                    TensorImage tensorImage = new TensorImage();
                    tensorImage.load(MediaImageExtractor.extract(mlImage));
                    return tensorImage;
                }
                throw new IllegalArgumentException("Illegal storage type: " + imageProperties.getStorageType());
            }
            ByteBuffer extract = ByteBufferExtractor.extract(mlImage);
            ImageFormatProxy createFromImageFormat = ImageFormatProxy.createFromImageFormat(imageProperties.getImageFormat());
            TensorImage tensorImage2 = new TensorImage();
            tensorImage2.load(extract, ImageProperties.builder().setColorSpaceType(createFromImageFormat.getColorSpaceType()).setHeight(mlImage.getHeight()).setWidth(mlImage.getWidth()).build());
            return tensorImage2;
        }
        return TensorImage.fromBitmap(BitmapExtractor.extract(mlImage));
    }

    public static ColorSpaceType createColorSpaceTypeFrom(int imageFormat) {
        return ImageFormatProxy.createFromImageFormat(imageFormat).getColorSpaceType();
    }

    private MlImageAdapter() {
    }
}
