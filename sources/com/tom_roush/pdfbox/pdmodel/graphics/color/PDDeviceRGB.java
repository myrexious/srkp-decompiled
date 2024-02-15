package com.tom_roush.pdfbox.pdmodel.graphics.color;

import android.graphics.Bitmap;
import android.util.Log;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;

/* loaded from: classes3.dex */
public final class PDDeviceRGB extends PDDeviceColorSpace {
    public static final PDDeviceRGB INSTANCE = new PDDeviceRGB();
    private final PDColor initialColor = new PDColor(new float[]{0.0f, 0.0f, 0.0f}, this);

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public int getNumberOfComponents() {
        return 3;
    }

    private PDDeviceRGB() {
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public String getName() {
        return COSName.DEVICERGB.getName();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public float[] getDefaultDecode(int i) {
        return new float[]{0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f};
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public PDColor getInitialColor() {
        return this.initialColor;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public float[] toRGB(float[] fArr) {
        return fArr.length == 3 ? fArr : this.initialColor.getComponents();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace
    public Bitmap toRGBImage(Bitmap bitmap) throws IOException {
        if (bitmap.getConfig() == Bitmap.Config.ALPHA_8) {
            Log.e("PdfBox-Android", "Raster in PDDeviceRGB was ALPHA_8");
        }
        return bitmap;
    }
}
