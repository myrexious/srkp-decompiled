package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import org.w3c.dom.Element;

/* loaded from: classes3.dex */
public abstract class FDFAnnotationTextMarkup extends FDFAnnotation {
    public FDFAnnotationTextMarkup() {
    }

    public FDFAnnotationTextMarkup(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationTextMarkup(Element element) throws IOException {
        super(element);
        String attribute = element.getAttribute("coords");
        if (attribute == null || attribute.isEmpty()) {
            throw new IOException("Error: missing attribute 'coords'");
        }
        String[] split = attribute.split(",");
        if (split.length < 8) {
            throw new IOException("Error: too little numbers in attribute 'coords'");
        }
        float[] fArr = new float[split.length];
        for (int i = 0; i < split.length; i++) {
            fArr[i] = Float.parseFloat(split[i]);
        }
        setCoords(fArr);
    }

    public void setCoords(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        this.annot.setItem(COSName.QUADPOINTS, (COSBase) cOSArray);
    }

    public float[] getCoords() {
        COSArray cOSArray = (COSArray) this.annot.getItem(COSName.QUADPOINTS);
        if (cOSArray != null) {
            return cOSArray.toFloatArray();
        }
        return null;
    }
}
