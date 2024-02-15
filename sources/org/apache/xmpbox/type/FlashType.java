package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;

@StructuredType(namespace = "http://ns.adobe.com/exif/1.0/", preferedPrefix = "exif")
/* loaded from: classes2.dex */
public class FlashType extends AbstractStructuredType {
    @PropertyType(type = Types.Boolean)
    public static final String FIRED = "Fired";
    @PropertyType(type = Types.Boolean)
    public static final String FUNCTION = "Function";
    @PropertyType(type = Types.Integer)
    public static final String MODE = "Mode";
    @PropertyType(type = Types.Boolean)
    public static final String RED_EYE_MODE = "RedEyeMode";
    @PropertyType(type = Types.Integer)
    public static final String RETURN = "Return";

    public FlashType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }
}
