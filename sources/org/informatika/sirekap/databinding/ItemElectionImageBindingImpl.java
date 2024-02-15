package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.google.android.material.chip.Chip;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ElectionImage;
import org.informatika.sirekap.support.DataBindingAdaptersKt;

/* loaded from: classes2.dex */
public class ItemElectionImageBindingImpl extends ItemElectionImageBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.image, 6);
    }

    public ItemElectionImageBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemElectionImageBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Chip) bindings[5], (ImageView) bindings[6], (TextView) bindings[3], (TextView) bindings[2], (TextView) bindings[4], (TextView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.chipStatus.setTag(null);
        this.labelElectionPageNumber.setTag(null);
        this.labelElectionPemilihan.setTag(null);
        this.labelInvalidReason.setTag(null);
        this.labelName.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
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
        if (8 == variableId) {
            setElectionImage((ElectionImage) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ItemElectionImageBinding
    public void setElectionImage(ElectionImage ElectionImage) {
        this.mElectionImage = ElectionImage;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(8);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        boolean z;
        int i;
        boolean z2;
        String str3;
        String str4;
        boolean z3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ElectionImage electionImage = this.mElectionImage;
        int i2 = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        String str5 = null;
        boolean z4 = false;
        if (i2 != 0) {
            if (electionImage != null) {
                z4 = electionImage.isValid();
                boolean isInvalid = electionImage.isInvalid();
                String electionPemilihanFormatted = electionImage.getElectionPemilihanFormatted(getRoot().getContext());
                i = electionImage.getStatus();
                str2 = electionImage.getInvalidReasonFormatted(getRoot().getContext());
                str4 = electionImage.getElectionPageNumberFormatted(getRoot().getContext());
                str = electionImage.getName();
                z3 = isInvalid;
                str5 = electionPemilihanFormatted;
            } else {
                str = null;
                str2 = null;
                str4 = null;
                z3 = false;
                i = 0;
            }
            boolean z5 = !z4;
            str3 = str5;
            str5 = str4;
            z2 = !z3;
            z = z5;
        } else {
            str = null;
            str2 = null;
            z = false;
            i = 0;
            z2 = false;
            str3 = null;
        }
        if (i2 != 0) {
            DataBindingAdaptersKt.setElectionImageStatusChip(this.chipStatus, i);
            DataBindingAdaptersKt.hidden(this.labelElectionPageNumber, z);
            TextViewBindingAdapter.setText(this.labelElectionPageNumber, str5);
            DataBindingAdaptersKt.hidden(this.labelElectionPemilihan, z);
            TextViewBindingAdapter.setText(this.labelElectionPemilihan, str3);
            DataBindingAdaptersKt.hidden(this.labelInvalidReason, z2);
            TextViewBindingAdapter.setText(this.labelInvalidReason, str2);
            TextViewBindingAdapter.setText(this.labelName, str);
        }
    }
}
