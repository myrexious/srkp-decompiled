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
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.loginTps.LoginTpsFormState;
import org.informatika.sirekap.ui.loginTps.LoginTpsViewModel;
import org.informatika.sirekap.usecase.GetListTpsUseCase;

/* loaded from: classes2.dex */
public abstract class FragmentLoginTpsBinding extends ViewDataBinding {
    public final ImageView appLogo;
    public final TextView appName;
    public final Button buttonLogin;
    public final Button buttonRetry;
    @Bindable
    protected GetListTpsUseCase mGetListTPSUseCase;
    @Bindable
    protected LoginTpsFormState mLoginTpsFormState;
    @Bindable
    protected LoginTpsViewModel mViewModel;
    public final TextView subtitleError;
    public final TextView titleError;
    public final TextInputLayout tps;
    public final MaterialAutoCompleteTextView tpsInput;

    public abstract void setGetListTPSUseCase(GetListTpsUseCase getListTPSUseCase);

    public abstract void setLoginTpsFormState(LoginTpsFormState loginTpsFormState);

    public abstract void setViewModel(LoginTpsViewModel viewModel);

    public FragmentLoginTpsBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView appLogo, TextView appName, Button buttonLogin, Button buttonRetry, TextView subtitleError, TextView titleError, TextInputLayout tps, MaterialAutoCompleteTextView tpsInput) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appLogo = appLogo;
        this.appName = appName;
        this.buttonLogin = buttonLogin;
        this.buttonRetry = buttonRetry;
        this.subtitleError = subtitleError;
        this.titleError = titleError;
        this.tps = tps;
        this.tpsInput = tpsInput;
    }

    public LoginTpsViewModel getViewModel() {
        return this.mViewModel;
    }

    public GetListTpsUseCase getGetListTPSUseCase() {
        return this.mGetListTPSUseCase;
    }

    public LoginTpsFormState getLoginTpsFormState() {
        return this.mLoginTpsFormState;
    }

    public static FragmentLoginTpsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginTpsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentLoginTpsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_login_tps, root, attachToRoot, component);
    }

    public static FragmentLoginTpsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginTpsBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentLoginTpsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_login_tps, null, false, component);
    }

    public static FragmentLoginTpsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLoginTpsBinding bind(View view, Object component) {
        return (FragmentLoginTpsBinding) bind(component, view, R.layout.fragment_login_tps);
    }
}
