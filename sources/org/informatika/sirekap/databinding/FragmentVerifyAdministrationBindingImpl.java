package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.FormC1AdministrationComplete;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel;

/* loaded from: classes2.dex */
public class FragmentVerifyAdministrationBindingImpl extends FragmentVerifyAdministrationBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final CircularProgressIndicator mboundView1;
    private final ConstraintLayout mboundView11;
    private final ImageView mboundView13;
    private final TextView mboundView15;
    private final TextView mboundView16;
    private final LinearLayout mboundView18;
    private final ConstraintLayout mboundView2;
    private final LinearLayout mboundView20;
    private final LinearLayout mboundView23;
    private final LinearLayout mboundView25;
    private final CircularProgressIndicator mboundView27;
    private final LinearLayout mboundView28;
    private final LinearLayout mboundView29;
    private final MaterialCardView mboundView3;
    private final TextView mboundView30;
    private final LinearLayout mboundView4;
    private final LinearLayout mboundView5;
    private final LinearLayout mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.buton_retry, 31);
        sparseIntArray.put(R.id.icon_container, 32);
    }

    public FragmentVerifyAdministrationBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 33, sIncludes, sViewsWithIds));
    }

    private FragmentVerifyAdministrationBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 12, (Button) bindings[31], (Button) bindings[10], (Button) bindings[9], (MaterialCardView) bindings[12], (MaterialCardView) bindings[17], (ImageView) bindings[32], (TextView) bindings[14], (RecyclerView) bindings[24], (RecyclerView) bindings[19], (RecyclerView) bindings[21], (RecyclerView) bindings[22], (Button) bindings[26]);
        this.mDirtyFlags = -1L;
        this.buttonContinueVerify.setTag(null);
        this.buttonRetakePhoto.setTag(null);
        this.c1ImageViewCard.setTag(null);
        this.cardInfo.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[1];
        this.mboundView1 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[11];
        this.mboundView11 = constraintLayout2;
        constraintLayout2.setTag(null);
        ImageView imageView = (ImageView) bindings[13];
        this.mboundView13 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[15];
        this.mboundView15 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[16];
        this.mboundView16 = textView2;
        textView2.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[18];
        this.mboundView18 = linearLayout;
        linearLayout.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout3;
        constraintLayout3.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[20];
        this.mboundView20 = linearLayout2;
        linearLayout2.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[23];
        this.mboundView23 = linearLayout3;
        linearLayout3.setTag(null);
        LinearLayout linearLayout4 = (LinearLayout) bindings[25];
        this.mboundView25 = linearLayout4;
        linearLayout4.setTag(null);
        CircularProgressIndicator circularProgressIndicator2 = (CircularProgressIndicator) bindings[27];
        this.mboundView27 = circularProgressIndicator2;
        circularProgressIndicator2.setTag(null);
        LinearLayout linearLayout5 = (LinearLayout) bindings[28];
        this.mboundView28 = linearLayout5;
        linearLayout5.setTag(null);
        LinearLayout linearLayout6 = (LinearLayout) bindings[29];
        this.mboundView29 = linearLayout6;
        linearLayout6.setTag(null);
        MaterialCardView materialCardView = (MaterialCardView) bindings[3];
        this.mboundView3 = materialCardView;
        materialCardView.setTag(null);
        TextView textView3 = (TextView) bindings[30];
        this.mboundView30 = textView3;
        textView3.setTag(null);
        LinearLayout linearLayout7 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout7;
        linearLayout7.setTag(null);
        LinearLayout linearLayout8 = (LinearLayout) bindings[5];
        this.mboundView5 = linearLayout8;
        linearLayout8.setTag(null);
        LinearLayout linearLayout9 = (LinearLayout) bindings[6];
        this.mboundView6 = linearLayout9;
        linearLayout9.setTag(null);
        TextView textView4 = (TextView) bindings[7];
        this.mboundView7 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[8];
        this.mboundView8 = textView5;
        textView5.setTag(null);
        this.photoDescription.setTag(null);
        this.recyclerViewDataDisabilitas.setTag(null);
        this.recyclerViewDataPemilih.setTag(null);
        this.recyclerViewDataPengguna.setTag(null);
        this.recyclerViewDataSurat.setTag(null);
        this.submitButton.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16384L;
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
            setViewModel((VerifyAdministrationViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentVerifyAdministrationBinding
    public void setGetElectionPageUseCase(GetElectionPageUseCase GetElectionPageUseCase) {
        this.mGetElectionPageUseCase = GetElectionPageUseCase;
        synchronized (this) {
            this.mDirtyFlags |= 4096;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentVerifyAdministrationBinding
    public void setViewModel(VerifyAdministrationViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8192;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelIsTableChecked((LiveData) object, fieldId);
            case 1:
                return onChangeViewModelPreviewImagePath((MutableLiveData) object, fieldId);
            case 2:
                return onChangeViewModelFormC1Kesesuaian((LiveData) object, fieldId);
            case 3:
                return onChangeViewModelFormC1Administration((LiveData) object, fieldId);
            case 4:
                return onChangeGetElectionPageUseCaseElectionPageWithRelation((LiveData) object, fieldId);
            case 5:
                return onChangeGetElectionPageUseCaseElection((LiveData) object, fieldId);
            case 6:
                return onChangeViewModelIsLoading((LiveData) object, fieldId);
            case 7:
                return onChangeViewModelFormC1Error((LiveData) object, fieldId);
            case 8:
                return onChangeGetElectionPageUseCaseElectionPage((LiveData) object, fieldId);
            case 9:
                return onChangeViewModelIsLoadingVerification((LiveData) object, fieldId);
            case 10:
                return onChangeViewModelIsError((LiveData) object, fieldId);
            case 11:
                return onChangeViewModelGetElectionPageUseCaseIsPhotoLimitReached((LiveData) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelIsTableChecked(LiveData<Boolean> ViewModelIsTableChecked, int fieldId) {
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

    private boolean onChangeViewModelFormC1Kesesuaian(LiveData<FormC1Kesesuaian> ViewModelFormC1Kesesuaian, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFormC1Administration(LiveData<FormC1AdministrationComplete> ViewModelFormC1Administration, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseElectionPageWithRelation(LiveData<ElectionPageWithRelation> GetElectionPageUseCaseElectionPageWithRelation, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseElection(LiveData<Election> GetElectionPageUseCaseElection, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoading(LiveData<Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFormC1Error(LiveData<FormC1Error> ViewModelFormC1Error, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseElectionPage(LiveData<ElectionPage> GetElectionPageUseCaseElectionPage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoadingVerification(LiveData<Boolean> ViewModelIsLoadingVerification, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsError(LiveData<Boolean> ViewModelIsError, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_KB;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelGetElectionPageUseCaseIsPhotoLimitReached(LiveData<Boolean> ViewModelGetElectionPageUseCaseIsPhotoLimitReached, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2048;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:321:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:471:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0308  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 1288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentVerifyAdministrationBindingImpl.executeBindings():void");
    }
}
