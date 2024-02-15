package org.informatika.sirekap.ui.sendImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.support.FileUtil;
import org.informatika.sirekap.support.SecurityUtil;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.templatematching.CheckerboardCropping;
import org.informatika.sirekap.support.templatematching.ScanUtils;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.sendImage.SendImageViewModel;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;

/* compiled from: SendImageViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.sendImage.SendImageViewModel$correctImage$1$1$1$1$1", f = "SendImageViewModel.kt", i = {}, l = {208, 247, 264}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class SendImageViewModel$correctImage$1$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Election $election;
    final /* synthetic */ Integer $oldAprilTagCode;
    final /* synthetic */ Bitmap $originalBitmap;
    final /* synthetic */ String $originalPhotoPath;
    final /* synthetic */ ElectionPage $page;
    int label;
    final /* synthetic */ SendImageViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SendImageViewModel$correctImage$1$1$1$1$1(SendImageViewModel sendImageViewModel, Election election, ElectionPage electionPage, Integer num, Bitmap bitmap, String str, Continuation<? super SendImageViewModel$correctImage$1$1$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = sendImageViewModel;
        this.$election = election;
        this.$page = electionPage;
        this.$oldAprilTagCode = num;
        this.$originalBitmap = bitmap;
        this.$originalPhotoPath = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SendImageViewModel$correctImage$1$1$1$1$1(this.this$0, this.$election, this.$page, this.$oldAprilTagCode, this.$originalBitmap, this.$originalPhotoPath, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SendImageViewModel$correctImage$1$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:61:0x0196 -> B:65:0x0199). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        MutableLiveData mutableLiveData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Exception e) {
            this.label = 3;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass3(e, this.this$0, this.$election, this.$page, this.$oldAprilTagCode, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!OpenCVLoader.initDebug()) {
                FirebaseCrashlytics.getInstance().log("AutocaptureFragment : Error loading Open CV lib");
                mutableLiveData = this.this$0.perspectiveCorrectionModel;
                mutableLiveData.postValue(new SendImageViewModel.ElectionPagePerspectiveCorrectionModel(this.$election.getId(), this.$page.getId(), null, "", null, this.$oldAprilTagCode));
            } else {
                Mat mat = new Mat(this.$originalBitmap.getHeight(), this.$originalBitmap.getWidth(), CvType.CV_8UC1);
                Utils.bitmapToMat(this.$originalBitmap, mat);
                double cols = mat.cols();
                double rows = mat.rows();
                ScanUtils scanUtils = ScanUtils.INSTANCE;
                Bitmap originalBitmap = this.$originalBitmap;
                Intrinsics.checkNotNullExpressionValue(originalBitmap, "originalBitmap");
                Pair<Integer, Double> detect = scanUtils.detect(mat, originalBitmap);
                if (detect != null) {
                    int roundToInt = MathKt.roundToInt(detect.getSecond().doubleValue() / 2.259d);
                    Mat convertToGrayScale = ScanUtils.INSTANCE.convertToGrayScale(mat);
                    ScanUtils scanUtils2 = ScanUtils.INSTANCE;
                    context = this.this$0.context;
                    List<Point> matchCheckerboard = scanUtils2.matchCheckerboard(convertToGrayScale, roundToInt, roundToInt, context, mat, false);
                    convertToGrayScale.release();
                    Log.wtf(SendImageViewModel.TAG, "April Tag ID : " + detect.getFirst());
                    Log.wtf(SendImageViewModel.TAG, "April Tag Size : " + detect.getSecond());
                    Log.wtf(SendImageViewModel.TAG, "Crop Locations : " + matchCheckerboard);
                    Mat cropCoordinate = CheckerboardCropping.cropCoordinate(mat, matchCheckerboard, Boxing.boxDouble(cols / rows));
                    mat.release();
                    Bitmap createBitmap = Bitmap.createBitmap(cropCoordinate.cols(), cropCoordinate.rows(), Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(cropCoordinate, createBitmap);
                    cropCoordinate.release();
                    this.label = 1;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.this$0, this.$originalPhotoPath, createBitmap, this.$election, this.$page, detect, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    mat.release();
                    this.label = 2;
                    if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.this$0, this.$election, this.$page, this.$oldAprilTagCode, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
        } else if (i != 1 && i != 2) {
            if (i == 3) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* compiled from: SendImageViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.sendImage.SendImageViewModel$correctImage$1$1$1$1$1$1", f = "SendImageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.sendImage.SendImageViewModel$correctImage$1$1$1$1$1$1 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Pair<Integer, Double> $aprilTag;
        final /* synthetic */ Bitmap $correctedBitmap;
        final /* synthetic */ Election $election;
        final /* synthetic */ String $originalPhotoPath;
        final /* synthetic */ ElectionPage $page;
        int label;
        final /* synthetic */ SendImageViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SendImageViewModel sendImageViewModel, String str, Bitmap bitmap, Election election, ElectionPage electionPage, Pair<Integer, Double> pair, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = sendImageViewModel;
            this.$originalPhotoPath = str;
            this.$correctedBitmap = bitmap;
            this.$election = election;
            this.$page = electionPage;
            this.$aprilTag = pair;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$originalPhotoPath, this.$correctedBitmap, this.$election, this.$page, this.$aprilTag, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Context context;
            EncryptedSharedPreferences encryptedSharedPreferences;
            String absolutePath;
            Context context2;
            MutableLiveData mutableLiveData;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                context = this.this$0.context;
                File file = new File(this.$originalPhotoPath);
                Bitmap correctedBitmap = this.$correctedBitmap;
                Intrinsics.checkNotNullExpressionValue(correctedBitmap, "correctedBitmap");
                Bitmap resizeImage$default = FileUtil.resizeImage$default(correctedBitmap, 0, 2, null);
                String pemilihan = this.$election.getPemilihan();
                String kodeTps = this.$election.getTps().getKodeTps();
                encryptedSharedPreferences = this.this$0.encryptedSharedPreferences;
                File saveCorrectedImage = FileUtil.saveCorrectedImage(context, file, resizeImage$default, pemilihan, kodeTps, encryptedSharedPreferences);
                if (saveCorrectedImage == null || (absolutePath = saveCorrectedImage.getAbsolutePath()) == null) {
                    return null;
                }
                SendImageViewModel sendImageViewModel = this.this$0;
                Election election = this.$election;
                ElectionPage electionPage = this.$page;
                Pair<Integer, Double> pair = this.$aprilTag;
                String hashDocument = new SecurityUtil().hashDocument(absolutePath);
                SecurityFacade securityFacade = SecurityFacade.INSTANCE;
                context2 = sendImageViewModel.context;
                SecurityFacade.INSTANCE.signJpgImage(securityFacade.buildKeystoreManager(context2), new File(absolutePath));
                mutableLiveData = sendImageViewModel.perspectiveCorrectionModel;
                mutableLiveData.postValue(new SendImageViewModel.ElectionPagePerspectiveCorrectionModel(election.getId(), electionPage.getId(), absolutePath, "", hashDocument, pair.getFirst()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: SendImageViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.sendImage.SendImageViewModel$correctImage$1$1$1$1$1$2", f = "SendImageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.sendImage.SendImageViewModel$correctImage$1$1$1$1$1$2 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Election $election;
        final /* synthetic */ Integer $oldAprilTagCode;
        final /* synthetic */ ElectionPage $page;
        int label;
        final /* synthetic */ SendImageViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(SendImageViewModel sendImageViewModel, Election election, ElectionPage electionPage, Integer num, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = sendImageViewModel;
            this.$election = election;
            this.$page = electionPage;
            this.$oldAprilTagCode = num;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$election, this.$page, this.$oldAprilTagCode, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableLiveData mutableLiveData;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FirebaseCrashlytics.getInstance().log("SendImageViewModel: April Tag Not Detected");
            mutableLiveData = this.this$0.perspectiveCorrectionModel;
            mutableLiveData.postValue(new SendImageViewModel.ElectionPagePerspectiveCorrectionModel(this.$election.getId(), this.$page.getId(), null, "", null, this.$oldAprilTagCode));
            return Unit.INSTANCE;
        }
    }

    /* compiled from: SendImageViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.sendImage.SendImageViewModel$correctImage$1$1$1$1$1$3", f = "SendImageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.sendImage.SendImageViewModel$correctImage$1$1$1$1$1$3 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Exception $e;
        final /* synthetic */ Election $election;
        final /* synthetic */ Integer $oldAprilTagCode;
        final /* synthetic */ ElectionPage $page;
        int label;
        final /* synthetic */ SendImageViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Exception exc, SendImageViewModel sendImageViewModel, Election election, ElectionPage electionPage, Integer num, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$e = exc;
            this.this$0 = sendImageViewModel;
            this.$election = election;
            this.$page = electionPage;
            this.$oldAprilTagCode = num;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.$e, this.this$0, this.$election, this.$page, this.$oldAprilTagCode, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableLiveData mutableLiveData;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FirebaseCrashlytics.getInstance().recordException(this.$e);
            mutableLiveData = this.this$0.perspectiveCorrectionModel;
            mutableLiveData.postValue(new SendImageViewModel.ElectionPagePerspectiveCorrectionModel(this.$election.getId(), this.$page.getId(), null, "", null, this.$oldAprilTagCode));
            return Unit.INSTANCE;
        }
    }
}
