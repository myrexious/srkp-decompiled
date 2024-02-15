package org.informatika.sirekap.ui.specialOccurrence.verify;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.SpecialOccurrencePage;
import org.informatika.sirekap.model.SpecialOccurrenceWithPages;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.specialOccurrence.CaptureSpecialOccurrenceImageUseCase;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;

/* compiled from: VerifySpecialOccurrenceViewModel.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020*J\u0006\u0010,\u001a\u00020*J\u0006\u0010-\u001a\u00020*J\u000e\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u00020&J\u0010\u00100\u001a\u00020*2\b\b\u0002\u00101\u001a\u00020 R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00120\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0014\u001a\u0010\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00160\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010!R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020 0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u001b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001dR\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020 0\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u0016\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010!¨\u00062"}, d2 = {"Lorg/informatika/sirekap/ui/specialOccurrence/verify/VerifySpecialOccurrenceViewModel;", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel;", "context", "Landroid/content/Context;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "captureImageUseCase", "Lorg/informatika/sirekap/ui/specialOccurrence/CaptureSpecialOccurrenceImageUseCase;", "getCaptureImageUseCase", "()Lorg/informatika/sirekap/ui/specialOccurrence/CaptureSpecialOccurrenceImageUseCase;", "checkItems", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/SpecialOccurrencePage;", "checkItemsResource", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/SpecialOccurrenceWithPages;", "currentIndex", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getCurrentIndex", "()Landroidx/lifecycle/MutableLiveData;", "currentItem", "Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "getCurrentItem", "()Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "firstUncheckedIndex", "isFirstCheck", "", "()Landroidx/lifecycle/LiveData;", "isFirstItem", "isLastItem", "isLoading", "kodeTps", "", "previewBitmap", "getPreviewBitmap", "backItem", "", "checkItem", "finishTakingPicture", "nextItem", "setup", "_kodeTps", "startChecking", "isJump", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifySpecialOccurrenceViewModel extends BaseVerifyViewModel {
    private final CaptureSpecialOccurrenceImageUseCase captureImageUseCase;
    private final LiveData<List<SpecialOccurrencePage>> checkItems;
    private final LiveData<Resource<SpecialOccurrenceWithPages>> checkItemsResource;
    private final MutableLiveData<Integer> currentIndex;
    private final CombinedLiveData<SpecialOccurrencePage> currentItem;
    private final DefaultElectionRepository electionRepository;
    private final LiveData<Integer> firstUncheckedIndex;
    private final LiveData<Boolean> isFirstCheck;
    private final LiveData<Boolean> isFirstItem;
    private final CombinedLiveData<Boolean> isLastItem;
    private final LiveData<Boolean> isLoading;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<String> previewBitmap;

    @Inject
    public VerifySpecialOccurrenceViewModel(@ApplicationContext Context context, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        this.electionRepository = electionRepository;
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(-1);
        this.currentIndex = mutableLiveData;
        MutableLiveData<String> mutableLiveData2 = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData2;
        this.captureImageUseCase = new CaptureSpecialOccurrenceImageUseCase(context, encryptedSharedPreferences, electionRepository);
        LiveData<Resource<SpecialOccurrenceWithPages>> switchMap = Transformations.switchMap(mutableLiveData2, new Function1<String, LiveData<Resource<SpecialOccurrenceWithPages>>>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel$checkItemsResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<SpecialOccurrenceWithPages>> invoke(String str) {
                DefaultElectionRepository defaultElectionRepository;
                if (str != null) {
                    defaultElectionRepository = VerifySpecialOccurrenceViewModel.this.electionRepository;
                    return defaultElectionRepository.getSpecialOccurrenceByKodeTps(str);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.checkItemsResource = switchMap;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<SpecialOccurrenceWithPages>, Boolean>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<SpecialOccurrenceWithPages> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        LiveData<List<SpecialOccurrencePage>> map = Transformations.map(switchMap, new Function1<Resource<SpecialOccurrenceWithPages>, List<SpecialOccurrencePage>>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel$checkItems$1
            @Override // kotlin.jvm.functions.Function1
            public final List<SpecialOccurrencePage> invoke(Resource<SpecialOccurrenceWithPages> resource) {
                SpecialOccurrenceWithPages payload;
                if (resource == null || (payload = resource.getPayload()) == null) {
                    return null;
                }
                return payload.getPages();
            }
        });
        this.checkItems = map;
        CombinedLiveData<SpecialOccurrencePage> combinedLiveData = new CombinedLiveData<>(new LiveData[]{map, mutableLiveData}, new Function1<List<? extends Object>, SpecialOccurrencePage>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel$currentItem$1
            @Override // kotlin.jvm.functions.Function1
            public final SpecialOccurrencePage invoke(List<? extends Object> data) {
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
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<org.informatika.sirekap.model.SpecialOccurrencePage>");
                List list2 = (List) obj2;
                Object obj3 = data.get(1);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                int intValue = ((Integer) obj3).intValue();
                if (intValue < 0 || intValue >= list2.size()) {
                    return null;
                }
                return (SpecialOccurrencePage) list2.get(intValue);
            }
        });
        this.currentItem = combinedLiveData;
        this.previewBitmap = Transformations.map(combinedLiveData, new Function1<SpecialOccurrencePage, String>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel$previewBitmap$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(SpecialOccurrencePage specialOccurrencePage) {
                if (specialOccurrencePage != null) {
                    return specialOccurrencePage.getCroppedPhotoPath();
                }
                return null;
            }
        });
        this.isFirstItem = Transformations.map(mutableLiveData, new Function1<Integer, Boolean>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel$isFirstItem$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Integer it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return Boolean.valueOf(it.intValue() <= 0);
            }
        });
        this.isLastItem = new CombinedLiveData<>(new LiveData[]{map, mutableLiveData}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel$isLastItem$1
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
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<org.informatika.sirekap.model.SpecialOccurrencePage>");
                Object obj3 = data.get(1);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                return Boolean.valueOf(((Integer) obj3).intValue() >= ((List) obj2).size() - 1);
            }
        });
        LiveData<Integer> map2 = Transformations.map(map, new Function1<List<SpecialOccurrencePage>, Integer>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel$firstUncheckedIndex$1
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(List<SpecialOccurrencePage> list) {
                if (list != null) {
                    Iterator<SpecialOccurrencePage> it = list.iterator();
                    int i = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i = -1;
                            break;
                        } else if (!it.next().getChecked()) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    return Integer.valueOf(i);
                }
                return null;
            }
        });
        this.firstUncheckedIndex = map2;
        this.isFirstCheck = Transformations.map(map2, new Function1<Integer, Boolean>() { // from class: org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel$isFirstCheck$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Integer num) {
                if (num != null) {
                    return Boolean.valueOf(num.intValue() <= 0);
                }
                return null;
            }
        });
    }

    public final MutableLiveData<Integer> getCurrentIndex() {
        return this.currentIndex;
    }

    public final CaptureSpecialOccurrenceImageUseCase getCaptureImageUseCase() {
        return this.captureImageUseCase;
    }

    public final void setup(String _kodeTps) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        this.kodeTps.postValue(_kodeTps);
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public static /* synthetic */ void startChecking$default(VerifySpecialOccurrenceViewModel verifySpecialOccurrenceViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        verifySpecialOccurrenceViewModel.startChecking(z);
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

    public final CombinedLiveData<SpecialOccurrencePage> getCurrentItem() {
        return this.currentItem;
    }

    public final LiveData<String> getPreviewBitmap() {
        return this.previewBitmap;
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

    public final void checkItem() {
        SpecialOccurrencePage value = this.currentItem.getValue();
        if (value != null) {
            this.electionRepository.markSpecialOccurrenceChecked(value.getId());
        }
    }

    public final void finishTakingPicture() {
        this.captureImageUseCase.finishUpdatingSpecialOccurrence();
        this.captureImageUseCase.finishInsertingSpecialOccurrence();
    }
}
