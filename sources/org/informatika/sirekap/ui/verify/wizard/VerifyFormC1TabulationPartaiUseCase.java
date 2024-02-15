package org.informatika.sirekap.ui.verify.wizard;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.FormC1TabulationPartai;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
import org.informatika.sirekap.model.FormC1TabulationPartaiWithCandidates;
import org.informatika.sirekap.repository.ElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem;

/* compiled from: VerifyFormC1TabulationPartaiUseCase.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001d\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010#2\u0006\u0010-\u001a\u00020\u0010¢\u0006\u0002\u0010.J\u001d\u0010/\u001a\u00020+2\b\u00100\u001a\u0004\u0018\u00010(2\u0006\u0010-\u001a\u00020\u0010¢\u0006\u0002\u00101J\u001e\u00102\u001a\u00020+2\u0006\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u00132\u0006\u00105\u001a\u00020\u0013R\"\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b0\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00190\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u00190\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u000eR!\u0010$\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010#\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u000eR\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00130!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130!X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010(\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u000e¨\u00066"}, d2 = {"Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1TabulationPartaiUseCase;", "", "context", "Landroid/content/Context;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "electionRepository", "Lorg/informatika/sirekap/repository/ElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/FormC1Repository;Lorg/informatika/sirekap/repository/ElectionRepository;)V", "candidates", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/Candidate;", "getCandidates$app_productionRelease", "()Landroidx/lifecycle/LiveData;", "checkItems", "Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;", "getCheckItems", "correctedPhotoPath", "", "getCorrectedPhotoPath", "election", "Lorg/informatika/sirekap/model/ElectionWithRelation;", "electionId", "electionResource", "Lorg/informatika/sirekap/support/Resource;", "formC1TabulationPartaiComplete", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "formC1TabulationPartaiCompleteResource", "formC1TabulationWithCandidates", "Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiWithCandidates;", "idImageTabulation", "Landroidx/lifecycle/MutableLiveData;", "isLoading", "", "isSesuaiPerItem", "jenisPemilihan", "kodeTps", "koreksiPerItem", "", "getKoreksiPerItem", "checkItem", "", "newChecked", "currentItem", "(Ljava/lang/Boolean;Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;)V", "correctItem", "correctedValue", "(Ljava/lang/Integer;Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;)V", "setup", "_idImage", "_jenisPemilihan", "_kodeTps", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyFormC1TabulationPartaiUseCase {
    private final LiveData<List<Candidate>> candidates;
    private final LiveData<List<FormC1CheckItem>> checkItems;
    private final Context context;
    private final LiveData<String> correctedPhotoPath;
    private final LiveData<ElectionWithRelation> election;
    private final LiveData<String> electionId;
    private final ElectionRepository electionRepository;
    private final LiveData<Resource<ElectionWithRelation>> electionResource;
    private final FormC1Repository formC1Repository;
    private final LiveData<FormC1TabulationPartaiComplete> formC1TabulationPartaiComplete;
    private final LiveData<Resource<FormC1TabulationPartaiComplete>> formC1TabulationPartaiCompleteResource;
    private final CombinedLiveData<FormC1TabulationPartaiWithCandidates> formC1TabulationWithCandidates;
    private final MutableLiveData<String> idImageTabulation;
    private final LiveData<Boolean> isLoading;
    private final LiveData<List<Boolean>> isSesuaiPerItem;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<List<Integer>> koreksiPerItem;

    public VerifyFormC1TabulationPartaiUseCase(Context context, FormC1Repository formC1Repository, ElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(formC1Repository, "formC1Repository");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.context = context;
        this.formC1Repository = formC1Repository;
        this.electionRepository = electionRepository;
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.idImageTabulation = mutableLiveData;
        this.jenisPemilihan = new MutableLiveData<>(null);
        this.kodeTps = new MutableLiveData<>(null);
        LiveData<Resource<FormC1TabulationPartaiComplete>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<FormC1TabulationPartaiComplete>>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$formC1TabulationPartaiCompleteResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1TabulationPartaiComplete>> invoke(String str) {
                FormC1Repository formC1Repository2;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                if (str != null) {
                    formC1Repository2 = VerifyFormC1TabulationPartaiUseCase.this.formC1Repository;
                    mutableLiveData2 = VerifyFormC1TabulationPartaiUseCase.this.jenisPemilihan;
                    T value = mutableLiveData2.getValue();
                    Intrinsics.checkNotNull(value);
                    String str2 = (String) value;
                    mutableLiveData3 = VerifyFormC1TabulationPartaiUseCase.this.kodeTps;
                    T value2 = mutableLiveData3.getValue();
                    Intrinsics.checkNotNull(value2);
                    return FormC1Repository.DefaultImpls.getFormC1TabulationPartai$default(formC1Repository2, str, str2, false, (String) value2, 4, null);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1TabulationPartaiCompleteResource = switchMap;
        LiveData<FormC1TabulationPartaiComplete> map = Transformations.map(switchMap, new Function1<Resource<FormC1TabulationPartaiComplete>, FormC1TabulationPartaiComplete>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$formC1TabulationPartaiComplete$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1TabulationPartaiComplete invoke(Resource<FormC1TabulationPartaiComplete> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.formC1TabulationPartaiComplete = map;
        LiveData<String> map2 = Transformations.map(map, new Function1<FormC1TabulationPartaiComplete, String>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$electionId$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete) {
                ElectionPage electionPage;
                if (formC1TabulationPartaiComplete == null || (electionPage = formC1TabulationPartaiComplete.getElectionPage()) == null) {
                    return null;
                }
                return electionPage.getElectionId();
            }
        });
        this.electionId = map2;
        LiveData<Resource<ElectionWithRelation>> switchMap2 = Transformations.switchMap(map2, new Function1<String, LiveData<Resource<ElectionWithRelation>>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$electionResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<ElectionWithRelation>> invoke(String str) {
                ElectionRepository electionRepository2;
                if (str != null) {
                    electionRepository2 = VerifyFormC1TabulationPartaiUseCase.this.electionRepository;
                    return electionRepository2.getElectionById(str);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.electionResource = switchMap2;
        LiveData<ElectionWithRelation> map3 = Transformations.map(switchMap2, new Function1<Resource<ElectionWithRelation>, ElectionWithRelation>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$election$1
            @Override // kotlin.jvm.functions.Function1
            public final ElectionWithRelation invoke(Resource<ElectionWithRelation> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.election = map3;
        LiveData<List<Candidate>> map4 = Transformations.map(map3, new Function1<ElectionWithRelation, List<Candidate>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$candidates$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Candidate> invoke(ElectionWithRelation electionWithRelation) {
                if (electionWithRelation != null) {
                    return electionWithRelation.getCandidates();
                }
                return null;
            }
        });
        this.candidates = map4;
        CombinedLiveData<FormC1TabulationPartaiWithCandidates> combinedLiveData = new CombinedLiveData<>(new LiveData[]{map, map4}, new Function1<List<? extends Object>, FormC1TabulationPartaiWithCandidates>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$formC1TabulationWithCandidates$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1TabulationPartaiWithCandidates invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        if (obj != null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (!z) {
                            z2 = false;
                            break;
                        }
                    }
                }
                z2 = true;
                if (z2) {
                    Object obj2 = data.get(1);
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<org.informatika.sirekap.model.Candidate>");
                    List list2 = (List) obj2;
                    if (true ^ list2.isEmpty()) {
                        Object obj3 = data.get(0);
                        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type org.informatika.sirekap.model.FormC1TabulationPartaiComplete");
                        return new FormC1TabulationPartaiWithCandidates((FormC1TabulationPartaiComplete) obj3, list2);
                    }
                    return null;
                }
                return null;
            }
        });
        this.formC1TabulationWithCandidates = combinedLiveData;
        this.isSesuaiPerItem = Transformations.map(map, new Function1<FormC1TabulationPartaiComplete, List<Boolean>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$isSesuaiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Boolean> invoke(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete) {
                if (formC1TabulationPartaiComplete != null) {
                    return formC1TabulationPartaiComplete.toIsSesuaiPerItem();
                }
                return null;
            }
        });
        this.koreksiPerItem = Transformations.map(map, new Function1<FormC1TabulationPartaiComplete, List<Integer>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$koreksiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Integer> invoke(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete) {
                if (formC1TabulationPartaiComplete != null) {
                    return formC1TabulationPartaiComplete.toKoreksiPerItem();
                }
                return null;
            }
        });
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<FormC1TabulationPartaiComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1TabulationPartaiComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.checkItems = Transformations.map(combinedLiveData, new Function1<FormC1TabulationPartaiWithCandidates, List<FormC1CheckItem>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$checkItems$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1CheckItem> invoke(FormC1TabulationPartaiWithCandidates formC1TabulationPartaiWithCandidates) {
                Context context2;
                if (formC1TabulationPartaiWithCandidates != null) {
                    VerifyFormC1TabulationPartaiUseCase verifyFormC1TabulationPartaiUseCase = VerifyFormC1TabulationPartaiUseCase.this;
                    FormC1CheckItem.Companion companion = FormC1CheckItem.Companion;
                    context2 = verifyFormC1TabulationPartaiUseCase.context;
                    return companion.createFromFormC1TabulationPartaiWithCandidates(context2, formC1TabulationPartaiWithCandidates);
                }
                return null;
            }
        });
        this.correctedPhotoPath = Transformations.map(map, new Function1<FormC1TabulationPartaiComplete, String>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase$correctedPhotoPath$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete) {
                ElectionPage electionPage;
                if (formC1TabulationPartaiComplete == null || (electionPage = formC1TabulationPartaiComplete.getElectionPage()) == null) {
                    return null;
                }
                return electionPage.getCorrectedPhotoPath();
            }
        });
    }

    public final void setup(String _idImage, String _jenisPemilihan, String _kodeTps) {
        Intrinsics.checkNotNullParameter(_idImage, "_idImage");
        Intrinsics.checkNotNullParameter(_jenisPemilihan, "_jenisPemilihan");
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        if (!Intrinsics.areEqual(this.kodeTps.getValue(), _kodeTps)) {
            this.kodeTps.setValue(_kodeTps);
        }
        if (!Intrinsics.areEqual(this.jenisPemilihan.getValue(), _jenisPemilihan)) {
            this.jenisPemilihan.setValue(_jenisPemilihan);
        }
        if (Intrinsics.areEqual(this.idImageTabulation.getValue(), _idImage)) {
            return;
        }
        this.idImageTabulation.setValue(_idImage);
    }

    public final void checkItem(Boolean bool, FormC1CheckItem currentItem) {
        String correctedValue;
        Intrinsics.checkNotNullParameter(currentItem, "currentItem");
        String value = this.idImageTabulation.getValue();
        if (value != null) {
            Integer num = null;
            if (Intrinsics.areEqual(currentItem.getRow(), "FORM_C1_ROW_CANDIDATE_")) {
                Integer index = currentItem.getIndex();
                if (index != null) {
                    int intValue = index.intValue();
                    FormC1Repository formC1Repository = this.formC1Repository;
                    if (bool != null && !Intrinsics.areEqual((Object) bool, (Object) true) && (correctedValue = currentItem.getCorrectedValue()) != null) {
                        num = StringsKt.toIntOrNull(correctedValue);
                    }
                    formC1Repository.saveFormC1KesesuaianTabulationCandidateVote(value, intValue, bool, num);
                    return;
                }
                return;
            }
            List<Boolean> mutableListOf = CollectionsKt.mutableListOf(null);
            List<Integer> mutableListOf2 = CollectionsKt.mutableListOf(null);
            if (Intrinsics.areEqual(currentItem.getRow(), FormC1TabulationPartai.FORM_C1_ROW_SUARA_SAH_PARTAI_CALON)) {
                mutableListOf.set(0, bool);
                if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                    mutableListOf2.set(0, null);
                }
            }
            this.formC1Repository.saveFormC1KesesuaianTabulationPartai(value, mutableListOf, mutableListOf2);
        }
    }

    public final void correctItem(Integer num, FormC1CheckItem currentItem) {
        Intrinsics.checkNotNullParameter(currentItem, "currentItem");
        String value = this.idImageTabulation.getValue();
        if (value != null) {
            if (Intrinsics.areEqual(currentItem.getRow(), "FORM_C1_ROW_CANDIDATE_")) {
                Integer index = currentItem.getIndex();
                if (index != null) {
                    this.formC1Repository.saveFormC1KesesuaianTabulationCandidateVote(value, index.intValue(), currentItem.getChecked(), num);
                    return;
                }
                return;
            }
            List<Boolean> isSesuaiPerItem = this.isSesuaiPerItem.getValue();
            if (isSesuaiPerItem != null) {
                List<Integer> mutableListOf = CollectionsKt.mutableListOf(null);
                if (Intrinsics.areEqual(currentItem.getRow(), FormC1TabulationPartai.FORM_C1_ROW_SUARA_SAH_PARTAI_CALON)) {
                    mutableListOf.set(0, num);
                }
                FormC1Repository formC1Repository = this.formC1Repository;
                Intrinsics.checkNotNullExpressionValue(isSesuaiPerItem, "isSesuaiPerItem");
                formC1Repository.saveFormC1KesesuaianTabulationPartai(value, isSesuaiPerItem, mutableListOf);
            }
        }
    }

    public final LiveData<List<Candidate>> getCandidates$app_productionRelease() {
        return this.candidates;
    }

    public final LiveData<List<Boolean>> isSesuaiPerItem() {
        return this.isSesuaiPerItem;
    }

    public final LiveData<List<Integer>> getKoreksiPerItem() {
        return this.koreksiPerItem;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final LiveData<List<FormC1CheckItem>> getCheckItems() {
        return this.checkItems;
    }

    public final LiveData<String> getCorrectedPhotoPath() {
        return this.correctedPhotoPath;
    }
}
