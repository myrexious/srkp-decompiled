package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSObjectKey;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.io.RandomAccessRead;
import com.tom_roush.pdfbox.pdfparser.XrefTrailerResolver;
import com.tom_roush.pdfbox.pdmodel.encryption.AccessPermission;
import com.tom_roush.pdfbox.pdmodel.encryption.DecryptionMaterial;
import com.tom_roush.pdfbox.pdmodel.encryption.PDEncryption;
import com.tom_roush.pdfbox.pdmodel.encryption.PublicKeyDecryptionMaterial;
import com.tom_roush.pdfbox.pdmodel.encryption.SecurityHandler;
import com.tom_roush.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
public class COSParser extends BaseParser {
    private static final int DEFAULT_TRAIL_BYTECOUNT = 2048;
    private static final String FDF_DEFAULT_VERSION = "1.0";
    private static final String FDF_HEADER = "%FDF-";
    private static final long MINIMUM_SEARCH_OFFSET = 6;
    private static final String PDF_DEFAULT_VERSION = "1.4";
    private static final String PDF_HEADER = "%PDF-";
    private static final int STREAMCOPYBUFLEN = 8192;
    private static final int STRMBUFLEN = 2048;
    public static final String SYSPROP_EOFLOOKUPRANGE = "com.tom_roush.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange";
    public static final String SYSPROP_PARSEMINIMAL = "com.tom_roush.pdfbox.pdfparser.nonSequentialPDFParser.parseMinimal";
    public static final String TMP_FILE_PREFIX = "tmpPDF";
    private static final int X = 120;
    private AccessPermission accessPermission;
    private Map<COSObjectKey, Long> bfSearchCOSObjectKeyOffsets;
    private List<Long> bfSearchXRefStreamsOffsets;
    private List<Long> bfSearchXRefTablesOffsets;
    private PDEncryption encryption;
    protected long fileLen;
    protected boolean initialParseDone;
    private boolean isLenient;
    private String keyAlias;
    private InputStream keyStoreInputStream;
    private Long lastEOFMarker;
    private String password;
    private int readTrailBytes;
    protected SecurityHandler securityHandler;
    protected final RandomAccessRead source;
    private final byte[] streamCopyBuf;
    private final byte[] strmBuf;
    private long trailerOffset;
    private boolean trailerWasRebuild;
    protected XrefTrailerResolver xrefTrailerResolver;
    private static final char[] XREF_TABLE = {'x', 'r', 'e', 'f'};
    private static final char[] XREF_STREAM = {'/', 'X', 'R', 'e', 'f'};
    private static final char[] STARTXREF = {'s', 't', 'a', 'r', 't', 'x', 'r', 'e', 'f'};
    private static final byte[] ENDSTREAM = {BuiltinOptions.BatchMatMulOptions, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.SegmentSumOptions, BuiltinOptions.BucketizeOptions, BuiltinOptions.GeluOptions, BuiltinOptions.RandomOptions, BuiltinOptions.BatchMatMulOptions, BuiltinOptions.ScatterNdOptions, BuiltinOptions.HashtableImportOptions};
    private static final byte[] ENDOBJ = {BuiltinOptions.BatchMatMulOptions, BuiltinOptions.HashtableSizeOptions, BuiltinOptions.SegmentSumOptions, BuiltinOptions.VarHandleOptions, BuiltinOptions.SelectV2Options, BuiltinOptions.Conv3DOptions};
    protected static final char[] EOF_MARKER = {'%', '%', 'E', 'O', 'F'};
    protected static final char[] OBJ_MARKER = {'o', 'b', 'j'};
    private static final char[] TRAILER_MARKER = {'t', 'r', 'a', 'i', 'l', 'e', 'r'};
    private static final char[] OBJ_STREAM = {'/', 'O', 'b', 'j', 'S', 't', 'm'};

    public COSParser(RandomAccessRead randomAccessRead) {
        super(new RandomAccessSource(randomAccessRead));
        this.strmBuf = new byte[2048];
        this.keyStoreInputStream = null;
        this.password = "";
        this.keyAlias = null;
        this.isLenient = true;
        this.initialParseDone = false;
        this.trailerWasRebuild = false;
        this.bfSearchCOSObjectKeyOffsets = null;
        this.lastEOFMarker = null;
        this.bfSearchXRefTablesOffsets = null;
        this.bfSearchXRefStreamsOffsets = null;
        this.encryption = null;
        this.securityHandler = null;
        this.readTrailBytes = 2048;
        this.xrefTrailerResolver = new XrefTrailerResolver();
        this.streamCopyBuf = new byte[8192];
        this.source = randomAccessRead;
    }

    public COSParser(RandomAccessRead randomAccessRead, String str, InputStream inputStream, String str2) {
        super(new RandomAccessSource(randomAccessRead));
        this.strmBuf = new byte[2048];
        this.keyStoreInputStream = null;
        this.password = "";
        this.keyAlias = null;
        this.isLenient = true;
        this.initialParseDone = false;
        this.trailerWasRebuild = false;
        this.bfSearchCOSObjectKeyOffsets = null;
        this.lastEOFMarker = null;
        this.bfSearchXRefTablesOffsets = null;
        this.bfSearchXRefStreamsOffsets = null;
        this.encryption = null;
        this.securityHandler = null;
        this.readTrailBytes = 2048;
        this.xrefTrailerResolver = new XrefTrailerResolver();
        this.streamCopyBuf = new byte[8192];
        this.source = randomAccessRead;
        this.password = str;
        this.keyAlias = str2;
        this.keyStoreInputStream = inputStream;
    }

    public void setEOFLookupRange(int i) {
        if (i > 15) {
            this.readTrailBytes = i;
        }
    }

    public COSDictionary retrieveTrailer() throws IOException {
        boolean z;
        COSDictionary cOSDictionary = null;
        try {
            long startxrefOffset = getStartxrefOffset();
            if (startxrefOffset > -1) {
                cOSDictionary = parseXref(startxrefOffset);
                z = false;
            } else {
                z = isLenient();
            }
        } catch (IOException e) {
            if (!isLenient()) {
                throw e;
            }
            z = true;
        }
        if (cOSDictionary != null && cOSDictionary.getItem(COSName.ROOT) == null) {
            z = isLenient();
        }
        if (z) {
            return rebuildTrailer();
        }
        prepareDecryption();
        Map<COSObjectKey, Long> map = this.bfSearchCOSObjectKeyOffsets;
        if (map == null || map.isEmpty()) {
            return cOSDictionary;
        }
        bfSearchForObjStreams();
        return cOSDictionary;
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x011b, code lost:
        throw new java.io.IOException("Expected trailer object at offset " + r16.source.getPosition());
     */
    /* JADX WARN: Removed duplicated region for block: B:119:0x014d A[LOOP:0: B:74:0x0029->B:119:0x014d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0151 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.tom_roush.pdfbox.cos.COSDictionary parseXref(long r17) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.COSParser.parseXref(long):com.tom_roush.pdfbox.cos.COSDictionary");
    }

    private long parseXrefObjStream(long j, boolean z) throws IOException {
        long readObjectNumber = readObjectNumber();
        this.document.setHighestXRefObjectNumber(Math.max(this.document.getHighestXRefObjectNumber(), readObjectNumber));
        readGenerationNumber();
        readExpectedString(OBJ_MARKER, true);
        COSDictionary parseCOSDictionary = parseCOSDictionary();
        COSStream parseCOSStream = parseCOSStream(parseCOSDictionary);
        parseXrefStream(parseCOSStream, j, z);
        parseCOSStream.close();
        return parseCOSDictionary.getLong(COSName.PREV);
    }

    public final long getStartxrefOffset() throws IOException {
        try {
            long j = this.fileLen;
            int i = this.readTrailBytes;
            if (j < i) {
                i = (int) j;
            }
            byte[] bArr = new byte[i];
            long j2 = j - i;
            this.source.seek(j2);
            int i2 = 0;
            while (i2 < i) {
                int i3 = i - i2;
                int read = this.source.read(bArr, i2, i3);
                if (read < 1) {
                    throw new IOException("No more bytes to read for trailing buffer, but expected: " + i3);
                }
                i2 += read;
            }
            this.source.seek(0L);
            char[] cArr = EOF_MARKER;
            int lastIndexOf = lastIndexOf(cArr, bArr, i);
            if (lastIndexOf >= 0) {
                i = lastIndexOf;
            } else if (this.isLenient) {
                Log.d("PdfBox-Android", "Missing end of file marker '" + new String(cArr) + OperatorName.SHOW_TEXT_LINE);
            } else {
                throw new IOException("Missing end of file marker '" + new String(cArr) + OperatorName.SHOW_TEXT_LINE);
            }
            int lastIndexOf2 = lastIndexOf(STARTXREF, bArr, i);
            if (lastIndexOf2 >= 0) {
                return j2 + lastIndexOf2;
            }
            throw new IOException("Missing 'startxref' marker.");
        } catch (Throwable th) {
            this.source.seek(0L);
            throw th;
        }
    }

    protected int lastIndexOf(char[] cArr, byte[] bArr, int i) {
        int length = cArr.length - 1;
        char c = cArr[length];
        while (true) {
            int i2 = length;
            while (true) {
                i--;
                if (i < 0) {
                    return -1;
                }
                if (bArr[i] == c) {
                    i2--;
                    if (i2 < 0) {
                        return i;
                    }
                    c = cArr[i2];
                } else if (i2 < length) {
                    break;
                }
            }
            c = cArr[length];
        }
    }

    public boolean isLenient() {
        return this.isLenient;
    }

    public void setLenient(boolean z) {
        if (this.initialParseDone) {
            throw new IllegalArgumentException("Cannot change leniency after parsing");
        }
        this.isLenient = z;
    }

    private long getObjectId(COSObject cOSObject) {
        return (cOSObject.getObjectNumber() << 32) | cOSObject.getGenerationNumber();
    }

    private void addNewToList(Queue<COSBase> queue, Collection<COSBase> collection, Set<Long> set) {
        for (COSBase cOSBase : collection) {
            addNewToList(queue, cOSBase, set);
        }
    }

    private void addNewToList(Queue<COSBase> queue, COSBase cOSBase, Set<Long> set) {
        if (cOSBase instanceof COSObject) {
            if (set.add(Long.valueOf(getObjectId((COSObject) cOSBase)))) {
                queue.add(cOSBase);
            }
        } else if ((cOSBase instanceof COSDictionary) || (cOSBase instanceof COSArray)) {
            queue.add(cOSBase);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:166:0x01c3, code lost:
        throw new java.io.IOException(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x01d5, code lost:
        if (r2.isEmpty() == false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x01d8, code lost:
        r5 = ((java.util.List) r2.remove(r2.firstKey())).iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x01ea, code lost:
        if (r5.hasNext() == false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x01ec, code lost:
        r7 = (com.tom_roush.pdfbox.cos.COSObject) r5.next();
        r8 = parseObjectDynamically(r7, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x01f6, code lost:
        if (r8 == null) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x01f8, code lost:
        r7.setObject(r8);
        addNewToList(r1, r8, r4);
        r3.add(java.lang.Long.valueOf(getObjectId(r7)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void parseDictObjects(com.tom_roush.pdfbox.cos.COSDictionary r18, com.tom_roush.pdfbox.cos.COSName... r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 522
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.COSParser.parseDictObjects(com.tom_roush.pdfbox.cos.COSDictionary, com.tom_roush.pdfbox.cos.COSName[]):void");
    }

    private void addExcludedToList(COSName[] cOSNameArr, COSDictionary cOSDictionary, Set<Long> set) {
        if (cOSNameArr != null) {
            for (COSName cOSName : cOSNameArr) {
                COSBase item = cOSDictionary.getItem(cOSName);
                if (item instanceof COSObject) {
                    set.add(Long.valueOf(getObjectId((COSObject) item)));
                }
            }
        }
    }

    protected final COSBase parseObjectDynamically(COSObject cOSObject, boolean z) throws IOException {
        return parseObjectDynamically(cOSObject.getObjectNumber(), cOSObject.getGenerationNumber(), z);
    }

    protected COSBase parseObjectDynamically(long j, int i, boolean z) throws IOException {
        COSObjectKey cOSObjectKey = new COSObjectKey(j, i);
        COSObject objectFromPool = this.document.getObjectFromPool(cOSObjectKey);
        if (objectFromPool.getObject() == null) {
            Long l = this.document.getXrefTable().get(cOSObjectKey);
            if (l == null && this.isLenient) {
                bfSearchForObjects();
                l = this.bfSearchCOSObjectKeyOffsets.get(cOSObjectKey);
                if (l != null) {
                    Log.d("PdfBox-Android", "Set missing offset " + l + " for object " + cOSObjectKey);
                    this.document.getXrefTable().put(cOSObjectKey, l);
                }
            }
            if (z && (l == null || l.longValue() <= 0)) {
                throw new IOException("Object must be defined and must not be compressed object: " + cOSObjectKey.getNumber() + ":" + cOSObjectKey.getGeneration());
            }
            if (objectFromPool.derefencingInProgress()) {
                throw new IOException("Possible recursion detected when dereferencing object " + j + StringUtils.SPACE + i);
            }
            objectFromPool.dereferencingStarted();
            if (l == null && this.isLenient && this.bfSearchCOSObjectKeyOffsets == null) {
                bfSearchForObjects();
                if (!this.bfSearchCOSObjectKeyOffsets.isEmpty()) {
                    Log.d("PdfBox-Android", "Add all new read objects from brute force search to the xref table");
                    Map<COSObjectKey, Long> xrefTable = this.document.getXrefTable();
                    for (Map.Entry<COSObjectKey, Long> entry : this.bfSearchCOSObjectKeyOffsets.entrySet()) {
                        COSObjectKey key = entry.getKey();
                        if (!xrefTable.containsKey(key)) {
                            xrefTable.put(key, entry.getValue());
                        }
                    }
                    l = xrefTable.get(cOSObjectKey);
                }
            }
            if (l == null) {
                objectFromPool.setObject(COSNull.NULL);
            } else if (l.longValue() > 0) {
                parseFileObject(l, cOSObjectKey, objectFromPool);
            } else {
                parseObjectStream((int) (-l.longValue()));
            }
            objectFromPool.dereferencingFinished();
        }
        return objectFromPool.getObject();
    }

    private void parseFileObject(Long l, COSObjectKey cOSObjectKey, COSObject cOSObject) throws IOException {
        COSBase cOSBase;
        this.source.seek(l.longValue());
        long readObjectNumber = readObjectNumber();
        int readGenerationNumber = readGenerationNumber();
        readExpectedString(OBJ_MARKER, true);
        if (readObjectNumber != cOSObjectKey.getNumber() || readGenerationNumber != cOSObjectKey.getGeneration()) {
            throw new IOException("XREF for " + cOSObjectKey.getNumber() + ":" + cOSObjectKey.getGeneration() + " points to wrong object: " + readObjectNumber + ":" + readGenerationNumber + " at offset " + l);
        }
        skipSpaces();
        COSBase parseDirObject = parseDirObject();
        String readString = readString();
        if (readString.equals("stream")) {
            this.source.rewind(readString.getBytes(Charsets.ISO_8859_1).length);
            if (parseDirObject instanceof COSDictionary) {
                COSStream parseCOSStream = parseCOSStream((COSDictionary) parseDirObject);
                SecurityHandler securityHandler = this.securityHandler;
                if (securityHandler != null) {
                    securityHandler.decryptStream(parseCOSStream, cOSObjectKey.getNumber(), cOSObjectKey.getGeneration());
                }
                skipSpaces();
                readString = readLine();
                cOSBase = parseCOSStream;
                if (!readString.startsWith("endobj")) {
                    cOSBase = parseCOSStream;
                    if (readString.startsWith("endstream")) {
                        readString = readString.substring(9).trim();
                        cOSBase = parseCOSStream;
                        if (readString.length() == 0) {
                            readString = readLine();
                            cOSBase = parseCOSStream;
                        }
                    }
                }
            } else {
                throw new IOException("Stream not preceded by dictionary (offset: " + l + ").");
            }
        } else {
            SecurityHandler securityHandler2 = this.securityHandler;
            cOSBase = parseDirObject;
            if (securityHandler2 != null) {
                securityHandler2.decrypt(parseDirObject, cOSObjectKey.getNumber(), cOSObjectKey.getGeneration());
                cOSBase = parseDirObject;
            }
        }
        cOSObject.setObject(cOSBase);
        if (readString.startsWith("endobj")) {
            return;
        }
        if (this.isLenient) {
            Log.w("PdfBox-Android", "Object (" + readObjectNumber + ":" + readGenerationNumber + ") at offset " + l + " does not end with 'endobj' but with '" + readString + OperatorName.SHOW_TEXT_LINE);
            return;
        }
        throw new IOException("Object (" + readObjectNumber + ":" + readGenerationNumber + ") at offset " + l + " does not end with 'endobj' but with '" + readString + OperatorName.SHOW_TEXT_LINE);
    }

    private void parseObjectStream(int i) throws IOException {
        COSBase parseObjectDynamically = parseObjectDynamically(i, 0, true);
        if (parseObjectDynamically instanceof COSStream) {
            try {
                PDFObjectStreamParser pDFObjectStreamParser = new PDFObjectStreamParser((COSStream) parseObjectDynamically, this.document);
                try {
                    pDFObjectStreamParser.parse();
                    for (COSObject cOSObject : pDFObjectStreamParser.getObjects()) {
                        COSObjectKey cOSObjectKey = new COSObjectKey(cOSObject);
                        Long l = this.xrefTrailerResolver.getXrefTable().get(cOSObjectKey);
                        if (l != null && l.longValue() == (-i)) {
                            this.document.getObjectFromPool(cOSObjectKey).setObject(cOSObject.getObject());
                        }
                    }
                } catch (IOException e) {
                    if (this.isLenient) {
                        Log.d("PdfBox-Android", "Stop reading object stream " + i + " due to an exception", e);
                        return;
                    }
                    throw e;
                }
            } catch (IOException e2) {
                if (this.isLenient) {
                    Log.e("PdfBox-Android", "object stream " + i + " could not be parsed due to an exception", e2);
                    return;
                }
                throw e2;
            }
        }
    }

    private COSNumber getLength(COSBase cOSBase, COSName cOSName) throws IOException {
        if (cOSBase == null) {
            return null;
        }
        if (cOSBase instanceof COSNumber) {
            return (COSNumber) cOSBase;
        }
        if (cOSBase instanceof COSObject) {
            COSObject cOSObject = (COSObject) cOSBase;
            COSBase object = cOSObject.getObject();
            if (object == null) {
                long position = this.source.getPosition();
                parseObjectDynamically(cOSObject, COSName.OBJ_STM.equals(cOSName));
                this.source.seek(position);
                object = cOSObject.getObject();
            }
            if (object == null) {
                throw new IOException("Length object content was not read.");
            }
            if (COSNull.NULL == object) {
                Log.w("PdfBox-Android", "Length object (" + cOSObject.getObjectNumber() + StringUtils.SPACE + cOSObject.getGenerationNumber() + ") not found");
                return null;
            } else if (!(object instanceof COSNumber)) {
                throw new IOException("Wrong type of referenced length object " + cOSObject + ": " + object.getClass().getSimpleName());
            } else {
                return (COSNumber) object;
            }
        }
        throw new IOException("Wrong type of length object: " + cOSBase.getClass().getSimpleName());
    }

    protected COSStream parseCOSStream(COSDictionary cOSDictionary) throws IOException {
        COSStream createCOSStream = this.document.createCOSStream(cOSDictionary);
        readString();
        skipWhiteSpaces();
        COSNumber length = getLength(cOSDictionary.getItem(COSName.LENGTH), cOSDictionary.getCOSName(COSName.TYPE));
        if (length == null) {
            if (this.isLenient) {
                Log.w("PdfBox-Android", "The stream doesn't provide any stream length, using fallback readUntilEnd, at offset " + this.source.getPosition());
            } else {
                throw new IOException("Missing length for stream.");
            }
        }
        if (length != null && validateStreamLength(length.longValue())) {
            OutputStream createRawOutputStream = createCOSStream.createRawOutputStream();
            try {
                readValidStream(createRawOutputStream, length);
            } finally {
                createRawOutputStream.close();
                createCOSStream.setItem(COSName.LENGTH, (COSBase) length);
            }
        } else {
            OutputStream createRawOutputStream2 = createCOSStream.createRawOutputStream();
            try {
                readUntilEndStream(new EndstreamOutputStream(createRawOutputStream2));
            } finally {
                createRawOutputStream2.close();
                if (length != null) {
                    createCOSStream.setItem(COSName.LENGTH, (COSBase) length);
                }
            }
        }
        String readString = readString();
        if (readString.equals("endobj") && this.isLenient) {
            Log.w("PdfBox-Android", "stream ends with 'endobj' instead of 'endstream' at offset " + this.source.getPosition());
            this.source.rewind(ENDOBJ.length);
        } else if (readString.length() > 9 && this.isLenient && readString.startsWith("endstream")) {
            Log.w("PdfBox-Android", "stream ends with '" + readString + "' instead of 'endstream' at offset " + this.source.getPosition());
            this.source.rewind(readString.substring(9).getBytes(Charsets.ISO_8859_1).length);
        } else if (!readString.equals("endstream")) {
            throw new IOException("Error reading stream, expected='endstream' actual='" + readString + "' at offset " + this.source.getPosition());
        }
        return createCOSStream;
    }

    private void readUntilEndStream(OutputStream outputStream) throws IOException {
        byte b;
        byte[] bArr = ENDSTREAM;
        int i = 0;
        while (true) {
            int read = this.source.read(this.strmBuf, i, 2048 - i);
            if (read <= 0) {
                break;
            }
            int i2 = read + i;
            int i3 = i2 - 5;
            int i4 = i;
            while (true) {
                if (i >= i2) {
                    break;
                }
                int i5 = i + 5;
                if (i4 != 0 || i5 >= i3 || ((b = this.strmBuf[i5]) <= 116 && b >= 97)) {
                    byte b2 = this.strmBuf[i];
                    if (b2 == bArr[i4]) {
                        i4++;
                        if (i4 == bArr.length) {
                            i++;
                            break;
                        }
                    } else {
                        if (i4 == 3) {
                            bArr = ENDOBJ;
                            if (b2 == bArr[i4]) {
                                i4++;
                            }
                        }
                        i4 = b2 == 101 ? 1 : (b2 == 110 && i4 == 7) ? 2 : 0;
                        bArr = ENDSTREAM;
                    }
                } else {
                    i = i5;
                }
                i++;
            }
            int max = Math.max(0, i - i4);
            if (max > 0) {
                outputStream.write(this.strmBuf, 0, max);
            }
            if (i4 == bArr.length) {
                this.source.rewind(i2 - max);
                break;
            } else {
                System.arraycopy(bArr, 0, this.strmBuf, 0, i4);
                i = i4;
            }
        }
        outputStream.flush();
    }

    private void readValidStream(OutputStream outputStream, COSNumber cOSNumber) throws IOException {
        long longValue = cOSNumber.longValue();
        while (longValue > 0) {
            int i = longValue > 8192 ? 8192 : (int) longValue;
            int read = this.source.read(this.streamCopyBuf, 0, i);
            if (read <= 0) {
                throw new IOException("read error at offset " + this.source.getPosition() + ": expected " + i + " bytes, but read() returns " + read);
            }
            outputStream.write(this.streamCopyBuf, 0, read);
            longValue -= read;
        }
    }

    private boolean validateStreamLength(long j) throws IOException {
        long position = this.source.getPosition();
        long j2 = position + j;
        boolean z = false;
        if (j2 > this.fileLen) {
            Log.w("PdfBox-Android", "The end of the stream is out of range, using workaround to read the stream, stream start position: " + position + ", length: " + j + ", expected end position: " + j2);
        } else {
            this.source.seek(j2);
            skipSpaces();
            if (isString(ENDSTREAM)) {
                z = true;
            } else {
                Log.w("PdfBox-Android", "The end of the stream doesn't point to the correct offset, using workaround to read the stream, stream start position: " + position + ", length: " + j + ", expected end position: " + j2);
            }
            this.source.seek(position);
        }
        return z;
    }

    private long checkXRefOffset(long j) throws IOException {
        if (this.isLenient) {
            this.source.seek(j);
            skipSpaces();
            if (this.source.peek() == 120 && isString(XREF_TABLE)) {
                return j;
            }
            if (j > 0) {
                return checkXRefStreamOffset(j) ? j : calculateXRefFixedOffset(j, false);
            }
            return -1L;
        }
        return j;
    }

    private boolean checkXRefStreamOffset(long j) throws IOException {
        if (!this.isLenient || j == 0) {
            return true;
        }
        this.source.seek(j - 1);
        if (isWhitespace(this.source.read())) {
            skipSpaces();
            if (isDigit()) {
                try {
                    readObjectNumber();
                    readGenerationNumber();
                    readExpectedString(OBJ_MARKER, true);
                    COSDictionary parseCOSDictionary = parseCOSDictionary();
                    this.source.seek(j);
                    return "XRef".equals(parseCOSDictionary.getNameAsString(COSName.TYPE));
                } catch (IOException unused) {
                    this.source.seek(j);
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    private long calculateXRefFixedOffset(long j, boolean z) throws IOException {
        if (j < 0) {
            Log.e("PdfBox-Android", "Invalid object offset " + j + " when searching for a xref table/stream");
            return 0L;
        }
        long bfSearchForXRef = bfSearchForXRef(j, z);
        if (bfSearchForXRef > -1) {
            Log.d("PdfBox-Android", "Fixed reference for xref table/stream " + j + " -> " + bfSearchForXRef);
            return bfSearchForXRef;
        }
        Log.e("PdfBox-Android", "Can't find the object xref table/stream at offset " + j);
        return 0L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean validateXrefOffsets(Map<COSObjectKey, Long> map) throws IOException {
        if (map == 0) {
            return true;
        }
        HashMap hashMap = new HashMap();
        HashSet hashSet = new HashSet();
        for (Map.Entry entry : map.entrySet()) {
            COSObjectKey cOSObjectKey = (COSObjectKey) entry.getKey();
            Long l = (Long) entry.getValue();
            if (l != null && l.longValue() >= 0) {
                COSObjectKey findObjectKey = findObjectKey(cOSObjectKey, l.longValue(), map);
                if (findObjectKey == null) {
                    Log.d("PdfBox-Android", "Stop checking xref offsets as at least one (" + cOSObjectKey + ") couldn't be dereferenced");
                    return false;
                } else if (findObjectKey != cOSObjectKey) {
                    hashMap.put(cOSObjectKey, findObjectKey);
                } else {
                    hashSet.add(cOSObjectKey);
                }
            }
        }
        HashMap hashMap2 = new HashMap();
        for (Map.Entry entry2 : hashMap.entrySet()) {
            if (!hashSet.contains(entry2.getValue())) {
                hashMap2.put(entry2.getValue(), map.get(entry2.getKey()));
            }
        }
        for (Map.Entry entry3 : hashMap.entrySet()) {
            map.remove(entry3.getKey());
        }
        for (Map.Entry entry4 : hashMap2.entrySet()) {
            map.put(entry4.getKey(), entry4.getValue());
        }
        return true;
    }

    private void checkXrefOffsets() throws IOException {
        if (this.isLenient) {
            Map<COSObjectKey, Long> xrefTable = this.xrefTrailerResolver.getXrefTable();
            if (validateXrefOffsets(xrefTable)) {
                return;
            }
            bfSearchForObjects();
            if (this.bfSearchCOSObjectKeyOffsets.isEmpty()) {
                return;
            }
            Log.d("PdfBox-Android", "Replaced read xref table with the results of a brute force search");
            xrefTable.clear();
            xrefTable.putAll(this.bfSearchCOSObjectKeyOffsets);
        }
    }

    private COSObjectKey findObjectKey(COSObjectKey cOSObjectKey, long j, Map<COSObjectKey, Long> map) throws IOException {
        int readGenerationNumber;
        if (j < MINIMUM_SEARCH_OFFSET) {
            return null;
        }
        try {
            this.source.seek(j);
            skipWhiteSpaces();
            if (this.source.getPosition() == j) {
                this.source.seek(j - 1);
                if (this.source.getPosition() < j) {
                    if (!isDigit()) {
                        this.source.read();
                    } else {
                        long position = this.source.getPosition() - 1;
                        this.source.seek(position);
                        while (isDigit()) {
                            position--;
                            this.source.seek(position);
                        }
                        COSObjectKey cOSObjectKey2 = new COSObjectKey(readObjectNumber(), readGenerationNumber());
                        Long l = map.get(cOSObjectKey2);
                        if (l != null && l.longValue() > 0 && Math.abs(j - l.longValue()) < 10) {
                            Log.d("PdfBox-Android", "Found the object " + cOSObjectKey2 + " instead of " + cOSObjectKey + " at offset " + j + " - ignoring");
                            return null;
                        }
                        this.source.seek(j);
                    }
                }
            }
            long readObjectNumber = readObjectNumber();
            if (cOSObjectKey.getNumber() != readObjectNumber) {
                Log.w("PdfBox-Android", "found wrong object number. expected [" + cOSObjectKey.getNumber() + "] found [" + readObjectNumber + "]");
                if (!this.isLenient) {
                    return null;
                }
                cOSObjectKey = new COSObjectKey(readObjectNumber, cOSObjectKey.getGeneration());
            }
            readGenerationNumber = readGenerationNumber();
            readExpectedString(OBJ_MARKER, true);
        } catch (IOException e) {
            Log.d("PdfBox-Android", "No valid object at given location " + j + " - ignoring", e);
        }
        if (readGenerationNumber == cOSObjectKey.getGeneration()) {
            return cOSObjectKey;
        }
        if (this.isLenient && readGenerationNumber > cOSObjectKey.getGeneration()) {
            return new COSObjectKey(cOSObjectKey.getNumber(), readGenerationNumber);
        }
        return null;
    }

    private void bfSearchForObjects() throws IOException {
        if (this.bfSearchCOSObjectKeyOffsets == null) {
            bfSearchForLastEOFMarker();
            this.bfSearchCOSObjectKeyOffsets = new HashMap();
            long position = this.source.getPosition();
            char[] charArray = "ndo".toCharArray();
            char[] charArray2 = "bj".toCharArray();
            long j = Long.MIN_VALUE;
            int i = Integer.MIN_VALUE;
            long j2 = MINIMUM_SEARCH_OFFSET;
            boolean z = false;
            long j3 = Long.MIN_VALUE;
            do {
                this.source.seek(j2);
                int read = this.source.read();
                j2++;
                if (isWhitespace(read) && isString(OBJ_MARKER)) {
                    long j4 = j2 - 2;
                    this.source.seek(j4);
                    int peek = this.source.peek();
                    if (isDigit(peek)) {
                        int i2 = peek - 48;
                        long j5 = j4 - 1;
                        this.source.seek(j5);
                        if (isWhitespace()) {
                            while (j5 > MINIMUM_SEARCH_OFFSET && isWhitespace()) {
                                j5--;
                                this.source.seek(j5);
                            }
                            boolean z2 = false;
                            while (j5 > MINIMUM_SEARCH_OFFSET && isDigit()) {
                                j5--;
                                this.source.seek(j5);
                                z2 = true;
                            }
                            if (z2) {
                                this.source.read();
                                long readObjectNumber = readObjectNumber();
                                if (j3 > 0) {
                                    this.bfSearchCOSObjectKeyOffsets.put(new COSObjectKey(j, i), Long.valueOf(j3));
                                }
                                j3 = j5 + 1;
                                j2 += OBJ_MARKER.length - 1;
                                i = i2;
                                j = readObjectNumber;
                                z = false;
                            }
                        }
                    }
                } else if (read == 101 && isString(charArray)) {
                    j2 += charArray.length;
                    this.source.seek(j2);
                    if (!this.source.isEOF()) {
                        if (isString(charArray2)) {
                            j2 += charArray2.length;
                        }
                    }
                    z = true;
                }
                if (j2 >= this.lastEOFMarker.longValue()) {
                    break;
                }
            } while (!this.source.isEOF());
            if ((this.lastEOFMarker.longValue() < Long.MAX_VALUE || z) && j3 > 0) {
                this.bfSearchCOSObjectKeyOffsets.put(new COSObjectKey(j, i), Long.valueOf(j3));
            }
            this.source.seek(position);
        }
    }

    private long bfSearchForXRef(long j, boolean z) throws IOException {
        List<Long> list;
        if (!z) {
            bfSearchForXRefTables();
        }
        bfSearchForXRefStreams();
        long searchNearestValue = (z || (list = this.bfSearchXRefTablesOffsets) == null) ? -1L : searchNearestValue(list, j);
        List<Long> list2 = this.bfSearchXRefStreamsOffsets;
        long searchNearestValue2 = list2 != null ? searchNearestValue(list2, j) : -1L;
        int i = (searchNearestValue > (-1L) ? 1 : (searchNearestValue == (-1L) ? 0 : -1));
        if (i > 0 && searchNearestValue2 > -1) {
            if (Math.abs(j - searchNearestValue) > Math.abs(j - searchNearestValue2)) {
                this.bfSearchXRefStreamsOffsets.remove(Long.valueOf(searchNearestValue2));
                return searchNearestValue2;
            }
            this.bfSearchXRefTablesOffsets.remove(Long.valueOf(searchNearestValue));
            return searchNearestValue;
        } else if (i > 0) {
            this.bfSearchXRefTablesOffsets.remove(Long.valueOf(searchNearestValue));
            return searchNearestValue;
        } else if (searchNearestValue2 > -1) {
            this.bfSearchXRefStreamsOffsets.remove(Long.valueOf(searchNearestValue2));
            return searchNearestValue2;
        } else {
            return -1L;
        }
    }

    private long searchNearestValue(List<Long> list, long j) {
        int size = list.size();
        Long l = null;
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            long longValue = j - list.get(i2).longValue();
            if (l == null || Math.abs(l.longValue()) > Math.abs(longValue)) {
                l = Long.valueOf(longValue);
                i = i2;
            }
        }
        if (i > -1) {
            return list.get(i).longValue();
        }
        return -1L;
    }

    private boolean bfSearchForTrailer(COSDictionary cOSDictionary) throws IOException {
        char[] cArr;
        COSObject cOSObject;
        COSDictionary retrieveCOSDictionary;
        COSDictionary retrieveCOSDictionary2;
        long position = this.source.getPosition();
        this.source.seek(MINIMUM_SEARCH_OFFSET);
        while (true) {
            boolean z = false;
            if (!this.source.isEOF()) {
                if (isString(TRAILER_MARKER)) {
                    RandomAccessRead randomAccessRead = this.source;
                    randomAccessRead.seek(randomAccessRead.getPosition() + cArr.length);
                    try {
                        skipSpaces();
                        COSDictionary parseCOSDictionary = parseCOSDictionary();
                        COSObject cOSObject2 = parseCOSDictionary.getCOSObject(COSName.ROOT);
                        boolean z2 = (cOSObject2 == null || (retrieveCOSDictionary2 = retrieveCOSDictionary(cOSObject2)) == null || !isCatalog(retrieveCOSDictionary2)) ? false : true;
                        COSObject cOSObject3 = parseCOSDictionary.getCOSObject(COSName.INFO);
                        if (cOSObject3 != null && (retrieveCOSDictionary = retrieveCOSDictionary(cOSObject3)) != null && isInfo(retrieveCOSDictionary)) {
                            z = true;
                        }
                        if (z2 && z) {
                            cOSDictionary.setItem(COSName.ROOT, (COSBase) cOSObject2);
                            cOSDictionary.setItem(COSName.INFO, (COSBase) cOSObject3);
                            if (parseCOSDictionary.containsKey(COSName.ENCRYPT) && (cOSObject = parseCOSDictionary.getCOSObject(COSName.ENCRYPT)) != null && retrieveCOSDictionary(cOSObject) != null) {
                                cOSDictionary.setItem(COSName.ENCRYPT, (COSBase) cOSObject);
                            }
                            if (!parseCOSDictionary.containsKey(COSName.ID)) {
                                break;
                            }
                            COSBase item = parseCOSDictionary.getItem(COSName.ID);
                            if (!(item instanceof COSArray)) {
                                break;
                            }
                            cOSDictionary.setItem(COSName.ID, item);
                            break;
                        }
                    } catch (IOException unused) {
                        continue;
                    }
                }
                this.source.read();
            } else {
                this.source.seek(position);
                return false;
            }
        }
        return true;
    }

    private void bfSearchForLastEOFMarker() throws IOException {
        if (this.lastEOFMarker == null) {
            long position = this.source.getPosition();
            this.source.seek(MINIMUM_SEARCH_OFFSET);
            while (!this.source.isEOF()) {
                if (isString(EOF_MARKER)) {
                    long position2 = this.source.getPosition();
                    this.source.seek(5 + position2);
                    try {
                        skipSpaces();
                        if (!isString(XREF_TABLE)) {
                            readObjectNumber();
                            readGenerationNumber();
                        }
                    } catch (IOException unused) {
                        this.lastEOFMarker = Long.valueOf(position2);
                    }
                }
                this.source.read();
            }
            this.source.seek(position);
            if (this.lastEOFMarker == null) {
                this.lastEOFMarker = Long.MAX_VALUE;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:235:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void bfSearchForObjStreams() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 704
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.COSParser.bfSearchForObjStreams():void");
    }

    private void bfSearchForXRefTables() throws IOException {
        if (this.bfSearchXRefTablesOffsets == null) {
            this.bfSearchXRefTablesOffsets = new ArrayList();
            long position = this.source.getPosition();
            this.source.seek(MINIMUM_SEARCH_OFFSET);
            while (!this.source.isEOF()) {
                if (isString(XREF_TABLE)) {
                    long position2 = this.source.getPosition();
                    this.source.seek(position2 - 1);
                    if (isWhitespace()) {
                        this.bfSearchXRefTablesOffsets.add(Long.valueOf(position2));
                    }
                    this.source.seek(position2 + 4);
                }
                this.source.read();
            }
            this.source.seek(position);
        }
    }

    private void bfSearchForXRefStreams() throws IOException {
        if (this.bfSearchXRefStreamsOffsets == null) {
            this.bfSearchXRefStreamsOffsets = new ArrayList();
            long position = this.source.getPosition();
            this.source.seek(MINIMUM_SEARCH_OFFSET);
            char[] charArray = " obj".toCharArray();
            while (!this.source.isEOF()) {
                if (isString(XREF_STREAM)) {
                    long position2 = this.source.getPosition();
                    boolean z = false;
                    long j = -1;
                    for (int i = 1; i < 40 && !z; i++) {
                        long j2 = position2 - (i * 10);
                        if (j2 > 0) {
                            this.source.seek(j2);
                            int i2 = 0;
                            while (true) {
                                if (i2 >= 10) {
                                    break;
                                } else if (isString(charArray)) {
                                    long j3 = j2 - 1;
                                    this.source.seek(j3);
                                    if (isDigit(this.source.peek())) {
                                        long j4 = j3 - 1;
                                        this.source.seek(j4);
                                        if (isSpace()) {
                                            long j5 = j4 - 1;
                                            this.source.seek(j5);
                                            int i3 = 0;
                                            while (j5 > MINIMUM_SEARCH_OFFSET && isDigit()) {
                                                j5--;
                                                this.source.seek(j5);
                                                i3++;
                                            }
                                            if (i3 > 0) {
                                                this.source.read();
                                                j = this.source.getPosition();
                                            }
                                        }
                                    }
                                    Log.d("PdfBox-Android", "Fixed reference for xref stream " + position2 + " -> " + j);
                                    z = true;
                                } else {
                                    j2++;
                                    this.source.read();
                                    i2++;
                                }
                            }
                        }
                    }
                    if (j > -1) {
                        this.bfSearchXRefStreamsOffsets.add(Long.valueOf(j));
                    }
                    this.source.seek(position2 + 5);
                }
                this.source.read();
            }
            this.source.seek(position);
        }
    }

    public final COSDictionary rebuildTrailer() throws IOException {
        COSDictionary cOSDictionary;
        boolean z;
        bfSearchForObjects();
        if (this.bfSearchCOSObjectKeyOffsets != null) {
            this.xrefTrailerResolver.reset();
            this.xrefTrailerResolver.nextXrefObj(0L, XrefTrailerResolver.XRefType.TABLE);
            for (Map.Entry<COSObjectKey, Long> entry : this.bfSearchCOSObjectKeyOffsets.entrySet()) {
                this.xrefTrailerResolver.setXRef(entry.getKey(), entry.getValue().longValue());
            }
            this.xrefTrailerResolver.setStartxref(0L);
            cOSDictionary = this.xrefTrailerResolver.getTrailer();
            getDocument().setTrailer(cOSDictionary);
            if (bfSearchForTrailer(cOSDictionary) || searchForTrailerItems(cOSDictionary)) {
                z = false;
            } else {
                bfSearchForObjStreams();
                searchForTrailerItems(cOSDictionary);
                z = true;
            }
            prepareDecryption();
            if (!z) {
                bfSearchForObjStreams();
            }
        } else {
            cOSDictionary = null;
        }
        this.trailerWasRebuild = true;
        return cOSDictionary;
    }

    private boolean searchForTrailerItems(COSDictionary cOSDictionary) throws IOException {
        COSObject objectFromPool;
        COSObject cOSObject = null;
        COSObject cOSObject2 = null;
        Long l = null;
        Long l2 = null;
        for (Map.Entry<COSObjectKey, Long> entry : this.bfSearchCOSObjectKeyOffsets.entrySet()) {
            COSDictionary retrieveCOSDictionary = retrieveCOSDictionary(entry.getKey(), entry.getValue().longValue());
            if (retrieveCOSDictionary != null) {
                if (isCatalog(retrieveCOSDictionary)) {
                    COSObject objectFromPool2 = this.document.getObjectFromPool(entry.getKey());
                    cOSObject = compareCOSObjects(objectFromPool2, entry.getValue(), cOSObject, l);
                    if (cOSObject == objectFromPool2) {
                        l = entry.getValue();
                    }
                } else if (isInfo(retrieveCOSDictionary) && (cOSObject2 = compareCOSObjects((objectFromPool = this.document.getObjectFromPool(entry.getKey())), entry.getValue(), cOSObject2, l2)) == objectFromPool) {
                    l2 = entry.getValue();
                }
            }
        }
        if (cOSObject != null) {
            cOSDictionary.setItem(COSName.ROOT, (COSBase) cOSObject);
        }
        if (cOSObject2 != null) {
            cOSDictionary.setItem(COSName.INFO, (COSBase) cOSObject2);
        }
        return cOSObject != null;
    }

    private COSObject compareCOSObjects(COSObject cOSObject, Long l, COSObject cOSObject2, Long l2) {
        return cOSObject2 != null ? cOSObject2.getObjectNumber() == cOSObject.getObjectNumber() ? cOSObject2.getGenerationNumber() < cOSObject.getGenerationNumber() ? cOSObject : cOSObject2 : (l2 == null || l.longValue() <= l2.longValue()) ? cOSObject2 : cOSObject : cOSObject;
    }

    private COSDictionary retrieveCOSDictionary(COSObject cOSObject) throws IOException {
        COSObjectKey cOSObjectKey = new COSObjectKey(cOSObject);
        Long l = this.bfSearchCOSObjectKeyOffsets.get(cOSObjectKey);
        if (l != null) {
            long position = this.source.getPosition();
            COSDictionary retrieveCOSDictionary = retrieveCOSDictionary(cOSObjectKey, l.longValue());
            this.source.seek(position);
            return retrieveCOSDictionary;
        }
        return null;
    }

    private COSDictionary retrieveCOSDictionary(COSObjectKey cOSObjectKey, long j) throws IOException {
        if (j < 0) {
            COSObject objectFromPool = this.document.getObjectFromPool(cOSObjectKey);
            if (objectFromPool.getObject() == null) {
                parseObjectStream((int) (-j));
            }
            COSBase object = objectFromPool.getObject();
            if (object instanceof COSDictionary) {
                return (COSDictionary) object;
            }
            return null;
        }
        this.source.seek(j);
        readObjectNumber();
        readGenerationNumber();
        readExpectedString(OBJ_MARKER, true);
        if (this.source.peek() != 60) {
            return null;
        }
        try {
            return parseCOSDictionary();
        } catch (IOException unused) {
            Log.d("PdfBox-Android", "Skipped object " + cOSObjectKey + ", either it's corrupt or not a dictionary");
            return null;
        }
    }

    public void checkPages(COSDictionary cOSDictionary) {
        if (!this.trailerWasRebuild || cOSDictionary == null) {
            return;
        }
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.PAGES);
        if (dictionaryObject instanceof COSDictionary) {
            checkPagesDictionary((COSDictionary) dictionaryObject, new HashSet());
        }
    }

    private int checkPagesDictionary(COSDictionary cOSDictionary, Set<COSObject> set) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.KIDS);
        int i = 0;
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            for (COSBase cOSBase : cOSArray.toList()) {
                if (cOSBase instanceof COSObject) {
                    COSObject cOSObject = (COSObject) cOSBase;
                    if (!set.contains(cOSObject)) {
                        COSBase object = cOSObject.getObject();
                        if (object == null || object.equals(COSNull.NULL)) {
                            Log.w("PdfBox-Android", "Removed null object " + cOSBase + " from pages dictionary");
                            cOSArray.remove(cOSBase);
                        } else if (object instanceof COSDictionary) {
                            COSDictionary cOSDictionary2 = (COSDictionary) object;
                            COSName cOSName = cOSDictionary2.getCOSName(COSName.TYPE);
                            if (COSName.PAGES.equals(cOSName)) {
                                set.add(cOSObject);
                                i += checkPagesDictionary(cOSDictionary2, set);
                            } else if (COSName.PAGE.equals(cOSName)) {
                                i++;
                            }
                        }
                    }
                }
                cOSArray.remove(cOSBase);
            }
        }
        cOSDictionary.setInt(COSName.COUNT, i);
        return i;
    }

    protected boolean isCatalog(COSDictionary cOSDictionary) {
        return COSName.CATALOG.equals(cOSDictionary.getCOSName(COSName.TYPE));
    }

    private boolean isInfo(COSDictionary cOSDictionary) {
        if (cOSDictionary.containsKey(COSName.PARENT) || cOSDictionary.containsKey(COSName.A) || cOSDictionary.containsKey(COSName.DEST)) {
            return false;
        }
        return cOSDictionary.containsKey(COSName.MOD_DATE) || cOSDictionary.containsKey(COSName.TITLE) || cOSDictionary.containsKey(COSName.AUTHOR) || cOSDictionary.containsKey(COSName.SUBJECT) || cOSDictionary.containsKey(COSName.KEYWORDS) || cOSDictionary.containsKey(COSName.CREATOR) || cOSDictionary.containsKey(COSName.PRODUCER) || cOSDictionary.containsKey(COSName.CREATION_DATE);
    }

    private long parseStartXref() throws IOException {
        if (isString(STARTXREF)) {
            readString();
            skipSpaces();
            return readLong();
        }
        return -1L;
    }

    private boolean isString(byte[] bArr) throws IOException {
        if (this.source.peek() == bArr[0]) {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            int read = this.source.read(bArr2, 0, length);
            while (read < length) {
                int read2 = this.source.read(bArr2, read, length - read);
                if (read2 < 0) {
                    break;
                }
                read += read2;
            }
            boolean equals = Arrays.equals(bArr, bArr2);
            this.source.rewind(read);
            return equals;
        }
        return false;
    }

    private boolean isString(char[] cArr) throws IOException {
        long position = this.source.getPosition();
        int length = cArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            }
            if (this.source.read() != cArr[i]) {
                break;
            }
            i++;
        }
        this.source.seek(position);
        return z;
    }

    private boolean parseTrailer() throws IOException {
        this.trailerOffset = this.source.getPosition();
        if (this.isLenient) {
            int peek = this.source.peek();
            while (peek != 116 && isDigit(peek)) {
                if (this.source.getPosition() == this.trailerOffset) {
                    Log.w("PdfBox-Android", "Expected trailer object at offset " + this.trailerOffset + ", keep trying");
                }
                readLine();
                peek = this.source.peek();
            }
        }
        if (this.source.peek() != 116) {
            return false;
        }
        long position = this.source.getPosition();
        String readLine = readLine();
        if (!readLine.trim().equals("trailer")) {
            if (!readLine.startsWith("trailer")) {
                return false;
            }
            this.source.seek(position + 7);
        }
        skipSpaces();
        this.xrefTrailerResolver.setTrailer(parseCOSDictionary());
        skipSpaces();
        return true;
    }

    public boolean parsePDFHeader() throws IOException {
        return parseHeader(PDF_HEADER, PDF_DEFAULT_VERSION);
    }

    public boolean parseFDFHeader() throws IOException {
        return parseHeader(FDF_HEADER, "1.0");
    }

    private boolean parseHeader(String str, String str2) throws IOException {
        String readLine = readLine();
        if (!readLine.contains(str)) {
            readLine = readLine();
            while (!readLine.contains(str) && (readLine.length() <= 0 || !Character.isDigit(readLine.charAt(0)))) {
                readLine = readLine();
            }
        }
        if (!readLine.contains(str)) {
            this.source.seek(0L);
            return false;
        }
        int indexOf = readLine.indexOf(str);
        if (indexOf > 0) {
            readLine = readLine.substring(indexOf);
        }
        if (readLine.startsWith(str) && !readLine.matches(str + "\\d.\\d")) {
            if (readLine.length() < str.length() + 3) {
                readLine = str + str2;
                Log.d("PdfBox-Android", "No version found, set to " + str2 + " as default.");
            } else {
                String str3 = readLine.substring(str.length() + 3, readLine.length()) + "\n";
                readLine = readLine.substring(0, str.length() + 3);
                this.source.rewind(str3.getBytes(Charsets.ISO_8859_1).length);
            }
        }
        float f = -1.0f;
        try {
            String[] split = readLine.split("-");
            if (split.length == 2) {
                f = Float.parseFloat(split[1]);
            }
        } catch (NumberFormatException e) {
            Log.d("PdfBox-Android", "Can't parse the header version.", e);
        }
        if (f < 0.0f) {
            if (!this.isLenient) {
                throw new IOException("Error getting header version: " + readLine);
            }
            f = 1.7f;
        }
        this.document.setVersion(f);
        this.source.seek(0L);
        return true;
    }

    protected boolean parseXrefTable(long j) throws IOException {
        if (this.source.peek() == 120 && readString().trim().equals("xref")) {
            String readString = readString();
            this.source.rewind(readString.getBytes(Charsets.ISO_8859_1).length);
            this.xrefTrailerResolver.nextXrefObj(j, XrefTrailerResolver.XRefType.TABLE);
            if (readString.startsWith("trailer")) {
                Log.w("PdfBox-Android", "skipping empty xref table");
                return false;
            }
            do {
                String readLine = readLine();
                String[] split = readLine.split("\\s");
                if (split.length != 2) {
                    Log.w("PdfBox-Android", "Unexpected XRefTable Entry: " + readLine);
                    return false;
                }
                try {
                    long parseLong = Long.parseLong(split[0]);
                    try {
                        int parseInt = Integer.parseInt(split[1]);
                        skipSpaces();
                        int i = 0;
                        while (true) {
                            if (i >= parseInt || this.source.isEOF() || isEndOfName((char) this.source.peek()) || this.source.peek() == 116) {
                                break;
                            }
                            String readLine2 = readLine();
                            String[] split2 = readLine2.split("\\s");
                            if (split2.length < 3) {
                                Log.w("PdfBox-Android", "invalid xref line: " + readLine2);
                                break;
                            }
                            if (split2[split2.length - 1].equals(OperatorName.ENDPATH)) {
                                try {
                                    long parseLong2 = Long.parseLong(split2[0]);
                                    if (parseLong2 > 0) {
                                        this.xrefTrailerResolver.setXRef(new COSObjectKey(parseLong, Integer.parseInt(split2[1])), parseLong2);
                                    }
                                } catch (NumberFormatException e) {
                                    throw new IOException(e);
                                }
                            } else if (!split2[2].equals(OperatorName.FILL_NON_ZERO)) {
                                throw new IOException("Corrupt XRefTable Entry - ObjID:" + parseLong);
                            }
                            parseLong++;
                            skipSpaces();
                            i++;
                        }
                        skipSpaces();
                    } catch (NumberFormatException unused) {
                        Log.w("PdfBox-Android", "XRefTable: invalid number of objects: " + readLine);
                        return false;
                    }
                } catch (NumberFormatException unused2) {
                    Log.w("PdfBox-Android", "XRefTable: invalid ID for the first object: " + readLine);
                    return false;
                }
            } while (isDigit());
            return true;
        }
        return false;
    }

    private void parseXrefStream(COSStream cOSStream, long j, boolean z) throws IOException {
        if (z) {
            this.xrefTrailerResolver.nextXrefObj(j, XrefTrailerResolver.XRefType.STREAM);
            this.xrefTrailerResolver.setTrailer(cOSStream);
        }
        new PDFXrefStreamParser(cOSStream, this.document, this.xrefTrailerResolver).parse();
    }

    public COSDocument getDocument() throws IOException {
        if (this.document == null) {
            throw new IOException("You must parse the document first before calling getDocument()");
        }
        return this.document;
    }

    public PDEncryption getEncryption() throws IOException {
        if (this.document == null) {
            throw new IOException("You must parse the document first before calling getEncryption()");
        }
        return this.encryption;
    }

    public AccessPermission getAccessPermission() throws IOException {
        if (this.document == null) {
            throw new IOException("You must parse the document first before calling getAccessPermission()");
        }
        return this.accessPermission;
    }

    public COSBase parseTrailerValuesDynamically(COSDictionary cOSDictionary) throws IOException {
        for (COSBase cOSBase : cOSDictionary.getValues()) {
            if (cOSBase instanceof COSObject) {
                parseObjectDynamically((COSObject) cOSBase, false);
            }
        }
        COSObject cOSObject = cOSDictionary.getCOSObject(COSName.ROOT);
        if (cOSObject == null) {
            throw new IOException("Missing root object specification in trailer.");
        }
        return cOSObject.getObject();
    }

    private void prepareDecryption() throws IOException {
        COSBase item;
        DecryptionMaterial standardDecryptionMaterial;
        if (this.encryption != null || (item = this.document.getTrailer().getItem(COSName.ENCRYPT)) == null || (item instanceof COSNull)) {
            return;
        }
        if (item instanceof COSObject) {
            parseDictionaryRecursive((COSObject) item);
        }
        try {
            try {
                this.encryption = new PDEncryption(this.document.getEncryptionDictionary());
                if (this.keyStoreInputStream != null) {
                    KeyStore keyStore = KeyStore.getInstance("PKCS12");
                    keyStore.load(this.keyStoreInputStream, this.password.toCharArray());
                    standardDecryptionMaterial = new PublicKeyDecryptionMaterial(keyStore, this.keyAlias, this.password);
                } else {
                    standardDecryptionMaterial = new StandardDecryptionMaterial(this.password);
                }
                SecurityHandler securityHandler = this.encryption.getSecurityHandler();
                this.securityHandler = securityHandler;
                securityHandler.prepareForDecryption(this.encryption, this.document.getDocumentID(), standardDecryptionMaterial);
                this.accessPermission = this.securityHandler.getCurrentAccessPermission();
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new IOException("Error (" + e2.getClass().getSimpleName() + ") while creating security handler for decryption", e2);
            }
        } finally {
            InputStream inputStream = this.keyStoreInputStream;
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

    private void parseDictionaryRecursive(COSObject cOSObject) throws IOException {
        parseObjectDynamically(cOSObject, true);
        if (!(cOSObject.getObject() instanceof COSDictionary)) {
            throw new IOException("Dictionary object expected at offset " + this.source.getPosition());
        }
        for (COSBase cOSBase : ((COSDictionary) cOSObject.getObject()).getValues()) {
            if (cOSBase instanceof COSObject) {
                COSObject cOSObject2 = (COSObject) cOSBase;
                if (cOSObject2.getObject() == null) {
                    parseDictionaryRecursive(cOSObject2);
                }
            }
        }
    }
}
