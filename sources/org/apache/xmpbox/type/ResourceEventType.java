package org.apache.xmpbox.type;

import java.util.Calendar;
import org.apache.xmpbox.XMPMetadata;

@StructuredType(namespace = "http://ns.adobe.com/xap/1.0/sType/ResourceEvent#", preferedPrefix = "stEvt")
/* loaded from: classes2.dex */
public class ResourceEventType extends AbstractStructuredType {
    @PropertyType(card = Cardinality.Simple, type = Types.Choice)
    public static final String ACTION = "action";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String CHANGED = "changed";
    @PropertyType(card = Cardinality.Simple, type = Types.GUID)
    public static final String INSTANCE_ID = "instanceID";
    @PropertyType(card = Cardinality.Simple, type = Types.Text)
    public static final String PARAMETERS = "parameters";
    @PropertyType(card = Cardinality.Simple, type = Types.AgentName)
    public static final String SOFTWARE_AGENT = "softwareAgent";
    @PropertyType(card = Cardinality.Simple, type = Types.Date)
    public static final String WHEN = "when";

    public ResourceEventType(XMPMetadata xMPMetadata) {
        super(xMPMetadata);
        addNamespace(getNamespace(), getPreferedPrefix());
    }

    public String getInstanceID() {
        return getPropertyValueAsString("instanceID");
    }

    public void setInstanceID(String str) {
        addSimpleProperty("instanceID", str);
    }

    public String getSoftwareAgent() {
        return getPropertyValueAsString(SOFTWARE_AGENT);
    }

    public void setSoftwareAgent(String str) {
        addSimpleProperty(SOFTWARE_AGENT, str);
    }

    public Calendar getWhen() {
        return getDatePropertyAsCalendar(WHEN);
    }

    public void setWhen(Calendar calendar) {
        addSimpleProperty(WHEN, calendar);
    }

    public String getAction() {
        return getPropertyValueAsString(ACTION);
    }

    public void setAction(String str) {
        addSimpleProperty(ACTION, str);
    }

    public String getChanged() {
        return getPropertyValueAsString(CHANGED);
    }

    public void setChanged(String str) {
        addSimpleProperty(CHANGED, str);
    }

    public String getParameters() {
        return getPropertyValueAsString(PARAMETERS);
    }

    public void setParameters(String str) {
        addSimpleProperty(PARAMETERS, str);
    }
}
