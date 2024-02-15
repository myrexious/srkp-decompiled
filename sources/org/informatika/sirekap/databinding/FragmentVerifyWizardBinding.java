package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentVerifyWizardBinding extends ViewDataBinding {
    @Bindable
    protected VerifyWizardViewModel mViewModel;
    public final ViewVerifyWizardOngoingBinding viewVerifyWizardOngoing;
    public final ViewVerifyWizardStartBinding viewVerifyWizardStart;

    public abstract void setViewModel(VerifyWizardViewModel viewModel);

    public FragmentVerifyWizardBinding(Object _bindingComponent, View _root, int _localFieldCount, ViewVerifyWizardOngoingBinding viewVerifyWizardOngoing, ViewVerifyWizardStartBinding viewVerifyWizardStart) {
        super(_bindingComponent, _root, _localFieldCount);
        this.viewVerifyWizardOngoing = viewVerifyWizardOngoing;
        this.viewVerifyWizardStart = viewVerifyWizardStart;
    }

    public VerifyWizardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentVerifyWizardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifyWizardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentVerifyWizardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_verify_wizard, root, attachToRoot, component);
    }

    public static FragmentVerifyWizardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifyWizardBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentVerifyWizardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_verify_wizard, null, false, component);
    }

    public static FragmentVerifyWizardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentVerifyWizardBinding bind(View view, Object component) {
        return (FragmentVerifyWizardBinding) bind(component, view, R.layout.fragment_verify_wizard);
    }
}
