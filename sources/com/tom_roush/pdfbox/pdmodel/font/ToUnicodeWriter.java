package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.pdfbox.util.Charsets;
import com.tom_roush.pdfbox.util.Hex;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ToUnicodeWriter {
    static final int MAX_ENTRIES_PER_OPERATOR = 100;
    private final Map<Integer, String> cidToUnicode = new TreeMap();
    private int wMode = 0;

    static boolean allowCodeRange(int i, int i2) {
        if (i + 1 != i2) {
            return false;
        }
        return ((i >> 8) & 255) == ((i2 >> 8) & 255) && (i & 255) < (i2 & 255);
    }

    public void setWMode(int i) {
        this.wMode = i;
    }

    public void add(int i, String str) {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("CID is not valid");
        }
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Text is null or empty");
        }
        this.cidToUnicode.put(Integer.valueOf(i), str);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, Charsets.US_ASCII));
        writeLine(bufferedWriter, "/CIDInit /ProcSet findresource begin");
        writeLine(bufferedWriter, "12 dict begin\n");
        writeLine(bufferedWriter, "begincmap");
        writeLine(bufferedWriter, "/CIDSystemInfo");
        writeLine(bufferedWriter, "<< /Registry (Adobe)");
        writeLine(bufferedWriter, "/Ordering (UCS)");
        writeLine(bufferedWriter, "/Supplement 0");
        writeLine(bufferedWriter, ">> def\n");
        writeLine(bufferedWriter, "/CMapName /Adobe-Identity-UCS def");
        writeLine(bufferedWriter, "/CMapType 2 def\n");
        if (this.wMode != 0) {
            writeLine(bufferedWriter, "/WMode /" + this.wMode + " def");
        }
        writeLine(bufferedWriter, "1 begincodespacerange");
        writeLine(bufferedWriter, "<0000> <FFFF>");
        writeLine(bufferedWriter, "endcodespacerange\n");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Map.Entry<Integer, String> entry = null;
        for (Map.Entry<Integer, String> entry2 : this.cidToUnicode.entrySet()) {
            if (allowCIDToUnicodeRange(entry, entry2)) {
                arrayList2.set(arrayList2.size() - 1, entry2.getKey());
            } else {
                arrayList.add(entry2.getKey());
                arrayList2.add(entry2.getKey());
                arrayList3.add(entry2.getValue());
            }
            entry = entry2;
        }
        int ceil = (int) Math.ceil(arrayList.size() / 100.0d);
        int i = 0;
        while (i < ceil) {
            int size = i == ceil + (-1) ? arrayList.size() - (i * 100) : 100;
            bufferedWriter.write(size + " beginbfrange\n");
            for (int i2 = 0; i2 < size; i2++) {
                int i3 = (i * 100) + i2;
                bufferedWriter.write(60);
                bufferedWriter.write(Hex.getChars(((Integer) arrayList.get(i3)).shortValue()));
                bufferedWriter.write("> ");
                bufferedWriter.write(60);
                bufferedWriter.write(Hex.getChars(((Integer) arrayList2.get(i3)).shortValue()));
                bufferedWriter.write("> ");
                bufferedWriter.write(60);
                bufferedWriter.write(Hex.getCharsUTF16BE((String) arrayList3.get(i3)));
                bufferedWriter.write(">\n");
            }
            writeLine(bufferedWriter, "endbfrange\n");
            i++;
        }
        writeLine(bufferedWriter, "endcmap");
        writeLine(bufferedWriter, "CMapName currentdict /CMap defineresource pop");
        writeLine(bufferedWriter, "end");
        writeLine(bufferedWriter, "end");
        bufferedWriter.flush();
    }

    private void writeLine(BufferedWriter bufferedWriter, String str) throws IOException {
        bufferedWriter.write(str);
        bufferedWriter.write(10);
    }

    static boolean allowCIDToUnicodeRange(Map.Entry<Integer, String> entry, Map.Entry<Integer, String> entry2) {
        return entry != null && entry2 != null && allowCodeRange(entry.getKey().intValue(), entry2.getKey().intValue()) && allowDestinationRange(entry.getValue(), entry2.getValue());
    }

    static boolean allowDestinationRange(String str, String str2) {
        return !str.isEmpty() && !str2.isEmpty() && allowCodeRange(str.codePointAt(0), str2.codePointAt(0)) && str.codePointCount(0, str.length()) == 1;
    }
}
