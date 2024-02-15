package org.informatika.sirekap.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.gson.Gson;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.response.EmptyApiResponse;
import org.informatika.sirekap.api.response.FormC1ImageApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.db.dao.FormC1KesesuaianTabulationCandidateVoteDao;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.ElectionPageStage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.FormC1Administration;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1AdministrationHal2Payload;
import org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete;
import org.informatika.sirekap.model.FormC1AdministrationPayload;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1KesesuaianAdministration;
import org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationPartai;
import org.informatika.sirekap.model.FormC1TabulationCandidateVote;
import org.informatika.sirekap.model.FormC1TabulationComplete;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
import org.informatika.sirekap.model.FormConfig;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.CrashlyticsUtil;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.NetworkBoundResource;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.vision.Vision;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: FormC1Repository.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\b\u0016\u0018\u0000 R2\u00020\u0001:\u0001RB9\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ8\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J8\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\u0018\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J8\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J8\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J6\u0010\u001e\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010 0\u001f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0012H\u0016J6\u0010$\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020%\u0018\u00010 0\u001f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0012H\u0016J6\u0010&\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020'\u0018\u00010 0\u001f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0012H\u0016J6\u0010(\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020)\u0018\u00010 0\u001f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0012H\u0016J6\u0010*\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020+\u0018\u00010 0\u001f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0012H\u0016J\u0010\u0010,\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J*\u0010-\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0.\u0018\u00010 0\u001f2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120.H\u0016J*\u00100\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0.\u0018\u00010 0\u001f2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120.H\u0016J(\u00101\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002032\u0006\u00105\u001a\u00020\u0012H\u0002J \u00106\u001a\u00020\u00102\u0006\u00107\u001a\u0002082\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0004J\u0018\u00109\u001a\u00020\u00102\u0006\u00107\u001a\u0002082\u0006\u0010\u0011\u001a\u00020\u0012H\u0004J\u0018\u0010:\u001a\u00020\u00102\u0006\u00107\u001a\u0002082\u0006\u0010\u0011\u001a\u00020\u0012H\u0004J\u0018\u0010;\u001a\u00020\u00102\u0006\u00107\u001a\u0002082\u0006\u0010\u0011\u001a\u00020\u0012H\u0004J\u0018\u0010<\u001a\u00020\u00102\u0006\u00107\u001a\u0002082\u0006\u0010\u0011\u001a\u00020\u0012H\u0004J \u0010=\u001a\u00020\u00102\u0006\u00107\u001a\u0002082\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010>\u001a\u00020\u0012H\u0004J\u0018\u0010?\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010@\u001a\u00020AH\u0016J0\u0010B\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u000e\u0010C\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170.2\u000e\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001030.H\u0016J\u0018\u0010E\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010F\u001a\u00020GH\u0016J1\u0010H\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010I\u001a\u0002032\b\u0010J\u001a\u0004\u0018\u00010\u00172\b\u0010K\u001a\u0004\u0018\u000103H\u0016¢\u0006\u0002\u0010LJ0\u0010M\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u000e\u0010C\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170.2\u000e\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001030.H\u0016Jv\u0010N\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020!\u0018\u00010 0\u001f2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010O\u001a\u00020\u00122\u0006\u00102\u001a\u0002032\u0006\u0010>\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010J\u001a\u00020\u00172\u000e\u0010C\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170.2\u0006\u0010P\u001a\u00020\u00122\u000e\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001030.2\u0006\u0010Q\u001a\u00020\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultFormC1Repository;", "Lorg/informatika/sirekap/repository/FormC1Repository;", "context", "Landroid/content/Context;", "api", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Landroid/content/Context;Lorg/informatika/sirekap/api/SirekapApiInterface;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Landroid/content/SharedPreferences;)V", "createEmptyFormC1Administration", "", "idImage", "", "jenisPemilihan", "correctedBitmap", "Landroid/graphics/Bitmap;", "isLn", "", "isPos", "maxAttemptReach", "createEmptyFormC1AdministrationHal2", "createEmptyFormC1AdministrationHal2Ppwp", "createEmptyFormC1Tabulation", "createEmptyFormC1TabulationPartai", "getFormC1Administration", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "isFetchApi", "kodeTpsOverride", "getFormC1AdministrationHal2", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "getFormC1AdministrationHal2Ppwp", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2PpwpComplete;", "getFormC1Tabulation", "Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "getFormC1TabulationPartai", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "getIsUserMustWait", "getListFormC1Tabulation", "", "idImages", "getListFormC1TabulationPartai", "insertError", "formType", "", "errorType", "error", "saveCallResultGetFormC1Administration", "item", "Lorg/informatika/sirekap/api/response/FormC1ImageApiResponse;", "saveCallResultGetFormC1AdministrationHal2", "saveCallResultGetFormC1AdministrationHal2Ppwp", "saveCallResultGetFormC1Tabulation", "saveCallResultGetFormC1TabulationPartai", "saveCallResultVerifyFormC1", "electionPageId", "saveFormC1KesesuaianAdministration", "formC1KesesuaianAdministration", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration;", "saveFormC1KesesuaianAdministrationHal2", "isSesuaiPerItem", "koreksiPerItem", "saveFormC1KesesuaianAdministrationHal2New", "formC1KesesuaianAdministrationHal2", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "saveFormC1KesesuaianTabulationCandidateVote", FirebaseAnalytics.Param.INDEX, "isSesuai", "koreksi", "(Ljava/lang/String;ILjava/lang/Boolean;Ljava/lang/Integer;)V", "saveFormC1KesesuaianTabulationPartai", "verifyFormC1", "deviceId", "komentar", "kodeTps", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DefaultFormC1Repository implements FormC1Repository {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DefaultFormC1Repo";
    private final SirekapApiInterface api;
    private final AppExecutors appExecutors;
    private final Context context;
    private final AppDatabase database;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final SharedPreferences sharedPreferences;

    @Inject
    public DefaultFormC1Repository(@ApplicationContext Context context, SirekapApiInterface api, AppDatabase database, AppExecutors appExecutors, EncryptedSharedPreferences encryptedSharedPreferences, SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        this.context = context;
        this.api = api;
        this.database = database;
        this.appExecutors = appExecutors;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.sharedPreferences = sharedPreferences;
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1AdministrationComplete>> getFormC1Administration(final String idImage, final String jenisPemilihan, final boolean z, final String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        return new NetworkBoundResource<FormC1AdministrationComplete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$getFormC1Administration$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                DefaultFormC1Repository.this.saveCallResultGetFormC1Administration(item, idImage, jenisPemilihan);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1AdministrationComplete formC1AdministrationComplete) {
                FormC1Error error;
                return (formC1AdministrationComplete != null ? formC1AdministrationComplete.getForm() : null) == null && !(formC1AdministrationComplete != null && (error = formC1AdministrationComplete.getError()) != null && error.getErrorType() == 20) && z;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1AdministrationComplete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultFormC1Repository.this.database;
                return appDatabase.formC1AdministrationDao().getByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<FormC1ImageApiResponse>> createCall() {
                boolean isUserMustWait;
                EncryptedSharedPreferences encryptedSharedPreferences;
                SirekapApiInterface sirekapApiInterface;
                isUserMustWait = DefaultFormC1Repository.this.getIsUserMustWait(idImage);
                if (!isUserMustWait) {
                    encryptedSharedPreferences = DefaultFormC1Repository.this.encryptedSharedPreferences;
                    String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
                    int extractIdImageReal = ElectionUtil.extractIdImageReal(idImage);
                    String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTpsOverride);
                    sirekapApiInterface = DefaultFormC1Repository.this.api;
                    return sirekapApiInterface.getFormCImage("https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/formc-image/get", extractIdImageReal, extractKodeTpsReal);
                }
                return new MutableLiveData(new ApiSuccessResponse(new FormC1ImageApiResponse(true, "", null, new FormC1ImageApiResponse.FormCImageApiResponseDetail(CollectionsKt.emptyList(), null)), (String) null));
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public void createEmptyFormC1Administration(final String idImage, final String jenisPemilihan, final Bitmap correctedBitmap, final boolean z, final boolean z2, final boolean z3) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(correctedBitmap, "correctedBitmap");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.createEmptyFormC1Administration$lambda$1(DefaultFormC1Repository.this, idImage, z3, correctedBitmap, jenisPemilihan, z, z2);
            }
        });
    }

    public static final void createEmptyFormC1Administration$lambda$1(DefaultFormC1Repository this$0, final String idImage, final boolean z, final Bitmap correctedBitmap, final String jenisPemilihan, final boolean z2, final boolean z3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(correctedBitmap, "$correctedBitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.createEmptyFormC1Administration$lambda$1$lambda$0(DefaultFormC1Repository.this, idImage, z, correctedBitmap, jenisPemilihan, z2, z3);
            }
        });
    }

    public static final void createEmptyFormC1Administration$lambda$1$lambda$0(DefaultFormC1Repository this$0, String idImage, boolean z, Bitmap correctedBitmap, String jenisPemilihan, boolean z2, boolean z3) {
        int i;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(correctedBitmap, "$correctedBitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        try {
            ElectionPageWithRelation byIdImage = this$0.database.electionPageDao().getByIdImage(idImage);
            i = 1;
            try {
                FormC1Administration createEmpty = FormC1Administration.Companion.createEmpty(idImage, byIdImage.getElection().isLn(), byIdImage.getElection().isLnPos(), jenisPemilihan, !z ? new Vision(this$0.context).predict(correctedBitmap, FormConfig.FORM_ADMIN1, jenisPemilihan, z2, z3, 0, 0) : null);
                this$0.database.formC1ErrorDao().deleteAll(idImage, 1);
                this$0.database.formC1KesesuaianAdministrasiDao().insertAll(CollectionsKt.listOf(FormC1KesesuaianAdministration.Companion.createFilled(idImage, createEmpty.isLnPos(), jenisPemilihan)));
                this$0.database.formC1AdministrationDao().insertAll(CollectionsKt.listOf(createEmpty));
                this$0.database.electionPageDao().markAsContinueVerify(byIdImage.getElectionPage().getId());
                this$0.database.electionPageDao().removeVisionError(idImage);
            } catch (Exception e) {
                e = e;
                String message = e.getMessage();
                if (message == null) {
                    message = "";
                }
                this$0.insertError(idImage, i, 20, message);
                this$0.database.electionPageDao().addVisionError(idImage);
            }
        } catch (Exception e2) {
            e = e2;
            i = 1;
        }
    }

    public final void saveCallResultGetFormC1Administration(FormC1ImageApiResponse item, final String idImage, final String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        final FormC1ImageApiResponse.FormCImageApiResponseDetail data = item.getData();
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveCallResultGetFormC1Administration$lambda$3$lambda$2(FormC1ImageApiResponse.FormCImageApiResponseDetail.this, this, idImage, jenisPemilihan);
            }
        });
    }

    public static final void saveCallResultGetFormC1Administration$lambda$3$lambda$2(FormC1ImageApiResponse.FormCImageApiResponseDetail this_apply, DefaultFormC1Repository this$0, String idImage, String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        if (!this_apply.getImagePayloads().isEmpty()) {
            try {
                ElectionPageWithRelation byIdImage = this$0.database.electionPageDao().getByIdImage(idImage);
                if (byIdImage.getElectionPage().isContinueVerify()) {
                    return;
                }
                boolean z = false;
                FormC1AdministrationPayload payload = (FormC1AdministrationPayload) new Gson().fromJson(this_apply.getImagePayloads().get(0).getPayload(), (Class<Object>) FormC1AdministrationPayload.class);
                String error = payload.getError();
                if (error == null || StringsKt.isBlank(error)) {
                    z = true;
                }
                if (!z) {
                    this$0.insertError(idImage, 1, 20, payload.getError());
                    return;
                }
                FormC1Administration.Companion companion = FormC1Administration.Companion;
                Intrinsics.checkNotNullExpressionValue(payload, "payload");
                FormC1Administration createFromFormC1AdministrationPayload = companion.createFromFormC1AdministrationPayload(payload, idImage, byIdImage.getElection().isLn(), byIdImage.getElection().isLnPos(), jenisPemilihan);
                this$0.database.formC1ErrorDao().deleteAll(idImage, 1);
                this$0.database.formC1KesesuaianAdministrasiDao().insertAll(CollectionsKt.listOf(FormC1KesesuaianAdministration.Companion.createFilled(idImage, createFromFormC1AdministrationPayload.isLnPos(), jenisPemilihan)));
                this$0.database.formC1AdministrationDao().insertAll(CollectionsKt.listOf(createFromFormC1AdministrationPayload));
                this$0.database.electionPageDao().removeVisionError(idImage);
                return;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                String message = e.getMessage();
                if (message == null) {
                    message = "Error parsing JSON";
                }
                this$0.insertError(idImage, 1, 20, message);
                return;
            }
        }
        this$0.insertError(idImage, 1, 10, "Gambar yang Anda kirim belum selesai diproses. Harap tunggu beberapa saat.");
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1TabulationComplete>> getFormC1Tabulation(final String idImage, final String jenisPemilihan, final boolean z, final String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        return new NetworkBoundResource<FormC1TabulationComplete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$getFormC1Tabulation$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                DefaultFormC1Repository.this.saveCallResultGetFormC1Tabulation(item, idImage);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1TabulationComplete formC1TabulationComplete) {
                List<FormC1TabulationCandidateVote> votes;
                FormC1Error error;
                return (formC1TabulationComplete != null && (votes = formC1TabulationComplete.getVotes()) != null && votes.isEmpty()) && !(formC1TabulationComplete != null && (error = formC1TabulationComplete.getError()) != null && error.getErrorType() == 20) && z;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1TabulationComplete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultFormC1Repository.this.database;
                return appDatabase.formC1TabulationDao().getByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<FormC1ImageApiResponse>> createCall() {
                boolean isUserMustWait;
                EncryptedSharedPreferences encryptedSharedPreferences;
                SirekapApiInterface sirekapApiInterface;
                isUserMustWait = DefaultFormC1Repository.this.getIsUserMustWait(idImage);
                if (!isUserMustWait) {
                    encryptedSharedPreferences = DefaultFormC1Repository.this.encryptedSharedPreferences;
                    String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
                    int extractIdImageReal = ElectionUtil.extractIdImageReal(idImage);
                    String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTpsOverride);
                    sirekapApiInterface = DefaultFormC1Repository.this.api;
                    return sirekapApiInterface.getFormCImage("https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/formc-image/get", extractIdImageReal, extractKodeTpsReal);
                }
                return new MutableLiveData(new ApiSuccessResponse(new FormC1ImageApiResponse(true, "", null, new FormC1ImageApiResponse.FormCImageApiResponseDetail(CollectionsKt.emptyList(), null)), (String) null));
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public void createEmptyFormC1Tabulation(final String idImage, final String jenisPemilihan, final Bitmap correctedBitmap, final boolean z, final boolean z2, final boolean z3) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(correctedBitmap, "correctedBitmap");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.createEmptyFormC1Tabulation$lambda$6(DefaultFormC1Repository.this, idImage, z3, correctedBitmap, jenisPemilihan, z, z2);
            }
        });
    }

    public static final void createEmptyFormC1Tabulation$lambda$6(DefaultFormC1Repository this$0, final String idImage, final boolean z, final Bitmap correctedBitmap, final String jenisPemilihan, final boolean z2, final boolean z3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(correctedBitmap, "$correctedBitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.createEmptyFormC1Tabulation$lambda$6$lambda$5(DefaultFormC1Repository.this, idImage, z, correctedBitmap, jenisPemilihan, z2, z3);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:218:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0175 A[Catch: Exception -> 0x0295, TryCatch #0 {Exception -> 0x0295, blocks: (B:200:0x001f, B:257:0x00de, B:259:0x00ec, B:319:0x0180, B:321:0x0195, B:376:0x0214, B:378:0x022b, B:379:0x024b, B:381:0x0251, B:382:0x026c, B:322:0x01a0, B:324:0x01a6, B:261:0x00f7, B:263:0x00fd, B:313:0x0168, B:316:0x0175, B:204:0x0067, B:206:0x006d, B:256:0x00d4), top: B:390:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0180 A[Catch: Exception -> 0x0295, TryCatch #0 {Exception -> 0x0295, blocks: (B:200:0x001f, B:257:0x00de, B:259:0x00ec, B:319:0x0180, B:321:0x0195, B:376:0x0214, B:378:0x022b, B:379:0x024b, B:381:0x0251, B:382:0x026c, B:322:0x01a0, B:324:0x01a6, B:261:0x00f7, B:263:0x00fd, B:313:0x0168, B:316:0x0175, B:204:0x0067, B:206:0x006d, B:256:0x00d4), top: B:390:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:336:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x0251 A[Catch: Exception -> 0x0295, LOOP:0: B:379:0x024b->B:381:0x0251, LOOP_END, TryCatch #0 {Exception -> 0x0295, blocks: (B:200:0x001f, B:257:0x00de, B:259:0x00ec, B:319:0x0180, B:321:0x0195, B:376:0x0214, B:378:0x022b, B:379:0x024b, B:381:0x0251, B:382:0x026c, B:322:0x01a0, B:324:0x01a6, B:261:0x00f7, B:263:0x00fd, B:313:0x0168, B:316:0x0175, B:204:0x0067, B:206:0x006d, B:256:0x00d4), top: B:390:0x001f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void createEmptyFormC1Tabulation$lambda$6$lambda$5(org.informatika.sirekap.repository.DefaultFormC1Repository r20, java.lang.String r21, boolean r22, android.graphics.Bitmap r23, java.lang.String r24, boolean r25, boolean r26) {
        /*
            Method dump skipped, instructions count: 686
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultFormC1Repository.createEmptyFormC1Tabulation$lambda$6$lambda$5(org.informatika.sirekap.repository.DefaultFormC1Repository, java.lang.String, boolean, android.graphics.Bitmap, java.lang.String, boolean, boolean):void");
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1TabulationPartaiComplete>> getFormC1TabulationPartai(final String idImage, final String jenisPemilihan, final boolean z, final String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        return new NetworkBoundResource<FormC1TabulationPartaiComplete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$getFormC1TabulationPartai$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                DefaultFormC1Repository.this.saveCallResultGetFormC1TabulationPartai(item, idImage);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete) {
                FormC1Error error;
                return (formC1TabulationPartaiComplete != null ? formC1TabulationPartaiComplete.getForm() : null) == null && !(formC1TabulationPartaiComplete != null && (error = formC1TabulationPartaiComplete.getError()) != null && error.getErrorType() == 20) && z;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1TabulationPartaiComplete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultFormC1Repository.this.database;
                return appDatabase.formC1TabulationDao().getTabulationPartaiByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<FormC1ImageApiResponse>> createCall() {
                boolean isUserMustWait;
                EncryptedSharedPreferences encryptedSharedPreferences;
                SirekapApiInterface sirekapApiInterface;
                isUserMustWait = DefaultFormC1Repository.this.getIsUserMustWait(idImage);
                if (!isUserMustWait) {
                    encryptedSharedPreferences = DefaultFormC1Repository.this.encryptedSharedPreferences;
                    String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
                    int extractIdImageReal = ElectionUtil.extractIdImageReal(idImage);
                    String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTpsOverride);
                    sirekapApiInterface = DefaultFormC1Repository.this.api;
                    return sirekapApiInterface.getFormCImage("https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/formc-image/get", extractIdImageReal, extractKodeTpsReal);
                }
                return new MutableLiveData(new ApiSuccessResponse(new FormC1ImageApiResponse(true, "", null, new FormC1ImageApiResponse.FormCImageApiResponseDetail(CollectionsKt.emptyList(), null)), (String) null));
            }
        }.asLiveData();
    }

    public final boolean getIsUserMustWait(String str) {
        boolean z;
        Long longOrNull;
        String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted("visionWaitUntil25_" + str, null);
        if (stringEncrypted == null || (longOrNull = StringsKt.toLongOrNull(stringEncrypted)) == null || new Date().getTime() >= longOrNull.longValue()) {
            z = false;
        } else {
            Log.wtf(TAG, "USER MUST WAIT: " + longOrNull + "✅");
            z = true;
        }
        String string = this.sharedPreferences.getString(SharedPreferencesModule.SP_KEY_FCM_TOKEN, "none");
        if (string == null) {
            string = "none";
        }
        if (true ^ Intrinsics.areEqual(string, "none")) {
            return z;
        }
        return false;
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public void createEmptyFormC1TabulationPartai(final String idImage, final String jenisPemilihan, final Bitmap correctedBitmap, final boolean z, final boolean z2, final boolean z3) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(correctedBitmap, "correctedBitmap");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda25
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.createEmptyFormC1TabulationPartai$lambda$9(DefaultFormC1Repository.this, idImage, z3, correctedBitmap, jenisPemilihan, z, z2);
            }
        });
    }

    public static final void createEmptyFormC1TabulationPartai$lambda$9(DefaultFormC1Repository this$0, final String idImage, final boolean z, final Bitmap correctedBitmap, final String jenisPemilihan, final boolean z2, final boolean z3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(correctedBitmap, "$correctedBitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.createEmptyFormC1TabulationPartai$lambda$9$lambda$8(DefaultFormC1Repository.this, idImage, z, correctedBitmap, jenisPemilihan, z2, z3);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x0076, code lost:
        if (r5.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00be, code lost:
        if (r5.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) != false) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void createEmptyFormC1TabulationPartai$lambda$9$lambda$8(org.informatika.sirekap.repository.DefaultFormC1Repository r17, java.lang.String r18, boolean r19, android.graphics.Bitmap r20, java.lang.String r21, boolean r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 452
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultFormC1Repository.createEmptyFormC1TabulationPartai$lambda$9$lambda$8(org.informatika.sirekap.repository.DefaultFormC1Repository, java.lang.String, boolean, android.graphics.Bitmap, java.lang.String, boolean, boolean):void");
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1AdministrationHal2Complete>> getFormC1AdministrationHal2(final String idImage, final String jenisPemilihan, final boolean z, final String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        return new NetworkBoundResource<FormC1AdministrationHal2Complete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$getFormC1AdministrationHal2$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                DefaultFormC1Repository.this.saveCallResultGetFormC1AdministrationHal2(item, idImage);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
                FormC1Error error;
                return (formC1AdministrationHal2Complete != null ? formC1AdministrationHal2Complete.getForm() : null) == null && !(formC1AdministrationHal2Complete != null && (error = formC1AdministrationHal2Complete.getError()) != null && error.getErrorType() == 20) && z;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1AdministrationHal2Complete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultFormC1Repository.this.database;
                return appDatabase.formC1AdministrationHal2Dao().getByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<FormC1ImageApiResponse>> createCall() {
                boolean isUserMustWait;
                EncryptedSharedPreferences encryptedSharedPreferences;
                SirekapApiInterface sirekapApiInterface;
                isUserMustWait = DefaultFormC1Repository.this.getIsUserMustWait(idImage);
                if (!isUserMustWait) {
                    encryptedSharedPreferences = DefaultFormC1Repository.this.encryptedSharedPreferences;
                    String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
                    int extractIdImageReal = ElectionUtil.extractIdImageReal(idImage);
                    String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTpsOverride);
                    sirekapApiInterface = DefaultFormC1Repository.this.api;
                    return sirekapApiInterface.getFormCImage("https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/formc-image/get", extractIdImageReal, extractKodeTpsReal);
                }
                return new MutableLiveData(new ApiSuccessResponse(new FormC1ImageApiResponse(true, "", null, new FormC1ImageApiResponse.FormCImageApiResponseDetail(CollectionsKt.emptyList(), null)), (String) null));
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public void createEmptyFormC1AdministrationHal2(final String idImage, final String jenisPemilihan, final Bitmap correctedBitmap, final boolean z, final boolean z2, final boolean z3) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(correctedBitmap, "correctedBitmap");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.createEmptyFormC1AdministrationHal2$lambda$11(DefaultFormC1Repository.this, idImage, z3, correctedBitmap, jenisPemilihan, z, z2);
            }
        });
    }

    public static final void createEmptyFormC1AdministrationHal2$lambda$11(DefaultFormC1Repository this$0, final String idImage, final boolean z, final Bitmap correctedBitmap, final String jenisPemilihan, final boolean z2, final boolean z3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(correctedBitmap, "$correctedBitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda22
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.createEmptyFormC1AdministrationHal2$lambda$11$lambda$10(DefaultFormC1Repository.this, idImage, z, correctedBitmap, jenisPemilihan, z2, z3);
            }
        });
    }

    public static final void createEmptyFormC1AdministrationHal2$lambda$11$lambda$10(DefaultFormC1Repository this$0, String idImage, boolean z, Bitmap correctedBitmap, String jenisPemilihan, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(correctedBitmap, "$correctedBitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "$jenisPemilihan");
        try {
            ElectionPageWithRelation byIdImage = this$0.database.electionPageDao().getByIdImage(idImage);
            List<Integer> predict = !z ? new Vision(this$0.context).predict(correctedBitmap, FormConfig.FORM_ADMIN2, jenisPemilihan, z2, z3, 0, 0) : null;
            this$0.database.formC1ErrorDao().deleteAll(idImage, 3);
            FormC1AdministrationHal2 createEmpty = FormC1AdministrationHal2.Companion.createEmpty(idImage, byIdImage.getElection().isLn(), byIdImage.getElection().isLnPos(), predict);
            this$0.database.formC1KesesuaianAdministrationHal2Dao().insertAll(CollectionsKt.listOf(FormC1KesesuaianAdministrationHal2.Companion.createFilled(idImage)));
            this$0.database.formC1AdministrationHal2Dao().insertAll(CollectionsKt.listOf(createEmpty));
            this$0.database.electionPageDao().markAsContinueVerify(byIdImage.getElectionPage().getId());
            this$0.database.electionPageDao().removeVisionError(idImage);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = "";
            }
            this$0.insertError(idImage, 3, 20, message);
            this$0.database.electionPageDao().addVisionError(idImage);
        }
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1AdministrationHal2PpwpComplete>> getFormC1AdministrationHal2Ppwp(final String idImage, final String jenisPemilihan, final boolean z, final String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        return new NetworkBoundResource<FormC1AdministrationHal2PpwpComplete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$getFormC1AdministrationHal2Ppwp$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                DefaultFormC1Repository.this.saveCallResultGetFormC1AdministrationHal2Ppwp(item, idImage);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete) {
                return z;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1AdministrationHal2PpwpComplete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultFormC1Repository.this.database;
                return appDatabase.formC1AdministrationHal2Dao().getPpwpByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<FormC1ImageApiResponse>> createCall() {
                EncryptedSharedPreferences encryptedSharedPreferences;
                SirekapApiInterface sirekapApiInterface;
                encryptedSharedPreferences = DefaultFormC1Repository.this.encryptedSharedPreferences;
                String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
                int extractIdImageReal = ElectionUtil.extractIdImageReal(idImage);
                String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTpsOverride);
                sirekapApiInterface = DefaultFormC1Repository.this.api;
                return sirekapApiInterface.getFormCImage("https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/formc-image/get", extractIdImageReal, extractKodeTpsReal);
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public void createEmptyFormC1AdministrationHal2Ppwp(final String idImage, String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.createEmptyFormC1AdministrationHal2Ppwp$lambda$14(DefaultFormC1Repository.this, idImage);
            }
        });
    }

    public static final void createEmptyFormC1AdministrationHal2Ppwp$lambda$14(DefaultFormC1Repository this$0, final String idImage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.createEmptyFormC1AdministrationHal2Ppwp$lambda$14$lambda$13(DefaultFormC1Repository.this, idImage);
            }
        });
    }

    public static final void createEmptyFormC1AdministrationHal2Ppwp$lambda$14$lambda$13(DefaultFormC1Repository this$0, String idImage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        ElectionPageWithRelation byIdImage = this$0.database.electionPageDao().getByIdImage(idImage);
        this$0.database.formC1ErrorDao().deleteAll(idImage, 3);
        FormC1AdministrationHal2 createEmpty$default = FormC1AdministrationHal2.Companion.createEmpty$default(FormC1AdministrationHal2.Companion, idImage, byIdImage.getElection().isLn(), byIdImage.getElection().isLnPos(), null, 8, null);
        List<FormC1TabulationCandidateVote> createEmpty$default2 = FormC1TabulationCandidateVote.Companion.createEmpty$default(FormC1TabulationCandidateVote.Companion, 2, idImage, 3, null, 8, null);
        FormC1KesesuaianTabulationCandidateVoteDao formC1KesesuaianTabulationCandidateVoteDao = this$0.database.formC1KesesuaianTabulationCandidateVoteDao();
        List<FormC1TabulationCandidateVote> list = createEmpty$default2;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (FormC1TabulationCandidateVote formC1TabulationCandidateVote : list) {
            arrayList.add(new FormC1KesesuaianTabulationCandidateVote(formC1TabulationCandidateVote.getIdImage(), formC1TabulationCandidateVote.getIndex(), true));
        }
        formC1KesesuaianTabulationCandidateVoteDao.insertAll(arrayList);
        this$0.database.formC1TabulationCandidateVoteDao().insertAll(createEmpty$default2);
        this$0.database.formC1KesesuaianAdministrationHal2Dao().insertAll(CollectionsKt.listOf(FormC1KesesuaianAdministrationHal2.Companion.createFilled(idImage)));
        this$0.database.formC1AdministrationHal2Dao().insertAll(CollectionsKt.listOf(createEmpty$default));
        this$0.database.electionPageDao().markAsContinueVerify(byIdImage.getElectionPage().getId());
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<List<FormC1TabulationComplete>>> getListFormC1Tabulation(final List<String> idImages) {
        Intrinsics.checkNotNullParameter(idImages, "idImages");
        return new NetworkBoundResource<List<? extends FormC1TabulationComplete>, EmptyApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$getListFormC1Tabulation$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(EmptyApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* renamed from: shouldFetch  reason: avoid collision after fix types in other method */
            protected boolean shouldFetch2(List<FormC1TabulationComplete> list) {
                return false;
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public /* bridge */ /* synthetic */ boolean shouldFetch(List<? extends FormC1TabulationComplete> list) {
                return shouldFetch2((List<FormC1TabulationComplete>) list);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<List<? extends FormC1TabulationComplete>> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultFormC1Repository.this.database;
                return appDatabase.formC1TabulationDao().getListByIdImages(idImages);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<List<FormC1TabulationPartaiComplete>>> getListFormC1TabulationPartai(final List<String> idImages) {
        Intrinsics.checkNotNullParameter(idImages, "idImages");
        return new NetworkBoundResource<List<? extends FormC1TabulationPartaiComplete>, EmptyApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$getListFormC1TabulationPartai$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(EmptyApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* renamed from: shouldFetch  reason: avoid collision after fix types in other method */
            protected boolean shouldFetch2(List<FormC1TabulationPartaiComplete> list) {
                return false;
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public /* bridge */ /* synthetic */ boolean shouldFetch(List<? extends FormC1TabulationPartaiComplete> list) {
                return shouldFetch2((List<FormC1TabulationPartaiComplete>) list);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<List<? extends FormC1TabulationPartaiComplete>> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultFormC1Repository.this.database;
                return appDatabase.formC1TabulationDao().getListTabulationPartaiByIdImages(idImages);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    public final void saveCallResultGetFormC1Tabulation(FormC1ImageApiResponse item, final String idImage) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        final FormC1ImageApiResponse.FormCImageApiResponseDetail data = item.getData();
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveCallResultGetFormC1Tabulation$lambda$17$lambda$16(FormC1ImageApiResponse.FormCImageApiResponseDetail.this, this, idImage);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0067 A[Catch: Exception -> 0x0173, TryCatch #0 {Exception -> 0x0173, blocks: (B:99:0x0022, B:102:0x0053, B:104:0x005b, B:110:0x0067, B:111:0x0070, B:170:0x0117, B:171:0x013a, B:173:0x0140, B:174:0x015b, B:114:0x008b, B:116:0x0093, B:169:0x010d), top: B:183:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0070 A[Catch: Exception -> 0x0173, TryCatch #0 {Exception -> 0x0173, blocks: (B:99:0x0022, B:102:0x0053, B:104:0x005b, B:110:0x0067, B:111:0x0070, B:170:0x0117, B:171:0x013a, B:173:0x0140, B:174:0x015b, B:114:0x008b, B:116:0x0093, B:169:0x010d), top: B:183:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x010b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void saveCallResultGetFormC1Tabulation$lambda$17$lambda$16(org.informatika.sirekap.api.response.FormC1ImageApiResponse.FormCImageApiResponseDetail r10, org.informatika.sirekap.repository.DefaultFormC1Repository r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultFormC1Repository.saveCallResultGetFormC1Tabulation$lambda$17$lambda$16(org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetail, org.informatika.sirekap.repository.DefaultFormC1Repository, java.lang.String):void");
    }

    public final void saveCallResultGetFormC1TabulationPartai(FormC1ImageApiResponse item, final String idImage) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        final FormC1ImageApiResponse.FormCImageApiResponseDetail data = item.getData();
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveCallResultGetFormC1TabulationPartai$lambda$20$lambda$19(FormC1ImageApiResponse.FormCImageApiResponseDetail.this, this, idImage);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x00b8, code lost:
        if (r7.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) != false) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0067 A[Catch: Exception -> 0x014f, TryCatch #0 {Exception -> 0x014f, blocks: (B:56:0x0022, B:59:0x0053, B:61:0x005b, B:67:0x0067, B:68:0x0070, B:84:0x00d8, B:85:0x00f6, B:87:0x00fc, B:88:0x0117, B:75:0x00a0, B:83:0x00ba, B:78:0x00a9, B:81:0x00b2), top: B:97:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0070 A[Catch: Exception -> 0x014f, TryCatch #0 {Exception -> 0x014f, blocks: (B:56:0x0022, B:59:0x0053, B:61:0x005b, B:67:0x0067, B:68:0x0070, B:84:0x00d8, B:85:0x00f6, B:87:0x00fc, B:88:0x0117, B:75:0x00a0, B:83:0x00ba, B:78:0x00a9, B:81:0x00b2), top: B:97:0x0022 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void saveCallResultGetFormC1TabulationPartai$lambda$20$lambda$19(org.informatika.sirekap.api.response.FormC1ImageApiResponse.FormCImageApiResponseDetail r11, org.informatika.sirekap.repository.DefaultFormC1Repository r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultFormC1Repository.saveCallResultGetFormC1TabulationPartai$lambda$20$lambda$19(org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetail, org.informatika.sirekap.repository.DefaultFormC1Repository, java.lang.String):void");
    }

    public final void saveCallResultGetFormC1AdministrationHal2(FormC1ImageApiResponse item, final String idImage) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        final FormC1ImageApiResponse.FormCImageApiResponseDetail data = item.getData();
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveCallResultGetFormC1AdministrationHal2$lambda$22$lambda$21(FormC1ImageApiResponse.FormCImageApiResponseDetail.this, this, idImage);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0063, code lost:
        r7.insertError(r8, 3, 20, r6.getError());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void saveCallResultGetFormC1AdministrationHal2$lambda$22$lambda$21(org.informatika.sirekap.api.response.FormC1ImageApiResponse.FormCImageApiResponseDetail r6, org.informatika.sirekap.repository.DefaultFormC1Repository r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "$this_apply"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "$idImage"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.util.List r0 = r6.getImagePayloads()
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r1 = 1
            r0 = r0 ^ r1
            r2 = 3
            if (r0 == 0) goto Ld2
            r0 = 20
            com.google.gson.Gson r3 = new com.google.gson.Gson     // Catch: java.lang.Exception -> Lb9
            r3.<init>()     // Catch: java.lang.Exception -> Lb9
            java.util.List r6 = r6.getImagePayloads()     // Catch: java.lang.Exception -> Lb9
            r4 = 0
            java.lang.Object r6 = r6.get(r4)     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetailPayload r6 = (org.informatika.sirekap.api.response.FormC1ImageApiResponse.FormCImageApiResponseDetailPayload) r6     // Catch: java.lang.Exception -> Lb9
            java.lang.String r6 = r6.getPayload()     // Catch: java.lang.Exception -> Lb9
            java.lang.Class<org.informatika.sirekap.model.FormC1AdministrationHal2Payload> r5 = org.informatika.sirekap.model.FormC1AdministrationHal2Payload.class
            java.lang.Object r6 = r3.fromJson(r6, r5)     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.model.FormC1AdministrationHal2Payload r6 = (org.informatika.sirekap.model.FormC1AdministrationHal2Payload) r6     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.db.AppDatabase r3 = r7.database     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.db.dao.ElectionPageDao r3 = r3.electionPageDao()     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.model.ElectionPageWithRelation r3 = r3.getByIdImage(r8)     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.model.ElectionPage r5 = r3.getElectionPage()     // Catch: java.lang.Exception -> Lb9
            boolean r5 = r5.isContinueVerify()     // Catch: java.lang.Exception -> Lb9
            if (r5 == 0) goto L51
            return
        L51:
            java.lang.String r5 = r6.getError()     // Catch: java.lang.Exception -> Lb9
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch: java.lang.Exception -> Lb9
            if (r5 == 0) goto L61
            boolean r5 = kotlin.text.StringsKt.isBlank(r5)     // Catch: java.lang.Exception -> Lb9
            if (r5 == 0) goto L60
            goto L61
        L60:
            r1 = r4
        L61:
            if (r1 != 0) goto L6b
            java.lang.String r6 = r6.getError()     // Catch: java.lang.Exception -> Lb9
            r7.insertError(r8, r2, r0, r6)     // Catch: java.lang.Exception -> Lb9
            goto Ld9
        L6b:
            org.informatika.sirekap.db.AppDatabase r1 = r7.database     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.db.dao.FormC1ErrorDao r1 = r1.formC1ErrorDao()     // Catch: java.lang.Exception -> Lb9
            r1.deleteAll(r8, r2)     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.model.FormC1AdministrationHal2$Companion r1 = org.informatika.sirekap.model.FormC1AdministrationHal2.Companion     // Catch: java.lang.Exception -> Lb9
            java.lang.String r4 = "payload"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r4)     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.model.Election r4 = r3.getElection()     // Catch: java.lang.Exception -> Lb9
            boolean r4 = r4.isLn()     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.model.Election r3 = r3.getElection()     // Catch: java.lang.Exception -> Lb9
            boolean r3 = r3.isLnPos()     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.model.FormC1AdministrationHal2 r6 = r1.createFromFormC1AdministrationHal2Payload(r6, r8, r4, r3)     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.db.AppDatabase r1 = r7.database     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.db.dao.FormC1KesesuaianAdministrationHal2Dao r1 = r1.formC1KesesuaianAdministrationHal2Dao()     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2$Companion r3 = org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2.Companion     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2 r3 = r3.createFilled(r8)     // Catch: java.lang.Exception -> Lb9
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)     // Catch: java.lang.Exception -> Lb9
            r1.insertAll(r3)     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.db.AppDatabase r1 = r7.database     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.db.dao.FormC1AdministrationHal2Dao r1 = r1.formC1AdministrationHal2Dao()     // Catch: java.lang.Exception -> Lb9
            java.util.List r6 = kotlin.collections.CollectionsKt.listOf(r6)     // Catch: java.lang.Exception -> Lb9
            r1.insertAll(r6)     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.db.AppDatabase r6 = r7.database     // Catch: java.lang.Exception -> Lb9
            org.informatika.sirekap.db.dao.ElectionPageDao r6 = r6.electionPageDao()     // Catch: java.lang.Exception -> Lb9
            r6.removeVisionError(r8)     // Catch: java.lang.Exception -> Lb9
            goto Ld9
        Lb9:
            r6 = move-exception
            java.lang.String r1 = r6.getMessage()
            r3 = r6
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.String r4 = "DefaultFormC1Repo"
            android.util.Log.e(r4, r1, r3)
            java.lang.String r6 = r6.getMessage()
            if (r6 != 0) goto Lce
            java.lang.String r6 = "Error parsing JSON"
        Lce:
            r7.insertError(r8, r2, r0, r6)
            goto Ld9
        Ld2:
            r6 = 10
            java.lang.String r0 = "Gambar yang Anda kirim belum selesai diproses. Harap tunggu beberapa saat."
            r7.insertError(r8, r2, r6, r0)
        Ld9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.DefaultFormC1Repository.saveCallResultGetFormC1AdministrationHal2$lambda$22$lambda$21(org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetail, org.informatika.sirekap.repository.DefaultFormC1Repository, java.lang.String):void");
    }

    public final void saveCallResultGetFormC1AdministrationHal2Ppwp(FormC1ImageApiResponse item, final String idImage) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        final FormC1ImageApiResponse.FormCImageApiResponseDetail data = item.getData();
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveCallResultGetFormC1AdministrationHal2Ppwp$lambda$25$lambda$24(FormC1ImageApiResponse.FormCImageApiResponseDetail.this, this, idImage);
            }
        });
    }

    public static final void saveCallResultGetFormC1AdministrationHal2Ppwp$lambda$25$lambda$24(FormC1ImageApiResponse.FormCImageApiResponseDetail this_apply, DefaultFormC1Repository this$0, String idImage) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        if (!this_apply.getImagePayloads().isEmpty()) {
            try {
                boolean z = false;
                FormC1AdministrationHal2Payload payload = (FormC1AdministrationHal2Payload) new Gson().fromJson(this_apply.getImagePayloads().get(0).getPayload(), (Class<Object>) FormC1AdministrationHal2Payload.class);
                ElectionPageWithRelation byIdImage = this$0.database.electionPageDao().getByIdImage(idImage);
                if (byIdImage.getElectionPage().isContinueVerify()) {
                    return;
                }
                String error = payload.getError();
                if (!((error == null || StringsKt.isBlank(error)) ? true : true)) {
                    this$0.insertError(idImage, 3, 20, payload.getError());
                    return;
                }
                this$0.database.formC1ErrorDao().deleteAll(idImage, 3);
                FormC1AdministrationHal2.Companion companion = FormC1AdministrationHal2.Companion;
                Intrinsics.checkNotNullExpressionValue(payload, "payload");
                FormC1AdministrationHal2 createFromFormC1AdministrationHal2Payload = companion.createFromFormC1AdministrationHal2Payload(payload, idImage, byIdImage.getElection().isLn(), byIdImage.getElection().isLnPos());
                List<FormC1TabulationCandidateVote> createFromFormC1AdministrationHal2$default = FormC1TabulationCandidateVote.Companion.createFromFormC1AdministrationHal2$default(FormC1TabulationCandidateVote.Companion, payload, idImage, 0, 4, null);
                FormC1KesesuaianTabulationCandidateVoteDao formC1KesesuaianTabulationCandidateVoteDao = this$0.database.formC1KesesuaianTabulationCandidateVoteDao();
                List<FormC1TabulationCandidateVote> list = createFromFormC1AdministrationHal2$default;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (FormC1TabulationCandidateVote formC1TabulationCandidateVote : list) {
                    arrayList.add(new FormC1KesesuaianTabulationCandidateVote(formC1TabulationCandidateVote.getIdImage(), formC1TabulationCandidateVote.getIndex(), true));
                }
                formC1KesesuaianTabulationCandidateVoteDao.insertAll(arrayList);
                this$0.database.formC1TabulationCandidateVoteDao().insertAll(createFromFormC1AdministrationHal2$default);
                this$0.database.formC1KesesuaianAdministrationHal2Dao().insertAll(CollectionsKt.listOf(FormC1KesesuaianAdministrationHal2.Companion.createFilled(idImage)));
                this$0.database.formC1AdministrationHal2Dao().insertAll(CollectionsKt.listOf(createFromFormC1AdministrationHal2Payload));
                this$0.database.electionPageDao().removeVisionError(idImage);
                return;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                String message = e.getMessage();
                if (message == null) {
                    message = "Error parsing JSON";
                }
                this$0.insertError(idImage, 3, 20, message);
                return;
            }
        }
        this$0.insertError(idImage, 3, 10, "Gambar yang Anda kirim belum selesai diproses. Harap tunggu beberapa saat.");
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1AdministrationComplete>> verifyFormC1(final String jenisPemilihan, final String deviceId, int i, final String electionPageId, final String idImage, final boolean z, final List<Boolean> isSesuaiPerItem, final String komentar, final List<Integer> koreksiPerItem, final String kodeTps) {
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
        Intrinsics.checkNotNullParameter(komentar, "komentar");
        Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        return new NetworkBoundResource<FormC1AdministrationComplete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$verifyFormC1$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1AdministrationComplete formC1AdministrationComplete) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                DefaultFormC1Repository.this.saveCallResultVerifyFormC1(item, idImage, electionPageId);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1AdministrationComplete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultFormC1Repository.this.database;
                return appDatabase.formC1AdministrationDao().getByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<FormC1ImageApiResponse>> createCall() {
                EncryptedSharedPreferences encryptedSharedPreferences;
                SirekapApiInterface sirekapApiInterface;
                Integer num;
                int extractIdImageReal = ElectionUtil.extractIdImageReal(idImage);
                boolean z2 = extractIdImageReal < 0;
                ArrayList arrayList = new ArrayList();
                List<Integer> list = koreksiPerItem;
                int i2 = 0;
                for (Object obj : isSesuaiPerItem) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (Intrinsics.areEqual((Object) ((Boolean) obj), (Object) false) && (num = list.get(i2)) != null) {
                        arrayList.add(Integer.valueOf(num.intValue()));
                    }
                    i2 = i3;
                }
                if (!z2) {
                    encryptedSharedPreferences = DefaultFormC1Repository.this.encryptedSharedPreferences;
                    String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
                    String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTps);
                    sirekapApiInterface = DefaultFormC1Repository.this.api;
                    return sirekapApiInterface.updateFormCImageKesesuaian("https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/formc-image/kesesuaian", deviceId, extractIdImageReal, z, isSesuaiPerItem, komentar, arrayList, extractKodeTpsReal, BuildConfig.USER_ID);
                }
                return new MutableLiveData(new ApiSuccessResponse(new FormC1ImageApiResponse(true, "", null, new FormC1ImageApiResponse.FormCImageApiResponseDetail(CollectionsKt.emptyList(), CollectionsKt.listOf(new FormC1ImageApiResponse.FormCImageApiResponseDetailKesesuaian(z, komentar, isSesuaiPerItem, arrayList)))), (String) null));
            }
        }.asLiveData();
    }

    public final void saveCallResultVerifyFormC1(FormC1ImageApiResponse item, final String idImage, final String electionPageId) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        final boolean z = ElectionUtil.extractIdImageReal(idImage) < 0;
        final FormC1ImageApiResponse.FormCImageApiResponseDetail data = item.getData();
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveCallResultVerifyFormC1$lambda$27$lambda$26(FormC1ImageApiResponse.FormCImageApiResponseDetail.this, idImage, this, electionPageId, z);
            }
        });
        CrashlyticsUtil.Companion.setElectionPageStatus(electionPageId, 3);
    }

    public static final void saveCallResultVerifyFormC1$lambda$27$lambda$26(FormC1ImageApiResponse.FormCImageApiResponseDetail this_apply, String idImage, DefaultFormC1Repository this$0, String electionPageId, boolean z) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        List<FormC1ImageApiResponse.FormCImageApiResponseDetailKesesuaian> imageKesesuaians = this_apply.getImageKesesuaians();
        if (imageKesesuaians == null || imageKesesuaians.isEmpty()) {
            return;
        }
        this$0.database.formC1KesesuaianDao().insertAll(CollectionsKt.listOf(new FormC1Kesesuaian(idImage, this_apply.getImageKesesuaians().get(0).isSesuai(), this_apply.getImageKesesuaians().get(0).getKomentar())));
        this$0.database.electionPageStageDao().updateStatus(1, electionPageId, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_PHOTO);
        if (z) {
            this$0.database.electionPageStageDao().updateStatusIsOffline(1, electionPageId, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND);
            this$0.database.electionPageStageDao().updateStatusIsOffline(1, electionPageId, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_VERIFY);
        } else {
            this$0.database.electionPageStageDao().updateStatus(1, electionPageId, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND);
            this$0.database.electionPageStageDao().updateStatus(1, electionPageId, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_VERIFY);
        }
        this$0.database.electionPageDao().finishVerify(electionPageId);
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public void saveFormC1KesesuaianAdministration(String idImage, final FormC1KesesuaianAdministration formC1KesesuaianAdministration) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(formC1KesesuaianAdministration, "formC1KesesuaianAdministration");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveFormC1KesesuaianAdministration$lambda$29(DefaultFormC1Repository.this, formC1KesesuaianAdministration);
            }
        });
    }

    public static final void saveFormC1KesesuaianAdministration$lambda$29(DefaultFormC1Repository this$0, final FormC1KesesuaianAdministration formC1KesesuaianAdministration) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(formC1KesesuaianAdministration, "$formC1KesesuaianAdministration");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveFormC1KesesuaianAdministration$lambda$29$lambda$28(DefaultFormC1Repository.this, formC1KesesuaianAdministration);
            }
        });
    }

    public static final void saveFormC1KesesuaianAdministration$lambda$29$lambda$28(DefaultFormC1Repository this$0, FormC1KesesuaianAdministration formC1KesesuaianAdministration) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(formC1KesesuaianAdministration, "$formC1KesesuaianAdministration");
        this$0.database.formC1KesesuaianAdministrasiDao().insertAllReplace(CollectionsKt.listOf(formC1KesesuaianAdministration));
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public void saveFormC1KesesuaianAdministrationHal2(final String idImage, final List<Boolean> isSesuaiPerItem, final List<Integer> koreksiPerItem) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
        Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda21
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveFormC1KesesuaianAdministrationHal2$lambda$31(DefaultFormC1Repository.this, idImage, isSesuaiPerItem, koreksiPerItem);
            }
        });
    }

    public static final void saveFormC1KesesuaianAdministrationHal2$lambda$31(DefaultFormC1Repository this$0, final String idImage, final List isSesuaiPerItem, final List koreksiPerItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(isSesuaiPerItem, "$isSesuaiPerItem");
        Intrinsics.checkNotNullParameter(koreksiPerItem, "$koreksiPerItem");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveFormC1KesesuaianAdministrationHal2$lambda$31$lambda$30(DefaultFormC1Repository.this, idImage, isSesuaiPerItem, koreksiPerItem);
            }
        });
    }

    public static final void saveFormC1KesesuaianAdministrationHal2$lambda$31$lambda$30(DefaultFormC1Repository this$0, String idImage, List isSesuaiPerItem, List koreksiPerItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(isSesuaiPerItem, "$isSesuaiPerItem");
        Intrinsics.checkNotNullParameter(koreksiPerItem, "$koreksiPerItem");
        this$0.database.formC1KesesuaianAdministrationHal2Dao().insertAllReplace(CollectionsKt.listOf(FormC1KesesuaianAdministrationHal2.Companion.createFromIsSesuaiPerItem(idImage, isSesuaiPerItem, koreksiPerItem)));
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public void saveFormC1KesesuaianAdministrationHal2New(String idImage, final FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(formC1KesesuaianAdministrationHal2, "formC1KesesuaianAdministrationHal2");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveFormC1KesesuaianAdministrationHal2New$lambda$33(DefaultFormC1Repository.this, formC1KesesuaianAdministrationHal2);
            }
        });
    }

    public static final void saveFormC1KesesuaianAdministrationHal2New$lambda$33(DefaultFormC1Repository this$0, final FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(formC1KesesuaianAdministrationHal2, "$formC1KesesuaianAdministrationHal2");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveFormC1KesesuaianAdministrationHal2New$lambda$33$lambda$32(DefaultFormC1Repository.this, formC1KesesuaianAdministrationHal2);
            }
        });
    }

    public static final void saveFormC1KesesuaianAdministrationHal2New$lambda$33$lambda$32(DefaultFormC1Repository this$0, FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(formC1KesesuaianAdministrationHal2, "$formC1KesesuaianAdministrationHal2");
        this$0.database.formC1KesesuaianAdministrationHal2Dao().insertAllReplace(CollectionsKt.listOf(formC1KesesuaianAdministrationHal2));
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public void saveFormC1KesesuaianTabulationPartai(final String idImage, final List<Boolean> isSesuaiPerItem, final List<Integer> koreksiPerItem) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
        Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveFormC1KesesuaianTabulationPartai$lambda$35(DefaultFormC1Repository.this, idImage, isSesuaiPerItem, koreksiPerItem);
            }
        });
    }

    public static final void saveFormC1KesesuaianTabulationPartai$lambda$35(DefaultFormC1Repository this$0, final String idImage, final List isSesuaiPerItem, final List koreksiPerItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(isSesuaiPerItem, "$isSesuaiPerItem");
        Intrinsics.checkNotNullParameter(koreksiPerItem, "$koreksiPerItem");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveFormC1KesesuaianTabulationPartai$lambda$35$lambda$34(DefaultFormC1Repository.this, idImage, isSesuaiPerItem, koreksiPerItem);
            }
        });
    }

    public static final void saveFormC1KesesuaianTabulationPartai$lambda$35$lambda$34(DefaultFormC1Repository this$0, String idImage, List isSesuaiPerItem, List koreksiPerItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        Intrinsics.checkNotNullParameter(isSesuaiPerItem, "$isSesuaiPerItem");
        Intrinsics.checkNotNullParameter(koreksiPerItem, "$koreksiPerItem");
        this$0.database.formC1KesesuaianTabulationPartaiDao().insertAllReplace(CollectionsKt.listOf(FormC1KesesuaianTabulationPartai.Companion.createFromIsSesuaiPerItem(idImage, isSesuaiPerItem, koreksiPerItem)));
    }

    @Override // org.informatika.sirekap.repository.FormC1Repository
    public void saveFormC1KesesuaianTabulationCandidateVote(final String idImage, final int i, final Boolean bool, final Integer num) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveFormC1KesesuaianTabulationCandidateVote$lambda$37(DefaultFormC1Repository.this, idImage, i, bool, num);
            }
        });
    }

    public static final void saveFormC1KesesuaianTabulationCandidateVote$lambda$37(DefaultFormC1Repository this$0, final String idImage, final int i, final Boolean bool, final Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultFormC1Repository$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DefaultFormC1Repository.saveFormC1KesesuaianTabulationCandidateVote$lambda$37$lambda$36(DefaultFormC1Repository.this, idImage, i, bool, num);
            }
        });
    }

    public static final void saveFormC1KesesuaianTabulationCandidateVote$lambda$37$lambda$36(DefaultFormC1Repository this$0, String idImage, int i, Boolean bool, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(idImage, "$idImage");
        this$0.database.formC1KesesuaianTabulationCandidateVoteDao().insertAllReplace(CollectionsKt.listOf(new FormC1KesesuaianTabulationCandidateVote(idImage, i, bool, num)));
    }

    private final void insertError(String str, int i, int i2, String str2) {
        this.database.formC1ErrorDao().insertAll(CollectionsKt.listOf(new FormC1Error(str, i, i2, str2)));
        if (i2 != 10) {
            this.database.electionPageDao().addVisionError(str);
        }
    }

    /* compiled from: FormC1Repository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultFormC1Repository$Companion;", "", "()V", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
