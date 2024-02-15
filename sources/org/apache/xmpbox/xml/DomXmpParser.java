package org.apache.xmpbox.xml;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.XmpConstants;
import org.apache.xmpbox.schema.XMPSchema;
import org.apache.xmpbox.schema.XmpSchemaException;
import org.apache.xmpbox.type.AbstractField;
import org.apache.xmpbox.type.AbstractSimpleProperty;
import org.apache.xmpbox.type.AbstractStructuredType;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.Attribute;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.ComplexPropertyContainer;
import org.apache.xmpbox.type.JobType;
import org.apache.xmpbox.type.PropertiesDescription;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.TypeMapping;
import org.apache.xmpbox.type.Types;
import org.apache.xmpbox.xml.XmpParsingException;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/* loaded from: classes2.dex */
public class DomXmpParser {
    private DocumentBuilder dBuilder;
    private NamespaceFinder nsFinder;
    private boolean strictParsing = true;

    public DomXmpParser() throws XmpParsingException {
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            newInstance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            newInstance.setXIncludeAware(false);
            newInstance.setExpandEntityReferences(false);
            newInstance.setIgnoringComments(true);
            newInstance.setNamespaceAware(true);
            this.dBuilder = newInstance.newDocumentBuilder();
            this.nsFinder = new NamespaceFinder();
        } catch (ParserConfigurationException e) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Configuration, "Failed to initialize", e);
        }
    }

    public boolean isStrictParsing() {
        return this.strictParsing;
    }

    public void setStrictParsing(boolean z) {
        this.strictParsing = z;
    }

    public XMPMetadata parse(byte[] bArr) throws XmpParsingException {
        return parse(new ByteArrayInputStream(bArr));
    }

    public XMPMetadata parse(InputStream inputStream) throws XmpParsingException {
        try {
            this.dBuilder.setErrorHandler(null);
            Document parse = this.dBuilder.parse(inputStream);
            removeComments(parse);
            Node firstChild = parse.getFirstChild();
            if (!(firstChild instanceof ProcessingInstruction)) {
                throw new XmpParsingException(XmpParsingException.ErrorType.XpacketBadStart, "xmp should start with a processing instruction");
            }
            XMPMetadata parseInitialXpacket = parseInitialXpacket((ProcessingInstruction) firstChild);
            Node nextSibling = firstChild.getNextSibling();
            while (nextSibling instanceof ProcessingInstruction) {
                nextSibling = nextSibling.getNextSibling();
            }
            if (!(nextSibling instanceof Element)) {
                throw new XmpParsingException(XmpParsingException.ErrorType.NoRootElement, "xmp should contain a root element");
            }
            Element element = (Element) nextSibling;
            Node nextSibling2 = nextSibling.getNextSibling();
            if (!(nextSibling2 instanceof ProcessingInstruction)) {
                throw new XmpParsingException(XmpParsingException.ErrorType.XpacketBadEnd, "xmp should end with a processing instruction");
            }
            parseEndPacket(parseInitialXpacket, (ProcessingInstruction) nextSibling2);
            if (nextSibling2.getNextSibling() != null) {
                throw new XmpParsingException(XmpParsingException.ErrorType.XpacketBadEnd, "xmp should end after xpacket end processing instruction");
            }
            List<Element> elementChildren = DomHelper.getElementChildren(findDescriptionsParent(element));
            ArrayList<Element> arrayList = new ArrayList(elementChildren.size());
            for (Element element2 : elementChildren) {
                Element firstChildElement = DomHelper.getFirstChildElement(element2);
                if (firstChildElement != null && "pdfaExtension".equals(firstChildElement.getPrefix())) {
                    PdfaExtensionHelper.validateNaming(parseInitialXpacket, element2);
                    parseDescriptionRoot(parseInitialXpacket, element2);
                } else {
                    arrayList.add(element2);
                }
            }
            PdfaExtensionHelper.populateSchemaMapping(parseInitialXpacket);
            for (Element element3 : arrayList) {
                parseDescriptionRoot(parseInitialXpacket, element3);
            }
            return parseInitialXpacket;
        } catch (IOException e) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Undefined, "Failed to parse", e);
        } catch (SAXException e2) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Undefined, "Failed to parse", e2);
        }
    }

    private void parseDescriptionRoot(XMPMetadata xMPMetadata, Element element) throws XmpParsingException {
        this.nsFinder.push(element);
        TypeMapping typeMapping = xMPMetadata.getTypeMapping();
        try {
            try {
                List<Element> elementChildren = DomHelper.getElementChildren(element);
                NamedNodeMap attributes = element.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    Attr attr = (Attr) attributes.item(i);
                    if (!"xmlns".equals(attr.getPrefix()) && ((!XmpConstants.DEFAULT_RDF_PREFIX.equals(attr.getPrefix()) || !XmpConstants.ABOUT_NAME.equals(attr.getLocalName())) && (attr.getPrefix() != null || !XmpConstants.ABOUT_NAME.equals(attr.getLocalName())))) {
                        parseDescriptionRootAttr(xMPMetadata, element, attr, typeMapping);
                    }
                }
                parseChildrenAsProperties(xMPMetadata, elementChildren, typeMapping, element);
            } catch (XmpSchemaException e) {
                throw new XmpParsingException(XmpParsingException.ErrorType.Undefined, "Parsing failed", e);
            }
        } finally {
            this.nsFinder.pop();
        }
    }

    private void parseDescriptionRootAttr(XMPMetadata xMPMetadata, Element element, Attr attr, TypeMapping typeMapping) throws XmpSchemaException, XmpParsingException {
        String namespaceURI = attr.getNamespaceURI();
        XMPSchema schema = xMPMetadata.getSchema(namespaceURI);
        if (schema == null && typeMapping.getSchemaFactory(namespaceURI) != null) {
            schema = typeMapping.getSchemaFactory(namespaceURI).createXMPSchema(xMPMetadata, attr.getPrefix());
            loadAttributes(schema, element);
        }
        XMPSchema xMPSchema = schema;
        if (xMPSchema != null) {
            ComplexPropertyContainer container = xMPSchema.getContainer();
            PropertyType checkPropertyDefinition = checkPropertyDefinition(xMPMetadata, new QName(attr.getNamespaceURI(), attr.getLocalName()));
            if (checkPropertyDefinition == null) {
                checkPropertyDefinition = TypeMapping.createPropertyType(Types.Text, Cardinality.Simple);
            }
            try {
                container.addProperty(typeMapping.instanciateSimpleProperty(namespaceURI, xMPSchema.getPrefix(), attr.getLocalName(), attr.getValue(), checkPropertyDefinition.type()));
            } catch (IllegalArgumentException e) {
                throw new XmpParsingException(XmpParsingException.ErrorType.Format, e.getMessage() + " in " + xMPSchema.getPrefix() + ":" + attr.getLocalName(), e);
            }
        }
    }

    private void parseChildrenAsProperties(XMPMetadata xMPMetadata, List<Element> list, TypeMapping typeMapping, Element element) throws XmpParsingException, XmpSchemaException {
        for (Element element2 : list) {
            String namespaceURI = element2.getNamespaceURI();
            PropertyType checkPropertyDefinition = checkPropertyDefinition(xMPMetadata, DomHelper.getQName(element2));
            if (!typeMapping.isDefinedSchema(namespaceURI)) {
                throw new XmpParsingException(XmpParsingException.ErrorType.NoSchema, "This namespace is not a schema or a structured type : " + namespaceURI);
            }
            XMPSchema schema = xMPMetadata.getSchema(namespaceURI);
            if (schema == null) {
                schema = typeMapping.getSchemaFactory(namespaceURI).createXMPSchema(xMPMetadata, element2.getPrefix());
                loadAttributes(schema, element);
            }
            createProperty(xMPMetadata, element2, checkPropertyDefinition, schema.getContainer());
        }
    }

    private void createProperty(XMPMetadata xMPMetadata, Element element, PropertyType propertyType, ComplexPropertyContainer complexPropertyContainer) throws XmpParsingException {
        String prefix = element.getPrefix();
        String localName = element.getLocalName();
        String namespaceURI = element.getNamespaceURI();
        this.nsFinder.push(element);
        try {
            try {
                if (propertyType == null) {
                    if (this.strictParsing) {
                        throw new XmpParsingException(XmpParsingException.ErrorType.InvalidType, "No type defined for {" + namespaceURI + StringSubstitutor.DEFAULT_VAR_END + localName);
                    }
                    manageSimpleType(xMPMetadata, element, Types.Text, complexPropertyContainer);
                } else if (propertyType.type() == Types.LangAlt) {
                    manageLangAlt(xMPMetadata, element, complexPropertyContainer);
                } else if (propertyType.card().isArray()) {
                    manageArray(xMPMetadata, element, propertyType, complexPropertyContainer);
                } else if (propertyType.type().isSimple()) {
                    manageSimpleType(xMPMetadata, element, propertyType.type(), complexPropertyContainer);
                } else if (propertyType.type().isStructured()) {
                    manageStructuredType(xMPMetadata, element, prefix, complexPropertyContainer);
                } else if (propertyType.type() == Types.DefinedType) {
                    manageDefinedType(xMPMetadata, element, prefix, complexPropertyContainer);
                }
            } catch (IllegalArgumentException e) {
                throw new XmpParsingException(XmpParsingException.ErrorType.Format, e.getMessage() + " in " + prefix + ":" + localName, e);
            }
        } finally {
            this.nsFinder.pop();
        }
    }

    private void manageDefinedType(XMPMetadata xMPMetadata, Element element, String str, ComplexPropertyContainer complexPropertyContainer) throws XmpParsingException {
        if (DomHelper.isParseTypeResource(element)) {
            AbstractStructuredType parseLiDescription = parseLiDescription(xMPMetadata, DomHelper.getQName(element), element);
            if (parseLiDescription == null) {
                throw new XmpParsingException(XmpParsingException.ErrorType.Format, "property should contain child elements : " + element);
            }
            parseLiDescription.setPrefix(str);
            complexPropertyContainer.addProperty(parseLiDescription);
            return;
        }
        Element firstChildElement = DomHelper.getFirstChildElement(element);
        if (firstChildElement == null) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Format, "property should contain child element : " + element);
        }
        AbstractStructuredType parseLiDescription2 = parseLiDescription(xMPMetadata, DomHelper.getQName(element), firstChildElement);
        if (parseLiDescription2 == null) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Format, "inner element should contain child elements : " + firstChildElement);
        }
        parseLiDescription2.setPrefix(str);
        complexPropertyContainer.addProperty(parseLiDescription2);
    }

    private void manageStructuredType(XMPMetadata xMPMetadata, Element element, String str, ComplexPropertyContainer complexPropertyContainer) throws XmpParsingException {
        if (DomHelper.isParseTypeResource(element)) {
            AbstractStructuredType parseLiDescription = parseLiDescription(xMPMetadata, DomHelper.getQName(element), element);
            if (parseLiDescription != null) {
                parseLiDescription.setPrefix(str);
                complexPropertyContainer.addProperty(parseLiDescription);
                return;
            }
            return;
        }
        Element firstChildElement = DomHelper.getFirstChildElement(element);
        if (firstChildElement != null) {
            this.nsFinder.push(firstChildElement);
            AbstractStructuredType parseLiDescription2 = parseLiDescription(xMPMetadata, DomHelper.getQName(element), firstChildElement);
            if (parseLiDescription2 == null) {
                throw new XmpParsingException(XmpParsingException.ErrorType.Format, "inner element should contain child elements : " + firstChildElement);
            }
            parseLiDescription2.setPrefix(str);
            complexPropertyContainer.addProperty(parseLiDescription2);
        }
    }

    private void manageSimpleType(XMPMetadata xMPMetadata, Element element, Types types, ComplexPropertyContainer complexPropertyContainer) {
        AbstractSimpleProperty instanciateSimpleProperty = xMPMetadata.getTypeMapping().instanciateSimpleProperty(element.getNamespaceURI(), element.getPrefix(), element.getLocalName(), element.getTextContent(), types);
        loadAttributes(instanciateSimpleProperty, element);
        complexPropertyContainer.addProperty(instanciateSimpleProperty);
    }

    private void manageArray(XMPMetadata xMPMetadata, Element element, PropertyType propertyType, ComplexPropertyContainer complexPropertyContainer) throws XmpParsingException {
        TypeMapping typeMapping = xMPMetadata.getTypeMapping();
        String prefix = element.getPrefix();
        String localName = element.getLocalName();
        String namespaceURI = element.getNamespaceURI();
        Element uniqueElementChild = DomHelper.getUniqueElementChild(element);
        if (uniqueElementChild == null) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Format, "Invalid array definition, expecting " + propertyType.card() + " and found " + (element.getFirstChild() != null ? element.getFirstChild().getClass().getName() : "nothing") + " [prefix=" + prefix + "; name=" + localName + "]");
        } else if (!uniqueElementChild.getLocalName().equals(propertyType.card().name())) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Format, "Invalid array type, expecting " + propertyType.card() + " and found " + uniqueElementChild.getLocalName() + " [prefix=" + prefix + "; name=" + localName + "]");
        } else {
            ArrayProperty createArrayProperty = typeMapping.createArrayProperty(namespaceURI, prefix, localName, propertyType.card());
            complexPropertyContainer.addProperty(createArrayProperty);
            for (Element element2 : DomHelper.getElementChildren(uniqueElementChild)) {
                AbstractField parseLiElement = parseLiElement(xMPMetadata, new QName(element2.getLocalName()), element2, propertyType.type());
                if (parseLiElement != null) {
                    createArrayProperty.addProperty(parseLiElement);
                }
            }
        }
    }

    private void manageLangAlt(XMPMetadata xMPMetadata, Element element, ComplexPropertyContainer complexPropertyContainer) throws XmpParsingException {
        manageArray(xMPMetadata, element, TypeMapping.createPropertyType(Types.LangAlt, Cardinality.Alt), complexPropertyContainer);
    }

    private void parseDescriptionInner(XMPMetadata xMPMetadata, Element element, ComplexPropertyContainer complexPropertyContainer) throws XmpParsingException {
        this.nsFinder.push(element);
        TypeMapping typeMapping = xMPMetadata.getTypeMapping();
        try {
            for (Element element2 : DomHelper.getElementChildren(element)) {
                createProperty(xMPMetadata, element2, typeMapping.getStructuredPropMapping(checkPropertyDefinition(xMPMetadata, DomHelper.getQName(element2)).type()).getPropertyType(element2.getLocalName()), complexPropertyContainer);
            }
        } finally {
            this.nsFinder.pop();
        }
    }

    private AbstractField parseLiElement(XMPMetadata xMPMetadata, QName qName, Element element, Types types) throws XmpParsingException {
        if (DomHelper.isParseTypeResource(element)) {
            return parseLiDescription(xMPMetadata, qName, element);
        }
        Element uniqueElementChild = DomHelper.getUniqueElementChild(element);
        if (uniqueElementChild != null) {
            this.nsFinder.push(uniqueElementChild);
            return parseLiDescription(xMPMetadata, qName, uniqueElementChild);
        }
        String textContent = element.getTextContent();
        TypeMapping typeMapping = xMPMetadata.getTypeMapping();
        if (types.isSimple()) {
            AbstractSimpleProperty instanciateSimpleProperty = typeMapping.instanciateSimpleProperty(qName.getNamespaceURI(), qName.getPrefix(), qName.getLocalPart(), textContent, types);
            loadAttributes(instanciateSimpleProperty, element);
            return instanciateSimpleProperty;
        }
        try {
            AbstractStructuredType instanciateStructuredType = typeMapping.instanciateStructuredType(types, qName.getLocalPart());
            loadAttributes(instanciateStructuredType, element);
            return instanciateStructuredType;
        } catch (BadFieldValueException e) {
            throw new XmpParsingException(XmpParsingException.ErrorType.InvalidType, "Parsing of structured type failed", e);
        }
    }

    private void loadAttributes(AbstractField abstractField, Element element) {
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Attr attr = (Attr) attributes.item(i);
            if (!"xmlns".equals(attr.getPrefix())) {
                if (XmpConstants.DEFAULT_RDF_PREFIX.equals(attr.getPrefix()) && XmpConstants.ABOUT_NAME.equals(attr.getLocalName())) {
                    if (abstractField instanceof XMPSchema) {
                        ((XMPSchema) abstractField).setAboutAsSimple(attr.getValue());
                    }
                } else {
                    abstractField.setAttribute(new Attribute("http://www.w3.org/XML/1998/namespace", attr.getLocalName(), attr.getValue()));
                }
            }
        }
    }

    private AbstractStructuredType parseLiDescription(XMPMetadata xMPMetadata, QName qName, Element element) throws XmpParsingException {
        PropertiesDescription definedDescriptionByNamespace;
        TypeMapping typeMapping = xMPMetadata.getTypeMapping();
        List<Element> elementChildren = DomHelper.getElementChildren(element);
        if (elementChildren.isEmpty()) {
            return null;
        }
        Element element2 = elementChildren.get(0);
        Types type = checkPropertyDefinition(xMPMetadata, DomHelper.getQName(element2)).type();
        AbstractStructuredType instanciateStructured = instanciateStructured(typeMapping, type, qName.getLocalPart(), element2.getNamespaceURI());
        instanciateStructured.setNamespace(qName.getNamespaceURI());
        instanciateStructured.setPrefix(qName.getPrefix());
        if (type.isStructured()) {
            definedDescriptionByNamespace = typeMapping.getStructuredPropMapping(type);
        } else {
            definedDescriptionByNamespace = typeMapping.getDefinedDescriptionByNamespace(element2.getNamespaceURI());
        }
        PropertiesDescription propertiesDescription = definedDescriptionByNamespace;
        for (Element element3 : elementChildren) {
            String prefix = element3.getPrefix();
            String localName = element3.getLocalName();
            String namespaceURI = element3.getNamespaceURI();
            PropertyType propertyType = propertiesDescription.getPropertyType(localName);
            if (propertyType == null) {
                throw new XmpParsingException(XmpParsingException.ErrorType.NoType, "Type '" + localName + "' not defined in " + element3.getNamespaceURI());
            }
            if (propertyType.card().isArray()) {
                ArrayProperty createArrayProperty = typeMapping.createArrayProperty(namespaceURI, prefix, localName, propertyType.card());
                instanciateStructured.getContainer().addProperty(createArrayProperty);
                for (Element element4 : DomHelper.getElementChildren(DomHelper.getUniqueElementChild(element3))) {
                    AbstractField parseLiElement = parseLiElement(xMPMetadata, qName, element4, propertyType.type());
                    if (parseLiElement != null) {
                        createArrayProperty.addProperty(parseLiElement);
                    }
                }
            } else if (propertyType.type().isSimple()) {
                AbstractSimpleProperty instanciateSimpleProperty = typeMapping.instanciateSimpleProperty(namespaceURI, prefix, localName, element3.getTextContent(), propertyType.type());
                loadAttributes(instanciateSimpleProperty, element3);
                instanciateStructured.getContainer().addProperty(instanciateSimpleProperty);
            } else if (propertyType.type().isStructured()) {
                AbstractStructuredType instanciateStructured2 = instanciateStructured(typeMapping, propertyType.type(), localName, null);
                instanciateStructured2.setNamespace(namespaceURI);
                instanciateStructured2.setPrefix(prefix);
                instanciateStructured.getContainer().addProperty(instanciateStructured2);
                ComplexPropertyContainer container = instanciateStructured2.getContainer();
                if (DomHelper.isParseTypeResource(element3)) {
                    parseDescriptionInner(xMPMetadata, element3, container);
                } else {
                    Element firstChildElement = DomHelper.getFirstChildElement(element3);
                    if (firstChildElement != null) {
                        parseDescriptionInner(xMPMetadata, firstChildElement, container);
                    }
                }
            } else {
                throw new XmpParsingException(XmpParsingException.ErrorType.NoType, "Unidentified element to parse " + element3 + " (type=" + propertyType + ")");
            }
        }
        return instanciateStructured;
    }

    private XMPMetadata parseInitialXpacket(ProcessingInstruction processingInstruction) throws XmpParsingException {
        if (!"xpacket".equals(processingInstruction.getNodeName())) {
            throw new XmpParsingException(XmpParsingException.ErrorType.XpacketBadStart, "Bad processing instruction name : " + processingInstruction.getNodeName());
        }
        String data = processingInstruction.getData();
        StringTokenizer stringTokenizer = new StringTokenizer(data, StringUtils.SPACE);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (!nextToken.endsWith(OperatorName.SHOW_TEXT_LINE_AND_SPACE) && !nextToken.endsWith(OperatorName.SHOW_TEXT_LINE)) {
                throw new XmpParsingException(XmpParsingException.ErrorType.XpacketBadStart, "Cannot understand PI data part : '" + nextToken + "' in '" + data + OperatorName.SHOW_TEXT_LINE);
            }
            int indexOf = nextToken.indexOf("=" + nextToken.substring(nextToken.length() - 1));
            if (indexOf <= 0) {
                throw new XmpParsingException(XmpParsingException.ErrorType.XpacketBadStart, "Cannot understand PI data part : '" + nextToken + "' in '" + data + OperatorName.SHOW_TEXT_LINE);
            }
            String substring = nextToken.substring(0, indexOf);
            int i = indexOf + 2;
            if (nextToken.length() - 1 < i) {
                throw new XmpParsingException(XmpParsingException.ErrorType.XpacketBadStart, "Cannot understand PI data part : '" + nextToken + "' in '" + data + OperatorName.SHOW_TEXT_LINE);
            }
            String substring2 = nextToken.substring(i, nextToken.length() - 1);
            if (JobType.ID.equals(substring)) {
                str2 = substring2;
            } else if ("begin".equals(substring)) {
                str = substring2;
            } else if ("bytes".equals(substring)) {
                str3 = substring2;
            } else if (!"encoding".equals(substring)) {
                throw new XmpParsingException(XmpParsingException.ErrorType.XpacketBadStart, "Unknown attribute in xpacket PI : '" + nextToken + OperatorName.SHOW_TEXT_LINE);
            } else {
                str4 = substring2;
            }
        }
        return XMPMetadata.createXMPMetadata(str, str2, str3, str4);
    }

    private void parseEndPacket(XMPMetadata xMPMetadata, ProcessingInstruction processingInstruction) throws XmpParsingException {
        String data = processingInstruction.getData();
        if (data.startsWith("end=")) {
            char charAt = data.charAt(5);
            if (charAt != 'r' && charAt != 'w') {
                throw new XmpParsingException(XmpParsingException.ErrorType.XpacketBadEnd, "Excepted xpacket 'end' attribute with value 'r' or 'w' ");
            }
            xMPMetadata.setEndXPacket(Character.toString(charAt));
            return;
        }
        throw new XmpParsingException(XmpParsingException.ErrorType.XpacketBadEnd, "Excepted xpacket 'end' attribute (must be present and placed in first)");
    }

    private Element findDescriptionsParent(Element element) throws XmpParsingException {
        expectNaming(element, "adobe:ns:meta/", "x", "xmpmeta");
        NodeList childNodes = element.getChildNodes();
        if (childNodes.getLength() == 0) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Format, "No rdf description found in xmp");
        }
        if (childNodes.getLength() > 1) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Format, "More than one element found in x:xmpmeta");
        }
        if (!(element.getFirstChild() instanceof Element)) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Format, "x:xmpmeta does not contains rdf:RDF element");
        }
        Element element2 = (Element) element.getFirstChild();
        expectNaming(element2, XmpConstants.RDF_NAMESPACE, XmpConstants.DEFAULT_RDF_PREFIX, XmpConstants.DEFAULT_RDF_LOCAL_NAME);
        return element2;
    }

    private void expectNaming(Element element, String str, String str2, String str3) throws XmpParsingException {
        if (str != null && !str.equals(element.getNamespaceURI())) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Format, "Expecting namespace '" + str + "' and found '" + element.getNamespaceURI() + OperatorName.SHOW_TEXT_LINE);
        }
        if (str2 != null && !str2.equals(element.getPrefix())) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Format, "Expecting prefix '" + str2 + "' and found '" + element.getPrefix() + OperatorName.SHOW_TEXT_LINE);
        }
        if (str3 != null && !str3.equals(element.getLocalName())) {
            throw new XmpParsingException(XmpParsingException.ErrorType.Format, "Expecting local name '" + str3 + "' and found '" + element.getLocalName() + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private void removeComments(Node node) {
        ArrayList<Node> arrayList = new ArrayList();
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() <= 1) {
            return;
        }
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item instanceof Comment) {
                arrayList.add(item);
            } else if (item instanceof Text) {
                if (item.getTextContent().trim().isEmpty()) {
                    arrayList.add(item);
                }
            } else if (item instanceof Element) {
                removeComments(item);
            }
        }
        for (Node node2 : arrayList) {
            node.removeChild(node2);
        }
    }

    private AbstractStructuredType instanciateStructured(TypeMapping typeMapping, Types types, String str, String str2) throws XmpParsingException {
        try {
            if (types.isStructured()) {
                return typeMapping.instanciateStructuredType(types, str);
            }
            if (types.isDefined()) {
                return typeMapping.instanciateDefinedType(str, str2);
            }
            throw new XmpParsingException(XmpParsingException.ErrorType.InvalidType, "Type not structured : " + types);
        } catch (BadFieldValueException e) {
            throw new XmpParsingException(XmpParsingException.ErrorType.InvalidType, "Parsing failed", e);
        }
    }

    private PropertyType checkPropertyDefinition(XMPMetadata xMPMetadata, QName qName) throws XmpParsingException {
        TypeMapping typeMapping = xMPMetadata.getTypeMapping();
        if (!this.nsFinder.containsNamespace(qName.getNamespaceURI())) {
            throw new XmpParsingException(XmpParsingException.ErrorType.NoSchema, "Schema is not set in this document : " + qName.getNamespaceURI());
        }
        if (!typeMapping.isDefinedNamespace(qName.getNamespaceURI())) {
            throw new XmpParsingException(XmpParsingException.ErrorType.NoSchema, "Cannot find a definition for the namespace " + qName.getNamespaceURI());
        }
        try {
            return typeMapping.getSpecifiedPropertyType(qName);
        } catch (BadFieldValueException e) {
            throw new XmpParsingException(XmpParsingException.ErrorType.InvalidType, "Failed to retrieve property definition", e);
        }
    }

    /* loaded from: classes2.dex */
    public static class NamespaceFinder {
        private final Deque<Map<String, String>> stack = new ArrayDeque();

        protected NamespaceFinder() {
        }

        protected void push(Element element) {
            NamedNodeMap attributes = element.getAttributes();
            HashMap hashMap = new HashMap(attributes.getLength());
            for (int i = 0; i < attributes.getLength(); i++) {
                Attr attr = (Attr) attributes.item(i);
                if ("http://www.w3.org/2000/xmlns/".equals(attr.getNamespaceURI())) {
                    hashMap.put(attr.getLocalName(), attr.getValue());
                }
            }
            this.stack.push(hashMap);
        }

        protected Map<String, String> pop() {
            return this.stack.pop();
        }

        protected boolean containsNamespace(String str) {
            for (Map<String, String> map : this.stack) {
                if (map.containsValue(str)) {
                    return true;
                }
            }
            return false;
        }
    }
}
