package org.apache.commons.imaging.formats.jpeg.iptc;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class IptcTypeLookup {
    private static final Map<Integer, IptcType> IPTC_TYPE_MAP = new HashMap();

    static {
        IptcTypes[] values;
        for (IptcTypes iptcTypes : IptcTypes.values()) {
            IPTC_TYPE_MAP.put(Integer.valueOf(iptcTypes.getType()), iptcTypes);
        }
    }

    private IptcTypeLookup() {
    }

    public static IptcType getIptcType(int i) {
        Map<Integer, IptcType> map = IPTC_TYPE_MAP;
        if (!map.containsKey(Integer.valueOf(i))) {
            return IptcTypes.getUnknown(i);
        }
        return map.get(Integer.valueOf(i));
    }
}
