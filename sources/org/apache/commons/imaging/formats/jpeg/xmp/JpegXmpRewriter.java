package org.apache.commons.imaging.formats.jpeg.xmp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.common.bytesource.ByteSourceInputStream;
import org.apache.commons.imaging.formats.jpeg.JpegConstants;
import org.apache.commons.imaging.formats.jpeg.xmp.JpegRewriter;

/* loaded from: classes2.dex */
public class JpegXmpRewriter extends JpegRewriter {
    public void removeXmpXml(File file, OutputStream outputStream) throws ImageReadException, IOException {
        removeXmpXml(new ByteSourceFile(file), outputStream);
    }

    public void removeXmpXml(byte[] bArr, OutputStream outputStream) throws ImageReadException, IOException {
        removeXmpXml(new ByteSourceArray(bArr), outputStream);
    }

    public void removeXmpXml(InputStream inputStream, OutputStream outputStream) throws ImageReadException, IOException {
        removeXmpXml(new ByteSourceInputStream(inputStream, null), outputStream);
    }

    public void removeXmpXml(ByteSource byteSource, OutputStream outputStream) throws ImageReadException, IOException {
        writeSegments(outputStream, removeXmpSegments(analyzeJFIF(byteSource).pieces));
    }

    public void updateXmpXml(byte[] bArr, OutputStream outputStream, String str) throws ImageReadException, IOException, ImageWriteException {
        updateXmpXml(new ByteSourceArray(bArr), outputStream, str);
    }

    public void updateXmpXml(InputStream inputStream, OutputStream outputStream, String str) throws ImageReadException, IOException, ImageWriteException {
        updateXmpXml(new ByteSourceInputStream(inputStream, null), outputStream, str);
    }

    public void updateXmpXml(File file, OutputStream outputStream, String str) throws ImageReadException, IOException, ImageWriteException {
        updateXmpXml(new ByteSourceFile(file), outputStream, str);
    }

    public void updateXmpXml(ByteSource byteSource, OutputStream outputStream, String str) throws ImageReadException, IOException, ImageWriteException {
        List removeXmpSegments = removeXmpSegments(analyzeJFIF(byteSource).pieces);
        ArrayList arrayList = new ArrayList();
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int i = 0;
        while (i < bytes.length) {
            int min = Math.min(bytes.length, 65535);
            arrayList.add(new JpegRewriter.JFIFPieceSegment(JpegConstants.JPEG_APP1_MARKER, writeXmpSegment(bytes, i, min)));
            i += min;
        }
        writeSegments(outputStream, insertAfterLastAppSegments(removeXmpSegments, arrayList));
    }

    private byte[] writeXmpSegment(byte[] bArr, int i, int i2) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JpegConstants.XMP_IDENTIFIER.writeTo(byteArrayOutputStream);
        byteArrayOutputStream.write(bArr, i, i2);
        return byteArrayOutputStream.toByteArray();
    }
}
