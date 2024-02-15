package com.tom_roush.pdfbox.pdmodel.encryption;

import java.nio.CharBuffer;
import java.text.Normalizer;

/* loaded from: classes3.dex */
class SaslPrep {
    private static boolean asciiControl(char c) {
        return (c >= 0 && c <= 31) || c == 127;
    }

    private static boolean changeDisplayProperties(int i) {
        return i == 832 || i == 833 || i == 8206 || i == 8207 || i == 8234 || i == 8235 || i == 8236 || i == 8237 || i == 8238 || i == 8298 || i == 8299 || i == 8300 || i == 8301 || i == 8302 || i == 8303;
    }

    private static boolean inappropriateForCanonical(int i) {
        return 12272 <= i && i <= 12283;
    }

    private static boolean inappropriateForPlainText(int i) {
        return i == 65529 || i == 65530 || i == 65531 || i == 65532 || i == 65533;
    }

    private static boolean mappedToNothing(char c) {
        return c == 173 || c == 847 || c == 6150 || c == 6155 || c == 6156 || c == 6157 || c == 8203 || c == 8204 || c == 8205 || c == 8288 || (65024 <= c && c <= 65039) || c == 65279;
    }

    private static boolean nonAsciiControl(int i) {
        return (128 <= i && i <= 159) || i == 1757 || i == 1807 || i == 6158 || i == 8204 || i == 8205 || i == 8232 || i == 8233 || i == 8288 || i == 8289 || i == 8290 || i == 8291 || (8298 <= i && i <= 8303) || i == 65279 || ((65529 <= i && i <= 65532) || (119155 <= i && i <= 119162));
    }

    private static boolean nonAsciiSpace(char c) {
        return c == 160 || c == 5760 || (8192 <= c && c <= 8203) || c == 8239 || c == 8287 || c == 12288;
    }

    private static boolean nonCharacterCodePoint(int i) {
        return (64976 <= i && i <= 65007) || (65534 <= i && i <= 65535) || ((131070 <= i && i <= 131071) || ((196606 <= i && i <= 196607) || ((262142 <= i && i <= 262143) || ((327678 <= i && i <= 327679) || ((393214 <= i && i <= 393215) || ((458750 <= i && i <= 458751) || ((524286 <= i && i <= 524287) || ((589822 <= i && i <= 589823) || ((655358 <= i && i <= 655359) || ((720894 <= i && i <= 720895) || ((786430 <= i && i <= 786431) || ((851966 <= i && i <= 851967) || ((917502 <= i && i <= 917503) || ((983038 <= i && i <= 983039) || ((1048574 <= i && i <= 1048575) || (1114110 <= i && i <= 1114111))))))))))))))));
    }

    private static boolean privateUse(int i) {
        return (57344 <= i && i <= 63743) || (983040 <= i && i <= 1048573) || (1048576 <= i && i <= 1114109);
    }

    private static boolean surrogateCodePoint(int i) {
        return 55296 <= i && i <= 57343;
    }

    private static boolean tagging(int i) {
        return i == 917505 || (917536 <= i && i <= 917631);
    }

    private SaslPrep() {
    }

    public static String saslPrepQuery(String str) {
        return saslPrep(str, true);
    }

    public static String saslPrepStored(String str) {
        return saslPrep(str, false);
    }

    private static String saslPrep(String str, boolean z) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (nonAsciiSpace(str.charAt(i))) {
                charArray[i] = ' ';
            }
        }
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            char c = charArray[i3];
            if (!mappedToNothing(c)) {
                charArray[i2] = c;
                i2++;
            }
        }
        String normalize = Normalizer.normalize(CharBuffer.wrap(charArray, 0, i2), Normalizer.Form.NFKC);
        int i4 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (i4 < normalize.length()) {
            int codePointAt = normalize.codePointAt(i4);
            if (prohibited(codePointAt)) {
                throw new IllegalArgumentException("Prohibited character " + codePointAt + " at position " + i4);
            }
            byte directionality = Character.getDirectionality(codePointAt);
            boolean z5 = true;
            boolean z6 = directionality == 1 || directionality == 2;
            z2 |= z6;
            z3 |= directionality == 0;
            z4 |= (i4 == 0 && z6) ? false : false;
            if (!z && !Character.isDefined(codePointAt)) {
                throw new IllegalArgumentException("Character at position " + i4 + " is unassigned");
            }
            i4 += Character.charCount(codePointAt);
            if (z4 && i4 >= normalize.length() && !z6) {
                throw new IllegalArgumentException("First character is RandALCat, but last character is not");
            }
        }
        if (z2 && z3) {
            throw new IllegalArgumentException("Contains both RandALCat characters and LCat characters");
        }
        return normalize;
    }

    static boolean prohibited(int i) {
        char c = (char) i;
        return nonAsciiSpace(c) || asciiControl(c) || nonAsciiControl(i) || privateUse(i) || nonCharacterCodePoint(i) || surrogateCodePoint(i) || inappropriateForPlainText(i) || inappropriateForCanonical(i) || changeDisplayProperties(i) || tagging(i);
    }
}
