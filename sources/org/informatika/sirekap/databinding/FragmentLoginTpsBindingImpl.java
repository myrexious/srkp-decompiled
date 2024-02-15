package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LiveData;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.loginTps.LoginTpsFormState;
import org.informatika.sirekap.ui.loginTps.LoginTpsViewModel;
import org.informatika.sirekap.usecase.GetListTpsUseCase;

/* loaded from: classes2.dex */
public class FragmentLoginTpsBindingImpl extends FragmentLoginTpsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final CircularProgressIndicator mboundView1;
    private final ConstraintLayout mboundView2;
    private final ScrollView mboundView4;
    private final CircularProgressIndicator mboundView8;
    private InverseBindingListener tpsInputandroidTextAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title_error, 9);
        sparseIntArray.put(R.id.button_retry, 10);
        sparseIntArray.put(R.id.app_logo, 11);
        sparseIntArray.put(R.id.app_name, 12);
    }

    public FragmentLoginTpsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }

    private FragmentLoginTpsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, (ImageView) bindings[11], (TextView) bindings[12], (Button) bindings[7], (Button) bindings[10], (TextView) bindings[3], (TextView) bindings[9], (TextInputLayout) bindings[5], (MaterialAutoCompleteTextView) bindings[6]);
        this.tpsInputandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentLoginTpsBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentLoginTpsBindingImpl.this.tpsInput);
                LoginTpsFormState loginTpsFormState = FragmentLoginTpsBindingImpl.this.mLoginTpsFormState;
                if (loginTpsFormState != null) {
                    loginTpsFormState.setTps(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.buttonLogin.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[1];
        this.mboundView1 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout2;
        constraintLayout2.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[4];
        this.mboundView4 = scrollView;
        scrollView.setTag(null);
        CircularProgressIndicator circularProgressIndicator2 = (CircularProgressIndicator) bindings[8];
        this.mboundView8 = circularProgressIndicator2;
        circularProgressIndicator2.setTag(null);
        this.subtitleError.setTag(null);
        this.tps.setTag(null);
        this.tpsInput.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4096L;
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
            setViewModel((LoginTpsViewModel) variable);
        } else if (20 == variableId) {
            setGetListTPSUseCase((GetListTpsUseCase) variable);
        } else if (63 != variableId) {
            return false;
        } else {
            setLoginTpsFormState((LoginTpsFormState) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentLoginTpsBinding
    public void setViewModel(LoginTpsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentLoginTpsBinding
    public void setGetListTPSUseCase(GetListTpsUseCase GetListTPSUseCase) {
        this.mGetListTPSUseCase = GetListTPSUseCase;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(20);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentLoginTpsBinding
    public void setLoginTpsFormState(LoginTpsFormState LoginTpsFormState) {
        updateRegistration(5, LoginTpsFormState);
        this.mLoginTpsFormState = LoginTpsFormState;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(63);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                if (localFieldId != 2) {
                    if (localFieldId != 3) {
                        if (localFieldId != 4) {
                            if (localFieldId != 5) {
                                return false;
                            }
                            return onChangeLoginTpsFormState((LoginTpsFormState) object, fieldId);
                        }
                        return onChangeViewModelIsLoading((LiveData) object, fieldId);
                    }
                    return onChangeGetListTPSUseCaseIsSuccess((LiveData) object, fieldId);
                }
                return onChangeGetListTPSUseCaseIsLoading((LiveData) object, fieldId);
            }
            return onChangeGetListTPSUseCaseIsError((LiveData) object, fieldId);
        }
        return onChangeGetListTPSUseCaseErrorMessage((LiveData) object, fieldId);
    }

    private boolean onChangeGetListTPSUseCaseErrorMessage(LiveData<String> GetListTPSUseCaseErrorMessage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetListTPSUseCaseIsError(LiveData<Boolean> GetListTPSUseCaseIsError, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetListTPSUseCaseIsLoading(LiveData<Boolean> GetListTPSUseCaseIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetListTPSUseCaseIsSuccess(LiveData<Boolean> GetListTPSUseCaseIsSuccess, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoading(LiveData<Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeLoginTpsFormState(LoginTpsFormState LoginTpsFormState, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (fieldId == 82) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (fieldId == 83) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (fieldId == 81) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_KB;
            }
            return true;
        } else if (fieldId == 1) {
            synchronized (this) {
                this.mDirtyFlags |= 2048;
            }
            return true;
        } else {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:191:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:321:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 581
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentLoginTpsBindingImpl.executeBindings():void");
    }
}
