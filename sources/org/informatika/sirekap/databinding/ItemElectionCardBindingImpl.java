package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionWithRelation;

/* loaded from: classes2.dex */
public class ItemElectionCardBindingImpl extends ItemElectionCardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final MaterialCardView mboundView0;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.bar_captured, 5);
        sparseIntArray.put(R.id.label_captured, 6);
        sparseIntArray.put(R.id.bar_verified, 7);
        sparseIntArray.put(R.id.bar_not_recognized, 8);
        sparseIntArray.put(R.id.button_detail_election, 9);
        sparseIntArray.put(R.id.button_witness, 10);
        sparseIntArray.put(R.id.button_time_tps, 11);
    }

    public ItemElectionCardBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ItemElectionCardBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ConstraintLayout) bindings[5], (ConstraintLayout) bindings[8], (ConstraintLayout) bindings[7], (Button) bindings[9], (Button) bindings[11], (Button) bindings[10], (TextView) bindings[6], (TextView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.labelElection.setTag(null);
        MaterialCardView materialCardView = (MaterialCardView) bindings[0];
        this.mboundView0 = materialCardView;
        materialCardView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
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
        if (12 == variableId) {
            setElectionWithRelation((ElectionWithRelation) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ItemElectionCardBinding
    public void setElectionWithRelation(ElectionWithRelation ElectionWithRelation) {
        this.mElectionWithRelation = ElectionWithRelation;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        int i;
        Election election;
        int i2;
        int i3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ElectionWithRelation electionWithRelation = this.mElectionWithRelation;
        int i4 = ((j & 3) > 0L ? 1 : ((j & 3) == 0L ? 0 : -1));
        String str4 = null;
        if (i4 != 0) {
            if (electionWithRelation != null) {
                i = electionWithRelation.getVerifiedCount();
                i2 = electionWithRelation.getCapturedCount();
                election = electionWithRelation.getElection();
                i3 = electionWithRelation.getNotRecognizedCount();
            } else {
                i = 0;
                election = null;
                i2 = 0;
                i3 = 0;
            }
            str = Integer.toString(i);
            str2 = Integer.toString(i2);
            str3 = Integer.toString(i3);
            if (election != null) {
                str4 = election.getDescription(getRoot().getContext());
            }
        } else {
            str = null;
            str2 = null;
            str3 = null;
        }
        if (i4 != 0) {
            TextViewBindingAdapter.setText(this.labelElection, str4);
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            TextViewBindingAdapter.setText(this.mboundView3, str);
            TextViewBindingAdapter.setText(this.mboundView4, str3);
        }
    }
}
