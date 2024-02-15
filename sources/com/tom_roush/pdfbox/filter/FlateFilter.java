package com.tom_roush.pdfbox.filter;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class FlateFilter extends Filter {
    @Override // com.tom_roush.pdfbox.filter.Filter
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        try {
            decompress(inputStream, Predictor.wrapPredictor(outputStream, getDecodeParams(cOSDictionary, i)));
            return new DecodeResult(cOSDictionary);
        } catch (DataFormatException e) {
            Log.e("PdfBox-Android", "FlateFilter: stop reading corrupt stream due to a DataFormatException");
            throw new IOException(e);
        }
    }

    private void decompress(InputStream inputStream, OutputStream outputStream) throws IOException, DataFormatException {
        byte[] bArr = new byte[2048];
        inputStream.read();
        inputStream.read();
        int read = inputStream.read(bArr);
        if (read > 0) {
            Inflater inflater = new Inflater(true);
            inflater.setInput(bArr, 0, read);
            byte[] bArr2 = new byte[1024];
            boolean z = false;
            while (true) {
                try {
                    try {
                        int inflate = inflater.inflate(bArr2);
                        if (inflate != 0) {
                            outputStream.write(bArr2, 0, inflate);
                            z = true;
                        } else if (inflater.finished() || inflater.needsDictionary() || inputStream.available() == 0) {
                            break;
                        } else {
                            inflater.setInput(bArr, 0, inputStream.read(bArr));
                        }
                    } catch (DataFormatException e) {
                        if (z) {
                            Log.w("PdfBox-Android", "FlateFilter: premature end of stream due to a DataFormatException");
                        } else {
                            throw e;
                        }
                    }
                } finally {
                    inflater.end();
                }
            }
        }
        outputStream.flush();
    }

    @Override // com.tom_roush.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        Deflater deflater = new Deflater(getCompressionLevel());
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream, deflater);
        IOUtils.copy(inputStream, deflaterOutputStream);
        deflaterOutputStream.close();
        outputStream.flush();
        deflater.end();
    }
}
