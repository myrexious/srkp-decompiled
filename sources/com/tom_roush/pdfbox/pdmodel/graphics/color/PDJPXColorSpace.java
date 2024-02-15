package com.tom_roush.pdfbox.pdmodel.graphics.color;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.os.Build;
import com.tom_roush.pdfbox.cos.COSBase;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class PDJPXColorSpace extends PDColorSpace {
    private final ColorSpace colorSpace;

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public String getName() {
        return "JPX";
    }

    public PDJPXColorSpace(ColorSpace colorSpace) {
        this.colorSpace = colorSpace;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public int getNumberOfComponents() {
        if (Build.VERSION.SDK_INT > 26) {
            return this.colorSpace.getComponentCount();
        }
        return 0;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public float[] getDefaultDecode(int i) {
        if (Build.VERSION.SDK_INT > 26) {
            int numberOfComponents = getNumberOfComponents();
            float[] fArr = new float[numberOfComponents * 2];
            for (int i2 = 0; i2 < numberOfComponents; i2++) {
                int i3 = i2 * 2;
                fArr[i3] = this.colorSpace.getMinValue(i2);
                fArr[i3 + 1] = this.colorSpace.getMaxValue(i2);
            }
            return fArr;
        }
        return new float[0];
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public PDColor getInitialColor() {
        throw new UnsupportedOperationException("JPX color spaces don't support drawing");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public float[] toRGB(float[] fArr) {
        throw new UnsupportedOperationException("JPX color spaces don't support drawing");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public Bitmap toRGBImage(Bitmap bitmap) throws IOException {
        if (Build.VERSION.SDK_INT > 26) {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565, false, this.colorSpace);
            new Canvas(createBitmap).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            return createBitmap;
        }
        return bitmap;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace, com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        throw new UnsupportedOperationException("JPX color spaces don't have COS objects");
    }
}
