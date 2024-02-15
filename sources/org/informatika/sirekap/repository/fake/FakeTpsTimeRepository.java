package org.informatika.sirekap.repository.fake;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.Date;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.response.EmptyApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.repository.DefaultTpsTimeRepository;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.NetworkBoundResource;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: FakeTpsTimeRepository.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJv\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00122\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/repository/fake/FakeTpsTimeRepository;", "Lorg/informatika/sirekap/repository/DefaultTpsTimeRepository;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "api", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/api/SirekapApiInterface;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "addTpsTime", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/TpsTime;", "kodeTps", "", "startDate", "Ljava/util/Date;", "startTime", "Lkotlin/Pair;", "", "endDate", "endTime", "jenisWaktu", "jenisPemilihan", "deviceId", "isOffline", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FakeTpsTimeRepository extends DefaultTpsTimeRepository {
    private final AppExecutors appExecutors;
    private final AppDatabase database;
    private final EncryptedSharedPreferences encryptedSharedPreferences;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Inject
    public FakeTpsTimeRepository(AppExecutors appExecutors, AppDatabase database, SirekapApiInterface api, EncryptedSharedPreferences encryptedSharedPreferences) {
        super(appExecutors, database, api, encryptedSharedPreferences);
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.appExecutors = appExecutors;
        this.database = database;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
    }

    @Override // org.informatika.sirekap.repository.DefaultTpsTimeRepository, org.informatika.sirekap.repository.TpsTimeRepository
    public LiveData<Resource<TpsTime>> addTpsTime(final String kodeTps, final Date startDate, final Pair<Integer, Integer> startTime, final Date endDate, final Pair<Integer, Integer> endTime, final int i, final String jenisPemilihan, String deviceId, final boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        return new NetworkBoundResource<TpsTime, EmptyApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.fake.FakeTpsTimeRepository$addTpsTime$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(TpsTime tpsTime) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(EmptyApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                FakeTpsTimeRepository.this.saveCallResultAddTpsTime(kodeTps, startDate, startTime, endDate, endTime, i, jenisPemilihan, z);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<TpsTime> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = FakeTpsTimeRepository.this.database;
                return appDatabase.tpsTimeDao().getByKodeTps(kodeTps, i, jenisPemilihan);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
                return new MutableLiveData(new ApiSuccessResponse(new EmptyApiResponse(true, "", null, 1), ""));
            }
        }.asLiveData();
    }
}
