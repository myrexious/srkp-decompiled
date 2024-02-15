package org.informatika.sirekap.ui.dashboard;

import android.graphics.Bitmap;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.support.templatematching.AprilTagConfig;
import org.informatika.sirekap.support.templatematching.ScanUtils;
import org.informatika.sirekap.ui.dashboard.AprilTagCheckUseCase;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/* compiled from: AprilTagCheckUseCase.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.dashboard.AprilTagCheckUseCase$detect$1", f = "AprilTagCheckUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class AprilTagCheckUseCase$detect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Bitmap $bitmap;
    int label;
    final /* synthetic */ AprilTagCheckUseCase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AprilTagCheckUseCase$detect$1(Bitmap bitmap, AprilTagCheckUseCase aprilTagCheckUseCase, Continuation<? super AprilTagCheckUseCase$detect$1> continuation) {
        super(2, continuation);
        this.$bitmap = bitmap;
        this.this$0 = aprilTagCheckUseCase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AprilTagCheckUseCase$detect$1(this.$bitmap, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AprilTagCheckUseCase$detect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Pair<Integer, Double> detect;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (!OpenCVLoader.initDebug()) {
            FirebaseCrashlytics.getInstance().log("AutocaptureFragment : Error loading Open CV lib");
            detect = null;
        } else {
            detect = ScanUtils.INSTANCE.detect(new Mat(this.$bitmap.getHeight(), this.$bitmap.getWidth(), CvType.CV_8UC1), this.$bitmap);
        }
        if (detect != null) {
            Pair<String, Integer> electionProfile = AprilTagConfig.INSTANCE.getElectionProfile(detect.getFirst().intValue());
            this.this$0.getDetectionResult().postValue(new AprilTagCheckUseCase.DetectResult(true, electionProfile != null ? electionProfile.getFirst() : null, electionProfile != null ? electionProfile.getSecond() : null, detect.getFirst()));
        } else {
            this.this$0.getDetectionResult().postValue(new AprilTagCheckUseCase.DetectResult(false, null, null, null, 14, null));
        }
        return Unit.INSTANCE;
    }
}
