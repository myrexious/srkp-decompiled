package com.tom_roush.pdfbox.cos;

import java.io.IOException;

/* loaded from: classes3.dex */
public abstract class COSNumber extends COSBase {
    @Deprecated
    public static final COSInteger ZERO = COSInteger.ZERO;
    @Deprecated
    public static final COSInteger ONE = COSInteger.ONE;

    public abstract double doubleValue();

    public abstract float floatValue();

    public abstract int intValue();

    public abstract long longValue();

    public static COSNumber get(String str) throws IOException {
        if (str.length() == 1) {
            char charAt = str.charAt(0);
            if ('0' > charAt || charAt > '9') {
                if (charAt == '-' || charAt == '.') {
                    return COSInteger.ZERO;
                }
                throw new IOException("Not a number: " + str);
            }
            return COSInteger.get(charAt - 48);
        } else if (isFloat(str)) {
            return new COSFloat(str);
        } else {
            try {
                if (str.charAt(0) == '+') {
                    return COSInteger.get(Long.parseLong(str.substring(1)));
                }
                return COSInteger.get(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                if (((str.startsWith("+") || str.startsWith("-")) ? str.substring(1) : str).matches("[0-9]*")) {
                    return str.startsWith("-") ? COSInteger.OUT_OF_RANGE_MIN : COSInteger.OUT_OF_RANGE_MAX;
                }
                throw new IOException("Not a number: " + str);
            }
        }
    }

    private static boolean isFloat(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '.' || charAt == 'e') {
                return true;
            }
        }
        return false;
    }
}
