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
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.login.LoginViewModel;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* loaded from: classes2.dex */
public abstract class FragmentLoginBinding extends ViewDataBinding {
    public final ImageView appLogo;
    public final TextView appName;
    public final Button buttonLogin;
    @Bindable
    protected AuthRequestUseCase mAuthRequestUseCase;
    @Bindable
    protected LoginViewModel mLoginViewModel;
    public final TextView versionCode;
    public final TextView versionName;

    public abstract void setAuthRequestUseCase(AuthRequestUseCase authRequestUseCase);

    public abstract void setLoginViewModel(LoginViewModel loginViewModel);

    public FragmentLoginBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView appLogo, TextView appName, Button buttonLogin, TextView versionCode, TextView versionName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appLogo = appLogo;
        this.appName = appName;
        this.buttonLogin = buttonLogin;
        this.versionCode = versionCode;
        this.versionName = versionName;
    }

    public AuthRequestUseCase getAuthRequestUseCase() {
        return this.mAuthRequestUseCase;
    }

    public LoginViewModel getLoginViewModel() {
        return this.mLoginViewModel;
    }

    public static FragmentLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_login, root, attachToRoot, component);
    }

    public static FragmentLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentLoginBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_login, null, false, component);
    }

    public static FragmentLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginBinding bind(View view, Object component) {
        return (FragmentLoginBinding) bind(component, view, R.layout.fragment_login);
    }
}
