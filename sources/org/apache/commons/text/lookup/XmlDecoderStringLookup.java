package org.apache.commons.text.lookup;

import org.apache.commons.text.StringEscapeUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class XmlDecoderStringLookup extends AbstractStringLookup {
    static final XmlDecoderStringLookup INSTANCE = new XmlDecoderStringLookup();

    private XmlDecoderStringLookup() {
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        return StringEscapeUtils.unescapeXml(str);
    }
}
