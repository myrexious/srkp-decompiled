package org.bouncycastle.tsp.ers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.operator.DigestCalculator;

/* loaded from: classes2.dex */
public class ERSDataGroup extends ERSCachingData {
    protected List<ERSData> dataObjects;

    public ERSDataGroup(List<ERSData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        this.dataObjects = arrayList;
        arrayList.addAll(list);
    }

    public ERSDataGroup(ERSData eRSData) {
        this.dataObjects = Collections.singletonList(eRSData);
    }

    public ERSDataGroup(ERSData... eRSDataArr) {
        ArrayList arrayList = new ArrayList(eRSDataArr.length);
        this.dataObjects = arrayList;
        arrayList.addAll(Arrays.asList(eRSDataArr));
    }

    @Override // org.bouncycastle.tsp.ers.ERSCachingData
    protected byte[] calculateHash(DigestCalculator digestCalculator, byte[] bArr) {
        List<byte[]> hashes = getHashes(digestCalculator, bArr);
        if (hashes.size() > 1) {
            ArrayList arrayList = new ArrayList(hashes.size());
            for (int i = 0; i != arrayList.size(); i++) {
                arrayList.add(hashes.get(i));
            }
            return ERSUtil.calculateDigest(digestCalculator, arrayList.iterator());
        }
        return hashes.get(0);
    }

    @Override // org.bouncycastle.tsp.ers.ERSCachingData, org.bouncycastle.tsp.ers.ERSData
    public byte[] getHash(DigestCalculator digestCalculator, byte[] bArr) {
        List<byte[]> hashes = getHashes(digestCalculator, bArr);
        return hashes.size() > 1 ? ERSUtil.calculateDigest(digestCalculator, hashes.iterator()) : hashes.get(0);
    }

    public List<byte[]> getHashes(DigestCalculator digestCalculator, byte[] bArr) {
        return ERSUtil.buildHashList(digestCalculator, this.dataObjects, bArr);
    }

    public int size() {
        return this.dataObjects.size();
    }
}
