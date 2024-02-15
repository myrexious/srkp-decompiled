package com.fasterxml.jackson.core.io.doubleparser;

import java.util.Arrays;

/* loaded from: classes.dex */
abstract class AbstractNumberParser {
    static final byte[] CHAR_TO_HEX_MAP;
    static final byte DECIMAL_POINT_CLASS = -4;
    public static final String ILLEGAL_OFFSET_OR_ILLEGAL_LENGTH = "offset < 0 or length > str.length";
    static final byte OTHER_CLASS = -1;
    public static final String SYNTAX_ERROR = "illegal syntax";
    public static final String VALUE_EXCEEDS_LIMITS = "value exceeds limits";

    static {
        byte[] bArr = new byte[256];
        CHAR_TO_HEX_MAP = bArr;
        Arrays.fill(bArr, (byte) -1);
        for (char c = '0'; c <= '9'; c = (char) (c + 1)) {
            CHAR_TO_HEX_MAP[c] = (byte) (c - '0');
        }
        for (char c2 = 'A'; c2 <= 'F'; c2 = (char) (c2 + 1)) {
            CHAR_TO_HEX_MAP[c2] = (byte) ((c2 - 'A') + 10);
        }
        for (char c3 = 'a'; c3 <= 'f'; c3 = (char) (c3 + 1)) {
            CHAR_TO_HEX_MAP[c3] = (byte) ((c3 - 'a') + 10);
        }
        for (char c4 = '.'; c4 <= '.'; c4 = (char) (c4 + 1)) {
            CHAR_TO_HEX_MAP[c4] = DECIMAL_POINT_CLASS;
        }
    }

    public static byte charAt(byte[] bArr, int i, int i2) {
        if (i < i2) {
            return bArr[i];
        }
        return (byte) 0;
    }

    public static char charAt(char[] cArr, int i, int i2) {
        if (i < i2) {
            return cArr[i];
        }
        return (char) 0;
    }

    public static char charAt(CharSequence charSequence, int i, int i2) {
        if (i < i2) {
            return charSequence.charAt(i);
        }
        return (char) 0;
    }

    public static int lookupHex(byte b) {
        return CHAR_TO_HEX_MAP[b & 255];
    }

    public static int lookupHex(char c) {
        if (c < 128) {
            return CHAR_TO_HEX_MAP[c];
        }
        return -1;
    }
}
