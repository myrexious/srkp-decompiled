package com.tom_roush.pdfbox.cos;

import com.tom_roush.pdfbox.filter.DecodeOptions;
import com.tom_roush.pdfbox.filter.DecodeResult;
import com.tom_roush.pdfbox.filter.Filter;
import com.tom_roush.pdfbox.io.RandomAccess;
import com.tom_roush.pdfbox.io.RandomAccessInputStream;
import com.tom_roush.pdfbox.io.RandomAccessOutputStream;
import com.tom_roush.pdfbox.io.ScratchFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes3.dex */
public final class COSInputStream extends FilterInputStream {
    private final List<DecodeResult> decodeResults;

    static COSInputStream create(List<Filter> list, COSDictionary cOSDictionary, InputStream inputStream, ScratchFile scratchFile) throws IOException {
        return create(list, cOSDictionary, inputStream, scratchFile, DecodeOptions.DEFAULT);
    }

    public static COSInputStream create(List<Filter> list, COSDictionary cOSDictionary, InputStream inputStream, ScratchFile scratchFile, DecodeOptions decodeOptions) throws IOException {
        InputStream byteArrayInputStream;
        if (list.isEmpty()) {
            return new COSInputStream(inputStream, Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList(list.size());
        if (list.size() > 1 && new HashSet(list).size() != list.size()) {
            throw new IOException("Duplicate");
        }
        InputStream inputStream2 = inputStream;
        for (int i = 0; i < list.size(); i++) {
            if (scratchFile != null) {
                final RandomAccess createBuffer = scratchFile.createBuffer();
                arrayList.add(list.get(i).decode(inputStream2, new RandomAccessOutputStream(createBuffer), cOSDictionary, i, decodeOptions));
                byteArrayInputStream = new RandomAccessInputStream(createBuffer) { // from class: com.tom_roush.pdfbox.cos.COSInputStream.1
                    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() throws IOException {
                        createBuffer.close();
                    }
                };
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                arrayList.add(list.get(i).decode(inputStream2, byteArrayOutputStream, cOSDictionary, i, decodeOptions));
                byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
            inputStream2 = byteArrayInputStream;
        }
        return new COSInputStream(inputStream2, arrayList);
    }

    private COSInputStream(InputStream inputStream, List<DecodeResult> list) {
        super(inputStream);
        this.decodeResults = list;
    }

    public DecodeResult getDecodeResult() {
        if (this.decodeResults.isEmpty()) {
            return DecodeResult.DEFAULT;
        }
        List<DecodeResult> list = this.decodeResults;
        return list.get(list.size() - 1);
    }
}
