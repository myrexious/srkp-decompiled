package com.tom_roush.pdfbox.pdmodel.common;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.filter.DecodeOptions;
import com.tom_roush.pdfbox.filter.FilterFactory;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PDStream implements COSObjectable {
    private final COSStream stream;

    public PDStream(PDDocument pDDocument) {
        this.stream = pDDocument.getDocument().createCOSStream();
    }

    public PDStream(COSDocument cOSDocument) {
        this.stream = cOSDocument.createCOSStream();
    }

    public PDStream(COSStream cOSStream) {
        this.stream = cOSStream;
    }

    public PDStream(PDDocument pDDocument, InputStream inputStream) throws IOException {
        this(pDDocument, inputStream, (COSBase) null);
    }

    public PDStream(PDDocument pDDocument, InputStream inputStream, COSName cOSName) throws IOException {
        this(pDDocument, inputStream, (COSBase) cOSName);
    }

    public PDStream(PDDocument pDDocument, InputStream inputStream, COSArray cOSArray) throws IOException {
        this(pDDocument, inputStream, (COSBase) cOSArray);
    }

    private PDStream(PDDocument pDDocument, InputStream inputStream, COSBase cOSBase) throws IOException {
        OutputStream outputStream = null;
        try {
            COSStream createCOSStream = pDDocument.getDocument().createCOSStream();
            this.stream = createCOSStream;
            outputStream = createCOSStream.createOutputStream(cOSBase);
            IOUtils.copy(inputStream, outputStream);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            inputStream.close();
        }
    }

    @Deprecated
    public void addCompression() {
        if (getFilters() == null) {
            if (this.stream.getLength() > 0) {
                OutputStream outputStream = null;
                try {
                    try {
                        byte[] byteArray = IOUtils.toByteArray(this.stream.createInputStream());
                        outputStream = this.stream.createOutputStream(COSName.FLATE_DECODE);
                        outputStream.write(byteArray);
                        return;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } finally {
                    IOUtils.closeQuietly(outputStream);
                }
            }
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(COSName.FLATE_DECODE);
            setFilters(arrayList);
        }
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSStream getCOSObject() {
        return this.stream;
    }

    public OutputStream createOutputStream() throws IOException {
        return this.stream.createOutputStream();
    }

    public OutputStream createOutputStream(COSName cOSName) throws IOException {
        return this.stream.createOutputStream(cOSName);
    }

    public COSInputStream createInputStream() throws IOException {
        return this.stream.createInputStream();
    }

    public COSInputStream createInputStream(DecodeOptions decodeOptions) throws IOException {
        return this.stream.createInputStream(decodeOptions);
    }

    public InputStream createInputStream(List<String> list) throws IOException {
        InputStream createRawInputStream = this.stream.createRawInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<COSName> filters = getFilters();
        if (filters != null) {
            for (int i = 0; i < filters.size(); i++) {
                COSName cOSName = filters.get(i);
                if (list != null && list.contains(cOSName.getName())) {
                    break;
                }
                try {
                    FilterFactory.INSTANCE.getFilter(cOSName).decode(createRawInputStream, byteArrayOutputStream, this.stream, i);
                    IOUtils.closeQuietly(createRawInputStream);
                    createRawInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                    byteArrayOutputStream.reset();
                } catch (Throwable th) {
                    IOUtils.closeQuietly(createRawInputStream);
                    throw th;
                }
            }
        }
        return createRawInputStream;
    }

    @Deprecated
    public COSStream getStream() {
        return this.stream;
    }

    public int getLength() {
        return this.stream.getInt(COSName.LENGTH, 0);
    }

    public List<COSName> getFilters() {
        COSBase filters = this.stream.getFilters();
        if (filters instanceof COSName) {
            COSName cOSName = (COSName) filters;
            return new COSArrayList(cOSName, cOSName, this.stream, COSName.FILTER);
        } else if (filters instanceof COSArray) {
            return ((COSArray) filters).toList();
        } else {
            return null;
        }
    }

    public void setFilters(List<COSName> list) {
        this.stream.setItem(COSName.FILTER, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public List<Object> getDecodeParms() throws IOException {
        return internalGetDecodeParams(COSName.DECODE_PARMS, COSName.DP);
    }

    public List<Object> getFileDecodeParams() throws IOException {
        return internalGetDecodeParams(COSName.F_DECODE_PARMS, null);
    }

    private List<Object> internalGetDecodeParams(COSName cOSName, COSName cOSName2) throws IOException {
        COSBase dictionaryObject = this.stream.getDictionaryObject(cOSName, cOSName2);
        if (dictionaryObject instanceof COSDictionary) {
            return new COSArrayList(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) dictionaryObject), dictionaryObject, this.stream, cOSName);
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            ArrayList arrayList = new ArrayList(cOSArray.size());
            for (int i = 0; i < cOSArray.size(); i++) {
                COSBase object = cOSArray.getObject(i);
                if (object instanceof COSDictionary) {
                    arrayList.add(COSDictionaryMap.convertBasicTypesToMap((COSDictionary) object));
                } else {
                    Log.w("PdfBox-Android", "Expected COSDictionary, got " + object + ", ignored");
                }
            }
            return new COSArrayList(arrayList, cOSArray);
        }
        return null;
    }

    public void setDecodeParms(List<?> list) {
        this.stream.setItem(COSName.DECODE_PARMS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(this.stream.getDictionaryObject(COSName.F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        this.stream.setItem(COSName.F, pDFileSpecification);
    }

    public List<String> getFileFilters() {
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.F_FILTER);
        if (dictionaryObject instanceof COSName) {
            COSName cOSName = (COSName) dictionaryObject;
            return new COSArrayList(cOSName.getName(), cOSName, this.stream, COSName.F_FILTER);
        } else if (dictionaryObject instanceof COSArray) {
            return COSArrayList.convertCOSNameCOSArrayToList((COSArray) dictionaryObject);
        } else {
            return null;
        }
    }

    public void setFileFilters(List<String> list) {
        this.stream.setItem(COSName.F_FILTER, (COSBase) COSArrayList.convertStringListToCOSNameCOSArray(list));
    }

    public void setFileDecodeParams(List<?> list) {
        this.stream.setItem(COSName.F_DECODE_PARMS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public byte[] toByteArray() throws IOException {
        COSInputStream cOSInputStream;
        try {
            cOSInputStream = createInputStream();
            try {
                byte[] byteArray = IOUtils.toByteArray(cOSInputStream);
                if (cOSInputStream != null) {
                    cOSInputStream.close();
                }
                return byteArray;
            } catch (Throwable th) {
                th = th;
                if (cOSInputStream != null) {
                    cOSInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cOSInputStream = null;
        }
    }

    public PDMetadata getMetadata() {
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.METADATA);
        if (dictionaryObject instanceof COSStream) {
            return new PDMetadata((COSStream) dictionaryObject);
        }
        if ((dictionaryObject instanceof COSNull) || dictionaryObject == null) {
            return null;
        }
        throw new IllegalStateException("Expected a COSStream but was a " + dictionaryObject.getClass().getSimpleName());
    }

    public void setMetadata(PDMetadata pDMetadata) {
        this.stream.setItem(COSName.METADATA, pDMetadata);
    }

    public int getDecodedStreamLength() {
        return this.stream.getInt(COSName.DL);
    }

    public void setDecodedStreamLength(int i) {
        this.stream.setInt(COSName.DL, i);
    }
}
