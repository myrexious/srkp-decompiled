package org.informatika.sirekap.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.UserRepository;
import org.informatika.sirekap.support.Resource;

/* compiled from: GetLoggedInUserUseCase.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\nR\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00110\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/informatika/sirekap/usecase/GetLoggedInUserUseCase;", "", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "userRepository", "Lorg/informatika/sirekap/repository/UserRepository;", "(Lorg/informatika/sirekap/repository/AuthRepository;Lorg/informatika/sirekap/repository/UserRepository;)V", "isAuthenticated", "Landroidx/lifecycle/LiveData;", "", "()Landroidx/lifecycle/LiveData;", "loggedInUser", "Lorg/informatika/sirekap/model/User;", "getLoggedInUser", "loggedInUserId", "", "loggedInUserResource", "Lorg/informatika/sirekap/support/Resource;", "getLoggedInUserResource", "refreshCount", "Landroidx/lifecycle/MutableLiveData;", "", "refresh", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GetLoggedInUserUseCase {
    private final AuthRepository authRepository;
    private final LiveData<Boolean> isAuthenticated;
    private final LiveData<User> loggedInUser;
    private final LiveData<String> loggedInUserId;
    private final LiveData<Resource<User>> loggedInUserResource;
    private final MutableLiveData<Integer> refreshCount;
    private final UserRepository userRepository;

    public GetLoggedInUserUseCase(AuthRepository authRepository, UserRepository userRepository) {
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        Intrinsics.checkNotNullParameter(userRepository, "userRepository");
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(0);
        this.refreshCount = mutableLiveData;
        this.isAuthenticated = Transformations.map(mutableLiveData, new Function1<Integer, Boolean>() { // from class: org.informatika.sirekap.usecase.GetLoggedInUserUseCase$isAuthenticated$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Integer num) {
                AuthRepository authRepository2;
                authRepository2 = GetLoggedInUserUseCase.this.authRepository;
                return Boolean.valueOf(authRepository2.isAuthenticated());
            }
        });
        LiveData<String> map = Transformations.map(mutableLiveData, new Function1<Integer, String>() { // from class: org.informatika.sirekap.usecase.GetLoggedInUserUseCase$loggedInUserId$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final String invoke(Integer num) {
                AuthRepository authRepository2;
                authRepository2 = GetLoggedInUserUseCase.this.authRepository;
                return authRepository2.getLoggedInUserId();
            }
        });
        this.loggedInUserId = map;
        LiveData<Resource<User>> switchMap = Transformations.switchMap(map, new Function1<String, LiveData<Resource<User>>>() { // from class: org.informatika.sirekap.usecase.GetLoggedInUserUseCase$loggedInUserResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<User>> invoke(String str) {
                UserRepository userRepository2;
                if (str == null) {
                    return null;
                }
                userRepository2 = GetLoggedInUserUseCase.this.userRepository;
                return userRepository2.getUserById(str);
            }
        });
        this.loggedInUserResource = switchMap;
        this.loggedInUser = Transformations.map(switchMap, new Function1<Resource<User>, User>() { // from class: org.informatika.sirekap.usecase.GetLoggedInUserUseCase$loggedInUser$1
            @Override // kotlin.jvm.functions.Function1
            public final User invoke(Resource<User> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
    }

    public final void refresh() {
        Integer value = this.refreshCount.getValue();
        if (value != null) {
            this.refreshCount.postValue(Integer.valueOf(value.intValue() + 1));
        }
    }

    public final LiveData<Boolean> isAuthenticated() {
        return this.isAuthenticated;
    }

    public final LiveData<Resource<User>> getLoggedInUserResource() {
        return this.loggedInUserResource;
    }

    public final LiveData<User> getLoggedInUser() {
        return this.loggedInUser;
    }
}
