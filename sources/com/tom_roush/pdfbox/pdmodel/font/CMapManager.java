package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.cmap.CMap;
import com.tom_roush.fontbox.cmap.CMapParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: CmapManager.java */
/* loaded from: classes3.dex */
public final class CMapManager {
    private static final Map<String, CMap> CMAP_CACHE = new ConcurrentHashMap();

    private CMapManager() {
    }

    public static CMap getPredefinedCMap(String str) throws IOException {
        Map<String, CMap> map = CMAP_CACHE;
        CMap cMap = map.get(str);
        if (cMap != null) {
            return cMap;
        }
        CMap parsePredefined = new CMapParser().parsePredefined(str);
        map.put(parsePredefined.getName(), parsePredefined);
        return parsePredefined;
    }

    public static CMap parseCMap(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            return new CMapParser(true).parse(inputStream);
        }
        return null;
    }
}
