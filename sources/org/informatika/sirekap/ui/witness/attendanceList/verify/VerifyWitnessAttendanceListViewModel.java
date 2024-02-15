package org.informatika.sirekap.ui.witness.attendanceList.verify;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.model.AttendancePage;
import org.informatika.sirekap.model.AttendanceWithPages;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.support.livedata.AbsentLiveData;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.ui.witness.attendanceList.CaptureAttendanceImageUseCase;

/* compiled from: VerifyWitnessAttendanceListViewModel.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020.J\u0006\u00100\u001a\u00020.J\u0006\u00101\u001a\u00020.J\u000e\u00102\u001a\u00020.2\u0006\u00103\u001a\u00020(J\u0010\u00104\u001a\u00020.2\b\b\u0002\u00105\u001a\u00020\"R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00140\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0016\u001a\u0010\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00180\u00180\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010#R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\u0010¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0019\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u001d¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\"0\u0010¢\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u0016\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0\u0010¢\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u0019\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0\u0010¢\u0006\b\n\u0000\u001a\u0004\b,\u0010#R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/verify/VerifyWitnessAttendanceListViewModel;", "Lorg/informatika/sirekap/ui/verify/BaseVerifyViewModel;", "context", "Landroid/content/Context;", "electionRepository", "Lorg/informatika/sirekap/repository/DefaultElectionRepository;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "witnessRepository", "Lorg/informatika/sirekap/repository/DefaultWitnessRepository;", "(Landroid/content/Context;Lorg/informatika/sirekap/repository/DefaultElectionRepository;Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;Lorg/informatika/sirekap/repository/DefaultWitnessRepository;)V", "captureImageUseCase", "Lorg/informatika/sirekap/ui/witness/attendanceList/CaptureAttendanceImageUseCase;", "getCaptureImageUseCase", "()Lorg/informatika/sirekap/ui/witness/attendanceList/CaptureAttendanceImageUseCase;", "checkItems", "Landroidx/lifecycle/LiveData;", "", "Lorg/informatika/sirekap/model/AttendancePage;", "checkItemsResource", "Lorg/informatika/sirekap/support/Resource;", "Lorg/informatika/sirekap/model/AttendanceWithPages;", "currentIndex", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getCurrentIndex", "()Landroidx/lifecycle/MutableLiveData;", "currentItem", "Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "getCurrentItem", "()Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "firstUncheckedIndex", "isFirstCheck", "", "()Landroidx/lifecycle/LiveData;", "isFirstItem", "isLastItem", "isLoading", "kodeTps", "", "nomorHalaman", "getNomorHalaman", "previewBitmap", "getPreviewBitmap", "backItem", "", "checkItem", "finishTakingPicture", "nextItem", "setup", "_kodeTps", "startChecking", "isJump", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyWitnessAttendanceListViewModel extends BaseVerifyViewModel {
    private final CaptureAttendanceImageUseCase captureImageUseCase;
    private final LiveData<List<AttendancePage>> checkItems;
    private final LiveData<Resource<AttendanceWithPages>> checkItemsResource;
    private final MutableLiveData<Integer> currentIndex;
    private final CombinedLiveData<AttendancePage> currentItem;
    private final LiveData<Integer> firstUncheckedIndex;
    private final LiveData<Boolean> isFirstCheck;
    private final LiveData<Boolean> isFirstItem;
    private final CombinedLiveData<Boolean> isLastItem;
    private final LiveData<Boolean> isLoading;
    private final MutableLiveData<String> kodeTps;
    private final LiveData<String> nomorHalaman;
    private final LiveData<String> previewBitmap;
    private final DefaultWitnessRepository witnessRepository;

    @Inject
    public VerifyWitnessAttendanceListViewModel(@ApplicationContext Context context, DefaultElectionRepository electionRepository, EncryptedSharedPreferences encryptedSharedPreferences, DefaultWitnessRepository witnessRepository) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(electionRepository, "electionRepository");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        Intrinsics.checkNotNullParameter(witnessRepository, "witnessRepository");
        this.witnessRepository = witnessRepository;
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(-1);
        this.currentIndex = mutableLiveData;
        MutableLiveData<String> mutableLiveData2 = new MutableLiveData<>(null);
        this.kodeTps = mutableLiveData2;
        this.captureImageUseCase = new CaptureAttendanceImageUseCase(context, encryptedSharedPreferences, electionRepository, witnessRepository);
        LiveData<Resource<AttendanceWithPages>> switchMap = Transformations.switchMap(mutableLiveData2, new Function1<String, LiveData<Resource<AttendanceWithPages>>>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$checkItemsResource$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LiveData<Resource<AttendanceWithPages>> invoke(String str) {
                DefaultWitnessRepository defaultWitnessRepository;
                if (str != null) {
                    defaultWitnessRepository = VerifyWitnessAttendanceListViewModel.this.witnessRepository;
                    return defaultWitnessRepository.getAttendanceByKodeTps(str);
                }
                return AbsentLiveData.Companion.create();
            }
        });
        this.checkItemsResource = switchMap;
        this.isLoading = Transformations.map(switchMap, new Function1<Resource<AttendanceWithPages>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Resource<AttendanceWithPages> resource) {
                return Boolean.valueOf((resource != null ? resource.getSuccess() : null) == ResourceStatus.LOADING);
            }
        });
        LiveData<List<AttendancePage>> map = Transformations.map(switchMap, new Function1<Resource<AttendanceWithPages>, List<AttendancePage>>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$checkItems$1
            @Override // kotlin.jvm.functions.Function1
            public final List<AttendancePage> invoke(Resource<AttendanceWithPages> resource) {
                AttendanceWithPages payload;
                List<AttendancePage> pages;
                if (resource == null || (payload = resource.getPayload()) == null || (pages = payload.getPages()) == null) {
                    return null;
                }
                return CollectionsKt.sortedWith(pages, new Comparator() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$checkItems$1$invoke$$inlined$sortedBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(((AttendancePage) t).getUrutan()), Integer.valueOf(((AttendancePage) t2).getUrutan()));
                    }
                });
            }
        });
        this.checkItems = map;
        CombinedLiveData<AttendancePage> combinedLiveData = new CombinedLiveData<>(new LiveData[]{map, mutableLiveData}, new Function1<List<? extends Object>, AttendancePage>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$currentItem$1
            @Override // kotlin.jvm.functions.Function1
            public final AttendancePage invoke(List<? extends Object> data) {
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
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<org.informatika.sirekap.model.AttendancePage>");
                List list2 = (List) obj2;
                Object obj3 = data.get(1);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                int intValue = ((Integer) obj3).intValue();
                if (intValue < 0 || intValue >= list2.size()) {
                    return null;
                }
                return (AttendancePage) list2.get(intValue);
            }
        });
        this.currentItem = combinedLiveData;
        this.previewBitmap = Transformations.map(combinedLiveData, new Function1<AttendancePage, String>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$previewBitmap$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(AttendancePage attendancePage) {
                if (attendancePage != null) {
                    return attendancePage.getCroppedPhotoPath();
                }
                return null;
            }
        });
        this.nomorHalaman = Transformations.map(combinedLiveData, new Function1<AttendancePage, String>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$nomorHalaman$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(AttendancePage attendancePage) {
                if (attendancePage != null) {
                    return "Halaman " + attendancePage.getUrutan();
                }
                return null;
            }
        });
        this.isFirstItem = Transformations.map(mutableLiveData, new Function1<Integer, Boolean>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$isFirstItem$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Integer it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return Boolean.valueOf(it.intValue() <= 0);
            }
        });
        this.isLastItem = new CombinedLiveData<>(new LiveData[]{map, mutableLiveData}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$isLastItem$1
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
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<org.informatika.sirekap.model.AttendancePage>");
                Object obj3 = data.get(1);
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Int");
                return Boolean.valueOf(((Integer) obj3).intValue() >= ((List) obj2).size() - 1);
            }
        });
        LiveData<Integer> map2 = Transformations.map(map, new Function1<List<AttendancePage>, Integer>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$firstUncheckedIndex$1
            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(List<AttendancePage> list) {
                if (list != null) {
                    Iterator<AttendancePage> it = list.iterator();
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
        this.isFirstCheck = Transformations.map(map2, new Function1<Integer, Boolean>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel$isFirstCheck$1
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

    public final CaptureAttendanceImageUseCase getCaptureImageUseCase() {
        return this.captureImageUseCase;
    }

    public final void setup(String _kodeTps) {
        Intrinsics.checkNotNullParameter(_kodeTps, "_kodeTps");
        this.kodeTps.postValue(_kodeTps);
    }

    public final LiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public static /* synthetic */ void startChecking$default(VerifyWitnessAttendanceListViewModel verifyWitnessAttendanceListViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        verifyWitnessAttendanceListViewModel.startChecking(z);
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

    public final CombinedLiveData<AttendancePage> getCurrentItem() {
        return this.currentItem;
    }

    public final LiveData<String> getPreviewBitmap() {
        return this.previewBitmap;
    }

    public final LiveData<String> getNomorHalaman() {
        return this.nomorHalaman;
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
        AttendancePage value = this.currentItem.getValue();
        if (value != null) {
            this.witnessRepository.markAttendeesChecked(value.getId());
        }
    }

    public final void finishTakingPicture() {
        this.captureImageUseCase.finishUpdatingAttendance();
        this.captureImageUseCase.finishInsertingAttendance();
    }
}
