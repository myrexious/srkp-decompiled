package com.tom_roush.pdfbox.cos;

import android.util.Log;
import com.tom_roush.pdfbox.android.PDFBoxConfig;
import com.tom_roush.pdfbox.filter.DecodeOptions;
import com.tom_roush.pdfbox.filter.Filter;
import com.tom_roush.pdfbox.filter.FilterFactory;
import com.tom_roush.pdfbox.io.IOUtils;
import com.tom_roush.pdfbox.io.RandomAccess;
import com.tom_roush.pdfbox.io.RandomAccessInputStream;
import com.tom_roush.pdfbox.io.RandomAccessOutputStream;
import com.tom_roush.pdfbox.io.ScratchFile;
import java.io.Closeable;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class COSStream extends COSDictionary implements Closeable {
    private boolean isWriting;
    private RandomAccess randomAccess;
    private final ScratchFile scratchFile;

    public COSStream() {
        this(ScratchFile.getMainMemoryOnlyInstance());
    }

    public COSStream(ScratchFile scratchFile) {
        setInt(COSName.LENGTH, 0);
        this.scratchFile = scratchFile == null ? ScratchFile.getMainMemoryOnlyInstance() : scratchFile;
    }

    private void checkClosed() throws IOException {
        RandomAccess randomAccess = this.randomAccess;
        if (randomAccess != null && randomAccess.isClosed()) {
            throw new IOException("COSStream has been closed and cannot be read. Perhaps its enclosing PDDocument has been closed?");
        }
    }

    @Deprecated
    public InputStream getFilteredStream() throws IOException {
        return createRawInputStream();
    }

    private void ensureRandomAccessExists(boolean z) throws IOException {
        if (this.randomAccess == null) {
            if (z && PDFBoxConfig.isDebugEnabled()) {
                Log.d("PdfBox-Android", "Create InputStream called without data being written before to stream.");
            }
            this.randomAccess = this.scratchFile.createBuffer();
        }
    }

    public InputStream createRawInputStream() throws IOException {
        checkClosed();
        if (this.isWriting) {
            throw new IllegalStateException("Cannot read while there is an open stream writer");
        }
        ensureRandomAccessExists(true);
        return new RandomAccessInputStream(this.randomAccess);
    }

    @Deprecated
    public InputStream getUnfilteredStream() throws IOException {
        return createInputStream();
    }

    public COSInputStream createInputStream() throws IOException {
        return createInputStream(DecodeOptions.DEFAULT);
    }

    public COSInputStream createInputStream(DecodeOptions decodeOptions) throws IOException {
        checkClosed();
        if (this.isWriting) {
            throw new IllegalStateException("Cannot read while there is an open stream writer");
        }
        ensureRandomAccessExists(true);
        return COSInputStream.create(getFilterList(), this, new RandomAccessInputStream(this.randomAccess), this.scratchFile, decodeOptions);
    }

    @Deprecated
    public OutputStream createUnfilteredStream() throws IOException {
        return createOutputStream();
    }

    public OutputStream createOutputStream() throws IOException {
        return createOutputStream(null);
    }

    public OutputStream createOutputStream(COSBase cOSBase) throws IOException {
        checkClosed();
        if (this.isWriting) {
            throw new IllegalStateException("Cannot have more than one open stream writer.");
        }
        if (cOSBase != null) {
            setItem(COSName.FILTER, cOSBase);
        }
        IOUtils.closeQuietly(this.randomAccess);
        this.randomAccess = this.scratchFile.createBuffer();
        COSOutputStream cOSOutputStream = new COSOutputStream(getFilterList(), this, new RandomAccessOutputStream(this.randomAccess), this.scratchFile);
        this.isWriting = true;
        return new FilterOutputStream(cOSOutputStream) { // from class: com.tom_roush.pdfbox.cos.COSStream.1
            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                this.out.write(bArr, i, i2);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                super.close();
                COSStream.this.setInt(COSName.LENGTH, (int) COSStream.this.randomAccess.length());
                COSStream.this.isWriting = false;
            }
        };
    }

    @Deprecated
    public OutputStream createFilteredStream() throws IOException {
        return createRawOutputStream();
    }

    public OutputStream createRawOutputStream() throws IOException {
        checkClosed();
        if (this.isWriting) {
            throw new IllegalStateException("Cannot have more than one open stream writer.");
        }
        IOUtils.closeQuietly(this.randomAccess);
        this.randomAccess = this.scratchFile.createBuffer();
        RandomAccessOutputStream randomAccessOutputStream = new RandomAccessOutputStream(this.randomAccess);
        this.isWriting = true;
        return new FilterOutputStream(randomAccessOutputStream) { // from class: com.tom_roush.pdfbox.cos.COSStream.2
            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                this.out.write(bArr, i, i2);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                super.close();
                COSStream.this.setInt(COSName.LENGTH, (int) COSStream.this.randomAccess.length());
                COSStream.this.isWriting = false;
            }
        };
    }

    private List<Filter> getFilterList() throws IOException {
        COSBase filters = getFilters();
        if (filters instanceof COSName) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(FilterFactory.INSTANCE.getFilter((COSName) filters));
            return arrayList;
        } else if (filters instanceof COSArray) {
            COSArray cOSArray = (COSArray) filters;
            ArrayList arrayList2 = new ArrayList(cOSArray.size());
            for (int i = 0; i < cOSArray.size(); i++) {
                COSBase cOSBase = cOSArray.get(i);
                if (!(cOSBase instanceof COSName)) {
                    throw new IOException("Forbidden type in filter array: " + (cOSBase == null ? "null" : cOSBase.getClass().getName()));
                }
                arrayList2.add(FilterFactory.INSTANCE.getFilter((COSName) cOSBase));
            }
            return arrayList2;
        } else {
            return new ArrayList();
        }
    }

    public long getLength() {
        if (this.isWriting) {
            throw new IllegalStateException("There is an open OutputStream associated with this COSStream. It must be closed before querying the length of this COSStream.");
        }
        return getInt(COSName.LENGTH, 0);
    }

    public COSBase getFilters() {
        return getDictionaryObject(COSName.FILTER);
    }

    @Deprecated
    public void setFilters(COSBase cOSBase) throws IOException {
        setItem(COSName.FILTER, cOSBase);
    }

    @Deprecated
    public String getString() {
        return toTextString();
    }

    public String toTextString() {
        COSInputStream cOSInputStream = null;
        try {
            try {
                cOSInputStream = createInputStream();
                byte[] byteArray = IOUtils.toByteArray(cOSInputStream);
                IOUtils.closeQuietly(cOSInputStream);
                return new COSString(byteArray).getString();
            } catch (IOException e) {
                Log.d("PdfBox-Android", "An exception occurred trying to get the content - returning empty string instead", e);
                IOUtils.closeQuietly(cOSInputStream);
                return "";
            }
        } catch (Throwable th) {
            IOUtils.closeQuietly(cOSInputStream);
            throw th;
        }
    }

    @Override // com.tom_roush.pdfbox.cos.COSDictionary, com.tom_roush.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromStream(this);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        RandomAccess randomAccess = this.randomAccess;
        if (randomAccess != null) {
            randomAccess.close();
        }
    }
}
