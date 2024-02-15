package org.informatika.sirekap.repository;

import kotlin.Metadata;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.model.UploadFileAttempt;

/* compiled from: UploadFileAttemptRepository.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J \u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/repository/UploadFileAttemptRepository;", "", "getUploadFileAttempt", "Lorg/informatika/sirekap/model/UploadFileAttempt;", JobType.ID, "", "type", "insertUploadFileAttempt", "", "counter", "", "markUploadFileAttemptAsFailed", "markUploadFileAttemptAsSuccess", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UploadFileAttemptRepository {
    UploadFileAttempt getUploadFileAttempt(String str, String str2);

    void insertUploadFileAttempt(String str, String str2, int i);

    void markUploadFileAttemptAsFailed(String str);

    void markUploadFileAttemptAsFailed(String str, String str2);

    void markUploadFileAttemptAsSuccess(String str);

    void markUploadFileAttemptAsSuccess(String str, String str2);
}
