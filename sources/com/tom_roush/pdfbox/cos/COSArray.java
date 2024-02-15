package com.tom_roush.pdfbox.cos;

import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.text.StringSubstitutor;

/* loaded from: classes3.dex */
public class COSArray extends COSBase implements Iterable<COSBase>, COSUpdateInfo {
    private boolean needToBeUpdated;
    private final List<COSBase> objects = new ArrayList();

    public void add(COSBase cOSBase) {
        this.objects.add(cOSBase);
    }

    public void add(COSObjectable cOSObjectable) {
        this.objects.add(cOSObjectable.getCOSObject());
    }

    public void add(int i, COSBase cOSBase) {
        this.objects.add(i, cOSBase);
    }

    public void clear() {
        this.objects.clear();
    }

    public void removeAll(Collection<COSBase> collection) {
        this.objects.removeAll(collection);
    }

    public void retainAll(Collection<COSBase> collection) {
        this.objects.retainAll(collection);
    }

    public void addAll(Collection<COSBase> collection) {
        this.objects.addAll(collection);
    }

    public void addAll(COSArray cOSArray) {
        if (cOSArray != null) {
            this.objects.addAll(cOSArray.objects);
        }
    }

    public void addAll(int i, Collection<COSBase> collection) {
        this.objects.addAll(i, collection);
    }

    public void set(int i, COSBase cOSBase) {
        this.objects.set(i, cOSBase);
    }

    public void set(int i, int i2) {
        this.objects.set(i, COSInteger.get(i2));
    }

    public void set(int i, COSObjectable cOSObjectable) {
        this.objects.set(i, cOSObjectable != null ? cOSObjectable.getCOSObject() : null);
    }

    public COSBase getObject(int i) {
        COSBase cOSBase = this.objects.get(i);
        if (cOSBase instanceof COSObject) {
            cOSBase = ((COSObject) cOSBase).getObject();
        }
        if (cOSBase instanceof COSNull) {
            return null;
        }
        return cOSBase;
    }

    public COSBase get(int i) {
        return this.objects.get(i);
    }

    public int getInt(int i) {
        return getInt(i, -1);
    }

    public int getInt(int i, int i2) {
        if (i < size()) {
            COSBase cOSBase = this.objects.get(i);
            return cOSBase instanceof COSNumber ? ((COSNumber) cOSBase).intValue() : i2;
        }
        return i2;
    }

    public void setInt(int i, int i2) {
        set(i, (COSBase) COSInteger.get(i2));
    }

    public void setName(int i, String str) {
        set(i, (COSBase) COSName.getPDFName(str));
    }

    public String getName(int i) {
        return getName(i, null);
    }

    public String getName(int i, String str) {
        if (i < size()) {
            COSBase cOSBase = this.objects.get(i);
            return cOSBase instanceof COSName ? ((COSName) cOSBase).getName() : str;
        }
        return str;
    }

    public void setString(int i, String str) {
        if (str != null) {
            set(i, (COSBase) new COSString(str));
        } else {
            set(i, (COSBase) null);
        }
    }

    public String getString(int i) {
        return getString(i, null);
    }

    public String getString(int i, String str) {
        if (i < size()) {
            COSBase cOSBase = this.objects.get(i);
            return cOSBase instanceof COSString ? ((COSString) cOSBase).getString() : str;
        }
        return str;
    }

    public int size() {
        return this.objects.size();
    }

    public COSBase remove(int i) {
        return this.objects.remove(i);
    }

    public boolean remove(COSBase cOSBase) {
        return this.objects.remove(cOSBase);
    }

    public boolean removeObject(COSBase cOSBase) {
        boolean remove = remove(cOSBase);
        if (!remove) {
            for (int i = 0; i < size(); i++) {
                COSBase cOSBase2 = get(i);
                if ((cOSBase2 instanceof COSObject) && ((COSObject) cOSBase2).getObject().equals(cOSBase)) {
                    return remove(cOSBase2);
                }
            }
        }
        return remove;
    }

    public String toString() {
        return "COSArray{" + this.objects + StringSubstitutor.DEFAULT_VAR_END;
    }

    @Override // java.lang.Iterable
    public Iterator<COSBase> iterator() {
        return this.objects.iterator();
    }

    public int indexOf(COSBase cOSBase) {
        for (int i = 0; i < size(); i++) {
            COSBase cOSBase2 = get(i);
            if (cOSBase2 == null) {
                if (cOSBase == null) {
                    return i;
                }
            } else if (cOSBase2.equals(cOSBase)) {
                return i;
            }
        }
        return -1;
    }

    public int indexOfObject(COSBase cOSBase) {
        for (int i = 0; i < size(); i++) {
            COSBase cOSBase2 = get(i);
            if (cOSBase2 == null) {
                if (cOSBase2 == cOSBase) {
                    return i;
                }
            } else if (cOSBase2.equals(cOSBase) || ((cOSBase2 instanceof COSObject) && ((COSObject) cOSBase2).getObject().equals(cOSBase))) {
                return i;
            }
        }
        return -1;
    }

    public void growToSize(int i) {
        growToSize(i, null);
    }

    public void growToSize(int i, COSBase cOSBase) {
        while (size() < i) {
            add(cOSBase);
        }
    }

    @Override // com.tom_roush.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromArray(this);
    }

    @Override // com.tom_roush.pdfbox.cos.COSUpdateInfo
    public boolean isNeedToBeUpdated() {
        return this.needToBeUpdated;
    }

    @Override // com.tom_roush.pdfbox.cos.COSUpdateInfo
    public void setNeedToBeUpdated(boolean z) {
        this.needToBeUpdated = z;
    }

    public float[] toFloatArray() {
        int size = size();
        float[] fArr = new float[size];
        for (int i = 0; i < size; i++) {
            COSBase object = getObject(i);
            fArr[i] = object instanceof COSNumber ? ((COSNumber) object).floatValue() : 0.0f;
        }
        return fArr;
    }

    public void setFloatArray(float[] fArr) {
        clear();
        for (float f : fArr) {
            add((COSBase) new COSFloat(f));
        }
    }

    public List<? extends COSBase> toList() {
        return new ArrayList(this.objects);
    }
}
