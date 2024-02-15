package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.TpsTime;

/* compiled from: TpsTimeDao.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\bg\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H'J\"\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H'J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000eH'¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/db/dao/TpsTimeDao;", "", "getByKodeTps", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/TpsTime;", "kodeTps", "", "jenisWaktu", "", "jenisPemilihan", "getByKodeTpsSync", "insertAll", "", "tpsTimes", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface TpsTimeDao {
    LiveData<TpsTime> getByKodeTps(String str, int i, String str2);

    TpsTime getByKodeTpsSync(String str, int i, String str2);

    void insertAll(List<TpsTime> list);
}
