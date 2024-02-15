package org.informatika.sirekap.ui.verify.tabulationPartai;

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
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
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
import org.informatika.sirekap.model.FormC1KesesuaianTabulationPartai;
import org.informatika.sirekap.model.FormC1TabulationCandidateVote;
import org.informatika.sirekap.model.FormC1TabulationPartai;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
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
import org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel;

/* compiled from: VerifyTabulationPartaiViewModel.kt */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001OB#\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010>\u001a\u00020?J2\u0010@\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010A2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u000b2\u0006\u0010B\u001a\u00020#2\u0006\u0010C\u001a\u00020#H\u0002J\u0006\u0010D\u001a\u00020?J\u000e\u0010E\u001a\u00020?2\u0006\u0010F\u001a\u00020:J&\u0010G\u001a\u00020?2\u0006\u0010H\u001a\u00020\u000b2\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000bJ\u0018\u0010L\u001a\u00020?2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010M\u001a\u0004\u0018\u00010NR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u001f\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00180\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0010R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\r¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0010R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020#0\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0010R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020#0\r¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0010R!\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010#\u0018\u00010(0\r¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0010R\u0019\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0010R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020#0\r¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0010R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010-\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010.\u0018\u00010(0\r¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0010R\u0019\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0014\u00103\u001a\b\u0012\u0004\u0012\u0002040\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u00105\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000206\u0018\u00010\u00180\r¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0010R\u001f\u00108\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020:\u0018\u0001090\r¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0010R\u0019\u0010<\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0010¨\u0006P"}, d2 = {"Lorg/informatika/sirekap/ui/verify/tabulationPartai/VerifyTabulationPartaiViewModel;", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel;", "context", "Landroid/content/Context;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/FormC1Repository;Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "electionPageId", "Landroidx/lifecycle/MutableLiveData;", "", "formC1Error", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/FormC1Error;", "getFormC1Error", "()Landroidx/lifecycle/LiveData;", "formC1Kesesuaian", "Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "getFormC1Kesesuaian", "formC1TabulationPartaiComplete", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "getFormC1TabulationPartaiComplete", "formC1TabulationPartaiCompleteResource", "Lorg/informatika/sirekap/support/Resource;", "getFormC1TabulationPartaiCompleteResource", "formC1TabulationWithCandidates", "Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "Lorg/informatika/sirekap/ui/verify/tabulationPartai/VerifyTabulationPartaiViewModel$FormC1TabulationWithCandidates;", "getElectionPageUseCase", "Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "getGetElectionPageUseCase", "()Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "idImage", "isCheckAllTrue", "", "isError", "isLoading", "isLoadingVerification", "isSesuaiPerItem", "", "isTableChecked", "isTablePerolehanSuaraEmpty", "jenisPemilihan", "kodeTps", "koreksiPerItem", "", "getKoreksiPerItem", "previewImagePath", "getPreviewImagePath", "()Landroidx/lifecycle/MutableLiveData;", "submittedFormC1", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel$VerifyFormC1Model;", "submittedFormResource", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "getSubmittedFormResource", "tablePerolehanSuara", "", "Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "getTablePerolehanSuara", "tablePerolehanSuaraTitle", "getTablePerolehanSuaraTitle", "continueVerify", "", "getJsonString", "Ljava/util/HashMap;", "isLnPos", "isLn", "refreshIdImage", "saveChecked", "item", "setup", "_idImage", "_electionPageId", "_jenisPemilihan", "_kodeTps", "submitVerification", "dialog", "Landroidx/appcompat/app/AlertDialog;", "FormC1TabulationWithCandidates", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyTabulationPartaiViewModel extends BaseVerifyViewModel {
    private final MutableLiveData<String> electionPageId;
    private final LiveData<FormC1Error> formC1Error;
    private final LiveData<FormC1Kesesuaian> formC1Kesesuaian;
    private final FormC1Repository formC1Repository;
    private final LiveData<FormC1TabulationPartaiComplete> formC1TabulationPartaiComplete;
    private final LiveData<Resource<FormC1TabulationPartaiComplete>> formC1TabulationPartaiCompleteResource;
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
    public VerifyTabulationPartaiViewModel(@ApplicationContext final Context context, FormC1Repository formC1Repository, DefaultElectionRepository electionRepository) {
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
        LiveData<Resource<FormC1TabulationPartaiComplete>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<FormC1TabulationPartaiComplete>>>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$formC1TabulationPartaiCompleteResource$1
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
                    formC1Repository2 = VerifyTabulationPartaiViewModel.this.formC1Repository;
                    mutableLiveData2 = VerifyTabulationPartaiViewModel.this.jenisPemilihan;
                    T value = mutableLiveData2.getValue();
                    Intrinsics.checkNotNull(value);
                    String str2 = (String) value;
                    mutableLiveData3 = VerifyTabulationPartaiViewModel.this.kodeTps;
                    T value2 = mutableLiveData3.getValue();
                    Intrinsics.checkNotNull(value2);
                    return FormC1Repository.DefaultImpls.getFormC1TabulationPartai$default(formC1Repository2, str, str2, false, (String) value2, 4, null);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1TabulationPartaiCompleteResource = switchMap;
        LiveData<FormC1TabulationPartaiComplete> map = Transformations.map(switchMap, new Function1<Resource<FormC1TabulationPartaiComplete>, FormC1TabulationPartaiComplete>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$formC1TabulationPartaiComplete$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1TabulationPartaiComplete invoke(Resource<FormC1TabulationPartaiComplete> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.formC1TabulationPartaiComplete = map;
        this.formC1Error = Transformations.map(map, new Function1<FormC1TabulationPartaiComplete, FormC1Error>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$formC1Error$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1Error invoke(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete) {
                if (formC1TabulationPartaiComplete != null) {
                    return formC1TabulationPartaiComplete.getError();
                }
                return null;
            }
        });
        this.formC1Kesesuaian = Transformations.map(map, new Function1<FormC1TabulationPartaiComplete, FormC1Kesesuaian>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$formC1Kesesuaian$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1Kesesuaian invoke(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete) {
                if (formC1TabulationPartaiComplete != null) {
                    return formC1TabulationPartaiComplete.getKesesuaian();
                }
                return null;
            }
        });
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<FormC1TabulationPartaiComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1TabulationPartaiComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.isError = Transformations.map(switchMap, new Function1<Resource<FormC1TabulationPartaiComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$isError$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1TabulationPartaiComplete> resource) {
                boolean z;
                if ((resource != null ? resource.getSuccess() : null) == ResourceStatus.ERROR) {
                    FormC1TabulationPartaiComplete payload = resource.getPayload();
                    if ((payload != null ? payload.getKesesuaianTabulationPartai() : null) == null) {
                        z = true;
                        return Boolean.valueOf(z);
                    }
                }
                z = false;
                return Boolean.valueOf(z);
            }
        });
        LiveData<List<Boolean>> map2 = Transformations.map(map, new Function1<FormC1TabulationPartaiComplete, List<Boolean>>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$isSesuaiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Boolean> invoke(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete) {
                if (formC1TabulationPartaiComplete != null) {
                    return formC1TabulationPartaiComplete.toIsSesuaiPerItem();
                }
                return null;
            }
        });
        this.isSesuaiPerItem = map2;
        this.koreksiPerItem = Transformations.map(map, new Function1<FormC1TabulationPartaiComplete, List<Integer>>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$koreksiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Integer> invoke(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete) {
                if (formC1TabulationPartaiComplete != null) {
                    return formC1TabulationPartaiComplete.toKoreksiPerItem();
                }
                return null;
            }
        });
        this.isCheckAllTrue = Transformations.map(map2, new Function1<List<Boolean>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$isCheckAllTrue$1
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
        this.isTableChecked = Transformations.map(map2, new Function1<List<Boolean>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$isTableChecked$1
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
        CombinedLiveData<FormC1TabulationWithCandidates> combinedLiveData = new CombinedLiveData<>(new LiveData[]{map, getElectionPageUseCase.getCandidates()}, new Function1<List<? extends Object>, FormC1TabulationWithCandidates>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$formC1TabulationWithCandidates$1
            @Override // kotlin.jvm.functions.Function1
            public final VerifyTabulationPartaiViewModel.FormC1TabulationWithCandidates invoke(List<? extends Object> data) {
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
                    Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type org.informatika.sirekap.model.FormC1TabulationPartaiComplete");
                    FormC1TabulationPartaiComplete formC1TabulationPartaiComplete = (FormC1TabulationPartaiComplete) obj3;
                    List<Candidate> list4 = list2;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
                    for (Candidate candidate : list4) {
                        arrayList.add(candidate.toDecrypted());
                    }
                    return new VerifyTabulationPartaiViewModel.FormC1TabulationWithCandidates(formC1TabulationPartaiComplete, arrayList);
                }
                return null;
            }
        });
        this.formC1TabulationWithCandidates = combinedLiveData;
        LiveData<List<FormC1ListItem>> map3 = Transformations.map(combinedLiveData, new Function1<FormC1TabulationWithCandidates, List<FormC1ListItem>>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$tablePerolehanSuara$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1ListItem> invoke(VerifyTabulationPartaiViewModel.FormC1TabulationWithCandidates formC1TabulationWithCandidates) {
                int size;
                Integer suratSahPartaiDanCalonCorrected;
                boolean z;
                Unit unit;
                Object obj;
                Object obj2;
                Object obj3;
                Integer voteCorrected;
                boolean z2;
                Set<String> keySet;
                List list;
                if (formC1TabulationWithCandidates == null) {
                    return null;
                }
                ElectionWithRelation value = VerifyTabulationPartaiViewModel.this.getGetElectionPageUseCase().getElectionWithRelation().getValue();
                Map<String, Long> allPartais = value != null ? value.getAllPartais() : null;
                ElectionPage value2 = VerifyTabulationPartaiViewModel.this.getGetElectionPageUseCase().getElectionPage().getValue();
                boolean z3 = true;
                String str = (allPartais == null || (keySet = allPartais.keySet()) == null || (list = CollectionsKt.toList(keySet)) == null) ? null : (String) list.get((value2 != null ? value2.getNumber() : 1) - 1);
                ArrayList arrayList = new ArrayList();
                FormC1TabulationPartai form = formC1TabulationWithCandidates.getFormC1TabulationPartaiComplete().getForm();
                FormC1KesesuaianTabulationPartai kesesuaianTabulationPartai = formC1TabulationWithCandidates.getFormC1TabulationPartaiComplete().getKesesuaianTabulationPartai();
                Integer aprilTagCode = value2 != null ? value2.getAprilTagCode() : null;
                if (aprilTagCode != null) {
                    size = AprilTagConfig.INSTANCE.getCandidateNum(aprilTagCode.intValue());
                } else {
                    size = formC1TabulationWithCandidates.getFormC1TabulationPartaiComplete().getVotes().size() - 1;
                }
                Election value3 = VerifyTabulationPartaiViewModel.this.getGetElectionPageUseCase().getElection().getValue();
                List<FormC1TabulationCandidateVote> votes = formC1TabulationWithCandidates.getFormC1TabulationPartaiComplete().getVotes();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(votes, 10));
                int i = 0;
                int i2 = 0;
                for (Object obj4 : votes) {
                    int i3 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    FormC1TabulationCandidateVote formC1TabulationCandidateVote = (FormC1TabulationCandidateVote) obj4;
                    List<FormC1KesesuaianTabulationCandidateVote> kesesuaianVotes = formC1TabulationWithCandidates.getFormC1TabulationPartaiComplete().getKesesuaianVotes();
                    if (kesesuaianVotes != null) {
                        Iterator<T> it = kesesuaianVotes.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it.next();
                            if (((FormC1KesesuaianTabulationCandidateVote) obj).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                                z2 = z3;
                                continue;
                            } else {
                                z2 = false;
                                continue;
                            }
                            if (z2) {
                                break;
                            }
                        }
                        FormC1KesesuaianTabulationCandidateVote formC1KesesuaianTabulationCandidateVote = (FormC1KesesuaianTabulationCandidateVote) obj;
                        if (i <= 0) {
                            arrayList.add(new FormC1ListItem("FORM_C1_ROW_CANDIDATE_", null, str == null ? "Suara Partai" : str, null, null, formC1TabulationCandidateVote.getVote(), formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.isChecked() : false, null, null, formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.getVote() : null, Integer.valueOf(formC1TabulationCandidateVote.getIndex()), null, null, formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.getVoteCorrected() : null, Boolean.valueOf(z3), null, value2 != null ? value2.getCroppedPhotoPath() : null, value2 != null ? value2.getCorrectedPhotoPath() : null, Integer.valueOf(size), value3 != null ? value3.getPemilihan() : null, value3 != null ? Boolean.valueOf(value3.isLn()) : null, value3 != null ? Boolean.valueOf(value3.isLnPos()) : null, 6144, null));
                            z = z3;
                        } else {
                            Iterator<T> it2 = formC1TabulationWithCandidates.getCandidates().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    obj2 = null;
                                    break;
                                }
                                obj2 = it2.next();
                                if (((Candidate) obj2).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                                    break;
                                }
                            }
                            Candidate candidate = (Candidate) obj2;
                            int noUrutPencalonan = candidate != null ? candidate.getNoUrutPencalonan() : 0;
                            Iterator<T> it3 = formC1TabulationWithCandidates.getCandidates().iterator();
                            while (true) {
                                if (!it3.hasNext()) {
                                    obj3 = null;
                                    break;
                                }
                                obj3 = it3.next();
                                if (((Candidate) obj3).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                                    break;
                                }
                            }
                            Candidate candidate2 = (Candidate) obj3;
                            String str2 = (candidate2 == null || (r0 = candidate2.getNama()) == null) ? "-" : "-";
                            int vote = formC1TabulationCandidateVote.getVote();
                            boolean isChecked = formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.isChecked() : false;
                            Boolean vote2 = formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.getVote() : null;
                            Integer voteCorrected2 = formC1KesesuaianTabulationCandidateVote != null ? formC1KesesuaianTabulationCandidateVote.getVoteCorrected() : null;
                            int index = formC1TabulationCandidateVote.getIndex();
                            String croppedPhotoPath = value2 != null ? value2.getCroppedPhotoPath() : null;
                            String correctedPhotoPath = value2 != null ? value2.getCorrectedPhotoPath() : null;
                            String pemilihan = value3 != null ? value3.getPemilihan() : null;
                            Boolean valueOf = value3 != null ? Boolean.valueOf(value3.isLn()) : null;
                            Boolean valueOf2 = value3 != null ? Boolean.valueOf(value3.isLnPos()) : null;
                            Integer valueOf3 = Integer.valueOf(noUrutPencalonan);
                            Integer valueOf4 = Integer.valueOf(index);
                            z = true;
                            arrayList.add(new FormC1ListItem("FORM_C1_ROW_CANDIDATE_", valueOf3, str2, null, null, vote, isChecked, null, null, vote2, valueOf4, null, null, voteCorrected2, true, null, croppedPhotoPath, correctedPhotoPath, Integer.valueOf(size), pemilihan, valueOf, valueOf2, 6144, null));
                        }
                        i2 += (formC1KesesuaianTabulationCandidateVote == null || (voteCorrected = formC1KesesuaianTabulationCandidateVote.getVoteCorrected()) == null) ? formC1TabulationCandidateVote.getVote() : voteCorrected.intValue();
                        unit = Unit.INSTANCE;
                    } else {
                        z = z3;
                        unit = null;
                    }
                    arrayList2.add(unit);
                    z3 = z;
                    i = i3;
                }
                boolean z4 = z3;
                if ((form != null ? form.getSuratSahPartaiDanCalon() : null) != null) {
                    arrayList.add(new FormC1ListItem(FormC1TabulationPartai.FORM_C1_ROW_SUARA_SAH_PARTAI_CALON, null, "Jumlah Suara Sah Partai Politik dan Calon ( A.1 + A.2 )", null, null, form.getSuratSahPartaiDanCalon().intValue(), kesesuaianTabulationPartai != null ? kesesuaianTabulationPartai.isCheckedRowSuratSahPartaiDanCalon() : false, null, null, kesesuaianTabulationPartai != null ? kesesuaianTabulationPartai.getSuratSahPartaiDanCalon() : null, null, null, null, kesesuaianTabulationPartai != null ? kesesuaianTabulationPartai.getSuratSahPartaiDanCalonCorrected() : null, Boolean.valueOf(((kesesuaianTabulationPartai == null || (suratSahPartaiDanCalonCorrected = kesesuaianTabulationPartai.getSuratSahPartaiDanCalonCorrected()) == null) ? form.getSuratSahPartaiDanCalon().intValue() : suratSahPartaiDanCalonCorrected.intValue()) == i2 ? z4 : false), "Jumlah harus sama dengan A.1. + A.2", value2 != null ? value2.getCroppedPhotoPath() : null, value2 != null ? value2.getCorrectedPhotoPath() : null, Integer.valueOf(size), value3 != null ? value3.getPemilihan() : null, value3 != null ? Boolean.valueOf(value3.isLn()) : null, value3 != null ? Boolean.valueOf(value3.isLnPos()) : null, 7168, null));
                }
                return arrayList;
            }
        });
        this.tablePerolehanSuara = map3;
        this.isTablePerolehanSuaraEmpty = Transformations.map(map3, new Function1<List<FormC1ListItem>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$isTablePerolehanSuaraEmpty$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<FormC1ListItem> list) {
                List<FormC1ListItem> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.tablePerolehanSuaraTitle = Transformations.map(getElectionPageUseCase.getElection(), new Function1<Election, String>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$tablePerolehanSuaraTitle$1
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
                jsonString = VerifyTabulationPartaiViewModel.this.getJsonString(context, election.getPemilihan(), election.isLnPos(), election.isLn());
                Object obj = jsonString != null ? jsonString.get("iv") : null;
                if (obj instanceof String) {
                    return (String) obj;
                }
                return null;
            }
        });
        MutableLiveData<BaseVerifyViewModel.VerifyFormC1Model> mutableLiveData2 = new MutableLiveData<>(null);
        this.submittedFormC1 = mutableLiveData2;
        LiveData<Resource<FormC1AdministrationComplete>> switchMap2 = Transformations.switchMap(mutableLiveData2, new Function1<BaseVerifyViewModel.VerifyFormC1Model, LiveData<Resource<FormC1AdministrationComplete>>>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$submittedFormResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1AdministrationComplete>> invoke(BaseVerifyViewModel.VerifyFormC1Model verifyFormC1Model) {
                MutableLiveData mutableLiveData3;
                FormC1Repository formC1Repository2;
                if (verifyFormC1Model != null) {
                    mutableLiveData3 = VerifyTabulationPartaiViewModel.this.electionPageId;
                    String str = (String) mutableLiveData3.getValue();
                    if (str != null) {
                        formC1Repository2 = VerifyTabulationPartaiViewModel.this.formC1Repository;
                        String jenisPemilihan = verifyFormC1Model.getJenisPemilihan();
                        String deviceId = verifyFormC1Model.getDeviceId();
                        String idImage = verifyFormC1Model.getIdImage();
                        boolean isSesuai = verifyFormC1Model.isSesuai();
                        List<Boolean> isSesuaiPerItem = verifyFormC1Model.isSesuaiPerItem();
                        String komentar = verifyFormC1Model.getKomentar();
                        if (komentar == null) {
                            komentar = "";
                        }
                        return formC1Repository2.verifyFormC1(jenisPemilihan, deviceId, 4, str, idImage, isSesuai, isSesuaiPerItem, komentar, verifyFormC1Model.getKoreksiPerItem(), verifyFormC1Model.getKodeTps());
                    }
                    return null;
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.submittedFormResource = switchMap2;
        this.isLoadingVerification = Transformations.map(switchMap2, new Function1<Resource<FormC1AdministrationComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel$isLoadingVerification$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
    }

    public final GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.getElectionPageUseCase;
    }

    public final LiveData<Resource<FormC1TabulationPartaiComplete>> getFormC1TabulationPartaiCompleteResource() {
        return this.formC1TabulationPartaiCompleteResource;
    }

    public final LiveData<FormC1TabulationPartaiComplete> getFormC1TabulationPartaiComplete() {
        return this.formC1TabulationPartaiComplete;
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

    /* compiled from: VerifyTabulationPartaiViewModel.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/ui/verify/tabulationPartai/VerifyTabulationPartaiViewModel$FormC1TabulationWithCandidates;", "", "formC1TabulationPartaiComplete", "Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "candidates", "", "Lorg/informatika/sirekap/model/Candidate;", "(Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;Ljava/util/List;)V", "getCandidates", "()Ljava/util/List;", "getFormC1TabulationPartaiComplete", "()Lorg/informatika/sirekap/model/FormC1TabulationPartaiComplete;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class FormC1TabulationWithCandidates {
        private final List<Candidate> candidates;
        private final FormC1TabulationPartaiComplete formC1TabulationPartaiComplete;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1TabulationWithCandidates copy$default(FormC1TabulationWithCandidates formC1TabulationWithCandidates, FormC1TabulationPartaiComplete formC1TabulationPartaiComplete, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                formC1TabulationPartaiComplete = formC1TabulationWithCandidates.formC1TabulationPartaiComplete;
            }
            if ((i & 2) != 0) {
                list = formC1TabulationWithCandidates.candidates;
            }
            return formC1TabulationWithCandidates.copy(formC1TabulationPartaiComplete, list);
        }

        public final FormC1TabulationPartaiComplete component1() {
            return this.formC1TabulationPartaiComplete;
        }

        public final List<Candidate> component2() {
            return this.candidates;
        }

        public final FormC1TabulationWithCandidates copy(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete, List<Candidate> candidates) {
            Intrinsics.checkNotNullParameter(formC1TabulationPartaiComplete, "formC1TabulationPartaiComplete");
            Intrinsics.checkNotNullParameter(candidates, "candidates");
            return new FormC1TabulationWithCandidates(formC1TabulationPartaiComplete, candidates);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof FormC1TabulationWithCandidates) {
                FormC1TabulationWithCandidates formC1TabulationWithCandidates = (FormC1TabulationWithCandidates) obj;
                return Intrinsics.areEqual(this.formC1TabulationPartaiComplete, formC1TabulationWithCandidates.formC1TabulationPartaiComplete) && Intrinsics.areEqual(this.candidates, formC1TabulationWithCandidates.candidates);
            }
            return false;
        }

        public int hashCode() {
            return (this.formC1TabulationPartaiComplete.hashCode() * 31) + this.candidates.hashCode();
        }

        public String toString() {
            FormC1TabulationPartaiComplete formC1TabulationPartaiComplete = this.formC1TabulationPartaiComplete;
            return "FormC1TabulationWithCandidates(formC1TabulationPartaiComplete=" + formC1TabulationPartaiComplete + ", candidates=" + this.candidates + ")";
        }

        public FormC1TabulationWithCandidates(FormC1TabulationPartaiComplete formC1TabulationPartaiComplete, List<Candidate> candidates) {
            Intrinsics.checkNotNullParameter(formC1TabulationPartaiComplete, "formC1TabulationPartaiComplete");
            Intrinsics.checkNotNullParameter(candidates, "candidates");
            this.formC1TabulationPartaiComplete = formC1TabulationPartaiComplete;
            this.candidates = candidates;
        }

        public final FormC1TabulationPartaiComplete getFormC1TabulationPartaiComplete() {
            return this.formC1TabulationPartaiComplete;
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
        int hashCode = str.hashCode();
        if (hashCode == -992700931) {
            if (str.equals(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                valueOf = Integer.valueOf(R.raw.pdprdk_strings);
            }
            valueOf = null;
        } else if (hashCode != -992700926) {
            if (hashCode == 3436278 && str.equals(Election.ELECTION_PEMILIHAN_DPR)) {
                valueOf = Integer.valueOf(z ? R.raw.pdpr_lnpos_strings : z2 ? R.raw.pdpr_ln_strings : R.raw.pdpr_dn_strings);
            }
            valueOf = null;
        } else {
            if (str.equals(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                valueOf = Integer.valueOf(R.raw.pdprdp_strings);
            }
            valueOf = null;
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
        Intrinsics.checkNotNullParameter(item, "item");
        String value2 = this.idImage.getValue();
        if (value2 == null || (value = isWrongTotal().getValue()) == null) {
            return;
        }
        String correctedTotal = getCorrectedTotal().getValue();
        Integer num = null;
        if (!Intrinsics.areEqual(item.getId(), "FORM_C1_ROW_CANDIDATE_")) {
            List<Boolean> mutableListOf = CollectionsKt.mutableListOf(null);
            List<Integer> mutableListOf2 = CollectionsKt.mutableListOf(null);
            if (Intrinsics.areEqual(item.getId(), FormC1TabulationPartai.FORM_C1_ROW_SUARA_SAH_PARTAI_CALON)) {
                mutableListOf.set(0, Boolean.valueOf(!value.booleanValue()));
                if (correctedTotal != null) {
                    Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                    num = StringsKt.toIntOrNull(correctedTotal);
                }
                mutableListOf2.set(0, num);
            }
            this.formC1Repository.saveFormC1KesesuaianTabulationPartai(value2, mutableListOf, mutableListOf2);
            return;
        }
        Integer index = item.getIndex();
        if (index != null) {
            int intValue = index.intValue();
            FormC1Repository formC1Repository = this.formC1Repository;
            Boolean valueOf = Boolean.valueOf(!value.booleanValue());
            if (correctedTotal != null) {
                Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                num = StringsKt.toIntOrNull(correctedTotal);
            }
            formC1Repository.saveFormC1KesesuaianTabulationCandidateVote(value2, intValue, valueOf, num);
        }
    }

    public final void continueVerify() {
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
        formC1Repository.createEmptyFormC1TabulationPartai(str, value4, bitmap, value2.getElection().isLn(), value2.getElection().isLnPos(), true);
    }

    public final void refreshIdImage() {
        String value = this.idImage.getValue();
        if (value != null) {
            this.idImage.postValue(value);
        }
    }
}
