package org.apache.commons.imaging.formats.jpeg.iptc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class PhotoshopApp13Data {
    private final List<IptcBlock> rawBlocks;
    private final List<IptcRecord> records;

    public PhotoshopApp13Data(List<IptcRecord> list, List<IptcBlock> list2) {
        this.rawBlocks = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.records = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    public List<IptcRecord> getRecords() {
        return new ArrayList(this.records);
    }

    public List<IptcBlock> getRawBlocks() {
        return new ArrayList(this.rawBlocks);
    }

    public List<IptcBlock> getNonIptcBlocks() {
        ArrayList arrayList = new ArrayList();
        for (IptcBlock iptcBlock : this.rawBlocks) {
            if (!iptcBlock.isIPTCBlock()) {
                arrayList.add(iptcBlock);
            }
        }
        return arrayList;
    }
}
