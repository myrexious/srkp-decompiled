package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import net.openid.appauth.AuthState;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.FastDatePrinter;

/* loaded from: classes2.dex */
public class FastDatePrinter implements DatePrinter, Serializable {
    public static final int FULL = 0;
    public static final int LONG = 1;
    private static final int MAX_DIGITS = 10;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static final long serialVersionUID = 1;
    private final Locale locale;
    private transient int maxLengthEstimate;
    private final String pattern;
    private transient Rule[] rules;
    private final TimeZone timeZone;
    private static final Rule[] EMPTY_RULE_ARRAY = new Rule[0];
    private static final ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = new ConcurrentHashMap(7);

    /* loaded from: classes2.dex */
    public interface NumberRule extends Rule {
        void appendTo(Appendable appendable, int i) throws IOException;
    }

    /* loaded from: classes2.dex */
    public interface Rule {
        void appendTo(Appendable appendable, Calendar calendar) throws IOException;

        int estimateLength();
    }

    public FastDatePrinter(String str, TimeZone timeZone, Locale locale) {
        this.pattern = str;
        this.timeZone = timeZone;
        this.locale = LocaleUtils.toLocale(locale);
        init();
    }

    private void init() {
        Rule[] ruleArr = (Rule[]) parsePattern().toArray(EMPTY_RULE_ARRAY);
        this.rules = ruleArr;
        int length = ruleArr.length;
        int i = 0;
        while (true) {
            length--;
            if (length >= 0) {
                i += this.rules[length].estimateLength();
            } else {
                this.maxLengthEstimate = i;
                return;
            }
        }
    }

    protected List<Rule> parsePattern() {
        int i;
        Object stringLiteral;
        Object obj;
        Object selectNumberRule;
        Object timeZoneNameRule;
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(this.locale);
        ArrayList arrayList = new ArrayList();
        String[] eras = dateFormatSymbols.getEras();
        String[] months = dateFormatSymbols.getMonths();
        String[] shortMonths = dateFormatSymbols.getShortMonths();
        String[] weekdays = dateFormatSymbols.getWeekdays();
        String[] shortWeekdays = dateFormatSymbols.getShortWeekdays();
        String[] amPmStrings = dateFormatSymbols.getAmPmStrings();
        int length = this.pattern.length();
        int i2 = 1;
        int[] iArr = new int[1];
        int i3 = 0;
        int i4 = 0;
        while (i4 < length) {
            iArr[i3] = i4;
            String parseToken = parseToken(this.pattern, iArr);
            int i5 = iArr[i3];
            int length2 = parseToken.length();
            if (length2 == 0) {
                return arrayList;
            }
            char charAt = parseToken.charAt(i3);
            if (charAt != '\'') {
                if (charAt == 'S') {
                    selectNumberRule = selectNumberRule(14, length2);
                } else if (charAt == 'a') {
                    selectNumberRule = new TextField(9, amPmStrings);
                } else if (charAt == 'd') {
                    selectNumberRule = selectNumberRule(5, length2);
                } else if (charAt == 'h') {
                    selectNumberRule = new TwelveHourField(selectNumberRule(10, length2));
                } else if (charAt == 'k') {
                    selectNumberRule = new TwentyFourHourField(selectNumberRule(11, length2));
                } else if (charAt == 'm') {
                    selectNumberRule = selectNumberRule(12, length2);
                } else if (charAt == 's') {
                    selectNumberRule = selectNumberRule(13, length2);
                } else if (charAt == 'u') {
                    selectNumberRule = new DayInWeekField(selectNumberRule(7, length2));
                } else if (charAt != 'w') {
                    if (charAt != 'y') {
                        if (charAt != 'z') {
                            switch (charAt) {
                                case 'D':
                                    selectNumberRule = selectNumberRule(6, length2);
                                    break;
                                case 'E':
                                    selectNumberRule = new TextField(7, length2 < 4 ? shortWeekdays : weekdays);
                                    break;
                                case 'F':
                                    selectNumberRule = selectNumberRule(8, length2);
                                    break;
                                case 'G':
                                    timeZoneNameRule = new TextField(0, eras);
                                    i = 0;
                                    break;
                                case 'H':
                                    selectNumberRule = selectNumberRule(11, length2);
                                    break;
                                default:
                                    switch (charAt) {
                                        case 'K':
                                            selectNumberRule = selectNumberRule(10, length2);
                                            break;
                                        case 'L':
                                            if (length2 < 4) {
                                                if (length2 != 3) {
                                                    if (length2 == 2) {
                                                        selectNumberRule = TwoDigitMonthField.INSTANCE;
                                                        break;
                                                    } else {
                                                        selectNumberRule = UnpaddedMonthField.INSTANCE;
                                                        break;
                                                    }
                                                } else {
                                                    obj = new TextField(2, CalendarUtils.getInstance(this.locale).getStandaloneShortMonthNames());
                                                    break;
                                                }
                                            } else {
                                                obj = new TextField(2, CalendarUtils.getInstance(this.locale).getStandaloneLongMonthNames());
                                                break;
                                            }
                                        case 'M':
                                            if (length2 < 4) {
                                                if (length2 != 3) {
                                                    if (length2 == 2) {
                                                        selectNumberRule = TwoDigitMonthField.INSTANCE;
                                                        break;
                                                    } else {
                                                        selectNumberRule = UnpaddedMonthField.INSTANCE;
                                                        break;
                                                    }
                                                } else {
                                                    obj = new TextField(2, shortMonths);
                                                    break;
                                                }
                                            } else {
                                                obj = new TextField(2, months);
                                                break;
                                            }
                                        default:
                                            switch (charAt) {
                                                case 'W':
                                                    selectNumberRule = selectNumberRule(4, length2);
                                                    break;
                                                case 'X':
                                                    selectNumberRule = Iso8601_Rule.getRule(length2);
                                                    break;
                                                case 'Y':
                                                    break;
                                                case 'Z':
                                                    if (length2 != 1) {
                                                        if (length2 == 2) {
                                                            selectNumberRule = Iso8601_Rule.ISO8601_HOURS_COLON_MINUTES;
                                                            break;
                                                        } else {
                                                            selectNumberRule = TimeZoneNumberRule.INSTANCE_COLON;
                                                            break;
                                                        }
                                                    } else {
                                                        selectNumberRule = TimeZoneNumberRule.INSTANCE_NO_COLON;
                                                        break;
                                                    }
                                                default:
                                                    throw new IllegalArgumentException("Illegal pattern component: " + parseToken);
                                            }
                                    }
                                    i2 = 1;
                                    i = 0;
                                    break;
                            }
                        } else if (length2 >= 4) {
                            obj = new TimeZoneNameRule(this.timeZone, this.locale, 1);
                            i2 = 1;
                            i = 0;
                        } else {
                            i = 0;
                            timeZoneNameRule = new TimeZoneNameRule(this.timeZone, this.locale, 0);
                        }
                        obj = timeZoneNameRule;
                        i2 = 1;
                    }
                    if (length2 == 2) {
                        selectNumberRule = TwoDigitYearField.INSTANCE;
                    } else {
                        selectNumberRule = selectNumberRule(1, Math.max(length2, 4));
                    }
                    if (charAt == 'Y') {
                        obj = new WeekYear((NumberRule) selectNumberRule);
                        i2 = 1;
                        i = 0;
                    }
                } else {
                    selectNumberRule = selectNumberRule(3, length2);
                }
                obj = selectNumberRule;
                i2 = 1;
                i = 0;
            } else {
                String substring = parseToken.substring(i2);
                if (substring.length() == i2) {
                    i = 0;
                    stringLiteral = new CharacterLiteral(substring.charAt(0));
                } else {
                    i = 0;
                    stringLiteral = new StringLiteral(substring);
                }
                obj = stringLiteral;
            }
            arrayList.add(obj);
            i4 = i5 + 1;
            i3 = i;
        }
        return arrayList;
    }

    protected String parseToken(String str, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        int i = iArr[0];
        int length = str.length();
        char charAt = str.charAt(i);
        if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
            sb.append(charAt);
            while (true) {
                int i2 = i + 1;
                if (i2 >= length || str.charAt(i2) != charAt) {
                    break;
                }
                sb.append(charAt);
                i = i2;
            }
        } else {
            sb.append('\'');
            boolean z = false;
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 != '\'') {
                    if (!z && ((charAt2 >= 'A' && charAt2 <= 'Z') || (charAt2 >= 'a' && charAt2 <= 'z'))) {
                        i--;
                        break;
                    }
                    sb.append(charAt2);
                } else {
                    int i3 = i + 1;
                    if (i3 >= length || str.charAt(i3) != '\'') {
                        z = !z;
                    } else {
                        sb.append(charAt2);
                        i = i3;
                    }
                }
                i++;
            }
        }
        iArr[0] = i;
        return sb.toString();
    }

    protected NumberRule selectNumberRule(int i, int i2) {
        if (i2 != 1) {
            if (i2 == 2) {
                return new TwoDigitNumberField(i);
            }
            return new PaddedNumberField(i, i2);
        }
        return new UnpaddedNumberField(i);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    @Deprecated
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Date) {
            return format((Date) obj, stringBuffer);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj, stringBuffer);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), stringBuffer);
        }
        throw new IllegalArgumentException("Unknown class: " + ClassUtils.getName(obj, "<null>"));
    }

    public String format(Object obj) {
        if (obj instanceof Date) {
            return format((Date) obj);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue());
        }
        throw new IllegalArgumentException("Unknown class: " + ClassUtils.getName(obj, "<null>"));
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(long j) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTimeInMillis(j);
        return applyRulesToString(newCalendar);
    }

    private String applyRulesToString(Calendar calendar) {
        return ((StringBuilder) applyRules(calendar, (Calendar) new StringBuilder(this.maxLengthEstimate))).toString();
    }

    private Calendar newCalendar() {
        return Calendar.getInstance(this.timeZone, this.locale);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(Date date) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTime(date);
        return applyRulesToString(newCalendar);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(Calendar calendar) {
        return ((StringBuilder) format(calendar, (Calendar) new StringBuilder(this.maxLengthEstimate))).toString();
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(long j, StringBuffer stringBuffer) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTimeInMillis(j);
        return (StringBuffer) applyRules(newCalendar, (Calendar) stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Date date, StringBuffer stringBuffer) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTime(date);
        return (StringBuffer) applyRules(newCalendar, (Calendar) stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Calendar calendar, StringBuffer stringBuffer) {
        return format(calendar.getTime(), stringBuffer);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(long j, B b) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTimeInMillis(j);
        return (B) applyRules(newCalendar, (Calendar) b);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(Date date, B b) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTime(date);
        return (B) applyRules(newCalendar, (Calendar) b);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(Calendar calendar, B b) {
        if (!calendar.getTimeZone().equals(this.timeZone)) {
            calendar = (Calendar) calendar.clone();
            calendar.setTimeZone(this.timeZone);
        }
        return (B) applyRules(calendar, (Calendar) b);
    }

    @Deprecated
    public StringBuffer applyRules(Calendar calendar, StringBuffer stringBuffer) {
        return (StringBuffer) applyRules(calendar, (Calendar) stringBuffer);
    }

    private <B extends Appendable> B applyRules(Calendar calendar, B b) {
        try {
            for (Rule rule : this.rules) {
                rule.appendTo(b, calendar);
            }
        } catch (IOException e) {
            ExceptionUtils.rethrow(e);
        }
        return b;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String getPattern() {
        return this.pattern;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public Locale getLocale() {
        return this.locale;
    }

    public int getMaxLengthEstimate() {
        return this.maxLengthEstimate;
    }

    public boolean equals(Object obj) {
        if (obj instanceof FastDatePrinter) {
            FastDatePrinter fastDatePrinter = (FastDatePrinter) obj;
            return this.pattern.equals(fastDatePrinter.pattern) && this.timeZone.equals(fastDatePrinter.timeZone) && this.locale.equals(fastDatePrinter.locale);
        }
        return false;
    }

    public int hashCode() {
        return this.pattern.hashCode() + ((this.timeZone.hashCode() + (this.locale.hashCode() * 13)) * 13);
    }

    public String toString() {
        return "FastDatePrinter[" + this.pattern + "," + this.locale + "," + this.timeZone.getID() + "]";
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init();
    }

    public static void appendDigits(Appendable appendable, int i) throws IOException {
        appendable.append((char) ((i / 10) + 48));
        appendable.append((char) ((i % 10) + 48));
    }

    public static void appendFullDigits(Appendable appendable, int i, int i2) throws IOException {
        if (i < 10000) {
            int i3 = i < 1000 ? i < 100 ? i < 10 ? 1 : 2 : 3 : 4;
            for (int i4 = i2 - i3; i4 > 0; i4--) {
                appendable.append('0');
            }
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 != 3) {
                        if (i3 != 4) {
                            return;
                        }
                        appendable.append((char) ((i / 1000) + 48));
                        i %= 1000;
                    }
                    if (i >= 100) {
                        appendable.append((char) ((i / 100) + 48));
                        i %= 100;
                    } else {
                        appendable.append('0');
                    }
                }
                if (i >= 10) {
                    appendable.append((char) ((i / 10) + 48));
                    i %= 10;
                } else {
                    appendable.append('0');
                }
            }
            appendable.append((char) (i + 48));
            return;
        }
        char[] cArr = new char[10];
        int i5 = 0;
        while (i != 0) {
            cArr[i5] = (char) ((i % 10) + 48);
            i /= 10;
            i5++;
        }
        while (i5 < i2) {
            appendable.append('0');
            i2--;
        }
        while (true) {
            i5--;
            if (i5 < 0) {
                return;
            }
            appendable.append(cArr[i5]);
        }
    }

    /* loaded from: classes2.dex */
    public static class CharacterLiteral implements Rule {
        private final char value;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 1;
        }

        CharacterLiteral(char c) {
            this.value = c;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendable.append(this.value);
        }
    }

    /* loaded from: classes2.dex */
    public static class StringLiteral implements Rule {
        private final String value;

        StringLiteral(String str) {
            this.value = str;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.value.length();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendable.append(this.value);
        }
    }

    /* loaded from: classes2.dex */
    public static class TextField implements Rule {
        private final int field;
        private final String[] values;

        TextField(int i, String[] strArr) {
            this.field = i;
            this.values = strArr;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            int length = this.values.length;
            int i = 0;
            while (true) {
                length--;
                if (length < 0) {
                    return i;
                }
                int length2 = this.values[length].length();
                if (length2 > i) {
                    i = length2;
                }
            }
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendable.append(this.values[calendar.get(this.field)]);
        }
    }

    /* loaded from: classes2.dex */
    public static class UnpaddedNumberField implements NumberRule {
        private final int field;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 4;
        }

        UnpaddedNumberField(int i) {
            this.field = i;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(this.field));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            if (i < 10) {
                appendable.append((char) (i + 48));
            } else if (i < 100) {
                FastDatePrinter.appendDigits(appendable, i);
            } else {
                FastDatePrinter.appendFullDigits(appendable, i, 1);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class UnpaddedMonthField implements NumberRule {
        static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        UnpaddedMonthField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            if (i >= 10) {
                FastDatePrinter.appendDigits(appendable, i);
            } else {
                appendable.append((char) (i + 48));
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class PaddedNumberField implements NumberRule {
        private final int field;
        private final int size;

        PaddedNumberField(int i, int i2) {
            if (i2 < 3) {
                throw new IllegalArgumentException();
            }
            this.field = i;
            this.size = i2;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.size;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(this.field));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            FastDatePrinter.appendFullDigits(appendable, i, this.size);
        }
    }

    /* loaded from: classes2.dex */
    public static class TwoDigitNumberField implements NumberRule {
        private final int field;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitNumberField(int i) {
            this.field = i;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(this.field));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            if (i < 100) {
                FastDatePrinter.appendDigits(appendable, i);
            } else {
                FastDatePrinter.appendFullDigits(appendable, i, 2);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class TwoDigitYearField implements NumberRule {
        static final TwoDigitYearField INSTANCE = new TwoDigitYearField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitYearField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(1) % 100);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            FastDatePrinter.appendDigits(appendable, i % 100);
        }
    }

    /* loaded from: classes2.dex */
    public static class TwoDigitMonthField implements NumberRule {
        static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitMonthField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            appendTo(appendable, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public final void appendTo(Appendable appendable, int i) throws IOException {
            FastDatePrinter.appendDigits(appendable, i);
        }
    }

    /* loaded from: classes2.dex */
    public static class TwelveHourField implements NumberRule {
        private final NumberRule rule;

        TwelveHourField(NumberRule numberRule) {
            this.rule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.rule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int i = calendar.get(10);
            if (i == 0) {
                i = calendar.getLeastMaximum(10) + 1;
            }
            this.rule.appendTo(appendable, i);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int i) throws IOException {
            this.rule.appendTo(appendable, i);
        }
    }

    /* loaded from: classes2.dex */
    public static class TwentyFourHourField implements NumberRule {
        private final NumberRule rule;

        TwentyFourHourField(NumberRule numberRule) {
            this.rule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.rule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int i = calendar.get(11);
            if (i == 0) {
                i = calendar.getMaximum(11) + 1;
            }
            this.rule.appendTo(appendable, i);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int i) throws IOException {
            this.rule.appendTo(appendable, i);
        }
    }

    /* loaded from: classes2.dex */
    public static class DayInWeekField implements NumberRule {
        private final NumberRule rule;

        DayInWeekField(NumberRule numberRule) {
            this.rule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.rule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int i = calendar.get(7);
            this.rule.appendTo(appendable, i != 1 ? i - 1 : 7);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int i) throws IOException {
            this.rule.appendTo(appendable, i);
        }
    }

    /* loaded from: classes2.dex */
    public static class WeekYear implements NumberRule {
        private final NumberRule rule;

        WeekYear(NumberRule numberRule) {
            this.rule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.rule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            this.rule.appendTo(appendable, calendar.getWeekYear());
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable appendable, int i) throws IOException {
            this.rule.appendTo(appendable, i);
        }
    }

    static String getTimeZoneDisplay(final TimeZone timeZone, final boolean z, final int i, final Locale locale) {
        return cTimeZoneDisplayCache.computeIfAbsent(new TimeZoneDisplayKey(timeZone, z, i, locale), new Function() { // from class: org.apache.commons.lang3.time.FastDatePrinter$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String displayName;
                FastDatePrinter.TimeZoneDisplayKey timeZoneDisplayKey = (FastDatePrinter.TimeZoneDisplayKey) obj;
                displayName = timeZone.getDisplayName(z, i, locale);
                return displayName;
            }
        });
    }

    /* loaded from: classes2.dex */
    public static class TimeZoneNameRule implements Rule {
        private final String daylight;
        private final Locale locale;
        private final String standard;
        private final int style;

        TimeZoneNameRule(TimeZone timeZone, Locale locale, int i) {
            this.locale = LocaleUtils.toLocale(locale);
            this.style = i;
            this.standard = FastDatePrinter.getTimeZoneDisplay(timeZone, false, i, locale);
            this.daylight = FastDatePrinter.getTimeZoneDisplay(timeZone, true, i, locale);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return Math.max(this.standard.length(), this.daylight.length());
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            TimeZone timeZone = calendar.getTimeZone();
            if (calendar.get(16) == 0) {
                appendable.append(FastDatePrinter.getTimeZoneDisplay(timeZone, false, this.style, this.locale));
            } else {
                appendable.append(FastDatePrinter.getTimeZoneDisplay(timeZone, true, this.style, this.locale));
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class TimeZoneNumberRule implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
        static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
        private final boolean colon;

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 5;
        }

        TimeZoneNumberRule(boolean z) {
            this.colon = z;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int i = calendar.get(15) + calendar.get(16);
            if (i < 0) {
                appendable.append(Soundex.SILENT_MARKER);
                i = -i;
            } else {
                appendable.append('+');
            }
            int i2 = i / 3600000;
            FastDatePrinter.appendDigits(appendable, i2);
            if (this.colon) {
                appendable.append(':');
            }
            FastDatePrinter.appendDigits(appendable, (i / AuthState.EXPIRY_TIME_TOLERANCE_MS) - (i2 * 60));
        }
    }

    /* loaded from: classes2.dex */
    public static class Iso8601_Rule implements Rule {
        private final int length;
        static final Iso8601_Rule ISO8601_HOURS = new Iso8601_Rule(3);
        static final Iso8601_Rule ISO8601_HOURS_MINUTES = new Iso8601_Rule(5);
        static final Iso8601_Rule ISO8601_HOURS_COLON_MINUTES = new Iso8601_Rule(6);

        static Iso8601_Rule getRule(int i) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return ISO8601_HOURS_COLON_MINUTES;
                    }
                    throw new IllegalArgumentException("invalid number of X");
                }
                return ISO8601_HOURS_MINUTES;
            }
            return ISO8601_HOURS;
        }

        Iso8601_Rule(int i) {
            this.length = i;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.length;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable appendable, Calendar calendar) throws IOException {
            int i = calendar.get(15) + calendar.get(16);
            if (i == 0) {
                appendable.append("Z");
                return;
            }
            if (i < 0) {
                appendable.append(Soundex.SILENT_MARKER);
                i = -i;
            } else {
                appendable.append('+');
            }
            int i2 = i / 3600000;
            FastDatePrinter.appendDigits(appendable, i2);
            int i3 = this.length;
            if (i3 < 5) {
                return;
            }
            if (i3 == 6) {
                appendable.append(':');
            }
            FastDatePrinter.appendDigits(appendable, (i / AuthState.EXPIRY_TIME_TOLERANCE_MS) - (i2 * 60));
        }
    }

    /* loaded from: classes2.dex */
    public static class TimeZoneDisplayKey {
        private final Locale locale;
        private final int style;
        private final TimeZone timeZone;

        TimeZoneDisplayKey(TimeZone timeZone, boolean z, int i, Locale locale) {
            this.timeZone = timeZone;
            if (z) {
                this.style = Integer.MIN_VALUE | i;
            } else {
                this.style = i;
            }
            this.locale = LocaleUtils.toLocale(locale);
        }

        public int hashCode() {
            return (((this.style * 31) + this.locale.hashCode()) * 31) + this.timeZone.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof TimeZoneDisplayKey) {
                TimeZoneDisplayKey timeZoneDisplayKey = (TimeZoneDisplayKey) obj;
                return this.timeZone.equals(timeZoneDisplayKey.timeZone) && this.style == timeZoneDisplayKey.style && this.locale.equals(timeZoneDisplayKey.locale);
            }
            return false;
        }
    }
}
