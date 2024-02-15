package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.TpsTime;
import org.informatika.sirekap.support.CombinedLiveData;
import org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel;

/* loaded from: classes2.dex */
public class FragmentTpsTimeBindingImpl extends FragmentTpsTimeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final CircularProgressIndicator mboundView1;
    private final CircularProgressIndicator mboundView19;
    private final ScrollView mboundView2;
    private final ConstraintLayout mboundView3;
    private final TextView mboundView4;
    private final ConstraintLayout mboundView7;
    private final ConstraintLayout mboundView8;
    private final ConstraintLayout mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.card_success, 20);
        sparseIntArray.put(R.id.label_start_date_time, 21);
        sparseIntArray.put(R.id.label_start_end_time, 22);
        sparseIntArray.put(R.id.card_warning, 23);
        sparseIntArray.put(R.id.text_input_layout_start_date, 24);
    }

    public FragmentTpsTimeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds));
    }

    private FragmentTpsTimeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 12, (MaterialCardView) bindings[20], (MaterialCardView) bindings[23], (TextView) bindings[5], (TextView) bindings[6], (TextInputEditText) bindings[15], (TextInputEditText) bindings[17], (TextInputEditText) bindings[11], (TextInputEditText) bindings[13], (TextView) bindings[21], (TextView) bindings[22], (Button) bindings[18], (TextInputLayout) bindings[14], (TextInputLayout) bindings[16], (TextInputLayout) bindings[24], (TextInputLayout) bindings[12], (TextView) bindings[10]);
        this.mDirtyFlags = -1L;
        this.contentStartDateTime.setTag(null);
        this.contentStartEndTime.setTag(null);
        this.inputEndDate.setTag(null);
        this.inputEndTime.setTag(null);
        this.inputStartDate.setTag(null);
        this.inputStartTime.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[1];
        this.mboundView1 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        CircularProgressIndicator circularProgressIndicator2 = (CircularProgressIndicator) bindings[19];
        this.mboundView19 = circularProgressIndicator2;
        circularProgressIndicator2.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[2];
        this.mboundView2 = scrollView;
        scrollView.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[3];
        this.mboundView3 = constraintLayout2;
        constraintLayout2.setTag(null);
        TextView textView = (TextView) bindings[4];
        this.mboundView4 = textView;
        textView.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[7];
        this.mboundView7 = constraintLayout3;
        constraintLayout3.setTag(null);
        ConstraintLayout constraintLayout4 = (ConstraintLayout) bindings[8];
        this.mboundView8 = constraintLayout4;
        constraintLayout4.setTag(null);
        ConstraintLayout constraintLayout5 = (ConstraintLayout) bindings[9];
        this.mboundView9 = constraintLayout5;
        constraintLayout5.setTag(null);
        this.submitButton.setTag(null);
        this.textInputLayoutEndDate.setTag(null);
        this.textInputLayoutEndTime.setTag(null);
        this.textInputLayoutStartTime.setTag(null);
        this.textSubtitle.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8192L;
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
            setViewModel((TpsTimeViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentTpsTimeBinding
    public void setViewModel(TpsTimeViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4096;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelEndTimeFormatted((LiveData) object, fieldId);
            case 1:
                return onChangeViewModelCanFillForm((CombinedLiveData) object, fieldId);
            case 2:
                return onChangeViewModelStartTimeFormatted((LiveData) object, fieldId);
            case 3:
                return onChangeViewModelEndDateFormatted((LiveData) object, fieldId);
            case 4:
                return onChangeViewModelStartDateFormatted((LiveData) object, fieldId);
            case 5:
                return onChangeViewModelCanSubmit((CombinedLiveData) object, fieldId);
            case 6:
                return onChangeViewModelGetTpsTimeUseCaseIsDataExist((LiveData) object, fieldId);
            case 7:
                return onChangeViewModelAddTpsTimeUseCaseIsLoading((LiveData) object, fieldId);
            case 8:
                return onChangeViewModelSuccessMessage((CombinedLiveData) object, fieldId);
            case 9:
                return onChangeViewModelFormInstruction((CombinedLiveData) object, fieldId);
            case 10:
                return onChangeViewModelGetTpsTimeUseCaseIsLoading((LiveData) object, fieldId);
            case 11:
                return onChangeViewModelGetTpsTimeUseCaseData((LiveData) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelEndTimeFormatted(LiveData<String> ViewModelEndTimeFormatted, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCanFillForm(CombinedLiveData<Boolean> ViewModelCanFillForm, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelStartTimeFormatted(LiveData<String> ViewModelStartTimeFormatted, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelEndDateFormatted(LiveData<String> ViewModelEndDateFormatted, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelStartDateFormatted(LiveData<String> ViewModelStartDateFormatted, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCanSubmit(CombinedLiveData<Boolean> ViewModelCanSubmit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelGetTpsTimeUseCaseIsDataExist(LiveData<Boolean> ViewModelGetTpsTimeUseCaseIsDataExist, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelAddTpsTimeUseCaseIsLoading(LiveData<Boolean> ViewModelAddTpsTimeUseCaseIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSuccessMessage(CombinedLiveData<String> ViewModelSuccessMessage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFormInstruction(CombinedLiveData<String> ViewModelFormInstruction, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelGetTpsTimeUseCaseIsLoading(LiveData<Boolean> ViewModelGetTpsTimeUseCaseIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_KB;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelGetTpsTimeUseCaseData(LiveData<TpsTime> ViewModelGetTpsTimeUseCaseData, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2048;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:226:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0248  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 897
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentTpsTimeBindingImpl.executeBindings():void");
    }
}
