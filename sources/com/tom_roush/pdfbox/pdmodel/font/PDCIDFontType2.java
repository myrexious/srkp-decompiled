package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.fontbox.cmap.CMap;
import com.tom_roush.fontbox.ttf.CmapLookup;
import com.tom_roush.fontbox.ttf.GlyphData;
import com.tom_roush.fontbox.ttf.OpenTypeFont;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.util.Set;

/* loaded from: classes3.dex */
public class PDCIDFontType2 extends PDCIDFont {
    private final int[] cid2gid;
    private final CmapLookup cmap;
    private BoundingBox fontBBox;
    private Matrix fontMatrix;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final Set<Integer> noMapping;
    private final TrueTypeFont ttf;

    public PDCIDFontType2(COSDictionary cOSDictionary, PDType0Font pDType0Font) throws IOException {
        this(cOSDictionary, pDType0Font, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public PDCIDFontType2(com.tom_roush.pdfbox.cos.COSDictionary r6, com.tom_roush.pdfbox.pdmodel.font.PDType0Font r7, com.tom_roush.fontbox.ttf.TrueTypeFont r8) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.String r0 = "PdfBox-Android"
            java.lang.String r1 = "Found CFF/OTF but expected embedded TTF font "
            r5.<init>(r6, r7)
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            r5.noMapping = r6
            com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor r6 = r5.getFontDescriptor()
            r7 = 0
            r2 = 1
            if (r8 == 0) goto L1e
            r5.ttf = r8
            r5.isEmbedded = r2
            r5.isDamaged = r7
            goto L87
        L1e:
            r8 = 0
            if (r6 == 0) goto L32
            com.tom_roush.pdfbox.pdmodel.common.PDStream r3 = r6.getFontFile2()
            if (r3 != 0) goto L2b
            com.tom_roush.pdfbox.pdmodel.common.PDStream r3 = r6.getFontFile3()
        L2b:
            if (r3 != 0) goto L33
            com.tom_roush.pdfbox.pdmodel.common.PDStream r3 = r6.getFontFile()
            goto L33
        L32:
            r3 = r8
        L33:
            if (r3 == 0) goto L76
            com.tom_roush.fontbox.ttf.OTFParser r4 = new com.tom_roush.fontbox.ttf.OTFParser     // Catch: java.io.IOException -> L5d
            r4.<init>(r2)     // Catch: java.io.IOException -> L5d
            com.tom_roush.pdfbox.cos.COSInputStream r3 = r3.createInputStream()     // Catch: java.io.IOException -> L5d
            com.tom_roush.fontbox.ttf.OpenTypeFont r8 = r4.parse(r3)     // Catch: java.io.IOException -> L5d
            boolean r3 = r8.isPostScript()     // Catch: java.io.IOException -> L5d
            if (r3 == 0) goto L76
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L5d
            r3.<init>(r1)     // Catch: java.io.IOException -> L5d
            java.lang.String r6 = r6.getFontName()     // Catch: java.io.IOException -> L5d
            java.lang.StringBuilder r6 = r3.append(r6)     // Catch: java.io.IOException -> L5d
            java.lang.String r6 = r6.toString()     // Catch: java.io.IOException -> L5d
            android.util.Log.w(r0, r6)     // Catch: java.io.IOException -> L5d
            goto L74
        L5d:
            r6 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Could not read embedded OTF for font "
            r1.<init>(r3)
            java.lang.String r3 = r5.getBaseFont()
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.util.Log.w(r0, r1, r6)
        L74:
            r6 = r2
            goto L77
        L76:
            r6 = r7
        L77:
            if (r8 == 0) goto L7a
            goto L7b
        L7a:
            r2 = r7
        L7b:
            r5.isEmbedded = r2
            r5.isDamaged = r6
            if (r8 != 0) goto L85
            com.tom_roush.fontbox.ttf.TrueTypeFont r8 = r5.findFontOrSubstitute()
        L85:
            r5.ttf = r8
        L87:
            com.tom_roush.fontbox.ttf.TrueTypeFont r6 = r5.ttf
            com.tom_roush.fontbox.ttf.CmapLookup r6 = r6.getUnicodeCmapLookup(r7)
            r5.cmap = r6
            int[] r6 = r5.readCIDToGIDMap()
            r5.cid2gid = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType2.<init>(com.tom_roush.pdfbox.cos.COSDictionary, com.tom_roush.pdfbox.pdmodel.font.PDType0Font, com.tom_roush.fontbox.ttf.TrueTypeFont):void");
    }

    private TrueTypeFont findFontOrSubstitute() throws IOException {
        OpenTypeFont openTypeFont;
        CIDFontMapping cIDFont = FontMappers.instance().getCIDFont(getBaseFont(), getFontDescriptor(), getCIDSystemInfo());
        if (cIDFont.isCIDFont()) {
            openTypeFont = cIDFont.getFont();
        } else {
            openTypeFont = (TrueTypeFont) cIDFont.getTrueTypeFont();
        }
        if (cIDFont.isFallback()) {
            Log.w("PdfBox-Android", "Using fallback font " + openTypeFont.getName() + " for CID-keyed TrueType font " + getBaseFont());
        }
        return openTypeFont;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        if (this.fontMatrix == null) {
            this.fontMatrix = new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
        }
        return this.fontMatrix;
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
        if (getFontDescriptor() != null && (fontBoundingBox = getFontDescriptor().getFontBoundingBox()) != null && (Float.compare(fontBoundingBox.getLowerLeftX(), 0.0f) != 0 || Float.compare(fontBoundingBox.getLowerLeftY(), 0.0f) != 0 || Float.compare(fontBoundingBox.getUpperRightX(), 0.0f) != 0 || Float.compare(fontBoundingBox.getUpperRightY(), 0.0f) != 0)) {
            return new BoundingBox(fontBoundingBox.getLowerLeftX(), fontBoundingBox.getLowerLeftY(), fontBoundingBox.getUpperRightX(), fontBoundingBox.getUpperRightY());
        }
        return this.ttf.getFontBBox();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDCIDFont
    public int codeToCID(int i) {
        String unicode;
        CMap cMap = this.parent.getCMap();
        if (!cMap.hasCIDMappings() && cMap.hasUnicodeMappings() && (unicode = cMap.toUnicode(i)) != null) {
            return unicode.codePointAt(0);
        }
        return cMap.toCID(i);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDCIDFont
    public int codeToGID(int i) throws IOException {
        if (!this.isEmbedded) {
            if (this.cid2gid != null && !this.isDamaged) {
                Log.w("PdfBox-Android", "Using non-embedded GIDs in font " + getName());
                int codeToCID = codeToCID(i);
                int[] iArr = this.cid2gid;
                if (codeToCID < iArr.length) {
                    return iArr[codeToCID];
                }
                return 0;
            }
            String unicode = this.parent.toUnicode(i);
            if (unicode == null) {
                if (!this.noMapping.contains(Integer.valueOf(i))) {
                    this.noMapping.add(Integer.valueOf(i));
                    Log.w("PdfBox-Android", "Failed to find a character mapping for " + i + " in " + getName());
                }
                return codeToCID(i);
            }
            if (unicode.length() > 1) {
                Log.w("PdfBox-Android", "Trying to map multi-byte character using 'cmap', result will be poor");
            }
            return this.cmap.getGlyphId(unicode.codePointAt(0));
        }
        int codeToCID2 = codeToCID(i);
        int[] iArr2 = this.cid2gid;
        if (iArr2 != null) {
            if (codeToCID2 < iArr2.length) {
                return iArr2[codeToCID2];
            }
            return 0;
        } else if (codeToCID2 < this.ttf.getNumberOfGlyphs()) {
            return codeToCID2;
        } else {
            return 0;
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        return (this.ttf.getHorizontalHeader().getAscender() + (-this.ttf.getHorizontalHeader().getDescender())) / this.ttf.getUnitsPerEm();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        float advanceWidth = this.ttf.getAdvanceWidth(codeToGID(i));
        int unitsPerEm = this.ttf.getUnitsPerEm();
        return unitsPerEm != 1000 ? advanceWidth * (1000.0f / unitsPerEm) : advanceWidth;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0037  */
    @Override // com.tom_roush.pdfbox.pdmodel.font.PDCIDFont
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] encode(int r7) {
        /*
            r6 = this;
            boolean r0 = r6.isEmbedded
            r1 = 0
            if (r0 == 0) goto L4d
            com.tom_roush.pdfbox.pdmodel.font.PDType0Font r0 = r6.parent
            com.tom_roush.fontbox.cmap.CMap r0 = r0.getCMap()
            java.lang.String r0 = r0.getName()
            java.lang.String r2 = "Identity-"
            boolean r0 = r0.startsWith(r2)
            r2 = -1
            if (r0 == 0) goto L21
            com.tom_roush.fontbox.ttf.CmapLookup r0 = r6.cmap
            if (r0 == 0) goto L34
            int r0 = r0.getGlyphId(r7)
            goto L35
        L21:
            com.tom_roush.pdfbox.pdmodel.font.PDType0Font r0 = r6.parent
            com.tom_roush.fontbox.cmap.CMap r0 = r0.getCMapUCS2()
            if (r0 == 0) goto L34
            com.tom_roush.pdfbox.pdmodel.font.PDType0Font r0 = r6.parent
            com.tom_roush.fontbox.cmap.CMap r0 = r0.getCMapUCS2()
            int r0 = r0.toCID(r7)
            goto L35
        L34:
            r0 = r2
        L35:
            if (r0 != r2) goto L53
            com.tom_roush.pdfbox.pdmodel.font.PDType0Font r0 = r6.parent
            com.tom_roush.fontbox.cmap.CMap r0 = r0.getToUnicodeCMap()
            if (r0 == 0) goto L4b
            char r2 = (char) r7
            java.lang.String r2 = java.lang.Character.toString(r2)
            byte[] r0 = r0.getCodesFromUnicode(r2)
            if (r0 == 0) goto L4b
            return r0
        L4b:
            r0 = r1
            goto L53
        L4d:
            com.tom_roush.fontbox.ttf.CmapLookup r0 = r6.cmap
            int r0 = r0.getGlyphId(r7)
        L53:
            r2 = 1
            r3 = 2
            if (r0 == 0) goto L66
            byte[] r7 = new byte[r3]
            int r3 = r0 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            byte r3 = (byte) r3
            r7[r1] = r3
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r7[r2] = r0
            return r7
        L66:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r7)
            r4[r1] = r5
            char r7 = (char) r7
            java.lang.Character r7 = java.lang.Character.valueOf(r7)
            r4[r2] = r7
            java.lang.String r7 = r6.getName()
            r4[r3] = r7
            java.lang.String r7 = "No glyph for U+%04X (%c) in font %s"
            java.lang.String r7 = java.lang.String.format(r7, r4)
            r0.<init>(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType2.encode(int):byte[]");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return this.isDamaged;
    }

    public TrueTypeFont getTrueTypeFont() {
        return this.ttf;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDVectorFont
    public Path getPath(int i) throws IOException {
        TrueTypeFont trueTypeFont = this.ttf;
        if ((trueTypeFont instanceof OpenTypeFont) && ((OpenTypeFont) trueTypeFont).isPostScript()) {
            return ((OpenTypeFont) this.ttf).getCFF().getFont().getType2CharString(codeToGID(i)).getPath();
        }
        GlyphData glyph = this.ttf.getGlyph().getGlyph(codeToGID(i));
        if (glyph != null) {
            return glyph.getPath();
        }
        return new Path();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDVectorFont
    public boolean hasGlyph(int i) throws IOException {
        return codeToGID(i) != 0;
    }
}
