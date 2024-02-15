package org.informatika.sirekap.db.dao;

import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationPartai;

/* compiled from: FormC1KesesuaianDao.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH'J\u0016\u0010\n\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH'¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/db/dao/FormC1KesesuaianTabulationPartaiDao;", "", "deleteBy", "", "idImage", "", "insertAll", "kesesuaian", "", "Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationPartai;", "insertAllReplace", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FormC1KesesuaianTabulationPartaiDao {
    void deleteBy(String str);

    void insertAll(List<FormC1KesesuaianTabulationPartai> list);

    void insertAllReplace(List<FormC1KesesuaianTabulationPartai> list);
}
