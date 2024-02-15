package org.apache.commons.lang3.builder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class DiffResult<T> implements Iterable<Diff<?>> {
    private static final String DIFFERS_STRING = "differs from";
    public static final String OBJECTS_SAME_STRING = "";
    private final List<Diff<?>> diffList;
    private final T lhs;
    private final T rhs;
    private final ToStringStyle style;

    public DiffResult(T t, T t2, List<Diff<?>> list, ToStringStyle toStringStyle) {
        Objects.requireNonNull(t, "lhs");
        Objects.requireNonNull(t2, "rhs");
        Objects.requireNonNull(list, "diffList");
        this.diffList = list;
        this.lhs = t;
        this.rhs = t2;
        if (toStringStyle == null) {
            this.style = ToStringStyle.DEFAULT_STYLE;
        } else {
            this.style = toStringStyle;
        }
    }

    public T getLeft() {
        return this.lhs;
    }

    public T getRight() {
        return this.rhs;
    }

    public List<Diff<?>> getDiffs() {
        return Collections.unmodifiableList(this.diffList);
    }

    public int getNumberOfDiffs() {
        return this.diffList.size();
    }

    public ToStringStyle getToStringStyle() {
        return this.style;
    }

    public String toString() {
        return toString(this.style);
    }

    public String toString(ToStringStyle toStringStyle) {
        if (this.diffList.isEmpty()) {
            return "";
        }
        final ToStringBuilder toStringBuilder = new ToStringBuilder(this.lhs, toStringStyle);
        final ToStringBuilder toStringBuilder2 = new ToStringBuilder(this.rhs, toStringStyle);
        this.diffList.forEach(new Consumer() { // from class: org.apache.commons.lang3.builder.DiffResult$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DiffResult.lambda$toString$0(ToStringBuilder.this, toStringBuilder2, (Diff) obj);
            }
        });
        return String.format("%s %s %s", toStringBuilder.build(), DIFFERS_STRING, toStringBuilder2.build());
    }

    public static /* synthetic */ void lambda$toString$0(ToStringBuilder toStringBuilder, ToStringBuilder toStringBuilder2, Diff diff) {
        toStringBuilder.append(diff.getFieldName(), diff.getLeft());
        toStringBuilder2.append(diff.getFieldName(), diff.getRight());
    }

    @Override // java.lang.Iterable
    public Iterator<Diff<?>> iterator() {
        return this.diffList.iterator();
    }
}
