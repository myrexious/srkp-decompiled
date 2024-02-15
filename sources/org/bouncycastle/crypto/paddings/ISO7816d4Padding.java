package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* loaded from: classes2.dex */
public class ISO7816d4Padding implements BlockCipherPadding {
    @Override // org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] bArr, int i) {
        int length = bArr.length - i;
        bArr[i] = ByteCompanionObject.MIN_VALUE;
        while (true) {
            i++;
            if (i >= bArr.length) {
                return length;
            }
            bArr[i] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "ISO7816-4";
    }

    @Override // org.bouncycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom secureRandom) throws IllegalArgumentException {
    }

    @Override // org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int padCount(byte[] bArr) throws InvalidCipherTextException {
        int length = bArr.length;
        int i = -1;
        int i2 = -1;
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            int i3 = bArr[length] & UByte.MAX_VALUE;
            i ^= ((((i3 ^ 128) - 1) >> 31) & i2) & (length ^ i);
            i2 &= ((i3 ^ 0) - 1) >> 31;
        }
        if (i >= 0) {
            return bArr.length - i;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }
}
