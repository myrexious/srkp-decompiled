package org.informatika.sirekap.usecase;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Attendance;
import org.informatika.sirekap.repository.WitnessRepository;
import org.informatika.sirekap.support.worker.uploadAttendancePdf.UploadAttendancePdfTask;

/* compiled from: SendAttendancePdfUseCase.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/usecase/SendAttendancePdfUseCase;", "", "context", "Landroid/content/Context;", "uploadAttendancePdfTask", "Lorg/informatika/sirekap/support/worker/uploadAttendancePdf/UploadAttendancePdfTask;", "witnessRepository", "Lorg/informatika/sirekap/repository/WitnessRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/support/worker/uploadAttendancePdf/UploadAttendancePdfTask;Lorg/informatika/sirekap/repository/WitnessRepository;)V", "start", "", "attendance", "Lorg/informatika/sirekap/model/Attendance;", "startUploadTask", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SendAttendancePdfUseCase {
    private final Context context;
    private final UploadAttendancePdfTask uploadAttendancePdfTask;
    private final WitnessRepository witnessRepository;

    public SendAttendancePdfUseCase(Context context, UploadAttendancePdfTask uploadAttendancePdfTask, WitnessRepository witnessRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uploadAttendancePdfTask, "uploadAttendancePdfTask");
        Intrinsics.checkNotNullParameter(witnessRepository, "witnessRepository");
        this.context = context;
        this.uploadAttendancePdfTask = uploadAttendancePdfTask;
        this.witnessRepository = witnessRepository;
    }

    public final void start(Attendance attendance) {
        Intrinsics.checkNotNullParameter(attendance, "attendance");
        this.witnessRepository.startUploadPdf(attendance.getKodeTps());
        startUploadTask(attendance);
    }

    private final void startUploadTask(Attendance attendance) {
        String pdfFilePath = attendance.getPdfFilePath();
        Intrinsics.checkNotNull(pdfFilePath);
        Pair<String, String> pair = new Pair<>(pdfFilePath, attendance.getPdfFileHash());
        this.uploadAttendancePdfTask.upload(this.context, attendance.getKodeTps(), pair, "Berkas Daftar Hadir TPS " + attendance.getKodeTps());
    }
}
