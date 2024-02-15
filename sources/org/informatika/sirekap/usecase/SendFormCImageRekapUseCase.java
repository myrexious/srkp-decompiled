package org.informatika.sirekap.usecase;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapTask;

/* compiled from: SendFormCImageRekapUseCase.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/usecase/SendFormCImageRekapUseCase;", "", "context", "Landroid/content/Context;", "uploadFormC1ImageRekapTask", "Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/UploadFormCImageRekapTask;", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/UploadFormCImageRekapTask;Lorg/informatika/sirekap/repository/ElectionRepository;)V", "start", "", "election", "Lorg/informatika/sirekap/model/Election;", "isOffline", "", "startUploadTask", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SendFormCImageRekapUseCase {
    private final Context context;
    private final ElectionRepository electionRepository;
    private final UploadFormCImageRekapTask uploadFormC1ImageRekapTask;

    public SendFormCImageRekapUseCase(Context context, UploadFormCImageRekapTask uploadFormC1ImageRekapTask, ElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uploadFormC1ImageRekapTask, "uploadFormC1ImageRekapTask");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.context = context;
        this.uploadFormC1ImageRekapTask = uploadFormC1ImageRekapTask;
        this.electionRepository = electionRepository;
    }

    public final void start(Election election, boolean z) {
        Intrinsics.checkNotNullParameter(election, "election");
        startUploadTask(election, z);
        this.electionRepository.startUploadPdf(election.getId());
    }

    private final void startUploadTask(Election election, boolean z) {
        String pdfFilePath = election.getPdfFilePath();
        Intrinsics.checkNotNull(pdfFilePath);
        Pair<String, String> pair = new Pair<>(pdfFilePath, election.getPdfFileHash());
        UploadFormCImageRekapTask uploadFormCImageRekapTask = this.uploadFormC1ImageRekapTask;
        Context context = this.context;
        String id2 = election.getId();
        String pemilihan = election.getPemilihan();
        String kodeTps = election.getTps().getKodeTps();
        String str = "Berkas Salinan C.Hasil " + election.getDescription(this.context);
        String pDFSignature = SecurityFacade.INSTANCE.getPDFSignature(new File(election.getPdfFilePath()));
        if (pDFSignature == null) {
            pDFSignature = "";
        }
        uploadFormCImageRekapTask.upload(context, id2, pemilihan, pair, kodeTps, str, pDFSignature, z);
    }
}
