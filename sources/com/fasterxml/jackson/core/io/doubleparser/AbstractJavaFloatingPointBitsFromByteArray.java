package com.fasterxml.jackson.core.io.doubleparser;

import androidx.work.WorkRequest;
import kotlin.UByte;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class AbstractJavaFloatingPointBitsFromByteArray extends AbstractFloatValueParser {
    abstract long nan();

    abstract long negativeInfinity();

    abstract long positiveInfinity();

    abstract long valueOfFloatLiteral(byte[] bArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    abstract long valueOfHexLiteral(byte[] bArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    private static int skipWhitespace(byte[] bArr, int i, int i2) {
        while (i < i2 && (bArr[i] & UByte.MAX_VALUE) <= 32) {
            i++;
        }
        return i;
    }

    private long parseDecFloatLiteral(byte[] bArr, int i, int i2, int i3, boolean z, boolean z2) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        long j;
        boolean z3;
        int i9;
        int compare;
        int tryToParseFourDigits;
        int i10 = -1;
        int i11 = i;
        long j2 = 0;
        byte b = 0;
        boolean z4 = false;
        while (true) {
            if (i11 >= i3) {
                break;
            }
            b = bArr[i11];
            if (!FastDoubleSwar.isDigit(b)) {
                if (b != 46) {
                    break;
                }
                z4 |= i10 >= 0;
                int i12 = i11;
                while (i12 < i3 - 4 && (tryToParseFourDigits = FastDoubleSwar.tryToParseFourDigits(bArr, i12 + 1)) >= 0) {
                    j2 = (j2 * WorkRequest.MIN_BACKOFF_MILLIS) + tryToParseFourDigits;
                    i12 += 4;
                }
                int i13 = i11;
                i11 = i12;
                i10 = i13;
            } else {
                j2 = ((j2 * 10) + b) - 48;
            }
            i11++;
        }
        if (i10 < 0) {
            i6 = i11 - i;
            i5 = i11;
            i4 = 0;
        } else {
            i4 = (i10 - i11) + 1;
            i5 = i10;
            i6 = (i11 - i) - 1;
        }
        if ((b | 32) == 101) {
            i7 = i11 + 1;
            byte charAt = charAt(bArr, i7, i3);
            boolean z5 = charAt == 45;
            if (z5 || charAt == 43) {
                i7++;
                charAt = charAt(bArr, i7, i3);
            }
            boolean z6 = z4 | (!FastDoubleSwar.isDigit(charAt));
            int i14 = 0;
            do {
                if (i14 < 1024) {
                    i14 = ((i14 * 10) + charAt) - 48;
                }
                i7++;
                charAt = charAt(bArr, i7, i3);
            } while (FastDoubleSwar.isDigit(charAt));
            if (z5) {
                i14 = -i14;
            }
            i4 += i14;
            z4 = z6;
            int i15 = i14;
            b = charAt;
            i8 = i15;
        } else {
            i7 = i11;
            i8 = 0;
        }
        if ((b == 100) | (b == 68) | (b == 102) | (b == 70)) {
            i7++;
        }
        int skipWhitespace = skipWhitespace(bArr, i7, i3);
        if (z4 || skipWhitespace < i3 || (!z2 && i6 == 0)) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (i6 > 19) {
            int i16 = i;
            int i17 = 0;
            long j3 = 0;
            while (i16 < i11) {
                byte b2 = bArr[i16];
                if (b2 != 46) {
                    compare = Long.compare(j3 ^ Long.MIN_VALUE, 1000000000000000000L ^ Long.MIN_VALUE);
                    if (compare >= 0) {
                        break;
                    }
                    j3 = ((j3 * 10) + b2) - 48;
                } else {
                    i17++;
                }
                i16++;
            }
            j = j3;
            z3 = i16 < i11;
            i9 = (i5 - i16) + i17 + i8;
        } else {
            j = j2;
            z3 = false;
            i9 = 0;
        }
        return valueOfFloatLiteral(bArr, i2, i3, z, j, i4, z3, i9);
    }

    public long parseFloatingPointLiteral(byte[] bArr, int i, int i2) {
        byte charAt;
        int i3 = i + i2;
        if (i < 0 || i3 < i || i3 > bArr.length || i2 > 2147483643) {
            throw new IllegalArgumentException(AbstractNumberParser.ILLEGAL_OFFSET_OR_ILLEGAL_LENGTH);
        }
        int skipWhitespace = skipWhitespace(bArr, i, i3);
        if (skipWhitespace == i3) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        byte b = bArr[skipWhitespace];
        boolean z = b == 45;
        if ((z || b == 43) && (b = charAt(bArr, (skipWhitespace = skipWhitespace + 1), i3)) == 0) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (b >= 73) {
            return parseNaNOrInfinity(bArr, skipWhitespace, i3, z);
        }
        boolean z2 = b == 48;
        if (z2 && ((charAt = charAt(bArr, (skipWhitespace = skipWhitespace + 1), i3)) == 120 || charAt == 88)) {
            return parseHexFloatingPointLiteral(bArr, skipWhitespace + 1, i, i3, z);
        }
        return parseDecFloatLiteral(bArr, skipWhitespace, i, i3, z, z2);
    }

    private long parseHexFloatingPointLiteral(byte[] bArr, int i, int i2, int i3, boolean z) {
        int min;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z2;
        int compare;
        int i9 = -1;
        int i10 = i;
        long j = 0;
        byte b = 0;
        boolean z3 = false;
        while (true) {
            if (i10 >= i3) {
                break;
            }
            b = bArr[i10];
            int lookupHex = lookupHex(b);
            if (lookupHex < 0) {
                if (lookupHex != -4) {
                    break;
                }
                z3 |= i9 >= 0;
                i9 = i10;
            } else {
                j = (j << 4) | lookupHex;
            }
            i10++;
        }
        if (i9 < 0) {
            i5 = i10 - i;
            i4 = i10;
            min = 0;
        } else {
            min = Math.min((i9 - i10) + 1, 1024) * 4;
            i4 = i9;
            i5 = (i10 - i) - 1;
        }
        boolean z4 = (b | 32) == 112;
        if (z4) {
            i6 = i10 + 1;
            byte charAt = charAt(bArr, i6, i3);
            boolean z5 = charAt == 45;
            if (z5 || charAt == 43) {
                i6++;
                charAt = charAt(bArr, i6, i3);
            }
            boolean z6 = z3 | (!FastDoubleSwar.isDigit(charAt));
            int i11 = 0;
            do {
                if (i11 < 1024) {
                    i11 = ((i11 * 10) + charAt) - 48;
                }
                i6++;
                charAt = charAt(bArr, i6, i3);
            } while (FastDoubleSwar.isDigit(charAt));
            if (z5) {
                i11 = -i11;
            }
            min += i11;
            z3 = z6;
            int i12 = i11;
            b = charAt;
            i7 = i12;
        } else {
            i6 = i10;
            i7 = 0;
        }
        if ((b == 70) | (b == 68) | (b == 100) | (b == 102)) {
            i6++;
        }
        int skipWhitespace = skipWhitespace(bArr, i6, i3);
        if (z3 || skipWhitespace < i3 || i5 == 0 || !z4) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (i5 > 16) {
            int i13 = i;
            int i14 = 0;
            j = 0;
            while (i13 < i10) {
                int lookupHex2 = lookupHex(bArr[i13]);
                if (lookupHex2 >= 0) {
                    compare = Long.compare(j ^ Long.MIN_VALUE, 1000000000000000000L ^ Long.MIN_VALUE);
                    if (compare >= 0) {
                        break;
                    }
                    j = (j << 4) | lookupHex2;
                } else {
                    i14++;
                }
                i13++;
            }
            z2 = i13 < i10;
            skipWhitespace = i13;
            i8 = i14;
        } else {
            i8 = 0;
            z2 = false;
        }
        return valueOfHexLiteral(bArr, i2, i3, z, j, min, z2, (((i4 - skipWhitespace) + i8) * 4) + i7);
    }

    private long parseNaNOrInfinity(byte[] bArr, int i, int i2, boolean z) {
        if (bArr[i] == 78) {
            int i3 = i + 2;
            if (i3 < i2 && bArr[i + 1] == 97 && bArr[i3] == 78 && skipWhitespace(bArr, i + 3, i2) == i2) {
                return nan();
            }
        } else if (i + 7 < i2 && FastDoubleSwar.readLongLE(bArr, i) == 8751735898823355977L && skipWhitespace(bArr, i + 8, i2) == i2) {
            return z ? negativeInfinity() : positiveInfinity();
        }
        throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
    }
}
