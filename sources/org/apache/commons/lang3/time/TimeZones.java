package org.apache.commons.lang3.time;

import java.util.TimeZone;
import java.util.function.Supplier;
import org.apache.commons.lang3.ObjectUtils;

/* loaded from: classes2.dex */
public class TimeZones {
    public static final String GMT_ID = "GMT";
    public static final TimeZone GMT = TimeZone.getTimeZone(GMT_ID);

    private TimeZones() {
    }

    public static TimeZone toTimeZone(TimeZone timeZone) {
        return (TimeZone) ObjectUtils.getIfNull(timeZone, new Supplier() { // from class: org.apache.commons.lang3.time.TimeZones$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                TimeZone timeZone2;
                timeZone2 = TimeZone.getDefault();
                return timeZone2;
            }
        });
    }
}
