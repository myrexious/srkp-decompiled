package org.informatika.sirekap.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.google.firebase.messaging.Constants;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.repository.TpsTimeRepository;
import org.informatika.sirekap.support.CombinedLiveData;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.usecase.GetTpsTimeUseCase;

/* compiled from: GetTpsTimeUseCase.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u000fR\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u001f\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00180\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\tR\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006%"}, d2 = {"Lorg/informatika/sirekap/usecase/GetTpsTimeUseCase;", "", "tpsTimeRepository", "Lorg/informatika/sirekap/repository/TpsTimeRepository;", "(Lorg/informatika/sirekap/repository/TpsTimeRepository;)V", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/TpsTime;", "getData", "()Landroidx/lifecycle/LiveData;", "isDataExist", "", "isLoading", "jenisPemilihan", "Landroidx/lifecycle/MutableLiveData;", "", "getJenisPemilihan", "()Landroidx/lifecycle/MutableLiveData;", "jenisWaktu", "", "getJenisWaktu", "kodeTps", "getKodeTps", "resource", "Lorg/informatika/sirekap/support/Resource;", "getResource", "timeTpsParameter", "Lorg/informatika/sirekap/support/CombinedLiveData;", "Lorg/informatika/sirekap/usecase/GetTpsTimeUseCase$TimeTpsParameter;", "getTimeTpsParameter", "()Lorg/informatika/sirekap/support/CombinedLiveData;", "setup", "", "_kodeTps", "_jenisWaktu", "_jenisPemilihan", "TimeTpsParameter", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GetTpsTimeUseCase {
    private final LiveData<TpsTime> data;
    private final LiveData<Boolean> isDataExist;
    private final LiveData<Boolean> isLoading;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<Integer> jenisWaktu;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<Resource<TpsTime>> resource;
    private final CombinedLiveData<TimeTpsParameter> timeTpsParameter;

    public GetTpsTimeUseCase(final TpsTimeRepository tpsTimeRepository) {
        Intrinsics.checkNotNullParameter(tpsTimeRepository, "tpsTimeRepository");
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData;
        MutableLiveData<Integer> mutableLiveData2 = new MutableLiveData<>(null);
        this.jenisWaktu = mutableLiveData2;
        MutableLiveData<String> mutableLiveData3 = new MutableLiveData<>(null);
        this.jenisPemilihan = mutableLiveData3;
        CombinedLiveData<TimeTpsParameter> combinedLiveData = new CombinedLiveData<>(new LiveData[]{mutableLiveData, mutableLiveData2, mutableLiveData3}, new Function1<List<? extends Object>, TimeTpsParameter>() { // from class: org.informatika.sirekap.usecase.GetTpsTimeUseCase$timeTpsParameter$1
            @Override // kotlin.jvm.functions.Function1
            public final GetTpsTimeUseCase.TimeTpsParameter invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        if (obj == null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (z2) {
                    return null;
                }
                Object obj2 = data.get(0);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
                Object obj3 = data.get(1);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                int intValue = ((Integer) obj3).intValue();
                Object obj4 = data.get(2);
                Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.String");
                return new GetTpsTimeUseCase.TimeTpsParameter((String) obj2, intValue, (String) obj4);
            }
        });
        this.timeTpsParameter = combinedLiveData;
        LiveData<Resource<TpsTime>> switchMap = Transformations.switchMap(combinedLiveData, new Function1<TimeTpsParameter, LiveData<Resource<TpsTime>>>() { // from class: org.informatika.sirekap.usecase.GetTpsTimeUseCase$resource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<TpsTime>> invoke(GetTpsTimeUseCase.TimeTpsParameter timeTpsParameter) {
                if (timeTpsParameter == null) {
                    return AbsentLiveData.Companion.create();
                }
                return TpsTimeRepository.this.getTpsTime(timeTpsParameter.getKodeTps(), timeTpsParameter.getJenisWaktu(), timeTpsParameter.getJenisPemilihan());
            }
        });
        this.resource = switchMap;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<TpsTime>, Boolean>() { // from class: org.informatika.sirekap.usecase.GetTpsTimeUseCase$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<TpsTime> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        LiveData<TpsTime> map = Transformations.map(switchMap, new Function1<Resource<TpsTime>, TpsTime>() { // from class: org.informatika.sirekap.usecase.GetTpsTimeUseCase$data$1
            @Override // kotlin.jvm.functions.Function1
            public final TpsTime invoke(Resource<TpsTime> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.data = map;
        this.isDataExist = Transformations.map(map, new Function1<TpsTime, Boolean>() { // from class: org.informatika.sirekap.usecase.GetTpsTimeUseCase$isDataExist$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(TpsTime tpsTime) {
                return Boolean.valueOf(tpsTime != null);
            }
        });
    }

    public final MutableLiveData<String> getKodeTps() {
        return this.kodeTps;
    }

    public final MutableLiveData<Integer> getJenisWaktu() {
        return this.jenisWaktu;
    }

    public final MutableLiveData<String> getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final void setup(String _kodeTps, int i, String _jenisPemilihan) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        Intrinsics.checkNotNullParameter(_jenisPemilihan, "_jenisPemilihan");
        if (!Intrinsics.areEqual(this.kodeTps.getValue(), _kodeTps)) {
            this.kodeTps.setValue(_kodeTps);
        }
        Integer value = this.jenisWaktu.getValue();
        if (value == null || value.intValue() != i) {
            this.jenisWaktu.setValue(Integer.valueOf(i));
        }
        if (Intrinsics.areEqual(this.jenisPemilihan.getValue(), _jenisPemilihan)) {
            return;
        }
        this.jenisPemilihan.setValue(_jenisPemilihan);
    }

    /* compiled from: GetTpsTimeUseCase.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/usecase/GetTpsTimeUseCase$TimeTpsParameter;", "", "kodeTps", "", "jenisWaktu", "", "jenisPemilihan", "(Ljava/lang/String;ILjava/lang/String;)V", "getJenisPemilihan", "()Ljava/lang/String;", "getJenisWaktu", "()I", "getKodeTps", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class TimeTpsParameter {
        private final String jenisPemilihan;
        private final int jenisWaktu;
        private final String kodeTps;

        public static /* synthetic */ TimeTpsParameter copy$default(TimeTpsParameter timeTpsParameter, String str, int i, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = timeTpsParameter.kodeTps;
            }
            if ((i2 & 2) != 0) {
                i = timeTpsParameter.jenisWaktu;
            }
            if ((i2 & 4) != 0) {
                str2 = timeTpsParameter.jenisPemilihan;
            }
            return timeTpsParameter.copy(str, i, str2);
        }

        public final String component1() {
            return this.kodeTps;
        }

        public final int component2() {
            return this.jenisWaktu;
        }

        public final String component3() {
            return this.jenisPemilihan;
        }

        public final TimeTpsParameter copy(String kodeTps, int i, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            return new TimeTpsParameter(kodeTps, i, jenisPemilihan);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof TimeTpsParameter) {
                TimeTpsParameter timeTpsParameter = (TimeTpsParameter) obj;
                return Intrinsics.areEqual(this.kodeTps, timeTpsParameter.kodeTps) && this.jenisWaktu == timeTpsParameter.jenisWaktu && Intrinsics.areEqual(this.jenisPemilihan, timeTpsParameter.jenisPemilihan);
            }
            return false;
        }

        public int hashCode() {
            return (((this.kodeTps.hashCode() * 31) + Integer.hashCode(this.jenisWaktu)) * 31) + this.jenisPemilihan.hashCode();
        }

        public String toString() {
            String str = this.kodeTps;
            int i = this.jenisWaktu;
            return "TimeTpsParameter(kodeTps=" + str + ", jenisWaktu=" + i + ", jenisPemilihan=" + this.jenisPemilihan + ")";
        }

        public TimeTpsParameter(String kodeTps, int i, String jenisPemilihan) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
            this.kodeTps = kodeTps;
            this.jenisWaktu = i;
            this.jenisPemilihan = jenisPemilihan;
        }

        public final String getKodeTps() {
            return this.kodeTps;
        }

        public final int getJenisWaktu() {
            return this.jenisWaktu;
        }

        public final String getJenisPemilihan() {
            return this.jenisPemilihan;
        }
    }

    public final CombinedLiveData<TimeTpsParameter> getTimeTpsParameter() {
        return this.timeTpsParameter;
    }

    public final LiveData<Resource<TpsTime>> getResource() {
        return this.resource;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final LiveData<TpsTime> getData() {
        return this.data;
    }

    public final LiveData<Boolean> isDataExist() {
        return this.isDataExist;
    }
}
