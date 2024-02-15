package org.apache.commons.lang3.reflect;

import java.lang.reflect.Method;
import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class MethodUtils$$ExternalSyntheticLambda11 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String method;
        method = ((Method) obj).toString();
        return method;
    }
}
