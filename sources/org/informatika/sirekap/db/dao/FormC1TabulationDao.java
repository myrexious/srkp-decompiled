package org.informatika.sirekap.db.dao;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.informatika.sirekap.model.FormC1TabulationComplete;
import org.informatika.sirekap.model.FormC1TabulationPartai;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;

/* compiled from: FormC1TabulationDao.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\"\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\b0\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\bH'J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\bH'J\"\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\b0\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\bH'J\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\bH'J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\bH'¨\u0006\u0013"}, d2 = {"Lorg/informatika/sirekap/db/dao/FormC1TabulationDao;", "", "getByIdImage", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "idImage", "", "getListByIdImages", "", "idImages", "getListByIdImagesSync", "getListTabulationPartaiByIdImages", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "getListTabulationPartaiByIdImagesSync", "getTabulationPartaiByIdImage", "insertAllTabulationPartai", "", "forms", "Lorg/informatika/sirekap/model/FormC1TabulationPartai;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FormC1TabulationDao {
    LiveData<FormC1TabulationComplete> getByIdImage(String str);

    LiveData<List<FormC1TabulationComplete>> getListByIdImages(List<String> list);

    List<FormC1TabulationComplete> getListByIdImagesSync(List<String> list);

    LiveData<List<FormC1TabulationPartaiComplete>> getListTabulationPartaiByIdImages(List<String> list);

    List<FormC1TabulationPartaiComplete> getListTabulationPartaiByIdImagesSync(List<String> list);

    LiveData<FormC1TabulationPartaiComplete> getTabulationPartaiByIdImage(String str);

    void insertAllTabulationPartai(List<FormC1TabulationPartai> list);
}
