package com.tom_roush.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.filter.FilterFactory;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public final class JPEGFactory {
    private JPEGFactory() {
    }

    public static PDImageXObject createFromStream(PDDocument pDDocument, InputStream inputStream) throws IOException {
        return createFromByteArray(pDDocument, IOUtils.toByteArray(inputStream));
    }

    public static PDImageXObject createFromByteArray(PDDocument pDDocument, byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Dimensions retrieveDimensions = retrieveDimensions(byteArrayInputStream);
        return new PDImageXObject(pDDocument, byteArrayInputStream, COSName.DCT_DECODE, retrieveDimensions.width, retrieveDimensions.height, 8, PDDeviceRGB.INSTANCE);
    }

    /* loaded from: classes3.dex */
    public static class Dimensions {
        private int height;
        private int numComponents;
        private int width;

        private Dimensions() {
        }
    }

    private static Dimensions retrieveDimensions(ByteArrayInputStream byteArrayInputStream) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(byteArrayInputStream, null, options);
        byteArrayInputStream.reset();
        Dimensions dimensions = new Dimensions();
        dimensions.width = options.outWidth;
        dimensions.height = options.outHeight;
        return dimensions;
    }

    public static PDImageXObject createFromImage(PDDocument pDDocument, Bitmap bitmap) throws IOException {
        return createFromImage(pDDocument, bitmap, 0.75f);
    }

    public static PDImageXObject createFromImage(PDDocument pDDocument, Bitmap bitmap, float f) throws IOException {
        return createFromImage(pDDocument, bitmap, f, 72);
    }

    public static PDImageXObject createFromImage(PDDocument pDDocument, Bitmap bitmap, float f, int i) throws IOException {
        return createJPEG(pDDocument, bitmap, f, i);
    }

    private static Bitmap getAlphaImage(Bitmap bitmap) {
        if (bitmap.hasAlpha()) {
            return bitmap.extractAlpha();
        }
        return null;
    }

    private static PDImageXObject createJPEG(PDDocument pDDocument, Bitmap bitmap, float f, int i) throws IOException {
        PDImageXObject pDImageXObject = new PDImageXObject(pDDocument, new ByteArrayInputStream(encodeImageToJPEGStream(bitmap, f, i)), COSName.DCT_DECODE, bitmap.getWidth(), bitmap.getHeight(), 8, PDDeviceRGB.INSTANCE);
        if (bitmap.hasAlpha()) {
            pDImageXObject.getCOSObject().setItem(COSName.SMASK, createAlphaFromARGBImage(pDDocument, bitmap));
        }
        return pDImageXObject;
    }

    private static PDImageXObject createAlphaFromARGBImage(PDDocument pDDocument, Bitmap bitmap) throws IOException {
        if (bitmap.hasAlpha()) {
            int height = bitmap.getHeight() * bitmap.getWidth();
            int[] iArr = new int[height];
            bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = 0; i < height; i++) {
                byteArrayOutputStream.write(Color.alpha(iArr[i]));
            }
            return prepareImageXObject(pDDocument, byteArrayOutputStream.toByteArray(), bitmap.getWidth(), bitmap.getHeight(), 8, PDDeviceGray.INSTANCE);
        }
        return null;
    }

    private static PDImageXObject prepareImageXObject(PDDocument pDDocument, byte[] bArr, int i, int i2, int i3, PDColorSpace pDColorSpace) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FilterFactory.INSTANCE.getFilter(COSName.FLATE_DECODE).encode(new ByteArrayInputStream(bArr), byteArrayOutputStream, new COSDictionary(), 0);
        return new PDImageXObject(pDDocument, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.FLATE_DECODE, i, i2, i3, pDColorSpace);
    }

    private static byte[] encodeImageToJPEGStream(Bitmap bitmap, float f, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, (int) (f * 100.0f), byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static Bitmap getColorImage(Bitmap bitmap) {
        if (bitmap.hasAlpha()) {
            if (!bitmap.getConfig().name().contains("RGB")) {
                throw new UnsupportedOperationException("only RGB color spaces are implemented");
            }
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setAlpha(0);
            new Canvas(createBitmap).drawBitmap(bitmap, 0.0f, 0.0f, paint);
            return createBitmap;
        }
        return bitmap;
    }
}
