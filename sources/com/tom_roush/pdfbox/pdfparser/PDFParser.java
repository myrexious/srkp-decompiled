package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.io.RandomAccessRead;
import com.tom_roush.pdfbox.io.ScratchFile;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class PDFParser extends COSParser {
    public PDFParser(RandomAccessRead randomAccessRead) throws IOException {
        this(randomAccessRead, "", ScratchFile.getMainMemoryOnlyInstance());
    }

    public PDFParser(RandomAccessRead randomAccessRead, ScratchFile scratchFile) throws IOException {
        this(randomAccessRead, "", scratchFile);
    }

    public PDFParser(RandomAccessRead randomAccessRead, String str) throws IOException {
        this(randomAccessRead, str, ScratchFile.getMainMemoryOnlyInstance());
    }

    public PDFParser(RandomAccessRead randomAccessRead, String str, ScratchFile scratchFile) throws IOException {
        this(randomAccessRead, str, null, null, scratchFile);
    }

    public PDFParser(RandomAccessRead randomAccessRead, String str, InputStream inputStream, String str2) throws IOException {
        this(randomAccessRead, str, inputStream, str2, ScratchFile.getMainMemoryOnlyInstance());
    }

    public PDFParser(RandomAccessRead randomAccessRead, String str, InputStream inputStream, String str2, ScratchFile scratchFile) throws IOException {
        super(randomAccessRead, str, inputStream, str2);
        this.fileLen = randomAccessRead.length();
        init(scratchFile);
    }

    private void init(ScratchFile scratchFile) {
        String property = System.getProperty(COSParser.SYSPROP_EOFLOOKUPRANGE);
        if (property != null) {
            try {
                setEOFLookupRange(Integer.parseInt(property));
            } catch (NumberFormatException unused) {
                Log.w("PdfBox-Android", "System property com.tom_roush.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange does not contain an integer value, but: '" + property + OperatorName.SHOW_TEXT_LINE);
            }
        }
        this.document = new COSDocument(scratchFile);
    }

    public PDDocument getPDDocument() throws IOException {
        PDDocument pDDocument = new PDDocument(getDocument(), this.source, getAccessPermission());
        pDDocument.setEncryptionDictionary(getEncryption());
        return pDDocument;
    }

    protected void initialParse() throws IOException {
        COSDictionary retrieveTrailer = retrieveTrailer();
        COSBase parseTrailerValuesDynamically = parseTrailerValuesDynamically(retrieveTrailer);
        if (!(parseTrailerValuesDynamically instanceof COSDictionary)) {
            throw new IOException("Expected root dictionary, but got this: " + parseTrailerValuesDynamically);
        }
        COSDictionary cOSDictionary = (COSDictionary) parseTrailerValuesDynamically;
        if (isLenient() && !cOSDictionary.containsKey(COSName.TYPE)) {
            cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.CATALOG);
        }
        parseDictObjects(cOSDictionary, null);
        COSBase dictionaryObject = retrieveTrailer.getDictionaryObject(COSName.INFO);
        if (dictionaryObject instanceof COSDictionary) {
            parseDictObjects((COSDictionary) dictionaryObject, null);
        }
        checkPages(cOSDictionary);
        if (!(cOSDictionary.getDictionaryObject(COSName.PAGES) instanceof COSDictionary)) {
            throw new IOException("Page tree root must be a dictionary");
        }
        this.document.setDecrypted();
        this.initialParseDone = true;
    }

    public void parse() throws IOException {
        try {
            if (!parsePDFHeader() && !parseFDFHeader()) {
                throw new IOException("Error: Header doesn't contain versioninfo");
            }
            if (this.initialParseDone) {
                return;
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
