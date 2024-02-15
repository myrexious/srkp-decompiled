package com.tom_roush.pdfbox.cos;

import android.util.Log;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.io.ScratchFile;
import com.tom_roush.pdfbox.pdfparser.PDFObjectStreamParser;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class COSDocument extends COSBase implements Closeable {
    private boolean closed;
    private long highestXRefObjectNumber;
    private boolean isDecrypted;
    private boolean isXRefStream;
    private final Map<COSObjectKey, COSObject> objectPool;
    private ScratchFile scratchFile;
    private long startXref;
    private final List<COSStream> streams;
    private COSDictionary trailer;
    private float version;
    private boolean warnMissingClose;
    private final Map<COSObjectKey, Long> xrefTable;

    public COSDocument() {
        this(ScratchFile.getMainMemoryOnlyInstance());
    }

    public COSDocument(ScratchFile scratchFile) {
        this.version = 1.4f;
        this.objectPool = new HashMap();
        this.xrefTable = new HashMap();
        this.streams = new ArrayList();
        this.warnMissingClose = true;
        this.isDecrypted = false;
        this.closed = false;
        this.scratchFile = scratchFile;
    }

    public COSStream createCOSStream() {
        COSStream cOSStream = new COSStream(this.scratchFile);
        this.streams.add(cOSStream);
        return cOSStream;
    }

    public COSStream createCOSStream(COSDictionary cOSDictionary) {
        COSStream cOSStream = new COSStream(this.scratchFile);
        for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
            cOSStream.setItem(entry.getKey(), entry.getValue());
        }
        return cOSStream;
    }

    public COSObject getObjectByType(COSName cOSName) throws IOException {
        for (COSObject cOSObject : this.objectPool.values()) {
            COSBase object = cOSObject.getObject();
            if (object instanceof COSDictionary) {
                try {
                    COSBase item = ((COSDictionary) object).getItem(COSName.TYPE);
                    if (item instanceof COSName) {
                        if (((COSName) item).equals(cOSName)) {
                            return cOSObject;
                        }
                    } else if (item != null) {
                        Log.d("PdfBox-Android", "Expected a /Name object after /Type, got '" + item + "' instead");
                    }
                } catch (ClassCastException e) {
                    Log.w("PdfBox-Android", e.getMessage(), e);
                }
            }
        }
        return null;
    }

    public List<COSObject> getObjectsByType(String str) throws IOException {
        return getObjectsByType(COSName.getPDFName(str));
    }

    public List<COSObject> getObjectsByType(COSName cOSName) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (COSObject cOSObject : this.objectPool.values()) {
            COSBase object = cOSObject.getObject();
            if (object instanceof COSDictionary) {
                try {
                    COSBase item = ((COSDictionary) object).getItem(COSName.TYPE);
                    if (item instanceof COSName) {
                        if (((COSName) item).equals(cOSName)) {
                            arrayList.add(cOSObject);
                        }
                    } else if (item != null) {
                        Log.d("PdfBox-Android", "Expected a /Name object after /Type, got '" + item + "' instead");
                    }
                } catch (ClassCastException e) {
                    Log.w("PdfBox-Android", e.getMessage(), e);
                }
            }
        }
        return arrayList;
    }

    public COSObjectKey getKey(COSBase cOSBase) {
        for (Map.Entry<COSObjectKey, COSObject> entry : this.objectPool.entrySet()) {
            if (entry.getValue().getObject() == cOSBase) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void print() {
        for (COSObject cOSObject : this.objectPool.values()) {
            System.out.println(cOSObject);
        }
    }

    public void setVersion(float f) {
        this.version = f;
    }

    public float getVersion() {
        return this.version;
    }

    public void setDecrypted() {
        this.isDecrypted = true;
    }

    public boolean isDecrypted() {
        return this.isDecrypted;
    }

    public boolean isEncrypted() {
        COSDictionary cOSDictionary = this.trailer;
        if (cOSDictionary != null) {
            return cOSDictionary.getDictionaryObject(COSName.ENCRYPT) instanceof COSDictionary;
        }
        return false;
    }

    public COSDictionary getEncryptionDictionary() {
        return this.trailer.getCOSDictionary(COSName.ENCRYPT);
    }

    public void setEncryptionDictionary(COSDictionary cOSDictionary) {
        this.trailer.setItem(COSName.ENCRYPT, (COSBase) cOSDictionary);
    }

    public COSArray getDocumentID() {
        return getTrailer().getCOSArray(COSName.ID);
    }

    public void setDocumentID(COSArray cOSArray) {
        getTrailer().setItem(COSName.ID, (COSBase) cOSArray);
    }

    public COSObject getCatalog() throws IOException {
        COSObject objectByType = getObjectByType(COSName.CATALOG);
        if (objectByType != null) {
            return objectByType;
        }
        throw new IOException("Catalog cannot be found");
    }

    public List<COSObject> getObjects() {
        return new ArrayList(this.objectPool.values());
    }

    public COSDictionary getTrailer() {
        return this.trailer;
    }

    public void setTrailer(COSDictionary cOSDictionary) {
        this.trailer = cOSDictionary;
    }

    public long getHighestXRefObjectNumber() {
        return this.highestXRefObjectNumber;
    }

    public void setHighestXRefObjectNumber(long j) {
        this.highestXRefObjectNumber = j;
    }

    @Override // com.tom_roush.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromDocument(this);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        IOException iOException = null;
        for (COSObject cOSObject : getObjects()) {
            COSBase object = cOSObject.getObject();
            if (object instanceof COSStream) {
                iOException = IOUtils.closeAndLogException((COSStream) object, "COSStream", iOException);
            }
        }
        for (COSStream cOSStream : this.streams) {
            iOException = IOUtils.closeAndLogException(cOSStream, "COSStream", iOException);
        }
        ScratchFile scratchFile = this.scratchFile;
        if (scratchFile != null) {
            iOException = IOUtils.closeAndLogException(scratchFile, "ScratchFile", iOException);
        }
        this.closed = true;
        if (iOException != null) {
            throw iOException;
        }
    }

    public boolean isClosed() {
        return this.closed;
    }

    protected void finalize() throws IOException {
        if (this.closed) {
            return;
        }
        if (this.warnMissingClose) {
            Log.w("PdfBox-Android", "Warning: You did not close a PDF Document");
        }
        close();
    }

    public void setWarnMissingClose(boolean z) {
        this.warnMissingClose = z;
    }

    public void dereferenceObjectStreams() throws IOException {
        for (COSObject cOSObject : getObjectsByType(COSName.OBJ_STM)) {
            PDFObjectStreamParser pDFObjectStreamParser = new PDFObjectStreamParser((COSStream) cOSObject.getObject(), this);
            pDFObjectStreamParser.parse();
            for (COSObject cOSObject2 : pDFObjectStreamParser.getObjects()) {
                COSObjectKey cOSObjectKey = new COSObjectKey(cOSObject2);
                if (this.objectPool.get(cOSObjectKey) == null || this.objectPool.get(cOSObjectKey).getObject() == null || (this.xrefTable.containsKey(cOSObjectKey) && this.xrefTable.get(cOSObjectKey).longValue() == (-cOSObject.getObjectNumber()))) {
                    getObjectFromPool(cOSObjectKey).setObject(cOSObject2.getObject());
                }
            }
        }
    }

    public COSObject getObjectFromPool(COSObjectKey cOSObjectKey) throws IOException {
        COSObject cOSObject = cOSObjectKey != null ? this.objectPool.get(cOSObjectKey) : null;
        if (cOSObject == null) {
            cOSObject = new COSObject(null);
            if (cOSObjectKey != null) {
                cOSObject.setObjectNumber(cOSObjectKey.getNumber());
                cOSObject.setGenerationNumber(cOSObjectKey.getGeneration());
                this.objectPool.put(cOSObjectKey, cOSObject);
            }
        }
        return cOSObject;
    }

    public COSObject removeObject(COSObjectKey cOSObjectKey) {
        return this.objectPool.remove(cOSObjectKey);
    }

    public void addXRefTable(Map<COSObjectKey, Long> map) {
        this.xrefTable.putAll(map);
    }

    public Map<COSObjectKey, Long> getXrefTable() {
        return this.xrefTable;
    }

    public void setStartXref(long j) {
        this.startXref = j;
    }

    public long getStartXref() {
        return this.startXref;
    }

    public boolean isXRefStream() {
        return this.isXRefStream;
    }

    public void setIsXRefStream(boolean z) {
        this.isXRefStream = z;
    }
}
