package com.tom_roush.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageOutputStream;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.filter.Filter;
import com.tom_roush.pdfbox.filter.FilterFactory;
import com.tom_roush.pdfbox.io.RandomAccess;
import com.tom_roush.pdfbox.io.RandomAccessBuffer;
import com.tom_roush.pdfbox.io.RandomAccessFile;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
public final class CCITTFactory {
    private CCITTFactory() {
    }

    public static PDImageXObject createFromImage(PDDocument pDDocument, Bitmap bitmap) throws IOException {
        if (bitmap.getConfig() != Bitmap.Config.ALPHA_8) {
            throw new IllegalArgumentException("Only 1-bit b/w images supported");
        }
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int[] iArr = new int[width];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(byteArrayOutputStream);
        for (int i = 0; i < height; i++) {
            bitmap.getPixels(iArr, 0, width, 0, i, width, 1);
            for (int i2 = 0; i2 < width; i2++) {
                memoryCacheImageOutputStream.writeBits(~(iArr[i2] & 1), 1);
            }
            int bitOffset = memoryCacheImageOutputStream.getBitOffset();
            if (bitOffset != 0) {
                memoryCacheImageOutputStream.writeBits(0L, 8 - bitOffset);
            }
        }
        memoryCacheImageOutputStream.flush();
        memoryCacheImageOutputStream.close();
        return prepareImageXObject(pDDocument, byteArrayOutputStream.toByteArray(), width, height, PDDeviceGray.INSTANCE);
    }

    public static PDImageXObject createFromByteArray(PDDocument pDDocument, byte[] bArr) throws IOException {
        return createFromByteArray(pDDocument, bArr, 0);
    }

    public static PDImageXObject createFromByteArray(PDDocument pDDocument, byte[] bArr, int i) throws IOException {
        RandomAccessBuffer randomAccessBuffer = new RandomAccessBuffer(bArr);
        try {
            return createFromRandomAccessImpl(pDDocument, randomAccessBuffer, i);
        } finally {
            randomAccessBuffer.close();
        }
    }

    private static PDImageXObject prepareImageXObject(PDDocument pDDocument, byte[] bArr, int i, int i2, PDColorSpace pDColorSpace) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Filter filter = FilterFactory.INSTANCE.getFilter(COSName.CCITTFAX_DECODE);
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setInt(COSName.COLUMNS, i);
        cOSDictionary.setInt(COSName.ROWS, i2);
        filter.encode(new ByteArrayInputStream(bArr), byteArrayOutputStream, cOSDictionary, 0);
        PDImageXObject pDImageXObject = new PDImageXObject(pDDocument, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.CCITTFAX_DECODE, i, i2, 1, pDColorSpace);
        cOSDictionary.setInt(COSName.K, -1);
        pDImageXObject.getCOSObject().setItem(COSName.DECODE_PARMS, (COSBase) cOSDictionary);
        return pDImageXObject;
    }

    @Deprecated
    public static PDImageXObject createFromRandomAccess(PDDocument pDDocument, RandomAccess randomAccess) throws IOException {
        return createFromRandomAccessImpl(pDDocument, randomAccess, 0);
    }

    @Deprecated
    public static PDImageXObject createFromRandomAccess(PDDocument pDDocument, RandomAccess randomAccess, int i) throws IOException {
        return createFromRandomAccessImpl(pDDocument, randomAccess, i);
    }

    public static PDImageXObject createFromFile(PDDocument pDDocument, File file) throws IOException {
        return createFromFile(pDDocument, file, 0);
    }

    public static PDImageXObject createFromFile(PDDocument pDDocument, File file, int i) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER);
        try {
            return createFromRandomAccessImpl(pDDocument, randomAccessFile, i);
        } finally {
            randomAccessFile.close();
        }
    }

    private static PDImageXObject createFromRandomAccessImpl(PDDocument pDDocument, RandomAccess randomAccess, int i) throws IOException {
        COSDictionary cOSDictionary = new COSDictionary();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        extractFromTiff(randomAccess, byteArrayOutputStream, cOSDictionary, i);
        if (byteArrayOutputStream.size() == 0) {
            return null;
        }
        PDImageXObject pDImageXObject = new PDImageXObject(pDDocument, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.CCITTFAX_DECODE, cOSDictionary.getInt(COSName.COLUMNS), cOSDictionary.getInt(COSName.ROWS), 1, PDDeviceGray.INSTANCE);
        pDImageXObject.getCOSObject().setItem(COSName.DECODE_PARMS, (COSBase) cOSDictionary);
        return pDImageXObject;
    }

    private static void extractFromTiff(RandomAccess randomAccess, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        char read;
        int read2;
        try {
            randomAccess.seek(0L);
            read = (char) randomAccess.read();
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (((char) randomAccess.read()) != read) {
                throw new IOException("Not a valid tiff file");
            }
            if (read != 'M' && read != 'I') {
                throw new IOException("Not a valid tiff file");
            }
            if (readshort(read, randomAccess) != 42) {
                throw new IOException("Not a valid tiff file");
            }
            long readlong = readlong(read, randomAccess);
            randomAccess.seek(readlong);
            for (int i2 = 0; i2 < i; i2++) {
                int readshort = readshort(read, randomAccess);
                if (readshort > 50) {
                    throw new IOException("Not a valid tiff file");
                }
                randomAccess.seek(readlong + 2 + (readshort * 12));
                readlong = readlong(read, randomAccess);
                if (readlong != 0) {
                    randomAccess.seek(readlong);
                } else {
                    outputStream.close();
                    return;
                }
            }
            int readshort2 = readshort(read, randomAccess);
            if (readshort2 > 50) {
                throw new IOException("Not a valid tiff file");
            }
            int i3 = -1000;
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < readshort2; i6++) {
                int readshort3 = readshort(read, randomAccess);
                int readshort4 = readshort(read, randomAccess);
                int readlong2 = readlong(read, randomAccess);
                if (readshort4 == 1) {
                    read2 = randomAccess.read();
                    randomAccess.read();
                    randomAccess.read();
                    randomAccess.read();
                } else if (readshort4 == 3) {
                    read2 = readshort(read, randomAccess);
                    randomAccess.read();
                    randomAccess.read();
                } else {
                    read2 = readlong(read, randomAccess);
                }
                if (readshort3 == 256) {
                    cOSDictionary.setInt(COSName.COLUMNS, read2);
                } else if (readshort3 == 257) {
                    cOSDictionary.setInt(COSName.ROWS, read2);
                } else if (readshort3 == 259) {
                    if (read2 == 4) {
                        i3 = -1;
                    }
                    if (read2 == 3) {
                        i3 = 0;
                    }
                } else if (readshort3 != 262) {
                    if (readshort3 != 266) {
                        if (readshort3 == 279) {
                            if (readlong2 != 1) {
                            }
                            i5 = read2;
                        } else if (readshort3 == 292) {
                            if ((read2 & 1) != 0) {
                                i3 = 50;
                            }
                            if ((read2 & 4) != 0) {
                                throw new IOException("CCITT Group 3 'uncompressed mode' is not supported");
                            }
                            if ((read2 & 2) != 0) {
                                throw new IOException("CCITT Group 3 'fill bits before EOL' is not supported");
                            }
                        } else if (readshort3 == 273) {
                            if (readlong2 != 1) {
                            }
                            i4 = read2;
                        } else if (readshort3 != 274) {
                            if (readshort3 == 324) {
                                if (readlong2 != 1) {
                                }
                                i4 = read2;
                            } else if (readshort3 == 325) {
                                if (readlong2 != 1) {
                                }
                                i5 = read2;
                            }
                        } else if (read2 != 1) {
                            throw new IOException("Orientation " + read2 + " is not supported");
                        }
                    } else if (read2 != 1) {
                        throw new IOException("FillOrder " + read2 + " is not supported");
                    }
                } else if (read2 == 1) {
                    cOSDictionary.setBoolean(COSName.BLACK_IS_1, true);
                }
            }
            if (i3 == -1000) {
                throw new IOException("First image in tiff is not CCITT T4 or T6 compressed");
            }
            if (i4 == 0) {
                throw new IOException("First image in tiff is not a single tile/strip");
            }
            cOSDictionary.setInt(COSName.K, i3);
            randomAccess.seek(i4);
            byte[] bArr = new byte[8192];
            while (true) {
                int read3 = randomAccess.read(bArr, 0, Math.min(8192, i5));
                if (read3 > 0) {
                    i5 -= read3;
                    outputStream.write(bArr, 0, read3);
                } else {
                    outputStream.close();
                    return;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            outputStream.close();
            throw th;
        }
    }

    private static int readshort(char c, RandomAccess randomAccess) throws IOException {
        int read;
        int read2;
        if (c == 'I') {
            read = randomAccess.read();
            read2 = randomAccess.read() << 8;
        } else {
            read = randomAccess.read() << 8;
            read2 = randomAccess.read();
        }
        return read | read2;
    }

    private static int readlong(char c, RandomAccess randomAccess) throws IOException {
        int read;
        int read2;
        if (c == 'I') {
            read = randomAccess.read() | (randomAccess.read() << 8) | (randomAccess.read() << 16);
            read2 = randomAccess.read() << 24;
        } else {
            read = (randomAccess.read() << 24) | (randomAccess.read() << 16) | (randomAccess.read() << 8);
            read2 = randomAccess.read();
        }
        return read | read2;
    }
}
