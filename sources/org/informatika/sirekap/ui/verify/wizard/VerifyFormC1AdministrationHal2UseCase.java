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
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.FormC1AdministrationHal2;
import org.informatika.sirekap.model.FormC1AdministrationHal2Complete;
import org.informatika.sirekap.model.FormC1KesesuaianAdministrationHal2;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem;

/* compiled from: VerifyFormC1AdministrationHal2UseCase.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u001a2\u0006\u0010$\u001a\u00020\n¢\u0006\u0002\u0010%J\u001d\u0010&\u001a\u00020\"2\b\u0010'\u001a\u0004\u0018\u00010\u001f2\u0006\u0010$\u001a\u00020\n¢\u0006\u0002\u0010(J\u001e\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000eR\u001f\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00130\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\fR!\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\fR\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u001e\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\f¨\u0006-"}, d2 = {"Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1AdministrationHal2UseCase;", "", "context", "Landroid/content/Context;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/FormC1Repository;)V", "checkItems", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;", "getCheckItems", "()Landroidx/lifecycle/LiveData;", "correctedPhotoPath", "", "getCorrectedPhotoPath", "formC1AdministrationHal2Complete", "Lorg/informatika/sirekap/model/FormC1AdministrationHal2Complete;", "formC1AdministrationHal2Resource", "Lorg/informatika/sirekap/support/Resource;", "formC1KesesuaianAdministrationHal2", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministrationHal2;", "getFormC1KesesuaianAdministrationHal2", "idImage", "Landroidx/lifecycle/MutableLiveData;", "isLoading", "", "isSesuaiPerItem", "jenisPemilihan", "kodeTps", "koreksiPerItem", "", "getKoreksiPerItem", "checkItem", "", "newChecked", "currentItem", "(Ljava/lang/Boolean;Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;)V", "correctItem", "correctedValue", "(Ljava/lang/Integer;Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;)V", "setup", "_idImage", "_jenisPemilihan", "_kodeTps", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyFormC1AdministrationHal2UseCase {
    private final LiveData<List<FormC1CheckItem>> checkItems;
    private final Context context;
    private final LiveData<String> correctedPhotoPath;
    private final LiveData<FormC1AdministrationHal2Complete> formC1AdministrationHal2Complete;
    private final LiveData<Resource<FormC1AdministrationHal2Complete>> formC1AdministrationHal2Resource;
    private final LiveData<FormC1KesesuaianAdministrationHal2> formC1KesesuaianAdministrationHal2;
    private final FormC1Repository formC1Repository;
    private final MutableLiveData<String> idImage;
    private final LiveData<Boolean> isLoading;
    private final LiveData<List<Boolean>> isSesuaiPerItem;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<List<Integer>> koreksiPerItem;

    public VerifyFormC1AdministrationHal2UseCase(Context context, FormC1Repository formC1Repository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(formC1Repository, "formC1Repository");
        this.context = context;
        this.formC1Repository = formC1Repository;
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.idImage = mutableLiveData;
        this.jenisPemilihan = new MutableLiveData<>(null);
        this.kodeTps = new MutableLiveData<>(null);
        LiveData<Resource<FormC1AdministrationHal2Complete>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<FormC1AdministrationHal2Complete>>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationHal2UseCase$formC1AdministrationHal2Resource$1
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
                    formC1Repository2 = VerifyFormC1AdministrationHal2UseCase.this.formC1Repository;
                    mutableLiveData2 = VerifyFormC1AdministrationHal2UseCase.this.jenisPemilihan;
                    T value = mutableLiveData2.getValue();
                    Intrinsics.checkNotNull(value);
                    String str2 = (String) value;
                    mutableLiveData3 = VerifyFormC1AdministrationHal2UseCase.this.kodeTps;
                    T value2 = mutableLiveData3.getValue();
                    Intrinsics.checkNotNull(value2);
                    return FormC1Repository.DefaultImpls.getFormC1AdministrationHal2$default(formC1Repository2, str, str2, false, (String) value2, 4, null);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1AdministrationHal2Resource = switchMap;
        LiveData<FormC1AdministrationHal2Complete> map = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationHal2Complete>, FormC1AdministrationHal2Complete>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationHal2UseCase$formC1AdministrationHal2Complete$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1AdministrationHal2Complete invoke(Resource<FormC1AdministrationHal2Complete> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.formC1AdministrationHal2Complete = map;
        LiveData<FormC1KesesuaianAdministrationHal2> map2 = Transformations.map(map, new Function1<FormC1AdministrationHal2Complete, FormC1KesesuaianAdministrationHal2>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationHal2UseCase$formC1KesesuaianAdministrationHal2$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1KesesuaianAdministrationHal2 invoke(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
                if (formC1AdministrationHal2Complete != null) {
                    return formC1AdministrationHal2Complete.getKesesuaianAdministrationHal2();
                }
                return null;
            }
        });
        this.formC1KesesuaianAdministrationHal2 = map2;
        this.isSesuaiPerItem = Transformations.map(map2, new Function1<FormC1KesesuaianAdministrationHal2, List<Boolean>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationHal2UseCase$isSesuaiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Boolean> invoke(FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
                if (formC1KesesuaianAdministrationHal2 != null) {
                    return formC1KesesuaianAdministrationHal2.toIsSesuaiPerItem();
                }
                return null;
            }
        });
        this.koreksiPerItem = Transformations.map(map2, new Function1<FormC1KesesuaianAdministrationHal2, List<Integer>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationHal2UseCase$koreksiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Integer> invoke(FormC1KesesuaianAdministrationHal2 formC1KesesuaianAdministrationHal2) {
                if (formC1KesesuaianAdministrationHal2 != null) {
                    return formC1KesesuaianAdministrationHal2.toKoreksiPerItem();
                }
                return null;
            }
        });
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationHal2Complete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationHal2UseCase$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationHal2Complete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.checkItems = Transformations.map(map, new Function1<FormC1AdministrationHal2Complete, List<FormC1CheckItem>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationHal2UseCase$checkItems$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1CheckItem> invoke(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
                Context context2;
                if (formC1AdministrationHal2Complete != null) {
                    VerifyFormC1AdministrationHal2UseCase verifyFormC1AdministrationHal2UseCase = VerifyFormC1AdministrationHal2UseCase.this;
                    FormC1CheckItem.Companion companion = FormC1CheckItem.Companion;
                    context2 = verifyFormC1AdministrationHal2UseCase.context;
                    return companion.createFromFormC1AdministrationHal2(context2, formC1AdministrationHal2Complete);
                }
                return null;
            }
        });
        this.correctedPhotoPath = Transformations.map(map, new Function1<FormC1AdministrationHal2Complete, String>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationHal2UseCase$correctedPhotoPath$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(FormC1AdministrationHal2Complete formC1AdministrationHal2Complete) {
                ElectionPage electionPage;
                if (formC1AdministrationHal2Complete == null || (electionPage = formC1AdministrationHal2Complete.getElectionPage()) == null) {
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
        if (Intrinsics.areEqual(this.idImage.getValue(), _idImage)) {
            return;
        }
        this.idImage.setValue(_idImage);
    }

    public final void checkItem(Boolean bool, FormC1CheckItem currentItem) {
        List<Boolean> isSesuaiPerItem;
        List<Integer> koreksiPerItem;
        Intrinsics.checkNotNullParameter(currentItem, "currentItem");
        String value = this.idImage.getValue();
        if (value == null || (isSesuaiPerItem = this.isSesuaiPerItem.getValue()) == null || (koreksiPerItem = this.koreksiPerItem.getValue()) == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(isSesuaiPerItem, "isSesuaiPerItem");
        List<Boolean> mutableList = CollectionsKt.toMutableList((Collection) isSesuaiPerItem);
        Intrinsics.checkNotNullExpressionValue(koreksiPerItem, "koreksiPerItem");
        List<Integer> mutableList2 = CollectionsKt.toMutableList((Collection) koreksiPerItem);
        String row = currentItem.getRow();
        int hashCode = row.hashCode();
        if (hashCode != -1225829493) {
            if (hashCode != 952919003) {
                if (hashCode == 1693880021 && row.equals(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH)) {
                    mutableList.set(1, bool);
                    if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                        mutableList2.set(1, null);
                    }
                }
            } else if (row.equals(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_SAH)) {
                mutableList.set(0, bool);
                if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                    mutableList2.set(0, null);
                }
            }
        } else if (row.equals(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_SURAT_SUARA)) {
            mutableList.set(2, bool);
            if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                mutableList2.set(2, null);
            }
        }
        this.formC1Repository.saveFormC1KesesuaianAdministrationHal2(value, mutableList, mutableList2);
    }

    public final void correctItem(Integer num, FormC1CheckItem currentItem) {
        List<Boolean> isSesuaiPerItem;
        List<Integer> koreksiPerItem;
        Intrinsics.checkNotNullParameter(currentItem, "currentItem");
        String value = this.idImage.getValue();
        if (value == null || (isSesuaiPerItem = this.isSesuaiPerItem.getValue()) == null || (koreksiPerItem = this.koreksiPerItem.getValue()) == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(koreksiPerItem, "koreksiPerItem");
        List<Integer> mutableList = CollectionsKt.toMutableList((Collection) koreksiPerItem);
        String row = currentItem.getRow();
        int hashCode = row.hashCode();
        if (hashCode != -1225829493) {
            if (hashCode != 952919003) {
                if (hashCode == 1693880021 && row.equals(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_TIDAK_SAH)) {
                    mutableList.set(1, num);
                }
            } else if (row.equals(FormC1AdministrationHal2.FORM_C1_ROW_SURAT_SUARA_SAH)) {
                mutableList.set(0, num);
            }
        } else if (row.equals(FormC1AdministrationHal2.FORM_C1_ROW_TOTAL_SURAT_SUARA)) {
            mutableList.set(2, num);
        }
        FormC1Repository formC1Repository = this.formC1Repository;
        Intrinsics.checkNotNullExpressionValue(isSesuaiPerItem, "isSesuaiPerItem");
        formC1Repository.saveFormC1KesesuaianAdministrationHal2(value, isSesuaiPerItem, mutableList);
    }

    public final LiveData<FormC1KesesuaianAdministrationHal2> getFormC1KesesuaianAdministrationHal2() {
        return this.formC1KesesuaianAdministrationHal2;
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
