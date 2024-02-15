package org.apache.commons.text.lookup;

import java.util.Date;
import org.apache.commons.lang3.time.FastDateFormat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class DateStringLookup extends AbstractStringLookup {
    static final DateStringLookup INSTANCE = new DateStringLookup();

    private DateStringLookup() {
    }

    private String formatDate(long j, String str) {
        FastDateFormat fastDateFormat;
        if (str != null) {
            try {
                fastDateFormat = FastDateFormat.getInstance(str);
            } catch (Exception e) {
                throw IllegalArgumentExceptions.format(e, "Invalid date format: [%s]", str);
            }
        } else {
            fastDateFormat = null;
        }
        if (fastDateFormat == null) {
            fastDateFormat = FastDateFormat.getInstance();
        }
        return fastDateFormat.format(new Date(j));
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        return formatDate(System.currentTimeMillis(), str);
    }
}
