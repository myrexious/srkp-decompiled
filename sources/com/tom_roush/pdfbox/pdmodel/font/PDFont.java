package com.tom_roush.pdfbox.pdmodel.font;

import android.util.Log;
import com.tom_roush.fontbox.afm.FontMetrics;
import com.tom_roush.fontbox.cmap.CMap;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.font.encoding.GlyphList;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public abstract class PDFont implements COSObjectable, PDFontLike {
    protected static final Matrix DEFAULT_FONT_MATRIX = new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
    private final FontMetrics afmStandard14;
    private float avgFontWidth;
    private final Map<Integer, Float> codeToWidthMap;
    protected final COSDictionary dict;
    private PDFontDescriptor fontDescriptor;
    private float fontWidthOfSpace;
    private final CMap toUnicodeCMap;
    private List<Float> widths;

    public abstract void addToSubset(int i);

    protected abstract byte[] encode(int i) throws IOException;

    protected abstract float getStandard14Width(int i);

    public abstract boolean isVertical();

    public abstract int readCode(InputStream inputStream) throws IOException;

    public abstract void subset() throws IOException;

    public abstract boolean willBeSubset();

    public PDFont() {
        this.fontWidthOfSpace = -1.0f;
        COSDictionary cOSDictionary = new COSDictionary();
        this.dict = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        this.toUnicodeCMap = null;
        this.fontDescriptor = null;
        this.afmStandard14 = null;
        this.codeToWidthMap = new HashMap();
    }

    public PDFont(String str) {
        this.fontWidthOfSpace = -1.0f;
        COSDictionary cOSDictionary = new COSDictionary();
        this.dict = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        this.toUnicodeCMap = null;
        FontMetrics afm = Standard14Fonts.getAFM(str);
        this.afmStandard14 = afm;
        if (afm == null) {
            throw new IllegalArgumentException("No AFM for font " + str);
        }
        this.fontDescriptor = PDType1FontEmbedder.buildFontDescriptor(afm);
        this.codeToWidthMap = new ConcurrentHashMap();
    }

    public PDFont(COSDictionary cOSDictionary) throws IOException {
        this.fontWidthOfSpace = -1.0f;
        this.dict = cOSDictionary;
        this.codeToWidthMap = new HashMap();
        this.afmStandard14 = Standard14Fonts.getAFM(getName());
        this.fontDescriptor = loadFontDescriptor();
        this.toUnicodeCMap = loadUnicodeCmap();
    }

    private PDFontDescriptor loadFontDescriptor() {
        COSDictionary cOSDictionary = this.dict.getCOSDictionary(COSName.FONT_DESC);
        if (cOSDictionary != null) {
            return new PDFontDescriptor(cOSDictionary);
        }
        FontMetrics fontMetrics = this.afmStandard14;
        if (fontMetrics != null) {
            return PDType1FontEmbedder.buildFontDescriptor(fontMetrics);
        }
        return null;
    }

    private CMap loadUnicodeCmap() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.TO_UNICODE);
        CMap cMap = null;
        if (dictionaryObject == null) {
            return null;
        }
        try {
            CMap readCMap = readCMap(dictionaryObject);
            if (readCMap == null || readCMap.hasUnicodeMappings()) {
                return readCMap;
            }
            Log.w("PdfBox-Android", "Invalid ToUnicode CMap in font " + getName());
            String name = readCMap.getName() != null ? readCMap.getName() : "";
            String ordering = readCMap.getOrdering() != null ? readCMap.getOrdering() : "";
            COSBase dictionaryObject2 = this.dict.getDictionaryObject(COSName.ENCODING);
            if (name.contains("Identity") || ordering.contains("Identity") || COSName.IDENTITY_H.equals(dictionaryObject2) || COSName.IDENTITY_V.equals(dictionaryObject2)) {
                cMap = CMapManager.getPredefinedCMap(COSName.IDENTITY_H.getName());
                Log.w("PdfBox-Android", "Using predefined identity CMap instead");
                return cMap;
            }
            return readCMap;
        } catch (IOException e) {
            Log.e("PdfBox-Android", "Could not read ToUnicode CMap in font " + getName(), e);
            return cMap;
        }
    }

    public final FontMetrics getStandard14AFM() {
        return this.afmStandard14;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public PDFontDescriptor getFontDescriptor() {
        return this.fontDescriptor;
    }

    public final void setFontDescriptor(PDFontDescriptor pDFontDescriptor) {
        this.fontDescriptor = pDFontDescriptor;
    }

    public final CMap readCMap(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSName) {
            return CMapManager.getPredefinedCMap(((COSName) cOSBase).getName());
        }
        if (cOSBase instanceof COSStream) {
            COSInputStream cOSInputStream = null;
            try {
                cOSInputStream = ((COSStream) cOSBase).createInputStream();
                return CMapManager.parseCMap(cOSInputStream);
            } finally {
                IOUtils.closeQuietly(cOSInputStream);
            }
        }
        throw new IOException("Expected Name or Stream");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dict;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public Vector getPositionVector(int i) {
        throw new UnsupportedOperationException("Horizontal fonts have no position vector");
    }

    public Vector getDisplacement(int i) throws IOException {
        return new Vector(getWidth(i) / 1000.0f, 0.0f);
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getWidth(int i) throws IOException {
        Float f = this.codeToWidthMap.get(Integer.valueOf(i));
        if (f != null) {
            return f.floatValue();
        }
        if (this.dict.getDictionaryObject(COSName.WIDTHS) != null || this.dict.containsKey(COSName.MISSING_WIDTH)) {
            int i2 = this.dict.getInt(COSName.FIRST_CHAR, -1);
            int i3 = this.dict.getInt(COSName.LAST_CHAR, -1);
            int size = getWidths().size();
            int i4 = i - i2;
            if (size > 0 && i >= i2 && i <= i3 && i4 < size) {
                Float f2 = getWidths().get(i4);
                if (f2 == null) {
                    f2 = Float.valueOf(0.0f);
                }
                this.codeToWidthMap.put(Integer.valueOf(i), f2);
                return f2.floatValue();
            }
            PDFontDescriptor fontDescriptor = getFontDescriptor();
            if (fontDescriptor != null) {
                Float valueOf = Float.valueOf(fontDescriptor.getMissingWidth());
                this.codeToWidthMap.put(Integer.valueOf(i), valueOf);
                return valueOf.floatValue();
            }
        }
        if (isStandard14()) {
            Float valueOf2 = Float.valueOf(getStandard14Width(i));
            this.codeToWidthMap.put(Integer.valueOf(i), valueOf2);
            return valueOf2.floatValue();
        }
        Float valueOf3 = Float.valueOf(getWidthFromFont(i));
        this.codeToWidthMap.put(Integer.valueOf(i), valueOf3);
        return valueOf3.floatValue();
    }

    public final byte[] encode(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < str.length()) {
            int codePointAt = str.codePointAt(i);
            byteArrayOutputStream.write(encode(codePointAt));
            i += Character.charCount(codePointAt);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public float getStringWidth(String str) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encode(str));
        float f = 0.0f;
        while (byteArrayInputStream.available() > 0) {
            f += getWidth(readCode(byteArrayInputStream));
        }
        return f;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        float f;
        float f2;
        float f3 = this.avgFontWidth;
        if (f3 == 0.0f) {
            COSArray cOSArray = this.dict.getCOSArray(COSName.WIDTHS);
            if (cOSArray != null) {
                f = 0.0f;
                f2 = 0.0f;
                for (int i = 0; i < cOSArray.size(); i++) {
                    COSBase object = cOSArray.getObject(i);
                    if (object instanceof COSNumber) {
                        float floatValue = ((COSNumber) object).floatValue();
                        if (floatValue > 0.0f) {
                            f += floatValue;
                            f2 += 1.0f;
                        }
                    }
                }
            } else {
                f = 0.0f;
                f2 = 0.0f;
            }
            f3 = f > 0.0f ? f / f2 : 0.0f;
            this.avgFontWidth = f3;
        }
        return f3;
    }

    public String toUnicode(int i, GlyphList glyphList) throws IOException {
        return toUnicode(i);
    }

    public String toUnicode(int i) throws IOException {
        CMap cMap = this.toUnicodeCMap;
        if (cMap != null) {
            if (cMap.getName() != null && this.toUnicodeCMap.getName().startsWith("Identity-") && ((this.dict.getDictionaryObject(COSName.TO_UNICODE) instanceof COSName) || !this.toUnicodeCMap.hasUnicodeMappings())) {
                return new String(new char[]{(char) i});
            }
            return this.toUnicodeCMap.toUnicode(i);
        }
        return null;
    }

    public String getType() {
        return this.dict.getNameAsString(COSName.TYPE);
    }

    public String getSubType() {
        return this.dict.getNameAsString(COSName.SUBTYPE);
    }

    public final List<Float> getWidths() {
        if (this.widths == null) {
            COSArray cOSArray = this.dict.getCOSArray(COSName.WIDTHS);
            if (cOSArray != null) {
                this.widths = COSArrayList.convertFloatCOSArrayToList(cOSArray);
            } else {
                this.widths = Collections.emptyList();
            }
        }
        return this.widths;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        return DEFAULT_FONT_MATRIX;
    }

    public float getSpaceWidth() {
        if (this.fontWidthOfSpace == -1.0f) {
            try {
                if (this.toUnicodeCMap != null && this.dict.containsKey(COSName.TO_UNICODE)) {
                    int spaceMapping = this.toUnicodeCMap.getSpaceMapping();
                    if (spaceMapping > -1) {
                        this.fontWidthOfSpace = getWidth(spaceMapping);
                    }
                } else {
                    this.fontWidthOfSpace = getWidth(32);
                }
                if (this.fontWidthOfSpace <= 0.0f) {
                    float widthFromFont = getWidthFromFont(32);
                    this.fontWidthOfSpace = widthFromFont;
                    if (widthFromFont <= 0.0f) {
                        this.fontWidthOfSpace = getAverageFontWidth();
                    }
                }
            } catch (Exception e) {
                Log.e("PdfBox-Android", "Can't determine the width of the space character, assuming 250", e);
                this.fontWidthOfSpace = 250.0f;
            }
        }
        return this.fontWidthOfSpace;
    }

    public boolean isStandard14() {
        if (isEmbedded()) {
            return false;
        }
        return Standard14Fonts.containsName(getName());
    }

    public boolean equals(Object obj) {
        return (obj instanceof PDFont) && ((PDFont) obj).getCOSObject() == getCOSObject();
    }

    public int hashCode() {
        return getCOSObject().hashCode();
    }

    public String toString() {
        return getClass().getSimpleName() + StringUtils.SPACE + getName();
    }

    public CMap getToUnicodeCMap() {
        return this.toUnicodeCMap;
    }
}
