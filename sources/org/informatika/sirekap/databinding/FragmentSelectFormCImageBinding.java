package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentSelectFormCImageBinding extends ViewDataBinding {
    public final Button buttonContinue;
    public final MaterialCardView c1ImageViewCard;
    public final MaterialCardView cardInfo;
    public final ImageView iconContainer;
    public final MaterialAutoCompleteTextView inputFieldPage;
    public final TextInputLayout inputPage;
    public final TextView labelInputElectionType;
    public final TextView labelInputPage;
    @Bindable
    protected SelectFormCImageViewModel mViewModel;
    public final RadioGroup radioGroup;

    public abstract void setViewModel(SelectFormCImageViewModel viewModel);

    public FragmentSelectFormCImageBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonContinue, MaterialCardView c1ImageViewCard, MaterialCardView cardInfo, ImageView iconContainer, MaterialAutoCompleteTextView inputFieldPage, TextInputLayout inputPage, TextView labelInputElectionType, TextView labelInputPage, RadioGroup radioGroup) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonContinue = buttonContinue;
        this.c1ImageViewCard = c1ImageViewCard;
        this.cardInfo = cardInfo;
        this.iconContainer = iconContainer;
        this.inputFieldPage = inputFieldPage;
        this.inputPage = inputPage;
        this.labelInputElectionType = labelInputElectionType;
        this.labelInputPage = labelInputPage;
        this.radioGroup = radioGroup;
    }

    public SelectFormCImageViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentSelectFormCImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSelectFormCImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSelectFormCImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_select_form_c_image, root, attachToRoot, component);
    }

    public static FragmentSelectFormCImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSelectFormCImageBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSelectFormCImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_select_form_c_image, null, false, component);
    }

    public static FragmentSelectFormCImageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSelectFormCImageBinding bind(View view, Object component) {
        return (FragmentSelectFormCImageBinding) bind(component, view, R.layout.fragment_select_form_c_image);
    }
}
