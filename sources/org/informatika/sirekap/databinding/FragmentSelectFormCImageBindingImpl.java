package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel;

/* loaded from: classes2.dex */
public class FragmentSelectFormCImageBindingImpl extends FragmentSelectFormCImageBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener inputFieldPageandroidTextAttrChanged;
    private long mDirtyFlags;
    private final ScrollView mboundView0;
    private final ImageView mboundView2;
    private InverseBindingListener radioGroupandroidCheckedButtonAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.icon_container, 6);
        sparseIntArray.put(R.id.card_info, 7);
        sparseIntArray.put(R.id.label_input_election_type, 8);
        sparseIntArray.put(R.id.label_input_page, 9);
        sparseIntArray.put(R.id.input_page, 10);
    }

    public FragmentSelectFormCImageBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private FragmentSelectFormCImageBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (Button) bindings[5], (MaterialCardView) bindings[1], (MaterialCardView) bindings[7], (ImageView) bindings[6], (MaterialAutoCompleteTextView) bindings[4], (TextInputLayout) bindings[10], (TextView) bindings[8], (TextView) bindings[9], (RadioGroup) bindings[3]);
        this.inputFieldPageandroidTextAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentSelectFormCImageBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentSelectFormCImageBindingImpl.this.inputFieldPage);
                SelectFormCImageViewModel selectFormCImageViewModel = FragmentSelectFormCImageBindingImpl.this.mViewModel;
                if (selectFormCImageViewModel != null) {
                    MutableLiveData<String> selectedPage = selectFormCImageViewModel.getSelectedPage();
                    if (selectedPage != null) {
                        selectedPage.setValue(textString);
                    }
                }
            }
        };
        this.radioGroupandroidCheckedButtonAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentSelectFormCImageBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                int checkedRadioButtonId = FragmentSelectFormCImageBindingImpl.this.radioGroup.getCheckedRadioButtonId();
                SelectFormCImageViewModel selectFormCImageViewModel = FragmentSelectFormCImageBindingImpl.this.mViewModel;
                if (selectFormCImageViewModel != null) {
                    MutableLiveData<Integer> selectedJenisPemilihanId = selectFormCImageViewModel.getSelectedJenisPemilihanId();
                    if (selectedJenisPemilihanId != null) {
                        selectedJenisPemilihanId.setValue(Integer.valueOf(checkedRadioButtonId));
                    }
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.buttonContinue.setTag(null);
        this.c1ImageViewCard.setTag(null);
        this.inputFieldPage.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        this.radioGroup.setTag(null);
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
            setViewModel((SelectFormCImageViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentSelectFormCImageBinding
    public void setViewModel(SelectFormCImageViewModel ViewModel) {
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
                        return onChangeViewModelCanSelectPage((LiveData) object, fieldId);
                    }
                    return onChangeViewModelSelectedJenisPemilihanId((MutableLiveData) object, fieldId);
                }
                return onChangeViewModelSelectedPage((MutableLiveData) object, fieldId);
            }
            return onChangeViewModelPreviewImagePath((MutableLiveData) object, fieldId);
        }
        return onChangeViewModelCanSubmit((LiveData) object, fieldId);
    }

    private boolean onChangeViewModelCanSubmit(LiveData<Boolean> ViewModelCanSubmit, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelPreviewImagePath(MutableLiveData<String> ViewModelPreviewImagePath, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSelectedPage(MutableLiveData<String> ViewModelSelectedPage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSelectedJenisPemilihanId(MutableLiveData<Integer> ViewModelSelectedJenisPemilihanId, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelCanSelectPage(LiveData<Boolean> ViewModelCanSelectPage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x005b, code lost:
        if (r11 == null) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x00c9  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentSelectFormCImageBindingImpl.executeBindings():void");
    }
}
