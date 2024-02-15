package org.informatika.sirekap.databinding;

import android.graphics.Bitmap;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.ui.verify.wizard.FormC1CheckItem;
import org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel;

/* loaded from: classes2.dex */
public class ViewVerifyWizardOngoingBindingImpl extends ViewVerifyWizardOngoingBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener inputCorrectionandroidTextAttrChanged;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.button_check_false, 14);
        sparseIntArray.put(R.id.button_check_true, 15);
        sparseIntArray.put(R.id.button_check_cancel, 16);
        sparseIntArray.put(R.id.button_check_ok, 17);
    }

    public ViewVerifyWizardOngoingBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds));
    }

    private ViewVerifyWizardOngoingBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (Button) bindings[11], (MaterialButton) bindings[16], (MaterialButton) bindings[14], (MaterialButton) bindings[17], (MaterialButton) bindings[15], (Button) bindings[13], (Button) bindings[12], (MaterialButtonToggleGroup) bindings[6], (MaterialButtonToggleGroup) bindings[10], (TextView) bindings[4], (ImageView) bindings[1], (TextInputEditText) bindings[9], (TextView) bindings[3], (TextInputLayout) bindings[8], (TextView) bindings[2], (TextView) bindings[7], (TextView) bindings[5]);
        this.inputCorrectionandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.ViewVerifyWizardOngoingBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ViewVerifyWizardOngoingBindingImpl.this.inputCorrection);
                VerifyWizardViewModel verifyWizardViewModel = ViewVerifyWizardOngoingBindingImpl.this.mViewModel;
                if (verifyWizardViewModel != null) {
                    CombinedLiveData<FormC1CheckItem> currentItem = verifyWizardViewModel.getCurrentItem();
                    if (currentItem != null) {
                        FormC1CheckItem value = currentItem.getValue();
                        if (value != null) {
                            value.setCorrectedValue(textString);
                        }
                    }
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.buttonBack.setTag(null);
        this.buttonFinish.setTag(null);
        this.buttonNext.setTag(null);
        this.buttonToggleCheck.setTag(null);
        this.buttonToggleCheckCorrection.setTag(null);
        this.content.setTag(null);
        this.imageForm.setTag(null);
        this.inputCorrection.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.subtitle.setTag(null);
        this.textInputLayoutCorrection.setTag(null);
        this.title.setTag(null);
        this.titleCorrection.setTag(null);
        this.value.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64L;
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
            setViewModel((VerifyWizardViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ViewVerifyWizardOngoingBinding
    public void setViewModel(VerifyWizardViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32;
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
                        if (localFieldId != 4) {
                            return false;
                        }
                        return onChangeViewModelFirstItem((LiveData) object, fieldId);
                    }
                    return onChangeViewModelPreviewBitmap((LiveData) object, fieldId);
                }
                return onChangeViewModelLastItem((CombinedLiveData) object, fieldId);
            }
            return onChangeViewModelCurrentItem((CombinedLiveData) object, fieldId);
        }
        return onChangeViewModelIsCorrectValue((MutableLiveData) object, fieldId);
    }

    private boolean onChangeViewModelIsCorrectValue(MutableLiveData<Boolean> ViewModelIsCorrectValue, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCurrentItem(CombinedLiveData<FormC1CheckItem> ViewModelCurrentItem, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelLastItem(CombinedLiveData<Boolean> ViewModelLastItem, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelPreviewBitmap(LiveData<Bitmap> ViewModelPreviewBitmap, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFirstItem(LiveData<Boolean> ViewModelFirstItem, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:169:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:241:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 567
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.ViewVerifyWizardOngoingBindingImpl.executeBindings():void");
    }
}
