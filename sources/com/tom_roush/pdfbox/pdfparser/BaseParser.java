package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSObjectKey;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public abstract class BaseParser {
    protected static final int A = 97;
    protected static final byte ASCII_CR = 13;
    protected static final byte ASCII_LF = 10;
    private static final byte ASCII_NINE = 57;
    private static final byte ASCII_SPACE = 32;
    private static final byte ASCII_ZERO = 48;
    protected static final int B = 98;
    protected static final int D = 100;
    public static final String DEF = "def";
    protected static final int E = 101;
    protected static final String ENDOBJ_STRING = "endobj";
    protected static final String ENDSTREAM_STRING = "endstream";
    private static final String FALSE = "false";
    private static final long GENERATION_NUMBER_THRESHOLD = 65535;
    protected static final int J = 106;
    protected static final int M = 109;
    static final int MAX_LENGTH_LONG = Long.toString(Long.MAX_VALUE).length();
    protected static final int N = 110;
    private static final String NULL = "null";
    protected static final int O = 111;
    private static final long OBJECT_NUMBER_THRESHOLD = 10000000000L;
    protected static final int R = 114;
    protected static final int S = 115;
    protected static final String STREAM_STRING = "stream";
    protected static final int T = 116;
    private static final String TRUE = "true";
    protected COSDocument document;
    final SequentialSource seqSource;
    private final CharsetDecoder utf8Decoder = Charsets.UTF_8.newDecoder();

    private boolean isCR(int i) {
        return 13 == i;
    }

    public static boolean isDigit(int i) {
        return i >= 48 && i <= 57;
    }

    private boolean isLF(int i) {
        return 10 == i;
    }

    public boolean isClosing(int i) {
        return i == 93;
    }

    public boolean isEndOfName(int i) {
        return i == 32 || i == 13 || i == 10 || i == 9 || i == 62 || i == 60 || i == 91 || i == 47 || i == 93 || i == 41 || i == 40 || i == 0 || i == 12 || i == 37;
    }

    protected boolean isSpace(int i) {
        return 32 == i;
    }

    public boolean isWhitespace(int i) {
        return i == 0 || i == 9 || i == 12 || i == 10 || i == 13 || i == 32;
    }

    public BaseParser(SequentialSource sequentialSource) {
        this.seqSource = sequentialSource;
    }

    private static boolean isHexDigit(char c) {
        return isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }

    private COSBase parseCOSDictionaryValue() throws IOException {
        long position = this.seqSource.getPosition();
        COSBase parseDirObject = parseDirObject();
        skipSpaces();
        if ((parseDirObject instanceof COSNumber) && isDigit()) {
            long position2 = this.seqSource.getPosition();
            COSBase parseDirObject2 = parseDirObject();
            skipSpaces();
            readExpectedChar('R');
            if (!(parseDirObject instanceof COSInteger)) {
                Log.e("PdfBox-Android", "expected number, actual=" + parseDirObject + " at offset " + position);
                return COSNull.NULL;
            } else if (!(parseDirObject2 instanceof COSInteger)) {
                Log.e("PdfBox-Android", "expected number, actual=" + parseDirObject2 + " at offset " + position2);
                return COSNull.NULL;
            } else {
                long longValue = ((COSInteger) parseDirObject).longValue();
                if (longValue <= 0) {
                    Log.w("PdfBox-Android", "invalid object number value =" + longValue + " at offset " + position);
                    return COSNull.NULL;
                }
                int intValue = ((COSInteger) parseDirObject2).intValue();
                if (intValue < 0) {
                    Log.e("PdfBox-Android", "invalid generation number value =" + intValue + " at offset " + position);
                    return COSNull.NULL;
                }
                return getObjectFromPool(new COSObjectKey(longValue, intValue));
            }
        }
        return parseDirObject;
    }

    private COSBase getObjectFromPool(COSObjectKey cOSObjectKey) throws IOException {
        COSDocument cOSDocument = this.document;
        if (cOSDocument == null) {
            throw new IOException("object reference " + cOSObjectKey + " at offset " + this.seqSource.getPosition() + " in content stream");
        }
        return cOSDocument.getObjectFromPool(cOSObjectKey);
    }

    public COSDictionary parseCOSDictionary() throws IOException {
        readExpectedChar(Typography.less);
        readExpectedChar(Typography.less);
        skipSpaces();
        COSDictionary cOSDictionary = new COSDictionary();
        boolean z = false;
        while (!z) {
            skipSpaces();
            char peek = (char) this.seqSource.peek();
            if (peek == '>') {
                z = true;
            } else if (peek == '/') {
                if (!parseCOSDictionaryNameValuePair(cOSDictionary)) {
                    return cOSDictionary;
                }
            } else {
                Log.w("PdfBox-Android", "Invalid dictionary, found: '" + peek + "' but expected: '/' at offset " + this.seqSource.getPosition());
                if (readUntilEndOfCOSDictionary()) {
                    return cOSDictionary;
                }
            }
        }
        readExpectedChar(Typography.greater);
        readExpectedChar(Typography.greater);
        return cOSDictionary;
    }

    /* JADX WARN: Code restructure failed: missing block: B:93:0x0087, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0092, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean readUntilEndOfCOSDictionary() throws java.io.IOException {
        /*
            r6 = this;
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
        L6:
            r1 = -1
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L90
            r4 = 47
            if (r0 == r4) goto L90
            r4 = 62
            if (r0 == r4) goto L90
            r1 = 101(0x65, float:1.42E-43)
            if (r0 != r1) goto L88
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            r4 = 110(0x6e, float:1.54E-43)
            if (r0 != r4) goto L88
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            r4 = 100
            if (r0 != r4) goto L88
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            r4 = 115(0x73, float:1.61E-43)
            if (r0 != r4) goto L67
            com.tom_roush.pdfbox.pdfparser.SequentialSource r4 = r6.seqSource
            int r4 = r4.read()
            r5 = 116(0x74, float:1.63E-43)
            if (r4 != r5) goto L67
            com.tom_roush.pdfbox.pdfparser.SequentialSource r4 = r6.seqSource
            int r4 = r4.read()
            r5 = 114(0x72, float:1.6E-43)
            if (r4 != r5) goto L67
            com.tom_roush.pdfbox.pdfparser.SequentialSource r4 = r6.seqSource
            int r4 = r4.read()
            if (r4 != r1) goto L67
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r6.seqSource
            int r1 = r1.read()
            r4 = 97
            if (r1 != r4) goto L67
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r6.seqSource
            int r1 = r1.read()
            r4 = 109(0x6d, float:1.53E-43)
            if (r1 != r4) goto L67
            r1 = r3
            goto L68
        L67:
            r1 = r2
        L68:
            if (r1 != 0) goto L83
            r4 = 111(0x6f, float:1.56E-43)
            if (r0 != r4) goto L83
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            r4 = 98
            if (r0 != r4) goto L83
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            r4 = 106(0x6a, float:1.49E-43)
            if (r0 != r4) goto L83
            r2 = r3
        L83:
            if (r1 != 0) goto L87
            if (r2 == 0) goto L88
        L87:
            return r3
        L88:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            goto L6
        L90:
            if (r0 != r1) goto L93
            return r3
        L93:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r6.seqSource
            r1.unread(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.BaseParser.readUntilEndOfCOSDictionary():boolean");
    }

    private boolean parseCOSDictionaryNameValuePair(COSDictionary cOSDictionary) throws IOException {
        COSName parseCOSName = parseCOSName();
        if (parseCOSName == null || parseCOSName.getName().isEmpty()) {
            Log.w("PdfBox-Android", "Empty COSName at offset " + this.seqSource.getPosition());
        }
        COSBase parseCOSDictionaryValue = parseCOSDictionaryValue();
        skipSpaces();
        if (parseCOSDictionaryValue == null) {
            Log.w("PdfBox-Android", "Bad dictionary declaration at offset " + this.seqSource.getPosition());
            return false;
        }
        if ((parseCOSDictionaryValue instanceof COSInteger) && !((COSInteger) parseCOSDictionaryValue).isValid()) {
            Log.w("PdfBox-Android", "Skipped out of range number value at offset " + this.seqSource.getPosition());
        } else {
            parseCOSDictionaryValue.setDirect(true);
            cOSDictionary.setItem(parseCOSName, parseCOSDictionaryValue);
        }
        return true;
    }

    public void skipWhiteSpaces() throws IOException {
        int read = this.seqSource.read();
        while (32 == read) {
            read = this.seqSource.read();
        }
        if (13 != read) {
            if (10 != read) {
                this.seqSource.unread(read);
                return;
            }
            return;
        }
        int read2 = this.seqSource.read();
        if (10 != read2) {
            this.seqSource.unread(read2);
        }
    }

    private int checkForEndOfString(int i) throws IOException {
        byte b;
        byte[] bArr = new byte[3];
        int read = this.seqSource.read(bArr);
        if (read == 3 && bArr[0] == 13 && (((b = bArr[1]) == 10 && bArr[2] == 47) || bArr[2] == 62 || b == 47 || b == 62)) {
            i = 0;
        }
        if (read > 0) {
            this.seqSource.unread(bArr, 0, read);
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:170:0x0118 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0020 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.tom_roush.pdfbox.cos.COSString parseCOSString() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.BaseParser.parseCOSString():com.tom_roush.pdfbox.cos.COSString");
    }

    /* JADX WARN: Code restructure failed: missing block: B:90:0x005d, code lost:
        return com.tom_roush.pdfbox.cos.COSString.parseHex(r0.toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.tom_roush.pdfbox.cos.COSString parseCOSHexString() throws java.io.IOException {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L5:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r5.seqSource
            int r1 = r1.read()
            char r2 = (char) r1
            boolean r3 = isHexDigit(r2)
            if (r3 == 0) goto L16
            r0.append(r2)
            goto L5
        L16:
            r2 = 62
            if (r1 != r2) goto L1b
            goto L55
        L1b:
            java.lang.String r3 = "Missing closing bracket for hex string. Reached EOS."
            if (r1 < 0) goto L64
            r4 = 32
            if (r1 == r4) goto L5
            r4 = 10
            if (r1 == r4) goto L5
            r4 = 9
            if (r1 == r4) goto L5
            r4 = 13
            if (r1 == r4) goto L5
            r4 = 8
            if (r1 == r4) goto L5
            r4 = 12
            if (r1 != r4) goto L38
            goto L5
        L38:
            int r1 = r0.length()
            int r1 = r1 % 2
            if (r1 == 0) goto L49
            int r1 = r0.length()
            int r1 = r1 + (-1)
            r0.deleteCharAt(r1)
        L49:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r5.seqSource
            int r1 = r1.read()
            if (r1 == r2) goto L53
            if (r1 >= 0) goto L49
        L53:
            if (r1 < 0) goto L5e
        L55:
            java.lang.String r0 = r0.toString()
            com.tom_roush.pdfbox.cos.COSString r0 = com.tom_roush.pdfbox.cos.COSString.parseHex(r0)
            return r0
        L5e:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r3)
            throw r0
        L64:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.BaseParser.parseCOSHexString():com.tom_roush.pdfbox.cos.COSString");
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x00da, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00db, code lost:
        r9.seqSource.read();
        skipSpaces();
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00e3, code lost:
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.tom_roush.pdfbox.cos.COSArray parseCOSArray() throws java.io.IOException {
        /*
            r9 = this;
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r9.seqSource
            long r0 = r0.getPosition()
            r2 = 91
            r9.readExpectedChar(r2)
            com.tom_roush.pdfbox.cos.COSArray r3 = new com.tom_roush.pdfbox.cos.COSArray
            r3.<init>()
            r9.skipSpaces()
        L13:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r4 = r9.seqSource
            int r4 = r4.peek()
            if (r4 <= 0) goto Ldb
            char r4 = (char) r4
            r5 = 93
            if (r4 == r5) goto Ldb
            com.tom_roush.pdfbox.cos.COSBase r4 = r9.parseDirObject()
            boolean r5 = r4 instanceof com.tom_roush.pdfbox.cos.COSObject
            if (r5 == 0) goto L7c
            int r4 = r3.size()
            r5 = 0
            if (r4 <= 0) goto L7b
            int r4 = r3.size()
            int r4 = r4 + (-1)
            com.tom_roush.pdfbox.cos.COSBase r4 = r3.get(r4)
            boolean r4 = r4 instanceof com.tom_roush.pdfbox.cos.COSInteger
            if (r4 == 0) goto L7b
            int r4 = r3.size()
            int r4 = r4 + (-1)
            com.tom_roush.pdfbox.cos.COSBase r4 = r3.remove(r4)
            com.tom_roush.pdfbox.cos.COSInteger r4 = (com.tom_roush.pdfbox.cos.COSInteger) r4
            int r6 = r3.size()
            if (r6 <= 0) goto L7b
            int r6 = r3.size()
            int r6 = r6 + (-1)
            com.tom_roush.pdfbox.cos.COSBase r6 = r3.get(r6)
            boolean r6 = r6 instanceof com.tom_roush.pdfbox.cos.COSInteger
            if (r6 == 0) goto L7b
            int r5 = r3.size()
            int r5 = r5 + (-1)
            com.tom_roush.pdfbox.cos.COSBase r5 = r3.remove(r5)
            com.tom_roush.pdfbox.cos.COSInteger r5 = (com.tom_roush.pdfbox.cos.COSInteger) r5
            com.tom_roush.pdfbox.cos.COSObjectKey r6 = new com.tom_roush.pdfbox.cos.COSObjectKey
            long r7 = r5.longValue()
            int r4 = r4.intValue()
            r6.<init>(r7, r4)
            com.tom_roush.pdfbox.cos.COSBase r4 = r9.getObjectFromPool(r6)
            goto L7c
        L7b:
            r4 = r5
        L7c:
            if (r4 == 0) goto L82
            r3.add(r4)
            goto Ld5
        L82:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Corrupt array element at offset "
            r4.<init>(r5)
            com.tom_roush.pdfbox.pdfparser.SequentialSource r5 = r9.seqSource
            long r5 = r5.getPosition()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = ", start offset: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "PdfBox-Android"
            android.util.Log.w(r5, r4)
            java.lang.String r4 = r9.readString()
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto Lb9
            com.tom_roush.pdfbox.pdfparser.SequentialSource r5 = r9.seqSource
            int r5 = r5.peek()
            if (r5 != r2) goto Lb9
            return r3
        Lb9:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r5 = r9.seqSource
            java.nio.charset.Charset r6 = com.tom_roush.pdfbox.util.Charsets.ISO_8859_1
            byte[] r6 = r4.getBytes(r6)
            r5.unread(r6)
            java.lang.String r5 = "endobj"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto Lda
            java.lang.String r5 = "endstream"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto Ld5
            goto Lda
        Ld5:
            r9.skipSpaces()
            goto L13
        Lda:
            return r3
        Ldb:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r9.seqSource
            r0.read()
            r9.skipSpaces()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.BaseParser.parseCOSArray():com.tom_roush.pdfbox.cos.COSArray");
    }

    public COSName parseCOSName() throws IOException {
        String str;
        char c;
        readExpectedChar('/');
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read = this.seqSource.read();
        while (read != -1) {
            if (read == 35) {
                int read2 = this.seqSource.read();
                int read3 = this.seqSource.read();
                if (isHexDigit((char) read2)) {
                    char c2 = (char) read3;
                    if (isHexDigit(c2)) {
                        String str2 = Character.toString(c) + c2;
                        try {
                            byteArrayOutputStream.write(Integer.parseInt(str2, 16));
                            read2 = this.seqSource.read();
                            read = read2;
                        } catch (NumberFormatException e) {
                            throw new IOException("Error: expected hex digit, actual='" + str2 + OperatorName.SHOW_TEXT_LINE, e);
                        }
                    }
                }
                if (read3 == -1 || read2 == -1) {
                    Log.e("PdfBox-Android", "Premature EOF in BaseParser#parseCOSName");
                    read = -1;
                    break;
                }
                this.seqSource.unread(read3);
                byteArrayOutputStream.write(read);
                read = read2;
            } else if (isEndOfName(read)) {
                break;
            } else {
                byteArrayOutputStream.write(read);
                read = this.seqSource.read();
            }
        }
        if (read != -1) {
            this.seqSource.unread(read);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (isValidUTF8(byteArray)) {
            str = new String(byteArray, Charsets.UTF_8);
        } else {
            str = new String(byteArray, Charsets.WINDOWS_1252);
        }
        return COSName.getPDFName(str);
    }

    private boolean isValidUTF8(byte[] bArr) {
        try {
            this.utf8Decoder.decode(ByteBuffer.wrap(bArr));
            return true;
        } catch (CharacterCodingException unused) {
            return false;
        }
    }

    protected COSBoolean parseBoolean() throws IOException {
        char peek = (char) this.seqSource.peek();
        if (peek == 't') {
            String str = new String(this.seqSource.readFully(4), Charsets.ISO_8859_1);
            if (!str.equals("true")) {
                throw new IOException("Error parsing boolean: expected='true' actual='" + str + "' at offset " + this.seqSource.getPosition());
            }
            return COSBoolean.TRUE;
        } else if (peek == 'f') {
            String str2 = new String(this.seqSource.readFully(5), Charsets.ISO_8859_1);
            if (!str2.equals("false")) {
                throw new IOException("Error parsing boolean: expected='true' actual='" + str2 + "' at offset " + this.seqSource.getPosition());
            }
            return COSBoolean.FALSE;
        } else {
            throw new IOException("Error parsing boolean expected='t or f' actual='" + peek + "' at offset " + this.seqSource.getPosition());
        }
    }

    public COSBase parseDirObject() throws IOException {
        skipSpaces();
        char peek = (char) this.seqSource.peek();
        if (peek != '(') {
            if (peek != '/') {
                if (peek == '<') {
                    int read = this.seqSource.read();
                    char peek2 = (char) this.seqSource.peek();
                    this.seqSource.unread(read);
                    return peek2 == '<' ? parseCOSDictionary() : parseCOSString();
                } else if (peek == 'R') {
                    this.seqSource.read();
                    return new COSObject(null);
                } else if (peek != '[') {
                    if (peek == 'f') {
                        String str = new String(this.seqSource.readFully(5), Charsets.ISO_8859_1);
                        if (str.equals("false")) {
                            return COSBoolean.FALSE;
                        }
                        throw new IOException("expected false actual='" + str + "' " + this.seqSource + "' at offset " + this.seqSource.getPosition());
                    } else if (peek == 'n') {
                        readExpectedString(NULL);
                        return COSNull.NULL;
                    } else if (peek == 't') {
                        String str2 = new String(this.seqSource.readFully(4), Charsets.ISO_8859_1);
                        if (str2.equals("true")) {
                            return COSBoolean.TRUE;
                        }
                        throw new IOException("expected true actual='" + str2 + "' " + this.seqSource + "' at offset " + this.seqSource.getPosition());
                    } else if (peek != 65535) {
                        if (Character.isDigit(peek) || peek == '-' || peek == '+' || peek == '.') {
                            return parseCOSNumber();
                        }
                        long position = this.seqSource.getPosition();
                        String readString = readString();
                        if (readString.isEmpty()) {
                            int peek3 = this.seqSource.peek();
                            throw new IOException("Unknown dir object c='" + peek + "' cInt=" + ((int) peek) + " peek='" + ((char) peek3) + "' peekInt=" + peek3 + " at offset " + this.seqSource.getPosition() + " (start offset: " + position + ")");
                        }
                        if (ENDOBJ_STRING.equals(readString) || ENDSTREAM_STRING.equals(readString)) {
                            this.seqSource.unread(readString.getBytes(Charsets.ISO_8859_1));
                        } else {
                            Log.w("PdfBox-Android", "Skipped unexpected dir object = '" + readString + "' at offset " + this.seqSource.getPosition() + " (start offset: " + position + ")");
                        }
                        return null;
                    } else {
                        return null;
                    }
                } else {
                    return parseCOSArray();
                }
            }
            return parseCOSName();
        }
        return parseCOSString();
    }

    private COSNumber parseCOSNumber() throws IOException {
        StringBuilder sb = new StringBuilder();
        int read = this.seqSource.read();
        while (true) {
            char c = (char) read;
            if (!Character.isDigit(c) && c != '-' && c != '+' && c != '.' && c != 'E' && c != 'e') {
                break;
            }
            sb.append(c);
            read = this.seqSource.read();
        }
        if (read != -1) {
            this.seqSource.unread(read);
        }
        return COSNumber.get(sb.toString());
    }

    public String readString() throws IOException {
        skipSpaces();
        StringBuilder sb = new StringBuilder();
        int read = this.seqSource.read();
        while (true) {
            char c = (char) read;
            if (isEndOfName(c) || read == -1) {
                break;
            }
            sb.append(c);
            read = this.seqSource.read();
        }
        if (read != -1) {
            this.seqSource.unread(read);
        }
        return sb.toString();
    }

    protected void readExpectedString(String str) throws IOException {
        readExpectedString(str.toCharArray(), false);
    }

    public final void readExpectedString(char[] cArr, boolean z) throws IOException {
        skipSpaces();
        for (char c : cArr) {
            if (this.seqSource.read() != c) {
                throw new IOException("Expected string '" + new String(cArr) + "' but missed at character '" + c + "' at offset " + this.seqSource.getPosition());
            }
        }
        skipSpaces();
    }

    protected void readExpectedChar(char c) throws IOException {
        char read = (char) this.seqSource.read();
        if (read != c) {
            throw new IOException("expected='" + c + "' actual='" + read + "' at offset " + this.seqSource.getPosition());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0040, code lost:
        r4.seqSource.unread(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.String readString(int r5) throws java.io.IOException {
        /*
            r4 = this;
            r4.skipSpaces()
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r4.seqSource
            int r0 = r0.read()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r5)
        Le:
            boolean r2 = r4.isWhitespace(r0)
            r3 = -1
            if (r2 != 0) goto L3e
            boolean r2 = r4.isClosing(r0)
            if (r2 != 0) goto L3e
            if (r0 == r3) goto L3e
            int r2 = r1.length()
            if (r2 >= r5) goto L3e
            r2 = 91
            if (r0 == r2) goto L3e
            r2 = 60
            if (r0 == r2) goto L3e
            r2 = 40
            if (r0 == r2) goto L3e
            r2 = 47
            if (r0 == r2) goto L3e
            char r0 = (char) r0
            r1.append(r0)
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r4.seqSource
            int r0 = r0.read()
            goto Le
        L3e:
            if (r0 == r3) goto L45
            com.tom_roush.pdfbox.pdfparser.SequentialSource r5 = r4.seqSource
            r5.unread(r0)
        L45:
            java.lang.String r5 = r1.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.BaseParser.readString(int):java.lang.String");
    }

    protected boolean isClosing() throws IOException {
        return isClosing(this.seqSource.peek());
    }

    public String readLine() throws IOException {
        int read;
        if (this.seqSource.isEOF()) {
            throw new IOException("Error: End-of-File, expected line at offset " + this.seqSource.getPosition());
        }
        StringBuilder sb = new StringBuilder(11);
        while (true) {
            read = this.seqSource.read();
            if (read == -1 || isEOL(read)) {
                break;
            }
            sb.append((char) read);
        }
        if (isCR(read) && isLF(this.seqSource.peek())) {
            this.seqSource.read();
        }
        return sb.toString();
    }

    protected boolean isEOL() throws IOException {
        return isEOL(this.seqSource.peek());
    }

    protected boolean isEOL(int i) {
        return isLF(i) || isCR(i);
    }

    public boolean isWhitespace() throws IOException {
        return isWhitespace(this.seqSource.peek());
    }

    public boolean isSpace() throws IOException {
        return isSpace(this.seqSource.peek());
    }

    public boolean isDigit() throws IOException {
        return isDigit(this.seqSource.peek());
    }

    public void skipSpaces() throws IOException {
        int read = this.seqSource.read();
        while (true) {
            if (!isWhitespace(read) && read != 37) {
                break;
            } else if (read == 37) {
                read = this.seqSource.read();
                while (!isEOL(read) && read != -1) {
                    read = this.seqSource.read();
                }
            } else {
                read = this.seqSource.read();
            }
        }
        if (read != -1) {
            this.seqSource.unread(read);
        }
    }

    public long readObjectNumber() throws IOException {
        long readLong = readLong();
        if (readLong < 0 || readLong >= OBJECT_NUMBER_THRESHOLD) {
            throw new IOException("Object Number '" + readLong + "' has more than 10 digits or is negative");
        }
        return readLong;
    }

    public int readGenerationNumber() throws IOException {
        int readInt = readInt();
        if (readInt < 0 || readInt > GENERATION_NUMBER_THRESHOLD) {
            throw new IOException("Generation Number '" + readInt + "' has more than 5 digits");
        }
        return readInt;
    }

    protected int readInt() throws IOException {
        skipSpaces();
        StringBuilder readStringNumber = readStringNumber();
        try {
            return Integer.parseInt(readStringNumber.toString());
        } catch (NumberFormatException e) {
            this.seqSource.unread(readStringNumber.toString().getBytes(Charsets.ISO_8859_1));
            throw new IOException("Error: Expected an integer type at offset " + this.seqSource.getPosition() + ", instead got '" + ((Object) readStringNumber) + OperatorName.SHOW_TEXT_LINE, e);
        }
    }

    public long readLong() throws IOException {
        skipSpaces();
        StringBuilder readStringNumber = readStringNumber();
        try {
            return Long.parseLong(readStringNumber.toString());
        } catch (NumberFormatException e) {
            this.seqSource.unread(readStringNumber.toString().getBytes(Charsets.ISO_8859_1));
            throw new IOException("Error: Expected a long type at offset " + this.seqSource.getPosition() + ", instead got '" + ((Object) readStringNumber) + OperatorName.SHOW_TEXT_LINE, e);
        }
    }

    protected final StringBuilder readStringNumber() throws IOException {
        StringBuilder sb = new StringBuilder();
        do {
            int read = this.seqSource.read();
            if (read < 48 || read > 57) {
                if (read != -1) {
                    this.seqSource.unread(read);
                }
                return sb;
            }
            sb.append((char) read);
        } while (sb.length() <= MAX_LENGTH_LONG);
        throw new IOException("Number '" + ((Object) sb) + "' is getting too long, stop reading at offset " + this.seqSource.getPosition());
    }
}
