package org.apache.commons.imaging.formats.jpeg.iptc;

import java.util.Comparator;
import java.util.function.ToIntFunction;

/* loaded from: classes2.dex */
public class IptcRecord {
    public static final Comparator<IptcRecord> COMPARATOR = Comparator.comparingInt(new ToIntFunction() { // from class: org.apache.commons.imaging.formats.jpeg.iptc.IptcRecord$$ExternalSyntheticLambda0
        @Override // java.util.function.ToIntFunction
        public final int applyAsInt(Object obj) {
            int type;
            type = ((IptcRecord) obj).iptcType.getType();
            return type;
        }
    });
    public final IptcType iptcType;
    private final String value;

    public IptcRecord(IptcType iptcType, String str) {
        this.iptcType = iptcType;
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public String getIptcTypeName() {
        return this.iptcType.getName();
    }
}
