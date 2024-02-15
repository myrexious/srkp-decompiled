package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObjectKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes3.dex */
public class XrefTrailerResolver {
    private final Map<Long, XrefTrailerObj> bytePosToXrefMap = new HashMap();
    private XrefTrailerObj curXrefTrailerObj = null;
    private XrefTrailerObj resolvedXrefTrailer = null;

    /* loaded from: classes3.dex */
    public enum XRefType {
        TABLE,
        STREAM
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class XrefTrailerObj {
        protected COSDictionary trailer;
        private final Map<COSObjectKey, Long> xrefTable;
        private XRefType xrefType;

        private XrefTrailerObj() {
            this.trailer = null;
            this.xrefTable = new HashMap();
            this.xrefType = XRefType.TABLE;
        }

        public void reset() {
            this.xrefTable.clear();
        }
    }

    public final COSDictionary getFirstTrailer() {
        if (this.bytePosToXrefMap.isEmpty()) {
            return null;
        }
        return this.bytePosToXrefMap.get(new TreeSet(this.bytePosToXrefMap.keySet()).first()).trailer;
    }

    public final COSDictionary getLastTrailer() {
        if (this.bytePosToXrefMap.isEmpty()) {
            return null;
        }
        return this.bytePosToXrefMap.get(new TreeSet(this.bytePosToXrefMap.keySet()).last()).trailer;
    }

    public final int getTrailerCount() {
        return this.bytePosToXrefMap.size();
    }

    public void nextXrefObj(long j, XRefType xRefType) {
        this.curXrefTrailerObj = new XrefTrailerObj();
        this.bytePosToXrefMap.put(Long.valueOf(j), this.curXrefTrailerObj);
        this.curXrefTrailerObj.xrefType = xRefType;
    }

    public XRefType getXrefType() {
        XrefTrailerObj xrefTrailerObj = this.resolvedXrefTrailer;
        if (xrefTrailerObj == null) {
            return null;
        }
        return xrefTrailerObj.xrefType;
    }

    public void setXRef(COSObjectKey cOSObjectKey, long j) {
        XrefTrailerObj xrefTrailerObj = this.curXrefTrailerObj;
        if (xrefTrailerObj != null) {
            if (xrefTrailerObj.xrefTable.containsKey(cOSObjectKey)) {
                return;
            }
            this.curXrefTrailerObj.xrefTable.put(cOSObjectKey, Long.valueOf(j));
            return;
        }
        Log.w("PdfBox-Android", "Cannot add XRef entry for '" + cOSObjectKey.getNumber() + "' because XRef start was not signalled.");
    }

    public void setTrailer(COSDictionary cOSDictionary) {
        XrefTrailerObj xrefTrailerObj = this.curXrefTrailerObj;
        if (xrefTrailerObj == null) {
            Log.w("PdfBox-Android", "Cannot add trailer because XRef start was not signalled.");
        } else {
            xrefTrailerObj.trailer = cOSDictionary;
        }
    }

    public COSDictionary getCurrentTrailer() {
        return this.curXrefTrailerObj.trailer;
    }

    public void setStartxref(long j) {
        if (this.resolvedXrefTrailer != null) {
            Log.w("PdfBox-Android", "Method must be called only ones with last startxref value.");
            return;
        }
        XrefTrailerObj xrefTrailerObj = new XrefTrailerObj();
        this.resolvedXrefTrailer = xrefTrailerObj;
        xrefTrailerObj.trailer = new COSDictionary();
        XrefTrailerObj xrefTrailerObj2 = this.bytePosToXrefMap.get(Long.valueOf(j));
        ArrayList<Long> arrayList = new ArrayList();
        if (xrefTrailerObj2 != null) {
            this.resolvedXrefTrailer.xrefType = xrefTrailerObj2.xrefType;
            arrayList.add(Long.valueOf(j));
            while (true) {
                if (xrefTrailerObj2.trailer == null) {
                    break;
                }
                long j2 = xrefTrailerObj2.trailer.getLong(COSName.PREV, -1L);
                if (j2 == -1) {
                    break;
                }
                xrefTrailerObj2 = this.bytePosToXrefMap.get(Long.valueOf(j2));
                if (xrefTrailerObj2 == null) {
                    Log.w("PdfBox-Android", "Did not found XRef object pointed to by 'Prev' key at position " + j2);
                    break;
                }
                arrayList.add(Long.valueOf(j2));
                if (arrayList.size() >= this.bytePosToXrefMap.size()) {
                    break;
                }
            }
            Collections.reverse(arrayList);
        } else {
            Log.w("PdfBox-Android", "Did not found XRef object at specified startxref position " + j);
            arrayList.addAll(this.bytePosToXrefMap.keySet());
            Collections.sort(arrayList);
        }
        for (Long l : arrayList) {
            XrefTrailerObj xrefTrailerObj3 = this.bytePosToXrefMap.get(l);
            if (xrefTrailerObj3.trailer != null) {
                this.resolvedXrefTrailer.trailer.addAll(xrefTrailerObj3.trailer);
            }
            this.resolvedXrefTrailer.xrefTable.putAll(xrefTrailerObj3.xrefTable);
        }
    }

    public COSDictionary getTrailer() {
        XrefTrailerObj xrefTrailerObj = this.resolvedXrefTrailer;
        if (xrefTrailerObj == null) {
            return null;
        }
        return xrefTrailerObj.trailer;
    }

    public Map<COSObjectKey, Long> getXrefTable() {
        XrefTrailerObj xrefTrailerObj = this.resolvedXrefTrailer;
        if (xrefTrailerObj == null) {
            return null;
        }
        return xrefTrailerObj.xrefTable;
    }

    public Set<Long> getContainedObjectNumbers(int i) {
        if (this.resolvedXrefTrailer == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        long j = -i;
        for (Map.Entry entry : this.resolvedXrefTrailer.xrefTable.entrySet()) {
            if (((Long) entry.getValue()).longValue() == j) {
                hashSet.add(Long.valueOf(((COSObjectKey) entry.getKey()).getNumber()));
            }
        }
        return hashSet;
    }

    public void reset() {
        for (XrefTrailerObj xrefTrailerObj : this.bytePosToXrefMap.values()) {
            xrefTrailerObj.reset();
        }
        this.curXrefTrailerObj = null;
        this.resolvedXrefTrailer = null;
    }
}
