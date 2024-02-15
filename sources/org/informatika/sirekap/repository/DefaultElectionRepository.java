package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.apache.xmpbox.type.JobType;
import org.apache.xmpbox.type.ResourceRefType;
import org.apache.xmpbox.type.ThumbnailType;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.api.response.EmptyApiResponse;
import org.informatika.sirekap.api.response.FormCImageUploadUrlApiResponse;
import org.informatika.sirekap.api.response.UploadFormC1ImageApiResponse;
import org.informatika.sirekap.api.response.UploadFormC1ImageRekapApiResponse;
import org.informatika.sirekap.api.response.WilayahApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPageStage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.SpecialOccurrencePage;
import org.informatika.sirekap.model.SpecialOccurrenceWithPages;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.NetworkBoundResource;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import retrofit2.Response;

/* compiled from: ElectionRepository.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ&\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u001e\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J(\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010H\u0016J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010H\u0016JS\u0010\u001d\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\r0\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010\u00102\b\u0010 \u001a\u0004\u0018\u00010\u00102\b\u0010!\u001a\u0004\u0018\u00010\u00102\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016¢\u0006\u0002\u0010$Jc\u0010%\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\r0\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\b\u0010'\u001a\u0004\u0018\u00010\u00102\u0006\u0010(\u001a\u00020#2\b\u0010)\u001a\u0004\u0018\u00010\u00102\b\u0010*\u001a\u0004\u0018\u00010\u00102\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016¢\u0006\u0002\u0010+J$\u0010,\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010-\u001a\u0004\u0018\u00010\u00102\u0006\u0010.\u001a\u00020\u000eH\u0016J \u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010H\u0016J\u0018\u00101\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u000eH\u0016Jh\u00103\u001a\u00020\u000e2\u0006\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u00102\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u0010H\u0016J \u0010>\u001a\u00020?2\u0006\u00104\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\u0010H\u0016J \u0010A\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\u00102\u0006\u0010C\u001a\u00020\u00102\u0006\u0010D\u001a\u00020\u0010H\u0016J\u0080\u0001\u0010E\u001a\u00020F2\u0006\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00102\u0006\u0010G\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\u00102\u0006\u0010H\u001a\u00020#2\u0006\u0010I\u001a\u00020#2\u0006\u00107\u001a\u00020\u00102\u0006\u0010J\u001a\u00020\u00102\u0006\u0010K\u001a\u00020\u000e2\u0006\u0010L\u001a\u00020\u00102\u0006\u0010<\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u00102\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\u0006\u0010=\u001a\u00020\u0010H\u0016J8\u0010M\u001a\u00020?2\u0006\u00104\u001a\u00020\u00102\u0006\u0010H\u001a\u00020#2\u0006\u0010I\u001a\u00020#2\u0006\u0010;\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\u0010H\u0016J \u0010N\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\u00102\u0006\u0010C\u001a\u00020\u00102\u0006\u0010D\u001a\u00020\u0010H\u0016J\u001e\u0010O\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\r0\f2\u0006\u0010P\u001a\u00020\u0010H\u0016J\u001e\u0010Q\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020R\u0018\u00010\r0\f2\u0006\u0010P\u001a\u00020\u0010H\u0016J\u001e\u0010S\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020R\u0018\u00010\r0\f2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J$\u0010T\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0U\u0018\u00010\r0\f2\u0006\u00100\u001a\u00020\u0010H\u0016J\u001e\u0010V\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\r0\f2\u0006\u00100\u001a\u00020\u0010H\u0016J \u0010W\u001a\u00020#2\u0006\u0010I\u001a\u00020#2\u0006\u00104\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\u0010H\u0002J\u001e\u0010X\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020Y\u0018\u00010\r0\f2\u0006\u00100\u001a\u00020\u0010H\u0016J>\u0010Z\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\r0\f2\u0006\u0010P\u001a\u00020[2\u0006\u00100\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u0010H\u0016J\u0010\u0010\\\u001a\u00020\u00162\u0006\u0010P\u001a\u00020[H\u0016J\u0018\u0010]\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010^\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J6\u0010_\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\r0\f2\u0006\u0010P\u001a\u00020[2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u0010H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006`"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "Lorg/informatika/sirekap/repository/ElectionRepository;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "apiUpload", "Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "deleteElectionPagePhoto", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "", "electionId", "", "electionPageId", "deleteSpecialOccurrence", "specialOccurrencePage", "Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "failElectionPageSend", "", "failUploadPdf", "finishCreatePdf", "pdfFilePath", "pdfWitnessFilePath", "pdfFileHash", "finishCreateZip", "finishElectionPagePerspectiveCorrection", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "correctedPhotoPath", "perspectiveCorrectionError", "hashDocumentCorrected", "aprilTagCode", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Landroidx/lifecycle/LiveData;", "finishElectionPagePhoto", "photoPath", "croppedPhotoPath", "takePhotoAttempt", "signature", "hashDocumentCropped", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Landroidx/lifecycle/LiveData;", "finishElectionPageSend", "idImage", "isOffline", "finishSpecialOccurrenceCreatePdf", "kodeTps", "finishUploadPdf", "isUploadedPdfOffline", "formCImageRekapUpload", "jenisPemilihan", "deviceId", "userId", "device", "fileUrl", "fileHash", "sign", "usl", "namaFile", "mac", "formCImageRekapUploadLink", "Lorg/informatika/sirekap/api/response/FormCImageUploadUrlApiResponse$FormCImageUploadUrlApiResponseDetail;", "kodeTpsOverride", "formCImageRekapUploadProvider", "url", ResourceRefType.FILE_PATH, "filename", "formCImageUpload", "Lorg/informatika/sirekap/api/response/UploadFormC1ImageApiResponse$UploadFormC1ImageApiResponseDetail;", "userIdOverride", "jenisImage", "noLembar", "notificationToken", "isValid", ThumbnailType.IMAGE, "formCImageUploadLink", "formCImageUploadProvider", "getElectionById", JobType.ID, "getElectionPageById", "Lorg/informatika/sirekap/model/ElectionPageWithRelation;", "getElectionPageStagesByElectionPageId", "getElectionsByKodeTps", "", "getFirstElectionByKodeTps", "getNoLembarFinal", "getSpecialOccurrenceByKodeTps", "Lorg/informatika/sirekap/model/SpecialOccurrenceWithPages;", "insertSpecialOccurrence", "", "markSpecialOccurrenceChecked", "startElectionPageSend", "startUploadPdf", "updateSpecialOccurrencePagePhoto", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DefaultElectionRepository implements ElectionRepository {
    private final SirekapApiInterfaceUpload apiUpload;
    private final AppExecutors appExecutors;
    private final AppDatabase database;
    private final EncryptedSharedPreferences encryptedSharedPreferences;

    @Inject
    public DefaultElectionRepository(AppExecutors appExecutors, AppDatabase database, SirekapApiInterfaceUpload apiUpload, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(apiUpload, "apiUpload");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.appExecutors = appExecutors;
        this.database = database;
        this.apiUpload = apiUpload;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<List<ElectionWithRelation>>> getElectionsByKodeTps(final String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        return new NetworkBoundResource<List<? extends ElectionWithRelation>, WilayahApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$getElectionsByKodeTps$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(WilayahApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* renamed from: shouldFetch  reason: avoid collision after fix types in other method */
            protected boolean shouldFetch2(List<ElectionWithRelation> list) {
                return false;
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public /* bridge */ /* synthetic */ boolean shouldFetch(List<? extends ElectionWithRelation> list) {
                return shouldFetch2((List<ElectionWithRelation>) list);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<List<? extends ElectionWithRelation>> loadFromDb() {
                EncryptedSharedPreferences encryptedSharedPreferences;
                AppDatabase appDatabase;
                encryptedSharedPreferences = DefaultElectionRepository.this.encryptedSharedPreferences;
                String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
                String str = stringEncrypted;
                String str2 = str == null || StringsKt.isBlank(str) ? "R" : stringEncrypted;
                appDatabase = DefaultElectionRepository.this.database;
                return appDatabase.electionDao().getAllByKodeTps(kodeTps, str2);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<WilayahApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<ElectionWithRelation>> getFirstElectionByKodeTps(final String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        return new NetworkBoundResource<ElectionWithRelation, WilayahApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$getFirstElectionByKodeTps$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(WilayahApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(ElectionWithRelation electionWithRelation) {
                return false;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<ElectionWithRelation> loadFromDb() {
                EncryptedSharedPreferences encryptedSharedPreferences;
                AppDatabase appDatabase;
                encryptedSharedPreferences = DefaultElectionRepository.this.encryptedSharedPreferences;
                String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
                String str = stringEncrypted;
                String str2 = str == null || StringsKt.isBlank(str) ? "R" : stringEncrypted;
                appDatabase = DefaultElectionRepository.this.database;
                return appDatabase.electionDao().getFirstByKodeTps(kodeTps, str2);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<WilayahApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<ElectionWithRelation>> getElectionById(final String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        return new NetworkBoundResource<ElectionWithRelation, WilayahApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$getElectionById$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(WilayahApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(ElectionWithRelation electionWithRelation) {
                return false;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<ElectionWithRelation> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultElectionRepository.this.database;
                return appDatabase.electionDao().getById(id2);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<WilayahApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<ElectionPageWithRelation>> getElectionPageById(final String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        return new NetworkBoundResource<ElectionPageWithRelation, EmptyApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$getElectionPageById$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(EmptyApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(ElectionPageWithRelation electionPageWithRelation) {
                return false;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<ElectionPageWithRelation> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultElectionRepository.this.database;
                return appDatabase.electionPageDao().getById(id2);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<ElectionPageWithRelation>> getElectionPageStagesByElectionPageId(final String electionPageId) {
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        return new NetworkBoundResource<ElectionPageWithRelation, WilayahApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$getElectionPageStagesByElectionPageId$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(WilayahApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(ElectionPageWithRelation electionPageWithRelation) {
                return false;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<ElectionPageWithRelation> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultElectionRepository.this.database;
                return appDatabase.electionPageDao().getById(electionPageId);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<WilayahApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public void startElectionPageSend(String electionId, final String electionPageId) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.startElectionPageSend$lambda$1(DefaultElectionRepository.this, electionPageId);
            }
        });
    }

    public static final void startElectionPageSend$lambda$1(DefaultElectionRepository this$0, final String electionPageId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.startElectionPageSend$lambda$1$lambda$0(DefaultElectionRepository.this, electionPageId);
            }
        });
    }

    public static final void startElectionPageSend$lambda$1$lambda$0(DefaultElectionRepository this$0, String electionPageId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        this$0.database.electionPageStageDao().updateStatus(2, electionPageId, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND);
        this$0.database.electionPageStageDao().updateStatus(-1, electionPageId, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_VERIFY);
        this$0.database.electionPageDao().startSend(electionPageId);
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public void failElectionPageSend(final String str) {
        if (str != null) {
            this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultElectionRepository.failElectionPageSend$lambda$3$lambda$2(DefaultElectionRepository.this, str);
                }
            });
        }
    }

    public static final void failElectionPageSend$lambda$3$lambda$2(DefaultElectionRepository this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.database.electionPageStageDao().updateStatus(0, str, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND);
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<ElectionWithRelation>> finishElectionPagePerspectiveCorrection(String electionId, String electionPageId, String str, String str2, String str3, Integer num) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        return new DefaultElectionRepository$finishElectionPagePerspectiveCorrection$1(this, electionPageId, str, str2, str3, num, electionId, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public void finishElectionPageSend(final String str, final String str2, final boolean z) {
        if (str != null) {
            this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultElectionRepository.finishElectionPageSend$lambda$6$lambda$5(DefaultElectionRepository.this, str, z, str2);
                }
            });
        }
    }

    public static final void finishElectionPageSend$lambda$6$lambda$5(DefaultElectionRepository this$0, String str, boolean z, String str2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.database.electionPageStageDao().updateStatus(1, str, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_PHOTO);
        if (z) {
            this$0.database.electionPageStageDao().updateStatusIsOffline(1, str, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND);
        } else {
            this$0.database.electionPageStageDao().updateStatus(1, str, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND);
        }
        this$0.database.electionPageDao().finishSend(str, str2);
        if (str2 != null) {
            this$0.database.formC1KesesuaianAdministrasiDao().deleteBy(str2);
            this$0.database.formC1KesesuaianAdministrationHal2Dao().deleteBy(str2);
            this$0.database.formC1KesesuaianTabulationCandidateVoteDao().deleteBy(str2);
            this$0.database.formC1KesesuaianTabulationPartaiDao().deleteBy(str2);
        }
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public FormCImageUploadUrlApiResponse.FormCImageUploadUrlApiResponseDetail formCImageUploadLink(String jenisPemilihan, int i, int i2, String usl, String device, String kodeTpsOverride) {
        String message;
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(usl, "usl");
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
        String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTpsOverride);
        Response<FormCImageUploadUrlApiResponse> execute = this.apiUpload.formCImageUploadUrl("https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/formc-image/upload-url", i, getNoLembarFinal(i2, jenisPemilihan, extractKodeTpsReal), usl, device, extractKodeTpsReal).execute();
        if (execute.isSuccessful()) {
            FormCImageUploadUrlApiResponse body = execute.body();
            if (body != null && body.isSuccess()) {
                FormCImageUploadUrlApiResponse.FormCImageUploadUrlApiResponseDetail data = body.getData();
                Intrinsics.checkNotNull(data);
                return data;
            }
            throw new Exception(body != null ? body.getMessage() : null);
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

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public FormCImageUploadUrlApiResponse.FormCImageUploadUrlApiResponseDetail formCImageRekapUploadLink(String jenisPemilihan, String usl, String kodeTpsOverride) {
        String message;
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(usl, "usl");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        Response<FormCImageUploadUrlApiResponse> execute = this.apiUpload.formCImageRekapUploadUrl("https://sirekap-api.kpu.go.id/" + this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null) + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/dokumen/salinan-chasil/upload-url", ElectionUtil.extractKodeTpsReal(kodeTpsOverride), usl).execute();
        if (execute.isSuccessful()) {
            FormCImageUploadUrlApiResponse body = execute.body();
            if (body != null && body.isSuccess()) {
                FormCImageUploadUrlApiResponse.FormCImageUploadUrlApiResponseDetail data = body.getData();
                Intrinsics.checkNotNull(data);
                return data;
            }
            throw new Exception(body != null ? body.getMessage() : null);
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

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public boolean formCImageUploadProvider(String url, String filePath, String filename) {
        String message;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(filename, "filename");
        File file = new File(filePath);
        File file2 = new File(CollectionsKt.joinToString$default(CollectionsKt.plus((Collection<? extends String>) CollectionsKt.plus((Collection<? extends String>) CollectionsKt.dropLast(CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) filePath, new String[]{RemoteSettings.FORWARD_SLASH_STRING}, false, 0, 6, (Object) null)), 1), "api"), filename), RemoteSettings.FORWARD_SLASH_STRING, null, null, 0, null, null, 62, null));
        FilesKt.copyTo$default(file, file2, false, 0, 6, null);
        SirekapApiInterfaceUpload sirekapApiInterfaceUpload = this.apiUpload;
        RequestBody create = RequestBody.create(MediaType.parse("image/jpg"), FileUtils.readFileToByteArray(file2));
        Intrinsics.checkNotNullExpressionValue(create, "create(\n                …namedFile),\n            )");
        Response<Unit> execute = sirekapApiInterfaceUpload.formCImageUploadProvider(url, create).execute();
        if (execute.isSuccessful()) {
            return true;
        }
        if (execute.code() == 502) {
            throw new Exception("Saat ini server sedang sibuk, silahkan coba beberapa saat lagi");
        }
        ResponseBody errorBody = execute.errorBody();
        if (errorBody == null || (message = errorBody.string()) == null) {
            message = execute.message();
        }
        throw new Exception(message);
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public boolean formCImageRekapUploadProvider(String url, String filePath, String filename) {
        String message;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(filename, "filename");
        File file = new File(filePath);
        File file2 = new File(CollectionsKt.joinToString$default(CollectionsKt.plus((Collection<? extends String>) CollectionsKt.plus((Collection<? extends String>) CollectionsKt.dropLast(CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) filePath, new String[]{RemoteSettings.FORWARD_SLASH_STRING}, false, 0, 6, (Object) null)), 1), "api"), filename), RemoteSettings.FORWARD_SLASH_STRING, null, null, 0, null, null, 62, null));
        FilesKt.copyTo$default(file, file2, false, 0, 6, null);
        SirekapApiInterfaceUpload sirekapApiInterfaceUpload = this.apiUpload;
        RequestBody create = RequestBody.create(MediaType.parse("application/pdf"), FileUtils.readFileToByteArray(file2));
        Intrinsics.checkNotNullExpressionValue(create, "create(\n                …namedFile),\n            )");
        Response<Unit> execute = sirekapApiInterfaceUpload.formCImageUploadProvider(url, create).execute();
        if (execute.isSuccessful()) {
            return true;
        }
        if (execute.code() == 502) {
            throw new Exception("Saat ini server sedang sibuk, silahkan coba beberapa saat lagi");
        }
        ResponseBody errorBody = execute.errorBody();
        if (errorBody == null || (message = errorBody.string()) == null) {
            message = execute.message();
        }
        throw new Exception(message);
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public UploadFormC1ImageApiResponse.UploadFormC1ImageApiResponseDetail formCImageUpload(String jenisPemilihan, String deviceId, String userIdOverride, String kodeTpsOverride, int i, int i2, String device, String notificationToken, boolean z, String image, String namaFile, String fileHash, String sign, String usl, String mac) {
        String message;
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(userIdOverride, "userIdOverride");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(notificationToken, "notificationToken");
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(namaFile, "namaFile");
        Intrinsics.checkNotNullParameter(fileHash, "fileHash");
        Intrinsics.checkNotNullParameter(sign, "sign");
        Intrinsics.checkNotNullParameter(usl, "usl");
        Intrinsics.checkNotNullParameter(mac, "mac");
        String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
        String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTpsOverride);
        int noLembarFinal = getNoLembarFinal(i2, jenisPemilihan, extractKodeTpsReal);
        Boolean IS_DUMMY_VISION = BuildConfig.IS_DUMMY_VISION;
        Intrinsics.checkNotNullExpressionValue(IS_DUMMY_VISION, "IS_DUMMY_VISION");
        Response<UploadFormC1ImageApiResponse> execute = this.apiUpload.formCImageUpload("https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/formc-image/upload", deviceId, userIdOverride, extractKodeTpsReal, i, noLembarFinal, device, notificationToken, z, image, namaFile, fileHash, sign, usl, IS_DUMMY_VISION.booleanValue(), mac).execute();
        if (execute.isSuccessful()) {
            UploadFormC1ImageApiResponse body = execute.body();
            if (body != null && body.isSuccess()) {
                UploadFormC1ImageApiResponse.UploadFormC1ImageApiResponseDetail data = body.getData();
                Intrinsics.checkNotNull(data);
                return data;
            }
            throw new Exception(body != null ? body.getMessage() : null);
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

    private final int getNoLembarFinal(int i, String str, String str2) {
        boolean z = false;
        boolean startsWith$default = StringsKt.startsWith$default(str2, "11", false, 2, (Object) null);
        if (Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPR) || ((Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) && !startsWith$default) || (Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPRD_KABKO) && !startsWith$default))) {
            z = true;
        }
        if (i == 18 && z) {
            return 24;
        }
        return i;
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public boolean formCImageRekapUpload(String jenisPemilihan, String deviceId, String userId, String device, String fileUrl, String fileHash, String sign, String usl, String kodeTps, String namaFile, boolean z, String mac) {
        String message;
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(fileUrl, "fileUrl");
        Intrinsics.checkNotNullParameter(fileHash, "fileHash");
        Intrinsics.checkNotNullParameter(sign, "sign");
        Intrinsics.checkNotNullParameter(usl, "usl");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(namaFile, "namaFile");
        Intrinsics.checkNotNullParameter(mac, "mac");
        String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
        String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTps);
        Response<UploadFormC1ImageRekapApiResponse> execute = this.apiUpload.formCImageRekapUpload("https://sirekap-api.kpu.go.id/" + stringEncrypted + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + "/dokumen/salinan-chasil/upload", deviceId, userId, device, fileUrl, fileHash, sign, usl, extractKodeTpsReal, namaFile, z, mac, extractKodeTpsReal).execute();
        if (execute.isSuccessful()) {
            UploadFormC1ImageRekapApiResponse body = execute.body();
            if (body == null || !body.isSuccess()) {
                throw new Exception(body != null ? body.getMessage() : null);
            }
            return true;
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

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<ElectionWithRelation>> finishElectionPagePhoto(String electionId, String electionPageId, String photoPath, String str, int i, String str2, String str3, Integer num) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        Intrinsics.checkNotNullParameter(photoPath, "photoPath");
        return new DefaultElectionRepository$finishElectionPagePhoto$1(this, electionPageId, photoPath, str, i, str2, str3, num, electionId, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<Boolean>> deleteElectionPagePhoto(String electionId, String electionPageId) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
        return new DefaultElectionRepository$deleteElectionPagePhoto$1(this, electionPageId, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public void finishCreatePdf(final String electionId, final String pdfFilePath, final String pdfWitnessFilePath, final String pdfFileHash) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        Intrinsics.checkNotNullParameter(pdfFilePath, "pdfFilePath");
        Intrinsics.checkNotNullParameter(pdfWitnessFilePath, "pdfWitnessFilePath");
        Intrinsics.checkNotNullParameter(pdfFileHash, "pdfFileHash");
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.finishCreatePdf$lambda$7(DefaultElectionRepository.this, electionId, pdfFilePath, pdfWitnessFilePath, pdfFileHash);
            }
        });
    }

    public static final void finishCreatePdf$lambda$7(DefaultElectionRepository this$0, String electionId, String pdfFilePath, String pdfWitnessFilePath, String pdfFileHash) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionId, "$electionId");
        Intrinsics.checkNotNullParameter(pdfFilePath, "$pdfFilePath");
        Intrinsics.checkNotNullParameter(pdfWitnessFilePath, "$pdfWitnessFilePath");
        Intrinsics.checkNotNullParameter(pdfFileHash, "$pdfFileHash");
        this$0.database.electionDao().finishCreatePdf(electionId, pdfFilePath, pdfWitnessFilePath, pdfFileHash);
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public void startUploadPdf(final String electionId) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.startUploadPdf$lambda$9(DefaultElectionRepository.this, electionId);
            }
        });
    }

    public static final void startUploadPdf$lambda$9(DefaultElectionRepository this$0, final String electionId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionId, "$electionId");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.startUploadPdf$lambda$9$lambda$8(DefaultElectionRepository.this, electionId);
            }
        });
    }

    public static final void startUploadPdf$lambda$9$lambda$8(DefaultElectionRepository this$0, String electionId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionId, "$electionId");
        this$0.database.electionDao().updatePdfStatus(2, electionId);
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public void failUploadPdf(final String electionId) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.failUploadPdf$lambda$10(DefaultElectionRepository.this, electionId);
            }
        });
    }

    public static final void failUploadPdf$lambda$10(DefaultElectionRepository this$0, String electionId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionId, "$electionId");
        this$0.database.electionDao().updatePdfStatus(0, electionId);
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public void finishUploadPdf(final String electionId, final boolean z) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.finishUploadPdf$lambda$11(DefaultElectionRepository.this, electionId, z);
            }
        });
    }

    public static final void finishUploadPdf$lambda$11(DefaultElectionRepository this$0, String electionId, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionId, "$electionId");
        this$0.database.electionDao().updatePdfStatus(1, electionId);
        this$0.database.electionDao().finishUploadPdf(electionId, z);
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public void finishCreateZip(final String electionId) {
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.finishCreateZip$lambda$12(DefaultElectionRepository.this, electionId);
            }
        });
    }

    public static final void finishCreateZip$lambda$12(DefaultElectionRepository this$0, String electionId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionId, "$electionId");
        this$0.database.electionDao().finishCreateZip(electionId);
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<SpecialOccurrencePage>> insertSpecialOccurrence(long j, String kodeTps, String photoPath, String croppedPhotoPath, String hashDocumentCropped) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(photoPath, "photoPath");
        Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
        Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
        return new DefaultElectionRepository$insertSpecialOccurrence$1(this, kodeTps, j, photoPath, croppedPhotoPath, hashDocumentCropped, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<SpecialOccurrencePage>> updateSpecialOccurrencePagePhoto(long j, String photoPath, String croppedPhotoPath, String hashDocumentCropped) {
        Intrinsics.checkNotNullParameter(photoPath, "photoPath");
        Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
        Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
        return new DefaultElectionRepository$updateSpecialOccurrencePagePhoto$1(this, j, photoPath, croppedPhotoPath, hashDocumentCropped, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<SpecialOccurrenceWithPages>> getSpecialOccurrenceByKodeTps(final String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        return new NetworkBoundResource<SpecialOccurrenceWithPages, EmptyApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$getSpecialOccurrenceByKodeTps$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(EmptyApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(SpecialOccurrenceWithPages specialOccurrenceWithPages) {
                return false;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<SpecialOccurrenceWithPages> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultElectionRepository.this.database;
                return appDatabase.specialOccurrenceDao().getByKodeTps(kodeTps);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public LiveData<Resource<Boolean>> deleteSpecialOccurrence(SpecialOccurrencePage specialOccurrencePage) {
        Intrinsics.checkNotNullParameter(specialOccurrencePage, "specialOccurrencePage");
        return new DefaultElectionRepository$deleteSpecialOccurrence$1(this, specialOccurrencePage, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public void markSpecialOccurrenceChecked(final long j) {
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.markSpecialOccurrenceChecked$lambda$14(DefaultElectionRepository.this, j);
            }
        });
    }

    public static final void markSpecialOccurrenceChecked$lambda$14(DefaultElectionRepository this$0, final long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.markSpecialOccurrenceChecked$lambda$14$lambda$13(DefaultElectionRepository.this, j);
            }
        });
    }

    public static final void markSpecialOccurrenceChecked$lambda$14$lambda$13(DefaultElectionRepository this$0, long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.database.specialOccurrencePageDao().markChecked(j);
    }

    @Override // org.informatika.sirekap.repository.ElectionRepository
    public void finishSpecialOccurrenceCreatePdf(final String kodeTps, final String pdfFilePath, final String pdfFileHash) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(pdfFilePath, "pdfFilePath");
        Intrinsics.checkNotNullParameter(pdfFileHash, "pdfFileHash");
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository.finishSpecialOccurrenceCreatePdf$lambda$15(DefaultElectionRepository.this, kodeTps, pdfFilePath, pdfFileHash);
            }
        });
    }

    public static final void finishSpecialOccurrenceCreatePdf$lambda$15(DefaultElectionRepository this$0, String kodeTps, String pdfFilePath, String pdfFileHash) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        Intrinsics.checkNotNullParameter(pdfFilePath, "$pdfFilePath");
        Intrinsics.checkNotNullParameter(pdfFileHash, "$pdfFileHash");
        this$0.database.specialOccurrenceDao().finishCreatePdf(kodeTps, pdfFilePath, pdfFileHash);
    }
}
