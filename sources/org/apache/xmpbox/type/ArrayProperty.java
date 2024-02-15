package org.apache.xmpbox.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public class ArrayProperty extends AbstractComplexProperty {
    private final Cardinality arrayType;
    private final String namespace;
    private final String prefix;

    public ArrayProperty(XMPMetadata xMPMetadata, String str, String str2, String str3, Cardinality cardinality) {
        super(xMPMetadata, str3);
        this.arrayType = cardinality;
        this.namespace = str;
        this.prefix = str2;
    }

    public Cardinality getArrayType() {
        return this.arrayType;
    }

    public List<String> getElementsAsString() {
        List<AbstractField> allProperties = getContainer().getAllProperties();
        ArrayList arrayList = new ArrayList(allProperties.size());
        Iterator<AbstractField> it = allProperties.iterator();
        while (it.hasNext()) {
            arrayList.add(((AbstractSimpleProperty) it.next()).getStringValue());
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // org.apache.xmpbox.type.AbstractField
    public final String getNamespace() {
        return this.namespace;
    }

    @Override // org.apache.xmpbox.type.AbstractField
    public String getPrefix() {
        return this.prefix;
    }
}
