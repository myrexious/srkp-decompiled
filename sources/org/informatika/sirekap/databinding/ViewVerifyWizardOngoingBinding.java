package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel;

/* loaded from: classes2.dex */
public abstract class ViewVerifyWizardOngoingBinding extends ViewDataBinding {
    public final Button buttonBack;
    public final MaterialButton buttonCheckCancel;
    public final MaterialButton buttonCheckFalse;
    public final MaterialButton buttonCheckOk;
    public final MaterialButton buttonCheckTrue;
    public final Button buttonFinish;
    public final Button buttonNext;
    public final MaterialButtonToggleGroup buttonToggleCheck;
    public final MaterialButtonToggleGroup buttonToggleCheckCorrection;
    public final TextView content;
    public final ImageView imageForm;
    public final TextInputEditText inputCorrection;
    @Bindable
    protected VerifyWizardViewModel mViewModel;
    public final TextView subtitle;
    public final TextInputLayout textInputLayoutCorrection;
    public final TextView title;
    public final TextView titleCorrection;
    public final TextView value;

    public abstract void setViewModel(VerifyWizardViewModel viewModel);

    public ViewVerifyWizardOngoingBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonBack, MaterialButton buttonCheckCancel, MaterialButton buttonCheckFalse, MaterialButton buttonCheckOk, MaterialButton buttonCheckTrue, Button buttonFinish, Button buttonNext, MaterialButtonToggleGroup buttonToggleCheck, MaterialButtonToggleGroup buttonToggleCheckCorrection, TextView content, ImageView imageForm, TextInputEditText inputCorrection, TextView subtitle, TextInputLayout textInputLayoutCorrection, TextView title, TextView titleCorrection, TextView value) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonBack = buttonBack;
        this.buttonCheckCancel = buttonCheckCancel;
        this.buttonCheckFalse = buttonCheckFalse;
        this.buttonCheckOk = buttonCheckOk;
        this.buttonCheckTrue = buttonCheckTrue;
        this.buttonFinish = buttonFinish;
        this.buttonNext = buttonNext;
        this.buttonToggleCheck = buttonToggleCheck;
        this.buttonToggleCheckCorrection = buttonToggleCheckCorrection;
        this.content = content;
        this.imageForm = imageForm;
        this.inputCorrection = inputCorrection;
        this.subtitle = subtitle;
        this.textInputLayoutCorrection = textInputLayoutCorrection;
        this.title = title;
        this.titleCorrection = titleCorrection;
        this.value = value;
    }

    public VerifyWizardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ViewVerifyWizardOngoingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewVerifyWizardOngoingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewVerifyWizardOngoingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_verify_wizard_ongoing, root, attachToRoot, component);
    }

    public static ViewVerifyWizardOngoingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewVerifyWizardOngoingBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewVerifyWizardOngoingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_verify_wizard_ongoing, null, false, component);
    }

    public static ViewVerifyWizardOngoingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewVerifyWizardOngoingBinding bind(View view, Object component) {
        return (ViewVerifyWizardOngoingBinding) bind(component, view, R.layout.view_verify_wizard_ongoing);
    }
}
