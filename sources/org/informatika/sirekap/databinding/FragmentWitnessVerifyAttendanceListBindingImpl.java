package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.DataBindingAdaptersKt;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel;

/* loaded from: classes2.dex */
public class FragmentWitnessVerifyAttendanceListBindingImpl extends FragmentWitnessVerifyAttendanceListBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final CircularProgressIndicator mboundView1;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(4);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_witness_verify_attendance_list_start", "view_witness_verify_attendance_list_ongoing"}, new int[]{2, 3}, new int[]{R.layout.view_witness_verify_attendance_list_start, R.layout.view_witness_verify_attendance_list_ongoing});
        sViewsWithIds = null;
    }

    public FragmentWitnessVerifyAttendanceListBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private FragmentWitnessVerifyAttendanceListBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (ViewWitnessVerifyAttendanceListOngoingBinding) bindings[3], (ViewWitnessVerifyAttendanceListStartBinding) bindings[2]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[1];
        this.mboundView1 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        setContainedBinding(this.viewVerifyAttendeesOngoing);
        setContainedBinding(this.viewVerifyAttendeesStart);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
        }
        this.viewVerifyAttendeesStart.invalidateAll();
        this.viewVerifyAttendeesOngoing.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.viewVerifyAttendeesStart.hasPendingBindings() || this.viewVerifyAttendeesOngoing.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (87 == variableId) {
            setViewModel((VerifyWitnessAttendanceListViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentWitnessVerifyAttendanceListBinding
    public void setViewModel(VerifyWitnessAttendanceListViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.viewVerifyAttendeesStart.setLifecycleOwner(lifecycleOwner);
        this.viewVerifyAttendeesOngoing.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                if (localFieldId != 2) {
                    if (localFieldId != 3) {
                        return false;
                    }
                    return onChangeViewModelIsLoading((LiveData) object, fieldId);
                }
                return onChangeViewVerifyAttendeesOngoing((ViewWitnessVerifyAttendanceListOngoingBinding) object, fieldId);
            }
            return onChangeViewModelCurrentIndex((MutableLiveData) object, fieldId);
        }
        return onChangeViewVerifyAttendeesStart((ViewWitnessVerifyAttendanceListStartBinding) object, fieldId);
    }

    private boolean onChangeViewVerifyAttendeesStart(ViewWitnessVerifyAttendanceListStartBinding ViewVerifyAttendeesStart, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCurrentIndex(MutableLiveData<Integer> ViewModelCurrentIndex, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewVerifyAttendeesOngoing(ViewWitnessVerifyAttendanceListOngoingBinding ViewVerifyAttendeesOngoing, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoading(LiveData<Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        VerifyWitnessAttendanceListViewModel verifyWitnessAttendanceListViewModel = this.mViewModel;
        int i = ((j & 58) > 0L ? 1 : ((j & 58) == 0L ? 0 : -1));
        if (i != 0) {
            LiveData<Boolean> isLoading = verifyWitnessAttendanceListViewModel != null ? verifyWitnessAttendanceListViewModel.isLoading() : null;
            updateLiveDataRegistration(3, isLoading);
            z = ViewDataBinding.safeUnbox(isLoading != null ? isLoading.getValue() : null);
            if (i != 0) {
                j = z ? j | 128 | 512 : j | 64 | 256;
            }
            z2 = (j & 56) != 0 ? ViewDataBinding.safeUnbox(Boolean.valueOf(!z)) : false;
        } else {
            z = false;
            z2 = false;
        }
        if ((j & 320) != 0) {
            MutableLiveData<Integer> currentIndex = verifyWitnessAttendanceListViewModel != null ? verifyWitnessAttendanceListViewModel.getCurrentIndex() : null;
            updateLiveDataRegistration(1, currentIndex);
            int safeUnbox = ViewDataBinding.safeUnbox(currentIndex != null ? currentIndex.getValue() : null);
            z4 = (256 & j) != 0 && safeUnbox < 0;
            z3 = (64 & j) != 0 && safeUnbox >= 0;
        } else {
            z3 = false;
            z4 = false;
        }
        int i2 = ((58 & j) > 0L ? 1 : ((58 & j) == 0L ? 0 : -1));
        if (i2 != 0) {
            boolean z7 = z ? true : z3;
            z6 = z ? true : z4;
            z5 = z7;
        } else {
            z5 = false;
            z6 = false;
        }
        if ((j & 56) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView1, z2);
        }
        if (i2 != 0) {
            DataBindingAdaptersKt.hidden(this.viewVerifyAttendeesOngoing.getRoot(), z6);
            DataBindingAdaptersKt.hidden(this.viewVerifyAttendeesStart.getRoot(), z5);
        }
        if ((j & 48) != 0) {
            this.viewVerifyAttendeesOngoing.setViewModel(verifyWitnessAttendanceListViewModel);
            this.viewVerifyAttendeesStart.setViewModel(verifyWitnessAttendanceListViewModel);
        }
        executeBindingsOn(this.viewVerifyAttendeesStart);
        executeBindingsOn(this.viewVerifyAttendeesOngoing);
    }
}
