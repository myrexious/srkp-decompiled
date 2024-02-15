package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel;

/* loaded from: classes2.dex */
public class FragmentImageHistoryBindingImpl extends FragmentImageHistoryBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final CoordinatorLayout mboundView0;
    private final ConstraintLayout mboundView1;
    private final ConstraintLayout mboundView14;
    private final TextView mboundView15;
    private final TextView mboundView16;
    private final ConstraintLayout mboundView2;
    private final ConstraintLayout mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.text_data_empty_title, 17);
        sparseIntArray.put(R.id.layout_filter_data, 18);
        sparseIntArray.put(R.id.title_filter_delivery_status, 19);
        sparseIntArray.put(R.id.filter_data, 20);
        sparseIntArray.put(R.id.autocomplete_filter_election_pemilihan, 21);
    }

    public FragmentImageHistoryBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private FragmentImageHistoryBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 10, (AutoCompleteTextView) bindings[21], (Chip) bindings[9], (Chip) bindings[4], (Chip) bindings[5], (Chip) bindings[6], (Chip) bindings[10], (HorizontalScrollView) bindings[20], (HorizontalScrollView) bindings[8], (TextInputLayout) bindings[12], (ConstraintLayout) bindings[18], (RecyclerView) bindings[13], (TextView) bindings[17], (TextView) bindings[19], (TextView) bindings[11], (TextView) bindings[7]);
        this.mDirtyFlags = -1L;
        this.chipFilterInvalid.setTag(null);
        this.chipFilterNotSent.setTag(null);
        this.chipFilterProcessed.setTag(null);
        this.chipFilterScanned.setTag(null);
        this.chipFilterValid.setTag(null);
        this.filterData2.setTag(null);
        this.filterElectionPemilihan.setTag(null);
        this.listData.setTag(null);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) bindings[0];
        this.mboundView0 = coordinatorLayout;
        coordinatorLayout.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[14];
        this.mboundView14 = constraintLayout2;
        constraintLayout2.setTag(null);
        TextView textView = (TextView) bindings[15];
        this.mboundView15 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[16];
        this.mboundView16 = textView2;
        textView2.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout3;
        constraintLayout3.setTag(null);
        ConstraintLayout constraintLayout4 = (ConstraintLayout) bindings[3];
        this.mboundView3 = constraintLayout4;
        constraintLayout4.setTag(null);
        this.titleFilterElectionPemilihan.setTag(null);
        this.titleFilterScanResult.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2048L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (87 == variableId) {
            setViewModel((ImageHistoryViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentImageHistoryBinding
    public void setViewModel(ImageHistoryViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= FileUtils.ONE_KB;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelIsCheckedScanned((LiveData) object, fieldId);
            case 1:
                return onChangeViewModelShowElectionPemilihanFilter((LiveData) object, fieldId);
            case 2:
                return onChangeViewModelIsCheckedNotSent((LiveData) object, fieldId);
            case 3:
                return onChangeViewModelShowScannedFilterChips((LiveData) object, fieldId);
            case 4:
                return onChangeViewModelIsCheckedValid((LiveData) object, fieldId);
            case 5:
                return onChangeViewModelIsCheckedInvalid((LiveData) object, fieldId);
            case 6:
                return onChangeViewModelIsEmpty((LiveData) object, fieldId);
            case 7:
                return onChangeViewModelIsCheckedProcessed((LiveData) object, fieldId);
            case 8:
                return onChangeViewModelShowEmptyText((LiveData) object, fieldId);
            case 9:
                return onChangeViewModelFilterImageHistory((MutableLiveData) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelIsCheckedScanned(LiveData<Boolean> ViewModelIsCheckedScanned, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShowElectionPemilihanFilter(LiveData<Boolean> ViewModelShowElectionPemilihanFilter, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedNotSent(LiveData<Boolean> ViewModelIsCheckedNotSent, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShowScannedFilterChips(LiveData<Boolean> ViewModelShowScannedFilterChips, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedValid(LiveData<Boolean> ViewModelIsCheckedValid, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedInvalid(LiveData<Boolean> ViewModelIsCheckedInvalid, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsEmpty(LiveData<Boolean> ViewModelIsEmpty, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedProcessed(LiveData<Boolean> ViewModelIsCheckedProcessed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShowEmptyText(LiveData<Boolean> ViewModelShowEmptyText, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFilterImageHistory(MutableLiveData<List<Integer>> ViewModelFilterImageHistory, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:342:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:389:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 711
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentImageHistoryBindingImpl.executeBindings():void");
    }
}
