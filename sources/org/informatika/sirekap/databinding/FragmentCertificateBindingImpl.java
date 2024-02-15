package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.security.CertificateViewModel;

/* loaded from: classes2.dex */
public class FragmentCertificateBindingImpl extends FragmentCertificateBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;
    private final ConstraintLayout mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.loading_text, 7);
        sparseIntArray.put(R.id.loading_progress, 8);
        sparseIntArray.put(R.id.title_error, 9);
        sparseIntArray.put(R.id.button_retry, 10);
    }

    public FragmentCertificateBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private FragmentCertificateBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (Button) bindings[2], (Button) bindings[10], (CircularProgressIndicator) bindings[8], (TextView) bindings[7], (TextView) bindings[4], (TextView) bindings[9], (TextView) bindings[6], (TextView) bindings[5]);
        this.mDirtyFlags = -1L;
        this.buttonCheck.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout2;
        constraintLayout2.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[3];
        this.mboundView3 = constraintLayout3;
        constraintLayout3.setTag(null);
        this.subtitleError.setTag(null);
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
            setFragmentViewModel((CertificateViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentCertificateBinding
    public void setFragmentViewModel(CertificateViewModel FragmentViewModel) {
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
                return onChangeFragmentViewModelIsLoading((MutableLiveData) object, fieldId);
            }
            return onChangeFragmentViewModelErrorMessage((MutableLiveData) object, fieldId);
        }
        return onChangeFragmentViewModelIsCheckButtonEnabled((MutableLiveData) object, fieldId);
    }

    private boolean onChangeFragmentViewModelIsCheckButtonEnabled(MutableLiveData<Boolean> FragmentViewModelIsCheckButtonEnabled, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFragmentViewModelErrorMessage(MutableLiveData<String> FragmentViewModelErrorMessage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFragmentViewModelIsLoading(MutableLiveData<Boolean> FragmentViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:169:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentCertificateBindingImpl.executeBindings():void");
    }
}
