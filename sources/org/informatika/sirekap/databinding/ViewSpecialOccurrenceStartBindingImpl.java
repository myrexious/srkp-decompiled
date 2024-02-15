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
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.DataBindingAdaptersKt;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel;

/* loaded from: classes2.dex */
public class ViewSpecialOccurrenceStartBindingImpl extends ViewSpecialOccurrenceStartBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.icon_verify_attendees, 5);
        sparseIntArray.put(R.id.title_start_verification, 6);
    }

    public ViewSpecialOccurrenceStartBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ViewSpecialOccurrenceStartBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (Button) bindings[4], (Button) bindings[3], (Button) bindings[2], (ImageView) bindings[5], (TextView) bindings[1], (TextView) bindings[6]);
        this.mDirtyFlags = -1L;
        this.buttonRestartStartVerification.setTag(null);
        this.buttonResumeVerification.setTag(null);
        this.buttonStartVerification.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.subtitleStartVerification.setTag(null);
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
        if (87 == variableId) {
            setViewModel((VerifySpecialOccurrenceViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ViewSpecialOccurrenceStartBinding
    public void setViewModel(VerifySpecialOccurrenceViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeViewModelIsFirstCheck((LiveData) object, fieldId);
    }

    private boolean onChangeViewModelIsFirstCheck(LiveData<Boolean> ViewModelIsFirstCheck, int fieldId) {
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
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        VerifySpecialOccurrenceViewModel verifySpecialOccurrenceViewModel = this.mViewModel;
        int i = ((j & 7) > 0L ? 1 : ((j & 7) == 0L ? 0 : -1));
        boolean z2 = false;
        String str = null;
        if (i != 0) {
            LiveData<Boolean> isFirstCheck = verifySpecialOccurrenceViewModel != null ? verifySpecialOccurrenceViewModel.isFirstCheck() : null;
            updateLiveDataRegistration(0, isFirstCheck);
            z2 = ViewDataBinding.safeUnbox(isFirstCheck != null ? isFirstCheck.getValue() : null);
            if (i != 0) {
                j |= z2 ? 16L : 8L;
            }
            boolean z3 = !z2;
            str = this.subtitleStartVerification.getResources().getString(z2 ? R.string.verify_special_occurrence_start_subtitle : R.string.verify_special_occurrence_start_subtitle_unfinished);
            z = ViewDataBinding.safeUnbox(Boolean.valueOf(z3));
        } else {
            z = false;
        }
        if ((j & 7) != 0) {
            DataBindingAdaptersKt.hidden(this.buttonRestartStartVerification, z2);
            DataBindingAdaptersKt.hidden(this.buttonResumeVerification, z2);
            DataBindingAdaptersKt.hidden(this.buttonStartVerification, z);
            TextViewBindingAdapter.setText(this.subtitleStartVerification, str);
        }
    }
}
