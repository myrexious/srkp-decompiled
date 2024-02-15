package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.support.Resource;

/* compiled from: UserRepository.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u00040\u0003H&J\u001e\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00040\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lorg/informatika/sirekap/repository/UserRepository;", "", "getAll", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "", "Lorg/informatika/sirekap/model/User;", "getUserById", JobType.ID, "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UserRepository {
    LiveData<Resource<List<User>>> getAll();

    LiveData<Resource<User>> getUserById(String str);
}
