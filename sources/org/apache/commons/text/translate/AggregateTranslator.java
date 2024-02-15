package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* loaded from: classes2.dex */
public class AggregateTranslator extends CharSequenceTranslator {
    private final List<CharSequenceTranslator> translators;

    public AggregateTranslator(CharSequenceTranslator... charSequenceTranslatorArr) {
        final ArrayList arrayList = new ArrayList();
        this.translators = arrayList;
        if (charSequenceTranslatorArr != null) {
            Stream filter = Stream.of((Object[]) charSequenceTranslatorArr).filter(new Predicate() { // from class: org.apache.commons.text.translate.AggregateTranslator$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean nonNull;
                    nonNull = Objects.nonNull((CharSequenceTranslator) obj);
                    return nonNull;
                }
            });
            Objects.requireNonNull(arrayList);
            filter.forEach(new Consumer() { // from class: org.apache.commons.text.translate.AggregateTranslator$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    arrayList.add((CharSequenceTranslator) obj);
                }
            });
        }
    }

    @Override // org.apache.commons.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
        for (CharSequenceTranslator charSequenceTranslator : this.translators) {
            int translate = charSequenceTranslator.translate(charSequence, i, writer);
            if (translate != 0) {
                return translate;
            }
        }
        return 0;
    }
}
