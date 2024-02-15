package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.ApiSuccessResponse;
import org.informatika.sirekap.api.response.EmptyApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.SpecialOccurrencePage;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.FileUtil;
import org.informatika.sirekap.support.NetworkBoundResource;

/* compiled from: ElectionRepository.kt */
@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0014\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00060\u0005H\u0014J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005H\u0014J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H\u0014J\u0017\u0010\u000b\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"org/informatika/sirekap/repository/DefaultElectionRepository$deleteSpecialOccurrence$1", "Lorg/informatika/sirekap/support/NetworkBoundResource;", "", "Lorg/informatika/sirekap/api/response/EmptyApiResponse;", "createCall", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/api/ApiResponse;", "loadFromDb", "saveCallResult", "", "item", "shouldFetch", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "(Ljava/lang/Boolean;)Z", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultElectionRepository$deleteSpecialOccurrence$1 extends NetworkBoundResource<Boolean, EmptyApiResponse> {
    final /* synthetic */ SpecialOccurrencePage $specialOccurrencePage;
    final /* synthetic */ DefaultElectionRepository this$0;

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public boolean shouldFetch(Boolean bool) {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultElectionRepository$deleteSpecialOccurrence$1(DefaultElectionRepository defaultElectionRepository, SpecialOccurrencePage specialOccurrencePage, AppExecutors appExecutors) {
        super(appExecutors);
        this.this$0 = defaultElectionRepository;
        this.$specialOccurrencePage = specialOccurrencePage;
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public void saveCallResult(EmptyApiResponse item) {
        AppDatabase appDatabase;
        Intrinsics.checkNotNullParameter(item, "item");
        appDatabase = this.this$0.database;
        final SpecialOccurrencePage specialOccurrencePage = this.$specialOccurrencePage;
        final DefaultElectionRepository defaultElectionRepository = this.this$0;
        appDatabase.runInTransaction(new Runnable() { // from class: org.informatika.sirekap.repository.DefaultElectionRepository$deleteSpecialOccurrence$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DefaultElectionRepository$deleteSpecialOccurrence$1.saveCallResult$lambda$3(SpecialOccurrencePage.this, defaultElectionRepository);
            }
        });
    }

    public static final void saveCallResult$lambda$3(SpecialOccurrencePage specialOccurrencePage, DefaultElectionRepository this$0) {
        AppDatabase appDatabase;
        Intrinsics.checkNotNullParameter(specialOccurrencePage, "$specialOccurrencePage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        appDatabase = this$0.database;
        appDatabase.specialOccurrencePageDao().deleteBy(specialOccurrencePage.getId());
        String photoPath = specialOccurrencePage.getPhotoPath();
        FileUtil.deleteFile(StringsKt.replace$default(photoPath, ".jpg", ".png", false, 4, (Object) null));
        FileUtil.deleteFile(photoPath);
        String croppedPhotoPath = specialOccurrencePage.getCroppedPhotoPath();
        FileUtil.deleteFile(StringsKt.replace$default(croppedPhotoPath, ".jpg", ".png", false, 4, (Object) null));
        FileUtil.deleteFile(croppedPhotoPath);
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    public LiveData<Boolean> loadFromDb() {
        return new MutableLiveData(true);
    }

    @Override // org.informatika.sirekap.support.NetworkBoundResource
    protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
        return new MutableLiveData(new ApiSuccessResponse(new EmptyApiResponse(true, "", null, null), ""));
    }
}
