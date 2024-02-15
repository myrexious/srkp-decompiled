package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.XmpConstants;

@StructuredType(namespace = "http://ns.adobe.com/photoshop/1.0/", preferedPrefix = "photoshop")
/* loaded from: classes2.dex */
public class LayerType extends AbstractStructuredType {
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String LAYER_NAME = "LayerName";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String LAYER_TEXT = "LayerText";

    public LayerType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
        setAttribute(new Attribute(XmpConstants.RDF_NAMESPACE, XmpConstants.PARSE_TYPE, XmpConstants.RESOURCE_NAME));
    }

    public String getLayerName() {
        AbstractField firstEquivalentProperty = getFirstEquivalentProperty(LAYER_NAME, TextType.class);
        if (firstEquivalentProperty != null) {
            return ((TextType) firstEquivalentProperty).getStringValue();
        }
        return null;
    }

    public void setLayerName(String str) {
        addProperty(createTextType(LAYER_NAME, str));
    }

    public String getLayerText() {
        AbstractField firstEquivalentProperty = getFirstEquivalentProperty(LAYER_TEXT, TextType.class);
        if (firstEquivalentProperty != null) {
            return ((TextType) firstEquivalentProperty).getStringValue();
        }
        return null;
    }

    public void setLayerText(String str) {
        addProperty(createTextType(LAYER_TEXT, str));
    }
}
