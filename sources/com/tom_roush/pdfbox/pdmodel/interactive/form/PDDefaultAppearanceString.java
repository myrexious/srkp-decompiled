package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdfparser.PDFStreamParser;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class PDDefaultAppearanceString {
    private static final float DEFAULT_FONT_SIZE = 12.0f;
    private final PDResources defaultResources;
    private PDFont font;
    private PDColor fontColor;
    private COSName fontName;
    private float fontSize = DEFAULT_FONT_SIZE;

    public PDDefaultAppearanceString(COSString cOSString, PDResources pDResources) throws IOException {
        if (cOSString == null) {
            throw new IllegalArgumentException("/DA is a required entry. Please set a default appearance first.");
        }
        if (pDResources == null) {
            throw new IllegalArgumentException("/DR is a required entry");
        }
        this.defaultResources = pDResources;
        processAppearanceStringOperators(cOSString.getBytes());
    }

    private void processAppearanceStringOperators(byte[] bArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        PDFStreamParser pDFStreamParser = new PDFStreamParser(bArr);
        for (Object parseNextToken = pDFStreamParser.parseNextToken(); parseNextToken != null; parseNextToken = pDFStreamParser.parseNextToken()) {
            if (parseNextToken instanceof Operator) {
                processOperator((Operator) parseNextToken, arrayList);
                arrayList = new ArrayList();
            } else {
                arrayList.add((COSBase) parseNextToken);
            }
        }
    }

    private void processOperator(Operator operator, List<COSBase> list) throws IOException {
        String name = operator.getName();
        if (OperatorName.SET_FONT_AND_SIZE.equals(name)) {
            processSetFont(list);
        } else if (OperatorName.NON_STROKING_GRAY.equals(name)) {
            processSetFontColor(list);
        } else if (OperatorName.NON_STROKING_RGB.equals(name)) {
            processSetFontColor(list);
        } else if (OperatorName.NON_STROKING_CMYK.equals(name)) {
            processSetFontColor(list);
        }
    }

    private void processSetFont(List<COSBase> list) throws IOException {
        if (list.size() < 2) {
            throw new IOException("Missing operands for set font operator " + Arrays.toString(list.toArray()));
        }
        COSBase cOSBase = list.get(0);
        COSBase cOSBase2 = list.get(1);
        if ((cOSBase instanceof COSName) && (cOSBase2 instanceof COSNumber)) {
            COSName cOSName = (COSName) cOSBase;
            PDFont font = this.defaultResources.getFont(cOSName);
            float floatValue = ((COSNumber) cOSBase2).floatValue();
            if (font == null) {
                throw new IOException("Could not find font: /" + cOSName.getName());
            }
            setFontName(cOSName);
            setFont(font);
            setFontSize(floatValue);
        }
    }

    private void processSetFontColor(List<COSBase> list) throws IOException {
        PDColorSpace pDColorSpace;
        int size = list.size();
        if (size == 1) {
            pDColorSpace = PDDeviceGray.INSTANCE;
        } else if (size == 3) {
            pDColorSpace = PDDeviceRGB.INSTANCE;
        } else if (size == 4) {
            pDColorSpace = PDDeviceRGB.INSTANCE;
        } else {
            throw new IOException("Missing operands for set non stroking color operator " + Arrays.toString(list.toArray()));
        }
        COSArray cOSArray = new COSArray();
        cOSArray.addAll(list);
        setFontColor(new PDColor(cOSArray, pDColorSpace));
    }

    public COSName getFontName() {
        return this.fontName;
    }

    void setFontName(COSName cOSName) {
        this.fontName = cOSName;
    }

    public PDFont getFont() {
        return this.font;
    }

    void setFont(PDFont pDFont) {
        this.font = pDFont;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    void setFontSize(float f) {
        this.fontSize = f;
    }

    PDColor getFontColor() {
        return this.fontColor;
    }

    void setFontColor(PDColor pDColor) {
        this.fontColor = pDColor;
    }

    public void writeTo(PDPageContentStream pDPageContentStream, float f) throws IOException {
        float fontSize = getFontSize();
        if (fontSize != 0.0f) {
            f = fontSize;
        }
        pDPageContentStream.setFont(getFont(), f);
        if (getFontColor() != null) {
            pDPageContentStream.setNonStrokingColor(getFontColor());
        }
    }

    public void copyNeededResourcesTo(PDAppearanceStream pDAppearanceStream) throws IOException {
        PDResources resources = pDAppearanceStream.getResources();
        if (resources == null) {
            resources = new PDResources();
            pDAppearanceStream.setResources(resources);
        }
        if (resources.getFont(this.fontName) == null) {
            resources.put(this.fontName, getFont());
        }
    }
}
