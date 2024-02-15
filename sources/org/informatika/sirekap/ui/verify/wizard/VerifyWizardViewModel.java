package org.informatika.sirekap.ui.verify.wizard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Candidate;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.support.vision.VisionKesesuaianUtil;
import org.informatika.sirekap.support.vision.VisionUtil;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;

/* compiled from: VerifyWizardViewModel.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B#\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010?\u001a\u00020@J'\u0010A\u001a\u00020@2\b\u0010B\u001a\u0004\u0018\u00010\"2\u0010\b\u0002\u0010C\u001a\n\u0012\u0004\u0012\u00020@\u0018\u00010D¢\u0006\u0002\u0010EJ'\u0010F\u001a\u00020@2\b\u0010G\u001a\u0004\u0018\u00010\u00132\u0010\b\u0002\u0010C\u001a\n\u0012\u0004\u0012\u00020@\u0018\u00010D¢\u0006\u0002\u0010HJ\u0006\u0010I\u001a\u00020@J&\u0010J\u001a\u00020@2\u0006\u0010K\u001a\u00020\u00132\u0006\u0010L\u001a\u00020\u00132\u0006\u0010M\u001a\u00020\u00102\u0006\u0010N\u001a\u00020\u0010J\u0010\u0010O\u001a\u00020@2\b\b\u0002\u0010P\u001a\u00020\"R\"\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b0\nX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\u00130\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001f\u0010!\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\"0\"0\u0012¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u001c¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0\u001c¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0019\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0\u001c¢\u0006\b\n\u0000\u001a\u0004\b'\u0010$R'\u0010(\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\"\u0018\u00010\u000b0\n¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u000e\u001a\u0004\b(\u0010\u0019R\u0016\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0\u001c¢\u0006\b\n\u0000\u001a\u0004\b.\u0010$R\u0014\u0010/\u001a\u000200X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u000204X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u000208X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0014\u0010;\u001a\u00020<X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>¨\u0006Q"}, d2 = {"Lorg/informatika/sirekap/ui/verify/wizard/VerifyWizardViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "formC1Repository", "Lorg/informatika/sirekap/repository/FormC1Repository;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/FormC1Repository;Lorg/informatika/sirekap/repository/DefaultElectionRepository;)V", "checkItems", "Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "", "Lorg/informatika/sirekap/ui/verify/wizard/FormC1CheckItem;", "getCheckItems$annotations", "()V", "correctedPhotoPath", "", "currentIndex", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getCurrentIndex", "()Landroidx/lifecycle/MutableLiveData;", "currentItem", "getCurrentItem", "()Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "electionPageKind", "firstUncheckedIndex", "Landroidx/lifecycle/LiveData;", "getElectionPageUseCase", "Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "getGetElectionPageUseCase", "()Lorg/informatika/sirekap/ui/sendImage/GetElectionPageUseCase;", "isCorrectValue", "", "isFirstCheck", "()Landroidx/lifecycle/LiveData;", "isFirstItem", "isLastItem", "isLoading", "isSesuaiPerItem", "isSesuaiPerItem$annotations", "jenisPemilihan", "kodeTps", "previewBitmap", "Landroid/graphics/Bitmap;", "getPreviewBitmap", "verifyFormC1AdministrationHal2UseCase", "Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1AdministrationHal2UseCase;", "getVerifyFormC1AdministrationHal2UseCase$app_productionRelease", "()Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1AdministrationHal2UseCase;", "verifyFormC1AdministrationUseCase", "Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1AdministrationUseCase;", "getVerifyFormC1AdministrationUseCase$app_productionRelease", "()Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1AdministrationUseCase;", "verifyFormC1TabulationPartaiUseCase", "Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1TabulationPartaiUseCase;", "getVerifyFormC1TabulationPartaiUseCase$app_productionRelease", "()Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1TabulationPartaiUseCase;", "verifyFormC1TabulationUseCase", "Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1TabulationUseCase;", "getVerifyFormC1TabulationUseCase$app_productionRelease", "()Lorg/informatika/sirekap/ui/verify/wizard/VerifyFormC1TabulationUseCase;", "backItem", "", "checkItem", "newChecked", "onFinish", "Lkotlin/Function0;", "(Ljava/lang/Boolean;Lkotlin/jvm/functions/Function0;)V", "correctItem", "correctedValue", "(Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)V", "nextItem", "setup", "_idImage", "_electionPageKind", "_jenisPemilihan", "_kodeTps", "startChecking", "isJump", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyWizardViewModel extends ViewModel {
    private final CombinedLiveData<List<FormC1CheckItem>> checkItems;
    private final CombinedLiveData<String> correctedPhotoPath;
    private final MutableLiveData<Integer> currentIndex;
    private final CombinedLiveData<FormC1CheckItem> currentItem;
    private final MutableLiveData<Integer> electionPageKind;
    private final LiveData<Integer> firstUncheckedIndex;
    private final FormC1Repository formC1Repository;
    private final GetElectionPageUseCase getElectionPageUseCase;
    private final MutableLiveData<Boolean> isCorrectValue;
    private final LiveData<Boolean> isFirstCheck;
    private final LiveData<Boolean> isFirstItem;
    private final CombinedLiveData<Boolean> isLastItem;
    private final LiveData<Boolean> isLoading;
    private final CombinedLiveData<List<Boolean>> isSesuaiPerItem;
    private final MutableLiveData<String> jenisPemilihan;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<Bitmap> previewBitmap;
    private final VerifyFormC1AdministrationHal2UseCase verifyFormC1AdministrationHal2UseCase;
    private final VerifyFormC1AdministrationUseCase verifyFormC1AdministrationUseCase;
    private final VerifyFormC1TabulationPartaiUseCase verifyFormC1TabulationPartaiUseCase;
    private final VerifyFormC1TabulationUseCase verifyFormC1TabulationUseCase;

    private static /* synthetic */ void getCheckItems$annotations() {
    }

    public static /* synthetic */ void isSesuaiPerItem$annotations() {
    }

    public final void setup(int i, int i2, String _jenisPemilihan, String _kodeTps) {
        Intrinsics.checkNotNullParameter(_jenisPemilihan, "_jenisPemilihan");
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
    }

    @Inject
    public VerifyWizardViewModel(@ApplicationContext Context context, FormC1Repository formC1Repository, DefaultElectionRepository electionRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(formC1Repository, "formC1Repository");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        this.formC1Repository = formC1Repository;
        DefaultElectionRepository defaultElectionRepository = electionRepository;
        this.getElectionPageUseCase = new GetElectionPageUseCase(defaultElectionRepository);
        VerifyFormC1AdministrationUseCase verifyFormC1AdministrationUseCase = new VerifyFormC1AdministrationUseCase(context, formC1Repository);
        this.verifyFormC1AdministrationUseCase = verifyFormC1AdministrationUseCase;
        VerifyFormC1TabulationUseCase verifyFormC1TabulationUseCase = new VerifyFormC1TabulationUseCase(context, formC1Repository, defaultElectionRepository);
        this.verifyFormC1TabulationUseCase = verifyFormC1TabulationUseCase;
        VerifyFormC1TabulationPartaiUseCase verifyFormC1TabulationPartaiUseCase = new VerifyFormC1TabulationPartaiUseCase(context, formC1Repository, defaultElectionRepository);
        this.verifyFormC1TabulationPartaiUseCase = verifyFormC1TabulationPartaiUseCase;
        VerifyFormC1AdministrationHal2UseCase verifyFormC1AdministrationHal2UseCase = new VerifyFormC1AdministrationHal2UseCase(context, formC1Repository);
        this.verifyFormC1AdministrationHal2UseCase = verifyFormC1AdministrationHal2UseCase;
        this.electionPageKind = new MutableLiveData<>(null);
        this.jenisPemilihan = new MutableLiveData<>(null);
        this.kodeTps = new MutableLiveData<>(null);
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(-1);
        this.currentIndex = mutableLiveData;
        this.isCorrectValue = new MutableLiveData<>(false);
        this.isSesuaiPerItem = new CombinedLiveData<>(new LiveData[]{verifyFormC1AdministrationUseCase.isSesuaiPerItem(), verifyFormC1TabulationUseCase.isSesuaiPerItem(), verifyFormC1TabulationPartaiUseCase.isSesuaiPerItem(), verifyFormC1AdministrationHal2UseCase.isSesuaiPerItem()}, new Function1<List<? extends Object>, List<? extends Boolean>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel$isSesuaiPerItem$1
            @Override // kotlin.jvm.functions.Function1
            public final List<Boolean> invoke(List<? extends Object> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                Object obj = data.get(0);
                List<Boolean> list = obj instanceof List ? (List) obj : null;
                Object obj2 = data.get(1);
                List<Boolean> list2 = obj2 instanceof List ? (List) obj2 : null;
                Object obj3 = data.get(2);
                List<Boolean> list3 = obj3 instanceof List ? (List) obj3 : null;
                Object obj4 = data.get(3);
                List<Boolean> list4 = obj4 instanceof List ? (List) obj4 : null;
                if (list == null) {
                    if (list2 == null) {
                        if (list3 == null) {
                            if (list4 == null) {
                                return null;
                            }
                            return list4;
                        }
                        return list3;
                    }
                    return list2;
                }
                return list;
            }
        });
        this.isLoading = new CombinedLiveData(new LiveData[]{verifyFormC1AdministrationUseCase.isLoading(), verifyFormC1TabulationUseCase.isLoading(), verifyFormC1TabulationPartaiUseCase.isLoading(), verifyFormC1AdministrationHal2UseCase.isLoading()}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<? extends Object> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                boolean z = false;
                Object obj = data.get(0);
                Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
                Object obj2 = data.get(1);
                Boolean bool2 = obj2 instanceof Boolean ? (Boolean) obj2 : null;
                Object obj3 = data.get(2);
                Boolean bool3 = obj3 instanceof Boolean ? (Boolean) obj3 : null;
                Object obj4 = data.get(3);
                Boolean bool4 = obj4 instanceof Boolean ? (Boolean) obj4 : null;
                if (bool != null) {
                    z = bool.booleanValue();
                } else if (bool2 != null) {
                    z = bool2.booleanValue();
                } else if (bool3 != null) {
                    z = bool3.booleanValue();
                } else if (bool4 != null) {
                    z = bool4.booleanValue();
                }
                return Boolean.valueOf(z);
            }
        });
        CombinedLiveData<List<FormC1CheckItem>> combinedLiveData = new CombinedLiveData<>(new LiveData[]{verifyFormC1AdministrationUseCase.getCheckItems(), verifyFormC1TabulationUseCase.getCheckItems(), verifyFormC1TabulationPartaiUseCase.getCheckItems(), verifyFormC1AdministrationHal2UseCase.getCheckItems()}, new Function1<List<? extends Object>, List<? extends FormC1CheckItem>>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel$checkItems$1
            @Override // kotlin.jvm.functions.Function1
            public final List<FormC1CheckItem> invoke(List<? extends Object> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                Object obj = data.get(0);
                List<FormC1CheckItem> list = obj instanceof List ? (List) obj : null;
                Object obj2 = data.get(1);
                List<FormC1CheckItem> list2 = obj2 instanceof List ? (List) obj2 : null;
                Object obj3 = data.get(2);
                List<FormC1CheckItem> list3 = obj3 instanceof List ? (List) obj3 : null;
                Object obj4 = data.get(3);
                List<FormC1CheckItem> list4 = obj4 instanceof List ? (List) obj4 : null;
                if (list == null) {
                    if (list2 == null) {
                        if (list3 == null) {
                            if (list4 == null) {
                                return null;
                            }
                            return list4;
                        }
                        return list3;
                    }
                    return list2;
                }
                return list;
            }
        });
        this.checkItems = combinedLiveData;
        CombinedLiveData<String> combinedLiveData2 = new CombinedLiveData<>(new LiveData[]{verifyFormC1AdministrationUseCase.getCorrectedPhotoPath(), verifyFormC1TabulationUseCase.getCorrectedPhotoPath(), verifyFormC1TabulationPartaiUseCase.getCorrectedPhotoPath(), verifyFormC1AdministrationHal2UseCase.getCorrectedPhotoPath()}, new Function1<List<? extends Object>, String>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel$correctedPhotoPath$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(List<? extends Object> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                Object obj = data.get(0);
                String str = obj instanceof String ? (String) obj : null;
                Object obj2 = data.get(1);
                String str2 = obj2 instanceof String ? (String) obj2 : null;
                Object obj3 = data.get(2);
                String str3 = obj3 instanceof String ? (String) obj3 : null;
                Object obj4 = data.get(3);
                String str4 = obj4 instanceof String ? (String) obj4 : null;
                if (str == null) {
                    if (str2 == null) {
                        if (str3 == null) {
                            if (str4 == null) {
                                return null;
                            }
                            return str4;
                        }
                        return str3;
                    }
                    return str2;
                }
                return str;
            }
        });
        this.correctedPhotoPath = combinedLiveData2;
        CombinedLiveData<FormC1CheckItem> combinedLiveData3 = new CombinedLiveData<>(new LiveData[]{combinedLiveData, mutableLiveData}, new Function1<List<? extends Object>, FormC1CheckItem>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel$currentItem$1
            @Override // kotlin.jvm.functions.Function1
            public final FormC1CheckItem invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        if (obj == null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (z2) {
                    return null;
                }
                Object obj2 = data.get(0);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem>");
                List list2 = (List) obj2;
                Object obj3 = data.get(1);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                int intValue = ((Integer) obj3).intValue();
                if (intValue < 0 || intValue >= list2.size()) {
                    return null;
                }
                return (FormC1CheckItem) list2.get(intValue);
            }
        });
        this.currentItem = combinedLiveData3;
        this.isFirstItem = Transformations.map(mutableLiveData, new Function1<Integer, Boolean>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel$isFirstItem$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Integer it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return Boolean.valueOf(it.intValue() <= 0);
            }
        });
        this.isLastItem = new CombinedLiveData<>(new LiveData[]{combinedLiveData, mutableLiveData}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel$isLastItem$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        if (obj == null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (z2) {
                    return null;
                }
                Object obj2 = data.get(0);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem>");
                Object obj3 = data.get(1);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                return Boolean.valueOf(((Integer) obj3).intValue() >= ((List) obj2).size() - 1);
            }
        });
        LiveData<Integer> map = Transformations.map(combinedLiveData, new Function1<List<FormC1CheckItem>, Integer>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel$firstUncheckedIndex$1
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(List<FormC1CheckItem> list) {
                if (list != null) {
                    Iterator<FormC1CheckItem> it = list.iterator();
                    int i = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i = -1;
                            break;
                        }
                        if (it.next().getChecked() == null) {
                            break;
                        }
                        i++;
                    }
                    return Integer.valueOf(i);
                }
                return null;
            }
        });
        this.firstUncheckedIndex = map;
        this.isFirstCheck = Transformations.map(map, new Function1<Integer, Boolean>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel$isFirstCheck$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Integer num) {
                if (num != null) {
                    return Boolean.valueOf(num.intValue() <= 0);
                }
                return null;
            }
        });
        this.previewBitmap = new CombinedLiveData(new LiveData[]{combinedLiveData2, combinedLiveData3}, new Function1<List<? extends Object>, Bitmap>() { // from class: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel$previewBitmap$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Bitmap invoke(List<? extends Object> data) {
                boolean z;
                boolean z2;
                MutableLiveData mutableLiveData2;
                Bitmap decodeFile;
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    for (Object obj : list) {
                        if (obj == null) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            z2 = true;
                            break;
                        }
                    }
                }
                z2 = false;
                if (z2) {
                    return null;
                }
                String str = (String) data.get(0);
                FormC1CheckItem formC1CheckItem = (FormC1CheckItem) data.get(1);
                mutableLiveData2 = VerifyWizardViewModel.this.electionPageKind;
                Integer num = (Integer) mutableLiveData2.getValue();
                if (num != null) {
                    VerifyWizardViewModel verifyWizardViewModel = VerifyWizardViewModel.this;
                    if (str == null || formC1CheckItem == null || (decodeFile = BitmapFactory.decodeFile(str)) == null) {
                        return null;
                    }
                    Intrinsics.checkNotNullExpressionValue(decodeFile, "decodeFile(correctedPhotoPath)");
                    VisionKesesuaianUtil visionKesesuaianUtil = VisionKesesuaianUtil.INSTANCE;
                    String str2 = num.intValue() == 10 ? VisionUtil.VISION_UTIL_TYPE_ADMIN : VisionUtil.VISION_UTIL_TYPE_TABULASI;
                    String row = formC1CheckItem.getRow();
                    Integer index = formC1CheckItem.getIndex();
                    List<Candidate> value = verifyWizardViewModel.getVerifyFormC1TabulationUseCase$app_productionRelease().getCandidates$app_productionRelease().getValue();
                    int size = value != null ? value.size() : 0;
                    Intrinsics.checkNotNullExpressionValue(str2, "if (electionPageKind == …VISION_UTIL_TYPE_TABULASI");
                    return VisionKesesuaianUtil.getKesesuaianSlicedMat$default(visionKesesuaianUtil, str2, row, decodeFile, null, size, index, 8, null);
                }
                return null;
            }
        });
    }

    public final GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.getElectionPageUseCase;
    }

    public final VerifyFormC1AdministrationUseCase getVerifyFormC1AdministrationUseCase$app_productionRelease() {
        return this.verifyFormC1AdministrationUseCase;
    }

    public final VerifyFormC1TabulationUseCase getVerifyFormC1TabulationUseCase$app_productionRelease() {
        return this.verifyFormC1TabulationUseCase;
    }

    public final VerifyFormC1TabulationPartaiUseCase getVerifyFormC1TabulationPartaiUseCase$app_productionRelease() {
        return this.verifyFormC1TabulationPartaiUseCase;
    }

    public final VerifyFormC1AdministrationHal2UseCase getVerifyFormC1AdministrationHal2UseCase$app_productionRelease() {
        return this.verifyFormC1AdministrationHal2UseCase;
    }

    public final MutableLiveData<Integer> getCurrentIndex() {
        return this.currentIndex;
    }

    public final MutableLiveData<Boolean> isCorrectValue() {
        return this.isCorrectValue;
    }

    public static /* synthetic */ void startChecking$default(VerifyWizardViewModel verifyWizardViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        verifyWizardViewModel.startChecking(z);
    }

    public final void startChecking(boolean z) {
        if (z) {
            Integer value = this.firstUncheckedIndex.getValue();
            if (value != null) {
                this.currentIndex.postValue(value);
                return;
            }
            return;
        }
        this.currentIndex.postValue(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void checkItem$default(VerifyWizardViewModel verifyWizardViewModel, Boolean bool, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        verifyWizardViewModel.checkItem(bool, function0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x0053, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_PRESIDEN) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x005c, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPR) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0065, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPD) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0068, code lost:
        r1 = r4.verifyFormC1TabulationUseCase;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "currentItem");
        r1.checkItem(r5, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0077, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0080, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0083, code lost:
        r1 = r4.verifyFormC1TabulationPartaiUseCase;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "currentItem");
        r1.checkItem(r5, r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void checkItem(java.lang.Boolean r5, kotlin.jvm.functions.Function0<kotlin.Unit> r6) {
        /*
            r4 = this;
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r0 = r4.electionPageKind
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L9e
            androidx.lifecycle.MutableLiveData<java.lang.String> r1 = r4.jenisPemilihan
            java.lang.Object r1 = r1.getValue()
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L9e
            int r0 = r0.intValue()
            r2 = 10
            java.lang.String r3 = "currentItem"
            if (r0 == r2) goto L8c
            r2 = 20
            if (r0 == r2) goto L3b
            r1 = 30
            if (r0 == r1) goto L28
            goto L9e
        L28:
            org.informatika.sirekap.support.livedata.CombinedLiveData<org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem> r0 = r4.currentItem
            java.lang.Object r0 = r0.getValue()
            org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem r0 = (org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem) r0
            if (r0 == 0) goto L9e
            org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationHal2UseCase r1 = r4.verifyFormC1AdministrationHal2UseCase
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r1.checkItem(r5, r0)
            goto L9e
        L3b:
            org.informatika.sirekap.support.livedata.CombinedLiveData<org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem> r0 = r4.currentItem
            java.lang.Object r0 = r0.getValue()
            org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem r0 = (org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem) r0
            if (r0 == 0) goto L9e
            int r2 = r1.hashCode()
            switch(r2) {
                case -992700931: goto L7a;
                case -992700926: goto L71;
                case 3436264: goto L5f;
                case 3436278: goto L56;
                case 3448025: goto L4d;
                default: goto L4c;
            }
        L4c:
            goto L9e
        L4d:
            java.lang.String r2 = "ppwp"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L9e
            goto L68
        L56:
            java.lang.String r2 = "pdpr"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L83
            goto L9e
        L5f:
            java.lang.String r2 = "pdpd"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L68
            goto L9e
        L68:
            org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationUseCase r1 = r4.verifyFormC1TabulationUseCase
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r1.checkItem(r5, r0)
            goto L9e
        L71:
            java.lang.String r2 = "pdprdp"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L83
            goto L9e
        L7a:
            java.lang.String r2 = "pdprdk"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L83
            goto L9e
        L83:
            org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase r1 = r4.verifyFormC1TabulationPartaiUseCase
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r1.checkItem(r5, r0)
            goto L9e
        L8c:
            org.informatika.sirekap.support.livedata.CombinedLiveData<org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem> r0 = r4.currentItem
            java.lang.Object r0 = r0.getValue()
            org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem r0 = (org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem) r0
            if (r0 == 0) goto L9e
            org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationUseCase r1 = r4.verifyFormC1AdministrationUseCase
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r1.checkItem(r5, r0)
        L9e:
            if (r6 == 0) goto La3
            r6.invoke()
        La3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel.checkItem(java.lang.Boolean, kotlin.jvm.functions.Function0):void");
    }

    public final void nextItem() {
        Integer value = this.currentIndex.getValue();
        if (value != null) {
            this.currentIndex.postValue(Integer.valueOf(value.intValue() + 1));
        }
    }

    public final void backItem() {
        Integer value = this.currentIndex.getValue();
        if (value != null) {
            this.currentIndex.postValue(Integer.valueOf(value.intValue() - 1));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void correctItem$default(VerifyWizardViewModel verifyWizardViewModel, Integer num, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        verifyWizardViewModel.correctItem(num, function0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x004a, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_PRESIDEN) != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0053, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPR) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x005c, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPD) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x005f, code lost:
        r0 = r4.currentItem.getValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0067, code lost:
        if (r0 == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0069, code lost:
        r1 = r4.verifyFormC1TabulationUseCase;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "currentItem");
        r1.correctItem(r5, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0078, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_PROVINSI) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0081, code lost:
        if (r1.equals(org.informatika.sirekap.model.Election.ELECTION_PEMILIHAN_DPRD_KABKO) == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0084, code lost:
        r0 = r4.currentItem.getValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x008c, code lost:
        if (r0 == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x008e, code lost:
        r1 = r4.verifyFormC1TabulationPartaiUseCase;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "currentItem");
        r1.correctItem(r5, r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void correctItem(java.lang.Integer r5, kotlin.jvm.functions.Function0<kotlin.Unit> r6) {
        /*
            r4 = this;
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r0 = r4.electionPageKind
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto La9
            androidx.lifecycle.MutableLiveData<java.lang.String> r1 = r4.jenisPemilihan
            java.lang.Object r1 = r1.getValue()
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto La9
            int r0 = r0.intValue()
            r2 = 10
            java.lang.String r3 = "currentItem"
            if (r0 == r2) goto L97
            r2 = 20
            if (r0 == r2) goto L3c
            r1 = 30
            if (r0 == r1) goto L28
            goto La9
        L28:
            org.informatika.sirekap.support.livedata.CombinedLiveData<org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem> r0 = r4.currentItem
            java.lang.Object r0 = r0.getValue()
            org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem r0 = (org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem) r0
            if (r0 == 0) goto La9
            org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationHal2UseCase r1 = r4.verifyFormC1AdministrationHal2UseCase
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r1.correctItem(r5, r0)
            goto La9
        L3c:
            int r0 = r1.hashCode()
            switch(r0) {
                case -992700931: goto L7b;
                case -992700926: goto L72;
                case 3436264: goto L56;
                case 3436278: goto L4d;
                case 3448025: goto L44;
                default: goto L43;
            }
        L43:
            goto La9
        L44:
            java.lang.String r0 = "ppwp"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto La9
            goto L5f
        L4d:
            java.lang.String r0 = "pdpr"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L84
            goto La9
        L56:
            java.lang.String r0 = "pdpd"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L5f
            goto La9
        L5f:
            org.informatika.sirekap.support.livedata.CombinedLiveData<org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem> r0 = r4.currentItem
            java.lang.Object r0 = r0.getValue()
            org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem r0 = (org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem) r0
            if (r0 == 0) goto La9
            org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationUseCase r1 = r4.verifyFormC1TabulationUseCase
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r1.correctItem(r5, r0)
            goto La9
        L72:
            java.lang.String r0 = "pdprdp"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L84
            goto La9
        L7b:
            java.lang.String r0 = "pdprdk"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L84
            goto La9
        L84:
            org.informatika.sirekap.support.livedata.CombinedLiveData<org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem> r0 = r4.currentItem
            java.lang.Object r0 = r0.getValue()
            org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem r0 = (org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem) r0
            if (r0 == 0) goto La9
            org.informatika.sirekap.ui.verify.wizard.VerifyFormC1TabulationPartaiUseCase r1 = r4.verifyFormC1TabulationPartaiUseCase
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r1.correctItem(r5, r0)
            goto La9
        L97:
            org.informatika.sirekap.support.livedata.CombinedLiveData<org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem> r0 = r4.currentItem
            java.lang.Object r0 = r0.getValue()
            org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem r0 = (org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem) r0
            if (r0 == 0) goto La9
            org.informatika.sirekap.ui.verify.wizard.VerifyFormC1AdministrationUseCase r1 = r4.verifyFormC1AdministrationUseCase
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            r1.correctItem(r5, r0)
        La9:
            if (r6 == 0) goto Lae
            r6.invoke()
        Lae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel.correctItem(java.lang.Integer, kotlin.jvm.functions.Function0):void");
    }

    public final CombinedLiveData<List<Boolean>> isSesuaiPerItem() {
        return this.isSesuaiPerItem;
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final CombinedLiveData<FormC1CheckItem> getCurrentItem() {
        return this.currentItem;
    }

    public final LiveData<Boolean> isFirstItem() {
        return this.isFirstItem;
    }

    public final CombinedLiveData<Boolean> isLastItem() {
        return this.isLastItem;
    }

    public final LiveData<Boolean> isFirstCheck() {
        return this.isFirstCheck;
    }

    public final LiveData<Bitmap> getPreviewBitmap() {
        return this.previewBitmap;
    }
}
