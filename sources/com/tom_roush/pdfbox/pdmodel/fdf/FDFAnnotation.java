package com.tom_roush.pdfbox.pdmodel.fdf;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.tom_roush.harmony.awt.AWTColor;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDWindowsLaunchParams;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderEffectDictionary;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import com.tom_roush.pdfbox.util.DateConverter;
import java.io.IOException;
import java.util.Calendar;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import net.openid.appauth.AuthorizationRequest;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.type.ThumbnailType;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/* loaded from: classes3.dex */
public abstract class FDFAnnotation implements COSObjectable {
    private static final int FLAG_HIDDEN = 2;
    private static final int FLAG_INVISIBLE = 1;
    private static final int FLAG_LOCKED = 128;
    private static final int FLAG_LOCKED_CONTENTS = 512;
    private static final int FLAG_NO_ROTATE = 16;
    private static final int FLAG_NO_VIEW = 32;
    private static final int FLAG_NO_ZOOM = 8;
    private static final int FLAG_PRINTED = 4;
    private static final int FLAG_READ_ONLY = 64;
    private static final int FLAG_TOGGLE_NO_VIEW = 256;
    protected COSDictionary annot;

    public FDFAnnotation() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.annot = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.ANNOT);
    }

    public FDFAnnotation(COSDictionary cOSDictionary) {
        this.annot = cOSDictionary;
    }

    public FDFAnnotation(Element element) throws IOException {
        this();
        String[] split;
        String attribute = element.getAttribute(AuthorizationRequest.Display.PAGE);
        if (attribute == null || attribute.isEmpty()) {
            throw new IOException("Error: missing required attribute 'page'");
        }
        setPage(Integer.parseInt(attribute));
        String attribute2 = element.getAttribute(TypedValues.Custom.S_COLOR);
        if (attribute2 != null && attribute2.length() == 7 && attribute2.charAt(0) == '#') {
            setColor(new AWTColor(Integer.parseInt(attribute2.substring(1, 7), 16)));
        }
        setDate(element.getAttribute("date"));
        String attribute3 = element.getAttribute("flags");
        if (attribute3 != null) {
            for (String str : attribute3.split(",")) {
                if (str.equals("invisible")) {
                    setInvisible(true);
                } else if (str.equals("hidden")) {
                    setHidden(true);
                } else if (str.equals(PDWindowsLaunchParams.OPERATION_PRINT)) {
                    setPrinted(true);
                } else if (str.equals("nozoom")) {
                    setNoZoom(true);
                } else if (str.equals("norotate")) {
                    setNoRotate(true);
                } else if (str.equals("noview")) {
                    setNoView(true);
                } else if (str.equals("readonly")) {
                    setReadOnly(true);
                } else if (str.equals("locked")) {
                    setLocked(true);
                } else if (str.equals("togglenoview")) {
                    setToggleNoView(true);
                }
            }
        }
        setName(element.getAttribute("name"));
        String attribute4 = element.getAttribute("rect");
        if (attribute4 == null) {
            throw new IOException("Error: missing attribute 'rect'");
        }
        String[] split2 = attribute4.split(",");
        if (split2.length != 4) {
            throw new IOException("Error: wrong amount of numbers in attribute 'rect'");
        }
        float[] fArr = new float[4];
        for (int i = 0; i < 4; i++) {
            fArr[i] = Float.parseFloat(split2[i]);
        }
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        setRectangle(new PDRectangle(cOSArray));
        setTitle(element.getAttribute("title"));
        setCreationDate(DateConverter.toCalendar(element.getAttribute("creationdate")));
        String attribute5 = element.getAttribute("opacity");
        if (attribute5 != null && !attribute5.isEmpty()) {
            setOpacity(Float.parseFloat(attribute5));
        }
        setSubject(element.getAttribute(DublinCoreSchema.SUBJECT));
        String attribute6 = element.getAttribute("intent");
        setIntent(attribute6.isEmpty() ? element.getAttribute("IT") : attribute6);
        XPath newXPath = XPathFactory.newInstance().newXPath();
        try {
            setContents(newXPath.evaluate("contents[1]", element));
        } catch (XPathExpressionException unused) {
            Log.d("PdfBox-Android", "Error while evaluating XPath expression for richtext contents");
        }
        try {
            Node node = (Node) newXPath.evaluate("contents-richtext[1]", element, XPathConstants.NODE);
            if (node != null) {
                setRichContents(richContentsToString(node, true));
                setContents(node.getTextContent().trim());
            }
        } catch (XPathExpressionException unused2) {
            Log.d("PdfBox-Android", "Error while evaluating XPath expression for richtext contents");
        }
        PDBorderStyleDictionary pDBorderStyleDictionary = new PDBorderStyleDictionary();
        String attribute7 = element.getAttribute(ThumbnailType.WIDTH);
        if (attribute7 != null && !attribute7.isEmpty()) {
            pDBorderStyleDictionary.setWidth(Float.parseFloat(attribute7));
        }
        if (pDBorderStyleDictionary.getWidth() > 0.0f) {
            String attribute8 = element.getAttribute("style");
            if (attribute8 != null && !attribute8.isEmpty()) {
                if (attribute8.equals("dash")) {
                    pDBorderStyleDictionary.setStyle("D");
                } else if (attribute8.equals("bevelled")) {
                    pDBorderStyleDictionary.setStyle("B");
                } else if (attribute8.equals("inset")) {
                    pDBorderStyleDictionary.setStyle("I");
                } else if (attribute8.equals("underline")) {
                    pDBorderStyleDictionary.setStyle("U");
                } else if (attribute8.equals("cloudy")) {
                    pDBorderStyleDictionary.setStyle("S");
                    PDBorderEffectDictionary pDBorderEffectDictionary = new PDBorderEffectDictionary();
                    pDBorderEffectDictionary.setStyle("C");
                    String attribute9 = element.getAttribute("intensity");
                    if (attribute9 != null && !attribute9.isEmpty()) {
                        pDBorderEffectDictionary.setIntensity(Float.parseFloat(element.getAttribute("intensity")));
                    }
                    setBorderEffect(pDBorderEffectDictionary);
                } else {
                    pDBorderStyleDictionary.setStyle("S");
                }
            }
            String attribute10 = element.getAttribute("dashes");
            if (attribute10 != null && !attribute10.isEmpty()) {
                String[] split3 = attribute10.split(",");
                COSArray cOSArray2 = new COSArray();
                for (String str2 : split3) {
                    cOSArray2.add((COSBase) COSNumber.get(str2));
                }
                pDBorderStyleDictionary.setDashStyle(cOSArray2);
            }
            setBorderStyle(pDBorderStyleDictionary);
        }
    }

    public static FDFAnnotation create(COSDictionary cOSDictionary) throws IOException {
        if (cOSDictionary != null) {
            String nameAsString = cOSDictionary.getNameAsString(COSName.SUBTYPE);
            if ("Text".equals(nameAsString)) {
                return new FDFAnnotationText(cOSDictionary);
            }
            if ("Caret".equals(nameAsString)) {
                return new FDFAnnotationCaret(cOSDictionary);
            }
            if ("FreeText".equals(nameAsString)) {
                return new FDFAnnotationFreeText(cOSDictionary);
            }
            if ("FileAttachment".equals(nameAsString)) {
                return new FDFAnnotationFileAttachment(cOSDictionary);
            }
            if ("Highlight".equals(nameAsString)) {
                return new FDFAnnotationHighlight(cOSDictionary);
            }
            if ("Ink".equals(nameAsString)) {
                return new FDFAnnotationInk(cOSDictionary);
            }
            if ("Line".equals(nameAsString)) {
                return new FDFAnnotationLine(cOSDictionary);
            }
            if ("Link".equals(nameAsString)) {
                return new FDFAnnotationLink(cOSDictionary);
            }
            if ("Circle".equals(nameAsString)) {
                return new FDFAnnotationCircle(cOSDictionary);
            }
            if ("Square".equals(nameAsString)) {
                return new FDFAnnotationSquare(cOSDictionary);
            }
            if ("Polygon".equals(nameAsString)) {
                return new FDFAnnotationPolygon(cOSDictionary);
            }
            if (FDFAnnotationPolyline.SUBTYPE.equals(nameAsString)) {
                return new FDFAnnotationPolyline(cOSDictionary);
            }
            if ("Sound".equals(nameAsString)) {
                return new FDFAnnotationSound(cOSDictionary);
            }
            if ("Squiggly".equals(nameAsString)) {
                return new FDFAnnotationSquiggly(cOSDictionary);
            }
            if ("Stamp".equals(nameAsString)) {
                return new FDFAnnotationStamp(cOSDictionary);
            }
            if ("StrikeOut".equals(nameAsString)) {
                return new FDFAnnotationStrikeOut(cOSDictionary);
            }
            if ("Underline".equals(nameAsString)) {
                return new FDFAnnotationUnderline(cOSDictionary);
            }
            Log.w("PdfBox-Android", "Unknown or unsupported annotation type '" + nameAsString + OperatorName.SHOW_TEXT_LINE);
        }
        return null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.annot;
    }

    public Integer getPage() {
        COSNumber cOSNumber = (COSNumber) this.annot.getDictionaryObject(COSName.PAGE);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public final void setPage(int i) {
        this.annot.setInt(COSName.PAGE, i);
    }

    public AWTColor getColor() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.C);
        if (cOSArray != null) {
            float[] floatArray = cOSArray.toFloatArray();
            if (floatArray.length >= 3) {
                return new AWTColor(floatArray[0], floatArray[1], floatArray[2]);
            }
        }
        return null;
    }

    public final void setColor(AWTColor aWTColor) {
        COSArray cOSArray = null;
        if (aWTColor != null) {
            float[] rGBColorComponents = aWTColor.getRGBColorComponents(null);
            cOSArray = new COSArray();
            cOSArray.setFloatArray(rGBColorComponents);
        }
        this.annot.setItem(COSName.C, (COSBase) cOSArray);
    }

    public String getDate() {
        return this.annot.getString(COSName.M);
    }

    public final void setDate(String str) {
        this.annot.setString(COSName.M, str);
    }

    public boolean isInvisible() {
        return this.annot.getFlag(COSName.F, 1);
    }

    public final void setInvisible(boolean z) {
        this.annot.setFlag(COSName.F, 1, z);
    }

    public boolean isHidden() {
        return this.annot.getFlag(COSName.F, 2);
    }

    public final void setHidden(boolean z) {
        this.annot.setFlag(COSName.F, 2, z);
    }

    public boolean isPrinted() {
        return this.annot.getFlag(COSName.F, 4);
    }

    public final void setPrinted(boolean z) {
        this.annot.setFlag(COSName.F, 4, z);
    }

    public boolean isNoZoom() {
        return this.annot.getFlag(COSName.F, 8);
    }

    public final void setNoZoom(boolean z) {
        this.annot.setFlag(COSName.F, 8, z);
    }

    public boolean isNoRotate() {
        return this.annot.getFlag(COSName.F, 16);
    }

    public final void setNoRotate(boolean z) {
        this.annot.setFlag(COSName.F, 16, z);
    }

    public boolean isNoView() {
        return this.annot.getFlag(COSName.F, 32);
    }

    public final void setNoView(boolean z) {
        this.annot.setFlag(COSName.F, 32, z);
    }

    public boolean isReadOnly() {
        return this.annot.getFlag(COSName.F, 64);
    }

    public final void setReadOnly(boolean z) {
        this.annot.setFlag(COSName.F, 64, z);
    }

    public boolean isLocked() {
        return this.annot.getFlag(COSName.F, 128);
    }

    public final void setLocked(boolean z) {
        this.annot.setFlag(COSName.F, 128, z);
    }

    public boolean isToggleNoView() {
        return this.annot.getFlag(COSName.F, 256);
    }

    public final void setToggleNoView(boolean z) {
        this.annot.setFlag(COSName.F, 256, z);
    }

    public boolean isLockedContents() {
        return this.annot.getFlag(COSName.F, 512);
    }

    public void setLockedContents(boolean z) {
        this.annot.setFlag(COSName.F, 512, z);
    }

    public final void setName(String str) {
        this.annot.setString(COSName.NM, str);
    }

    public String getName() {
        return this.annot.getString(COSName.NM);
    }

    public final void setRectangle(PDRectangle pDRectangle) {
        this.annot.setItem(COSName.RECT, pDRectangle);
    }

    public PDRectangle getRectangle() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.RECT);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public final void setContents(String str) {
        this.annot.setString(COSName.CONTENTS, str);
    }

    public String getContents() {
        return this.annot.getString(COSName.CONTENTS);
    }

    public final void setTitle(String str) {
        this.annot.setString(COSName.T, str);
    }

    public String getTitle() {
        return this.annot.getString(COSName.T);
    }

    public Calendar getCreationDate() throws IOException {
        return this.annot.getDate(COSName.CREATION_DATE);
    }

    public final void setCreationDate(Calendar calendar) {
        this.annot.setDate(COSName.CREATION_DATE, calendar);
    }

    public final void setOpacity(float f) {
        this.annot.setFloat(COSName.CA, f);
    }

    public float getOpacity() {
        return this.annot.getFloat(COSName.CA, 1.0f);
    }

    public final void setSubject(String str) {
        this.annot.setString(COSName.SUBJ, str);
    }

    public String getSubject() {
        return this.annot.getString(COSName.SUBJ);
    }

    public final void setIntent(String str) {
        this.annot.setName(COSName.IT, str);
    }

    public String getIntent() {
        return this.annot.getNameAsString(COSName.IT);
    }

    public String getRichContents() {
        return getStringOrStream(this.annot.getDictionaryObject(COSName.RC));
    }

    public final void setRichContents(String str) {
        this.annot.setItem(COSName.RC, (COSBase) new COSString(str));
    }

    public final void setBorderStyle(PDBorderStyleDictionary pDBorderStyleDictionary) {
        this.annot.setItem(COSName.BS, pDBorderStyleDictionary);
    }

    public PDBorderStyleDictionary getBorderStyle() {
        COSDictionary cOSDictionary = (COSDictionary) this.annot.getDictionaryObject(COSName.BS);
        if (cOSDictionary != null) {
            return new PDBorderStyleDictionary(cOSDictionary);
        }
        return null;
    }

    public final void setBorderEffect(PDBorderEffectDictionary pDBorderEffectDictionary) {
        this.annot.setItem(COSName.BE, pDBorderEffectDictionary);
    }

    public PDBorderEffectDictionary getBorderEffect() {
        COSDictionary cOSDictionary = (COSDictionary) this.annot.getDictionaryObject(COSName.BE);
        if (cOSDictionary != null) {
            return new PDBorderEffectDictionary(cOSDictionary);
        }
        return null;
    }

    protected final String getStringOrStream(COSBase cOSBase) {
        if (cOSBase == null) {
            return "";
        }
        if (cOSBase instanceof COSString) {
            return ((COSString) cOSBase).getString();
        }
        return cOSBase instanceof COSStream ? ((COSStream) cOSBase).toTextString() : "";
    }

    private String richContentsToString(Node node, boolean z) {
        StringBuilder sb = new StringBuilder();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item instanceof Element) {
                sb.append(richContentsToString(item, false));
            } else if (item instanceof CDATASection) {
                sb.append("<![CDATA[").append(((CDATASection) item).getData()).append("]]>");
            } else if (item instanceof Text) {
                String data = ((Text) item).getData();
                if (data != null) {
                    data = data.replace("&", "&amp;").replace("<", "&lt;");
                }
                sb.append(data);
            }
        }
        if (z) {
            return sb.toString();
        }
        NamedNodeMap attributes = node.getAttributes();
        StringBuilder sb2 = new StringBuilder();
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            Node item2 = attributes.item(i2);
            String nodeValue = item2.getNodeValue();
            if (nodeValue != null) {
                nodeValue = nodeValue.replace(OperatorName.SHOW_TEXT_LINE_AND_SPACE, "&quot;");
            }
            sb2.append(String.format(" %s=\"%s\"", item2.getNodeName(), nodeValue));
        }
        return String.format("<%s%s>%s</%s>", node.getNodeName(), sb2.toString(), sb.toString(), node.getNodeName());
    }
}
