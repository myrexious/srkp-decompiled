package org.informatika.sirekap.repository;

import kotlin.Metadata;
import org.apache.xmpbox.type.ResourceRefType;
import org.informatika.sirekap.api.response.AttendancePdfUploadUrlApiResponse;

/* compiled from: SpecialOccurrenceRepository.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J@\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005H&J \u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H&¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/repository/SpecialOccurrenceRepository;", "", "failUploadPdf", "", "kodeTps", "", "finishUploadPdf", "isUploadedPdfOffline", "", "getUploadLink", "Lorg/informatika/sirekap/api/response/AttendancePdfUploadUrlApiResponse$AttendancePdfUploadUrlApiResponseDetail;", "kodeTpsOverride", "usl", "startUploadPdf", "upload", "deviceId", "fileUrl", "userIdOverride", "namaFile", "mac", "uploadProvider", "url", ResourceRefType.FILE_PATH, "filename", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface SpecialOccurrenceRepository {
    void failUploadPdf(String str);

    void finishUploadPdf(String str, boolean z);

    AttendancePdfUploadUrlApiResponse.AttendancePdfUploadUrlApiResponseDetail getUploadLink(String str, String str2) throws Exception;

    void startUploadPdf(String str);

    boolean upload(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws Exception;

    boolean uploadProvider(String str, String str2, String str3) throws Exception;

    /* compiled from: SpecialOccurrenceRepository.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void finishUploadPdf$default(SpecialOccurrenceRepository specialOccurrenceRepository, String str, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: finishUploadPdf");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            specialOccurrenceRepository.finishUploadPdf(str, z);
        }
    }
}
