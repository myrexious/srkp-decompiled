package org.informatika.sirekap.ui.autocapture;

import android.app.Activity;
import android.hardware.Camera;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;

/* compiled from: ScanSurfaceView.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.autocapture.ScanSurfaceView$previewCallback$1$1", f = "ScanSurfaceView.kt", i = {0, 1, 3}, l = {313, 330, 349, 339, 349, 349}, m = "invokeSuspend", n = {"omrMat", "omrMat", "omrMat"}, s = {"L$0", "L$0", "L$0"})
/* loaded from: classes4.dex */
public final class ScanSurfaceView$previewCallback$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ Camera $camera;
    final /* synthetic */ byte[] $data;
    Object L$0;
    int label;
    final /* synthetic */ ScanSurfaceView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScanSurfaceView$previewCallback$1$1(ScanSurfaceView scanSurfaceView, Camera camera, byte[] bArr, Activity activity, Continuation<? super ScanSurfaceView$previewCallback$1$1> continuation) {
        super(2, continuation);
        this.this$0 = scanSurfaceView;
        this.$camera = camera;
        this.$data = bArr;
        this.$activity = activity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ScanSurfaceView$previewCallback$1$1(this.this$0, this.$camera, this.$data, this.$activity, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ScanSurfaceView$previewCallback$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0131 A[RETURN] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v14, types: [kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.internal.Ref$ObjectRef] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7, types: [kotlin.jvm.functions.Function2] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r6v2, types: [T, org.opencv.core.Mat] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instructions count: 420
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.autocapture.ScanSurfaceView$previewCallback$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* compiled from: ScanSurfaceView.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.autocapture.ScanSurfaceView$previewCallback$1$1$1", f = "ScanSurfaceView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.autocapture.ScanSurfaceView$previewCallback$1$1$1 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $omrOriginalPreviewArea;
        final /* synthetic */ Size $omrOriginalPreviewSize;
        final /* synthetic */ List<Point> $omrQuad;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ScanSurfaceView this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(List<? extends Point> list, ScanSurfaceView scanSurfaceView, Size size, int i, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$omrQuad = list;
            this.this$0 = scanSurfaceView;
            this.$omrOriginalPreviewSize = size;
            this.$omrOriginalPreviewArea = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$omrQuad, this.this$0, this.$omrOriginalPreviewSize, this.$omrOriginalPreviewArea, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
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
            if (CoroutineScopeKt.isActive((CoroutineScope) this.L$0)) {
                if (this.$omrQuad != null) {
                    this.this$0.clearAndInvalidateCanvas();
                    ScanSurfaceView scanSurfaceView = this.this$0;
                    List<Point> list = this.$omrQuad;
                    Size omrOriginalPreviewSize = this.$omrOriginalPreviewSize;
                    Intrinsics.checkNotNullExpressionValue(omrOriginalPreviewSize, "omrOriginalPreviewSize");
                    scanSurfaceView.drawEdgeOMR(list, omrOriginalPreviewSize, this.$omrOriginalPreviewArea);
                } else {
                    this.this$0.cancelAutoCapture();
                    this.this$0.showOmrNotDetected();
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: ScanSurfaceView.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.autocapture.ScanSurfaceView$previewCallback$1$1$2", f = "ScanSurfaceView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.autocapture.ScanSurfaceView$previewCallback$1$1$2 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ScanSurfaceView this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ScanSurfaceView scanSurfaceView, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = scanSurfaceView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (CoroutineScopeKt.isActive((CoroutineScope) this.L$0)) {
                this.this$0.cancelAutoCapture();
                this.this$0.showOmrNotDetected();
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: ScanSurfaceView.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.autocapture.ScanSurfaceView$previewCallback$1$1$3", f = "ScanSurfaceView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.autocapture.ScanSurfaceView$previewCallback$1$1$3 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ScanSurfaceView this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(ScanSurfaceView scanSurfaceView, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.this$0 = scanSurfaceView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (CoroutineScopeKt.isActive((CoroutineScope) this.L$0)) {
                this.this$0.cancelAutoCapture();
                this.this$0.lastDetectedAprilTag = new Pair(Boxing.boxInt(-1), Boxing.boxDouble(0.0d));
                this.this$0.showOmrNotDetected();
                this.this$0.showAprilTagHint();
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: ScanSurfaceView.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.autocapture.ScanSurfaceView$previewCallback$1$1$4", f = "ScanSurfaceView.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.autocapture.ScanSurfaceView$previewCallback$1$1$4 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<Mat> $omrMat;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ScanSurfaceView this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Ref.ObjectRef<Mat> objectRef, ScanSurfaceView scanSurfaceView, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$omrMat = objectRef;
            this.this$0 = scanSurfaceView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$omrMat, this.this$0, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (CoroutineScopeKt.isActive((CoroutineScope) this.L$0)) {
                Mat mat = this.$omrMat.element;
                if (mat != null) {
                    mat.release();
                }
                this.this$0.isProcessing = false;
            }
            return Unit.INSTANCE;
        }
    }
}
