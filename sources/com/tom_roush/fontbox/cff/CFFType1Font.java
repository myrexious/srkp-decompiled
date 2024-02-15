package com.tom_roush.fontbox.cff;

import android.graphics.Path;
import com.tom_roush.fontbox.EncodedFont;
import com.tom_roush.fontbox.type1.Type1CharStringReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class CFFType1Font extends CFFFont implements EncodedFont {
    private CFFEncoding encoding;
    private final Map<String, Object> privateDict = new LinkedHashMap();
    private final Map<Integer, Type2CharString> charStringCache = new ConcurrentHashMap();
    private final PrivateType1CharStringReader reader = new PrivateType1CharStringReader();

    /* loaded from: classes3.dex */
    public class PrivateType1CharStringReader implements Type1CharStringReader {
        private PrivateType1CharStringReader() {
            CFFType1Font.this = r1;
        }

        @Override // com.tom_roush.fontbox.type1.Type1CharStringReader
        public Type1CharString getType1CharString(String str) throws IOException {
            return CFFType1Font.this.getType1CharString(str);
        }
    }

    @Override // com.tom_roush.fontbox.FontBoxFont
    public Path getPath(String str) throws IOException {
        return getType1CharString(str).getPath();
    }

    @Override // com.tom_roush.fontbox.FontBoxFont
    public float getWidth(String str) throws IOException {
        return getType1CharString(str).getWidth();
    }

    @Override // com.tom_roush.fontbox.FontBoxFont
    public boolean hasGlyph(String str) {
        return this.charset.getGIDForSID(this.charset.getSID(str)) != 0;
    }

    @Override // com.tom_roush.fontbox.cff.CFFFont, com.tom_roush.fontbox.FontBoxFont
    public List<Number> getFontMatrix() {
        return (List) this.topDict.get("FontMatrix");
    }

    public Type1CharString getType1CharString(String str) throws IOException {
        return getType2CharString(nameToGID(str), str);
    }

    public int nameToGID(String str) {
        return this.charset.getGIDForSID(this.charset.getSID(str));
    }

    @Override // com.tom_roush.fontbox.cff.CFFFont
    public Type2CharString getType2CharString(int i) throws IOException {
        return getType2CharString(i, "GID+" + i);
    }

    private Type2CharString getType2CharString(int i, String str) throws IOException {
        Type2CharString type2CharString = this.charStringCache.get(Integer.valueOf(i));
        if (type2CharString == null) {
            byte[] bArr = i < this.charStrings.length ? this.charStrings[i] : null;
            if (bArr == null) {
                bArr = this.charStrings[0];
            }
            Type2CharString type2CharString2 = new Type2CharString(this.reader, this.fontName, str, i, new Type2CharStringParser(this.fontName, str).parse(bArr, this.globalSubrIndex, getLocalSubrIndex()), getDefaultWidthX(), getNominalWidthX());
            this.charStringCache.put(Integer.valueOf(i), type2CharString2);
            return type2CharString2;
        }
        return type2CharString;
    }

    public Map<String, Object> getPrivateDict() {
        return this.privateDict;
    }

    public void addToPrivateDict(String str, Object obj) {
        if (obj != null) {
            this.privateDict.put(str, obj);
        }
    }

    @Override // com.tom_roush.fontbox.EncodedFont
    public CFFEncoding getEncoding() {
        return this.encoding;
    }

    public void setEncoding(CFFEncoding cFFEncoding) {
        this.encoding = cFFEncoding;
    }

    private byte[][] getLocalSubrIndex() {
        return (byte[][]) this.privateDict.get("Subrs");
    }

    private Object getProperty(String str) {
        Object obj = this.topDict.get(str);
        return obj != null ? obj : this.privateDict.get(str);
    }

    private int getDefaultWidthX() {
        Number number = (Number) getProperty("defaultWidthX");
        if (number == null) {
            return 1000;
        }
        return number.intValue();
    }

    private int getNominalWidthX() {
        Number number = (Number) getProperty("nominalWidthX");
        if (number == null) {
            return 0;
        }
        return number.intValue();
    }
}
