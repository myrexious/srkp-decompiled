package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.settings.SettingsViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentSettingsBinding extends ViewDataBinding {
    public final LinearLayout itemChangeElectionType;
    public final LinearLayout itemChangeProfile;
    @Bindable
    protected MainViewModel mMainViewModel;
    @Bindable
    protected SettingsViewModel mViewModel;

    public abstract void setMainViewModel(MainViewModel mainViewModel);

    public abstract void setViewModel(SettingsViewModel viewModel);

    public FragmentSettingsBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout itemChangeElectionType, LinearLayout itemChangeProfile) {
        super(_bindingComponent, _root, _localFieldCount);
        this.itemChangeElectionType = itemChangeElectionType;
        this.itemChangeProfile = itemChangeProfile;
    }

    public SettingsViewModel getViewModel() {
        return this.mViewModel;
    }

    public MainViewModel getMainViewModel() {
        return this.mMainViewModel;
    }

    public static FragmentSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentSettingsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_settings, root, attachToRoot, component);
    }

    public static FragmentSettingsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSettingsBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentSettingsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_settings, null, false, component);
    }

    public static FragmentSettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentSettingsBinding bind(View view, Object component) {
        return (FragmentSettingsBinding) bind(component, view, R.layout.fragment_settings);
    }
}
