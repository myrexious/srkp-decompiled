package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.security.CertificateViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentCertificateBinding extends ViewDataBinding {
    public final Button buttonCheck;
    public final Button buttonRetry;
    public final CircularProgressIndicator loadingProgress;
    public final TextView loadingText;
    @Bindable
    protected CertificateViewModel mFragmentViewModel;
    public final TextView subtitleError;
    public final TextView titleError;
    public final TextView versionCode;
    public final TextView versionName;

    public abstract void setFragmentViewModel(CertificateViewModel fragmentViewModel);

    public FragmentCertificateBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonCheck, Button buttonRetry, CircularProgressIndicator loadingProgress, TextView loadingText, TextView subtitleError, TextView titleError, TextView versionCode, TextView versionName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonCheck = buttonCheck;
        this.buttonRetry = buttonRetry;
        this.loadingProgress = loadingProgress;
        this.loadingText = loadingText;
        this.subtitleError = subtitleError;
        this.titleError = titleError;
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public CertificateViewModel getFragmentViewModel() {
        return this.mFragmentViewModel;
    }

    public static FragmentCertificateBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCertificateBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCertificateBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_certificate, root, attachToRoot, component);
    }

    public static FragmentCertificateBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCertificateBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCertificateBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_certificate, null, false, component);
    }

    public static FragmentCertificateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCertificateBinding bind(View view, Object component) {
        return (FragmentCertificateBinding) bind(component, view, R.layout.fragment_certificate);
    }
}
