package org.informatika.sirekap.repository.fake;

import com.google.firebase.analytics.FirebaseAnalytics;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.apache.xmpbox.type.ResourceRefType;
import org.apache.xmpbox.type.ThumbnailType;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.api.response.FormCImageUploadUrlApiResponse;
import org.informatika.sirekap.api.response.UploadFormC1ImageApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: FakeElectionRepository.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0080\u0001\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000eH\u0016J8\u0010\u001f\u001a\u00020 2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J \u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000eH\u0016¨\u0006%"}, d2 = {"Lorg/informatika/sirekap/repository/fake/FakeElectionRepository;", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "apiUpload", "Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Lorg/informatika/sirekap/support/AppExecutors;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/api/SirekapApiInterfaceUpload;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "formCImageUpload", "Lorg/informatika/sirekap/api/response/UploadFormC1ImageApiResponse$UploadFormC1ImageApiResponseDetail;", "jenisPemilihan", "", "deviceId", "userIdOverride", "kodeTpsOverride", "jenisImage", "", "noLembar", "device", "notificationToken", "isValid", "", ThumbnailType.IMAGE, "namaFile", "fileHash", "sign", "usl", "mac", "formCImageUploadLink", "Lorg/informatika/sirekap/api/response/FormCImageUploadUrlApiResponse$FormCImageUploadUrlApiResponseDetail;", "formCImageUploadProvider", "url", ResourceRefType.FILE_PATH, "filename", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FakeElectionRepository extends DefaultElectionRepository {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Inject
    public FakeElectionRepository(AppExecutors appExecutors, AppDatabase database, SirekapApiInterfaceUpload apiUpload, EncryptedSharedPreferences encryptedSharedPreferences) {
        super(appExecutors, database, apiUpload, encryptedSharedPreferences);
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(apiUpload, "apiUpload");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
    }

    @Override // org.informatika.sirekap.repository.DefaultElectionRepository, org.informatika.sirekap.repository.ElectionRepository
    public FormCImageUploadUrlApiResponse.FormCImageUploadUrlApiResponseDetail formCImageUploadLink(String jenisPemilihan, int i, int i2, String usl, String device, String kodeTpsOverride) {
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(usl, "usl");
        Intrinsics.checkNotNullParameter(device, "device");
        Intrinsics.checkNotNullParameter(kodeTpsOverride, "kodeTpsOverride");
        if (Intrinsics.areEqual(FirebaseAnalytics.Param.SUCCESS, "error")) {
            throw new Exception("Saat ini server sedang sibuk, silahkan coba beberapa saat lagi");
        }
        return new FormCImageUploadUrlApiResponse.FormCImageUploadUrlApiResponseDetail("https://alicloud.com/upload/form-cimage.png", "https://alicloud.com/view/form-cimage.png", "formc-image.png", "");
    }

    @Override // org.informatika.sirekap.repository.DefaultElectionRepository, org.informatika.sirekap.repository.ElectionRepository
    public boolean formCImageUploadProvider(String url, String filePath, String filename) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(filename, "filename");
        if (Intrinsics.areEqual(FirebaseAnalytics.Param.SUCCESS, "error")) {
            throw new Exception("Saat ini server sedang sibuk, silahkan coba beberapa saat lagi");
        }
        return true;
    }

    @Override // org.informatika.sirekap.repository.DefaultElectionRepository, org.informatika.sirekap.repository.ElectionRepository
    public UploadFormC1ImageApiResponse.UploadFormC1ImageApiResponseDetail formCImageUpload(String jenisPemilihan, String deviceId, String userIdOverride, String kodeTpsOverride, int i, int i2, String device, String notificationToken, boolean z, String image, String namaFile, String fileHash, String sign, String usl, String mac) {
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
        if (Intrinsics.areEqual(FirebaseAnalytics.Param.SUCCESS, "error")) {
            throw new Exception("Saat ini server sedang sibuk, silahkan coba beberapa saat lagi");
        }
        return new UploadFormC1ImageApiResponse.UploadFormC1ImageApiResponseDetail("", Random.Default.nextInt(), i, jenisPemilihan, i2, "");
    }
}
