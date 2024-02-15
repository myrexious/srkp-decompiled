package com.tom_roush.pdfbox.pdfwriter;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSObjectKey;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.cos.COSUpdateInfo;
import com.tom_roush.pdfbox.cos.ICOSVisitor;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.io.RandomAccessInputStream;
import com.tom_roush.pdfbox.io.RandomAccessRead;
import com.tom_roush.pdfbox.pdfparser.PDFXRefStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.encryption.SecurityHandler;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFDocument;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.COSFilterInputStream;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import com.tom_roush.pdfbox.util.Charsets;
import com.tom_roush.pdfbox.util.Hex;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.lang3.StringUtils;
import org.tensorflow.lite.schema.BuiltinOptions;

/* loaded from: classes3.dex */
public class COSWriter implements ICOSVisitor, Closeable {
    private final Set<COSBase> actualsAdded;
    private COSArray byteRangeArray;
    private long byteRangeLength;
    private long byteRangeOffset;
    private COSObjectKey currentObjectKey;
    private FDFDocument fdfDocument;
    private final NumberFormat formatXrefGeneration;
    private final NumberFormat formatXrefOffset;
    private byte[] incrementPart;
    private RandomAccessRead incrementalInput;
    private OutputStream incrementalOutput;
    private boolean incrementalUpdate;
    private final Map<COSObjectKey, COSBase> keyObject;
    private long number;
    private final Map<COSBase, COSObjectKey> objectKeys;
    private final Deque<COSBase> objectsToWrite;
    private final Set<COSBase> objectsToWriteSet;
    private OutputStream output;
    private PDDocument pdDocument;
    private boolean reachedSignature;
    private SignatureInterface signatureInterface;
    private long signatureLength;
    private long signatureOffset;
    private COSStandardOutputStream standardOutput;
    private long startxref;
    private boolean willEncrypt;
    private final Set<COSBase> writtenObjects;
    private final List<COSWriterXRefEntry> xRefEntries;
    public static final byte[] DICT_OPEN = "<<".getBytes(Charsets.US_ASCII);
    public static final byte[] DICT_CLOSE = ">>".getBytes(Charsets.US_ASCII);
    public static final byte[] SPACE = {32};
    public static final byte[] COMMENT = {BuiltinOptions.CastOptions};
    public static final byte[] VERSION = "PDF-1.4".getBytes(Charsets.US_ASCII);
    public static final byte[] GARBAGE = {-10, -28, -4, -33};
    public static final byte[] EOF = "%%EOF".getBytes(Charsets.US_ASCII);
    public static final byte[] REFERENCE = "R".getBytes(Charsets.US_ASCII);
    public static final byte[] XREF = "xref".getBytes(Charsets.US_ASCII);
    public static final byte[] XREF_FREE = OperatorName.FILL_NON_ZERO.getBytes(Charsets.US_ASCII);
    public static final byte[] XREF_USED = OperatorName.ENDPATH.getBytes(Charsets.US_ASCII);
    public static final byte[] TRAILER = "trailer".getBytes(Charsets.US_ASCII);
    public static final byte[] STARTXREF = "startxref".getBytes(Charsets.US_ASCII);
    public static final byte[] OBJ = "obj".getBytes(Charsets.US_ASCII);
    public static final byte[] ENDOBJ = "endobj".getBytes(Charsets.US_ASCII);
    public static final byte[] ARRAY_OPEN = "[".getBytes(Charsets.US_ASCII);
    public static final byte[] ARRAY_CLOSE = "]".getBytes(Charsets.US_ASCII);
    public static final byte[] STREAM = "stream".getBytes(Charsets.US_ASCII);
    public static final byte[] ENDSTREAM = "endstream".getBytes(Charsets.US_ASCII);

    public COSWriter(OutputStream outputStream) {
        this.formatXrefOffset = new DecimalFormat("0000000000", DecimalFormatSymbols.getInstance(Locale.US));
        this.formatXrefGeneration = new DecimalFormat("00000", DecimalFormatSymbols.getInstance(Locale.US));
        this.startxref = 0L;
        this.number = 0L;
        this.objectKeys = new Hashtable();
        this.keyObject = new HashMap();
        this.xRefEntries = new ArrayList();
        this.objectsToWriteSet = new HashSet();
        this.objectsToWrite = new LinkedList();
        this.writtenObjects = new HashSet();
        this.actualsAdded = new HashSet();
        this.currentObjectKey = null;
        this.pdDocument = null;
        this.fdfDocument = null;
        this.willEncrypt = false;
        this.incrementalUpdate = false;
        this.reachedSignature = false;
        setOutput(outputStream);
        setStandardOutput(new COSStandardOutputStream(this.output));
    }

    public COSWriter(OutputStream outputStream, RandomAccessRead randomAccessRead) throws IOException {
        this.formatXrefOffset = new DecimalFormat("0000000000", DecimalFormatSymbols.getInstance(Locale.US));
        this.formatXrefGeneration = new DecimalFormat("00000", DecimalFormatSymbols.getInstance(Locale.US));
        this.startxref = 0L;
        this.number = 0L;
        this.objectKeys = new Hashtable();
        this.keyObject = new HashMap();
        this.xRefEntries = new ArrayList();
        this.objectsToWriteSet = new HashSet();
        this.objectsToWrite = new LinkedList();
        this.writtenObjects = new HashSet();
        this.actualsAdded = new HashSet();
        this.currentObjectKey = null;
        this.pdDocument = null;
        this.fdfDocument = null;
        this.willEncrypt = false;
        this.incrementalUpdate = false;
        this.reachedSignature = false;
        setOutput(new ByteArrayOutputStream());
        setStandardOutput(new COSStandardOutputStream(this.output, randomAccessRead.length()));
        this.incrementalInput = randomAccessRead;
        this.incrementalOutput = outputStream;
        this.incrementalUpdate = true;
    }

    public COSWriter(OutputStream outputStream, RandomAccessRead randomAccessRead, Set<COSDictionary> set) throws IOException {
        this(outputStream, randomAccessRead);
        this.objectsToWrite.addAll(set);
    }

    private void prepareIncrement(PDDocument pDDocument) {
        if (pDDocument != null) {
            try {
                COSDocument document = pDDocument.getDocument();
                Set<COSObjectKey> keySet = document.getXrefTable().keySet();
                long highestXRefObjectNumber = pDDocument.getDocument().getHighestXRefObjectNumber();
                for (COSObjectKey cOSObjectKey : keySet) {
                    if (cOSObjectKey != null) {
                        COSBase object = document.getObjectFromPool(cOSObjectKey).getObject();
                        if (object != null && !(object instanceof COSNumber)) {
                            this.objectKeys.put(object, cOSObjectKey);
                            this.keyObject.put(cOSObjectKey, object);
                        }
                        long number = cOSObjectKey.getNumber();
                        if (number > highestXRefObjectNumber) {
                            highestXRefObjectNumber = number;
                        }
                    }
                }
                setNumber(highestXRefObjectNumber);
            } catch (IOException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
            }
        }
    }

    protected void addXRefEntry(COSWriterXRefEntry cOSWriterXRefEntry) {
        getXRefEntries().add(cOSWriterXRefEntry);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (getStandardOutput() != null) {
            getStandardOutput().close();
        }
        OutputStream outputStream = this.incrementalOutput;
        if (outputStream != null) {
            outputStream.close();
        }
    }

    protected long getNumber() {
        return this.number;
    }

    public Map<COSBase, COSObjectKey> getObjectKeys() {
        return this.objectKeys;
    }

    protected OutputStream getOutput() {
        return this.output;
    }

    protected COSStandardOutputStream getStandardOutput() {
        return this.standardOutput;
    }

    protected long getStartxref() {
        return this.startxref;
    }

    protected List<COSWriterXRefEntry> getXRefEntries() {
        return this.xRefEntries;
    }

    protected void setNumber(long j) {
        this.number = j;
    }

    private void setOutput(OutputStream outputStream) {
        this.output = outputStream;
    }

    private void setStandardOutput(COSStandardOutputStream cOSStandardOutputStream) {
        this.standardOutput = cOSStandardOutputStream;
    }

    protected void setStartxref(long j) {
        this.startxref = j;
    }

    protected void doWriteBody(COSDocument cOSDocument) throws IOException {
        COSDictionary trailer = cOSDocument.getTrailer();
        COSDictionary cOSDictionary = trailer.getCOSDictionary(COSName.ROOT);
        COSDictionary cOSDictionary2 = trailer.getCOSDictionary(COSName.INFO);
        COSDictionary cOSDictionary3 = trailer.getCOSDictionary(COSName.ENCRYPT);
        if (cOSDictionary != null) {
            addObjectToWrite(cOSDictionary);
        }
        if (cOSDictionary2 != null) {
            addObjectToWrite(cOSDictionary2);
        }
        doWriteObjects();
        this.willEncrypt = false;
        if (cOSDictionary3 != null) {
            addObjectToWrite(cOSDictionary3);
        }
        doWriteObjects();
    }

    private void doWriteObjects() throws IOException {
        while (this.objectsToWrite.size() > 0) {
            COSBase removeFirst = this.objectsToWrite.removeFirst();
            this.objectsToWriteSet.remove(removeFirst);
            doWriteObject(removeFirst);
        }
    }

    private void addObjectToWrite(COSBase cOSBase) {
        COSObjectKey cOSObjectKey;
        COSBase object = cOSBase instanceof COSObject ? ((COSObject) cOSBase).getObject() : cOSBase;
        if (this.writtenObjects.contains(cOSBase) || this.objectsToWriteSet.contains(cOSBase) || this.actualsAdded.contains(object)) {
            return;
        }
        if (object != null && (cOSObjectKey = this.objectKeys.get(object)) != null) {
            COSBase cOSBase2 = this.keyObject.get(cOSObjectKey);
            if (!isNeedToBeUpdated(cOSBase) && !isNeedToBeUpdated(cOSBase2)) {
                return;
            }
        }
        this.objectsToWrite.add(cOSBase);
        this.objectsToWriteSet.add(cOSBase);
        if (object != null) {
            this.actualsAdded.add(object);
        }
    }

    private boolean isNeedToBeUpdated(COSBase cOSBase) {
        if (cOSBase instanceof COSUpdateInfo) {
            return ((COSUpdateInfo) cOSBase).isNeedToBeUpdated();
        }
        return false;
    }

    public void doWriteObject(COSBase cOSBase) throws IOException {
        this.writtenObjects.add(cOSBase);
        this.currentObjectKey = getObjectKey(cOSBase);
        addXRefEntry(new COSWriterXRefEntry(getStandardOutput().getPos(), cOSBase, this.currentObjectKey));
        getStandardOutput().write(String.valueOf(this.currentObjectKey.getNumber()).getBytes(Charsets.ISO_8859_1));
        COSStandardOutputStream standardOutput = getStandardOutput();
        byte[] bArr = SPACE;
        standardOutput.write(bArr);
        getStandardOutput().write(String.valueOf(this.currentObjectKey.getGeneration()).getBytes(Charsets.ISO_8859_1));
        getStandardOutput().write(bArr);
        getStandardOutput().write(OBJ);
        getStandardOutput().writeEOL();
        cOSBase.accept(this);
        getStandardOutput().writeEOL();
        getStandardOutput().write(ENDOBJ);
        getStandardOutput().writeEOL();
    }

    protected void doWriteHeader(COSDocument cOSDocument) throws IOException {
        String str;
        if (this.fdfDocument != null) {
            str = "%FDF-" + cOSDocument.getVersion();
        } else {
            str = "%PDF-" + cOSDocument.getVersion();
        }
        getStandardOutput().write(str.getBytes(Charsets.ISO_8859_1));
        getStandardOutput().writeEOL();
        getStandardOutput().write(COMMENT);
        getStandardOutput().write(GARBAGE);
        getStandardOutput().writeEOL();
    }

    protected void doWriteTrailer(COSDocument cOSDocument) throws IOException {
        getStandardOutput().write(TRAILER);
        getStandardOutput().writeEOL();
        COSDictionary trailer = cOSDocument.getTrailer();
        Collections.sort(getXRefEntries());
        trailer.setLong(COSName.SIZE, getXRefEntries().get(getXRefEntries().size() - 1).getKey().getNumber() + 1);
        if (!this.incrementalUpdate) {
            trailer.removeItem(COSName.PREV);
        }
        if (!cOSDocument.isXRefStream()) {
            trailer.removeItem(COSName.XREF_STM);
        }
        trailer.removeItem(COSName.DOC_CHECKSUM);
        COSArray cOSArray = trailer.getCOSArray(COSName.ID);
        if (cOSArray != null) {
            cOSArray.setDirect(true);
        }
        trailer.accept(this);
    }

    private void doWriteXRefInc(COSDocument cOSDocument, long j) throws IOException {
        if (cOSDocument.isXRefStream() || j != -1) {
            PDFXRefStream pDFXRefStream = new PDFXRefStream(cOSDocument);
            for (COSWriterXRefEntry cOSWriterXRefEntry : getXRefEntries()) {
                pDFXRefStream.addEntry(cOSWriterXRefEntry);
            }
            COSDictionary trailer = cOSDocument.getTrailer();
            if (this.incrementalUpdate) {
                trailer.setLong(COSName.PREV, cOSDocument.getStartXref());
            } else {
                trailer.removeItem(COSName.PREV);
            }
            pDFXRefStream.addTrailerInfo(trailer);
            pDFXRefStream.setSize(getNumber() + 2);
            setStartxref(getStandardOutput().getPos());
            doWriteObject(pDFXRefStream.getStream());
        }
        if (cOSDocument.isXRefStream() && j == -1) {
            return;
        }
        COSDictionary trailer2 = cOSDocument.getTrailer();
        trailer2.setLong(COSName.PREV, cOSDocument.getStartXref());
        if (j != -1) {
            COSName cOSName = COSName.XREF_STM;
            trailer2.removeItem(cOSName);
            trailer2.setLong(cOSName, getStartxref());
        }
        doWriteXRefTable();
        doWriteTrailer(cOSDocument);
    }

    private void doWriteXRefTable() throws IOException {
        addXRefEntry(COSWriterXRefEntry.getNullEntry());
        Collections.sort(getXRefEntries());
        setStartxref(getStandardOutput().getPos());
        getStandardOutput().write(XREF);
        getStandardOutput().writeEOL();
        Long[] xRefRanges = getXRefRanges(getXRefEntries());
        int length = xRefRanges.length;
        if (length % 2 == 0) {
            int i = 0;
            for (int i2 = 0; i2 < length; i2 += 2) {
                long longValue = xRefRanges[i2 + 1].longValue();
                writeXrefRange(xRefRanges[i2].longValue(), longValue);
                int i3 = 0;
                while (i3 < longValue) {
                    writeXrefEntry(this.xRefEntries.get(i));
                    i3++;
                    i++;
                }
            }
        }
    }

    private void doWriteIncrement() throws IOException {
        IOUtils.copy(new RandomAccessInputStream(this.incrementalInput), this.incrementalOutput);
        this.incrementalOutput.write(((ByteArrayOutputStream) this.output).toByteArray());
    }

    private void doWriteSignature() throws IOException {
        long length = this.incrementalInput.length();
        long j = this.signatureOffset;
        long j2 = this.signatureLength + j;
        long pos = (getStandardOutput().getPos() - (this.signatureLength + length)) - (this.signatureOffset - length);
        String str = "0 " + j + StringUtils.SPACE + j2 + StringUtils.SPACE + pos + "]";
        int i = 0;
        this.byteRangeArray.set(0, (COSBase) COSInteger.ZERO);
        this.byteRangeArray.set(1, (COSBase) COSInteger.get(j));
        this.byteRangeArray.set(2, (COSBase) COSInteger.get(j2));
        this.byteRangeArray.set(3, (COSBase) COSInteger.get(pos));
        if (str.length() > this.byteRangeLength) {
            throw new IOException("Can't write new byteRange '" + str + "' not enough space: byteRange.length(): " + str.length() + ", byteRangeLength: " + this.byteRangeLength);
        }
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) this.output;
        byteArrayOutputStream.flush();
        this.incrementPart = byteArrayOutputStream.toByteArray();
        byte[] bytes = str.getBytes(Charsets.ISO_8859_1);
        while (true) {
            long j3 = i;
            if (j3 >= this.byteRangeLength) {
                break;
            }
            if (i >= bytes.length) {
                this.incrementPart[(int) ((this.byteRangeOffset + j3) - length)] = 32;
            } else {
                this.incrementPart[(int) ((this.byteRangeOffset + j3) - length)] = bytes[i];
            }
            i++;
        }
        if (this.signatureInterface != null) {
            writeExternalSignature(this.signatureInterface.sign(getDataToSign()));
        }
    }

    public InputStream getDataToSign() throws IOException {
        RandomAccessRead randomAccessRead;
        if (this.incrementPart == null || (randomAccessRead = this.incrementalInput) == null) {
            throw new IllegalStateException("PDF not prepared for signing");
        }
        int length = (int) (this.signatureOffset - randomAccessRead.length());
        int i = ((int) this.signatureLength) + length;
        return new SequenceInputStream(new RandomAccessInputStream(this.incrementalInput), new COSFilterInputStream(this.incrementPart, new int[]{0, length, i, this.incrementPart.length - i}));
    }

    public void writeExternalSignature(byte[] bArr) throws IOException {
        if (this.incrementPart == null || this.incrementalInput == null) {
            throw new IllegalStateException("PDF not prepared for setting signature");
        }
        byte[] bytes = Hex.getBytes(bArr);
        if (bytes.length > this.signatureLength - 2) {
            throw new IOException("Can't write signature, not enough space; adjust it with SignatureOptions.setPreferredSignatureSize");
        }
        System.arraycopy(bytes, 0, this.incrementPart, ((int) (this.signatureOffset - this.incrementalInput.length())) + 1, bytes.length);
        IOUtils.copy(new RandomAccessInputStream(this.incrementalInput), this.incrementalOutput);
        this.incrementalOutput.write(this.incrementPart);
        this.incrementPart = null;
    }

    private void writeXrefRange(long j, long j2) throws IOException {
        getStandardOutput().write(String.valueOf(j).getBytes(Charsets.ISO_8859_1));
        getStandardOutput().write(SPACE);
        getStandardOutput().write(String.valueOf(j2).getBytes(Charsets.ISO_8859_1));
        getStandardOutput().writeEOL();
    }

    private void writeXrefEntry(COSWriterXRefEntry cOSWriterXRefEntry) throws IOException {
        String format = this.formatXrefOffset.format(cOSWriterXRefEntry.getOffset());
        String format2 = this.formatXrefGeneration.format(cOSWriterXRefEntry.getKey().getGeneration());
        getStandardOutput().write(format.getBytes(Charsets.ISO_8859_1));
        COSStandardOutputStream standardOutput = getStandardOutput();
        byte[] bArr = SPACE;
        standardOutput.write(bArr);
        getStandardOutput().write(format2.getBytes(Charsets.ISO_8859_1));
        getStandardOutput().write(bArr);
        getStandardOutput().write(cOSWriterXRefEntry.isFree() ? XREF_FREE : XREF_USED);
        getStandardOutput().writeCRLF();
    }

    protected Long[] getXRefRanges(List<COSWriterXRefEntry> list) {
        ArrayList arrayList = new ArrayList();
        long j = -2;
        long j2 = 1;
        for (COSWriterXRefEntry cOSWriterXRefEntry : list) {
            long number = cOSWriterXRefEntry.getKey().getNumber();
            if (number == j + 1) {
                j2++;
            } else if (j != -2) {
                arrayList.add(Long.valueOf((j - j2) + 1));
                arrayList.add(Long.valueOf(j2));
                j2 = 1;
            }
            j = number;
        }
        if (list.size() > 0) {
            arrayList.add(Long.valueOf((j - j2) + 1));
            arrayList.add(Long.valueOf(j2));
        }
        return (Long[]) arrayList.toArray(new Long[arrayList.size()]);
    }

    private COSObjectKey getObjectKey(COSBase cOSBase) {
        COSBase object = cOSBase instanceof COSObject ? ((COSObject) cOSBase).getObject() : cOSBase;
        COSObjectKey cOSObjectKey = this.objectKeys.get(cOSBase);
        if (cOSObjectKey == null && object != null) {
            cOSObjectKey = this.objectKeys.get(object);
        }
        if (cOSObjectKey == null) {
            setNumber(getNumber() + 1);
            cOSObjectKey = new COSObjectKey(getNumber(), 0);
            this.objectKeys.put(cOSBase, cOSObjectKey);
            if (object != null) {
                this.objectKeys.put(object, cOSObjectKey);
            }
        }
        return cOSObjectKey;
    }

    @Override // com.tom_roush.pdfbox.cos.ICOSVisitor
    public Object visitFromArray(COSArray cOSArray) throws IOException {
        getStandardOutput().write(ARRAY_OPEN);
        Iterator<COSBase> it = cOSArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            COSBase next = it.next();
            if (next instanceof COSDictionary) {
                if (next.isDirect()) {
                    visitFromDictionary((COSDictionary) next);
                } else {
                    addObjectToWrite(next);
                    writeReference(next);
                }
            } else if (next instanceof COSObject) {
                COSBase object = ((COSObject) next).getObject();
                if (this.willEncrypt || this.incrementalUpdate || (object instanceof COSDictionary) || object == null) {
                    addObjectToWrite(next);
                    writeReference(next);
                } else {
                    object.accept(this);
                }
            } else if (next == null) {
                COSNull.NULL.accept(this);
            } else {
                next.accept(this);
            }
            i++;
            if (it.hasNext()) {
                if (i % 10 == 0) {
                    getStandardOutput().writeEOL();
                } else {
                    getStandardOutput().write(SPACE);
                }
            }
        }
        getStandardOutput().write(ARRAY_CLOSE);
        getStandardOutput().writeEOL();
        return null;
    }

    @Override // com.tom_roush.pdfbox.cos.ICOSVisitor
    public Object visitFromBoolean(COSBoolean cOSBoolean) throws IOException {
        cOSBoolean.writePDF(getStandardOutput());
        return null;
    }

    @Override // com.tom_roush.pdfbox.cos.ICOSVisitor
    public Object visitFromDictionary(COSDictionary cOSDictionary) throws IOException {
        if (!this.reachedSignature) {
            COSBase item = cOSDictionary.getItem(COSName.TYPE);
            if (COSName.SIG.equals(item) || COSName.DOC_TIME_STAMP.equals(item)) {
                this.reachedSignature = true;
            }
        }
        getStandardOutput().write(DICT_OPEN);
        getStandardOutput().writeEOL();
        for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
            COSBase value = entry.getValue();
            if (value != null) {
                entry.getKey().accept(this);
                getStandardOutput().write(SPACE);
                if (value instanceof COSDictionary) {
                    COSDictionary cOSDictionary2 = (COSDictionary) value;
                    if (!this.incrementalUpdate) {
                        COSBase item2 = cOSDictionary2.getItem(COSName.XOBJECT);
                        if (item2 != null && !COSName.XOBJECT.equals(entry.getKey())) {
                            item2.setDirect(true);
                        }
                        COSBase item3 = cOSDictionary2.getItem(COSName.RESOURCES);
                        if (item3 != null && !COSName.RESOURCES.equals(entry.getKey())) {
                            item3.setDirect(true);
                        }
                    }
                    if (cOSDictionary2.isDirect()) {
                        visitFromDictionary(cOSDictionary2);
                    } else {
                        addObjectToWrite(cOSDictionary2);
                        writeReference(cOSDictionary2);
                    }
                } else if (value instanceof COSObject) {
                    COSBase object = ((COSObject) value).getObject();
                    if (this.willEncrypt || this.incrementalUpdate || (object instanceof COSDictionary) || object == null) {
                        addObjectToWrite(value);
                        writeReference(value);
                    } else {
                        object.accept(this);
                    }
                } else if (this.reachedSignature && COSName.CONTENTS.equals(entry.getKey())) {
                    this.signatureOffset = getStandardOutput().getPos();
                    value.accept(this);
                    this.signatureLength = getStandardOutput().getPos() - this.signatureOffset;
                } else if (this.reachedSignature && COSName.BYTERANGE.equals(entry.getKey())) {
                    this.byteRangeArray = (COSArray) entry.getValue();
                    this.byteRangeOffset = getStandardOutput().getPos() + 1;
                    value.accept(this);
                    this.byteRangeLength = (getStandardOutput().getPos() - 1) - this.byteRangeOffset;
                    this.reachedSignature = false;
                } else {
                    value.accept(this);
                }
                getStandardOutput().writeEOL();
            }
        }
        getStandardOutput().write(DICT_CLOSE);
        getStandardOutput().writeEOL();
        return null;
    }

    @Override // com.tom_roush.pdfbox.cos.ICOSVisitor
    public Object visitFromDocument(COSDocument cOSDocument) throws IOException {
        if (!this.incrementalUpdate) {
            doWriteHeader(cOSDocument);
        } else {
            getStandardOutput().writeCRLF();
        }
        doWriteBody(cOSDocument);
        COSDictionary trailer = cOSDocument.getTrailer();
        long j = trailer != null ? trailer.getLong(COSName.XREF_STM) : -1L;
        if (this.incrementalUpdate || cOSDocument.isXRefStream()) {
            doWriteXRefInc(cOSDocument, j);
        } else {
            doWriteXRefTable();
            doWriteTrailer(cOSDocument);
        }
        getStandardOutput().write(STARTXREF);
        getStandardOutput().writeEOL();
        getStandardOutput().write(String.valueOf(getStartxref()).getBytes(Charsets.ISO_8859_1));
        getStandardOutput().writeEOL();
        getStandardOutput().write(EOF);
        getStandardOutput().writeEOL();
        if (this.incrementalUpdate) {
            if (this.signatureOffset == 0 || this.byteRangeOffset == 0) {
                doWriteIncrement();
                return null;
            }
            doWriteSignature();
            return null;
        }
        return null;
    }

    @Override // com.tom_roush.pdfbox.cos.ICOSVisitor
    public Object visitFromFloat(COSFloat cOSFloat) throws IOException {
        cOSFloat.writePDF(getStandardOutput());
        return null;
    }

    @Override // com.tom_roush.pdfbox.cos.ICOSVisitor
    public Object visitFromInt(COSInteger cOSInteger) throws IOException {
        cOSInteger.writePDF(getStandardOutput());
        return null;
    }

    @Override // com.tom_roush.pdfbox.cos.ICOSVisitor
    public Object visitFromName(COSName cOSName) throws IOException {
        cOSName.writePDF(getStandardOutput());
        return null;
    }

    @Override // com.tom_roush.pdfbox.cos.ICOSVisitor
    public Object visitFromNull(COSNull cOSNull) throws IOException {
        cOSNull.writePDF(getStandardOutput());
        return null;
    }

    public void writeReference(COSBase cOSBase) throws IOException {
        COSObjectKey objectKey = getObjectKey(cOSBase);
        getStandardOutput().write(String.valueOf(objectKey.getNumber()).getBytes(Charsets.ISO_8859_1));
        COSStandardOutputStream standardOutput = getStandardOutput();
        byte[] bArr = SPACE;
        standardOutput.write(bArr);
        getStandardOutput().write(String.valueOf(objectKey.getGeneration()).getBytes(Charsets.ISO_8859_1));
        getStandardOutput().write(bArr);
        getStandardOutput().write(REFERENCE);
    }

    @Override // com.tom_roush.pdfbox.cos.ICOSVisitor
    public Object visitFromStream(COSStream cOSStream) throws IOException {
        Throwable th;
        InputStream inputStream;
        if (this.willEncrypt) {
            this.pdDocument.getEncryption().getSecurityHandler().encryptStream(cOSStream, this.currentObjectKey.getNumber(), this.currentObjectKey.getGeneration());
        }
        try {
            visitFromDictionary(cOSStream);
            getStandardOutput().write(STREAM);
            getStandardOutput().writeCRLF();
            inputStream = cOSStream.createRawInputStream();
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        try {
            IOUtils.copy(inputStream, getStandardOutput());
            getStandardOutput().writeCRLF();
            getStandardOutput().write(ENDSTREAM);
            getStandardOutput().writeEOL();
            if (inputStream != null) {
                inputStream.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    @Override // com.tom_roush.pdfbox.cos.ICOSVisitor
    public Object visitFromString(COSString cOSString) throws IOException {
        if (this.willEncrypt) {
            this.pdDocument.getEncryption().getSecurityHandler().encryptString(cOSString, this.currentObjectKey.getNumber(), this.currentObjectKey.getGeneration());
        }
        writeString(cOSString, getStandardOutput());
        return null;
    }

    public void write(COSDocument cOSDocument) throws IOException {
        write(new PDDocument(cOSDocument));
    }

    public void write(PDDocument pDDocument) throws IOException {
        write(pDDocument, null);
    }

    public void write(PDDocument pDDocument, SignatureInterface signatureInterface) throws IOException {
        COSArray cOSArray;
        Long valueOf = Long.valueOf(pDDocument.getDocumentId() == null ? System.currentTimeMillis() : pDDocument.getDocumentId().longValue());
        this.pdDocument = pDDocument;
        this.signatureInterface = signatureInterface;
        if (this.incrementalUpdate) {
            prepareIncrement(pDDocument);
        }
        boolean z = true;
        if (pDDocument.isAllSecurityToBeRemoved()) {
            this.willEncrypt = false;
            pDDocument.getDocument().getTrailer().removeItem(COSName.ENCRYPT);
        } else if (this.pdDocument.getEncryption() != null) {
            if (!this.incrementalUpdate) {
                SecurityHandler securityHandler = this.pdDocument.getEncryption().getSecurityHandler();
                if (!securityHandler.hasProtectionPolicy()) {
                    throw new IllegalStateException("PDF contains an encryption dictionary, please remove it with setAllSecurityToBeRemoved() or set a protection policy with protect()");
                }
                securityHandler.prepareDocumentForEncryption(this.pdDocument);
            }
            this.willEncrypt = true;
        } else {
            this.willEncrypt = false;
        }
        COSDocument document = this.pdDocument.getDocument();
        COSDictionary trailer = document.getTrailer();
        COSBase dictionaryObject = trailer.getDictionaryObject(COSName.ID);
        if (dictionaryObject instanceof COSArray) {
            cOSArray = (COSArray) dictionaryObject;
            if (cOSArray.size() == 2) {
                z = false;
            }
        } else {
            cOSArray = null;
        }
        if (cOSArray != null && cOSArray.size() == 2) {
            z = false;
        }
        if (z || this.incrementalUpdate) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                messageDigest.update(Long.toString(valueOf.longValue()).getBytes(Charsets.ISO_8859_1));
                COSDictionary cOSDictionary = trailer.getCOSDictionary(COSName.INFO);
                if (cOSDictionary != null) {
                    for (COSBase cOSBase : cOSDictionary.getValues()) {
                        messageDigest.update(cOSBase.toString().getBytes(Charsets.ISO_8859_1));
                    }
                }
                COSString cOSString = z ? new COSString(messageDigest.digest()) : (COSString) cOSArray.get(0);
                COSString cOSString2 = z ? cOSString : new COSString(messageDigest.digest());
                COSArray cOSArray2 = new COSArray();
                cOSArray2.add((COSBase) cOSString);
                cOSArray2.add((COSBase) cOSString2);
                trailer.setItem(COSName.ID, (COSBase) cOSArray2);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        document.accept(this);
    }

    public void write(FDFDocument fDFDocument) throws IOException {
        this.fdfDocument = fDFDocument;
        this.willEncrypt = false;
        fDFDocument.getDocument().accept(this);
    }

    public static void writeString(COSString cOSString, OutputStream outputStream) throws IOException {
        writeString(cOSString.getBytes(), cOSString.getForceHexForm(), outputStream);
    }

    public static void writeString(byte[] bArr, OutputStream outputStream) throws IOException {
        writeString(bArr, false, outputStream);
    }

    private static void writeString(byte[] bArr, boolean z, OutputStream outputStream) throws IOException {
        boolean z2;
        if (!z) {
            for (byte b : bArr) {
                if (b < 0 || b == 13 || b == 10) {
                    z2 = false;
                    break;
                }
            }
        }
        z2 = true;
        if (z2 && !z) {
            outputStream.write(40);
            for (int i : bArr) {
                if (i == 40 || i == 41 || i == 92) {
                    outputStream.write(92);
                    outputStream.write(i);
                } else {
                    outputStream.write(i);
                }
            }
            outputStream.write(41);
            return;
        }
        outputStream.write(60);
        Hex.writeHexBytes(bArr, outputStream);
        outputStream.write(62);
    }
}
