package org.apache.commons.imaging.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;

/* loaded from: classes2.dex */
public class ZlibDeflate {
    public static byte[] decompress(byte[] bArr, int i) throws ImageReadException {
        try {
            Inflater inflater = new Inflater();
            inflater.setInput(bArr);
            byte[] bArr2 = new byte[i];
            inflater.inflate(bArr2);
            return bArr2;
        } catch (DataFormatException e) {
            throw new ImageReadException("Unable to decompress image", e);
        }
    }

    public static byte[] compress(byte[] bArr) throws ImageWriteException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length / 2);
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
            deflaterOutputStream.write(bArr);
            deflaterOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new ImageWriteException("Unable to compress image", (Throwable) e);
        }
    }
}
