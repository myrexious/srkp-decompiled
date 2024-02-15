package org.informatika.sirekap.usecase;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.SpecialOccurrence;
import org.informatika.sirekap.repository.SpecialOccurrenceRepository;
import org.informatika.sirekap.support.worker.uploadSpecialOccurrence.UploadSpecialOccurrenceTask;

/* compiled from: SendSpecialOccurrencePdfUseCase.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/usecase/SendSpecialOccurrencePdfUseCase;", "", "context", "Landroid/content/Context;", "uploadTask", "Lorg/informatika/sirekap/support/worker/uploadSpecialOccurrence/UploadSpecialOccurrenceTask;", "specialOccurrenceRepository", "Lorg/informatika/sirekap/repository/SpecialOccurrenceRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/support/worker/uploadSpecialOccurrence/UploadSpecialOccurrenceTask;Lorg/informatika/sirekap/repository/SpecialOccurrenceRepository;)V", "start", "", "specialOccurrence", "Lorg/informatika/sirekap/model/SpecialOccurrence;", "startUploadTask", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SendSpecialOccurrencePdfUseCase {
    private final Context context;
    private final SpecialOccurrenceRepository specialOccurrenceRepository;
    private final UploadSpecialOccurrenceTask uploadTask;

    public SendSpecialOccurrencePdfUseCase(Context context, UploadSpecialOccurrenceTask uploadTask, SpecialOccurrenceRepository specialOccurrenceRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uploadTask, "uploadTask");
        Intrinsics.checkNotNullParameter(specialOccurrenceRepository, "specialOccurrenceRepository");
        this.context = context;
        this.uploadTask = uploadTask;
        this.specialOccurrenceRepository = specialOccurrenceRepository;
    }

    public final void start(SpecialOccurrence specialOccurrence) {
        Intrinsics.checkNotNullParameter(specialOccurrence, "specialOccurrence");
        this.specialOccurrenceRepository.startUploadPdf(specialOccurrence.getKodeTps());
        startUploadTask(specialOccurrence);
    }

    private final void startUploadTask(SpecialOccurrence specialOccurrence) {
        String pdfFilePath = specialOccurrence.getPdfFilePath();
        Intrinsics.checkNotNull(pdfFilePath);
        Pair<String, String> pair = new Pair<>(pdfFilePath, specialOccurrence.getPdfFileHash());
        this.uploadTask.upload(this.context, specialOccurrence.getKodeTps(), pair, "Berkas Kejadian Khusus TPS " + specialOccurrence.getKodeTps());
    }
}
