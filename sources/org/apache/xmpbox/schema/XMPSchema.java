package org.apache.xmpbox.schema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.XmpConstants;
import org.apache.xmpbox.type.AbstractField;
import org.apache.xmpbox.type.AbstractSimpleProperty;
import org.apache.xmpbox.type.AbstractStructuredType;
import org.apache.xmpbox.type.ArrayProperty;
import org.apache.xmpbox.type.Attribute;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.type.BooleanType;
import org.apache.xmpbox.type.Cardinality;
import org.apache.xmpbox.type.ComplexPropertyContainer;
import org.apache.xmpbox.type.DateType;
import org.apache.xmpbox.type.IntegerType;
import org.apache.xmpbox.type.TextType;
import org.apache.xmpbox.type.Types;

/* loaded from: classes2.dex */
public class XMPSchema extends AbstractStructuredType {
    public XMPSchema(XMPMetadata xMPMetadata, String str, String str2, String str3) {
        super(xMPMetadata, str, str2, str3);
        addNamespace(getNamespace(), getPrefix());
    }

    public XMPSchema(XMPMetadata xMPMetadata) {
        this(xMPMetadata, null, null, null);
    }

    public XMPSchema(XMPMetadata xMPMetadata, String str) {
        this(xMPMetadata, null, str, null);
    }

    public XMPSchema(XMPMetadata xMPMetadata, String str, String str2) {
        this(xMPMetadata, str, str2, null);
    }

    public AbstractField getAbstractProperty(String str) {
        for (AbstractField abstractField : getContainer().getAllProperties()) {
            if (abstractField.getPropertyName().equals(str)) {
                return abstractField;
            }
        }
        return null;
    }

    public Attribute getAboutAttribute() {
        return getAttribute(XmpConstants.ABOUT_NAME);
    }

    public String getAboutValue() {
        Attribute attribute = getAttribute(XmpConstants.ABOUT_NAME);
        return attribute != null ? attribute.getValue() : "";
    }

    public void setAbout(Attribute attribute) throws BadFieldValueException {
        if (XmpConstants.RDF_NAMESPACE.equals(attribute.getNamespace()) && XmpConstants.ABOUT_NAME.equals(attribute.getName())) {
            setAttribute(attribute);
            return;
        }
        throw new BadFieldValueException("Attribute 'about' must be named 'rdf:about' or 'about'");
    }

    public void setAboutAsSimple(String str) {
        if (str == null) {
            removeAttribute(XmpConstants.ABOUT_NAME);
        } else {
            setAttribute(new Attribute(XmpConstants.RDF_NAMESPACE, XmpConstants.ABOUT_NAME, str));
        }
    }

    private void setSpecifiedSimpleTypeProperty(Types types, String str, Object obj) {
        if (obj == null) {
            for (AbstractField abstractField : getContainer().getAllProperties()) {
                if (abstractField.getPropertyName().equals(str)) {
                    getContainer().removeProperty(abstractField);
                    return;
                }
            }
            return;
        }
        try {
            AbstractSimpleProperty instanciateSimpleProperty = getMetadata().getTypeMapping().instanciateSimpleProperty(null, getPrefix(), str, obj, types);
            for (AbstractField abstractField2 : getAllProperties()) {
                if (abstractField2.getPropertyName().equals(str)) {
                    removeProperty(abstractField2);
                    addProperty(instanciateSimpleProperty);
                    return;
                }
            }
            addProperty(instanciateSimpleProperty);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to create property with the specified type given in parameters", e);
        }
    }

    private void setSpecifiedSimpleTypeProperty(AbstractSimpleProperty abstractSimpleProperty) {
        for (AbstractField abstractField : getAllProperties()) {
            if (abstractField.getPropertyName().equals(abstractSimpleProperty.getPropertyName())) {
                removeProperty(abstractField);
                addProperty(abstractSimpleProperty);
                return;
            }
        }
        addProperty(abstractSimpleProperty);
    }

    public void setTextProperty(TextType textType) {
        setSpecifiedSimpleTypeProperty(textType);
    }

    public void setTextPropertyValue(String str, String str2) {
        setSpecifiedSimpleTypeProperty(Types.Text, str, str2);
    }

    public void setTextPropertyValueAsSimple(String str, String str2) {
        setTextPropertyValue(str, str2);
    }

    public TextType getUnqualifiedTextProperty(String str) {
        AbstractField abstractProperty = getAbstractProperty(str);
        if (abstractProperty != null) {
            if (abstractProperty instanceof TextType) {
                return (TextType) abstractProperty;
            }
            throw new IllegalArgumentException("Property asked is not a Text Property");
        }
        return null;
    }

    public String getUnqualifiedTextPropertyValue(String str) {
        TextType unqualifiedTextProperty = getUnqualifiedTextProperty(str);
        if (unqualifiedTextProperty == null) {
            return null;
        }
        return unqualifiedTextProperty.getStringValue();
    }

    public DateType getDateProperty(String str) {
        AbstractField abstractProperty = getAbstractProperty(str);
        if (abstractProperty != null) {
            if (abstractProperty instanceof DateType) {
                return (DateType) abstractProperty;
            }
            throw new IllegalArgumentException("Property asked is not a Date Property");
        }
        return null;
    }

    public Calendar getDatePropertyValueAsSimple(String str) {
        return getDatePropertyValue(str);
    }

    public Calendar getDatePropertyValue(String str) {
        AbstractField abstractProperty = getAbstractProperty(str);
        if (abstractProperty != null) {
            if (abstractProperty instanceof DateType) {
                return ((DateType) abstractProperty).getValue();
            }
            throw new IllegalArgumentException("Property asked is not a Date Property");
        }
        return null;
    }

    public void setDateProperty(DateType dateType) {
        setSpecifiedSimpleTypeProperty(dateType);
    }

    public void setDatePropertyValueAsSimple(String str, Calendar calendar) {
        setDatePropertyValue(str, calendar);
    }

    public void setDatePropertyValue(String str, Calendar calendar) {
        setSpecifiedSimpleTypeProperty(Types.Date, str, calendar);
    }

    public BooleanType getBooleanProperty(String str) {
        AbstractField abstractProperty = getAbstractProperty(str);
        if (abstractProperty != null) {
            if (abstractProperty instanceof BooleanType) {
                return (BooleanType) abstractProperty;
            }
            throw new IllegalArgumentException("Property asked is not a Boolean Property");
        }
        return null;
    }

    public Boolean getBooleanPropertyValueAsSimple(String str) {
        return getBooleanPropertyValue(str);
    }

    public Boolean getBooleanPropertyValue(String str) {
        AbstractField abstractProperty = getAbstractProperty(str);
        if (abstractProperty != null) {
            if (abstractProperty instanceof BooleanType) {
                return ((BooleanType) abstractProperty).getValue();
            }
            throw new IllegalArgumentException("Property asked is not a Boolean Property");
        }
        return null;
    }

    public void setBooleanProperty(BooleanType booleanType) {
        setSpecifiedSimpleTypeProperty(booleanType);
    }

    public void setBooleanPropertyValueAsSimple(String str, Boolean bool) {
        setBooleanPropertyValue(str, bool);
    }

    public void setBooleanPropertyValue(String str, Boolean bool) {
        setSpecifiedSimpleTypeProperty(Types.Boolean, str, bool);
    }

    public IntegerType getIntegerProperty(String str) {
        AbstractField abstractProperty = getAbstractProperty(str);
        if (abstractProperty != null) {
            if (abstractProperty instanceof IntegerType) {
                return (IntegerType) abstractProperty;
            }
            throw new IllegalArgumentException("Property asked is not an Integer Property");
        }
        return null;
    }

    public Integer getIntegerPropertyValueAsSimple(String str) {
        return getIntegerPropertyValue(str);
    }

    public Integer getIntegerPropertyValue(String str) {
        AbstractField abstractProperty = getAbstractProperty(str);
        if (abstractProperty != null) {
            if (abstractProperty instanceof IntegerType) {
                return ((IntegerType) abstractProperty).getValue();
            }
            throw new IllegalArgumentException("Property asked is not an Integer Property");
        }
        return null;
    }

    public void setIntegerProperty(IntegerType integerType) {
        setSpecifiedSimpleTypeProperty(integerType);
    }

    public void setIntegerPropertyValueAsSimple(String str, Integer num) {
        setIntegerPropertyValue(str, num);
    }

    public void setIntegerPropertyValue(String str, Integer num) {
        setSpecifiedSimpleTypeProperty(Types.Integer, str, num);
    }

    private void removeUnqualifiedArrayValue(String str, String str2) {
        ArrayProperty arrayProperty = (ArrayProperty) getAbstractProperty(str);
        if (arrayProperty != null) {
            ArrayList<AbstractField> arrayList = new ArrayList();
            Iterator<AbstractField> it = arrayProperty.getContainer().getAllProperties().iterator();
            while (it.hasNext()) {
                AbstractSimpleProperty abstractSimpleProperty = (AbstractSimpleProperty) it.next();
                if (abstractSimpleProperty.getStringValue().equals(str2)) {
                    arrayList.add(abstractSimpleProperty);
                }
            }
            for (AbstractField abstractField : arrayList) {
                arrayProperty.getContainer().removeProperty(abstractField);
            }
        }
    }

    public void removeUnqualifiedBagValue(String str, String str2) {
        removeUnqualifiedArrayValue(str, str2);
    }

    public void addBagValueAsSimple(String str, String str2) {
        internalAddBagValue(str, str2);
    }

    private void internalAddBagValue(String str, String str2) {
        ArrayProperty arrayProperty = (ArrayProperty) getAbstractProperty(str);
        TextType createTextType = createTextType(XmpConstants.LIST_NAME, str2);
        if (arrayProperty != null) {
            arrayProperty.getContainer().addProperty(createTextType);
            return;
        }
        ArrayProperty createArrayProperty = createArrayProperty(str, Cardinality.Bag);
        createArrayProperty.getContainer().addProperty(createTextType);
        addProperty(createArrayProperty);
    }

    public void addQualifiedBagValue(String str, String str2) {
        internalAddBagValue(str, str2);
    }

    public List<String> getUnqualifiedBagValueList(String str) {
        ArrayProperty arrayProperty = (ArrayProperty) getAbstractProperty(str);
        if (arrayProperty != null) {
            return arrayProperty.getElementsAsString();
        }
        return null;
    }

    public void removeUnqualifiedSequenceValue(String str, String str2) {
        removeUnqualifiedArrayValue(str, str2);
    }

    public void removeUnqualifiedArrayValue(String str, AbstractField abstractField) {
        ArrayProperty arrayProperty = (ArrayProperty) getAbstractProperty(str);
        if (arrayProperty != null) {
            ArrayList<AbstractField> arrayList = new ArrayList();
            Iterator<AbstractField> it = arrayProperty.getContainer().getAllProperties().iterator();
            while (it.hasNext()) {
                AbstractSimpleProperty abstractSimpleProperty = (AbstractSimpleProperty) it.next();
                if (abstractSimpleProperty.equals(abstractField)) {
                    arrayList.add(abstractSimpleProperty);
                }
            }
            for (AbstractField abstractField2 : arrayList) {
                arrayProperty.getContainer().removeProperty(abstractField2);
            }
        }
    }

    public void removeUnqualifiedSequenceValue(String str, AbstractField abstractField) {
        removeUnqualifiedArrayValue(str, abstractField);
    }

    public void addUnqualifiedSequenceValue(String str, String str2) {
        ArrayProperty arrayProperty = (ArrayProperty) getAbstractProperty(str);
        TextType createTextType = createTextType(XmpConstants.LIST_NAME, str2);
        if (arrayProperty != null) {
            arrayProperty.getContainer().addProperty(createTextType);
            return;
        }
        ArrayProperty createArrayProperty = createArrayProperty(str, Cardinality.Seq);
        createArrayProperty.getContainer().addProperty(createTextType);
        addProperty(createArrayProperty);
    }

    public void addBagValue(String str, AbstractField abstractField) {
        ArrayProperty arrayProperty = (ArrayProperty) getAbstractProperty(str);
        if (arrayProperty != null) {
            arrayProperty.getContainer().addProperty(abstractField);
            return;
        }
        ArrayProperty createArrayProperty = createArrayProperty(str, Cardinality.Bag);
        createArrayProperty.getContainer().addProperty(abstractField);
        addProperty(createArrayProperty);
    }

    public void addUnqualifiedSequenceValue(String str, AbstractField abstractField) {
        ArrayProperty arrayProperty = (ArrayProperty) getAbstractProperty(str);
        if (arrayProperty != null) {
            arrayProperty.getContainer().addProperty(abstractField);
            return;
        }
        ArrayProperty createArrayProperty = createArrayProperty(str, Cardinality.Seq);
        createArrayProperty.getContainer().addProperty(abstractField);
        addProperty(createArrayProperty);
    }

    public List<String> getUnqualifiedSequenceValueList(String str) {
        ArrayProperty arrayProperty = (ArrayProperty) getAbstractProperty(str);
        if (arrayProperty != null) {
            return arrayProperty.getElementsAsString();
        }
        return null;
    }

    public void removeUnqualifiedSequenceDateValue(String str, Calendar calendar) {
        ArrayProperty arrayProperty = (ArrayProperty) getAbstractProperty(str);
        if (arrayProperty != null) {
            ArrayList<AbstractField> arrayList = new ArrayList();
            for (AbstractField abstractField : arrayProperty.getContainer().getAllProperties()) {
                if ((abstractField instanceof DateType) && ((DateType) abstractField).getValue().equals(calendar)) {
                    arrayList.add(abstractField);
                }
            }
            for (AbstractField abstractField2 : arrayList) {
                arrayProperty.getContainer().removeProperty(abstractField2);
            }
        }
    }

    public void addSequenceDateValueAsSimple(String str, Calendar calendar) {
        addUnqualifiedSequenceDateValue(str, calendar);
    }

    public void addUnqualifiedSequenceDateValue(String str, Calendar calendar) {
        addUnqualifiedSequenceValue(str, getMetadata().getTypeMapping().createDate(null, XmpConstants.DEFAULT_RDF_LOCAL_NAME, XmpConstants.LIST_NAME, calendar));
    }

    public List<Calendar> getUnqualifiedSequenceDateValueList(String str) {
        ArrayProperty arrayProperty = (ArrayProperty) getAbstractProperty(str);
        if (arrayProperty != null) {
            ArrayList arrayList = new ArrayList();
            for (AbstractField abstractField : arrayProperty.getContainer().getAllProperties()) {
                if (abstractField instanceof DateType) {
                    arrayList.add(((DateType) abstractField).getValue());
                }
            }
            return arrayList;
        }
        return null;
    }

    public void reorganizeAltOrder(ComplexPropertyContainer complexPropertyContainer) {
        Iterator<AbstractField> it = complexPropertyContainer.getAllProperties().iterator();
        if (it.hasNext() && it.next().getAttribute(XmpConstants.LANG_NAME).getValue().equals(XmpConstants.X_DEFAULT)) {
            return;
        }
        AbstractField abstractField = null;
        boolean z = false;
        while (it.hasNext() && !z) {
            abstractField = it.next();
            if (abstractField.getAttribute(XmpConstants.LANG_NAME).getValue().equals(XmpConstants.X_DEFAULT)) {
                complexPropertyContainer.removeProperty(abstractField);
                z = true;
            }
        }
        if (z) {
            ArrayList<AbstractField> arrayList = new ArrayList();
            ArrayList<AbstractField> arrayList2 = new ArrayList();
            arrayList.add(abstractField);
            for (AbstractField abstractField2 : complexPropertyContainer.getAllProperties()) {
                arrayList.add(abstractField2);
                arrayList2.add(abstractField2);
            }
            for (AbstractField abstractField3 : arrayList2) {
                complexPropertyContainer.removeProperty(abstractField3);
            }
            for (AbstractField abstractField4 : arrayList) {
                complexPropertyContainer.addProperty(abstractField4);
            }
        }
    }

    public void setUnqualifiedLanguagePropertyValue(String str, String str2, String str3) {
        if (str2 == null || str2.isEmpty()) {
            str2 = XmpConstants.X_DEFAULT;
        }
        AbstractField abstractProperty = getAbstractProperty(str);
        if (abstractProperty != null) {
            if (abstractProperty instanceof ArrayProperty) {
                ArrayProperty arrayProperty = (ArrayProperty) abstractProperty;
                for (AbstractField abstractField : arrayProperty.getContainer().getAllProperties()) {
                    if (abstractField.getAttribute(XmpConstants.LANG_NAME).getValue().equals(str2)) {
                        arrayProperty.getContainer().removeProperty(abstractField);
                        if (str3 != null) {
                            TextType createTextType = createTextType(XmpConstants.LIST_NAME, str3);
                            createTextType.setAttribute(new Attribute("http://www.w3.org/XML/1998/namespace", XmpConstants.LANG_NAME, str2));
                            arrayProperty.getContainer().addProperty(createTextType);
                        }
                        reorganizeAltOrder(arrayProperty.getContainer());
                        return;
                    }
                }
                TextType createTextType2 = createTextType(XmpConstants.LIST_NAME, str3);
                createTextType2.setAttribute(new Attribute("http://www.w3.org/XML/1998/namespace", XmpConstants.LANG_NAME, str2));
                arrayProperty.getContainer().addProperty(createTextType2);
                reorganizeAltOrder(arrayProperty.getContainer());
                return;
            }
            return;
        }
        ArrayProperty createArrayProperty = createArrayProperty(str, Cardinality.Alt);
        AbstractField createTextType3 = createTextType(XmpConstants.LIST_NAME, str3);
        createTextType3.setAttribute(new Attribute("http://www.w3.org/XML/1998/namespace", XmpConstants.LANG_NAME, str2));
        createArrayProperty.getContainer().addProperty(createTextType3);
        addProperty(createArrayProperty);
    }

    public String getUnqualifiedLanguagePropertyValue(String str, String str2) {
        if (str2 == null) {
            str2 = XmpConstants.X_DEFAULT;
        }
        AbstractField abstractProperty = getAbstractProperty(str);
        if (abstractProperty != null) {
            if (abstractProperty instanceof ArrayProperty) {
                for (AbstractField abstractField : ((ArrayProperty) abstractProperty).getContainer().getAllProperties()) {
                    Attribute attribute = abstractField.getAttribute(XmpConstants.LANG_NAME);
                    if (attribute != null && attribute.getValue().equals(str2)) {
                        return ((TextType) abstractField).getStringValue();
                    }
                }
                return null;
            }
            throw new IllegalArgumentException("The property '" + str + "' is not of Lang Alt type");
        }
        return null;
    }

    public List<String> getUnqualifiedLanguagePropertyLanguagesValue(String str) {
        AbstractField abstractProperty = getAbstractProperty(str);
        if (abstractProperty != null) {
            if (abstractProperty instanceof ArrayProperty) {
                List<AbstractField> allProperties = ((ArrayProperty) abstractProperty).getContainer().getAllProperties();
                ArrayList arrayList = new ArrayList(allProperties.size());
                for (AbstractField abstractField : allProperties) {
                    Attribute attribute = abstractField.getAttribute(XmpConstants.LANG_NAME);
                    arrayList.add(attribute != null ? attribute.getValue() : XmpConstants.X_DEFAULT);
                }
                return arrayList;
            }
            throw new IllegalArgumentException("The property '" + str + "' is not of Lang Alt type");
        }
        return null;
    }

    public void merge(XMPSchema xMPSchema) throws IOException {
        if (!xMPSchema.getClass().equals(getClass())) {
            throw new IOException("Can only merge schemas of the same type.");
        }
        for (Attribute attribute : xMPSchema.getAllAttributes()) {
            if (attribute.getNamespace().equals(getNamespace())) {
                setAttribute(attribute);
            }
        }
        for (AbstractField abstractField : xMPSchema.getContainer().getAllProperties()) {
            if (abstractField.getPrefix().equals(getPrefix())) {
                if (abstractField instanceof ArrayProperty) {
                    String propertyName = abstractField.getPropertyName();
                    for (AbstractField abstractField2 : getAllProperties()) {
                        if ((abstractField2 instanceof ArrayProperty) && abstractField2.getPropertyName().equals(propertyName) && mergeComplexProperty(((ArrayProperty) abstractField).getContainer().getAllProperties().iterator(), (ArrayProperty) abstractField2)) {
                            return;
                        }
                    }
                    continue;
                } else {
                    addProperty(abstractField);
                }
            }
        }
    }

    private boolean mergeComplexProperty(Iterator<AbstractField> it, ArrayProperty arrayProperty) {
        while (it.hasNext()) {
            TextType textType = (TextType) it.next();
            Iterator<AbstractField> it2 = arrayProperty.getContainer().getAllProperties().iterator();
            while (it2.hasNext()) {
                if (((TextType) it2.next()).getStringValue().equals(textType.getStringValue())) {
                    return true;
                }
            }
            arrayProperty.getContainer().addProperty(textType);
        }
        return false;
    }

    public List<AbstractField> getUnqualifiedArrayList(String str) throws BadFieldValueException {
        ArrayProperty arrayProperty;
        Iterator<AbstractField> it = getAllProperties().iterator();
        while (true) {
            if (!it.hasNext()) {
                arrayProperty = null;
                break;
            }
            AbstractField next = it.next();
            if (next.getPropertyName().equals(str)) {
                if (next instanceof ArrayProperty) {
                    arrayProperty = (ArrayProperty) next;
                } else {
                    throw new BadFieldValueException("Property asked is not an array");
                }
            }
        }
        if (arrayProperty != null) {
            return new ArrayList(arrayProperty.getContainer().getAllProperties());
        }
        return null;
    }

    public AbstractSimpleProperty instanciateSimple(String str, Object obj) {
        return getMetadata().getTypeMapping().instanciateSimpleField(getClass(), null, getPrefix(), str, obj);
    }
}
