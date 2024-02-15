package org.informatika.sirekap.repository.fake;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.informatika.sirekap.R;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.response.FormC1ImageApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete;
import org.informatika.sirekap.model.FormC1TabulationComplete;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
import org.informatika.sirekap.repository.DefaultFormC1Repository;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.NetworkBoundResource;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: FakeFormC1Repository.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\u0018\u0000 ,2\u00020\u0001:\u0001,B9\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ6\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J6\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J6\u0010\u001b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J6\u0010\u001d\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J6\u0010\u001f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020 \u0018\u00010\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0014H\u0016Jv\u0010!\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u00172\u000e\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170(2\u0006\u0010)\u001a\u00020\u00142\u000e\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0(2\u0006\u0010+\u001a\u00020\u0014H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lorg/informatika/sirekap/repository/fake/FakeFormC1Repository;", "Lorg/informatika/sirekap/repository/DefaultFormC1Repository;", "context", "Landroid/content/Context;", "api", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Landroid/content/Context;Lorg/informatika/sirekap/api/SirekapApiInterface;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Landroid/content/SharedPreferences;)V", "getFormC1Administration", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "idImage", "", "jenisPemilihan", "isFetchApi", "", "kodeTpsOverride", "getFormC1AdministrationHal2", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "getFormC1AdministrationHal2Ppwp", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2PpwpComplete;", "getFormC1Tabulation", "Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "getFormC1TabulationPartai", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "verifyFormC1", "deviceId", "formType", "", "electionPageId", "isSesuai", "isSesuaiPerItem", "", "komentar", "koreksiPerItem", "kodeTps", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FakeFormC1Repository extends DefaultFormC1Repository {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "FakeFormC1Repo";
    private final AppExecutors appExecutors;
    private final Context context;
    private final AppDatabase database;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Inject
    public FakeFormC1Repository(@ApplicationContext Context context, SirekapApiInterface api, AppDatabase database, AppExecutors appExecutors, EncryptedSharedPreferences encryptedSharedPreferences, SharedPreferences sharedPreferences) {
        super(context, api, database, appExecutors, encryptedSharedPreferences, sharedPreferences);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        this.context = context;
        this.database = database;
        this.appExecutors = appExecutors;
    }

    @Override // org.informatika.sirekap.repository.DefaultFormC1Repository, org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1AdministrationComplete>> getFormC1Administration(final String idImage, final String jenisPemilihan, boolean z, String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        return new NetworkBoundResource<FormC1AdministrationComplete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.fake.FakeFormC1Repository$getFormC1Administration$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1AdministrationComplete formC1AdministrationComplete) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                FakeFormC1Repository.this.saveCallResultGetFormC1Administration(item, idImage, jenisPemilihan);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1AdministrationComplete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = FakeFormC1Repository.this.database;
                return appDatabase.formC1AdministrationDao().getByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            protected androidx.lifecycle.LiveData<org.informatika.sirekap.api.ApiResponse<org.informatika.sirekap.api.response.FormC1ImageApiResponse>> createCall() {
                /*
                    r7 = this;
                    java.lang.String r0 = r3
                    int r1 = r0.hashCode()
                    switch(r1) {
                        case -992700931: goto L37;
                        case -992700926: goto L2c;
                        case 3436264: goto L21;
                        case 3436278: goto L16;
                        case 3448025: goto Lb;
                        default: goto L9;
                    }
                L9:
                    goto La9
                Lb:
                    java.lang.String r1 = "ppwp"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto La9
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_ppwp_lnpos_hal_1
                    goto L41
                L16:
                    java.lang.String r1 = "pdpr"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto La9
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_pdpr_lnpos_hal_1
                    goto L41
                L21:
                    java.lang.String r1 = "pdpd"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto La9
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_ppwp_hal_1
                    goto L41
                L2c:
                    java.lang.String r1 = "pdprdp"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto La9
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_ppwp_hal_1
                    goto L41
                L37:
                    java.lang.String r1 = "pdprdk"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto La9
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_ppwp_hal_1
                L41:
                    org.informatika.sirekap.repository.fake.FakeFormC1Repository r1 = org.informatika.sirekap.repository.fake.FakeFormC1Repository.this
                    android.content.Context r1 = org.informatika.sirekap.repository.fake.FakeFormC1Repository.access$getContext$p(r1)
                    android.content.res.Resources r1 = r1.getResources()
                    java.io.InputStream r0 = r1.openRawResource(r0)
                    java.lang.String r1 = "context.resources.openRawResource(dummyJson)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
                    java.io.InputStreamReader r2 = new java.io.InputStreamReader
                    r2.<init>(r0, r1)
                    java.io.Reader r2 = (java.io.Reader) r2
                    boolean r0 = r2 instanceof java.io.BufferedReader
                    if (r0 == 0) goto L64
                    java.io.BufferedReader r2 = (java.io.BufferedReader) r2
                    goto L6c
                L64:
                    java.io.BufferedReader r0 = new java.io.BufferedReader
                    r1 = 8192(0x2000, float:1.14794E-41)
                    r0.<init>(r2, r1)
                    r2 = r0
                L6c:
                    java.io.Closeable r2 = (java.io.Closeable) r2
                    r0 = r2
                    java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch: java.lang.Throwable -> La2
                    java.io.Reader r0 = (java.io.Reader) r0     // Catch: java.lang.Throwable -> La2
                    java.lang.String r0 = kotlin.io.TextStreamsKt.readText(r0)     // Catch: java.lang.Throwable -> La2
                    r1 = 0
                    kotlin.io.CloseableKt.closeFinally(r2, r1)
                    androidx.lifecycle.MutableLiveData r2 = new androidx.lifecycle.MutableLiveData
                    org.informatika.sirekap.api.ApiSuccessResponse r3 = new org.informatika.sirekap.api.ApiSuccessResponse
                    org.informatika.sirekap.api.response.FormC1ImageApiResponse r4 = new org.informatika.sirekap.api.response.FormC1ImageApiResponse
                    org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetail r5 = new org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetail
                    org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetailPayload r6 = new org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetailPayload
                    r6.<init>(r0)
                    java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r6)
                    java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
                    r5.<init>(r0, r6)
                    r0 = 1
                    java.lang.String r6 = ""
                    r4.<init>(r0, r6, r1, r5)
                    r3.<init>(r4, r6)
                    r2.<init>(r3)
                    androidx.lifecycle.LiveData r2 = (androidx.lifecycle.LiveData) r2
                    return r2
                La2:
                    r0 = move-exception
                    throw r0     // Catch: java.lang.Throwable -> La4
                La4:
                    r1 = move-exception
                    kotlin.io.CloseableKt.closeFinally(r2, r0)
                    throw r1
                La9:
                    java.lang.Exception r0 = new java.lang.Exception
                    java.lang.String r1 = "Unreachable"
                    r0.<init>(r1)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.fake.FakeFormC1Repository$getFormC1Administration$1.createCall():androidx.lifecycle.LiveData");
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.DefaultFormC1Repository, org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1TabulationComplete>> getFormC1Tabulation(final String idImage, final String jenisPemilihan, boolean z, String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        return new NetworkBoundResource<FormC1TabulationComplete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.fake.FakeFormC1Repository$getFormC1Tabulation$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1TabulationComplete formC1TabulationComplete) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                FakeFormC1Repository.this.saveCallResultGetFormC1Tabulation(item, idImage);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1TabulationComplete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = FakeFormC1Repository.this.database;
                return appDatabase.formC1TabulationDao().getByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<FormC1ImageApiResponse>> createCall() {
                int i;
                Context context;
                String str = jenisPemilihan;
                if (Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                    i = R.raw.dummy_image_payload_ppwp_hal_2;
                } else if (!Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPD)) {
                    throw new Exception("Unreachable");
                } else {
                    i = R.raw.dummy_image_payload_pdpd_hal_2;
                }
                context = FakeFormC1Repository.this.context;
                InputStream openRawResource = context.getResources().openRawResource(i);
                Intrinsics.checkNotNullExpressionValue(openRawResource, "context.resources.openRawResource(dummyJson)");
                InputStreamReader inputStreamReader = new InputStreamReader(openRawResource, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    String readText = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    return new MutableLiveData(new ApiSuccessResponse(new FormC1ImageApiResponse(true, "", null, new FormC1ImageApiResponse.FormCImageApiResponseDetail(CollectionsKt.listOf(new FormC1ImageApiResponse.FormCImageApiResponseDetailPayload(readText)), CollectionsKt.emptyList())), ""));
                } finally {
                }
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.DefaultFormC1Repository, org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1TabulationPartaiComplete>> getFormC1TabulationPartai(final String idImage, final String jenisPemilihan, boolean z, String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        return new NetworkBoundResource<FormC1TabulationPartaiComplete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.fake.FakeFormC1Repository$getFormC1TabulationPartai$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                FakeFormC1Repository.this.saveCallResultGetFormC1TabulationPartai(item, idImage);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1TabulationPartaiComplete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = FakeFormC1Repository.this.database;
                return appDatabase.formC1TabulationDao().getTabulationPartaiByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            protected androidx.lifecycle.LiveData<org.informatika.sirekap.api.ApiResponse<org.informatika.sirekap.api.response.FormC1ImageApiResponse>> createCall() {
                /*
                    r7 = this;
                    java.lang.String r0 = r3
                    int r1 = r0.hashCode()
                    r2 = -992700931(0xffffffffc4d495fd, float:-1700.6871)
                    if (r1 == r2) goto L2b
                    r2 = -992700926(0xffffffffc4d49602, float:-1700.6877)
                    if (r1 == r2) goto L20
                    r2 = 3436278(0x346ef6, float:4.815251E-39)
                    if (r1 != r2) goto L9d
                    java.lang.String r1 = "pdpr"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L9d
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_pdpr_hal_2
                    goto L35
                L20:
                    java.lang.String r1 = "pdprdp"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L9d
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_pdprdp_hal_2
                    goto L35
                L2b:
                    java.lang.String r1 = "pdprdk"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L9d
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_pdprdk_hal_2
                L35:
                    org.informatika.sirekap.repository.fake.FakeFormC1Repository r1 = org.informatika.sirekap.repository.fake.FakeFormC1Repository.this
                    android.content.Context r1 = org.informatika.sirekap.repository.fake.FakeFormC1Repository.access$getContext$p(r1)
                    android.content.res.Resources r1 = r1.getResources()
                    java.io.InputStream r0 = r1.openRawResource(r0)
                    java.lang.String r1 = "context.resources.openRawResource(dummyJson)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
                    java.io.InputStreamReader r2 = new java.io.InputStreamReader
                    r2.<init>(r0, r1)
                    java.io.Reader r2 = (java.io.Reader) r2
                    boolean r0 = r2 instanceof java.io.BufferedReader
                    if (r0 == 0) goto L58
                    java.io.BufferedReader r2 = (java.io.BufferedReader) r2
                    goto L60
                L58:
                    java.io.BufferedReader r0 = new java.io.BufferedReader
                    r1 = 8192(0x2000, float:1.14794E-41)
                    r0.<init>(r2, r1)
                    r2 = r0
                L60:
                    java.io.Closeable r2 = (java.io.Closeable) r2
                    r0 = r2
                    java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch: java.lang.Throwable -> L96
                    java.io.Reader r0 = (java.io.Reader) r0     // Catch: java.lang.Throwable -> L96
                    java.lang.String r0 = kotlin.io.TextStreamsKt.readText(r0)     // Catch: java.lang.Throwable -> L96
                    r1 = 0
                    kotlin.io.CloseableKt.closeFinally(r2, r1)
                    androidx.lifecycle.MutableLiveData r2 = new androidx.lifecycle.MutableLiveData
                    org.informatika.sirekap.api.ApiSuccessResponse r3 = new org.informatika.sirekap.api.ApiSuccessResponse
                    org.informatika.sirekap.api.response.FormC1ImageApiResponse r4 = new org.informatika.sirekap.api.response.FormC1ImageApiResponse
                    org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetail r5 = new org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetail
                    org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetailPayload r6 = new org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetailPayload
                    r6.<init>(r0)
                    java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r6)
                    java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
                    r5.<init>(r0, r6)
                    r0 = 1
                    java.lang.String r6 = ""
                    r4.<init>(r0, r6, r1, r5)
                    r3.<init>(r4, r6)
                    r2.<init>(r3)
                    androidx.lifecycle.LiveData r2 = (androidx.lifecycle.LiveData) r2
                    return r2
                L96:
                    r0 = move-exception
                    throw r0     // Catch: java.lang.Throwable -> L98
                L98:
                    r1 = move-exception
                    kotlin.io.CloseableKt.closeFinally(r2, r0)
                    throw r1
                L9d:
                    java.lang.Exception r0 = new java.lang.Exception
                    java.lang.String r1 = "Unreachable"
                    r0.<init>(r1)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.fake.FakeFormC1Repository$getFormC1TabulationPartai$1.createCall():androidx.lifecycle.LiveData");
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.DefaultFormC1Repository, org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1AdministrationHal2Complete>> getFormC1AdministrationHal2(final String idImage, final String jenisPemilihan, boolean z, String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        return new NetworkBoundResource<FormC1AdministrationHal2Complete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.fake.FakeFormC1Repository$getFormC1AdministrationHal2$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                FakeFormC1Repository.this.saveCallResultGetFormC1AdministrationHal2(item, idImage);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1AdministrationHal2Complete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = FakeFormC1Repository.this.database;
                return appDatabase.formC1AdministrationHal2Dao().getByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            protected androidx.lifecycle.LiveData<org.informatika.sirekap.api.ApiResponse<org.informatika.sirekap.api.response.FormC1ImageApiResponse>> createCall() {
                /*
                    r7 = this;
                    java.lang.String r0 = r3
                    int r1 = r0.hashCode()
                    switch(r1) {
                        case -992700931: goto L2c;
                        case -992700926: goto L21;
                        case 3436264: goto L16;
                        case 3436278: goto Lb;
                        default: goto L9;
                    }
                L9:
                    goto L9e
                Lb:
                    java.lang.String r1 = "pdpr"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L9e
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_pdpr_hal_3
                    goto L36
                L16:
                    java.lang.String r1 = "pdpd"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L9e
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_pdpr_hal_3
                    goto L36
                L21:
                    java.lang.String r1 = "pdprdp"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L9e
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_pdpr_hal_3
                    goto L36
                L2c:
                    java.lang.String r1 = "pdprdk"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L9e
                    int r0 = org.informatika.sirekap.R.raw.dummy_image_payload_pdpr_hal_3
                L36:
                    org.informatika.sirekap.repository.fake.FakeFormC1Repository r1 = org.informatika.sirekap.repository.fake.FakeFormC1Repository.this
                    android.content.Context r1 = org.informatika.sirekap.repository.fake.FakeFormC1Repository.access$getContext$p(r1)
                    android.content.res.Resources r1 = r1.getResources()
                    java.io.InputStream r0 = r1.openRawResource(r0)
                    java.lang.String r1 = "context.resources.openRawResource(dummyJson)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
                    java.io.InputStreamReader r2 = new java.io.InputStreamReader
                    r2.<init>(r0, r1)
                    java.io.Reader r2 = (java.io.Reader) r2
                    boolean r0 = r2 instanceof java.io.BufferedReader
                    if (r0 == 0) goto L59
                    java.io.BufferedReader r2 = (java.io.BufferedReader) r2
                    goto L61
                L59:
                    java.io.BufferedReader r0 = new java.io.BufferedReader
                    r1 = 8192(0x2000, float:1.14794E-41)
                    r0.<init>(r2, r1)
                    r2 = r0
                L61:
                    java.io.Closeable r2 = (java.io.Closeable) r2
                    r0 = r2
                    java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch: java.lang.Throwable -> L97
                    java.io.Reader r0 = (java.io.Reader) r0     // Catch: java.lang.Throwable -> L97
                    java.lang.String r0 = kotlin.io.TextStreamsKt.readText(r0)     // Catch: java.lang.Throwable -> L97
                    r1 = 0
                    kotlin.io.CloseableKt.closeFinally(r2, r1)
                    androidx.lifecycle.MutableLiveData r2 = new androidx.lifecycle.MutableLiveData
                    org.informatika.sirekap.api.ApiSuccessResponse r3 = new org.informatika.sirekap.api.ApiSuccessResponse
                    org.informatika.sirekap.api.response.FormC1ImageApiResponse r4 = new org.informatika.sirekap.api.response.FormC1ImageApiResponse
                    org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetail r5 = new org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetail
                    org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetailPayload r6 = new org.informatika.sirekap.api.response.FormC1ImageApiResponse$FormCImageApiResponseDetailPayload
                    r6.<init>(r0)
                    java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r6)
                    java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()
                    r5.<init>(r0, r6)
                    r0 = 1
                    java.lang.String r6 = ""
                    r4.<init>(r0, r6, r1, r5)
                    r3.<init>(r4, r6)
                    r2.<init>(r3)
                    androidx.lifecycle.LiveData r2 = (androidx.lifecycle.LiveData) r2
                    return r2
                L97:
                    r0 = move-exception
                    throw r0     // Catch: java.lang.Throwable -> L99
                L99:
                    r1 = move-exception
                    kotlin.io.CloseableKt.closeFinally(r2, r0)
                    throw r1
                L9e:
                    java.lang.Exception r0 = new java.lang.Exception
                    java.lang.String r1 = "Unreachable"
                    r0.<init>(r1)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.repository.fake.FakeFormC1Repository$getFormC1AdministrationHal2$1.createCall():androidx.lifecycle.LiveData");
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.DefaultFormC1Repository, org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1AdministrationHal2PpwpComplete>> getFormC1AdministrationHal2Ppwp(final String idImage, final String jenisPemilihan, boolean z, String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        return new NetworkBoundResource<FormC1AdministrationHal2PpwpComplete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.fake.FakeFormC1Repository$getFormC1AdministrationHal2Ppwp$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                FakeFormC1Repository.this.saveCallResultGetFormC1AdministrationHal2Ppwp(item, idImage);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1AdministrationHal2PpwpComplete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = FakeFormC1Repository.this.database;
                return appDatabase.formC1AdministrationHal2Dao().getPpwpByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<FormC1ImageApiResponse>> createCall() {
                int i;
                Context context;
                String str = jenisPemilihan;
                if (Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                    i = R.raw.dummy_image_payload_ppwp_hal_3;
                } else if (!Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPR)) {
                    throw new Exception("Unreachable");
                } else {
                    i = R.raw.dummy_image_payload_pdpr_hal_3;
                }
                context = FakeFormC1Repository.this.context;
                InputStream openRawResource = context.getResources().openRawResource(i);
                Intrinsics.checkNotNullExpressionValue(openRawResource, "context.resources.openRawResource(dummyJson)");
                InputStreamReader inputStreamReader = new InputStreamReader(openRawResource, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    String readText = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    return new MutableLiveData(new ApiSuccessResponse(new FormC1ImageApiResponse(true, "", null, new FormC1ImageApiResponse.FormCImageApiResponseDetail(CollectionsKt.listOf(new FormC1ImageApiResponse.FormCImageApiResponseDetailPayload(readText)), CollectionsKt.emptyList())), ""));
                } finally {
                }
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.DefaultFormC1Repository, org.informatika.sirekap.repository.FormC1Repository
    public LiveData<Resource<FormC1AdministrationComplete>> verifyFormC1(String jenisPemilihan, String deviceId, int i, final String electionPageId, final String idImage, final boolean z, final List<Boolean> isSesuaiPerItem, final String komentar, final List<Integer> koreksiPerItem, String kodeTps) {
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        Intrinsics.checkNotNullParameter(idImage, "idImage");
        Intrinsics.checkNotNullParameter(isSesuaiPerItem, "isSesuaiPerItem");
        Intrinsics.checkNotNullParameter(komentar, "komentar");
        Intrinsics.checkNotNullParameter(koreksiPerItem, "koreksiPerItem");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        return new NetworkBoundResource<FormC1AdministrationComplete, FormC1ImageApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.fake.FakeFormC1Repository$verifyFormC1$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(FormC1AdministrationComplete formC1AdministrationComplete) {
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(FormC1ImageApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
                FakeFormC1Repository.this.saveCallResultVerifyFormC1(item, idImage, electionPageId);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<FormC1AdministrationComplete> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = FakeFormC1Repository.this.database;
                return appDatabase.formC1AdministrationDao().getByIdImage(idImage);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<FormC1ImageApiResponse>> createCall() {
                Context context;
                Integer num;
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
                context = FakeFormC1Repository.this.context;
                InputStream openRawResource = context.getResources().openRawResource(R.raw.dummy_image_payload_ppwp_hal_1);
                Intrinsics.checkNotNullExpressionValue(openRawResource, "context.resources.openRa…image_payload_ppwp_hal_1)");
                InputStreamReader inputStreamReader = new InputStreamReader(openRawResource, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    String readText = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    return new MutableLiveData(new ApiSuccessResponse(new FormC1ImageApiResponse(true, "", null, new FormC1ImageApiResponse.FormCImageApiResponseDetail(CollectionsKt.listOf(new FormC1ImageApiResponse.FormCImageApiResponseDetailPayload(readText)), CollectionsKt.listOf(new FormC1ImageApiResponse.FormCImageApiResponseDetailKesesuaian(z, komentar, isSesuaiPerItem, arrayList)))), ""));
                } finally {
                }
            }
        }.asLiveData();
    }

    /* compiled from: FakeFormC1Repository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/repository/fake/FakeFormC1Repository$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }
    }
}
