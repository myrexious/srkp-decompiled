package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.font.encoding.DictionaryEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.GlyphList;
import com.tom_roush.pdfbox.pdmodel.font.encoding.MacRomanEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.StandardEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class PDSimpleFont extends PDFont {
    protected Encoding encoding;
    protected GlyphList glyphList;
    private Boolean isSymbolic;
    private final Set<Integer> noUnicode;

    public abstract FontBoxFont getFontBoxFont();

    public abstract Path getPath(String str) throws IOException;

    public abstract boolean hasGlyph(String str) throws IOException;

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public boolean isVertical() {
        return false;
    }

    protected abstract Encoding readEncodingFromFont() throws IOException;

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public boolean willBeSubset() {
        return false;
    }

    public PDSimpleFont() {
        this.noUnicode = new HashSet();
    }

    public PDSimpleFont(String str) {
        super(str);
        this.noUnicode = new HashSet();
        assignGlyphList(str);
    }

    public PDSimpleFont(COSDictionary cOSDictionary) throws IOException {
        super(cOSDictionary);
        this.noUnicode = new HashSet();
    }

    public void readEncoding() throws IOException {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.ENCODING);
        if (dictionaryObject instanceof COSName) {
            COSName cOSName = (COSName) dictionaryObject;
            Encoding encoding = Encoding.getInstance(cOSName);
            this.encoding = encoding;
            if (encoding == null) {
                Log.w("PdfBox-Android", "Unknown encoding: " + cOSName.getName());
                this.encoding = readEncodingFromFont();
            }
        } else if (dictionaryObject instanceof COSDictionary) {
            COSDictionary cOSDictionary = (COSDictionary) dictionaryObject;
            Boolean symbolicFlag = getSymbolicFlag();
            COSName cOSName2 = cOSDictionary.getCOSName(COSName.BASE_ENCODING);
            Encoding readEncodingFromFont = ((cOSName2 != null && Encoding.getInstance(cOSName2) != null) || !Boolean.TRUE.equals(symbolicFlag)) ? null : readEncodingFromFont();
            if (symbolicFlag == null) {
                symbolicFlag = false;
            }
            this.encoding = new DictionaryEncoding(cOSDictionary, !symbolicFlag.booleanValue(), readEncodingFromFont);
        } else {
            this.encoding = readEncodingFromFont();
        }
        assignGlyphList(Standard14Fonts.getMappedFontName(getName()));
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public GlyphList getGlyphList() {
        return this.glyphList;
    }

    public final boolean isSymbolic() {
        if (this.isSymbolic == null) {
            Boolean isFontSymbolic = isFontSymbolic();
            if (isFontSymbolic != null) {
                this.isSymbolic = isFontSymbolic;
            } else {
                this.isSymbolic = true;
            }
        }
        return this.isSymbolic.booleanValue();
    }

    protected Boolean isFontSymbolic() {
        Boolean symbolicFlag = getSymbolicFlag();
        if (symbolicFlag != null) {
            return symbolicFlag;
        }
        boolean z = false;
        if (isStandard14()) {
            String mappedFontName = Standard14Fonts.getMappedFontName(getName());
            return Boolean.valueOf((mappedFontName.equals("Symbol") || mappedFontName.equals("ZapfDingbats")) ? true : true);
        }
        Encoding encoding = this.encoding;
        if (encoding == null) {
            if (!(this instanceof PDTrueTypeFont)) {
                throw new IllegalStateException("PDFBox bug: encoding should not be null!");
            }
            return true;
        } else if ((encoding instanceof WinAnsiEncoding) || (encoding instanceof MacRomanEncoding) || (encoding instanceof StandardEncoding)) {
            return false;
        } else {
            if (encoding instanceof DictionaryEncoding) {
                for (String str : ((DictionaryEncoding) encoding).getDifferences().values()) {
                    if (!".notdef".equals(str) && (!WinAnsiEncoding.INSTANCE.contains(str) || !MacRomanEncoding.INSTANCE.contains(str) || !StandardEncoding.INSTANCE.contains(str))) {
                        return true;
                    }
                }
                return false;
            }
            return null;
        }
    }

    public final Boolean getSymbolicFlag() {
        if (getFontDescriptor() != null) {
            return Boolean.valueOf(getFontDescriptor().isSymbolic());
        }
        return null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public String toUnicode(int i) throws IOException {
        return toUnicode(i, GlyphList.getAdobeGlyphList());
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public String toUnicode(int i, GlyphList glyphList) throws IOException {
        String str;
        if (this.glyphList != GlyphList.getAdobeGlyphList()) {
            glyphList = this.glyphList;
        }
        String unicode = super.toUnicode(i);
        if (unicode != null) {
            return unicode;
        }
        Encoding encoding = this.encoding;
        if (encoding != null) {
            str = encoding.getName(i);
            String unicode2 = glyphList.toUnicode(str);
            if (unicode2 != null) {
                return unicode2;
            }
        } else {
            str = null;
        }
        if (!this.noUnicode.contains(Integer.valueOf(i))) {
            this.noUnicode.add(Integer.valueOf(i));
            if (str != null) {
                Log.w("PdfBox-Android", "No Unicode mapping for " + str + " (" + i + ") in font " + getName());
            } else {
                Log.w("PdfBox-Android", "No Unicode mapping for character code " + i + " in font " + getName());
            }
        }
        return null;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    protected final float getStandard14Width(int i) {
        if (getStandard14AFM() != null) {
            String name = getEncoding().getName(i);
            if (".notdef".equals(name)) {
                return 250.0f;
            }
            if ("nbspace".equals(name)) {
                name = "space";
            } else if ("sfthyphen".equals(name)) {
                name = "hyphen";
            }
            return getStandard14AFM().getCharacterWidth(name);
        }
        throw new IllegalStateException("No AFM");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public boolean isStandard14() {
        if (getEncoding() instanceof DictionaryEncoding) {
            DictionaryEncoding dictionaryEncoding = (DictionaryEncoding) getEncoding();
            if (dictionaryEncoding.getDifferences().size() > 0) {
                Encoding baseEncoding = dictionaryEncoding.getBaseEncoding();
                for (Map.Entry<Integer, String> entry : dictionaryEncoding.getDifferences().entrySet()) {
                    if (!entry.getValue().equals(baseEncoding.getName(entry.getKey().intValue()))) {
                        return false;
                    }
                }
            }
        }
        return super.isStandard14();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public void addToSubset(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFont
    public void subset() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public boolean hasExplicitWidth(int i) throws IOException {
        int i2;
        return this.dict.containsKey(COSName.WIDTHS) && i >= (i2 = this.dict.getInt(COSName.FIRST_CHAR, -1)) && i - i2 < getWidths().size();
    }

    private void assignGlyphList(String str) {
        if ("ZapfDingbats".equals(str)) {
            this.glyphList = GlyphList.getZapfDingbats();
        } else {
            this.glyphList = GlyphList.getAdobeGlyphList();
        }
    }
}
