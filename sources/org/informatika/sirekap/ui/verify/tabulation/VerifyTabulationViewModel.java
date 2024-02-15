package org.informatika.sirekap.ui.verify.tabulation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.google.gson.Gson;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote;
import org.informatika.sirekap.model.FormC1TabulationCandidateVote;
import org.informatika.sirekap.model.FormC1TabulationComplete;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.support.templatematching.AprilTagConfig;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.ui.verify.FormC1ListItem;
import org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel;

/* compiled from: VerifyTabulationViewModel.kt */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001PB#\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010?\u001a\u00020@2\u0006\u0010\u0002\u001a\u00020\u0003J2\u0010A\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010B2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u000b2\u0006\u0010C\u001a\u00020&2\u0006\u0010D\u001a\u00020&H\u0002J\u0006\u0010E\u001a\u00020@J\u000e\u0010F\u001a\u00020@2\u0006\u0010G\u001a\u00020;J&\u0010H\u001a\u00020@2\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000b2\u0006\u0010L\u001a\u00020\u000bJ\u0018\u0010M\u001a\u00020@2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010N\u001a\u0004\u0018\u00010OR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001c\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00150\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u001f\u0010\u001a\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001b0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u0016\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0010R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\r¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0010R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020&0\r¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0010R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020&0\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0010R!\u0010*\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010&\u0018\u00010\u00150\r¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0010R\u0019\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\r¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0010R\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020&0\r¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0010R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010/\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u000100\u0018\u00010\u00150\r¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0010R\u0019\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0014\u00105\u001a\b\u0012\u0004\u0012\u0002060\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u00107\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000208\u0018\u00010\u001b0\r¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0010R!\u0010:\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010;\u0018\u00010\u00150\r¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0010R\u0019\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0010¨\u0006Q"}, d2 = {"Lorg/informatika/sirekap/ui/verify/tabulation/VerifyTabulationViewModel;", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel;", "context", "Landroid/content/Context;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/FormC1Repository;Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "electionPageId", "Landroidx/lifecycle/MutableLiveData;", "", "formC1Error", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/FormC1Error;", "getFormC1Error", "()Landroidx/lifecycle/LiveData;", "formC1Kesesuaian", "Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "getFormC1Kesesuaian", "formC1KesesuaianTabulationCandidateVotes", "", "Lorg/informatika/sirekap/model/FormC1KesesuaianTabulationCandidateVote;", "formC1Tabulation", "Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "getFormC1Tabulation", "formC1TabulationResource", "Lorg/informatika/sirekap/support/Resource;", "getFormC1TabulationResource", "formC1TabulationWithCandidates", "Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "Lorg/informatika/sirekap/ui/verify/tabulation/VerifyTabulationViewModel$FormC1TabulationWithCandidates;", "getElectionPageUseCase", "Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "getGetElectionPageUseCase", "()Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "idImage", "isCheckAllTrue", "", "isError", "isLoading", "isLoadingVerification", "isSesuaiPerItem", "isTableChecked", "isTablePerolehanSuaraEmpty", "jenisPemilihan", "kodeTps", "koreksiPerItem", "", "getKoreksiPerItem", "previewImagePath", "getPreviewImagePath", "()Landroidx/lifecycle/MutableLiveData;", "submittedFormC1", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel$VerifyFormC1Model;", "submittedFormResource", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "getSubmittedFormResource", "tablePerolehanSuara", "Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "getTablePerolehanSuara", "tablePerolehanSuaraTitle", "getTablePerolehanSuaraTitle", "continueVerify", "", "getJsonString", "Ljava/util/HashMap;", "isLnPos", "isLn", "refreshIdImage", "saveChecked", "item", "setup", "_idImage", "_electionPageId", "_jenisPemilihan", "_kodeTps", "submitVerification", "dialog", "Landroidx/appcompat/app/AlertDialog;", "FormC1TabulationWithCandidates", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyTabulationViewModel extends BaseVerifyViewModel {
    private final MutableLiveData<String> electionPageId;
    private final LiveData<FormC1Error> formC1Error;
    private final LiveData<FormC1Kesesuaian> formC1Kesesuaian;
    private final LiveData<List<FormC1KesesuaianTabulationCandidateVote>> formC1KesesuaianTabulationCandidateVotes;
    private final FormC1Repository formC1Repository;
    private final LiveData<FormC1TabulationComplete> formC1Tabulation;
    private final LiveData<Resource<FormC1TabulationComplete>> formC1TabulationResource;
    private final CombinedLiveData<FormC1TabulationWithCandidates> formC1TabulationWithCandidates;
    private final GetElectionPageUseCase getElectionPageUseCase;
    private final MutableLiveData<String> idImage;
    private final LiveData<Boolean> isCheckAllTrue;
    private final LiveData<Boolean> isError;
    private final LiveData<Boolean> isLoading;
    private final LiveData<Boolean> isLoadingVerification;
    private final LiveData<List<Boolean>> isSesuaiPerItem;
    private final LiveData<Boolean> isTableChecked;
    private final LiveData<Boolean> isTablePerolehanSuaraEmpty;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<List<Integer>> koreksiPerItem;
    private final MutableLiveData<String> previewImagePath;
    private final MutableLiveData<BaseVerifyViewModel.VerifyFormC1Model> submittedFormC1;
    private final LiveData<Resource<FormC1AdministrationComplete>> submittedFormResource;
    private final LiveData<List<FormC1ListItem>> tablePerolehanSuara;
    private final LiveData<String> tablePerolehanSuaraTitle;

    @Inject
    public VerifyTabulationViewModel(@ApplicationContext final Context context, FormC1Repository formC1Repository, DefaultElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(formC1Repository, "formC1Repository");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.formC1Repository = formC1Repository;
        GetElectionPageUseCase getElectionPageUseCase = new GetElectionPageUseCase(electionRepository);
        this.getElectionPageUseCase = getElectionPageUseCase;
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.idImage = mutableLiveData;
        this.electionPageId = new MutableLiveData<>(null);
        this.jenisPemilihan = new MutableLiveData<>(null);
        this.kodeTps = new MutableLiveData<>(null);
        LiveData<Resource<FormC1TabulationComplete>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<FormC1TabulationComplete>>>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$formC1TabulationResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1TabulationComplete>> invoke(String str) {
                FormC1Repository formC1Repository2;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                if (str != null) {
                    formC1Repository2 = VerifyTabulationViewModel.this.formC1Repository;
                    mutableLiveData2 = VerifyTabulationViewModel.this.jenisPemilihan;
                    T value = mutableLiveData2.getValue();
                    Intrinsics.checkNotNull(value);
                    String str2 = (String) value;
                    mutableLiveData3 = VerifyTabulationViewModel.this.kodeTps;
                    T value2 = mutableLiveData3.getValue();
                    Intrinsics.checkNotNull(value2);
                    return FormC1Repository.DefaultImpls.getFormC1Tabulation$default(formC1Repository2, str, str2, false, (String) value2, 4, null);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1TabulationResource = switchMap;
        LiveData<FormC1TabulationComplete> map = Transformations.map(switchMap, new Function1<Resource<FormC1TabulationComplete>, FormC1TabulationComplete>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$formC1Tabulation$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1TabulationComplete invoke(Resource<FormC1TabulationComplete> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.formC1Tabulation = map;
        this.formC1Error = Transformations.map(map, new Function1<FormC1TabulationComplete, FormC1Error>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$formC1Error$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1Error invoke(FormC1TabulationComplete formC1TabulationComplete) {
                if (formC1TabulationComplete != null) {
                    return formC1TabulationComplete.getError();
                }
                return null;
            }
        });
        this.formC1Kesesuaian = Transformations.map(map, new Function1<FormC1TabulationComplete, FormC1Kesesuaian>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$formC1Kesesuaian$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1Kesesuaian invoke(FormC1TabulationComplete formC1TabulationComplete) {
                if (formC1TabulationComplete != null) {
                    return formC1TabulationComplete.getKesesuaian();
                }
                return null;
            }
        });
        LiveData<List<FormC1KesesuaianTabulationCandidateVote>> map2 = Transformations.map(map, new Function1<FormC1TabulationComplete, List<FormC1KesesuaianTabulationCandidateVote>>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$formC1KesesuaianTabulationCandidateVotes$1
            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1KesesuaianTabulationCandidateVote> invoke(FormC1TabulationComplete formC1TabulationComplete) {
                if (formC1TabulationComplete != null) {
                    return formC1TabulationComplete.getKesesuaianVotes();
                }
                return null;
            }
        });
        this.formC1KesesuaianTabulationCandidateVotes = map2;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<FormC1TabulationComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1TabulationComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.isError = Transformations.map(switchMap, new Function1<Resource<FormC1TabulationComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$isError$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1TabulationComplete> resource) {
                boolean z = false;
                if ((resource != null ? resource.getSuccess() : null) == ResourceStatus.ERROR) {
                    FormC1TabulationComplete payload = resource.getPayload();
                    List<FormC1KesesuaianTabulationCandidateVote> kesesuaianVotes = payload != null ? payload.getKesesuaianVotes() : null;
                    if (kesesuaianVotes == null || kesesuaianVotes.isEmpty()) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }
        });
        LiveData<List<Boolean>> map3 = Transformations.map(map2, new Function1<List<FormC1KesesuaianTabulationCandidateVote>, List<Boolean>>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$isSesuaiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Boolean> invoke(List<FormC1KesesuaianTabulationCandidateVote> list) {
                if (list != null) {
                    List<FormC1KesesuaianTabulationCandidateVote> list2 = list;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    for (FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote : list2) {
                        arrayList.add(formC1KesesuaianTabulationCandidateVote.getVote());
                    }
                    return arrayList;
                }
                return null;
            }
        });
        this.isSesuaiPerItem = map3;
        this.koreksiPerItem = Transformations.map(map2, new Function1<List<FormC1KesesuaianTabulationCandidateVote>, List<Integer>>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$koreksiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Integer> invoke(List<FormC1KesesuaianTabulationCandidateVote> list) {
                if (list != null) {
                    List<FormC1KesesuaianTabulationCandidateVote> list2 = list;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    for (FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote : list2) {
                        arrayList.add(formC1KesesuaianTabulationCandidateVote.getVoteCorrected());
                    }
                    return arrayList;
                }
                return null;
            }
        });
        this.isCheckAllTrue = Transformations.map(map3, new Function1<List<Boolean>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$isCheckAllTrue$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<Boolean> list) {
                if (list != null) {
                    List<Boolean> list2 = list;
                    boolean z = true;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator<T> it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            } else if (!Intrinsics.areEqual((Object) ((Boolean) it.next()), (Object) true)) {
                                z = false;
                                break;
                            }
                        }
                    }
                    return Boolean.valueOf(z);
                }
                return null;
            }
        });
        this.isTableChecked = Transformations.map(map3, new Function1<List<Boolean>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$isTableChecked$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<Boolean> list) {
                boolean z;
                if (list != null) {
                    List<Boolean> list2 = list;
                    boolean z2 = true;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator<T> it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (((Boolean) it.next()) != null) {
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
                    return Boolean.valueOf(z2);
                }
                return null;
            }
        });
        this.previewImagePath = new MutableLiveData<>(null);
        CombinedLiveData<FormC1TabulationWithCandidates> combinedLiveData = new CombinedLiveData<>(new LiveData[]{map, getElectionPageUseCase.getCandidates()}, new Function1<List<? extends Object>, FormC1TabulationWithCandidates>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$formC1TabulationWithCandidates$1
            @Override // kotlin.jvm.functions.Function1
            public final VerifyTabulationViewModel.FormC1TabulationWithCandidates invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                boolean z3 = true;
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
                    List list2 = obj2 instanceof List ? (List) obj2 : null;
                    List list3 = list2;
                    if (list3 != null && !list3.isEmpty()) {
                        z3 = false;
                    }
                    if (z3) {
                        return null;
                    }
                    Object obj3 = data.get(0);
                    Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type org.informatika.sirekap.model.FormC1TabulationComplete");
                    FormC1TabulationComplete formC1TabulationComplete = (FormC1TabulationComplete) obj3;
                    List<Candidate> list4 = list2;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
                    for (Candidate candidate : list4) {
                        arrayList.add(candidate.toDecrypted());
                    }
                    return new VerifyTabulationViewModel.FormC1TabulationWithCandidates(formC1TabulationComplete, arrayList);
                }
                return null;
            }
        });
        this.formC1TabulationWithCandidates = combinedLiveData;
        LiveData<List<FormC1ListItem>> map4 = Transformations.map(combinedLiveData, new Function1<FormC1TabulationWithCandidates, List<FormC1ListItem>>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$tablePerolehanSuara$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1ListItem> invoke(VerifyTabulationViewModel.FormC1TabulationWithCandidates formC1TabulationWithCandidates) {
                FormC1TabulationComplete formC1Tabulation;
                List<FormC1TabulationCandidateVote> votes;
                int size;
                FormC1ListItem formC1ListItem;
                Object obj;
                Object obj2;
                Object obj3;
                Object obj4;
                Object obj5;
                boolean z;
                boolean z2;
                boolean z3;
                boolean z4;
                if (formC1TabulationWithCandidates == null || (formC1Tabulation = formC1TabulationWithCandidates.getFormC1Tabulation()) == null || (votes = formC1Tabulation.getVotes()) == null) {
                    return null;
                }
                List<FormC1TabulationCandidateVote> list = votes;
                VerifyTabulationViewModel verifyTabulationViewModel = VerifyTabulationViewModel.this;
                int i = 10;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                int i2 = 0;
                for (Object obj6 : list) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    FormC1TabulationCandidateVote formC1TabulationCandidateVote = (FormC1TabulationCandidateVote) obj6;
                    Election value = verifyTabulationViewModel.getGetElectionPageUseCase().getElection().getValue();
                    ElectionPage electionPage = formC1TabulationWithCandidates.getFormC1Tabulation().getElectionPage();
                    Integer aprilTagCode = electionPage.getAprilTagCode();
                    if (aprilTagCode != null) {
                        size = AprilTagConfig.INSTANCE.getCandidateNum(aprilTagCode.intValue());
                    } else if (Intrinsics.areEqual(value != null ? value.getPemilihan() : null, Election.ELECTION_PEMILIHAN_DPD)) {
                        int size2 = formC1TabulationWithCandidates.getCandidates().size();
                        size = (1 <= size2 && size2 < 9) || (13 <= size2 && size2 < 17) ? 8 : ((((9 <= size2 && size2 < 11) || (17 <= size2 && size2 < 21)) || (25 <= size2 && size2 < 31)) || (37 <= size2 && size2 < 41)) || (49 <= size2 && size2 < 51) ? i : 12;
                    } else {
                        size = formC1TabulationWithCandidates.getFormC1Tabulation().getVotes().size();
                    }
                    List<FormC1KesesuaianTabulationCandidateVote> kesesuaianVotes = formC1TabulationWithCandidates.getFormC1Tabulation().getKesesuaianVotes();
                    if (kesesuaianVotes != null) {
                        Iterator<T> it = formC1TabulationWithCandidates.getCandidates().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it.next();
                            if (((Candidate) obj).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                                break;
                            }
                        }
                        Candidate candidate = (Candidate) obj;
                        int noUrutPencalonan = candidate != null ? candidate.getNoUrutPencalonan() : 0;
                        Iterator<T> it2 = formC1TabulationWithCandidates.getCandidates().iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                obj2 = null;
                                break;
                            }
                            obj2 = it2.next();
                            if (((Candidate) obj2).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                                z4 = true;
                                continue;
                            } else {
                                z4 = false;
                                continue;
                            }
                            if (z4) {
                                break;
                            }
                        }
                        Candidate candidate2 = (Candidate) obj2;
                        String str = (candidate2 == null || (r5 = candidate2.getNama()) == null) ? "-" : "-";
                        int vote = formC1TabulationCandidateVote.getVote();
                        List<FormC1KesesuaianTabulationCandidateVote> list2 = kesesuaianVotes;
                        Iterator<T> it3 = list2.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                obj3 = null;
                                break;
                            }
                            obj3 = it3.next();
                            if (((FormC1KesesuaianTabulationCandidateVote) obj3).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                                z3 = true;
                                continue;
                            } else {
                                z3 = false;
                                continue;
                            }
                            if (z3) {
                                break;
                            }
                        }
                        FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote = (FormC1KesesuaianTabulationCandidateVote) obj3;
                        boolean isChecked = formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.isChecked() : false;
                        Iterator<T> it4 = list2.iterator();
                        while (true) {
                            if (!it4.hasNext()) {
                                obj4 = null;
                                break;
                            }
                            obj4 = it4.next();
                            if (((FormC1KesesuaianTabulationCandidateVote) obj4).getIndex() == formC1TabulationCandidateVote.getIndex()) {
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
                        FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote2 = (FormC1KesesuaianTabulationCandidateVote) obj4;
                        Boolean vote2 = formC1KesesuaianTabulationCandidateVote2 != null ? formC1KesesuaianTabulationCandidateVote2.getVote() : null;
                        Iterator<T> it5 = list2.iterator();
                        while (true) {
                            if (!it5.hasNext()) {
                                obj5 = null;
                                break;
                            }
                            obj5 = it5.next();
                            if (((FormC1KesesuaianTabulationCandidateVote) obj5).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                                z = true;
                                continue;
                            } else {
                                z = false;
                                continue;
                            }
                            if (z) {
                                break;
                            }
                        }
                        FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote3 = (FormC1KesesuaianTabulationCandidateVote) obj5;
                        formC1ListItem = new FormC1ListItem("FORM_C1_ROW_CANDIDATE_", Integer.valueOf(noUrutPencalonan), str, null, null, vote, isChecked, null, null, vote2, Integer.valueOf(formC1TabulationCandidateVote.getIndex()), null, null, formC1KesesuaianTabulationCandidateVote3 != null ? formC1KesesuaianTabulationCandidateVote3.getVoteCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), Integer.valueOf(size), value != null ? value.getPemilihan() : null, value != null ? Boolean.valueOf(value.isLn()) : null, value != null ? Boolean.valueOf(value.isLnPos()) : null, 6144, null);
                    } else {
                        formC1ListItem = null;
                    }
                    arrayList.add(formC1ListItem);
                    i2 = i3;
                    i = 10;
                }
                return arrayList;
            }
        });
        this.tablePerolehanSuara = map4;
        this.isTablePerolehanSuaraEmpty = Transformations.map(map4, new Function1<List<FormC1ListItem>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$isTablePerolehanSuaraEmpty$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<FormC1ListItem> list) {
                List<FormC1ListItem> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.tablePerolehanSuaraTitle = Transformations.map(getElectionPageUseCase.getElection(), new Function1<Election, String>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$tablePerolehanSuaraTitle$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final String invoke(Election election) {
                HashMap jsonString;
                if (election == null) {
                    return "";
                }
                jsonString = VerifyTabulationViewModel.this.getJsonString(context, election.getPemilihan(), election.isLnPos(), election.isLn());
                Object obj = jsonString != null ? jsonString.get("iv") : null;
                if (obj instanceof String) {
                    return (String) obj;
                }
                return null;
            }
        });
        MutableLiveData<BaseVerifyViewModel.VerifyFormC1Model> mutableLiveData2 = new MutableLiveData<>(null);
        this.submittedFormC1 = mutableLiveData2;
        LiveData<Resource<FormC1AdministrationComplete>> switchMap2 = Transformations.switchMap(mutableLiveData2, new Function1<BaseVerifyViewModel.VerifyFormC1Model, LiveData<Resource<FormC1AdministrationComplete>>>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$submittedFormResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1AdministrationComplete>> invoke(BaseVerifyViewModel.VerifyFormC1Model verifyFormC1Model) {
                MutableLiveData mutableLiveData3;
                FormC1Repository formC1Repository2;
                if (verifyFormC1Model != null) {
                    mutableLiveData3 = VerifyTabulationViewModel.this.electionPageId;
                    String str = (String) mutableLiveData3.getValue();
                    if (str != null) {
                        formC1Repository2 = VerifyTabulationViewModel.this.formC1Repository;
                        String jenisPemilihan = verifyFormC1Model.getJenisPemilihan();
                        String deviceId = verifyFormC1Model.getDeviceId();
                        String idImage = verifyFormC1Model.getIdImage();
                        boolean isSesuai = verifyFormC1Model.isSesuai();
                        List<Boolean> isSesuaiPerItem = verifyFormC1Model.isSesuaiPerItem();
                        String komentar = verifyFormC1Model.getKomentar();
                        if (komentar == null) {
                            komentar = "";
                        }
                        return formC1Repository2.verifyFormC1(jenisPemilihan, deviceId, 2, str, idImage, isSesuai, isSesuaiPerItem, komentar, verifyFormC1Model.getKoreksiPerItem(), verifyFormC1Model.getKodeTps());
                    }
                    return null;
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.submittedFormResource = switchMap2;
        this.isLoadingVerification = Transformations.map(switchMap2, new Function1<Resource<FormC1AdministrationComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel$isLoadingVerification$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
    }

    public final GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.getElectionPageUseCase;
    }

    public final LiveData<Resource<FormC1TabulationComplete>> getFormC1TabulationResource() {
        return this.formC1TabulationResource;
    }

    public final LiveData<FormC1TabulationComplete> getFormC1Tabulation() {
        return this.formC1Tabulation;
    }

    public final LiveData<FormC1Error> getFormC1Error() {
        return this.formC1Error;
    }

    public final LiveData<FormC1Kesesuaian> getFormC1Kesesuaian() {
        return this.formC1Kesesuaian;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final LiveData<Boolean> isError() {
        return this.isError;
    }

    public final LiveData<List<Boolean>> isSesuaiPerItem() {
        return this.isSesuaiPerItem;
    }

    public final LiveData<List<Integer>> getKoreksiPerItem() {
        return this.koreksiPerItem;
    }

    public final LiveData<Boolean> isCheckAllTrue() {
        return this.isCheckAllTrue;
    }

    public final LiveData<Boolean> isTableChecked() {
        return this.isTableChecked;
    }

    public final MutableLiveData<String> getPreviewImagePath() {
        return this.previewImagePath;
    }

    /* compiled from: VerifyTabulationViewModel.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/ui/verify/tabulation/VerifyTabulationViewModel$FormC1TabulationWithCandidates;", "", "formC1Tabulation", "Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "candidates", "", "Lorg/informatika/sirekap/model/Candidate;", "(Lorg/informatika/sirekap/model/FormC1TabulationComplete;Ljava/util/List;)V", "getCandidates", "()Ljava/util/List;", "getFormC1Tabulation", "()Lorg/informatika/sirekap/model/FormC1TabulationComplete;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class FormC1TabulationWithCandidates {
        private final List<Candidate> candidates;
        private final FormC1TabulationComplete formC1Tabulation;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1TabulationWithCandidates copy$default(FormC1TabulationWithCandidates formC1TabulationWithCandidates, FormC1TabulationComplete formC1TabulationComplete, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                formC1TabulationComplete = formC1TabulationWithCandidates.formC1Tabulation;
            }
            if ((i & 2) != 0) {
                list = formC1TabulationWithCandidates.candidates;
            }
            return formC1TabulationWithCandidates.copy(formC1TabulationComplete, list);
        }

        public final FormC1TabulationComplete component1() {
            return this.formC1Tabulation;
        }

        public final List<Candidate> component2() {
            return this.candidates;
        }

        public final FormC1TabulationWithCandidates copy(FormC1TabulationComplete formC1Tabulation, List<Candidate> candidates) {
            Intrinsics.checkNotNullParameter(formC1Tabulation, "formC1Tabulation");
            Intrinsics.checkNotNullParameter(candidates, "candidates");
            return new FormC1TabulationWithCandidates(formC1Tabulation, candidates);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof FormC1TabulationWithCandidates) {
                FormC1TabulationWithCandidates formC1TabulationWithCandidates = (FormC1TabulationWithCandidates) obj;
                return Intrinsics.areEqual(this.formC1Tabulation, formC1TabulationWithCandidates.formC1Tabulation) && Intrinsics.areEqual(this.candidates, formC1TabulationWithCandidates.candidates);
            }
            return false;
        }

        public int hashCode() {
            return (this.formC1Tabulation.hashCode() * 31) + this.candidates.hashCode();
        }

        public String toString() {
            FormC1TabulationComplete formC1TabulationComplete = this.formC1Tabulation;
            return "FormC1TabulationWithCandidates(formC1Tabulation=" + formC1TabulationComplete + ", candidates=" + this.candidates + ")";
        }

        public FormC1TabulationWithCandidates(FormC1TabulationComplete formC1Tabulation, List<Candidate> candidates) {
            Intrinsics.checkNotNullParameter(formC1Tabulation, "formC1Tabulation");
            Intrinsics.checkNotNullParameter(candidates, "candidates");
            this.formC1Tabulation = formC1Tabulation;
            this.candidates = candidates;
        }

        public final FormC1TabulationComplete getFormC1Tabulation() {
            return this.formC1Tabulation;
        }

        public final List<Candidate> getCandidates() {
            return this.candidates;
        }
    }

    public final LiveData<List<FormC1ListItem>> getTablePerolehanSuara() {
        return this.tablePerolehanSuara;
    }

    public final LiveData<Boolean> isTablePerolehanSuaraEmpty() {
        return this.isTablePerolehanSuaraEmpty;
    }

    public final LiveData<String> getTablePerolehanSuaraTitle() {
        return this.tablePerolehanSuaraTitle;
    }

    public final HashMap<?, ?> getJsonString(Context context, String str, boolean z, boolean z2) {
        Integer valueOf;
        int i;
        if (Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_PRESIDEN)) {
            if (z) {
                i = R.raw.ppwp_lnpos_strings;
            } else if (z2) {
                i = R.raw.ppwp_ln_strings;
            } else {
                i = R.raw.ppwp_dn_strings;
            }
            valueOf = Integer.valueOf(i);
        } else {
            valueOf = Intrinsics.areEqual(str, Election.ELECTION_PEMILIHAN_DPD) ? Integer.valueOf(R.raw.pdpd_strings) : null;
        }
        if (valueOf == null) {
            return null;
        }
        int intValue = valueOf.intValue();
        Gson gson = new Gson();
        InputStream openRawResource = context.getResources().openRawResource(intValue);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "context.resources.openRawResource(jsonStringsStr)");
        InputStreamReader inputStreamReader = new InputStreamReader(openRawResource, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String readText = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            return (HashMap) gson.fromJson(readText, (Class<Object>) HashMap.class);
        } finally {
        }
    }

    public final void setup(String _idImage, String _electionPageId, String _jenisPemilihan, String _kodeTps) {
        Intrinsics.checkNotNullParameter(_idImage, "_idImage");
        Intrinsics.checkNotNullParameter(_electionPageId, "_electionPageId");
        Intrinsics.checkNotNullParameter(_jenisPemilihan, "_jenisPemilihan");
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        if (!Intrinsics.areEqual(this.kodeTps.getValue(), _kodeTps)) {
            this.kodeTps.setValue(_kodeTps);
        }
        if (!Intrinsics.areEqual(this.jenisPemilihan.getValue(), _jenisPemilihan)) {
            this.jenisPemilihan.setValue(_jenisPemilihan);
        }
        if (!Intrinsics.areEqual(this.idImage.getValue(), _idImage)) {
            this.idImage.setValue(_idImage);
        }
        if (Intrinsics.areEqual(this.electionPageId.getValue(), _electionPageId)) {
            return;
        }
        this.electionPageId.setValue(_electionPageId);
    }

    public final LiveData<Resource<FormC1AdministrationComplete>> getSubmittedFormResource() {
        return this.submittedFormResource;
    }

    public final LiveData<Boolean> isLoadingVerification() {
        return this.isLoadingVerification;
    }

    public final void submitVerification(Context context, AlertDialog alertDialog) {
        List<Boolean> isSesuaiPerItem;
        List<Integer> koreksiPerItem;
        Boolean isCheckAllTrue;
        Intrinsics.checkNotNullParameter(context, "context");
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        String value = this.idImage.getValue();
        if (value == null || (isSesuaiPerItem = this.isSesuaiPerItem.getValue()) == null || (koreksiPerItem = this.koreksiPerItem.getValue()) == null || (isCheckAllTrue = this.isCheckAllTrue.getValue()) == null) {
            return;
        }
        MutableLiveData<BaseVerifyViewModel.VerifyFormC1Model> mutableLiveData = this.submittedFormC1;
        String deviceId = SecurityFacade.INSTANCE.getDeviceId(context);
        String value2 = this.jenisPemilihan.getValue();
        Intrinsics.checkNotNull(value2);
        String str = value2;
        String value3 = this.kodeTps.getValue();
        Intrinsics.checkNotNull(value3);
        Intrinsics.checkNotNullExpressionValue(isCheckAllTrue, "isCheckAllTrue");
        boolean booleanValue = isCheckAllTrue.booleanValue();
        Intrinsics.checkNotNullExpressionValue(isSesuaiPerItem, "isSesuaiPerItem");
        Intrinsics.checkNotNullExpressionValue(koreksiPerItem, "koreksiPerItem");
        mutableLiveData.postValue(new BaseVerifyViewModel.VerifyFormC1Model(value, booleanValue, isSesuaiPerItem, null, koreksiPerItem, deviceId, str, value3));
    }

    public final void saveChecked(FormC1ListItem item) {
        Boolean value;
        Integer num;
        Intrinsics.checkNotNullParameter(item, "item");
        String value2 = this.idImage.getValue();
        if (value2 == null || this.isSesuaiPerItem.getValue() == null || (value = isWrongTotal().getValue()) == null) {
            return;
        }
        String correctedTotal = getCorrectedTotal().getValue();
        Integer index = item.getIndex();
        if (index != null) {
            int intValue = index.intValue();
            FormC1Repository formC1Repository = this.formC1Repository;
            Boolean valueOf = Boolean.valueOf(!value.booleanValue());
            if (correctedTotal != null) {
                Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                num = StringsKt.toIntOrNull(correctedTotal);
            } else {
                num = null;
            }
            formC1Repository.saveFormC1KesesuaianTabulationCandidateVote(value2, intValue, valueOf, num);
        }
    }

    public final void continueVerify(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ElectionPage value = this.getElectionPageUseCase.getElectionPage().getValue();
        ElectionWithRelation value2 = this.getElectionPageUseCase.getElectionWithRelation().getValue();
        if (value2 == null || value == null) {
            return;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(value.getCorrectedPhotoPath());
        if (decodeFile == null) {
            decodeFile = BitmapFactory.decodeFile(value.getCroppedPhotoPath());
        }
        Bitmap bitmap = decodeFile;
        FormC1Repository formC1Repository = this.formC1Repository;
        String value3 = this.idImage.getValue();
        Intrinsics.checkNotNull(value3);
        String str = value3;
        String value4 = this.jenisPemilihan.getValue();
        Intrinsics.checkNotNull(value4);
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        formC1Repository.createEmptyFormC1Tabulation(str, value4, bitmap, value2.getElection().isLn(), value2.getElection().isLnPos(), true);
    }

    public final void refreshIdImage() {
        String value = this.idImage.getValue();
        if (value != null) {
            this.idImage.postValue(value);
        }
    }
}
