package org.apache.commons.text.lookup;

import org.apache.commons.text.StringEscapeUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class XmlEncoderStringLookup extends AbstractStringLookup {
    static final XmlEncoderStringLookup INSTANCE = new XmlEncoderStringLookup();

    XmlEncoderStringLookup() {
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        return StringEscapeUtils.escapeXml10(str);
    }
}
