package org.informatika.sirekap.repository;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.util.Collection;
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
import org.apache.xmpbox.type.ResourceRefType;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.api.response.AttendancePdfApiResponse;
import org.informatika.sirekap.api.response.AttendancePdfUploadUrlApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import retrofit2.Response;

/* compiled from: SpecialOccurrenceRepository.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J@\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000eH\u0016J \u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultSpecialOccurrenceRepository;", "Lorg/informatika/sirekap/repository/SpecialOccurrenceRepository;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "apiUpload", "Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "failUploadPdf", "", "kodeTps", "", "finishUploadPdf", "isUploadedPdfOffline", "", "getUploadLink", "Lorg/informatika/sirekap/api/response/AttendancePdfUploadUrlApiResponse$AttendancePdfUploadUrlApiResponseDetail;", "kodeTpsOverride", "usl", "startUploadPdf", "upload", "deviceId", "fileUrl", "userIdOverride", "namaFile", "mac", "uploadProvider", "url", ResourceRefType.FILE_PATH, "filename", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DefaultSpecialOccurrenceRepository implements SpecialOccurrenceRepository {
    private final SirekapApiInterfaceUpload apiUpload;
    private final AppExecutors appExecutors;
    private final AppDatabase database;
    private final EncryptedSharedPreferences encryptedSharedPreferences;

    @Inject
    public DefaultSpecialOccurrenceRepository(AppExecutors appExecutors, AppDatabase database, SirekapApiInterfaceUpload apiUpload, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(apiUpload, "apiUpload");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.appExecutors = appExecutors;
        this.database = database;
        this.apiUpload = apiUpload;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
    }

    @Override // org.informatika.sirekap.repository.SpecialOccurrenceRepository
    public AttendancePdfUploadUrlApiResponse.AttendancePdfUploadUrlApiResponseDetail getUploadLink(String kodeTpsOverride, String usl) {
        String message;
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        Intrinsics.checkNotNullParameter(usl, "usl");
        Response<AttendancePdfUploadUrlApiResponse> execute = this.apiUpload.specialOccurrenceUploadUrl("https://sirekap-api.kpu.go.id/" + this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null) + "/ppwp/dokumen/kejadian-khusus/upload-url", usl, ElectionUtil.extractKodeTpsReal(kodeTpsOverride)).execute();
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

    @Override // org.informatika.sirekap.repository.SpecialOccurrenceRepository
    public boolean uploadProvider(String url, String filePath, String filename) {
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

    @Override // org.informatika.sirekap.repository.SpecialOccurrenceRepository
    public boolean upload(String deviceId, String fileUrl, String userIdOverride, String kodeTps, String namaFile, String usl, String mac) {
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
        Response<AttendancePdfApiResponse> execute = this.apiUpload.specialOccurrenceUpload("https://sirekap-api.kpu.go.id/" + stringEncrypted + "/ppwp/dokumen/kejadian-khusus/upload", fileUrl, deviceId, userIdOverride, extractKodeTpsReal, namaFile, usl, mac, extractKodeTpsReal).execute();
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

    @Override // org.informatika.sirekap.repository.SpecialOccurrenceRepository
    public void startUploadPdf(final String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultSpecialOccurrenceRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DefaultSpecialOccurrenceRepository.startUploadPdf$lambda$1(DefaultSpecialOccurrenceRepository.this, kodeTps);
            }
        });
    }

    public static final void startUploadPdf$lambda$1(DefaultSpecialOccurrenceRepository this$0, final String kodeTps) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultSpecialOccurrenceRepository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                DefaultSpecialOccurrenceRepository.startUploadPdf$lambda$1$lambda$0(DefaultSpecialOccurrenceRepository.this, kodeTps);
            }
        });
    }

    public static final void startUploadPdf$lambda$1$lambda$0(DefaultSpecialOccurrenceRepository this$0, String kodeTps) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        this$0.database.specialOccurrenceDao().updatePdfStatus(2, kodeTps);
    }

    @Override // org.informatika.sirekap.repository.SpecialOccurrenceRepository
    public void failUploadPdf(final String kodeTps) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        this.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultSpecialOccurrenceRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DefaultSpecialOccurrenceRepository.failUploadPdf$lambda$2(DefaultSpecialOccurrenceRepository.this, kodeTps);
            }
        });
    }

    public static final void failUploadPdf$lambda$2(DefaultSpecialOccurrenceRepository this$0, String kodeTps) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        this$0.database.specialOccurrenceDao().updatePdfStatus(0, kodeTps);
    }

    @Override // org.informatika.sirekap.repository.SpecialOccurrenceRepository
    public void finishUploadPdf(final String kodeTps, final boolean z) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        this.appExecutors.diskIO().execute(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultSpecialOccurrenceRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultSpecialOccurrenceRepository.finishUploadPdf$lambda$4(DefaultSpecialOccurrenceRepository.this, kodeTps, z);
            }
        });
    }

    public static final void finishUploadPdf$lambda$4(DefaultSpecialOccurrenceRepository this$0, final String kodeTps, final boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        this$0.database.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultSpecialOccurrenceRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DefaultSpecialOccurrenceRepository.finishUploadPdf$lambda$4$lambda$3(DefaultSpecialOccurrenceRepository.this, kodeTps, z);
            }
        });
    }

    public static final void finishUploadPdf$lambda$4$lambda$3(DefaultSpecialOccurrenceRepository this$0, String kodeTps, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(kodeTps, "$kodeTps");
        this$0.database.specialOccurrenceDao().updatePdfStatus(1, kodeTps);
        this$0.database.specialOccurrenceDao().finishUploadPdf(kodeTps, z);
    }
}
