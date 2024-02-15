package org.apache.xmpbox.schema;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.AbstractField;
import org.apache.xmpbox.type.AgentNameType;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.DateType;
import org.apache.xmpbox.type.IntegerType;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.TextType;
import org.apache.xmpbox.type.ThumbnailType;
import org.apache.xmpbox.type.Types;
import org.apache.xmpbox.type.URLType;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/", preferedPrefix = "xmp")
/* loaded from: classes2.dex */
public class XMPBasicSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Bag, type = Types.XPath)
    public static final String ADVISORY = "Advisory";
    @PropertyType(card = Cardinality.Simple, type = Types.URL)
    public static final String BASEURL = "BaseURL";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String CREATEDATE = "CreateDate";
    @PropertyType(card = Cardinality.Simple, type = Types.AgentName)
    public static final String CREATORTOOL = "CreatorTool";
    @PropertyType(card = Cardinality.Bag, type = Types.Text)
    public static final String IDENTIFIER = "Identifier";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String LABEL = "Label";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String METADATADATE = "MetadataDate";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String MODIFIER_DATE = "ModifierDate";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String MODIFYDATE = "ModifyDate";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String NICKNAME = "Nickname";
    @PropertyType(card = Cardinality.Simple, type = Types.Integer)
    public static final String RATING = "Rating";
    @PropertyType(card = Cardinality.Alt, type = Types.Thumbnail)
    public static final String THUMBNAILS = "Thumbnails";
    private ArrayProperty altThumbs;

    public XMPBasicSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public XMPBasicSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public void addThumbnails(Integer num, Integer num2, String str, String str2) {
        if (this.altThumbs == null) {
            ArrayProperty createArrayProperty = createArrayProperty(THUMBNAILS, Cardinality.Alt);
            this.altThumbs = createArrayProperty;
            addProperty(createArrayProperty);
        }
        ThumbnailType thumbnailType = new ThumbnailType(getMetadata());
        thumbnailType.setHeight(num);
        thumbnailType.setWidth(num2);
        thumbnailType.setFormat(str);
        thumbnailType.setImage(str2);
        this.altThumbs.getContainer().addProperty(thumbnailType);
    }

    public void addAdvisory(String str) {
        addQualifiedBagValue(ADVISORY, str);
    }

    public void removeAdvisory(String str) {
        removeUnqualifiedBagValue(ADVISORY, str);
    }

    public void setBaseURL(String str) {
        setBaseURLProperty((URLType) instanciateSimple(BASEURL, str));
    }

    public void setBaseURLProperty(URLType uRLType) {
        addProperty(uRLType);
    }

    public void setCreateDate(Calendar calendar) {
        setCreateDateProperty((DateType) instanciateSimple(CREATEDATE, calendar));
    }

    public void setCreateDateProperty(DateType dateType) {
        addProperty(dateType);
    }

    public void setCreatorTool(String str) {
        setCreatorToolProperty((AgentNameType) instanciateSimple(CREATORTOOL, str));
    }

    public void setCreatorToolProperty(AgentNameType agentNameType) {
        addProperty(agentNameType);
    }

    public void addIdentifier(String str) {
        addQualifiedBagValue(IDENTIFIER, str);
    }

    public void removeIdentifier(String str) {
        removeUnqualifiedBagValue(IDENTIFIER, str);
    }

    public void setLabel(String str) {
        setLabelProperty((TextType) instanciateSimple(LABEL, str));
    }

    public void setLabelProperty(TextType textType) {
        addProperty(textType);
    }

    public void setMetadataDate(Calendar calendar) {
        setMetadataDateProperty((DateType) instanciateSimple(METADATADATE, calendar));
    }

    public void setMetadataDateProperty(DateType dateType) {
        addProperty(dateType);
    }

    public void setModifyDate(Calendar calendar) {
        setModifyDateProperty((DateType) instanciateSimple(MODIFYDATE, calendar));
    }

    public void setModifierDate(Calendar calendar) {
        setModifierDateProperty((DateType) instanciateSimple(MODIFIER_DATE, calendar));
    }

    public void setModifyDateProperty(DateType dateType) {
        addProperty(dateType);
    }

    public void setModifierDateProperty(DateType dateType) {
        addProperty(dateType);
    }

    public void setNickname(String str) {
        setNicknameProperty((TextType) instanciateSimple(NICKNAME, str));
    }

    public void setNicknameProperty(TextType textType) {
        addProperty(textType);
    }

    public void setRating(Integer num) {
        setRatingProperty((IntegerType) instanciateSimple(RATING, num));
    }

    public void setRatingProperty(IntegerType integerType) {
        addProperty(integerType);
    }

    public ArrayProperty getAdvisoryProperty() {
        return (ArrayProperty) getProperty(ADVISORY);
    }

    public List<String> getAdvisory() {
        return getUnqualifiedBagValueList(ADVISORY);
    }

    public TextType getBaseURLProperty() {
        return (TextType) getProperty(BASEURL);
    }

    public String getBaseURL() {
        TextType textType = (TextType) getProperty(BASEURL);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public DateType getCreateDateProperty() {
        return (DateType) getProperty(CREATEDATE);
    }

    public Calendar getCreateDate() {
        DateType dateType = (DateType) getProperty(CREATEDATE);
        if (dateType != null) {
            return dateType.getValue();
        }
        return null;
    }

    public TextType getCreatorToolProperty() {
        return (TextType) getProperty(CREATORTOOL);
    }

    public String getCreatorTool() {
        TextType textType = (TextType) getProperty(CREATORTOOL);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public ArrayProperty getIdentifiersProperty() {
        return (ArrayProperty) getProperty(IDENTIFIER);
    }

    public List<String> getIdentifiers() {
        return getUnqualifiedBagValueList(IDENTIFIER);
    }

    public TextType getLabelProperty() {
        return (TextType) getProperty(LABEL);
    }

    public String getLabel() {
        TextType textType = (TextType) getProperty(LABEL);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public DateType getMetadataDateProperty() {
        return (DateType) getProperty(METADATADATE);
    }

    public Calendar getMetadataDate() {
        DateType dateType = (DateType) getProperty(METADATADATE);
        if (dateType == null) {
            return null;
        }
        return dateType.getValue();
    }

    public DateType getModifyDateProperty() {
        return (DateType) getProperty(MODIFYDATE);
    }

    public DateType getModifierDateProperty() {
        return (DateType) getProperty(MODIFIER_DATE);
    }

    public Calendar getModifyDate() {
        DateType dateType = (DateType) getProperty(MODIFYDATE);
        if (dateType != null) {
            return dateType.getValue();
        }
        return null;
    }

    public Calendar getModifierDate() {
        DateType dateType = (DateType) getProperty(MODIFIER_DATE);
        if (dateType != null) {
            return dateType.getValue();
        }
        return null;
    }

    public TextType getNicknameProperty() {
        return (TextType) getProperty(NICKNAME);
    }

    public String getNickname() {
        TextType textType = (TextType) getProperty(NICKNAME);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public IntegerType getRatingProperty() {
        return (IntegerType) getProperty(RATING);
    }

    public Integer getRating() {
        IntegerType integerType = (IntegerType) getProperty(RATING);
        if (integerType == null) {
            return null;
        }
        return integerType.getValue();
    }

    public List<ThumbnailType> getThumbnailsProperty() throws BadFieldValueException {
        List<AbstractField> unqualifiedArrayList = getUnqualifiedArrayList(THUMBNAILS);
        if (unqualifiedArrayList != null) {
            ArrayList arrayList = new ArrayList();
            for (AbstractField abstractField : unqualifiedArrayList) {
                if (abstractField instanceof ThumbnailType) {
                    arrayList.add((ThumbnailType) abstractField);
                } else {
                    throw new BadFieldValueException("Thumbnail expected and " + abstractField.getClass().getName() + " found.");
                }
            }
            return arrayList;
        }
        return null;
    }
}
