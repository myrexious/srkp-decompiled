package com.tom_roush.pdfbox.pdmodel.common;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class PDNameTreeNode<T extends COSObjectable> implements COSObjectable {
    private final COSDictionary node;
    private PDNameTreeNode<T> parent;

    protected abstract T convertCOSToPD(COSBase cOSBase) throws IOException;

    protected abstract PDNameTreeNode<T> createChildNode(COSDictionary cOSDictionary);

    public PDNameTreeNode() {
        this.node = new COSDictionary();
    }

    public PDNameTreeNode(COSDictionary cOSDictionary) {
        this.node = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.node;
    }

    public PDNameTreeNode<T> getParent() {
        return this.parent;
    }

    public void setParent(PDNameTreeNode<T> pDNameTreeNode) {
        this.parent = pDNameTreeNode;
        calculateLimits();
    }

    public boolean isRootNode() {
        return this.parent == null;
    }

    public List<PDNameTreeNode<T>> getKids() {
        COSArray cOSArray = this.node.getCOSArray(COSName.KIDS);
        if (cOSArray != null) {
            ArrayList arrayList = new ArrayList(cOSArray.size());
            for (int i = 0; i < cOSArray.size(); i++) {
                arrayList.add(createChildNode((COSDictionary) cOSArray.getObject(i)));
            }
            return new COSArrayList(arrayList, cOSArray);
        }
        return null;
    }

    public void setKids(List<? extends PDNameTreeNode<T>> list) {
        if (list != null && !list.isEmpty()) {
            for (PDNameTreeNode<T> pDNameTreeNode : list) {
                pDNameTreeNode.setParent(this);
            }
            this.node.setItem(COSName.KIDS, (COSBase) COSArrayList.converterToCOSArray(list));
            if (isRootNode()) {
                this.node.setItem(COSName.NAMES, (COSBase) null);
            }
        } else {
            this.node.setItem(COSName.KIDS, (COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) null);
        }
        calculateLimits();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0070 -> B:45:0x007e). Please submit an issue!!! */
    private void calculateLimits() {
        COSBase cOSBase = null;
        cOSBase = null;
        cOSBase = null;
        if (isRootNode()) {
            this.node.setItem(COSName.LIMITS, (COSBase) null);
            return;
        }
        List<PDNameTreeNode<T>> kids = getKids();
        if (kids != null && !kids.isEmpty()) {
            setLowerLimit(kids.get(0).getLowerLimit());
            setUpperLimit(kids.get(kids.size() - 1).getUpperLimit());
            return;
        }
        try {
            Map<String, T> names = getNames();
            if (names != null && names.size() > 0) {
                Set<String> keySet = names.keySet();
                String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
                setLowerLimit(strArr[0]);
                setUpperLimit(strArr[strArr.length - 1]);
            } else {
                this.node.setItem(COSName.LIMITS, (COSBase) null);
            }
        } catch (IOException e) {
            this.node.setItem(COSName.LIMITS, cOSBase);
            Log.e("PdfBox-Android", "Error while calculating the Limits of a PageNameTreeNode:", e);
            cOSBase = "PdfBox-Android";
        }
    }

    public T getValue(String str) throws IOException {
        Map<String, T> names = getNames();
        if (names != null) {
            return names.get(str);
        }
        List<PDNameTreeNode<T>> kids = getKids();
        if (kids != null) {
            for (int i = 0; i < kids.size(); i++) {
                PDNameTreeNode<T> pDNameTreeNode = kids.get(i);
                String upperLimit = pDNameTreeNode.getUpperLimit();
                String lowerLimit = pDNameTreeNode.getLowerLimit();
                if (upperLimit == null || lowerLimit == null || upperLimit.compareTo(lowerLimit) < 0 || (lowerLimit.compareTo(str) <= 0 && upperLimit.compareTo(str) >= 0)) {
                    return pDNameTreeNode.getValue(str);
                }
            }
            return null;
        }
        Log.w("PdfBox-Android", "NameTreeNode does not have \"names\" nor \"kids\" objects.");
        return null;
    }

    public Map<String, T> getNames() throws IOException {
        COSArray cOSArray = this.node.getCOSArray(COSName.NAMES);
        if (cOSArray == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cOSArray.size() % 2 != 0) {
            Log.w("PdfBox-Android", "Names array has odd size: " + cOSArray.size());
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i2 < cOSArray.size()) {
                COSBase object = cOSArray.getObject(i);
                if (!(object instanceof COSString)) {
                    throw new IOException("Expected string, found " + object + " in name tree at index " + i);
                }
                linkedHashMap.put(((COSString) object).getString(), convertCOSToPD(cOSArray.getObject(i2)));
                i += 2;
            } else {
                return Collections.unmodifiableMap(linkedHashMap);
            }
        }
    }

    public void setNames(Map<String, T> map) {
        if (map == null) {
            this.node.setItem(COSName.NAMES, (COSObjectable) null);
            this.node.setItem(COSName.LIMITS, (COSObjectable) null);
            return;
        }
        COSArray cOSArray = new COSArray();
        ArrayList<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            cOSArray.add((COSBase) new COSString(str));
            cOSArray.add(map.get(str));
        }
        this.node.setItem(COSName.NAMES, (COSBase) cOSArray);
        calculateLimits();
    }

    public String getUpperLimit() {
        COSArray cOSArray = this.node.getCOSArray(COSName.LIMITS);
        if (cOSArray != null) {
            return cOSArray.getString(1);
        }
        return null;
    }

    private void setUpperLimit(String str) {
        COSArray cOSArray = this.node.getCOSArray(COSName.LIMITS);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) null);
            cOSArray.add((COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) cOSArray);
        }
        cOSArray.setString(1, str);
    }

    public String getLowerLimit() {
        COSArray cOSArray = this.node.getCOSArray(COSName.LIMITS);
        if (cOSArray != null) {
            return cOSArray.getString(0);
        }
        return null;
    }

    private void setLowerLimit(String str) {
        COSArray cOSArray = this.node.getCOSArray(COSName.LIMITS);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) null);
            cOSArray.add((COSBase) null);
            this.node.setItem(COSName.LIMITS, (COSBase) cOSArray);
        }
        cOSArray.setString(0, str);
    }
}
