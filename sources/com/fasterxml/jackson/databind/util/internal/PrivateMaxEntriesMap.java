package com.fasterxml.jackson.databind.util.internal;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public final class PrivateMaxEntriesMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    static final long MAXIMUM_CAPACITY = 9223372034707292160L;
    static final int NCPU;
    static final int NUMBER_OF_READ_BUFFERS;
    static final int READ_BUFFERS_MASK;
    static final int READ_BUFFER_DRAIN_THRESHOLD = 8;
    static final int READ_BUFFER_INDEX_MASK = 15;
    static final int READ_BUFFER_SIZE = 16;
    static final int READ_BUFFER_THRESHOLD = 4;
    static final int WRITE_BUFFER_DRAIN_THRESHOLD = 16;
    static final long serialVersionUID = 1;
    final AtomicLong capacity;
    final int concurrencyLevel;
    final ConcurrentMap<K, Node<K, V>> data;
    final AtomicReference<DrainStatus> drainStatus;
    transient Set<Map.Entry<K, V>> entrySet;
    final LinkedDeque<Node<K, V>> evictionDeque;
    final Lock evictionLock;
    transient Set<K> keySet;
    final AtomicLongArray readBufferDrainAtWriteCount;
    final long[] readBufferReadCount;
    final AtomicLongArray readBufferWriteCount;
    final AtomicReferenceArray<Node<K, V>> readBuffers;
    transient Collection<V> values;
    final AtomicLong weightedSize;
    final Queue<Runnable> writeBuffer;

    /* loaded from: classes.dex */
    public enum DrainStatus {
        IDLE { // from class: com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.DrainStatus.1
            @Override // com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.DrainStatus
            boolean shouldDrainBuffers(boolean z) {
                return !z;
            }
        },
        REQUIRED { // from class: com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.DrainStatus.2
            @Override // com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.DrainStatus
            boolean shouldDrainBuffers(boolean z) {
                return true;
            }
        },
        PROCESSING { // from class: com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.DrainStatus.3
            @Override // com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap.DrainStatus
            boolean shouldDrainBuffers(boolean z) {
                return false;
            }
        };

        abstract boolean shouldDrainBuffers(boolean z);
    }

    private static int readBufferIndex(int i, int i2) {
        return (i * 16) + i2;
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        NCPU = availableProcessors;
        int min = Math.min(4, ceilingNextPowerOfTwo(availableProcessors));
        NUMBER_OF_READ_BUFFERS = min;
        READ_BUFFERS_MASK = min - 1;
    }

    static int ceilingNextPowerOfTwo(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }

    PrivateMaxEntriesMap(Builder<K, V> builder) {
        int i = builder.concurrencyLevel;
        this.concurrencyLevel = i;
        this.capacity = new AtomicLong(Math.min(builder.capacity, (long) MAXIMUM_CAPACITY));
        this.data = new ConcurrentHashMap(builder.initialCapacity, 0.75f, i);
        this.evictionLock = new ReentrantLock();
        this.weightedSize = new AtomicLong();
        this.evictionDeque = new LinkedDeque<>();
        this.writeBuffer = new ConcurrentLinkedQueue();
        this.drainStatus = new AtomicReference<>(DrainStatus.IDLE);
        int i2 = NUMBER_OF_READ_BUFFERS;
        this.readBufferReadCount = new long[i2];
        this.readBufferWriteCount = new AtomicLongArray(i2);
        this.readBufferDrainAtWriteCount = new AtomicLongArray(i2);
        this.readBuffers = new AtomicReferenceArray<>(i2 * 16);
    }

    static void checkNotNull(Object obj) {
        obj.getClass();
    }

    static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public long capacity() {
        return this.capacity.get();
    }

    public void setCapacity(long j) {
        checkArgument(j >= 0);
        this.evictionLock.lock();
        try {
            this.capacity.lazySet(Math.min(j, (long) MAXIMUM_CAPACITY));
            drainBuffers();
            evict();
        } finally {
            this.evictionLock.unlock();
        }
    }

    boolean hasOverflowed() {
        return this.weightedSize.get() > this.capacity.get();
    }

    void evict() {
        Node<K, V> poll;
        while (hasOverflowed() && (poll = this.evictionDeque.poll()) != null) {
            this.data.remove(poll.key, poll);
            makeDead(poll);
        }
    }

    void afterRead(Node<K, V> node) {
        int readBufferIndex = readBufferIndex();
        drainOnReadIfNeeded(readBufferIndex, recordRead(readBufferIndex, node));
    }

    static int readBufferIndex() {
        return ((int) Thread.currentThread().getId()) & READ_BUFFERS_MASK;
    }

    long recordRead(int i, Node<K, V> node) {
        long j = this.readBufferWriteCount.get(i);
        this.readBufferWriteCount.lazySet(i, serialVersionUID + j);
        this.readBuffers.lazySet(readBufferIndex(i, (int) (15 & j)), node);
        return j;
    }

    void drainOnReadIfNeeded(int i, long j) {
        if (this.drainStatus.get().shouldDrainBuffers(j - this.readBufferDrainAtWriteCount.get(i) < 4)) {
            tryToDrainBuffers();
        }
    }

    void afterWrite(Runnable runnable) {
        this.writeBuffer.add(runnable);
        this.drainStatus.lazySet(DrainStatus.REQUIRED);
        tryToDrainBuffers();
    }

    void tryToDrainBuffers() {
        if (this.evictionLock.tryLock()) {
            try {
                this.drainStatus.lazySet(DrainStatus.PROCESSING);
                drainBuffers();
            } finally {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.drainStatus, DrainStatus.PROCESSING, DrainStatus.IDLE);
                this.evictionLock.unlock();
            }
        }
    }

    void drainBuffers() {
        drainReadBuffers();
        drainWriteBuffer();
    }

    void drainReadBuffers() {
        int id2 = (int) Thread.currentThread().getId();
        int i = NUMBER_OF_READ_BUFFERS + id2;
        while (id2 < i) {
            drainReadBuffer(READ_BUFFERS_MASK & id2);
            id2++;
        }
    }

    void drainReadBuffer(int i) {
        int readBufferIndex;
        Node<K, V> node;
        long j = this.readBufferWriteCount.get(i);
        for (int i2 = 0; i2 < 8 && (node = this.readBuffers.get((readBufferIndex = readBufferIndex(i, (int) (this.readBufferReadCount[i] & 15))))) != null; i2++) {
            this.readBuffers.lazySet(readBufferIndex, null);
            applyRead(node);
            long[] jArr = this.readBufferReadCount;
            jArr[i] = jArr[i] + serialVersionUID;
        }
        this.readBufferDrainAtWriteCount.lazySet(i, j);
    }

    void applyRead(Node<K, V> node) {
        if (this.evictionDeque.contains((Linked<?>) node)) {
            this.evictionDeque.moveToBack(node);
        }
    }

    void drainWriteBuffer() {
        Runnable poll;
        for (int i = 0; i < 16 && (poll = this.writeBuffer.poll()) != null; i++) {
            poll.run();
        }
    }

    boolean tryToRetire(Node<K, V> node, WeightedValue<V> weightedValue) {
        if (weightedValue.isAlive()) {
            return node.compareAndSet(weightedValue, new WeightedValue(weightedValue.value, -weightedValue.weight));
        }
        return false;
    }

    void makeRetired(Node<K, V> node) {
        WeightedValue weightedValue;
        do {
            weightedValue = (WeightedValue) node.get();
            if (!weightedValue.isAlive()) {
                return;
            }
        } while (!node.compareAndSet(weightedValue, new WeightedValue(weightedValue.value, -weightedValue.weight)));
    }

    void makeDead(Node<K, V> node) {
        WeightedValue weightedValue;
        do {
            weightedValue = (WeightedValue) node.get();
        } while (!node.compareAndSet(weightedValue, new WeightedValue(weightedValue.value, 0)));
        AtomicLong atomicLong = this.weightedSize;
        atomicLong.lazySet(atomicLong.get() - Math.abs(weightedValue.weight));
    }

    /* loaded from: classes.dex */
    public final class AddTask implements Runnable {
        final Node<K, V> node;
        final int weight;

        AddTask(Node<K, V> node, int i) {
            PrivateMaxEntriesMap.this = r1;
            this.weight = i;
            this.node = node;
        }

        @Override // java.lang.Runnable
        public void run() {
            PrivateMaxEntriesMap.this.weightedSize.lazySet(PrivateMaxEntriesMap.this.weightedSize.get() + this.weight);
            if (((WeightedValue) this.node.get()).isAlive()) {
                PrivateMaxEntriesMap.this.evictionDeque.add((LinkedDeque<Node<K, V>>) this.node);
                PrivateMaxEntriesMap.this.evict();
            }
        }
    }

    /* loaded from: classes.dex */
    public final class RemovalTask implements Runnable {
        final Node<K, V> node;

        RemovalTask(Node<K, V> node) {
            PrivateMaxEntriesMap.this = r1;
            this.node = node;
        }

        @Override // java.lang.Runnable
        public void run() {
            PrivateMaxEntriesMap.this.evictionDeque.remove((LinkedDeque<Node<K, V>>) this.node);
            PrivateMaxEntriesMap.this.makeDead(this.node);
        }
    }

    /* loaded from: classes.dex */
    public final class UpdateTask implements Runnable {
        final Node<K, V> node;
        final int weightDifference;

        UpdateTask(Node<K, V> node, int i) {
            PrivateMaxEntriesMap.this = r1;
            this.weightDifference = i;
            this.node = node;
        }

        @Override // java.lang.Runnable
        public void run() {
            PrivateMaxEntriesMap.this.weightedSize.lazySet(PrivateMaxEntriesMap.this.weightedSize.get() + this.weightDifference);
            PrivateMaxEntriesMap.this.applyRead(this.node);
            PrivateMaxEntriesMap.this.evict();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.data.size();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.evictionLock.lock();
        while (true) {
            try {
                Node<K, V> poll = this.evictionDeque.poll();
                if (poll == null) {
                    break;
                }
                this.data.remove(poll.key, poll);
                makeDead(poll);
            } finally {
                this.evictionLock.unlock();
            }
        }
        for (int i = 0; i < this.readBuffers.length(); i++) {
            this.readBuffers.lazySet(i, null);
        }
        while (true) {
            Runnable poll2 = this.writeBuffer.poll();
            if (poll2 == null) {
                return;
            }
            poll2.run();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return this.data.containsKey(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        checkNotNull(obj);
        for (Node<K, V> node : this.data.values()) {
            if (node.getValue().equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Node<K, V> node = this.data.get(obj);
        if (node == null) {
            return null;
        }
        afterRead(node);
        return node.getValue();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        return put(k, v, false);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K k, V v) {
        return put(k, v, true);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$WeightedValue] */
    V put(K k, V v, boolean z) {
        checkNotNull(k);
        checkNotNull(v);
        WeightedValue weightedValue = new WeightedValue(v, 1);
        Node<K, V> node = new Node<>(k, weightedValue);
        while (true) {
            Node<K, V> putIfAbsent = this.data.putIfAbsent(node.key, node);
            if (putIfAbsent == null) {
                afterWrite(new AddTask(node, 1));
                return null;
            } else if (z) {
                afterRead(putIfAbsent);
                return putIfAbsent.getValue();
            } else {
                while (true) {
                    ?? r2 = (V) ((WeightedValue) putIfAbsent.get());
                    if (!r2.isAlive()) {
                        break;
                    } else if (putIfAbsent.compareAndSet(r2, weightedValue)) {
                        int i = 1 - r2.weight;
                        if (i == 0) {
                            afterRead(putIfAbsent);
                        } else {
                            afterWrite(new UpdateTask(putIfAbsent, i));
                        }
                        return r2.value;
                    }
                }
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        Node<K, V> remove = this.data.remove(obj);
        if (remove == null) {
            return null;
        }
        makeRetired(remove);
        afterWrite(new RemovalTask(remove));
        return remove.getValue();
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object obj, Object obj2) {
        Node<K, V> node = this.data.get(obj);
        if (node != null && obj2 != null) {
            WeightedValue<V> weightedValue = (WeightedValue) node.get();
            while (true) {
                if (!weightedValue.contains(obj2)) {
                    break;
                } else if (tryToRetire(node, weightedValue)) {
                    if (this.data.remove(obj, node)) {
                        afterWrite(new RemovalTask(node));
                        return true;
                    }
                } else {
                    weightedValue = (WeightedValue) node.get();
                    if (!weightedValue.isAlive()) {
                        break;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$WeightedValue] */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K k, V v) {
        ?? r2;
        checkNotNull(k);
        checkNotNull(v);
        WeightedValue weightedValue = new WeightedValue(v, 1);
        Node<K, V> node = this.data.get(k);
        if (node == null) {
            return null;
        }
        do {
            r2 = (V) ((WeightedValue) node.get());
            if (!r2.isAlive()) {
                return null;
            }
        } while (!node.compareAndSet(r2, weightedValue));
        int i = 1 - r2.weight;
        if (i == 0) {
            afterRead(node);
        } else {
            afterWrite(new UpdateTask(node, i));
        }
        return r2.value;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, com.fasterxml.jackson.databind.util.internal.PrivateMaxEntriesMap$WeightedValue] */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K k, V v, V v2) {
        ?? r2;
        checkNotNull(k);
        checkNotNull(v);
        checkNotNull(v2);
        WeightedValue weightedValue = new WeightedValue(v2, 1);
        Node<K, V> node = this.data.get(k);
        if (node == null) {
            return false;
        }
        do {
            r2 = (V) ((WeightedValue) node.get());
            if (!r2.isAlive() || !r2.contains(v)) {
                return false;
            }
        } while (!node.compareAndSet(r2, weightedValue));
        int i = 1 - r2.weight;
        if (i == 0) {
            afterRead(node);
        } else {
            afterWrite(new UpdateTask(node, i));
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set == null) {
            KeySet keySet = new KeySet();
            this.keySet = keySet;
            return keySet;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection == null) {
            Values values = new Values();
            this.values = values;
            return values;
        }
        return collection;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set == null) {
            EntrySet entrySet = new EntrySet();
            this.entrySet = entrySet;
            return entrySet;
        }
        return set;
    }

    /* loaded from: classes.dex */
    public static final class WeightedValue<V> {
        final V value;
        final int weight;

        WeightedValue(V v, int i) {
            this.weight = i;
            this.value = v;
        }

        boolean contains(Object obj) {
            V v = this.value;
            return obj == v || v.equals(obj);
        }

        boolean isAlive() {
            return this.weight > 0;
        }
    }

    /* loaded from: classes.dex */
    public static final class Node<K, V> extends AtomicReference<WeightedValue<V>> implements Linked<Node<K, V>> {
        final K key;
        Node<K, V> next;
        Node<K, V> prev;

        @Override // com.fasterxml.jackson.databind.util.internal.Linked
        public /* bridge */ /* synthetic */ void setNext(Linked linked) {
            setNext((Node) ((Node) linked));
        }

        @Override // com.fasterxml.jackson.databind.util.internal.Linked
        public /* bridge */ /* synthetic */ void setPrevious(Linked linked) {
            setPrevious((Node) ((Node) linked));
        }

        Node(K k, WeightedValue<V> weightedValue) {
            super(weightedValue);
            this.key = k;
        }

        @Override // com.fasterxml.jackson.databind.util.internal.Linked
        public Node<K, V> getPrevious() {
            return this.prev;
        }

        public void setPrevious(Node<K, V> node) {
            this.prev = node;
        }

        @Override // com.fasterxml.jackson.databind.util.internal.Linked
        public Node<K, V> getNext() {
            return this.next;
        }

        public void setNext(Node<K, V> node) {
            this.next = node;
        }

        V getValue() {
            return ((WeightedValue) get()).value;
        }
    }

    /* loaded from: classes.dex */
    final class KeySet extends AbstractSet<K> {
        final PrivateMaxEntriesMap<K, V> map;

        KeySet() {
            PrivateMaxEntriesMap.this = r1;
            this.map = r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.map.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return PrivateMaxEntriesMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return this.map.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return this.map.data.keySet().toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) this.map.data.keySet().toArray(tArr);
        }
    }

    /* loaded from: classes.dex */
    final class KeyIterator implements Iterator<K> {
        K current;
        final Iterator<K> iterator;

        KeyIterator() {
            PrivateMaxEntriesMap.this = r1;
            this.iterator = r1.data.keySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public K next() {
            K next = this.iterator.next();
            this.current = next;
            return next;
        }

        @Override // java.util.Iterator
        public void remove() {
            PrivateMaxEntriesMap.checkState(this.current != null);
            PrivateMaxEntriesMap.this.remove(this.current);
            this.current = null;
        }
    }

    /* loaded from: classes.dex */
    final class Values extends AbstractCollection<V> {
        Values() {
            PrivateMaxEntriesMap.this = r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return PrivateMaxEntriesMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            PrivateMaxEntriesMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return PrivateMaxEntriesMap.this.containsValue(obj);
        }
    }

    /* loaded from: classes.dex */
    final class ValueIterator implements Iterator<V> {
        Node<K, V> current;
        final Iterator<Node<K, V>> iterator;

        ValueIterator() {
            PrivateMaxEntriesMap.this = r1;
            this.iterator = r1.data.values().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public V next() {
            Node<K, V> next = this.iterator.next();
            this.current = next;
            return next.getValue();
        }

        @Override // java.util.Iterator
        public void remove() {
            PrivateMaxEntriesMap.checkState(this.current != null);
            PrivateMaxEntriesMap.this.remove(this.current.key);
            this.current = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        final PrivateMaxEntriesMap<K, V> map;

        EntrySet() {
            PrivateMaxEntriesMap.this = r1;
            this.map = r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean add(Object obj) {
            return add((Map.Entry) ((Map.Entry) obj));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.map.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Node<K, V> node = this.map.data.get(entry.getKey());
                return node != null && node.getValue().equals(entry.getValue());
            }
            return false;
        }

        public boolean add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException("ConcurrentLinkedHashMap does not allow add to be called on entrySet()");
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return this.map.remove(entry.getKey(), entry.getValue());
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    final class EntryIterator implements Iterator<Map.Entry<K, V>> {
        Node<K, V> current;
        final Iterator<Node<K, V>> iterator;

        EntryIterator() {
            PrivateMaxEntriesMap.this = r1;
            this.iterator = r1.data.values().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            this.current = this.iterator.next();
            return new WriteThroughEntry(this.current);
        }

        @Override // java.util.Iterator
        public void remove() {
            PrivateMaxEntriesMap.checkState(this.current != null);
            PrivateMaxEntriesMap.this.remove(this.current.key);
            this.current = null;
        }
    }

    /* loaded from: classes.dex */
    public final class WriteThroughEntry extends AbstractMap.SimpleEntry<K, V> {
        static final long serialVersionUID = 1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        WriteThroughEntry(Node<K, V> node) {
            super(node.key, node.getValue());
            PrivateMaxEntriesMap.this = r1;
        }

        @Override // java.util.AbstractMap.SimpleEntry, java.util.Map.Entry
        public V setValue(V v) {
            PrivateMaxEntriesMap.this.put(getKey(), v);
            return (V) super.setValue(v);
        }

        Object writeReplace() {
            return new AbstractMap.SimpleEntry(this);
        }
    }

    Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    /* loaded from: classes.dex */
    static final class SerializationProxy<K, V> implements Serializable {
        static final long serialVersionUID = 1;
        final long capacity;
        final int concurrencyLevel;
        final Map<K, V> data;

        SerializationProxy(PrivateMaxEntriesMap<K, V> privateMaxEntriesMap) {
            this.concurrencyLevel = privateMaxEntriesMap.concurrencyLevel;
            this.data = new HashMap(privateMaxEntriesMap);
            this.capacity = privateMaxEntriesMap.capacity.get();
        }

        Object readResolve() {
            PrivateMaxEntriesMap<K, V> build = new Builder().maximumCapacity(this.capacity).build();
            build.putAll((Map<K, V>) this.data);
            return build;
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder<K, V> {
        static final int DEFAULT_CONCURRENCY_LEVEL = 16;
        static final int DEFAULT_INITIAL_CAPACITY = 16;
        long capacity = -1;
        int initialCapacity = 16;
        int concurrencyLevel = 16;

        public Builder<K, V> initialCapacity(int i) {
            PrivateMaxEntriesMap.checkArgument(i >= 0);
            this.initialCapacity = i;
            return this;
        }

        public Builder<K, V> maximumCapacity(long j) {
            PrivateMaxEntriesMap.checkArgument(j >= 0);
            this.capacity = j;
            return this;
        }

        public Builder<K, V> concurrencyLevel(int i) {
            PrivateMaxEntriesMap.checkArgument(i > 0);
            this.concurrencyLevel = i;
            return this;
        }

        public PrivateMaxEntriesMap<K, V> build() {
            PrivateMaxEntriesMap.checkState(this.capacity >= 0);
            return new PrivateMaxEntriesMap<>(this);
        }
    }
}
