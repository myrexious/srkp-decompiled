package com.tom_roush.pdfbox.filter;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.util.Hex;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ASCIIHexFilter extends Filter {
    private static final int[] REVERSE_HEX = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    private boolean isEOD(int i) {
        return i == 62;
    }

    private boolean isWhitespace(int i) {
        return i == 0 || i == 9 || i == 10 || i == 12 || i == 13 || i == 32;
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x0075, code lost:
        r9.write(r11);
     */
    @Override // com.tom_roush.pdfbox.filter.Filter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.tom_roush.pdfbox.filter.DecodeResult decode(java.io.InputStream r8, java.io.OutputStream r9, com.tom_roush.pdfbox.cos.COSDictionary r10, int r11) throws java.io.IOException {
        /*
            r7 = this;
        L0:
            int r11 = r8.read()
            r0 = -1
            if (r11 == r0) goto L78
        L7:
            boolean r1 = r7.isWhitespace(r11)
            if (r1 == 0) goto L12
            int r11 = r8.read()
            goto L7
        L12:
            if (r11 == r0) goto L78
            boolean r1 = r7.isEOD(r11)
            if (r1 == 0) goto L1b
            goto L78
        L1b:
            int[] r1 = com.tom_roush.pdfbox.filter.ASCIIHexFilter.REVERSE_HEX
            r2 = r1[r11]
            java.lang.String r3 = " char: "
            java.lang.String r4 = "Invalid hex, int: "
            java.lang.String r5 = "PdfBox-Android"
            if (r2 != r0) goto L40
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r4)
            java.lang.StringBuilder r2 = r2.append(r11)
            java.lang.StringBuilder r2 = r2.append(r3)
            char r6 = (char) r11
            java.lang.StringBuilder r2 = r2.append(r6)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r5, r2)
        L40:
            r11 = r1[r11]
            int r11 = r11 * 16
            int r2 = r8.read()
            if (r2 == r0) goto L75
            boolean r6 = r7.isEOD(r2)
            if (r6 == 0) goto L51
            goto L75
        L51:
            r6 = r1[r2]
            if (r6 != r0) goto L6e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r4)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r3)
            char r3 = (char) r2
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r5, r0)
        L6e:
            r0 = r1[r2]
            int r11 = r11 + r0
            r9.write(r11)
            goto L0
        L75:
            r9.write(r11)
        L78:
            r9.flush()
            com.tom_roush.pdfbox.filter.DecodeResult r8 = new com.tom_roush.pdfbox.filter.DecodeResult
            r8.<init>(r10)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.filter.ASCIIHexFilter.decode(java.io.InputStream, java.io.OutputStream, com.tom_roush.pdfbox.cos.COSDictionary, int):com.tom_roush.pdfbox.filter.DecodeResult");
    }

    @Override // com.tom_roush.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        while (true) {
            int read = inputStream.read();
            if (read != -1) {
                Hex.writeHexByte((byte) read, outputStream);
            } else {
                outputStream.flush();
                return;
            }
        }
    }
}
