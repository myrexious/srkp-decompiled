package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class PDStructureNode implements COSObjectable {
    private final COSDictionary dictionary;

    public PDStructureNode(String str) {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, str);
    }

    public PDStructureNode(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    public static PDStructureNode create(COSDictionary cOSDictionary) {
        String nameAsString = cOSDictionary.getNameAsString(COSName.TYPE);
        if ("StructTreeRoot".equals(nameAsString)) {
            return new PDStructureTreeRoot(cOSDictionary);
        }
        if (nameAsString == null || PDStructureElement.TYPE.equals(nameAsString)) {
            return new PDStructureElement(cOSDictionary);
        }
        throw new IllegalArgumentException("Dictionary must not include a Type entry with a value that is neither StructTreeRoot nor StructElem.");
    }

    @Override // com.tom_roush.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public String getType() {
        return getCOSObject().getNameAsString(COSName.TYPE);
    }

    public List<Object> getKids() {
        ArrayList arrayList = new ArrayList();
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.K);
        if (dictionaryObject instanceof COSArray) {
            Iterator<COSBase> it = ((COSArray) dictionaryObject).iterator();
            while (it.hasNext()) {
                Object createObject = createObject(it.next());
                if (createObject != null) {
                    arrayList.add(createObject);
                }
            }
        } else {
            Object createObject2 = createObject(dictionaryObject);
            if (createObject2 != null) {
                arrayList.add(createObject2);
            }
        }
        return arrayList;
    }

    public void setKids(List<Object> list) {
        getCOSObject().setItem(COSName.K, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public void appendKid(PDStructureElement pDStructureElement) {
        appendObjectableKid(pDStructureElement);
        pDStructureElement.setParent(this);
    }

    public void appendObjectableKid(COSObjectable cOSObjectable) {
        if (cOSObjectable == null) {
            return;
        }
        appendKid(cOSObjectable.getCOSObject());
    }

    public void appendKid(COSBase cOSBase) {
        if (cOSBase == null) {
            return;
        }
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.K);
        if (dictionaryObject == null) {
            getCOSObject().setItem(COSName.K, cOSBase);
        } else if (dictionaryObject instanceof COSArray) {
            ((COSArray) dictionaryObject).add(cOSBase);
        } else {
            COSArray cOSArray = new COSArray();
            cOSArray.add(dictionaryObject);
            cOSArray.add(cOSBase);
            getCOSObject().setItem(COSName.K, (COSBase) cOSArray);
        }
    }

    public void insertBefore(PDStructureElement pDStructureElement, Object obj) {
        insertObjectableBefore(pDStructureElement, obj);
    }

    public void insertObjectableBefore(COSObjectable cOSObjectable, Object obj) {
        if (cOSObjectable == null) {
            return;
        }
        insertBefore(cOSObjectable.getCOSObject(), obj);
    }

    public void insertBefore(COSBase cOSBase, Object obj) {
        COSBase dictionaryObject;
        if (cOSBase == null || obj == null || (dictionaryObject = getCOSObject().getDictionaryObject(COSName.K)) == null) {
            return;
        }
        COSBase cOSObject = obj instanceof COSObjectable ? ((COSObjectable) obj).getCOSObject() : null;
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            cOSArray.add(cOSArray.indexOfObject(cOSObject), cOSBase.getCOSObject());
            return;
        }
        boolean equals = dictionaryObject.equals(cOSObject);
        if (!equals && (dictionaryObject instanceof COSObject)) {
            equals = ((COSObject) dictionaryObject).getObject().equals(cOSObject);
        }
        if (equals) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.add(cOSBase);
            cOSArray2.add(cOSObject);
            getCOSObject().setItem(COSName.K, (COSBase) cOSArray2);
        }
    }

    public boolean removeKid(PDStructureElement pDStructureElement) {
        boolean removeObjectableKid = removeObjectableKid(pDStructureElement);
        if (removeObjectableKid) {
            pDStructureElement.setParent(null);
        }
        return removeObjectableKid;
    }

    public boolean removeObjectableKid(COSObjectable cOSObjectable) {
        if (cOSObjectable == null) {
            return false;
        }
        return removeKid(cOSObjectable.getCOSObject());
    }

    public boolean removeKid(COSBase cOSBase) {
        COSBase dictionaryObject;
        if (cOSBase == null || (dictionaryObject = getCOSObject().getDictionaryObject(COSName.K)) == null) {
            return false;
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            boolean removeObject = cOSArray.removeObject(cOSBase);
            if (cOSArray.size() == 1) {
                getCOSObject().setItem(COSName.K, cOSArray.getObject(0));
            }
            return removeObject;
        }
        boolean equals = dictionaryObject.equals(cOSBase);
        if (!equals && (dictionaryObject instanceof COSObject)) {
            equals = ((COSObject) dictionaryObject).getObject().equals(cOSBase);
        }
        if (equals) {
            getCOSObject().setItem(COSName.K, (COSBase) null);
            return true;
        }
        return false;
    }

    protected Object createObject(COSBase cOSBase) {
        COSDictionary cOSDictionary;
        if (cOSBase instanceof COSDictionary) {
            cOSDictionary = (COSDictionary) cOSBase;
        } else {
            if (cOSBase instanceof COSObject) {
                COSBase object = ((COSObject) cOSBase).getObject();
                if (object instanceof COSDictionary) {
                    cOSDictionary = (COSDictionary) object;
                }
            }
            cOSDictionary = null;
        }
        if (cOSDictionary != null) {
            return createObjectFromDic(cOSDictionary);
        }
        if (cOSBase instanceof COSInteger) {
            return Integer.valueOf(((COSInteger) cOSBase).intValue());
        }
        return null;
    }

    private COSObjectable createObjectFromDic(COSDictionary cOSDictionary) {
        String nameAsString = cOSDictionary.getNameAsString(COSName.TYPE);
        if (nameAsString == null || PDStructureElement.TYPE.equals(nameAsString)) {
            return new PDStructureElement(cOSDictionary);
        }
        if (PDObjectReference.TYPE.equals(nameAsString)) {
            return new PDObjectReference(cOSDictionary);
        }
        if (PDMarkedContentReference.TYPE.equals(nameAsString)) {
            return new PDMarkedContentReference(cOSDictionary);
        }
        return null;
    }
}
