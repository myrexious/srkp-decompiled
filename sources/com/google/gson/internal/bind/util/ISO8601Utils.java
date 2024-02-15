package com.google.gson.internal.bind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import net.openid.appauth.AuthState;
import org.apache.commons.codec.language.Soundex;

/* loaded from: classes3.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        char c = Soundex.SILENT_MARKER;
        sb.append(Soundex.SILENT_MARKER);
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append(Soundex.SILENT_MARKER);
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / AuthState.EXPIRY_TIME_TOLERANCE_MS;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x00d4 A[Catch: IllegalArgumentException -> 0x01c0, NumberFormatException -> 0x01c2, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01c4, TryCatch #2 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01c4, blocks: (B:101:0x000a, B:103:0x001c, B:104:0x001e, B:106:0x002a, B:107:0x002c, B:109:0x003b, B:111:0x0041, B:115:0x0055, B:117:0x0065, B:118:0x0067, B:120:0x0073, B:121:0x0075, B:123:0x007b, B:127:0x0085, B:132:0x0095, B:134:0x009d, B:146:0x00ce, B:148:0x00d4, B:150:0x00db, B:176:0x0186, B:156:0x00ea, B:157:0x0102, B:158:0x0103, B:162:0x0121, B:164:0x012e, B:167:0x0137, B:169:0x0152, B:172:0x0161, B:173:0x0181, B:175:0x0184, B:161:0x010e, B:178:0x01b8, B:179:0x01bf, B:139:0x00b6, B:140:0x00b9), top: B:195:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x01b8 A[Catch: IllegalArgumentException -> 0x01c0, NumberFormatException -> 0x01c2, IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01c4, TryCatch #2 {IndexOutOfBoundsException | NumberFormatException | IllegalArgumentException -> 0x01c4, blocks: (B:101:0x000a, B:103:0x001c, B:104:0x001e, B:106:0x002a, B:107:0x002c, B:109:0x003b, B:111:0x0041, B:115:0x0055, B:117:0x0065, B:118:0x0067, B:120:0x0073, B:121:0x0075, B:123:0x007b, B:127:0x0085, B:132:0x0095, B:134:0x009d, B:146:0x00ce, B:148:0x00d4, B:150:0x00db, B:176:0x0186, B:156:0x00ea, B:157:0x0102, B:158:0x0103, B:162:0x0121, B:164:0x012e, B:167:0x0137, B:169:0x0152, B:172:0x0161, B:173:0x0181, B:175:0x0184, B:161:0x010e, B:178:0x01b8, B:179:0x01bf, B:139:0x00b6, B:140:0x00b9), top: B:195:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Date parse(java.lang.String r19, java.text.ParsePosition r20) throws java.text.ParseException {
        /*
            Method dump skipped, instructions count: 557
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = -digit;
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i3 = (i3 * 10) - digit2;
            i4 = i5;
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
