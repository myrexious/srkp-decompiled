package org.informatika.sirekap.db.dao;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.informatika.sirekap.model.SecurityProperties;

/* compiled from: SecurityDao.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J%\u0010\t\u001a\u00020\u00032\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u000b\"\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\fJ!\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/db/dao/SecurityDao;", "", "deleteValue", "", "key", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getValue", "Lorg/informatika/sirekap/model/SecurityProperties;", "insertValue", "securityProperties", "", "([Lorg/informatika/sirekap/model/SecurityProperties;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateValue", "value", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface SecurityDao {
    Object deleteValue(String str, Continuation<? super Unit> continuation);

    Object getValue(String str, Continuation<? super SecurityProperties> continuation);

    Object insertValue(SecurityProperties[] securityPropertiesArr, Continuation<? super Unit> continuation);

    Object updateValue(String str, String str2, Continuation<? super Unit> continuation);
}
