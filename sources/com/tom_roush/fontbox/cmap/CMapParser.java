package com.tom_roush.fontbox.cmap;

import com.tom_roush.fontbox.util.Charsets;
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.List;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class CMapParser {
    private static final String MARK_END_OF_ARRAY = "]";
    private static final String MARK_END_OF_DICTIONARY = ">>";
    private boolean strictMode;
    private final byte[] tokenParserByteBuffer;

    private boolean isDelimiter(int i) {
        return i == 37 || i == 47 || i == 60 || i == 62 || i == 91 || i == 93 || i == 123 || i == 125 || i == 40 || i == 41;
    }

    private boolean isWhitespaceOrEOF(int i) {
        return i == -1 || i == 32 || i == 13 || i == 10;
    }

    public CMapParser() {
        this.tokenParserByteBuffer = new byte[512];
        this.strictMode = false;
    }

    public CMapParser(boolean z) {
        this.tokenParserByteBuffer = new byte[512];
        this.strictMode = z;
    }

    public CMap parse(File file) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                CMap parse = parse(fileInputStream2);
                fileInputStream2.close();
                return parse;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public CMap parsePredefined(String str) throws IOException {
        InputStream inputStream;
        try {
            inputStream = getExternalCMap(str);
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            this.strictMode = false;
            CMap parse = parse(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return parse;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public CMap parse(InputStream inputStream) throws IOException {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
        CMap cMap = new CMap();
        Integer num = null;
        while (true) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken == null) {
                break;
            }
            if (parseNextToken instanceof Operator) {
                Operator operator = (Operator) parseNextToken;
                if (operator.op.equals("endcmap")) {
                    break;
                } else if (num != null) {
                    if (operator.op.equals("usecmap") && (num instanceof LiteralName)) {
                        parseUsecmap((LiteralName) num, cMap);
                    } else if (num instanceof Number) {
                        if (operator.op.equals("begincodespacerange")) {
                            parseBegincodespacerange(num, pushbackInputStream, cMap);
                        } else if (operator.op.equals("beginbfchar")) {
                            parseBeginbfchar(num, pushbackInputStream, cMap);
                        } else if (operator.op.equals("beginbfrange")) {
                            parseBeginbfrange(num, pushbackInputStream, cMap);
                        } else if (operator.op.equals("begincidchar")) {
                            parseBegincidchar(num, pushbackInputStream, cMap);
                        } else if (operator.op.equals("begincidrange") && (num instanceof Integer)) {
                            parseBegincidrange(num.intValue(), pushbackInputStream, cMap);
                        }
                    }
                }
            } else if (parseNextToken instanceof LiteralName) {
                parseLiteralName((LiteralName) parseNextToken, pushbackInputStream, cMap);
            }
            num = parseNextToken;
        }
        return cMap;
    }

    private void parseUsecmap(LiteralName literalName, CMap cMap) throws IOException {
        cMap.useCmap(parse(getExternalCMap(literalName.name)));
    }

    private void parseLiteralName(LiteralName literalName, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        if ("WMode".equals(literalName.name)) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Integer) {
                cMap.setWMode(((Integer) parseNextToken).intValue());
            }
        } else if ("CMapName".equals(literalName.name)) {
            Object parseNextToken2 = parseNextToken(pushbackInputStream);
            if (parseNextToken2 instanceof LiteralName) {
                cMap.setName(((LiteralName) parseNextToken2).name);
            }
        } else if ("CMapVersion".equals(literalName.name)) {
            Object parseNextToken3 = parseNextToken(pushbackInputStream);
            if (parseNextToken3 instanceof Number) {
                cMap.setVersion(parseNextToken3.toString());
            } else if (parseNextToken3 instanceof String) {
                cMap.setVersion((String) parseNextToken3);
            }
        } else if ("CMapType".equals(literalName.name)) {
            Object parseNextToken4 = parseNextToken(pushbackInputStream);
            if (parseNextToken4 instanceof Integer) {
                cMap.setType(((Integer) parseNextToken4).intValue());
            }
        } else if ("Registry".equals(literalName.name)) {
            Object parseNextToken5 = parseNextToken(pushbackInputStream);
            if (parseNextToken5 instanceof String) {
                cMap.setRegistry((String) parseNextToken5);
            }
        } else if ("Ordering".equals(literalName.name)) {
            Object parseNextToken6 = parseNextToken(pushbackInputStream);
            if (parseNextToken6 instanceof String) {
                cMap.setOrdering((String) parseNextToken6);
            }
        } else if ("Supplement".equals(literalName.name)) {
            Object parseNextToken7 = parseNextToken(pushbackInputStream);
            if (parseNextToken7 instanceof Integer) {
                cMap.setSupplement(((Integer) parseNextToken7).intValue());
            }
        }
    }

    private void checkExpectedOperator(Operator operator, String str, String str2) throws IOException {
        if (!operator.op.equals(str)) {
            throw new IOException("Error : ~" + str2 + " contains an unexpected operator : " + operator.op);
        }
    }

    private void parseBegincodespacerange(Number number, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        for (int i = 0; i < number.intValue(); i++) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Operator) {
                checkExpectedOperator((Operator) parseNextToken, "endcodespacerange", "codespacerange");
                return;
            } else if (!(parseNextToken instanceof byte[])) {
                throw new IOException("start range missing");
            } else {
                try {
                    cMap.addCodespaceRange(new CodespaceRange((byte[]) parseNextToken, (byte[]) parseNextToken(pushbackInputStream)));
                } catch (IllegalArgumentException e) {
                    throw new IOException(e);
                }
            }
        }
    }

    private void parseBeginbfchar(Number number, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        for (int i = 0; i < number.intValue(); i++) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Operator) {
                checkExpectedOperator((Operator) parseNextToken, "endbfchar", "bfchar");
                return;
            } else if (!(parseNextToken instanceof byte[])) {
                throw new IOException("input code missing");
            } else {
                byte[] bArr = (byte[]) parseNextToken;
                Object parseNextToken2 = parseNextToken(pushbackInputStream);
                if (parseNextToken2 instanceof byte[]) {
                    cMap.addCharMapping(bArr, createStringFromBytes((byte[]) parseNextToken2));
                } else if (!(parseNextToken2 instanceof LiteralName)) {
                    throw new IOException("Error parsing CMap beginbfchar, expected{COSString or COSName} and not " + parseNextToken2);
                } else {
                    cMap.addCharMapping(bArr, ((LiteralName) parseNextToken2).name);
                }
            }
        }
    }

    private void parseBegincidrange(int i, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Operator) {
                checkExpectedOperator((Operator) parseNextToken, "endcidrange", "cidrange");
                return;
            } else if (!(parseNextToken instanceof byte[])) {
                throw new IOException("start range missing");
            } else {
                byte[] bArr = (byte[]) parseNextToken;
                int createIntFromBytes = createIntFromBytes(bArr);
                byte[] bArr2 = (byte[]) parseNextToken(pushbackInputStream);
                int createIntFromBytes2 = createIntFromBytes(bArr2);
                int intValue = ((Integer) parseNextToken(pushbackInputStream)).intValue();
                if (bArr.length > 2 || bArr2.length > 2) {
                    int i3 = (createIntFromBytes2 + intValue) - createIntFromBytes;
                    while (intValue <= i3) {
                        cMap.addCIDMapping(intValue, createIntFromBytes(bArr));
                        increment(bArr, bArr.length - 1, false);
                        intValue++;
                    }
                } else if (createIntFromBytes2 == createIntFromBytes) {
                    cMap.addCIDMapping(intValue, createIntFromBytes);
                } else {
                    cMap.addCIDRange((char) createIntFromBytes, (char) createIntFromBytes2, intValue);
                }
            }
        }
    }

    private void parseBegincidchar(Number number, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        for (int i = 0; i < number.intValue(); i++) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Operator) {
                checkExpectedOperator((Operator) parseNextToken, "endcidchar", "cidchar");
                return;
            } else if (!(parseNextToken instanceof byte[])) {
                throw new IOException("start code missing");
            } else {
                cMap.addCIDMapping(((Integer) parseNextToken(pushbackInputStream)).intValue(), createIntFromBytes((byte[]) parseNextToken));
            }
        }
    }

    private void parseBeginbfrange(Number number, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        for (int i = 0; i < number.intValue(); i++) {
            Object parseNextToken = parseNextToken(pushbackInputStream);
            if (parseNextToken instanceof Operator) {
                checkExpectedOperator((Operator) parseNextToken, "endbfrange", "bfrange");
                return;
            } else if (!(parseNextToken instanceof byte[])) {
                throw new IOException("start code missing");
            } else {
                byte[] bArr = (byte[]) parseNextToken;
                Object parseNextToken2 = parseNextToken(pushbackInputStream);
                if (parseNextToken2 instanceof Operator) {
                    checkExpectedOperator((Operator) parseNextToken2, "endbfrange", "bfrange");
                    return;
                } else if (!(parseNextToken2 instanceof byte[])) {
                    throw new IOException("end code missing");
                } else {
                    byte[] bArr2 = (byte[]) parseNextToken2;
                    int i2 = CMap.toInt(bArr, bArr.length);
                    int i3 = CMap.toInt(bArr2, bArr2.length);
                    if (i3 < i2) {
                        return;
                    }
                    Object parseNextToken3 = parseNextToken(pushbackInputStream);
                    if (parseNextToken3 instanceof List) {
                        List<byte[]> list = (List) parseNextToken3;
                        if (!list.isEmpty() && list.size() >= i3 - i2) {
                            addMappingFrombfrange(cMap, bArr, list);
                        }
                    } else if (parseNextToken3 instanceof byte[]) {
                        byte[] bArr3 = (byte[]) parseNextToken3;
                        if (bArr3.length > 0) {
                            if (bArr3.length == 2 && i2 == 0 && i3 == 65535 && bArr3[0] == 0 && bArr3[1] == 0) {
                                for (int i4 = 0; i4 < 256; i4++) {
                                    byte b = (byte) i4;
                                    bArr[0] = b;
                                    bArr[1] = 0;
                                    bArr3[0] = b;
                                    bArr3[1] = 0;
                                    addMappingFrombfrange(cMap, bArr, 256, bArr3);
                                }
                            } else {
                                addMappingFrombfrange(cMap, bArr, (i3 - i2) + 1, bArr3);
                            }
                        }
                    }
                }
            }
        }
    }

    private void addMappingFrombfrange(CMap cMap, byte[] bArr, List<byte[]> list) {
        for (byte[] bArr2 : list) {
            cMap.addCharMapping(bArr, createStringFromBytes(bArr2));
            increment(bArr, bArr.length - 1, false);
        }
    }

    private void addMappingFrombfrange(CMap cMap, byte[] bArr, int i, byte[] bArr2) {
        for (int i2 = 0; i2 < i; i2++) {
            cMap.addCharMapping(bArr, createStringFromBytes(bArr2));
            if (!increment(bArr2, bArr2.length - 1, this.strictMode)) {
                return;
            }
            increment(bArr, bArr.length - 1, false);
        }
    }

    protected InputStream getExternalCMap(String str) throws IOException {
        if (PDFBoxResourceLoader.isReady()) {
            return new BufferedInputStream(PDFBoxResourceLoader.getStream("com/tom_roush/fontbox/resources/cmap/" + str));
        }
        InputStream resourceAsStream = getClass().getResourceAsStream("/com/tom_roush/fontbox/resources/cmap/" + str);
        if (resourceAsStream == null) {
            throw new IOException("Error: Could not find referenced cmap stream " + str);
        }
        return new BufferedInputStream(resourceAsStream);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:223:0x00ae A[Catch: NumberFormatException -> 0x00b8, TryCatch #0 {NumberFormatException -> 0x00b8, blocks: (B:221:0x00a8, B:223:0x00ae, B:224:0x00b3), top: B:303:0x00a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x00b3 A[Catch: NumberFormatException -> 0x00b8, TRY_LEAVE, TryCatch #0 {NumberFormatException -> 0x00b8, blocks: (B:221:0x00a8, B:223:0x00ae, B:224:0x00b3), top: B:303:0x00a8 }] */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v16, types: [com.tom_roush.fontbox.cmap.CMapParser$Operator] */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.Object parseNextToken(java.io.PushbackInputStream r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 574
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.fontbox.cmap.CMapParser.parseNextToken(java.io.PushbackInputStream):java.lang.Object");
    }

    private void readUntilEndOfLine(InputStream inputStream, StringBuilder sb) throws IOException {
        int read = inputStream.read();
        while (read != -1 && read != 13 && read != 10) {
            sb.append((char) read);
            read = inputStream.read();
        }
    }

    private boolean increment(byte[] bArr, int i, boolean z) {
        if (i <= 0 || (bArr[i] & UByte.MAX_VALUE) != 255) {
            bArr[i] = (byte) (bArr[i] + 1);
        } else if (z) {
            return false;
        } else {
            bArr[i] = 0;
            increment(bArr, i - 1, z);
        }
        return true;
    }

    private int createIntFromBytes(byte[] bArr) {
        int i = bArr[0] & UByte.MAX_VALUE;
        return bArr.length == 2 ? (i << 8) + (bArr[1] & UByte.MAX_VALUE) : i;
    }

    private String createStringFromBytes(byte[] bArr) {
        return new String(bArr, bArr.length == 1 ? Charsets.ISO_8859_1 : Charsets.UTF_16BE);
    }

    /* loaded from: classes3.dex */
    public static final class LiteralName {
        private String name;

        private LiteralName(String str) {
            this.name = str;
        }
    }

    /* loaded from: classes3.dex */
    public static final class Operator {
        private String op;

        private Operator(String str) {
            this.op = str;
        }
    }
}
