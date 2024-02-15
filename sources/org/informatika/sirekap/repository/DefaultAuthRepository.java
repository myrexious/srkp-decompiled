package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.KeyCloakApiInterface;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.response.GetTpsByEmailApiResponse;
import org.informatika.sirekap.api.response.KeyCloakUserInfoApiResponse;
import org.informatika.sirekap.api.response.WilayahApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.db.dao.TpsDao;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageStage;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.UserInformation;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.NetworkBoundResource;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import retrofit2.Response;

/* compiled from: AuthRepository.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J$\u0010\u0013\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0016\u0018\u00010\u00150\u00142\u0006\u0010\u0018\u001a\u00020\u000eH\u0016J\n\u0010\u0019\u001a\u0004\u0018\u00010\u000eH\u0016J\n\u0010\u001a\u001a\u0004\u0018\u00010\u000eH\u0016J\n\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0016J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J \u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0004J0\u0010$\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u000eH\u0004J\b\u0010*\u001a\u00020\"H\u0016J \u0010+\u001a\u00020,2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u000eH\u0016J\b\u0010.\u001a\u00020\u001fH\u0016J\u0010\u0010/\u001a\u00020\u001f2\u0006\u00100\u001a\u00020\u000eH\u0016J\u0010\u00101\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\u0010H\u0016J\u0010\u00103\u001a\u00020\u001f2\u0006\u00104\u001a\u000205H\u0004J\u0010\u00106\u001a\u00020\u001f2\u0006\u00107\u001a\u00020\u000eH\u0016J\u0010\u00108\u001a\u00020\u001f2\u0006\u00109\u001a\u00020\u000eH\u0016J\u0010\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\u001dH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultAuthRepository;", "Lorg/informatika/sirekap/repository/AuthRepository;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "api", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "keyCloakApi", "Lorg/informatika/sirekap/api/KeyCloakApiInterface;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/api/SirekapApiInterface;Lorg/informatika/sirekap/api/KeyCloakApiInterface;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "getAccessToken", "", "getActiveProfile", "Lorg/informatika/sirekap/model/ActiveProfile;", "getAllProfiles", "Lorg/informatika/sirekap/api/response/KeyCloakUserInfoApiResponse;", "getAllTps", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "", "Lorg/informatika/sirekap/model/Tps;", "idWilayah", "getJwtSub", "getLoggedInUserId", "getRefreshToken", "getUserInformation", "Lorg/informatika/sirekap/model/UserInformation;", "insertDummyWitnesses", "", "kodeTps", "isPkwkp", "", "isPkwkk", "insertElection", "jmlLembar", "", "electionId", "electionType", "jenisPemilihan", "isAuthenticated", "loginSync", "Lorg/informatika/sirekap/api/response/WilayahApiResponse;", "profile", "logout", "saveAccessToken", "accessToken", "saveActiveProfile", "activeProfile", "saveCallResultGetAllTps", "item", "Lorg/informatika/sirekap/api/response/GetTpsByEmailApiResponse;", "saveJwtSub", "sub", "saveRefreshToken", "refreshToken", "setUserInformation", "userInformation", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DefaultAuthRepository implements AuthRepository {
    private final SirekapApiInterface api;
    private final AppExecutors appExecutors;
    private final AppDatabase database;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final KeyCloakApiInterface keyCloakApi;

    protected final void insertDummyWitnesses(String kodeTps, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
    }

    @Inject
    public DefaultAuthRepository(AppDatabase database, AppExecutors appExecutors, SirekapApiInterface api, KeyCloakApiInterface keyCloakApi, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(keyCloakApi, "keyCloakApi");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.database = database;
        this.appExecutors = appExecutors;
        this.api = api;
        this.keyCloakApi = keyCloakApi;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public void saveAccessToken(String accessToken) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        EncryptedSharedPreferences encryptedSharedPreferences = this.encryptedSharedPreferences;
        encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN, accessToken);
        encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN_UPLOAD, accessToken);
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public void saveRefreshToken(String refreshToken) {
        Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
        this.encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_REFRESH_TOKEN, refreshToken);
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public String getAccessToken() {
        return this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN, null);
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public String getRefreshToken() {
        return this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_REFRESH_TOKEN, null);
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public void saveActiveProfile(ActiveProfile activeProfile) {
        Intrinsics.checkNotNullParameter(activeProfile, "activeProfile");
        EncryptedSharedPreferences encryptedSharedPreferences = this.encryptedSharedPreferences;
        encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, activeProfile.getNama_profil());
        encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_KODE_WILAYAH, activeProfile.getKodeWilayah());
        encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_ROLE, activeProfile.getRole());
        encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE_START, activeProfile.getStart());
        encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE_END, activeProfile.getEnd());
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public ActiveProfile getActiveProfile() {
        String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
        String stringEncrypted2 = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_KODE_WILAYAH, null);
        String stringEncrypted3 = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ROLE, null);
        String stringEncrypted4 = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE_START, null);
        String str = stringEncrypted4 == null ? "" : stringEncrypted4;
        String stringEncrypted5 = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE_END, null);
        String str2 = stringEncrypted5 == null ? "" : stringEncrypted5;
        String str3 = stringEncrypted;
        if (str3 == null || StringsKt.isBlank(str3)) {
            return null;
        }
        return new ActiveProfile(stringEncrypted, stringEncrypted2, stringEncrypted2, stringEncrypted3, str, str2);
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public String getJwtSub() {
        return this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_JWT_SUB, null);
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public void saveJwtSub(String sub) {
        Intrinsics.checkNotNullParameter(sub, "sub");
        this.encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_JWT_SUB, sub);
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public UserInformation getUserInformation() {
        String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_USER_SID, "");
        String stringEncrypted2 = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_USER_REAL_NAME, "");
        if (stringEncrypted == null || stringEncrypted2 == null) {
            return null;
        }
        return new UserInformation(stringEncrypted, stringEncrypted2);
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public void setUserInformation(UserInformation userInformation) {
        Intrinsics.checkNotNullParameter(userInformation, "userInformation");
        this.encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_USER_SID, userInformation.getUserId());
        this.encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_USER_REAL_NAME, userInformation.getName());
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public String getLoggedInUserId() {
        return this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_USER_ID, null);
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public boolean isAuthenticated() {
        String loggedInUserId = getLoggedInUserId();
        return !(loggedInUserId == null || StringsKt.isBlank(loggedInUserId));
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public WilayahApiResponse loginSync(String kodeTps, String jenisPemilihan, String profile) {
        String message;
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(profile, "profile");
        Response<WilayahApiResponse> execute = this.api.getWilayahSync("https://sirekap-api.kpu.go.id/" + profile + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/wilayah/tps/get", kodeTps).execute();
        if (execute.isSuccessful()) {
            WilayahApiResponse body = execute.body();
            if (body == null || !body.isSuccess()) {
                throw new Exception(body != null ? body.getMessage() : null);
            }
            return body;
        } else if (execute.code() == 502) {
            throw new Exception("Saat ini server sedang sibuk, silahkan coba beberapa saat lagi");
        } else {
            ResponseBody errorBody = execute.errorBody();
            if (errorBody == null || (message = errorBody.string()) == null) {
                message = execute.message();
            }
            throw new Exception(message);
        }
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public void logout() {
        EncryptedSharedPreferences encryptedSharedPreferences = this.encryptedSharedPreferences;
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_USER_ID);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_KODE_AKSES);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_ACCESS_TOKEN_UPLOAD);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_REFRESH_TOKEN);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_PROFILE);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_KODE_WILAYAH);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_USER_REAL_NAME);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_USER_SID);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_ROLE);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_JWT_SUB);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_PROFILE_START);
        encryptedSharedPreferences.removeEncrypted(SharedPreferencesModule.SP_KEY_PROFILE_END);
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public LiveData<Resource<List<Tps>>> getAllTps(final String idWilayah) {
        Intrinsics.checkNotNullParameter(idWilayah, "idWilayah");
        return new NetworkBoundResource<List<? extends Tps>, GetTpsByEmailApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultAuthRepository$getAllTps$1
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
                DefaultAuthRepository.this.saveCallResultGetAllTps(item);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<List<? extends Tps>> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultAuthRepository.this.database;
                return appDatabase.tpsDao().getAll();
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<GetTpsByEmailApiResponse>> createCall() {
                EncryptedSharedPreferences encryptedSharedPreferences;
                SirekapApiInterface sirekapApiInterface;
                encryptedSharedPreferences = DefaultAuthRepository.this.encryptedSharedPreferences;
                String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
                sirekapApiInterface = DefaultAuthRepository.this.api;
                return sirekapApiInterface.getListTpsByIdWilayah("https://sirekap-api.kpu.go.id/" + stringEncrypted + "/ppwp/wilayah/tps/list", idWilayah);
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.AuthRepository
    public KeyCloakUserInfoApiResponse getAllProfiles() {
        String message;
        Response<KeyCloakUserInfoApiResponse> execute = this.keyCloakApi.getUserInfo().execute();
        if (execute.isSuccessful()) {
            KeyCloakUserInfoApiResponse body = execute.body();
            if (body != null) {
                return body;
            }
            throw new Exception("body is null");
        } else if (execute.code() == 502) {
            throw new Exception("Saat ini server sedang sibuk, silahkan coba beberapa saat lagi");
        } else {
            ResponseBody errorBody = execute.errorBody();
            if (errorBody == null || (message = errorBody.string()) == null) {
                message = execute.message();
            }
            throw new Exception(message);
        }
    }

    public final void saveCallResultGetAllTps(GetTpsByEmailApiResponse item) {
        Intrinsics.checkNotNullParameter(item, "item");
        final List<GetTpsByEmailApiResponse.TpsByEmailApiResponse> data = item.getData();
        if (data != null) {
            this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultAuthRepository$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultAuthRepository.saveCallResultGetAllTps$lambda$7$lambda$6(DefaultAuthRepository.this, data);
                }
            });
        }
    }

    public static final void saveCallResultGetAllTps$lambda$7$lambda$6(DefaultAuthRepository this$0, List this_apply) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this$0.database.tpsDao().deleteAll();
        TpsDao tpsDao = this$0.database.tpsDao();
        List<GetTpsByEmailApiResponse.TpsByEmailApiResponse> list = this_apply;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (GetTpsByEmailApiResponse.TpsByEmailApiResponse tpsByEmailApiResponse : list) {
            arrayList.add(tpsByEmailApiResponse.toTPS());
        }
        tpsDao.insertAll(arrayList);
    }

    protected final void insertElection(String kodeTps, int i, String electionId, String electionType, String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(electionType, "electionType");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        int i2 = i - 2;
        ArrayList<ElectionPage> arrayList = new ArrayList();
        arrayList.add(new ElectionPage(kodeTps + "-" + electionId + "-10-1" + electionType, jenisPemilihan, electionId, 10, 1));
        if (1 <= i2) {
            int i3 = 1;
            while (true) {
                arrayList.add(new ElectionPage(kodeTps + "-" + electionId + "-20-" + i3 + electionType, jenisPemilihan, electionId, 20, i3));
                if (i3 == i2) {
                    break;
                }
                i3++;
            }
        }
        arrayList.add(new ElectionPage(kodeTps + "-" + electionId + "-30-1" + electionType, jenisPemilihan, electionId, 30, 1));
        this.database.electionPageDao().insertAll(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (ElectionPage electionPage : arrayList) {
            arrayList2.add(new ElectionPageStage(electionId, electionPage.getId(), ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_PHOTO, -1, false, 16, null));
            arrayList2.add(new ElectionPageStage(electionId, electionPage.getId(), ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND, -1, false, 16, null));
            arrayList2.add(new ElectionPageStage(electionId, electionPage.getId(), ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_VERIFY, -1, false, 16, null));
        }
        this.database.electionPageStageDao().insertAll(arrayList2);
    }
}
