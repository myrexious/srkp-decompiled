package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionWithRelation;

/* compiled from: ElectionDao.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H'J(\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H'J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H'J$\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H'J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00122\u0006\u0010\t\u001a\u00020\u0005H'J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0005H'J\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H'J\u0016\u0010\u001a\u001a\u00020\u00032\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0013H'J\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\t\u001a\u00020\u0005H'¨\u0006 "}, d2 = {"Lorg/informatika/sirekap/db/dao/ElectionDao;", "", "deleteBy", "", "kodeTps", "", "deleteById", "electionId", "finishCreatePdf", JobType.ID, "pdfFilePath", "pdfWitnessFilePath", "pdfFileHash", "finishCreateZip", "finishUploadPdf", "isUploadedPdfOffline", "", "getAllByKodeTps", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "electionType", "getAllByKodeTpsSync", "getById", "getByIdSync", "getFirstByKodeTps", "insertAll", "elections", "Lorg/informatika/sirekap/model/Election;", "updatePdfStatus", "uploadPdfStatus", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ElectionDao {
    void deleteBy(String str);

    void deleteById(String str);

    void finishCreatePdf(String str, String str2, String str3, String str4);

    void finishCreateZip(String str);

    void finishUploadPdf(String str, boolean z);

    LiveData<List<ElectionWithRelation>> getAllByKodeTps(String str, String str2);

    List<ElectionWithRelation> getAllByKodeTpsSync(String str);

    LiveData<ElectionWithRelation> getById(String str);

    ElectionWithRelation getByIdSync(String str);

    LiveData<ElectionWithRelation> getFirstByKodeTps(String str, String str2);

    void insertAll(List<Election> list);

    void updatePdfStatus(int i, String str);
}
