package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.support.DataBindingAdaptersKt;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.ui.DeviceInitializationViewModel;

/* loaded from: classes2.dex */
public class ActivityDeviceInitializationBindingImpl extends ActivityDeviceInitializationBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final CircularProgressIndicator mboundView1;
    private final ConstraintLayout mboundView2;

    public ActivityDeviceInitializationBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ActivityDeviceInitializationBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (Button) bindings[5], (TextView) bindings[4], (TextView) bindings[3], (TextView) bindings[7], (TextView) bindings[6]);
        this.mDirtyFlags = -1L;
        this.buttonRetry.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[1];
        this.mboundView1 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout2;
        constraintLayout2.setTag(null);
        this.subtitleError.setTag(null);
        this.titleError.setTag(null);
        this.versionCode.setTag(null);
        this.versionName.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
        if (15 == variableId) {
            setFragmentViewModel((DeviceInitializationViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ActivityDeviceInitializationBinding
    public void setFragmentViewModel(DeviceInitializationViewModel FragmentViewModel) {
        this.mFragmentViewModel = FragmentViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(15);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                if (localFieldId != 2) {
                    return false;
                }
                return onChangeFragmentViewModelIsLoading((CombinedLiveData) object, fieldId);
            }
            return onChangeFragmentViewModelError((CombinedLiveData) object, fieldId);
        }
        return onChangeFragmentViewModelIsDeviceCheckFailed((MutableLiveData) object, fieldId);
    }

    private boolean onChangeFragmentViewModelIsDeviceCheckFailed(MutableLiveData<Boolean> FragmentViewModelIsDeviceCheckFailed, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFragmentViewModelError(CombinedLiveData<String> FragmentViewModelError, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFragmentViewModelIsLoading(CombinedLiveData<Boolean> FragmentViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
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
        String str;
        boolean z5;
        boolean z6;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        DeviceInitializationViewModel deviceInitializationViewModel = this.mFragmentViewModel;
        if ((31 & j) != 0) {
            int i = ((j & 25) > 0L ? 1 : ((j & 25) == 0L ? 0 : -1));
            if (i != 0) {
                z6 = deviceInitializationViewModel != null ? deviceInitializationViewModel.isExpired() : false;
                if (i != 0) {
                    j = z6 ? j | 64 : j | 32;
                }
                z = (j & 24) != 0 ? !z6 : false;
            } else {
                z = false;
                z6 = false;
            }
            if ((j & 26) != 0) {
                CombinedLiveData<String> error = deviceInitializationViewModel != null ? deviceInitializationViewModel.getError() : null;
                updateLiveDataRegistration(1, error);
                str = error != null ? error.getValue() : null;
                z2 = str == null;
            } else {
                z2 = false;
                str = null;
            }
            if ((j & 28) != 0) {
                CombinedLiveData<Boolean> isLoading = deviceInitializationViewModel != null ? deviceInitializationViewModel.isLoading() : null;
                updateLiveDataRegistration(2, isLoading);
                z3 = ViewDataBinding.safeUnbox(Boolean.valueOf(!ViewDataBinding.safeUnbox(isLoading != null ? isLoading.getValue() : null)));
            } else {
                z3 = false;
            }
            z4 = z6;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            str = null;
        }
        if ((32 & j) != 0) {
            MutableLiveData<Boolean> isDeviceCheckFailed = deviceInitializationViewModel != null ? deviceInitializationViewModel.isDeviceCheckFailed() : null;
            updateLiveDataRegistration(0, isDeviceCheckFailed);
            z5 = ViewDataBinding.safeUnbox(isDeviceCheckFailed != null ? isDeviceCheckFailed.getValue() : null);
        } else {
            z5 = false;
        }
        int i2 = ((25 & j) > 0L ? 1 : ((25 & j) == 0L ? 0 : -1));
        boolean z7 = i2 != 0 ? z4 ? true : z5 : false;
        if (i2 != 0) {
            DataBindingAdaptersKt.hidden(this.buttonRetry, z7);
        }
        if ((28 & j) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView1, z3);
        }
        if ((26 & j) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView2, z2);
            TextViewBindingAdapter.setText(this.subtitleError, str);
        }
        if ((24 & j) != 0) {
            DataBindingAdaptersKt.hidden(this.titleError, z4);
            DataBindingAdaptersKt.hidden(this.versionName, z);
        }
        if ((j & 16) != 0) {
            DataBindingAdaptersKt.hidden(this.versionCode, true);
            TextViewBindingAdapter.setText(this.versionCode, Integer.toString(50));
            TextViewBindingAdapter.setText(this.versionName, BuildConfig.VERSION_NAME);
        }
    }
}
