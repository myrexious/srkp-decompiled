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
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.sendImage.SendImageViewModel;

/* loaded from: classes2.dex */
public class FragmentSendImageBindingImpl extends FragmentSendImageBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;
    private final ImageView mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.sendImageFragment_loading, 9);
        sparseIntArray.put(R.id.card_image, 10);
        sparseIntArray.put(R.id.icon_container, 11);
        sparseIntArray.put(R.id.label_photo_instruction, 12);
        sparseIntArray.put(R.id.button_send_photo, 13);
        sparseIntArray.put(R.id.button_retake_photo, 14);
        sparseIntArray.put(R.id.button_delete_photo, 15);
    }

    public FragmentSendImageBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private FragmentSendImageBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (Button) bindings[15], (Button) bindings[14], (Button) bindings[13], (MaterialCardView) bindings[4], (MaterialCardView) bindings[10], (MaterialCardView) bindings[5], (MaterialCardView) bindings[6], (MaterialCardView) bindings[8], (MaterialCardView) bindings[7], (ImageView) bindings[11], (TextView) bindings[12], (ScrollView) bindings[2], (CircularProgressIndicator) bindings[9]);
        this.mDirtyFlags = -1L;
        this.cardFailed.setTag(null);
        this.cardInstruction.setTag(null);
        this.cardLoading.setTag(null);
        this.cardMaxRetryReached.setTag(null);
        this.cardSuccess.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout2;
        constraintLayout2.setTag(null);
        ImageView imageView = (ImageView) bindings[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        this.scrollView2.setTag(null);
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
        if (16 == variableId) {
            setGetElectionPageUseCase((GetElectionPageUseCase) variable);
        } else if (87 != variableId) {
            return false;
        } else {
            setViewModel((SendImageViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentSendImageBinding
    public void setGetElectionPageUseCase(GetElectionPageUseCase GetElectionPageUseCase) {
        this.mGetElectionPageUseCase = GetElectionPageUseCase;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentSendImageBinding
    public void setViewModel(SendImageViewModel ViewModel) {
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
                    return false;
                }
                return onChangeGetElectionPageUseCaseElectionPageWithRelation((LiveData) object, fieldId);
            }
            return onChangeGetElectionPageUseCaseElectionPage((LiveData) object, fieldId);
        }
        return onChangeViewModelCorrectingImage((MutableLiveData) object, fieldId);
    }

    private boolean onChangeViewModelCorrectingImage(MutableLiveData<Boolean> ViewModelCorrectingImage, int fieldId) {
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

    /* JADX WARN: Removed duplicated region for block: B:118:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0044  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentSendImageBindingImpl.executeBindings():void");
    }
}
