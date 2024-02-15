package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Pair;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.support.Resource;

/* compiled from: TpsTimeRepository.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001Jv\u0010\u0002\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\t2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H&J.\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007H&¨\u0006\u0015"}, d2 = {"Lorg/informatika/sirekap/repository/TpsTimeRepository;", "", "addTpsTime", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/TpsTime;", "kodeTps", "", "startDate", "Ljava/util/Date;", "startTime", "Lkotlin/Pair;", "", "endDate", "endTime", "jenisWaktu", "jenisPemilihan", "deviceId", "isOffline", "", "getTpsTime", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface TpsTimeRepository {
    LiveData<Resource<TpsTime>> addTpsTime(String str, Date date, Pair<Integer, Integer> pair, Date date2, Pair<Integer, Integer> pair2, int i, String str2, String str3, boolean z);

    LiveData<Resource<TpsTime>> getTpsTime(String str, int i, String str2);
}
