package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.PDContentStream;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.io.RandomAccessBuffer;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PDFStreamParser extends BaseParser {
    private static final int MAX_BIN_CHAR_TEST_LENGTH = 10;
    private final byte[] binCharTestArr;
    private final List<Object> streamObjects;

    private boolean isSpaceOrReturn(int i) {
        return i == 10 || i == 13 || i == 32;
    }

    @Deprecated
    public PDFStreamParser(PDStream pDStream) throws IOException {
        super(new InputStreamSource(pDStream.createInputStream()));
        this.streamObjects = new ArrayList(100);
        this.binCharTestArr = new byte[10];
    }

    @Deprecated
    public PDFStreamParser(COSStream cOSStream) throws IOException {
        super(new InputStreamSource(cOSStream.createInputStream()));
        this.streamObjects = new ArrayList(100);
        this.binCharTestArr = new byte[10];
    }

    public PDFStreamParser(PDContentStream pDContentStream) throws IOException {
        super(new InputStreamSource(pDContentStream.getContents()));
        this.streamObjects = new ArrayList(100);
        this.binCharTestArr = new byte[10];
    }

    public PDFStreamParser(byte[] bArr) {
        super(new RandomAccessSource(new RandomAccessBuffer(bArr)));
        this.streamObjects = new ArrayList(100);
        this.binCharTestArr = new byte[10];
    }

    public void parse() throws IOException {
        while (true) {
            Object parseNextToken = parseNextToken();
            if (parseNextToken == null) {
                return;
            }
            this.streamObjects.add(parseNextToken);
        }
    }

    public List<Object> getTokens() {
        return this.streamObjects;
    }

    /* JADX WARN: Removed duplicated region for block: B:246:0x0218  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:250:0x0224 -> B:237:0x01f9). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object parseNextToken() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 586
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.PDFStreamParser.parseNextToken():java.lang.Object");
    }

    private boolean hasNoFollowingBinData(SequentialSource sequentialSource) throws IOException {
        int i = 10;
        int read = sequentialSource.read(this.binCharTestArr, 0, 10);
        boolean z = true;
        if (read > 0) {
            int i2 = -1;
            int i3 = -1;
            for (int i4 = 0; i4 < read; i4++) {
                byte b = this.binCharTestArr[i4];
                if ((b != 0 && b < 9) || (b > 10 && b < 32 && b != 13)) {
                    z = false;
                    break;
                }
                if (i2 == -1 && b != 0 && b != 9 && b != 32 && b != 10 && b != 13) {
                    i2 = i4;
                } else if (i2 != -1 && i3 == -1 && (b == 0 || b == 9 || b == 32 || b == 10 || b == 13)) {
                    i3 = i4;
                }
            }
            if (i3 != -1 && i2 != -1) {
                String str = new String(this.binCharTestArr, i2, i3 - i2);
                if (!OperatorName.RESTORE.equals(str) && !OperatorName.END_MARKED_CONTENT.equals(str) && !"S".equals(str)) {
                    z = false;
                }
            }
            if (read == 10) {
                if (i2 == -1 || i3 != -1) {
                    i = i3;
                }
                if (i != -1 && i2 != -1 && i - i2 > 3) {
                    z = false;
                }
            }
            sequentialSource.unread(this.binCharTestArr, 0, read);
        }
        if (!z) {
            Log.w("PdfBox-Android", "ignoring 'EI' assumed to be in the middle of inline image at stream offset " + sequentialSource.getPosition());
        }
        return z;
    }

    protected String readOperator() throws IOException {
        skipSpaces();
        StringBuilder sb = new StringBuilder(4);
        int peek = this.seqSource.peek();
        while (peek != -1 && !isWhitespace(peek) && !isClosing(peek) && peek != 91 && peek != 60 && peek != 40 && peek != 47 && (peek < 48 || peek > 57)) {
            char read = (char) this.seqSource.read();
            int peek2 = this.seqSource.peek();
            sb.append(read);
            if (read == 'd' && (peek2 == 48 || peek2 == 49)) {
                sb.append((char) this.seqSource.read());
                peek = this.seqSource.peek();
            } else {
                peek = peek2;
            }
        }
        return sb.toString();
    }

    private boolean hasNextSpaceOrReturn() throws IOException {
        return isSpaceOrReturn(this.seqSource.peek());
    }
}
