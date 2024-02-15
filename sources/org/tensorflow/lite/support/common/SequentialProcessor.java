package org.tensorflow.lite.support.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;

/* loaded from: classes4.dex */
public class SequentialProcessor<T> implements Processor<T> {
    protected final Map<String, List<Integer>> operatorIndex;
    protected final List<Operator<T>> operatorList;

    public SequentialProcessor(Builder<T> builder) {
        this.operatorList = ((Builder) builder).operatorList;
        this.operatorIndex = Collections.unmodifiableMap(((Builder) builder).operatorIndex);
    }

    @Override // org.tensorflow.lite.support.common.Processor
    public T process(T x) {
        for (Operator<T> operator : this.operatorList) {
            x = operator.apply(x);
        }
        return x;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class Builder<T> {
        private final List<Operator<T>> operatorList = new ArrayList();
        private final Map<String, List<Integer>> operatorIndex = new HashMap();

        public Builder<T> add(Operator<T> op) {
            SupportPreconditions.checkNotNull(op, "Adding null Op is illegal.");
            this.operatorList.add(op);
            String name = op.getClass().getName();
            if (!this.operatorIndex.containsKey(name)) {
                this.operatorIndex.put(name, new ArrayList());
            }
            this.operatorIndex.get(name).add(Integer.valueOf(this.operatorList.size() - 1));
            return this;
        }

        public SequentialProcessor<T> build() {
            return new SequentialProcessor<>(this);
        }
    }
}
