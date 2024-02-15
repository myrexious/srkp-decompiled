package org.apache.commons.lang3.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/* loaded from: classes2.dex */
public class DefaultExceptionContext implements ExceptionContext, Serializable {
    private static final long serialVersionUID = 20110706;
    private final List<Pair<String, Object>> contextValues = new ArrayList();

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public DefaultExceptionContext addContextValue(String str, Object obj) {
        this.contextValues.add(new ImmutablePair(str, obj));
        return this;
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public List<Pair<String, Object>> getContextEntries() {
        return this.contextValues;
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public Set<String> getContextLabels() {
        return (Set) stream().map(new Function() { // from class: org.apache.commons.lang3.exception.DefaultExceptionContext$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (String) ((Pair) obj).getKey();
            }
        }).collect(Collectors.toSet());
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public List<Object> getContextValues(final String str) {
        return (List) stream().filter(new Predicate() { // from class: org.apache.commons.lang3.exception.DefaultExceptionContext$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = StringUtils.equals(str, (CharSequence) ((Pair) obj).getKey());
                return equals;
            }
        }).map(new DefaultExceptionContext$$ExternalSyntheticLambda2()).collect(Collectors.toList());
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public Object getFirstContextValue(final String str) {
        return stream().filter(new Predicate() { // from class: org.apache.commons.lang3.exception.DefaultExceptionContext$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = StringUtils.equals(str, (CharSequence) ((Pair) obj).getKey());
                return equals;
            }
        }).findFirst().map(new DefaultExceptionContext$$ExternalSyntheticLambda2()).orElse(null);
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public String getFormattedExceptionMessage(String str) {
        String str2;
        StringBuilder sb = new StringBuilder(256);
        if (str != null) {
            sb.append(str);
        }
        if (!this.contextValues.isEmpty()) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append("Exception Context:\n");
            int i = 0;
            for (Pair<String, Object> pair : this.contextValues) {
                sb.append("\t[");
                i++;
                sb.append(i);
                sb.append(':');
                sb.append(pair.getKey());
                sb.append("=");
                Object value = pair.getValue();
                if (value == null) {
                    sb.append("null");
                } else {
                    try {
                        str2 = value.toString();
                    } catch (Exception e) {
                        str2 = "Exception thrown on toString(): " + ExceptionUtils.getStackTrace(e);
                    }
                    sb.append(str2);
                }
                sb.append("]\n");
            }
            sb.append("---------------------------------");
        }
        return sb.toString();
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public DefaultExceptionContext setContextValue(final String str, Object obj) {
        this.contextValues.removeIf(new Predicate() { // from class: org.apache.commons.lang3.exception.DefaultExceptionContext$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj2) {
                boolean equals;
                equals = StringUtils.equals(str, (CharSequence) ((Pair) obj2).getKey());
                return equals;
            }
        });
        addContextValue(str, obj);
        return this;
    }

    private Stream<Pair<String, Object>> stream() {
        return this.contextValues.stream();
    }
}
