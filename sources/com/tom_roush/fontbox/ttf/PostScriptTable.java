package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.IOException;

/* loaded from: classes3.dex */
public class PostScriptTable extends TTFTable {
    public static final String TAG = "post";
    private float formatType;
    private String[] glyphNames;
    private long isFixedPitch;
    private float italicAngle;
    private long maxMemType1;
    private long maxMemType42;
    private long mimMemType1;
    private long minMemType42;
    private short underlinePosition;
    private short underlineThickness;

    public PostScriptTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
        this.glyphNames = null;
    }

    @Override // com.tom_roush.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        String[] strArr;
        this.formatType = tTFDataStream.read32Fixed();
        this.italicAngle = tTFDataStream.read32Fixed();
        this.underlinePosition = tTFDataStream.readSignedShort();
        this.underlineThickness = tTFDataStream.readSignedShort();
        this.isFixedPitch = tTFDataStream.readUnsignedInt();
        this.minMemType42 = tTFDataStream.readUnsignedInt();
        this.maxMemType42 = tTFDataStream.readUnsignedInt();
        this.mimMemType1 = tTFDataStream.readUnsignedInt();
        this.maxMemType1 = tTFDataStream.readUnsignedInt();
        float f = this.formatType;
        int i = 0;
        if (f == 1.0f) {
            this.glyphNames = new String[WGL4Names.NUMBER_OF_MAC_GLYPHS];
            System.arraycopy(WGL4Names.MAC_GLYPH_NAMES, 0, this.glyphNames, 0, WGL4Names.NUMBER_OF_MAC_GLYPHS);
        } else if (f == 2.0f) {
            int readUnsignedShort = tTFDataStream.readUnsignedShort();
            int[] iArr = new int[readUnsignedShort];
            this.glyphNames = new String[readUnsignedShort];
            int i2 = Integer.MIN_VALUE;
            for (int i3 = 0; i3 < readUnsignedShort; i3++) {
                int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
                iArr[i3] = readUnsignedShort2;
                if (readUnsignedShort2 <= 32767) {
                    i2 = Math.max(i2, readUnsignedShort2);
                }
            }
            if (i2 >= 258) {
                int i4 = (i2 - WGL4Names.NUMBER_OF_MAC_GLYPHS) + 1;
                strArr = new String[i4];
                int i5 = 0;
                while (i5 < i4) {
                    try {
                        strArr[i5] = tTFDataStream.readString(tTFDataStream.readUnsignedByte());
                        i5++;
                    } catch (IOException e) {
                        Log.w("PdfBox-Android", "Error reading names in PostScript table at entry " + i5 + " of " + i4 + ", setting remaining entries to .notdef", e);
                        while (i5 < i4) {
                            strArr[i5] = ".notdef";
                            i5++;
                        }
                    }
                }
            } else {
                strArr = null;
            }
            while (i < readUnsignedShort) {
                int i6 = iArr[i];
                if (i6 >= 0 && i6 < 258) {
                    this.glyphNames[i] = WGL4Names.MAC_GLYPH_NAMES[i6];
                } else if (i6 >= 258 && i6 <= 32767) {
                    this.glyphNames[i] = strArr[i6 - 258];
                } else {
                    this.glyphNames[i] = ".undefined";
                }
                i++;
            }
        } else if (f == 2.5f) {
            int numberOfGlyphs = trueTypeFont.getNumberOfGlyphs();
            int[] iArr2 = new int[numberOfGlyphs];
            int i7 = 0;
            while (i7 < numberOfGlyphs) {
                int i8 = i7 + 1;
                iArr2[i7] = tTFDataStream.readSignedByte() + i8;
                i7 = i8;
            }
            this.glyphNames = new String[numberOfGlyphs];
            while (i < this.glyphNames.length) {
                int i9 = iArr2[i];
                if (i9 >= 0 && i9 < 258) {
                    String str = WGL4Names.MAC_GLYPH_NAMES[i9];
                    if (str != null) {
                        this.glyphNames[i] = str;
                    }
                } else {
                    Log.d("PdfBox-Android", "incorrect glyph name index " + i9 + ", valid numbers 0..258");
                }
                i++;
            }
        } else if (f == 3.0f) {
            Log.d("PdfBox-Android", "No PostScript name information is provided for the font " + this.font.getName());
        }
        this.initialized = true;
    }

    public float getFormatType() {
        return this.formatType;
    }

    public void setFormatType(float f) {
        this.formatType = f;
    }

    public long getIsFixedPitch() {
        return this.isFixedPitch;
    }

    public void setIsFixedPitch(long j) {
        this.isFixedPitch = j;
    }

    public float getItalicAngle() {
        return this.italicAngle;
    }

    public void setItalicAngle(float f) {
        this.italicAngle = f;
    }

    public long getMaxMemType1() {
        return this.maxMemType1;
    }

    public void setMaxMemType1(long j) {
        this.maxMemType1 = j;
    }

    public long getMaxMemType42() {
        return this.maxMemType42;
    }

    public void setMaxMemType42(long j) {
        this.maxMemType42 = j;
    }

    public long getMinMemType1() {
        return this.mimMemType1;
    }

    public void setMimMemType1(long j) {
        this.mimMemType1 = j;
    }

    public long getMinMemType42() {
        return this.minMemType42;
    }

    public void setMinMemType42(long j) {
        this.minMemType42 = j;
    }

    public short getUnderlinePosition() {
        return this.underlinePosition;
    }

    public void setUnderlinePosition(short s) {
        this.underlinePosition = s;
    }

    public short getUnderlineThickness() {
        return this.underlineThickness;
    }

    public void setUnderlineThickness(short s) {
        this.underlineThickness = s;
    }

    public String[] getGlyphNames() {
        return this.glyphNames;
    }

    public void setGlyphNames(String[] strArr) {
        this.glyphNames = strArr;
    }

    public String getName(int i) {
        String[] strArr;
        if (i < 0 || (strArr = this.glyphNames) == null || i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }
}
