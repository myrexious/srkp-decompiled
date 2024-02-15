package org.informatika.sirekap.databinding;

import android.graphics.Bitmap;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.textfield.TextInputEditText;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.ui.verify.FormC1ListItem;

/* loaded from: classes2.dex */
public class DialogCheckFormC1ListItemBindingImpl extends DialogCheckFormC1ListItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener checkboxLandroidCheckedAttrChanged;
    private InverseBindingListener checkboxPandroidCheckedAttrChanged;
    private InverseBindingListener checkboxTotalandroidCheckedAttrChanged;
    private long mDirtyFlags;
    private final ScrollView mboundView0;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final LinearLayout mboundView13;
    private final ImageView mboundView15;
    private final TextView mboundView17;
    private final TextView mboundView18;
    private final LinearLayout mboundView19;
    private final ImageView mboundView20;
    private final TextView mboundView22;
    private final TextView mboundView23;
    private final LinearLayout mboundView24;
    private final View mboundView25;
    private final TextView mboundView7;
    private final ImageView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.text_title, 26);
        sparseIntArray.put(R.id.text_subtitle, 27);
        sparseIntArray.put(R.id.input_correction_l, 28);
        sparseIntArray.put(R.id.input_correction_p, 29);
        sparseIntArray.put(R.id.row_total, 30);
        sparseIntArray.put(R.id.input_correction_total, 31);
        sparseIntArray.put(R.id.button_save, 32);
    }

    public DialogCheckFormC1ListItemBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 33, sIncludes, sViewsWithIds));
    }

    private DialogCheckFormC1ListItemBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 6, (Button) bindings[32], (CheckBox) bindings[10], (CheckBox) bindings[16], (CheckBox) bindings[21], (TextInputEditText) bindings[28], (TextInputEditText) bindings[29], (TextInputEditText) bindings[31], (ImageView) bindings[2], (TextView) bindings[3], (TextView) bindings[6], (TextView) bindings[5], (TextView) bindings[4], (LinearLayout) bindings[8], (LinearLayout) bindings[14], (LinearLayout) bindings[30], (TextView) bindings[1], (TextView) bindings[27], (TextView) bindings[26]);
        this.checkboxLandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = DialogCheckFormC1ListItemBindingImpl.this.checkboxL.isChecked();
                BaseVerifyViewModel baseVerifyViewModel = DialogCheckFormC1ListItemBindingImpl.this.mViewModel;
                if (baseVerifyViewModel != null) {
                    MutableLiveData<Boolean> isWrongL = baseVerifyViewModel.isWrongL();
                    if (isWrongL != null) {
                        isWrongL.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.checkboxPandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBindingImpl.2
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = DialogCheckFormC1ListItemBindingImpl.this.checkboxP.isChecked();
                BaseVerifyViewModel baseVerifyViewModel = DialogCheckFormC1ListItemBindingImpl.this.mViewModel;
                if (baseVerifyViewModel != null) {
                    MutableLiveData<Boolean> isWrongP = baseVerifyViewModel.isWrongP();
                    if (isWrongP != null) {
                        isWrongP.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.checkboxTotalandroidCheckedAttrChanged = new InverseBindingListener() { // from class: org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBindingImpl.3
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = DialogCheckFormC1ListItemBindingImpl.this.checkboxTotal.isChecked();
                BaseVerifyViewModel baseVerifyViewModel = DialogCheckFormC1ListItemBindingImpl.this.mViewModel;
                if (baseVerifyViewModel != null) {
                    MutableLiveData<Boolean> isWrongTotal = baseVerifyViewModel.isWrongTotal();
                    if (isWrongTotal != null) {
                        isWrongTotal.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.checkboxL.setTag(null);
        this.checkboxP.setTag(null);
        this.checkboxTotal.setTag(null);
        this.kesesuaianSliceBitmap.setTag(null);
        this.kesesuaianSliceError.setTag(null);
        this.kesesuaianSliceErrorDetail.setTag(null);
        this.kesesuaianSliceErrorHideDetail.setTag(null);
        this.kesesuaianSliceErrorShowDetail.setTag(null);
        ScrollView scrollView = (ScrollView) bindings[0];
        this.mboundView0 = scrollView;
        scrollView.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[12];
        this.mboundView12 = textView2;
        textView2.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[13];
        this.mboundView13 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[15];
        this.mboundView15 = imageView;
        imageView.setTag(null);
        TextView textView3 = (TextView) bindings[17];
        this.mboundView17 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[18];
        this.mboundView18 = textView4;
        textView4.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[19];
        this.mboundView19 = linearLayout2;
        linearLayout2.setTag(null);
        ImageView imageView2 = (ImageView) bindings[20];
        this.mboundView20 = imageView2;
        imageView2.setTag(null);
        TextView textView5 = (TextView) bindings[22];
        this.mboundView22 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[23];
        this.mboundView23 = textView6;
        textView6.setTag(null);
        LinearLayout linearLayout3 = (LinearLayout) bindings[24];
        this.mboundView24 = linearLayout3;
        linearLayout3.setTag(null);
        View view = (View) bindings[25];
        this.mboundView25 = view;
        view.setTag(null);
        TextView textView7 = (TextView) bindings[7];
        this.mboundView7 = textView7;
        textView7.setTag(null);
        ImageView imageView3 = (ImageView) bindings[9];
        this.mboundView9 = imageView3;
        imageView3.setTag(null);
        this.rowL.setTag(null);
        this.rowP.setTag(null);
        this.textCaptionPpwpHal2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = FileUtils.ONE_KB;
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
        } else if (45 == variableId) {
            setIsLoading((Boolean) variable);
        } else if (80 == variableId) {
            setShowCorrection((Boolean) variable);
        } else if (87 != variableId) {
            return false;
        } else {
            setViewModel((BaseVerifyViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBinding
    public void setItem(FormC1ListItem Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(48);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBinding
    public void setIsLoading(Boolean IsLoading) {
        this.mIsLoading = IsLoading;
    }

    @Override // org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBinding
    public void setShowCorrection(Boolean ShowCorrection) {
        this.mShowCorrection = ShowCorrection;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(80);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBinding
    public void setViewModel(BaseVerifyViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 512;
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
                            return onChangeViewModelShowKesesuaianSliceError((MutableLiveData) object, fieldId);
                        }
                        return onChangeViewModelIsWrongL((MutableLiveData) object, fieldId);
                    }
                    return onChangeViewModelIsWrongP((MutableLiveData) object, fieldId);
                }
                return onChangeViewModelKesesuaianSliceError((MutableLiveData) object, fieldId);
            }
            return onChangeViewModelPreviewKesesuaianBitmap((MutableLiveData) object, fieldId);
        }
        return onChangeViewModelIsWrongTotal((MutableLiveData) object, fieldId);
    }

    private boolean onChangeViewModelIsWrongTotal(MutableLiveData<Boolean> ViewModelIsWrongTotal, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelPreviewKesesuaianBitmap(MutableLiveData<Bitmap> ViewModelPreviewKesesuaianBitmap, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelKesesuaianSliceError(MutableLiveData<String> ViewModelKesesuaianSliceError, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsWrongP(MutableLiveData<Boolean> ViewModelIsWrongP, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsWrongL(MutableLiveData<Boolean> ViewModelIsWrongL, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShowKesesuaianSliceError(MutableLiveData<Boolean> ViewModelShowKesesuaianSliceError, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x042c  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:541:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:544:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x04a0  */
    /* JADX WARN: Removed duplicated region for block: B:548:0x04ab  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:554:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:557:0x050a  */
    /* JADX WARN: Removed duplicated region for block: B:560:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x0531  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:568:0x057b  */
    /* JADX WARN: Removed duplicated region for block: B:570:0x0584  */
    /* JADX WARN: Removed duplicated region for block: B:573:0x0593  */
    /* JADX WARN: Removed duplicated region for block: B:576:0x05a0  */
    /* JADX WARN: Removed duplicated region for block: B:583:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 1451
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.DialogCheckFormC1ListItemBindingImpl.executeBindings():void");
    }
}
