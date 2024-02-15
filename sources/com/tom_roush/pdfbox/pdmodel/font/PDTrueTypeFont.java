package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.ttf.CmapSubtable;
import com.tom_roush.fontbox.ttf.CmapTable;
import com.tom_roush.fontbox.ttf.GlyphData;
import com.tom_roush.fontbox.ttf.PostScriptTable;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.encoding.BuiltInEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.GlyphList;
import com.tom_roush.pdfbox.pdmodel.font.encoding.MacOSRomanEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.MacRomanEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.StandardEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Type1Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class PDTrueTypeFont extends PDSimpleFont implements PDVectorFont {
    private static final Map<String, Integer> INVERTED_MACOS_ROMAN = new HashMap((int) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    private static final int START_RANGE_F000 = 61440;
    private static final int START_RANGE_F100 = 61696;
    private static final int START_RANGE_F200 = 61952;
    private boolean cmapInitialized;
    private CmapSubtable cmapMacRoman;
    private CmapSubtable cmapWinSymbol;
    private CmapSubtable cmapWinUnicode;
    private BoundingBox fontBBox;
    private Map<Integer, Integer> gidToCode;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final TrueTypeFont ttf;

    static {
        for (Map.Entry<Integer, String> entry : MacOSRomanEncoding.INSTANCE.getCodeToNameMap().entrySet()) {
            Map<String, Integer> map = INVERTED_MACOS_ROMAN;
            if (!map.containsKey(entry.getValue())) {
                map.put(entry.getValue(), entry.getKey());
            }
        }
    }

    public static PDTrueTypeFont load(PDDocument pDDocument, File file, Encoding encoding) throws IOException {
        return new PDTrueTypeFont(pDDocument, new TTFParser().parse(file), encoding, true);
    }

    public static PDTrueTypeFont load(PDDocument pDDocument, InputStream inputStream, Encoding encoding) throws IOException {
        return new PDTrueTypeFont(pDDocument, new TTFParser().parse(inputStream), encoding, true);
    }

    public static PDTrueTypeFont load(PDDocument pDDocument, TrueTypeFont trueTypeFont, Encoding encoding) throws IOException {
        return new PDTrueTypeFont(pDDocument, trueTypeFont, encoding, false);
    }

    @Deprecated
    public static PDTrueTypeFont loadTTF(PDDocument pDDocument, File file) throws IOException {
        return new PDTrueTypeFont(pDDocument, new TTFParser().parse(file), WinAnsiEncoding.INSTANCE, true);
    }

    @Deprecated
    public static PDTrueTypeFont loadTTF(PDDocument pDDocument, InputStream inputStream) throws IOException {
        return new PDTrueTypeFont(pDDocument, new TTFParser().parse(inputStream), WinAnsiEncoding.INSTANCE, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public PDTrueTypeFont(com.tom_roush.pdfbox.cos.COSDictionary r8) throws java.io.IOException {
        /*
            r7 = this;
            r7.<init>(r8)
            r8 = 0
            r7.cmapWinUnicode = r8
            r7.cmapWinSymbol = r8
            r7.cmapMacRoman = r8
            r0 = 0
            r7.cmapInitialized = r0
            com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor r1 = r7.getFontDescriptor()
            java.lang.String r2 = "PdfBox-Android"
            r3 = 1
            if (r1 == 0) goto L4d
            com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor r1 = super.getFontDescriptor()
            com.tom_roush.pdfbox.pdmodel.common.PDStream r1 = r1.getFontFile2()
            if (r1 == 0) goto L4d
            com.tom_roush.fontbox.ttf.TTFParser r4 = new com.tom_roush.fontbox.ttf.TTFParser     // Catch: java.io.IOException -> L30
            r4.<init>(r3)     // Catch: java.io.IOException -> L30
            com.tom_roush.pdfbox.cos.COSInputStream r1 = r1.createInputStream()     // Catch: java.io.IOException -> L30
            com.tom_roush.fontbox.ttf.TrueTypeFont r8 = r4.parse(r1)     // Catch: java.io.IOException -> L2e
            goto L4d
        L2e:
            r4 = move-exception
            goto L32
        L30:
            r4 = move-exception
            r1 = r8
        L32:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Could not read embedded TTF for font "
            r5.<init>(r6)
            java.lang.String r6 = r7.getBaseFont()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.util.Log.w(r2, r5, r4)
            com.tom_roush.pdfbox.io.IOUtils.closeQuietly(r1)
            r1 = r3
            goto L4e
        L4d:
            r1 = r0
        L4e:
            if (r8 == 0) goto L51
            r0 = r3
        L51:
            r7.isEmbedded = r0
            r7.isDamaged = r1
            if (r8 != 0) goto L9a
            com.tom_roush.pdfbox.pdmodel.font.FontMapper r8 = com.tom_roush.pdfbox.pdmodel.font.FontMappers.instance()
            java.lang.String r0 = r7.getBaseFont()
            com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor r1 = r7.getFontDescriptor()
            com.tom_roush.pdfbox.pdmodel.font.FontMapping r8 = r8.getTrueTypeFont(r0, r1)
            com.tom_roush.fontbox.FontBoxFont r0 = r8.getFont()
            com.tom_roush.fontbox.ttf.TrueTypeFont r0 = (com.tom_roush.fontbox.ttf.TrueTypeFont) r0
            boolean r8 = r8.isFallback()
            if (r8 == 0) goto L99
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r1 = "Using fallback font '"
            r8.<init>(r1)
            java.lang.StringBuilder r8 = r8.append(r0)
            java.lang.String r1 = "' for '"
            java.lang.StringBuilder r8 = r8.append(r1)
            java.lang.String r1 = r7.getBaseFont()
            java.lang.StringBuilder r8 = r8.append(r1)
            java.lang.String r1 = "'"
            java.lang.StringBuilder r8 = r8.append(r1)
            java.lang.String r8 = r8.toString()
            android.util.Log.w(r2, r8)
        L99:
            r8 = r0
        L9a:
            r7.ttf = r8
            r7.readEncoding()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.PDTrueTypeFont.<init>(com.tom_roush.pdfbox.cos.COSDictionary):void");
    }

    public final String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    protected Encoding readEncodingFromFont() throws IOException {
        if (!isEmbedded() && getStandard14AFM() != null) {
            return new Type1Encoding(getStandard14AFM());
        }
        if (getSymbolicFlag() != null && !getSymbolicFlag().booleanValue()) {
            return StandardEncoding.INSTANCE;
        }
        String mappedFontName = Standard14Fonts.getMappedFontName(getName());
        if (isStandard14() && !mappedFontName.equals("Symbol") && !mappedFontName.equals("ZapfDingbats")) {
            return StandardEncoding.INSTANCE;
        }
        PostScriptTable postScript = this.ttf.getPostScript();
        HashMap hashMap = new HashMap();
        for (int i = 0; i <= 256; i++) {
            int codeToGID = codeToGID(i);
            if (codeToGID > 0) {
                String name = postScript != null ? postScript.getName(codeToGID) : null;
                if (name == null) {
                    name = Integer.toString(codeToGID);
                }
                hashMap.put(Integer.valueOf(i), name);
            }
        }
        return new BuiltInEncoding(hashMap);
    }

    private PDTrueTypeFont(PDDocument pDDocument, TrueTypeFont trueTypeFont, Encoding encoding, boolean z) throws IOException {
        this.cmapWinUnicode = null;
        this.cmapWinSymbol = null;
        this.cmapMacRoman = null;
        this.cmapInitialized = false;
        PDTrueTypeFontEmbedder pDTrueTypeFontEmbedder = new PDTrueTypeFontEmbedder(pDDocument, this.dict, trueTypeFont, encoding);
        this.encoding = encoding;
        this.ttf = trueTypeFont;
        setFontDescriptor(pDTrueTypeFontEmbedder.getFontDescriptor());
        this.isEmbedded = true;
        this.isDamaged = false;
        this.glyphList = GlyphList.getAdobeGlyphList();
        if (z) {
            trueTypeFont.close();
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public String getName() {
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
        if (getFontDescriptor() != null && (fontBoundingBox = getFontDescriptor().getFontBoundingBox()) != null) {
            return new BoundingBox(fontBoundingBox.getLowerLeftX(), fontBoundingBox.getLowerLeftY(), fontBoundingBox.getUpperRightX(), fontBoundingBox.getUpperRightY());
        }
        return this.ttf.getFontBBox();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return this.isDamaged;
    }

    public TrueTypeFont getTrueTypeFont() {
        return this.ttf;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        float advanceWidth = this.ttf.getAdvanceWidth(codeToGID(i));
        float unitsPerEm = this.ttf.getUnitsPerEm();
        return unitsPerEm != 1000.0f ? advanceWidth * (1000.0f / unitsPerEm) : advanceWidth;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        GlyphData glyph = this.ttf.getGlyph().getGlyph(codeToGID(i));
        if (glyph != null) {
            return glyph.getBoundingBox().getHeight();
        }
        return 0.0f;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    protected byte[] encode(int i) throws IOException {
        if (this.encoding != null) {
            if (!this.encoding.contains(getGlyphList().codePointToName(i))) {
                throw new IllegalArgumentException(String.format("U+%04X is not available in this font's encoding: %s", Integer.valueOf(i), this.encoding.getEncodingName()));
            }
            String codePointToName = getGlyphList().codePointToName(i);
            Map<String, Integer> nameToCodeMap = this.encoding.getNameToCodeMap();
            if (this.ttf.hasGlyph(codePointToName) || this.ttf.hasGlyph(UniUtil.getUniNameOfCodePoint(i))) {
                return new byte[]{(byte) nameToCodeMap.get(codePointToName).intValue()};
            }
            throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), getName()));
        }
        String codePointToName2 = getGlyphList().codePointToName(i);
        if (!this.ttf.hasGlyph(codePointToName2)) {
            throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), getName()));
        }
        Integer num = getGIDToCode().get(Integer.valueOf(this.ttf.nameToGID(codePointToName2)));
        if (num != null) {
            return new byte[]{(byte) num.intValue()};
        }
        throw new IllegalArgumentException(String.format("U+%04X is not available in this font's Encoding", Integer.valueOf(i)));
    }

    protected Map<Integer, Integer> getGIDToCode() throws IOException {
        Map<Integer, Integer> map = this.gidToCode;
        if (map != null) {
            return map;
        }
        this.gidToCode = new HashMap();
        for (int i = 0; i <= 255; i++) {
            int codeToGID = codeToGID(i);
            if (!this.gidToCode.containsKey(Integer.valueOf(codeToGID))) {
                this.gidToCode.put(Integer.valueOf(codeToGID), Integer.valueOf(i));
            }
        }
        return this.gidToCode;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDVectorFont
    public Path getPath(int i) throws IOException {
        GlyphData glyph = this.ttf.getGlyph().getGlyph(codeToGID(i));
        if (glyph == null) {
            return new Path();
        }
        return glyph.getPath();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public Path getPath(String str) throws IOException {
        int nameToGID = this.ttf.nameToGID(str);
        if (nameToGID == 0) {
            nameToGID = 0;
            try {
                int parseInt = Integer.parseInt(str);
                if (parseInt <= this.ttf.getNumberOfGlyphs()) {
                    nameToGID = parseInt;
                }
            } catch (NumberFormatException unused) {
            }
        }
        if (nameToGID == 0) {
            return new Path();
        }
        GlyphData glyph = this.ttf.getGlyph().getGlyph(nameToGID);
        if (glyph != null) {
            return glyph.getPath();
        }
        return new Path();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public boolean hasGlyph(String str) throws IOException {
        return this.ttf.nameToGID(str) != 0;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public FontBoxFont getFontBoxFont() {
        return this.ttf;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDVectorFont
    public boolean hasGlyph(int i) throws IOException {
        return codeToGID(i) != 0;
    }

    public int codeToGID(int i) throws IOException {
        CmapSubtable cmapSubtable;
        Integer num;
        String unicode;
        extractCmapTable();
        int i2 = 0;
        if (!isSymbolic()) {
            String name = this.encoding.getName(i);
            if (".notdef".equals(name)) {
                return 0;
            }
            if (this.cmapWinUnicode != null && (unicode = GlyphList.getAdobeGlyphList().toUnicode(name)) != null) {
                i2 = this.cmapWinUnicode.getGlyphId(unicode.codePointAt(0));
            }
            if (i2 == 0 && this.cmapMacRoman != null && (num = INVERTED_MACOS_ROMAN.get(name)) != null) {
                i2 = this.cmapMacRoman.getGlyphId(num.intValue());
            }
            return i2 == 0 ? this.ttf.nameToGID(name) : i2;
        }
        if (this.cmapWinUnicode != null) {
            if ((this.encoding instanceof WinAnsiEncoding) || (this.encoding instanceof MacRomanEncoding)) {
                String name2 = this.encoding.getName(i);
                if (".notdef".equals(name2)) {
                    return 0;
                }
                String unicode2 = GlyphList.getAdobeGlyphList().toUnicode(name2);
                if (unicode2 != null) {
                    i2 = this.cmapWinUnicode.getGlyphId(unicode2.codePointAt(0));
                }
            } else {
                i2 = this.cmapWinUnicode.getGlyphId(i);
            }
        }
        CmapSubtable cmapSubtable2 = this.cmapWinSymbol;
        if (cmapSubtable2 != null) {
            int glyphId = cmapSubtable2.getGlyphId(i);
            if (i >= 0 && i <= 255) {
                if (glyphId == 0) {
                    glyphId = this.cmapWinSymbol.getGlyphId(START_RANGE_F000 + i);
                }
                if (glyphId == 0) {
                    glyphId = this.cmapWinSymbol.getGlyphId(START_RANGE_F100 + i);
                }
                if (glyphId == 0) {
                    glyphId = this.cmapWinSymbol.getGlyphId(START_RANGE_F200 + i);
                }
            }
            i2 = glyphId;
        }
        return (i2 != 0 || (cmapSubtable = this.cmapMacRoman) == null) ? i2 : cmapSubtable.getGlyphId(i);
    }

    private void extractCmapTable() throws IOException {
        CmapSubtable[] cmaps;
        if (this.cmapInitialized) {
            return;
        }
        CmapTable cmap = this.ttf.getCmap();
        if (cmap != null) {
            for (CmapSubtable cmapSubtable : cmap.getCmaps()) {
                if (3 == cmapSubtable.getPlatformId()) {
                    if (1 == cmapSubtable.getPlatformEncodingId()) {
                        this.cmapWinUnicode = cmapSubtable;
                    } else if (cmapSubtable.getPlatformEncodingId() == 0) {
                        this.cmapWinSymbol = cmapSubtable;
                    }
                } else if (1 == cmapSubtable.getPlatformId() && cmapSubtable.getPlatformEncodingId() == 0) {
                    this.cmapMacRoman = cmapSubtable;
                } else if (cmapSubtable.getPlatformId() == 0 && cmapSubtable.getPlatformEncodingId() == 0) {
                    this.cmapWinUnicode = cmapSubtable;
                } else if (cmapSubtable.getPlatformId() == 0 && 3 == cmapSubtable.getPlatformEncodingId()) {
                    this.cmapWinUnicode = cmapSubtable;
                }
            }
        }
        this.cmapInitialized = true;
    }
}
