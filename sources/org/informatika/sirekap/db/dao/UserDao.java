package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.User;

/* compiled from: UserDao.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH'J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH'J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\tH'¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/db/dao/UserDao;", "", "deleteAll", "", "deleteById", JobType.ID, "", "getAll", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/User;", "getAllSync", "getById", "insertAll", "users", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UserDao {
    void deleteAll();

    void deleteById(String str);

    LiveData<List<User>> getAll();

    List<User> getAllSync();

    LiveData<User> getById(String str);

    void insertAll(List<User> list);
}
