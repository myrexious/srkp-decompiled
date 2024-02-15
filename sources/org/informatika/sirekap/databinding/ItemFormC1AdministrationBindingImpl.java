package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.ui.verify.FormC1ListItem;

/* loaded from: classes2.dex */
public class ItemFormC1AdministrationBindingImpl extends ItemFormC1AdministrationBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final ImageView mboundView6;
    private final LinearLayout mboundView7;
    private final View mboundView8;
    private final TextView mboundView9;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemFormC1AdministrationBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ItemFormC1AdministrationBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[0]);
        this.mDirtyFlags = -1L;
        this.linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[5];
        this.mboundView5 = textView5;
        textView5.setTag(null);
        ImageView imageView = (ImageView) bindings[6];
        this.mboundView6 = imageView;
        imageView.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[7];
        this.mboundView7 = linearLayout;
        linearLayout.setTag(null);
        View view = (View) bindings[8];
        this.mboundView8 = view;
        view.setTag(null);
        TextView textView6 = (TextView) bindings[9];
        this.mboundView9 = textView6;
        textView6.setTag(null);
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
        if (48 == variableId) {
            setItem((FormC1ListItem) variable);
        } else if (80 == variableId) {
            setShowCorrection((Boolean) variable);
        } else if (43 != variableId) {
            return false;
        } else {
            setIsDone((Boolean) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.ItemFormC1AdministrationBinding
    public void setItem(FormC1ListItem Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(48);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ItemFormC1AdministrationBinding
    public void setShowCorrection(Boolean ShowCorrection) {
        this.mShowCorrection = ShowCorrection;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(80);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ItemFormC1AdministrationBinding
    public void setIsDone(Boolean IsDone) {
        this.mIsDone = IsDone;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(43);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:143:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 419
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.ItemFormC1AdministrationBindingImpl.executeBindings():void");
    }
}
