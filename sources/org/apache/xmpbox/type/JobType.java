package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/sType/Job#", preferedPrefix = "stJob")
/* loaded from: classes2.dex */
public class JobType extends AbstractStructuredType {
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String ID = "id";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String NAME = "name";
    @PropertyType(card = Cardinality.Simple, type = Types.URL)
    public static final String URL = "url";

    public JobType(XMPMetadata xMPMetadata) {
        this(xMPMetadata, null);
    }

    public JobType(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
        addNamespace(getNamespace(), getPrefix());
    }

    public void setId(String str) {
        addSimpleProperty(ID, str);
    }

    public void setName(String str) {
        addSimpleProperty("name", str);
    }

    public void setUrl(String str) {
        addSimpleProperty("url", str);
    }

    public String getId() {
        return getPropertyValueAsString(ID);
    }

    public String getName() {
        return getPropertyValueAsString("name");
    }

    public String getUrl() {
        return getPropertyValueAsString("url");
    }
}
