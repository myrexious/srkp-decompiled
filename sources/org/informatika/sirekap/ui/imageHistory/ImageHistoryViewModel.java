package org.informatika.sirekap.ui.imageHistory;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionImage;
import org.informatika.sirekap.support.CombinedLiveData;

/* compiled from: ImageHistoryViewModel.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002JG\u0010#\u001a\u00020$2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010%R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR%\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00050\n¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0013R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00020\u0012X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u001c\u0010\u001e\u001a\r\u0012\t\u0012\u00070\u0012¢\u0006\u0002\b\u001f0\u0011¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0013¨\u0006&"}, d2 = {"Lorg/informatika/sirekap/ui/imageHistory/ImageHistoryViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "filterImageHistory", "Landroidx/lifecycle/MutableLiveData;", "", "", "getFilterImageHistory", "()Landroidx/lifecycle/MutableLiveData;", "imageHistory", "Lorg/informatika/sirekap/support/CombinedLiveData;", "Lorg/informatika/sirekap/model/ElectionImage;", "getImageHistory$annotations", "getImageHistory", "()Lorg/informatika/sirekap/support/CombinedLiveData;", "imageHistoryRaw", "isCheckedInvalid", "Landroidx/lifecycle/LiveData;", "", "()Landroidx/lifecycle/LiveData;", "isCheckedNotSent", "isCheckedProcessed", "isCheckedScanned", "isCheckedValid", "isEmpty", "isEmptyRaw", "isLoading", "()Z", "showElectionPemilihanFilter", "getShowElectionPemilihanFilter", "showEmptyText", "Lkotlin/jvm/JvmSuppressWildcards;", "getShowEmptyText", "showScannedFilterChips", "getShowScannedFilterChips", "updateFilter", "", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ImageHistoryViewModel extends ViewModel {
    private final MutableLiveData<List<Integer>> filterImageHistory;
    private final CombinedLiveData<List<ElectionImage>> imageHistory;
    private final MutableLiveData<List<ElectionImage>> imageHistoryRaw;
    private final LiveData<Boolean> isCheckedInvalid;
    private final LiveData<Boolean> isCheckedNotSent;
    private final LiveData<Boolean> isCheckedProcessed;
    private final LiveData<Boolean> isCheckedScanned;
    private final LiveData<Boolean> isCheckedValid;
    private final LiveData<Boolean> isEmpty;
    private final LiveData<Boolean> isEmptyRaw;
    private final boolean isLoading;
    private final LiveData<Boolean> showElectionPemilihanFilter;
    private final LiveData<Boolean> showEmptyText;
    private final LiveData<Boolean> showScannedFilterChips;

    public static /* synthetic */ void getImageHistory$annotations() {
    }

    @Inject
    public ImageHistoryViewModel() {
        MutableLiveData<List<Integer>> mutableLiveData = new MutableLiveData<>(CollectionsKt.listOf(1));
        this.filterImageHistory = mutableLiveData;
        MutableLiveData<List<ElectionImage>> mutableLiveData2 = new MutableLiveData<>(CollectionsKt.listOf((Object[]) new ElectionImage[]{new ElectionImage("1", "Gambar 1", 1, null, null, null, 56, null), new ElectionImage(ExifInterface.GPS_MEASUREMENT_2D, "Gambar 2", 2, null, null, null, 56, null), new ElectionImage(ExifInterface.GPS_MEASUREMENT_3D, "Gambar 3", 3, Election.ELECTION_PEMILIHAN_PRESIDEN, 1, null, 32, null), new ElectionImage("4", "Gambar 4", 4, null, null, "Gambar Terpotong", 24, null)}));
        this.imageHistoryRaw = mutableLiveData2;
        LiveData<Boolean> map = Transformations.map(mutableLiveData2, new Function1<List<ElectionImage>, Boolean>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$isEmptyRaw$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<ElectionImage> list) {
                List<ElectionImage> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.isEmptyRaw = map;
        CombinedLiveData<List<ElectionImage>> combinedLiveData = new CombinedLiveData<>(new LiveData[]{mutableLiveData2, mutableLiveData}, new Function1<List<? extends Object>, List<? extends ElectionImage>>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$imageHistory$1
            @Override // kotlin.jvm.functions.Function1
            public final List<ElectionImage> invoke(List<? extends Object> data) {
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
                List list2 = (List) data.get(0);
                List list3 = (List) data.get(1);
                if (list2 != null) {
                    ArrayList arrayList = new ArrayList();
                    for (Object obj2 : list2) {
                        if (list3 != null ? list3.contains(Integer.valueOf(((ElectionImage) obj2).getStatus())) : false) {
                            arrayList.add(obj2);
                        }
                    }
                    return arrayList;
                }
                return null;
            }
        });
        this.imageHistory = combinedLiveData;
        this.isEmpty = Transformations.map(combinedLiveData, new Function1<List<ElectionImage>, Boolean>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$isEmpty$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<ElectionImage> list) {
                List<ElectionImage> list2 = list;
                return Boolean.valueOf(list2 == null || list2.isEmpty());
            }
        });
        this.showEmptyText = Transformations.map(map, new Function1<Boolean, Boolean>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$showEmptyText$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                return invoke(bool.booleanValue());
            }

            public final Boolean invoke(boolean z) {
                return Boolean.valueOf(z);
            }
        });
        this.isCheckedNotSent = Transformations.map(mutableLiveData, new Function1<List<Integer>, Boolean>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$isCheckedNotSent$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<Integer> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(1));
                }
                return null;
            }
        });
        this.isCheckedProcessed = Transformations.map(mutableLiveData, new Function1<List<Integer>, Boolean>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$isCheckedProcessed$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<Integer> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(2));
                }
                return null;
            }
        });
        LiveData<Boolean> map2 = Transformations.map(mutableLiveData, new Function1<List<Integer>, Boolean>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$isCheckedScanned$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<Integer> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(5));
                }
                return null;
            }
        });
        this.isCheckedScanned = map2;
        LiveData<Boolean> map3 = Transformations.map(mutableLiveData, new Function1<List<Integer>, Boolean>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$isCheckedValid$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<Integer> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(3));
                }
                return null;
            }
        });
        this.isCheckedValid = map3;
        this.isCheckedInvalid = Transformations.map(mutableLiveData, new Function1<List<Integer>, Boolean>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$isCheckedInvalid$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<Integer> list) {
                if (list != null) {
                    return Boolean.valueOf(list.contains(4));
                }
                return null;
            }
        });
        this.showScannedFilterChips = Transformations.map(map2, new Function1<Boolean, Boolean>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$showScannedFilterChips$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Boolean bool) {
                return Boolean.valueOf(Intrinsics.areEqual((Object) bool, (Object) true));
            }
        });
        this.showElectionPemilihanFilter = Transformations.map(map3, new Function1<Boolean, Boolean>() { // from class: org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel$showElectionPemilihanFilter$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Boolean bool) {
                return Boolean.valueOf(Intrinsics.areEqual((Object) bool, (Object) true));
            }
        });
    }

    public final MutableLiveData<List<Integer>> getFilterImageHistory() {
        return this.filterImageHistory;
    }

    public final CombinedLiveData<List<ElectionImage>> getImageHistory() {
        return this.imageHistory;
    }

    public final LiveData<Boolean> isEmpty() {
        return this.isEmpty;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public final LiveData<Boolean> getShowEmptyText() {
        return this.showEmptyText;
    }

    public final LiveData<Boolean> isCheckedNotSent() {
        return this.isCheckedNotSent;
    }

    public final LiveData<Boolean> isCheckedProcessed() {
        return this.isCheckedProcessed;
    }

    public final LiveData<Boolean> isCheckedScanned() {
        return this.isCheckedScanned;
    }

    public final LiveData<Boolean> isCheckedValid() {
        return this.isCheckedValid;
    }

    public final LiveData<Boolean> isCheckedInvalid() {
        return this.isCheckedInvalid;
    }

    public final LiveData<Boolean> getShowScannedFilterChips() {
        return this.showScannedFilterChips;
    }

    public final LiveData<Boolean> getShowElectionPemilihanFilter() {
        return this.showElectionPemilihanFilter;
    }

    public static /* synthetic */ void updateFilter$default(ImageHistoryViewModel imageHistoryViewModel, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = null;
        }
        if ((i & 2) != 0) {
            bool2 = null;
        }
        if ((i & 4) != 0) {
            bool3 = null;
        }
        if ((i & 8) != 0) {
            bool4 = null;
        }
        if ((i & 16) != 0) {
            bool5 = null;
        }
        imageHistoryViewModel.updateFilter(bool, bool2, bool3, bool4, bool5);
    }

    public final void updateFilter(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5) {
        List<Integer> value = this.filterImageHistory.getValue();
        if (value != null) {
            List<Integer> mutableList = CollectionsKt.toMutableList((Collection) value);
            if (bool != null) {
                if (bool.booleanValue()) {
                    mutableList.add(1);
                } else {
                    mutableList.removeAll(CollectionsKt.listOf(1));
                }
            }
            if (bool2 != null) {
                if (bool2.booleanValue()) {
                    mutableList.add(2);
                } else {
                    mutableList.removeAll(CollectionsKt.listOf(2));
                }
            }
            if (bool3 != null) {
                if (bool3.booleanValue()) {
                    mutableList.add(5);
                    mutableList.add(3);
                    mutableList.add(4);
                } else {
                    mutableList.remove((Object) 5);
                    mutableList.removeAll(CollectionsKt.listOf(3));
                    mutableList.removeAll(CollectionsKt.listOf(4));
                }
            }
            if (bool4 != null) {
                if (bool4.booleanValue()) {
                    mutableList.add(3);
                } else {
                    mutableList.removeAll(CollectionsKt.listOf(3));
                }
            }
            if (bool5 != null) {
                if (bool5.booleanValue()) {
                    mutableList.add(4);
                } else {
                    mutableList.removeAll(CollectionsKt.listOf(4));
                }
            }
            this.filterImageHistory.postValue(mutableList);
        }
    }
}
