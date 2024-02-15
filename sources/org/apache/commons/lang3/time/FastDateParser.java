package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.LocaleUtils;

/* loaded from: classes2.dex */
public class FastDateParser implements DateParser, Serializable {
    private static final long serialVersionUID = 3;
    private final int century;
    private final Locale locale;
    private final String pattern;
    private transient List<StrategyAndWidth> patterns;
    private final int startYear;
    private final TimeZone timeZone;
    static final Locale JAPANESE_IMPERIAL = new Locale("ja", "JP", "JP");
    private static final Comparator<String> LONGER_FIRST_LOWERCASE = Comparator.reverseOrder();
    private static final ConcurrentMap<Locale, Strategy>[] caches = new ConcurrentMap[17];
    private static final Strategy ABBREVIATED_YEAR_STRATEGY = new NumberStrategy(1) { // from class: org.apache.commons.lang3.time.FastDateParser.1
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser fastDateParser, int i) {
            return i < 100 ? fastDateParser.adjustYear(i) : i;
        }
    };
    private static final Strategy NUMBER_MONTH_STRATEGY = new NumberStrategy(2) { // from class: org.apache.commons.lang3.time.FastDateParser.2
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser fastDateParser, int i) {
            return i - 1;
        }
    };
    private static final Strategy LITERAL_YEAR_STRATEGY = new NumberStrategy(1);
    private static final Strategy WEEK_OF_YEAR_STRATEGY = new NumberStrategy(3);
    private static final Strategy WEEK_OF_MONTH_STRATEGY = new NumberStrategy(4);
    private static final Strategy DAY_OF_YEAR_STRATEGY = new NumberStrategy(6);
    private static final Strategy DAY_OF_MONTH_STRATEGY = new NumberStrategy(5);
    private static final Strategy DAY_OF_WEEK_STRATEGY = new NumberStrategy(7) { // from class: org.apache.commons.lang3.time.FastDateParser.3
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser fastDateParser, int i) {
            if (i == 7) {
                return 1;
            }
            return 1 + i;
        }
    };
    private static final Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = new NumberStrategy(8);
    private static final Strategy HOUR_OF_DAY_STRATEGY = new NumberStrategy(11);
    private static final Strategy HOUR24_OF_DAY_STRATEGY = new NumberStrategy(11) { // from class: org.apache.commons.lang3.time.FastDateParser.4
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser fastDateParser, int i) {
            if (i == 24) {
                return 0;
            }
            return i;
        }
    };
    private static final Strategy HOUR12_STRATEGY = new NumberStrategy(10) { // from class: org.apache.commons.lang3.time.FastDateParser.5
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser fastDateParser, int i) {
            if (i == 12) {
                return 0;
            }
            return i;
        }
    };
    private static final Strategy HOUR_STRATEGY = new NumberStrategy(10);
    private static final Strategy MINUTE_STRATEGY = new NumberStrategy(12);
    private static final Strategy SECOND_STRATEGY = new NumberStrategy(13);
    private static final Strategy MILLISECOND_STRATEGY = new NumberStrategy(14);

    public static boolean isFormatLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public FastDateParser(String str, TimeZone timeZone, Locale locale) {
        this(str, timeZone, locale, null);
    }

    public FastDateParser(String str, TimeZone timeZone, Locale locale, Date date) {
        int i;
        this.pattern = (String) Objects.requireNonNull(str, "pattern");
        this.timeZone = (TimeZone) Objects.requireNonNull(timeZone, "timeZone");
        Locale locale2 = LocaleUtils.toLocale(locale);
        this.locale = locale2;
        Calendar calendar = Calendar.getInstance(timeZone, locale2);
        if (date != null) {
            calendar.setTime(date);
            i = calendar.get(1);
        } else if (locale2.equals(JAPANESE_IMPERIAL)) {
            i = 0;
        } else {
            calendar.setTime(new Date());
            i = calendar.get(1) - 80;
        }
        int i2 = (i / 100) * 100;
        this.century = i2;
        this.startYear = i - i2;
        init(calendar);
    }

    private void init(Calendar calendar) {
        this.patterns = new ArrayList();
        StrategyParser strategyParser = new StrategyParser(calendar);
        while (true) {
            StrategyAndWidth nextStrategy = strategyParser.getNextStrategy();
            if (nextStrategy == null) {
                return;
            }
            this.patterns.add(nextStrategy);
        }
    }

    /* loaded from: classes2.dex */
    public static class StrategyAndWidth {
        final Strategy strategy;
        final int width;

        StrategyAndWidth(Strategy strategy, int i) {
            this.strategy = strategy;
            this.width = i;
        }

        int getMaxWidth(ListIterator<StrategyAndWidth> listIterator) {
            if (this.strategy.isNumber() && listIterator.hasNext()) {
                Strategy strategy = listIterator.next().strategy;
                listIterator.previous();
                if (strategy.isNumber()) {
                    return this.width;
                }
                return 0;
            }
            return 0;
        }

        public String toString() {
            return "StrategyAndWidth [strategy=" + this.strategy + ", width=" + this.width + "]";
        }
    }

    /* loaded from: classes2.dex */
    public class StrategyParser {
        private int currentIdx;
        private final Calendar definingCalendar;

        StrategyParser(Calendar calendar) {
            FastDateParser.this = r1;
            this.definingCalendar = calendar;
        }

        StrategyAndWidth getNextStrategy() {
            if (this.currentIdx >= FastDateParser.this.pattern.length()) {
                return null;
            }
            char charAt = FastDateParser.this.pattern.charAt(this.currentIdx);
            if (FastDateParser.isFormatLetter(charAt)) {
                return letterPattern(charAt);
            }
            return literal();
        }

        private StrategyAndWidth letterPattern(char c) {
            int i = this.currentIdx;
            do {
                int i2 = this.currentIdx + 1;
                this.currentIdx = i2;
                if (i2 >= FastDateParser.this.pattern.length()) {
                    break;
                }
            } while (FastDateParser.this.pattern.charAt(this.currentIdx) == c);
            int i3 = this.currentIdx - i;
            return new StrategyAndWidth(FastDateParser.this.getStrategy(c, i3, this.definingCalendar), i3);
        }

        private StrategyAndWidth literal() {
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            while (this.currentIdx < FastDateParser.this.pattern.length()) {
                char charAt = FastDateParser.this.pattern.charAt(this.currentIdx);
                if (!z && FastDateParser.isFormatLetter(charAt)) {
                    break;
                }
                if (charAt == '\'') {
                    int i = this.currentIdx + 1;
                    this.currentIdx = i;
                    if (i == FastDateParser.this.pattern.length() || FastDateParser.this.pattern.charAt(this.currentIdx) != '\'') {
                        z = !z;
                    }
                }
                this.currentIdx++;
                sb.append(charAt);
            }
            if (z) {
                throw new IllegalArgumentException("Unterminated quote");
            }
            String sb2 = sb.toString();
            return new StrategyAndWidth(new CopyQuotedStrategy(sb2), sb2.length());
        }
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public String getPattern() {
        return this.pattern;
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public Locale getLocale() {
        return this.locale;
    }

    public boolean equals(Object obj) {
        if (obj instanceof FastDateParser) {
            FastDateParser fastDateParser = (FastDateParser) obj;
            return this.pattern.equals(fastDateParser.pattern) && this.timeZone.equals(fastDateParser.timeZone) && this.locale.equals(fastDateParser.locale);
        }
        return false;
    }

    public int hashCode() {
        return this.pattern.hashCode() + ((this.timeZone.hashCode() + (this.locale.hashCode() * 13)) * 13);
    }

    public String toString() {
        return "FastDateParser[" + this.pattern + ", " + this.locale + ", " + this.timeZone.getID() + "]";
    }

    public String toStringAll() {
        return "FastDateParser [pattern=" + this.pattern + ", timeZone=" + this.timeZone + ", locale=" + this.locale + ", century=" + this.century + ", startYear=" + this.startYear + ", patterns=" + this.patterns + "]";
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init(Calendar.getInstance(this.timeZone, this.locale));
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Object parseObject(String str) throws ParseException {
        return parse(str);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Date parse(String str) throws ParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = parse(str, parsePosition);
        if (parse == null) {
            if (this.locale.equals(JAPANESE_IMPERIAL)) {
                throw new ParseException("(The " + this.locale + " locale does not support dates before 1868 AD)\nUnparseable date: \"" + str, parsePosition.getErrorIndex());
            }
            throw new ParseException("Unparseable date: " + str, parsePosition.getErrorIndex());
        }
        return parse;
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Object parseObject(String str, ParsePosition parsePosition) {
        return parse(str, parsePosition);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Date parse(String str, ParsePosition parsePosition) {
        Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
        calendar.clear();
        if (parse(str, parsePosition, calendar)) {
            return calendar.getTime();
        }
        return null;
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public boolean parse(String str, ParsePosition parsePosition, Calendar calendar) {
        ListIterator<StrategyAndWidth> listIterator = this.patterns.listIterator();
        while (listIterator.hasNext()) {
            StrategyAndWidth next = listIterator.next();
            if (!next.strategy.parse(this, calendar, str, parsePosition, next.getMaxWidth(listIterator))) {
                return false;
            }
        }
        return true;
    }

    public static StringBuilder simpleQuote(StringBuilder sb, String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != '$' && charAt != '.' && charAt != '?' && charAt != '^' && charAt != '[' && charAt != '\\' && charAt != '{' && charAt != '|') {
                switch (charAt) {
                    case '(':
                    case ')':
                    case '*':
                    case '+':
                        break;
                    default:
                        sb.append(charAt);
                }
            }
            sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
            sb.append(charAt);
        }
        if (sb.charAt(sb.length() - 1) == '.') {
            sb.append('?');
        }
        return sb;
    }

    public static Map<String, Integer> appendDisplayNames(Calendar calendar, Locale locale, int i, final StringBuilder sb) {
        final HashMap hashMap = new HashMap();
        final Locale locale2 = LocaleUtils.toLocale(locale);
        Map<String, Integer> displayNames = calendar.getDisplayNames(i, 0, locale2);
        final TreeSet treeSet = new TreeSet(LONGER_FIRST_LOWERCASE);
        displayNames.forEach(new BiConsumer() { // from class: org.apache.commons.lang3.time.FastDateParser$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                FastDateParser.lambda$appendDisplayNames$0(locale2, treeSet, hashMap, (String) obj, (Integer) obj2);
            }
        });
        treeSet.forEach(new Consumer() { // from class: org.apache.commons.lang3.time.FastDateParser$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FastDateParser.simpleQuote(sb, (String) obj).append('|');
            }
        });
        return hashMap;
    }

    public static /* synthetic */ void lambda$appendDisplayNames$0(Locale locale, TreeSet treeSet, Map map, String str, Integer num) {
        String lowerCase = str.toLowerCase(locale);
        if (treeSet.add(lowerCase)) {
            map.put(lowerCase, num);
        }
    }

    public int adjustYear(int i) {
        int i2 = this.century + i;
        return i >= this.startYear ? i2 : i2 + 100;
    }

    /* loaded from: classes2.dex */
    public static abstract class Strategy {
        boolean isNumber() {
            return false;
        }

        abstract boolean parse(FastDateParser fastDateParser, Calendar calendar, String str, ParsePosition parsePosition, int i);

        private Strategy() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class PatternStrategy extends Strategy {
        Pattern pattern;

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            return false;
        }

        abstract void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str);

        private PatternStrategy() {
            super();
        }

        void createPattern(StringBuilder sb) {
            createPattern(sb.toString());
        }

        void createPattern(String str) {
            this.pattern = Pattern.compile(str);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean parse(FastDateParser fastDateParser, Calendar calendar, String str, ParsePosition parsePosition, int i) {
            Matcher matcher = this.pattern.matcher(str.substring(parsePosition.getIndex()));
            if (!matcher.lookingAt()) {
                parsePosition.setErrorIndex(parsePosition.getIndex());
                return false;
            }
            parsePosition.setIndex(parsePosition.getIndex() + matcher.end(1));
            setCalendar(fastDateParser, calendar, matcher.group(1));
            return true;
        }

        public String toString() {
            return getClass().getSimpleName() + " [pattern=" + this.pattern + "]";
        }
    }

    public Strategy getStrategy(char c, int i, Calendar calendar) {
        if (c != 'S') {
            if (c != 'a') {
                if (c != 'd') {
                    if (c != 'h') {
                        if (c != 'k') {
                            if (c != 'm') {
                                if (c != 's') {
                                    if (c != 'u') {
                                        if (c != 'w') {
                                            if (c != 'y') {
                                                if (c != 'z') {
                                                    switch (c) {
                                                        case 'D':
                                                            return DAY_OF_YEAR_STRATEGY;
                                                        case 'E':
                                                            return getLocaleSpecificStrategy(7, calendar);
                                                        case 'F':
                                                            return DAY_OF_WEEK_IN_MONTH_STRATEGY;
                                                        case 'G':
                                                            return getLocaleSpecificStrategy(0, calendar);
                                                        case 'H':
                                                            return HOUR_OF_DAY_STRATEGY;
                                                        default:
                                                            switch (c) {
                                                                case 'K':
                                                                    return HOUR_STRATEGY;
                                                                case 'L':
                                                                case 'M':
                                                                    return i >= 3 ? getLocaleSpecificStrategy(2, calendar) : NUMBER_MONTH_STRATEGY;
                                                                default:
                                                                    switch (c) {
                                                                        case 'W':
                                                                            return WEEK_OF_MONTH_STRATEGY;
                                                                        case 'X':
                                                                            return ISO8601TimeZoneStrategy.getStrategy(i);
                                                                        case 'Y':
                                                                            break;
                                                                        case 'Z':
                                                                            if (i == 2) {
                                                                                return ISO8601TimeZoneStrategy.ISO_8601_3_STRATEGY;
                                                                            }
                                                                            break;
                                                                        default:
                                                                            throw new IllegalArgumentException("Format '" + c + "' not supported");
                                                                    }
                                                            }
                                                    }
                                                }
                                                return getLocaleSpecificStrategy(15, calendar);
                                            }
                                            return i > 2 ? LITERAL_YEAR_STRATEGY : ABBREVIATED_YEAR_STRATEGY;
                                        }
                                        return WEEK_OF_YEAR_STRATEGY;
                                    }
                                    return DAY_OF_WEEK_STRATEGY;
                                }
                                return SECOND_STRATEGY;
                            }
                            return MINUTE_STRATEGY;
                        }
                        return HOUR24_OF_DAY_STRATEGY;
                    }
                    return HOUR12_STRATEGY;
                }
                return DAY_OF_MONTH_STRATEGY;
            }
            return getLocaleSpecificStrategy(9, calendar);
        }
        return MILLISECOND_STRATEGY;
    }

    private static ConcurrentMap<Locale, Strategy> getCache(int i) {
        ConcurrentMap<Locale, Strategy> concurrentMap;
        ConcurrentMap<Locale, Strategy>[] concurrentMapArr = caches;
        synchronized (concurrentMapArr) {
            if (concurrentMapArr[i] == null) {
                concurrentMapArr[i] = new ConcurrentHashMap(3);
            }
            concurrentMap = concurrentMapArr[i];
        }
        return concurrentMap;
    }

    private Strategy getLocaleSpecificStrategy(final int i, final Calendar calendar) {
        return getCache(i).computeIfAbsent(this.locale, new Function() { // from class: org.apache.commons.lang3.time.FastDateParser$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FastDateParser.this.m1858xdc739664(i, calendar, (Locale) obj);
            }
        });
    }

    /* renamed from: lambda$getLocaleSpecificStrategy$2$org-apache-commons-lang3-time-FastDateParser */
    public /* synthetic */ Strategy m1858xdc739664(int i, Calendar calendar, Locale locale) {
        return i == 15 ? new TimeZoneStrategy(this.locale) : new CaseInsensitiveTextStrategy(i, calendar, this.locale);
    }

    /* loaded from: classes2.dex */
    public static class CopyQuotedStrategy extends Strategy {
        private final String formatField;

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            return false;
        }

        CopyQuotedStrategy(String str) {
            super();
            this.formatField = str;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean parse(FastDateParser fastDateParser, Calendar calendar, String str, ParsePosition parsePosition, int i) {
            for (int i2 = 0; i2 < this.formatField.length(); i2++) {
                int index = parsePosition.getIndex() + i2;
                if (index == str.length()) {
                    parsePosition.setErrorIndex(index);
                    return false;
                } else if (this.formatField.charAt(i2) != str.charAt(index)) {
                    parsePosition.setErrorIndex(index);
                    return false;
                }
            }
            parsePosition.setIndex(this.formatField.length() + parsePosition.getIndex());
            return true;
        }

        public String toString() {
            return "CopyQuotedStrategy [formatField=" + this.formatField + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class CaseInsensitiveTextStrategy extends PatternStrategy {
        private final int field;
        private final Map<String, Integer> lKeyValues;
        final Locale locale;

        CaseInsensitiveTextStrategy(int i, Calendar calendar, Locale locale) {
            super();
            this.field = i;
            this.locale = LocaleUtils.toLocale(locale);
            StringBuilder sb = new StringBuilder();
            sb.append("((?iu)");
            this.lKeyValues = FastDateParser.appendDisplayNames(calendar, locale, i, sb);
            sb.setLength(sb.length() - 1);
            sb.append(")");
            createPattern(sb);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            String lowerCase = str.toLowerCase(this.locale);
            Integer num = this.lKeyValues.get(lowerCase);
            if (num == null) {
                num = this.lKeyValues.get(lowerCase + '.');
            }
            if (9 != this.field || num.intValue() <= 1) {
                calendar.set(this.field, num.intValue());
            }
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        public String toString() {
            return "CaseInsensitiveTextStrategy [field=" + this.field + ", locale=" + this.locale + ", lKeyValues=" + this.lKeyValues + ", pattern=" + this.pattern + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class NumberStrategy extends Strategy {
        private final int field;

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            return true;
        }

        int modify(FastDateParser fastDateParser, int i) {
            return i;
        }

        NumberStrategy(int i) {
            super();
            this.field = i;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean parse(FastDateParser fastDateParser, Calendar calendar, String str, ParsePosition parsePosition, int i) {
            int index = parsePosition.getIndex();
            int length = str.length();
            if (i == 0) {
                while (index < length && Character.isWhitespace(str.charAt(index))) {
                    index++;
                }
                parsePosition.setIndex(index);
            } else {
                int i2 = i + index;
                if (length > i2) {
                    length = i2;
                }
            }
            while (index < length && Character.isDigit(str.charAt(index))) {
                index++;
            }
            if (parsePosition.getIndex() == index) {
                parsePosition.setErrorIndex(index);
                return false;
            }
            int parseInt = Integer.parseInt(str.substring(parsePosition.getIndex(), index));
            parsePosition.setIndex(index);
            calendar.set(this.field, modify(fastDateParser, parseInt));
            return true;
        }

        public String toString() {
            return "NumberStrategy [field=" + this.field + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class TimeZoneStrategy extends PatternStrategy {
        private static final String GMT_OPTION = "GMT[+-]\\d{1,2}:\\d{2}";
        private static final int ID = 0;
        private static final String RFC_822_TIME_ZONE = "[+-]\\d{4}";
        private final Locale locale;
        private final Map<String, TzInfo> tzNames;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class TzInfo {
            final int dstOffset;
            final TimeZone zone;

            TzInfo(TimeZone timeZone, boolean z) {
                this.zone = timeZone;
                this.dstOffset = z ? timeZone.getDSTSavings() : 0;
            }
        }

        TimeZoneStrategy(Locale locale) {
            super();
            String[][] zoneStrings;
            this.tzNames = new HashMap();
            this.locale = LocaleUtils.toLocale(locale);
            final StringBuilder sb = new StringBuilder();
            sb.append("((?iu)[+-]\\d{4}|GMT[+-]\\d{1,2}:\\d{2}");
            TreeSet treeSet = new TreeSet(FastDateParser.LONGER_FIRST_LOWERCASE);
            for (String[] strArr : DateFormatSymbols.getInstance(locale).getZoneStrings()) {
                String str = strArr[0];
                if (!str.equalsIgnoreCase(TimeZones.GMT_ID)) {
                    TimeZone timeZone = TimeZone.getTimeZone(str);
                    TzInfo tzInfo = new TzInfo(timeZone, false);
                    TzInfo tzInfo2 = tzInfo;
                    for (int i = 1; i < strArr.length; i++) {
                        if (i == 3) {
                            tzInfo2 = new TzInfo(timeZone, true);
                        } else if (i == 5) {
                            tzInfo2 = tzInfo;
                        }
                        String str2 = strArr[i];
                        if (str2 != null) {
                            String lowerCase = str2.toLowerCase(locale);
                            if (treeSet.add(lowerCase)) {
                                this.tzNames.put(lowerCase, tzInfo2);
                            }
                        }
                    }
                }
            }
            treeSet.forEach(new Consumer() { // from class: org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    FastDateParser.simpleQuote(sb.append('|'), (String) obj);
                }
            });
            sb.append(")");
            createPattern(sb);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            TimeZone gmtTimeZone = FastTimeZone.getGmtTimeZone(str);
            if (gmtTimeZone != null) {
                calendar.setTimeZone(gmtTimeZone);
                return;
            }
            String lowerCase = str.toLowerCase(this.locale);
            TzInfo tzInfo = this.tzNames.get(lowerCase);
            if (tzInfo == null) {
                tzInfo = this.tzNames.get(lowerCase + '.');
            }
            calendar.set(16, tzInfo.dstOffset);
            calendar.set(15, tzInfo.zone.getRawOffset());
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        public String toString() {
            return "TimeZoneStrategy [locale=" + this.locale + ", tzNames=" + this.tzNames + ", pattern=" + this.pattern + "]";
        }
    }

    /* loaded from: classes2.dex */
    public static class ISO8601TimeZoneStrategy extends PatternStrategy {
        private static final Strategy ISO_8601_1_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}))");
        private static final Strategy ISO_8601_2_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}\\d{2}))");
        private static final Strategy ISO_8601_3_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}(?::)\\d{2}))");

        ISO8601TimeZoneStrategy(String str) {
            super();
            createPattern(str);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str) {
            calendar.setTimeZone(FastTimeZone.getGmtTimeZone(str));
        }

        static Strategy getStrategy(int i) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return ISO_8601_3_STRATEGY;
                    }
                    throw new IllegalArgumentException("invalid number of X");
                }
                return ISO_8601_2_STRATEGY;
            }
            return ISO_8601_1_STRATEGY;
        }
    }
}
