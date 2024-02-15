package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.TypeKey;
import java.util.function.BiConsumer;

/* loaded from: classes.dex */
public final class ReadOnlyClassToSerializerMap {
    private final Bucket[] _buckets;
    private final int _mask;
    private final int _size;

    private static final int findSize(int i) {
        int i2 = 8;
        while (i2 < (i <= 64 ? i + i : i + (i >> 2))) {
            i2 += i2;
        }
        return i2;
    }

    public ReadOnlyClassToSerializerMap(LRUMap<TypeKey, JsonSerializer<Object>> lRUMap) {
        int findSize = findSize(lRUMap.size());
        this._size = findSize;
        this._mask = findSize - 1;
        final Bucket[] bucketArr = new Bucket[findSize];
        lRUMap.contents(new BiConsumer() { // from class: com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ReadOnlyClassToSerializerMap.this.m121x39dc3eed(bucketArr, (TypeKey) obj, (JsonSerializer) obj2);
            }
        });
        this._buckets = bucketArr;
    }

    /* renamed from: lambda$new$0$com-fasterxml-jackson-databind-ser-impl-ReadOnlyClassToSerializerMap */
    public /* synthetic */ void m121x39dc3eed(Bucket[] bucketArr, TypeKey typeKey, JsonSerializer jsonSerializer) {
        int hashCode = typeKey.hashCode() & this._mask;
        bucketArr[hashCode] = new Bucket(bucketArr[hashCode], typeKey, jsonSerializer);
    }

    public static ReadOnlyClassToSerializerMap from(LRUMap<TypeKey, JsonSerializer<Object>> lRUMap) {
        return new ReadOnlyClassToSerializerMap(lRUMap);
    }

    public int size() {
        return this._size;
    }

    public JsonSerializer<Object> typedValueSerializer(JavaType javaType) {
        Bucket bucket = this._buckets[TypeKey.typedHash(javaType) & this._mask];
        if (bucket == null) {
            return null;
        }
        if (bucket.matchesTyped(javaType)) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return null;
            }
        } while (!bucket.matchesTyped(javaType));
        return bucket.value;
    }

    public JsonSerializer<Object> typedValueSerializer(Class<?> cls) {
        Bucket bucket = this._buckets[TypeKey.typedHash(cls) & this._mask];
        if (bucket == null) {
            return null;
        }
        if (bucket.matchesTyped(cls)) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return null;
            }
        } while (!bucket.matchesTyped(cls));
        return bucket.value;
    }

    public JsonSerializer<Object> untypedValueSerializer(JavaType javaType) {
        Bucket bucket = this._buckets[TypeKey.untypedHash(javaType) & this._mask];
        if (bucket == null) {
            return null;
        }
        if (bucket.matchesUntyped(javaType)) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return null;
            }
        } while (!bucket.matchesUntyped(javaType));
        return bucket.value;
    }

    public JsonSerializer<Object> untypedValueSerializer(Class<?> cls) {
        Bucket bucket = this._buckets[TypeKey.untypedHash(cls) & this._mask];
        if (bucket == null) {
            return null;
        }
        if (bucket.matchesUntyped(cls)) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return null;
            }
        } while (!bucket.matchesUntyped(cls));
        return bucket.value;
    }

    /* loaded from: classes.dex */
    public static final class Bucket {
        protected final Class<?> _class;
        protected final boolean _isTyped;
        protected final JavaType _type;
        public final Bucket next;
        public final JsonSerializer<Object> value;

        public Bucket(Bucket bucket, TypeKey typeKey, JsonSerializer<Object> jsonSerializer) {
            this.next = bucket;
            this.value = jsonSerializer;
            this._isTyped = typeKey.isTyped();
            this._class = typeKey.getRawType();
            this._type = typeKey.getType();
        }

        public boolean matchesTyped(Class<?> cls) {
            return this._class == cls && this._isTyped;
        }

        public boolean matchesUntyped(Class<?> cls) {
            return this._class == cls && !this._isTyped;
        }

        public boolean matchesTyped(JavaType javaType) {
            return this._isTyped && javaType.equals(this._type);
        }

        public boolean matchesUntyped(JavaType javaType) {
            return !this._isTyped && javaType.equals(this._type);
        }
    }
}