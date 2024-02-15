package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.SpecialOccurrencePage;

/* compiled from: SpecialOccurrencePageDao.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J,\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\bH'J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u000e\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u0010H'J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/db/dao/SpecialOccurrencePageDao;", "", "deleteBy", "", JobType.ID, "", "finishPhoto", "photoPath", "", "croppedPhotoPath", "hashDocumentCropped", "getById", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "insertAll", "specialOccurrences", "", "markChecked", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface SpecialOccurrencePageDao {
    void deleteBy(long j);

    void finishPhoto(long j, String str, String str2, String str3);

    LiveData<SpecialOccurrencePage> getById(long j);

    void insertAll(List<SpecialOccurrencePage> list);

    void markChecked(long j);
}
