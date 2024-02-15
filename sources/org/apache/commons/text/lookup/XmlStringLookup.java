package org.apache.commons.text.lookup;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.InputSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class XmlStringLookup extends AbstractStringLookup {
    private static final Map<String, Boolean> DEFAULT_FEATURES;
    static final XmlStringLookup INSTANCE;
    private final Map<String, Boolean> xPathFactoryFeatures;

    static {
        HashMap hashMap = new HashMap(1);
        DEFAULT_FEATURES = hashMap;
        hashMap.put("http://javax.xml.XMLConstants/feature/secure-processing", Boolean.TRUE);
        INSTANCE = new XmlStringLookup(hashMap);
    }

    public XmlStringLookup(Map<String, Boolean> map) {
        this.xPathFactoryFeatures = (Map) Objects.requireNonNull(map, "xPathFfactoryFeatures");
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(SPLIT_STR);
        if (split.length != 2) {
            throw IllegalArgumentExceptions.format("Bad XML key format [%s]; expected format is DocumentPath:XPath.", str);
        }
        String str2 = split[0];
        String substringAfter = StringUtils.substringAfter(str, 58);
        try {
            InputStream newInputStream = Files.newInputStream(Paths.get(str2, new String[0]), new OpenOption[0]);
            XPathFactory newInstance = XPathFactory.newInstance();
            for (Map.Entry<String, Boolean> entry : this.xPathFactoryFeatures.entrySet()) {
                newInstance.setFeature(entry.getKey(), entry.getValue().booleanValue());
            }
            String evaluate = newInstance.newXPath().evaluate(substringAfter, new InputSource(newInputStream));
            if (newInputStream != null) {
                newInputStream.close();
            }
            return evaluate;
        } catch (Exception e) {
            throw IllegalArgumentExceptions.format(e, "Error looking up XML document [%s] and XPath [%s].", str2, substringAfter);
        }
    }
}
