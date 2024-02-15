package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.DeviceInitializationViewModel;

/* loaded from: classes2.dex */
public abstract class ActivityDeviceInitializationBinding extends ViewDataBinding {
    public final Button buttonRetry;
    @Bindable
    protected DeviceInitializationViewModel mFragmentViewModel;
    public final TextView subtitleError;
    public final TextView titleError;
    public final TextView versionCode;
    public final TextView versionName;

    public abstract void setFragmentViewModel(DeviceInitializationViewModel fragmentViewModel);

    public ActivityDeviceInitializationBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonRetry, TextView subtitleError, TextView titleError, TextView versionCode, TextView versionName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonRetry = buttonRetry;
        this.subtitleError = subtitleError;
        this.titleError = titleError;
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public DeviceInitializationViewModel getFragmentViewModel() {
        return this.mFragmentViewModel;
    }

    public static ActivityDeviceInitializationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDeviceInitializationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDeviceInitializationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_device_initialization, root, attachToRoot, component);
    }

    public static ActivityDeviceInitializationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDeviceInitializationBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDeviceInitializationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_device_initialization, null, false, component);
    }

    public static ActivityDeviceInitializationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDeviceInitializationBinding bind(View view, Object component) {
        return (ActivityDeviceInitializationBinding) bind(component, view, R.layout.activity_device_initialization);
    }
}
