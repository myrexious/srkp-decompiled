package com.tom_roush.fontbox.ttf;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class VerticalOriginTable extends TTFTable {
    public static final String TAG = "VORG";
    private int defaultVertOriginY;
    private Map<Integer, Integer> origins;
    private float version;

    public VerticalOriginTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
    }

    @Override // com.tom_roush.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.version = tTFDataStream.read32Fixed();
        this.defaultVertOriginY = tTFDataStream.readSignedShort();
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        this.origins = new ConcurrentHashMap(readUnsignedShort);
        for (int i = 0; i < readUnsignedShort; i++) {
            this.origins.put(Integer.valueOf(tTFDataStream.readUnsignedShort()), Integer.valueOf(tTFDataStream.readSignedShort()));
        }
        this.initialized = true;
    }

    public float getVersion() {
        return this.version;
    }

    public int getOriginY(int i) {
        if (this.origins.containsKey(Integer.valueOf(i))) {
            return this.origins.get(Integer.valueOf(i)).intValue();
        }
        return this.defaultVertOriginY;
    }
}
