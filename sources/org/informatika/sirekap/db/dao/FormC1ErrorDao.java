package org.informatika.sirekap.db.dao;

import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.FormC1Error;

/* compiled from: FormC1ErrorDao.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH'¨\u0006\f"}, d2 = {"Lorg/informatika/sirekap/db/dao/FormC1ErrorDao;", "", "deleteAll", "", "idImage", "", "formType", "", "insertAll", "errors", "", "Lorg/informatika/sirekap/model/FormC1Error;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FormC1ErrorDao {
    void deleteAll(String str, int i);

    void insertAll(List<FormC1Error> list);
}
