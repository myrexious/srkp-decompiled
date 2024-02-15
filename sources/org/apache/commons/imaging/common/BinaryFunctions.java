package org.apache.commons.imaging.common;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.util.logging.Logger;
import kotlin.UByte;
import org.apache.commons.imaging.ImageReadException;
import org.visp.core.VpRGBa;

/* loaded from: classes2.dex */
public final class BinaryFunctions {
    private static final Logger LOGGER = Logger.getLogger(BinaryFunctions.class.getName());

    public static int charsToQuad(char c, char c2, char c3, char c4) {
        return ((c & VpRGBa.alphaDefault) << 24) | ((c2 & VpRGBa.alphaDefault) << 16) | ((c3 & VpRGBa.alphaDefault) << 8) | ((c4 & VpRGBa.alphaDefault) << 0);
    }

    public static byte[] quadsToByteArray(int i) {
        return new byte[]{(byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) i};
    }

    private BinaryFunctions() {
    }

    public static boolean startsWith(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null || bArr == null || bArr2.length > bArr.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr2[i] != bArr[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean startsWith(byte[] bArr, BinaryConstant binaryConstant) {
        if (bArr == null || bArr.length < binaryConstant.size()) {
            return false;
        }
        for (int i = 0; i < binaryConstant.size(); i++) {
            if (bArr[i] != binaryConstant.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static byte readByte(String str, InputStream inputStream, String str2) throws IOException {
        int read = inputStream.read();
        if (read >= 0) {
            return (byte) (read & 255);
        }
        throw new IOException(str2);
    }

    public static byte[] readBytes(String str, InputStream inputStream, int i) throws IOException {
        return readBytes(str, inputStream, i, str + " could not be read.");
    }

    public static byte[] readBytes(String str, InputStream inputStream, int i, String str2) throws IOException {
        int i2 = 0;
        if (i < 0) {
            throw new IOException(String.format("%s, invalid length: %d", str2, Integer.valueOf(i)));
        }
        byte[] bArr = new byte[i];
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read < 0) {
                throw new IOException(str2 + " count: " + read + " read: " + i2 + " length: " + i);
            }
            i2 += read;
        }
        return bArr;
    }

    public static byte[] readBytes(InputStream inputStream, int i) throws IOException {
        return readBytes("", inputStream, i, "Unexpected EOF");
    }

    public static void readAndVerifyBytes(InputStream inputStream, byte[] bArr, String str) throws ImageReadException, IOException {
        for (byte b : bArr) {
            int read = inputStream.read();
            byte b2 = (byte) (read & 255);
            if (read < 0) {
                throw new ImageReadException("Unexpected EOF.");
            }
            if (b2 != b) {
                throw new ImageReadException(str);
            }
        }
    }

    public static void readAndVerifyBytes(InputStream inputStream, BinaryConstant binaryConstant, String str) throws ImageReadException, IOException {
        for (int i = 0; i < binaryConstant.size(); i++) {
            int read = inputStream.read();
            byte b = (byte) (read & 255);
            if (read < 0) {
                throw new ImageReadException("Unexpected EOF.");
            }
            if (b != binaryConstant.get(i)) {
                throw new ImageReadException(str);
            }
        }
    }

    public static void skipBytes(InputStream inputStream, long j, String str) throws IOException {
        long j2 = 0;
        while (j != j2) {
            long skip = inputStream.skip(j - j2);
            if (skip < 1) {
                throw new IOException(str + " (" + skip + ")");
            }
            j2 += skip;
        }
    }

    public static byte[] remainingBytes(String str, byte[] bArr, int i) {
        return slice(bArr, i, bArr.length - i);
    }

    public static byte[] slice(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static byte[] head(byte[] bArr, int i) {
        if (i > bArr.length) {
            i = bArr.length;
        }
        return slice(bArr, 0, i);
    }

    public static boolean compareBytes(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr.length >= i + i3 && bArr2.length >= i2 + i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                if (bArr[i + i4] != bArr2[i2 + i4]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static int read4Bytes(String str, InputStream inputStream, String str2, ByteOrder byteOrder) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        int read4 = inputStream.read();
        if ((read | read2 | read3 | read4) < 0) {
            throw new IOException(str2);
        }
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return (read << 24) | (read2 << 16) | (read3 << 8) | (read4 << 0);
        }
        return (read << 0) | (read4 << 24) | (read3 << 16) | (read2 << 8);
    }

    public static int read3Bytes(String str, InputStream inputStream, String str2, ByteOrder byteOrder) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        if ((read | read2 | read3) < 0) {
            throw new IOException(str2);
        }
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return (read << 16) | (read2 << 8) | (read3 << 0);
        }
        return (read << 0) | (read3 << 16) | (read2 << 8);
    }

    public static int read2Bytes(String str, InputStream inputStream, String str2, ByteOrder byteOrder) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if ((read | read2) >= 0) {
            return byteOrder == ByteOrder.BIG_ENDIAN ? (read << 8) | read2 : read | (read2 << 8);
        }
        throw new IOException(str2);
    }

    public static void printCharQuad(String str, int i) {
        LOGGER.finest(str + ": '" + ((char) ((i >> 24) & 255)) + ((char) ((i >> 16) & 255)) + ((char) ((i >> 8) & 255)) + ((char) ((i >> 0) & 255)) + OperatorName.SHOW_TEXT_LINE);
    }

    public static void printCharQuad(PrintWriter printWriter, String str, int i) {
        printWriter.println(str + ": '" + ((char) ((i >> 24) & 255)) + ((char) ((i >> 16) & 255)) + ((char) ((i >> 8) & 255)) + ((char) ((i >> 0) & 255)) + OperatorName.SHOW_TEXT_LINE);
    }

    public static void printByteBits(String str, byte b) {
        LOGGER.finest(str + ": '" + Integer.toBinaryString(b & UByte.MAX_VALUE));
    }

    public static boolean searchQuad(int i, InputStream inputStream) throws IOException {
        byte[] quadsToByteArray = quadsToByteArray(i);
        while (true) {
            int i2 = 0;
            while (true) {
                int read = inputStream.read();
                if (read == -1) {
                    return false;
                }
                if (quadsToByteArray[i2] == read) {
                    i2++;
                    if (i2 == quadsToByteArray.length) {
                        return true;
                    }
                }
            }
        }
    }

    public static int findNull(byte[] bArr) {
        return findNull(bArr, 0);
    }

    public static int findNull(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static byte[] getRAFBytes(RandomAccessFile randomAccessFile, long j, int i, String str) throws IOException {
        int i2 = 0;
        if (i < 0) {
            throw new IOException(String.format("%s, invalid length: %d", str, Integer.valueOf(i)));
        }
        byte[] bArr = new byte[i];
        randomAccessFile.seek(j);
        while (i2 < i) {
            int read = randomAccessFile.read(bArr, i2, i - i2);
            if (read < 0) {
                throw new IOException(str);
            }
            i2 += read;
        }
        return bArr;
    }

    public static void skipBytes(InputStream inputStream, long j) throws IOException {
        skipBytes(inputStream, j, "Couldn't skip bytes");
    }

    public static void copyStreamToStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static byte[] getStreamBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copyStreamToStream(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
