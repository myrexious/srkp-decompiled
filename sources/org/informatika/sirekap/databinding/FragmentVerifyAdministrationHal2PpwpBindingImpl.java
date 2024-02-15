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
import org.informatika.sirekap.model.FormC1AdministrationHal2PpwpComplete;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.model.FormC1Kesesuaian;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel;

/* loaded from: classes2.dex */
public class FragmentVerifyAdministrationHal2PpwpBindingImpl extends FragmentVerifyAdministrationHal2PpwpBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final CircularProgressIndicator mboundView1;
    private final ConstraintLayout mboundView10;
    private final ImageView mboundView12;
    private final TextView mboundView14;
    private final TextView mboundView15;
    private final MaterialCardView mboundView17;
    private final TextView mboundView18;
    private final ConstraintLayout mboundView2;
    private final MaterialCardView mboundView20;
    private final LinearLayout mboundView22;
    private final CircularProgressIndicator mboundView24;
    private final LinearLayout mboundView25;
    private final TextView mboundView26;
    private final LinearLayout mboundView27;
    private final TextView mboundView28;
    private final MaterialCardView mboundView3;
    private final LinearLayout mboundView4;
    private final LinearLayout mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.icon_container, 29);
    }

    public FragmentVerifyAdministrationHal2PpwpBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 30, sIncludes, sViewsWithIds));
    }

    private FragmentVerifyAdministrationHal2PpwpBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 15, (Button) bindings[9], (Button) bindings[8], (MaterialCardView) bindings[11], (MaterialCardView) bindings[16], (ImageView) bindings[29], (TextView) bindings[13], (RecyclerView) bindings[19], (RecyclerView) bindings[21], (Button) bindings[23]);
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
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[10];
        this.mboundView10 = constraintLayout2;
        constraintLayout2.setTag(null);
        ImageView imageView = (ImageView) bindings[12];
        this.mboundView12 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[14];
        this.mboundView14 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[15];
        this.mboundView15 = textView2;
        textView2.setTag(null);
        MaterialCardView materialCardView = (MaterialCardView) bindings[17];
        this.mboundView17 = materialCardView;
        materialCardView.setTag(null);
        TextView textView3 = (TextView) bindings[18];
        this.mboundView18 = textView3;
        textView3.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout3;
        constraintLayout3.setTag(null);
        MaterialCardView materialCardView2 = (MaterialCardView) bindings[20];
        this.mboundView20 = materialCardView2;
        materialCardView2.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[22];
        this.mboundView22 = linearLayout;
        linearLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator2 = (CircularProgressIndicator) bindings[24];
        this.mboundView24 = circularProgressIndicator2;
        circularProgressIndicator2.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[25];
        this.mboundView25 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView4 = (TextView) bindings[26];
        this.mboundView26 = textView4;
        textView4.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[27];
        this.mboundView27 = linearLayout3;
        linearLayout3.setTag(null);
        TextView textView5 = (TextView) bindings[28];
        this.mboundView28 = textView5;
        textView5.setTag(null);
        MaterialCardView materialCardView3 = (MaterialCardView) bindings[3];
        this.mboundView3 = materialCardView3;
        materialCardView3.setTag(null);
        LinearLayout linearLayout4 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout4;
        linearLayout4.setTag(null);
        LinearLayout linearLayout5 = (LinearLayout) bindings[5];
        this.mboundView5 = linearLayout5;
        linearLayout5.setTag(null);
        TextView textView6 = (TextView) bindings[6];
        this.mboundView6 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[7];
        this.mboundView7 = textView7;
        textView7.setTag(null);
        this.photoDescription.setTag(null);
        this.recyclerViewDataPerolehanSuara.setTag(null);
        this.recyclerViewDataSurat.setTag(null);
        this.submitButton.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 131072L;
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
            setViewModel((VerifyAdministrationHal2PpwpViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentVerifyAdministrationHal2PpwpBinding
    public void setGetElectionPageUseCase(GetElectionPageUseCase GetElectionPageUseCase) {
        this.mGetElectionPageUseCase = GetElectionPageUseCase;
        synchronized (this) {
            this.mDirtyFlags |= 32768;
        }
        notifyPropertyChanged(16);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentVerifyAdministrationHal2PpwpBinding
    public void setViewModel(VerifyAdministrationHal2PpwpViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 65536;
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
                return onChangeViewModelFormC1AdministrationHal2PpwpComplete((LiveData) object, fieldId);
            case 4:
                return onChangeViewModelFormC1Kesesuaian((LiveData) object, fieldId);
            case 5:
                return onChangeGetElectionPageUseCaseElectionPageWithRelation((LiveData) object, fieldId);
            case 6:
                return onChangeViewModelIsTableSuratEmpty((LiveData) object, fieldId);
            case 7:
                return onChangeGetElectionPageUseCaseElection((LiveData) object, fieldId);
            case 8:
                return onChangeViewModelIsLoading((LiveData) object, fieldId);
            case 9:
                return onChangeViewModelTablePerolehanSuaraTitle((LiveData) object, fieldId);
            case 10:
                return onChangeViewModelFormC1Error((LiveData) object, fieldId);
            case 11:
                return onChangeGetElectionPageUseCaseElectionPage((LiveData) object, fieldId);
            case 12:
                return onChangeViewModelIsLoadingVerification((LiveData) object, fieldId);
            case 13:
                return onChangeViewModelIsError((LiveData) object, fieldId);
            case 14:
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

    private boolean onChangeViewModelFormC1AdministrationHal2PpwpComplete(LiveData<FormC1AdministrationHal2PpwpComplete> ViewModelFormC1AdministrationHal2PpwpComplete, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFormC1Kesesuaian(LiveData<FormC1Kesesuaian> ViewModelFormC1Kesesuaian, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseElectionPageWithRelation(LiveData<ElectionPageWithRelation> GetElectionPageUseCaseElectionPageWithRelation, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsTableSuratEmpty(LiveData<Boolean> ViewModelIsTableSuratEmpty, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseElection(LiveData<Election> GetElectionPageUseCaseElection, int fieldId) {
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

    private boolean onChangeViewModelTablePerolehanSuaraTitle(LiveData<String> ViewModelTablePerolehanSuaraTitle, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFormC1Error(LiveData<FormC1Error> ViewModelFormC1Error, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_KB;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeGetElectionPageUseCaseElectionPage(LiveData<ElectionPage> GetElectionPageUseCaseElectionPage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2048;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoadingVerification(LiveData<Boolean> ViewModelIsLoadingVerification, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsError(LiveData<Boolean> ViewModelIsError, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelGetElectionPageUseCaseIsPhotoLimitReached(LiveData<Boolean> ViewModelGetElectionPageUseCaseIsPhotoLimitReached, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:384:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:544:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:547:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:562:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:574:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:577:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:600:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:644:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:650:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:653:0x0506  */
    /* JADX WARN: Removed duplicated region for block: B:657:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:660:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:667:0x0532  */
    /* JADX WARN: Removed duplicated region for block: B:669:0x053a  */
    /* JADX WARN: Removed duplicated region for block: B:670:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:673:0x0551  */
    /* JADX WARN: Removed duplicated region for block: B:676:0x0561  */
    /* JADX WARN: Removed duplicated region for block: B:679:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:681:0x0582  */
    /* JADX WARN: Removed duplicated region for block: B:684:0x0596  */
    /* JADX WARN: Removed duplicated region for block: B:687:0x05a5  */
    /* JADX WARN: Removed duplicated region for block: B:690:0x05b2  */
    /* JADX WARN: Removed duplicated region for block: B:693:0x05bf  */
    /* JADX WARN: Removed duplicated region for block: B:696:0x05cc  */
    /* JADX WARN: Removed duplicated region for block: B:699:0x05d9  */
    /* JADX WARN: Removed duplicated region for block: B:702:0x05f8  */
    /* JADX WARN: Removed duplicated region for block: B:705:0x0607  */
    /* JADX WARN: Removed duplicated region for block: B:708:0x0624  */
    /* JADX WARN: Removed duplicated region for block: B:711:0x0633  */
    /* JADX WARN: Removed duplicated region for block: B:714:0x0642  */
    /* JADX WARN: Removed duplicated region for block: B:716:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:723:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 1626
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentVerifyAdministrationHal2PpwpBindingImpl.executeBindings():void");
    }
}
