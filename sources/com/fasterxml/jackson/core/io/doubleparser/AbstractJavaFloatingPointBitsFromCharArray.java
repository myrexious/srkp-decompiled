package com.fasterxml.jackson.core.io.doubleparser;

import androidx.work.WorkRequest;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class AbstractJavaFloatingPointBitsFromCharArray extends AbstractFloatValueParser {
    private static final boolean CONDITIONAL_COMPILATION_PARSE_EIGHT_HEX_DIGITS = true;

    abstract long nan();

    abstract long negativeInfinity();

    abstract long positiveInfinity();

    abstract long valueOfFloatLiteral(char[] cArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    abstract long valueOfHexLiteral(char[] cArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    private static int skipWhitespace(char[] cArr, int i, int i2) {
        while (i < i2 && cArr[i] <= ' ') {
            i++;
        }
        return i;
    }

    private long parseDecFloatLiteral(char[] cArr, int i, int i2, int i3, boolean z, boolean z2) {
        int i4;
        int i5;
        int i6;
        int i7;
        long j;
        boolean z3;
        int i8;
        int compare;
        int min = Math.min(i3 - 4, 1073741824);
        int i9 = -1;
        int i10 = i;
        long j2 = 0;
        char c = 0;
        boolean z4 = false;
        while (i10 < i3) {
            c = cArr[i10];
            if (!FastDoubleSwar.isDigit(c)) {
                if (c != '.') {
                    break;
                }
                z4 |= i9 >= 0;
                int i11 = i10;
                while (i11 < min) {
                    int tryToParseFourDigits = FastDoubleSwar.tryToParseFourDigits(cArr, i11 + 1);
                    if (tryToParseFourDigits < 0) {
                        break;
                    }
                    j2 = (j2 * WorkRequest.MIN_BACKOFF_MILLIS) + tryToParseFourDigits;
                    i11 += 4;
                }
                int i12 = i10;
                i10 = i11;
                i9 = i12;
            } else {
                j2 = ((j2 * 10) + c) - 48;
            }
            i10++;
        }
        if (i9 < 0) {
            i4 = i10 - i;
            i9 = i10;
            i5 = 0;
        } else {
            i4 = (i10 - i) - 1;
            i5 = (i9 - i10) + 1;
        }
        if ((c | ' ') == 101) {
            i6 = i10 + 1;
            c = charAt(cArr, i6, i3);
            boolean z5 = c == '-';
            if (z5 || c == '+') {
                i6++;
                c = charAt(cArr, i6, i3);
            }
            boolean z6 = (!FastDoubleSwar.isDigit(c)) | z4;
            int i13 = 0;
            do {
                if (i13 < 1024) {
                    i13 = ((i13 * 10) + c) - 48;
                }
                i6++;
                c = charAt(cArr, i6, i3);
            } while (FastDoubleSwar.isDigit(c));
            if (z5) {
                i13 = -i13;
            }
            i5 += i13;
            i7 = i13;
            z4 = z6;
        } else {
            i6 = i10;
            i7 = 0;
        }
        if ((c == 'F') | (c == 'd') | (c == 'D') | (c == 'f')) {
            i6++;
        }
        int skipWhitespace = skipWhitespace(cArr, i6, i3);
        if (z4 || skipWhitespace < i3 || (!z2 && i4 == 0)) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (i4 > 19) {
            int i14 = i;
            int i15 = 0;
            long j3 = 0;
            while (i14 < i10) {
                char c2 = cArr[i14];
                if (c2 != '.') {
                    compare = Long.compare(j3 ^ Long.MIN_VALUE, 1000000000000000000L ^ Long.MIN_VALUE);
                    if (compare >= 0) {
                        break;
                    }
                    j3 = ((j3 * 10) + c2) - 48;
                } else {
                    i15++;
                }
                i14++;
            }
            j = j3;
            z3 = i14 < i10;
            i8 = (i9 - i14) + i15 + i7;
        } else {
            j = j2;
            z3 = false;
            i8 = 0;
        }
        return valueOfFloatLiteral(cArr, i2, i3, z, j, i5, z3, i8);
    }

    public long parseFloatingPointLiteral(char[] cArr, int i, int i2) {
        char charAt;
        int i3 = i + i2;
        if (i < 0 || i3 < i || i3 > cArr.length || i2 > 2147483643) {
            throw new IllegalArgumentException(AbstractNumberParser.ILLEGAL_OFFSET_OR_ILLEGAL_LENGTH);
        }
        int skipWhitespace = skipWhitespace(cArr, i, i3);
        if (skipWhitespace == i3) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        char c = cArr[skipWhitespace];
        boolean z = c == '-';
        if ((z || c == '+') && (c = charAt(cArr, (skipWhitespace = skipWhitespace + 1), i3)) == 0) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (c >= 'I') {
            return parseNaNOrInfinity(cArr, skipWhitespace, i3, z);
        }
        boolean z2 = c == '0';
        if (z2 && ((charAt = charAt(cArr, (skipWhitespace = skipWhitespace + 1), i3)) == 'x' || charAt == 'X')) {
            return parseHexFloatLiteral(cArr, skipWhitespace + 1, i, i3, z);
        }
        return parseDecFloatLiteral(cArr, skipWhitespace, i, i3, z, z2);
    }

    private long parseHexFloatLiteral(char[] cArr, int i, int i2, int i3, boolean z) {
        int min;
        int i4;
        int i5;
        boolean z2;
        int i6;
        int i7;
        long j;
        boolean z3;
        int i8;
        int compare;
        int i9 = -1;
        int i10 = i;
        long j2 = 0;
        char c = 0;
        boolean z4 = false;
        while (i10 < i3) {
            c = cArr[i10];
            int lookupHex = lookupHex(c);
            if (lookupHex < 0) {
                if (lookupHex != -4) {
                    break;
                }
                z4 |= i9 >= 0;
                int i11 = i10;
                while (i11 < i3 - 8) {
                    long tryToParseEightHexDigits = tryToParseEightHexDigits(cArr, i11 + 1);
                    if (tryToParseEightHexDigits < 0) {
                        break;
                    }
                    j2 = (j2 << 32) + tryToParseEightHexDigits;
                    i11 += 8;
                }
                int i12 = i10;
                i10 = i11;
                i9 = i12;
            } else {
                j2 = (j2 << 4) | lookupHex;
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
        boolean z5 = (c | ' ') == 112;
        if (z5) {
            i6 = i10 + 1;
            c = charAt(cArr, i6, i3);
            boolean z6 = c == '-';
            if (z6 || c == '+') {
                i6++;
                c = charAt(cArr, i6, i3);
            }
            boolean z7 = (!FastDoubleSwar.isDigit(c)) | z4;
            int i13 = 0;
            do {
                if (i13 < 1024) {
                    i13 = ((i13 * 10) + c) - 48;
                }
                z2 = true;
                i6++;
                c = charAt(cArr, i6, i3);
            } while (FastDoubleSwar.isDigit(c));
            if (z6) {
                i13 = -i13;
            }
            min += i13;
            i7 = i13;
            z4 = z7;
        } else {
            z2 = true;
            i6 = i10;
            i7 = 0;
        }
        char c2 = c;
        int i14 = min;
        if ((c2 == 'F' ? z2 : false) | (c2 == 'd' ? z2 : false) | (c2 == 'D' ? z2 : false) | (c2 == 'f' ? z2 : false)) {
            i6++;
        }
        int skipWhitespace = skipWhitespace(cArr, i6, i3);
        if (z4 || skipWhitespace < i3 || i5 == 0 || !z5) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (i5 > 16) {
            skipWhitespace = i;
            int i15 = 0;
            long j3 = 0;
            while (skipWhitespace < i10) {
                int lookupHex2 = lookupHex(cArr[skipWhitespace]);
                if (lookupHex2 >= 0) {
                    compare = Long.compare(j3 ^ Long.MIN_VALUE, 1000000000000000000L ^ Long.MIN_VALUE);
                    if (compare >= 0) {
                        break;
                    }
                    j3 = (j3 << 4) | lookupHex2;
                } else {
                    i15++;
                }
                skipWhitespace++;
            }
            j = j3;
            z3 = skipWhitespace < i10 ? z2 : false;
            i8 = i15;
        } else {
            j = j2;
            z3 = false;
            i8 = 0;
        }
        return valueOfHexLiteral(cArr, i2, i3, z, j, i14, z3, (((i4 - skipWhitespace) + i8) * 4) + i7);
    }

    private long parseNaNOrInfinity(char[] cArr, int i, int i2, boolean z) {
        char c = cArr[i];
        if (c == 'N') {
            int i3 = i + 2;
            if (i3 < i2 && cArr[i + 1] == 'a' && cArr[i3] == 'N' && skipWhitespace(cArr, i + 3, i2) == i2) {
                return nan();
            }
        } else {
            int i4 = i + 7;
            if (i4 < i2 && c == 'I' && cArr[i + 1] == 'n' && cArr[i + 2] == 'f' && cArr[i + 3] == 'i' && cArr[i + 4] == 'n' && cArr[i + 5] == 'i' && cArr[i + 6] == 't' && cArr[i4] == 'y' && skipWhitespace(cArr, i + 8, i2) == i2) {
                return z ? negativeInfinity() : positiveInfinity();
            }
        }
        throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
    }

    private long tryToParseEightHexDigits(char[] cArr, int i) {
        return FastDoubleSwar.tryToParseEightHexDigits(cArr, i);
    }
}
