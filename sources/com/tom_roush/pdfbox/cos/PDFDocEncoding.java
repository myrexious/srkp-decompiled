package com.tom_roush.pdfbox.cos;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import kotlin.UByte;
import kotlin.text.Typography;
import org.tensorflow.lite.schema.BuiltinOperator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class PDFDocEncoding {
    private static final char REPLACEMENT_CHARACTER = 65533;
    private static final int[] CODE_TO_UNI = new int[256];
    private static final Map<Character, Integer> UNI_TO_CODE = new HashMap(256);

    static {
        for (int i = 0; i < 256; i++) {
            if ((i <= 23 || i >= 32) && ((i <= 126 || i >= 161) && i != 173)) {
                set(i, (char) i);
            }
        }
        set(24, (char) 728);
        set(25, (char) 711);
        set(26, (char) 710);
        set(27, (char) 729);
        set(28, (char) 733);
        set(29, (char) 731);
        set(30, (char) 730);
        set(31, (char) 732);
        set(127, REPLACEMENT_CHARACTER);
        set(128, Typography.bullet);
        set(129, Typography.f29dagger);
        set(130, Typography.doubleDagger);
        set(131, Typography.ellipsis);
        set(132, Typography.mdash);
        set(133, Typography.ndash);
        set(134, (char) 402);
        set(135, (char) 8260);
        set(136, (char) 8249);
        set(137, (char) 8250);
        set(138, (char) 8722);
        set(139, (char) 8240);
        set(140, Typography.lowDoubleQuote);
        set(141, Typography.leftDoubleQuote);
        set(142, Typography.rightDoubleQuote);
        set(143, Typography.leftSingleQuote);
        set(BuiltinOperator.ASSIGN_VARIABLE, Typography.rightSingleQuote);
        set(BuiltinOperator.BROADCAST_ARGS, Typography.lowSingleQuote);
        set(BuiltinOperator.RANDOM_STANDARD_NORMAL, Typography.tm);
        set(BuiltinOperator.BUCKETIZE, (char) 64257);
        set(BuiltinOperator.RANDOM_UNIFORM, (char) 64258);
        set(BuiltinOperator.MULTINOMIAL, (char) 321);
        set(BuiltinOperator.GELU, (char) 338);
        set(BuiltinOperator.DYNAMIC_UPDATE_SLICE, (char) 352);
        set(152, (char) 376);
        set(153, (char) 381);
        set(154, (char) 305);
        set(155, (char) 322);
        set(156, (char) 339);
        set(157, (char) 353);
        set(158, (char) 382);
        set(159, REPLACEMENT_CHARACTER);
        set(160, Typography.euro);
    }

    private PDFDocEncoding() {
    }

    private static void set(int i, char c) {
        CODE_TO_UNI[i] = c;
        UNI_TO_CODE.put(Character.valueOf(c), Integer.valueOf(i));
    }

    public static String toString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            int i = b & UByte.MAX_VALUE;
            int[] iArr = CODE_TO_UNI;
            if (i >= iArr.length) {
                sb.append('?');
            } else {
                sb.append((char) iArr[i]);
            }
        }
        return sb.toString();
    }

    public static byte[] getBytes(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (char c : str.toCharArray()) {
            Integer num = UNI_TO_CODE.get(Character.valueOf(c));
            if (num == null) {
                byteArrayOutputStream.write(0);
            } else {
                byteArrayOutputStream.write(num.intValue());
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean containsChar(char c) {
        return UNI_TO_CODE.containsKey(Character.valueOf(c));
    }
}
