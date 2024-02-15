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
import org.informatika.sirekap.model.Attendance;
import org.informatika.sirekap.model.AttendanceWithPages;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel;

/* loaded from: classes2.dex */
public class ViewLockAttendanceBindingImpl extends ViewLockAttendanceBinding {
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

    public ViewLockAttendanceBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ViewLockAttendanceBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
            this.mDirtyFlags = 256L;
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
        if (2 == variableId) {
            setAttendance((Attendance) variable);
        } else if (87 != variableId) {
            return false;
        } else {
            setViewModel((WitnessAttendanceListViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.ViewLockAttendanceBinding
    public void setAttendance(Attendance Attendance) {
        this.mAttendance = Attendance;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ViewLockAttendanceBinding
    public void setViewModel(WitnessAttendanceListViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
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
                            return onChangeViewModelAttendance((LiveData) object, fieldId);
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

    private boolean onChangeViewModelAttendance(LiveData<AttendanceWithPages> ViewModelAttendance, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:216:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0127  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 554
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.ViewLockAttendanceBindingImpl.executeBindings():void");
    }
}
