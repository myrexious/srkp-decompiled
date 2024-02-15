package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.DataBindingAdaptersKt;

/* loaded from: classes2.dex */
public class ItemSpecialOccurrenceBindingImpl extends ItemSpecialOccurrenceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final MaterialCardView mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.button_retake_photo, 2);
        sparseIntArray.put(R.id.button_delete_photo, 3);
    }

    public ItemSpecialOccurrenceBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemSpecialOccurrenceBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Button) bindings[3], (Button) bindings[2], (ImageView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.image.setTag(null);
        MaterialCardView materialCardView = (MaterialCardView) bindings[0];
        this.mboundView0 = materialCardView;
        materialCardView.setTag(null);
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
        if (7 == variableId) {
            setCroppedPhotoPath((String) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ItemSpecialOccurrenceBinding
    public void setCroppedPhotoPath(String CroppedPhotoPath) {
        this.mCroppedPhotoPath = CroppedPhotoPath;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mCroppedPhotoPath;
        if ((j & 3) != 0) {
            DataBindingAdaptersKt.filePath(this.image, str);
        }
    }
}
