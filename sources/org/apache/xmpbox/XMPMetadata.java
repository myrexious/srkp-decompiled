package org.apache.xmpbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.PDFAExtensionSchema;
import org.apache.xmpbox.schema.PDFAIdentificationSchema;
import org.apache.xmpbox.schema.PhotoshopSchema;
import org.apache.xmpbox.schema.XMPBasicJobTicketSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.schema.XMPMediaManagementSchema;
import org.apache.xmpbox.schema.XMPRightsManagementSchema;
import org.apache.xmpbox.schema.XMPSchema;
import org.apache.xmpbox.schema.XmpSchemaException;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.TypeMapping;

/* loaded from: classes2.dex */
public class XMPMetadata {
    private List<XMPSchema> schemas;
    private TypeMapping typeMapping;
    private String xpacketBegin;
    private String xpacketBytes;
    private String xpacketEncoding;
    private String xpacketEndData;
    private String xpacketId;

    protected XMPMetadata() {
        this(XmpConstants.DEFAULT_XPACKET_BEGIN, XmpConstants.DEFAULT_XPACKET_ID, XmpConstants.DEFAULT_XPACKET_BYTES, "UTF-8");
    }

    protected XMPMetadata(String str, String str2, String str3, String str4) {
        this.xpacketId = null;
        this.xpacketBegin = null;
        this.xpacketBytes = null;
        this.xpacketEncoding = null;
        this.xpacketEndData = "w";
        this.schemas = new ArrayList();
        this.typeMapping = new TypeMapping(this);
        this.xpacketBegin = str;
        this.xpacketId = str2;
        this.xpacketBytes = str3;
        this.xpacketEncoding = str4;
    }

    public static XMPMetadata createXMPMetadata() {
        return new XMPMetadata();
    }

    public static XMPMetadata createXMPMetadata(String str, String str2, String str3, String str4) {
        return new XMPMetadata(str, str2, str3, str4);
    }

    public TypeMapping getTypeMapping() {
        return this.typeMapping;
    }

    public String getXpacketBytes() {
        return this.xpacketBytes;
    }

    public String getXpacketEncoding() {
        return this.xpacketEncoding;
    }

    public String getXpacketBegin() {
        return this.xpacketBegin;
    }

    public String getXpacketId() {
        return this.xpacketId;
    }

    public List<XMPSchema> getAllSchemas() {
        ArrayList arrayList = new ArrayList();
        for (XMPSchema xMPSchema : this.schemas) {
            arrayList.add(xMPSchema);
        }
        return arrayList;
    }

    public void setEndXPacket(String str) {
        this.xpacketEndData = str;
    }

    public String getEndXPacket() {
        return this.xpacketEndData;
    }

    public XMPSchema getSchema(String str) {
        for (XMPSchema xMPSchema : this.schemas) {
            if (xMPSchema.getNamespace().equals(str)) {
                return xMPSchema;
            }
        }
        return null;
    }

    public XMPSchema getSchema(Class<? extends XMPSchema> cls) {
        return getSchema(((StructuredType) cls.getAnnotation(StructuredType.class)).namespace());
    }

    public XMPSchema getSchema(String str, String str2) {
        for (XMPSchema xMPSchema : getAllSchemas()) {
            if (xMPSchema.getNamespace().equals(str2) && xMPSchema.getPrefix().equals(str)) {
                return xMPSchema;
            }
        }
        return null;
    }

    public XMPSchema createAndAddDefaultSchema(String str, String str2) {
        XMPSchema xMPSchema = new XMPSchema(this, str2, str);
        xMPSchema.setAboutAsSimple("");
        addSchema(xMPSchema);
        return xMPSchema;
    }

    public PDFAExtensionSchema createAndAddPDFAExtensionSchemaWithDefaultNS() {
        PDFAExtensionSchema pDFAExtensionSchema = new PDFAExtensionSchema(this);
        pDFAExtensionSchema.setAboutAsSimple("");
        addSchema(pDFAExtensionSchema);
        return pDFAExtensionSchema;
    }

    public PDFAExtensionSchema createAndAddPDFAExtensionSchemaWithNS(Map<String, String> map) throws XmpSchemaException {
        PDFAExtensionSchema pDFAExtensionSchema = new PDFAExtensionSchema(this);
        pDFAExtensionSchema.setAboutAsSimple("");
        addSchema(pDFAExtensionSchema);
        return pDFAExtensionSchema;
    }

    public PDFAExtensionSchema getPDFExtensionSchema() {
        return (PDFAExtensionSchema) getSchema(PDFAExtensionSchema.class);
    }

    public PDFAIdentificationSchema createAndAddPFAIdentificationSchema() {
        PDFAIdentificationSchema pDFAIdentificationSchema = new PDFAIdentificationSchema(this);
        pDFAIdentificationSchema.setAboutAsSimple("");
        addSchema(pDFAIdentificationSchema);
        return pDFAIdentificationSchema;
    }

    public PDFAIdentificationSchema getPDFIdentificationSchema() {
        return (PDFAIdentificationSchema) getSchema(PDFAIdentificationSchema.class);
    }

    public DublinCoreSchema createAndAddDublinCoreSchema() {
        DublinCoreSchema dublinCoreSchema = new DublinCoreSchema(this);
        dublinCoreSchema.setAboutAsSimple("");
        addSchema(dublinCoreSchema);
        return dublinCoreSchema;
    }

    public DublinCoreSchema getDublinCoreSchema() {
        return (DublinCoreSchema) getSchema(DublinCoreSchema.class);
    }

    public XMPBasicJobTicketSchema createAndAddBasicJobTicketSchema() {
        XMPBasicJobTicketSchema xMPBasicJobTicketSchema = new XMPBasicJobTicketSchema(this);
        xMPBasicJobTicketSchema.setAboutAsSimple("");
        addSchema(xMPBasicJobTicketSchema);
        return xMPBasicJobTicketSchema;
    }

    public XMPBasicJobTicketSchema getBasicJobTicketSchema() {
        return (XMPBasicJobTicketSchema) getSchema(XMPBasicJobTicketSchema.class);
    }

    public XMPRightsManagementSchema createAndAddXMPRightsManagementSchema() {
        XMPRightsManagementSchema xMPRightsManagementSchema = new XMPRightsManagementSchema(this);
        xMPRightsManagementSchema.setAboutAsSimple("");
        addSchema(xMPRightsManagementSchema);
        return xMPRightsManagementSchema;
    }

    public XMPRightsManagementSchema getXMPRightsManagementSchema() {
        return (XMPRightsManagementSchema) getSchema(XMPRightsManagementSchema.class);
    }

    public XMPBasicSchema createAndAddXMPBasicSchema() {
        XMPBasicSchema xMPBasicSchema = new XMPBasicSchema(this);
        xMPBasicSchema.setAboutAsSimple("");
        addSchema(xMPBasicSchema);
        return xMPBasicSchema;
    }

    public XMPBasicSchema getXMPBasicSchema() {
        return (XMPBasicSchema) getSchema(XMPBasicSchema.class);
    }

    public XMPMediaManagementSchema createAndAddXMPMediaManagementSchema() {
        XMPMediaManagementSchema xMPMediaManagementSchema = new XMPMediaManagementSchema(this);
        xMPMediaManagementSchema.setAboutAsSimple("");
        addSchema(xMPMediaManagementSchema);
        return xMPMediaManagementSchema;
    }

    public PhotoshopSchema createAndAddPhotoshopSchema() {
        PhotoshopSchema photoshopSchema = new PhotoshopSchema(this);
        photoshopSchema.setAboutAsSimple("");
        addSchema(photoshopSchema);
        return photoshopSchema;
    }

    public PhotoshopSchema getPhotoshopSchema() {
        return (PhotoshopSchema) getSchema(PhotoshopSchema.class);
    }

    public XMPMediaManagementSchema getXMPMediaManagementSchema() {
        return (XMPMediaManagementSchema) getSchema(XMPMediaManagementSchema.class);
    }

    public AdobePDFSchema createAndAddAdobePDFSchema() {
        AdobePDFSchema adobePDFSchema = new AdobePDFSchema(this);
        adobePDFSchema.setAboutAsSimple("");
        addSchema(adobePDFSchema);
        return adobePDFSchema;
    }

    public AdobePDFSchema getAdobePDFSchema() {
        return (AdobePDFSchema) getSchema(AdobePDFSchema.class);
    }

    public void addSchema(XMPSchema xMPSchema) {
        this.schemas.add(xMPSchema);
    }

    public void removeSchema(XMPSchema xMPSchema) {
        this.schemas.remove(xMPSchema);
    }

    public void clearSchemas() {
        this.schemas.clear();
    }
}
