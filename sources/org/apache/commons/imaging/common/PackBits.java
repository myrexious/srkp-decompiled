package org.apache.commons.imaging.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.imaging.ImageReadException;

/* loaded from: classes2.dex */
public class PackBits {
    public byte[] decompress(byte[] bArr, int i) throws ImageReadException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            if (i3 >= bArr.length) {
                throw new ImageReadException("Tiff: Unpack bits source exhausted: " + i3 + ", done + " + i2 + ", expected + " + i);
            }
            int i4 = i3 + 1;
            int i5 = bArr[i3];
            if (i5 >= 0 && i5 <= 127) {
                int i6 = i5 + 1;
                i2 += i6;
                int i7 = 0;
                while (i7 < i6) {
                    byteArrayOutputStream.write(bArr[i4]);
                    i7++;
                    i4++;
                }
            } else if (i5 >= -127 && i5 <= -1) {
                int i8 = i4 + 1;
                byte b = bArr[i4];
                int i9 = (-i5) + 1;
                i2 += i9;
                for (int i10 = 0; i10 < i9; i10++) {
                    byteArrayOutputStream.write(b);
                }
                i3 = i8;
            } else if (i5 == -128) {
                throw new ImageReadException("Packbits: " + i5);
            }
            i3 = i4;
        }
        return byteArrayOutputStream.toByteArray();
    }

    private int findNextDuplicate(byte[] bArr, int i) {
        if (i >= bArr.length) {
            return -1;
        }
        byte b = bArr[i];
        int i2 = i + 1;
        while (i2 < bArr.length) {
            byte b2 = bArr[i2];
            if (b2 == b) {
                return i2 - 1;
            }
            i2++;
            b = b2;
        }
        return -1;
    }

    private int findRunLength(byte[] bArr, int i) {
        byte b = bArr[i];
        int i2 = i + 1;
        while (i2 < bArr.length && bArr[i2] == b) {
            i2++;
        }
        return i2 - i;
    }

    public byte[] compress(byte[] bArr) throws IOException {
        int findRunLength;
        int i;
        int findNextDuplicate;
        FastByteArrayOutputStream fastByteArrayOutputStream = new FastByteArrayOutputStream(bArr.length * 2);
        int i2 = 0;
        while (i2 < bArr.length) {
            try {
                int findNextDuplicate2 = findNextDuplicate(bArr, i2);
                if (findNextDuplicate2 == i2) {
                    int min = Math.min(findRunLength(bArr, findNextDuplicate2), 128);
                    fastByteArrayOutputStream.write(-(min - 1));
                    fastByteArrayOutputStream.write(bArr[i2]);
                    i2 += min;
                } else {
                    int i3 = findNextDuplicate2 - i2;
                    if (findNextDuplicate2 > 0 && (findRunLength = findRunLength(bArr, findNextDuplicate2)) < 3 && (findNextDuplicate = findNextDuplicate(bArr, (i = i2 + i3 + findRunLength))) != i) {
                        i3 = findNextDuplicate - i2;
                        findNextDuplicate2 = findNextDuplicate;
                    }
                    if (findNextDuplicate2 < 0) {
                        i3 = bArr.length - i2;
                    }
                    int min2 = Math.min(i3, 128);
                    fastByteArrayOutputStream.write(min2 - 1);
                    for (int i4 = 0; i4 < min2; i4++) {
                        fastByteArrayOutputStream.write(bArr[i2]);
                        i2++;
                    }
                }
            } catch (Throwable th) {
                try {
                    fastByteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        byte[] byteArray = fastByteArrayOutputStream.toByteArray();
        fastByteArrayOutputStream.close();
        return byteArray;
    }
}
