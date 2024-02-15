package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel;

/* loaded from: classes2.dex */
public class ViewSpecialOccurrenceOngoingBindingImpl extends ViewSpecialOccurrenceOngoingBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.card_image, 5);
        sparseIntArray.put(R.id.icon_container, 6);
        sparseIntArray.put(R.id.card_instruction, 7);
        sparseIntArray.put(R.id.label_photo_instruction, 8);
        sparseIntArray.put(R.id.button_retake_photo, 9);
    }

    public ViewSpecialOccurrenceOngoingBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ViewSpecialOccurrenceOngoingBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3, (Button) bindings[2], (Button) bindings[4], (Button) bindings[3], (Button) bindings[9], (MaterialCardView) bindings[5], (MaterialCardView) bindings[7], (ImageView) bindings[6], (TextView) bindings[8]);
        this.mDirtyFlags = -1L;
        this.buttonBack.setTag(null);
        this.buttonFinish.setTag(null);
        this.buttonNext.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
            setViewModel((VerifySpecialOccurrenceViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ViewSpecialOccurrenceOngoingBinding
    public void setViewModel(VerifySpecialOccurrenceViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            if (localFieldId != 1) {
                if (localFieldId != 2) {
                    return false;
                }
                return onChangeViewModelFirstItem((LiveData) object, fieldId);
            }
            return onChangeViewModelLastItem((CombinedLiveData) object, fieldId);
        }
        return onChangeViewModelPreviewBitmap((LiveData) object, fieldId);
    }

    private boolean onChangeViewModelPreviewBitmap(LiveData<String> ViewModelPreviewBitmap, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelLastItem(CombinedLiveData<Boolean> ViewModelLastItem, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelFirstItem(LiveData<Boolean> ViewModelFirstItem, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x008a  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            r20 = this;
            r1 = r20
            monitor-enter(r20)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> Lb7
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> Lb7
            monitor-exit(r20)     // Catch: java.lang.Throwable -> Lb7
            org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel r0 = r1.mViewModel
            r6 = 31
            long r6 = r6 & r2
            int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            r7 = 26
            r9 = 25
            r11 = 28
            r13 = 0
            if (r6 == 0) goto L8e
            long r15 = r2 & r9
            int r6 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r6 == 0) goto L34
            if (r0 == 0) goto L27
            androidx.lifecycle.LiveData r6 = r0.getPreviewBitmap()
            goto L28
        L27:
            r6 = 0
        L28:
            r1.updateLiveDataRegistration(r13, r6)
            if (r6 == 0) goto L34
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L35
        L34:
            r6 = 0
        L35:
            long r15 = r2 & r7
            int r15 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r15 == 0) goto L60
            if (r0 == 0) goto L42
            org.informatika.sirekap.support.livedata.CombinedLiveData r15 = r0.isLastItem()
            goto L43
        L42:
            r15 = 0
        L43:
            r13 = 1
            r1.updateLiveDataRegistration(r13, r15)
            if (r15 == 0) goto L50
            java.lang.Object r13 = r15.getValue()
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            goto L51
        L50:
            r13 = 0
        L51:
            boolean r13 = androidx.databinding.ViewDataBinding.safeUnbox(r13)
            r15 = r13 ^ 1
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r15)
            boolean r15 = androidx.databinding.ViewDataBinding.safeUnbox(r15)
            goto L62
        L60:
            r13 = 0
            r15 = 0
        L62:
            long r17 = r2 & r11
            int r17 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r17 == 0) goto L8a
            if (r0 == 0) goto L6f
            androidx.lifecycle.LiveData r0 = r0.isFirstItem()
            goto L70
        L6f:
            r0 = 0
        L70:
            r14 = 2
            r1.updateLiveDataRegistration(r14, r0)
            if (r0 == 0) goto L7e
            java.lang.Object r0 = r0.getValue()
            r14 = r0
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            goto L7f
        L7e:
            r14 = 0
        L7f:
            boolean r0 = androidx.databinding.ViewDataBinding.safeUnbox(r14)
            r14 = r6
            r19 = r13
            r13 = r0
            r0 = r19
            goto L92
        L8a:
            r14 = r6
            r0 = r13
            r13 = 0
            goto L92
        L8e:
            r0 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L92:
            long r11 = r11 & r2
            int r6 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r6 == 0) goto L9c
            android.widget.Button r6 = r1.buttonBack
            org.informatika.sirekap.support.DataBindingAdaptersKt.hidden(r6, r13)
        L9c:
            long r6 = r2 & r7
            int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r6 == 0) goto Lac
            android.widget.Button r6 = r1.buttonFinish
            org.informatika.sirekap.support.DataBindingAdaptersKt.hidden(r6, r15)
            android.widget.Button r6 = r1.buttonNext
            org.informatika.sirekap.support.DataBindingAdaptersKt.hidden(r6, r0)
        Lac:
            long r2 = r2 & r9
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto Lb6
            android.widget.ImageView r0 = r1.mboundView1
            org.informatika.sirekap.support.DataBindingAdaptersKt.filePath(r0, r14)
        Lb6:
            return
        Lb7:
            r0 = move-exception
            monitor-exit(r20)     // Catch: java.lang.Throwable -> Lb7
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.ViewSpecialOccurrenceOngoingBindingImpl.executeBindings():void");
    }
}
