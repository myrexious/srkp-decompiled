package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.DataBindingAdaptersKt;

/* loaded from: classes2.dex */
public class ItemWitnessBindingImpl extends ItemWitnessBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.saksi_icon, 5);
    }

    public ItemWitnessBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemWitnessBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[4], (LinearLayout) bindings[0], (ImageView) bindings[5], (TextView) bindings[3], (TextView) bindings[2], (ImageView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.candidateName.setTag(null);
        this.linearLayout.setTag(null);
        this.saksiJenis.setTag(null);
        this.saksiName.setTag(null);
        this.syncFailedIcon.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
        if (88 == variableId) {
            setWitness((WitnessWithShare) variable);
        } else if (44 != variableId) {
            return false;
        } else {
            setIsEmpty((Boolean) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.ItemWitnessBinding
    public void setWitness(WitnessWithShare Witness) {
        this.mWitness = Witness;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(88);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ItemWitnessBinding
    public void setIsEmpty(Boolean IsEmpty) {
        this.mIsEmpty = IsEmpty;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        WitnessWithShare witnessWithShare = this.mWitness;
        int i = ((5 & j) > 0L ? 1 : ((5 & j) == 0L ? 0 : -1));
        boolean z = false;
        String str2 = null;
        if (i != 0) {
            Witness witness = witnessWithShare != null ? witnessWithShare.getWitness() : null;
            if (witness != null) {
                z = witness.isWitnessLocal();
                str2 = witness.getJenisPemeriksaText(getRoot().getContext());
                str = witness.getNama();
            } else {
                str = null;
            }
            z = !z;
        } else {
            str = null;
        }
        if ((j & 4) != 0) {
            DataBindingAdaptersKt.hidden(this.candidateName, true);
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.saksiJenis, str2);
            TextViewBindingAdapter.setText(this.saksiName, str);
            DataBindingAdaptersKt.hidden(this.syncFailedIcon, z);
        }
    }
}
