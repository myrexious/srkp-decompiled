package org.informatika.sirekap.ui.verify.administration;

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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.FormC1Administration;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1KesesuaianAdministration;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.security.SecurityFacade;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.ui.verify.FormC1ListItem;

/* compiled from: VerifyAdministrationViewModel.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B#\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010?\u001a\u00020@J2\u0010A\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010B2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u000b2\u0006\u0010C\u001a\u00020\"2\u0006\u0010D\u001a\u00020\"H\u0002J\u0006\u0010E\u001a\u00020@J\u000e\u0010F\u001a\u00020@2\u0006\u0010G\u001a\u000206J&\u0010H\u001a\u00020@2\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u000b2\u0006\u0010L\u001a\u00020\u000bJ\u001a\u0010M\u001a\u00020@2\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010N\u001a\u0004\u0018\u00010OR\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00120\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0010R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0010R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\r¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0010R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0010R!\u0010&\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\"\u0018\u00010'0\r¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0010R\u0019\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\r¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0010R\u0016\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010+\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010,\u0018\u00010'0\r¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0010R\u0019\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0014\u00101\u001a\b\u0012\u0004\u0012\u0002020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u00103\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00120\r¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0010R\u001f\u00105\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000206\u0018\u00010'0\r¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0010R\u001f\u00108\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000206\u0018\u00010'0\r¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0010R\u001f\u0010:\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000206\u0018\u00010;0\r¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0010R\u001f\u0010=\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000206\u0018\u00010;0\r¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0010¨\u0006P"}, d2 = {"Lorg/informatika/sirekap/ui/verify/administration/VerifyAdministrationViewModel;", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel;", "context", "Landroid/content/Context;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/FormC1Repository;Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "electionPageId", "Landroidx/lifecycle/MutableLiveData;", "", "formC1Administration", "Landroidx/lifecycle/LiveData;", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "getFormC1Administration", "()Landroidx/lifecycle/LiveData;", "formC1AdministrationResource", "Lorg/informatika/sirekap/support/Resource;", "getFormC1AdministrationResource", "formC1Error", "Lorg/informatika/sirekap/model/FormC1Error;", "getFormC1Error", "formC1Kesesuaian", "Lorg/informatika/sirekap/model/FormC1Kesesuaian;", "getFormC1Kesesuaian", "formC1KesesuaianAdministration", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration;", "getElectionPageUseCase", "Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "getGetElectionPageUseCase", "()Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "idImage", "isCheckAllTrue", "", "isError", "isLoading", "isLoadingVerification", "isSesuaiPerItem", "", "isTableChecked", "jenisPemilihan", "kodeTps", "koreksiPerItem", "", "getKoreksiPerItem", "previewImagePath", "getPreviewImagePath", "()Landroidx/lifecycle/MutableLiveData;", "submittedFormC1", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel$VerifyFormC1Model;", "submittedFormResource", "getSubmittedFormResource", "tableDisabilitas", "Lorg/informatika/sirekap/ui/verify/FormC1ListItem;", "getTableDisabilitas", "tablePemilih", "getTablePemilih", "tablePengguna", "", "getTablePengguna", "tableSurat", "getTableSurat", "continueVerify", "", "getJsonString", "Ljava/util/HashMap;", "isLnPos", "isLn", "refreshIdImage", "saveChecked", "item", "setup", "_idImage", "_electionPageId", "_jenisPemilihan", "_kodeTps", "submitVerification", "dialog", "Landroidx/appcompat/app/AlertDialog;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyAdministrationViewModel extends BaseVerifyViewModel {
    private final MutableLiveData<String> electionPageId;
    private final LiveData<FormC1AdministrationComplete> formC1Administration;
    private final LiveData<Resource<FormC1AdministrationComplete>> formC1AdministrationResource;
    private final LiveData<FormC1Error> formC1Error;
    private final LiveData<FormC1Kesesuaian> formC1Kesesuaian;
    private final LiveData<FormC1KesesuaianAdministration> formC1KesesuaianAdministration;
    private final FormC1Repository formC1Repository;
    private final GetElectionPageUseCase getElectionPageUseCase;
    private final MutableLiveData<String> idImage;
    private final LiveData<Boolean> isCheckAllTrue;
    private final LiveData<Boolean> isError;
    private final LiveData<Boolean> isLoading;
    private final LiveData<Boolean> isLoadingVerification;
    private final LiveData<List<Boolean>> isSesuaiPerItem;
    private final LiveData<Boolean> isTableChecked;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<List<Integer>> koreksiPerItem;
    private final MutableLiveData<String> previewImagePath;
    private final MutableLiveData<BaseVerifyViewModel.VerifyFormC1Model> submittedFormC1;
    private final LiveData<Resource<FormC1AdministrationComplete>> submittedFormResource;
    private final LiveData<List<FormC1ListItem>> tableDisabilitas;
    private final LiveData<List<FormC1ListItem>> tablePemilih;
    private final LiveData<List<FormC1ListItem>> tablePengguna;
    private final LiveData<List<FormC1ListItem>> tableSurat;

    @Inject
    public VerifyAdministrationViewModel(@ApplicationContext final Context context, FormC1Repository formC1Repository, DefaultElectionRepository electionRepository) {
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
        this.previewImagePath = new MutableLiveData<>(null);
        LiveData<Resource<FormC1AdministrationComplete>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<FormC1AdministrationComplete>>>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$formC1AdministrationResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1AdministrationComplete>> invoke(String str) {
                FormC1Repository formC1Repository2;
                MutableLiveData mutableLiveData2;
                MutableLiveData mutableLiveData3;
                if (str != null) {
                    formC1Repository2 = VerifyAdministrationViewModel.this.formC1Repository;
                    mutableLiveData2 = VerifyAdministrationViewModel.this.jenisPemilihan;
                    T value = mutableLiveData2.getValue();
                    Intrinsics.checkNotNull(value);
                    String str2 = (String) value;
                    mutableLiveData3 = VerifyAdministrationViewModel.this.kodeTps;
                    T value2 = mutableLiveData3.getValue();
                    Intrinsics.checkNotNull(value2);
                    return FormC1Repository.DefaultImpls.getFormC1Administration$default(formC1Repository2, str, str2, false, (String) value2, 4, null);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1AdministrationResource = switchMap;
        LiveData<FormC1AdministrationComplete> map = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationComplete>, FormC1AdministrationComplete>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$formC1Administration$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1AdministrationComplete invoke(Resource<FormC1AdministrationComplete> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.formC1Administration = map;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.formC1Error = Transformations.map(map, new Function1<FormC1AdministrationComplete, FormC1Error>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$formC1Error$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1Error invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                if (formC1AdministrationComplete != null) {
                    return formC1AdministrationComplete.getError();
                }
                return null;
            }
        });
        this.isError = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$isError$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationComplete> resource) {
                boolean z;
                if ((resource != null ? resource.getSuccess() : null) == ResourceStatus.ERROR) {
                    FormC1AdministrationComplete payload = resource.getPayload();
                    if ((payload != null ? payload.getKesesuaianAdministration() : null) == null) {
                        z = true;
                        return Boolean.valueOf(z);
                    }
                }
                z = false;
                return Boolean.valueOf(z);
            }
        });
        this.formC1Kesesuaian = Transformations.map(map, new Function1<FormC1AdministrationComplete, FormC1Kesesuaian>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$formC1Kesesuaian$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1Kesesuaian invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                if (formC1AdministrationComplete != null) {
                    return formC1AdministrationComplete.getKesesuaian();
                }
                return null;
            }
        });
        LiveData<FormC1KesesuaianAdministration> map2 = Transformations.map(map, new Function1<FormC1AdministrationComplete, FormC1KesesuaianAdministration>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$formC1KesesuaianAdministration$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1KesesuaianAdministration invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                if (formC1AdministrationComplete != null) {
                    return formC1AdministrationComplete.getKesesuaianAdministration();
                }
                return null;
            }
        });
        this.formC1KesesuaianAdministration = map2;
        LiveData<List<Boolean>> map3 = Transformations.map(map2, new Function1<FormC1KesesuaianAdministration, List<Boolean>>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$isSesuaiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Boolean> invoke(FormC1KesesuaianAdministration formC1KesesuaianAdministration) {
                if (formC1KesesuaianAdministration != null) {
                    return formC1KesesuaianAdministration.toIsSesuaiPerItem();
                }
                return null;
            }
        });
        this.isSesuaiPerItem = map3;
        this.koreksiPerItem = Transformations.map(map2, new Function1<FormC1KesesuaianAdministration, List<Integer>>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$koreksiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Integer> invoke(FormC1KesesuaianAdministration formC1KesesuaianAdministration) {
                if (formC1KesesuaianAdministration != null) {
                    return formC1KesesuaianAdministration.toKoreksiPerItem();
                }
                return null;
            }
        });
        this.isCheckAllTrue = Transformations.map(map3, new Function1<List<Boolean>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$isCheckAllTrue$1
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
        this.isTableChecked = Transformations.map(map2, new Function1<FormC1KesesuaianAdministration, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$isTableChecked$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FormC1KesesuaianAdministration formC1KesesuaianAdministration) {
                if (formC1KesesuaianAdministration != null) {
                    return Boolean.valueOf(formC1KesesuaianAdministration.isCheckedAll());
                }
                return null;
            }
        });
        this.tablePemilih = Transformations.map(map, new Function1<FormC1AdministrationComplete, List<FormC1ListItem>>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$tablePemilih$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1ListItem> invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                FormC1Administration form;
                MutableLiveData mutableLiveData2;
                HashMap jsonString;
                if (formC1AdministrationComplete == null || (form = formC1AdministrationComplete.getForm()) == null) {
                    return null;
                }
                FormC1KesesuaianAdministration kesesuaianAdministration = formC1AdministrationComplete.getKesesuaianAdministration();
                ElectionPage electionPage = formC1AdministrationComplete.getElectionPage();
                mutableLiveData2 = VerifyAdministrationViewModel.this.jenisPemilihan;
                String str = (String) mutableLiveData2.getValue();
                jsonString = VerifyAdministrationViewModel.this.getJsonString(context, formC1AdministrationComplete.getElectionPage().getJenisPemilihan(), formC1AdministrationComplete.getForm().isLnPos(), formC1AdministrationComplete.getForm().isLn());
                Object obj = jsonString != null ? jsonString.get("ia") : null;
                String str2 = obj instanceof String ? (String) obj : null;
                if (str2 == null) {
                    str2 = "";
                }
                return CollectionsKt.listOf(new FormC1ListItem(FormC1Administration.FORM_C1_ROW_PEMILIH_DPT, null, str2, Integer.valueOf(form.getPemilihDpt_L()), Integer.valueOf(form.getPemilihDpt_P()), form.getTotalPemilihDpt(), kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowPemilihDpt() : false, kesesuaianAdministration != null ? kesesuaianAdministration.getPemilihDpt_L() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getPemilihDpt_P() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPemilihDpt() : null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getPemilihDpt_LCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getPemilihDpt_PCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPemilihDptCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null));
            }
        });
        this.tablePengguna = Transformations.map(map, new Function1<FormC1AdministrationComplete, List<FormC1ListItem>>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$tablePengguna$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1ListItem> invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                FormC1Administration form;
                HashMap jsonString;
                MutableLiveData mutableLiveData2;
                Integer totalPengguna;
                Integer totalPenggunaDptb;
                Integer totalPenggunaDpk;
                Integer totalPenggunaDptCorrected;
                if (formC1AdministrationComplete == null || (form = formC1AdministrationComplete.getForm()) == null) {
                    return null;
                }
                FormC1KesesuaianAdministration kesesuaianAdministration = formC1AdministrationComplete.getKesesuaianAdministration();
                jsonString = VerifyAdministrationViewModel.this.getJsonString(context, formC1AdministrationComplete.getElectionPage().getJenisPemilihan(), formC1AdministrationComplete.getForm().isLnPos(), formC1AdministrationComplete.getForm().isLn());
                Object obj = jsonString != null ? jsonString.get("ib1") : null;
                String str = obj instanceof String ? (String) obj : null;
                String str2 = str == null ? "" : str;
                ElectionPage electionPage = formC1AdministrationComplete.getElectionPage();
                mutableLiveData2 = VerifyAdministrationViewModel.this.jenisPemilihan;
                String str3 = (String) mutableLiveData2.getValue();
                FormC1ListItem[] formC1ListItemArr = new FormC1ListItem[1];
                formC1ListItemArr[0] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPT, 1, str2, Integer.valueOf(form.getPenggunaDpt_L()), Integer.valueOf(form.getPenggunaDpt_P()), form.getTotalPenggunaDpt(), kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowPenggunaDpt() : false, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDpt_L() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDpt_P() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPenggunaDpt() : null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDpt_LCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDpt_PCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPenggunaDptCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str3, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                List<FormC1ListItem> mutableListOf = CollectionsKt.mutableListOf(formC1ListItemArr);
                FormC1ListItem[] formC1ListItemArr2 = new FormC1ListItem[3];
                Object obj2 = jsonString != null ? jsonString.get("ib2") : null;
                String str4 = obj2 instanceof String ? (String) obj2 : null;
                String str5 = str4 == null ? "" : str4;
                Integer penggunaDptb_L = form.getPenggunaDptb_L();
                Integer penggunaDptb_P = form.getPenggunaDptb_P();
                Integer totalPenggunaDptb2 = form.getTotalPenggunaDptb();
                formC1ListItemArr2[0] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPTB, 2, str5, penggunaDptb_L, penggunaDptb_P, totalPenggunaDptb2 != null ? totalPenggunaDptb2.intValue() : 0, kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowPenggunaDptb() : false, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDptb_L() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDptb_P() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPenggunaDptb() : null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDptb_LCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDptb_PCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPenggunaDptbCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str3, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                Object obj3 = jsonString != null ? jsonString.get("ib3") : null;
                String str6 = obj3 instanceof String ? (String) obj3 : null;
                String str7 = str6 == null ? "" : str6;
                Integer penggunaDpk_L = form.getPenggunaDpk_L();
                Integer penggunaDpk_P = form.getPenggunaDpk_P();
                Integer totalPenggunaDpk2 = form.getTotalPenggunaDpk();
                formC1ListItemArr2[1] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPK, 3, str7, penggunaDpk_L, penggunaDpk_P, totalPenggunaDpk2 != null ? totalPenggunaDpk2.intValue() : 0, kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowPenggunaDpk() : false, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDpk_L() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDpk_P() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPenggunaDpk() : null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDpk_LCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getPenggunaDpk_PCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPenggunaDpkCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str3, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                Object obj4 = jsonString != null ? jsonString.get("ib4") : null;
                String str8 = obj4 instanceof String ? (String) obj4 : null;
                String str9 = str8 == null ? "" : str8;
                Integer totalPengguna_L = form.getTotalPengguna_L();
                Integer totalPengguna_P = form.getTotalPengguna_P();
                Integer totalPengguna2 = form.getTotalPengguna();
                formC1ListItemArr2[2] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_PENGGUNA_TOTAL, 4, str9, totalPengguna_L, totalPengguna_P, totalPengguna2 != null ? totalPengguna2.intValue() : 0, kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowTotalPengguna() : false, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPengguna_L() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPengguna_P() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPengguna() : null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPengguna_LCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPengguna_PCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPenggunaCorrected() : null, Boolean.valueOf(form.isTotalPenggunaValid(((kesesuaianAdministration == null || (totalPengguna = kesesuaianAdministration.getTotalPenggunaCorrected()) == null) && (totalPengguna = form.getTotalPengguna()) == null) ? 0 : totalPengguna.intValue(), (kesesuaianAdministration == null || (totalPenggunaDptCorrected = kesesuaianAdministration.getTotalPenggunaDptCorrected()) == null) ? form.getTotalPenggunaDpt() : totalPenggunaDptCorrected.intValue(), ((kesesuaianAdministration == null || (totalPenggunaDpk = kesesuaianAdministration.getTotalPenggunaDpkCorrected()) == null) && (totalPenggunaDpk = form.getTotalPenggunaDpk()) == null) ? 0 : totalPenggunaDpk.intValue(), ((kesesuaianAdministration == null || (totalPenggunaDptb = kesesuaianAdministration.getTotalPenggunaDptbCorrected()) == null) && (totalPenggunaDptb = form.getTotalPenggunaDptb()) == null) ? 0 : totalPenggunaDptb.intValue())), context.getString(FormC1Administration.Companion.getTotalPenggunaInvalidMessage()), electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str3, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                mutableListOf.addAll(CollectionsKt.listOf((Object[]) formC1ListItemArr2));
                return mutableListOf;
            }
        });
        this.tableSurat = Transformations.map(map, new Function1<FormC1AdministrationComplete, List<FormC1ListItem>>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$tableSurat$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1ListItem> invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                FormC1Administration form;
                HashMap jsonString;
                MutableLiveData mutableLiveData2;
                Integer totalPengguna;
                boolean z;
                Integer suratKembaliPPLN;
                Integer suratTidakTerpakai;
                Integer suratTidakDikembalikan;
                Integer suratTidakDigunakanCorrected;
                Integer suratDigunakanCorrected;
                if (formC1AdministrationComplete == null || (form = formC1AdministrationComplete.getForm()) == null) {
                    return null;
                }
                FormC1KesesuaianAdministration kesesuaianAdministration = formC1AdministrationComplete.getKesesuaianAdministration();
                jsonString = VerifyAdministrationViewModel.this.getJsonString(context, formC1AdministrationComplete.getElectionPage().getJenisPemilihan(), formC1AdministrationComplete.getForm().isLnPos(), formC1AdministrationComplete.getForm().isLn());
                Object obj = jsonString != null ? jsonString.get("ii1") : null;
                String str = obj instanceof String ? (String) obj : null;
                String str2 = str == null ? "" : str;
                Object obj2 = jsonString != null ? jsonString.get("ii2") : null;
                String str3 = obj2 instanceof String ? (String) obj2 : null;
                if (str3 == null) {
                    str3 = "";
                }
                Object obj3 = jsonString != null ? jsonString.get("ii3") : null;
                String str4 = obj3 instanceof String ? (String) obj3 : null;
                String str5 = str4 == null ? "" : str4;
                Object obj4 = jsonString != null ? jsonString.get("ii4") : null;
                String str6 = obj4 instanceof String ? (String) obj4 : null;
                String str7 = str6 == null ? "" : str6;
                ElectionPage electionPage = formC1AdministrationComplete.getElectionPage();
                mutableLiveData2 = VerifyAdministrationViewModel.this.jenisPemilihan;
                String str8 = (String) mutableLiveData2.getValue();
                FormC1ListItem[] formC1ListItemArr = new FormC1ListItem[4];
                formC1ListItemArr[0] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_SURAT_DITERIMA, 1, str2, null, null, form.getSuratDiterima(), kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowSuratDiterima() : false, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratDiterima() : null, null, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratDiterimaCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str8, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                formC1ListItemArr[1] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_SURAT_DIGUNAKAN, 2, str3, null, null, form.getSuratDigunakan(), kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowSuratDigunakan() : false, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratDigunakan() : null, null, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratDigunakanCorrected() : null, Boolean.valueOf(form.isSuratDigunakanValid((kesesuaianAdministration == null || (suratDigunakanCorrected = kesesuaianAdministration.getSuratDigunakanCorrected()) == null) ? form.getSuratDigunakan() : suratDigunakanCorrected.intValue(), ((kesesuaianAdministration == null || (totalPengguna = kesesuaianAdministration.getTotalPenggunaCorrected()) == null) && (totalPengguna = form.getTotalPengguna()) == null) ? 0 : totalPengguna.intValue())), context.getString(FormC1Administration.Companion.getSuratDigunakanInvalidMessage()), electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str8, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                formC1ListItemArr[2] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_SURAT_DIKEMBALIKAN, 3, str5, null, null, form.getSuratDikembalikan(), kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowSuratDikembalikan() : false, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratDikembalikan() : null, null, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratDikembalikanCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str8, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                int suratTidakDigunakan = form.getSuratTidakDigunakan();
                boolean isCheckedRowSuratTidakDigunakan = kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowSuratTidakDigunakan() : false;
                Boolean suratTidakDigunakan2 = kesesuaianAdministration != null ? kesesuaianAdministration.getSuratTidakDigunakan() : null;
                Integer suratTidakDigunakanCorrected2 = kesesuaianAdministration != null ? kesesuaianAdministration.getSuratTidakDigunakanCorrected() : null;
                if (form.isLnPos()) {
                    z = form.isSuratTidakDigunakanLnPosValid((kesesuaianAdministration == null || (suratTidakDigunakanCorrected = kesesuaianAdministration.getSuratTidakDigunakanCorrected()) == null) ? form.getSuratTidakDigunakan() : suratTidakDigunakanCorrected.intValue(), ((kesesuaianAdministration == null || (suratKembaliPPLN = kesesuaianAdministration.getSuratKembaliPPLNCorrected()) == null) && (suratKembaliPPLN = form.getSuratKembaliPPLN()) == null) ? 0 : suratKembaliPPLN.intValue(), ((kesesuaianAdministration == null || (suratTidakTerpakai = kesesuaianAdministration.getSuratTidakTerpakaiCorrected()) == null) && (suratTidakTerpakai = form.getSuratTidakTerpakai()) == null) ? 0 : suratTidakTerpakai.intValue(), ((kesesuaianAdministration == null || (suratTidakDikembalikan = kesesuaianAdministration.getSuratTidakDikembalikanCorrected()) == null) && (suratTidakDikembalikan = form.getSuratTidakDikembalikan()) == null) ? 0 : suratTidakDikembalikan.intValue());
                } else {
                    z = true;
                }
                formC1ListItemArr[3] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_DIGUNAKAN, 4, str7, null, null, suratTidakDigunakan, isCheckedRowSuratTidakDigunakan, null, null, suratTidakDigunakan2, null, null, null, suratTidakDigunakanCorrected2, Boolean.valueOf(z), form.isLnPos() ? context.getString(FormC1Administration.Companion.getSuratTidakDigunakanLnPosInvalidMessage()) : null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str8, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                List<FormC1ListItem> mutableListOf = CollectionsKt.mutableListOf(formC1ListItemArr);
                if (form.isLnPos()) {
                    FormC1ListItem[] formC1ListItemArr2 = new FormC1ListItem[3];
                    Object obj5 = jsonString != null ? jsonString.get("ii4a") : null;
                    String str9 = obj5 instanceof String ? (String) obj5 : null;
                    String str10 = str9 == null ? "" : str9;
                    Integer suratKembaliPPLN2 = form.getSuratKembaliPPLN();
                    formC1ListItemArr2[0] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_SURAT_KEMBALI_PPLN, null, str10, null, null, suratKembaliPPLN2 != null ? suratKembaliPPLN2.intValue() : 0, kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowSuratKembaliPPLN() : false, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratKembaliPPLN() : null, null, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratKembaliPPLNCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str8, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                    Object obj6 = jsonString != null ? jsonString.get("ii4b") : null;
                    String str11 = obj6 instanceof String ? (String) obj6 : null;
                    String str12 = str11 == null ? "" : str11;
                    Integer suratTidakTerpakai2 = form.getSuratTidakTerpakai();
                    formC1ListItemArr2[1] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_TERPAKAI, null, str12, null, null, suratTidakTerpakai2 != null ? suratTidakTerpakai2.intValue() : 0, kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowSuratTidakTerpakai() : false, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratTidakTerpakai() : null, null, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratTidakTerpakaiCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str8, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                    Object obj7 = jsonString != null ? jsonString.get("ii4c") : null;
                    String str13 = obj7 instanceof String ? (String) obj7 : null;
                    String str14 = str13 == null ? "" : str13;
                    Integer suratTidakDikembalikan2 = form.getSuratTidakDikembalikan();
                    formC1ListItemArr2[2] = new FormC1ListItem(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_DIKEMBALIKAN, null, str14, null, null, suratTidakDikembalikan2 != null ? suratTidakDikembalikan2.intValue() : 0, kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowSuratTidakDikembalikan() : false, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratTidakDikembalikan() : null, null, null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getSuratTidakDikembalikanCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str8, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null);
                    mutableListOf.addAll(CollectionsKt.listOf((Object[]) formC1ListItemArr2));
                }
                return mutableListOf;
            }
        });
        this.tableDisabilitas = Transformations.map(map, new Function1<FormC1AdministrationComplete, List<FormC1ListItem>>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$tableDisabilitas$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1ListItem> invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                FormC1Administration form;
                HashMap jsonString;
                MutableLiveData mutableLiveData2;
                if (formC1AdministrationComplete == null || (form = formC1AdministrationComplete.getForm()) == null) {
                    return null;
                }
                FormC1KesesuaianAdministration kesesuaianAdministration = formC1AdministrationComplete.getKesesuaianAdministration();
                jsonString = VerifyAdministrationViewModel.this.getJsonString(context, formC1AdministrationComplete.getElectionPage().getJenisPemilihan(), formC1AdministrationComplete.getForm().isLnPos(), formC1AdministrationComplete.getForm().isLn());
                ElectionPage electionPage = formC1AdministrationComplete.getElectionPage();
                mutableLiveData2 = VerifyAdministrationViewModel.this.jenisPemilihan;
                String str = (String) mutableLiveData2.getValue();
                Object obj = jsonString != null ? jsonString.get("iii") : null;
                String str2 = obj instanceof String ? (String) obj : null;
                if (str2 == null) {
                    str2 = "";
                }
                return CollectionsKt.listOf(new FormC1ListItem(FormC1Administration.FORM_C1_ROW_PEMILIH_DISABILITAS, null, str2, Integer.valueOf(form.getPemilihDisabilitas_L()), Integer.valueOf(form.getPemilihDisabilitas_P()), form.getTotalPemilihDisabilitas(), kesesuaianAdministration != null ? kesesuaianAdministration.isCheckedRowPemilihDisabilitas() : false, kesesuaianAdministration != null ? kesesuaianAdministration.getPemilihDisabilitas_L() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getPemilihDisabilitas_P() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPemilihDisabilitas() : null, null, kesesuaianAdministration != null ? kesesuaianAdministration.getPemilihDisabilitas_LCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getPemilihDisabilitas_PCorrected() : null, kesesuaianAdministration != null ? kesesuaianAdministration.getTotalPemilihDisabilitasCorrected() : null, true, null, electionPage.getCroppedPhotoPath(), electionPage.getCorrectedPhotoPath(), null, str, Boolean.valueOf(form.isLn()), Boolean.valueOf(form.isLnPos()), 263168, null));
            }
        });
        MutableLiveData<BaseVerifyViewModel.VerifyFormC1Model> mutableLiveData2 = new MutableLiveData<>(null);
        this.submittedFormC1 = mutableLiveData2;
        LiveData<Resource<FormC1AdministrationComplete>> switchMap2 = Transformations.switchMap(mutableLiveData2, new Function1<BaseVerifyViewModel.VerifyFormC1Model, LiveData<Resource<FormC1AdministrationComplete>>>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$submittedFormResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<FormC1AdministrationComplete>> invoke(BaseVerifyViewModel.VerifyFormC1Model verifyFormC1Model) {
                MutableLiveData mutableLiveData3;
                FormC1Repository formC1Repository2;
                if (verifyFormC1Model != null) {
                    mutableLiveData3 = VerifyAdministrationViewModel.this.electionPageId;
                    String str = (String) mutableLiveData3.getValue();
                    if (str != null) {
                        formC1Repository2 = VerifyAdministrationViewModel.this.formC1Repository;
                        String jenisPemilihan = verifyFormC1Model.getJenisPemilihan();
                        String deviceId = verifyFormC1Model.getDeviceId();
                        String idImage = verifyFormC1Model.getIdImage();
                        boolean isSesuai = verifyFormC1Model.isSesuai();
                        List<Boolean> isSesuaiPerItem = verifyFormC1Model.isSesuaiPerItem();
                        String komentar = verifyFormC1Model.getKomentar();
                        if (komentar == null) {
                            komentar = "";
                        }
                        return formC1Repository2.verifyFormC1(jenisPemilihan, deviceId, 1, str, idImage, isSesuai, isSesuaiPerItem, komentar, verifyFormC1Model.getKoreksiPerItem(), verifyFormC1Model.getKodeTps());
                    }
                    return null;
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.submittedFormResource = switchMap2;
        this.isLoadingVerification = Transformations.map(switchMap2, new Function1<Resource<FormC1AdministrationComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel$isLoadingVerification$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
    }

    public final GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.getElectionPageUseCase;
    }

    public final MutableLiveData<String> getPreviewImagePath() {
        return this.previewImagePath;
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

    public final LiveData<Resource<FormC1AdministrationComplete>> getFormC1AdministrationResource() {
        return this.formC1AdministrationResource;
    }

    public final LiveData<FormC1AdministrationComplete> getFormC1Administration() {
        return this.formC1Administration;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final LiveData<FormC1Error> getFormC1Error() {
        return this.formC1Error;
    }

    public final LiveData<Boolean> isError() {
        return this.isError;
    }

    public final LiveData<FormC1Kesesuaian> getFormC1Kesesuaian() {
        return this.formC1Kesesuaian;
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

    public final void saveChecked(FormC1ListItem item) {
        Boolean value;
        Boolean value2;
        Boolean value3;
        Integer num;
        Integer num2;
        Integer num3;
        Integer num4;
        Integer num5;
        Integer num6;
        Integer num7;
        Integer num8;
        Integer num9;
        Integer num10;
        Integer num11;
        Integer num12;
        Integer num13;
        Integer num14;
        Integer num15;
        Integer num16;
        Integer num17;
        Integer num18;
        Integer num19;
        Integer num20;
        Integer num21;
        Integer num22;
        Integer num23;
        Integer num24;
        Integer num25;
        Intrinsics.checkNotNullParameter(item, "item");
        String value4 = this.idImage.getValue();
        if (value4 == null || (value = isWrongL().getValue()) == null || (value2 = isWrongP().getValue()) == null || (value3 = isWrongTotal().getValue()) == null) {
            return;
        }
        String correctedL = getCorrectedL().getValue();
        String correctedP = getCorrectedP().getValue();
        String correctedTotal = getCorrectedTotal().getValue();
        FormC1KesesuaianAdministration formC1KesesuaianAdministration = this.formC1KesesuaianAdministration.getValue();
        if (formC1KesesuaianAdministration != null) {
            Intrinsics.checkNotNullExpressionValue(formC1KesesuaianAdministration, "formC1KesesuaianAdministration");
            String id2 = item.getId();
            switch (id2.hashCode()) {
                case -2070480492:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_TOTAL)) {
                        Boolean valueOf = Boolean.valueOf(!value.booleanValue());
                        Boolean valueOf2 = Boolean.valueOf(!value2.booleanValue());
                        Boolean valueOf3 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedL != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedL, "correctedL");
                            num = StringsKt.toIntOrNull(correctedL);
                        } else {
                            num = null;
                        }
                        if (correctedP != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedP, "correctedP");
                            num2 = StringsKt.toIntOrNull(correctedP);
                        } else {
                            num2 = null;
                        }
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num3 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num3 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : valueOf, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : valueOf2, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : valueOf3, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : num, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : num2, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : num3, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case -1503438034:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_SURAT_KEMBALI_PPLN)) {
                        Boolean valueOf4 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num4 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num4 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : valueOf4, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : num4, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case -918385296:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_DIGUNAKAN)) {
                        Boolean valueOf5 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num5 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num5 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : valueOf5, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : num5, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case -35123702:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_PEMILIH_DISABILITAS)) {
                        Boolean valueOf6 = Boolean.valueOf(!value.booleanValue());
                        Boolean valueOf7 = Boolean.valueOf(!value2.booleanValue());
                        Boolean valueOf8 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedL != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedL, "correctedL");
                            num6 = StringsKt.toIntOrNull(correctedL);
                        } else {
                            num6 = null;
                        }
                        if (correctedP != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedP, "correctedP");
                            num7 = StringsKt.toIntOrNull(correctedP);
                        } else {
                            num7 = null;
                        }
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num8 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num8 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : valueOf6, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : valueOf7, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : valueOf8, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : num6, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : num7, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : num8);
                        break;
                    }
                    break;
                case 420350717:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_SURAT_DITERIMA)) {
                        Boolean valueOf9 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num9 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num9 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : valueOf9, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : num9, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case 557253552:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_DIKEMBALIKAN)) {
                        Boolean valueOf10 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num10 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num10 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : valueOf10, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : num10, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case 646561923:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_TERPAKAI)) {
                        Boolean valueOf11 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num11 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num11 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : valueOf11, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : num11, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case 764018602:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPTB)) {
                        Boolean valueOf12 = Boolean.valueOf(!value.booleanValue());
                        Boolean valueOf13 = Boolean.valueOf(!value2.booleanValue());
                        Boolean valueOf14 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedL != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedL, "correctedL");
                            num12 = StringsKt.toIntOrNull(correctedL);
                        } else {
                            num12 = null;
                        }
                        if (correctedP != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedP, "correctedP");
                            num13 = StringsKt.toIntOrNull(correctedP);
                        } else {
                            num13 = null;
                        }
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num14 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num14 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : valueOf12, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : valueOf13, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : valueOf14, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : num12, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : num13, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : num14, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case 1270520387:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_PEMILIH_DPT)) {
                        Boolean valueOf15 = Boolean.valueOf(!value.booleanValue());
                        Boolean valueOf16 = Boolean.valueOf(!value2.booleanValue());
                        Boolean valueOf17 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedL != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedL, "correctedL");
                            num15 = StringsKt.toIntOrNull(correctedL);
                        } else {
                            num15 = null;
                        }
                        if (correctedP != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedP, "correctedP");
                            num16 = StringsKt.toIntOrNull(correctedP);
                        } else {
                            num16 = null;
                        }
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num17 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num17 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : valueOf15, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : valueOf16, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : valueOf17, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : num15, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : num16, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : num17, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case 1492254506:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_SURAT_DIKEMBALIKAN)) {
                        Boolean valueOf18 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num18 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num18 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : valueOf18, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : num18, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case 1947456566:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_SURAT_DIGUNAKAN)) {
                        Boolean valueOf19 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num19 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num19 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : valueOf19, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : num19, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case 1964308399:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPK)) {
                        Boolean valueOf20 = Boolean.valueOf(!value.booleanValue());
                        Boolean valueOf21 = Boolean.valueOf(!value2.booleanValue());
                        Boolean valueOf22 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedL != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedL, "correctedL");
                            num20 = StringsKt.toIntOrNull(correctedL);
                        } else {
                            num20 = null;
                        }
                        if (correctedP != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedP, "correctedP");
                            num21 = StringsKt.toIntOrNull(correctedP);
                        } else {
                            num21 = null;
                        }
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num22 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num22 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : null, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : null, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : null, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : valueOf20, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : valueOf21, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : valueOf22, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : null, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : null, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : null, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : num20, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : num21, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : num22, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
                case 1964308408:
                    if (id2.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPT)) {
                        Boolean valueOf23 = Boolean.valueOf(!value.booleanValue());
                        Boolean valueOf24 = Boolean.valueOf(!value2.booleanValue());
                        Boolean valueOf25 = Boolean.valueOf(!value3.booleanValue());
                        if (correctedL != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedL, "correctedL");
                            num23 = StringsKt.toIntOrNull(correctedL);
                        } else {
                            num23 = null;
                        }
                        if (correctedP != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedP, "correctedP");
                            num24 = StringsKt.toIntOrNull(correctedP);
                        } else {
                            num24 = null;
                        }
                        if (correctedTotal != null) {
                            Intrinsics.checkNotNullExpressionValue(correctedTotal, "correctedTotal");
                            num25 = StringsKt.toIntOrNull(correctedTotal);
                        } else {
                            num25 = null;
                        }
                        formC1KesesuaianAdministration = formC1KesesuaianAdministration.copy((r77 & 1) != 0 ? formC1KesesuaianAdministration.idImage : null, (r77 & 2) != 0 ? formC1KesesuaianAdministration.isLnPos : false, (r77 & 4) != 0 ? formC1KesesuaianAdministration.jenisPemilihan : null, (r77 & 8) != 0 ? formC1KesesuaianAdministration.pemilihDpt_L : null, (r77 & 16) != 0 ? formC1KesesuaianAdministration.pemilihDpt_P : null, (r77 & 32) != 0 ? formC1KesesuaianAdministration.totalPemilihDpt : null, (r77 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpt_L : valueOf23, (r77 & 128) != 0 ? formC1KesesuaianAdministration.penggunaDpt_P : valueOf24, (r77 & 256) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpt : valueOf25, (r77 & 512) != 0 ? formC1KesesuaianAdministration.penggunaDptb_L : null, (r77 & 1024) != 0 ? formC1KesesuaianAdministration.penggunaDptb_P : null, (r77 & 2048) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptb : null, (r77 & 4096) != 0 ? formC1KesesuaianAdministration.penggunaDpk_L : null, (r77 & 8192) != 0 ? formC1KesesuaianAdministration.penggunaDpk_P : null, (r77 & 16384) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpk : null, (r77 & 32768) != 0 ? formC1KesesuaianAdministration.totalPengguna_L : null, (r77 & 65536) != 0 ? formC1KesesuaianAdministration.totalPengguna_P : null, (r77 & 131072) != 0 ? formC1KesesuaianAdministration.totalPengguna : null, (r77 & 262144) != 0 ? formC1KesesuaianAdministration.suratDiterima : null, (r77 & 524288) != 0 ? formC1KesesuaianAdministration.suratDikembalikan : null, (r77 & 1048576) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakan : null, (r77 & 2097152) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikan : null, (r77 & 4194304) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLN : null, (r77 & 8388608) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakai : null, (r77 & 16777216) != 0 ? formC1KesesuaianAdministration.suratDigunakan : null, (r77 & 33554432) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_L : null, (r77 & 67108864) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_P : null, (r77 & 134217728) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitas : null, (r77 & 268435456) != 0 ? formC1KesesuaianAdministration.pemilihDpt_LCorrected : null, (r77 & 536870912) != 0 ? formC1KesesuaianAdministration.pemilihDpt_PCorrected : null, (r77 & 1073741824) != 0 ? formC1KesesuaianAdministration.totalPemilihDptCorrected : null, (r77 & Integer.MIN_VALUE) != 0 ? formC1KesesuaianAdministration.penggunaDpt_LCorrected : num23, (r78 & 1) != 0 ? formC1KesesuaianAdministration.penggunaDpt_PCorrected : num24, (r78 & 2) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptCorrected : num25, (r78 & 4) != 0 ? formC1KesesuaianAdministration.penggunaDptb_LCorrected : null, (r78 & 8) != 0 ? formC1KesesuaianAdministration.penggunaDptb_PCorrected : null, (r78 & 16) != 0 ? formC1KesesuaianAdministration.totalPenggunaDptbCorrected : null, (r78 & 32) != 0 ? formC1KesesuaianAdministration.penggunaDpk_LCorrected : null, (r78 & 64) != 0 ? formC1KesesuaianAdministration.penggunaDpk_PCorrected : null, (r78 & 128) != 0 ? formC1KesesuaianAdministration.totalPenggunaDpkCorrected : null, (r78 & 256) != 0 ? formC1KesesuaianAdministration.totalPengguna_LCorrected : null, (r78 & 512) != 0 ? formC1KesesuaianAdministration.totalPengguna_PCorrected : null, (r78 & 1024) != 0 ? formC1KesesuaianAdministration.totalPenggunaCorrected : null, (r78 & 2048) != 0 ? formC1KesesuaianAdministration.suratDiterimaCorrected : null, (r78 & 4096) != 0 ? formC1KesesuaianAdministration.suratDikembalikanCorrected : null, (r78 & 8192) != 0 ? formC1KesesuaianAdministration.suratTidakDigunakanCorrected : null, (r78 & 16384) != 0 ? formC1KesesuaianAdministration.suratTidakDikembalikanCorrected : null, (r78 & 32768) != 0 ? formC1KesesuaianAdministration.suratKembaliPPLNCorrected : null, (r78 & 65536) != 0 ? formC1KesesuaianAdministration.suratTidakTerpakaiCorrected : null, (r78 & 131072) != 0 ? formC1KesesuaianAdministration.suratDigunakanCorrected : null, (r78 & 262144) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_LCorrected : null, (r78 & 524288) != 0 ? formC1KesesuaianAdministration.pemilihDisabilitas_PCorrected : null, (r78 & 1048576) != 0 ? formC1KesesuaianAdministration.totalPemilihDisabilitasCorrected : null);
                        break;
                    }
                    break;
            }
            this.formC1Repository.saveFormC1KesesuaianAdministration(value4, formC1KesesuaianAdministration);
            Unit unit = Unit.INSTANCE;
            Unit unit2 = Unit.INSTANCE;
        }
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
                    num = Integer.valueOf(z ? R.raw.ppwp_lnpos_strings : z2 ? R.raw.ppwp_ln_strings : R.raw.ppwp_dn_strings);
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

    public final LiveData<List<FormC1ListItem>> getTablePemilih() {
        return this.tablePemilih;
    }

    public final LiveData<List<FormC1ListItem>> getTablePengguna() {
        return this.tablePengguna;
    }

    public final LiveData<List<FormC1ListItem>> getTableSurat() {
        return this.tableSurat;
    }

    public final LiveData<List<FormC1ListItem>> getTableDisabilitas() {
        return this.tableDisabilitas;
    }

    public final LiveData<Resource<FormC1AdministrationComplete>> getSubmittedFormResource() {
        return this.submittedFormResource;
    }

    public final LiveData<Boolean> isLoadingVerification() {
        return this.isLoadingVerification;
    }

    public static /* synthetic */ void submitVerification$default(VerifyAdministrationViewModel verifyAdministrationViewModel, Context context, AlertDialog alertDialog, int i, Object obj) {
        if ((i & 2) != 0) {
            alertDialog = null;
        }
        verifyAdministrationViewModel.submitVerification(context, alertDialog);
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
        formC1Repository.createEmptyFormC1Administration(str, value4, bitmap, value2.getElection().isLn(), value2.getElection().isLnPos(), true);
    }

    public final void refreshIdImage() {
        String value = this.idImage.getValue();
        if (value != null) {
            this.idImage.postValue(value);
        }
    }
}
