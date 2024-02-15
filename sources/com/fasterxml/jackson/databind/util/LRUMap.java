package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;

/* loaded from: classes.dex */
public class LRUMap<K, V> implements LookupCache<K, V>, Serializable {
    private static final long serialVersionUID = 2;
    protected final int _initialEntries;
    protected final transient PrivateMaxEntriesMap<K, V> _map;
    protected final int _maxEntries;

    public LRUMap(int i, int i2) {
        this._initialEntries = i;
        this._maxEntries = i2;
        this._map = new PrivateMaxEntriesMap.Builder().initialCapacity(i).maximumCapacity(i2).concurrencyLevel(4).build();
    }

    @Override // com.fasterxml.jackson.databind.util.LookupCache
    public V put(K k, V v) {
        return this._map.put(k, v);
    }

    @Override // com.fasterxml.jackson.databind.util.LookupCache
    public V putIfAbsent(K k, V v) {
        return this._map.putIfAbsent(k, v);
    }

    @Override // com.fasterxml.jackson.databind.util.LookupCache
    public V get(Object obj) {
        return this._map.get(obj);
    }

    @Override // com.fasterxml.jackson.databind.util.LookupCache
    public void clear() {
        this._map.clear();
    }

    @Override // com.fasterxml.jackson.databind.util.LookupCache
    public int size() {
        return this._map.size();
    }

    public void contents(BiConsumer<K, V> biConsumer) {
        for (Map.Entry<K, V> entry : this._map.entrySet()) {
            biConsumer.accept(entry.getKey(), entry.getValue());
        }
    }

    protected Object readResolve() {
        return new LRUMap(this._initialEntries, this._maxEntries);
    }
}
