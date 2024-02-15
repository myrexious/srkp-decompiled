package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.DataBindingAdaptersKt;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.ui.verify.VerifyFormState;

/* loaded from: classes2.dex */
public class DialogVerifyBodyBindingImpl extends DialogVerifyBodyBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private InverseBindingListener commentandroidTextAttrChanged;
    private long mDirtyFlags;
    private final ScrollView mboundView0;
    private final AppCompatCheckBox mboundView3;
    private InverseBindingListener mboundView3androidCheckedAttrChanged;

    public DialogVerifyBodyBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private DialogVerifyBodyBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2, (TextInputEditText) bindings[2], (TextInputLayout) bindings[1], (Button) bindings[4]);
        this.commentandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.DialogVerifyBodyBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(DialogVerifyBodyBindingImpl.this.comment);
                BaseVerifyViewModel baseVerifyViewModel = DialogVerifyBodyBindingImpl.this.mViewModel;
                if (baseVerifyViewModel != null) {
                    VerifyFormState verifyForm = baseVerifyViewModel.getVerifyForm();
                    if (verifyForm != null) {
                        verifyForm.setComment(textString);
                    }
                }
            }
        };
        this.mboundView3androidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.DialogVerifyBodyBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = DialogVerifyBodyBindingImpl.this.mboundView3.isChecked();
                BaseVerifyViewModel baseVerifyViewModel = DialogVerifyBodyBindingImpl.this.mViewModel;
                if (baseVerifyViewModel != null) {
                    MutableLiveData<Boolean> isConfirmationChecked = baseVerifyViewModel.isConfirmationChecked();
                    if (isConfirmationChecked != null) {
                        isConfirmationChecked.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.comment.setTag(null);
        this.commentContainer.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
        AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) bindings[3];
        this.mboundView3 = appCompatCheckBox;
        appCompatCheckBox.setTag(null);
        this.submitButton.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128L;
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
            setViewModel((BaseVerifyViewModel) variable);
        } else if (21 != variableId) {
            return false;
        } else {
            setHideCommentField((Boolean) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.DialogVerifyBodyBinding
    public void setViewModel(BaseVerifyViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.DialogVerifyBodyBinding
    public void setHideCommentField(Boolean HideCommentField) {
        this.mHideCommentField = HideCommentField;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(21);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                return false;
            }
            return onChangeViewModelIsConfirmationChecked((MutableLiveData) object, fieldId);
        }
        return onChangeViewModelVerifyForm((VerifyFormState) object, fieldId);
    }

    private boolean onChangeViewModelVerifyForm(VerifyFormState ViewModelVerifyForm, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId == 5) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (fieldId == 6) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (fieldId == 4) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean onChangeViewModelIsConfirmationChecked(MutableLiveData<Boolean> ViewModelIsConfirmationChecked, int fieldId) {
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
        VerifyFormState verifyFormState;
        String str;
        boolean z;
        boolean z2;
        boolean z3;
        String str2;
        boolean z4;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        BaseVerifyViewModel baseVerifyViewModel = this.mViewModel;
        Boolean bool = this.mHideCommentField;
        if ((247 & j) != 0) {
            if ((j & 245) != 0) {
                verifyFormState = baseVerifyViewModel != null ? baseVerifyViewModel.getVerifyForm() : null;
                updateRegistration(0, verifyFormState);
                str = ((j & 197) == 0 || verifyFormState == null) ? null : verifyFormState.getComment();
                int i = ((j & 181) > 0L ? 1 : ((j & 181) == 0L ? 0 : -1));
                if (i != 0) {
                    z2 = verifyFormState != null ? verifyFormState.getCommentTouched() : false;
                    if (i != 0) {
                        j = z2 ? j | 512 : j | 256;
                    }
                } else {
                    z2 = false;
                }
            } else {
                z2 = false;
                verifyFormState = null;
                str = null;
            }
            if ((j & 134) != 0) {
                LiveData<?> isConfirmationChecked = baseVerifyViewModel != null ? baseVerifyViewModel.isConfirmationChecked() : null;
                updateLiveDataRegistration(1, isConfirmationChecked);
                z = ViewDataBinding.safeUnbox(isConfirmationChecked != null ? isConfirmationChecked.getValue() : null);
            } else {
                z = false;
            }
        } else {
            verifyFormState = null;
            str = null;
            z = false;
            z2 = false;
        }
        boolean safeUnbox = (j & 136) != 0 ? ViewDataBinding.safeUnbox(bool) : false;
        if ((512 & j) != 0) {
            z3 = !(verifyFormState != null ? verifyFormState.getCommentValid() : false);
        } else {
            z3 = false;
        }
        int i2 = ((j & 181) > 0L ? 1 : ((j & 181) == 0L ? 0 : -1));
        if (i2 != 0) {
            boolean z5 = z2 ? z3 : false;
            if (i2 != 0) {
                j |= z5 ? 2048L : FileUtils.ONE_KB;
            }
            str2 = z5 ? this.commentContainer.getResources().getString(R.string.message_invalid_comment) : null;
            z4 = z5;
        } else {
            str2 = null;
            z4 = false;
        }
        if ((197 & j) != 0) {
            TextViewBindingAdapter.setText(this.comment, str);
        }
        if ((128 & j) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.comment, null, null, null, this.commentandroidTextAttrChanged);
            CompoundButtonBindingAdapter.setListeners(this.mboundView3, null, this.mboundView3androidCheckedAttrChanged);
        }
        if ((j & 136) != 0) {
            DataBindingAdaptersKt.hidden(this.commentContainer, safeUnbox);
        }
        if ((j & 181) != 0) {
            this.commentContainer.setError(str2);
            this.commentContainer.setErrorEnabled(z4);
        }
        if ((j & 134) != 0) {
            CompoundButtonBindingAdapter.setChecked(this.mboundView3, z);
            this.submitButton.setEnabled(z);
        }
    }
}
