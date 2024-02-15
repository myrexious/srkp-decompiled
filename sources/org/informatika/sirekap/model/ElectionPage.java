package org.informatika.sirekap.model;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.vision.VisionUtil;

/* compiled from: ElectionPage.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\bF\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0087\b\u0018\u0000 f2\u00020\u0001:\u0001fB/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tB½\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0011\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u001aJ\u0006\u0010<\u001a\u00020\u0011J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0011HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0002\u00102J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010F\u001a\u00020\u0011HÆ\u0003J\t\u0010G\u001a\u00020\u0011HÆ\u0003J\t\u0010H\u001a\u00020\u0003HÆ\u0003J\u0010\u0010I\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001cJ\t\u0010J\u001a\u00020\u0003HÆ\u0003J\t\u0010K\u001a\u00020\u0007HÆ\u0003J\t\u0010L\u001a\u00020\u0007HÆ\u0003J\t\u0010M\u001a\u00020\u0007HÆ\u0003J\t\u0010N\u001a\u00020\u0007HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jê\u0001\u0010Q\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0018\u001a\u00020\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010RJ\u0013\u0010S\u001a\u00020\u00112\b\u0010T\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010U\u001a\u00020\u0003J\u000e\u0010V\u001a\u00020\u00032\u0006\u0010W\u001a\u00020XJ\u0006\u0010Y\u001a\u00020\u0003J\u000e\u0010Z\u001a\u00020\u00072\u0006\u0010[\u001a\u00020\u0007J\u0016\u0010\\\u001a\u00020\u00032\u0006\u0010W\u001a\u00020X2\u0006\u0010[\u001a\u00020\u0007J\u0006\u0010]\u001a\u00020\u0011J\t\u0010^\u001a\u00020\u0007HÖ\u0001J\u0006\u0010_\u001a\u00020\u0011J\u0006\u0010`\u001a\u00020\u0011J\u0006\u0010a\u001a\u00020\u0011J\u0006\u0010b\u001a\u00020\u0011J\u0006\u0010c\u001a\u00020\u0011J\u0006\u0010d\u001a\u00020\u0011J\t\u0010e\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0019\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001f\"\u0004\b#\u0010!R\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0011\u0010\u0018\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001f\"\u0004\b/\u0010!R\u0011\u0010\u0017\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010*R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010*\"\u0004\b0\u00101R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0011¢\u0006\n\n\u0002\u00103\u001a\u0004\b\u0013\u00102R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b5\u0010%R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b6\u0010%R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u001fR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u001fR\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010%\"\u0004\b;\u0010'¨\u0006g"}, d2 = {"Lorg/informatika/sirekap/model/ElectionPage;", "", JobType.ID, "", "jenisPemilihan", "electionId", "kind", "", "number", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "currentStageIndex", "takePhotoAttempt", "photoPath", "croppedPhotoPath", "correctedPhotoPath", "perspectiveCorrectionError", "isCorrected", "", "idImage", "isSaved", "signatureCroppedPhoto", "hashDocumentCropped", "hashDocumentCorrected", "isContinueVerify", "hasVisionError", "aprilTagCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/Integer;)V", "getAprilTagCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCorrectedPhotoPath", "()Ljava/lang/String;", "setCorrectedPhotoPath", "(Ljava/lang/String;)V", "getCroppedPhotoPath", "setCroppedPhotoPath", "getCurrentStageIndex", "()I", "setCurrentStageIndex", "(I)V", "getElectionId", "getHasVisionError", "()Z", "getHashDocumentCorrected", "getHashDocumentCropped", "getId", "getIdImage", "setIdImage", "setCorrected", "(Z)V", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getJenisPemilihan", "getKind", "getNumber", "getPerspectiveCorrectionError", "getPhotoPath", "getSignatureCroppedPhoto", "getTakePhotoAttempt", "setTakePhotoAttempt", "canTakePhoto", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/Integer;)Lorg/informatika/sirekap/model/ElectionPage;", "equals", "other", "getCurrentPageStageType", "getKindDescription", "context", "Landroid/content/Context;", "getKindVisionTableName", "getPageNumber", "jmlLembar", "getPageNumberJmlLembar", "hasVerifyStep", "hashCode", "isDone", "isImageSentLocally", "isPerspectiveCorrectionValid", "isPhotoSent", "isPhotoTaken", "isVerified", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ElectionPage {
    public static final int CURRENT_STAGE_INDEX_IMAGE_SENT = 2;
    public static final int CURRENT_STAGE_INDEX_IMAGE_TAKEN = 1;
    public static final int CURRENT_STAGE_INDEX_IMAGE_VERIFIED = 3;
    public static final int CURRENT_STAGE_INDEX_NOT_STARTED = 0;
    public static final Companion Companion = new Companion(null);
    public static final int JENIS_IMAGE_1A = 10;
    public static final int JENIS_IMAGE_1B = 15;
    public static final int JENIS_IMAGE_2 = 20;
    public static final int JENIS_IMAGE_3A = 30;
    public static final int JENIS_IMAGE_3B = 35;
    public static final int MAX_TAKE_PHOTO_ATTEMPTS = 5;
    private final Integer aprilTagCode;
    private String correctedPhotoPath;
    private String croppedPhotoPath;
    private int currentStageIndex;
    private final String electionId;
    private final boolean hasVisionError;
    private final String hashDocumentCorrected;
    private final String hashDocumentCropped;

    /* renamed from: id */
    private final String f64id;
    private String idImage;
    private final boolean isContinueVerify;
    private boolean isCorrected;
    private final Boolean isSaved;
    private final String jenisPemilihan;
    private final int kind;
    private final int number;
    private final String perspectiveCorrectionError;
    private final String photoPath;
    private final String signatureCroppedPhoto;
    private int takePhotoAttempt;

    public final String component1() {
        return this.f64id;
    }

    public final String component10() {
        return this.correctedPhotoPath;
    }

    public final String component11() {
        return this.perspectiveCorrectionError;
    }

    public final boolean component12() {
        return this.isCorrected;
    }

    public final String component13() {
        return this.idImage;
    }

    public final Boolean component14() {
        return this.isSaved;
    }

    public final String component15() {
        return this.signatureCroppedPhoto;
    }

    public final String component16() {
        return this.hashDocumentCropped;
    }

    public final String component17() {
        return this.hashDocumentCorrected;
    }

    public final boolean component18() {
        return this.isContinueVerify;
    }

    public final boolean component19() {
        return this.hasVisionError;
    }

    public final String component2() {
        return this.jenisPemilihan;
    }

    public final Integer component20() {
        return this.aprilTagCode;
    }

    public final String component3() {
        return this.electionId;
    }

    public final int component4() {
        return this.kind;
    }

    public final int component5() {
        return this.number;
    }

    public final int component6() {
        return this.currentStageIndex;
    }

    public final int component7() {
        return this.takePhotoAttempt;
    }

    public final String component8() {
        return this.photoPath;
    }

    public final String component9() {
        return this.croppedPhotoPath;
    }

    public final ElectionPage copy(String id2, String jenisPemilihan, String electionId, int i, int i2, int i3, int i4, String str, String str2, String str3, String str4, boolean z, String str5, Boolean bool, String str6, String str7, String str8, boolean z2, boolean z3, Integer num) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        return new ElectionPage(id2, jenisPemilihan, electionId, i, i2, i3, i4, str, str2, str3, str4, z, str5, bool, str6, str7, str8, z2, z3, num);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ElectionPage) {
            ElectionPage electionPage = (ElectionPage) obj;
            return Intrinsics.areEqual(this.f64id, electionPage.f64id) && Intrinsics.areEqual(this.jenisPemilihan, electionPage.jenisPemilihan) && Intrinsics.areEqual(this.electionId, electionPage.electionId) && this.kind == electionPage.kind && this.number == electionPage.number && this.currentStageIndex == electionPage.currentStageIndex && this.takePhotoAttempt == electionPage.takePhotoAttempt && Intrinsics.areEqual(this.photoPath, electionPage.photoPath) && Intrinsics.areEqual(this.croppedPhotoPath, electionPage.croppedPhotoPath) && Intrinsics.areEqual(this.correctedPhotoPath, electionPage.correctedPhotoPath) && Intrinsics.areEqual(this.perspectiveCorrectionError, electionPage.perspectiveCorrectionError) && this.isCorrected == electionPage.isCorrected && Intrinsics.areEqual(this.idImage, electionPage.idImage) && Intrinsics.areEqual(this.isSaved, electionPage.isSaved) && Intrinsics.areEqual(this.signatureCroppedPhoto, electionPage.signatureCroppedPhoto) && Intrinsics.areEqual(this.hashDocumentCropped, electionPage.hashDocumentCropped) && Intrinsics.areEqual(this.hashDocumentCorrected, electionPage.hashDocumentCorrected) && this.isContinueVerify == electionPage.isContinueVerify && this.hasVisionError == electionPage.hasVisionError && Intrinsics.areEqual(this.aprilTagCode, electionPage.aprilTagCode);
        }
        return false;
    }

    public final boolean hasVerifyStep() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((((((this.f64id.hashCode() * 31) + this.jenisPemilihan.hashCode()) * 31) + this.electionId.hashCode()) * 31) + Integer.hashCode(this.kind)) * 31) + Integer.hashCode(this.number)) * 31) + Integer.hashCode(this.currentStageIndex)) * 31) + Integer.hashCode(this.takePhotoAttempt)) * 31;
        String str = this.photoPath;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.croppedPhotoPath;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.correctedPhotoPath;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.perspectiveCorrectionError;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        boolean z = this.isCorrected;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode5 + i) * 31;
        String str5 = this.idImage;
        int hashCode6 = (i2 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Boolean bool = this.isSaved;
        int hashCode7 = (hashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str6 = this.signatureCroppedPhoto;
        int hashCode8 = (hashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.hashDocumentCropped;
        int hashCode9 = (hashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.hashDocumentCorrected;
        int hashCode10 = (hashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31;
        boolean z2 = this.isContinueVerify;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (hashCode10 + i3) * 31;
        boolean z3 = this.hasVisionError;
        int i5 = (i4 + (z3 ? 1 : z3 ? 1 : 0)) * 31;
        Integer num = this.aprilTagCode;
        return i5 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        String str = this.f64id;
        String str2 = this.jenisPemilihan;
        String str3 = this.electionId;
        int i = this.kind;
        int i2 = this.number;
        int i3 = this.currentStageIndex;
        int i4 = this.takePhotoAttempt;
        String str4 = this.photoPath;
        String str5 = this.croppedPhotoPath;
        String str6 = this.correctedPhotoPath;
        String str7 = this.perspectiveCorrectionError;
        boolean z = this.isCorrected;
        String str8 = this.idImage;
        Boolean bool = this.isSaved;
        String str9 = this.signatureCroppedPhoto;
        String str10 = this.hashDocumentCropped;
        String str11 = this.hashDocumentCorrected;
        boolean z2 = this.isContinueVerify;
        boolean z3 = this.hasVisionError;
        return "ElectionPage(id=" + str + ", jenisPemilihan=" + str2 + ", electionId=" + str3 + ", kind=" + i + ", number=" + i2 + ", currentStageIndex=" + i3 + ", takePhotoAttempt=" + i4 + ", photoPath=" + str4 + ", croppedPhotoPath=" + str5 + ", correctedPhotoPath=" + str6 + ", perspectiveCorrectionError=" + str7 + ", isCorrected=" + z + ", idImage=" + str8 + ", isSaved=" + bool + ", signatureCroppedPhoto=" + str9 + ", hashDocumentCropped=" + str10 + ", hashDocumentCorrected=" + str11 + ", isContinueVerify=" + z2 + ", hasVisionError=" + z3 + ", aprilTagCode=" + this.aprilTagCode + ")";
    }

    public ElectionPage(String id2, String jenisPemilihan, String electionId, int i, int i2, int i3, int i4, String str, String str2, String str3, String str4, boolean z, String str5, Boolean bool, String str6, String str7, String str8, boolean z2, boolean z3, Integer num) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(electionId, "electionId");
        this.f64id = id2;
        this.jenisPemilihan = jenisPemilihan;
        this.electionId = electionId;
        this.kind = i;
        this.number = i2;
        this.currentStageIndex = i3;
        this.takePhotoAttempt = i4;
        this.photoPath = str;
        this.croppedPhotoPath = str2;
        this.correctedPhotoPath = str3;
        this.perspectiveCorrectionError = str4;
        this.isCorrected = z;
        this.idImage = str5;
        this.isSaved = bool;
        this.signatureCroppedPhoto = str6;
        this.hashDocumentCropped = str7;
        this.hashDocumentCorrected = str8;
        this.isContinueVerify = z2;
        this.hasVisionError = z3;
        this.aprilTagCode = num;
    }

    public /* synthetic */ ElectionPage(String str, String str2, String str3, int i, int i2, int i3, int i4, String str4, String str5, String str6, String str7, boolean z, String str8, Boolean bool, String str9, String str10, String str11, boolean z2, boolean z3, Integer num, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, i, i2, i3, i4, str4, str5, str6, str7, z, str8, bool, str9, str10, str11, (i5 & 131072) != 0 ? false : z2, (i5 & 262144) != 0 ? false : z3, num);
    }

    public final String getId() {
        return this.f64id;
    }

    public final String getJenisPemilihan() {
        return this.jenisPemilihan;
    }

    public final String getElectionId() {
        return this.electionId;
    }

    public final int getKind() {
        return this.kind;
    }

    public final int getNumber() {
        return this.number;
    }

    public final int getCurrentStageIndex() {
        return this.currentStageIndex;
    }

    public final void setCurrentStageIndex(int i) {
        this.currentStageIndex = i;
    }

    public final int getTakePhotoAttempt() {
        return this.takePhotoAttempt;
    }

    public final void setTakePhotoAttempt(int i) {
        this.takePhotoAttempt = i;
    }

    public final String getPhotoPath() {
        return this.photoPath;
    }

    public final String getCroppedPhotoPath() {
        return this.croppedPhotoPath;
    }

    public final void setCroppedPhotoPath(String str) {
        this.croppedPhotoPath = str;
    }

    public final String getCorrectedPhotoPath() {
        return this.correctedPhotoPath;
    }

    public final void setCorrectedPhotoPath(String str) {
        this.correctedPhotoPath = str;
    }

    public final String getPerspectiveCorrectionError() {
        return this.perspectiveCorrectionError;
    }

    public final boolean isCorrected() {
        return this.isCorrected;
    }

    public final void setCorrected(boolean z) {
        this.isCorrected = z;
    }

    public final String getIdImage() {
        return this.idImage;
    }

    public final void setIdImage(String str) {
        this.idImage = str;
    }

    public final Boolean isSaved() {
        return this.isSaved;
    }

    public final String getSignatureCroppedPhoto() {
        return this.signatureCroppedPhoto;
    }

    public final String getHashDocumentCropped() {
        return this.hashDocumentCropped;
    }

    public final String getHashDocumentCorrected() {
        return this.hashDocumentCorrected;
    }

    public final boolean isContinueVerify() {
        return this.isContinueVerify;
    }

    public final boolean getHasVisionError() {
        return this.hasVisionError;
    }

    public final Integer getAprilTagCode() {
        return this.aprilTagCode;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ElectionPage(String id2, String jenisPemilihan, String electionId, int i, int i2) {
        this(id2, jenisPemilihan, electionId, i, i2, 0, 0, null, null, null, null, false, null, null, null, null, null, false, false, null, 393216, null);
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(electionId, "electionId");
    }

    public final boolean isImageSentLocally() {
        String str = this.idImage;
        return str != null && StringsKt.contains$default((CharSequence) str, (CharSequence) "-", false, 2, (Object) null);
    }

    /* compiled from: ElectionPage.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/model/ElectionPage$Companion;", "", "()V", "CURRENT_STAGE_INDEX_IMAGE_SENT", "", "CURRENT_STAGE_INDEX_IMAGE_TAKEN", "CURRENT_STAGE_INDEX_IMAGE_VERIFIED", "CURRENT_STAGE_INDEX_NOT_STARTED", "JENIS_IMAGE_1A", "JENIS_IMAGE_1B", "JENIS_IMAGE_2", "JENIS_IMAGE_3A", "JENIS_IMAGE_3B", "MAX_TAKE_PHOTO_ATTEMPTS", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final boolean isPhotoTaken() {
        return this.currentStageIndex >= 1;
    }

    public final boolean isPhotoSent() {
        return this.currentStageIndex >= 2;
    }

    public final boolean isVerified() {
        return this.currentStageIndex >= 3;
    }

    public final boolean isDone() {
        return this.currentStageIndex >= 3;
    }

    public final boolean canTakePhoto() {
        return this.takePhotoAttempt < 5;
    }

    public final boolean isPerspectiveCorrectionValid() {
        String str = this.correctedPhotoPath;
        return !(str == null || StringsKt.isBlank(str));
    }

    public final String getCurrentPageStageType() {
        int i = this.currentStageIndex;
        return i != 0 ? i != 1 ? ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_VERIFY : ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND : ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_PHOTO;
    }

    public final String getKindDescription(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i = this.kind;
        if (i == 10) {
            String string = context.getString(R.string.election_page_type_kind_1a);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ection_page_type_kind_1a)");
            return string;
        } else if (i == 20) {
            String string2 = context.getString(R.string.election_page_type_kind_2);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…lection_page_type_kind_2)");
            return string2;
        } else if (i != 30) {
            return "";
        } else {
            String string3 = context.getString(R.string.election_page_type_kind_3a);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri…ection_page_type_kind_3a)");
            return string3;
        }
    }

    public final String getPageNumberJmlLembar(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i2 = this.kind;
        if (i2 == 10) {
            i = this.number;
        } else if (i2 == 20) {
            i = this.number + 1;
        } else if (i2 != 30) {
            i = 0;
        }
        if (i != 0) {
            String string = context.getString(R.string.election_page_number, Integer.valueOf(i));
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ion_page_number, pageNum)");
            return string;
        }
        return "";
    }

    public final int getPageNumber(int i) {
        int i2 = this.kind;
        if (i2 != 10) {
            if (i2 != 20) {
                if (i2 != 30) {
                    return 0;
                }
                return i;
            }
            return this.number + 1;
        }
        return this.number;
    }

    public final String getKindVisionTableName() {
        int i = this.kind;
        if (i == 10) {
            String VISION_UTIL_TYPE_ADMIN = VisionUtil.VISION_UTIL_TYPE_ADMIN;
            Intrinsics.checkNotNullExpressionValue(VISION_UTIL_TYPE_ADMIN, "VISION_UTIL_TYPE_ADMIN");
            return VISION_UTIL_TYPE_ADMIN;
        } else if (i == 20) {
            String VISION_UTIL_TYPE_TALLY = VisionUtil.VISION_UTIL_TYPE_TALLY;
            Intrinsics.checkNotNullExpressionValue(VISION_UTIL_TYPE_TALLY, "VISION_UTIL_TYPE_TALLY");
            return VISION_UTIL_TYPE_TALLY;
        } else {
            String VISION_UTIL_TYPE_TABULASI = VisionUtil.VISION_UTIL_TYPE_TABULASI;
            Intrinsics.checkNotNullExpressionValue(VISION_UTIL_TYPE_TABULASI, "VISION_UTIL_TYPE_TABULASI");
            return VISION_UTIL_TYPE_TABULASI;
        }
    }
}
