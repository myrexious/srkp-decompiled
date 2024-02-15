package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.android.PDFBoxConfig;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes3.dex */
public class PDFObjectStreamParser extends BaseParser {
    private final int firstObject;
    private final int numberOfObjects;
    private List<COSObject> streamObjects;

    public PDFObjectStreamParser(COSStream cOSStream, COSDocument cOSDocument) throws IOException {
        super(new InputStreamSource(cOSStream.createInputStream()));
        this.streamObjects = null;
        this.document = cOSDocument;
        int i = cOSStream.getInt(COSName.N);
        this.numberOfObjects = i;
        if (i == -1) {
            throw new IOException("/N entry missing in object stream");
        }
        if (i < 0) {
            throw new IOException("Illegal /N entry in object stream: " + i);
        }
        int i2 = cOSStream.getInt(COSName.FIRST);
        this.firstObject = i2;
        if (i2 == -1) {
            throw new IOException("/First entry missing in object stream");
        }
        if (i2 < 0) {
            throw new IOException("Illegal /First entry in object stream: " + i2);
        }
    }

    public void parse() throws IOException {
        try {
            Map<Integer, Long> readOffsets = readOffsets();
            this.streamObjects = new ArrayList(readOffsets.size());
            for (Map.Entry<Integer, Long> entry : readOffsets.entrySet()) {
                COSObject cOSObject = new COSObject(parseObject(entry.getKey().intValue()));
                cOSObject.setGenerationNumber(0);
                cOSObject.setObjectNumber(entry.getValue().longValue());
                this.streamObjects.add(cOSObject);
                if (PDFBoxConfig.isDebugEnabled()) {
                    Log.d("PdfBox-Android", "parsed=" + cOSObject);
                }
            }
        } finally {
            this.seqSource.close();
        }
    }

    public List<COSObject> getObjects() {
        return this.streamObjects;
    }

    private Map<Integer, Long> readOffsets() throws IOException {
        TreeMap treeMap = new TreeMap();
        long position = (this.seqSource.getPosition() + this.firstObject) - 1;
        for (int i = 0; i < this.numberOfObjects && this.seqSource.getPosition() < position; i++) {
            treeMap.put(Integer.valueOf((int) readLong()), Long.valueOf(readObjectNumber()));
        }
        return treeMap;
    }

    private COSBase parseObject(int i) throws IOException {
        long position = this.seqSource.getPosition();
        int i2 = this.firstObject + i;
        if (i2 > 0 && position < i2) {
            this.seqSource.readFully(i2 - ((int) position));
        }
        return parseDirObject();
    }
}
