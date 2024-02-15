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
import org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel;

/* loaded from: classes2.dex */
public class FragmentVerifyWizardBindingImpl extends FragmentVerifyWizardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final CircularProgressIndicator mboundView1;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(4);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"view_verify_wizard_start", "view_verify_wizard_ongoing"}, new int[]{2, 3}, new int[]{R.layout.view_verify_wizard_start, R.layout.view_verify_wizard_ongoing});
        sViewsWithIds = null;
    }

    public FragmentVerifyWizardBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private FragmentVerifyWizardBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (ViewVerifyWizardOngoingBinding) bindings[3], (ViewVerifyWizardStartBinding) bindings[2]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[1];
        this.mboundView1 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        setContainedBinding(this.viewVerifyWizardOngoing);
        setContainedBinding(this.viewVerifyWizardStart);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
        }
        this.viewVerifyWizardStart.invalidateAll();
        this.viewVerifyWizardOngoing.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.viewVerifyWizardStart.hasPendingBindings() || this.viewVerifyWizardOngoing.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (87 == variableId) {
            setViewModel((VerifyWizardViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentVerifyWizardBinding
    public void setViewModel(VerifyWizardViewModel ViewModel) {
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
        this.viewVerifyWizardStart.setLifecycleOwner(lifecycleOwner);
        this.viewVerifyWizardOngoing.setLifecycleOwner(lifecycleOwner);
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
                return onChangeViewVerifyWizardStart((ViewVerifyWizardStartBinding) object, fieldId);
            }
            return onChangeViewVerifyWizardOngoing((ViewVerifyWizardOngoingBinding) object, fieldId);
        }
        return onChangeViewModelCurrentIndex((MutableLiveData) object, fieldId);
    }

    private boolean onChangeViewModelCurrentIndex(MutableLiveData<Integer> ViewModelCurrentIndex, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewVerifyWizardOngoing(ViewVerifyWizardOngoingBinding ViewVerifyWizardOngoing, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewVerifyWizardStart(ViewVerifyWizardStartBinding ViewVerifyWizardStart, int fieldId) {
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

    /* JADX WARN: Removed duplicated region for block: B:104:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x00e9  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentVerifyWizardBindingImpl.executeBindings():void");
    }
}
