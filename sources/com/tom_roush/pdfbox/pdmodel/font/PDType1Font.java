package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;
import com.tom_roush.fontbox.EncodedFont;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.type1.Type1Font;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.StandardEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.SymbolEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Type1Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.ZapfDingbatsEncoding;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class PDType1Font extends PDSimpleFont {
    private static final Map<String, String> ALT_NAMES;
    public static final PDType1Font COURIER;
    public static final PDType1Font COURIER_BOLD;
    public static final PDType1Font COURIER_BOLD_OBLIQUE;
    public static final PDType1Font COURIER_OBLIQUE;
    public static final PDType1Font HELVETICA;
    public static final PDType1Font HELVETICA_BOLD;
    public static final PDType1Font HELVETICA_BOLD_OBLIQUE;
    public static final PDType1Font HELVETICA_OBLIQUE;
    private static final int PFB_START_MARKER = 128;
    public static final PDType1Font SYMBOL;
    public static final PDType1Font TIMES_BOLD;
    public static final PDType1Font TIMES_BOLD_ITALIC;
    public static final PDType1Font TIMES_ITALIC;
    public static final PDType1Font TIMES_ROMAN;
    public static final PDType1Font ZAPF_DINGBATS;
    private final Map<Integer, byte[]> codeToBytesMap;
    private BoundingBox fontBBox;
    private Matrix fontMatrix;
    private final AffineTransform fontMatrixTransform;
    private final FontBoxFont genericFont;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final Type1Font type1font;

    static {
        HashMap hashMap = new HashMap();
        ALT_NAMES = hashMap;
        hashMap.put("ff", "f_f");
        hashMap.put("ffi", "f_f_i");
        hashMap.put("ffl", "f_f_l");
        hashMap.put("fi", "f_i");
        hashMap.put("fl", "f_l");
        hashMap.put("st", "s_t");
        hashMap.put("IJ", "I_J");
        hashMap.put("ij", "i_j");
        hashMap.put("ellipsis", "elipsis");
        TIMES_ROMAN = new PDType1Font("Times-Roman");
        TIMES_BOLD = new PDType1Font("Times-Bold");
        TIMES_ITALIC = new PDType1Font("Times-Italic");
        TIMES_BOLD_ITALIC = new PDType1Font("Times-BoldItalic");
        HELVETICA = new PDType1Font("Helvetica");
        HELVETICA_BOLD = new PDType1Font("Helvetica-Bold");
        HELVETICA_OBLIQUE = new PDType1Font("Helvetica-Oblique");
        HELVETICA_BOLD_OBLIQUE = new PDType1Font("Helvetica-BoldOblique");
        COURIER = new PDType1Font("Courier");
        COURIER_BOLD = new PDType1Font("Courier-Bold");
        COURIER_OBLIQUE = new PDType1Font("Courier-Oblique");
        COURIER_BOLD_OBLIQUE = new PDType1Font("Courier-BoldOblique");
        SYMBOL = new PDType1Font("Symbol");
        ZAPF_DINGBATS = new PDType1Font("ZapfDingbats");
    }

    private PDType1Font(String str) {
        super(str);
        String str2;
        this.dict.setItem(COSName.SUBTYPE, (COSBase) COSName.TYPE1);
        this.dict.setName(COSName.BASE_FONT, str);
        if ("ZapfDingbats".equals(str)) {
            this.encoding = ZapfDingbatsEncoding.INSTANCE;
        } else if ("Symbol".equals(str)) {
            this.encoding = SymbolEncoding.INSTANCE;
        } else {
            this.encoding = WinAnsiEncoding.INSTANCE;
            this.dict.setItem(COSName.ENCODING, (COSBase) COSName.WIN_ANSI_ENCODING);
        }
        this.codeToBytesMap = new ConcurrentHashMap();
        this.type1font = null;
        FontMapping<FontBoxFont> fontBoxFont = FontMappers.instance().getFontBoxFont(getBaseFont(), getFontDescriptor());
        FontBoxFont font = fontBoxFont.getFont();
        this.genericFont = font;
        if (fontBoxFont.isFallback()) {
            try {
                str2 = font.getName();
            } catch (IOException unused) {
                str2 = "?";
            }
            Log.w("PdfBox-Android", "Using fallback font " + str2 + " for base font " + getBaseFont());
        }
        this.isEmbedded = false;
        this.isDamaged = false;
        this.fontMatrixTransform = new AffineTransform();
    }

    public PDType1Font(PDDocument pDDocument, InputStream inputStream) throws IOException {
        this(pDDocument, inputStream, null);
    }

    public PDType1Font(PDDocument pDDocument, InputStream inputStream, Encoding encoding) throws IOException {
        PDType1FontEmbedder pDType1FontEmbedder = new PDType1FontEmbedder(pDDocument, this.dict, inputStream, encoding);
        this.encoding = encoding == null ? pDType1FontEmbedder.getFontEncoding() : encoding;
        this.glyphList = pDType1FontEmbedder.getGlyphList();
        this.type1font = pDType1FontEmbedder.getType1Font();
        this.genericFont = pDType1FontEmbedder.getType1Font();
        this.isEmbedded = true;
        this.isDamaged = false;
        this.fontMatrixTransform = new AffineTransform();
        this.codeToBytesMap = new HashMap();
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public PDType1Font(com.tom_roush.pdfbox.cos.COSDictionary r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.PDType1Font.<init>(com.tom_roush.pdfbox.cos.COSDictionary):void");
    }

    private int repairLength1(byte[] bArr, int i) {
        int max = Math.max(0, i - 4);
        if (max <= 0 || max > bArr.length - 4) {
            max = bArr.length - 4;
        }
        int findBinaryOffsetAfterExec = findBinaryOffsetAfterExec(bArr, max);
        if (findBinaryOffsetAfterExec == 0 && i > 0) {
            findBinaryOffsetAfterExec = findBinaryOffsetAfterExec(bArr, bArr.length - 4);
        }
        if (i - findBinaryOffsetAfterExec == 0 || findBinaryOffsetAfterExec <= 0) {
            return i;
        }
        Log.w("PdfBox-Android", "Ignored invalid Length1 " + i + " for Type 1 font " + getName());
        return findBinaryOffsetAfterExec;
    }

    private static int findBinaryOffsetAfterExec(byte[] bArr, int i) {
        byte b;
        while (true) {
            if (i <= 0) {
                break;
            } else if (bArr[i + 0] == 101 && bArr[i + 1] == 120 && bArr[i + 2] == 101 && bArr[i + 3] == 99) {
                i += 4;
                while (i < bArr.length && ((b = bArr[i]) == 13 || b == 10 || b == 32 || b == 9)) {
                    i++;
                }
            } else {
                i--;
            }
        }
        return i;
    }

    private int repairLength2(byte[] bArr, int i, int i2) {
        if (i2 < 0 || i2 > bArr.length - i) {
            Log.w("PdfBox-Android", "Ignored invalid Length2 " + i2 + " for Type 1 font " + getName());
            return bArr.length - i;
        }
        return i2;
    }

    public final String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        if (getStandard14AFM() != null) {
            return getStandard14AFM().getCharacterHeight(getEncoding().getName(i));
        }
        String codeToName = codeToName(i);
        RectF rectF = new RectF();
        this.genericFont.getPath(codeToName).computeBounds(rectF, true);
        return rectF.height();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    protected byte[] encode(int i) throws IOException {
        byte[] bArr = this.codeToBytesMap.get(Integer.valueOf(i));
        if (bArr != null) {
            return bArr;
        }
        String codePointToName = getGlyphList().codePointToName(i);
        if (isStandard14()) {
            if (!this.encoding.contains(codePointToName)) {
                throw new IllegalArgumentException(String.format("U+%04X ('%s') is not available in the font %s, encoding: %s", Integer.valueOf(i), codePointToName, getName(), this.encoding.getEncodingName()));
            }
            if (".notdef".equals(codePointToName)) {
                throw new IllegalArgumentException(String.format("No glyph for U+%04X in the font %s", Integer.valueOf(i), getName()));
            }
        } else if (!this.encoding.contains(codePointToName)) {
            throw new IllegalArgumentException(String.format("U+%04X ('%s') is not available in the font %s (generic: %s), encoding: %s", Integer.valueOf(i), codePointToName, getName(), this.genericFont.getName(), this.encoding.getEncodingName()));
        } else {
            String nameInFont = getNameInFont(codePointToName);
            if (nameInFont.equals(".notdef") || !this.genericFont.hasGlyph(nameInFont)) {
                throw new IllegalArgumentException(String.format("No glyph for U+%04X in the font %s (generic: %s)", Integer.valueOf(i), getName(), this.genericFont.getName()));
            }
        }
        int intValue = this.encoding.getNameToCodeMap().get(codePointToName).intValue();
        if (intValue < 0) {
            throw new IllegalArgumentException(String.format("U+%04X ('%s') is not available in the font %s (generic: %s), encoding: %s", Integer.valueOf(i), codePointToName, getName(), this.genericFont.getName(), this.encoding.getEncodingName()));
        }
        byte[] bArr2 = {(byte) intValue};
        this.codeToBytesMap.put(Integer.valueOf(i), bArr2);
        return bArr2;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        String codeToName = codeToName(i);
        if (this.isEmbedded || !".notdef".equals(codeToName)) {
            float[] fArr = {this.genericFont.getWidth(codeToName), 0.0f};
            this.fontMatrixTransform.transform(fArr, 0, fArr, 0, 1);
            return fArr[0];
        }
        return 250.0f;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont, com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        if (getStandard14AFM() != null) {
            return getStandard14AFM().getAverageCharacterWidth();
        }
        return super.getAverageFontWidth();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
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

    public Type1Font getType1Font() {
        return this.type1font;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public FontBoxFont getFontBoxFont() {
        return this.genericFont;
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
        if (getFontDescriptor() != null && (fontBoundingBox = getFontDescriptor().getFontBoundingBox()) != null && (fontBoundingBox.getLowerLeftX() != 0.0f || fontBoundingBox.getLowerLeftY() != 0.0f || fontBoundingBox.getUpperRightX() != 0.0f || fontBoundingBox.getUpperRightY() != 0.0f)) {
            return new BoundingBox(fontBoundingBox.getLowerLeftX(), fontBoundingBox.getLowerLeftY(), fontBoundingBox.getUpperRightX(), fontBoundingBox.getUpperRightY());
        }
        return this.genericFont.getFontBBox();
    }

    public String codeToName(int i) throws IOException {
        return getNameInFont(getEncoding() != null ? getEncoding().getName(i) : ".notdef");
    }

    private String getNameInFont(String str) throws IOException {
        Integer num;
        if (isEmbedded() || this.genericFont.hasGlyph(str)) {
            return str;
        }
        String str2 = ALT_NAMES.get(str);
        if (str2 == null || str.equals(".notdef") || !this.genericFont.hasGlyph(str2)) {
            String unicode = getGlyphList().toUnicode(str);
            if (unicode != null && unicode.length() == 1) {
                String uniNameOfCodePoint = UniUtil.getUniNameOfCodePoint(unicode.codePointAt(0));
                if (this.genericFont.hasGlyph(uniNameOfCodePoint)) {
                    return uniNameOfCodePoint;
                }
                if ("SymbolMT".equals(this.genericFont.getName()) && (num = SymbolEncoding.INSTANCE.getNameToCodeMap().get(str)) != null) {
                    String uniNameOfCodePoint2 = UniUtil.getUniNameOfCodePoint(num.intValue() + 61440);
                    if (this.genericFont.hasGlyph(uniNameOfCodePoint2)) {
                        return uniNameOfCodePoint2;
                    }
                }
            }
            return ".notdef";
        }
        return str2;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public Path getPath(String str) throws IOException {
        if (str.equals(".notdef") && !this.isEmbedded) {
            return new Path();
        }
        return this.genericFont.getPath(getNameInFont(str));
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont
    public boolean hasGlyph(String str) throws IOException {
        return this.genericFont.hasGlyph(getNameInFont(str));
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
}
