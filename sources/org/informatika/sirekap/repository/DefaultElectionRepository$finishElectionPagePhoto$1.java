package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;
import org.informatika.sirekap.api.response.EmptyApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.ElectionPageStage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.CrashlyticsUtil;
import org.informatika.sirekap.support.NetworkBoundResource;

/* compiled from: ElectionRepository.kt */
@Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0014\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00060\u0005H\u0014J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0014J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H\u0014J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0014¨\u0006\u000e"}, d2 = {"org/informatika/sirekap/repository/DefaultElectionRepository$finishElectionPagePhoto$1", "Lorg/informatika/sirekap/support/NetworkBoundResource;", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "Lorg/informatika/sirekap/api/response/EmptyApiResponse;", "createCall", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/api/ApiResponse;", "loadFromDb", "saveCallResult", "", "item", "shouldFetch", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultElectionRepository$finishElectionPagePhoto$1 extends NetworkBoundResource<ElectionWithRelation, EmptyApiResponse> {
    final /* synthetic */ Integer $aprilTagCode;
    final /* synthetic */ String $croppedPhotoPath;
    final /* synthetic */ String $electionId;
    final /* synthetic */ String $electionPageId;
    final /* synthetic */ String $hashDocumentCropped;
    final /* synthetic */ String $photoPath;
    final /* synthetic */ String $signature;
    final /* synthetic */ int $takePhotoAttempt;
    final /* synthetic */ DefaultElectionRepository this$0;

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public boolean shouldFetch(ElectionWithRelation electionWithRelation) {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultElectionRepository$finishElectionPagePhoto$1(DefaultElectionRepository defaultElectionRepository, String str, String str2, String str3, int i, String str4, String str5, Integer num, String str6, AppExecutors appExecutors) {
        super(appExecutors);
        this.this$0 = defaultElectionRepository;
        this.$electionPageId = str;
        this.$photoPath = str2;
        this.$croppedPhotoPath = str3;
        this.$takePhotoAttempt = i;
        this.$signature = str4;
        this.$hashDocumentCropped = str5;
        this.$aprilTagCode = num;
        this.$electionId = str6;
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public void saveCallResult(EmptyApiResponse item) {
        AppDatabase appDatabase;
        Intrinsics.checkNotNullParameter(item, "item");
        appDatabase = this.this$0.database;
        final DefaultElectionRepository defaultElectionRepository = this.this$0;
        final String str = this.$electionPageId;
        final String str2 = this.$photoPath;
        final String str3 = this.$croppedPhotoPath;
        final int i = this.$takePhotoAttempt;
        final String str4 = this.$signature;
        final String str5 = this.$hashDocumentCropped;
        final Integer num = this.$aprilTagCode;
        appDatabase.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$finishElectionPagePhoto$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository$finishElectionPagePhoto$1.saveCallResult$lambda$0(DefaultElectionRepository.this, str, str2, str3, i, str4, str5, num);
            }
        });
        CrashlyticsUtil.Companion.setElectionPageStatus(this.$electionPageId, 1);
    }

    public static final void saveCallResult$lambda$0(DefaultElectionRepository this$0, String electionPageId, String photoPath, String str, int i, String str2, String str3, Integer num) {
        AppDatabase appDatabase;
        AppDatabase appDatabase2;
        AppDatabase appDatabase3;
        AppDatabase appDatabase4;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPageId, "$electionPageId");
        Intrinsics.checkNotNullParameter(photoPath, "$photoPath");
        appDatabase = this$0.database;
        appDatabase.electionPageStageDao().updateStatus(1, electionPageId, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_PHOTO);
        appDatabase2 = this$0.database;
        appDatabase2.electionPageStageDao().updateStatus(-1, electionPageId, ElectionPageStage.ELECTION_PAGE_STAGE_TYPE_SEND);
        appDatabase3 = this$0.database;
        appDatabase3.electionPageDao().finishPhoto(electionPageId, photoPath, str, i, str2, str3, num);
        appDatabase4 = this$0.database;
        appDatabase4.uploadImageAttemptDao().deleteBy(electionPageId);
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public LiveData<ElectionWithRelation> loadFromDb() {
        AppDatabase appDatabase;
        appDatabase = this.this$0.database;
        return appDatabase.electionDao().getById(this.$electionId);
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
        return new MutableLiveData(new ApiSuccessResponse(new EmptyApiResponse(true, "", null, null), ""));
    }
}
