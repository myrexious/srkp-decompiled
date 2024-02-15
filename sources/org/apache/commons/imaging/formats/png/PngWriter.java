package org.apache.commons.imaging.formats.png;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.DeflaterOutputStream;
import kotlin.UByte;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.formats.png.PngText;
import org.apache.commons.imaging.palette.Palette;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PngWriter {
    private void writeInt(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 24) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 0) & 255);
    }

    private void writeChunk(OutputStream outputStream, ChunkType chunkType, byte[] bArr) throws IOException {
        writeInt(outputStream, bArr == null ? 0 : bArr.length);
        outputStream.write(chunkType.array);
        if (bArr != null) {
            outputStream.write(bArr);
        }
        PngCrc pngCrc = new PngCrc();
        long start_partial_crc = pngCrc.start_partial_crc(chunkType.array, chunkType.array.length);
        if (bArr != null) {
            start_partial_crc = pngCrc.continue_partial_crc(start_partial_crc, bArr, bArr.length);
        }
        writeInt(outputStream, (int) pngCrc.finish_partial_crc(start_partial_crc));
    }

    /* loaded from: classes2.dex */
    public static class ImageHeader {
        public final byte bitDepth;
        public final byte compressionMethod;
        public final byte filterMethod;
        public final int height;
        public final InterlaceMethod interlaceMethod;
        public final PngColorType pngColorType;
        public final int width;

        ImageHeader(int i, int i2, byte b, PngColorType pngColorType, byte b2, byte b3, InterlaceMethod interlaceMethod) {
            this.width = i;
            this.height = i2;
            this.bitDepth = b;
            this.pngColorType = pngColorType;
            this.compressionMethod = b2;
            this.filterMethod = b3;
            this.interlaceMethod = interlaceMethod;
        }
    }

    private void writeChunkIHDR(OutputStream outputStream, ImageHeader imageHeader) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeInt(byteArrayOutputStream, imageHeader.width);
        writeInt(byteArrayOutputStream, imageHeader.height);
        byteArrayOutputStream.write(imageHeader.bitDepth & UByte.MAX_VALUE);
        byteArrayOutputStream.write(imageHeader.pngColorType.getValue() & 255);
        byteArrayOutputStream.write(imageHeader.compressionMethod & UByte.MAX_VALUE);
        byteArrayOutputStream.write(imageHeader.filterMethod & UByte.MAX_VALUE);
        byteArrayOutputStream.write(imageHeader.interlaceMethod.ordinal() & 255);
        writeChunk(outputStream, ChunkType.IHDR, byteArrayOutputStream.toByteArray());
    }

    private void writeChunkiTXt(OutputStream outputStream, PngText.Itxt itxt) throws IOException, ImageWriteException {
        if (!isValidISO_8859_1(itxt.keyword)) {
            throw new ImageWriteException("Png tEXt chunk keyword is not ISO-8859-1: " + itxt.keyword);
        }
        if (!isValidISO_8859_1(itxt.languageTag)) {
            throw new ImageWriteException("Png tEXt chunk language tag is not ISO-8859-1: " + itxt.languageTag);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(itxt.keyword.getBytes(StandardCharsets.ISO_8859_1));
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(1);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(itxt.languageTag.getBytes(StandardCharsets.ISO_8859_1));
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(itxt.translatedKeyword.getBytes(StandardCharsets.UTF_8));
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(deflate(itxt.text.getBytes(StandardCharsets.UTF_8)));
        writeChunk(outputStream, ChunkType.iTXt, byteArrayOutputStream.toByteArray());
    }

    private void writeChunkzTXt(OutputStream outputStream, PngText.Ztxt ztxt) throws IOException, ImageWriteException {
        if (!isValidISO_8859_1(ztxt.keyword)) {
            throw new ImageWriteException("Png zTXt chunk keyword is not ISO-8859-1: " + ztxt.keyword);
        }
        if (!isValidISO_8859_1(ztxt.text)) {
            throw new ImageWriteException("Png zTXt chunk text is not ISO-8859-1: " + ztxt.text);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(ztxt.keyword.getBytes(StandardCharsets.ISO_8859_1));
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(deflate(ztxt.text.getBytes(StandardCharsets.ISO_8859_1)));
        writeChunk(outputStream, ChunkType.zTXt, byteArrayOutputStream.toByteArray());
    }

    private void writeChunktEXt(OutputStream outputStream, PngText.Text text) throws IOException, ImageWriteException {
        if (!isValidISO_8859_1(text.keyword)) {
            throw new ImageWriteException("Png tEXt chunk keyword is not ISO-8859-1: " + text.keyword);
        }
        if (!isValidISO_8859_1(text.text)) {
            throw new ImageWriteException("Png tEXt chunk text is not ISO-8859-1: " + text.text);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(text.keyword.getBytes(StandardCharsets.ISO_8859_1));
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(text.text.getBytes(StandardCharsets.ISO_8859_1));
        writeChunk(outputStream, ChunkType.tEXt, byteArrayOutputStream.toByteArray());
    }

    private byte[] deflate(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
            deflaterOutputStream.write(bArr);
            deflaterOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private boolean isValidISO_8859_1(String str) {
        return str.equals(new String(str.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1));
    }

    private void writeChunkXmpiTXt(OutputStream outputStream, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(PngConstants.XMP_KEYWORD.getBytes(StandardCharsets.ISO_8859_1));
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(1);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(PngConstants.XMP_KEYWORD.getBytes(StandardCharsets.UTF_8));
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(deflate(str.getBytes(StandardCharsets.UTF_8)));
        writeChunk(outputStream, ChunkType.iTXt, byteArrayOutputStream.toByteArray());
    }

    private void writeChunkPLTE(OutputStream outputStream, Palette palette) throws IOException {
        int length = palette.length();
        byte[] bArr = new byte[length * 3];
        for (int i = 0; i < length; i++) {
            int entry = palette.getEntry(i);
            int i2 = i * 3;
            bArr[i2 + 0] = (byte) ((entry >> 16) & 255);
            bArr[i2 + 1] = (byte) ((entry >> 8) & 255);
            bArr[i2 + 2] = (byte) ((entry >> 0) & 255);
        }
        writeChunk(outputStream, ChunkType.PLTE, bArr);
    }

    private void writeChunkTRNS(OutputStream outputStream, Palette palette) throws IOException {
        int length = palette.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) ((palette.getEntry(i) >> 24) & 255);
        }
        writeChunk(outputStream, ChunkType.tRNS, bArr);
    }

    private void writeChunkIEND(OutputStream outputStream) throws IOException {
        writeChunk(outputStream, ChunkType.IEND, null);
    }

    private void writeChunkIDAT(OutputStream outputStream, byte[] bArr) throws IOException {
        writeChunk(outputStream, ChunkType.IDAT, bArr);
    }

    private void writeChunkPHYS(OutputStream outputStream, int i, int i2, byte b) throws IOException {
        writeChunk(outputStream, ChunkType.pHYs, new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 0) & 255), (byte) ((i2 >> 24) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 0) & 255), b});
    }

    private void writeChunkSCAL(OutputStream outputStream, double d, double d2, byte b) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(b);
        byteArrayOutputStream.write(String.valueOf(d).getBytes(StandardCharsets.ISO_8859_1));
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(String.valueOf(d2).getBytes(StandardCharsets.ISO_8859_1));
        writeChunk(outputStream, ChunkType.sCAL, byteArrayOutputStream.toByteArray());
    }

    private byte getBitDepth(PngColorType pngColorType, PngImagingParameters pngImagingParameters) {
        byte bitDepth = pngImagingParameters.getBitDepth();
        if (pngColorType.isBitDepthAllowed(bitDepth)) {
            return bitDepth;
        }
        return (byte) 8;
    }

    /* JADX WARN: Removed duplicated region for block: B:171:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0302  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeImage(java.awt.image.BufferedImage r27, java.io.OutputStream r28, org.apache.commons.imaging.formats.png.PngImagingParameters r29) throws org.apache.commons.imaging.ImageWriteException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 780
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.imaging.formats.png.PngWriter.writeImage(java.awt.image.BufferedImage, java.io.OutputStream, org.apache.commons.imaging.formats.png.PngImagingParameters):void");
    }
}
