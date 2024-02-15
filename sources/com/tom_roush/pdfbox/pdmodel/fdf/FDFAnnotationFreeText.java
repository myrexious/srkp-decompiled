package com.tom_roush.pdfbox.pdmodel.fdf;

import android.util.Log;
import androidx.constraintlayout.motion.widget.Key;
import com.tom_roush.fontbox.ttf.HeaderTable;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import java.io.IOException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;

/* loaded from: classes3.dex */
public class FDFAnnotationFreeText extends FDFAnnotation {
    public static final String SUBTYPE = "FreeText";

    public FDFAnnotationFreeText() {
        this.annot.setName(COSName.SUBTYPE, "FreeText");
    }

    public FDFAnnotationFreeText(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationFreeText(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "FreeText");
        setJustification(element.getAttribute("justification"));
        XPath newXPath = XPathFactory.newInstance().newXPath();
        try {
            setDefaultAppearance(newXPath.evaluate("defaultappearance", element));
            setDefaultStyle(newXPath.evaluate("defaultstyle", element));
        } catch (XPathExpressionException unused) {
            Log.d("PdfBox-Android", "Error while evaluating XPath expression");
        }
        initCallout(element);
        String attribute = element.getAttribute(Key.ROTATION);
        if (attribute != null && !attribute.isEmpty()) {
            setRotation(Integer.parseInt(attribute));
        }
        initFringe(element);
        String attribute2 = element.getAttribute(HeaderTable.TAG);
        if (attribute2 == null || attribute2.isEmpty()) {
            return;
        }
        setLineEndingStyle(attribute2);
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

    private void initCallout(Element element) {
        String attribute = element.getAttribute("callout");
        if (attribute == null || attribute.isEmpty()) {
            return;
        }
        String[] split = attribute.split(",");
        float[] fArr = new float[split.length];
        for (int i = 0; i < split.length; i++) {
            fArr[i] = Float.parseFloat(split[i]);
        }
        setCallout(fArr);
    }

    public void setCallout(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        this.annot.setItem(COSName.CL, (COSBase) cOSArray);
    }

    public float[] getCallout() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.CL);
        if (cOSArray != null) {
            return cOSArray.toFloatArray();
        }
        return null;
    }

    public final void setJustification(String str) {
        int i;
        if ("centered".equals(str)) {
            i = 1;
        } else {
            i = "right".equals(str) ? 2 : 0;
        }
        this.annot.setInt(COSName.Q, i);
    }

    public String getJustification() {
        return "" + this.annot.getInt(COSName.Q, 0);
    }

    public final void setRotation(int i) {
        this.annot.setInt(COSName.ROTATE, i);
    }

    public String getRotation() {
        return this.annot.getString(COSName.ROTATE);
    }

    public final void setDefaultAppearance(String str) {
        this.annot.setString(COSName.DA, str);
    }

    public String getDefaultAppearance() {
        return this.annot.getString(COSName.DA);
    }

    public final void setDefaultStyle(String str) {
        this.annot.setString(COSName.DS, str);
    }

    public String getDefaultStyle() {
        return this.annot.getString(COSName.DS);
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

    public final void setLineEndingStyle(String str) {
        this.annot.setName(COSName.LE, str);
    }

    public String getLineEndingStyle() {
        return this.annot.getNameAsString(COSName.LE);
    }
}
