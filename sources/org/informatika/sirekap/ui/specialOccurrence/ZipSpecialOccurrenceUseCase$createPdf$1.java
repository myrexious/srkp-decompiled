package org.informatika.sirekap.ui.specialOccurrence;

import android.content.Context;
import android.widget.Toast;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.UserInformation;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.support.SecurityUtil;
import org.informatika.sirekap.support.pdf.PdfFacade;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.exceptions.ImageValidationException;
import org.informatika.sirekap.support.security.keystore.KeystoreManager;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.ui.specialOccurrence.ZipSpecialOccurrenceUseCase;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* compiled from: ZipSpecialOccurrenceUseCase.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.specialOccurrence.ZipSpecialOccurrenceUseCase$createPdf$1", f = "ZipSpecialOccurrenceUseCase.kt", i = {0, 0, 1, 1}, l = {107, 110}, m = "invokeSuspend", n = {"pdfFile", "pdfFileHash", "pdfFile", "pdfFileHash"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes4.dex */
final class ZipSpecialOccurrenceUseCase$createPdf$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AppDatabase $appDatabase;
    final /* synthetic */ Context $context;
    final /* synthetic */ List<String> $images;
    final /* synthetic */ String $kodeTps;
    final /* synthetic */ List<Integer> $pages;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ZipSpecialOccurrenceUseCase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZipSpecialOccurrenceUseCase$createPdf$1(Context context, ZipSpecialOccurrenceUseCase zipSpecialOccurrenceUseCase, String str, List<String> list, List<Integer> list2, AppDatabase appDatabase, Continuation<? super ZipSpecialOccurrenceUseCase$createPdf$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.this$0 = zipSpecialOccurrenceUseCase;
        this.$kodeTps = str;
        this.$images = list;
        this.$pages = list2;
        this.$appDatabase = appDatabase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZipSpecialOccurrenceUseCase$createPdf$1(this.$context, this.this$0, this.$kodeTps, this.$images, this.$pages, this.$appDatabase, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ZipSpecialOccurrenceUseCase$createPdf$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        File file;
        File file2;
        ElectionRepository electionRepository;
        AuthRepository authRepository;
        AuthRequestUseCase authRequestUseCase;
        PdfLtv pdfLtv;
        String str2 = "Gambar pada halaman ";
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            try {
            } catch (Exception e) {
                str = str2;
                file = file2;
                FirebaseCrashlytics.getInstance().recordException(e);
                this.L$0 = file;
                this.L$1 = str;
                this.label = 2;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.$context, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message != null && StringsKt.contains$default((CharSequence) message, (CharSequence) "jwt", false, 2, (Object) null)) {
                this.this$0.getShouldRelogin().postValue(Boxing.boxBoolean(true));
                this.this$0.getResult().postValue(new ZipSpecialOccurrenceUseCase.CreatePdfModel(false, e2.getMessage()));
                return Unit.INSTANCE;
            }
            this.this$0.getResult().postValue(new ZipSpecialOccurrenceUseCase.CreatePdfModel(false, e2.getMessage()));
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            KeystoreManager buildKeystoreManager = SecurityFacade.INSTANCE.buildKeystoreManager(this.$context);
            this.this$0.deleteCurrentDocuments(this.$context, this.$kodeTps);
            file2 = this.this$0.getPdfFile(this.$context, this.$kodeTps);
            if (file2.getAbsolutePath() != null) {
                try {
                    SecurityFacade.INSTANCE.validateImages(this.$context, this.$images);
                    List<ElectionWithRelation> allByKodeTpsSync = this.$appDatabase.electionDao().getAllByKodeTpsSync(this.$kodeTps);
                    PdfFacade pdfFacade = PdfFacade.INSTANCE;
                    Tps tps = ((ElectionWithRelation) CollectionsKt.first((List<? extends Object>) allByKodeTpsSync)).getElection().getTps();
                    ArrayList<String> arrayList = new ArrayList<>(this.$images);
                    authRepository = this.this$0.authRepository;
                    UserInformation userInformation = authRepository.getUserInformation();
                    pdfFacade.generatePdfKejadianKhusus(this.$context, file2, arrayList, buildKeystoreManager, tps, (userInformation == null || (r2 = userInformation.getName()) == null) ? "Tidak Diketahui" : "Tidak Diketahui");
                    SecurityUtil securityUtil = new SecurityUtil();
                    String absolutePath = file2.getAbsolutePath();
                    Intrinsics.checkNotNullExpressionValue(absolutePath, "pdfFile.absolutePath");
                    str2 = securityUtil.hashDocument(absolutePath);
                    SecurityFacade.INSTANCE.signPdf(buildKeystoreManager, file2);
                    SecurityFacade securityFacade = SecurityFacade.INSTANCE;
                    Context context = this.$context;
                    authRequestUseCase = this.this$0.authRequestUseCase;
                    pdfLtv = this.this$0.pdfLtv;
                    this.L$0 = file2;
                    this.L$1 = str2;
                    this.label = 1;
                    if (securityFacade.addPdfVerificationInfo(context, authRequestUseCase, pdfLtv, file2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } catch (ImageValidationException e3) {
                    this.this$0.getResult().postValue(new ZipSpecialOccurrenceUseCase.CreatePdfModel(false, "Gambar pada halaman " + this.$pages.get(e3.getIndex()) + " tidak valid. Silakan ulangi pengambilan foto halaman tersebut."));
                    this.this$0.isLoading().postValue(Boxing.boxBoolean(false));
                    return Unit.INSTANCE;
                }
            } else {
                this.this$0.getResult().postValue(new ZipSpecialOccurrenceUseCase.CreatePdfModel(false, "file pdf = null"));
                this.this$0.isLoading().postValue(Boxing.boxBoolean(false));
                return Unit.INSTANCE;
            }
        } else if (i != 1) {
            if (i == 2) {
                str = (String) this.L$1;
                file = (File) this.L$0;
                ResultKt.throwOnFailure(obj);
                file2 = file;
                str2 = str;
                electionRepository = this.this$0.electionRepository;
                String str3 = this.$kodeTps;
                String absolutePath2 = file2.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath2, "pdfFile.absolutePath");
                electionRepository.finishSpecialOccurrenceCreatePdf(str3, absolutePath2, str2);
                this.this$0.getResult().postValue(new ZipSpecialOccurrenceUseCase.CreatePdfModel(true, null, 2, null));
                this.this$0.isLoading().postValue(Boxing.boxBoolean(false));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            str2 = (String) this.L$1;
            file2 = (File) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        electionRepository = this.this$0.electionRepository;
        String str32 = this.$kodeTps;
        String absolutePath22 = file2.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath22, "pdfFile.absolutePath");
        electionRepository.finishSpecialOccurrenceCreatePdf(str32, absolutePath22, str2);
        this.this$0.getResult().postValue(new ZipSpecialOccurrenceUseCase.CreatePdfModel(true, null, 2, null));
        this.this$0.isLoading().postValue(Boxing.boxBoolean(false));
        return Unit.INSTANCE;
    }

    /* compiled from: ZipSpecialOccurrenceUseCase.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.specialOccurrence.ZipSpecialOccurrenceUseCase$createPdf$1$1", f = "ZipSpecialOccurrenceUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.specialOccurrence.ZipSpecialOccurrenceUseCase$createPdf$1$1 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Toast.makeText(this.$context, "Saat ini PDF belum ditambahkan informasi verifikasi", 1).show();
            return Unit.INSTANCE;
        }
    }
}
