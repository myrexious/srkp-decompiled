package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes2.dex */
public class LocaleUtils {
    private static final char DASH = '-';
    private static final char UNDERSCORE = '_';
    private static final ConcurrentMap<String, List<Locale>> cLanguagesByCountry = new ConcurrentHashMap();
    private static final ConcurrentMap<String, List<Locale>> cCountriesByLanguage = new ConcurrentHashMap();

    /* loaded from: classes2.dex */
    public static class SyncAvoid {
        private static final List<Locale> AVAILABLE_LOCALE_LIST;
        private static final Set<Locale> AVAILABLE_LOCALE_SET;

        SyncAvoid() {
        }

        static {
            ArrayList arrayList = new ArrayList(Arrays.asList(Locale.getAvailableLocales()));
            AVAILABLE_LOCALE_LIST = Collections.unmodifiableList(arrayList);
            AVAILABLE_LOCALE_SET = Collections.unmodifiableSet(new HashSet(arrayList));
        }
    }

    public static List<Locale> availableLocaleList() {
        return SyncAvoid.AVAILABLE_LOCALE_LIST;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<Locale> availableLocaleList(Predicate<Locale> predicate) {
        return (List) availableLocaleList().stream().filter(predicate).collect(Collectors.toList());
    }

    public static Set<Locale> availableLocaleSet() {
        return SyncAvoid.AVAILABLE_LOCALE_SET;
    }

    public static List<Locale> countriesByLanguage(final String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        return cCountriesByLanguage.computeIfAbsent(str, new Function() { // from class: org.apache.commons.lang3.LocaleUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                List unmodifiableList;
                String str2 = (String) obj;
                unmodifiableList = Collections.unmodifiableList(LocaleUtils.availableLocaleList(new Predicate() { // from class: org.apache.commons.lang3.LocaleUtils$$ExternalSyntheticLambda3
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj2) {
                        return LocaleUtils.lambda$null$0(r1, (Locale) obj2);
                    }
                }));
                return unmodifiableList;
            }
        });
    }

    public static /* synthetic */ boolean lambda$null$0(String str, Locale locale) {
        return str.equals(locale.getLanguage()) && !locale.getCountry().isEmpty() && locale.getVariant().isEmpty();
    }

    public static boolean isAvailableLocale(Locale locale) {
        return availableLocaleSet().contains(locale);
    }

    private static boolean isISO3166CountryCode(String str) {
        return StringUtils.isAllUpperCase(str) && str.length() == 2;
    }

    private static boolean isISO639LanguageCode(String str) {
        return StringUtils.isAllLowerCase(str) && (str.length() == 2 || str.length() == 3);
    }

    private static boolean isNumericAreaCode(String str) {
        return StringUtils.isNumeric(str) && str.length() == 3;
    }

    public static List<Locale> languagesByCountry(final String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        return cLanguagesByCountry.computeIfAbsent(str, new Function() { // from class: org.apache.commons.lang3.LocaleUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                List unmodifiableList;
                String str2 = (String) obj;
                unmodifiableList = Collections.unmodifiableList(LocaleUtils.availableLocaleList(new Predicate() { // from class: org.apache.commons.lang3.LocaleUtils$$ExternalSyntheticLambda1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj2) {
                        return LocaleUtils.lambda$null$2(r1, (Locale) obj2);
                    }
                }));
                return unmodifiableList;
            }
        });
    }

    public static /* synthetic */ boolean lambda$null$2(String str, Locale locale) {
        return str.equals(locale.getCountry()) && locale.getVariant().isEmpty();
    }

    public static List<Locale> localeLookupList(Locale locale) {
        return localeLookupList(locale, locale);
    }

    public static List<Locale> localeLookupList(Locale locale, Locale locale2) {
        ArrayList arrayList = new ArrayList(4);
        if (locale != null) {
            arrayList.add(locale);
            if (!locale.getVariant().isEmpty()) {
                arrayList.add(new Locale(locale.getLanguage(), locale.getCountry()));
            }
            if (!locale.getCountry().isEmpty()) {
                arrayList.add(new Locale(locale.getLanguage(), ""));
            }
            if (!arrayList.contains(locale2)) {
                arrayList.add(locale2);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static Locale parseLocale(String str) {
        String[] split;
        if (isISO639LanguageCode(str)) {
            return new Locale(str);
        }
        if (str.indexOf(95) != -1) {
            split = str.split(String.valueOf((char) UNDERSCORE), -1);
        } else {
            split = str.split(String.valueOf('-'), -1);
        }
        String str2 = split[0];
        if (split.length == 2) {
            String str3 = split[1];
            if ((isISO639LanguageCode(str2) && isISO3166CountryCode(str3)) || isNumericAreaCode(str3)) {
                return new Locale(str2, str3);
            }
        } else if (split.length == 3) {
            String str4 = split[1];
            String str5 = split[2];
            if (isISO639LanguageCode(str2) && ((str4.isEmpty() || isISO3166CountryCode(str4) || isNumericAreaCode(str4)) && !str5.isEmpty())) {
                return new Locale(str2, str4, str5);
            }
        }
        throw new IllegalArgumentException("Invalid locale format: " + str);
    }

    public static Locale toLocale(Locale locale) {
        return locale != null ? locale : Locale.getDefault();
    }

    public static Locale toLocale(String str) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return new Locale("", "");
        }
        if (str.contains("#")) {
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        int length = str.length();
        if (length < 2) {
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        char charAt = str.charAt(0);
        if (charAt == '_' || charAt == '-') {
            if (length < 3) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            char charAt2 = str.charAt(1);
            char charAt3 = str.charAt(2);
            if (Character.isUpperCase(charAt2) && Character.isUpperCase(charAt3)) {
                if (length == 3) {
                    return new Locale("", str.substring(1, 3));
                }
                if (length < 5) {
                    throw new IllegalArgumentException("Invalid locale format: " + str);
                }
                if (str.charAt(3) != charAt) {
                    throw new IllegalArgumentException("Invalid locale format: " + str);
                }
                return new Locale("", str.substring(1, 3), str.substring(4));
            }
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        return parseLocale(str);
    }
}
