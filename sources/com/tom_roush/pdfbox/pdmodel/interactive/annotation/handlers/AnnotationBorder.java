package com.tom_roush.pdfbox.pdmodel.interactive.annotation.handlers;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;

/* loaded from: classes3.dex */
class AnnotationBorder {
    float[] dashArray = null;
    boolean underline = false;
    float width = 0.0f;

    AnnotationBorder() {
    }

    public static AnnotationBorder getAnnotationBorder(PDAnnotation pDAnnotation, PDBorderStyleDictionary pDBorderStyleDictionary) {
        AnnotationBorder annotationBorder = new AnnotationBorder();
        boolean z = true;
        if (pDBorderStyleDictionary == null) {
            COSArray border = pDAnnotation.getBorder();
            if (border.size() >= 3 && (border.getObject(2) instanceof COSNumber)) {
                annotationBorder.width = ((COSNumber) border.getObject(2)).floatValue();
            }
            if (border.size() > 3) {
                COSBase object = border.getObject(3);
                if (object instanceof COSArray) {
                    annotationBorder.dashArray = ((COSArray) object).toFloatArray();
                }
            }
        } else {
            annotationBorder.width = pDBorderStyleDictionary.getWidth();
            if (pDBorderStyleDictionary.getStyle().equals("D")) {
                annotationBorder.dashArray = pDBorderStyleDictionary.getDashStyle().getDashArray();
            }
            if (pDBorderStyleDictionary.getStyle().equals("U")) {
                annotationBorder.underline = true;
            }
        }
        float[] fArr = annotationBorder.dashArray;
        if (fArr != null) {
            int length = fArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (Float.compare(fArr[i], 0.0f) != 0) {
                    z = false;
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                annotationBorder.dashArray = null;
            }
        }
        return annotationBorder;
    }
}
