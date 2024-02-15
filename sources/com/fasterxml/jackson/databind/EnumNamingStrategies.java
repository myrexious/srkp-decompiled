package com.fasterxml.jackson.databind;

/* loaded from: classes.dex */
public class EnumNamingStrategies {
    private EnumNamingStrategies() {
    }

    /* loaded from: classes.dex */
    public static class CamelCaseStrategy implements EnumNamingStrategy {
        public static final CamelCaseStrategy INSTANCE = new CamelCaseStrategy();

        @Override // com.fasterxml.jackson.databind.EnumNamingStrategy
        public String convertEnumToExternalName(String str) {
            StringBuilder sb = null;
            if (str == null) {
                return null;
            }
            int i = 0;
            int i2 = -1;
            do {
                i2 = indexIn(str, i2 + 1);
                if (i2 != -1) {
                    if (i == 0) {
                        sb = new StringBuilder(str.length() + 4);
                        sb.append(toLowerCase(str.substring(i, i2)));
                    } else {
                        sb.append(normalizeWord(str.substring(i, i2)));
                    }
                    i = i2 + 1;
                    continue;
                }
            } while (i2 != -1);
            if (i == 0) {
                return toLowerCase(str);
            }
            sb.append(normalizeWord(str.substring(i)));
            return sb.toString();
        }

        private static int indexIn(CharSequence charSequence, int i) {
            int length = charSequence.length();
            while (i < length) {
                if ('_' == charSequence.charAt(i)) {
                    return i;
                }
                i++;
            }
            return -1;
        }

        private static String normalizeWord(String str) {
            int length = str.length();
            return length == 0 ? str : new StringBuilder(length).append(charToUpperCaseIfLower(str.charAt(0))).append(toLowerCase(str.substring(1))).toString();
        }

        private static String toLowerCase(String str) {
            int length = str.length();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                sb.append(charToLowerCaseIfUpper(str.charAt(i)));
            }
            return sb.toString();
        }

        private static char charToUpperCaseIfLower(char c) {
            return Character.isLowerCase(c) ? Character.toUpperCase(c) : c;
        }

        private static char charToLowerCaseIfUpper(char c) {
            return Character.isUpperCase(c) ? Character.toLowerCase(c) : c;
        }
    }
}
