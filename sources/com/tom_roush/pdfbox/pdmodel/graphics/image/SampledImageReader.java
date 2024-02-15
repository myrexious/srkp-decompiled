package com.tom_roush.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.filter.DecodeOptions;
import com.tom_roush.pdfbox.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: classes3.dex */
public final class SampledImageReader {
    private SampledImageReader() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x007e, code lost:
        android.util.Log.w("PdfBox-Android", "premature EOF, image will be incomplete");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap getStencilImage(com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage r17, android.graphics.Paint r18) throws java.io.IOException {
        /*
            int r0 = r17.getWidth()
            int r1 = r17.getHeight()
            android.graphics.Bitmap$Config r2 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r0, r1, r2)
            android.graphics.Canvas r3 = new android.graphics.Canvas
            r3.<init>(r2)
            r4 = 0
            r5 = 0
            float r6 = (float) r0
            float r7 = (float) r1
            r8 = r18
            r3.drawRect(r4, r5, r6, r7, r8)
            r3 = 0
            com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageInputStream r4 = new com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageInputStream     // Catch: java.lang.Throwable -> L93
            java.io.InputStream r5 = r17.createInputStream()     // Catch: java.lang.Throwable -> L93
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L93
            float[] r3 = getDecodeArray(r17)     // Catch: java.lang.Throwable -> L90
            r5 = 0
            r6 = r3[r5]     // Catch: java.lang.Throwable -> L90
            r7 = 1
            r3 = r3[r7]     // Catch: java.lang.Throwable -> L90
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 >= 0) goto L36
            r3 = r7
            goto L37
        L36:
            r3 = r5
        L37:
            int r6 = r0 / 8
            int r8 = r0 % 8
            if (r8 <= 0) goto L3f
            int r6 = r6 + 1
        L3f:
            byte[] r8 = new byte[r6]     // Catch: java.lang.Throwable -> L90
            r9 = r5
        L42:
            if (r9 >= r1) goto L8c
            int r10 = r4.read(r8)     // Catch: java.lang.Throwable -> L90
            r11 = r5
            r12 = r11
        L4a:
            if (r11 >= r6) goto L79
            if (r11 >= r10) goto L79
            r13 = r8[r11]     // Catch: java.lang.Throwable -> L90
            r14 = 128(0x80, float:1.794E-43)
            r15 = 7
        L53:
            r7 = 8
            if (r5 >= r7) goto L70
            r7 = r13 & r14
            int r7 = r7 >> r15
            r16 = 1
            int r14 = r14 >> 1
            int r15 = r15 + (-1)
            if (r7 != r3) goto L67
            r7 = 0
            r2.setPixel(r12, r9, r7)     // Catch: java.lang.Throwable -> L90
            goto L68
        L67:
            r7 = 0
        L68:
            int r12 = r12 + 1
            if (r12 != r0) goto L6d
            goto L73
        L6d:
            int r5 = r5 + 1
            goto L53
        L70:
            r7 = 0
            r16 = 1
        L73:
            int r11 = r11 + 1
            r5 = r7
            r7 = r16
            goto L4a
        L79:
            r16 = r7
            r7 = r5
            if (r10 == r6) goto L86
            java.lang.String r0 = "PdfBox-Android"
            java.lang.String r1 = "premature EOF, image will be incomplete"
            android.util.Log.w(r0, r1)     // Catch: java.lang.Throwable -> L90
            goto L8c
        L86:
            int r9 = r9 + 1
            r5 = r7
            r7 = r16
            goto L42
        L8c:
            r4.close()
            return r2
        L90:
            r0 = move-exception
            r3 = r4
            goto L94
        L93:
            r0 = move-exception
        L94:
            if (r3 == 0) goto L99
            r3.close()
        L99:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.graphics.image.SampledImageReader.getStencilImage(com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage, android.graphics.Paint):android.graphics.Bitmap");
    }

    public static Bitmap getRGBImage(PDImage pDImage, COSArray cOSArray) throws IOException {
        return getRGBImage(pDImage, null, 1, cOSArray);
    }

    private static Rect clipRegion(PDImage pDImage, Rect rect) {
        if (rect == null) {
            return new Rect(0, 0, pDImage.getWidth(), pDImage.getHeight());
        }
        int max = Math.max(0, rect.left);
        int max2 = Math.max(0, rect.top);
        return new Rect(max, max2, Math.min(rect.width(), pDImage.getWidth() - max), Math.min(rect.height(), pDImage.getHeight() - max2));
    }

    public static Bitmap getRGBImage(PDImage pDImage, Rect rect, int i, COSArray cOSArray) throws IOException {
        if (pDImage.isEmpty()) {
            throw new IOException("Image stream is empty");
        }
        Rect clipRegion = clipRegion(pDImage, rect);
        int numberOfComponents = pDImage.getColorSpace().getNumberOfComponents();
        int ceil = (int) Math.ceil(clipRegion.width() / i);
        int ceil2 = (int) Math.ceil(clipRegion.height() / i);
        int bitsPerComponent = pDImage.getBitsPerComponent();
        if (ceil <= 0 || ceil2 <= 0 || pDImage.getWidth() <= 0 || pDImage.getHeight() <= 0) {
            throw new IOException("image width and height must be positive");
        }
        try {
            if (bitsPerComponent == 1 && cOSArray == null && numberOfComponents == 1) {
                return from1Bit(pDImage, clipRegion, i, ceil, ceil2);
            }
            float[] defaultDecode = pDImage.getColorSpace().getDefaultDecode(8);
            float[] decodeArray = getDecodeArray(pDImage);
            if (pDImage.getSuffix() != null && pDImage.getSuffix().equals("jpg") && i == 1) {
                return BitmapFactory.decodeStream(pDImage.createInputStream());
            }
            if (bitsPerComponent == 8 && cOSArray == null && Arrays.equals(decodeArray, defaultDecode)) {
                return from8bit(pDImage, clipRegion, i, ceil, ceil2);
            }
            Log.e("PdfBox-Android", "Trying to create other-bit image not supported");
            return from8bit(pDImage, clipRegion, i, ceil, ceil2);
        } catch (NegativeArraySizeException e) {
            throw new IOException(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:99:0x00ad, code lost:
        r23[r16] = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Bitmap from1Bit(com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage r22, android.graphics.Rect r23, int r24, int r25, int r26) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.graphics.image.SampledImageReader.from1Bit(com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage, android.graphics.Rect, int, int, int):android.graphics.Bitmap");
    }

    private static Bitmap from8bit(PDImage pDImage, Rect rect, int i, int i2, int i3) throws IOException {
        int i4;
        int width;
        int height;
        int i5;
        int i6;
        int i7;
        DecodeOptions decodeOptions = new DecodeOptions(i);
        decodeOptions.setSourceRegion(rect);
        InputStream createInputStream = pDImage.createInputStream(decodeOptions);
        try {
            if (decodeOptions.isFilterSubsampled()) {
                i5 = 0;
                i4 = 0;
                i6 = i2;
                width = i6;
                height = i3;
                i7 = 1;
            } else {
                int width2 = pDImage.getWidth();
                int i8 = rect.left;
                i4 = rect.top;
                width = rect.width();
                height = rect.height();
                i5 = i8;
                i6 = width2;
                i7 = i;
            }
            int numberOfComponents = pDImage.getColorSpace().getNumberOfComponents();
            if (i5 == 0 && i4 == 0 && width == i2 && height == i3) {
                return createBitmapFromRawStream(createInputStream, i6, numberOfComponents, i7);
            }
            Bitmap createBitmapFromRawStream = createBitmapFromRawStream(createInputStream, i6, numberOfComponents, i7);
            if (i7 > 1) {
                i5 /= i7;
                i4 /= i7;
            }
            return Bitmap.createBitmap(createBitmapFromRawStream, i5, i4, i2, i3);
        } finally {
            IOUtils.closeQuietly(createInputStream);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Bitmap createBitmapFromRawStream(java.io.InputStream r7, int r8, int r9, int r10) throws java.io.IOException {
        /*
            byte[] r7 = com.tom_roush.pdfbox.io.IOUtils.toByteArray(r7)
            int r0 = r7.length
            int r0 = r0 / r9
            int r0 = r0 / r8
            r1 = 1
            if (r9 != r1) goto L30
            int r9 = r8 * r0
            int r2 = r9 * 4
            byte[] r2 = new byte[r2]
            int r9 = r9 - r1
        L11:
            if (r9 < 0) goto L2e
            int r3 = r9 * 4
            int r4 = r3 + 3
            r5 = r7[r9]
            r2[r4] = r5
            r4 = r7[r9]
            r2[r3] = r4
            int r4 = r3 + 1
            r5 = r7[r9]
            r2[r4] = r5
            int r3 = r3 + 2
            r4 = r7[r9]
            r2[r3] = r4
            int r9 = r9 + (-1)
            goto L11
        L2e:
            r7 = r2
            goto L5c
        L30:
            r2 = 3
            if (r9 != r2) goto L5c
            int r9 = r8 * r0
            int r2 = r9 * 4
            byte[] r2 = new byte[r2]
            int r9 = r9 - r1
        L3a:
            if (r9 < 0) goto L2e
            int r3 = r9 * 4
            int r4 = r9 * 3
            int r5 = r3 + 3
            r6 = -1
            r2[r5] = r6
            r5 = r7[r4]
            r2[r3] = r5
            int r5 = r3 + 1
            int r6 = r4 + 1
            r6 = r7[r6]
            r2[r5] = r6
            int r3 = r3 + 2
            int r4 = r4 + 2
            r4 = r7[r4]
            r2[r3] = r4
            int r9 = r9 + (-1)
            goto L3a
        L5c:
            android.graphics.Bitmap$Config r9 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r9 = android.graphics.Bitmap.createBitmap(r8, r0, r9)
            java.nio.ByteBuffer r7 = java.nio.ByteBuffer.wrap(r7)
            r9.copyPixelsFromBuffer(r7)
            if (r10 <= r1) goto L71
            int r8 = r8 / r10
            int r0 = r0 / r10
            android.graphics.Bitmap r9 = android.graphics.Bitmap.createScaledBitmap(r9, r8, r0, r1)
        L71:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.graphics.image.SampledImageReader.createBitmapFromRawStream(java.io.InputStream, int, int, int):android.graphics.Bitmap");
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a8 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static float[] getDecodeArray(com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage r11) throws java.io.IOException {
        /*
            com.tom_roush.pdfbox.cos.COSArray r0 = r11.getDecode()
            if (r0 == 0) goto L98
            com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace r1 = r11.getColorSpace()
            int r1 = r1.getNumberOfComponents()
            int r2 = r0.size()
            r3 = 2
            int r1 = r1 * r3
            if (r2 == r1) goto L93
            boolean r1 = r11.isStencil()
            java.lang.String r2 = "decode array "
            java.lang.String r4 = "PdfBox-Android"
            if (r1 == 0) goto L7c
            int r1 = r0.size()
            if (r1 < r3) goto L7c
            r1 = 0
            com.tom_roush.pdfbox.cos.COSBase r5 = r0.get(r1)
            boolean r5 = r5 instanceof com.tom_roush.pdfbox.cos.COSNumber
            if (r5 == 0) goto L7c
            r5 = 1
            com.tom_roush.pdfbox.cos.COSBase r6 = r0.get(r5)
            boolean r6 = r6 instanceof com.tom_roush.pdfbox.cos.COSNumber
            if (r6 == 0) goto L7c
            com.tom_roush.pdfbox.cos.COSBase r6 = r0.get(r1)
            com.tom_roush.pdfbox.cos.COSNumber r6 = (com.tom_roush.pdfbox.cos.COSNumber) r6
            float r6 = r6.floatValue()
            com.tom_roush.pdfbox.cos.COSBase r7 = r0.get(r5)
            com.tom_roush.pdfbox.cos.COSNumber r7 = (com.tom_roush.pdfbox.cos.COSNumber) r7
            float r7 = r7.floatValue()
            r8 = 0
            int r9 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r9 < 0) goto L7c
            r9 = 1065353216(0x3f800000, float:1.0)
            int r10 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r10 > 0) goto L7c
            int r8 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r8 < 0) goto L7c
            int r8 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r8 > 0) goto L7c
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r2)
            java.lang.StringBuilder r11 = r11.append(r0)
            java.lang.String r0 = " not compatible with color space, using the first two entries"
            java.lang.StringBuilder r11 = r11.append(r0)
            java.lang.String r11 = r11.toString()
            android.util.Log.w(r4, r11)
            float[] r11 = new float[r3]
            r11[r1] = r6
            r11[r5] = r7
            return r11
        L7c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = " not compatible with color space, using default"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r4, r0)
            goto L98
        L93:
            float[] r0 = r0.toFloatArray()
            goto L99
        L98:
            r0 = 0
        L99:
            if (r0 != 0) goto La8
            com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace r0 = r11.getColorSpace()
            int r11 = r11.getBitsPerComponent()
            float[] r11 = r0.getDefaultDecode(r11)
            return r11
        La8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.graphics.image.SampledImageReader.getDecodeArray(com.tom_roush.pdfbox.pdmodel.graphics.image.PDImage):float[]");
    }
}
