package org.apache.commons.lang3;

import java.util.function.Function;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class EnumUtils$$ExternalSyntheticLambda1 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String name;
        name = ((Enum) obj).name();
        return name;
    }
}
