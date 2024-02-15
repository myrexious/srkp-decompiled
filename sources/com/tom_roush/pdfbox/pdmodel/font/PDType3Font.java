package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.ResourceCache;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.encoding.DictionaryEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.GlyphList;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* loaded from: classes3.dex */
public class PDType3Font extends PDSimpleFont {
    private COSDictionary charProcs;
    private BoundingBox fontBBox;
    private Matrix fontMatrix;
    private final ResourceCache resourceCache;
    private PDResources resources;

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return false;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return true;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont, com.tom_roush.pdfbox.pdmodel.font.PDFont
    public boolean isStandard14() {
        return false;
    }

    public PDType3Font(COSDictionary cOSDictionary) throws IOException {
        this(cOSDictionary, null);
    }

    public PDType3Font(COSDictionary cOSDictionary, ResourceCache resourceCache) throws IOException {
        super(cOSDictionary);
        this.resourceCache = resourceCache;
        readEncoding();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public String getName() {
        return this.dict.getNameAsString(COSName.NAME);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public final void readEncoding() throws IOException {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.ENCODING);
        if (dictionaryObject instanceof COSName) {
            COSName cOSName = (COSName) dictionaryObject;
            this.encoding = Encoding.getInstance(cOSName);
            if (this.encoding == null) {
                Log.w("PdfBox-Android", "Unknown encoding: " + cOSName.getName());
            }
        } else if (dictionaryObject instanceof COSDictionary) {
            this.encoding = new DictionaryEncoding((COSDictionary) dictionaryObject);
        }
        this.glyphList = GlyphList.getAdobeGlyphList();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    protected Encoding readEncodingFromFont() throws IOException {
        throw new UnsupportedOperationException("not supported for Type 3 fonts");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    protected Boolean isFontSymbolic() {
        return false;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public Path getPath(String str) throws IOException {
        throw new UnsupportedOperationException("not supported for Type 3 fonts");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public boolean hasGlyph(String str) throws IOException {
        return (getCharProcs() == null || getCharProcs().getCOSStream(COSName.getPDFName(str)) == null) ? false : true;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public FontBoxFont getFontBoxFont() {
        throw new UnsupportedOperationException("not supported for Type 3 fonts");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public Vector getDisplacement(int i) throws IOException {
        return getFontMatrix().transform(new Vector(getWidth(i), 0.0f));
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont, com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getWidth(int i) throws IOException {
        Float f;
        int i2 = this.dict.getInt(COSName.FIRST_CHAR, -1);
        int i3 = this.dict.getInt(COSName.LAST_CHAR, -1);
        List<Float> widths = getWidths();
        if (!widths.isEmpty() && i >= i2 && i <= i3) {
            int i4 = i - i2;
            if (i4 < widths.size() && (f = widths.get(i4)) != null) {
                return f.floatValue();
            }
            return 0.0f;
        }
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        if (fontDescriptor != null) {
            return fontDescriptor.getMissingWidth();
        }
        return getWidthFromFont(i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        PDType3CharProc charProc = getCharProc(i);
        if (charProc == null || charProc.getContentStream().getLength() == 0) {
            return 0.0f;
        }
        return charProc.getWidth();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        if (fontDescriptor != null) {
            PDRectangle fontBoundingBox = fontDescriptor.getFontBoundingBox();
            float height = fontBoundingBox != null ? fontBoundingBox.getHeight() / 2.0f : 0.0f;
            if (height == 0.0f) {
                height = fontDescriptor.getCapHeight();
            }
            if (height == 0.0f) {
                height = fontDescriptor.getAscent();
            }
            if (height == 0.0f) {
                float xHeight = fontDescriptor.getXHeight();
                return xHeight > 0.0f ? xHeight - fontDescriptor.getDescent() : xHeight;
            }
            return height;
        }
        return 0.0f;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    protected byte[] encode(int i) throws IOException {
        throw new UnsupportedOperationException("Not implemented: Type3");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont, com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        if (this.fontMatrix == null) {
            COSArray cOSArray = this.dict.getCOSArray(COSName.FONT_MATRIX);
            this.fontMatrix = checkFontMatrixValues(cOSArray) ? Matrix.createMatrix(cOSArray) : super.getFontMatrix();
        }
        return this.fontMatrix;
    }

    private boolean checkFontMatrixValues(COSArray cOSArray) {
        if (cOSArray == null || cOSArray.size() != 6) {
            return false;
        }
        for (COSBase cOSBase : cOSArray.toList()) {
            if (!(cOSBase instanceof COSNumber)) {
                return false;
            }
        }
        return true;
    }

    public PDResources getResources() {
        if (this.resources == null) {
            COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.RESOURCES);
            if (dictionaryObject instanceof COSDictionary) {
                this.resources = new PDResources((COSDictionary) dictionaryObject, this.resourceCache);
            }
        }
        return this.resources;
    }

    public PDRectangle getFontBBox() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.FONT_BBOX);
        if (dictionaryObject instanceof COSArray) {
            return new PDRectangle((COSArray) dictionaryObject);
        }
        return null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public BoundingBox getBoundingBox() {
        if (this.fontBBox == null) {
            this.fontBBox = generateBoundingBox();
        }
        return this.fontBBox;
    }

    private BoundingBox generateBoundingBox() {
        COSDictionary charProcs;
        PDRectangle fontBBox = getFontBBox();
        if (fontBBox == null) {
            Log.w("PdfBox-Android", "FontBBox missing, returning empty rectangle");
            return new BoundingBox();
        }
        if (fontBBox.getLowerLeftX() == 0.0f && fontBBox.getLowerLeftY() == 0.0f && fontBBox.getUpperRightX() == 0.0f && fontBBox.getUpperRightY() == 0.0f && (charProcs = getCharProcs()) != null) {
            for (COSName cOSName : charProcs.keySet()) {
                COSStream cOSStream = charProcs.getCOSStream(cOSName);
                if (cOSStream != null) {
                    try {
                        PDRectangle glyphBBox = new PDType3CharProc(this, cOSStream).getGlyphBBox();
                        if (glyphBBox != null) {
                            fontBBox.setLowerLeftX(Math.min(fontBBox.getLowerLeftX(), glyphBBox.getLowerLeftX()));
                            fontBBox.setLowerLeftY(Math.min(fontBBox.getLowerLeftY(), glyphBBox.getLowerLeftY()));
                            fontBBox.setUpperRightX(Math.max(fontBBox.getUpperRightX(), glyphBBox.getUpperRightX()));
                            fontBBox.setUpperRightY(Math.max(fontBBox.getUpperRightY(), glyphBBox.getUpperRightY()));
                        }
                    } catch (IOException unused) {
                    }
                }
            }
        }
        return new BoundingBox(fontBBox.getLowerLeftX(), fontBBox.getLowerLeftY(), fontBBox.getUpperRightX(), fontBBox.getUpperRightY());
    }

    public COSDictionary getCharProcs() {
        if (this.charProcs == null) {
            this.charProcs = this.dict.getCOSDictionary(COSName.CHAR_PROCS);
        }
        return this.charProcs;
    }

    public PDType3CharProc getCharProc(int i) {
        if (getEncoding() == null || getCharProcs() == null) {
            return null;
        }
        COSStream cOSStream = getCharProcs().getCOSStream(COSName.getPDFName(getEncoding().getName(i)));
        if (cOSStream != null) {
            return new PDType3CharProc(this, cOSStream);
        }
        return null;
    }
}
