package com.tom_roush.pdfbox.filter;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class CCITTFaxFilter extends Filter {
    /* JADX WARN: Code restructure failed: missing block: B:49:0x005d, code lost:
        if (r7 != 1) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00a7  */
    @Override // com.tom_roush.pdfbox.filter.Filter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.tom_roush.pdfbox.filter.DecodeResult decode(java.io.InputStream r17, java.io.OutputStream r18, com.tom_roush.pdfbox.cos.COSDictionary r19, int r20) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r19
            r3 = r20
            com.tom_roush.pdfbox.cos.COSDictionary r3 = r0.getDecodeParams(r2, r3)
            com.tom_roush.pdfbox.cos.COSName r4 = com.tom_roush.pdfbox.cos.COSName.COLUMNS
            r5 = 1728(0x6c0, float:2.421E-42)
            int r8 = r3.getInt(r4, r5)
            com.tom_roush.pdfbox.cos.COSName r4 = com.tom_roush.pdfbox.cos.COSName.ROWS
            r5 = 0
            int r4 = r3.getInt(r4, r5)
            com.tom_roush.pdfbox.cos.COSName r6 = com.tom_roush.pdfbox.cos.COSName.HEIGHT
            com.tom_roush.pdfbox.cos.COSName r7 = com.tom_roush.pdfbox.cos.COSName.H
            int r6 = r2.getInt(r6, r7, r5)
            if (r4 <= 0) goto L28
            if (r6 <= 0) goto L28
            goto L2c
        L28:
            int r6 = java.lang.Math.max(r4, r6)
        L2c:
            com.tom_roush.pdfbox.cos.COSName r4 = com.tom_roush.pdfbox.cos.COSName.K
            int r4 = r3.getInt(r4, r5)
            com.tom_roush.pdfbox.cos.COSName r7 = com.tom_roush.pdfbox.cos.COSName.ENCODED_BYTE_ALIGN
            boolean r12 = r3.getBoolean(r7, r5)
            int r7 = r8 + 7
            int r7 = r7 / 8
            int r7 = r7 * r6
            byte[] r13 = new byte[r7]
            r6 = 4
            if (r4 != 0) goto L8a
            r4 = 20
            byte[] r11 = new byte[r4]
            int r14 = r1.read(r11)
            java.io.PushbackInputStream r15 = new java.io.PushbackInputStream
            r15.<init>(r1, r4)
            r15.unread(r11, r5, r14)
            r1 = r11[r5]
            r4 = 1
            if (r1 != 0) goto L62
            r7 = r11[r4]
            int r9 = r7 >> 4
            if (r9 == r4) goto L60
            if (r7 == r4) goto L60
            goto L62
        L60:
            r7 = 3
            goto L87
        L62:
            int r1 = r1 << 8
            r7 = r11[r4]
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r1 = r1 + r7
            int r1 = r1 >> r6
            short r1 = (short) r1
            r6 = 12
        L6d:
            int r7 = r14 * 8
            if (r6 >= r7) goto L86
            int r1 = r1 << r4
            int r7 = r6 / 8
            r7 = r11[r7]
            int r9 = r6 % 8
            int r9 = 7 - r9
            int r7 = r7 >> r9
            r7 = r7 & r4
            int r1 = r1 + r7
            short r1 = (short) r1
            r7 = r1 & 4095(0xfff, float:5.738E-42)
            if (r7 != r4) goto L83
            goto L60
        L83:
            int r6 = r6 + 1
            goto L6d
        L86:
            r7 = 2
        L87:
            r9 = r7
            r7 = r15
            goto L94
        L8a:
            if (r4 <= 0) goto L92
            r6 = 1
            r10 = r6
            r9 = 3
            r7 = r1
            goto L96
        L92:
            r7 = r1
            r9 = r6
        L94:
            r10 = 0
        L96:
            com.tom_roush.pdfbox.filter.CCITTFaxDecoderStream r1 = new com.tom_roush.pdfbox.filter.CCITTFaxDecoderStream
            r6 = r1
            r6.<init>(r7, r8, r9, r10, r12)
            r0.readFromDecoderStream(r1, r13)
            com.tom_roush.pdfbox.cos.COSName r1 = com.tom_roush.pdfbox.cos.COSName.BLACK_IS_1
            boolean r1 = r3.getBoolean(r1, r5)
            if (r1 != 0) goto Laa
            r0.invertBitmap(r13)
        Laa:
            r1 = r18
            r1.write(r13)
            com.tom_roush.pdfbox.filter.DecodeResult r1 = new com.tom_roush.pdfbox.filter.DecodeResult
            r1.<init>(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.filter.CCITTFaxFilter.decode(java.io.InputStream, java.io.OutputStream, com.tom_roush.pdfbox.cos.COSDictionary, int):com.tom_roush.pdfbox.filter.DecodeResult");
    }

    void readFromDecoderStream(CCITTFaxDecoderStream cCITTFaxDecoderStream, byte[] bArr) throws IOException {
        int i = 0;
        do {
            int read = cCITTFaxDecoderStream.read(bArr, i, bArr.length - i);
            if (read <= -1) {
                return;
            }
            i += read;
        } while (i < bArr.length);
    }

    private void invertBitmap(byte[] bArr) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) ((~bArr[i]) & 255);
        }
    }

    @Override // com.tom_roush.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        IOUtils.copy(inputStream, new CCITTFaxEncoderStream(outputStream, cOSDictionary.getInt(COSName.COLUMNS), cOSDictionary.getInt(COSName.ROWS), 1));
    }
}
