package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import java.io.IOException;
import org.w3c.dom.Element;

/* loaded from: classes3.dex */
public class FDFAnnotationCaret extends FDFAnnotation {
    public static final String SUBTYPE = "Caret";

    public FDFAnnotationCaret() {
        this.annot.setName(COSName.SUBTYPE, "Caret");
    }

    public FDFAnnotationCaret(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationCaret(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Caret");
        initFringe(element);
        String attribute = element.getAttribute("symbol");
        if (attribute == null || attribute.isEmpty()) {
            return;
        }
        setSymbol(attribute);
    }

    private void initFringe(Element element) throws IOException {
        String attribute = element.getAttribute("fringe");
        if (attribute == null || attribute.isEmpty()) {
            return;
        }
        String[] split = attribute.split(",");
        if (split.length != 4) {
            throw new IOException("Error: wrong amount of numbers in attribute 'fringe'");
        }
        PDRectangle pDRectangle = new PDRectangle();
        pDRectangle.setLowerLeftX(Float.parseFloat(split[0]));
        pDRectangle.setLowerLeftY(Float.parseFloat(split[1]));
        pDRectangle.setUpperRightX(Float.parseFloat(split[2]));
        pDRectangle.setUpperRightY(Float.parseFloat(split[3]));
        setFringe(pDRectangle);
    }

    public final void setFringe(PDRectangle pDRectangle) {
        this.annot.setItem(COSName.RD, pDRectangle);
    }

    public PDRectangle getFringe() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.RD);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public final void setSymbol(String str) {
        this.annot.setString(COSName.SY, "paragraph".equals(str) ? "P" : "None");
    }

    public String getSymbol() {
        return this.annot.getString(COSName.SY);
    }
}
