package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.CombinedLiveData;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel;

/* loaded from: classes2.dex */
public class FragmentWitnessUrlsBindingImpl extends FragmentWitnessUrlsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;
    private final ConstraintLayout mboundView14;
    private final TextView mboundView15;
    private final ConstraintLayout mboundView2;
    private final ConstraintLayout mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.text_witness_empty_title, 17);
        sparseIntArray.put(R.id.textView, 18);
        sparseIntArray.put(R.id.title_filter_pemilihan, 19);
        sparseIntArray.put(R.id.filter_pemilihan, 20);
        sparseIntArray.put(R.id.saksi_divider, 21);
    }

    public FragmentWitnessUrlsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds));
    }

    private FragmentWitnessUrlsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 13, (MaterialCardView) bindings[12], (Chip) bindings[9], (Chip) bindings[6], (Chip) bindings[8], (Chip) bindings[7], (Chip) bindings[10], (Chip) bindings[5], (Chip) bindings[11], (HorizontalScrollView) bindings[20], (ConstraintLayout) bindings[4], (RecyclerView) bindings[13], (View) bindings[21], (TextView) bindings[18], (TextView) bindings[17], (TextView) bindings[16], (TextView) bindings[19]);
        this.mDirtyFlags = -1L;
        this.cardAlert.setTag(null);
        this.chipFilterDpd.setTag(null);
        this.chipFilterDpr.setTag(null);
        this.chipFilterDprdk.setTag(null);
        this.chipFilterDprdp.setTag(null);
        this.chipFilterPilgub.setTag(null);
        this.chipFilterPilpres.setTag(null);
        this.chipFilterPilwabup.setTag(null);
        this.layoutFilterPemilihan.setTag(null);
        this.listWitness.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout2;
        constraintLayout2.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[14];
        this.mboundView14 = constraintLayout3;
        constraintLayout3.setTag(null);
        TextView textView = (TextView) bindings[15];
        this.mboundView15 = textView;
        textView.setTag(null);
        ConstraintLayout constraintLayout4 = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout4;
        constraintLayout4.setTag(null);
        ConstraintLayout constraintLayout5 = (ConstraintLayout) bindings[3];
        this.mboundView3 = constraintLayout5;
        constraintLayout5.setTag(null);
        this.textWitnessEmptyTitle2.setTag(null);
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
        if (87 == variableId) {
            setViewModel((WitnessUrlListViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentWitnessUrlsBinding
    public void setViewModel(WitnessUrlListViewModel ViewModel) {
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
                return onChangeViewModelIsCheckedPilwabup((LiveData) object, fieldId);
            case 1:
                return onChangeViewModelIsCheckedDprdp((LiveData) object, fieldId);
            case 2:
                return onChangeViewModelIsLoading((LiveData) object, fieldId);
            case 3:
                return onChangeViewModelIsCheckedPilpres((LiveData) object, fieldId);
            case 4:
                return onChangeViewModelIsCheckedDpd((LiveData) object, fieldId);
            case 5:
                return onChangeViewModelIsCheckedDprdk((LiveData) object, fieldId);
            case 6:
                return onChangeViewModelIsEmpty((LiveData) object, fieldId);
            case 7:
                return onChangeViewModelIsCheckedDpr((LiveData) object, fieldId);
            case 8:
                return onChangeViewModelIsZipped((LiveData) object, fieldId);
            case 9:
                return onChangeViewModelIsMultipleElections((LiveData) object, fieldId);
            case 10:
                return onChangeViewModelShowEmptyText((CombinedLiveData) object, fieldId);
            case 11:
                return onChangeViewModelIsCheckedPilgub((LiveData) object, fieldId);
            case 12:
                return onChangeViewModelFilterJenisPemilihan((MutableLiveData) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelIsCheckedPilwabup(LiveData<Boolean> ViewModelIsCheckedPilwabup, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedDprdp(LiveData<Boolean> ViewModelIsCheckedDprdp, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoading(LiveData<Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedPilpres(LiveData<Boolean> ViewModelIsCheckedPilpres, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedDpd(LiveData<Boolean> ViewModelIsCheckedDpd, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedDprdk(LiveData<Boolean> ViewModelIsCheckedDprdk, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsEmpty(LiveData<Boolean> ViewModelIsEmpty, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedDpr(LiveData<Boolean> ViewModelIsCheckedDpr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsZipped(LiveData<Boolean> ViewModelIsZipped, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsMultipleElections(LiveData<Boolean> ViewModelIsMultipleElections, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShowEmptyText(CombinedLiveData<Boolean> ViewModelShowEmptyText, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_KB;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedPilgub(LiveData<Boolean> ViewModelIsCheckedPilgub, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2048;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFilterJenisPemilihan(MutableLiveData<String> ViewModelFilterJenisPemilihan, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:299:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:447:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:493:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 993
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentWitnessUrlsBindingImpl.executeBindings():void");
    }
}
