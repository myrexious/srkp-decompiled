package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.DataBindingAdaptersKt;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* loaded from: classes2.dex */
public class ActivityMainBindingImpl extends ActivityMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.app_bar_layout, 2);
        sparseIntArray.put(R.id.toolbar, 3);
        sparseIntArray.put(R.id.nav_host_fragment, 4);
    }

    public ActivityMainBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ActivityMainBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (AppBarLayout) bindings[2], (FragmentContainerView) bindings[4], (MaterialToolbar) bindings[3]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout2;
        constraintLayout2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ActivityMainBinding
    public void setAuthRequestUseCase(AuthRequestUseCase AuthRequestUseCase) {
        this.mAuthRequestUseCase = AuthRequestUseCase;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeAuthRequestUseCaseIsLoading((MutableLiveData) object, fieldId);
    }

    private boolean onChangeAuthRequestUseCaseIsLoading(MutableLiveData<Boolean> AuthRequestUseCaseIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        AuthRequestUseCase authRequestUseCase = this.mAuthRequestUseCase;
        int i = ((j & 7) > 0L ? 1 : ((j & 7) == 0L ? 0 : -1));
        boolean z = false;
        if (i != 0) {
            MutableLiveData<Boolean> isLoading = authRequestUseCase != null ? authRequestUseCase.isLoading() : null;
            updateLiveDataRegistration(0, isLoading);
            z = ViewDataBinding.safeUnbox(Boolean.valueOf(!ViewDataBinding.safeUnbox(isLoading != null ? isLoading.getValue() : null)));
        }
        if (i != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView1, z);
        }
    }
}
