package org.informatika.sirekap.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.content.FileProvider;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.io.File;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.openid.appauth.AuthorizationRequest;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.R;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.support.FileUtil;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.SecurityUtil;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.keystore.KeystoreManager;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.dashboard.CaptureImageUseCase;
import org.informatika.sirekap.ui.sendImage.SendImageViewModel;

/* compiled from: CaptureImageUseCase.kt */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 V2\u00020\u0001:\u0003VWXB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010,\u001a\u00020-J\u0006\u0010.\u001a\u00020-J\u0006\u0010/\u001a\u00020-J\u0006\u00100\u001a\u00020-JU\u00101\u001a\u00020-2\u0006\u00102\u001a\u00020\n2\u0006\u00103\u001a\u00020\n2\u0006\u00104\u001a\u00020\n2\b\u00105\u001a\u0004\u0018\u00010\n2\u0006\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\n2\b\u00109\u001a\u0004\u0018\u00010\n2\b\u0010:\u001a\u0004\u0018\u000107H\u0002¢\u0006\u0002\u0010;J\u0006\u0010<\u001a\u00020-J\b\u0010=\u001a\u00020\nH\u0002JN\u0010>\u001a\u00020-2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\n2\u001e\u0010@\u001a\u001a\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020-0A2\u0016\u0010B\u001a\u0012\u0012\b\u0012\u00060Dj\u0002`E\u0012\u0004\u0012\u00020-0CJ5\u0010F\u001a\u00020-2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\n2\u0006\u0010?\u001a\u00020\n2\u0006\u0010J\u001a\u00020K2\b\u0010:\u001a\u0004\u0018\u000107¢\u0006\u0002\u0010LJ5\u0010M\u001a\u00020-2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\n2\u0006\u0010?\u001a\u00020\n2\u0006\u0010J\u001a\u00020K2\b\u0010:\u001a\u0004\u0018\u000107¢\u0006\u0002\u0010LJY\u0010N\u001a\u00020-2\u0006\u0010O\u001a\u00020P2\u0006\u0010?\u001a\u00020\n2\u0006\u0010I\u001a\u00020\n2!\u0010@\u001a\u001d\u0012\u0013\u0012\u00110H¢\u0006\f\bQ\u0012\b\bR\u0012\u0004\b\b(G\u0012\u0004\u0012\u00020-0C2\u0016\u0010S\u001a\u0012\u0012\b\u0012\u00060Dj\u0002`E\u0012\u0004\u0012\u00020-0CJ\u001c\u0010T\u001a\u00020-2\u0006\u0010G\u001a\u00020H2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020-0UR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u001f\u0010\u001d\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f0\u001e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\f\"\u0004\b%\u0010\u000eR\u0019\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010'0\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0015R\u001f\u0010)\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f0\u001e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u0010\u0010+\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Y"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase;", "", "context", "Landroid/content/Context;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/repository/ElectionRepository;)V", "correctedPhotoAbsolutePath", "", "getCorrectedPhotoAbsolutePath", "()Ljava/lang/String;", "setCorrectedPhotoAbsolutePath", "(Ljava/lang/String;)V", "correctedPhotoUri", "Landroid/net/Uri;", "cropPhotoResult", "Landroidx/lifecycle/MutableLiveData;", "Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase$CropPhotoResult;", "getCropPhotoResult", "()Landroidx/lifecycle/MutableLiveData;", "croppedPhotoAbsolutePath", "getCroppedPhotoAbsolutePath", "setCroppedPhotoAbsolutePath", "croppedPhotoUri", "perspectiveCorrectionModel", "Lorg/informatika/sirekap/ui/sendImage/SendImageViewModel$ElectionPagePerspectiveCorrectionModel;", "getPerspectiveCorrectionModel", "perspectiveCorrectionResource", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getPerspectiveCorrectionResource", "()Landroidx/lifecycle/LiveData;", "photoAbsolutePath", "getPhotoAbsolutePath", "setPhotoAbsolutePath", "photoModel", "Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase$ElectionPagePhotoModel;", "getPhotoModel", "photoResource", "getPhotoResource", "photoUri", "deleteOriginalPhoto", "", "deletePreparedPhoto", "finishCrop", "finishPerspectiveCorrection", "finishPhoto", "electionId", "electionPageId", "photoPath", "croppedPhotoPath", "takePhotoAttempt", "", "signature", "hashDocumentCropped", "aprilTagCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "finishTakingPhoto", "getElectionType", "prepareTakingPhoto", "kodeTps", "onSuccess", "Lkotlin/Function3;", "onFailure", "Lkotlin/Function1;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "saveCorrectedPhoto", "bitmap", "Landroid/graphics/Bitmap;", "jenisPemilihan", AuthorizationRequest.Display.PAGE, "Lorg/informatika/sirekap/model/ElectionPage;", "(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/model/ElectionPage;Ljava/lang/Integer;)V", "saveCroppedPhoto", "saveOriginalPhoto", "activity", "Landroid/app/Activity;", "Lkotlin/ParameterName;", "name", "onError", "saveTempCroppedPhoto", "Lkotlin/Function0;", "Companion", "CropPhotoResult", "ElectionPagePhotoModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public class CaptureImageUseCase {
    public static final Companion Companion = new Companion(null);
    public static final int ERROR_CODE_CROPPED_IMAGE_NULL = 1;
    private static final String TAG = "CaptureImageUseCase";
    private final Context context;
    private String correctedPhotoAbsolutePath;
    private Uri correctedPhotoUri;
    private final MutableLiveData<CropPhotoResult> cropPhotoResult;
    private String croppedPhotoAbsolutePath;
    private Uri croppedPhotoUri;
    private final ElectionRepository electionRepository;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final MutableLiveData<SendImageViewModel.ElectionPagePerspectiveCorrectionModel> perspectiveCorrectionModel;
    private final LiveData<Resource<ElectionWithRelation>> perspectiveCorrectionResource;
    private String photoAbsolutePath;
    private final MutableLiveData<ElectionPagePhotoModel> photoModel;
    private final LiveData<Resource<ElectionWithRelation>> photoResource;
    private Uri photoUri;

    public CaptureImageUseCase(Context context, EncryptedSharedPreferences encryptedSharedPreferences, ElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.context = context;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.electionRepository = electionRepository;
        this.cropPhotoResult = new MutableLiveData<>(null);
        MutableLiveData<ElectionPagePhotoModel> mutableLiveData = new MutableLiveData<>(null);
        this.photoModel = mutableLiveData;
        this.photoResource = Transformations.switchMap(mutableLiveData, new Function1<ElectionPagePhotoModel, LiveData<Resource<ElectionWithRelation>>>() { // from class: org.informatika.sirekap.ui.dashboard.CaptureImageUseCase$photoResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<ElectionWithRelation>> invoke(CaptureImageUseCase.ElectionPagePhotoModel electionPagePhotoModel) {
                ElectionRepository electionRepository2;
                if (electionPagePhotoModel != null) {
                    electionRepository2 = CaptureImageUseCase.this.electionRepository;
                    return electionRepository2.finishElectionPagePhoto(electionPagePhotoModel.getElectionId(), electionPagePhotoModel.getElectionPageId(), electionPagePhotoModel.getPhotoPath(), electionPagePhotoModel.getCroppedPhotoPath(), electionPagePhotoModel.getTakePhotoAttempt(), electionPagePhotoModel.getSignature(), electionPagePhotoModel.getHashDocumentCropped(), electionPagePhotoModel.getAprilTagCode());
                }
                return AbsentLiveData.Companion.create();
            }
        });
        MutableLiveData<SendImageViewModel.ElectionPagePerspectiveCorrectionModel> mutableLiveData2 = new MutableLiveData<>(null);
        this.perspectiveCorrectionModel = mutableLiveData2;
        this.perspectiveCorrectionResource = Transformations.switchMap(mutableLiveData2, new Function1<SendImageViewModel.ElectionPagePerspectiveCorrectionModel, LiveData<Resource<ElectionWithRelation>>>() { // from class: org.informatika.sirekap.ui.dashboard.CaptureImageUseCase$perspectiveCorrectionResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<ElectionWithRelation>> invoke(SendImageViewModel.ElectionPagePerspectiveCorrectionModel electionPagePerspectiveCorrectionModel) {
                ElectionRepository electionRepository2;
                if (electionPagePerspectiveCorrectionModel != null) {
                    electionRepository2 = CaptureImageUseCase.this.electionRepository;
                    return electionRepository2.finishElectionPagePerspectiveCorrection(electionPagePerspectiveCorrectionModel.getElectionId(), electionPagePerspectiveCorrectionModel.getElectionPageId(), electionPagePerspectiveCorrectionModel.getCorrectedPhotoPath(), electionPagePerspectiveCorrectionModel.getPerspectiveCorrectionError(), electionPagePerspectiveCorrectionModel.getHashDocumentCorrected(), electionPagePerspectiveCorrectionModel.getAprilTagCode());
                }
                return AbsentLiveData.Companion.create();
            }
        });
    }

    public final String getPhotoAbsolutePath() {
        return this.photoAbsolutePath;
    }

    public final void setPhotoAbsolutePath(String str) {
        this.photoAbsolutePath = str;
    }

    public final String getCroppedPhotoAbsolutePath() {
        return this.croppedPhotoAbsolutePath;
    }

    public final void setCroppedPhotoAbsolutePath(String str) {
        this.croppedPhotoAbsolutePath = str;
    }

    public final String getCorrectedPhotoAbsolutePath() {
        return this.correctedPhotoAbsolutePath;
    }

    public final void setCorrectedPhotoAbsolutePath(String str) {
        this.correctedPhotoAbsolutePath = str;
    }

    public final void prepareTakingPhoto(Context context, String kodeTps, Function3<? super Uri, ? super Uri, ? super Uri, Unit> onSuccess, Function1<? super Exception, Unit> onFailure) {
        File file;
        File file2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onFailure, "onFailure");
        File file3 = null;
        try {
            file = FileUtil.saveOriginalImage(context, kodeTps, getElectionType());
        } catch (Exception e) {
            onFailure.invoke(e);
            e.printStackTrace();
            file = null;
        }
        if (file != null) {
            this.photoAbsolutePath = file.getAbsolutePath();
            this.photoUri = FileProvider.getUriForFile(context, BuildConfig.FILE_PROVIDER, file);
            try {
                String name = file.getName();
                Intrinsics.checkNotNullExpressionValue(name, "file.name");
                file2 = FileUtil.saveTempCroppedImage(name, context, kodeTps, getElectionType());
            } catch (Exception e2) {
                onFailure.invoke(e2);
                e2.printStackTrace();
                file2 = null;
            }
            if (file2 != null) {
                this.croppedPhotoAbsolutePath = file2.getAbsolutePath();
                this.croppedPhotoUri = FileProvider.getUriForFile(context, BuildConfig.FILE_PROVIDER, file2);
            }
            try {
                String name2 = file.getName();
                Intrinsics.checkNotNullExpressionValue(name2, "file.name");
                file3 = FileUtil.saveTempCorrectedImage(name2, context, kodeTps, getElectionType());
            } catch (Exception e3) {
                onFailure.invoke(e3);
                e3.printStackTrace();
            }
            if (file3 != null) {
                this.correctedPhotoAbsolutePath = file3.getAbsolutePath();
                this.correctedPhotoUri = FileProvider.getUriForFile(context, BuildConfig.FILE_PROVIDER, file3);
                Uri uri = this.photoUri;
                Intrinsics.checkNotNull(uri);
                Uri uri2 = this.croppedPhotoUri;
                Intrinsics.checkNotNull(uri2);
                Uri uri3 = this.correctedPhotoUri;
                Intrinsics.checkNotNull(uri3);
                onSuccess.invoke(uri, uri2, uri3);
            }
        }
    }

    public final void saveTempCroppedPhoto(Bitmap bitmap, Function0<Unit> onSuccess) {
        OutputStream openOutputStream;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        try {
            Uri uri = this.croppedPhotoUri;
            if (uri != null && (openOutputStream = this.context.getContentResolver().openOutputStream(uri)) != null) {
                OutputStream outputStream = openOutputStream;
                Boolean.valueOf(bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream));
                CloseableKt.closeFinally(outputStream, null);
            }
            onSuccess.invoke();
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }

    public final void deletePreparedPhoto() {
        String str = this.photoAbsolutePath;
        if (str != null) {
            FileUtil.deleteFile(str);
            this.photoAbsolutePath = null;
            this.photoUri = null;
        }
        String str2 = this.croppedPhotoAbsolutePath;
        if (str2 != null) {
            FileUtil.deleteFile(str2);
            this.croppedPhotoAbsolutePath = null;
            this.croppedPhotoUri = null;
        }
        String str3 = this.correctedPhotoAbsolutePath;
        if (str3 != null) {
            FileUtil.deleteFile(str3);
            this.correctedPhotoAbsolutePath = null;
            this.correctedPhotoUri = null;
        }
    }

    public final void saveOriginalPhoto(Activity activity, String kodeTps, String jenisPemilihan, Function1<? super Bitmap, Unit> onSuccess, Function1<? super Exception, Unit> onError) {
        String str;
        Bitmap resizeImage$default;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Uri uri = this.photoUri;
        if (uri == null || (str = this.photoAbsolutePath) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28) {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
            resizeImage$default = bitmap != null ? FileUtil.adjustImageRotation(FileUtil.resizeImage$default(bitmap, 0, 2, null), str) : null;
        } else {
            ImageDecoder.Source createSource = ImageDecoder.createSource(activity.getContentResolver(), uri);
            Intrinsics.checkNotNullExpressionValue(createSource, "createSource(activity.contentResolver, photoUri)");
            Bitmap decodeBitmap = ImageDecoder.decodeBitmap(createSource, new ImageDecoder.OnHeaderDecodedListener() { // from class: org.informatika.sirekap.ui.dashboard.CaptureImageUseCase$$ExternalSyntheticLambda0
                @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
                public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                    CaptureImageUseCase.saveOriginalPhoto$lambda$11$lambda$10$lambda$8(imageDecoder, imageInfo, source);
                }
            });
            Intrinsics.checkNotNullExpressionValue(decodeBitmap, "decodeBitmap(source) { d…rue\n                    }");
            resizeImage$default = FileUtil.resizeImage$default(decodeBitmap, 0, 2, null);
        }
        if (resizeImage$default != null) {
            File saveAdjustedImage = FileUtil.saveAdjustedImage(activity, new File(str), resizeImage$default, jenisPemilihan, kodeTps, getElectionType());
            FileUtil.deleteFile(str);
            this.photoAbsolutePath = saveAdjustedImage != null ? saveAdjustedImage.getAbsolutePath() : null;
            onSuccess.invoke(resizeImage$default);
            return;
        }
        onError.invoke(new Exception("Data gambar tidak ditemukan"));
    }

    public static final void saveOriginalPhoto$lambda$11$lambda$10$lambda$8(ImageDecoder decoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        Intrinsics.checkNotNullParameter(imageInfo, "<anonymous parameter 1>");
        Intrinsics.checkNotNullParameter(source, "<anonymous parameter 2>");
        decoder.setMutableRequired(true);
    }

    public final void deleteOriginalPhoto() {
        String str = this.photoAbsolutePath;
        if (str != null) {
            FileUtil.deleteFile(str);
            this.photoAbsolutePath = null;
            this.photoUri = null;
        }
        String str2 = this.croppedPhotoAbsolutePath;
        if (str2 != null) {
            FileUtil.deleteFile(str2);
            this.croppedPhotoAbsolutePath = null;
            this.croppedPhotoUri = null;
        }
        String str3 = this.correctedPhotoAbsolutePath;
        if (str3 != null) {
            FileUtil.deleteFile(str3);
            this.correctedPhotoAbsolutePath = null;
            this.correctedPhotoUri = null;
        }
    }

    /* compiled from: CaptureImageUseCase.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase$CropPhotoResult;", "", "isSuccess", "", "errorMessage", "", "(ZLjava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class CropPhotoResult {
        private final String errorMessage;
        private final boolean isSuccess;

        public static /* synthetic */ CropPhotoResult copy$default(CropPhotoResult cropPhotoResult, boolean z, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = cropPhotoResult.isSuccess;
            }
            if ((i & 2) != 0) {
                str = cropPhotoResult.errorMessage;
            }
            return cropPhotoResult.copy(z, str);
        }

        public final boolean component1() {
            return this.isSuccess;
        }

        public final String component2() {
            return this.errorMessage;
        }

        public final CropPhotoResult copy(boolean z, String str) {
            return new CropPhotoResult(z, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof CropPhotoResult) {
                CropPhotoResult cropPhotoResult = (CropPhotoResult) obj;
                return this.isSuccess == cropPhotoResult.isSuccess && Intrinsics.areEqual(this.errorMessage, cropPhotoResult.errorMessage);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.isSuccess;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.errorMessage;
            return i + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            boolean z = this.isSuccess;
            return "CropPhotoResult(isSuccess=" + z + ", errorMessage=" + this.errorMessage + ")";
        }

        public CropPhotoResult(boolean z, String str) {
            this.isSuccess = z;
            this.errorMessage = str;
        }

        public /* synthetic */ CropPhotoResult(boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? null : str);
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }

        public final String getErrorMessage() {
            return this.errorMessage;
        }
    }

    public final MutableLiveData<CropPhotoResult> getCropPhotoResult() {
        return this.cropPhotoResult;
    }

    public final void saveCroppedPhoto(Bitmap bitmap, String jenisPemilihan, String kodeTps, ElectionPage page, Integer num) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(page, "page");
        try {
            KeystoreManager buildKeystoreManager = SecurityFacade.INSTANCE.buildKeystoreManager(this.context);
            String str = this.photoAbsolutePath;
            if (str != null) {
                File saveCroppedImage = FileUtil.saveCroppedImage(this.context, new File(str), FileUtil.resizeImage$default(bitmap, 0, 2, null), jenisPemilihan, kodeTps, this.encryptedSharedPreferences);
                if (saveCroppedImage != null) {
                    String croppedPath = saveCroppedImage.getAbsolutePath();
                    SecurityUtil securityUtil = new SecurityUtil();
                    Intrinsics.checkNotNullExpressionValue(croppedPath, "croppedPath");
                    String hashDocument = securityUtil.hashDocument(croppedPath);
                    File file = new File(croppedPath);
                    SecurityFacade.INSTANCE.signJpgImage(buildKeystoreManager, file);
                    String imageSignature = SecurityFacade.INSTANCE.getImageSignature(file);
                    String str2 = this.croppedPhotoAbsolutePath;
                    if (str2 != null) {
                        FileUtil.deleteFile(str2);
                    }
                    String photoPath = page.getPhotoPath();
                    if (photoPath != null) {
                        FileUtil.deleteFile(StringsKt.replace$default(photoPath, ".jpg", ".png", false, 4, (Object) null));
                        FileUtil.deleteFile(photoPath);
                    }
                    String croppedPhotoPath = page.getCroppedPhotoPath();
                    if (croppedPhotoPath != null) {
                        FileUtil.deleteFile(StringsKt.replace$default(croppedPhotoPath, ".jpg", ".png", false, 4, (Object) null));
                        FileUtil.deleteFile(croppedPhotoPath);
                    }
                    String correctedPhotoPath = page.getCorrectedPhotoPath();
                    if (correctedPhotoPath != null) {
                        FileUtil.deleteFile(StringsKt.replace$default(correctedPhotoPath, ".jpg", ".png", false, 4, (Object) null));
                        FileUtil.deleteFile(correctedPhotoPath);
                    }
                    finishPhoto(page.getElectionId(), page.getId(), str, saveCroppedImage.getAbsolutePath(), page.getTakePhotoAttempt() + 1, imageSignature, hashDocument, num);
                    return;
                }
                deleteOriginalPhoto();
                this.cropPhotoResult.postValue(new CropPhotoResult(false, this.context.getString(R.string.error_signature_not_exist, 1)));
            }
        } catch (Exception e) {
            Log.e("Exception", String.valueOf(e.getMessage()));
            e.printStackTrace();
        }
    }

    public final void finishCrop() {
        this.cropPhotoResult.postValue(null);
    }

    /* compiled from: CaptureImageUseCase.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\fJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000eJf\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\bHÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006("}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase$ElectionPagePhotoModel;", "", "electionId", "", "electionPageId", "photoPath", "croppedPhotoPath", "takePhotoAttempt", "", "signature", "hashDocumentCropped", "aprilTagCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getAprilTagCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCroppedPhotoPath", "()Ljava/lang/String;", "getElectionId", "getElectionPageId", "getHashDocumentCropped", "getPhotoPath", "getSignature", "getTakePhotoAttempt", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase$ElectionPagePhotoModel;", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ElectionPagePhotoModel {
        private final Integer aprilTagCode;
        private final String croppedPhotoPath;
        private final String electionId;
        private final String electionPageId;
        private final String hashDocumentCropped;
        private final String photoPath;
        private final String signature;
        private final int takePhotoAttempt;

        public final String component1() {
            return this.electionId;
        }

        public final String component2() {
            return this.electionPageId;
        }

        public final String component3() {
            return this.photoPath;
        }

        public final String component4() {
            return this.croppedPhotoPath;
        }

        public final int component5() {
            return this.takePhotoAttempt;
        }

        public final String component6() {
            return this.signature;
        }

        public final String component7() {
            return this.hashDocumentCropped;
        }

        public final Integer component8() {
            return this.aprilTagCode;
        }

        public final ElectionPagePhotoModel copy(String electionId, String electionPageId, String photoPath, String str, int i, String str2, String str3, Integer num) {
            Intrinsics.checkNotNullParameter(electionId, "electionId");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            Intrinsics.checkNotNullParameter(photoPath, "photoPath");
            return new ElectionPagePhotoModel(electionId, electionPageId, photoPath, str, i, str2, str3, num);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ElectionPagePhotoModel) {
                ElectionPagePhotoModel electionPagePhotoModel = (ElectionPagePhotoModel) obj;
                return Intrinsics.areEqual(this.electionId, electionPagePhotoModel.electionId) && Intrinsics.areEqual(this.electionPageId, electionPagePhotoModel.electionPageId) && Intrinsics.areEqual(this.photoPath, electionPagePhotoModel.photoPath) && Intrinsics.areEqual(this.croppedPhotoPath, electionPagePhotoModel.croppedPhotoPath) && this.takePhotoAttempt == electionPagePhotoModel.takePhotoAttempt && Intrinsics.areEqual(this.signature, electionPagePhotoModel.signature) && Intrinsics.areEqual(this.hashDocumentCropped, electionPagePhotoModel.hashDocumentCropped) && Intrinsics.areEqual(this.aprilTagCode, electionPagePhotoModel.aprilTagCode);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = ((((this.electionId.hashCode() * 31) + this.electionPageId.hashCode()) * 31) + this.photoPath.hashCode()) * 31;
            String str = this.croppedPhotoPath;
            int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.takePhotoAttempt)) * 31;
            String str2 = this.signature;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.hashDocumentCropped;
            int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Integer num = this.aprilTagCode;
            return hashCode4 + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            String str = this.electionId;
            String str2 = this.electionPageId;
            String str3 = this.photoPath;
            String str4 = this.croppedPhotoPath;
            int i = this.takePhotoAttempt;
            String str5 = this.signature;
            String str6 = this.hashDocumentCropped;
            return "ElectionPagePhotoModel(electionId=" + str + ", electionPageId=" + str2 + ", photoPath=" + str3 + ", croppedPhotoPath=" + str4 + ", takePhotoAttempt=" + i + ", signature=" + str5 + ", hashDocumentCropped=" + str6 + ", aprilTagCode=" + this.aprilTagCode + ")";
        }

        public ElectionPagePhotoModel(String electionId, String electionPageId, String photoPath, String str, int i, String str2, String str3, Integer num) {
            Intrinsics.checkNotNullParameter(electionId, "electionId");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            Intrinsics.checkNotNullParameter(photoPath, "photoPath");
            this.electionId = electionId;
            this.electionPageId = electionPageId;
            this.photoPath = photoPath;
            this.croppedPhotoPath = str;
            this.takePhotoAttempt = i;
            this.signature = str2;
            this.hashDocumentCropped = str3;
            this.aprilTagCode = num;
        }

        public final String getElectionId() {
            return this.electionId;
        }

        public final String getElectionPageId() {
            return this.electionPageId;
        }

        public final String getPhotoPath() {
            return this.photoPath;
        }

        public final String getCroppedPhotoPath() {
            return this.croppedPhotoPath;
        }

        public final int getTakePhotoAttempt() {
            return this.takePhotoAttempt;
        }

        public final String getSignature() {
            return this.signature;
        }

        public final String getHashDocumentCropped() {
            return this.hashDocumentCropped;
        }

        public final Integer getAprilTagCode() {
            return this.aprilTagCode;
        }
    }

    public final MutableLiveData<ElectionPagePhotoModel> getPhotoModel() {
        return this.photoModel;
    }

    public final LiveData<Resource<ElectionWithRelation>> getPhotoResource() {
        return this.photoResource;
    }

    private final void finishPhoto(String str, String str2, String str3, String str4, int i, String str5, String str6, Integer num) {
        this.photoModel.postValue(new ElectionPagePhotoModel(str, str2, str3, str4, i, str5, str6, num));
    }

    public final void finishTakingPhoto() {
        this.photoModel.postValue(null);
    }

    public final void saveCorrectedPhoto(Bitmap bitmap, String jenisPemilihan, String kodeTps, ElectionPage page, Integer num) {
        File saveCorrectedImage;
        String absolutePath;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(page, "page");
        try {
            String str = this.photoAbsolutePath;
            if (str == null || (saveCorrectedImage = FileUtil.saveCorrectedImage(this.context, new File(str), FileUtil.resizeImage$default(bitmap, 0, 2, null), jenisPemilihan, kodeTps, this.encryptedSharedPreferences)) == null || (absolutePath = saveCorrectedImage.getAbsolutePath()) == null) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(absolutePath, "absolutePath");
            String hashDocument = new SecurityUtil().hashDocument(absolutePath);
            SecurityFacade.INSTANCE.signJpgImage(SecurityFacade.INSTANCE.buildKeystoreManager(this.context), new File(absolutePath));
            this.perspectiveCorrectionModel.postValue(new SendImageViewModel.ElectionPagePerspectiveCorrectionModel(page.getElectionId(), page.getId(), absolutePath, "", hashDocument, num));
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            this.perspectiveCorrectionModel.postValue(new SendImageViewModel.ElectionPagePerspectiveCorrectionModel(page.getElectionId(), page.getId(), null, "", null, num));
        }
    }

    public final MutableLiveData<SendImageViewModel.ElectionPagePerspectiveCorrectionModel> getPerspectiveCorrectionModel() {
        return this.perspectiveCorrectionModel;
    }

    public final LiveData<Resource<ElectionWithRelation>> getPerspectiveCorrectionResource() {
        return this.perspectiveCorrectionResource;
    }

    public final void finishPerspectiveCorrection() {
        this.perspectiveCorrectionModel.postValue(null);
    }

    private final String getElectionType() {
        String stringEncrypted = this.encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
        String str = stringEncrypted;
        return str == null || StringsKt.isBlank(str) ? "R" : stringEncrypted;
    }

    /* compiled from: CaptureImageUseCase.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase$Companion;", "", "()V", "ERROR_CODE_CROPPED_IMAGE_NULL", "", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }
    }
}
