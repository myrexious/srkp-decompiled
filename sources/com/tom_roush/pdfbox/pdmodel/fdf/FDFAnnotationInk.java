package com.tom_roush.pdfbox.pdmodel.fdf;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes3.dex */
public class FDFAnnotationInk extends FDFAnnotation {
    public static final String SUBTYPE = "Ink";

    public FDFAnnotationInk() {
        this.annot.setName(COSName.SUBTYPE, "Ink");
    }

    public FDFAnnotationInk(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationInk(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Ink");
        try {
            NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().evaluate("inklist/gesture", element, XPathConstants.NODESET);
            if (nodeList.getLength() == 0) {
                throw new IOException("Error: missing element 'gesture'");
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node item = nodeList.item(i);
                if (item instanceof Element) {
                    String[] split = item.getFirstChild().getNodeValue().split(",|;");
                    float[] fArr = new float[split.length];
                    for (int i2 = 0; i2 < split.length; i2++) {
                        fArr[i2] = Float.parseFloat(split[i2]);
                    }
                    arrayList.add(fArr);
                }
            }
            setInkList(arrayList);
        } catch (XPathExpressionException unused) {
            Log.d("PdfBox-Android", "Error while evaluating XPath expression for inklist gestures");
        }
    }

    public final void setInkList(List<float[]> list) {
        COSArray cOSArray = new COSArray();
        for (float[] fArr : list) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.setFloatArray(fArr);
            cOSArray.add((COSBase) cOSArray2);
        }
        this.annot.setItem(COSName.INKLIST, (COSBase) cOSArray);
    }

    public List<float[]> getInkList() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.INKLIST);
        if (cOSArray != null) {
            ArrayList arrayList = new ArrayList();
            Iterator<COSBase> it = cOSArray.iterator();
            while (it.hasNext()) {
                arrayList.add(((COSArray) it.next()).toFloatArray());
            }
            return arrayList;
        }
        return null;
    }
}
