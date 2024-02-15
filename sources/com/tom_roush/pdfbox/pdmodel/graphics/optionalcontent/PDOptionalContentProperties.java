package com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class PDOptionalContentProperties implements COSObjectable {
    private final COSDictionary dict;

    /* loaded from: classes3.dex */
    public enum BaseState {
        ON(COSName.ON),
        OFF(COSName.OFF),
        UNCHANGED(COSName.UNCHANGED);
        
        private final COSName name;

        BaseState(COSName cOSName) {
            this.name = cOSName;
        }

        public COSName getName() {
            return this.name;
        }

        public static BaseState valueOf(COSName cOSName) {
            if (cOSName == null) {
                return ON;
            }
            return valueOf(cOSName.getName().toUpperCase());
        }
    }

    public PDOptionalContentProperties() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dict = cOSDictionary;
        cOSDictionary.setItem(COSName.OCGS, (COSBase) new COSArray());
        COSDictionary cOSDictionary2 = new COSDictionary();
        cOSDictionary2.setString(COSName.NAME, "Top");
        cOSDictionary.setItem(COSName.D, (COSBase) cOSDictionary2);
    }

    public PDOptionalContentProperties(COSDictionary cOSDictionary) {
        this.dict = cOSDictionary;
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dict;
    }

    private COSArray getOCGs() {
        COSArray cOSArray = this.dict.getCOSArray(COSName.OCGS);
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            this.dict.setItem(COSName.OCGS, (COSBase) cOSArray2);
            return cOSArray2;
        }
        return cOSArray;
    }

    private COSDictionary getD() {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.D);
        if (dictionaryObject instanceof COSDictionary) {
            return (COSDictionary) dictionaryObject;
        }
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setString(COSName.NAME, "Top");
        this.dict.setItem(COSName.D, (COSBase) cOSDictionary);
        return cOSDictionary;
    }

    public PDOptionalContentGroup getGroup(String str) {
        Iterator<COSBase> it = getOCGs().iterator();
        while (it.hasNext()) {
            COSDictionary dictionary = toDictionary(it.next());
            if (dictionary.getString(COSName.NAME).equals(str)) {
                return new PDOptionalContentGroup(dictionary);
            }
        }
        return null;
    }

    public void addGroup(PDOptionalContentGroup pDOptionalContentGroup) {
        getOCGs().add((COSBase) pDOptionalContentGroup.getCOSObject());
        COSArray cOSArray = (COSArray) getD().getDictionaryObject(COSName.ORDER);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            getD().setItem(COSName.ORDER, (COSBase) cOSArray);
        }
        cOSArray.add(pDOptionalContentGroup);
    }

    public Collection<PDOptionalContentGroup> getOptionalContentGroups() {
        ArrayList arrayList = new ArrayList();
        Iterator<COSBase> it = getOCGs().iterator();
        while (it.hasNext()) {
            arrayList.add(new PDOptionalContentGroup(toDictionary(it.next())));
        }
        return arrayList;
    }

    public BaseState getBaseState() {
        return BaseState.valueOf((COSName) getD().getItem(COSName.BASE_STATE));
    }

    public void setBaseState(BaseState baseState) {
        getD().setItem(COSName.BASE_STATE, (COSBase) baseState.getName());
    }

    public String[] getGroupNames() {
        COSArray cOSArray = this.dict.getCOSArray(COSName.OCGS);
        if (cOSArray == null) {
            return new String[0];
        }
        int size = cOSArray.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = toDictionary(cOSArray.get(i)).getString(COSName.NAME);
        }
        return strArr;
    }

    public boolean hasGroup(String str) {
        for (String str2 : getGroupNames()) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGroupEnabled(String str) {
        Iterator<COSBase> it = getOCGs().iterator();
        boolean z = false;
        while (it.hasNext()) {
            COSDictionary dictionary = toDictionary(it.next());
            if (str.equals(dictionary.getString(COSName.NAME)) && isGroupEnabled(new PDOptionalContentGroup(dictionary))) {
                z = true;
            }
        }
        return z;
    }

    public boolean isGroupEnabled(PDOptionalContentGroup pDOptionalContentGroup) {
        boolean z = !getBaseState().equals(BaseState.OFF);
        if (pDOptionalContentGroup == null) {
            return z;
        }
        COSDictionary d = getD();
        COSBase dictionaryObject = d.getDictionaryObject(COSName.ON);
        if (dictionaryObject instanceof COSArray) {
            Iterator<COSBase> it = ((COSArray) dictionaryObject).iterator();
            while (it.hasNext()) {
                if (toDictionary(it.next()) == pDOptionalContentGroup.getCOSObject()) {
                    return true;
                }
            }
        }
        COSBase dictionaryObject2 = d.getDictionaryObject(COSName.OFF);
        if (dictionaryObject2 instanceof COSArray) {
            Iterator<COSBase> it2 = ((COSArray) dictionaryObject2).iterator();
            while (it2.hasNext()) {
                if (toDictionary(it2.next()) == pDOptionalContentGroup.getCOSObject()) {
                    return false;
                }
            }
        }
        return z;
    }

    private COSDictionary toDictionary(COSBase cOSBase) {
        if (cOSBase instanceof COSObject) {
            return (COSDictionary) ((COSObject) cOSBase).getObject();
        }
        return (COSDictionary) cOSBase;
    }

    public boolean setGroupEnabled(String str, boolean z) {
        Iterator<COSBase> it = getOCGs().iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            COSDictionary dictionary = toDictionary(it.next());
            if (str.equals(dictionary.getString(COSName.NAME)) && setGroupEnabled(new PDOptionalContentGroup(dictionary), z)) {
                z2 = true;
            }
        }
        return z2;
    }

    public boolean setGroupEnabled(PDOptionalContentGroup pDOptionalContentGroup, boolean z) {
        COSArray cOSArray;
        COSArray cOSArray2;
        COSDictionary d = getD();
        COSBase dictionaryObject = d.getDictionaryObject(COSName.ON);
        if (!(dictionaryObject instanceof COSArray)) {
            cOSArray = new COSArray();
            d.setItem(COSName.ON, (COSBase) cOSArray);
        } else {
            cOSArray = (COSArray) dictionaryObject;
        }
        COSBase dictionaryObject2 = d.getDictionaryObject(COSName.OFF);
        if (!(dictionaryObject2 instanceof COSArray)) {
            cOSArray2 = new COSArray();
            d.setItem(COSName.OFF, (COSBase) cOSArray2);
        } else {
            cOSArray2 = (COSArray) dictionaryObject2;
        }
        boolean z2 = true;
        if (z) {
            Iterator<COSBase> it = cOSArray2.iterator();
            while (it.hasNext()) {
                COSBase next = it.next();
                if (toDictionary(next) == pDOptionalContentGroup.getCOSObject()) {
                    cOSArray2.remove(next);
                    cOSArray.add(next);
                    break;
                }
            }
            z2 = false;
        } else {
            Iterator<COSBase> it2 = cOSArray.iterator();
            while (it2.hasNext()) {
                COSBase next2 = it2.next();
                if (toDictionary(next2) == pDOptionalContentGroup.getCOSObject()) {
                    cOSArray.remove(next2);
                    cOSArray2.add(next2);
                    break;
                }
            }
            z2 = false;
        }
        if (!z2) {
            if (z) {
                cOSArray.add((COSBase) pDOptionalContentGroup.getCOSObject());
            } else {
                cOSArray2.add((COSBase) pDOptionalContentGroup.getCOSObject());
            }
        }
        return z2;
    }
}
