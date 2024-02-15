package org.informatika.sirekap.ui.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.lifecycle.MutableLiveData;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import net.openid.appauth.AuthorizationRequest;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;

/* compiled from: AprilTagCheckUseCase.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u001f B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00120\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/AprilTagCheckUseCase;", "", "context", "Landroid/content/Context;", "captureImageUseCase", "Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/content/Context;Lorg/informatika/sirekap/ui/dashboard/CaptureImageUseCase;Lkotlinx/coroutines/CoroutineScope;)V", "checkResult", "Landroidx/lifecycle/MutableLiveData;", "Lorg/informatika/sirekap/ui/dashboard/AprilTagCheckUseCase$CheckResult;", "getCheckResult", "()Landroidx/lifecycle/MutableLiveData;", "detectionResult", "Lorg/informatika/sirekap/ui/dashboard/AprilTagCheckUseCase$DetectResult;", "getDetectionResult", "elections", "", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getElections", "check", "", "pemilihan", "", "pageNum", "", "detect", "bitmap", "Landroid/graphics/Bitmap;", "finishAprilTagCheck", "CheckResult", "DetectResult", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AprilTagCheckUseCase {
    private final CaptureImageUseCase captureImageUseCase;
    private final MutableLiveData<CheckResult> checkResult;
    private final Context context;
    private final MutableLiveData<DetectResult> detectionResult;
    private final MutableLiveData<List<ElectionWithRelation>> elections;
    private final CoroutineScope scope;

    public AprilTagCheckUseCase(Context context, CaptureImageUseCase captureImageUseCase, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(captureImageUseCase, "captureImageUseCase");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.context = context;
        this.captureImageUseCase = captureImageUseCase;
        this.scope = scope;
        this.elections = new MutableLiveData<>(null);
        this.checkResult = new MutableLiveData<>(null);
        this.detectionResult = new MutableLiveData<>(null);
    }

    public final MutableLiveData<List<ElectionWithRelation>> getElections() {
        return this.elections;
    }

    /* compiled from: AprilTagCheckUseCase.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\nHÆ\u0003JC\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/AprilTagCheckUseCase$CheckResult;", "", "isSuccess", "", "errorMessage", "", "errorType", "election", "Lorg/informatika/sirekap/model/Election;", AuthorizationRequest.Display.PAGE, "Lorg/informatika/sirekap/model/ElectionPage;", "(ZLjava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/model/Election;Lorg/informatika/sirekap/model/ElectionPage;)V", "getElection", "()Lorg/informatika/sirekap/model/Election;", "getErrorMessage", "()Ljava/lang/String;", "getErrorType", "()Z", "getPage", "()Lorg/informatika/sirekap/model/ElectionPage;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class CheckResult {
        private final Election election;
        private final String errorMessage;
        private final String errorType;
        private final boolean isSuccess;
        private final ElectionPage page;

        public static /* synthetic */ CheckResult copy$default(CheckResult checkResult, boolean z, String str, String str2, Election election, ElectionPage electionPage, int i, Object obj) {
            if ((i & 1) != 0) {
                z = checkResult.isSuccess;
            }
            if ((i & 2) != 0) {
                str = checkResult.errorMessage;
            }
            String str3 = str;
            if ((i & 4) != 0) {
                str2 = checkResult.errorType;
            }
            String str4 = str2;
            if ((i & 8) != 0) {
                election = checkResult.election;
            }
            Election election2 = election;
            if ((i & 16) != 0) {
                electionPage = checkResult.page;
            }
            return checkResult.copy(z, str3, str4, election2, electionPage);
        }

        public final boolean component1() {
            return this.isSuccess;
        }

        public final String component2() {
            return this.errorMessage;
        }

        public final String component3() {
            return this.errorType;
        }

        public final Election component4() {
            return this.election;
        }

        public final ElectionPage component5() {
            return this.page;
        }

        public final CheckResult copy(boolean z, String str, String str2, Election election, ElectionPage electionPage) {
            return new CheckResult(z, str, str2, election, electionPage);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof CheckResult) {
                CheckResult checkResult = (CheckResult) obj;
                return this.isSuccess == checkResult.isSuccess && Intrinsics.areEqual(this.errorMessage, checkResult.errorMessage) && Intrinsics.areEqual(this.errorType, checkResult.errorType) && Intrinsics.areEqual(this.election, checkResult.election) && Intrinsics.areEqual(this.page, checkResult.page);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v11 */
        public int hashCode() {
            boolean z = this.isSuccess;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.errorMessage;
            int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.errorType;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            Election election = this.election;
            int hashCode3 = (hashCode2 + (election == null ? 0 : election.hashCode())) * 31;
            ElectionPage electionPage = this.page;
            return hashCode3 + (electionPage != null ? electionPage.hashCode() : 0);
        }

        public String toString() {
            boolean z = this.isSuccess;
            String str = this.errorMessage;
            String str2 = this.errorType;
            Election election = this.election;
            return "CheckResult(isSuccess=" + z + ", errorMessage=" + str + ", errorType=" + str2 + ", election=" + election + ", page=" + this.page + ")";
        }

        public CheckResult(boolean z, String str, String str2, Election election, ElectionPage electionPage) {
            this.isSuccess = z;
            this.errorMessage = str;
            this.errorType = str2;
            this.election = election;
            this.page = electionPage;
        }

        public /* synthetic */ CheckResult(boolean z, String str, String str2, Election election, ElectionPage electionPage, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : election, (i & 16) != 0 ? null : electionPage);
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }

        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public final String getErrorType() {
            return this.errorType;
        }

        public final Election getElection() {
            return this.election;
        }

        public final ElectionPage getPage() {
            return this.page;
        }
    }

    public final MutableLiveData<CheckResult> getCheckResult() {
        return this.checkResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void check(String pemilihan, int i) {
        ElectionWithRelation electionWithRelation;
        boolean z;
        Object obj;
        Intrinsics.checkNotNullParameter(pemilihan, "pemilihan");
        List<ElectionWithRelation> value = this.elections.getValue();
        ElectionPage electionPage = null;
        if (value != null) {
            Iterator<T> it = value.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((ElectionWithRelation) obj).getElection().getPemilihan(), pemilihan)) {
                    break;
                }
            }
            electionWithRelation = (ElectionWithRelation) obj;
        } else {
            electionWithRelation = null;
        }
        if (electionWithRelation != null) {
            int jmlLembar = i == 99 ? electionWithRelation.getElection().getJmlLembar() : i;
            if (i == 25 && !electionWithRelation.getElection().isAceh() && (Intrinsics.areEqual(pemilihan, Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) || Intrinsics.areEqual(pemilihan, Election.ELECTION_PEMILIHAN_DPRD_KABKO))) {
                jmlLembar = i - 6;
            }
            Iterator<T> it2 = electionWithRelation.getPages().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                if (((ElectionPage) next).getPageNumber(electionWithRelation.getElection().getJmlLembar()) == jmlLembar) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    electionPage = next;
                    break;
                }
            }
            ElectionPage electionPage2 = electionPage;
            if (electionPage2 != null) {
                this.checkResult.postValue(new CheckResult(true, null, null, electionWithRelation.getElection(), electionPage2, 6, null));
                return;
            }
            this.checkResult.postValue(new CheckResult(false, this.context.getString(R.string.april_tag_page_number_error, Integer.valueOf(i), electionWithRelation.getElection().getDescription(this.context)), null, null, null, 28, null));
            this.captureImageUseCase.deletePreparedPhoto();
            return;
        }
        this.checkResult.postValue(new CheckResult(false, this.context.getString(R.string.april_tag_not_registered_error), null, null, null, 28, null));
        this.captureImageUseCase.deletePreparedPhoto();
    }

    public final void finishAprilTagCheck() {
        this.checkResult.postValue(null);
    }

    /* compiled from: AprilTagCheckUseCase.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000bJ<\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/ui/dashboard/AprilTagCheckUseCase$DetectResult;", "", "isSuccess", "", "pemilihan", "", "nomorHalaman", "", "aprilTagCode", "(ZLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getAprilTagCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "getNomorHalaman", "getPemilihan", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "(ZLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/informatika/sirekap/ui/dashboard/AprilTagCheckUseCase$DetectResult;", "equals", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class DetectResult {
        private final Integer aprilTagCode;
        private final boolean isSuccess;
        private final Integer nomorHalaman;
        private final String pemilihan;

        public static /* synthetic */ DetectResult copy$default(DetectResult detectResult, boolean z, String str, Integer num, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = detectResult.isSuccess;
            }
            if ((i & 2) != 0) {
                str = detectResult.pemilihan;
            }
            if ((i & 4) != 0) {
                num = detectResult.nomorHalaman;
            }
            if ((i & 8) != 0) {
                num2 = detectResult.aprilTagCode;
            }
            return detectResult.copy(z, str, num, num2);
        }

        public final boolean component1() {
            return this.isSuccess;
        }

        public final String component2() {
            return this.pemilihan;
        }

        public final Integer component3() {
            return this.nomorHalaman;
        }

        public final Integer component4() {
            return this.aprilTagCode;
        }

        public final DetectResult copy(boolean z, String str, Integer num, Integer num2) {
            return new DetectResult(z, str, num, num2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof DetectResult) {
                DetectResult detectResult = (DetectResult) obj;
                return this.isSuccess == detectResult.isSuccess && Intrinsics.areEqual(this.pemilihan, detectResult.pemilihan) && Intrinsics.areEqual(this.nomorHalaman, detectResult.nomorHalaman) && Intrinsics.areEqual(this.aprilTagCode, detectResult.aprilTagCode);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        public int hashCode() {
            boolean z = this.isSuccess;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.pemilihan;
            int hashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
            Integer num = this.nomorHalaman;
            int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.aprilTagCode;
            return hashCode2 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            boolean z = this.isSuccess;
            String str = this.pemilihan;
            Integer num = this.nomorHalaman;
            return "DetectResult(isSuccess=" + z + ", pemilihan=" + str + ", nomorHalaman=" + num + ", aprilTagCode=" + this.aprilTagCode + ")";
        }

        public DetectResult(boolean z, String str, Integer num, Integer num2) {
            this.isSuccess = z;
            this.pemilihan = str;
            this.nomorHalaman = num;
            this.aprilTagCode = num2;
        }

        public /* synthetic */ DetectResult(boolean z, String str, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2);
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }

        public final String getPemilihan() {
            return this.pemilihan;
        }

        public final Integer getNomorHalaman() {
            return this.nomorHalaman;
        }

        public final Integer getAprilTagCode() {
            return this.aprilTagCode;
        }
    }

    public final MutableLiveData<DetectResult> getDetectionResult() {
        return this.detectionResult;
    }

    public final void detect(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getDefault(), null, new AprilTagCheckUseCase$detect$1(bitmap, this, null), 2, null);
    }
}
