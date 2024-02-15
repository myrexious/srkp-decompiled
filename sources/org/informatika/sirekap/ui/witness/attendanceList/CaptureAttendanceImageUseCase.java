package org.informatika.sirekap.ui.witness.attendanceList;

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
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.AttendancePage;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.repository.WitnessRepository;
import org.informatika.sirekap.support.FileUtil;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.security.keystore.KeystoreManager;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.dashboard.CaptureImageUseCase;
import org.informatika.sirekap.ui.witness.attendanceList.CaptureAttendanceImageUseCase;

/* compiled from: CaptureAttendanceImageUseCase.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002'(B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ0\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\b\u0010$\u001a\u0004\u0018\u00010\u00132\u0006\u0010%\u001a\u00020&R\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u001f\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/CaptureAttendanceImageUseCase;", "Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase;", "context", "Landroid/content/Context;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "witnessRepository", "Lorg/informatika/sirekap/repository/WitnessRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/repository/ElectionRepository;Lorg/informatika/sirekap/repository/WitnessRepository;)V", "attendanceInsertModel", "Landroidx/lifecycle/MutableLiveData;", "Lorg/informatika/sirekap/ui/witness/attendanceList/CaptureAttendanceImageUseCase$AttendanceInsertModel;", "getAttendanceInsertModel", "()Landroidx/lifecycle/MutableLiveData;", "attendanceInsertResource", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/AttendancePage;", "getAttendanceInsertResource", "()Landroidx/lifecycle/LiveData;", "attendanceUpdateModel", "Lorg/informatika/sirekap/ui/witness/attendanceList/CaptureAttendanceImageUseCase$AttendanceUpdateModel;", "getAttendanceUpdateModel", "attendanceUpdateResource", "getAttendanceUpdateResource", "finishInsertingAttendance", "", "finishUpdatingAttendance", "saveCroppedPhoto", "bitmap", "Landroid/graphics/Bitmap;", "jenisPemilihan", "", "kodeTps", AuthorizationRequest.Display.PAGE, "urutan", "", "AttendanceInsertModel", "AttendanceUpdateModel", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CaptureAttendanceImageUseCase extends CaptureImageUseCase {
    private final MutableLiveData<AttendanceInsertModel> attendanceInsertModel;
    private final LiveData<Resource<AttendancePage>> attendanceInsertResource;
    private final MutableLiveData<AttendanceUpdateModel> attendanceUpdateModel;
    private final LiveData<Resource<AttendancePage>> attendanceUpdateResource;
    private final Context context;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final WitnessRepository witnessRepository;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaptureAttendanceImageUseCase(Context context, EncryptedSharedPreferences encryptedSharedPreferences, ElectionRepository electionRepository, WitnessRepository witnessRepository) {
        super(context, encryptedSharedPreferences, electionRepository);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(witnessRepository, "witnessRepository");
        this.context = context;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.witnessRepository = witnessRepository;
        MutableLiveData<AttendanceUpdateModel> mutableLiveData = new MutableLiveData<>(null);
        this.attendanceUpdateModel = mutableLiveData;
        this.attendanceUpdateResource = Transformations.switchMap(mutableLiveData, new Function1<AttendanceUpdateModel, LiveData<Resource<AttendancePage>>>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.CaptureAttendanceImageUseCase$attendanceUpdateResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<AttendancePage>> invoke(CaptureAttendanceImageUseCase.AttendanceUpdateModel attendanceUpdateModel) {
                WitnessRepository witnessRepository2;
                if (attendanceUpdateModel != null) {
                    witnessRepository2 = CaptureAttendanceImageUseCase.this.witnessRepository;
                    return witnessRepository2.updateAttendancePagePhoto(attendanceUpdateModel.getAttendeesPageId(), attendanceUpdateModel.getPhotoPath(), attendanceUpdateModel.getCroppedPhotoPath(), attendanceUpdateModel.getHashDocumentCropped());
                }
                return AbsentLiveData.Companion.create();
            }
        });
        MutableLiveData<AttendanceInsertModel> mutableLiveData2 = new MutableLiveData<>(null);
        this.attendanceInsertModel = mutableLiveData2;
        this.attendanceInsertResource = Transformations.switchMap(mutableLiveData2, new Function1<AttendanceInsertModel, LiveData<Resource<AttendancePage>>>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.CaptureAttendanceImageUseCase$attendanceInsertResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<AttendancePage>> invoke(CaptureAttendanceImageUseCase.AttendanceInsertModel attendanceInsertModel) {
                WitnessRepository witnessRepository2;
                if (attendanceInsertModel != null) {
                    witnessRepository2 = CaptureAttendanceImageUseCase.this.witnessRepository;
                    return witnessRepository2.insertAttendance(attendanceInsertModel.getAttendeesPageId(), attendanceInsertModel.getKodeTps(), attendanceInsertModel.getPhotoPath(), attendanceInsertModel.getCroppedPhotoPath(), attendanceInsertModel.getUrutan(), attendanceInsertModel.getHashDocumentCropped());
                }
                return AbsentLiveData.Companion.create();
            }
        });
    }

    public final void saveCroppedPhoto(Bitmap bitmap, String jenisPemilihan, String kodeTps, AttendancePage attendancePage, int i) {
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
                if (attendancePage != null) {
                    String photoPath = attendancePage.getPhotoPath();
                    FileUtil.deleteFile(StringsKt.replace$default(photoPath, ".jpg", ".png", false, 4, (Object) null));
                    FileUtil.deleteFile(photoPath);
                    String croppedPhotoPath = attendancePage.getCroppedPhotoPath();
                    FileUtil.deleteFile(StringsKt.replace$default(croppedPhotoPath, ".jpg", ".png", false, 4, (Object) null));
                    FileUtil.deleteFile(croppedPhotoPath);
                    long id2 = attendancePage.getId();
                    MutableLiveData<AttendanceUpdateModel> mutableLiveData = this.attendanceUpdateModel;
                    String absolutePath = saveCroppedImage.getAbsolutePath();
                    Intrinsics.checkNotNullExpressionValue(absolutePath, "croppedImage.absolutePath");
                    mutableLiveData.postValue(new AttendanceUpdateModel(id2, photoAbsolutePath, absolutePath, imageSignature));
                    return;
                }
                MutableLiveData<AttendanceInsertModel> mutableLiveData2 = this.attendanceInsertModel;
                String absolutePath2 = saveCroppedImage.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath2, "croppedImage.absolutePath");
                mutableLiveData2.postValue(new AttendanceInsertModel(new Date().getTime() + RangesKt.random(new IntRange(100, RoomDatabase.MAX_BIND_PARAMETER_CNT), Random.Default), kodeTps, photoAbsolutePath, absolutePath2, i, imageSignature));
                return;
            }
            FileUtil.deleteFile(photoAbsolutePath);
            getCropPhotoResult().postValue(new CaptureImageUseCase.CropPhotoResult(false, this.context.getString(R.string.error_signature_not_exist, 1)));
        }
    }

    /* compiled from: CaptureAttendanceImageUseCase.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/CaptureAttendanceImageUseCase$AttendanceUpdateModel;", "", "attendeesPageId", "", "photoPath", "", "croppedPhotoPath", "hashDocumentCropped", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAttendeesPageId", "()J", "getCroppedPhotoPath", "()Ljava/lang/String;", "getHashDocumentCropped", "getPhotoPath", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class AttendanceUpdateModel {
        private final long attendeesPageId;
        private final String croppedPhotoPath;
        private final String hashDocumentCropped;
        private final String photoPath;

        public static /* synthetic */ AttendanceUpdateModel copy$default(AttendanceUpdateModel attendanceUpdateModel, long j, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                j = attendanceUpdateModel.attendeesPageId;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                str = attendanceUpdateModel.photoPath;
            }
            String str4 = str;
            if ((i & 4) != 0) {
                str2 = attendanceUpdateModel.croppedPhotoPath;
            }
            String str5 = str2;
            if ((i & 8) != 0) {
                str3 = attendanceUpdateModel.hashDocumentCropped;
            }
            return attendanceUpdateModel.copy(j2, str4, str5, str3);
        }

        public final long component1() {
            return this.attendeesPageId;
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

        public final AttendanceUpdateModel copy(long j, String photoPath, String croppedPhotoPath, String hashDocumentCropped) {
            Intrinsics.checkNotNullParameter(photoPath, "photoPath");
            Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
            Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
            return new AttendanceUpdateModel(j, photoPath, croppedPhotoPath, hashDocumentCropped);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof AttendanceUpdateModel) {
                AttendanceUpdateModel attendanceUpdateModel = (AttendanceUpdateModel) obj;
                return this.attendeesPageId == attendanceUpdateModel.attendeesPageId && Intrinsics.areEqual(this.photoPath, attendanceUpdateModel.photoPath) && Intrinsics.areEqual(this.croppedPhotoPath, attendanceUpdateModel.croppedPhotoPath) && Intrinsics.areEqual(this.hashDocumentCropped, attendanceUpdateModel.hashDocumentCropped);
            }
            return false;
        }

        public int hashCode() {
            return (((((Long.hashCode(this.attendeesPageId) * 31) + this.photoPath.hashCode()) * 31) + this.croppedPhotoPath.hashCode()) * 31) + this.hashDocumentCropped.hashCode();
        }

        public String toString() {
            long j = this.attendeesPageId;
            String str = this.photoPath;
            String str2 = this.croppedPhotoPath;
            return "AttendanceUpdateModel(attendeesPageId=" + j + ", photoPath=" + str + ", croppedPhotoPath=" + str2 + ", hashDocumentCropped=" + this.hashDocumentCropped + ")";
        }

        public AttendanceUpdateModel(long j, String photoPath, String croppedPhotoPath, String hashDocumentCropped) {
            Intrinsics.checkNotNullParameter(photoPath, "photoPath");
            Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
            Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
            this.attendeesPageId = j;
            this.photoPath = photoPath;
            this.croppedPhotoPath = croppedPhotoPath;
            this.hashDocumentCropped = hashDocumentCropped;
        }

        public final long getAttendeesPageId() {
            return this.attendeesPageId;
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

    public final MutableLiveData<AttendanceUpdateModel> getAttendanceUpdateModel() {
        return this.attendanceUpdateModel;
    }

    public final LiveData<Resource<AttendancePage>> getAttendanceUpdateResource() {
        return this.attendanceUpdateResource;
    }

    public final void finishUpdatingAttendance() {
        this.attendanceUpdateModel.postValue(null);
    }

    /* compiled from: CaptureAttendanceImageUseCase.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\tHÖ\u0001J\t\u0010 \u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/CaptureAttendanceImageUseCase$AttendanceInsertModel;", "", "attendeesPageId", "", "kodeTps", "", "photoPath", "croppedPhotoPath", "urutan", "", "hashDocumentCropped", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getAttendeesPageId", "()J", "getCroppedPhotoPath", "()Ljava/lang/String;", "getHashDocumentCropped", "getKodeTps", "getPhotoPath", "getUrutan", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class AttendanceInsertModel {
        private final long attendeesPageId;
        private final String croppedPhotoPath;
        private final String hashDocumentCropped;
        private final String kodeTps;
        private final String photoPath;
        private final int urutan;

        public final long component1() {
            return this.attendeesPageId;
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

        public final int component5() {
            return this.urutan;
        }

        public final String component6() {
            return this.hashDocumentCropped;
        }

        public final AttendanceInsertModel copy(long j, String kodeTps, String photoPath, String croppedPhotoPath, int i, String hashDocumentCropped) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(photoPath, "photoPath");
            Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
            Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
            return new AttendanceInsertModel(j, kodeTps, photoPath, croppedPhotoPath, i, hashDocumentCropped);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof AttendanceInsertModel) {
                AttendanceInsertModel attendanceInsertModel = (AttendanceInsertModel) obj;
                return this.attendeesPageId == attendanceInsertModel.attendeesPageId && Intrinsics.areEqual(this.kodeTps, attendanceInsertModel.kodeTps) && Intrinsics.areEqual(this.photoPath, attendanceInsertModel.photoPath) && Intrinsics.areEqual(this.croppedPhotoPath, attendanceInsertModel.croppedPhotoPath) && this.urutan == attendanceInsertModel.urutan && Intrinsics.areEqual(this.hashDocumentCropped, attendanceInsertModel.hashDocumentCropped);
            }
            return false;
        }

        public int hashCode() {
            return (((((((((Long.hashCode(this.attendeesPageId) * 31) + this.kodeTps.hashCode()) * 31) + this.photoPath.hashCode()) * 31) + this.croppedPhotoPath.hashCode()) * 31) + Integer.hashCode(this.urutan)) * 31) + this.hashDocumentCropped.hashCode();
        }

        public String toString() {
            long j = this.attendeesPageId;
            String str = this.kodeTps;
            String str2 = this.photoPath;
            String str3 = this.croppedPhotoPath;
            int i = this.urutan;
            return "AttendanceInsertModel(attendeesPageId=" + j + ", kodeTps=" + str + ", photoPath=" + str2 + ", croppedPhotoPath=" + str3 + ", urutan=" + i + ", hashDocumentCropped=" + this.hashDocumentCropped + ")";
        }

        public AttendanceInsertModel(long j, String kodeTps, String photoPath, String croppedPhotoPath, int i, String hashDocumentCropped) {
            Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
            Intrinsics.checkNotNullParameter(photoPath, "photoPath");
            Intrinsics.checkNotNullParameter(croppedPhotoPath, "croppedPhotoPath");
            Intrinsics.checkNotNullParameter(hashDocumentCropped, "hashDocumentCropped");
            this.attendeesPageId = j;
            this.kodeTps = kodeTps;
            this.photoPath = photoPath;
            this.croppedPhotoPath = croppedPhotoPath;
            this.urutan = i;
            this.hashDocumentCropped = hashDocumentCropped;
        }

        public final long getAttendeesPageId() {
            return this.attendeesPageId;
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

        public final int getUrutan() {
            return this.urutan;
        }

        public final String getHashDocumentCropped() {
            return this.hashDocumentCropped;
        }
    }

    public final MutableLiveData<AttendanceInsertModel> getAttendanceInsertModel() {
        return this.attendanceInsertModel;
    }

    public final LiveData<Resource<AttendancePage>> getAttendanceInsertResource() {
        return this.attendanceInsertResource;
    }

    public final void finishInsertingAttendance() {
        this.attendanceInsertModel.postValue(null);
    }
}
