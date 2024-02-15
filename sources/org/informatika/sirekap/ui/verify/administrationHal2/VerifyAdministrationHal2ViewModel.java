package org.informatika.sirekap.ui.verify.administrationHal2;

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
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1AdministrationHal2;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.ui.verify.FormC1ListItem;

/* compiled from: VerifyAdministrationHal2ViewModel.kt */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B#\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010;\u001a\u00020<J2\u0010=\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010>2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010?\u001a\u00020\"2\u0006\u0010@\u001a\u00020\"H\u0002J\u0006\u0010A\u001a\u00020<J\u000e\u0010B\u001a\u00020<2\u0006\u0010C\u001a\u000209J&\u0010D\u001a\u00020<2\u0006\u0010E\u001a\u00020\u000b2\u0006\u0010F\u001a\u00020\u000b2\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010H\u001a\u00020\u000bJ\u0018\u0010I\u001a\u00020<2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010J\u001a\u0004\u0018\u00010KR\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00120\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0010R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0010R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\r¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0010R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0010R!\u0010&\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\"\u0018\u00010'0\r¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0010R\u0019\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\r¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0010R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\"0\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0010R\u0016\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010,\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010-\u0018\u00010'0\r¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0010R\u0019\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0014\u00102\u001a\b\u0012\u0004\u0012\u0002030\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u00104\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000205\u0018\u00010\u00120\r¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0010R\u001f\u00107\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000209\u0018\u0001080\r¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0010¨\u0006L"}, d2 = {"Lorg/informatika/sirekap/ui/verify/administrationHal2/VerifyAdministrationHal2ViewModel;", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel;", "context", "Landroid/content/Context;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/FormC1Repository;Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "electionPageId", "Landroidx/lifecycle/MutableLiveData;", "", "formC1AdministrationHal2Complete", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "getFormC1AdministrationHal2Complete", "()Landroidx/lifecycle/LiveData;", "formC1AdministrationHal2Resource", "Lorg/informatika/sirekap/support/Resource;", "getFormC1AdministrationHal2Resource", "formC1Error", "Lorg/informatika/sirekap/model/FormC1Error;", "getFormC1Error", "formC1Kesesuaian", "Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "getFormC1Kesesuaian", "formC1KesesuaianAdministrationHal2", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "getElectionPageUseCase", "Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "getGetElectionPageUseCase", "()Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "idImage", "isCheckAllTrue", "", "isError", "isLoading", "isLoadingVerification", "isSesuaiPerItem", "", "isTableChecked", "isTableSuratEmpty", "jenisPemilihan", "kodeTps", "koreksiPerItem", "", "getKoreksiPerItem", "previewImagePath", "getPreviewImagePath", "()Landroidx/lifecycle/MutableLiveData;", "submittedFormC1", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel$VerifyFormC1Model;", "submittedFormResource", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "getSubmittedFormResource", "tableSurat", "", "Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "getTableSurat", "continueVerify", "", "getJsonString", "Ljava/util/HashMap;", "isLnPos", "isLn", "refreshIdImage", "saveChecked", "item", "setup", "_idImage", "_electionPageId", "_jenisPemilihan", "_kodeTps", "submitVerification", "dialog", "Landroidx/appcompat/app/AlertDialog;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyAdministrationHal2ViewModel extends BaseVerifyViewModel {
    private final MutableLiveData<String> electionPageId;
    private final LiveData<FormC1AdministrationHal2Complete> formC1AdministrationHal2Complete;
    private final LiveData<Resource<FormC1AdministrationHal2Complete>> formC1AdministrationHal2Resource;
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
    private final LiveData<Boolean> isTableSuratEmpty;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<List<Integer>> koreksiPerItem;
    private final MutableLiveData<String> previewImagePath;
    private final MutableLiveData<BaseVerifyViewModel.VerifyFormC1Model> submittedFormC1;
    private final LiveData<Resource<FormC1AdministrationComplete>> submittedFormResource;
    private final LiveData<List<FormC1ListItem>> tableSurat;

    @Inject
    public VerifyAdministrationHal2ViewModel(@ApplicationContext final Context context, FormC1Repository formC1Repository, DefaultElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(formC1Repository, "formC1Repository");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.formC1Repository = formC1Repository;
        this.getElectionPageUseCase = new GetElectionPageUseCase(electionRepository);
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.idImage = mutableLiveData;
        this.electionPageId = new MutableLiveData<>(null);
        this.jenisPemilihan = new MutableLiveData<>(null);
        this.kodeTps = new MutableLiveData<>(null);
        LiveData<Resource<FormC1AdministrationHal2Complete>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<FormC1AdministrationHal2Complete>>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$formC1AdministrationHal2Resource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1AdministrationHal2Complete>> invoke(String str) {
                FormC1Repository formC1Repository2;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                if (str != null) {
                    formC1Repository2 = VerifyAdministrationHal2ViewModel.this.formC1Repository;
                    mutableLiveData2 = VerifyAdministrationHal2ViewModel.this.jenisPemilihan;
                    T value = mutableLiveData2.getValue();
                    Intrinsics.checkNotNull(value);
                    String str2 = (String) value;
                    mutableLiveData3 = VerifyAdministrationHal2ViewModel.this.kodeTps;
                    T value2 = mutableLiveData3.getValue();
                    Intrinsics.checkNotNull(value2);
                    return FormC1Repository.DefaultImpls.getFormC1AdministrationHal2$default(formC1Repository2, str, str2, false, (String) value2, 4, null);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1AdministrationHal2Resource = switchMap;
        LiveData<FormC1AdministrationHal2Complete> map = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationHal2Complete>, FormC1AdministrationHal2Complete>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$formC1AdministrationHal2Complete$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1AdministrationHal2Complete invoke(Resource<FormC1AdministrationHal2Complete> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.formC1AdministrationHal2Complete = map;
        this.formC1Error = Transformations.map(map, new Function1<FormC1AdministrationHal2Complete, FormC1Error>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$formC1Error$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1Error invoke(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
                if (formC1AdministrationHal2Complete != null) {
                    return formC1AdministrationHal2Complete.getError();
                }
                return null;
            }
        });
        this.formC1Kesesuaian = Transformations.map(map, new Function1<FormC1AdministrationHal2Complete, FormC1Kesesuaian>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$formC1Kesesuaian$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1Kesesuaian invoke(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
                if (formC1AdministrationHal2Complete != null) {
                    return formC1AdministrationHal2Complete.getKesesuaian();
                }
                return null;
            }
        });
        LiveData<FormC1KesesuaianAdministrationHal2> map2 = Transformations.map(map, new Function1<FormC1AdministrationHal2Complete, FormC1KesesuaianAdministrationHal2>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$formC1KesesuaianAdministrationHal2$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1KesesuaianAdministrationHal2 invoke(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
                if (formC1AdministrationHal2Complete != null) {
                    return formC1AdministrationHal2Complete.getKesesuaianAdministrationHal2();
                }
                return null;
            }
        });
        this.formC1KesesuaianAdministrationHal2 = map2;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationHal2Complete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationHal2Complete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.isError = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationHal2Complete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$isError$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationHal2Complete> resource) {
                boolean z;
                if ((resource != null ? resource.getSuccess() : null) == ResourceStatus.ERROR) {
                    FormC1AdministrationHal2Complete payload = resource.getPayload();
                    if ((payload != null ? payload.getKesesuaianAdministrationHal2() : null) == null) {
                        z = true;
                        return Boolean.valueOf(z);
                    }
                }
                z = false;
                return Boolean.valueOf(z);
            }
        });
        LiveData<List<Boolean>> map3 = Transformations.map(map2, new Function1<FormC1KesesuaianAdministrationHal2, List<Boolean>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$isSesuaiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Boolean> invoke(FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
                if (formC1KesesuaianAdministrationHal2 != null) {
                    return formC1KesesuaianAdministrationHal2.toIsSesuaiPerItem();
                }
                return null;
            }
        });
        this.isSesuaiPerItem = map3;
        this.koreksiPerItem = Transformations.map(map2, new Function1<FormC1KesesuaianAdministrationHal2, List<Integer>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$koreksiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Integer> invoke(FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
                if (formC1KesesuaianAdministrationHal2 != null) {
                    return formC1KesesuaianAdministrationHal2.toKoreksiPerItem();
                }
                return null;
            }
        });
        this.isCheckAllTrue = Transformations.map(map3, new Function1<List<Boolean>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$isCheckAllTrue$1
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
        this.isTableChecked = Transformations.map(map3, new Function1<List<Boolean>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$isTableChecked$1
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
        LiveData<List<FormC1ListItem>> map4 = Transformations.map(map, new Function1<FormC1AdministrationHal2Complete, List<FormC1ListItem>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$tableSurat$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1ListItem> invoke(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
                HashMap jsonString;
                Integer suratSuaraSah;
                Integer suratSuaraTidakSah;
                Integer totalSuratSuaraCorrected;
                if (formC1AdministrationHal2Complete == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                FormC1AdministrationHal2 form = formC1AdministrationHal2Complete.getForm();
                FormC1KesesuaianAdministrationHal2 kesesuaianAdministrationHal2 = formC1AdministrationHal2Complete.getKesesuaianAdministrationHal2();
                ElectionPage electionPage = formC1AdministrationHal2Complete.getElectionPage();
                Election value = VerifyAdministrationHal2ViewModel.this.getGetElectionPageUseCase().getElection().getValue();
                VerifyAdministrationHal2ViewModel verifyAdministrationHal2ViewModel = VerifyAdministrationHal2ViewModel.this;
                Context context2 = context;
                String jenisPemilihan = formC1AdministrationHal2Complete.getElectionPage().getJenisPemilihan();
                FormC1AdministrationHal2 form2 = formC1AdministrationHal2Complete.getForm();
                boolean isLnPos = form2 != null ? form2.isLnPos() : false;
                FormC1AdministrationHal2 form3 = formC1AdministrationHal2Complete.getForm();
                jsonString = verifyAdministrationHal2ViewModel.getJsonString(context2, jenisPemilihan, isLnPos, form3 != null ? form3.isLn() : false);
                if ((form != null ? form.getSuratSuaraSah() : null) != null) {
                    Object obj = jsonString != null ? jsonString.get("va") : null;
                    String str = obj instanceof String ? (String) obj : null;
                    arrayList.add(new FormC1ListItem(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_SAH, null, str == null ? "" : str, null, null, form.getSuratSuaraSah().intValue(), kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.isCheckedRowSuratSuaraSah() : false, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraSah() : null, null, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraSahCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, value != null ? value.getPemilihan() : null, value != null ? Boolean.valueOf(value.isLn()) : null, value != null ? Boolean.valueOf(value.isLnPos()) : null, 269312, null));
                }
                if ((form != null ? form.getSuratSuaraTidakSah() : null) != null) {
                    Object obj2 = jsonString != null ? jsonString.get("vb") : null;
                    String str2 = obj2 instanceof String ? (String) obj2 : null;
                    arrayList.add(new FormC1ListItem(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH, null, str2 == null ? "" : str2, null, null, form.getSuratSuaraTidakSah().intValue(), kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.isCheckedRowSuratSuaraTidakSah() : false, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraTidakSah() : null, null, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getSuratSuaraTidakSahCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, value != null ? value.getPemilihan() : null, value != null ? Boolean.valueOf(value.isLn()) : null, value != null ? Boolean.valueOf(value.isLnPos()) : null, 269312, null));
                }
                if ((form != null ? form.getTotalSuratSuara() : null) != null) {
                    Object obj3 = jsonString != null ? jsonString.get("vc") : null;
                    String str3 = obj3 instanceof String ? (String) obj3 : null;
                    arrayList.add(new FormC1ListItem(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_SURAT_SUARA, null, str3 == null ? "" : str3, null, null, form.getTotalSuratSuara().intValue(), kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.isCheckedRowTotalSuratSuara() : false, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getTotalSuratSuara() : null, null, null, null, kesesuaianAdministrationHal2 != null ? kesesuaianAdministrationHal2.getTotalSuratSuaraCorrected() : null, Boolean.valueOf(form.isTotalSuratSuaraValid(((kesesuaianAdministrationHal2 == null || (suratSuaraSah = kesesuaianAdministrationHal2.getSuratSuaraSahCorrected()) == null) && (suratSuaraSah = form.getSuratSuaraSah()) == null) ? 0 : suratSuaraSah.intValue(), ((kesesuaianAdministrationHal2 == null || (suratSuaraTidakSah = kesesuaianAdministrationHal2.getSuratSuaraTidakSahCorrected()) == null) && (suratSuaraTidakSah = form.getSuratSuaraTidakSah()) == null) ? 0 : suratSuaraTidakSah.intValue(), (kesesuaianAdministrationHal2 == null || (totalSuratSuaraCorrected = kesesuaianAdministrationHal2.getTotalSuratSuaraCorrected()) == null) ? form.getTotalSuratSuara().intValue() : totalSuratSuaraCorrected.intValue())), context.getString(FormC1AdministrationHal2.Companion.getTotalSuratSuaraInvalidMessage()), electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, value != null ? value.getPemilihan() : null, value != null ? Boolean.valueOf(value.isLn()) : null, value != null ? Boolean.valueOf(value.isLnPos()) : null, 269312, null));
                }
                return arrayList;
            }
        });
        this.tableSurat = map4;
        this.isTableSuratEmpty = Transformations.map(map4, new Function1<List<FormC1ListItem>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$isTableSuratEmpty$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<FormC1ListItem> list) {
                List<FormC1ListItem> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        MutableLiveData<BaseVerifyViewModel.VerifyFormC1Model> mutableLiveData2 = new MutableLiveData<>(null);
        this.submittedFormC1 = mutableLiveData2;
        LiveData<Resource<FormC1AdministrationComplete>> switchMap2 = Transformations.switchMap(mutableLiveData2, new Function1<BaseVerifyViewModel.VerifyFormC1Model, LiveData<Resource<FormC1AdministrationComplete>>>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$submittedFormResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1AdministrationComplete>> invoke(BaseVerifyViewModel.VerifyFormC1Model verifyFormC1Model) {
                MutableLiveData mutableLiveData3;
                FormC1Repository formC1Repository2;
                if (verifyFormC1Model != null) {
                    mutableLiveData3 = VerifyAdministrationHal2ViewModel.this.electionPageId;
                    String str = (String) mutableLiveData3.getValue();
                    if (str != null) {
                        formC1Repository2 = VerifyAdministrationHal2ViewModel.this.formC1Repository;
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
        this.isLoadingVerification = Transformations.map(switchMap2, new Function1<Resource<FormC1AdministrationComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel$isLoadingVerification$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
    }

    public final GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.getElectionPageUseCase;
    }

    public final LiveData<Resource<FormC1AdministrationHal2Complete>> getFormC1AdministrationHal2Resource() {
        return this.formC1AdministrationHal2Resource;
    }

    public final LiveData<FormC1AdministrationHal2Complete> getFormC1AdministrationHal2Complete() {
        return this.formC1AdministrationHal2Complete;
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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final HashMap<?, ?> getJsonString(Context context, String str, boolean z, boolean z2) {
        Integer num;
        switch (str.hashCode()) {
            case -992700931:
                if (str.equals(Election.ELECTION_PEMILIHAN_DPRD_KABKO)) {
                    num = Integer.valueOf(R.raw.pdprdk_strings);
                    break;
                }
                num = null;
                break;
            case -992700926:
                if (str.equals(Election.ELECTION_PEMILIHAN_DPRD_PROVINSI)) {
                    num = Integer.valueOf(R.raw.pdprdp_strings);
                    break;
                }
                num = null;
                break;
            case 3436264:
                if (str.equals(Election.ELECTION_PEMILIHAN_DPD)) {
                    num = Integer.valueOf(R.raw.pdpd_strings);
                    break;
                }
                num = null;
                break;
            case 3436278:
                if (str.equals(Election.ELECTION_PEMILIHAN_DPR)) {
                    num = Integer.valueOf(z ? R.raw.pdpr_lnpos_strings : z2 ? R.raw.pdpr_ln_strings : R.raw.pdpr_dn_strings);
                    break;
                }
                num = null;
                break;
            case 3448025:
                if (str.equals(Election.ELECTION_PEMILIHAN_PRESIDEN)) {
                    num = Integer.valueOf(R.raw.ppwp_dn_strings);
                    break;
                }
                num = null;
                break;
            default:
                num = null;
                break;
        }
        if (num == null) {
            return null;
        }
        int intValue = num.intValue();
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
        Intrinsics.checkNotNullExpressionValue(isCheckAllTrue, "isCheckAllTrue");
        boolean booleanValue = isCheckAllTrue.booleanValue();
        Intrinsics.checkNotNullExpressionValue(isSesuaiPerItem, "isSesuaiPerItem");
        Intrinsics.checkNotNullExpressionValue(koreksiPerItem, "koreksiPerItem");
        String deviceId = SecurityFacade.INSTANCE.getDeviceId(context);
        String value2 = this.jenisPemilihan.getValue();
        Intrinsics.checkNotNull(value2);
        String str = value2;
        String value3 = this.kodeTps.getValue();
        Intrinsics.checkNotNull(value3);
        mutableLiveData.postValue(new BaseVerifyViewModel.VerifyFormC1Model(value, booleanValue, isSesuaiPerItem, null, koreksiPerItem, deviceId, str, value3));
    }

    public final void saveChecked(FormC1ListItem item) {
        List<Boolean> isSesuaiPerItem;
        List<Integer> koreksiPerItem;
        Boolean value;
        Intrinsics.checkNotNullParameter(item, "item");
        String value2 = this.idImage.getValue();
        if (value2 == null || (isSesuaiPerItem = this.isSesuaiPerItem.getValue()) == null || (koreksiPerItem = this.koreksiPerItem.getValue()) == null || (value = isWrongTotal().getValue()) == null) {
            return;
        }
        String correctedTotal = getCorrectedTotal().getValue();
        Intrinsics.checkNotNullExpressionValue(isSesuaiPerItem, "isSesuaiPerItem");
        List<Boolean> mutableList = CollectionsKt.toMutableList((Collection) isSesuaiPerItem);
        Intrinsics.checkNotNullExpressionValue(koreksiPerItem, "koreksiPerItem");
        List<Integer> mutableList2 = CollectionsKt.toMutableList((Collection) koreksiPerItem);
        String id2 = item.getId();
        int hashCode = id2.hashCode();
        Integer num = null;
        if (hashCode != -1225829493) {
            if (hashCode != 952919003) {
                if (hashCode == 1693880021 && id2.equals(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH)) {
                    mutableList.set(1, Boolean.valueOf(!value.booleanValue()));
                    if (correctedTotal != null) {
                        Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                        num = StringsKt.toIntOrNull(correctedTotal);
                    }
                    mutableList2.set(1, num);
                }
            } else if (id2.equals(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_SAH)) {
                mutableList.set(0, Boolean.valueOf(!value.booleanValue()));
                if (correctedTotal != null) {
                    Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                    num = StringsKt.toIntOrNull(correctedTotal);
                }
                mutableList2.set(0, num);
            }
        } else if (id2.equals(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_SURAT_SUARA)) {
            mutableList.set(2, Boolean.valueOf(!value.booleanValue()));
            if (correctedTotal != null) {
                Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                num = StringsKt.toIntOrNull(correctedTotal);
            }
            mutableList2.set(2, num);
        }
        this.formC1Repository.saveFormC1KesesuaianAdministrationHal2(value2, mutableList, mutableList2);
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
        formC1Repository.createEmptyFormC1AdministrationHal2(str, value4, bitmap, value2.getElection().isLn(), value2.getElection().isLnPos(), true);
    }

    public final void refreshIdImage() {
        String value = this.idImage.getValue();
        if (value != null) {
            this.idImage.postValue(value);
        }
    }
}
