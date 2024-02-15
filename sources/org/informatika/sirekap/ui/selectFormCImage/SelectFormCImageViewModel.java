package org.informatika.sirekap.ui.selectFormCImage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.usecase.GetListElectionUseCase;

/* compiled from: SelectFormCImageViewModel.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020\rJ\u001a\u0010+\u001a\u00020(2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0017R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u001f\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0006¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\tR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001f\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0006¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u000f\u001a\u0004\b\u001e\u0010\tR\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\tR\u0019\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u0016¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u001f\u0010$\u001a\u0010\u0012\f\u0012\n %*\u0004\u0018\u00010\r0\r0\u0016¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001a¨\u0006-"}, d2 = {"Lorg/informatika/sirekap/ui/selectFormCImage/SelectFormCImageViewModel;", "Landroidx/lifecycle/ViewModel;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "canSelectPage", "Landroidx/lifecycle/LiveData;", "", "getCanSelectPage", "()Landroidx/lifecycle/LiveData;", "canSubmit", "getCanSubmit", "electionPageId", "", "getElectionPageId$annotations", "()V", "getElectionPageId", "getListElectionUseCase", "Lorg/informatika/sirekap/usecase/GetListElectionUseCase;", "getGetListElectionUseCase", "()Lorg/informatika/sirekap/usecase/GetListElectionUseCase;", "optionsMapping", "Landroidx/lifecycle/MutableLiveData;", "", "previewImagePath", "getPreviewImagePath", "()Landroidx/lifecycle/MutableLiveData;", "selectedElection", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "getSelectedElection$annotations", "getSelectedElection", "selectedJenisPemilihan", "getSelectedJenisPemilihan", "selectedJenisPemilihanId", "", "getSelectedJenisPemilihanId", "selectedPage", "kotlin.jvm.PlatformType", "getSelectedPage", "setup", "", "kodeTps", "_previewImagePath", "updateOptionsMapping", "_optionsMapping", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SelectFormCImageViewModel extends ViewModel {
    private final LiveData<Boolean> canSelectPage;
    private final LiveData<Boolean> canSubmit;
    private final LiveData<String> electionPageId;
    private final GetListElectionUseCase getListElectionUseCase;
    private final MutableLiveData<Map<String, String>> optionsMapping;
    private final MutableLiveData<String> previewImagePath;
    private final LiveData<ElectionWithRelation> selectedElection;
    private final LiveData<String> selectedJenisPemilihan;
    private final MutableLiveData<Integer> selectedJenisPemilihanId;
    private final MutableLiveData<String> selectedPage;

    public static /* synthetic */ void getElectionPageId$annotations() {
    }

    public static /* synthetic */ void getSelectedElection$annotations() {
    }

    @Inject
    public SelectFormCImageViewModel(DefaultElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        MutableLiveData<Map<String, String>> mutableLiveData = new MutableLiveData<>(null);
        this.optionsMapping = mutableLiveData;
        this.previewImagePath = new MutableLiveData<>(null);
        GetListElectionUseCase getListElectionUseCase = new GetListElectionUseCase(electionRepository);
        this.getListElectionUseCase = getListElectionUseCase;
        MutableLiveData<Integer> mutableLiveData2 = new MutableLiveData<>(null);
        this.selectedJenisPemilihanId = mutableLiveData2;
        LiveData<String> map = Transformations.map(mutableLiveData2, SelectFormCImageViewModel$selectedJenisPemilihan$1.INSTANCE);
        this.selectedJenisPemilihan = map;
        MutableLiveData<String> mutableLiveData3 = new MutableLiveData<>("");
        this.selectedPage = mutableLiveData3;
        this.electionPageId = new CombinedLiveData(new LiveData[]{mutableLiveData, mutableLiveData3}, new Function1<List<? extends Object>, String>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel$electionPageId$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(List<? extends Object> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                Object obj = data.get(0);
                Map map2 = obj instanceof Map ? (Map) obj : null;
                Object obj2 = data.get(1);
                String str = obj2 instanceof String ? (String) obj2 : null;
                if (map2 == null || str == null) {
                    return null;
                }
                return (String) map2.get(str);
            }
        });
        CombinedLiveData combinedLiveData = new CombinedLiveData(new LiveData[]{getListElectionUseCase.getElections(), map}, new Function1<List<? extends Object>, ElectionWithRelation>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel$selectedElection$1
            @Override // kotlin.jvm.functions.Function1
            public final ElectionWithRelation invoke(List<? extends Object> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                boolean z = false;
                Object obj = data.get(0);
                Object obj2 = null;
                List list = obj instanceof List ? (List) obj : null;
                Object obj3 = data.get(1);
                String str = obj3 instanceof String ? (String) obj3 : null;
                if (list != null && (!list.isEmpty())) {
                    z = true;
                }
                if (!z || str == null) {
                    return null;
                }
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (Intrinsics.areEqual(((ElectionWithRelation) next).getElection().getPemilihan(), str)) {
                        obj2 = next;
                        break;
                    }
                }
                return (ElectionWithRelation) obj2;
            }
        });
        this.selectedElection = combinedLiveData;
        this.canSelectPage = Transformations.map(combinedLiveData, new Function1<ElectionWithRelation, Boolean>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel$canSelectPage$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ElectionWithRelation electionWithRelation) {
                return Boolean.valueOf(electionWithRelation != null);
            }
        });
        this.canSubmit = new CombinedLiveData(new LiveData[]{mutableLiveData2, mutableLiveData3}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel$canSubmit$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<? extends Object> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                boolean z = false;
                Object obj = data.get(0);
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                Object obj2 = data.get(1);
                String str = obj2 instanceof String ? (String) obj2 : null;
                if (num != null) {
                    String str2 = str;
                    if (!(str2 == null || StringsKt.isBlank(str2))) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    public final void setup(String kodeTps, String _previewImagePath) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(_previewImagePath, "_previewImagePath");
        this.getListElectionUseCase.setup(kodeTps);
        this.previewImagePath.postValue(_previewImagePath);
    }

    public final void updateOptionsMapping(Map<String, String> _optionsMapping) {
        Intrinsics.checkNotNullParameter(_optionsMapping, "_optionsMapping");
        this.optionsMapping.setValue(_optionsMapping);
    }

    public final MutableLiveData<String> getPreviewImagePath() {
        return this.previewImagePath;
    }

    public final GetListElectionUseCase getGetListElectionUseCase() {
        return this.getListElectionUseCase;
    }

    public final MutableLiveData<Integer> getSelectedJenisPemilihanId() {
        return this.selectedJenisPemilihanId;
    }

    public final LiveData<String> getSelectedJenisPemilihan() {
        return this.selectedJenisPemilihan;
    }

    public final MutableLiveData<String> getSelectedPage() {
        return this.selectedPage;
    }

    public final LiveData<String> getElectionPageId() {
        return this.electionPageId;
    }

    public final LiveData<ElectionWithRelation> getSelectedElection() {
        return this.selectedElection;
    }

    public final LiveData<Boolean> getCanSelectPage() {
        return this.canSelectPage;
    }

    public final LiveData<Boolean> getCanSubmit() {
        return this.canSubmit;
    }
}
