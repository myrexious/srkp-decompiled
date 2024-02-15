package org.apache.xmpbox.schema;

import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.Types;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/t/pg/", preferedPrefix = "xmpTPg")
/* loaded from: classes2.dex */
public class XMPageTextSchema extends XMPSchema {
    @PropertyType(type = Types.Dimensions)
    public static final String MAX_PAGE_SIZE = "MaxPageSize";
    @PropertyType(type = Types.Integer)
    public static final String N_PAGES = "NPages";

    public XMPageTextSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public XMPageTextSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }
}
