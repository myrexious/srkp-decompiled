package org.informatika.sirekap.support;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

/* compiled from: BitmapUtils.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a*\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\u0001\u001a\u001a\u0010\f\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e\u001a\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e¨\u0006\u0013"}, d2 = {"addWatermark", "Landroid/graphics/Bitmap;", "watermarkText", "", "applyPerspectiveTransformation", "topLeft", "Lorg/opencv/core/Point;", "topRight", "bottomLeft", "bottomRight", "convertRGBtoYV12", "", "decodeBitmapFromByteArray", "reqWidth", "", "reqHeight", "resizeToScreenContentSize", "newWidth", "newHeight", "app_productionRelease"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BitmapUtilsKt {
    public static final Bitmap decodeBitmapFromByteArray(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i3 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        if (i4 > i2 || i5 > i) {
            int i6 = i4 / 2;
            int i7 = i5 / 2;
            while (i6 / i3 >= i2 && i7 / i3 >= i) {
                i3 *= 2;
            }
        }
        options.inSampleSize = i3;
        options.inJustDecodeBounds = false;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        Intrinsics.checkNotNullExpressionValue(decodeByteArray, "decodeByteArray(this, 0, this.size, options)");
        return decodeByteArray;
    }

    public static final Bitmap addWatermark(Bitmap bitmap, String watermarkText) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        Intrinsics.checkNotNullParameter(watermarkText, "watermarkText");
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(-16711936);
        paint.setTextSize(50.0f);
        paint.setAntiAlias(true);
        canvas.drawText(watermarkText, 30.0f, ((paint.descent() - paint.ascent()) / 2) + 130.0f, paint);
        return bitmap;
    }

    public static final Bitmap resizeToScreenContentSize(Bitmap bitmap, int i, int i2) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();
        return createBitmap;
    }

    public static final Bitmap applyPerspectiveTransformation(Bitmap bitmap, Point topLeft, Point topRight, Point bottomLeft, Point bottomRight) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        Intrinsics.checkNotNullParameter(topLeft, "topLeft");
        Intrinsics.checkNotNullParameter(topRight, "topRight");
        Intrinsics.checkNotNullParameter(bottomLeft, "bottomLeft");
        Intrinsics.checkNotNullParameter(bottomRight, "bottomRight");
        int max = Math.max((int) (topRight.x - topLeft.x), (int) (bottomRight.x - bottomLeft.x));
        int max2 = Math.max((int) (bottomLeft.y - topLeft.y), (int) (bottomRight.y - topRight.y));
        Mat mat = new Mat(bitmap.getHeight(), bitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, mat);
        Mat mat2 = new Mat(max, max2, CvType.CV_8UC1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(topLeft);
        arrayList.add(topRight);
        arrayList.add(bottomLeft);
        arrayList.add(bottomRight);
        Mat vector_Point2f_to_Mat = Converters.vector_Point2f_to_Mat(arrayList);
        Point point = new Point(0.0d, 0.0d);
        double d = max;
        Point point2 = new Point(d, 0.0d);
        double d2 = max2;
        Point point3 = new Point(0.0d, d2);
        Point point4 = new Point(d, d2);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(point);
        arrayList2.add(point2);
        arrayList2.add(point3);
        arrayList2.add(point4);
        Imgproc.warpPerspective(mat, mat2, Imgproc.getPerspectiveTransform(vector_Point2f_to_Mat, Converters.vector_Point2f_to_Mat(arrayList2)), new Size(d, d2));
        Bitmap output = Bitmap.createBitmap(max, max2, Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat2, output);
        Intrinsics.checkNotNullExpressionValue(output, "output");
        return output;
    }

    public static final byte[] convertRGBtoYV12(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[(i * 3) / 2];
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                int i4 = i2 * width;
                int i5 = i4 + i3;
                int i6 = iArr[i5];
                int red = Color.red(i6);
                int green = Color.green(i6);
                int blue = Color.blue(i6);
                bArr[i5] = (byte) red;
                if (i2 % 2 == 0 && i3 % 2 == 0) {
                    int i7 = i4 / 4;
                    int i8 = i3 / 2;
                    bArr[i + i7 + i8] = (byte) green;
                    bArr[(i / 4) + i + i7 + i8] = (byte) blue;
                }
            }
        }
        return bArr;
    }
}
