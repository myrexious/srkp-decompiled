package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel;

/* loaded from: classes2.dex */
public class ViewWitnessVerifyAttendanceListOngoingBindingImpl extends ViewWitnessVerifyAttendanceListOngoingBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.card_image, 6);
        sparseIntArray.put(R.id.icon_container, 7);
        sparseIntArray.put(R.id.card_instruction, 8);
        sparseIntArray.put(R.id.label_photo_instruction, 9);
        sparseIntArray.put(R.id.button_retake_photo, 10);
    }

    public ViewWitnessVerifyAttendanceListOngoingBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private ViewWitnessVerifyAttendanceListOngoingBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (Button) bindings[3], (Button) bindings[5], (Button) bindings[4], (Button) bindings[10], (MaterialCardView) bindings[6], (MaterialCardView) bindings[8], (ImageView) bindings[7], (TextView) bindings[9], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.buttonBack.setTag(null);
        this.buttonFinish.setTag(null);
        this.buttonNext.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        this.nomorHalamanTitle.setTag(null);
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
        if (87 == variableId) {
            setViewModel((VerifyWitnessAttendanceListViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ViewWitnessVerifyAttendanceListOngoingBinding
    public void setViewModel(VerifyWitnessAttendanceListViewModel ViewModel) {
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
                    if (localFieldId != 3) {
                        return false;
                    }
                    return onChangeViewModelNomorHalaman((LiveData) object, fieldId);
                }
                return onChangeViewModelFirstItem((LiveData) object, fieldId);
            }
            return onChangeViewModelLastItem((CombinedLiveData) object, fieldId);
        }
        return onChangeViewModelPreviewBitmap((LiveData) object, fieldId);
    }

    private boolean onChangeViewModelPreviewBitmap(LiveData<String> ViewModelPreviewBitmap, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelLastItem(CombinedLiveData<Boolean> ViewModelLastItem, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFirstItem(LiveData<Boolean> ViewModelFirstItem, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelNomorHalaman(LiveData<String> ViewModelNomorHalaman, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0072  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.ViewWitnessVerifyAttendanceListOngoingBindingImpl.executeBindings():void");
    }
}
