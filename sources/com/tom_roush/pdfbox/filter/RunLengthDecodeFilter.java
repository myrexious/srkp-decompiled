package com.tom_roush.pdfbox.filter;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSDictionary;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class RunLengthDecodeFilter extends Filter {
    private static final int RUN_LENGTH_EOD = 128;

    @Override // com.tom_roush.pdfbox.filter.Filter
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        byte[] bArr = new byte[128];
        while (true) {
            int read = inputStream.read();
            if (read == -1 || read == 128) {
                break;
            }
            if (read <= 127) {
                int i2 = read + 1;
                while (i2 > 0) {
                    int read2 = inputStream.read(bArr, 0, i2);
                    if (read2 == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read2);
                    i2 -= read2;
                }
            } else {
                int read3 = inputStream.read();
                if (read3 == -1) {
                    break;
                }
                for (int i3 = 0; i3 < 257 - read; i3++) {
                    outputStream.write(read3);
                }
            }
        }
        return new DecodeResult(cOSDictionary);
    }

    @Override // com.tom_roush.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        Log.w("PdfBox-Android", "RunLengthDecodeFilter.encode is not implemented yet, skipping this stream.");
    }
}
