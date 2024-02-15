package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;

/* compiled from: ElectionPageDao.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H'J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H'J=\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H'¢\u0006\u0002\u0010\u0012JM\u0010\u0013\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H'¢\u0006\u0002\u0010\u0019J.\u0010\u001a\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u001a\u0010\u001b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H'J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\t\u001a\u00020\u0005H'J\u0010\u0010 \u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0012\u0010!\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\"\u001a\u00020\u00032\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H'J\u0010\u0010&\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H'J\u0018\u0010'\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010(\u001a\u00020)H'J\u0010\u0010*\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010+\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H'¨\u0006,"}, d2 = {"Lorg/informatika/sirekap/db/dao/ElectionPageDao;", "", "addVisionError", "", "idImage", "", "deleteById", "electionPageId", "deletePhoto", JobType.ID, "finishCreatePdf", "electionId", "finishPerspectiveCorrection", "correctedPhotoPath", "perspectiveCorrectionError", "hashDocumentCorrected", "aprilTagCode", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "finishPhoto", "photoPath", "croppedPhotoPath", "takePhotoAttempt", "signatureCroppedPhoto", "hashDocumentCropped", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "finishPhotoFromServer", "finishSend", "finishVerify", "getById", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/ElectionPageWithRelation;", "getByIdImage", "getByIdImageNullable", "insertAll", "electionPages", "", "Lorg/informatika/sirekap/model/ElectionPage;", "markAsContinueVerify", "markAsSaved", "isSaved", "", "removeVisionError", "startSend", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ElectionPageDao {
    void addVisionError(String str);

    void deleteById(String str);

    void deletePhoto(String str);

    void finishCreatePdf(String str);

    void finishPerspectiveCorrection(String str, String str2, String str3, String str4, Integer num);

    void finishPhoto(String str, String str2, String str3, int i, String str4, String str5, Integer num);

    void finishPhotoFromServer(String str, String str2, String str3, String str4);

    void finishSend(String str, String str2);

    void finishVerify(String str);

    LiveData<ElectionPageWithRelation> getById(String str);

    ElectionPageWithRelation getByIdImage(String str);

    ElectionPageWithRelation getByIdImageNullable(String str);

    void insertAll(List<ElectionPage> list);

    void markAsContinueVerify(String str);

    void markAsSaved(String str, boolean z);

    void removeVisionError(String str);

    void startSend(String str);
}
