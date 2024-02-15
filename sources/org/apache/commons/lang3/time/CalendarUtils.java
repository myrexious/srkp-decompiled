package org.apache.commons.lang3.time;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;

/* loaded from: classes2.dex */
public class CalendarUtils {
    public static final CalendarUtils INSTANCE = new CalendarUtils(Calendar.getInstance());
    private final Calendar calendar;
    private final Locale locale;

    public static CalendarUtils getInstance(Locale locale) {
        return new CalendarUtils(Calendar.getInstance(locale), locale);
    }

    public CalendarUtils(Calendar calendar) {
        this(calendar, Locale.getDefault());
    }

    CalendarUtils(Calendar calendar, Locale locale) {
        this.calendar = (Calendar) Objects.requireNonNull(calendar, "calendar");
        this.locale = (Locale) Objects.requireNonNull(locale, "locale");
    }

    public int getDayOfMonth() {
        return this.calendar.get(5);
    }

    public int getDayOfYear() {
        return this.calendar.get(6);
    }

    public int getMonth() {
        return this.calendar.get(2);
    }

    String[] getMonthDisplayNames(int i) {
        Map<String, Integer> displayNames = this.calendar.getDisplayNames(2, i, this.locale);
        if (displayNames == null) {
            return null;
        }
        final String[] strArr = new String[displayNames.size()];
        displayNames.forEach(new BiConsumer() { // from class: org.apache.commons.lang3.time.CalendarUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                CalendarUtils.lambda$getMonthDisplayNames$0(strArr, (String) obj, (Integer) obj2);
            }
        });
        return strArr;
    }

    public static /* synthetic */ void lambda$getMonthDisplayNames$0(String[] strArr, String str, Integer num) {
        strArr[num.intValue()] = str;
    }

    public String[] getStandaloneLongMonthNames() {
        return getMonthDisplayNames(32770);
    }

    public String[] getStandaloneShortMonthNames() {
        return getMonthDisplayNames(TiffTagConstants.COMPRESSION_VALUE_EPSON_ERF_COMPRESSED);
    }

    public int getYear() {
        return this.calendar.get(1);
    }
}
