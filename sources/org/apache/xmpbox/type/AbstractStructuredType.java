package org.apache.xmpbox.type;

import java.util.Calendar;
import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public abstract class AbstractStructuredType extends AbstractComplexProperty {
    protected static final String STRUCTURE_ARRAY_NAME = "li";
    private String namespace;
    private String preferedPrefix;
    private String prefix;

    public AbstractStructuredType(XMPMetadata xMPMetadata) {
        this(xMPMetadata, null, null, null);
    }

    public AbstractStructuredType(XMPMetadata xMPMetadata, String str) {
        this(xMPMetadata, str, null, null);
        StructuredType structuredType = (StructuredType) getClass().getAnnotation(StructuredType.class);
        if (structuredType != null) {
            this.namespace = structuredType.namespace();
            String preferedPrefix = structuredType.preferedPrefix();
            this.preferedPrefix = preferedPrefix;
            this.prefix = preferedPrefix;
            return;
        }
        throw new IllegalArgumentException(" StructuredType annotation cannot be null");
    }

    public AbstractStructuredType(XMPMetadata xMPMetadata, String str, String str2, String str3) {
        super(xMPMetadata, str3);
        StructuredType structuredType = (StructuredType) getClass().getAnnotation(StructuredType.class);
        if (structuredType != null) {
            this.namespace = structuredType.namespace();
            this.preferedPrefix = structuredType.preferedPrefix();
        } else if (str == null) {
            throw new IllegalArgumentException("Both StructuredType annotation and namespace parameter cannot be null");
        } else {
            this.namespace = str;
            this.preferedPrefix = str2;
        }
        this.prefix = str2 == null ? this.preferedPrefix : str2;
    }

    @Override // org.apache.xmpbox.type.AbstractField
    public final String getNamespace() {
        return this.namespace;
    }

    public final void setNamespace(String str) {
        this.namespace = str;
    }

    @Override // org.apache.xmpbox.type.AbstractField
    public final String getPrefix() {
        return this.prefix;
    }

    public final void setPrefix(String str) {
        this.prefix = str;
    }

    public final String getPreferedPrefix() {
        return this.preferedPrefix;
    }

    public void addSimpleProperty(String str, Object obj) {
        addProperty(getMetadata().getTypeMapping().instanciateSimpleField(getClass(), null, getPrefix(), str, obj));
    }

    public String getPropertyValueAsString(String str) {
        AbstractSimpleProperty abstractSimpleProperty = (AbstractSimpleProperty) getProperty(str);
        if (abstractSimpleProperty == null) {
            return null;
        }
        return abstractSimpleProperty.getStringValue();
    }

    public Calendar getDatePropertyAsCalendar(String str) {
        DateType dateType = (DateType) getFirstEquivalentProperty(str, DateType.class);
        if (dateType != null) {
            return dateType.getValue();
        }
        return null;
    }

    public TextType createTextType(String str, String str2) {
        return getMetadata().getTypeMapping().createText(getNamespace(), getPrefix(), str, str2);
    }

    public ArrayProperty createArrayProperty(String str, Cardinality cardinality) {
        return getMetadata().getTypeMapping().createArrayProperty(getNamespace(), getPrefix(), str, cardinality);
    }
}
