package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.BackgroundProcess;

/* compiled from: BackgroundProcessDao.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH'J \u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H'J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH'¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/db/dao/BackgroundProcessDao;", "", "deleteById", "", JobType.ID, "", "getById", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/BackgroundProcess;", "insertAll", "backgroundProcesses", "", "markAsFailed", "endedAt", "", "errorMessage", "markAsSuccess", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface BackgroundProcessDao {
    void deleteById(String str);

    LiveData<BackgroundProcess> getById(String str);

    void insertAll(List<BackgroundProcess> list);

    void markAsFailed(String str, long j, String str2);

    void markAsSuccess(String str, long j);
}
