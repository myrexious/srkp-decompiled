package org.informatika.sirekap.ui.autocapture;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.apache.xmpbox.type.JobType;
import org.apache.xmpbox.type.ThumbnailType;
import org.informatika.sirekap.support.templatematching.AprilTagConfig;
import org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel;

/* compiled from: AutoCaptureViewModel.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 /2\u00020\u0001:\u0002/0B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001b\u001a\u00020\u0007J\u001e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!J\u000e\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u0007J\u000e\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020!J\u000e\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)J\u001e\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020,R\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\tR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001f\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00070\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\tR\u001f\u0010\u0017\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00070\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\tR\u001f\u0010\u0019\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00070\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\t¨\u00061"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/AutoCaptureViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "aprilTagValid", "Landroidx/lifecycle/MutableLiveData;", "", "getAprilTagValid", "()Landroidx/lifecycle/MutableLiveData;", "hintAprilTag", "", "getHintAprilTag", "hintText", "getHintText", "hintValid", "getHintValid", "imageProcessingUseCase", "Lorg/informatika/sirekap/ui/autocapture/ImageProcessingUseCase;", "getImageProcessingUseCase", "()Lorg/informatika/sirekap/ui/autocapture/ImageProcessingUseCase;", "isProcessingImage", "kotlin.jvm.PlatformType", "shutterShown", "getShutterShown", "useAutoCapture", "getUseAutoCapture", "isAutoCapture", "processImage", "", "bitmap", "Landroid/graphics/Bitmap;", ThumbnailType.WIDTH, "", ThumbnailType.HEIGHT, "setEnableShutter", "enable", "setHintAprilTag", JobType.ID, "setHintOmr", "scanHint", "Lorg/informatika/sirekap/ui/autocapture/AutoCaptureViewModel$ScanHint;", "setup", "imageUri", "Landroid/net/Uri;", "croppedImageUri", "correctedImageUri", "Companion", "ScanHint", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AutoCaptureViewModel extends ViewModel {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "autocapture_viewmodel";
    private final MutableLiveData<Boolean> aprilTagValid;
    private final MutableLiveData<String> hintAprilTag;
    private final MutableLiveData<String> hintText;
    private final MutableLiveData<Boolean> hintValid;
    private final ImageProcessingUseCase imageProcessingUseCase;
    private final MutableLiveData<Boolean> isProcessingImage;
    private final MutableLiveData<Boolean> shutterShown;
    private final MutableLiveData<Boolean> useAutoCapture;

    /* compiled from: AutoCaptureViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/AutoCaptureViewModel$ScanHint;", "", "(Ljava/lang/String;I)V", "MOVE_AWAY", "MOVE_CLOSER", "DO_NOT_MOVE", "ADJUST_ANGLE", "OMR_EDGE_NOT_DETECTED", "OMR_EDGE_DETECTED", "CAPTURING_IMAGE", "NO_MESSAGE", "PREPARING_TO_CAPTURE", "PROCESSING_IMAGE", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public enum ScanHint {
        MOVE_AWAY,
        MOVE_CLOSER,
        DO_NOT_MOVE,
        ADJUST_ANGLE,
        OMR_EDGE_NOT_DETECTED,
        OMR_EDGE_DETECTED,
        CAPTURING_IMAGE,
        NO_MESSAGE,
        PREPARING_TO_CAPTURE,
        PROCESSING_IMAGE
    }

    /* compiled from: AutoCaptureViewModel.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScanHint.values().length];
            try {
                iArr[ScanHint.MOVE_CLOSER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ScanHint.MOVE_AWAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ScanHint.ADJUST_ANGLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ScanHint.DO_NOT_MOVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ScanHint.OMR_EDGE_NOT_DETECTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ScanHint.OMR_EDGE_DETECTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ScanHint.CAPTURING_IMAGE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ScanHint.PREPARING_TO_CAPTURE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ScanHint.PROCESSING_IMAGE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ScanHint.NO_MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public AutoCaptureViewModel(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.imageProcessingUseCase = new ImageProcessingUseCase(context, new Function1<ScanHint, Unit>() { // from class: org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel$imageProcessingUseCase$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AutoCaptureViewModel.ScanHint scanHint) {
                invoke2(scanHint);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(AutoCaptureViewModel.ScanHint it) {
                Intrinsics.checkNotNullParameter(it, "it");
                AutoCaptureViewModel.this.setHintOmr(it);
            }
        });
        this.hintText = new MutableLiveData<>(null);
        this.hintValid = new MutableLiveData<>(null);
        this.aprilTagValid = new MutableLiveData<>(null);
        this.hintAprilTag = new MutableLiveData<>(null);
        this.shutterShown = new MutableLiveData<>(true);
        this.isProcessingImage = new MutableLiveData<>(false);
        this.useAutoCapture = new MutableLiveData<>(false);
    }

    public final ImageProcessingUseCase getImageProcessingUseCase() {
        return this.imageProcessingUseCase;
    }

    public final void setup(Uri imageUri, Uri croppedImageUri, Uri correctedImageUri) {
        Intrinsics.checkNotNullParameter(imageUri, "imageUri");
        Intrinsics.checkNotNullParameter(croppedImageUri, "croppedImageUri");
        Intrinsics.checkNotNullParameter(correctedImageUri, "correctedImageUri");
        this.imageProcessingUseCase.setupOriginalImagePath(imageUri, croppedImageUri, correctedImageUri);
    }

    public final MutableLiveData<String> getHintText() {
        return this.hintText;
    }

    public final MutableLiveData<Boolean> getHintValid() {
        return this.hintValid;
    }

    public final void setHintOmr(ScanHint scanHint) {
        Intrinsics.checkNotNullParameter(scanHint, "scanHint");
        switch (WhenMappings.$EnumSwitchMapping$0[scanHint.ordinal()]) {
            case 1:
                this.hintText.postValue(null);
                this.hintValid.postValue(null);
                return;
            case 2:
                this.hintText.postValue(null);
                this.hintValid.postValue(null);
                return;
            case 3:
                this.hintText.postValue(null);
                this.hintValid.postValue(null);
                return;
            case 4:
                this.hintText.postValue(null);
                this.hintValid.postValue(null);
                return;
            case 5:
                this.hintText.postValue(null);
                this.hintValid.postValue(null);
                return;
            case 6:
                this.hintText.postValue(null);
                this.hintValid.postValue(null);
                return;
            case 7:
                this.hintText.postValue("Mengambil gambar…");
                this.hintValid.postValue(true);
                return;
            case 8:
                this.hintText.postValue(null);
                this.hintValid.postValue(null);
                return;
            case 9:
                this.hintText.postValue("Memproses Gambar…");
                this.hintValid.postValue(true);
                return;
            case 10:
                this.hintText.postValue(null);
                this.hintValid.postValue(null);
                return;
            default:
                return;
        }
    }

    public final MutableLiveData<Boolean> getAprilTagValid() {
        return this.aprilTagValid;
    }

    public final MutableLiveData<String> getHintAprilTag() {
        return this.hintAprilTag;
    }

    public final void setHintAprilTag(int i) {
        if (i == -1) {
            this.hintAprilTag.postValue("AprilTag tidak terdeteksi");
            this.aprilTagValid.postValue(null);
            return;
        }
        String formName = AprilTagConfig.INSTANCE.getFormName(i);
        if (Intrinsics.areEqual(formName, AprilTagConfig.FORMC_NOT_FOUND)) {
            this.aprilTagValid.postValue(false);
        } else {
            this.aprilTagValid.postValue(true);
        }
        this.hintAprilTag.postValue("AprilTag terdeteksi: " + formName);
    }

    public final MutableLiveData<Boolean> getShutterShown() {
        return this.shutterShown;
    }

    public final void setEnableShutter(boolean z) {
        this.shutterShown.postValue(Boolean.valueOf(z));
    }

    public final MutableLiveData<Boolean> isProcessingImage() {
        return this.isProcessingImage;
    }

    public final void processImage(Bitmap bitmap, int i, int i2) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.isProcessingImage.postValue(true);
        setHintOmr(ScanHint.PROCESSING_IMAGE);
        setEnableShutter(false);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getDefault(), null, new AutoCaptureViewModel$processImage$1(this, bitmap, i, i2, null), 2, null);
    }

    public final MutableLiveData<Boolean> getUseAutoCapture() {
        return this.useAutoCapture;
    }

    public final boolean isAutoCapture() {
        return Intrinsics.areEqual((Object) this.useAutoCapture.getValue(), (Object) true);
    }

    /* compiled from: AutoCaptureViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/ui/autocapture/AutoCaptureViewModel$Companion;", "", "()V", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
