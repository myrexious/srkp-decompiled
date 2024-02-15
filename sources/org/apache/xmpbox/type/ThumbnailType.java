package org.apache.xmpbox.type;

import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.XmpConstants;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/g/img/", preferedPrefix = "xmpGImg")
/* loaded from: classes2.dex */
public class ThumbnailType extends AbstractStructuredType {
    @PropertyType(card = Cardinality.Simple, type = Types.Choice)
    public static final String FORMAT = "format";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String HEIGHT = "height";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String IMAGE = "image";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String WIDTH = "width";

    public ThumbnailType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
        setAttribute(new Attribute(XmpConstants.RDF_NAMESPACE, XmpConstants.PARSE_TYPE, XmpConstants.RESOURCE_NAME));
    }

    public Integer getHeight() {
        AbstractField firstEquivalentProperty = getFirstEquivalentProperty(HEIGHT, IntegerType.class);
        if (firstEquivalentProperty != null) {
            return ((IntegerType) firstEquivalentProperty).getValue();
        }
        return null;
    }

    public void setHeight(Integer num) {
        addSimpleProperty(HEIGHT, num);
    }

    public Integer getWidth() {
        AbstractField firstEquivalentProperty = getFirstEquivalentProperty(WIDTH, IntegerType.class);
        if (firstEquivalentProperty != null) {
            return ((IntegerType) firstEquivalentProperty).getValue();
        }
        return null;
    }

    public void setWidth(Integer num) {
        addSimpleProperty(WIDTH, num);
    }

    public String getImage() {
        AbstractField firstEquivalentProperty = getFirstEquivalentProperty(IMAGE, TextType.class);
        if (firstEquivalentProperty != null) {
            return ((TextType) firstEquivalentProperty).getStringValue();
        }
        return null;
    }

    public void setImage(String str) {
        addSimpleProperty(IMAGE, str);
    }

    public String getFormat() {
        AbstractField firstEquivalentProperty = getFirstEquivalentProperty("format", ChoiceType.class);
        if (firstEquivalentProperty != null) {
            return ((TextType) firstEquivalentProperty).getStringValue();
        }
        return null;
    }

    public void setFormat(String str) {
        addSimpleProperty("format", str);
    }
}
