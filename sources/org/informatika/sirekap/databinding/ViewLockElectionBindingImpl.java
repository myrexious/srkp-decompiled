package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.support.DataBindingAdaptersKt;

/* loaded from: classes2.dex */
public class ViewLockElectionBindingImpl extends ViewLockElectionBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView11;
    private final CircularProgressIndicator mboundView12;
    private final TextView mboundView13;
    private final LinearLayout mboundView2;
    private final TextView mboundView3;
    private final LinearLayout mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView7;
    private final CircularProgressIndicator mboundView8;
    private final TextView mboundView9;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ViewLockElectionBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }

    private ViewLockElectionBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Button) bindings[6], (Button) bindings[15], (Button) bindings[10], (Button) bindings[14], (MaterialCardView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.buttonCreatePdf.setTag(null);
        this.buttonShareZip.setTag(null);
        this.buttonUploadPdf.setTag(null);
        this.buttonUploadPdfOffline.setTag(null);
        this.cardInfo.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[12];
        this.mboundView12 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        TextView textView2 = (TextView) bindings[13];
        this.mboundView13 = textView2;
        textView2.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[2];
        this.mboundView2 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[4];
        this.mboundView4 = linearLayout3;
        linearLayout3.setTag(null);
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[7];
        this.mboundView7 = textView5;
        textView5.setTag(null);
        CircularProgressIndicator circularProgressIndicator2 = (CircularProgressIndicator) bindings[8];
        this.mboundView8 = circularProgressIndicator2;
        circularProgressIndicator2.setTag(null);
        TextView textView6 = (TextView) bindings[9];
        this.mboundView9 = textView6;
        textView6.setTag(null);
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
        if (46 == variableId) {
            setIsLoadingZip((Boolean) variable);
        } else if (12 != variableId) {
            return false;
        } else {
            setElectionWithRelation((ElectionWithRelation) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.ViewLockElectionBinding
    public void setIsLoadingZip(Boolean IsLoadingZip) {
        this.mIsLoadingZip = IsLoadingZip;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(46);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ViewLockElectionBinding
    public void setElectionWithRelation(ElectionWithRelation ElectionWithRelation) {
        this.mElectionWithRelation = ElectionWithRelation;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        String str;
        String str2;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        Election election;
        boolean z13;
        String str3;
        boolean z14;
        boolean z15;
        boolean z16;
        boolean z17;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        Boolean bool = this.mIsLoadingZip;
        ElectionWithRelation electionWithRelation = this.mElectionWithRelation;
        boolean safeUnbox = (j & 5) != 0 ? ViewDataBinding.safeUnbox(Boolean.valueOf(!ViewDataBinding.safeUnbox(bool))) : false;
        int i = ((j & 6) > 0L ? 1 : ((j & 6) == 0L ? 0 : -1));
        if (i != 0) {
            if (electionWithRelation != null) {
                z11 = electionWithRelation.isAllPhotosDoneOrCreatedPdf();
                Election election2 = electionWithRelation.getElection();
                z13 = electionWithRelation.isShowInfoAlertOffline();
                election = election2;
            } else {
                election = null;
                z13 = false;
                z11 = false;
            }
            boolean z18 = !z11;
            z5 = !z13;
            if (i != 0) {
                j |= z18 ? 16L : 8L;
            }
            if (election != null) {
                z7 = election.getHideUploadPdfOfflineButton();
                z10 = election.isZipped();
                z16 = election.isUploadedPdf();
                z17 = election.isShowUploadPdfButton();
                str = election.salinanPdfUploadSuccessDescription(getRoot().getContext());
                z15 = election.isSuccessUploadPdf();
                str3 = election.getCreatePdfInstruction(getRoot().getContext());
                z14 = election.isLoadingUploadPdf();
            } else {
                str3 = null;
                str = null;
                z14 = false;
                z15 = false;
                z7 = false;
                z10 = false;
                z16 = false;
                z17 = false;
            }
            z9 = !z10;
            boolean z19 = !z17;
            z6 = !z15;
            z4 = !z14;
            z8 = z18;
            boolean z20 = z16;
            z = safeUnbox;
            z2 = z20;
            str2 = str3;
            z3 = z19;
        } else {
            z = safeUnbox;
            str = null;
            str2 = null;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
            z6 = false;
            z7 = false;
            z8 = false;
            z9 = false;
            z10 = false;
            z11 = false;
        }
        int i2 = ((6 & j) > 0L ? 1 : ((6 & j) == 0L ? 0 : -1));
        if (i2 != 0) {
            z12 = z8 ? true : z10;
        } else {
            z12 = false;
        }
        if (i2 != 0) {
            DataBindingAdaptersKt.hidden(this.buttonCreatePdf, z12);
            DataBindingAdaptersKt.hidden(this.buttonShareZip, z9);
            this.buttonShareZip.setEnabled(z2);
            DataBindingAdaptersKt.hidden(this.buttonUploadPdf, z3);
            this.buttonUploadPdf.setEnabled(z4);
            DataBindingAdaptersKt.hidden(this.buttonUploadPdfOffline, z7);
            DataBindingAdaptersKt.hidden(this.cardInfo, z5);
            DataBindingAdaptersKt.hidden(this.mboundView11, z4);
            DataBindingAdaptersKt.hidden(this.mboundView12, z4);
            DataBindingAdaptersKt.hidden(this.mboundView13, z6);
            TextViewBindingAdapter.setText(this.mboundView13, str);
            DataBindingAdaptersKt.hidden(this.mboundView2, z11);
            DataBindingAdaptersKt.hidden(this.mboundView3, z10);
            DataBindingAdaptersKt.hidden(this.mboundView4, z8);
            DataBindingAdaptersKt.hidden(this.mboundView5, z10);
            TextViewBindingAdapter.setText(this.mboundView5, str2);
            DataBindingAdaptersKt.hidden(this.mboundView9, z9);
        }
        if ((j & 5) != 0) {
            boolean z21 = z;
            this.buttonCreatePdf.setEnabled(z21);
            DataBindingAdaptersKt.hidden(this.mboundView7, z21);
            DataBindingAdaptersKt.hidden(this.mboundView8, z21);
        }
    }
}
