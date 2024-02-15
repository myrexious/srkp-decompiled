package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import android.graphics.PointF;
import android.graphics.RectF;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PDShadingType6 extends PDMeshBasedShadingType {
    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShadingType4, com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading
    public int getShadingType() {
        return 6;
    }

    public PDShadingType6(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDMeshBasedShadingType
    protected Patch generatePatch(PointF[] pointFArr, float[][] fArr) {
        return new CoonsPatch(pointFArr, fArr);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.graphics.shading.PDMeshBasedShadingType, com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShadingType4, com.tom_roush.pdfbox.pdmodel.graphics.shading.PDTriangleBasedShadingType, com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading
    public RectF getBounds(AffineTransform affineTransform, Matrix matrix) throws IOException {
        return getBounds(affineTransform, matrix, 12);
    }
}
