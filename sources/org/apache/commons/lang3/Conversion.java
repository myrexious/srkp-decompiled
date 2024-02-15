package org.apache.commons.lang3;

import java.util.UUID;
import kotlin.UShort;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes2.dex */
public class Conversion {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean[] TTTT = {true, true, true, true};
    private static final boolean[] FTTT = {false, true, true, true};
    private static final boolean[] TFTT = {true, false, true, true};
    private static final boolean[] FFTT = {false, false, true, true};
    private static final boolean[] TTFT = {true, true, false, true};
    private static final boolean[] FTFT = {false, true, false, true};
    private static final boolean[] TFFT = {true, false, false, true};
    private static final boolean[] FFFT = {false, false, false, true};
    private static final boolean[] TTTF = {true, true, true, false};
    private static final boolean[] FTTF = {false, true, true, false};
    private static final boolean[] TFTF = {true, false, true, false};
    private static final boolean[] FFTF = {false, false, true, false};
    private static final boolean[] TTFF = {true, true, false, false};
    private static final boolean[] FTFF = {false, true, false, false};
    private static final boolean[] TFFF = {true, false, false, false};
    private static final boolean[] FFFF = {false, false, false, false};

    public static int hexDigitToInt(char c) {
        int digit = Character.digit(c, 16);
        if (digit >= 0) {
            return digit;
        }
        throw new IllegalArgumentException("Cannot interpret '" + c + "' as a hexadecimal digit");
    }

    public static int hexDigitMsb0ToInt(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 8;
            case '2':
                return 4;
            case '3':
                return 12;
            case '4':
                return 2;
            case '5':
                return 10;
            case '6':
                return 6;
            case '7':
                return 14;
            case '8':
                return 1;
            case '9':
                return 9;
            default:
                switch (c) {
                    case 'A':
                        return 5;
                    case 'B':
                        return 13;
                    case 'C':
                        return 3;
                    case 'D':
                        return 11;
                    case 'E':
                        return 7;
                    case 'F':
                        return 15;
                    default:
                        switch (c) {
                            case 'a':
                                return 5;
                            case 'b':
                                return 13;
                            case 'c':
                                return 3;
                            case 'd':
                                return 11;
                            case 'e':
                                return 7;
                            case 'f':
                                return 15;
                            default:
                                throw new IllegalArgumentException("Cannot interpret '" + c + "' as a hexadecimal digit");
                        }
                }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean[] hexDigitToBinary(char r3) {
        /*
            switch(r3) {
                case 48: goto Lab;
                case 49: goto La2;
                case 50: goto L99;
                case 51: goto L90;
                case 52: goto L87;
                case 53: goto L7e;
                case 54: goto L75;
                case 55: goto L6c;
                case 56: goto L63;
                case 57: goto L5a;
                default: goto L3;
            }
        L3:
            switch(r3) {
                case 65: goto L51;
                case 66: goto L48;
                case 67: goto L3f;
                case 68: goto L36;
                case 69: goto L2d;
                case 70: goto L24;
                default: goto L6;
            }
        L6:
            switch(r3) {
                case 97: goto L51;
                case 98: goto L48;
                case 99: goto L3f;
                case 100: goto L36;
                case 101: goto L2d;
                case 102: goto L24;
                default: goto L9;
            }
        L9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Cannot interpret '"
            r1.<init>(r2)
            java.lang.StringBuilder r3 = r1.append(r3)
            java.lang.String r1 = "' as a hexadecimal digit"
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r3 = r3.toString()
            r0.<init>(r3)
            throw r0
        L24:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L2d:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L36:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L3f:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L48:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L51:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L5a:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L63:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L6c:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L75:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L7e:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L87:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L90:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L99:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        La2:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        Lab:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.Conversion.hexDigitToBinary(char):boolean[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean[] hexDigitMsb0ToBinary(char r3) {
        /*
            switch(r3) {
                case 48: goto Lab;
                case 49: goto La2;
                case 50: goto L99;
                case 51: goto L90;
                case 52: goto L87;
                case 53: goto L7e;
                case 54: goto L75;
                case 55: goto L6c;
                case 56: goto L63;
                case 57: goto L5a;
                default: goto L3;
            }
        L3:
            switch(r3) {
                case 65: goto L51;
                case 66: goto L48;
                case 67: goto L3f;
                case 68: goto L36;
                case 69: goto L2d;
                case 70: goto L24;
                default: goto L6;
            }
        L6:
            switch(r3) {
                case 97: goto L51;
                case 98: goto L48;
                case 99: goto L3f;
                case 100: goto L36;
                case 101: goto L2d;
                case 102: goto L24;
                default: goto L9;
            }
        L9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Cannot interpret '"
            r1.<init>(r2)
            java.lang.StringBuilder r3 = r1.append(r3)
            java.lang.String r1 = "' as a hexadecimal digit"
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r3 = r3.toString()
            r0.<init>(r3)
            throw r0
        L24:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L2d:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L36:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L3f:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TTFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L48:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L51:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L5a:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L63:
            boolean[] r3 = org.apache.commons.lang3.Conversion.TFFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L6c:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L75:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L7e:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L87:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FTFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L90:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFTT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        L99:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFTF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        La2:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFFT
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        Lab:
            boolean[] r3 = org.apache.commons.lang3.Conversion.FFFF
            java.lang.Object r3 = r3.clone()
            boolean[] r3 = (boolean[]) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.Conversion.hexDigitMsb0ToBinary(char):boolean[]");
    }

    public static char binaryToHexDigit(boolean[] zArr) {
        return binaryToHexDigit(zArr, 0);
    }

    public static char binaryToHexDigit(boolean[] zArr, int i) {
        if (zArr.length == 0) {
            throw new IllegalArgumentException("Cannot convert an empty array.");
        }
        int i2 = i + 3;
        if (zArr.length > i2 && zArr[i2]) {
            return zArr[i + 2] ? zArr[i + 1] ? zArr[i] ? 'f' : 'e' : zArr[i] ? 'd' : 'c' : zArr[i + 1] ? zArr[i] ? 'b' : 'a' : zArr[i] ? '9' : '8';
        }
        int i3 = i + 2;
        if (zArr.length > i3 && zArr[i3]) {
            return zArr[i + 1] ? zArr[i] ? '7' : '6' : zArr[i] ? '5' : '4';
        }
        int i4 = i + 1;
        return (zArr.length <= i4 || !zArr[i4]) ? zArr[i] ? '1' : '0' : zArr[i] ? '3' : '2';
    }

    public static char binaryToHexDigitMsb0_4bits(boolean[] zArr) {
        return binaryToHexDigitMsb0_4bits(zArr, 0);
    }

    public static char binaryToHexDigitMsb0_4bits(boolean[] zArr, int i) {
        if (zArr.length > 8) {
            throw new IllegalArgumentException("src.length>8: src.length=" + zArr.length);
        }
        if (zArr.length - i >= 4) {
            return zArr[i + 3] ? zArr[i + 2] ? zArr[i + 1] ? zArr[i] ? 'f' : '7' : zArr[i] ? 'b' : '3' : zArr[i + 1] ? zArr[i] ? 'd' : '5' : zArr[i] ? '9' : '1' : zArr[i + 2] ? zArr[i + 1] ? zArr[i] ? 'e' : '6' : zArr[i] ? 'a' : '2' : zArr[i + 1] ? zArr[i] ? 'c' : '4' : zArr[i] ? '8' : '0';
        }
        throw new IllegalArgumentException("src.length-srcPos<4: src.length=" + zArr.length + ", srcPos=" + i);
    }

    public static char binaryBeMsb0ToHexDigit(boolean[] zArr) {
        return binaryBeMsb0ToHexDigit(zArr, 0);
    }

    public static char binaryBeMsb0ToHexDigit(boolean[] zArr, int i) {
        int compare;
        compare = Integer.compare(i ^ Integer.MIN_VALUE, zArr.length ^ Integer.MIN_VALUE);
        if (compare >= 0) {
            if (zArr.length == 0) {
                throw new IllegalArgumentException("Cannot convert an empty array.");
            }
            throw new IndexOutOfBoundsException(i + " is not within array length " + zArr.length);
        }
        int length = (zArr.length - 1) - i;
        return (3 > length || !zArr[length + (-3)]) ? (2 > length || !zArr[length + (-2)]) ? (1 > length || !zArr[length + (-1)]) ? zArr[length] ? '1' : '0' : zArr[length] ? '3' : '2' : zArr[length + (-1)] ? zArr[length] ? '7' : '6' : zArr[length] ? '5' : '4' : zArr[length + (-2)] ? zArr[length + (-1)] ? zArr[length] ? 'f' : 'e' : zArr[length] ? 'd' : 'c' : zArr[length + (-1)] ? zArr[length] ? 'b' : 'a' : zArr[length] ? '9' : '8';
    }

    public static char intToHexDigit(int i) {
        char forDigit = Character.forDigit(i, 16);
        if (forDigit != 0) {
            return forDigit;
        }
        throw new IllegalArgumentException("nibble value not between 0 and 15: " + i);
    }

    public static char intToHexDigitMsb0(int i) {
        switch (i) {
            case 0:
                return '0';
            case 1:
                return '8';
            case 2:
                return '4';
            case 3:
                return 'c';
            case 4:
                return '2';
            case 5:
                return 'a';
            case 6:
                return '6';
            case 7:
                return 'e';
            case 8:
                return '1';
            case 9:
                return '9';
            case 10:
                return '5';
            case 11:
                return 'd';
            case 12:
                return '3';
            case 13:
                return 'b';
            case 14:
                return '7';
            case 15:
                return 'f';
            default:
                throw new IllegalArgumentException("nibble value not between 0 and 15: " + i);
        }
    }

    public static long intArrayToLong(int[] iArr, int i, long j, int i2, int i3) {
        if ((iArr.length == 0 && i == 0) || i3 == 0) {
            return j;
        }
        if (((i3 - 1) * 32) + i2 < 64) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = (i4 * 32) + i2;
                j = (j & (~(BodyPartID.bodyIdMax << i5))) | ((iArr[i4 + i] & BodyPartID.bodyIdMax) << i5);
            }
            return j;
        }
        throw new IllegalArgumentException("(nInts-1)*32+dstPos is greater or equal to than 64");
    }

    public static long shortArrayToLong(short[] sArr, int i, long j, int i2, int i3) {
        if ((sArr.length == 0 && i == 0) || i3 == 0) {
            return j;
        }
        if (((i3 - 1) * 16) + i2 < 64) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = (i4 * 16) + i2;
                j = (j & (~(65535 << i5))) | ((sArr[i4 + i] & 65535) << i5);
            }
            return j;
        }
        throw new IllegalArgumentException("(nShorts-1)*16+dstPos is greater or equal to than 64");
    }

    public static int shortArrayToInt(short[] sArr, int i, int i2, int i3, int i4) {
        if ((sArr.length == 0 && i == 0) || i4 == 0) {
            return i2;
        }
        if (((i4 - 1) * 16) + i3 < 32) {
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = (i5 * 16) + i3;
                i2 = (i2 & (~(65535 << i6))) | ((sArr[i5 + i] & UShort.MAX_VALUE) << i6);
            }
            return i2;
        }
        throw new IllegalArgumentException("(nShorts-1)*16+dstPos is greater or equal to than 32");
    }

    public static long byteArrayToLong(byte[] bArr, int i, long j, int i2, int i3) {
        if ((bArr.length == 0 && i == 0) || i3 == 0) {
            return j;
        }
        if (((i3 - 1) * 8) + i2 < 64) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = (i4 * 8) + i2;
                j = (j & (~(255 << i5))) | ((bArr[i4 + i] & 255) << i5);
            }
            return j;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greater or equal to than 64");
    }

    public static int byteArrayToInt(byte[] bArr, int i, int i2, int i3, int i4) {
        if ((bArr.length == 0 && i == 0) || i4 == 0) {
            return i2;
        }
        if (((i4 - 1) * 8) + i3 < 32) {
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = (i5 * 8) + i3;
                i2 = (i2 & (~(255 << i6))) | ((bArr[i5 + i] & 255) << i6);
            }
            return i2;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greater or equal to than 32");
    }

    public static short byteArrayToShort(byte[] bArr, int i, short s, int i2, int i3) {
        if ((bArr.length == 0 && i == 0) || i3 == 0) {
            return s;
        }
        if (((i3 - 1) * 8) + i2 < 16) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = (i4 * 8) + i2;
                s = (short) ((s & (~(255 << i5))) | ((bArr[i4 + i] & 255) << i5));
            }
            return s;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greater or equal to than 16");
    }

    public static long hexToLong(String str, int i, long j, int i2, int i3) {
        if (i3 == 0) {
            return j;
        }
        if (((i3 - 1) * 4) + i2 < 64) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = (i4 * 4) + i2;
                j = (j & (~(15 << i5))) | ((hexDigitToInt(str.charAt(i4 + i)) & 15) << i5);
            }
            return j;
        }
        throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 64");
    }

    public static int hexToInt(String str, int i, int i2, int i3, int i4) {
        if (i4 == 0) {
            return i2;
        }
        if (((i4 - 1) * 4) + i3 < 32) {
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = (i5 * 4) + i3;
                i2 = (i2 & (~(15 << i6))) | ((hexDigitToInt(str.charAt(i5 + i)) & 15) << i6);
            }
            return i2;
        }
        throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 32");
    }

    public static short hexToShort(String str, int i, short s, int i2, int i3) {
        if (i3 == 0) {
            return s;
        }
        if (((i3 - 1) * 4) + i2 < 16) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = (i4 * 4) + i2;
                s = (short) ((s & (~(15 << i5))) | ((hexDigitToInt(str.charAt(i4 + i)) & 15) << i5));
            }
            return s;
        }
        throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 16");
    }

    public static byte hexToByte(String str, int i, byte b, int i2, int i3) {
        if (i3 == 0) {
            return b;
        }
        if (((i3 - 1) * 4) + i2 < 8) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = (i4 * 4) + i2;
                b = (byte) ((b & (~(15 << i5))) | ((hexDigitToInt(str.charAt(i4 + i)) & 15) << i5));
            }
            return b;
        }
        throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 8");
    }

    public static long binaryToLong(boolean[] zArr, int i, long j, int i2, int i3) {
        if ((zArr.length == 0 && i == 0) || i3 == 0) {
            return j;
        }
        if ((i3 - 1) + i2 < 64) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = i4 + i2;
                j = (j & (~(1 << i5))) | ((zArr[i4 + i] ? 1L : 0L) << i5);
            }
            return j;
        }
        throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 64");
    }

    public static int binaryToInt(boolean[] zArr, int i, int i2, int i3, int i4) {
        if ((zArr.length == 0 && i == 0) || i4 == 0) {
            return i2;
        }
        if ((i4 - 1) + i3 < 32) {
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = i5 + i3;
                i2 = (i2 & (~(1 << i6))) | ((zArr[i5 + i] ? 1 : 0) << i6);
            }
            return i2;
        }
        throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 32");
    }

    public static short binaryToShort(boolean[] zArr, int i, short s, int i2, int i3) {
        if ((zArr.length == 0 && i == 0) || i3 == 0) {
            return s;
        }
        if ((i3 - 1) + i2 < 16) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = i4 + i2;
                s = (short) ((s & (~(1 << i5))) | ((zArr[i4 + i] ? 1 : 0) << i5));
            }
            return s;
        }
        throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 16");
    }

    public static byte binaryToByte(boolean[] zArr, int i, byte b, int i2, int i3) {
        if ((zArr.length == 0 && i == 0) || i3 == 0) {
            return b;
        }
        if ((i3 - 1) + i2 < 8) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = i4 + i2;
                b = (byte) ((b & (~(1 << i5))) | ((zArr[i4 + i] ? 1 : 0) << i5));
            }
            return b;
        }
        throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 8");
    }

    public static int[] longToIntArray(long j, int i, int[] iArr, int i2, int i3) {
        if (i3 == 0) {
            return iArr;
        }
        if (((i3 - 1) * 32) + i < 64) {
            for (int i4 = 0; i4 < i3; i4++) {
                iArr[i2 + i4] = (int) ((-1) & (j >> ((i4 * 32) + i)));
            }
            return iArr;
        }
        throw new IllegalArgumentException("(nInts-1)*32+srcPos is greater or equal to than 64");
    }

    public static short[] longToShortArray(long j, int i, short[] sArr, int i2, int i3) {
        if (i3 == 0) {
            return sArr;
        }
        if (((i3 - 1) * 16) + i < 64) {
            for (int i4 = 0; i4 < i3; i4++) {
                sArr[i2 + i4] = (short) (65535 & (j >> ((i4 * 16) + i)));
            }
            return sArr;
        }
        throw new IllegalArgumentException("(nShorts-1)*16+srcPos is greater or equal to than 64");
    }

    public static short[] intToShortArray(int i, int i2, short[] sArr, int i3, int i4) {
        if (i4 == 0) {
            return sArr;
        }
        if (((i4 - 1) * 16) + i2 < 32) {
            for (int i5 = 0; i5 < i4; i5++) {
                sArr[i3 + i5] = (short) ((i >> ((i5 * 16) + i2)) & 65535);
            }
            return sArr;
        }
        throw new IllegalArgumentException("(nShorts-1)*16+srcPos is greater or equal to than 32");
    }

    public static byte[] longToByteArray(long j, int i, byte[] bArr, int i2, int i3) {
        if (i3 == 0) {
            return bArr;
        }
        if (((i3 - 1) * 8) + i < 64) {
            for (int i4 = 0; i4 < i3; i4++) {
                bArr[i2 + i4] = (byte) (255 & (j >> ((i4 * 8) + i)));
            }
            return bArr;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greater or equal to than 64");
    }

    public static byte[] intToByteArray(int i, int i2, byte[] bArr, int i3, int i4) {
        if (i4 == 0) {
            return bArr;
        }
        if (((i4 - 1) * 8) + i2 < 32) {
            for (int i5 = 0; i5 < i4; i5++) {
                bArr[i3 + i5] = (byte) ((i >> ((i5 * 8) + i2)) & 255);
            }
            return bArr;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greater or equal to than 32");
    }

    public static byte[] shortToByteArray(short s, int i, byte[] bArr, int i2, int i3) {
        if (i3 == 0) {
            return bArr;
        }
        if (((i3 - 1) * 8) + i < 16) {
            for (int i4 = 0; i4 < i3; i4++) {
                bArr[i2 + i4] = (byte) ((s >> ((i4 * 8) + i)) & 255);
            }
            return bArr;
        }
        throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greater or equal to than 16");
    }

    public static String longToHex(long j, int i, String str, int i2, int i3) {
        if (i3 == 0) {
            return str;
        }
        if (((i3 - 1) * 4) + i >= 64) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 64");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (int) ((j >> ((i4 * 4) + i)) & 15);
            int i6 = i2 + i4;
            if (i6 == length) {
                length++;
                sb.append(intToHexDigit(i5));
            } else {
                sb.setCharAt(i6, intToHexDigit(i5));
            }
        }
        return sb.toString();
    }

    public static String intToHex(int i, int i2, String str, int i3, int i4) {
        if (i4 == 0) {
            return str;
        }
        if (((i4 - 1) * 4) + i2 >= 32) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 32");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = (i >> ((i5 * 4) + i2)) & 15;
            int i7 = i3 + i5;
            if (i7 == length) {
                length++;
                sb.append(intToHexDigit(i6));
            } else {
                sb.setCharAt(i7, intToHexDigit(i6));
            }
        }
        return sb.toString();
    }

    public static String shortToHex(short s, int i, String str, int i2, int i3) {
        if (i3 == 0) {
            return str;
        }
        if (((i3 - 1) * 4) + i >= 16) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 16");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (s >> ((i4 * 4) + i)) & 15;
            int i6 = i2 + i4;
            if (i6 == length) {
                length++;
                sb.append(intToHexDigit(i5));
            } else {
                sb.setCharAt(i6, intToHexDigit(i5));
            }
        }
        return sb.toString();
    }

    public static String byteToHex(byte b, int i, String str, int i2, int i3) {
        if (i3 == 0) {
            return str;
        }
        if (((i3 - 1) * 4) + i >= 8) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 8");
        }
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (b >> ((i4 * 4) + i)) & 15;
            int i6 = i2 + i4;
            if (i6 == length) {
                length++;
                sb.append(intToHexDigit(i5));
            } else {
                sb.setCharAt(i6, intToHexDigit(i5));
            }
        }
        return sb.toString();
    }

    public static boolean[] longToBinary(long j, int i, boolean[] zArr, int i2, int i3) {
        if (i3 == 0) {
            return zArr;
        }
        if ((i3 - 1) + i < 64) {
            for (int i4 = 0; i4 < i3; i4++) {
                zArr[i2 + i4] = (1 & (j >> (i4 + i))) != 0;
            }
            return zArr;
        }
        throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 64");
    }

    public static boolean[] intToBinary(int i, int i2, boolean[] zArr, int i3, int i4) {
        if (i4 == 0) {
            return zArr;
        }
        if ((i4 - 1) + i2 < 32) {
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = i3 + i5;
                boolean z = true;
                if (((i >> (i5 + i2)) & 1) == 0) {
                    z = false;
                }
                zArr[i6] = z;
            }
            return zArr;
        }
        throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 32");
    }

    public static boolean[] shortToBinary(short s, int i, boolean[] zArr, int i2, int i3) {
        if (i3 == 0) {
            return zArr;
        }
        if ((i3 - 1) + i < 16) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = i2 + i4;
                boolean z = true;
                if (((s >> (i4 + i)) & 1) == 0) {
                    z = false;
                }
                zArr[i5] = z;
            }
            return zArr;
        }
        throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 16");
    }

    public static boolean[] byteToBinary(byte b, int i, boolean[] zArr, int i2, int i3) {
        if (i3 == 0) {
            return zArr;
        }
        if ((i3 - 1) + i < 8) {
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = i2 + i4;
                boolean z = true;
                if (((b >> (i4 + i)) & 1) == 0) {
                    z = false;
                }
                zArr[i5] = z;
            }
            return zArr;
        }
        throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 8");
    }

    public static byte[] uuidToByteArray(UUID uuid, byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return bArr;
        }
        if (i2 > 16) {
            throw new IllegalArgumentException("nBytes is greater than 16");
        }
        longToByteArray(uuid.getMostSignificantBits(), 0, bArr, i, Math.min(i2, 8));
        if (i2 >= 8) {
            longToByteArray(uuid.getLeastSignificantBits(), 0, bArr, i + 8, i2 - 8);
        }
        return bArr;
    }

    public static UUID byteArrayToUuid(byte[] bArr, int i) {
        if (bArr.length - i < 16) {
            throw new IllegalArgumentException("Need at least 16 bytes for UUID");
        }
        return new UUID(byteArrayToLong(bArr, i, 0L, 0, 8), byteArrayToLong(bArr, i + 8, 0L, 0, 8));
    }
}
