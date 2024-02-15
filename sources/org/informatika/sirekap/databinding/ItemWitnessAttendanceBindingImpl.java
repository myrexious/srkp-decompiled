package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.DataBindingAdaptersKt;

/* loaded from: classes2.dex */
public class ItemWitnessAttendanceBindingImpl extends ItemWitnessAttendanceBinding {
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
        sparseIntArray.put(R.id.button_retake_photo, 3);
        sparseIntArray.put(R.id.button_delete_photo, 4);
        sparseIntArray.put(R.id.button_more_menu, 5);
    }

    public ItemWitnessAttendanceBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ItemWitnessAttendanceBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Button) bindings[4], (Button) bindings[5], (Button) bindings[3], (ImageView) bindings[1], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.image.setTag(null);
        this.labelName.setTag(null);
        MaterialCardView materialCardView = (MaterialCardView) bindings[0];
        this.mboundView0 = materialCardView;
        materialCardView.setTag(null);
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
        if (7 == variableId) {
            setCroppedPhotoPath((String) variable);
        } else if (75 != variableId) {
            return false;
        } else {
            setNomorHalaman((String) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.ItemWitnessAttendanceBinding
    public void setCroppedPhotoPath(String CroppedPhotoPath) {
        this.mCroppedPhotoPath = CroppedPhotoPath;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ItemWitnessAttendanceBinding
    public void setNomorHalaman(String NomorHalaman) {
        this.mNomorHalaman = NomorHalaman;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(75);
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
        String str2 = this.mNomorHalaman;
        int i = ((5 & j) > 0L ? 1 : ((5 & j) == 0L ? 0 : -1));
        int i2 = ((j & 6) > 0L ? 1 : ((j & 6) == 0L ? 0 : -1));
        if (i != 0) {
            DataBindingAdaptersKt.filePath(this.image, str);
        }
        if (i2 != 0) {
            TextViewBindingAdapter.setText(this.labelName, str2);
        }
    }
}
