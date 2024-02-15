package com.tom_roush.pdfbox.pdmodel.fdf;

import android.util.Log;
import com.tom_roush.harmony.awt.AWTColor;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;

/* loaded from: classes3.dex */
public class FDFAnnotationPolygon extends FDFAnnotation {
    public static final String SUBTYPE = "Polygon";

    public FDFAnnotationPolygon() {
        this.annot.setName(COSName.SUBTYPE, "Polygon");
    }

    public FDFAnnotationPolygon(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationPolygon(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Polygon");
        initVertices(element);
        String attribute = element.getAttribute("interior-color");
        if (attribute != null && attribute.length() == 7 && attribute.charAt(0) == '#') {
            setInteriorColor(new AWTColor(Integer.parseInt(attribute.substring(1, 7), 16)));
        }
    }

    private void initVertices(Element element) throws IOException {
        try {
            String evaluate = XPathFactory.newInstance().newXPath().evaluate("vertices", element);
            if (evaluate == null || evaluate.isEmpty()) {
                throw new IOException("Error: missing element 'vertices'");
            }
            String[] split = evaluate.split(",|;");
            float[] fArr = new float[split.length];
            for (int i = 0; i < split.length; i++) {
                fArr[i] = Float.parseFloat(split[i]);
            }
            setVertices(fArr);
        } catch (XPathExpressionException unused) {
            Log.d("PdfBox-Android", "Error while evaluating XPath expression for polygon vertices");
        }
    }

    public void setVertices(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        this.annot.setItem(COSName.VERTICES, (COSBase) cOSArray);
    }

    public float[] getVertices() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.VERTICES);
        if (cOSArray != null) {
            return cOSArray.toFloatArray();
        }
        return null;
    }

    public final void setInteriorColor(AWTColor aWTColor) {
        COSArray cOSArray = null;
        if (aWTColor != null) {
            float[] rGBColorComponents = aWTColor.getRGBColorComponents(null);
            cOSArray = new COSArray();
            cOSArray.setFloatArray(rGBColorComponents);
        }
        this.annot.setItem(COSName.IC, (COSBase) cOSArray);
    }

    public AWTColor getInteriorColor() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.IC);
        if (cOSArray != null) {
            float[] floatArray = cOSArray.toFloatArray();
            if (floatArray.length >= 3) {
                return new AWTColor(floatArray[0], floatArray[1], floatArray[2]);
            }
        }
        return null;
    }
}
