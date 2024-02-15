package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.FormC1AdministrationHal2;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete;

/* compiled from: FormC1AdministrationHal2Dao.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH'¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/db/dao/FormC1AdministrationHal2Dao;", "", "getByIdImage", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "idImage", "", "getByIdImageSync", "getPpwpByIdImage", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2PpwpComplete;", "insertAll", "", "forms", "", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FormC1AdministrationHal2Dao {
    LiveData<FormC1AdministrationHal2Complete> getByIdImage(String str);

    FormC1AdministrationHal2Complete getByIdImageSync(String str);

    LiveData<FormC1AdministrationHal2PpwpComplete> getPpwpByIdImage(String str);

    void insertAll(List<FormC1AdministrationHal2> list);
}
