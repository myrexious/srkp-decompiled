package org.informatika.sirekap.repository;

import androidx.lifecycle.LiveData;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;
import org.informatika.sirekap.api.ApiResponse;
import org.informatika.sirekap.api.response.EmptyApiResponse;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.NetworkBoundResource;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.livedata.AbsentLiveData;

/* compiled from: UserRepository.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0018\u00010\t0\bH\u0016J\u001e\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t0\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/repository/DefaultUserRepository;", "Lorg/informatika/sirekap/repository/UserRepository;", "database", "Lorg/informatika/sirekap/db/AppDatabase;", "appExecutors", "Lorg/informatika/sirekap/support/AppExecutors;", "(Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/support/AppExecutors;)V", "getAll", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/support/Resource;", "", "Lorg/informatika/sirekap/model/User;", "getUserById", JobType.ID, "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultUserRepository implements UserRepository {
    private final AppExecutors appExecutors;
    private final AppDatabase database;

    @Inject
    public DefaultUserRepository(AppDatabase database, AppExecutors appExecutors) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(appExecutors, "appExecutors");
        this.database = database;
        this.appExecutors = appExecutors;
    }

    @Override // org.informatika.sirekap.repository.UserRepository
    public LiveData<Resource<User>> getUserById(final String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        return new NetworkBoundResource<User, EmptyApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultUserRepository$getUserById$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(EmptyApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public boolean shouldFetch(User user) {
                return false;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<User> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultUserRepository.this.database;
                return appDatabase.userDao().getById(id2);
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }

    @Override // org.informatika.sirekap.repository.UserRepository
    public LiveData<Resource<List<User>>> getAll() {
        return new NetworkBoundResource<List<? extends User>, EmptyApiResponse>(this.appExecutors) { // from class: org.informatika.sirekap.repository.DefaultUserRepository$getAll$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public void saveCallResult(EmptyApiResponse item) {
                Intrinsics.checkNotNullParameter(item, "item");
            }

            /* renamed from: shouldFetch  reason: avoid collision after fix types in other method */
            protected boolean shouldFetch2(List<User> list) {
                return false;
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public /* bridge */ /* synthetic */ boolean shouldFetch(List<? extends User> list) {
                return shouldFetch2((List<User>) list);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // org.informatika.sirekap.support.NetworkBoundResource
            public LiveData<List<? extends User>> loadFromDb() {
                AppDatabase appDatabase;
                appDatabase = DefaultUserRepository.this.database;
                return appDatabase.userDao().getAll();
            }

            @Override // org.informatika.sirekap.support.NetworkBoundResource
            protected LiveData<ApiResponse<EmptyApiResponse>> createCall() {
                return AbsentLiveData.Companion.create();
            }
        }.asLiveData();
    }
}
