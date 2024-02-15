package org.informatika.sirekap.di;

import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;
import kotlin.Metadata;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultBackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository;
import org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository;
import org.informatika.sirekap.repository.UploadFileAttemptRepository;
import org.informatika.sirekap.repository.UploadImageAttemptRepository;
import org.informatika.sirekap.support.worker.login.DefaultLoginTask;
import org.informatika.sirekap.support.worker.login.LoginTask;
import org.informatika.sirekap.support.worker.refreshToken.DefaultRefreshTokenTask;
import org.informatika.sirekap.support.worker.refreshToken.RefreshTokenTask;
import org.informatika.sirekap.support.worker.registerWitness.DefaultRegisterWitnessTask;
import org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessTask;
import org.informatika.sirekap.support.worker.security.CertificateCheckerTask;
import org.informatika.sirekap.support.worker.security.DefaultCertificateCheckerTask;
import org.informatika.sirekap.support.worker.uploadAttendancePdf.DefaultUploadAttendanceTask;
import org.informatika.sirekap.support.worker.uploadAttendancePdf.UploadAttendancePdfTask;
import org.informatika.sirekap.support.worker.uploadFormCImage.DefaultUploadFormCImageTask;
import org.informatika.sirekap.support.worker.uploadFormCImage.FakeUploadFormCImageTask;
import org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageTask;
import org.informatika.sirekap.support.worker.uploadFormCImageRekap.DefaultUploadFormCImageRekapTask;
import org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapTask;
import org.informatika.sirekap.support.worker.uploadSpecialOccurrence.DefaultUploadSpecialOccurrenceTask;
import org.informatika.sirekap.support.worker.uploadSpecialOccurrence.UploadSpecialOccurrenceTask;
import org.informatika.sirekap.support.worker.zipAttendance.DefaultZipAttendanceTask;
import org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceTask;
import org.informatika.sirekap.support.worker.zipElection.DefaultZipElectionTask;
import org.informatika.sirekap.support.worker.zipElection.ZipElectionTask;
import org.informatika.sirekap.support.worker.zipSpecialOccurrence.DefaultZipSpecialOccurrenceTask;
import org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceTask;

/* compiled from: WorkerModule.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH'J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH'J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H'J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0015H'J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0018H'J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u001bH'J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH'J\u0010\u0010 \u001a\u00020!2\u0006\u0010\r\u001a\u00020\"H'J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020%H'J\u0010\u0010&\u001a\u00020'2\u0006\u0010\r\u001a\u00020(H'J\u0010\u0010)\u001a\u00020\f2\u0006\u0010\r\u001a\u00020*H'J\u0010\u0010+\u001a\u00020,2\u0006\u0010\u0011\u001a\u00020-H'J\u0010\u0010.\u001a\u00020/2\u0006\u0010\u0011\u001a\u000200H'J\u0010\u00101\u001a\u0002022\u0006\u0010\u0011\u001a\u000203H'¨\u00064"}, d2 = {"Lorg/informatika/sirekap/di/WorkerModule;", "", "()V", "bindBackgroundProcessRepository", "Lorg/informatika/sirekap/repository/BackgroundProcessRepository;", "backgroundProcessRepository", "Lorg/informatika/sirekap/repository/DefaultBackgroundProcessRepository;", "bindCertificateCheckerTask", "Lorg/informatika/sirekap/support/worker/security/CertificateCheckerTask;", "certificateChecker", "Lorg/informatika/sirekap/support/worker/security/DefaultCertificateCheckerTask;", "bindFakeUploadTask", "Lorg/informatika/sirekap/support/worker/uploadFormCImage/UploadFormCImageTask;", "uploadTask", "Lorg/informatika/sirekap/support/worker/uploadFormCImage/FakeUploadFormCImageTask;", "bindLoginTask", "Lorg/informatika/sirekap/support/worker/login/LoginTask;", "loginTask", "Lorg/informatika/sirekap/support/worker/login/DefaultLoginTask;", "bindRefreshTokenTask", "Lorg/informatika/sirekap/support/worker/refreshToken/RefreshTokenTask;", "Lorg/informatika/sirekap/support/worker/refreshToken/DefaultRefreshTokenTask;", "bindRegisterWitnessTask", "Lorg/informatika/sirekap/support/worker/registerWitness/RegisterWitnessTask;", "Lorg/informatika/sirekap/support/worker/registerWitness/DefaultRegisterWitnessTask;", "bindUploadAttendancePdfTask", "Lorg/informatika/sirekap/support/worker/uploadAttendancePdf/UploadAttendancePdfTask;", "Lorg/informatika/sirekap/support/worker/uploadAttendancePdf/DefaultUploadAttendanceTask;", "bindUploadFileAttemptRepository", "Lorg/informatika/sirekap/repository/UploadFileAttemptRepository;", "repository", "Lorg/informatika/sirekap/repository/DefaultUploadFileAttemptRepository;", "bindUploadFormCImageRekapTask", "Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/UploadFormCImageRekapTask;", "Lorg/informatika/sirekap/support/worker/uploadFormCImageRekap/DefaultUploadFormCImageRekapTask;", "bindUploadImageAttemptRepository", "Lorg/informatika/sirekap/repository/UploadImageAttemptRepository;", "Lorg/informatika/sirekap/repository/DefaultUploadImageAttemptRepository;", "bindUploadSpecialOccurrenceTask", "Lorg/informatika/sirekap/support/worker/uploadSpecialOccurrence/UploadSpecialOccurrenceTask;", "Lorg/informatika/sirekap/support/worker/uploadSpecialOccurrence/DefaultUploadSpecialOccurrenceTask;", "bindUploadTask", "Lorg/informatika/sirekap/support/worker/uploadFormCImage/DefaultUploadFormCImageTask;", "bindZipAttendanceTask", "Lorg/informatika/sirekap/support/worker/zipAttendance/ZipAttendanceTask;", "Lorg/informatika/sirekap/support/worker/zipAttendance/DefaultZipAttendanceTask;", "bindZipElectionTask", "Lorg/informatika/sirekap/support/worker/zipElection/ZipElectionTask;", "Lorg/informatika/sirekap/support/worker/zipElection/DefaultZipElectionTask;", "bindZipSpecialOccurrenceTask", "Lorg/informatika/sirekap/support/worker/zipSpecialOccurrence/ZipSpecialOccurrenceTask;", "Lorg/informatika/sirekap/support/worker/zipSpecialOccurrence/DefaultZipSpecialOccurrenceTask;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Module
/* loaded from: classes2.dex */
public abstract class WorkerModule {
    @Singleton
    @Binds
    public abstract BackgroundProcessRepository bindBackgroundProcessRepository(DefaultBackgroundProcessRepository defaultBackgroundProcessRepository);

    @Singleton
    @Binds
    public abstract CertificateCheckerTask bindCertificateCheckerTask(DefaultCertificateCheckerTask defaultCertificateCheckerTask);

    @Singleton
    @Binds
    public abstract UploadFormCImageTask bindFakeUploadTask(FakeUploadFormCImageTask fakeUploadFormCImageTask);

    @Singleton
    @Binds
    public abstract LoginTask bindLoginTask(DefaultLoginTask defaultLoginTask);

    @Singleton
    @Binds
    public abstract RefreshTokenTask bindRefreshTokenTask(DefaultRefreshTokenTask defaultRefreshTokenTask);

    @Singleton
    @Binds
    public abstract RegisterWitnessTask bindRegisterWitnessTask(DefaultRegisterWitnessTask defaultRegisterWitnessTask);

    @Singleton
    @Binds
    public abstract UploadAttendancePdfTask bindUploadAttendancePdfTask(DefaultUploadAttendanceTask defaultUploadAttendanceTask);

    @Singleton
    @Binds
    public abstract UploadFileAttemptRepository bindUploadFileAttemptRepository(DefaultUploadFileAttemptRepository defaultUploadFileAttemptRepository);

    @Singleton
    @Binds
    public abstract UploadFormCImageRekapTask bindUploadFormCImageRekapTask(DefaultUploadFormCImageRekapTask defaultUploadFormCImageRekapTask);

    @Singleton
    @Binds
    public abstract UploadImageAttemptRepository bindUploadImageAttemptRepository(DefaultUploadImageAttemptRepository defaultUploadImageAttemptRepository);

    @Singleton
    @Binds
    public abstract UploadSpecialOccurrenceTask bindUploadSpecialOccurrenceTask(DefaultUploadSpecialOccurrenceTask defaultUploadSpecialOccurrenceTask);

    @Singleton
    @Binds
    public abstract UploadFormCImageTask bindUploadTask(DefaultUploadFormCImageTask defaultUploadFormCImageTask);

    @Singleton
    @Binds
    public abstract ZipAttendanceTask bindZipAttendanceTask(DefaultZipAttendanceTask defaultZipAttendanceTask);

    @Singleton
    @Binds
    public abstract ZipElectionTask bindZipElectionTask(DefaultZipElectionTask defaultZipElectionTask);

    @Singleton
    @Binds
    public abstract ZipSpecialOccurrenceTask bindZipSpecialOccurrenceTask(DefaultZipSpecialOccurrenceTask defaultZipSpecialOccurrenceTask);
}
