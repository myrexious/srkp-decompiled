package org.informatika.sirekap.db.dao;

import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.UploadImageAttempt;

/* compiled from: UploadImageAttemptDao.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\fH'J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/db/dao/UploadImageAttemptDao;", "", "deleteBy", "", "electionPageId", "", "jenisImage", "", "getBy", "Lorg/informatika/sirekap/model/UploadImageAttempt;", "insertAll", "records", "", "markAsSuccess", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UploadImageAttemptDao {
    void deleteBy(String str);

    void deleteBy(String str, int i);

    UploadImageAttempt getBy(String str, int i);

    void insertAll(List<UploadImageAttempt> list);

    void markAsSuccess(String str, int i);
}