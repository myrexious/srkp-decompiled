package org.apache.xmpbox.xml;

import androidx.exifinterface.media.ExifInterface;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.XmpConstants;
import org.apache.xmpbox.schema.XMPSchema;
import org.apache.xmpbox.type.AbstractComplexProperty;
import org.apache.xmpbox.type.AbstractField;
import org.apache.xmpbox.type.AbstractSimpleProperty;
import org.apache.xmpbox.type.AbstractStructuredType;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.Attribute;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* loaded from: classes2.dex */
public class XmpSerializer {
    private final DocumentBuilder documentBuilder;
    private boolean parseTypeResourceForLi;
    private final TransformerFactory transformerFactory;

    public XmpSerializer() {
        this(TransformerFactory.newInstance(), DocumentBuilderFactory.newInstance());
    }

    public XmpSerializer(TransformerFactory transformerFactory, DocumentBuilderFactory documentBuilderFactory) {
        this.parseTypeResourceForLi = true;
        this.transformerFactory = transformerFactory;
        try {
            this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void serialize(XMPMetadata xMPMetadata, OutputStream outputStream, boolean z) throws TransformerException {
        Document newDocument = this.documentBuilder.newDocument();
        Element createRdfElement = createRdfElement(newDocument, xMPMetadata, z);
        for (XMPSchema xMPSchema : xMPMetadata.getAllSchemas()) {
            createRdfElement.appendChild(serializeSchema(newDocument, xMPSchema));
        }
        save(newDocument, outputStream, "UTF-8");
    }

    protected Element serializeSchema(Document document, XMPSchema xMPSchema) {
        Element createElementNS = document.createElementNS(XmpConstants.RDF_NAMESPACE, "rdf:Description");
        createElementNS.setAttributeNS(XmpConstants.RDF_NAMESPACE, "rdf:about", xMPSchema.getAboutValue());
        createElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + xMPSchema.getPrefix(), xMPSchema.getNamespace());
        fillElementWithAttributes(createElementNS, xMPSchema);
        serializeFields(document, createElementNS, xMPSchema.getAllProperties(), xMPSchema.getPrefix(), null, true);
        return createElementNS;
    }

    public void serializeFields(Document document, Element element, List<AbstractField> list, String str, String str2, boolean z) {
        Element element2;
        boolean z2 = (str2 == null || str2.isEmpty()) ? false : true;
        for (AbstractField abstractField : list) {
            if (abstractField instanceof AbstractSimpleProperty) {
                AbstractSimpleProperty abstractSimpleProperty = (AbstractSimpleProperty) abstractField;
                Element createElement = document.createElement((z2 ? str2 : abstractSimpleProperty.getPrefix()) + ":" + abstractSimpleProperty.getPropertyName());
                createElement.setTextContent(abstractSimpleProperty.getStringValue());
                for (Attribute attribute : abstractSimpleProperty.getAllAttributes()) {
                    createElement.setAttributeNS(attribute.getNamespace(), attribute.getName(), attribute.getValue());
                }
                element.appendChild(createElement);
            } else if (abstractField instanceof ArrayProperty) {
                ArrayProperty arrayProperty = (ArrayProperty) abstractField;
                Element createElement2 = document.createElement(arrayProperty.getPrefix() + ":" + arrayProperty.getPropertyName());
                element.appendChild(createElement2);
                fillElementWithAttributes(createElement2, arrayProperty);
                Element createElement3 = document.createElement("rdf:" + arrayProperty.getArrayType());
                createElement2.appendChild(createElement3);
                serializeFields(document, createElement3, arrayProperty.getAllProperties(), str, XmpConstants.DEFAULT_RDF_PREFIX, false);
            } else if (abstractField instanceof AbstractStructuredType) {
                AbstractStructuredType abstractStructuredType = (AbstractStructuredType) abstractField;
                List<AbstractField> allProperties = abstractStructuredType.getAllProperties();
                if (z) {
                    element2 = document.createElement(str + ":" + abstractStructuredType.getPropertyName());
                    element.appendChild(element2);
                } else {
                    element2 = element;
                }
                Element createElement4 = document.createElement("rdf:li");
                element2.appendChild(createElement4);
                if (this.parseTypeResourceForLi) {
                    createElement4.setAttribute("rdf:parseType", XmpConstants.RESOURCE_NAME);
                    serializeFields(document, createElement4, allProperties, str, null, true);
                } else {
                    Element createElement5 = document.createElement("rdf:Description");
                    createElement4.appendChild(createElement5);
                    serializeFields(document, createElement5, allProperties, str, null, true);
                }
            } else {
                System.err.println(">> TODO >> " + abstractField.getClass());
            }
        }
    }

    private void fillElementWithAttributes(Element element, AbstractComplexProperty abstractComplexProperty) {
        for (Attribute attribute : normalizeAttributes(abstractComplexProperty)) {
            if (XmpConstants.RDF_NAMESPACE.equals(attribute.getNamespace())) {
                element.setAttribute("rdf:" + attribute.getName(), attribute.getValue());
            } else {
                element.setAttribute(attribute.getName(), attribute.getValue());
            }
        }
        for (Map.Entry<String, String> entry : abstractComplexProperty.getAllNamespacesWithPrefix().entrySet()) {
            element.setAttribute("xmlns:" + entry.getValue(), entry.getKey());
        }
    }

    private List<Attribute> normalizeAttributes(AbstractComplexProperty abstractComplexProperty) {
        boolean z;
        List<Attribute> allAttributes = abstractComplexProperty.getAllAttributes();
        ArrayList arrayList = new ArrayList();
        List<AbstractField> allProperties = abstractComplexProperty.getAllProperties();
        for (Attribute attribute : allAttributes) {
            Iterator<AbstractField> it = allProperties.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (attribute.getName().compareTo(it.next().getPropertyName()) == 0) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                arrayList.add(attribute);
            }
        }
        return arrayList;
    }

    protected Element createRdfElement(Document document, XMPMetadata xMPMetadata, boolean z) {
        if (z) {
            document.appendChild(document.createProcessingInstruction("xpacket", "begin=\"" + xMPMetadata.getXpacketBegin() + "\" id=\"" + xMPMetadata.getXpacketId() + OperatorName.SHOW_TEXT_LINE_AND_SPACE));
        }
        Element createElementNS = document.createElementNS("adobe:ns:meta/", "x:xmpmeta");
        createElementNS.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:x", "adobe:ns:meta/");
        document.appendChild(createElementNS);
        if (z) {
            document.appendChild(document.createProcessingInstruction("xpacket", "end=\"" + xMPMetadata.getEndXPacket() + OperatorName.SHOW_TEXT_LINE_AND_SPACE));
        }
        Element createElementNS2 = document.createElementNS(XmpConstants.RDF_NAMESPACE, "rdf:RDF");
        createElementNS.appendChild(createElementNS2);
        return createElementNS2;
    }

    private void save(Node node, OutputStream outputStream, String str) throws TransformerException {
        Transformer newTransformer = this.transformerFactory.newTransformer();
        newTransformer.setOutputProperty("indent", BooleanUtils.YES);
        newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", ExifInterface.GPS_MEASUREMENT_2D);
        newTransformer.setOutputProperty("encoding", str);
        newTransformer.setOutputProperty("omit-xml-declaration", BooleanUtils.YES);
        newTransformer.transform(new DOMSource(node), new StreamResult(outputStream));
    }
}
