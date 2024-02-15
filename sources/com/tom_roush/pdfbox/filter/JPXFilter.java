package com.tom_roush.pdfbox.filter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import com.gemalto.jp2.JP2Decoder;
import com.gemalto.jp2.JP2Encoder;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDJPXColorSpace;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public final class JPXFilter extends Filter {
    private static final int CACHE_SIZE = 1024;

    @Override // com.tom_roush.pdfbox.filter.Filter
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i, DecodeOptions decodeOptions) throws IOException {
        DecodeResult decodeResult = new DecodeResult(new COSDictionary());
        decodeResult.getParameters().addAll(cOSDictionary);
        Bitmap readJPX = readJPX(inputStream, decodeOptions, decodeResult);
        int width = readJPX.getWidth() * readJPX.getHeight();
        int[] iArr = new int[width];
        readJPX.getPixels(iArr, 0, readJPX.getWidth(), 0, 0, readJPX.getWidth(), readJPX.getHeight());
        byte[] bArr = new byte[3072];
        int i2 = 0;
        for (int i3 = 0; i3 < width; i3++) {
            if (i2 + 3 >= 3072) {
                outputStream.write(bArr, 0, i2);
                i2 = 0;
            }
            int i4 = iArr[i3];
            bArr[i2] = (byte) Color.red(i4);
            bArr[i2 + 1] = (byte) Color.green(i4);
            bArr[i2 + 2] = (byte) Color.blue(i4);
            i2 += 3;
        }
        outputStream.write(bArr, 0, i2);
        return decodeResult;
    }

    @Override // com.tom_roush.pdfbox.filter.Filter
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        return decode(inputStream, outputStream, cOSDictionary, i, DecodeOptions.DEFAULT);
    }

    private Bitmap readJPX(InputStream inputStream, DecodeOptions decodeOptions, DecodeResult decodeResult) throws IOException {
        try {
            Class.forName("com.gemalto.jp2.JP2Decoder");
            Bitmap decode = new JP2Decoder(inputStream).decode();
            COSDictionary parameters = decodeResult.getParameters();
            if (!parameters.getBoolean(COSName.IMAGE_MASK, false)) {
                parameters.setItem(COSName.DECODE, (COSBase) null);
            }
            parameters.setInt(COSName.WIDTH, decode.getWidth());
            parameters.setInt(COSName.HEIGHT, decode.getHeight());
            if (!parameters.containsKey(COSName.COLORSPACE) && Build.VERSION.SDK_INT > 26) {
                decodeResult.setColorSpace(new PDJPXColorSpace(decode.getColorSpace()));
            }
            return decode;
        } catch (ClassNotFoundException unused) {
            throw new MissingImageReaderException("Cannot read JPX image: JP2Android is not installed.");
        }
    }

    @Override // com.tom_roush.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        IOUtils.copy(new ByteArrayInputStream(new JP2Encoder(BitmapFactory.decodeStream(inputStream)).encode()), outputStream);
        outputStream.flush();
    }
}
