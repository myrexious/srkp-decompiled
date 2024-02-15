package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.DataBindingAdaptersKt;
import org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase;
import org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel;

/* loaded from: classes2.dex */
public class FragmentWitnessQrCodeBindingImpl extends FragmentWitnessQrCodeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final CircularProgressIndicator mboundView1;
    private final View mboundView11;
    private final View mboundView12;
    private final View mboundView13;
    private final View mboundView14;
    private final View mboundView15;
    private final View mboundView16;
    private final View mboundView17;
    private final ScrollView mboundView2;
    private final CircularProgressIndicator mboundView9;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(31);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(10, new String[]{"view_witness_share", "view_witness_share", "view_witness_share", "view_witness_share", "view_witness_share", "view_witness_share", "view_witness_share"}, new int[]{18, 19, 20, 21, 22, 23, 24}, new int[]{R.layout.view_witness_share, R.layout.view_witness_share, R.layout.view_witness_share, R.layout.view_witness_share, R.layout.view_witness_share, R.layout.view_witness_share, R.layout.view_witness_share});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title_info, 25);
        sparseIntArray.put(R.id.card_witness_info, 26);
        sparseIntArray.put(R.id.label_witness_name, 27);
        sparseIntArray.put(R.id.label_witness_nik, 28);
        sparseIntArray.put(R.id.label_witness_phone, 29);
        sparseIntArray.put(R.id.label_witness_type, 30);
    }

    public FragmentWitnessQrCodeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 31, sIncludes, sViewsWithIds));
    }

    private FragmentWitnessQrCodeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 17, (Button) bindings[8], (MaterialCardView) bindings[26], (MaterialCardView) bindings[7], (TextView) bindings[27], (TextView) bindings[28], (TextView) bindings[29], (TextView) bindings[30], (ViewWitnessShareBinding) bindings[24], (ViewWitnessShareBinding) bindings[21], (ViewWitnessShareBinding) bindings[23], (ViewWitnessShareBinding) bindings[22], (ViewWitnessShareBinding) bindings[18], (ViewWitnessShareBinding) bindings[20], (ViewWitnessShareBinding) bindings[19], (TextView) bindings[25], (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[5], (TextView) bindings[6], (LinearLayout) bindings[10]);
        this.mDirtyFlags = -1L;
        this.buttonSync.setTag(null);
        this.cardWitnessSync.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[1];
        this.mboundView1 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        View view = (View) bindings[11];
        this.mboundView11 = view;
        view.setTag(null);
        View view2 = (View) bindings[12];
        this.mboundView12 = view2;
        view2.setTag(null);
        View view3 = (View) bindings[13];
        this.mboundView13 = view3;
        view3.setTag(null);
        View view4 = (View) bindings[14];
        this.mboundView14 = view4;
        view4.setTag(null);
        View view5 = (View) bindings[15];
        this.mboundView15 = view5;
        view5.setTag(null);
        View view6 = (View) bindings[16];
        this.mboundView16 = view6;
        view6.setTag(null);
        View view7 = (View) bindings[17];
        this.mboundView17 = view7;
        view7.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[2];
        this.mboundView2 = scrollView;
        scrollView.setTag(null);
        CircularProgressIndicator circularProgressIndicator2 = (CircularProgressIndicator) bindings[9];
        this.mboundView9 = circularProgressIndicator2;
        circularProgressIndicator2.setTag(null);
        setContainedBinding(this.shareDpdCard);
        setContainedBinding(this.shareDprCard);
        setContainedBinding(this.shareDprdkCard);
        setContainedBinding(this.shareDprdpCard);
        setContainedBinding(this.sharePilpresCard);
        setContainedBinding(this.sharePkwkkCard);
        setContainedBinding(this.sharePkwkpCard);
        this.valueWitnessName.setTag(null);
        this.valueWitnessNik.setTag(null);
        this.valueWitnessPhone.setTag(null);
        this.valueWitnessType.setTag(null);
        this.witnessShareView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 262144L;
        }
        this.sharePilpresCard.invalidateAll();
        this.sharePkwkpCard.invalidateAll();
        this.sharePkwkkCard.invalidateAll();
        this.shareDprCard.invalidateAll();
        this.shareDprdpCard.invalidateAll();
        this.shareDprdkCard.invalidateAll();
        this.shareDpdCard.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return this.sharePilpresCard.hasPendingBindings() || this.sharePkwkpCard.hasPendingBindings() || this.sharePkwkkCard.hasPendingBindings() || this.shareDprCard.hasPendingBindings() || this.shareDprdpCard.hasPendingBindings() || this.shareDprdkCard.hasPendingBindings() || this.shareDpdCard.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (87 == variableId) {
            setViewModel((WitnessQrCodeViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentWitnessQrCodeBinding
    public void setViewModel(WitnessQrCodeViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 131072;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.sharePilpresCard.setLifecycleOwner(lifecycleOwner);
        this.sharePkwkpCard.setLifecycleOwner(lifecycleOwner);
        this.sharePkwkkCard.setLifecycleOwner(lifecycleOwner);
        this.shareDprCard.setLifecycleOwner(lifecycleOwner);
        this.shareDprdpCard.setLifecycleOwner(lifecycleOwner);
        this.shareDprdkCard.setLifecycleOwner(lifecycleOwner);
        this.shareDpdCard.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeShareDprdpCard((ViewWitnessShareBinding) object, fieldId);
            case 1:
                return onChangeViewModelShareDpdWitness((LiveData) object, fieldId);
            case 2:
                return onChangeShareDprdkCard((ViewWitnessShareBinding) object, fieldId);
            case 3:
                return onChangeViewModelSharePkwkkWitness((LiveData) object, fieldId);
            case 4:
                return onChangeSharePkwkkCard((ViewWitnessShareBinding) object, fieldId);
            case 5:
                return onChangeViewModelIsSyncingLocalWitness((LiveData) object, fieldId);
            case 6:
                return onChangeViewModelIsLoading((LiveData) object, fieldId);
            case 7:
                return onChangeShareDpdCard((ViewWitnessShareBinding) object, fieldId);
            case 8:
                return onChangeViewModelShareDprdpWitness((LiveData) object, fieldId);
            case 9:
                return onChangeViewModelShareDprdkWitness((LiveData) object, fieldId);
            case 10:
                return onChangeShareDprCard((ViewWitnessShareBinding) object, fieldId);
            case 11:
                return onChangeSharePilpresCard((ViewWitnessShareBinding) object, fieldId);
            case 12:
                return onChangeSharePkwkpCard((ViewWitnessShareBinding) object, fieldId);
            case 13:
                return onChangeViewModelSharePkwkpWitness((LiveData) object, fieldId);
            case 14:
                return onChangeViewModelWitness((LiveData) object, fieldId);
            case 15:
                return onChangeViewModelShareDprWitness((LiveData) object, fieldId);
            case 16:
                return onChangeViewModelSharePilpresWitness((LiveData) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeShareDprdpCard(ViewWitnessShareBinding ShareDprdpCard, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShareDpdWitness(LiveData<WitnessWithShare> ViewModelShareDpdWitness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareDprdkCard(ViewWitnessShareBinding ShareDprdkCard, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSharePkwkkWitness(LiveData<WitnessWithShare> ViewModelSharePkwkkWitness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeSharePkwkkCard(ViewWitnessShareBinding SharePkwkkCard, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsSyncingLocalWitness(LiveData<Boolean> ViewModelIsSyncingLocalWitness, int fieldId) {
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

    private boolean onChangeShareDpdCard(ViewWitnessShareBinding ShareDpdCard, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShareDprdpWitness(LiveData<WitnessWithShare> ViewModelShareDprdpWitness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShareDprdkWitness(LiveData<WitnessWithShare> ViewModelShareDprdkWitness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareDprCard(ViewWitnessShareBinding ShareDprCard, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_KB;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeSharePilpresCard(ViewWitnessShareBinding SharePilpresCard, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2048;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeSharePkwkpCard(ViewWitnessShareBinding SharePkwkpCard, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSharePkwkpWitness(LiveData<WitnessWithShare> ViewModelSharePkwkpWitness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelWitness(LiveData<WitnessWithShare> ViewModelWitness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShareDprWitness(LiveData<WitnessWithShare> ViewModelShareDprWitness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSharePilpresWitness(LiveData<WitnessWithShare> ViewModelSharePilpresWitness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 65536;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        String str;
        String str2;
        String str3;
        String str4;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase2;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase3;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase4;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase5;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase6;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase7;
        boolean z11;
        boolean z12;
        boolean z13;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase8;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase9;
        boolean z14;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase10;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase11;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase12;
        boolean z15;
        boolean z16;
        boolean z17;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase13;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase14;
        boolean z18;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase15;
        boolean z19;
        boolean z20;
        String str5;
        String str6;
        String str7;
        String str8;
        boolean z21;
        String str9;
        ShareWitnessUrlUseCase shareWitnessUrlUseCase16;
        LiveData<WitnessWithShare> liveData;
        String str10;
        boolean z22;
        boolean z23;
        LiveData<WitnessWithShare> liveData2;
        String str11;
        LiveData<Boolean> liveData3;
        String str12;
        LiveData<WitnessWithShare> liveData4;
        String str13;
        LiveData<WitnessWithShare> liveData5;
        LiveData<WitnessWithShare> liveData6;
        String str14;
        LiveData<WitnessWithShare> liveData7;
        String str15;
        LiveData<WitnessWithShare> liveData8;
        String str16;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        WitnessQrCodeViewModel witnessQrCodeViewModel = this.mViewModel;
        if ((516970 & j) != 0) {
            if ((j & 393218) != 0) {
                shareWitnessUrlUseCase8 = witnessQrCodeViewModel != null ? witnessQrCodeViewModel.getShareDpd() : null;
                if (shareWitnessUrlUseCase8 != null) {
                    str16 = shareWitnessUrlUseCase8.getElectionPemilihan();
                    liveData8 = shareWitnessUrlUseCase8.getWitness();
                } else {
                    liveData8 = null;
                    str16 = null;
                }
                updateLiveDataRegistration(1, liveData8);
                WitnessWithShare value = liveData8 != null ? liveData8.getValue() : null;
                z13 = !(value != null ? value.isJenisPemilihan(str16) : false);
            } else {
                z13 = false;
                shareWitnessUrlUseCase8 = null;
            }
            if ((j & 425984) != 0) {
                shareWitnessUrlUseCase9 = witnessQrCodeViewModel != null ? witnessQrCodeViewModel.getShareDpr() : null;
                if (shareWitnessUrlUseCase9 != null) {
                    liveData7 = shareWitnessUrlUseCase9.getWitness();
                    str15 = shareWitnessUrlUseCase9.getElectionPemilihan();
                } else {
                    liveData7 = null;
                    str15 = null;
                }
                updateLiveDataRegistration(15, liveData7);
                WitnessWithShare value2 = liveData7 != null ? liveData7.getValue() : null;
                z6 = !(value2 != null ? value2.isJenisPemilihan(str15) : false);
            } else {
                z6 = false;
                shareWitnessUrlUseCase9 = null;
            }
            if ((j & 393728) != 0) {
                shareWitnessUrlUseCase10 = witnessQrCodeViewModel != null ? witnessQrCodeViewModel.getShareDprdk() : null;
                if (shareWitnessUrlUseCase10 != null) {
                    liveData6 = shareWitnessUrlUseCase10.getWitness();
                    str14 = shareWitnessUrlUseCase10.getElectionPemilihan();
                } else {
                    liveData6 = null;
                    str14 = null;
                }
                updateLiveDataRegistration(9, liveData6);
                WitnessWithShare value3 = liveData6 != null ? liveData6.getValue() : null;
                z14 = !(value3 != null ? value3.isJenisPemilihan(str14) : false);
            } else {
                z14 = false;
                shareWitnessUrlUseCase10 = null;
            }
            if ((j & 458752) != 0) {
                shareWitnessUrlUseCase11 = witnessQrCodeViewModel != null ? witnessQrCodeViewModel.getSharePilpres() : null;
                if (shareWitnessUrlUseCase11 != null) {
                    str13 = shareWitnessUrlUseCase11.getElectionPemilihan();
                    liveData5 = shareWitnessUrlUseCase11.getWitness();
                } else {
                    str13 = null;
                    liveData5 = null;
                }
                updateLiveDataRegistration(16, liveData5);
                WitnessWithShare value4 = liveData5 != null ? liveData5.getValue() : null;
                z7 = !(value4 != null ? value4.isJenisPemilihan(str13) : false);
            } else {
                z7 = false;
                shareWitnessUrlUseCase11 = null;
            }
            if ((j & 401408) != 0) {
                shareWitnessUrlUseCase12 = witnessQrCodeViewModel != null ? witnessQrCodeViewModel.getSharePkwkp() : null;
                if (shareWitnessUrlUseCase12 != null) {
                    str12 = shareWitnessUrlUseCase12.getElectionPemilihan();
                    liveData4 = shareWitnessUrlUseCase12.getWitness();
                } else {
                    str12 = null;
                    liveData4 = null;
                }
                updateLiveDataRegistration(13, liveData4);
                WitnessWithShare value5 = liveData4 != null ? liveData4.getValue() : null;
                z5 = !(value5 != null ? value5.isJenisPemilihan(str12) : false);
            } else {
                z5 = false;
                shareWitnessUrlUseCase12 = null;
            }
            if ((j & 393248) != 0) {
                LiveData<Boolean> isSyncingLocalWitness = witnessQrCodeViewModel != null ? witnessQrCodeViewModel.isSyncingLocalWitness() : null;
                updateLiveDataRegistration(5, isSyncingLocalWitness);
                z8 = ViewDataBinding.safeUnbox(Boolean.valueOf(!ViewDataBinding.safeUnbox(isSyncingLocalWitness != null ? isSyncingLocalWitness.getValue() : null)));
            } else {
                z8 = false;
            }
            if ((j & 393280) != 0) {
                if (witnessQrCodeViewModel != null) {
                    liveData3 = witnessQrCodeViewModel.isLoading();
                    z15 = z14;
                } else {
                    z15 = z14;
                    liveData3 = null;
                }
                updateLiveDataRegistration(6, liveData3);
                z16 = ViewDataBinding.safeUnbox(liveData3 != null ? liveData3.getValue() : null);
                z9 = ViewDataBinding.safeUnbox(Boolean.valueOf(!z16));
            } else {
                z15 = z14;
                z16 = false;
                z9 = false;
            }
            if ((j & 393472) != 0) {
                shareWitnessUrlUseCase15 = witnessQrCodeViewModel != null ? witnessQrCodeViewModel.getShareDprdp() : null;
                if (shareWitnessUrlUseCase15 != null) {
                    shareWitnessUrlUseCase14 = shareWitnessUrlUseCase8;
                    z17 = z16;
                    liveData2 = shareWitnessUrlUseCase15.getWitness();
                    shareWitnessUrlUseCase13 = shareWitnessUrlUseCase11;
                    str11 = shareWitnessUrlUseCase15.getElectionPemilihan();
                } else {
                    z17 = z16;
                    shareWitnessUrlUseCase13 = shareWitnessUrlUseCase11;
                    shareWitnessUrlUseCase14 = shareWitnessUrlUseCase8;
                    liveData2 = null;
                    str11 = null;
                }
                updateLiveDataRegistration(8, liveData2);
                WitnessWithShare value6 = liveData2 != null ? liveData2.getValue() : null;
                z18 = !(value6 != null ? value6.isJenisPemilihan(str11) : false);
            } else {
                z17 = z16;
                shareWitnessUrlUseCase13 = shareWitnessUrlUseCase11;
                shareWitnessUrlUseCase14 = shareWitnessUrlUseCase8;
                z18 = false;
                shareWitnessUrlUseCase15 = null;
            }
            if ((j & 409600) != 0) {
                LiveData<WitnessWithShare> witness = witnessQrCodeViewModel != null ? witnessQrCodeViewModel.getWitness() : null;
                updateLiveDataRegistration(14, witness);
                WitnessWithShare value7 = witness != null ? witness.getValue() : null;
                Witness witness2 = value7 != null ? value7.getWitness() : null;
                if (witness2 != null) {
                    str6 = witness2.getJenisPemeriksaText(getRoot().getContext());
                    str7 = witness2.getNama();
                    z22 = witness2.isWitnessLocal();
                    str8 = witness2.getNoHandphoneFormatted();
                    z23 = witness2.isSharedLink();
                    str5 = witness2.getNik();
                } else {
                    z22 = false;
                    z23 = false;
                    str5 = null;
                    str6 = null;
                    str7 = null;
                    str8 = null;
                }
                z19 = !z22;
                z20 = !z23;
            } else {
                z19 = false;
                z20 = false;
                str5 = null;
                str6 = null;
                str7 = null;
                str8 = null;
            }
            if ((j & 393224) != 0) {
                ShareWitnessUrlUseCase sharePkwkk = witnessQrCodeViewModel != null ? witnessQrCodeViewModel.getSharePkwkk() : null;
                if (sharePkwkk != null) {
                    LiveData<WitnessWithShare> witness3 = sharePkwkk.getWitness();
                    String electionPemilihan = sharePkwkk.getElectionPemilihan();
                    str9 = str5;
                    shareWitnessUrlUseCase16 = sharePkwkk;
                    liveData = witness3;
                    z21 = z18;
                    str10 = electionPemilihan;
                } else {
                    shareWitnessUrlUseCase16 = sharePkwkk;
                    z21 = z18;
                    str9 = str5;
                    liveData = null;
                    str10 = null;
                }
                updateLiveDataRegistration(3, liveData);
                WitnessWithShare value8 = liveData != null ? liveData.getValue() : null;
                r27 = !(value8 != null ? value8.isJenisPemilihan(str10) : false);
                z10 = z13;
                shareWitnessUrlUseCase2 = shareWitnessUrlUseCase9;
                shareWitnessUrlUseCase3 = shareWitnessUrlUseCase10;
                shareWitnessUrlUseCase7 = shareWitnessUrlUseCase12;
                shareWitnessUrlUseCase4 = shareWitnessUrlUseCase15;
                z3 = z17;
                shareWitnessUrlUseCase5 = shareWitnessUrlUseCase13;
                shareWitnessUrlUseCase = shareWitnessUrlUseCase14;
                str4 = str7;
                z = z19;
                str2 = str8;
                z2 = z20;
                shareWitnessUrlUseCase6 = shareWitnessUrlUseCase16;
            } else {
                z21 = z18;
                str9 = str5;
                z10 = z13;
                shareWitnessUrlUseCase2 = shareWitnessUrlUseCase9;
                shareWitnessUrlUseCase3 = shareWitnessUrlUseCase10;
                shareWitnessUrlUseCase7 = shareWitnessUrlUseCase12;
                shareWitnessUrlUseCase6 = null;
                shareWitnessUrlUseCase4 = shareWitnessUrlUseCase15;
                z3 = z17;
                shareWitnessUrlUseCase5 = shareWitnessUrlUseCase13;
                shareWitnessUrlUseCase = shareWitnessUrlUseCase14;
                str4 = str7;
                z = z19;
                str2 = str8;
                z2 = z20;
            }
            z11 = z21;
            str3 = str9;
            str = str6;
            z4 = r27;
            r27 = z15;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
            z6 = false;
            z7 = false;
            z8 = false;
            z9 = false;
            z10 = false;
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            shareWitnessUrlUseCase = null;
            shareWitnessUrlUseCase2 = null;
            shareWitnessUrlUseCase3 = null;
            shareWitnessUrlUseCase4 = null;
            shareWitnessUrlUseCase5 = null;
            shareWitnessUrlUseCase6 = null;
            shareWitnessUrlUseCase7 = null;
            z11 = false;
        }
        if ((j & 393248) != 0) {
            z12 = z6;
            this.buttonSync.setEnabled(z8);
            DataBindingAdaptersKt.hidden(this.mboundView9, z8);
        } else {
            z12 = z6;
        }
        if ((j & 409600) != 0) {
            DataBindingAdaptersKt.hidden(this.cardWitnessSync, z);
            TextViewBindingAdapter.setText(this.valueWitnessName, str4);
            TextViewBindingAdapter.setText(this.valueWitnessNik, str3);
            TextViewBindingAdapter.setText(this.valueWitnessPhone, str2);
            TextViewBindingAdapter.setText(this.valueWitnessType, str);
            DataBindingAdaptersKt.hidden(this.witnessShareView, z2);
        }
        if ((j & 393280) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView1, z9);
            DataBindingAdaptersKt.hidden(this.mboundView2, z3);
        }
        if ((458752 & j) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView11, z7);
        }
        if ((401408 & j) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView12, z5);
        }
        if ((393224 & j) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView13, z4);
        }
        if ((j & 425984) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView14, z12);
        }
        if ((393472 & j) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView15, z11);
        }
        if ((393728 & j) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView16, r27);
        }
        if ((j & 393218) != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView17, z10);
        }
        if ((j & 393216) != 0) {
            this.shareDpdCard.setShareWitnessUrlUseCase(shareWitnessUrlUseCase);
            this.shareDprCard.setShareWitnessUrlUseCase(shareWitnessUrlUseCase2);
            this.shareDprdkCard.setShareWitnessUrlUseCase(shareWitnessUrlUseCase3);
            this.shareDprdpCard.setShareWitnessUrlUseCase(shareWitnessUrlUseCase4);
            this.sharePilpresCard.setShareWitnessUrlUseCase(shareWitnessUrlUseCase5);
            this.sharePkwkkCard.setShareWitnessUrlUseCase(shareWitnessUrlUseCase6);
            this.sharePkwkpCard.setShareWitnessUrlUseCase(shareWitnessUrlUseCase7);
        }
        executeBindingsOn(this.sharePilpresCard);
        executeBindingsOn(this.sharePkwkpCard);
        executeBindingsOn(this.sharePkwkkCard);
        executeBindingsOn(this.shareDprCard);
        executeBindingsOn(this.shareDprdpCard);
        executeBindingsOn(this.shareDprdkCard);
        executeBindingsOn(this.shareDpdCard);
    }
}
