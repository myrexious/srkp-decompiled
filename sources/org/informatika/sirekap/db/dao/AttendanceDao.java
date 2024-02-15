package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.Attendance;
import org.informatika.sirekap.model.AttendanceWithPages;

/* compiled from: AttendanceDao.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H'J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH'J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H'J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/db/dao/AttendanceDao;", "", "finishCreatePdf", "", "kodeTps", "", "pdfFilePath", "pdfFileHash", "finishUploadPdf", "isUploadedPdfOffline", "", "getByKodeTps", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/AttendanceWithPages;", "getByKodeTpsSync", "insertAll", "attendees", "", "Lorg/informatika/sirekap/model/Attendance;", "updatePdfStatus", "uploadPdfStatus", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface AttendanceDao {
    void finishCreatePdf(String str, String str2, String str3);

    void finishUploadPdf(String str, boolean z);

    LiveData<AttendanceWithPages> getByKodeTps(String str);

    AttendanceWithPages getByKodeTpsSync(String str);

    void insertAll(List<Attendance> list);

    void updatePdfStatus(int i, String str);
}
