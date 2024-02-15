package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PDUserAttributeObject extends PDAttributeObject {
    public static final String OWNER_USER_PROPERTIES = "UserProperties";

    public void userPropertyChanged(PDUserProperty pDUserProperty) {
    }

    public PDUserAttributeObject() {
        setOwner(OWNER_USER_PROPERTIES);
    }

    public PDUserAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public List<PDUserProperty> getOwnerUserProperties() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.P);
        ArrayList arrayList = new ArrayList(cOSArray.size());
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(new PDUserProperty((COSDictionary) cOSArray.getObject(i), this));
        }
        return arrayList;
    }

    public void setUserProperties(List<PDUserProperty> list) {
        COSArray cOSArray = new COSArray();
        for (PDUserProperty pDUserProperty : list) {
            cOSArray.add(pDUserProperty);
        }
        getCOSObject().setItem(COSName.P, (COSBase) cOSArray);
    }

    public void addUserProperty(PDUserProperty pDUserProperty) {
        ((COSArray) getCOSObject().getDictionaryObject(COSName.P)).add(pDUserProperty);
        notifyChanged();
    }

    public void removeUserProperty(PDUserProperty pDUserProperty) {
        if (pDUserProperty == null) {
            return;
        }
        ((COSArray) getCOSObject().getDictionaryObject(COSName.P)).remove(pDUserProperty.getCOSObject());
        notifyChanged();
    }

    @Override // com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject
    public String toString() {
        return super.toString() + ", userProperties=" + getOwnerUserProperties();
    }
}
