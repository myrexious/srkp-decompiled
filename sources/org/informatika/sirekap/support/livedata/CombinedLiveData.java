package org.informatika.sirekap.support.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LiveDataUtil.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002BL\u0012\u001a\u0010\u0003\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004\"\u0006\u0012\u0002\b\u00030\u0005\u0012)\u0010\u0006\u001a%\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\t0\b¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\rR1\u0010\u0006\u001a%\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\t0\b¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "R", "Landroidx/lifecycle/MediatorLiveData;", "multipleLiveData", "", "Landroidx/lifecycle/LiveData;", "combine", "Lkotlin/Function1;", "", "", "Lkotlin/ParameterName;", "name", "datas", "([Landroidx/lifecycle/LiveData;Lkotlin/jvm/functions/Function1;)V", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CombinedLiveData<R> extends MediatorLiveData<R> {
    private final Function1<List<? extends Object>, R> combine;
    private final List<Object> datas;

    /* JADX WARN: Multi-variable type inference failed */
    public CombinedLiveData(LiveData<?>[] multipleLiveData, Function1<? super List<? extends Object>, ? extends R> combine) {
        Intrinsics.checkNotNullParameter(multipleLiveData, "multipleLiveData");
        Intrinsics.checkNotNullParameter(combine, "combine");
        this.combine = combine;
        int length = multipleLiveData.length;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(null);
        }
        this.datas = arrayList;
        int length2 = multipleLiveData.length;
        for (final int i2 = 0; i2 < length2; i2++) {
            super.addSource(multipleLiveData[i2], new CombinedLiveData$sam$androidx_lifecycle_Observer$0(new Function1(this) { // from class: org.informatika.sirekap.support.livedata.CombinedLiveData.1
                final /* synthetic */ CombinedLiveData<R> this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                    this.this$0 = this;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m1967invoke(obj);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke */
                public final void m1967invoke(Object obj) {
                    ((CombinedLiveData) this.this$0).datas.set(i2, obj);
                    CombinedLiveData<R> combinedLiveData = this.this$0;
                    combinedLiveData.setValue(((CombinedLiveData) combinedLiveData).combine.invoke(((CombinedLiveData) this.this$0).datas));
                }
            }));
        }
    }
}
