package org.apache.xmpbox.schema;

import java.util.ArrayList;
import java.util.List;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.AbstractField;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.DateType;
import org.apache.xmpbox.type.IntegerType;
import org.apache.xmpbox.type.LayerType;
import org.apache.xmpbox.type.ProperNameType;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.TextType;
import org.apache.xmpbox.type.Types;
import org.apache.xmpbox.type.URIType;

@StructuredType(namespace = "http://ns.adobe.com/photoshop/1.0/", preferedPrefix = "photoshop")
/* loaded from: classes2.dex */
public class PhotoshopSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Simple, type = Types.URI)
    public static final String ANCESTORID = "AncestorID";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String AUTHORS_POSITION = "AuthorsPosition";
    @PropertyType(card = Cardinality.Simple, type = Types.ProperName)
    public static final String CAPTION_WRITER = "CaptionWriter";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String CATEGORY = "Category";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String CITY = "City";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String COLOR_MODE = "ColorMode";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String COUNTRY = "Country";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String CREDIT = "Credit";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String DATE_CREATED = "DateCreated";
    @PropertyType(card = Cardinality.Bag, type = Types.Text)
    public static final String DOCUMENT_ANCESTORS = "DocumentAncestors";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String HEADLINE = "Headline";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String HISTORY = "History";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String ICC_PROFILE = "ICCProfile";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String INSTRUCTIONS = "Instructions";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String SOURCE = "Source";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String STATE = "State";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String SUPPLEMENTAL_CATEGORIES = "SupplementalCategories";
    @PropertyType(card = Cardinality.Seq, type = Types.Layer)
    public static final String TEXT_LAYERS = "TextLayers";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String TRANSMISSION_REFERENCE = "TransmissionReference";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String URGENCY = "Urgency";
    private ArrayProperty seqLayer;

    public PhotoshopSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public PhotoshopSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public URIType getAncestorIDProperty() {
        return (URIType) getProperty(ANCESTORID);
    }

    public String getAncestorID() {
        TextType textType = (TextType) getProperty(ANCESTORID);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setAncestorID(String str) {
        setAncestorIDProperty((URIType) instanciateSimple(ANCESTORID, str));
    }

    public void setAncestorIDProperty(URIType uRIType) {
        addProperty(uRIType);
    }

    public TextType getAuthorsPositionProperty() {
        return (TextType) getProperty(AUTHORS_POSITION);
    }

    public String getAuthorsPosition() {
        TextType textType = (TextType) getProperty(AUTHORS_POSITION);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setAuthorsPosition(String str) {
        setAuthorsPositionProperty((TextType) instanciateSimple(AUTHORS_POSITION, str));
    }

    public void setAuthorsPositionProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getCaptionWriterProperty() {
        return (TextType) getProperty(CAPTION_WRITER);
    }

    public String getCaptionWriter() {
        TextType textType = (TextType) getProperty(CAPTION_WRITER);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setCaptionWriter(String str) {
        setCaptionWriterProperty((ProperNameType) instanciateSimple(CAPTION_WRITER, str));
    }

    public void setCaptionWriterProperty(ProperNameType properNameType) {
        addProperty(properNameType);
    }

    public TextType getCategoryProperty() {
        return (TextType) getProperty(CATEGORY);
    }

    public String getCategory() {
        TextType textType = (TextType) getProperty(CATEGORY);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setCategory(String str) {
        setCategoryProperty((TextType) instanciateSimple(CATEGORY, str));
    }

    public void setCategoryProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getCityProperty() {
        return (TextType) getProperty(CITY);
    }

    public String getCity() {
        TextType textType = (TextType) getProperty(CITY);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setCity(String str) {
        setCityProperty((TextType) instanciateSimple(CITY, str));
    }

    public void setCityProperty(TextType textType) {
        addProperty(textType);
    }

    public IntegerType getColorModeProperty() {
        return (IntegerType) getProperty(COLOR_MODE);
    }

    public Integer getColorMode() {
        IntegerType integerType = (IntegerType) getProperty(COLOR_MODE);
        if (integerType == null) {
            return null;
        }
        return integerType.getValue();
    }

    public void setColorMode(String str) {
        setColorModeProperty((IntegerType) instanciateSimple(COLOR_MODE, str));
    }

    public void setColorModeProperty(IntegerType integerType) {
        addProperty(integerType);
    }

    public TextType getCountryProperty() {
        return (TextType) getProperty(COUNTRY);
    }

    public String getCountry() {
        TextType textType = (TextType) getProperty(COUNTRY);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setCountry(String str) {
        setCountryProperty((TextType) instanciateSimple(COUNTRY, str));
    }

    public void setCountryProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getCreditProperty() {
        return (TextType) getProperty(CREDIT);
    }

    public String getCredit() {
        TextType textType = (TextType) getProperty(CREDIT);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setCredit(String str) {
        setCreditProperty((TextType) instanciateSimple(CREDIT, str));
    }

    public void setCreditProperty(TextType textType) {
        addProperty(textType);
    }

    public DateType getDateCreatedProperty() {
        return (DateType) getProperty(DATE_CREATED);
    }

    public String getDateCreated() {
        TextType textType = (TextType) getProperty(DATE_CREATED);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setDateCreated(String str) {
        setDateCreatedProperty((DateType) instanciateSimple(DATE_CREATED, str));
    }

    public void setDateCreatedProperty(DateType dateType) {
        addProperty(dateType);
    }

    public void addDocumentAncestors(String str) {
        addQualifiedBagValue(DOCUMENT_ANCESTORS, str);
    }

    public ArrayProperty getDocumentAncestorsProperty() {
        return (ArrayProperty) getProperty(DOCUMENT_ANCESTORS);
    }

    public List<String> getDocumentAncestors() {
        return getUnqualifiedBagValueList(DOCUMENT_ANCESTORS);
    }

    public TextType getHeadlineProperty() {
        return (TextType) getProperty(HEADLINE);
    }

    public String getHeadline() {
        TextType textType = (TextType) getProperty(HEADLINE);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setHeadline(String str) {
        setHeadlineProperty((TextType) instanciateSimple(HEADLINE, str));
    }

    public void setHeadlineProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getHistoryProperty() {
        return (TextType) getProperty("History");
    }

    public String getHistory() {
        TextType textType = (TextType) getProperty("History");
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setHistory(String str) {
        setHistoryProperty((TextType) instanciateSimple("History", str));
    }

    public void setHistoryProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getICCProfileProperty() {
        return (TextType) getProperty(ICC_PROFILE);
    }

    public String getICCProfile() {
        TextType textType = (TextType) getProperty(ICC_PROFILE);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setICCProfile(String str) {
        setICCProfileProperty((TextType) instanciateSimple(ICC_PROFILE, str));
    }

    public void setICCProfileProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getInstructionsProperty() {
        return (TextType) getProperty(INSTRUCTIONS);
    }

    public String getInstructions() {
        TextType textType = (TextType) getProperty(INSTRUCTIONS);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setInstructions(String str) {
        setInstructionsProperty((TextType) instanciateSimple(INSTRUCTIONS, str));
    }

    public void setInstructionsProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getSourceProperty() {
        return (TextType) getProperty(SOURCE);
    }

    public String getSource() {
        TextType textType = (TextType) getProperty(SOURCE);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setSource(String str) {
        setSourceProperty((TextType) instanciateSimple(SOURCE, str));
    }

    public void setSourceProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getStateProperty() {
        return (TextType) getProperty(STATE);
    }

    public String getState() {
        TextType textType = (TextType) getProperty(STATE);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setState(String str) {
        setStateProperty((TextType) instanciateSimple(STATE, str));
    }

    public void setStateProperty(TextType textType) {
        addProperty(textType);
    }

    public TextType getSupplementalCategoriesProperty() {
        return (TextType) getProperty(SUPPLEMENTAL_CATEGORIES);
    }

    public String getSupplementalCategories() {
        TextType textType = (TextType) getProperty(SUPPLEMENTAL_CATEGORIES);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setSupplementalCategories(String str) {
        setSupplementalCategoriesProperty((TextType) instanciateSimple(SUPPLEMENTAL_CATEGORIES, str));
    }

    public void setSupplementalCategoriesProperty(TextType textType) {
        addProperty(textType);
    }

    public void addTextLayers(String str, String str2) {
        if (this.seqLayer == null) {
            ArrayProperty createArrayProperty = createArrayProperty(TEXT_LAYERS, Cardinality.Seq);
            this.seqLayer = createArrayProperty;
            addProperty(createArrayProperty);
        }
        LayerType layerType = new LayerType(getMetadata());
        layerType.setLayerName(str);
        layerType.setLayerText(str2);
        this.seqLayer.getContainer().addProperty(layerType);
    }

    public List<LayerType> getTextLayers() throws BadFieldValueException {
        List<AbstractField> unqualifiedArrayList = getUnqualifiedArrayList(TEXT_LAYERS);
        if (unqualifiedArrayList != null) {
            ArrayList arrayList = new ArrayList();
            for (AbstractField abstractField : unqualifiedArrayList) {
                if (abstractField instanceof LayerType) {
                    arrayList.add((LayerType) abstractField);
                } else {
                    throw new BadFieldValueException("Layer expected and " + abstractField.getClass().getName() + " found.");
                }
            }
            return arrayList;
        }
        return null;
    }

    public TextType getTransmissionReferenceProperty() {
        return (TextType) getProperty(TRANSMISSION_REFERENCE);
    }

    public String getTransmissionReference() {
        TextType textType = (TextType) getProperty(TRANSMISSION_REFERENCE);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public void setTransmissionReference(String str) {
        setTransmissionReferenceProperty((TextType) instanciateSimple(TRANSMISSION_REFERENCE, str));
    }

    public void setTransmissionReferenceProperty(TextType textType) {
        addProperty(textType);
    }

    public IntegerType getUrgencyProperty() {
        return (IntegerType) getProperty(URGENCY);
    }

    public Integer getUrgency() {
        IntegerType integerType = (IntegerType) getProperty(URGENCY);
        if (integerType == null) {
            return null;
        }
        return integerType.getValue();
    }

    public void setUrgency(String str) {
        setUrgencyProperty((IntegerType) instanciateSimple(URGENCY, str));
    }

    public void setUrgency(Integer num) {
        setUrgencyProperty((IntegerType) instanciateSimple(URGENCY, num));
    }

    public void setUrgencyProperty(IntegerType integerType) {
        addProperty(integerType);
    }
}
