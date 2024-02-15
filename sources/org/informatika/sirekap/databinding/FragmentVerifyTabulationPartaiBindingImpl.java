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
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.model.FormC1TabulationPartaiComplete;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel;

/* loaded from: classes2.dex */
public class FragmentVerifyTabulationPartaiBindingImpl extends FragmentVerifyTabulationPartaiBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final CircularProgressIndicator mboundView1;
    private final ConstraintLayout mboundView11;
    private final ImageView mboundView13;
    private final TextView mboundView15;
    private final TextView mboundView16;
    private final MaterialCardView mboundView18;
    private final TextView mboundView19;
    private final ConstraintLayout mboundView2;
    private final LinearLayout mboundView21;
    private final CircularProgressIndicator mboundView23;
    private final LinearLayout mboundView24;
    private final LinearLayout mboundView25;
    private final TextView mboundView26;
    private final MaterialCardView mboundView3;
    private final LinearLayout mboundView4;
    private final LinearLayout mboundView5;
    private final LinearLayout mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.buton_retry, 27);
        sparseIntArray.put(R.id.icon_container, 28);
    }

    public FragmentVerifyTabulationPartaiBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 29, sIncludes, sViewsWithIds));
    }

    private FragmentVerifyTabulationPartaiBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 14, (Button) bindings[27], (Button) bindings[10], (Button) bindings[9], (MaterialCardView) bindings[12], (MaterialCardView) bindings[17], (ImageView) bindings[28], (TextView) bindings[14], (RecyclerView) bindings[20], (Button) bindings[22]);
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
        MaterialCardView materialCardView = (MaterialCardView) bindings[18];
        this.mboundView18 = materialCardView;
        materialCardView.setTag(null);
        TextView textView3 = (TextView) bindings[19];
        this.mboundView19 = textView3;
        textView3.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout3;
        constraintLayout3.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[21];
        this.mboundView21 = linearLayout;
        linearLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator2 = (CircularProgressIndicator) bindings[23];
        this.mboundView23 = circularProgressIndicator2;
        circularProgressIndicator2.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[24];
        this.mboundView24 = linearLayout2;
        linearLayout2.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[25];
        this.mboundView25 = linearLayout3;
        linearLayout3.setTag(null);
        TextView textView4 = (TextView) bindings[26];
        this.mboundView26 = textView4;
        textView4.setTag(null);
        MaterialCardView materialCardView2 = (MaterialCardView) bindings[3];
        this.mboundView3 = materialCardView2;
        materialCardView2.setTag(null);
        LinearLayout linearLayout4 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout4;
        linearLayout4.setTag(null);
        LinearLayout linearLayout5 = (LinearLayout) bindings[5];
        this.mboundView5 = linearLayout5;
        linearLayout5.setTag(null);
        LinearLayout linearLayout6 = (LinearLayout) bindings[6];
        this.mboundView6 = linearLayout6;
        linearLayout6.setTag(null);
        TextView textView5 = (TextView) bindings[7];
        this.mboundView7 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[8];
        this.mboundView8 = textView6;
        textView6.setTag(null);
        this.photoDescription.setTag(null);
        this.recyclerViewDataPerolehanSuara.setTag(null);
        this.submitButton.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 65536L;
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
            setViewModel((VerifyTabulationPartaiViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentVerifyTabulationPartaiBinding
    public void setGetElectionPageUseCase(GetElectionPageUseCase GetElectionPageUseCase) {
        this.mGetElectionPageUseCase = GetElectionPageUseCase;
        synchronized (this) {
            this.mDirtyFlags |= 16384;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentVerifyTabulationPartaiBinding
    public void setViewModel(VerifyTabulationPartaiViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 32768;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelIsTablePerolehanSuaraEmpty((LiveData) object, fieldId);
            case 1:
                return onChangeViewModelIsTableChecked((LiveData) object, fieldId);
            case 2:
                return onChangeViewModelPreviewImagePath((MutableLiveData) object, fieldId);
            case 3:
                return onChangeViewModelFormC1Kesesuaian((LiveData) object, fieldId);
            case 4:
                return onChangeGetElectionPageUseCaseElectionPageWithRelation((LiveData) object, fieldId);
            case 5:
                return onChangeGetElectionPageUseCaseElection((LiveData) object, fieldId);
            case 6:
                return onChangeViewModelIsLoading((LiveData) object, fieldId);
            case 7:
                return onChangeViewModelTablePerolehanSuaraTitle((LiveData) object, fieldId);
            case 8:
                return onChangeViewModelFormC1Error((LiveData) object, fieldId);
            case 9:
                return onChangeGetElectionPageUseCaseElectionPage((LiveData) object, fieldId);
            case 10:
                return onChangeViewModelIsLoadingVerification((LiveData) object, fieldId);
            case 11:
                return onChangeViewModelFormC1TabulationPartaiComplete((LiveData) object, fieldId);
            case 12:
                return onChangeViewModelIsError((LiveData) object, fieldId);
            case 13:
                return onChangeViewModelGetElectionPageUseCaseIsPhotoLimitReached((LiveData) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelIsTablePerolehanSuaraEmpty(LiveData<Boolean> ViewModelIsTablePerolehanSuaraEmpty, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsTableChecked(LiveData<Boolean> ViewModelIsTableChecked, int fieldId) {
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

    private boolean onChangeViewModelFormC1Kesesuaian(LiveData<FormC1Kesesuaian> ViewModelFormC1Kesesuaian, int fieldId) {
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

    private boolean onChangeViewModelTablePerolehanSuaraTitle(LiveData<String> ViewModelTablePerolehanSuaraTitle, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFormC1Error(LiveData<FormC1Error> ViewModelFormC1Error, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseElectionPage(LiveData<ElectionPage> GetElectionPageUseCaseElectionPage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoadingVerification(LiveData<Boolean> ViewModelIsLoadingVerification, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_KB;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFormC1TabulationPartaiComplete(LiveData<FormC1TabulationPartaiComplete> ViewModelFormC1TabulationPartaiComplete, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2048;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsError(LiveData<Boolean> ViewModelIsError, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelGetElectionPageUseCaseIsPhotoLimitReached(LiveData<Boolean> ViewModelGetElectionPageUseCaseIsPhotoLimitReached, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:347:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:540:0x0393  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 1458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentVerifyTabulationPartaiBindingImpl.executeBindings():void");
    }
}
