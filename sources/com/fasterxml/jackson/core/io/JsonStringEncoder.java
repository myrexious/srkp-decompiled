package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import org.apache.commons.io.IOUtils;

/* loaded from: classes.dex */
public final class JsonStringEncoder {
    static final int MAX_BYTE_BUFFER_SIZE = 32000;
    static final int MAX_CHAR_BUFFER_SIZE = 32000;
    static final int MIN_BYTE_BUFFER_SIZE = 24;
    static final int MIN_CHAR_BUFFER_SIZE = 16;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    private static final char[] HC = CharTypes.copyHexChars(true);
    private static final byte[] HB = CharTypes.copyHexBytes(true);
    private static final JsonStringEncoder instance = new JsonStringEncoder();

    private char[] _qbuf() {
        return new char[]{IOUtils.DIR_SEPARATOR_WINDOWS, 0, '0', '0'};
    }

    public static JsonStringEncoder getInstance() {
        return instance;
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x0020, code lost:
        if (r6 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0022, code lost:
        r6 = _qbuf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0026, code lost:
        r9 = r7 + 1;
        r7 = r13.charAt(r7);
        r10 = r2[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x002e, code lost:
        if (r10 >= 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0030, code lost:
        r7 = _appendNumeric(r7, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0035, code lost:
        r7 = _appendNamed(r10, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0039, code lost:
        r10 = r8 + r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x003c, code lost:
        if (r10 <= r1.length) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x003e, code lost:
        r10 = r1.length - r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0040, code lost:
        if (r10 <= 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0042, code lost:
        java.lang.System.arraycopy(r6, 0, r1, r8, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0045, code lost:
        if (r5 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0047, code lost:
        r5 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x004b, code lost:
        r1 = r5.finishCurrentSegment();
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x004f, code lost:
        r7 = r7 - r10;
        java.lang.System.arraycopy(r6, r10, r1, 0, r7);
        r8 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0055, code lost:
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x005b, code lost:
        throw new java.lang.IllegalStateException(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x005c, code lost:
        java.lang.System.arraycopy(r6, 0, r1, r8, r7);
        r8 = r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public char[] quoteAsString(java.lang.String r13) {
        /*
            r12 = this;
            int r0 = r13.length()
            int r1 = _initialCharBufSize(r0)
            char[] r1 = new char[r1]
            int[] r2 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r3 = r2.length
            r4 = 0
            r5 = 0
            r7 = r4
            r8 = r7
            r6 = r5
        L14:
            if (r7 >= r0) goto L84
        L16:
            char r9 = r13.charAt(r7)
            if (r9 >= r3) goto L62
            r10 = r2[r9]
            if (r10 == 0) goto L62
            if (r6 != 0) goto L26
            char[] r6 = r12._qbuf()
        L26:
            int r9 = r7 + 1
            char r7 = r13.charAt(r7)
            r10 = r2[r7]
            if (r10 >= 0) goto L35
            int r7 = r12._appendNumeric(r7, r6)
            goto L39
        L35:
            int r7 = r12._appendNamed(r10, r6)
        L39:
            int r10 = r8 + r7
            int r11 = r1.length
            if (r10 <= r11) goto L5c
            int r10 = r1.length
            int r10 = r10 - r8
            if (r10 <= 0) goto L45
            java.lang.System.arraycopy(r6, r4, r1, r8, r10)
        L45:
            if (r5 != 0) goto L4b
            com.fasterxml.jackson.core.util.TextBuffer r5 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1)
        L4b:
            char[] r1 = r5.finishCurrentSegment()     // Catch: java.io.IOException -> L55
            int r7 = r7 - r10
            java.lang.System.arraycopy(r6, r10, r1, r4, r7)
            r8 = r7
            goto L60
        L55:
            r13 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L5c:
            java.lang.System.arraycopy(r6, r4, r1, r8, r7)
            r8 = r10
        L60:
            r7 = r9
            goto L14
        L62:
            int r10 = r1.length
            if (r8 < r10) goto L78
            if (r5 != 0) goto L6b
            com.fasterxml.jackson.core.util.TextBuffer r5 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1)
        L6b:
            char[] r1 = r5.finishCurrentSegment()     // Catch: java.io.IOException -> L71
            r8 = r4
            goto L78
        L71:
            r13 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L78:
            int r10 = r8 + 1
            r1[r8] = r9
            int r7 = r7 + 1
            if (r7 < r0) goto L82
            r8 = r10
            goto L84
        L82:
            r8 = r10
            goto L16
        L84:
            if (r5 != 0) goto L8b
            char[] r13 = java.util.Arrays.copyOfRange(r1, r4, r8)
            return r13
        L8b:
            r5.setCurrentLength(r8)
            char[] r13 = r5.contentsAsArray()     // Catch: java.io.IOException -> L93
            return r13
        L93:
            r13 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x002b, code lost:
        if (r6 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x002d, code lost:
        r6 = _qbuf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0031, code lost:
        r9 = r7 + 1;
        r7 = r13.charAt(r7);
        r10 = r2[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0039, code lost:
        if (r10 >= 0) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x003b, code lost:
        r7 = _appendNumeric(r7, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0040, code lost:
        r7 = _appendNamed(r10, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0044, code lost:
        r10 = r8 + r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0047, code lost:
        if (r10 <= r1.length) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0049, code lost:
        r10 = r1.length - r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x004b, code lost:
        if (r10 <= 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x004d, code lost:
        java.lang.System.arraycopy(r6, 0, r1, r8, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0050, code lost:
        if (r4 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0052, code lost:
        r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0056, code lost:
        r1 = r4.finishCurrentSegment();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x005a, code lost:
        r7 = r7 - r10;
        java.lang.System.arraycopy(r6, r10, r1, 0, r7);
        r8 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0060, code lost:
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0066, code lost:
        throw new java.lang.IllegalStateException(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0067, code lost:
        java.lang.System.arraycopy(r6, 0, r1, r8, r7);
        r8 = r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public char[] quoteAsString(java.lang.CharSequence r13) {
        /*
            r12 = this;
            boolean r0 = r13 instanceof java.lang.String
            if (r0 == 0) goto Lb
            java.lang.String r13 = (java.lang.String) r13
            char[] r13 = r12.quoteAsString(r13)
            return r13
        Lb:
            int r0 = r13.length()
            int r1 = _initialCharBufSize(r0)
            char[] r1 = new char[r1]
            int[] r2 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r3 = r2.length
            r4 = 0
            r5 = 0
            r6 = r4
            r7 = r5
            r8 = r7
        L1f:
            if (r7 >= r0) goto L8f
        L21:
            char r9 = r13.charAt(r7)
            if (r9 >= r3) goto L6d
            r10 = r2[r9]
            if (r10 == 0) goto L6d
            if (r6 != 0) goto L31
            char[] r6 = r12._qbuf()
        L31:
            int r9 = r7 + 1
            char r7 = r13.charAt(r7)
            r10 = r2[r7]
            if (r10 >= 0) goto L40
            int r7 = r12._appendNumeric(r7, r6)
            goto L44
        L40:
            int r7 = r12._appendNamed(r10, r6)
        L44:
            int r10 = r8 + r7
            int r11 = r1.length
            if (r10 <= r11) goto L67
            int r10 = r1.length
            int r10 = r10 - r8
            if (r10 <= 0) goto L50
            java.lang.System.arraycopy(r6, r5, r1, r8, r10)
        L50:
            if (r4 != 0) goto L56
            com.fasterxml.jackson.core.util.TextBuffer r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1)
        L56:
            char[] r1 = r4.finishCurrentSegment()     // Catch: java.io.IOException -> L60
            int r7 = r7 - r10
            java.lang.System.arraycopy(r6, r10, r1, r5, r7)
            r8 = r7
            goto L6b
        L60:
            r13 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L67:
            java.lang.System.arraycopy(r6, r5, r1, r8, r7)
            r8 = r10
        L6b:
            r7 = r9
            goto L1f
        L6d:
            int r10 = r1.length
            if (r8 < r10) goto L83
            if (r4 != 0) goto L76
            com.fasterxml.jackson.core.util.TextBuffer r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r1)
        L76:
            char[] r1 = r4.finishCurrentSegment()     // Catch: java.io.IOException -> L7c
            r8 = r5
            goto L83
        L7c:
            r13 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        L83:
            int r10 = r8 + 1
            r1[r8] = r9
            int r7 = r7 + 1
            if (r7 < r0) goto L8d
            r8 = r10
            goto L8f
        L8d:
            r8 = r10
            goto L21
        L8f:
            if (r4 != 0) goto L96
            char[] r13 = java.util.Arrays.copyOfRange(r1, r5, r8)
            return r13
        L96:
            r4.setCurrentLength(r8)
            char[] r13 = r4.contentsAsArray()     // Catch: java.io.IOException -> L9e
            return r13
        L9e:
            r13 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.CharSequence):char[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0018, code lost:
        if (r4 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x001a, code lost:
        r4 = _qbuf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x001e, code lost:
        r6 = r5 + 1;
        r5 = r9.charAt(r5);
        r7 = r0[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0026, code lost:
        if (r7 >= 0) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0028, code lost:
        r5 = _appendNumeric(r5, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x002d, code lost:
        r5 = _appendNamed(r7, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0031, code lost:
        r10.append(r4, 0, r5);
        r5 = r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void quoteAsString(java.lang.CharSequence r9, java.lang.StringBuilder r10) {
        /*
            r8 = this;
            int[] r0 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r1 = r0.length
            int r2 = r9.length()
            r3 = 0
            r4 = 0
            r5 = r3
        Lc:
            if (r5 >= r2) goto L3d
        Le:
            char r6 = r9.charAt(r5)
            if (r6 >= r1) goto L36
            r7 = r0[r6]
            if (r7 == 0) goto L36
            if (r4 != 0) goto L1e
            char[] r4 = r8._qbuf()
        L1e:
            int r6 = r5 + 1
            char r5 = r9.charAt(r5)
            r7 = r0[r5]
            if (r7 >= 0) goto L2d
            int r5 = r8._appendNumeric(r5, r4)
            goto L31
        L2d:
            int r5 = r8._appendNamed(r7, r4)
        L31:
            r10.append(r4, r3, r5)
            r5 = r6
            goto Lc
        L36:
            r10.append(r6)
            int r5 = r5 + 1
            if (r5 < r2) goto Le
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.CharSequence, java.lang.StringBuilder):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] quoteAsUTF8(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x00e8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] encodeAsUTF8(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.encodeAsUTF8(java.lang.String):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x00e8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] encodeAsUTF8(java.lang.CharSequence r12) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.encodeAsUTF8(java.lang.CharSequence):byte[]");
    }

    private int _appendNumeric(int i, char[] cArr) {
        cArr[1] = 'u';
        char[] cArr2 = HC;
        cArr[4] = cArr2[i >> 4];
        cArr[5] = cArr2[i & 15];
        return 6;
    }

    private int _appendNamed(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByte(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byte[] bArr = HB;
                byteArrayBuilder.append(bArr[i4 >> 4]);
                byteArrayBuilder.append(bArr[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byte[] bArr2 = HB;
            byteArrayBuilder.append(bArr2[i >> 4]);
            byteArrayBuilder.append(bArr2[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private static int _convert(int i, int i2) {
        if (i2 < 56320 || i2 > 57343) {
            throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
        }
        return ((i - 55296) << 10) + 65536 + (i2 - 56320);
    }

    private static void _illegal(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }

    static int _initialCharBufSize(int i) {
        return Math.min(Math.max(16, i + Math.min((i >> 3) + 6, 1000)), 32000);
    }

    static int _initialByteBufSize(int i) {
        return Math.min(Math.max(24, i + 6 + (i >> 1)), 32000);
    }
}
