package com.tom_roush.fontbox.cmap;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class CMap {
    private static final String SPACE = " ";
    private int maxCodeLength;
    private int wmode = 0;
    private String cmapName = null;
    private String cmapVersion = null;
    private int cmapType = -1;
    private String registry = null;
    private String ordering = null;
    private int supplement = 0;
    private int minCodeLength = 4;
    private final List<CodespaceRange> codespaceRanges = new ArrayList();
    private final Map<Integer, String> charToUnicode = new HashMap();
    private final Map<String, byte[]> unicodeToByteCodes = new HashMap();
    private final Map<Integer, Integer> codeToCid = new HashMap();
    private final List<CIDRange> codeToCidRanges = new ArrayList();
    private int spaceMapping = -1;

    public boolean hasCIDMappings() {
        return (this.codeToCid.isEmpty() && this.codeToCidRanges.isEmpty()) ? false : true;
    }

    public boolean hasUnicodeMappings() {
        return !this.charToUnicode.isEmpty();
    }

    public String toUnicode(int i) {
        return this.charToUnicode.get(Integer.valueOf(i));
    }

    public int readCode(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[this.maxCodeLength];
        inputStream.read(bArr, 0, this.minCodeLength);
        inputStream.mark(this.maxCodeLength);
        int i = this.minCodeLength - 1;
        while (i < this.maxCodeLength) {
            i++;
            for (CodespaceRange codespaceRange : this.codespaceRanges) {
                if (codespaceRange.isFullMatch(bArr, i)) {
                    return toInt(bArr, i);
                }
            }
            if (i < this.maxCodeLength) {
                bArr[i] = (byte) inputStream.read();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < this.maxCodeLength; i2++) {
            sb.append(String.format("0x%02X (%04o) ", Byte.valueOf(bArr[i2]), Byte.valueOf(bArr[i2])));
        }
        Log.w("PdfBox-Android", "Invalid character code sequence " + ((Object) sb) + "in CMap " + this.cmapName);
        if (inputStream.markSupported()) {
            inputStream.reset();
        } else {
            Log.w("PdfBox-Android", "mark() and reset() not supported, " + (this.maxCodeLength - 1) + " bytes have been skipped");
        }
        return toInt(bArr, this.minCodeLength);
    }

    public static int toInt(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 << 8) | (bArr[i3] & UByte.MAX_VALUE);
        }
        return i2;
    }

    public int toCID(int i) {
        Integer num = this.codeToCid.get(Integer.valueOf(i));
        if (num != null) {
            return num.intValue();
        }
        for (CIDRange cIDRange : this.codeToCidRanges) {
            int map = cIDRange.map((char) i);
            if (map != -1) {
                return map;
            }
        }
        return 0;
    }

    private int getCodeFromArray(byte[] bArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 << 8) | ((bArr[i + i4] + 256) % 256);
        }
        return i3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addCharMapping(byte[] bArr, String str) {
        this.unicodeToByteCodes.put(str, bArr.clone());
        int codeFromArray = getCodeFromArray(bArr, 0, bArr.length);
        this.charToUnicode.put(Integer.valueOf(codeFromArray), str);
        if (" ".equals(str)) {
            this.spaceMapping = codeFromArray;
        }
    }

    public byte[] getCodesFromUnicode(String str) {
        return this.unicodeToByteCodes.get(str);
    }

    public void addCIDMapping(int i, int i2) {
        this.codeToCid.put(Integer.valueOf(i2), Integer.valueOf(i));
    }

    public void addCIDRange(char c, char c2, int i) {
        CIDRange cIDRange;
        if (this.codeToCidRanges.isEmpty()) {
            cIDRange = null;
        } else {
            List<CIDRange> list = this.codeToCidRanges;
            cIDRange = list.get(list.size() - 1);
        }
        if (cIDRange == null || !cIDRange.extend(c, c2, i)) {
            this.codeToCidRanges.add(new CIDRange(c, c2, i));
        }
    }

    public void addCodespaceRange(CodespaceRange codespaceRange) {
        this.codespaceRanges.add(codespaceRange);
        this.maxCodeLength = Math.max(this.maxCodeLength, codespaceRange.getCodeLength());
        this.minCodeLength = Math.min(this.minCodeLength, codespaceRange.getCodeLength());
    }

    public void useCmap(CMap cMap) {
        for (CodespaceRange codespaceRange : cMap.codespaceRanges) {
            addCodespaceRange(codespaceRange);
        }
        this.charToUnicode.putAll(cMap.charToUnicode);
        this.codeToCid.putAll(cMap.codeToCid);
        this.codeToCidRanges.addAll(cMap.codeToCidRanges);
    }

    public int getWMode() {
        return this.wmode;
    }

    public void setWMode(int i) {
        this.wmode = i;
    }

    public String getName() {
        return this.cmapName;
    }

    public void setName(String str) {
        this.cmapName = str;
    }

    public String getVersion() {
        return this.cmapVersion;
    }

    public void setVersion(String str) {
        this.cmapVersion = str;
    }

    public int getType() {
        return this.cmapType;
    }

    public void setType(int i) {
        this.cmapType = i;
    }

    public String getRegistry() {
        return this.registry;
    }

    public void setRegistry(String str) {
        this.registry = str;
    }

    public String getOrdering() {
        return this.ordering;
    }

    public void setOrdering(String str) {
        this.ordering = str;
    }

    public int getSupplement() {
        return this.supplement;
    }

    public void setSupplement(int i) {
        this.supplement = i;
    }

    public int getSpaceMapping() {
        return this.spaceMapping;
    }

    public String toString() {
        return this.cmapName;
    }
}
