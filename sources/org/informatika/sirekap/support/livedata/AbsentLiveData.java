package org.informatika.sirekap.support.livedata;

import androidx.lifecycle.LiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AbsentLiveData.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u0004*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/support/livedata/AbsentLiveData;", "T", "Landroidx/lifecycle/LiveData;", "()V", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AbsentLiveData<T> extends LiveData<T> {
    public static final Companion Companion = new Companion(null);

    public /* synthetic */ AbsentLiveData(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private AbsentLiveData() {
        postValue(null);
    }

    /* compiled from: AbsentLiveData.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/support/livedata/AbsentLiveData$Companion;", "", "()V", "create", "Landroidx/lifecycle/LiveData;", "T", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> LiveData<T> create() {
            return new AbsentLiveData(null);
        }
    }
}
