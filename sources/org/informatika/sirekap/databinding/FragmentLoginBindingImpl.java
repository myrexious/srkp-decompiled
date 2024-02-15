package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.DataBindingAdaptersKt;
import org.informatika.sirekap.ui.login.LoginViewModel;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* loaded from: classes2.dex */
public class FragmentLoginBindingImpl extends FragmentLoginBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final CircularProgressIndicator mboundView2;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.app_logo, 5);
        sparseIntArray.put(R.id.app_name, 6);
    }

    public FragmentLoginBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private FragmentLoginBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (ImageView) bindings[5], (TextView) bindings[6], (Button) bindings[1], (TextView) bindings[4], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.buttonLogin.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[2];
        this.mboundView2 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
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
        if (3 == variableId) {
            setAuthRequestUseCase((AuthRequestUseCase) variable);
        } else if (64 != variableId) {
            return false;
        } else {
            setLoginViewModel((LoginViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentLoginBinding
    public void setAuthRequestUseCase(AuthRequestUseCase AuthRequestUseCase) {
        this.mAuthRequestUseCase = AuthRequestUseCase;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentLoginBinding
    public void setLoginViewModel(LoginViewModel LoginViewModel) {
        this.mLoginViewModel = LoginViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(64);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                return false;
            }
            return onChangeAuthRequestUseCaseIsLoading((MutableLiveData) object, fieldId);
        }
        return onChangeLoginViewModelIsLoading((LiveData) object, fieldId);
    }

    private boolean onChangeLoginViewModelIsLoading(LiveData<Boolean> LoginViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeAuthRequestUseCaseIsLoading(MutableLiveData<Boolean> AuthRequestUseCaseIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        AuthRequestUseCase authRequestUseCase = this.mAuthRequestUseCase;
        LoginViewModel loginViewModel = this.mLoginViewModel;
        int i = ((j & 31) > 0L ? 1 : ((j & 31) == 0L ? 0 : -1));
        boolean z3 = false;
        if (i != 0) {
            MutableLiveData<Boolean> isLoading = authRequestUseCase != null ? authRequestUseCase.isLoading() : null;
            updateLiveDataRegistration(1, isLoading);
            z = !ViewDataBinding.safeUnbox(isLoading != null ? isLoading.getValue() : null);
            if (i != 0) {
                j = z ? j | 64 : j | 32;
            }
        } else {
            z = false;
        }
        if ((j & 64) != 0) {
            LiveData<Boolean> isLoading2 = loginViewModel != null ? loginViewModel.isLoading() : null;
            updateLiveDataRegistration(0, isLoading2);
            z2 = !ViewDataBinding.safeUnbox(isLoading2 != null ? isLoading2.getValue() : null);
        } else {
            z2 = false;
        }
        int i2 = ((31 & j) > 0L ? 1 : ((31 & j) == 0L ? 0 : -1));
        if (i2 != 0 && z) {
            z3 = z2;
        }
        if (i2 != 0) {
            this.buttonLogin.setEnabled(z3);
            DataBindingAdaptersKt.hidden(this.mboundView2, z3);
        }
        if ((j & 16) != 0) {
            DataBindingAdaptersKt.hidden(this.versionCode, true);
            TextViewBindingAdapter.setText(this.versionCode, Integer.toString(50));
            TextViewBindingAdapter.setText(this.versionName, BuildConfig.VERSION_NAME);
        }
    }
}
