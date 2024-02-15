package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessPemeriksa;
import org.informatika.sirekap.model.WitnessShare;
import org.informatika.sirekap.model.WitnessWithShare;

/* compiled from: WitnessDao.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H'J0\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H'J\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\r2\u0006\u0010\u0007\u001a\u00020\u0005H'J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u0005H'J\u0016\u0010\u0013\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000eH'J\u0016\u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000eH'J\u0016\u0010\u0019\u001a\u00020\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000eH'J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H'J \u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0005H'J\u0018\u0010 \u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"H'¨\u0006#"}, d2 = {"Lorg/informatika/sirekap/db/dao/WitnessDao;", "", "deleteAllOnline", "", "kodeTps", "", "deleteByNoHandphone", "noHandphone", "deleteLocalBy", "name", "nik", "jenisPemeriksa", "getAllByKodeTps", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/WitnessWithShare;", "getAllByKodeTpsSync", "getByNoHandphone", "getByNoHandphoneSync", "insertAll", "witnesses", "Lorg/informatika/sirekap/model/Witness;", "insertAllPemeriksas", "witnessPemeriksas", "Lorg/informatika/sirekap/model/WitnessPemeriksa;", "insertAllShares", "witnessShares", "Lorg/informatika/sirekap/model/WitnessShare;", "markAsSharedNoHandphone", "jenisPemilihan", "markWitnessPemeriksaSynced", "url", "markWitnessSynced", "idPetugas", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface WitnessDao {
    void deleteAllOnline(String str);

    void deleteByNoHandphone(String str);

    void deleteLocalBy(String str, String str2, String str3, String str4, String str5);

    LiveData<List<WitnessWithShare>> getAllByKodeTps(String str);

    List<WitnessWithShare> getAllByKodeTpsSync(String str);

    LiveData<WitnessWithShare> getByNoHandphone(String str);

    WitnessWithShare getByNoHandphoneSync(String str);

    void insertAll(List<Witness> list);

    void insertAllPemeriksas(List<WitnessPemeriksa> list);

    void insertAllShares(List<WitnessShare> list);

    void markAsSharedNoHandphone(String str, String str2);

    void markWitnessPemeriksaSynced(String str, String str2, String str3);

    void markWitnessSynced(String str, long j);
}
