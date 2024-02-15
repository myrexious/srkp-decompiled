package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.graphics.PointF;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.cff.CFFCIDFont;
import com.tom_roush.fontbox.cff.CFFFont;
import com.tom_roush.fontbox.cff.CFFParser;
import com.tom_roush.fontbox.cff.CFFType1Font;
import com.tom_roush.fontbox.cff.Type2CharString;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class PDCIDFontType0 extends PDCIDFont {
    private Float avgWidth;
    private int[] cid2gid;
    private final CFFCIDFont cidFont;
    private BoundingBox fontBBox;
    private Matrix fontMatrix;
    private final AffineTransform fontMatrixTransform;
    private final Map<Integer, Float> glyphHeights;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final FontBoxFont t1Font;

    private float getAverageCharacterWidth() {
        return 500.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public PDCIDFontType0(com.tom_roush.pdfbox.cos.COSDictionary r7, com.tom_roush.pdfbox.pdmodel.font.PDType0Font r8) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType0.<init>(com.tom_roush.pdfbox.cos.COSDictionary, com.tom_roush.pdfbox.pdmodel.font.PDType0Font):void");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public final Matrix getFontMatrix() {
        List<Number> fontMatrix;
        if (this.fontMatrix == null) {
            CFFCIDFont cFFCIDFont = this.cidFont;
            if (cFFCIDFont != null) {
                fontMatrix = cFFCIDFont.getFontMatrix();
            } else {
                try {
                    fontMatrix = this.t1Font.getFontMatrix();
                } catch (IOException unused) {
                    return new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
                }
            }
            if (fontMatrix != null && fontMatrix.size() == 6) {
                this.fontMatrix = new Matrix(fontMatrix.get(0).floatValue(), fontMatrix.get(1).floatValue(), fontMatrix.get(2).floatValue(), fontMatrix.get(3).floatValue(), fontMatrix.get(4).floatValue(), fontMatrix.get(5).floatValue());
            } else {
                this.fontMatrix = new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
            }
        }
        return this.fontMatrix;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class FF3ByteSource implements CFFParser.ByteSource {
        private FF3ByteSource() {
            PDCIDFontType0.this = r1;
        }

        @Override // com.tom_roush.fontbox.cff.CFFParser.ByteSource
        public byte[] getBytes() throws IOException {
            return PDCIDFontType0.this.getFontDescriptor().getFontFile3().toByteArray();
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public BoundingBox getBoundingBox() {
        if (this.fontBBox == null) {
            this.fontBBox = generateBoundingBox();
        }
        return this.fontBBox;
    }

    private BoundingBox generateBoundingBox() {
        if (getFontDescriptor() != null) {
            PDRectangle fontBoundingBox = getFontDescriptor().getFontBoundingBox();
            if (fontBoundingBox.getLowerLeftX() != 0.0f || fontBoundingBox.getLowerLeftY() != 0.0f || fontBoundingBox.getUpperRightX() != 0.0f || fontBoundingBox.getUpperRightY() != 0.0f) {
                return new BoundingBox(fontBoundingBox.getLowerLeftX(), fontBoundingBox.getLowerLeftY(), fontBoundingBox.getUpperRightX(), fontBoundingBox.getUpperRightY());
            }
        }
        CFFCIDFont cFFCIDFont = this.cidFont;
        if (cFFCIDFont != null) {
            return cFFCIDFont.getFontBBox();
        }
        try {
            return this.t1Font.getFontBBox();
        } catch (IOException unused) {
            return new BoundingBox();
        }
    }

    public CFFFont getCFFFont() {
        CFFCIDFont cFFCIDFont = this.cidFont;
        if (cFFCIDFont != null) {
            return cFFCIDFont;
        }
        FontBoxFont fontBoxFont = this.t1Font;
        if (fontBoxFont instanceof CFFType1Font) {
            return (CFFType1Font) fontBoxFont;
        }
        return null;
    }

    public FontBoxFont getFontBoxFont() {
        CFFCIDFont cFFCIDFont = this.cidFont;
        return cFFCIDFont != null ? cFFCIDFont : this.t1Font;
    }

    public Type2CharString getType2CharString(int i) throws IOException {
        CFFCIDFont cFFCIDFont = this.cidFont;
        if (cFFCIDFont != null) {
            return cFFCIDFont.getType2CharString(i);
        }
        FontBoxFont fontBoxFont = this.t1Font;
        if (fontBoxFont instanceof CFFType1Font) {
            return ((CFFType1Font) fontBoxFont).getType2CharString(i);
        }
        return null;
    }

    private String getGlyphName(int i) throws IOException {
        String unicode = this.parent.toUnicode(i);
        return unicode == null ? ".notdef" : UniUtil.getUniNameOfCodePoint(unicode.codePointAt(0));
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDVectorFont
    public Path getPath(int i) throws IOException {
        int codeToCID = codeToCID(i);
        int[] iArr = this.cid2gid;
        if (iArr != null && this.isEmbedded) {
            codeToCID = iArr[codeToCID];
        }
        Type2CharString type2CharString = getType2CharString(codeToCID);
        if (type2CharString != null) {
            return type2CharString.getPath();
        }
        if (this.isEmbedded) {
            FontBoxFont fontBoxFont = this.t1Font;
            if (fontBoxFont instanceof CFFType1Font) {
                return ((CFFType1Font) fontBoxFont).getType2CharString(codeToCID).getPath();
            }
        }
        return this.t1Font.getPath(getGlyphName(i));
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDVectorFont
    public boolean hasGlyph(int i) throws IOException {
        int codeToCID = codeToCID(i);
        Type2CharString type2CharString = getType2CharString(codeToCID);
        if (type2CharString != null) {
            return type2CharString.getGID() != 0;
        }
        if (this.isEmbedded) {
            FontBoxFont fontBoxFont = this.t1Font;
            if (fontBoxFont instanceof CFFType1Font) {
                return ((CFFType1Font) fontBoxFont).getType2CharString(codeToCID).getGID() != 0;
            }
        }
        return this.t1Font.hasGlyph(getGlyphName(i));
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDCIDFont
    public int codeToCID(int i) {
        return this.parent.getCMap().toCID(i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDCIDFont
    public int codeToGID(int i) {
        int codeToCID = codeToCID(i);
        CFFCIDFont cFFCIDFont = this.cidFont;
        return cFFCIDFont != null ? cFFCIDFont.getCharset().getGIDForCID(codeToCID) : codeToCID;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDCIDFont
    public byte[] encode(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        float width;
        int width2;
        int codeToCID = codeToCID(i);
        if (this.cidFont != null) {
            width2 = getType2CharString(codeToCID).getWidth();
        } else {
            if (this.isEmbedded) {
                FontBoxFont fontBoxFont = this.t1Font;
                if (fontBoxFont instanceof CFFType1Font) {
                    width2 = ((CFFType1Font) fontBoxFont).getType2CharString(codeToCID).getWidth();
                }
            }
            width = this.t1Font.getWidth(getGlyphName(i));
            PointF pointF = new PointF(width, 0.0f);
            this.fontMatrixTransform.transform(pointF, pointF);
            return pointF.x;
        }
        width = width2;
        PointF pointF2 = new PointF(width, 0.0f);
        this.fontMatrixTransform.transform(pointF2, pointF2);
        return pointF2.x;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return this.isDamaged;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        int codeToCID = codeToCID(i);
        if (!this.glyphHeights.containsKey(Integer.valueOf(codeToCID))) {
            float height = getType2CharString(codeToCID).getBounds().height();
            this.glyphHeights.put(Integer.valueOf(codeToCID), Float.valueOf(height));
            return height;
        }
        return this.glyphHeights.get(Integer.valueOf(codeToCID)).floatValue();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDCIDFont, com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        if (this.avgWidth == null) {
            this.avgWidth = Float.valueOf(getAverageCharacterWidth());
        }
        return this.avgWidth.floatValue();
    }
}
