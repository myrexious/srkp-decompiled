package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.fontbox.EncodedFont;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.cff.CFFParser;
import com.tom_roush.fontbox.cff.CFFType1Font;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.StandardEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Type1Encoding;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class PDType1CFont extends PDSimpleFont {
    private Float avgWidth;
    private final CFFType1Font cffFont;
    private BoundingBox fontBBox;
    private Matrix fontMatrix;
    private final AffineTransform fontMatrixTransform;
    private final FontBoxFont genericFont;
    private final Map<String, Float> glyphHeights;
    private final boolean isDamaged;
    private final boolean isEmbedded;

    private float getAverageCharacterWidth() {
        return 500.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x003f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public PDType1CFont(com.tom_roush.pdfbox.cos.COSDictionary r9) throws java.io.IOException {
        /*
            r8 = this;
            java.lang.String r0 = "Expected CFFType1Font, got "
            r8.<init>(r9)
            java.util.HashMap r9 = new java.util.HashMap
            r9.<init>()
            r8.glyphHeights = r9
            r9 = 0
            r8.avgWidth = r9
            com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor r1 = r8.getFontDescriptor()
            java.lang.String r2 = "PdfBox-Android"
            if (r1 == 0) goto L3a
            com.tom_roush.pdfbox.pdmodel.common.PDStream r3 = r1.getFontFile3()
            if (r3 == 0) goto L3a
            byte[] r3 = r3.toByteArray()
            int r4 = r3.length
            if (r4 != 0) goto L3b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Invalid data for embedded Type1C font "
            r3.<init>(r4)
            java.lang.String r4 = r8.getName()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3)
        L3a:
            r3 = r9
        L3b:
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L8d
            com.tom_roush.fontbox.cff.CFFParser r6 = new com.tom_roush.fontbox.cff.CFFParser     // Catch: java.io.IOException -> L74
            r6.<init>()     // Catch: java.io.IOException -> L74
            com.tom_roush.pdfbox.pdmodel.font.PDType1CFont$FF3ByteSource r7 = new com.tom_roush.pdfbox.pdmodel.font.PDType1CFont$FF3ByteSource     // Catch: java.io.IOException -> L74
            r7.<init>()     // Catch: java.io.IOException -> L74
            java.util.List r3 = r6.parse(r3, r7)     // Catch: java.io.IOException -> L74
            java.lang.Object r3 = r3.get(r5)     // Catch: java.io.IOException -> L74
            com.tom_roush.fontbox.cff.CFFFont r3 = (com.tom_roush.fontbox.cff.CFFFont) r3     // Catch: java.io.IOException -> L74
            boolean r6 = r3 instanceof com.tom_roush.fontbox.cff.CFFType1Font     // Catch: java.io.IOException -> L74
            if (r6 == 0) goto L5b
            com.tom_roush.fontbox.cff.CFFType1Font r3 = (com.tom_roush.fontbox.cff.CFFType1Font) r3     // Catch: java.io.IOException -> L74
            r9 = r3
            goto L8d
        L5b:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L74
            r6.<init>(r0)     // Catch: java.io.IOException -> L74
            java.lang.Class r0 = r3.getClass()     // Catch: java.io.IOException -> L74
            java.lang.String r0 = r0.getSimpleName()     // Catch: java.io.IOException -> L74
            java.lang.StringBuilder r0 = r6.append(r0)     // Catch: java.io.IOException -> L74
            java.lang.String r0 = r0.toString()     // Catch: java.io.IOException -> L74
            android.util.Log.e(r2, r0)     // Catch: java.io.IOException -> L74
            goto L8b
        L74:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r6 = "Can't read the embedded Type1C font "
            r3.<init>(r6)
            java.lang.String r6 = r8.getName()
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3, r0)
        L8b:
            r0 = r4
            goto L8e
        L8d:
            r0 = r5
        L8e:
            r8.isDamaged = r0
            r8.cffFont = r9
            if (r9 == 0) goto L99
            r8.genericFont = r9
            r8.isEmbedded = r4
            goto Ld7
        L99:
            com.tom_roush.pdfbox.pdmodel.font.FontMapper r9 = com.tom_roush.pdfbox.pdmodel.font.FontMappers.instance()
            java.lang.String r0 = r8.getBaseFont()
            com.tom_roush.pdfbox.pdmodel.font.FontMapping r9 = r9.getFontBoxFont(r0, r1)
            com.tom_roush.fontbox.FontBoxFont r0 = r9.getFont()
            r8.genericFont = r0
            boolean r9 = r9.isFallback()
            if (r9 == 0) goto Ld5
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r1 = "Using fallback font "
            r9.<init>(r1)
            java.lang.String r0 = r0.getName()
            java.lang.StringBuilder r9 = r9.append(r0)
            java.lang.String r0 = " for "
            java.lang.StringBuilder r9 = r9.append(r0)
            java.lang.String r0 = r8.getBaseFont()
            java.lang.StringBuilder r9 = r9.append(r0)
            java.lang.String r9 = r9.toString()
            android.util.Log.w(r2, r9)
        Ld5:
            r8.isEmbedded = r5
        Ld7:
            r8.readEncoding()
            com.tom_roush.pdfbox.util.Matrix r9 = r8.getFontMatrix()
            com.tom_roush.harmony.awt.geom.AffineTransform r9 = r9.createAffineTransform()
            r8.fontMatrixTransform = r9
            r0 = 4652007308841189376(0x408f400000000000, double:1000.0)
            r9.scale(r0, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.PDType1CFont.<init>(com.tom_roush.pdfbox.cos.COSDictionary):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class FF3ByteSource implements CFFParser.ByteSource {
        private FF3ByteSource() {
            PDType1CFont.this = r1;
        }

        @Override // com.tom_roush.fontbox.cff.CFFParser.ByteSource
        public byte[] getBytes() throws IOException {
            return PDType1CFont.this.getFontDescriptor().getFontFile3().toByteArray();
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public FontBoxFont getFontBoxFont() {
        return this.genericFont;
    }

    public final String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public Path getPath(String str) throws IOException {
        if (str.equals(".notdef") && !isEmbedded() && !isStandard14()) {
            return new Path();
        }
        if ("sfthyphen".equals(str)) {
            return this.genericFont.getPath("hyphen");
        }
        if ("nbspace".equals(str)) {
            if (!hasGlyph("space")) {
                return new Path();
            }
            return this.genericFont.getPath("space");
        }
        return this.genericFont.getPath(str);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public boolean hasGlyph(String str) throws IOException {
        return this.genericFont.hasGlyph(str);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public final String getName() {
        return getBaseFont();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public BoundingBox getBoundingBox() throws IOException {
        if (this.fontBBox == null) {
            this.fontBBox = generateBoundingBox();
        }
        return this.fontBBox;
    }

    private BoundingBox generateBoundingBox() throws IOException {
        PDRectangle fontBoundingBox;
        if (getFontDescriptor() != null && (fontBoundingBox = getFontDescriptor().getFontBoundingBox()) != null && (fontBoundingBox.getLowerLeftX() != 0.0f || fontBoundingBox.getLowerLeftY() != 0.0f || fontBoundingBox.getUpperRightX() != 0.0f || fontBoundingBox.getUpperRightY() != 0.0f)) {
            return new BoundingBox(fontBoundingBox.getLowerLeftX(), fontBoundingBox.getLowerLeftY(), fontBoundingBox.getUpperRightX(), fontBoundingBox.getUpperRightY());
        }
        return this.genericFont.getFontBBox();
    }

    public String codeToName(int i) {
        return getEncoding().getName(i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    protected Encoding readEncodingFromFont() throws IOException {
        if (!isEmbedded() && getStandard14AFM() != null) {
            return new Type1Encoding(getStandard14AFM());
        }
        FontBoxFont fontBoxFont = this.genericFont;
        if (fontBoxFont instanceof EncodedFont) {
            return Type1Encoding.fromFontBox(((EncodedFont) fontBoxFont).getEncoding());
        }
        return StandardEncoding.INSTANCE;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont, com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public final Matrix getFontMatrix() {
        List<Number> list;
        if (this.fontMatrix == null) {
            try {
                list = this.genericFont.getFontMatrix();
            } catch (IOException unused) {
                this.fontMatrix = DEFAULT_FONT_MATRIX;
                list = null;
            }
            if (list != null && list.size() == 6) {
                this.fontMatrix = new Matrix(list.get(0).floatValue(), list.get(1).floatValue(), list.get(2).floatValue(), list.get(3).floatValue(), list.get(4).floatValue(), list.get(5).floatValue());
            } else {
                return super.getFontMatrix();
            }
        }
        return this.fontMatrix;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return this.isDamaged;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        float[] fArr = {this.genericFont.getWidth(getNameInFont(codeToName(i))), 0.0f};
        this.fontMatrixTransform.transform(fArr, 0, fArr, 0, 1);
        return fArr[0];
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        String codeToName = codeToName(i);
        if (!this.glyphHeights.containsKey(codeToName)) {
            CFFType1Font cFFType1Font = this.cffFont;
            if (cFFType1Font == null) {
                Log.w("PdfBox-Android", "No embedded CFF font, returning 0");
                return 0.0f;
            }
            float height = cFFType1Font.getType1CharString(codeToName).getBounds().height();
            this.glyphHeights.put(codeToName, Float.valueOf(height));
            return height;
        }
        return this.glyphHeights.get(codeToName).floatValue();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    protected byte[] encode(int i) throws IOException {
        String codePointToName = getGlyphList().codePointToName(i);
        if (!this.encoding.contains(codePointToName)) {
            throw new IllegalArgumentException(String.format("U+%04X ('%s') is not available in this font's encoding: %s", Integer.valueOf(i), codePointToName, this.encoding.getEncodingName()));
        }
        String nameInFont = getNameInFont(codePointToName);
        Map<String, Integer> nameToCodeMap = this.encoding.getNameToCodeMap();
        if (nameInFont.equals(".notdef") || !this.genericFont.hasGlyph(nameInFont)) {
            throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), getName()));
        }
        return new byte[]{(byte) nameToCodeMap.get(codePointToName).intValue()};
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public float getStringWidth(String str) throws IOException {
        float f = 0.0f;
        if (this.cffFont == null) {
            Log.w("PdfBox-Android", "No embedded CFF font, returning 0");
            return 0.0f;
        }
        for (int i = 0; i < str.length(); i++) {
            f += this.cffFont.getType1CharString(getGlyphList().codePointToName(str.codePointAt(i))).getWidth();
        }
        return f;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont, com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        if (this.avgWidth == null) {
            this.avgWidth = Float.valueOf(getAverageCharacterWidth());
        }
        return this.avgWidth.floatValue();
    }

    public CFFType1Font getCFFType1Font() {
        return this.cffFont;
    }

    private String getNameInFont(String str) throws IOException {
        if (isEmbedded() || this.genericFont.hasGlyph(str)) {
            return str;
        }
        String unicode = getGlyphList().toUnicode(str);
        if (unicode != null && unicode.length() == 1) {
            String uniNameOfCodePoint = UniUtil.getUniNameOfCodePoint(unicode.codePointAt(0));
            if (this.genericFont.hasGlyph(uniNameOfCodePoint)) {
                return uniNameOfCodePoint;
            }
        }
        return ".notdef";
    }
}
