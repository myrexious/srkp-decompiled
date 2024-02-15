package org.apache.commons.imaging.formats.icns;

import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class Rle24Compression {
    private Rle24Compression() {
    }

    public static byte[] decompress(int i, int i2, byte[] bArr) {
        int i3;
        int i4 = i * i2;
        byte[] bArr2 = new byte[i4 * 4];
        int i5 = (i < 128 || i2 < 128) ? 0 : 4;
        for (int i6 = 1; i6 <= 3; i6++) {
            int i7 = i4;
            int i8 = 0;
            while (i7 > 0) {
                byte b = bArr[i5];
                if ((b & ByteCompanionObject.MIN_VALUE) != 0) {
                    i3 = (b & UByte.MAX_VALUE) - 125;
                    int i9 = 0;
                    while (i9 < i3) {
                        bArr2[(i8 * 4) + i6] = bArr[i5 + 1];
                        i9++;
                        i8++;
                    }
                    i5 += 2;
                } else {
                    i3 = (b & UByte.MAX_VALUE) + 1;
                    i5++;
                    int i10 = 0;
                    while (i10 < i3) {
                        bArr2[(i8 * 4) + i6] = bArr[i5];
                        i10++;
                        i8++;
                        i5++;
                    }
                }
                i7 -= i3;
            }
        }
        return bArr2;
    }
}
