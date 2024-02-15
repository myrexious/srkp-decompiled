package com.tom_roush.pdfbox.pdmodel.font;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.PDContentStream;
import com.tom_roush.pdfbox.contentstream.operator.Operator;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdfparser.PDFStreamParser;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public final class PDType3CharProc implements COSObjectable, PDContentStream {
    private final COSStream charStream;
    private final PDType3Font font;

    public PDType3CharProc(PDType3Font pDType3Font, COSStream cOSStream) {
        this.font = pDType3Font;
        this.charStream = cOSStream;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSStream getCOSObject() {
        return this.charStream;
    }

    public PDType3Font getFont() {
        return this.font;
    }

    public PDStream getContentStream() {
        return new PDStream(this.charStream);
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public InputStream getContents() throws IOException {
        return this.charStream.createInputStream();
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public PDResources getResources() {
        if (this.charStream.containsKey(COSName.RESOURCES)) {
            Log.w("PdfBox-Android", "Using resources dictionary found in charproc entry");
            Log.w("PdfBox-Android", "This should have been in the font or in the page dictionary");
            return new PDResources((COSDictionary) this.charStream.getDictionaryObject(COSName.RESOURCES));
        }
        return this.font.getResources();
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public PDRectangle getBBox() {
        return this.font.getFontBBox();
    }

    public PDRectangle getGlyphBBox() throws IOException {
        ArrayList arrayList = new ArrayList();
        PDFStreamParser pDFStreamParser = new PDFStreamParser(this);
        for (Object parseNextToken = pDFStreamParser.parseNextToken(); parseNextToken != null; parseNextToken = pDFStreamParser.parseNextToken()) {
            if (parseNextToken instanceof Operator) {
                if (((Operator) parseNextToken).getName().equals(OperatorName.TYPE3_D1) && arrayList.size() == 6) {
                    for (int i = 0; i < 6; i++) {
                        if (!(arrayList.get(i) instanceof COSNumber)) {
                            return null;
                        }
                    }
                    float floatValue = ((COSNumber) arrayList.get(2)).floatValue();
                    float floatValue2 = ((COSNumber) arrayList.get(3)).floatValue();
                    return new PDRectangle(floatValue, floatValue2, ((COSNumber) arrayList.get(4)).floatValue() - floatValue, ((COSNumber) arrayList.get(5)).floatValue() - floatValue2);
                }
                return null;
            }
            arrayList.add((COSBase) parseNextToken);
        }
        return null;
    }

    @Override // com.tom_roush.pdfbox.contentstream.PDContentStream
    public Matrix getMatrix() {
        return this.font.getFontMatrix();
    }

    public float getWidth() throws IOException {
        ArrayList arrayList = new ArrayList();
        PDFStreamParser pDFStreamParser = new PDFStreamParser(this);
        for (Object parseNextToken = pDFStreamParser.parseNextToken(); parseNextToken != null; parseNextToken = pDFStreamParser.parseNextToken()) {
            if (parseNextToken instanceof Operator) {
                return parseWidth((Operator) parseNextToken, arrayList);
            }
            arrayList.add((COSBase) parseNextToken);
        }
        throw new IOException("Unexpected end of stream");
    }

    private float parseWidth(Operator operator, List<COSBase> list) throws IOException {
        if (operator.getName().equals(OperatorName.TYPE3_D0) || operator.getName().equals(OperatorName.TYPE3_D1)) {
            COSBase cOSBase = list.get(0);
            if (cOSBase instanceof COSNumber) {
                return ((COSNumber) cOSBase).floatValue();
            }
            throw new IOException("Unexpected argument type: " + cOSBase.getClass().getName());
        }
        throw new IOException("First operator must be d0 or d1");
    }
}
