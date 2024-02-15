package org.informatika.sirekap.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.support.CombinedLiveData;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: SettingsViewModel.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010'\u001a\u00020(J\u001c\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020(0-J\u0006\u0010.\u001a\u00020(J\u000e\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020\u0017J\u000e\u00101\u001a\u00020(2\u0006\u00102\u001a\u00020\u0017J\u000e\u00103\u001a\u00020(2\u0006\u00104\u001a\u00020\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00170\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u001a0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0011R\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0016¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001f\u0010#\u001a\u0010\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00170\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0011¨\u00065"}, d2 = {"Lorg/informatika/sirekap/ui/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "authRepository", "Lorg/informatika/sirekap/repository/AuthRepository;", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "(Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/repository/AuthRepository;Lorg/informatika/sirekap/db/AppDatabase;)V", "getAppDatabase", "()Lorg/informatika/sirekap/db/AppDatabase;", "election", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getElection", "()Landroidx/lifecycle/LiveData;", "electionType", "", "getElectionType", "electionTypeIndex", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "electionsResource", "Lorg/informatika/sirekap/support/Resource;", "isLoading", "Lorg/informatika/sirekap/support/CombinedLiveData;", "", "()Lorg/informatika/sirekap/support/CombinedLiveData;", "isLoadingElection", "kodeTps", "getKodeTps", "()Landroidx/lifecycle/MutableLiveData;", "profileIndex", "getProfileIndex", "selectedElectionType", "getSelectedElectionType", "confirmSelectedElectionType", "", "confirmSelectedProfile", "selectedProfileEncrypted", "Lorg/informatika/sirekap/model/User;", "onSuccess", "Lkotlin/Function0;", "refresh", "saveSelectedElectionType", "_electionTypeIndex", "saveSelectedProfile", "_profileIndex", "setup", "_kodeTps", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SettingsViewModel extends ViewModel {
    private final AppDatabase appDatabase;
    private final AuthRepository authRepository;
    private final LiveData<ElectionWithRelation> election;
    private final DefaultElectionRepository electionRepository;
    private final LiveData<String> electionType;
    private final MutableLiveData<Integer> electionTypeIndex;
    private final LiveData<Resource<ElectionWithRelation>> electionsResource;
    private final EncryptedSharedPreferences encryptedSharedPreferences;
    private final CombinedLiveData<Boolean> isLoading;
    private final LiveData<Boolean> isLoadingElection;
    private final MutableLiveData<String> kodeTps;
    private final MutableLiveData<Integer> profileIndex;
    private final LiveData<String> selectedElectionType;

    public final AppDatabase getAppDatabase() {
        return this.appDatabase;
    }

    @Inject
    public SettingsViewModel(DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, AuthRepository authRepository, AppDatabase appDatabase) {
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(authRepository, "authRepository");
        Intrinsics.checkNotNullParameter(appDatabase, "appDatabase");
        this.electionRepository = electionRepository;
        this.encryptedSharedPreferences = encryptedSharedPreferences;
        this.authRepository = authRepository;
        this.appDatabase = appDatabase;
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData;
        LiveData<Resource<ElectionWithRelation>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<ElectionWithRelation>>>() { // from class: org.informatika.sirekap.ui.settings.SettingsViewModel$electionsResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<ElectionWithRelation>> invoke(String str) {
                DefaultElectionRepository defaultElectionRepository;
                if (str == null) {
                    return null;
                }
                defaultElectionRepository = SettingsViewModel.this.electionRepository;
                return defaultElectionRepository.getFirstElectionByKodeTps(str);
            }
        });
        this.electionsResource = switchMap;
        LiveData<Boolean> map = Transformations.map(switchMap, new Function1<Resource<ElectionWithRelation>, Boolean>() { // from class: org.informatika.sirekap.ui.settings.SettingsViewModel$isLoadingElection$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<ElectionWithRelation> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.isLoadingElection = map;
        LiveData<ElectionWithRelation> map2 = Transformations.map(switchMap, new Function1<Resource<ElectionWithRelation>, ElectionWithRelation>() { // from class: org.informatika.sirekap.ui.settings.SettingsViewModel$election$1
            @Override // kotlin.jvm.functions.Function1
            public final ElectionWithRelation invoke(Resource<ElectionWithRelation> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.election = map2;
        this.electionType = Transformations.map(map2, new Function1<ElectionWithRelation, String>() { // from class: org.informatika.sirekap.ui.settings.SettingsViewModel$electionType$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(ElectionWithRelation electionWithRelation) {
                Election election;
                if (electionWithRelation == null || (election = electionWithRelation.getElection()) == null) {
                    return null;
                }
                return election.getElectionType();
            }
        });
        MutableLiveData<Integer> mutableLiveData2 = new MutableLiveData<>(0);
        this.electionTypeIndex = mutableLiveData2;
        this.selectedElectionType = Transformations.map(mutableLiveData2, new Function1<Integer, String>() { // from class: org.informatika.sirekap.ui.settings.SettingsViewModel$selectedElectionType$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(Integer num) {
                return (num != null && num.intValue() == 1) ? "U" : (num != null && num.intValue() == 2) ? "S" : (num != null && num.intValue() == 3) ? "L" : "R";
            }
        });
        this.isLoading = new CombinedLiveData<>(new LiveData[]{map}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.settings.SettingsViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                boolean z3 = false;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        z = true;
                        if (obj == null) {
                            z2 = true;
                            continue;
                        } else {
                            z2 = false;
                            continue;
                        }
                        if (z2) {
                            break;
                        }
                    }
                }
                z = false;
                if (!z) {
                    Object obj2 = data.get(0);
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                    z3 = ((Boolean) obj2).booleanValue();
                }
                return Boolean.valueOf(z3);
            }
        });
        this.profileIndex = new MutableLiveData<>(0);
    }

    public final MutableLiveData<String> getKodeTps() {
        return this.kodeTps;
    }

    public final LiveData<Boolean> isLoadingElection() {
        return this.isLoadingElection;
    }

    public final LiveData<ElectionWithRelation> getElection() {
        return this.election;
    }

    public final LiveData<String> getElectionType() {
        return this.electionType;
    }

    public final void setup(String _kodeTps) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        if (Intrinsics.areEqual(this.kodeTps.getValue(), _kodeTps)) {
            return;
        }
        this.kodeTps.postValue(_kodeTps);
    }

    public final LiveData<String> getSelectedElectionType() {
        return this.selectedElectionType;
    }

    public final void saveSelectedElectionType(int i) {
        this.electionTypeIndex.postValue(Integer.valueOf(i));
    }

    public final void confirmSelectedElectionType() {
        Integer value = this.electionTypeIndex.getValue();
        if (value != null) {
            int intValue = value.intValue();
            this.encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, intValue != 1 ? intValue != 2 ? intValue != 3 ? "R" : "L" : "S" : "U");
        }
        refresh();
    }

    public final void refresh() {
        String value = this.kodeTps.getValue();
        if (value != null) {
            this.kodeTps.postValue(value);
        }
    }

    public final CombinedLiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final MutableLiveData<Integer> getProfileIndex() {
        return this.profileIndex;
    }

    public final void saveSelectedProfile(int i) {
        this.profileIndex.postValue(Integer.valueOf(i));
    }

    public final void confirmSelectedProfile(User selectedProfileEncrypted, Function0<Unit> onSuccess) {
        Intrinsics.checkNotNullParameter(selectedProfileEncrypted, "selectedProfileEncrypted");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        this.encryptedSharedPreferences.putStringEncrypted(SharedPreferencesModule.SP_KEY_USER_ID, selectedProfileEncrypted.getId());
        this.authRepository.saveActiveProfile(new ActiveProfile(selectedProfileEncrypted.getNamaProfil(), null, ElectionUtil.extractKodeTpsReal(selectedProfileEncrypted.getId()), selectedProfileEncrypted.getRole(), selectedProfileEncrypted.getProfilStart(), selectedProfileEncrypted.getProfilEnd()));
        onSuccess.invoke();
    }
}
