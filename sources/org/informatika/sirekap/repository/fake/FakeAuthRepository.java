package org.informatika.sirekap.repository.fake;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;
import org.informatika.sirekap.api.KeyCloakApiInterface;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.response.GetTpsByEmailApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.repository.DefaultAuthRepository;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.NetworkBoundResource;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: FakeAuthRepository.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ$\u0010\r\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0018\u00010\u000f0\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/informatika/sirekap/repository/fake/FakeAuthRepository;", "Lorg/informatika/sirekap/repository/DefaultAuthRepository;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "api", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "keyCloakApiInterface", "Lorg/informatika/sirekap/api/KeyCloakApiInterface;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/api/SirekapApiInterface;Lorg/informatika/sirekap/api/KeyCloakApiInterface;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "getAllTps", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "", "Lorg/informatika/sirekap/model/Tps;", "idWilayah", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FakeAuthRepository extends DefaultAuthRepository {
    private final AppExecutors appExecutors;
    private final AppDatabase database;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Inject
    public FakeAuthRepository(AppDatabase database, AppExecutors appExecutors, SirekapApiInterface api, KeyCloakApiInterface keyCloakApiInterface, EncryptedSharedPreferences encryptedSharedPreferences) {
        super(database, appExecutors, api, keyCloakApiInterface, encryptedSharedPreferences);
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(keyCloakApiInterface, "keyCloakApiInterface");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.database = database;
        this.appExecutors = appExecutors;
    }

    @Override // org.informatika.sirekap.repository.DefaultAuthRepository, org.informatika.sirekap.repository.AuthRepository
    public LiveData<Resource<List<Tps>>> getAllTps(String idWilayah) {
        Intrinsics.checkNotNullParameter(idWilayah, "idWilayah");
        return new NetworkBoundResource<List<? extends Tps>, GetTpsByEmailApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.fake.FakeAuthRepository$getAllTps$1
            /* renamed from: shouldFetch  reason: avoid collision after fix types in other method */
            protected boolean shouldFetch2(List<Tps> list) {
                return true;
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public /* bridge */ /* synthetic */ boolean shouldFetch(List<? extends Tps> list) {
                return shouldFetch2((List<Tps>) list);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(GetTpsByEmailApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                FakeAuthRepository.this.saveCallResultGetAllTps(item);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<List<? extends Tps>> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = FakeAuthRepository.this.database;
                return appDatabase.tpsDao().getAll();
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<GetTpsByEmailApiResponse>> createCall() {
                return new MutableLiveData(new ApiSuccessResponse(new GetTpsByEmailApiResponse(true, "", null, CollectionsKt.listOf(new GetTpsByEmailApiResponse.TpsByEmailApiResponse(18L, null, null, null, null, null, "TPS 2", null, null, null, null, null, null, "DKI JAKARTA", "KOTA ADM. JAKARTA SELATAN", "TEBET", "TEBET BARAT", null, null, null, null, null, null, null, null, null, "3174011002002", null))), ""));
            }
        }.asLiveData();
    }
}
