package org.apache.commons.lang3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class RegExUtils {
    public static Pattern dotAll(String str) {
        return Pattern.compile(str, 32);
    }

    public static Matcher dotAllMatcher(String str, String str2) {
        return dotAll(str).matcher(str2);
    }

    public static String removeAll(String str, Pattern pattern) {
        return replaceAll(str, pattern, "");
    }

    public static String removeAll(String str, String str2) {
        return replaceAll(str, str2, "");
    }

    public static String removeFirst(String str, Pattern pattern) {
        return replaceFirst(str, pattern, "");
    }

    public static String removeFirst(String str, String str2) {
        return replaceFirst(str, str2, "");
    }

    public static String removePattern(String str, String str2) {
        return replacePattern(str, str2, "");
    }

    public static String replaceAll(String str, Pattern pattern, String str2) {
        return ObjectUtils.anyNull(str, pattern, str2) ? str : pattern.matcher(str).replaceAll(str2);
    }

    public static String replaceAll(String str, String str2, String str3) {
        return ObjectUtils.anyNull(str, str2, str3) ? str : str.replaceAll(str2, str3);
    }

    public static String replaceFirst(String str, Pattern pattern, String str2) {
        return (str == null || pattern == null || str2 == null) ? str : pattern.matcher(str).replaceFirst(str2);
    }

    public static String replaceFirst(String str, String str2, String str3) {
        return (str == null || str2 == null || str3 == null) ? str : str.replaceFirst(str2, str3);
    }

    public static String replacePattern(String str, String str2, String str3) {
        return ObjectUtils.anyNull(str, str2, str3) ? str : dotAllMatcher(str2, str).replaceAll(str3);
    }
}
