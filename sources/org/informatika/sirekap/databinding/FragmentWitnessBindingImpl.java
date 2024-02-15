package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.CombinedLiveData;
import org.informatika.sirekap.ui.witness.WitnessViewModel;

/* loaded from: classes2.dex */
public class FragmentWitnessBindingImpl extends FragmentWitnessBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final CoordinatorLayout mboundView0;
    private final ConstraintLayout mboundView1;
    private final ConstraintLayout mboundView11;
    private final TextView mboundView12;
    private final ConstraintLayout mboundView2;
    private final ConstraintLayout mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.text_witness_empty_title, 15);
        sparseIntArray.put(R.id.textView, 16);
        sparseIntArray.put(R.id.title_filter_pemilihan, 17);
        sparseIntArray.put(R.id.filter_pemilihan, 18);
        sparseIntArray.put(R.id.saksi_divider, 19);
        sparseIntArray.put(R.id.add_witness_button, 20);
    }

    public FragmentWitnessBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }

    private FragmentWitnessBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 10, (FloatingActionButton) bindings[20], (Chip) bindings[9], (Chip) bindings[6], (Chip) bindings[8], (Chip) bindings[7], (Chip) bindings[5], (HorizontalScrollView) bindings[18], (ConstraintLayout) bindings[4], (RecyclerView) bindings[10], (View) bindings[19], (TextView) bindings[16], (TextView) bindings[14], (TextView) bindings[15], (TextView) bindings[13], (TextView) bindings[17]);
        this.mDirtyFlags = -1L;
        this.chipFilterDpd.setTag(null);
        this.chipFilterDpr.setTag(null);
        this.chipFilterDprdk.setTag(null);
        this.chipFilterDprdp.setTag(null);
        this.chipFilterPilpres.setTag(null);
        this.layoutFilterPemilihan.setTag(null);
        this.listWitness.setTag(null);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) bindings[0];
        this.mboundView0 = coordinatorLayout;
        coordinatorLayout.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[11];
        this.mboundView11 = constraintLayout2;
        constraintLayout2.setTag(null);
        TextView textView = (TextView) bindings[12];
        this.mboundView12 = textView;
        textView.setTag(null);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) bindings[2];
        this.mboundView2 = constraintLayout3;
        constraintLayout3.setTag(null);
        ConstraintLayout constraintLayout4 = (ConstraintLayout) bindings[3];
        this.mboundView3 = constraintLayout4;
        constraintLayout4.setTag(null);
        this.textView2.setTag(null);
        this.textWitnessEmptyTitle2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2048L;
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
            setViewModel((WitnessViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentWitnessBinding
    public void setViewModel(WitnessViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= FileUtils.ONE_KB;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelIsCheckedDprdp((LiveData) object, fieldId);
            case 1:
                return onChangeViewModelIsLoading((LiveData) object, fieldId);
            case 2:
                return onChangeViewModelIsCheckedPilpres((LiveData) object, fieldId);
            case 3:
                return onChangeViewModelIsCheckedDpd((LiveData) object, fieldId);
            case 4:
                return onChangeViewModelIsCheckedDprdk((LiveData) object, fieldId);
            case 5:
                return onChangeViewModelIsEmpty((LiveData) object, fieldId);
            case 6:
                return onChangeViewModelIsCheckedDpr((LiveData) object, fieldId);
            case 7:
                return onChangeViewModelIsMultipleElections((LiveData) object, fieldId);
            case 8:
                return onChangeViewModelShowEmptyText((CombinedLiveData) object, fieldId);
            case 9:
                return onChangeViewModelFilterJenisPemilihan((MutableLiveData) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelIsCheckedDprdp(LiveData<Boolean> ViewModelIsCheckedDprdp, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoading(LiveData<Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedPilpres(LiveData<Boolean> ViewModelIsCheckedPilpres, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedDpd(LiveData<Boolean> ViewModelIsCheckedDpd, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedDprdk(LiveData<Boolean> ViewModelIsCheckedDprdk, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsEmpty(LiveData<Boolean> ViewModelIsEmpty, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsCheckedDpr(LiveData<Boolean> ViewModelIsCheckedDpr, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsMultipleElections(LiveData<Boolean> ViewModelIsMultipleElections, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShowEmptyText(CombinedLiveData<Boolean> ViewModelShowEmptyText, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFilterJenisPemilihan(MutableLiveData<List<String>> ViewModelFilterJenisPemilihan, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:229:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:375:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 693
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentWitnessBindingImpl.executeBindings():void");
    }
}
