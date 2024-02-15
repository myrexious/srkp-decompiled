package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSString;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public final class FieldUtils {

    /* loaded from: classes3.dex */
    public static class KeyValue {
        private final String key;
        private final String value;

        KeyValue(String str, String str2) {
            this.key = str;
            this.value = str2;
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return "(" + this.key + ", " + this.value + ")";
        }
    }

    /* loaded from: classes3.dex */
    static class KeyValueKeyComparator implements Serializable, Comparator<KeyValue> {
        private static final long serialVersionUID = 6715364290007167694L;

        KeyValueKeyComparator() {
        }

        @Override // java.util.Comparator
        public int compare(KeyValue keyValue, KeyValue keyValue2) {
            return keyValue.key.compareTo(keyValue2.key);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class KeyValueValueComparator implements Serializable, Comparator<KeyValue> {
        private static final long serialVersionUID = -3984095679894798265L;

        KeyValueValueComparator() {
        }

        @Override // java.util.Comparator
        public int compare(KeyValue keyValue, KeyValue keyValue2) {
            return keyValue.value.compareTo(keyValue2.value);
        }
    }

    private FieldUtils() {
    }

    public static List<KeyValue> toKeyValueList(List<String> list, List<String> list2) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new KeyValue(list.get(i), list2.get(i)));
        }
        return arrayList;
    }

    public static void sortByValue(List<KeyValue> list) {
        Collections.sort(list, new KeyValueValueComparator());
    }

    static void sortByKey(List<KeyValue> list) {
        Collections.sort(list, new KeyValueKeyComparator());
    }

    public static List<String> getPairableItems(COSBase cOSBase, int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Only 0 and 1 are allowed as an index into two-element arrays");
        }
        if (cOSBase instanceof COSString) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(((COSString) cOSBase).getString());
            return arrayList;
        } else if (cOSBase instanceof COSArray) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<COSBase> it = ((COSArray) cOSBase).iterator();
            while (it.hasNext()) {
                COSBase next = it.next();
                if (next instanceof COSString) {
                    arrayList2.add(((COSString) next).getString());
                } else if (next instanceof COSArray) {
                    COSArray cOSArray = (COSArray) next;
                    if (cOSArray.size() >= i + 1 && (cOSArray.get(i) instanceof COSString)) {
                        arrayList2.add(((COSString) cOSArray.get(i)).getString());
                    }
                }
            }
            return arrayList2;
        } else {
            return Collections.emptyList();
        }
    }
}
