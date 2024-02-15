package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.AttendanceWithPages;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel;

/* loaded from: classes2.dex */
public class FragmentWitnessAttendanceListBindingImpl extends FragmentWitnessAttendanceListBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;
    private final ConstraintLayout mboundView2;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(10);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(4, new String[]{"view_lock_attendance"}, new int[]{7}, new int[]{R.layout.view_lock_attendance});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.text_witness_empty_title, 8);
        sparseIntArray.put(R.id.textView, 9);
    }

    public FragmentWitnessAttendanceListBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private FragmentWitnessAttendanceListBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, (FloatingActionButton) bindings[6], (FloatingActionButton) bindings[5], (ConstraintLayout) bindings[4], (RecyclerView) bindings[3], (TextView) bindings[9], (TextView) bindings[8], (ViewLockAttendanceBinding) bindings[7]);
        this.mDirtyFlags = -1L;
        this.buttonAdd.setTag(null);
        this.buttonSend.setTag(null);
        this.containerLockSpecialOccurrence.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout2;
        constraintLayout2.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout3;
        constraintLayout3.setTag(null);
        this.recyclerView.setTag(null);
        setContainedBinding(this.viewLockAttendance);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128L;
        }
        this.viewLockAttendance.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.viewLockAttendance.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (87 == variableId) {
            setViewModel((WitnessAttendanceListViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentWitnessAttendanceListBinding
    public void setViewModel(WitnessAttendanceListViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.viewLockAttendance.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                if (localFieldId != 2) {
                    if (localFieldId != 3) {
                        if (localFieldId != 4) {
                            if (localFieldId != 5) {
                                return false;
                            }
                            return onChangeViewModelIsLoading((LiveData) object, fieldId);
                        }
                        return onChangeViewModelAttendance((LiveData) object, fieldId);
                    }
                    return onChangeViewModelIsAnyUnverified((LiveData) object, fieldId);
                }
                return onChangeViewLockAttendance((ViewLockAttendanceBinding) object, fieldId);
            }
            return onChangeViewModelIsEmpty1((LiveData) object, fieldId);
        }
        return onChangeViewModelIsEmpty((LiveData) object, fieldId);
    }

    private boolean onChangeViewModelIsEmpty(LiveData<Boolean> ViewModelIsEmpty, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsEmpty1(LiveData<Boolean> ViewModelIsEmpty, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewLockAttendance(ViewLockAttendanceBinding ViewLockAttendance, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsAnyUnverified(LiveData<Boolean> ViewModelIsAnyUnverified, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelAttendance(LiveData<AttendanceWithPages> ViewModelAttendance, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoading(LiveData<Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:192:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0112  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 526
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentWitnessAttendanceListBindingImpl.executeBindings():void");
    }
}
