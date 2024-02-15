package org.apache.commons.text;

import java.text.Format;
import java.util.Locale;

@FunctionalInterface
/* loaded from: classes2.dex */
public interface FormatFactory {
    Format getFormat(String str, String str2, Locale locale);
}
