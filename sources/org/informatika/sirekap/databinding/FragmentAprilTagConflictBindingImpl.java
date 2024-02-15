package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictViewModel;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;

/* loaded from: classes2.dex */
public class FragmentAprilTagConflictBindingImpl extends FragmentAprilTagConflictBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ScrollView mboundView0;
    private final ImageView mboundView2;
    private InverseBindingListener radioGroupElectionPageandroidCheckedButtonAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.icon_container, 7);
        sparseIntArray.put(R.id.card_info, 8);
        sparseIntArray.put(R.id.label_input_election_page, 9);
        sparseIntArray.put(R.id.button_retry, 10);
    }

    public FragmentAprilTagConflictBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private FragmentAprilTagConflictBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 5, (Button) bindings[6], (Button) bindings[10], (MaterialCardView) bindings[1], (MaterialCardView) bindings[8], (ImageView) bindings[7], (TextView) bindings[9], (RadioButton) bindings[5], (RadioButton) bindings[4], (RadioGroup) bindings[3]);
        this.radioGroupElectionPageandroidCheckedButtonAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.FragmentAprilTagConflictBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                int checkedRadioButtonId = FragmentAprilTagConflictBindingImpl.this.radioGroupElectionPage.getCheckedRadioButtonId();
                AprilTagConflictViewModel aprilTagConflictViewModel = FragmentAprilTagConflictBindingImpl.this.mViewModel;
                if (aprilTagConflictViewModel != null) {
                    MutableLiveData<Integer> selectedElectionPage = aprilTagConflictViewModel.getSelectedElectionPage();
                    if (selectedElectionPage != null) {
                        selectedElectionPage.setValue(Integer.valueOf(checkedRadioButtonId));
                    }
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.buttonContinue.setTag(null);
        this.c1ImageViewCard.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        this.radioElectionPageAprilTag.setTag(null);
        this.radioElectionPageManual.setTag(null);
        this.radioGroupElectionPage.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
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
        if (17 == variableId) {
            setGetElectionPageUseCaseAprilTag((GetElectionPageUseCase) variable);
        } else if (18 == variableId) {
            setGetElectionPageUseCaseManual((GetElectionPageUseCase) variable);
        } else if (87 != variableId) {
            return false;
        } else {
            setViewModel((AprilTagConflictViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentAprilTagConflictBinding
    public void setGetElectionPageUseCaseAprilTag(GetElectionPageUseCase GetElectionPageUseCaseAprilTag) {
        this.mGetElectionPageUseCaseAprilTag = GetElectionPageUseCaseAprilTag;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(17);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentAprilTagConflictBinding
    public void setGetElectionPageUseCaseManual(GetElectionPageUseCase GetElectionPageUseCaseManual) {
        this.mGetElectionPageUseCaseManual = GetElectionPageUseCaseManual;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(18);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentAprilTagConflictBinding
    public void setViewModel(AprilTagConflictViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
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
                        return onChangeGetElectionPageUseCaseManualElectionPageWithRelation((LiveData) object, fieldId);
                    }
                    return onChangeViewModelSelectedElectionPage((MutableLiveData) object, fieldId);
                }
                return onChangeViewModelPreviewImagePath((MutableLiveData) object, fieldId);
            }
            return onChangeGetElectionPageUseCaseAprilTagElectionPageWithRelation((LiveData) object, fieldId);
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

    private boolean onChangeGetElectionPageUseCaseAprilTagElectionPageWithRelation(LiveData<ElectionPageWithRelation> GetElectionPageUseCaseAprilTagElectionPageWithRelation, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelPreviewImagePath(MutableLiveData<String> ViewModelPreviewImagePath, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSelectedElectionPage(MutableLiveData<Integer> ViewModelSelectedElectionPage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseManualElectionPageWithRelation(LiveData<ElectionPageWithRelation> GetElectionPageUseCaseManualElectionPageWithRelation, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:183:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentAprilTagConflictBindingImpl.executeBindings():void");
    }
}
