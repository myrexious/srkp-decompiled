package org.apache.commons.lang3;

import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class StringUtils$$ExternalSyntheticLambda0 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String stringOrEmpty;
        stringOrEmpty = StringUtils.toStringOrEmpty(obj);
        return stringOrEmpty;
    }
}
