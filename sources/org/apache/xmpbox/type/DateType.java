package org.apache.xmpbox.type;

import java.io.IOException;
import java.util.Calendar;
import org.apache.xmpbox.DateConverter;
import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public class DateType extends AbstractSimpleProperty {
    private Calendar dateValue;

    public DateType(XMPMetadata xMPMetadata, String str, String str2, String str3, Object obj) {
        super(xMPMetadata, str, str2, str3, obj);
    }

    private void setValueFromCalendar(Calendar calendar) {
        this.dateValue = calendar;
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public Calendar getValue() {
        return this.dateValue;
    }

    private boolean isGoodType(Object obj) {
        if (obj instanceof Calendar) {
            return true;
        }
        if (obj instanceof String) {
            try {
                DateConverter.toCalendar((String) obj);
                return true;
            } catch (IOException unused) {
            }
        }
        return false;
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public void setValue(Object obj) {
        if (!isGoodType(obj)) {
            if (obj == null) {
                throw new IllegalArgumentException("Value null is not allowed for the Date type");
            }
            throw new IllegalArgumentException("Value given is not allowed for the Date type: " + obj.getClass() + ", value: " + obj);
        } else if (obj instanceof String) {
            setValueFromString((String) obj);
        } else {
            setValueFromCalendar((Calendar) obj);
        }
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public String getStringValue() {
        return DateConverter.toISO8601(this.dateValue);
    }

    private void setValueFromString(String str) {
        try {
            setValueFromCalendar(DateConverter.toCalendar(str));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
