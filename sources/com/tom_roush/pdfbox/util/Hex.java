package com.tom_roush.pdfbox.util;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
public final class Hex {
    private static final byte[] HEX_BYTES = {BuiltinOptions.SliceOptions, 49, 50, 51, 52, 53, 54, 55, BuiltinOptions.PowOptions, BuiltinOptions.ArgMinOptions, BuiltinOptions.FloorDivOptions, BuiltinOptions.SquareOptions, BuiltinOptions.ZerosLikeOptions, BuiltinOptions.FillOptions, BuiltinOptions.BidirectionalSequenceLSTMOptions, BuiltinOptions.BidirectionalSequenceRNNOptions};
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static int getHighNibble(byte b) {
        return (b & 240) >> 4;
    }

    private static int getLowNibble(byte b) {
        return b & 15;
    }

    private Hex() {
    }

    public static String getString(byte b) {
        char[] cArr = HEX_CHARS;
        return new String(new char[]{cArr[getHighNibble(b)], cArr[getLowNibble(b)]});
    }

    public static String getString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            char[] cArr = HEX_CHARS;
            sb.append(cArr[getHighNibble(b)]).append(cArr[getLowNibble(b)]);
        }
        return sb.toString();
    }

    public static byte[] getBytes(byte b) {
        byte[] bArr = HEX_BYTES;
        return new byte[]{bArr[getHighNibble(b)], bArr[getLowNibble(b)]};
    }

    public static byte[] getBytes(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            byte[] bArr3 = HEX_BYTES;
            bArr2[i2] = bArr3[getHighNibble(bArr[i])];
            bArr2[i2 + 1] = bArr3[getLowNibble(bArr[i])];
        }
        return bArr2;
    }

    public static char[] getChars(short s) {
        char[] cArr = HEX_CHARS;
        return new char[]{cArr[(s >> 12) & 15], cArr[(s >> 8) & 15], cArr[(s >> 4) & 15], cArr[s & 15]};
    }

    public static char[] getCharsUTF16BE(String str) {
        char[] cArr = new char[str.length() * 4];
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            int i3 = i + 1;
            char[] cArr2 = HEX_CHARS;
            cArr[i] = cArr2[(charAt >> '\f') & 15];
            int i4 = i3 + 1;
            cArr[i3] = cArr2[(charAt >> '\b') & 15];
            int i5 = i4 + 1;
            cArr[i4] = cArr2[(charAt >> 4) & 15];
            i = i5 + 1;
            cArr[i5] = cArr2[charAt & 15];
        }
        return cArr;
    }

    public static void writeHexByte(byte b, OutputStream outputStream) throws IOException {
        byte[] bArr = HEX_BYTES;
        outputStream.write(bArr[getHighNibble(b)]);
        outputStream.write(bArr[getLowNibble(b)]);
    }

    public static void writeHexBytes(byte[] bArr, OutputStream outputStream) throws IOException {
        for (byte b : bArr) {
            writeHexByte(b, outputStream);
        }
    }

    public static byte[] decodeBase64(String str) {
        try {
            Class<?> cls = Class.forName("java.util.Base64");
            Object invoke = cls.getMethod("getDecoder", new Class[0]).invoke(cls, new Object[0]);
            return (byte[]) invoke.getClass().getMethod("decode", String.class).invoke(invoke, str.replaceAll("\\s", ""));
        } catch (ClassNotFoundException e) {
            Log.d("PdfBox-Android", e.getMessage(), e);
            try {
                return (byte[]) Class.forName("javax.xml.bind.DatatypeConverter").getMethod("parseBase64Binary", String.class).invoke(null, str);
            } catch (ClassNotFoundException e2) {
                Log.d("PdfBox-Android", e2.getMessage(), e2);
                Log.e("PdfBox-Android", "Can't decode base64 value, try adding javax.xml.bind:jaxb-api to your build");
                return new byte[0];
            } catch (IllegalAccessException e3) {
                Log.d("PdfBox-Android", e3.getMessage(), e3);
                Log.e("PdfBox-Android", "Can't decode base64 value, try adding javax.xml.bind:jaxb-api to your build");
                return new byte[0];
            } catch (IllegalArgumentException e4) {
                Log.d("PdfBox-Android", e4.getMessage(), e4);
                Log.e("PdfBox-Android", "Can't decode base64 value, try adding javax.xml.bind:jaxb-api to your build");
                return new byte[0];
            } catch (NoSuchMethodException e5) {
                Log.d("PdfBox-Android", e5.getMessage(), e5);
                Log.e("PdfBox-Android", "Can't decode base64 value, try adding javax.xml.bind:jaxb-api to your build");
                return new byte[0];
            } catch (SecurityException e6) {
                Log.d("PdfBox-Android", e6.getMessage(), e6);
                Log.e("PdfBox-Android", "Can't decode base64 value, try adding javax.xml.bind:jaxb-api to your build");
                return new byte[0];
            } catch (InvocationTargetException e7) {
                Log.d("PdfBox-Android", e7.getMessage(), e7);
                Log.e("PdfBox-Android", "Can't decode base64 value, try adding javax.xml.bind:jaxb-api to your build");
                return new byte[0];
            }
        } catch (IllegalAccessException e8) {
            Log.d("PdfBox-Android", e8.getMessage(), e8);
            return (byte[]) Class.forName("javax.xml.bind.DatatypeConverter").getMethod("parseBase64Binary", String.class).invoke(null, str);
        } catch (IllegalArgumentException e9) {
            Log.d("PdfBox-Android", e9.getMessage(), e9);
            return (byte[]) Class.forName("javax.xml.bind.DatatypeConverter").getMethod("parseBase64Binary", String.class).invoke(null, str);
        } catch (NoSuchMethodException e10) {
            Log.d("PdfBox-Android", e10.getMessage(), e10);
            return (byte[]) Class.forName("javax.xml.bind.DatatypeConverter").getMethod("parseBase64Binary", String.class).invoke(null, str);
        } catch (SecurityException e11) {
            Log.d("PdfBox-Android", e11.getMessage(), e11);
            return (byte[]) Class.forName("javax.xml.bind.DatatypeConverter").getMethod("parseBase64Binary", String.class).invoke(null, str);
        } catch (InvocationTargetException e12) {
            Log.d("PdfBox-Android", e12.getMessage(), e12);
            return (byte[]) Class.forName("javax.xml.bind.DatatypeConverter").getMethod("parseBase64Binary", String.class).invoke(null, str);
        }
    }

    public static byte[] decodeHex(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < str.length() - 1) {
            if (str.charAt(i) == '\n' || str.charAt(i) == '\r') {
                i++;
            } else {
                int i2 = i + 2;
                String substring = str.substring(i, i2);
                try {
                    byteArrayOutputStream.write(Integer.parseInt(substring, 16));
                    i = i2;
                } catch (NumberFormatException e) {
                    Log.e("PdfBox-Android", "Can't parse " + substring + ", aborting decode", e);
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
