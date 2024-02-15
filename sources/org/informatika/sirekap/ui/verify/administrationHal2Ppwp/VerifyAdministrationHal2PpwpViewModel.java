package org.informatika.sirekap.ui.verify.administrationHal2Ppwp;

import android.content.Context;
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
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2;
import org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2;
import org.informatika.sirekap.model.FormC1KesesuaianTabulationCandidateVote;
import org.informatika.sirekap.model.FormC1TabulationCandidateVote;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.ui.verify.FormC1ListItem;
import org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel;

/* compiled from: VerifyAdministrationHal2PpwpViewModel.kt */
@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001RB#\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010D\u001a\u00020EJ\u0018\u0010F\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030G2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010H\u001a\u00020E2\u0006\u0010I\u001a\u00020=J&\u0010J\u001a\u00020E2\u0006\u0010K\u001a\u00020\u000b2\u0006\u0010L\u001a\u00020\u000b2\u0006\u0010M\u001a\u00020\u000b2\u0006\u0010N\u001a\u00020\u000bJ\u0018\u0010O\u001a\u00020E2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010P\u001a\u0004\u0018\u00010QR\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00120\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010R\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0010R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\r¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0010R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020&0\r¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0010R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020&0\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0010R!\u0010*\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010&\u0018\u00010+0\r¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0010R\u0019\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\r¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0010R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020&0\r¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0010R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020&0\r¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0010R\u0016\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R!\u00101\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u000102\u0018\u00010+0\r¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0010R\u0019\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0014\u00107\u001a\b\u0012\u0004\u0012\u0002080\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u00109\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020:\u0018\u00010\u00120\r¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0010R!\u0010<\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010=\u0018\u00010+0\r¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0010R\u0019\u0010?\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0010R\u001f\u0010A\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020=\u0018\u00010B0\r¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0010¨\u0006S"}, d2 = {"Lorg/informatika/sirekap/ui/verify/administrationHal2Ppwp/VerifyAdministrationHal2PpwpViewModel;", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel;", "context", "Landroid/content/Context;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/FormC1Repository;Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "electionPageId", "Landroidx/lifecycle/MutableLiveData;", "", "formC1AdministrationHal2PpwpComplete", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2PpwpComplete;", "getFormC1AdministrationHal2PpwpComplete", "()Landroidx/lifecycle/LiveData;", "formC1AdministrationHal2PpwpResource", "Lorg/informatika/sirekap/support/Resource;", "getFormC1AdministrationHal2PpwpResource", "formC1AdministrationHal2PpwpWithCandidates", "Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "Lorg/informatika/sirekap/ui/verify/administrationHal2Ppwp/VerifyAdministrationHal2PpwpViewModel$FormC1AdministrationHal2PpwpWithCandidates;", "formC1Error", "Lorg/informatika/sirekap/model/FormC1Error;", "getFormC1Error", "formC1Kesesuaian", "Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "getFormC1Kesesuaian", "formC1KesesuaianAdministrationHal2", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "getFormC1KesesuaianAdministrationHal2", "getElectionPageUseCase", "Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "getGetElectionPageUseCase", "()Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "idImage", "isCheckAllTrue", "", "isError", "isLoading", "isLoadingVerification", "isSesuaiPerItem", "", "isTableChecked", "isTablePerolehanSuaraEmpty", "isTableSuratEmpty", "jenisPemilihan", "kodeTps", "koreksiPerItem", "", "getKoreksiPerItem", "previewImagePath", "getPreviewImagePath", "()Landroidx/lifecycle/MutableLiveData;", "submittedFormC1", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel$VerifyFormC1Model;", "submittedFormResource", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "getSubmittedFormResource", "tablePerolehanSuara", "Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "getTablePerolehanSuara", "tablePerolehanSuaraTitle", "getTablePerolehanSuaraTitle", "tableSurat", "", "getTableSurat", "continueVerify", "", "getJsonString", "Ljava/util/HashMap;", "saveChecked", "item", "setup", "_idImage", "_electionPageId", "_jenisPemilihan", "_kodeTps", "submitVerification", "dialog", "Landroidx/appcompat/app/AlertDialog;", "FormC1AdministrationHal2PpwpWithCandidates", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyAdministrationHal2PpwpViewModel extends BaseVerifyViewModel {
    private final MutableLiveData<String> electionPageId;
    private final LiveData<FormC1AdministrationHal2PpwpComplete> formC1AdministrationHal2PpwpComplete;
    private final LiveData<Resource<FormC1AdministrationHal2PpwpComplete>> formC1AdministrationHal2PpwpResource;
    private final CombinedLiveData<FormC1AdministrationHal2PpwpWithCandidates> formC1AdministrationHal2PpwpWithCandidates;
    private final LiveData<FormC1Error> formC1Error;
    private final LiveData<FormC1Kesesuaian> formC1Kesesuaian;
    private final LiveData<FormC1KesesuaianAdministrationHal2> formC1KesesuaianAdministrationHal2;
    private final FormC1Repository formC1Repository;
    private final GetElectionPageUseCase getElectionPageUseCase;
    private final MutableLiveData<String> idImage;
    private final LiveData<Boolean> isCheckAllTrue;
    private final LiveData<Boolean> isError;
    private final LiveData<Boolean> isLoading;
    private final LiveData<Boolean> isLoadingVerification;
    private final LiveData<List<Boolean>> isSesuaiPerItem;
    private final LiveData<Boolean> isTableChecked;
    private final LiveData<Boolean> isTablePerolehanSuaraEmpty;
    private final LiveData<Boolean> isTableSuratEmpty;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<List<Integer>> koreksiPerItem;
    private final MutableLiveData<String> previewImagePath;
    private final MutableLiveData<BaseVerifyViewModel.VerifyFormC1Model> submittedFormC1;
    private final LiveData<Resource<FormC1AdministrationComplete>> submittedFormResource;
    private final LiveData<List<FormC1ListItem>> tablePerolehanSuara;
    private final LiveData<String> tablePerolehanSuaraTitle;
    private final LiveData<List<FormC1ListItem>> tableSurat;

    @Inject
    public VerifyAdministrationHal2PpwpViewModel(@ApplicationContext final Context context, FormC1Repository formC1Repository, DefaultElectionRepository electionRepository) {
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
        LiveData<Resource<FormC1AdministrationHal2PpwpComplete>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<FormC1AdministrationHal2PpwpComplete>>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$formC1AdministrationHal2PpwpResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1AdministrationHal2PpwpComplete>> invoke(String str) {
                FormC1Repository formC1Repository2;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                if (str != null) {
                    formC1Repository2 = VerifyAdministrationHal2PpwpViewModel.this.formC1Repository;
                    mutableLiveData2 = VerifyAdministrationHal2PpwpViewModel.this.jenisPemilihan;
                    T value = mutableLiveData2.getValue();
                    Intrinsics.checkNotNull(value);
                    String str2 = (String) value;
                    mutableLiveData3 = VerifyAdministrationHal2PpwpViewModel.this.kodeTps;
                    T value2 = mutableLiveData3.getValue();
                    Intrinsics.checkNotNull(value2);
                    return FormC1Repository.DefaultImpls.getFormC1AdministrationHal2Ppwp$default(formC1Repository2, str, str2, false, (String) value2, 4, null);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1AdministrationHal2PpwpResource = switchMap;
        LiveData<FormC1AdministrationHal2PpwpComplete> map = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationHal2PpwpComplete>, FormC1AdministrationHal2PpwpComplete>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$formC1AdministrationHal2PpwpComplete$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1AdministrationHal2PpwpComplete invoke(Resource<FormC1AdministrationHal2PpwpComplete> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.formC1AdministrationHal2PpwpComplete = map;
        this.formC1Error = Transformations.map(map, new Function1<FormC1AdministrationHal2PpwpComplete, FormC1Error>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$formC1Error$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1Error invoke(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete) {
                if (formC1AdministrationHal2PpwpComplete != null) {
                    return formC1AdministrationHal2PpwpComplete.getError();
                }
                return null;
            }
        });
        this.formC1KesesuaianAdministrationHal2 = Transformations.map(map, new Function1<FormC1AdministrationHal2PpwpComplete, FormC1KesesuaianAdministrationHal2>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$formC1KesesuaianAdministrationHal2$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1KesesuaianAdministrationHal2 invoke(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete) {
                if (formC1AdministrationHal2PpwpComplete != null) {
                    return formC1AdministrationHal2PpwpComplete.getKesesuaianAdministrationHal2();
                }
                return null;
            }
        });
        this.formC1Kesesuaian = Transformations.map(map, new Function1<FormC1AdministrationHal2PpwpComplete, FormC1Kesesuaian>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$formC1Kesesuaian$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1Kesesuaian invoke(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete) {
                if (formC1AdministrationHal2PpwpComplete != null) {
                    return formC1AdministrationHal2PpwpComplete.getKesesuaian();
                }
                return null;
            }
        });
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationHal2PpwpComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationHal2PpwpComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.isError = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationHal2PpwpComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$isError$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationHal2PpwpComplete> resource) {
                boolean z;
                if ((resource != null ? resource.getSuccess() : null) == ResourceStatus.ERROR) {
                    FormC1AdministrationHal2PpwpComplete payload = resource.getPayload();
                    if ((payload != null ? payload.getKesesuaianAdministrationHal2() : null) == null) {
                        z = true;
                        return Boolean.valueOf(z);
                    }
                }
                z = false;
                return Boolean.valueOf(z);
            }
        });
        LiveData<List<Boolean>> map2 = Transformations.map(map, new Function1<FormC1AdministrationHal2PpwpComplete, List<Boolean>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$isSesuaiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Boolean> invoke(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete) {
                if (formC1AdministrationHal2PpwpComplete != null) {
                    return formC1AdministrationHal2PpwpComplete.toIsSesuaiPerItem();
                }
                return null;
            }
        });
        this.isSesuaiPerItem = map2;
        this.koreksiPerItem = Transformations.map(map, new Function1<FormC1AdministrationHal2PpwpComplete, List<Integer>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$koreksiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Integer> invoke(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete) {
                if (formC1AdministrationHal2PpwpComplete != null) {
                    return formC1AdministrationHal2PpwpComplete.toKoreksiPerItem();
                }
                return null;
            }
        });
        this.isCheckAllTrue = Transformations.map(map2, new Function1<List<Boolean>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$isCheckAllTrue$1
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
        this.isTableChecked = Transformations.map(map2, new Function1<List<Boolean>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$isTableChecked$1
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
        CombinedLiveData<FormC1AdministrationHal2PpwpWithCandidates> combinedLiveData = new CombinedLiveData<>(new LiveData[]{map, getElectionPageUseCase.getCandidates()}, new Function1<List<? extends Object>, FormC1AdministrationHal2PpwpWithCandidates>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$formC1AdministrationHal2PpwpWithCandidates$1
            @Override // kotlin.jvm.functions.Function1
            public final VerifyAdministrationHal2PpwpViewModel.FormC1AdministrationHal2PpwpWithCandidates invoke(List<? extends Object> data) {
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
                    Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete");
                    return new VerifyAdministrationHal2PpwpViewModel.FormC1AdministrationHal2PpwpWithCandidates((FormC1AdministrationHal2PpwpComplete) obj3, list2);
                }
                return null;
            }
        });
        this.formC1AdministrationHal2PpwpWithCandidates = combinedLiveData;
        LiveData<List<FormC1ListItem>> map3 = Transformations.map(combinedLiveData, new Function1<FormC1AdministrationHal2PpwpWithCandidates, List<FormC1ListItem>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$tablePerolehanSuara$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1ListItem> invoke(VerifyAdministrationHal2PpwpViewModel.FormC1AdministrationHal2PpwpWithCandidates formC1AdministrationHal2PpwpWithCandidates) {
                FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete;
                List<FormC1TabulationCandidateVote> votes;
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
                boolean z5;
                if (formC1AdministrationHal2PpwpWithCandidates == null || (formC1AdministrationHal2PpwpComplete = formC1AdministrationHal2PpwpWithCandidates.getFormC1AdministrationHal2PpwpComplete()) == null || (votes = formC1AdministrationHal2PpwpComplete.getVotes()) == null) {
                    return null;
                }
                List<FormC1TabulationCandidateVote> list = votes;
                VerifyAdministrationHal2PpwpViewModel verifyAdministrationHal2PpwpViewModel = VerifyAdministrationHal2PpwpViewModel.this;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                int i = 0;
                for (Object obj6 : list) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    FormC1TabulationCandidateVote formC1TabulationCandidateVote = (FormC1TabulationCandidateVote) obj6;
                    ElectionPage electionPage = formC1AdministrationHal2PpwpWithCandidates.getFormC1AdministrationHal2PpwpComplete().getElectionPage();
                    Election value = verifyAdministrationHal2PpwpViewModel.getGetElectionPageUseCase().getElection().getValue();
                    List<FormC1KesesuaianTabulationCandidateVote> kesesuaianVotes = formC1AdministrationHal2PpwpWithCandidates.getFormC1AdministrationHal2PpwpComplete().getKesesuaianVotes();
                    if (kesesuaianVotes != null) {
                        Iterator<T> it = formC1AdministrationHal2PpwpWithCandidates.getCandidates().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it.next();
                            if (((Candidate) obj).getIndex() == formC1TabulationCandidateVote.getIndex()) {
                                z5 = true;
                                continue;
                            } else {
                                z5 = false;
                                continue;
                            }
                            if (z5) {
                                break;
                            }
                        }
                        Candidate candidate = (Candidate) obj;
                        int noUrutPencalonan = candidate != null ? candidate.getNoUrutPencalonan() : 0;
                        Iterator<T> it2 = formC1AdministrationHal2PpwpWithCandidates.getCandidates().iterator();
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
                        String str = (candidate2 == null || (r0 = candidate2.getNama()) == null) ? "-" : "-";
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
                        formC1ListItem = new FormC1ListItem("FORM_C1_ROW_CANDIDATE_", Integer.valueOf(noUrutPencalonan), str, null, null, vote, isChecked, null, null, vote2, Integer.valueOf(formC1TabulationCandidateVote.getIndex()), null, null, formC1KesesuaianTabulationCandidateVote3 != null ? formC1KesesuaianTabulationCandidateVote3.getVoteCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, value != null ? value.getPemilihan() : null, value != null ? Boolean.valueOf(value.isLn()) : null, value != null ? Boolean.valueOf(value.isLnPos()) : null, 268288, null);
                    } else {
                        formC1ListItem = null;
                    }
                    arrayList.add(formC1ListItem);
                    i = i2;
                }
                return arrayList;
            }
        });
        this.tablePerolehanSuara = map3;
        this.isTablePerolehanSuaraEmpty = Transformations.map(map3, new Function1<List<FormC1ListItem>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$isTablePerolehanSuaraEmpty$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<FormC1ListItem> list) {
                List<FormC1ListItem> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.tablePerolehanSuaraTitle = Transformations.map(getElectionPageUseCase.getElection(), new Function1<Election, String>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$tablePerolehanSuaraTitle$1
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
                jsonString = VerifyAdministrationHal2PpwpViewModel.this.getJsonString(context);
                Object obj = jsonString.get("iv");
                if (obj instanceof String) {
                    return (String) obj;
                }
                return null;
            }
        });
        LiveData<List<FormC1ListItem>> map4 = Transformations.map(map, new Function1<FormC1AdministrationHal2PpwpComplete, List<FormC1ListItem>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$tableSurat$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1ListItem> invoke(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete) {
                HashMap jsonString;
                Integer suratSuaraSah;
                Integer suratSuaraTidakSah;
                Integer totalSuratSuaraCorrected;
                if (formC1AdministrationHal2PpwpComplete == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                FormC1AdministrationHal2 form = formC1AdministrationHal2PpwpComplete.getForm();
                FormC1KesesuaianAdministrationHal2 kesesuaianAdministrationHal2 = formC1AdministrationHal2PpwpComplete.getKesesuaianAdministrationHal2();
                ElectionPage electionPage = formC1AdministrationHal2PpwpComplete.getElectionPage();
                Election value = VerifyAdministrationHal2PpwpViewModel.this.getGetElectionPageUseCase().getElection().getValue();
                jsonString = VerifyAdministrationHal2PpwpViewModel.this.getJsonString(context);
                if ((form != null ? form.getSuratSuaraSah() : null) != null) {
                    Object obj = jsonString.get("va");
                    String str = obj instanceof String ? (String) obj : null;
                    arrayList.add(new FormC1ListItem(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_SAH, null, str == null ? "" : str, null, null, form.getSuratSuaraSah().intValue(), kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.isCheckedRowSuratSuaraSah() : false, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraSah() : null, null, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraSahCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, value != null ? value.getPemilihan() : null, value != null ? Boolean.valueOf(value.isLn()) : null, value != null ? Boolean.valueOf(value.isLnPos()) : null, 269312, null));
                }
                if ((form != null ? form.getSuratSuaraTidakSah() : null) != null) {
                    Object obj2 = jsonString.get("vb");
                    String str2 = obj2 instanceof String ? (String) obj2 : null;
                    arrayList.add(new FormC1ListItem(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH, null, str2 == null ? "" : str2, null, null, form.getSuratSuaraTidakSah().intValue(), kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.isCheckedRowSuratSuaraTidakSah() : false, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraTidakSah() : null, null, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraTidakSahCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, value != null ? value.getPemilihan() : null, value != null ? Boolean.valueOf(value.isLn()) : null, value != null ? Boolean.valueOf(value.isLnPos()) : null, 269312, null));
                }
                if ((form != null ? form.getTotalSuratSuara() : null) != null) {
                    Object obj3 = jsonString.get("vc");
                    String str3 = obj3 instanceof String ? (String) obj3 : null;
                    arrayList.add(new FormC1ListItem(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_SURAT_SUARA, null, str3 == null ? "" : str3, null, null, form.getTotalSuratSuara().intValue(), kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.isCheckedRowTotalSuratSuara() : false, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getTotalSuratSuara() : null, null, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getTotalSuratSuaraCorrected() : null, Boolean.valueOf(form.isTotalSuratSuaraValid(((kesesuaianAdministrationHal2 == null || (suratSuaraSah = kesesuaianAdministrationHal2.getSuratSuaraSahCorrected()) == null) && (suratSuaraSah = form.getSuratSuaraSah()) == null) ? 0 : suratSuaraSah.intValue(), ((kesesuaianAdministrationHal2 == null || (suratSuaraTidakSah = kesesuaianAdministrationHal2.getSuratSuaraTidakSahCorrected()) == null) && (suratSuaraTidakSah = form.getSuratSuaraTidakSah()) == null) ? 0 : suratSuaraTidakSah.intValue(), (kesesuaianAdministrationHal2 == null || (totalSuratSuaraCorrected = kesesuaianAdministrationHal2.getTotalSuratSuaraCorrected()) == null) ? form.getTotalSuratSuara().intValue() : totalSuratSuaraCorrected.intValue())), context.getString(FormC1AdministrationHal2.Companion.getTotalSuratSuaraInvalidMessage()), electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, value != null ? value.getPemilihan() : null, value != null ? Boolean.valueOf(value.isLn()) : null, value != null ? Boolean.valueOf(value.isLnPos()) : null, 269312, null));
                }
                return arrayList;
            }
        });
        this.tableSurat = map4;
        this.isTableSuratEmpty = Transformations.map(map4, new Function1<List<FormC1ListItem>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$isTableSuratEmpty$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<FormC1ListItem> list) {
                List<FormC1ListItem> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        MutableLiveData<BaseVerifyViewModel.VerifyFormC1Model> mutableLiveData2 = new MutableLiveData<>(null);
        this.submittedFormC1 = mutableLiveData2;
        LiveData<Resource<FormC1AdministrationComplete>> switchMap2 = Transformations.switchMap(mutableLiveData2, new Function1<BaseVerifyViewModel.VerifyFormC1Model, LiveData<Resource<FormC1AdministrationComplete>>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$submittedFormResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1AdministrationComplete>> invoke(BaseVerifyViewModel.VerifyFormC1Model verifyFormC1Model) {
                MutableLiveData mutableLiveData3;
                FormC1Repository formC1Repository2;
                if (verifyFormC1Model != null) {
                    mutableLiveData3 = VerifyAdministrationHal2PpwpViewModel.this.electionPageId;
                    String str = (String) mutableLiveData3.getValue();
                    if (str != null) {
                        formC1Repository2 = VerifyAdministrationHal2PpwpViewModel.this.formC1Repository;
                        String jenisPemilihan = verifyFormC1Model.getJenisPemilihan();
                        String deviceId = verifyFormC1Model.getDeviceId();
                        String idImage = verifyFormC1Model.getIdImage();
                        boolean isSesuai = verifyFormC1Model.isSesuai();
                        List<Boolean> isSesuaiPerItem = verifyFormC1Model.isSesuaiPerItem();
                        String komentar = verifyFormC1Model.getKomentar();
                        if (komentar == null) {
                            komentar = "";
                        }
                        return formC1Repository2.verifyFormC1(jenisPemilihan, deviceId, 3, str, idImage, isSesuai, isSesuaiPerItem, komentar, verifyFormC1Model.getKoreksiPerItem(), verifyFormC1Model.getKodeTps());
                    }
                    return null;
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.submittedFormResource = switchMap2;
        this.isLoadingVerification = Transformations.map(switchMap2, new Function1<Resource<FormC1AdministrationComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel$isLoadingVerification$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
    }

    public final GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.getElectionPageUseCase;
    }

    public final LiveData<Resource<FormC1AdministrationHal2PpwpComplete>> getFormC1AdministrationHal2PpwpResource() {
        return this.formC1AdministrationHal2PpwpResource;
    }

    public final LiveData<FormC1AdministrationHal2PpwpComplete> getFormC1AdministrationHal2PpwpComplete() {
        return this.formC1AdministrationHal2PpwpComplete;
    }

    public final LiveData<FormC1Error> getFormC1Error() {
        return this.formC1Error;
    }

    public final LiveData<FormC1KesesuaianAdministrationHal2> getFormC1KesesuaianAdministrationHal2() {
        return this.formC1KesesuaianAdministrationHal2;
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

    /* compiled from: VerifyAdministrationHal2PpwpViewModel.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/ui/verify/administrationHal2Ppwp/VerifyAdministrationHal2PpwpViewModel$FormC1AdministrationHal2PpwpWithCandidates;", "", "formC1AdministrationHal2PpwpComplete", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2PpwpComplete;", "candidates", "", "Lorg/informatika/sirekap/model/Candidate;", "(Lorg/informatika/sirekap/model/FormC1AdministrationHal2PpwpComplete;Ljava/util/List;)V", "getCandidates", "()Ljava/util/List;", "getFormC1AdministrationHal2PpwpComplete", "()Lorg/informatika/sirekap/model/FormC1AdministrationHal2PpwpComplete;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class FormC1AdministrationHal2PpwpWithCandidates {
        private final List<Candidate> candidates;
        private final FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FormC1AdministrationHal2PpwpWithCandidates copy$default(FormC1AdministrationHal2PpwpWithCandidates formC1AdministrationHal2PpwpWithCandidates, FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                formC1AdministrationHal2PpwpComplete = formC1AdministrationHal2PpwpWithCandidates.formC1AdministrationHal2PpwpComplete;
            }
            if ((i & 2) != 0) {
                list = formC1AdministrationHal2PpwpWithCandidates.candidates;
            }
            return formC1AdministrationHal2PpwpWithCandidates.copy(formC1AdministrationHal2PpwpComplete, list);
        }

        public final FormC1AdministrationHal2PpwpComplete component1() {
            return this.formC1AdministrationHal2PpwpComplete;
        }

        public final List<Candidate> component2() {
            return this.candidates;
        }

        public final FormC1AdministrationHal2PpwpWithCandidates copy(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete, List<Candidate> candidates) {
            Intrinsics.checkNotNullParameter(formC1AdministrationHal2PpwpComplete, "formC1AdministrationHal2PpwpComplete");
            Intrinsics.checkNotNullParameter(candidates, "candidates");
            return new FormC1AdministrationHal2PpwpWithCandidates(formC1AdministrationHal2PpwpComplete, candidates);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof FormC1AdministrationHal2PpwpWithCandidates) {
                FormC1AdministrationHal2PpwpWithCandidates formC1AdministrationHal2PpwpWithCandidates = (FormC1AdministrationHal2PpwpWithCandidates) obj;
                return Intrinsics.areEqual(this.formC1AdministrationHal2PpwpComplete, formC1AdministrationHal2PpwpWithCandidates.formC1AdministrationHal2PpwpComplete) && Intrinsics.areEqual(this.candidates, formC1AdministrationHal2PpwpWithCandidates.candidates);
            }
            return false;
        }

        public int hashCode() {
            return (this.formC1AdministrationHal2PpwpComplete.hashCode() * 31) + this.candidates.hashCode();
        }

        public String toString() {
            FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete = this.formC1AdministrationHal2PpwpComplete;
            return "FormC1AdministrationHal2PpwpWithCandidates(formC1AdministrationHal2PpwpComplete=" + formC1AdministrationHal2PpwpComplete + ", candidates=" + this.candidates + ")";
        }

        public FormC1AdministrationHal2PpwpWithCandidates(FormC1AdministrationHal2PpwpComplete formC1AdministrationHal2PpwpComplete, List<Candidate> candidates) {
            Intrinsics.checkNotNullParameter(formC1AdministrationHal2PpwpComplete, "formC1AdministrationHal2PpwpComplete");
            Intrinsics.checkNotNullParameter(candidates, "candidates");
            this.formC1AdministrationHal2PpwpComplete = formC1AdministrationHal2PpwpComplete;
            this.candidates = candidates;
        }

        public final FormC1AdministrationHal2PpwpComplete getFormC1AdministrationHal2PpwpComplete() {
            return this.formC1AdministrationHal2PpwpComplete;
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

    public final HashMap<?, ?> getJsonString(Context context) {
        int i;
        Election value = this.getElectionPageUseCase.getElection().getValue();
        boolean z = true;
        if (value != null && value.isLnPos()) {
            i = R.raw.ppwp_lnpos_strings;
        } else {
            if (value == null || !value.isLn()) {
                z = false;
            }
            if (z) {
                i = R.raw.ppwp_ln_strings;
            } else {
                i = R.raw.ppwp_dn_strings;
            }
        }
        Gson gson = new Gson();
        InputStream openRawResource = context.getResources().openRawResource(i);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "context.resources.openRawResource(jsonStringsStr)");
        InputStreamReader inputStreamReader = new InputStreamReader(openRawResource, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String readText = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            Object fromJson = gson.fromJson(readText, (Class<Object>) HashMap.class);
            Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(\n       …ap::class.java,\n        )");
            return (HashMap) fromJson;
        } finally {
        }
    }

    public final LiveData<List<FormC1ListItem>> getTableSurat() {
        return this.tableSurat;
    }

    public final LiveData<Boolean> isTableSuratEmpty() {
        return this.isTableSuratEmpty;
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
        Integer num2;
        Integer num3;
        Intrinsics.checkNotNullParameter(item, "item");
        String value2 = this.idImage.getValue();
        if (value2 == null || (value = isWrongTotal().getValue()) == null) {
            return;
        }
        String correctedTotal = getCorrectedTotal().getValue();
        FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2 = this.formC1KesesuaianAdministrationHal2.getValue();
        if (formC1KesesuaianAdministrationHal2 != null) {
            Integer num4 = null;
            if (!Intrinsics.areEqual(item.getId(), "FORM_C1_ROW_CANDIDATE_")) {
                Intrinsics.checkNotNullExpressionValue(formC1KesesuaianAdministrationHal2, "formC1KesesuaianAdministrationHal2");
                String id2 = item.getId();
                int hashCode = id2.hashCode();
                if (hashCode != -1225829493) {
                    if (hashCode != 952919003) {
                        if (hashCode == 1693880021 && id2.equals(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH)) {
                            Boolean valueOf = Boolean.valueOf(!value.booleanValue());
                            if (correctedTotal != null) {
                                Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                                num3 = StringsKt.toIntOrNull(correctedTotal);
                            } else {
                                num3 = null;
                            }
                            formC1KesesuaianAdministrationHal2 = FormC1KesesuaianAdministrationHal2.copy$default(formC1KesesuaianAdministrationHal2, null, null, valueOf, null, null, num3, null, 91, null);
                        }
                    } else if (id2.equals(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_SAH)) {
                        Boolean valueOf2 = Boolean.valueOf(!value.booleanValue());
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num2 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num2 = null;
                        }
                        formC1KesesuaianAdministrationHal2 = FormC1KesesuaianAdministrationHal2.copy$default(formC1KesesuaianAdministrationHal2, null, valueOf2, null, null, num2, null, null, 109, null);
                    }
                } else if (id2.equals(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_SURAT_SUARA)) {
                    Boolean valueOf3 = Boolean.valueOf(!value.booleanValue());
                    if (correctedTotal != null) {
                        Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                        num = StringsKt.toIntOrNull(correctedTotal);
                    } else {
                        num = null;
                    }
                    formC1KesesuaianAdministrationHal2 = FormC1KesesuaianAdministrationHal2.copy$default(formC1KesesuaianAdministrationHal2, null, null, null, valueOf3, null, null, num, 55, null);
                }
                this.formC1Repository.saveFormC1KesesuaianAdministrationHal2New(value2, formC1KesesuaianAdministrationHal2);
                return;
            }
            Integer index = item.getIndex();
            if (index != null) {
                int intValue = index.intValue();
                FormC1Repository formC1Repository = this.formC1Repository;
                Boolean valueOf4 = Boolean.valueOf(!value.booleanValue());
                if (correctedTotal != null) {
                    Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                    num4 = StringsKt.toIntOrNull(correctedTotal);
                }
                formC1Repository.saveFormC1KesesuaianTabulationCandidateVote(value2, intValue, valueOf4, num4);
            }
        }
    }

    public final void continueVerify() {
        FormC1Repository formC1Repository = this.formC1Repository;
        String value = this.idImage.getValue();
        Intrinsics.checkNotNull(value);
        String value2 = this.jenisPemilihan.getValue();
        Intrinsics.checkNotNull(value2);
        formC1Repository.createEmptyFormC1AdministrationHal2Ppwp(value, value2);
    }
}
