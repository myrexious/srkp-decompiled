package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

@StructuredType(namespace = "http://ns.adobe.com/exif/1.0/", preferedPrefix = "exif")
/* loaded from: classes2.dex */
public class OECFType extends AbstractStructuredType {
    @PropertyType(type = Types.Integer)
    public static final String COLUMNS = "Columns";
    @PropertyType(card = Cardinality.Seq, type = Types.Text)
    public static final String NAMES = "Names";
    @PropertyType(type = Types.Integer)
    public static final String ROWS = "Rows";
    @PropertyType(card = Cardinality.Seq, type = Types.Real)
    public static final String VALUES = "Values";

    public OECFType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }
}
