package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.sendImage.GetElectionPageUseCase;
import org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel;

/* loaded from: classes2.dex */
public abstract class ViewVerifyWizardStartBinding extends ViewDataBinding {
    public final Button buttonRestartStartVerification;
    public final Button buttonResumeVerification;
    public final Button buttonStartVerification;
    public final TextView captionStartVerification;
    public final LinearLayout captionStartVerification2;
    public final ImageView iconVerify;
    @Bindable
    protected GetElectionPageUseCase mGetElectionPageUseCase;
    @Bindable
    protected VerifyWizardViewModel mViewModel;
    public final TextView subtitleStartVerification;
    public final TextView titleStartVerification;

    public abstract void setGetElectionPageUseCase(GetElectionPageUseCase getElectionPageUseCase);

    public abstract void setViewModel(VerifyWizardViewModel viewModel);

    public ViewVerifyWizardStartBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonRestartStartVerification, Button buttonResumeVerification, Button buttonStartVerification, TextView captionStartVerification, LinearLayout captionStartVerification2, ImageView iconVerify, TextView subtitleStartVerification, TextView titleStartVerification) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonRestartStartVerification = buttonRestartStartVerification;
        this.buttonResumeVerification = buttonResumeVerification;
        this.buttonStartVerification = buttonStartVerification;
        this.captionStartVerification = captionStartVerification;
        this.captionStartVerification2 = captionStartVerification2;
        this.iconVerify = iconVerify;
        this.subtitleStartVerification = subtitleStartVerification;
        this.titleStartVerification = titleStartVerification;
    }

    public VerifyWizardViewModel getViewModel() {
        return this.mViewModel;
    }

    public GetElectionPageUseCase getGetElectionPageUseCase() {
        return this.mGetElectionPageUseCase;
    }

    public static ViewVerifyWizardStartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewVerifyWizardStartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewVerifyWizardStartBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_verify_wizard_start, root, attachToRoot, component);
    }

    public static ViewVerifyWizardStartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewVerifyWizardStartBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewVerifyWizardStartBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_verify_wizard_start, null, false, component);
    }

    public static ViewVerifyWizardStartBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewVerifyWizardStartBinding bind(View view, Object component) {
        return (ViewVerifyWizardStartBinding) bind(component, view, R.layout.view_verify_wizard_start);
    }
}
