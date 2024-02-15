package org.apache.xmpbox.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmpbox.XmpConstants;
import org.apache.xmpbox.xml.XmpParsingException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/* loaded from: classes2.dex */
public final class DomHelper {
    private DomHelper() {
    }

    public static Element getUniqueElementChild(Element element) throws XmpParsingException {
        NodeList childNodes = element.getChildNodes();
        int i = -1;
        for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
            if (childNodes.item(i2) instanceof Element) {
                if (i >= 0) {
                    throw new XmpParsingException(XmpParsingException.ErrorType.Undefined, "Found two child elements in " + element);
                }
                i = i2;
            }
        }
        return (Element) childNodes.item(i);
    }

    public static Element getFirstChildElement(Element element) throws XmpParsingException {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element) {
                return (Element) childNodes.item(i);
            }
        }
        return null;
    }

    public static List<Element> getElementChildren(Element element) throws XmpParsingException {
        NodeList childNodes = element.getChildNodes();
        ArrayList arrayList = new ArrayList(childNodes.getLength());
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element) {
                arrayList.add((Element) childNodes.item(i));
            }
        }
        return arrayList;
    }

    public static QName getQName(Element element) {
        return new QName(element.getNamespaceURI(), element.getLocalName(), element.getPrefix());
    }

    public static boolean isRdfDescription(Element element) {
        return XmpConstants.DEFAULT_RDF_PREFIX.equals(element.getPrefix()) && XmpConstants.DESCRIPTION_NAME.equals(element.getLocalName());
    }

    public static boolean isParseTypeResource(Element element) {
        Attr attributeNodeNS = element.getAttributeNodeNS(XmpConstants.RDF_NAMESPACE, XmpConstants.PARSE_TYPE);
        return attributeNodeNS != null && XmpConstants.RESOURCE_NAME.equals(attributeNodeNS.getValue());
    }
}
