package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.support.CombinedLiveData;
import org.informatika.sirekap.ui.settings.SettingsViewModel;

/* loaded from: classes2.dex */
public class FragmentSettingsBindingImpl extends FragmentSettingsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final CircularProgressIndicator mboundView1;
    private final LinearLayout mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.item_change_election_type, 5);
        sparseIntArray.put(R.id.item_change_profile, 6);
    }

    public FragmentSettingsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private FragmentSettingsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (LinearLayout) bindings[5], (LinearLayout) bindings[6]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[1];
        this.mboundView1 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[2];
        this.mboundView2 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32L;
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
        if (65 == variableId) {
            setMainViewModel((MainViewModel) variable);
        } else if (87 != variableId) {
            return false;
        } else {
            setViewModel((SettingsViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentSettingsBinding
    public void setMainViewModel(MainViewModel MainViewModel) {
        this.mMainViewModel = MainViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(65);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentSettingsBinding
    public void setViewModel(SettingsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                if (localFieldId != 2) {
                    return false;
                }
                return onChangeMainViewModelGetLoggedInUserUseCaseLoggedInUser((LiveData) object, fieldId);
            }
            return onChangeViewModelIsLoading((CombinedLiveData) object, fieldId);
        }
        return onChangeViewModelElection((LiveData) object, fieldId);
    }

    private boolean onChangeViewModelElection(LiveData<ElectionWithRelation> ViewModelElection, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoading(CombinedLiveData<Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeMainViewModelGetLoggedInUserUseCaseLoggedInUser(LiveData<User> MainViewModelGetLoggedInUserUseCaseLoggedInUser, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:133:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0050  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentSettingsBindingImpl.executeBindings():void");
    }
}
