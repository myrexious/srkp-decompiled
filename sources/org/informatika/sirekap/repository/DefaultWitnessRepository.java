package org.informatika.sirekap.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.google.firebase.sessions.settings.RemoteSettings;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.File;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
import org.apache.xmpbox.type.ResourceRefType;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.api.response.AttendancePdfApiResponse;
import org.informatika.sirekap.api.response.AttendancePdfUploadUrlApiResponse;
import org.informatika.sirekap.api.response.EmptyApiResponse;
import org.informatika.sirekap.api.response.WitnessDetailApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.AttendancePage;
import org.informatika.sirekap.model.AttendanceWithPages;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessPemeriksa;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.repository.WitnessRepository;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.NetworkBoundResource;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.WitnessUtil;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import retrofit2.Response;

/* compiled from: WitnessRepository.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000e\b\u0016\u0018\u00002\u00020\u0001B9\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ.\u0010\u000f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0018\u00010\u00110\u00102\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J&\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00110\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J(\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u0017H\u0016J@\u0010!\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u0017H\u0016J\u0018\u0010)\u001a\u00020*2\u0006\u0010'\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u0017H\u0016J \u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u0017H\u0016J\u001e\u0010/\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u00110\u00102\u0006\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u0002032\u0006\u0010%\u001a\u00020\u0017H\u0016J \u00104\u001a\u0002032\u0006\u0010%\u001a\u00020\u00172\u0006\u00105\u001a\u00020\u00172\u0006\u00106\u001a\u00020\u0017H\u0016J\u0018\u00107\u001a\u0002032\u0006\u0010%\u001a\u00020\u00172\u0006\u00108\u001a\u00020\"H\u0016J\u001e\u00109\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020:\u0018\u00010\u00110\u00102\u0006\u0010%\u001a\u00020\u0017H\u0016J\u001e\u0010;\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00110\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J$\u0010<\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0018\u00010\u00110\u00102\u0006\u0010%\u001a\u00020\u0017H\u0016JF\u0010=\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000201\u0018\u00010\u00110\u00102\u0006\u0010>\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u00172\u0006\u0010@\u001a\u00020\u00172\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0017H\u0016J$\u0010D\u001a\u0002032\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00122\f\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00170\u0012H\u0004J&\u0010G\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00110\u00102\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010H\u001a\u00020\u0017H\u0016J\u0010\u0010I\u001a\u0002032\u0006\u0010J\u001a\u00020\u001aH\u0016J.\u0010K\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000201\u0018\u00010\u00110\u00102\u0006\u00100\u001a\u0002012\u0006\u0010L\u001a\u0002012\u0006\u0010M\u001a\u00020\"H\u0016J\u0010\u0010N\u001a\u0002032\u0006\u0010%\u001a\u00020\u0017H\u0016J6\u0010O\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000201\u0018\u00010\u00110\u00102\u0006\u0010>\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u00172\u0006\u0010@\u001a\u00020\u00172\u0006\u0010C\u001a\u00020\u0017H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006P"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "Lorg/informatika/sirekap/repository/WitnessRepository;", "context", "Landroid/content/Context;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "api", "Lorg/informatika/sirekap/api/SirekapApiInterface;", "apiUpload", "Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Landroid/content/Context;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/api/SirekapApiInterface;Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "addWitness", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "", "Lorg/informatika/sirekap/model/WitnessWithShare;", "witness", "Lorg/informatika/sirekap/repository/WitnessRepository$AddWitnessModel;", "noHandphone", "", "addWitnessLocal", "idPetugas", "", "addWitnessSync", "Lorg/informatika/sirekap/model/Witness;", "witnessPemeriksa", "Lorg/informatika/sirekap/model/WitnessPemeriksa;", "deviceId", "userId", "attendancePdfUpload", "", "fileUrl", "userIdOverride", "kodeTps", "namaFile", "usl", "mac", "attendancePdfUploadLink", "Lorg/informatika/sirekap/api/response/AttendancePdfUploadUrlApiResponse$AttendancePdfUploadUrlApiResponseDetail;", "attendancePdfUploadProvider", "url", ResourceRefType.FILE_PATH, "filename", "deleteAttendance", "attendancePage", "Lorg/informatika/sirekap/model/AttendancePage;", "failUploadPdf", "", "finishCreatePdf", "pdfFilePath", "pdfFileHash", "finishUploadPdf", "isUploadedPdfOffline", "getAttendanceByKodeTps", "Lorg/informatika/sirekap/model/AttendanceWithPages;", "getWitnessById", "getWitnessesByKodeTps", "insertAttendance", "attendancePageId", "photoPath", "croppedPhotoPath", "urutan", "", "hashDocumentCropped", "insertWitnesses", "witnesses", "noHandphones", "markAsShared", "jenisPemilihan", "markAttendeesChecked", "attendeesId", "moveAttendees", "switchedAttendancePage", "isUp", "startUploadPdf", "updateAttendancePagePhoto", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DefaultWitnessRepository implements WitnessRepository {
    private final SirekapApiInterface api;
    private final SirekapApiInterfaceUpload apiUpload;
    private final AppExecutors appExecutors;
    private final Context context;
    private final AppDatabase database;
    private final EncryptedSharedPreferences encryptedSharedPreferences;

    @Inject
    public DefaultWitnessRepository(@ApplicationContext Context context, AppDatabase database, SirekapApiInterface api, SirekapApiInterfaceUpload apiUpload, AppExecutors appExecutors, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(apiUpload, "apiUpload");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.context = context;
        this.database = database;
        this.api = api;
        this.apiUpload = apiUpload;
        this.appExecutors = appExecutors;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public LiveData<Resource<List<WitnessWithShare>>> getWitnessesByKodeTps(String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        return new DefaultWitnessRepository$getWitnessesByKodeTps$1(this, kodeTps, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public LiveData<Resource<WitnessWithShare>> getWitnessById(final String noHandphone) {
        Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
        return new NetworkBoundResource<WitnessWithShare, EmptyApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$getWitnessById$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(EmptyApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(WitnessWithShare witnessWithShare) {
                return false;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<WitnessWithShare> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultWitnessRepository.this.database;
                return appDatabase.witnessDao().getByNoHandphone(noHandphone);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.List, T] */
    @Override // org.informatika.sirekap.repository.WitnessRepository
    public LiveData<Resource<List<WitnessWithShare>>> addWitness(WitnessRepository.AddWitnessModel witness, String str) {
        Intrinsics.checkNotNullParameter(witness, "witness");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = witness.getIdPaslon();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = witness.getJenisPemilihan();
        if (Intrinsics.areEqual(witness.getJenisPemeriksa(), Witness.WITNESS_TYPE_PPS) || Intrinsics.areEqual(witness.getJenisPemeriksa(), Witness.WITNESS_TYPE_PANWAS)) {
            objectRef.element = CollectionsKt.mutableListOf(0L);
            objectRef2.element = CollectionsKt.mutableListOf(Election.ELECTION_PEMILIHAN_PRESIDEN);
        }
        return new DefaultWitnessRepository$addWitness$1(objectRef, objectRef2, this, str, witness, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public Witness addWitnessSync(Witness witness, WitnessPemeriksa witnessPemeriksa, String deviceId, String userId) {
        String message;
        Intrinsics.checkNotNullParameter(witness, "witness");
        Intrinsics.checkNotNullParameter(witnessPemeriksa, "witnessPemeriksa");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Response<WitnessDetailApiResponse> execute = this.api.addWitnessSync("https://sirekap-api.kpu.go.id/" + this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null) + RemoteSettings.FORWARD_SLASH_STRING + witnessPemeriksa.getJenisPemilihan() + "/petugas/create", ElectionUtil.extractKodeTpsReal(witness.getKodeTps()), witness.getNama(), witness.getJenisPemeriksa(), witness.getNik(), witness.getNoHandphoneFormatted(), witnessPemeriksa.getIdPilihan(), deviceId, CollectionsKt.emptyList(), userId).execute();
        if (execute.isSuccessful()) {
            WitnessDetailApiResponse body = execute.body();
            if (body != null && body.isSuccess()) {
                return body.getData();
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

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public LiveData<Resource<WitnessWithShare>> markAsShared(String noHandphone, String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(noHandphone, "noHandphone");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        return new DefaultWitnessRepository$markAsShared$1(this, noHandphone, jenisPemilihan, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public LiveData<Resource<WitnessWithShare>> addWitnessLocal(WitnessRepository.AddWitnessModel witness, long j) {
        Intrinsics.checkNotNullParameter(witness, "witness");
        return new DefaultWitnessRepository$addWitnessLocal$1(this, witness, j, SecurityFacade.INSTANCE.encryptCode(witness.getNoHandphone()), this.appExecutors).asLiveData();
    }

    public final void insertWitnesses(List<Witness> witnesses, List<String> noHandphones) {
        Intrinsics.checkNotNullParameter(witnesses, "witnesses");
        Intrinsics.checkNotNullParameter(noHandphones, "noHandphones");
        WitnessUtil.Companion.insertWitnessesToDatabase(this.database, witnesses, noHandphones);
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public LiveData<Resource<AttendancePage>> insertAttendance(long j, String kodeTps, String photoPath, String croppedPhotoPath, int i, String hashDocumentCropped) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(photoPath, "photoPath");
        Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
        Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
        return new DefaultWitnessRepository$insertAttendance$1(this, kodeTps, j, photoPath, croppedPhotoPath, i, hashDocumentCropped, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public LiveData<Resource<AttendancePage>> updateAttendancePagePhoto(long j, String photoPath, String croppedPhotoPath, String hashDocumentCropped) {
        Intrinsics.checkNotNullParameter(photoPath, "photoPath");
        Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
        Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
        return new DefaultWitnessRepository$updateAttendancePagePhoto$1(this, j, photoPath, croppedPhotoPath, hashDocumentCropped, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public LiveData<Resource<AttendanceWithPages>> getAttendanceByKodeTps(final String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        return new NetworkBoundResource<AttendanceWithPages, EmptyApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$getAttendanceByKodeTps$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(EmptyApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(AttendanceWithPages attendanceWithPages) {
                return false;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<AttendanceWithPages> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultWitnessRepository.this.database;
                return appDatabase.attendanceDao().getByKodeTps(kodeTps);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public LiveData<Resource<Boolean>> deleteAttendance(AttendancePage attendancePage) {
        Intrinsics.checkNotNullParameter(attendancePage, "attendancePage");
        return new DefaultWitnessRepository$deleteAttendance$1(this, attendancePage, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public LiveData<Resource<AttendancePage>> moveAttendees(AttendancePage attendancePage, AttendancePage switchedAttendancePage, boolean z) {
        Intrinsics.checkNotNullParameter(attendancePage, "attendancePage");
        Intrinsics.checkNotNullParameter(switchedAttendancePage, "switchedAttendancePage");
        return new DefaultWitnessRepository$moveAttendees$1(this, z, attendancePage, switchedAttendancePage, this.appExecutors).asLiveData();
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public void markAttendeesChecked(final long j) {
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository.markAttendeesChecked$lambda$1(DefaultWitnessRepository.this, j);
            }
        });
    }

    public static final void markAttendeesChecked$lambda$1(DefaultWitnessRepository this$0, final long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository.markAttendeesChecked$lambda$1$lambda$0(DefaultWitnessRepository.this, j);
            }
        });
    }

    public static final void markAttendeesChecked$lambda$1$lambda$0(DefaultWitnessRepository this$0, long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.database.attendancePageDao().markChecked(j);
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public void finishCreatePdf(final String kodeTps, final String pdfFilePath, final String pdfFileHash) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(pdfFilePath, "pdfFilePath");
        Intrinsics.checkNotNullParameter(pdfFileHash, "pdfFileHash");
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository.finishCreatePdf$lambda$2(DefaultWitnessRepository.this, kodeTps, pdfFilePath, pdfFileHash);
            }
        });
    }

    public static final void finishCreatePdf$lambda$2(DefaultWitnessRepository this$0, String kodeTps, String pdfFilePath, String pdfFileHash) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        Intrinsics.checkNotNullParameter(pdfFilePath, "$pdfFilePath");
        Intrinsics.checkNotNullParameter(pdfFileHash, "$pdfFileHash");
        this$0.database.attendanceDao().finishCreatePdf(kodeTps, pdfFilePath, pdfFileHash);
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public AttendancePdfUploadUrlApiResponse.AttendancePdfUploadUrlApiResponseDetail attendancePdfUploadLink(String usl, String kodeTps) {
        String message;
        Intrinsics.checkNotNullParameter(usl, "usl");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Response<AttendancePdfUploadUrlApiResponse> execute = this.apiUpload.attendancePdfUploadUrl("https://sirekap-api.kpu.go.id/" + this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null) + "/ppwp/dokumen/daftar-hadir/upload-url", usl, ElectionUtil.extractKodeTpsReal(kodeTps)).execute();
        if (execute.isSuccessful()) {
            AttendancePdfUploadUrlApiResponse body = execute.body();
            if (body != null && body.isSuccess()) {
                AttendancePdfUploadUrlApiResponse.AttendancePdfUploadUrlApiResponseDetail data = body.getData();
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

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public boolean attendancePdfUploadProvider(String url, String filePath, String filename) {
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

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public boolean attendancePdfUpload(String deviceId, String fileUrl, String userIdOverride, String kodeTps, String namaFile, String usl, String mac) {
        String message;
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(fileUrl, "fileUrl");
        Intrinsics.checkNotNullParameter(userIdOverride, "userIdOverride");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(namaFile, "namaFile");
        Intrinsics.checkNotNullParameter(usl, "usl");
        Intrinsics.checkNotNullParameter(mac, "mac");
        String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
        String extractKodeTpsReal = ElectionUtil.extractKodeTpsReal(kodeTps);
        Response<AttendancePdfApiResponse> execute = this.apiUpload.attendancePdfUpload("https://sirekap-api.kpu.go.id/" + stringEncrypted + "/ppwp/dokumen/daftar-hadir/upload", fileUrl, deviceId, userIdOverride, extractKodeTpsReal, namaFile, usl, mac, extractKodeTpsReal).execute();
        if (execute.isSuccessful()) {
            AttendancePdfApiResponse body = execute.body();
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

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public void startUploadPdf(final String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository.startUploadPdf$lambda$4(DefaultWitnessRepository.this, kodeTps);
            }
        });
    }

    public static final void startUploadPdf$lambda$4(DefaultWitnessRepository this$0, final String kodeTps) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository.startUploadPdf$lambda$4$lambda$3(DefaultWitnessRepository.this, kodeTps);
            }
        });
    }

    public static final void startUploadPdf$lambda$4$lambda$3(DefaultWitnessRepository this$0, String kodeTps) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        this$0.database.attendanceDao().updatePdfStatus(2, kodeTps);
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public void failUploadPdf(final String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository.failUploadPdf$lambda$5(DefaultWitnessRepository.this, kodeTps);
            }
        });
    }

    public static final void failUploadPdf$lambda$5(DefaultWitnessRepository this$0, String kodeTps) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        this$0.database.attendanceDao().updatePdfStatus(0, kodeTps);
    }

    @Override // org.informatika.sirekap.repository.WitnessRepository
    public void finishUploadPdf(final String kodeTps, final boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository.finishUploadPdf$lambda$7(DefaultWitnessRepository.this, kodeTps, z);
            }
        });
    }

    public static final void finishUploadPdf$lambda$7(DefaultWitnessRepository this$0, final String kodeTps, final boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultWitnessRepository$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                DefaultWitnessRepository.finishUploadPdf$lambda$7$lambda$6(DefaultWitnessRepository.this, kodeTps, z);
            }
        });
    }

    public static final void finishUploadPdf$lambda$7$lambda$6(DefaultWitnessRepository this$0, String kodeTps, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        this$0.database.attendanceDao().updatePdfStatus(1, kodeTps);
        this$0.database.attendanceDao().finishUploadPdf(kodeTps, z);
    }
}
