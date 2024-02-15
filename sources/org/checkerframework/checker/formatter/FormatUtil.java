package org.checkerframework.checker.formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;
import java.util.MissingFormatArgumentException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
import org.checkerframework.checker.formatter.qual.ConversionCategory;
import org.checkerframework.checker.formatter.qual.ReturnsFormat;

/* loaded from: classes2.dex */
public class FormatUtil {
    private static final String formatSpecifier = "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";
    private static Pattern fsPattern = Pattern.compile(formatSpecifier);

    /* loaded from: classes2.dex */
    public static class Conversion {
        private final ConversionCategory cath;
        private final int index;

        public Conversion(char c, int i) {
            this.index = i;
            this.cath = ConversionCategory.fromConversionChar(c);
        }

        int index() {
            return this.index;
        }

        ConversionCategory category() {
            return this.cath;
        }
    }

    @ReturnsFormat
    public static String asFormat(String str, ConversionCategory... conversionCategoryArr) throws IllegalFormatException {
        ConversionCategory[] formatParameterCategories = formatParameterCategories(str);
        if (formatParameterCategories.length != conversionCategoryArr.length) {
            throw new ExcessiveOrMissingFormatArgumentException(conversionCategoryArr.length, formatParameterCategories.length);
        }
        for (int i = 0; i < conversionCategoryArr.length; i++) {
            if (conversionCategoryArr[i] != formatParameterCategories[i]) {
                throw new IllegalFormatConversionCategoryException(conversionCategoryArr[i], formatParameterCategories[i]);
            }
        }
        return str;
    }

    public static void tryFormatSatisfiability(String str) throws IllegalFormatException {
        String.format(str, null);
    }

    public static ConversionCategory[] formatParameterCategories(String str) throws IllegalFormatException {
        tryFormatSatisfiability(str);
        Conversion[] parse = parse(str);
        HashMap hashMap = new HashMap();
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        for (Conversion conversion : parse) {
            int index = conversion.index();
            if (index != -1) {
                if (index != 0) {
                    i3 = index - 1;
                } else {
                    i2++;
                    i3 = i2;
                }
            }
            i = Math.max(i, i3);
            hashMap.put(Integer.valueOf(i3), ConversionCategory.intersect(hashMap.containsKey(Integer.valueOf(i3)) ? (ConversionCategory) hashMap.get(Integer.valueOf(i3)) : ConversionCategory.UNUSED, conversion.category()));
        }
        ConversionCategory[] conversionCategoryArr = new ConversionCategory[i + 1];
        for (int i4 = 0; i4 <= i; i4++) {
            conversionCategoryArr[i4] = hashMap.containsKey(Integer.valueOf(i4)) ? (ConversionCategory) hashMap.get(Integer.valueOf(i4)) : ConversionCategory.UNUSED;
        }
        return conversionCategoryArr;
    }

    private static int indexFromFormat(Matcher matcher) {
        String group = matcher.group(1);
        if (group != null) {
            return Integer.parseInt(group.substring(0, group.length() - 1));
        }
        return (matcher.group(2) == null || !matcher.group(2).contains(String.valueOf((char) Typography.less))) ? 0 : -1;
    }

    private static char conversionCharFromFormat(Matcher matcher) {
        String group = matcher.group(5);
        if (group == null) {
            return matcher.group(6).charAt(0);
        }
        return group.charAt(0);
    }

    private static Conversion[] parse(String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = fsPattern.matcher(str);
        while (matcher.find()) {
            char conversionCharFromFormat = conversionCharFromFormat(matcher);
            if (conversionCharFromFormat != '%' && conversionCharFromFormat != 'n') {
                arrayList.add(new Conversion(conversionCharFromFormat, indexFromFormat(matcher)));
            }
        }
        return (Conversion[]) arrayList.toArray(new Conversion[arrayList.size()]);
    }

    /* loaded from: classes2.dex */
    public static class ExcessiveOrMissingFormatArgumentException extends MissingFormatArgumentException {
        private static final long serialVersionUID = 17000126;
        private final int expected;
        private final int found;

        public ExcessiveOrMissingFormatArgumentException(int i, int i2) {
            super("-");
            this.expected = i;
            this.found = i2;
        }

        public int getExpected() {
            return this.expected;
        }

        public int getFound() {
            return this.found;
        }

        @Override // java.util.MissingFormatArgumentException, java.lang.Throwable
        public String getMessage() {
            return String.format("Expected %d arguments but found %d.", Integer.valueOf(this.expected), Integer.valueOf(this.found));
        }
    }

    /* loaded from: classes2.dex */
    public static class IllegalFormatConversionCategoryException extends IllegalFormatConversionException {
        private static final long serialVersionUID = 17000126;
        private final ConversionCategory expected;
        private final ConversionCategory found;

        public IllegalFormatConversionCategoryException(ConversionCategory conversionCategory, ConversionCategory conversionCategory2) {
            super(conversionCategory.chars.length() == 0 ? Soundex.SILENT_MARKER : conversionCategory.chars.charAt(0), conversionCategory2.types == null ? Object.class : conversionCategory2.types[0]);
            this.expected = conversionCategory;
            this.found = conversionCategory2;
        }

        public ConversionCategory getExpected() {
            return this.expected;
        }

        public ConversionCategory getFound() {
            return this.found;
        }

        @Override // java.util.IllegalFormatConversionException, java.lang.Throwable
        public String getMessage() {
            return String.format("Expected category %s but found %s.", this.expected, this.found);
        }
    }
}
