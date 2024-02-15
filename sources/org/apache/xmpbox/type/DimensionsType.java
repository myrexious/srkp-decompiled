package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/sType/Dimensions#", preferedPrefix = "stDim")
/* loaded from: classes2.dex */
public class DimensionsType extends AbstractStructuredType {
    @PropertyType(type = Types.Real)
    public static final String H = "h";
    @PropertyType(type = Types.Text)
    public static final String UNIT = "unit";
    @PropertyType(type = Types.Real)
    public static final String W = "w";

    public DimensionsType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }
}
