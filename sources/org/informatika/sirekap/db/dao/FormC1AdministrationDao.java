package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.FormC1Administration;
import org.informatika.sirekap.model.FormC1AdministrationComplete;

/* compiled from: FormC1AdministrationDao.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH'¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/db/dao/FormC1AdministrationDao;", "", "getByIdImage", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "idImage", "", "getByIdImageSync", "insertAll", "", "forms", "", "Lorg/informatika/sirekap/model/FormC1Administration;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FormC1AdministrationDao {
    LiveData<FormC1AdministrationComplete> getByIdImage(String str);

    FormC1AdministrationComplete getByIdImageSync(String str);

    void insertAll(List<FormC1Administration> list);
}
