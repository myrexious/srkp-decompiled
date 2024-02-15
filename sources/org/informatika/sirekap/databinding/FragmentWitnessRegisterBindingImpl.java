package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterFormState;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterFormUseCase;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel;

/* loaded from: classes2.dex */
public class FragmentWitnessRegisterBindingImpl extends FragmentWitnessRegisterBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private InverseBindingListener idPaslonDpdandroidTextAttrChanged;
    private InverseBindingListener idPaslonDprandroidTextAttrChanged;
    private InverseBindingListener idPaslonDprdkandroidTextAttrChanged;
    private InverseBindingListener idPaslonDprdpandroidTextAttrChanged;
    private InverseBindingListener idPaslonPilgubandroidTextAttrChanged;
    private InverseBindingListener idPaslonPilwalkotandroidTextAttrChanged;
    private InverseBindingListener idPaslonPresidenandroidTextAttrChanged;
    private InverseBindingListener jenisSaksiPemilihanDpdandroidCheckedAttrChanged;
    private InverseBindingListener jenisSaksiPemilihanDprandroidCheckedAttrChanged;
    private InverseBindingListener jenisSaksiPemilihanDprdkandroidCheckedAttrChanged;
    private InverseBindingListener jenisSaksiPemilihanDprdpandroidCheckedAttrChanged;
    private InverseBindingListener jenisSaksiPemilihanPilgubandroidCheckedAttrChanged;
    private InverseBindingListener jenisSaksiPemilihanPilwabupandroidCheckedAttrChanged;
    private InverseBindingListener jenisSaksiPemilihanPresidenandroidCheckedAttrChanged;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    private long mDirtyFlags_2;
    private long mDirtyFlags_3;
    private final ScrollView mboundView0;
    private final ConstraintLayout mboundView1;
    private InverseBindingListener nameandroidTextAttrChanged;
    private InverseBindingListener nikandroidTextAttrChanged;
    private InverseBindingListener noHandphoneandroidTextAttrChanged;
    private InverseBindingListener perwakilanandroidTextAttrChanged;

    public FragmentWitnessRegisterBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 36, sIncludes, sViewsWithIds));
    }

    private FragmentWitnessRegisterBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 10, (Button) bindings[33], (CircularProgressIndicator) bindings[34], (MaterialAutoCompleteTextView) bindings[31], (MaterialAutoCompleteTextView) bindings[22], (MaterialAutoCompleteTextView) bindings[28], (MaterialAutoCompleteTextView) bindings[25], (MaterialAutoCompleteTextView) bindings[16], (MaterialAutoCompleteTextView) bindings[19], (MaterialAutoCompleteTextView) bindings[13], (CheckBox) bindings[29], (TextInputLayout) bindings[30], (CheckBox) bindings[20], (TextInputLayout) bindings[21], (CheckBox) bindings[26], (TextInputLayout) bindings[27], (CheckBox) bindings[23], (TextInputLayout) bindings[24], (TextView) bindings[32], (CheckBox) bindings[14], (TextInputLayout) bindings[15], (CheckBox) bindings[17], (TextInputLayout) bindings[18], (CheckBox) bindings[11], (TextInputLayout) bindings[12], (TextView) bindings[10], (TextInputEditText) bindings[3], (TextInputEditText) bindings[5], (TextInputEditText) bindings[7], (MaterialAutoCompleteTextView) bindings[9], (CircularProgressIndicator) bindings[35], (TextInputLayout) bindings[2], (TextInputLayout) bindings[4], (TextInputLayout) bindings[8], (TextInputLayout) bindings[6]);
        this.idPaslonDpdandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.idPaslonDpd);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setIdPaslonDpd(textString);
                    }
                }
            }
        };
        this.idPaslonDprandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.idPaslonDpr);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setIdPartaiDpr(textString);
                    }
                }
            }
        };
        this.idPaslonDprdkandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.idPaslonDprdk);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setIdPartaiDprdk(textString);
                    }
                }
            }
        };
        this.idPaslonDprdpandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.4
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.idPaslonDprdp);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setIdPartaiDprdp(textString);
                    }
                }
            }
        };
        this.idPaslonPilgubandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.5
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.idPaslonPilgub);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setIdPaslonPilgub(textString);
                    }
                }
            }
        };
        this.idPaslonPilwalkotandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.6
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.idPaslonPilwalkot);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setIdPaslonPilwalkot(textString);
                    }
                }
            }
        };
        this.idPaslonPresidenandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.7
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.idPaslonPresiden);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setIdPaslonPresiden(textString);
                    }
                }
            }
        };
        this.jenisSaksiPemilihanDpdandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.8
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = FragmentWitnessRegisterBindingImpl.this.jenisSaksiPemilihanDpd.isChecked();
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setJenisPemilihanDpd(isChecked);
                    }
                }
            }
        };
        this.jenisSaksiPemilihanDprandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.9
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = FragmentWitnessRegisterBindingImpl.this.jenisSaksiPemilihanDpr.isChecked();
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setJenisPemilihanDpr(isChecked);
                    }
                }
            }
        };
        this.jenisSaksiPemilihanDprdkandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.10
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = FragmentWitnessRegisterBindingImpl.this.jenisSaksiPemilihanDprdk.isChecked();
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setJenisPemilihanDprdk(isChecked);
                    }
                }
            }
        };
        this.jenisSaksiPemilihanDprdpandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.11
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = FragmentWitnessRegisterBindingImpl.this.jenisSaksiPemilihanDprdp.isChecked();
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setJenisPemilihanDprdp(isChecked);
                    }
                }
            }
        };
        this.jenisSaksiPemilihanPilgubandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.12
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = FragmentWitnessRegisterBindingImpl.this.jenisSaksiPemilihanPilgub.isChecked();
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setJenisPemilihanPkwkp(isChecked);
                    }
                }
            }
        };
        this.jenisSaksiPemilihanPilwabupandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.13
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = FragmentWitnessRegisterBindingImpl.this.jenisSaksiPemilihanPilwabup.isChecked();
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setJenisPemilihanPkwkk(isChecked);
                    }
                }
            }
        };
        this.jenisSaksiPemilihanPresidenandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.14
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = FragmentWitnessRegisterBindingImpl.this.jenisSaksiPemilihanPresiden.isChecked();
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setJenisPemilihanPresiden(isChecked);
                    }
                }
            }
        };
        this.nameandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.15
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.name);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setName(textString);
                    }
                }
            }
        };
        this.nikandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.16
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.nik);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setNik(textString);
                    }
                }
            }
        };
        this.noHandphoneandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.17
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.noHandphone);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setNoHandphone(textString);
                    }
                }
            }
        };
        this.perwakilanandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.18
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentWitnessRegisterBindingImpl.this.perwakilan);
                WitnessRegisterFormUseCase witnessRegisterFormUseCase = FragmentWitnessRegisterBindingImpl.this.mFormState;
                if (witnessRegisterFormUseCase != null) {
                    WitnessRegisterFormState form = witnessRegisterFormUseCase.getForm();
                    if (form != null) {
                        form.setJenisPemeriksa(textString);
                    }
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.mDirtyFlags_1 = -1L;
        this.mDirtyFlags_2 = -1L;
        this.mDirtyFlags_3 = -1L;
        this.buttonRegister.setTag(null);
        this.circularProgressIndicator.setTag(null);
        this.idPaslonDpd.setTag(null);
        this.idPaslonDpr.setTag(null);
        this.idPaslonDprdk.setTag(null);
        this.idPaslonDprdp.setTag(null);
        this.idPaslonPilgub.setTag(null);
        this.idPaslonPilwalkot.setTag(null);
        this.idPaslonPresiden.setTag(null);
        this.jenisSaksiPemilihanDpd.setTag(null);
        this.jenisSaksiPemilihanDpdSelect.setTag(null);
        this.jenisSaksiPemilihanDpr.setTag(null);
        this.jenisSaksiPemilihanDprSelect.setTag(null);
        this.jenisSaksiPemilihanDprdk.setTag(null);
        this.jenisSaksiPemilihanDprdkSelect.setTag(null);
        this.jenisSaksiPemilihanDprdp.setTag(null);
        this.jenisSaksiPemilihanDprdpSelect.setTag(null);
        this.jenisSaksiPemilihanError.setTag(null);
        this.jenisSaksiPemilihanPilgub.setTag(null);
        this.jenisSaksiPemilihanPilgubSelect.setTag(null);
        this.jenisSaksiPemilihanPilwabup.setTag(null);
        this.jenisSaksiPemilihanPilwabupSelect.setTag(null);
        this.jenisSaksiPemilihanPresiden.setTag(null);
        this.jenisSaksiPemilihanPresidenSelect.setTag(null);
        this.jenisSaksiPemilihanTitle.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag(null);
        this.name.setTag(null);
        this.nik.setTag(null);
        this.noHandphone.setTag(null);
        this.perwakilan.setTag(null);
        this.progressBar.setTag(null);
        this.registerWitnessName.setTag(null);
        this.registerWitnessNik.setTag(null);
        this.witnessRegisterJenis.setTag(null);
        this.witnessRegsiterHandphone.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 72057594037927936L;
            this.mDirtyFlags_1 = 0L;
            this.mDirtyFlags_2 = 0L;
            this.mDirtyFlags_3 = 0L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags == 0 && this.mDirtyFlags_1 == 0 && this.mDirtyFlags_2 == 0 && this.mDirtyFlags_3 == 0) {
                return false;
            }
            return true;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (87 == variableId) {
            setViewModel((WitnessRegisterViewModel) variable);
        } else if (14 != variableId) {
            return false;
        } else {
            setFormState((WitnessRegisterFormUseCase) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentWitnessRegisterBinding
    public void setViewModel(WitnessRegisterViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= FileUtils.ONE_KB;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentWitnessRegisterBinding
    public void setFormState(WitnessRegisterFormUseCase FormState) {
        this.mFormState = FormState;
        synchronized (this) {
            this.mDirtyFlags |= 2048;
        }
        notifyPropertyChanged(14);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeFormStateIsDprShown((CombinedLiveData) object, fieldId);
            case 1:
                return onChangeFormStateIsDpdShown((CombinedLiveData) object, fieldId);
            case 2:
                return onChangeFormStateForm((WitnessRegisterFormState) object, fieldId);
            case 3:
                return onChangeFormStateIsDprdpShown((CombinedLiveData) object, fieldId);
            case 4:
                return onChangeFormStateIsDprdkShown((CombinedLiveData) object, fieldId);
            case 5:
                return onChangeFormStateIsPilwalkotShown((CombinedLiveData) object, fieldId);
            case 6:
                return onChangeFormStateIsPilgubShown((CombinedLiveData) object, fieldId);
            case 7:
                return onChangeFormStateIsLoadingForm((LiveData) object, fieldId);
            case 8:
                return onChangeViewModelIsLoading((LiveData) object, fieldId);
            case 9:
                return onChangeFormStateIsPresidenShown((CombinedLiveData) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeFormStateIsDprShown(CombinedLiveData<Boolean> FormStateIsDprShown, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFormStateIsDpdShown(CombinedLiveData<Boolean> FormStateIsDpdShown, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFormStateForm(WitnessRegisterFormState FormStateForm, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (fieldId == 67) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        } else if (fieldId == 68) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (fieldId == 66) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        } else if (fieldId == 70) {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        } else if (fieldId == 71) {
            synchronized (this) {
                this.mDirtyFlags |= 65536;
            }
            return true;
        } else if (fieldId == 69) {
            synchronized (this) {
                this.mDirtyFlags |= 131072;
            }
            return true;
        } else if (fieldId == 73) {
            synchronized (this) {
                this.mDirtyFlags |= 262144;
            }
            return true;
        } else if (fieldId == 74) {
            synchronized (this) {
                this.mDirtyFlags |= 524288;
            }
            return true;
        } else if (fieldId == 72) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_MB;
            }
            return true;
        } else if (fieldId == 51) {
            synchronized (this) {
                this.mDirtyFlags |= 2097152;
            }
            return true;
        } else if (fieldId == 52) {
            synchronized (this) {
                this.mDirtyFlags |= 4194304;
            }
            return true;
        } else if (fieldId == 49) {
            synchronized (this) {
                this.mDirtyFlags |= 8388608;
            }
            return true;
        } else if (fieldId == 53) {
            synchronized (this) {
                this.mDirtyFlags |= 16777216;
            }
            return true;
        } else if (fieldId == 60) {
            synchronized (this) {
                this.mDirtyFlags |= 33554432;
            }
            return true;
        } else if (fieldId == 41) {
            synchronized (this) {
                this.mDirtyFlags |= 67108864;
            }
            return true;
        } else if (fieldId == 42) {
            synchronized (this) {
                this.mDirtyFlags |= 134217728;
            }
            return true;
        } else if (fieldId == 40) {
            synchronized (this) {
                this.mDirtyFlags |= 268435456;
            }
            return true;
        } else if (fieldId == 59) {
            synchronized (this) {
                this.mDirtyFlags |= 536870912;
            }
            return true;
        } else if (fieldId == 35) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_GB;
            }
            return true;
        } else if (fieldId == 36) {
            synchronized (this) {
                this.mDirtyFlags |= 2147483648L;
            }
            return true;
        } else if (fieldId == 34) {
            synchronized (this) {
                this.mDirtyFlags |= 4294967296L;
            }
            return true;
        } else if (fieldId == 58) {
            synchronized (this) {
                this.mDirtyFlags |= 8589934592L;
            }
            return true;
        } else if (fieldId == 38) {
            synchronized (this) {
                this.mDirtyFlags |= 17179869184L;
            }
            return true;
        } else if (fieldId == 39) {
            synchronized (this) {
                this.mDirtyFlags |= 34359738368L;
            }
            return true;
        } else if (fieldId == 37) {
            synchronized (this) {
                this.mDirtyFlags |= 68719476736L;
            }
            return true;
        } else if (fieldId == 55) {
            synchronized (this) {
                this.mDirtyFlags |= 137438953472L;
            }
            return true;
        } else if (fieldId == 23) {
            synchronized (this) {
                this.mDirtyFlags |= 274877906944L;
            }
            return true;
        } else if (fieldId == 24) {
            synchronized (this) {
                this.mDirtyFlags |= 549755813888L;
            }
            return true;
        } else if (fieldId == 22) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_TB;
            }
            return true;
        } else if (fieldId == 57) {
            synchronized (this) {
                this.mDirtyFlags |= 2199023255552L;
            }
            return true;
        } else if (fieldId == 29) {
            synchronized (this) {
                this.mDirtyFlags |= 4398046511104L;
            }
            return true;
        } else if (fieldId == 30) {
            synchronized (this) {
                this.mDirtyFlags |= 8796093022208L;
            }
            return true;
        } else if (fieldId == 28) {
            synchronized (this) {
                this.mDirtyFlags |= 17592186044416L;
            }
            return true;
        } else if (fieldId == 56) {
            synchronized (this) {
                this.mDirtyFlags |= 35184372088832L;
            }
            return true;
        } else if (fieldId == 26) {
            synchronized (this) {
                this.mDirtyFlags |= 70368744177664L;
            }
            return true;
        } else if (fieldId == 27) {
            synchronized (this) {
                this.mDirtyFlags |= 140737488355328L;
            }
            return true;
        } else if (fieldId == 25) {
            synchronized (this) {
                this.mDirtyFlags |= 281474976710656L;
            }
            return true;
        } else if (fieldId == 54) {
            synchronized (this) {
                this.mDirtyFlags |= 562949953421312L;
            }
            return true;
        } else if (fieldId == 32) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_PB;
            }
            return true;
        } else if (fieldId == 33) {
            synchronized (this) {
                this.mDirtyFlags |= 2251799813685248L;
            }
            return true;
        } else if (fieldId == 31) {
            synchronized (this) {
                this.mDirtyFlags |= 4503599627370496L;
            }
            return true;
        } else if (fieldId == 61) {
            synchronized (this) {
                this.mDirtyFlags |= 9007199254740992L;
            }
            return true;
        } else if (fieldId == 62) {
            synchronized (this) {
                this.mDirtyFlags |= 18014398509481984L;
            }
            return true;
        } else if (fieldId == 1) {
            synchronized (this) {
                this.mDirtyFlags |= 36028797018963968L;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean onChangeFormStateIsDprdpShown(CombinedLiveData<Boolean> FormStateIsDprdpShown, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFormStateIsDprdkShown(CombinedLiveData<Boolean> FormStateIsDprdkShown, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFormStateIsPilwalkotShown(CombinedLiveData<Boolean> FormStateIsPilwalkotShown, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFormStateIsPilgubShown(CombinedLiveData<Boolean> FormStateIsPilgubShown, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFormStateIsLoadingForm(LiveData<Boolean> FormStateIsLoadingForm, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoading(LiveData<Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeFormStateIsPresidenShown(CombinedLiveData<Boolean> FormStateIsPresidenShown, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: SSATransform
        java.lang.IndexOutOfBoundsException: bitIndex < 0: -127
        	at java.base/java.util.BitSet.get(Unknown Source)
        	at jadx.core.dex.visitors.ssa.LiveVarAnalysis.fillBasicBlockInfo(LiveVarAnalysis.java:65)
        	at jadx.core.dex.visitors.ssa.LiveVarAnalysis.runAnalysis(LiveVarAnalysis.java:36)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:55)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:41)
        */
    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 6913
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentWitnessRegisterBindingImpl.executeBindings():void");
    }
}
