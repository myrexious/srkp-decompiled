package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.verify.BaseVerifyViewModel;
import org.informatika.sirekap.ui.verify.FormC1ListItem;

/* loaded from: classes2.dex */
public abstract class DialogCheckFormC1ListItemBinding extends ViewDataBinding {
    public final Button buttonSave;
    public final CheckBox checkboxL;
    public final CheckBox checkboxP;
    public final CheckBox checkboxTotal;
    public final TextInputEditText inputCorrectionL;
    public final TextInputEditText inputCorrectionP;
    public final TextInputEditText inputCorrectionTotal;
    public final ImageView kesesuaianSliceBitmap;
    public final TextView kesesuaianSliceError;
    public final TextView kesesuaianSliceErrorDetail;
    public final TextView kesesuaianSliceErrorHideDetail;
    public final TextView kesesuaianSliceErrorShowDetail;
    @Bindable
    protected Boolean mIsLoading;
    @Bindable
    protected FormC1ListItem mItem;
    @Bindable
    protected Boolean mShowCorrection;
    @Bindable
    protected BaseVerifyViewModel mViewModel;
    public final LinearLayout rowL;
    public final LinearLayout rowP;
    public final LinearLayout rowTotal;
    public final TextView textCaptionPpwpHal2;
    public final TextView textSubtitle;
    public final TextView textTitle;

    public abstract void setIsLoading(Boolean isLoading);

    public abstract void setItem(FormC1ListItem item);

    public abstract void setShowCorrection(Boolean showCorrection);

    public abstract void setViewModel(BaseVerifyViewModel viewModel);

    public DialogCheckFormC1ListItemBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonSave, CheckBox checkboxL, CheckBox checkboxP, CheckBox checkboxTotal, TextInputEditText inputCorrectionL, TextInputEditText inputCorrectionP, TextInputEditText inputCorrectionTotal, ImageView kesesuaianSliceBitmap, TextView kesesuaianSliceError, TextView kesesuaianSliceErrorDetail, TextView kesesuaianSliceErrorHideDetail, TextView kesesuaianSliceErrorShowDetail, LinearLayout rowL, LinearLayout rowP, LinearLayout rowTotal, TextView textCaptionPpwpHal2, TextView textSubtitle, TextView textTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonSave = buttonSave;
        this.checkboxL = checkboxL;
        this.checkboxP = checkboxP;
        this.checkboxTotal = checkboxTotal;
        this.inputCorrectionL = inputCorrectionL;
        this.inputCorrectionP = inputCorrectionP;
        this.inputCorrectionTotal = inputCorrectionTotal;
        this.kesesuaianSliceBitmap = kesesuaianSliceBitmap;
        this.kesesuaianSliceError = kesesuaianSliceError;
        this.kesesuaianSliceErrorDetail = kesesuaianSliceErrorDetail;
        this.kesesuaianSliceErrorHideDetail = kesesuaianSliceErrorHideDetail;
        this.kesesuaianSliceErrorShowDetail = kesesuaianSliceErrorShowDetail;
        this.rowL = rowL;
        this.rowP = rowP;
        this.rowTotal = rowTotal;
        this.textCaptionPpwpHal2 = textCaptionPpwpHal2;
        this.textSubtitle = textSubtitle;
        this.textTitle = textTitle;
    }

    public Boolean getShowCorrection() {
        return this.mShowCorrection;
    }

    public FormC1ListItem getItem() {
        return this.mItem;
    }

    public BaseVerifyViewModel getViewModel() {
        return this.mViewModel;
    }

    public Boolean getIsLoading() {
        return this.mIsLoading;
    }

    public static DialogCheckFormC1ListItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCheckFormC1ListItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogCheckFormC1ListItemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_check_form_c1_list_item, root, attachToRoot, component);
    }

    public static DialogCheckFormC1ListItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCheckFormC1ListItemBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogCheckFormC1ListItemBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_check_form_c1_list_item, null, false, component);
    }

    public static DialogCheckFormC1ListItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogCheckFormC1ListItemBinding bind(View view, Object component) {
        return (DialogCheckFormC1ListItemBinding) bind(component, view, R.layout.dialog_check_form_c1_list_item);
    }
}
