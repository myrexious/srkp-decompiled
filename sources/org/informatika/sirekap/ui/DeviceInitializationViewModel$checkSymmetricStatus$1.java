package org.informatika.sirekap.ui;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DeviceInitializationViewModel.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.DeviceInitializationViewModel", f = "DeviceInitializationViewModel.kt", i = {}, l = {73}, m = "checkSymmetricStatus", n = {}, s = {})
/* loaded from: classes4.dex */
public final class DeviceInitializationViewModel$checkSymmetricStatus$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DeviceInitializationViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceInitializationViewModel$checkSymmetricStatus$1(DeviceInitializationViewModel deviceInitializationViewModel, Continuation<? super DeviceInitializationViewModel$checkSymmetricStatus$1> continuation) {
        super(continuation);
        this.this$0 = deviceInitializationViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.checkSymmetricStatus(this);
    }
}
