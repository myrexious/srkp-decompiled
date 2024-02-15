package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.Tps;

/* compiled from: TpsDao.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0014\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H'J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H'¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/db/dao/TpsDao;", "", "deleteAll", "", "getAll", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/Tps;", "insertAll", "tpss", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface TpsDao {
    void deleteAll();

    LiveData<List<Tps>> getAll();

    void insertAll(List<Tps> list);
}
