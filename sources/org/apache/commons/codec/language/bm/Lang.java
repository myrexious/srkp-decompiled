package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages;

/* loaded from: classes2.dex */
public class Lang {
    private static final String LANGUAGE_RULES_RN = "org/apache/commons/codec/language/bm/%s_lang.txt";
    private static final Map<NameType, Lang> Langs = new EnumMap(NameType.class);
    private final Languages languages;
    private final List<LangRule> rules;

    /* loaded from: classes2.dex */
    public static final class LangRule {
        private final boolean acceptOnMatch;
        private final Set<String> languages;
        private final Pattern pattern;

        private LangRule(Pattern pattern, Set<String> set, boolean z) {
            this.pattern = pattern;
            this.languages = set;
            this.acceptOnMatch = z;
        }

        public boolean matches(String str) {
            return this.pattern.matcher(str).find();
        }
    }

    static {
        NameType[] values;
        for (NameType nameType : NameType.values()) {
            Langs.put(nameType, loadFromResource(String.format(LANGUAGE_RULES_RN, nameType.getName()), Languages.getInstance(nameType)));
        }
    }

    public static Lang instance(NameType nameType) {
        return Langs.get(nameType);
    }

    /* JADX WARN: Code restructure failed: missing block: B:81:0x00a7, code lost:
        throw new java.lang.IllegalArgumentException("Malformed line '" + r4 + "' in language resource '" + r9 + com.tom_roush.pdfbox.contentstream.operator.OperatorName.SHOW_TEXT_LINE);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.apache.commons.codec.language.bm.Lang loadFromResource(java.lang.String r9, org.apache.commons.codec.language.bm.Languages r10) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Scanner r1 = new java.util.Scanner
            java.io.InputStream r2 = org.apache.commons.codec.Resources.getInputStream(r9)
            java.lang.String r3 = "UTF-8"
            r1.<init>(r2, r3)
            r2 = 0
        L11:
            r3 = r2
        L12:
            boolean r4 = r1.hasNextLine()     // Catch: java.lang.Throwable -> Lb1
            if (r4 == 0) goto La8
            java.lang.String r4 = r1.nextLine()     // Catch: java.lang.Throwable -> Lb1
            if (r3 == 0) goto L27
        */
        //  java.lang.String r5 = "*/"
        /*
            boolean r4 = r4.endsWith(r5)     // Catch: java.lang.Throwable -> Lb1
            if (r4 == 0) goto L12
            goto L11
        L27:
            java.lang.String r5 = "/*"
            boolean r5 = r4.startsWith(r5)     // Catch: java.lang.Throwable -> Lb1
            r6 = 1
            if (r5 == 0) goto L32
            r3 = r6
            goto L12
        L32:
            java.lang.String r5 = "//"
            int r5 = r4.indexOf(r5)     // Catch: java.lang.Throwable -> Lb1
            if (r5 < 0) goto L3f
            java.lang.String r5 = r4.substring(r2, r5)     // Catch: java.lang.Throwable -> Lb1
            goto L40
        L3f:
            r5 = r4
        L40:
            java.lang.String r5 = r5.trim()     // Catch: java.lang.Throwable -> Lb1
            int r7 = r5.length()     // Catch: java.lang.Throwable -> Lb1
            if (r7 != 0) goto L4b
            goto L12
        L4b:
            java.lang.String r7 = "\\s+"
            java.lang.String[] r5 = r5.split(r7)     // Catch: java.lang.Throwable -> Lb1
            int r7 = r5.length     // Catch: java.lang.Throwable -> Lb1
            r8 = 3
            if (r7 != r8) goto L7f
            r4 = r5[r2]     // Catch: java.lang.Throwable -> Lb1
            java.util.regex.Pattern r4 = java.util.regex.Pattern.compile(r4)     // Catch: java.lang.Throwable -> Lb1
            r6 = r5[r6]     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r7 = "\\+"
            java.lang.String[] r6 = r6.split(r7)     // Catch: java.lang.Throwable -> Lb1
            r7 = 2
            r5 = r5[r7]     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r7 = "true"
            boolean r5 = r5.equals(r7)     // Catch: java.lang.Throwable -> Lb1
            org.apache.commons.codec.language.bm.Lang$LangRule r7 = new org.apache.commons.codec.language.bm.Lang$LangRule     // Catch: java.lang.Throwable -> Lb1
            java.util.HashSet r8 = new java.util.HashSet     // Catch: java.lang.Throwable -> Lb1
            java.util.List r6 = java.util.Arrays.asList(r6)     // Catch: java.lang.Throwable -> Lb1
            r8.<init>(r6)     // Catch: java.lang.Throwable -> Lb1
            r6 = 0
            r7.<init>(r4, r8, r5)     // Catch: java.lang.Throwable -> Lb1
            r0.add(r7)     // Catch: java.lang.Throwable -> Lb1
            goto L12
        L7f:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> Lb1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb1
            r0.<init>()     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r2 = "Malformed line '"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch: java.lang.Throwable -> Lb1
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r2 = "' in language resource '"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch: java.lang.Throwable -> Lb1
            java.lang.StringBuilder r9 = r0.append(r9)     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r0 = "'"
            java.lang.StringBuilder r9 = r9.append(r0)     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Lb1
            r10.<init>(r9)     // Catch: java.lang.Throwable -> Lb1
            throw r10     // Catch: java.lang.Throwable -> Lb1
        La8:
            r1.close()
            org.apache.commons.codec.language.bm.Lang r9 = new org.apache.commons.codec.language.bm.Lang
            r9.<init>(r0, r10)
            return r9
        Lb1:
            r9 = move-exception
            throw r9     // Catch: java.lang.Throwable -> Lb3
        Lb3:
            r10 = move-exception
            r1.close()     // Catch: java.lang.Throwable -> Lb8
            goto Lbc
        Lb8:
            r0 = move-exception
            r9.addSuppressed(r0)
        Lbc:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.bm.Lang.loadFromResource(java.lang.String, org.apache.commons.codec.language.bm.Languages):org.apache.commons.codec.language.bm.Lang");
    }

    private Lang(List<LangRule> list, Languages languages) {
        this.rules = Collections.unmodifiableList(list);
        this.languages = languages;
    }

    public String guessLanguage(String str) {
        Languages.LanguageSet guessLanguages = guessLanguages(str);
        return guessLanguages.isSingleton() ? guessLanguages.getAny() : Languages.ANY;
    }

    public Languages.LanguageSet guessLanguages(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        HashSet hashSet = new HashSet(this.languages.getLanguages());
        for (LangRule langRule : this.rules) {
            if (langRule.matches(lowerCase)) {
                if (langRule.acceptOnMatch) {
                    hashSet.retainAll(langRule.languages);
                } else {
                    hashSet.removeAll(langRule.languages);
                }
            }
        }
        Languages.LanguageSet from = Languages.LanguageSet.from(hashSet);
        return from.equals(Languages.NO_LANGUAGES) ? Languages.ANY_LANGUAGE : from;
    }
}
