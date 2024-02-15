package org.informatika.sirekap.usecase;

import android.content.Context;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageTask;

/* compiled from: SendFormCImageUseCase.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/usecase/SendFormCImageUseCase;", "", "context", "Landroid/content/Context;", "uploadTask", "Lorg/informatika/sirekap/support/worker/uploadFormCImage/UploadFormCImageTask;", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/support/worker/uploadFormCImage/UploadFormCImageTask;Lorg/informatika/sirekap/repository/ElectionRepository;)V", "start", "", "electionPageWithRelation", "Lorg/informatika/sirekap/model/ElectionPageWithRelation;", "isOffline", "", "startUploadTask", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SendFormCImageUseCase {
    private final Context context;
    private final ElectionRepository electionRepository;
    private final UploadFormCImageTask uploadTask;

    public SendFormCImageUseCase(Context context, UploadFormCImageTask uploadTask, ElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uploadTask, "uploadTask");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.context = context;
        this.uploadTask = uploadTask;
        this.electionRepository = electionRepository;
    }

    public final void start(ElectionPageWithRelation electionPageWithRelation, boolean z) {
        Intrinsics.checkNotNullParameter(electionPageWithRelation, "electionPageWithRelation");
        startUploadTask(electionPageWithRelation, z);
        this.electionRepository.startElectionPageSend(electionPageWithRelation.getElection().getId(), electionPageWithRelation.getElectionPage().getId());
    }

    private final void startUploadTask(ElectionPageWithRelation electionPageWithRelation, boolean z) {
        Map<Integer, Pair<String, String>> mutableMapOf;
        ElectionPage electionPage = electionPageWithRelation.getElectionPage();
        int kind = electionPage.getKind();
        if (kind == 10) {
            mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to(10, new Pair(electionPage.getCroppedPhotoPath(), electionPage.getHashDocumentCropped())));
        } else if (kind == 20) {
            mutableMapOf = MapsKt.mapOf(TuplesKt.to(20, new Pair(electionPage.getCroppedPhotoPath(), electionPage.getHashDocumentCropped())));
        } else {
            mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to(30, new Pair(electionPage.getCroppedPhotoPath(), electionPage.getHashDocumentCropped())));
        }
        Map<Integer, Pair<String, String>> map = mutableMapOf;
        UploadFormCImageTask uploadFormCImageTask = this.uploadTask;
        Context context = this.context;
        String id2 = electionPage.getId();
        String kodeTps = electionPageWithRelation.getElection().getTps().getKodeTps();
        int number = electionPage.getNumber();
        String pemilihan = electionPageWithRelation.getElection().getPemilihan();
        String imageDescription = electionPageWithRelation.getImageDescription(this.context);
        String signatureCroppedPhoto = electionPage.getSignatureCroppedPhoto();
        if (signatureCroppedPhoto == null) {
            signatureCroppedPhoto = "";
        }
        String correctedPhotoPath = electionPage.getCorrectedPhotoPath();
        uploadFormCImageTask.upload(context, id2, map, kodeTps, number, pemilihan, imageDescription, true, signatureCroppedPhoto, correctedPhotoPath != null ? correctedPhotoPath : "", electionPageWithRelation.getElection().isLn(), electionPageWithRelation.getElection().isLnPos(), z);
    }
}
