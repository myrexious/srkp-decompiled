package org.apache.commons.lang3;

import java.util.Objects;
import java.util.function.IntFunction;

/* loaded from: classes2.dex */
public class CharUtils {
    private static final String[] CHAR_STRING_ARRAY;
    public static final char CR = '\r';
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final char LF = '\n';
    public static final char NUL = 0;

    public static int compare(char c, char c2) {
        return c - c2;
    }

    public static boolean isAscii(char c) {
        return c < 128;
    }

    public static boolean isAsciiAlphaLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isAsciiAlphaUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isAsciiControl(char c) {
        return c < ' ' || c == 127;
    }

    public static boolean isAsciiNumeric(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isAsciiPrintable(char c) {
        return c >= ' ' && c < 127;
    }

    static {
        String[] strArr = new String[128];
        CHAR_STRING_ARRAY = strArr;
        ArrayUtils.setAll(strArr, new IntFunction() { // from class: org.apache.commons.lang3.CharUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                String valueOf;
                valueOf = String.valueOf((char) i);
                return valueOf;
            }
        });
    }

    @Deprecated
    public static Character toCharacterObject(char c) {
        return Character.valueOf(c);
    }

    public static Character toCharacterObject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }

    public static char toChar(Character ch) {
        return ((Character) Objects.requireNonNull(ch, "ch")).charValue();
    }

    public static char toChar(Character ch, char c) {
        return ch != null ? ch.charValue() : c;
    }

    public static char toChar(String str) {
        Validate.notEmpty(str, "The String must not be empty", new Object[0]);
        return str.charAt(0);
    }

    public static char toChar(String str, char c) {
        return StringUtils.isEmpty(str) ? c : str.charAt(0);
    }

    public static int toIntValue(char c) {
        if (isAsciiNumeric(c)) {
            return c - '0';
        }
        throw new IllegalArgumentException("The character " + c + " is not in the range '0' - '9'");
    }

    public static int toIntValue(char c, int i) {
        return isAsciiNumeric(c) ? c - '0' : i;
    }

    public static int toIntValue(Character ch) {
        return toIntValue(toChar(ch));
    }

    public static int toIntValue(Character ch, int i) {
        return ch != null ? toIntValue(ch.charValue(), i) : i;
    }

    public static String toString(char c) {
        String[] strArr = CHAR_STRING_ARRAY;
        if (c < strArr.length) {
            return strArr[c];
        }
        return String.valueOf(c);
    }

    public static String toString(Character ch) {
        if (ch != null) {
            return toString(ch.charValue());
        }
        return null;
    }

    public static String unicodeEscaped(char c) {
        StringBuilder sb = new StringBuilder("\\u");
        char[] cArr = HEX_DIGITS;
        return sb.append(cArr[(c >> '\f') & 15]).append(cArr[(c >> '\b') & 15]).append(cArr[(c >> 4) & 15]).append(cArr[c & 15]).toString();
    }

    public static String unicodeEscaped(Character ch) {
        if (ch != null) {
            return unicodeEscaped(ch.charValue());
        }
        return null;
    }

    public static boolean isAsciiAlpha(char c) {
        return isAsciiAlphaUpper(c) || isAsciiAlphaLower(c);
    }

    public static boolean isAsciiAlphanumeric(char c) {
        return isAsciiAlpha(c) || isAsciiNumeric(c);
    }
}
