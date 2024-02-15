package com.tom_roush.pdfbox.util;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/* loaded from: classes3.dex */
public final class XMLUtil {
    private XMLUtil() {
    }

    public static Document parse(InputStream inputStream) throws IOException {
        return parse(inputStream, false);
    }

    public static Document parse(InputStream inputStream, boolean z) throws IOException {
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            newInstance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            newInstance.setXIncludeAware(false);
            newInstance.setExpandEntityReferences(false);
            newInstance.setNamespaceAware(z);
            return newInstance.newDocumentBuilder().parse(inputStream);
        } catch (FactoryConfigurationError e) {
            throw new IOException(e.getMessage(), e);
        } catch (ParserConfigurationException e2) {
            throw new IOException(e2.getMessage(), e2);
        } catch (SAXException e3) {
            throw new IOException(e3.getMessage(), e3);
        }
    }

    public static String getNodeValue(Element element) {
        StringBuilder sb = new StringBuilder();
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node item = childNodes.item(i);
            if (item instanceof Text) {
                sb.append(item.getNodeValue());
            }
        }
        return sb.toString();
    }
}
