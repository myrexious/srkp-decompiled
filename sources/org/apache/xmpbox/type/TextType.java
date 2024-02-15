package org.apache.xmpbox.type;

import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import org.apache.xmpbox.XMPMetadata;

/* loaded from: classes2.dex */
public class TextType extends AbstractSimpleProperty {
    private String textValue;

    public TextType(XMPMetadata xMPMetadata, String str, String str2, String str3, Object obj) {
        super(xMPMetadata, str, str2, str3, obj);
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public void setValue(Object obj) {
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException("Value given is not allowed for the Text type : '" + obj + OperatorName.SHOW_TEXT_LINE);
        }
        this.textValue = (String) obj;
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public String getStringValue() {
        return this.textValue;
    }

    @Override // org.apache.xmpbox.type.AbstractSimpleProperty
    public Object getValue() {
        return this.textValue;
    }
}
