package org.apache.commons.text.lookup;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/* loaded from: classes2.dex */
public class InterpolatorStringLookup extends AbstractStringLookup {
    static final AbstractStringLookup INSTANCE = new InterpolatorStringLookup();
    private static final char PREFIX_SEPARATOR = ':';
    private final StringLookup defaultStringLookup;
    private final Map<String, StringLookup> stringLookupMap;

    InterpolatorStringLookup() {
        this((Map) null);
    }

    public InterpolatorStringLookup(Map<String, StringLookup> map, StringLookup stringLookup, boolean z) {
        this.defaultStringLookup = stringLookup;
        Map<String, StringLookup> map2 = (Map) map.entrySet().stream().collect(Collectors.toMap(new Function() { // from class: org.apache.commons.text.lookup.InterpolatorStringLookup$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String key;
                key = StringLookupFactory.toKey((String) ((Map.Entry) obj).getKey());
                return key;
            }
        }, new Function() { // from class: org.apache.commons.text.lookup.InterpolatorStringLookup$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (StringLookup) ((Map.Entry) obj).getValue();
            }
        }));
        this.stringLookupMap = map2;
        if (z) {
            StringLookupFactory.INSTANCE.addDefaultStringLookups(map2);
        }
    }

    public <V> InterpolatorStringLookup(Map<String, V> map) {
        this(StringLookupFactory.INSTANCE.mapStringLookup(map));
    }

    public InterpolatorStringLookup(StringLookup stringLookup) {
        this(Collections.emptyMap(), stringLookup, true);
    }

    public Map<String, StringLookup> getStringLookupMap() {
        return this.stringLookupMap;
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(58);
        if (indexOf >= 0) {
            String key = StringLookupFactory.toKey(str.substring(0, indexOf));
            int i = indexOf + 1;
            String substring = str.substring(i);
            StringLookup stringLookup = this.stringLookupMap.get(key);
            String lookup = stringLookup != null ? stringLookup.lookup(substring) : null;
            if (lookup != null) {
                return lookup;
            }
            str = str.substring(i);
        }
        StringLookup stringLookup2 = this.defaultStringLookup;
        if (stringLookup2 != null) {
            return stringLookup2.lookup(str);
        }
        return null;
    }

    public String toString() {
        return super.toString() + " [stringLookupMap=" + this.stringLookupMap + ", defaultStringLookup=" + this.defaultStringLookup + "]";
    }
}
