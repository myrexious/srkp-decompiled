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
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.DataBindingAdaptersKt;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel;

/* loaded from: classes2.dex */
public class FragmentSpecialOccurrenceBindingImpl extends FragmentSpecialOccurrenceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;
    private final ConstraintLayout mboundView2;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(10);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(4, new String[]{"view_lock_special_occurrence"}, new int[]{7}, new int[]{R.layout.view_lock_special_occurrence});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.text_special_occurrence_empty_title, 8);
        sparseIntArray.put(R.id.textView, 9);
    }

    public FragmentSpecialOccurrenceBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private FragmentSpecialOccurrenceBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (FloatingActionButton) bindings[6], (FloatingActionButton) bindings[5], (ConstraintLayout) bindings[4], (RecyclerView) bindings[3], (TextView) bindings[8], (TextView) bindings[9], (ViewLockSpecialOccurrenceBinding) bindings[7]);
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
        setContainedBinding(this.viewLockSpecialOccurrence);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
        }
        this.viewLockSpecialOccurrence.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.viewLockSpecialOccurrence.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (87 == variableId) {
            setViewModel((SpecialOccurrenceViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentSpecialOccurrenceBinding
    public void setViewModel(SpecialOccurrenceViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.viewLockSpecialOccurrence.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                if (localFieldId != 2) {
                    if (localFieldId != 3) {
                        if (localFieldId != 4) {
                            return false;
                        }
                        return onChangeViewModelIsLoading((LiveData) object, fieldId);
                    }
                    return onChangeViewLockSpecialOccurrence((ViewLockSpecialOccurrenceBinding) object, fieldId);
                }
                return onChangeViewModelIsAnyUnverified((LiveData) object, fieldId);
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

    private boolean onChangeViewModelIsAnyUnverified(LiveData<Boolean> ViewModelIsAnyUnverified, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewLockSpecialOccurrence(ViewLockSpecialOccurrenceBinding ViewLockSpecialOccurrence, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoading(LiveData<Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        LiveData<Boolean> liveData;
        LiveData<Boolean> liveData2;
        Boolean bool;
        Boolean bool2;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        SpecialOccurrenceViewModel specialOccurrenceViewModel = this.mViewModel;
        boolean z9 = true;
        if ((119 & j) != 0) {
            int i = ((j & 102) > 0L ? 1 : ((j & 102) == 0L ? 0 : -1));
            if (i != 0) {
                liveData2 = specialOccurrenceViewModel != null ? specialOccurrenceViewModel.isEmpty() : null;
                updateLiveDataRegistration(1, liveData2);
                bool = liveData2 != null ? liveData2.getValue() : null;
                z4 = ViewDataBinding.safeUnbox(bool);
                if (i != 0) {
                    j = z4 ? j | FileUtils.ONE_KB : j | 512;
                }
            } else {
                liveData2 = null;
                bool = null;
                z4 = false;
            }
            if ((j & 100) != 0) {
                liveData = specialOccurrenceViewModel != null ? specialOccurrenceViewModel.isAnyUnverified() : null;
                updateLiveDataRegistration(2, liveData);
                bool2 = liveData != null ? liveData.getValue() : null;
                z5 = !ViewDataBinding.safeUnbox(bool2);
                z8 = ViewDataBinding.safeUnbox(Boolean.valueOf(z5));
            } else {
                liveData = null;
                bool2 = null;
                z5 = false;
                z8 = false;
            }
            if ((j & 115) != 0) {
                LiveData<Boolean> isLoading = specialOccurrenceViewModel != null ? specialOccurrenceViewModel.isLoading() : null;
                updateLiveDataRegistration(4, isLoading);
                z = ViewDataBinding.safeUnbox(isLoading != null ? isLoading.getValue() : null);
                if ((j & 114) != 0) {
                    j = z ? j | 256 : j | 128;
                }
                if ((j & 113) != 0) {
                    j = z ? j | 4096 : j | 2048;
                }
                if ((j & 112) != 0) {
                    z2 = ViewDataBinding.safeUnbox(Boolean.valueOf(!z));
                    z3 = z8;
                } else {
                    z3 = z8;
                }
            } else {
                z3 = z8;
                z = false;
            }
            z2 = false;
        } else {
            liveData = null;
            liveData2 = null;
            bool = null;
            bool2 = null;
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
        }
        if ((j & 2048) != 0) {
            LiveData<Boolean> isEmpty = specialOccurrenceViewModel != null ? specialOccurrenceViewModel.isEmpty() : null;
            z6 = false;
            updateLiveDataRegistration(0, isEmpty);
            z7 = !ViewDataBinding.safeUnbox(isEmpty != null ? isEmpty.getValue() : null);
        } else {
            z6 = false;
            z7 = false;
        }
        if ((j & 128) != 0) {
            if (specialOccurrenceViewModel != null) {
                liveData2 = specialOccurrenceViewModel.isEmpty();
            }
            updateLiveDataRegistration(1, liveData2);
            if (liveData2 != null) {
                bool = liveData2.getValue();
            }
            z4 = ViewDataBinding.safeUnbox(bool);
            if ((j & 102) != 0) {
                j = z4 ? j | FileUtils.ONE_KB : j | 512;
            }
        }
        int i2 = ((114 & j) > 0L ? 1 : ((114 & j) == 0L ? 0 : -1));
        boolean z10 = i2 != 0 ? z ? true : z4 : z6;
        int i3 = ((j & 113) > 0L ? 1 : ((j & 113) == 0L ? 0 : -1));
        if (i3 == 0) {
            z7 = z6;
        } else if (z) {
            z7 = true;
        }
        if ((j & 512) != 0) {
            if (specialOccurrenceViewModel != null) {
                liveData = specialOccurrenceViewModel.isAnyUnverified();
            }
            updateLiveDataRegistration(2, liveData);
            if (liveData != null) {
                bool2 = liveData.getValue();
            }
            z5 = !ViewDataBinding.safeUnbox(bool2);
        }
        int i4 = ((j & 102) > 0L ? 1 : ((j & 102) == 0L ? 0 : -1));
        if (i4 == 0) {
            z9 = z6;
        } else if (!z4) {
            z9 = z5;
        }
        if ((j & 100) != 0) {
            DataBindingAdaptersKt.hidden(this.buttonAdd, z3);
        }
        if (i4 != 0) {
            DataBindingAdaptersKt.hidden(this.buttonSend, z9);
        }
        if ((112 & j) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView1, z2);
        }
        if (i3 != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView2, z7);
        }
        if (i2 != 0) {
            DataBindingAdaptersKt.hidden(this.recyclerView, z10);
        }
        if ((j & 96) != 0) {
            this.viewLockSpecialOccurrence.setViewModel(specialOccurrenceViewModel);
        }
        executeBindingsOn(this.viewLockSpecialOccurrence);
    }
}
