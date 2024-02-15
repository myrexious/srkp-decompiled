package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.io.RandomAccessBuffer;
import com.tom_roush.pdfbox.io.RandomAccessFile;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class FDFParser extends COSParser {
    public FDFParser(String str) throws IOException {
        this(new File(str));
    }

    public FDFParser(File file) throws IOException {
        super(new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER));
        this.fileLen = file.length();
        init();
    }

    public FDFParser(InputStream inputStream) throws IOException {
        super(new RandomAccessBuffer(inputStream));
        this.fileLen = this.source.length();
        init();
    }

    @Override // com.tom_roush.pdfbox.pdfparser.COSParser
    protected final boolean isCatalog(COSDictionary cOSDictionary) {
        return cOSDictionary.containsKey(COSName.FDF);
    }

    private void init() {
        String property = System.getProperty(COSParser.SYSPROP_EOFLOOKUPRANGE);
        if (property != null) {
            try {
                setEOFLookupRange(Integer.parseInt(property));
            } catch (NumberFormatException unused) {
                Log.w("PdfBox-Android", "System property com.tom_roush.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange does not contain an integer value, but: '" + property + OperatorName.SHOW_TEXT_LINE);
            }
        }
        this.document = new COSDocument();
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0016, code lost:
        if (isLenient() != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0020, code lost:
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0021, code lost:
        r2 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initialParse() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 1
            r1 = 0
            long r2 = r6.getStartxrefOffset()     // Catch: java.io.IOException -> L19
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r5 = 0
            if (r4 <= 0) goto L12
            com.tom_roush.pdfbox.cos.COSDictionary r2 = r6.parseXref(r2)     // Catch: java.io.IOException -> L19
            goto L22
        L12:
            boolean r2 = r6.isLenient()     // Catch: java.io.IOException -> L19
            if (r2 == 0) goto L21
            goto L20
        L19:
            r2 = move-exception
            boolean r3 = r6.isLenient()
            if (r3 == 0) goto L3b
        L20:
            r5 = r0
        L21:
            r2 = r1
        L22:
            if (r5 == 0) goto L28
            com.tom_roush.pdfbox.cos.COSDictionary r2 = r6.rebuildTrailer()
        L28:
            com.tom_roush.pdfbox.cos.COSBase r2 = r6.parseTrailerValuesDynamically(r2)
            boolean r3 = r2 instanceof com.tom_roush.pdfbox.cos.COSDictionary
            if (r3 == 0) goto L38
            com.tom_roush.pdfbox.cos.COSDictionary r2 = (com.tom_roush.pdfbox.cos.COSDictionary) r2
            r3 = r1
            com.tom_roush.pdfbox.cos.COSName[] r3 = (com.tom_roush.pdfbox.cos.COSName[]) r3
            r6.parseDictObjects(r2, r1)
        L38:
            r6.initialParseDone = r0
            return
        L3b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.FDFParser.initialParse():void");
    }

    public void parse() throws IOException {
        try {
            if (!parseFDFHeader()) {
                throw new IOException("Error: Header doesn't contain versioninfo");
            }
            initialParse();
        } catch (Throwable th) {
            if (this.document != null) {
                IOUtils.closeQuietly(this.document);
                this.document = null;
            }
            throw th;
        }
    }
}
