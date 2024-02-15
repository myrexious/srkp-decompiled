package org.informatika.sirekap.repository;

import kotlin.Metadata;
import org.informatika.sirekap.model.UploadImageAttempt;

/* compiled from: UploadImageAttemptRepository.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J \u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H&J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/repository/UploadImageAttemptRepository;", "", "getUploadImageAttempt", "Lorg/informatika/sirekap/model/UploadImageAttempt;", "electionPageId", "", "jenisImage", "", "insertUploadImageAttempt", "", "counter", "markUploadImageAttemptAsFailed", "markUploadImageAttemptAsSuccess", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UploadImageAttemptRepository {
    UploadImageAttempt getUploadImageAttempt(String str, int i);

    void insertUploadImageAttempt(String str, int i, int i2);

    void markUploadImageAttemptAsFailed(String str);

    void markUploadImageAttemptAsFailed(String str, int i);

    void markUploadImageAttemptAsSuccess(String str);

    void markUploadImageAttemptAsSuccess(String str, int i);
}
