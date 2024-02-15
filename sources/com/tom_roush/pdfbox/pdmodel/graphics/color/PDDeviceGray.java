package com.tom_roush.pdfbox.pdmodel.graphics.color;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class PDDeviceGray extends PDDeviceColorSpace {
    public static final PDDeviceGray INSTANCE = new PDDeviceGray();
    private final PDColor initialColor = new PDColor(new float[]{0.0f}, this);

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public int getNumberOfComponents() {
        return 1;
    }

    private PDDeviceGray() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public String getName() {
        return COSName.DEVICEGRAY.getName();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public float[] getDefaultDecode(int i) {
        return new float[]{0.0f, 1.0f};
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public PDColor getInitialColor() {
        return this.initialColor;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public float[] toRGB(float[] fArr) {
        float f = fArr[0];
        return new float[]{fArr[0], f, f};
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public Bitmap toRGBImage(Bitmap bitmap) throws IOException {
        if (bitmap.getConfig() != Bitmap.Config.ALPHA_8) {
            Log.e("PdfBox-Android", "Raster in PDDevicGrey was not ALPHA_8");
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width];
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] iArr2 = new int[width];
        for (int i = 0; i < height; i++) {
            bitmap.getPixels(iArr, 0, width, 0, i, width, 1);
            for (int i2 = 0; i2 < width; i2++) {
                int alpha = Color.alpha(iArr[i2]);
                iArr2[i2] = Color.argb(255, alpha, alpha, alpha);
            }
            createBitmap.setPixels(iArr2, 0, width, 0, i, width, 1);
        }
        return createBitmap;
    }
}
