package org.informatika.sirekap.ui.sendImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageTask;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.sendImage.SendImageViewModel;
import org.informatika.sirekap.usecase.SendFormCImageUseCase;

/* compiled from: SendImageViewModel.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u0000 02\u00020\u0001:\u000201B+\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010(\u001a\u00020)J\u0006\u0010\u0011\u001a\u00020)J\u0006\u0010*\u001a\u00020)J\u0006\u0010+\u001a\u00020)J\u000e\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0019J\u000e\u0010.\u001a\u00020)2\u0006\u0010/\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010!\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u00062"}, d2 = {"Lorg/informatika/sirekap/ui/sendImage/SendImageViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "uploadTask", "Lorg/informatika/sirekap/support/worker/uploadFormCImage/UploadFormCImageTask;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Landroid/content/Context;Lorg/informatika/sirekap/support/worker/uploadFormCImage/UploadFormCImageTask;Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "correctingImage", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getCorrectingImage", "()Landroidx/lifecycle/MutableLiveData;", "deleteFormCImage", "Lorg/informatika/sirekap/model/ElectionPage;", "deleteFormCImageResource", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "getDeleteFormCImageResource", "()Landroidx/lifecycle/LiveData;", "electionPageId", "", "getElectionPageId", "getElectionPageUseCase", "Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "getGetElectionPageUseCase", "()Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "perspectiveCorrectionModel", "Lorg/informatika/sirekap/ui/sendImage/SendImageViewModel$ElectionPagePerspectiveCorrectionModel;", "perspectiveCorrectionResource", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getPerspectiveCorrectionResource", "sendFormCImageUseCase", "Lorg/informatika/sirekap/usecase/SendFormCImageUseCase;", "getSendFormCImageUseCase", "()Lorg/informatika/sirekap/usecase/SendFormCImageUseCase;", "correctImage", "", "finishDeletingFormCImage", "finishPerspectiveCorrection", "setup", "_electionPageId", "startSendFormCImageUseCase", "isOffline", "Companion", "ElectionPagePerspectiveCorrectionModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SendImageViewModel extends ViewModel {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "SendImageViewModel";
    private final Context context;
    private final MutableLiveData<Boolean> correctingImage;
    private final MutableLiveData<ElectionPage> deleteFormCImage;
    private final LiveData<Resource<Boolean>> deleteFormCImageResource;
    private final MutableLiveData<String> electionPageId;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final GetElectionPageUseCase getElectionPageUseCase;
    private final MutableLiveData<ElectionPagePerspectiveCorrectionModel> perspectiveCorrectionModel;
    private final LiveData<Resource<ElectionWithRelation>> perspectiveCorrectionResource;
    private final SendFormCImageUseCase sendFormCImageUseCase;

    @Inject
    public SendImageViewModel(@ApplicationContext Context context, UploadFormCImageTask uploadTask, final DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uploadTask, "uploadTask");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.context = context;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        DefaultElectionRepository defaultElectionRepository = electionRepository;
        this.sendFormCImageUseCase = new SendFormCImageUseCase(context, uploadTask, defaultElectionRepository);
        this.electionPageId = new MutableLiveData<>(null);
        this.getElectionPageUseCase = new GetElectionPageUseCase(defaultElectionRepository);
        MutableLiveData<ElectionPage> mutableLiveData = new MutableLiveData<>(null);
        this.deleteFormCImage = mutableLiveData;
        this.deleteFormCImageResource = Transformations.switchMap(mutableLiveData, new Function1<ElectionPage, LiveData<Resource<Boolean>>>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageViewModel$deleteFormCImageResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<Boolean>> invoke(ElectionPage electionPage) {
                if (electionPage == null) {
                    return AbsentLiveData.Companion.create();
                }
                return DefaultElectionRepository.this.deleteElectionPagePhoto(electionPage.getElectionId(), electionPage.getId());
            }
        });
        MutableLiveData<ElectionPagePerspectiveCorrectionModel> mutableLiveData2 = new MutableLiveData<>(null);
        this.perspectiveCorrectionModel = mutableLiveData2;
        this.perspectiveCorrectionResource = Transformations.switchMap(mutableLiveData2, new Function1<ElectionPagePerspectiveCorrectionModel, LiveData<Resource<ElectionWithRelation>>>() { // from class: org.informatika.sirekap.ui.sendImage.SendImageViewModel$perspectiveCorrectionResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<ElectionWithRelation>> invoke(SendImageViewModel.ElectionPagePerspectiveCorrectionModel electionPagePerspectiveCorrectionModel) {
                if (electionPagePerspectiveCorrectionModel == null) {
                    return AbsentLiveData.Companion.create();
                }
                return DefaultElectionRepository.this.finishElectionPagePerspectiveCorrection(electionPagePerspectiveCorrectionModel.getElectionId(), electionPagePerspectiveCorrectionModel.getElectionPageId(), electionPagePerspectiveCorrectionModel.getCorrectedPhotoPath(), electionPagePerspectiveCorrectionModel.getPerspectiveCorrectionError(), electionPagePerspectiveCorrectionModel.getHashDocumentCorrected(), electionPagePerspectiveCorrectionModel.getAprilTagCode());
            }
        });
        this.correctingImage = new MutableLiveData<>(false);
    }

    public final SendFormCImageUseCase getSendFormCImageUseCase() {
        return this.sendFormCImageUseCase;
    }

    public final MutableLiveData<String> getElectionPageId() {
        return this.electionPageId;
    }

    public final GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.getElectionPageUseCase;
    }

    public final void setup(String _electionPageId) {
        Intrinsics.checkNotNullParameter(_electionPageId, "_electionPageId");
        if (!Intrinsics.areEqual(this.electionPageId.getValue(), _electionPageId)) {
            this.electionPageId.postValue(_electionPageId);
        }
        this.getElectionPageUseCase.setup(_electionPageId);
    }

    public final void startSendFormCImageUseCase(boolean z) {
        ElectionPageWithRelation value = this.getElectionPageUseCase.getElectionPageWithRelation().getValue();
        ElectionWithRelation value2 = this.getElectionPageUseCase.getElectionWithRelation().getValue();
        if (value == null || value2 == null) {
            return;
        }
        this.sendFormCImageUseCase.start(value, z);
    }

    public final void deleteFormCImage() {
        this.deleteFormCImage.postValue(this.getElectionPageUseCase.getElectionPage().getValue());
    }

    public final LiveData<Resource<Boolean>> getDeleteFormCImageResource() {
        return this.deleteFormCImageResource;
    }

    public final void finishDeletingFormCImage() {
        this.deleteFormCImage.postValue(null);
    }

    /* compiled from: SendImageViewModel.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\fJR\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\tHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006!"}, d2 = {"Lorg/informatika/sirekap/ui/sendImage/SendImageViewModel$ElectionPagePerspectiveCorrectionModel;", "", "electionId", "", "electionPageId", "correctedPhotoPath", "perspectiveCorrectionError", "hashDocumentCorrected", "aprilTagCode", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getAprilTagCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCorrectedPhotoPath", "()Ljava/lang/String;", "getElectionId", "getElectionPageId", "getHashDocumentCorrected", "getPerspectiveCorrectionError", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lorg/informatika/sirekap/ui/sendImage/SendImageViewModel$ElectionPagePerspectiveCorrectionModel;", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ElectionPagePerspectiveCorrectionModel {
        private final Integer aprilTagCode;
        private final String correctedPhotoPath;
        private final String electionId;
        private final String electionPageId;
        private final String hashDocumentCorrected;
        private final String perspectiveCorrectionError;

        public static /* synthetic */ ElectionPagePerspectiveCorrectionModel copy$default(ElectionPagePerspectiveCorrectionModel electionPagePerspectiveCorrectionModel, String str, String str2, String str3, String str4, String str5, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                str = electionPagePerspectiveCorrectionModel.electionId;
            }
            if ((i & 2) != 0) {
                str2 = electionPagePerspectiveCorrectionModel.electionPageId;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = electionPagePerspectiveCorrectionModel.correctedPhotoPath;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = electionPagePerspectiveCorrectionModel.perspectiveCorrectionError;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                str5 = electionPagePerspectiveCorrectionModel.hashDocumentCorrected;
            }
            String str9 = str5;
            if ((i & 32) != 0) {
                num = electionPagePerspectiveCorrectionModel.aprilTagCode;
            }
            return electionPagePerspectiveCorrectionModel.copy(str, str6, str7, str8, str9, num);
        }

        public final String component1() {
            return this.electionId;
        }

        public final String component2() {
            return this.electionPageId;
        }

        public final String component3() {
            return this.correctedPhotoPath;
        }

        public final String component4() {
            return this.perspectiveCorrectionError;
        }

        public final String component5() {
            return this.hashDocumentCorrected;
        }

        public final Integer component6() {
            return this.aprilTagCode;
        }

        public final ElectionPagePerspectiveCorrectionModel copy(String electionId, String electionPageId, String str, String str2, String str3, Integer num) {
            Intrinsics.checkNotNullParameter(electionId, "electionId");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            return new ElectionPagePerspectiveCorrectionModel(electionId, electionPageId, str, str2, str3, num);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ElectionPagePerspectiveCorrectionModel) {
                ElectionPagePerspectiveCorrectionModel electionPagePerspectiveCorrectionModel = (ElectionPagePerspectiveCorrectionModel) obj;
                return Intrinsics.areEqual(this.electionId, electionPagePerspectiveCorrectionModel.electionId) && Intrinsics.areEqual(this.electionPageId, electionPagePerspectiveCorrectionModel.electionPageId) && Intrinsics.areEqual(this.correctedPhotoPath, electionPagePerspectiveCorrectionModel.correctedPhotoPath) && Intrinsics.areEqual(this.perspectiveCorrectionError, electionPagePerspectiveCorrectionModel.perspectiveCorrectionError) && Intrinsics.areEqual(this.hashDocumentCorrected, electionPagePerspectiveCorrectionModel.hashDocumentCorrected) && Intrinsics.areEqual(this.aprilTagCode, electionPagePerspectiveCorrectionModel.aprilTagCode);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = ((this.electionId.hashCode() * 31) + this.electionPageId.hashCode()) * 31;
            String str = this.correctedPhotoPath;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.perspectiveCorrectionError;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.hashDocumentCorrected;
            int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Integer num = this.aprilTagCode;
            return hashCode4 + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            String str = this.electionId;
            String str2 = this.electionPageId;
            String str3 = this.correctedPhotoPath;
            String str4 = this.perspectiveCorrectionError;
            String str5 = this.hashDocumentCorrected;
            return "ElectionPagePerspectiveCorrectionModel(electionId=" + str + ", electionPageId=" + str2 + ", correctedPhotoPath=" + str3 + ", perspectiveCorrectionError=" + str4 + ", hashDocumentCorrected=" + str5 + ", aprilTagCode=" + this.aprilTagCode + ")";
        }

        public ElectionPagePerspectiveCorrectionModel(String electionId, String electionPageId, String str, String str2, String str3, Integer num) {
            Intrinsics.checkNotNullParameter(electionId, "electionId");
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            this.electionId = electionId;
            this.electionPageId = electionPageId;
            this.correctedPhotoPath = str;
            this.perspectiveCorrectionError = str2;
            this.hashDocumentCorrected = str3;
            this.aprilTagCode = num;
        }

        public final String getElectionId() {
            return this.electionId;
        }

        public final String getElectionPageId() {
            return this.electionPageId;
        }

        public final String getCorrectedPhotoPath() {
            return this.correctedPhotoPath;
        }

        public final String getPerspectiveCorrectionError() {
            return this.perspectiveCorrectionError;
        }

        public final String getHashDocumentCorrected() {
            return this.hashDocumentCorrected;
        }

        public final Integer getAprilTagCode() {
            return this.aprilTagCode;
        }
    }

    public final LiveData<Resource<ElectionWithRelation>> getPerspectiveCorrectionResource() {
        return this.perspectiveCorrectionResource;
    }

    public final void finishPerspectiveCorrection() {
        this.perspectiveCorrectionModel.postValue(null);
        this.correctingImage.postValue(false);
    }

    public final MutableLiveData<Boolean> getCorrectingImage() {
        return this.correctingImage;
    }

    public final void correctImage() {
        Election election;
        ElectionPageWithRelation value = this.getElectionPageUseCase.getElectionPageWithRelation().getValue();
        if (value == null || (election = value.getElection()) == null) {
            return;
        }
        ElectionPage electionPage = value.getElectionPage();
        Integer aprilTagCode = electionPage.getAprilTagCode();
        String croppedPhotoPath = electionPage.getCroppedPhotoPath();
        if (croppedPhotoPath != null) {
            Bitmap decodeFile = BitmapFactory.decodeFile(croppedPhotoPath);
            this.correctingImage.postValue(true);
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getDefault(), null, new SendImageViewModel$correctImage$1$1$1$1$1(this, election, electionPage, aprilTagCode, decodeFile, croppedPhotoPath, null), 2, null);
        }
    }

    /* compiled from: SendImageViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/informatika/sirekap/ui/sendImage/SendImageViewModel$Companion;", "", "()V", "TAG", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
