package org.apache.xmpbox.type;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public class BooleanType extends AbstractSimpleProperty {
    public static final String FALSE = "False";
    public static final String TRUE = "True";
    private boolean booleanValue;

    public BooleanType(XMPMetadata xMPMetadata, String str, String str2, String str3, Object obj) {
        super(xMPMetadata, str, str2, str3, obj);
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public Boolean getValue() {
        return Boolean.valueOf(this.booleanValue);
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public void setValue(Object obj) {
        if (obj instanceof Boolean) {
            this.booleanValue = ((Boolean) obj).booleanValue();
        } else if (obj instanceof String) {
            String upperCase = obj.toString().trim().toUpperCase();
            if ("TRUE".equals(upperCase)) {
                this.booleanValue = true;
            } else if ("FALSE".equals(upperCase)) {
                this.booleanValue = false;
            } else {
                throw new IllegalArgumentException("Not a valid boolean value : '" + obj + OperatorName.SHOW_TEXT_LINE);
            }
        } else {
            throw new IllegalArgumentException("Value given is not allowed for the Boolean type.");
        }
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public String getStringValue() {
        return this.booleanValue ? TRUE : FALSE;
    }
}
