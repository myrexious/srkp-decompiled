package org.apache.xmpbox.schema;

import java.util.Calendar;
import java.util.List;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.MIMEType;
import org.apache.xmpbox.type.PropertyType;
import org.apache.xmpbox.type.StructuredType;
import org.apache.xmpbox.type.TextType;
import org.apache.xmpbox.type.Types;

@StructuredType(namespace = "http://purl.org/dc/elements/1.1/", preferedPrefix = "dc")
/* loaded from: classes2.dex */
public class DublinCoreSchema extends XMPSchema {
    @PropertyType(card = Cardinality.Bag, type = Types.Text)
    public static final String CONTRIBUTOR = "contributor";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String COVERAGE = "coverage";
    @PropertyType(card = Cardinality.Seq, type = Types.Text)
    public static final String CREATOR = "creator";
    @PropertyType(card = Cardinality.Seq, type = Types.Date)
    public static final String DATE = "date";
    @PropertyType(card = Cardinality.Simple, type = Types.LangAlt)
    public static final String DESCRIPTION = "description";
    @PropertyType(card = Cardinality.Simple, type = Types.MIMEType)
    public static final String FORMAT = "format";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String IDENTIFIER = "identifier";
    @PropertyType(card = Cardinality.Bag, type = Types.Text)
    public static final String LANGUAGE = "language";
    @PropertyType(card = Cardinality.Bag, type = Types.Text)
    public static final String PUBLISHER = "publisher";
    @PropertyType(card = Cardinality.Bag, type = Types.Text)
    public static final String RELATION = "relation";
    @PropertyType(card = Cardinality.Simple, type = Types.LangAlt)
    public static final String RIGHTS = "rights";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String SOURCE = "source";
    @PropertyType(card = Cardinality.Bag, type = Types.Text)
    public static final String SUBJECT = "subject";
    @PropertyType(card = Cardinality.Simple, type = Types.LangAlt)
    public static final String TITLE = "title";
    @PropertyType(card = Cardinality.Bag, type = Types.Text)
    public static final String TYPE = "type";

    public DublinCoreSchema(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
    }

    public DublinCoreSchema(XMPMetadata xMPMetadata, String str) {
        super(xMPMetadata, str);
    }

    public void addContributor(String str) {
        addQualifiedBagValue(CONTRIBUTOR, str);
    }

    public void removeContributor(String str) {
        removeUnqualifiedBagValue(CONTRIBUTOR, str);
    }

    public void setCoverage(String str) {
        addProperty(createTextType(COVERAGE, str));
    }

    public void setCoverageProperty(TextType textType) {
        addProperty(textType);
    }

    public void addCreator(String str) {
        addUnqualifiedSequenceValue(CREATOR, str);
    }

    public void removeCreator(String str) {
        removeUnqualifiedSequenceValue(CREATOR, str);
    }

    public void addDate(Calendar calendar) {
        addUnqualifiedSequenceDateValue("date", calendar);
    }

    public void removeDate(Calendar calendar) {
        removeUnqualifiedSequenceDateValue("date", calendar);
    }

    public void addDescription(String str, String str2) {
        setUnqualifiedLanguagePropertyValue("description", str, str2);
    }

    public void setDescription(String str) {
        addDescription(null, str);
    }

    public void setFormat(String str) {
        addProperty(createTextType("format", str));
    }

    public void setIdentifier(String str) {
        addProperty(createTextType(IDENTIFIER, str));
    }

    public void setIdentifierProperty(TextType textType) {
        addProperty(textType);
    }

    public void addLanguage(String str) {
        addQualifiedBagValue(LANGUAGE, str);
    }

    public void removeLanguage(String str) {
        removeUnqualifiedBagValue(LANGUAGE, str);
    }

    public void addPublisher(String str) {
        addQualifiedBagValue(PUBLISHER, str);
    }

    public void removePublisher(String str) {
        removeUnqualifiedBagValue(PUBLISHER, str);
    }

    public void addRelation(String str) {
        addQualifiedBagValue(RELATION, str);
    }

    public void removeRelation(String str) {
        removeUnqualifiedBagValue(RELATION, str);
    }

    public void addRights(String str, String str2) {
        setUnqualifiedLanguagePropertyValue(RIGHTS, str, str2);
    }

    public void setSource(String str) {
        addProperty(createTextType("source", str));
    }

    public void setSourceProperty(TextType textType) {
        addProperty(textType);
    }

    public void setFormatProperty(MIMEType mIMEType) {
        addProperty(mIMEType);
    }

    public void addSubject(String str) {
        addQualifiedBagValue(SUBJECT, str);
    }

    public void removeSubject(String str) {
        removeUnqualifiedBagValue(SUBJECT, str);
    }

    public void setTitle(String str, String str2) {
        setUnqualifiedLanguagePropertyValue("title", str, str2);
    }

    public void setTitle(String str) {
        setTitle(null, str);
    }

    public void addTitle(String str, String str2) {
        setTitle(str, str2);
    }

    public void addType(String str) {
        addQualifiedBagValue("type", str);
    }

    public ArrayProperty getContributorsProperty() {
        return (ArrayProperty) getProperty(CONTRIBUTOR);
    }

    public List<String> getContributors() {
        return getUnqualifiedBagValueList(CONTRIBUTOR);
    }

    public TextType getCoverageProperty() {
        return (TextType) getProperty(COVERAGE);
    }

    public String getCoverage() {
        TextType textType = (TextType) getProperty(COVERAGE);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public ArrayProperty getCreatorsProperty() {
        return (ArrayProperty) getProperty(CREATOR);
    }

    public List<String> getCreators() {
        return getUnqualifiedSequenceValueList(CREATOR);
    }

    public ArrayProperty getDatesProperty() {
        return (ArrayProperty) getProperty("date");
    }

    public List<Calendar> getDates() {
        return getUnqualifiedSequenceDateValueList("date");
    }

    public ArrayProperty getDescriptionProperty() {
        return (ArrayProperty) getProperty("description");
    }

    public List<String> getDescriptionLanguages() {
        return getUnqualifiedLanguagePropertyLanguagesValue("description");
    }

    public String getDescription(String str) {
        return getUnqualifiedLanguagePropertyValue("description", str);
    }

    public String getDescription() {
        return getDescription(null);
    }

    public TextType getFormatProperty() {
        return (TextType) getProperty("format");
    }

    public String getFormat() {
        TextType textType = (TextType) getProperty("format");
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public TextType getIdentifierProperty() {
        return (TextType) getProperty(IDENTIFIER);
    }

    public String getIdentifier() {
        TextType textType = (TextType) getProperty(IDENTIFIER);
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public ArrayProperty getLanguagesProperty() {
        return (ArrayProperty) getProperty(LANGUAGE);
    }

    public List<String> getLanguages() {
        return getUnqualifiedBagValueList(LANGUAGE);
    }

    public ArrayProperty getPublishersProperty() {
        return (ArrayProperty) getProperty(PUBLISHER);
    }

    public List<String> getPublishers() {
        return getUnqualifiedBagValueList(PUBLISHER);
    }

    public ArrayProperty getRelationsProperty() {
        return (ArrayProperty) getProperty(RELATION);
    }

    public List<String> getRelations() {
        return getUnqualifiedBagValueList(RELATION);
    }

    public ArrayProperty getRightsProperty() {
        return (ArrayProperty) getProperty(RIGHTS);
    }

    public List<String> getRightsLanguages() {
        return getUnqualifiedLanguagePropertyLanguagesValue(RIGHTS);
    }

    public String getRights(String str) {
        return getUnqualifiedLanguagePropertyValue(RIGHTS, str);
    }

    public String getRights() {
        return getRights(null);
    }

    public TextType getSourceProperty() {
        return (TextType) getProperty("source");
    }

    public String getSource() {
        TextType textType = (TextType) getProperty("source");
        if (textType == null) {
            return null;
        }
        return textType.getStringValue();
    }

    public ArrayProperty getSubjectsProperty() {
        return (ArrayProperty) getProperty(SUBJECT);
    }

    public List<String> getSubjects() {
        return getUnqualifiedBagValueList(SUBJECT);
    }

    public ArrayProperty getTitleProperty() {
        return (ArrayProperty) getProperty("title");
    }

    public List<String> getTitleLanguages() {
        return getUnqualifiedLanguagePropertyLanguagesValue("title");
    }

    public String getTitle(String str) {
        return getUnqualifiedLanguagePropertyValue("title", str);
    }

    public String getTitle() {
        return getTitle(null);
    }

    public ArrayProperty getTypesProperty() {
        return (ArrayProperty) getProperty("type");
    }

    public List<String> getTypes() {
        return getUnqualifiedBagValueList("type");
    }

    public void removeType(String str) {
        removeUnqualifiedBagValue("type", str);
    }
}
