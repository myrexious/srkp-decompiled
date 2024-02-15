package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.apache.xmpbox.type.JobType;
import org.apache.xmpbox.type.ResourceRefType;
import org.apache.xmpbox.type.ThumbnailType;
import org.informatika.sirekap.api.response.FormCImageUploadUrlApiResponse;
import org.informatika.sirekap.api.response.UploadFormC1ImageApiResponse;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.SpecialOccurrencePage;
import org.informatika.sirekap.model.SpecialOccurrenceWithPages;
import org.informatika.sirekap.support.Resource;

/* compiled from: ElectionRepository.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&J\u001e\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0012\u0010\f\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H&J(\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007H&J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H&JS\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH&¢\u0006\u0002\u0010\u001bJc\u0010\u001c\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001f\u001a\u00020\u001a2\b\u0010 \u001a\u0004\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH&¢\u0006\u0002\u0010\"J&\u0010#\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010$\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010%\u001a\u00020\u0005H&J \u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007H&J\u001a\u0010(\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010)\u001a\u00020\u0005H&Jh\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u0007H&J \u00105\u001a\u0002062\u0006\u0010+\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u0007H&J \u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\u00072\u0006\u0010;\u001a\u00020\u0007H&J\u0080\u0001\u0010<\u001a\u00020=2\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u00072\u0006\u0010?\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u00072\u0006\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u0007H&J8\u0010D\u001a\u0002062\u0006\u0010+\u001a\u00020\u00072\u0006\u0010?\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020\u001a2\u0006\u00102\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u0007H&J \u0010E\u001a\u00020\u00052\u0006\u00109\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\u00072\u0006\u0010;\u001a\u00020\u0007H&J\u001e\u0010F\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00040\u00032\u0006\u0010G\u001a\u00020\u0007H&J\u001e\u0010H\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020I\u0018\u00010\u00040\u00032\u0006\u0010G\u001a\u00020\u0007H&J\u001e\u0010J\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020I\u0018\u00010\u00040\u00032\u0006\u0010\b\u001a\u00020\u0007H&J$\u0010K\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150L\u0018\u00010\u00040\u00032\u0006\u0010'\u001a\u00020\u0007H&J\u001e\u0010M\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00040\u00032\u0006\u0010'\u001a\u00020\u0007H&J\u001e\u0010N\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020O\u0018\u00010\u00040\u00032\u0006\u0010'\u001a\u00020\u0007H&J>\u0010P\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00040\u00032\u0006\u0010G\u001a\u00020Q2\u0006\u0010'\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0007H&J\u0010\u0010R\u001a\u00020\r2\u0006\u0010G\u001a\u00020QH&J\u0018\u0010S\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010T\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H&J6\u0010U\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00040\u00032\u0006\u0010G\u001a\u00020Q2\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0007H&¨\u0006V"}, d2 = {"Lorg/informatika/sirekap/repository/ElectionRepository;", "", "deleteElectionPagePhoto", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "", "electionId", "", "electionPageId", "deleteSpecialOccurrence", "specialOccurrencePage", "Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "failElectionPageSend", "", "failUploadPdf", "finishCreatePdf", "pdfFilePath", "pdfWitnessFilePath", "pdfFileHash", "finishCreateZip", "finishElectionPagePerspectiveCorrection", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "correctedPhotoPath", "perspectiveCorrectionError", "hashDocumentCorrected", "aprilTagCode", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Landroidx/lifecycle/LiveData;", "finishElectionPagePhoto", "photoPath", "croppedPhotoPath", "takePhotoAttempt", "signature", "hashDocumentCropped", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Landroidx/lifecycle/LiveData;", "finishElectionPageSend", "idImage", "isOffline", "finishSpecialOccurrenceCreatePdf", "kodeTps", "finishUploadPdf", "isUploadedPdfOffline", "formCImageRekapUpload", "jenisPemilihan", "deviceId", "userId", "device", "fileUrl", "fileHash", "sign", "usl", "namaFile", "mac", "formCImageRekapUploadLink", "Lorg/informatika/sirekap/api/response/FormCImageUploadUrlApiResponse$FormCImageUploadUrlApiResponseDetail;", "kodeTpsOverride", "formCImageRekapUploadProvider", "url", ResourceRefType.FILE_PATH, "filename", "formCImageUpload", "Lorg/informatika/sirekap/api/response/UploadFormC1ImageApiResponse$UploadFormC1ImageApiResponseDetail;", "userIdOverride", "jenisImage", "noLembar", "notificationToken", "isValid", ThumbnailType.IMAGE, "formCImageUploadLink", "formCImageUploadProvider", "getElectionById", JobType.ID, "getElectionPageById", "Lorg/informatika/sirekap/model/ElectionPageWithRelation;", "getElectionPageStagesByElectionPageId", "getElectionsByKodeTps", "", "getFirstElectionByKodeTps", "getSpecialOccurrenceByKodeTps", "Lorg/informatika/sirekap/model/SpecialOccurrenceWithPages;", "insertSpecialOccurrence", "", "markSpecialOccurrenceChecked", "startElectionPageSend", "startUploadPdf", "updateSpecialOccurrencePagePhoto", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ElectionRepository {
    LiveData<Resource<Boolean>> deleteElectionPagePhoto(String str, String str2);

    LiveData<Resource<Boolean>> deleteSpecialOccurrence(SpecialOccurrencePage specialOccurrencePage);

    void failElectionPageSend(String str);

    void failUploadPdf(String str);

    void finishCreatePdf(String str, String str2, String str3, String str4);

    void finishCreateZip(String str);

    LiveData<Resource<ElectionWithRelation>> finishElectionPagePerspectiveCorrection(String str, String str2, String str3, String str4, String str5, Integer num);

    LiveData<Resource<ElectionWithRelation>> finishElectionPagePhoto(String str, String str2, String str3, String str4, int i, String str5, String str6, Integer num);

    void finishElectionPageSend(String str, String str2, boolean z);

    void finishSpecialOccurrenceCreatePdf(String str, String str2, String str3);

    void finishUploadPdf(String str, boolean z);

    boolean formCImageRekapUpload(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, boolean z, String str11) throws Exception;

    FormCImageUploadUrlApiResponse.FormCImageUploadUrlApiResponseDetail formCImageRekapUploadLink(String str, String str2, String str3) throws Exception;

    boolean formCImageRekapUploadProvider(String str, String str2, String str3) throws Exception;

    UploadFormC1ImageApiResponse.UploadFormC1ImageApiResponseDetail formCImageUpload(String str, String str2, String str3, String str4, int i, int i2, String str5, String str6, boolean z, String str7, String str8, String str9, String str10, String str11, String str12) throws Exception;

    FormCImageUploadUrlApiResponse.FormCImageUploadUrlApiResponseDetail formCImageUploadLink(String str, int i, int i2, String str2, String str3, String str4) throws Exception;

    boolean formCImageUploadProvider(String str, String str2, String str3) throws Exception;

    LiveData<Resource<ElectionWithRelation>> getElectionById(String str);

    LiveData<Resource<ElectionPageWithRelation>> getElectionPageById(String str);

    LiveData<Resource<ElectionPageWithRelation>> getElectionPageStagesByElectionPageId(String str);

    LiveData<Resource<List<ElectionWithRelation>>> getElectionsByKodeTps(String str);

    LiveData<Resource<ElectionWithRelation>> getFirstElectionByKodeTps(String str);

    LiveData<Resource<SpecialOccurrenceWithPages>> getSpecialOccurrenceByKodeTps(String str);

    LiveData<Resource<SpecialOccurrencePage>> insertSpecialOccurrence(long j, String str, String str2, String str3, String str4);

    void markSpecialOccurrenceChecked(long j);

    void startElectionPageSend(String str, String str2);

    void startUploadPdf(String str);

    LiveData<Resource<SpecialOccurrencePage>> updateSpecialOccurrencePagePhoto(long j, String str, String str2, String str3);

    /* compiled from: ElectionRepository.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void finishElectionPageSend$default(ElectionRepository electionRepository, String str, String str2, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: finishElectionPageSend");
            }
            if ((i & 4) != 0) {
                z = false;
            }
            electionRepository.finishElectionPageSend(str, str2, z);
        }

        public static /* synthetic */ void finishUploadPdf$default(ElectionRepository electionRepository, String str, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: finishUploadPdf");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            electionRepository.finishUploadPdf(str, z);
        }
    }
}
