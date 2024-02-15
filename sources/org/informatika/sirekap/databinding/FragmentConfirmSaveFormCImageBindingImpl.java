package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageViewModel;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;

/* loaded from: classes2.dex */
public class FragmentConfirmSaveFormCImageBindingImpl extends FragmentConfirmSaveFormCImageBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ScrollView mboundView0;
    private final ImageView mboundView2;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.icon_container, 11);
        sparseIntArray.put(R.id.title, 12);
        sparseIntArray.put(R.id.button_save, 13);
        sparseIntArray.put(R.id.button_retry, 14);
        sparseIntArray.put(R.id.button_back, 15);
    }

    public FragmentConfirmSaveFormCImageBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private FragmentConfirmSaveFormCImageBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4, (Button) bindings[15], (Button) bindings[14], (Button) bindings[13], (MaterialCardView) bindings[1], (MaterialCardView) bindings[8], (MaterialCardView) bindings[4], (ConstraintLayout) bindings[7], (ImageView) bindings[11], (ConstraintLayout) bindings[3], (TextView) bindings[5], (TextView) bindings[9], (TextView) bindings[6], (TextView) bindings[10], (TextView) bindings[12]);
        this.mDirtyFlags = -1L;
        this.c1ImageViewCard.setTag(null);
        this.cardError.setTag(null);
        this.cardInfo.setTag(null);
        this.errorNormal.setTag(null);
        this.layoutNormal.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        this.textElection.setTag(null);
        this.textElection2.setTag(null);
        this.textPage.setTag(null);
        this.textPage2.setTag(null);
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
        if (16 == variableId) {
            setGetElectionPageUseCase((GetElectionPageUseCase) variable);
        } else if (87 != variableId) {
            return false;
        } else {
            setViewModel((ConfirmSaveFormCImageViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentConfirmSaveFormCImageBinding
    public void setGetElectionPageUseCase(GetElectionPageUseCase GetElectionPageUseCase) {
        this.mGetElectionPageUseCase = GetElectionPageUseCase;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentConfirmSaveFormCImageBinding
    public void setViewModel(ConfirmSaveFormCImageViewModel ViewModel) {
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
                        return false;
                    }
                    return onChangeGetElectionPageUseCaseElection((LiveData) object, fieldId);
                }
                return onChangeGetElectionPageUseCaseElectionPageWithRelation((LiveData) object, fieldId);
            }
            return onChangeGetElectionPageUseCaseElectionPage((LiveData) object, fieldId);
        }
        return onChangeViewModelPreviewImagePath((MutableLiveData) object, fieldId);
    }

    private boolean onChangeViewModelPreviewImagePath(MutableLiveData<String> ViewModelPreviewImagePath, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseElectionPage(LiveData<ElectionPage> GetElectionPageUseCaseElectionPage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseElectionPageWithRelation(LiveData<ElectionPageWithRelation> GetElectionPageUseCaseElectionPageWithRelation, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseElection(LiveData<Election> GetElectionPageUseCaseElection, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x00af  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentConfirmSaveFormCImageBindingImpl.executeBindings():void");
    }
}
