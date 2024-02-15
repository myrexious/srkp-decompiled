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
import org.informatika.sirekap.model.FormC1Administration;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1KesesuaianAdministration;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem;

/* compiled from: VerifyFormC1AdministrationUseCase.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\u0006\u0010#\u001a\u00020\n¢\u0006\u0002\u0010$J\u001d\u0010%\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010\u001e2\u0006\u0010#\u001a\u00020\n¢\u0006\u0002\u0010'J\u001e\u0010(\u001a\u00020!2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u000eR\u001f\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00130\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR!\u0010\u001a\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u001d\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0018\u00010\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\f¨\u0006,"}, d2 = {"Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1AdministrationUseCase;", "", "context", "Landroid/content/Context;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/FormC1Repository;)V", "checkItems", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;", "getCheckItems", "()Landroidx/lifecycle/LiveData;", "correctedPhotoPath", "", "getCorrectedPhotoPath", "formC1Administration", "Lorg/informatika/sirekap/model/FormC1AdministrationComplete;", "formC1AdministrationResource", "Lorg/informatika/sirekap/support/Resource;", "formC1KesesuaianAdministration", "Lorg/informatika/sirekap/model/FormC1KesesuaianAdministration;", "idImageAdministration", "Landroidx/lifecycle/MutableLiveData;", "isLoading", "", "isSesuaiPerItem", "jenisPemilihan", "kodeTps", "koreksiPerItem", "", "getKoreksiPerItem", "checkItem", "", "newChecked", "currentItem", "(Ljava/lang/Boolean;Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;)V", "correctItem", "correctedValue", "(Ljava/lang/Integer;Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;)V", "setup", "_idImage", "_jenisPemilihan", "_kodeTps", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyFormC1AdministrationUseCase {
    private final LiveData<List<FormC1CheckItem>> checkItems;
    private final Context context;
    private final LiveData<String> correctedPhotoPath;
    private final LiveData<FormC1AdministrationComplete> formC1Administration;
    private final LiveData<Resource<FormC1AdministrationComplete>> formC1AdministrationResource;
    private final LiveData<FormC1KesesuaianAdministration> formC1KesesuaianAdministration;
    private final FormC1Repository formC1Repository;
    private final MutableLiveData<String> idImageAdministration;
    private final LiveData<Boolean> isLoading;
    private final LiveData<List<Boolean>> isSesuaiPerItem;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<List<Integer>> koreksiPerItem;

    public VerifyFormC1AdministrationUseCase(Context context, FormC1Repository formC1Repository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(formC1Repository, "formC1Repository");
        this.context = context;
        this.formC1Repository = formC1Repository;
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>(null);
        this.idImageAdministration = mutableLiveData;
        this.jenisPemilihan = new MutableLiveData<>(null);
        this.kodeTps = new MutableLiveData<>(null);
        LiveData<Resource<FormC1AdministrationComplete>> switchMap = Transformations.switchMap(mutableLiveData, new Function1<String, LiveData<Resource<FormC1AdministrationComplete>>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationUseCase$formC1AdministrationResource$1
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
                    formC1Repository2 = VerifyFormC1AdministrationUseCase.this.formC1Repository;
                    mutableLiveData2 = VerifyFormC1AdministrationUseCase.this.jenisPemilihan;
                    T value = mutableLiveData2.getValue();
                    Intrinsics.checkNotNull(value);
                    String str2 = (String) value;
                    mutableLiveData3 = VerifyFormC1AdministrationUseCase.this.kodeTps;
                    T value2 = mutableLiveData3.getValue();
                    Intrinsics.checkNotNull(value2);
                    return FormC1Repository.DefaultImpls.getFormC1Administration$default(formC1Repository2, str, str2, false, (String) value2, 4, null);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.formC1AdministrationResource = switchMap;
        LiveData<FormC1AdministrationComplete> map = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationComplete>, FormC1AdministrationComplete>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationUseCase$formC1Administration$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1AdministrationComplete invoke(Resource<FormC1AdministrationComplete> resource) {
                if (resource != null) {
                    return resource.getPayload();
                }
                return null;
            }
        });
        this.formC1Administration = map;
        LiveData<FormC1KesesuaianAdministration> map2 = Transformations.map(map, new Function1<FormC1AdministrationComplete, FormC1KesesuaianAdministration>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationUseCase$formC1KesesuaianAdministration$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1KesesuaianAdministration invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                if (formC1AdministrationComplete != null) {
                    return formC1AdministrationComplete.getKesesuaianAdministration();
                }
                return null;
            }
        });
        this.formC1KesesuaianAdministration = map2;
        this.checkItems = Transformations.map(map, new Function1<FormC1AdministrationComplete, List<FormC1CheckItem>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationUseCase$checkItems$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1CheckItem> invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                Context context2;
                if (formC1AdministrationComplete != null) {
                    VerifyFormC1AdministrationUseCase verifyFormC1AdministrationUseCase = VerifyFormC1AdministrationUseCase.this;
                    FormC1CheckItem.Companion companion = FormC1CheckItem.Companion;
                    context2 = verifyFormC1AdministrationUseCase.context;
                    return companion.createFromFormC1AdministrationComplete(context2, formC1AdministrationComplete);
                }
                return null;
            }
        });
        this.correctedPhotoPath = Transformations.map(map, new Function1<FormC1AdministrationComplete, String>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationUseCase$correctedPhotoPath$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(FormC1AdministrationComplete formC1AdministrationComplete) {
                ElectionPage electionPage;
                if (formC1AdministrationComplete == null || (electionPage = formC1AdministrationComplete.getElectionPage()) == null) {
                    return null;
                }
                return electionPage.getCorrectedPhotoPath();
            }
        });
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<FormC1AdministrationComplete>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationUseCase$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<FormC1AdministrationComplete> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        this.isSesuaiPerItem = Transformations.map(map2, new Function1<FormC1KesesuaianAdministration, List<Boolean>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationUseCase$isSesuaiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Boolean> invoke(FormC1KesesuaianAdministration formC1KesesuaianAdministration) {
                if (formC1KesesuaianAdministration != null) {
                    return formC1KesesuaianAdministration.toIsSesuaiPerItem();
                }
                return null;
            }
        });
        this.koreksiPerItem = Transformations.map(map2, new Function1<FormC1KesesuaianAdministration, List<Integer>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationUseCase$koreksiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Integer> invoke(FormC1KesesuaianAdministration formC1KesesuaianAdministration) {
                if (formC1KesesuaianAdministration != null) {
                    return formC1KesesuaianAdministration.toKoreksiPerItem();
                }
                return null;
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
        if (Intrinsics.areEqual(this.idImageAdministration.getValue(), _idImage)) {
            return;
        }
        this.idImageAdministration.setValue(_idImage);
    }

    public final LiveData<List<FormC1CheckItem>> getCheckItems() {
        return this.checkItems;
    }

    public final LiveData<String> getCorrectedPhotoPath() {
        return this.correctedPhotoPath;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final LiveData<List<Boolean>> isSesuaiPerItem() {
        return this.isSesuaiPerItem;
    }

    public final LiveData<List<Integer>> getKoreksiPerItem() {
        return this.koreksiPerItem;
    }

    public final void checkItem(Boolean bool, FormC1CheckItem currentItem) {
        List<Boolean> isSesuaiPerItem;
        List<Integer> koreksiPerItem;
        Intrinsics.checkNotNullParameter(currentItem, "currentItem");
        if (this.idImageAdministration.getValue() == null || (isSesuaiPerItem = this.isSesuaiPerItem.getValue()) == null || (koreksiPerItem = this.koreksiPerItem.getValue()) == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(isSesuaiPerItem, "isSesuaiPerItem");
        List mutableList = CollectionsKt.toMutableList((Collection) isSesuaiPerItem);
        Intrinsics.checkNotNullExpressionValue(koreksiPerItem, "koreksiPerItem");
        List mutableList2 = CollectionsKt.toMutableList((Collection) koreksiPerItem);
        String row = currentItem.getRow();
        switch (row.hashCode()) {
            case -2070480492:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_TOTAL)) {
                    Integer type = currentItem.getType();
                    if (type != null && type.intValue() == 0) {
                        mutableList.set(12, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(12, null);
                            return;
                        }
                        return;
                    } else if (type != null && type.intValue() == 1) {
                        mutableList.set(13, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(13, null);
                            return;
                        }
                        return;
                    } else if (type != null && type.intValue() == 2) {
                        mutableList.set(14, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(14, null);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case -918385296:
                if (row.equals(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_DIGUNAKAN)) {
                    mutableList.set(17, bool);
                    if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                        mutableList2.set(17, null);
                        return;
                    }
                    return;
                }
                return;
            case -35123702:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PEMILIH_DISABILITAS)) {
                    Integer type2 = currentItem.getType();
                    if (type2 != null && type2.intValue() == 0) {
                        mutableList.set(19, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(19, null);
                            return;
                        }
                        return;
                    } else if (type2 != null && type2.intValue() == 1) {
                        mutableList.set(20, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(20, null);
                            return;
                        }
                        return;
                    } else if (type2 != null && type2.intValue() == 2) {
                        mutableList.set(21, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(21, null);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 420350717:
                if (row.equals(FormC1Administration.FORM_C1_ROW_SURAT_DITERIMA)) {
                    mutableList.set(15, bool);
                    if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                        mutableList2.set(15, null);
                        return;
                    }
                    return;
                }
                return;
            case 764018602:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPTB)) {
                    Integer type3 = currentItem.getType();
                    if (type3 != null && type3.intValue() == 0) {
                        mutableList.set(6, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(6, null);
                            return;
                        }
                        return;
                    } else if (type3 != null && type3.intValue() == 1) {
                        mutableList.set(7, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(7, null);
                            return;
                        }
                        return;
                    } else if (type3 != null && type3.intValue() == 2) {
                        mutableList.set(8, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(8, null);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 1270520387:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PEMILIH_DPT)) {
                    Integer type4 = currentItem.getType();
                    if (type4 != null && type4.intValue() == 0) {
                        mutableList.set(0, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(0, null);
                            return;
                        }
                        return;
                    } else if (type4 != null && type4.intValue() == 1) {
                        mutableList.set(1, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(1, null);
                            return;
                        }
                        return;
                    } else if (type4 != null && type4.intValue() == 2) {
                        mutableList.set(2, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(2, null);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 1492254506:
                if (row.equals(FormC1Administration.FORM_C1_ROW_SURAT_DIKEMBALIKAN)) {
                    mutableList.set(16, bool);
                    if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                        mutableList2.set(16, null);
                        return;
                    }
                    return;
                }
                return;
            case 1947456566:
                if (row.equals(FormC1Administration.FORM_C1_ROW_SURAT_DIGUNAKAN)) {
                    mutableList.set(18, bool);
                    if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                        mutableList2.set(18, null);
                        return;
                    }
                    return;
                }
                return;
            case 1964308399:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPK)) {
                    Integer type5 = currentItem.getType();
                    if (type5 != null && type5.intValue() == 0) {
                        mutableList.set(9, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(9, null);
                            return;
                        }
                        return;
                    } else if (type5 != null && type5.intValue() == 1) {
                        mutableList.set(10, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(10, null);
                            return;
                        }
                        return;
                    } else if (type5 != null && type5.intValue() == 2) {
                        mutableList.set(11, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(11, null);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 1964308408:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPT)) {
                    Integer type6 = currentItem.getType();
                    if (type6 != null && type6.intValue() == 0) {
                        mutableList.set(3, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(3, null);
                            return;
                        }
                        return;
                    } else if (type6 != null && type6.intValue() == 1) {
                        mutableList.set(4, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(4, null);
                            return;
                        }
                        return;
                    } else if (type6 != null && type6.intValue() == 2) {
                        mutableList.set(5, bool);
                        if (bool == null || Intrinsics.areEqual((Object) bool, (Object) true)) {
                            mutableList2.set(5, null);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    public final void correctItem(Integer num, FormC1CheckItem currentItem) {
        List<Integer> koreksiPerItem;
        Intrinsics.checkNotNullParameter(currentItem, "currentItem");
        if (this.idImageAdministration.getValue() == null || this.isSesuaiPerItem.getValue() == null || (koreksiPerItem = this.koreksiPerItem.getValue()) == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(koreksiPerItem, "koreksiPerItem");
        List mutableList = CollectionsKt.toMutableList((Collection) koreksiPerItem);
        String row = currentItem.getRow();
        switch (row.hashCode()) {
            case -2070480492:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_TOTAL)) {
                    Integer type = currentItem.getType();
                    if (type != null && type.intValue() == 0) {
                        mutableList.set(12, num);
                        return;
                    } else if (type != null && type.intValue() == 1) {
                        mutableList.set(13, num);
                        return;
                    } else if (type != null && type.intValue() == 2) {
                        mutableList.set(14, num);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case -918385296:
                if (row.equals(FormC1Administration.FORM_C1_ROW_SURAT_TIDAK_DIGUNAKAN)) {
                    mutableList.set(17, num);
                    return;
                }
                return;
            case -35123702:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PEMILIH_DISABILITAS)) {
                    Integer type2 = currentItem.getType();
                    if (type2 != null && type2.intValue() == 0) {
                        mutableList.set(19, num);
                        return;
                    } else if (type2 != null && type2.intValue() == 1) {
                        mutableList.set(20, num);
                        return;
                    } else if (type2 != null && type2.intValue() == 2) {
                        mutableList.set(21, num);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 420350717:
                if (row.equals(FormC1Administration.FORM_C1_ROW_SURAT_DITERIMA)) {
                    mutableList.set(15, num);
                    return;
                }
                return;
            case 764018602:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPTB)) {
                    Integer type3 = currentItem.getType();
                    if (type3 != null && type3.intValue() == 0) {
                        mutableList.set(6, num);
                        return;
                    } else if (type3 != null && type3.intValue() == 1) {
                        mutableList.set(7, num);
                        return;
                    } else if (type3 != null && type3.intValue() == 2) {
                        mutableList.set(8, num);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 1270520387:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PEMILIH_DPT)) {
                    Integer type4 = currentItem.getType();
                    if (type4 != null && type4.intValue() == 0) {
                        mutableList.set(0, num);
                        return;
                    } else if (type4 != null && type4.intValue() == 1) {
                        mutableList.set(1, num);
                        return;
                    } else if (type4 != null && type4.intValue() == 2) {
                        mutableList.set(2, num);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 1492254506:
                if (row.equals(FormC1Administration.FORM_C1_ROW_SURAT_DIKEMBALIKAN)) {
                    mutableList.set(16, num);
                    return;
                }
                return;
            case 1947456566:
                if (row.equals(FormC1Administration.FORM_C1_ROW_SURAT_DIGUNAKAN)) {
                    mutableList.set(18, num);
                    return;
                }
                return;
            case 1964308399:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPK)) {
                    Integer type5 = currentItem.getType();
                    if (type5 != null && type5.intValue() == 0) {
                        mutableList.set(9, num);
                        return;
                    } else if (type5 != null && type5.intValue() == 1) {
                        mutableList.set(10, num);
                        return;
                    } else if (type5 != null && type5.intValue() == 2) {
                        mutableList.set(11, num);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 1964308408:
                if (row.equals(FormC1Administration.FORM_C1_ROW_PENGGUNA_DPT)) {
                    Integer type6 = currentItem.getType();
                    if (type6 != null && type6.intValue() == 0) {
                        mutableList.set(3, num);
                        return;
                    } else if (type6 != null && type6.intValue() == 1) {
                        mutableList.set(4, num);
                        return;
                    } else if (type6 != null && type6.intValue() == 2) {
                        mutableList.set(5, num);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }
}
