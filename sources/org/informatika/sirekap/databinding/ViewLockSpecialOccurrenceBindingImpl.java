package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.model.SpecialOccurrenceWithPages;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel;

/* loaded from: classes2.dex */
public class ViewLockSpecialOccurrenceBindingImpl extends ViewLockSpecialOccurrenceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final CircularProgressIndicator mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final CircularProgressIndicator mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    public ViewLockSpecialOccurrenceBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ViewLockSpecialOccurrenceBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, (Button) bindings[2], (Button) bindings[11], (Button) bindings[6], (Button) bindings[10]);
        this.mDirtyFlags = -1L;
        this.buttonCreatePdf.setTag(null);
        this.buttonShareZip.setTag(null);
        this.buttonUploadPdf.setTag(null);
        this.buttonUploadPdfOffline.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        CircularProgressIndicator circularProgressIndicator = (CircularProgressIndicator) bindings[3];
        this.mboundView3 = circularProgressIndicator;
        circularProgressIndicator.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
        CircularProgressIndicator circularProgressIndicator2 = (CircularProgressIndicator) bindings[7];
        this.mboundView7 = circularProgressIndicator2;
        circularProgressIndicator2.setTag(null);
        TextView textView4 = (TextView) bindings[8];
        this.mboundView8 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[9];
        this.mboundView9 = textView5;
        textView5.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128L;
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
            setViewModel((SpecialOccurrenceViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ViewLockSpecialOccurrenceBinding
    public void setViewModel(SpecialOccurrenceViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                if (localFieldId != 2) {
                    if (localFieldId != 3) {
                        if (localFieldId != 4) {
                            if (localFieldId != 5) {
                                return false;
                            }
                            return onChangeViewModelSpecialOccurrence((LiveData) object, fieldId);
                        }
                        return onChangeViewModelIsLoadingZip((LiveData) object, fieldId);
                    }
                    return onChangeViewModelIsZipped1((LiveData) object, fieldId);
                }
                return onChangeViewModelIsZipped((LiveData) object, fieldId);
            }
            return onChangeViewModelIsEmpty((LiveData) object, fieldId);
        }
        return onChangeViewModelIsAnyUnverified((LiveData) object, fieldId);
    }

    private boolean onChangeViewModelIsAnyUnverified(LiveData<Boolean> ViewModelIsAnyUnverified, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsEmpty(LiveData<Boolean> ViewModelIsEmpty, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsZipped(LiveData<Boolean> ViewModelIsZipped, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsZipped1(LiveData<Boolean> ViewModelIsZipped, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsLoadingZip(LiveData<Boolean> ViewModelIsLoadingZip, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelSpecialOccurrence(LiveData<SpecialOccurrenceWithPages> ViewModelSpecialOccurrence, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:215:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:307:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 623
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.ViewLockSpecialOccurrenceBindingImpl.executeBindings():void");
    }
}
