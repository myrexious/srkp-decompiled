package com.tom_roush.pdfbox.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class SmallMap<K, V> implements Map<K, V> {
    private Object[] mapArr;

    public SmallMap() {
    }

    public SmallMap(Map<? extends K, ? extends V> map) {
        putAll(map);
    }

    private int findKey(Object obj) {
        if (!isEmpty() && obj != null) {
            int i = 0;
            while (true) {
                Object[] objArr = this.mapArr;
                if (i >= objArr.length) {
                    break;
                } else if (obj.equals(objArr[i])) {
                    return i;
                } else {
                    i += 2;
                }
            }
        }
        return -1;
    }

    private int findValue(Object obj) {
        if (!isEmpty() && obj != null) {
            int i = 1;
            while (true) {
                Object[] objArr = this.mapArr;
                if (i >= objArr.length) {
                    break;
                } else if (obj.equals(objArr[i])) {
                    return i;
                } else {
                    i += 2;
                }
            }
        }
        return -1;
    }

    @Override // java.util.Map
    public int size() {
        Object[] objArr = this.mapArr;
        if (objArr == null) {
            return 0;
        }
        return objArr.length >> 1;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        Object[] objArr = this.mapArr;
        return objArr == null || objArr.length == 0;
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return findKey(obj) >= 0;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return findValue(obj) >= 0;
    }

    @Override // java.util.Map
    public V get(Object obj) {
        int findKey = findKey(obj);
        if (findKey < 0) {
            return null;
        }
        return (V) this.mapArr[findKey + 1];
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("Key or value must not be null.");
        }
        if (this.mapArr == null) {
            this.mapArr = new Object[]{k, v};
            return null;
        }
        int findKey = findKey(k);
        if (findKey < 0) {
            Object[] objArr = this.mapArr;
            int length = objArr.length;
            Object[] objArr2 = new Object[length + 2];
            System.arraycopy(objArr, 0, objArr2, 0, length);
            objArr2[length] = k;
            objArr2[length + 1] = v;
            this.mapArr = objArr2;
            return null;
        }
        Object[] objArr3 = this.mapArr;
        int i = findKey + 1;
        V v2 = (V) objArr3[i];
        objArr3[i] = v;
        return v2;
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        int findKey = findKey(obj);
        if (findKey < 0) {
            return null;
        }
        Object[] objArr = this.mapArr;
        V v = (V) objArr[findKey + 1];
        int length = objArr.length;
        if (length == 2) {
            this.mapArr = null;
        } else {
            Object[] objArr2 = new Object[length - 2];
            System.arraycopy(objArr, 0, objArr2, 0, findKey);
            System.arraycopy(this.mapArr, findKey + 2, objArr2, findKey, (length - findKey) - 2);
            this.mapArr = objArr2;
        }
        return v;
    }

    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        Object[] objArr = this.mapArr;
        int i = 0;
        if (objArr == null || objArr.length == 0) {
            this.mapArr = new Object[map.size() << 1];
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    throw new NullPointerException("Key or value must not be null.");
                }
                int i2 = i + 1;
                this.mapArr[i] = entry.getKey();
                this.mapArr[i2] = entry.getValue();
                i = i2 + 1;
            }
            return;
        }
        int length = objArr.length;
        int size = (map.size() << 1) + length;
        Object[] objArr2 = new Object[size];
        System.arraycopy(this.mapArr, 0, objArr2, 0, length);
        for (Map.Entry<? extends K, ? extends V> entry2 : map.entrySet()) {
            if (entry2.getKey() == null || entry2.getValue() == null) {
                throw new NullPointerException("Key or value must not be null.");
            }
            int findKey = findKey(entry2.getKey());
            if (findKey >= 0) {
                objArr2[findKey + 1] = entry2.getValue();
            } else {
                int i3 = length + 1;
                objArr2[length] = entry2.getKey();
                length = i3 + 1;
                objArr2[i3] = entry2.getValue();
            }
        }
        if (length < size) {
            Object[] objArr3 = new Object[length];
            System.arraycopy(objArr2, 0, objArr3, 0, length);
            objArr2 = objArr3;
        }
        this.mapArr = objArr2;
    }

    @Override // java.util.Map
    public void clear() {
        this.mapArr = null;
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = 0;
        while (true) {
            Object[] objArr = this.mapArr;
            if (i < objArr.length) {
                linkedHashSet.add(objArr[i]);
                i += 2;
            } else {
                return Collections.unmodifiableSet(linkedHashSet);
            }
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        int i = 1;
        ArrayList arrayList = new ArrayList(this.mapArr.length >> 1);
        while (true) {
            Object[] objArr = this.mapArr;
            if (i < objArr.length) {
                arrayList.add(objArr[i]);
                i += 2;
            } else {
                return Collections.unmodifiableList(arrayList);
            }
        }
    }

    /* loaded from: classes3.dex */
    private class SmallMapEntry implements Map.Entry<K, V> {
        private final int keyIdx;

        SmallMapEntry(int i) {
            SmallMap.this = r1;
            this.keyIdx = i;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return (K) SmallMap.this.mapArr[this.keyIdx];
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return (V) SmallMap.this.mapArr[this.keyIdx + 1];
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            if (v == null) {
                throw new NullPointerException("Key or value must not be null.");
            }
            V v2 = (V) getValue();
            SmallMap.this.mapArr[this.keyIdx + 1] = v;
            return v2;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return getKey().hashCode();
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj instanceof SmallMapEntry) {
                SmallMapEntry smallMapEntry = (SmallMapEntry) obj;
                return getKey().equals(smallMapEntry.getKey()) && getValue().equals(smallMapEntry.getValue());
            }
            return false;
        }
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (isEmpty()) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i = 0; i < this.mapArr.length; i += 2) {
            linkedHashSet.add(new SmallMapEntry(i));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }
}
