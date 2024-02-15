package org.apache.xmpbox.type;

import java.util.Calendar;
import org.apache.xmpbox.XMPMetadata;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/sType/Version#", preferedPrefix = "stVer")
/* loaded from: classes2.dex */
public class VersionType extends AbstractStructuredType {
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String COMMENTS = "comments";
    @PropertyType(card = Cardinality.Simple, type = Types.ResourceEvent)
    public static final String EVENT = "event";
    @PropertyType(card = Cardinality.Simple, type = Types.ProperName)
    public static final String MODIFIER = "modifier";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String MODIFY_DATE = "modifyDate";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String VERSION = "version";

    public VersionType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
        addNamespace(getNamespace(), getPreferedPrefix());
    }

    public String getComments() {
        return getPropertyValueAsString(COMMENTS);
    }

    public void setComments(String str) {
        addSimpleProperty(COMMENTS, str);
    }

    public ResourceEventType getEvent() {
        return (ResourceEventType) getFirstEquivalentProperty("event", ResourceEventType.class);
    }

    public void setEvent(ResourceEventType resourceEventType) {
        addProperty(resourceEventType);
    }

    public Calendar getModifyDate() {
        return getDatePropertyAsCalendar(MODIFY_DATE);
    }

    public void setModifyDate(Calendar calendar) {
        addSimpleProperty(MODIFY_DATE, calendar);
    }

    public String getVersion() {
        return getPropertyValueAsString(VERSION);
    }

    public void setVersion(String str) {
        addSimpleProperty(VERSION, str);
    }

    public String getModifier() {
        return getPropertyValueAsString(MODIFIER);
    }

    public void setModifier(String str) {
        addSimpleProperty(MODIFIER, str);
    }
}
