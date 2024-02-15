package com.fasterxml.jackson.core.io.doubleparser;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class AbstractJavaFloatingPointBitsFromCharSequence extends AbstractFloatValueParser {
    abstract long nan();

    abstract long negativeInfinity();

    abstract long positiveInfinity();

    abstract long valueOfFloatLiteral(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    abstract long valueOfHexLiteral(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    private static int skipWhitespace(CharSequence charSequence, int i, int i2) {
        while (i < i2 && charSequence.charAt(i) <= ' ') {
            i++;
        }
        return i;
    }

    private long parseDecFloatLiteral(CharSequence charSequence, int i, int i2, int i3, boolean z, boolean z2) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        long j;
        boolean z3;
        int i9;
        int compare;
        int i10 = -1;
        int i11 = i;
        long j2 = 0;
        char c = 0;
        boolean z4 = false;
        while (true) {
            if (i11 >= i3) {
                break;
            }
            c = charSequence.charAt(i11);
            if (!FastDoubleSwar.isDigit(c)) {
                if (c != '.') {
                    break;
                }
                z4 |= i10 >= 0;
                i10 = i11;
            } else {
                j2 = ((j2 * 10) + c) - 48;
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
        if ((c | ' ') == 101) {
            i7 = i11 + 1;
            char charAt = charAt(charSequence, i7, i3);
            boolean z5 = charAt == '-';
            if (z5 || charAt == '+') {
                i7++;
                charAt = charAt(charSequence, i7, i3);
            }
            boolean z6 = z4 | (!FastDoubleSwar.isDigit(charAt));
            int i12 = 0;
            do {
                if (i12 < 1024) {
                    i12 = ((i12 * 10) + charAt) - 48;
                }
                i7++;
                charAt = charAt(charSequence, i7, i3);
            } while (FastDoubleSwar.isDigit(charAt));
            if (z5) {
                i12 = -i12;
            }
            i4 += i12;
            z4 = z6;
            int i13 = i12;
            c = charAt;
            i8 = i13;
        } else {
            i7 = i11;
            i8 = 0;
        }
        if ((c == 'd') | (c == 'D') | (c == 'f') | (c == 'F')) {
            i7++;
        }
        int skipWhitespace = skipWhitespace(charSequence, i7, i3);
        if (z4 || skipWhitespace < i3 || (!z2 && i6 == 0)) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (i6 > 19) {
            int i14 = i;
            int i15 = 0;
            long j3 = 0;
            while (i14 < i11) {
                char charAt2 = charSequence.charAt(i14);
                if (charAt2 != '.') {
                    compare = Long.compare(j3 ^ Long.MIN_VALUE, 1000000000000000000L ^ Long.MIN_VALUE);
                    if (compare >= 0) {
                        break;
                    }
                    j3 = ((j3 * 10) + charAt2) - 48;
                } else {
                    i15++;
                }
                i14++;
            }
            j = j3;
            z3 = i14 < i11;
            i9 = (i5 - i14) + i15 + i8;
        } else {
            j = j2;
            z3 = false;
            i9 = 0;
        }
        return valueOfFloatLiteral(charSequence, i2, i3, z, j, i4, z3, i9);
    }

    public final long parseFloatingPointLiteral(CharSequence charSequence, int i, int i2) {
        char charAt;
        int i3 = i + i2;
        if (i < 0 || i3 < i || i3 > charSequence.length() || i2 > 2147483643) {
            throw new IllegalArgumentException(AbstractNumberParser.ILLEGAL_OFFSET_OR_ILLEGAL_LENGTH);
        }
        int skipWhitespace = skipWhitespace(charSequence, i, i3);
        if (skipWhitespace == i3) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        char charAt2 = charSequence.charAt(skipWhitespace);
        boolean z = charAt2 == '-';
        if ((z || charAt2 == '+') && (charAt2 = charAt(charSequence, (skipWhitespace = skipWhitespace + 1), i3)) == 0) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (charAt2 >= 'I') {
            return parseNaNOrInfinity(charSequence, skipWhitespace, i3, z);
        }
        boolean z2 = charAt2 == '0';
        if (z2 && ((charAt = charAt(charSequence, (skipWhitespace = skipWhitespace + 1), i3)) == 'x' || charAt == 'X')) {
            return parseHexFloatLiteral(charSequence, skipWhitespace + 1, i, i3, z);
        }
        return parseDecFloatLiteral(charSequence, skipWhitespace, i, i3, z, z2);
    }

    private long parseHexFloatLiteral(CharSequence charSequence, int i, int i2, int i3, boolean z) {
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
        char c = 0;
        boolean z3 = false;
        while (i10 < i3) {
            c = charSequence.charAt(i10);
            int lookupHex = lookupHex(c);
            if (lookupHex < 0) {
                if (lookupHex != -4) {
                    break;
                }
                z3 |= i9 >= 0;
                int i11 = i10;
                while (i11 < i3 - 8) {
                    long tryToParseEightHexDigits = FastDoubleSwar.tryToParseEightHexDigits(charSequence, i11 + 1);
                    if (tryToParseEightHexDigits < 0) {
                        break;
                    }
                    j = (j << 32) + tryToParseEightHexDigits;
                    i11 += 8;
                }
                int i12 = i10;
                i10 = i11;
                i9 = i12;
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
        boolean z4 = (c | ' ') == 112;
        if (z4) {
            i6 = i10 + 1;
            char charAt = charAt(charSequence, i6, i3);
            boolean z5 = charAt == '-';
            if (z5 || charAt == '+') {
                i6++;
                charAt = charAt(charSequence, i6, i3);
            }
            boolean z6 = z3 | (!FastDoubleSwar.isDigit(charAt));
            int i13 = 0;
            do {
                if (i13 < 1024) {
                    i13 = ((i13 * 10) + charAt) - 48;
                }
                i6++;
                charAt = charAt(charSequence, i6, i3);
            } while (FastDoubleSwar.isDigit(charAt));
            if (z5) {
                i13 = -i13;
            }
            min += i13;
            z3 = z6;
            int i14 = i13;
            c = charAt;
            i7 = i14;
        } else {
            i6 = i10;
            i7 = 0;
        }
        if ((c == 'F') | (c == 'D') | (c == 'd') | (c == 'f')) {
            i6++;
        }
        int skipWhitespace = skipWhitespace(charSequence, i6, i3);
        if (z3 || skipWhitespace < i3 || i5 == 0 || !z4) {
            throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
        }
        if (i5 > 16) {
            int i15 = i;
            int i16 = 0;
            j = 0;
            while (i15 < i10) {
                int lookupHex2 = lookupHex(charSequence.charAt(i15));
                if (lookupHex2 >= 0) {
                    compare = Long.compare(j ^ Long.MIN_VALUE, 1000000000000000000L ^ Long.MIN_VALUE);
                    if (compare >= 0) {
                        break;
                    }
                    j = (j << 4) | lookupHex2;
                } else {
                    i16++;
                }
                i15++;
            }
            z2 = i15 < i10;
            skipWhitespace = i15;
            i8 = i16;
        } else {
            i8 = 0;
            z2 = false;
        }
        return valueOfHexLiteral(charSequence, i2, i3, z, j, min, z2, (((i4 - skipWhitespace) + i8) * 4) + i7);
    }

    private long parseNaNOrInfinity(CharSequence charSequence, int i, int i2, boolean z) {
        if (charSequence.charAt(i) == 'N') {
            int i3 = i + 2;
            if (i3 < i2 && charSequence.charAt(i + 1) == 'a' && charSequence.charAt(i3) == 'N' && skipWhitespace(charSequence, i + 3, i2) == i2) {
                return nan();
            }
        } else {
            int i4 = i + 7;
            if (i4 < i2 && charSequence.charAt(i) == 'I' && charSequence.charAt(i + 1) == 'n' && charSequence.charAt(i + 2) == 'f' && charSequence.charAt(i + 3) == 'i' && charSequence.charAt(i + 4) == 'n' && charSequence.charAt(i + 5) == 'i' && charSequence.charAt(i + 6) == 't' && charSequence.charAt(i4) == 'y' && skipWhitespace(charSequence, i + 8, i2) == i2) {
                return z ? negativeInfinity() : positiveInfinity();
            }
        }
        throw new NumberFormatException(AbstractNumberParser.SYNTAX_ERROR);
    }
}
