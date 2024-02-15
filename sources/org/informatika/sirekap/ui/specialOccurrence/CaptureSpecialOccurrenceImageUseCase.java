package org.informatika.sirekap.ui.specialOccurrence;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.room.RoomDatabase;
import java.io.File;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.openid.appauth.AuthorizationRequest;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.SpecialOccurrencePage;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.support.FileUtil;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.keystore.KeystoreManager;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.dashboard.CaptureImageUseCase;
import org.informatika.sirekap.ui.specialOccurrence.CaptureSpecialOccurrenceImageUseCase;

/* compiled from: CaptureSpecialOccurrenceImageUseCase.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002#$B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aJ(\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001f\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\rR\u001f\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013¨\u0006%"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/CaptureSpecialOccurrenceImageUseCase;", "Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase;", "context", "Landroid/content/Context;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/repository/ElectionRepository;)V", "specialOccurrenceInsertModel", "Landroidx/lifecycle/MutableLiveData;", "Lorg/informatika/sirekap/ui/specialOccurrence/CaptureSpecialOccurrenceImageUseCase$SpecialOccurrenceInsertModel;", "getSpecialOccurrenceInsertModel", "()Landroidx/lifecycle/MutableLiveData;", "specialOccurrenceInsertResource", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "getSpecialOccurrenceInsertResource", "()Landroidx/lifecycle/LiveData;", "specialOccurrenceUpdateModel", "Lorg/informatika/sirekap/ui/specialOccurrence/CaptureSpecialOccurrenceImageUseCase$SpecialOccurrenceUpdateModel;", "getSpecialOccurrenceUpdateModel", "specialOccurrenceUpdateResource", "getSpecialOccurrenceUpdateResource", "finishInsertingSpecialOccurrence", "", "finishUpdatingSpecialOccurrence", "saveCroppedPhoto", "bitmap", "Landroid/graphics/Bitmap;", "jenisPemilihan", "", "kodeTps", AuthorizationRequest.Display.PAGE, "SpecialOccurrenceInsertModel", "SpecialOccurrenceUpdateModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CaptureSpecialOccurrenceImageUseCase extends CaptureImageUseCase {
    private final Context context;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final MutableLiveData<SpecialOccurrenceInsertModel> specialOccurrenceInsertModel;
    private final LiveData<Resource<SpecialOccurrencePage>> specialOccurrenceInsertResource;
    private final MutableLiveData<SpecialOccurrenceUpdateModel> specialOccurrenceUpdateModel;
    private final LiveData<Resource<SpecialOccurrencePage>> specialOccurrenceUpdateResource;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaptureSpecialOccurrenceImageUseCase(Context context, EncryptedSharedPreferences encryptedSharedPreferences, final ElectionRepository electionRepository) {
        super(context, encryptedSharedPreferences, electionRepository);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.context = context;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        MutableLiveData<SpecialOccurrenceUpdateModel> mutableLiveData = new MutableLiveData<>(null);
        this.specialOccurrenceUpdateModel = mutableLiveData;
        this.specialOccurrenceUpdateResource = Transformations.switchMap(mutableLiveData, new Function1<SpecialOccurrenceUpdateModel, LiveData<Resource<SpecialOccurrencePage>>>() { // from class: org.informatika.sirekap.ui.specialOccurrence.CaptureSpecialOccurrenceImageUseCase$specialOccurrenceUpdateResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<SpecialOccurrencePage>> invoke(CaptureSpecialOccurrenceImageUseCase.SpecialOccurrenceUpdateModel specialOccurrenceUpdateModel) {
                if (specialOccurrenceUpdateModel == null) {
                    return AbsentLiveData.Companion.create();
                }
                return ElectionRepository.this.updateSpecialOccurrencePagePhoto(specialOccurrenceUpdateModel.getId(), specialOccurrenceUpdateModel.getPhotoPath(), specialOccurrenceUpdateModel.getCroppedPhotoPath(), specialOccurrenceUpdateModel.getHashDocumentCropped());
            }
        });
        MutableLiveData<SpecialOccurrenceInsertModel> mutableLiveData2 = new MutableLiveData<>(null);
        this.specialOccurrenceInsertModel = mutableLiveData2;
        this.specialOccurrenceInsertResource = Transformations.switchMap(mutableLiveData2, new Function1<SpecialOccurrenceInsertModel, LiveData<Resource<SpecialOccurrencePage>>>() { // from class: org.informatika.sirekap.ui.specialOccurrence.CaptureSpecialOccurrenceImageUseCase$specialOccurrenceInsertResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<SpecialOccurrencePage>> invoke(CaptureSpecialOccurrenceImageUseCase.SpecialOccurrenceInsertModel specialOccurrenceInsertModel) {
                if (specialOccurrenceInsertModel == null) {
                    return AbsentLiveData.Companion.create();
                }
                return ElectionRepository.this.insertSpecialOccurrence(specialOccurrenceInsertModel.getId(), specialOccurrenceInsertModel.getKodeTps(), specialOccurrenceInsertModel.getPhotoPath(), specialOccurrenceInsertModel.getCroppedPhotoPath(), specialOccurrenceInsertModel.getHashDocumentCropped());
            }
        });
    }

    public final void saveCroppedPhoto(Bitmap bitmap, String jenisPemilihan, String kodeTps, SpecialOccurrencePage specialOccurrencePage) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        String photoAbsolutePath = getPhotoAbsolutePath();
        if (photoAbsolutePath != null) {
            KeystoreManager buildKeystoreManager = SecurityFacade.INSTANCE.buildKeystoreManager(this.context);
            File saveCroppedImage = FileUtil.saveCroppedImage(this.context, new File(photoAbsolutePath), FileUtil.resizeImage$default(bitmap, 0, 2, null), jenisPemilihan, kodeTps, this.encryptedSharedPreferences);
            if (saveCroppedImage != null) {
                File file = new File(saveCroppedImage.getAbsolutePath());
                SecurityFacade.INSTANCE.signJpgImage(buildKeystoreManager, file);
                String imageSignature = SecurityFacade.INSTANCE.getImageSignature(file);
                if (specialOccurrencePage != null) {
                    String photoPath = specialOccurrencePage.getPhotoPath();
                    FileUtil.deleteFile(StringsKt.replace$default(photoPath, ".jpg", ".png", false, 4, (Object) null));
                    FileUtil.deleteFile(photoPath);
                    String croppedPhotoPath = specialOccurrencePage.getCroppedPhotoPath();
                    FileUtil.deleteFile(StringsKt.replace$default(croppedPhotoPath, ".jpg", ".png", false, 4, (Object) null));
                    FileUtil.deleteFile(croppedPhotoPath);
                    long id2 = specialOccurrencePage.getId();
                    MutableLiveData<SpecialOccurrenceUpdateModel> mutableLiveData = this.specialOccurrenceUpdateModel;
                    String absolutePath = saveCroppedImage.getAbsolutePath();
                    Intrinsics.checkNotNullExpressionValue(absolutePath, "croppedImage.absolutePath");
                    mutableLiveData.postValue(new SpecialOccurrenceUpdateModel(id2, photoAbsolutePath, absolutePath, imageSignature));
                    return;
                }
                MutableLiveData<SpecialOccurrenceInsertModel> mutableLiveData2 = this.specialOccurrenceInsertModel;
                String absolutePath2 = saveCroppedImage.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath2, "croppedImage.absolutePath");
                mutableLiveData2.postValue(new SpecialOccurrenceInsertModel(new Date().getTime() + RangesKt.random(new IntRange(100, RoomDatabase.MAX_BIND_PARAMETER_CNT), Random.Default), kodeTps, photoAbsolutePath, absolutePath2, imageSignature));
                return;
            }
            FileUtil.deleteFile(photoAbsolutePath);
            getCropPhotoResult().postValue(new CaptureImageUseCase.CropPhotoResult(false, this.context.getString(R.string.error_signature_not_exist, 1)));
        }
    }

    /* compiled from: CaptureSpecialOccurrenceImageUseCase.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/CaptureSpecialOccurrenceImageUseCase$SpecialOccurrenceUpdateModel;", "", JobType.ID, "", "photoPath", "", "croppedPhotoPath", "hashDocumentCropped", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCroppedPhotoPath", "()Ljava/lang/String;", "getHashDocumentCropped", "getId", "()J", "getPhotoPath", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class SpecialOccurrenceUpdateModel {
        private final String croppedPhotoPath;
        private final String hashDocumentCropped;

        /* renamed from: id */
        private final long f74id;
        private final String photoPath;

        public static /* synthetic */ SpecialOccurrenceUpdateModel copy$default(SpecialOccurrenceUpdateModel specialOccurrenceUpdateModel, long j, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                j = specialOccurrenceUpdateModel.f74id;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                str = specialOccurrenceUpdateModel.photoPath;
            }
            String str4 = str;
            if ((i & 4) != 0) {
                str2 = specialOccurrenceUpdateModel.croppedPhotoPath;
            }
            String str5 = str2;
            if ((i & 8) != 0) {
                str3 = specialOccurrenceUpdateModel.hashDocumentCropped;
            }
            return specialOccurrenceUpdateModel.copy(j2, str4, str5, str3);
        }

        public final long component1() {
            return this.f74id;
        }

        public final String component2() {
            return this.photoPath;
        }

        public final String component3() {
            return this.croppedPhotoPath;
        }

        public final String component4() {
            return this.hashDocumentCropped;
        }

        public final SpecialOccurrenceUpdateModel copy(long j, String photoPath, String croppedPhotoPath, String hashDocumentCropped) {
            Intrinsics.checkNotNullParameter(photoPath, "photoPath");
            Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
            Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
            return new SpecialOccurrenceUpdateModel(j, photoPath, croppedPhotoPath, hashDocumentCropped);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SpecialOccurrenceUpdateModel) {
                SpecialOccurrenceUpdateModel specialOccurrenceUpdateModel = (SpecialOccurrenceUpdateModel) obj;
                return this.f74id == specialOccurrenceUpdateModel.f74id && Intrinsics.areEqual(this.photoPath, specialOccurrenceUpdateModel.photoPath) && Intrinsics.areEqual(this.croppedPhotoPath, specialOccurrenceUpdateModel.croppedPhotoPath) && Intrinsics.areEqual(this.hashDocumentCropped, specialOccurrenceUpdateModel.hashDocumentCropped);
            }
            return false;
        }

        public int hashCode() {
            return (((((Long.hashCode(this.f74id) * 31) + this.photoPath.hashCode()) * 31) + this.croppedPhotoPath.hashCode()) * 31) + this.hashDocumentCropped.hashCode();
        }

        public String toString() {
            long j = this.f74id;
            String str = this.photoPath;
            String str2 = this.croppedPhotoPath;
            return "SpecialOccurrenceUpdateModel(id=" + j + ", photoPath=" + str + ", croppedPhotoPath=" + str2 + ", hashDocumentCropped=" + this.hashDocumentCropped + ")";
        }

        public SpecialOccurrenceUpdateModel(long j, String photoPath, String croppedPhotoPath, String hashDocumentCropped) {
            Intrinsics.checkNotNullParameter(photoPath, "photoPath");
            Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
            Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
            this.f74id = j;
            this.photoPath = photoPath;
            this.croppedPhotoPath = croppedPhotoPath;
            this.hashDocumentCropped = hashDocumentCropped;
        }

        public final long getId() {
            return this.f74id;
        }

        public final String getPhotoPath() {
            return this.photoPath;
        }

        public final String getCroppedPhotoPath() {
            return this.croppedPhotoPath;
        }

        public final String getHashDocumentCropped() {
            return this.hashDocumentCropped;
        }
    }

    public final MutableLiveData<SpecialOccurrenceUpdateModel> getSpecialOccurrenceUpdateModel() {
        return this.specialOccurrenceUpdateModel;
    }

    public final LiveData<Resource<SpecialOccurrencePage>> getSpecialOccurrenceUpdateResource() {
        return this.specialOccurrenceUpdateResource;
    }

    public final void finishUpdatingSpecialOccurrence() {
        this.specialOccurrenceUpdateModel.postValue(null);
    }

    /* compiled from: CaptureSpecialOccurrenceImageUseCase.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001d"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/CaptureSpecialOccurrenceImageUseCase$SpecialOccurrenceInsertModel;", "", JobType.ID, "", "kodeTps", "", "photoPath", "croppedPhotoPath", "hashDocumentCropped", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCroppedPhotoPath", "()Ljava/lang/String;", "getHashDocumentCropped", "getId", "()J", "getKodeTps", "getPhotoPath", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class SpecialOccurrenceInsertModel {
        private final String croppedPhotoPath;
        private final String hashDocumentCropped;

        /* renamed from: id */
        private final long f73id;
        private final String kodeTps;
        private final String photoPath;

        public static /* synthetic */ SpecialOccurrenceInsertModel copy$default(SpecialOccurrenceInsertModel specialOccurrenceInsertModel, long j, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                j = specialOccurrenceInsertModel.f73id;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                str = specialOccurrenceInsertModel.kodeTps;
            }
            String str5 = str;
            if ((i & 4) != 0) {
                str2 = specialOccurrenceInsertModel.photoPath;
            }
            String str6 = str2;
            if ((i & 8) != 0) {
                str3 = specialOccurrenceInsertModel.croppedPhotoPath;
            }
            String str7 = str3;
            if ((i & 16) != 0) {
                str4 = specialOccurrenceInsertModel.hashDocumentCropped;
            }
            return specialOccurrenceInsertModel.copy(j2, str5, str6, str7, str4);
        }

        public final long component1() {
            return this.f73id;
        }

        public final String component2() {
            return this.kodeTps;
        }

        public final String component3() {
            return this.photoPath;
        }

        public final String component4() {
            return this.croppedPhotoPath;
        }

        public final String component5() {
            return this.hashDocumentCropped;
        }

        public final SpecialOccurrenceInsertModel copy(long j, String kodeTps, String photoPath, String croppedPhotoPath, String hashDocumentCropped) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(photoPath, "photoPath");
            Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
            Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
            return new SpecialOccurrenceInsertModel(j, kodeTps, photoPath, croppedPhotoPath, hashDocumentCropped);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SpecialOccurrenceInsertModel) {
                SpecialOccurrenceInsertModel specialOccurrenceInsertModel = (SpecialOccurrenceInsertModel) obj;
                return this.f73id == specialOccurrenceInsertModel.f73id && Intrinsics.areEqual(this.kodeTps, specialOccurrenceInsertModel.kodeTps) && Intrinsics.areEqual(this.photoPath, specialOccurrenceInsertModel.photoPath) && Intrinsics.areEqual(this.croppedPhotoPath, specialOccurrenceInsertModel.croppedPhotoPath) && Intrinsics.areEqual(this.hashDocumentCropped, specialOccurrenceInsertModel.hashDocumentCropped);
            }
            return false;
        }

        public int hashCode() {
            return (((((((Long.hashCode(this.f73id) * 31) + this.kodeTps.hashCode()) * 31) + this.photoPath.hashCode()) * 31) + this.croppedPhotoPath.hashCode()) * 31) + this.hashDocumentCropped.hashCode();
        }

        public String toString() {
            long j = this.f73id;
            String str = this.kodeTps;
            String str2 = this.photoPath;
            String str3 = this.croppedPhotoPath;
            return "SpecialOccurrenceInsertModel(id=" + j + ", kodeTps=" + str + ", photoPath=" + str2 + ", croppedPhotoPath=" + str3 + ", hashDocumentCropped=" + this.hashDocumentCropped + ")";
        }

        public SpecialOccurrenceInsertModel(long j, String kodeTps, String photoPath, String croppedPhotoPath, String hashDocumentCropped) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(photoPath, "photoPath");
            Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
            Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
            this.f73id = j;
            this.kodeTps = kodeTps;
            this.photoPath = photoPath;
            this.croppedPhotoPath = croppedPhotoPath;
            this.hashDocumentCropped = hashDocumentCropped;
        }

        public final long getId() {
            return this.f73id;
        }

        public final String getKodeTps() {
            return this.kodeTps;
        }

        public final String getPhotoPath() {
            return this.photoPath;
        }

        public final String getCroppedPhotoPath() {
            return this.croppedPhotoPath;
        }

        public final String getHashDocumentCropped() {
            return this.hashDocumentCropped;
        }
    }

    public final MutableLiveData<SpecialOccurrenceInsertModel> getSpecialOccurrenceInsertModel() {
        return this.specialOccurrenceInsertModel;
    }

    public final LiveData<Resource<SpecialOccurrencePage>> getSpecialOccurrenceInsertResource() {
        return this.specialOccurrenceInsertResource;
    }

    public final void finishInsertingSpecialOccurrence() {
        this.specialOccurrenceInsertModel.postValue(null);
    }
}
