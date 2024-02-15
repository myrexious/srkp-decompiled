package org.bouncycastle.tsp.ers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes2.dex */
public class SortedIndexedHashList {
    private static final Comparator<byte[]> hashComp = new ByteArrayComparator();
    private final LinkedList<IndexedHash> baseList = new LinkedList<>();

    public void add(IndexedHash indexedHash) {
        if (this.baseList.size() == 0 || hashComp.compare(indexedHash.digest, this.baseList.get(0).digest) < 0) {
            this.baseList.addFirst(indexedHash);
            return;
        }
        int i = 1;
        while (i < this.baseList.size() && hashComp.compare(this.baseList.get(i).digest, indexedHash.digest) <= 0) {
            i++;
        }
        if (i == this.baseList.size()) {
            this.baseList.add(indexedHash);
        } else {
            this.baseList.add(i, indexedHash);
        }
    }

    public IndexedHash getFirst() {
        return this.baseList.getFirst();
    }

    public int size() {
        return this.baseList.size();
    }

    public List<IndexedHash> toList() {
        return new ArrayList(this.baseList);
    }
}
