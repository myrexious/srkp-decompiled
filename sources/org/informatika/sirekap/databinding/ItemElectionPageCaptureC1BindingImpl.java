package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionPageWithRelation;

/* loaded from: classes2.dex */
public class ItemElectionPageCaptureC1BindingImpl extends ItemElectionPageCaptureC1Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final MaterialCardView mboundView0;
    private final CircularProgressIndicator mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.button_detail, 9);
        sparseIntArray.put(R.id.button_more_menu, 10);
    }

    public ItemElectionPageCaptureC1BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }

    private ItemElectionPageCaptureC1BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Button) bindings[9], (Button) bindings[10], (TextView) bindings[8], (ImageView) bindings[2], (ImageView) bindings[5], (ImageView) bindings[7], (TextView) bindings[1], (View) bindings[3], (View) bindings[6]);
        this.mDirtyFlags = -1L;
        this.errorText.setTag(null);
        this.iconPhoto.setTag(null);
        this.iconUpload.setTag(null);
        this.iconVerify.setTag(null);
        this.labelPage.setTag(null);
        this.line1.setTag(null);
        this.line2.setTag(null);
        MaterialCardView materialCardView = (MaterialCardView) bindings[0];
        this.mboundView0 = materialCardView;
        materialCardView.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[4];
        this.mboundView4 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (9 == variableId) {
            setElectionJmlLembar((Integer) variable);
        } else if (11 == variableId) {
            setElectionPageWithRelation((ElectionPageWithRelation) variable);
        } else if (10 != variableId) {
            return false;
        } else {
            setElectionPage((ElectionPage) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.ItemElectionPageCaptureC1Binding
    public void setElectionJmlLembar(Integer ElectionJmlLembar) {
        this.mElectionJmlLembar = ElectionJmlLembar;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(9);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ItemElectionPageCaptureC1Binding
    public void setElectionPageWithRelation(ElectionPageWithRelation ElectionPageWithRelation) {
        this.mElectionPageWithRelation = ElectionPageWithRelation;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ItemElectionPageCaptureC1Binding
    public void setElectionPage(ElectionPage ElectionPage) {
        this.mElectionPage = ElectionPage;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(10);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.ItemElectionPageCaptureC1BindingImpl.executeBindings():void");
    }
}
