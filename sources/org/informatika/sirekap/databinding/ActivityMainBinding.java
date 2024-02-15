package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import org.informatika.sirekap.R;
import org.informatika.sirekap.usecase.AuthRequestUseCase;

/* loaded from: classes2.dex */
public abstract class ActivityMainBinding extends ViewDataBinding {
    public final AppBarLayout appBarLayout;
    @Bindable
    protected AuthRequestUseCase mAuthRequestUseCase;
    public final FragmentContainerView navHostFragment;
    public final MaterialToolbar toolbar;

    public abstract void setAuthRequestUseCase(AuthRequestUseCase authRequestUseCase);

    public ActivityMainBinding(Object _bindingComponent, View _root, int _localFieldCount, AppBarLayout appBarLayout, FragmentContainerView navHostFragment, MaterialToolbar toolbar) {
        super(_bindingComponent, _root, _localFieldCount);
        this.appBarLayout = appBarLayout;
        this.navHostFragment = navHostFragment;
        this.toolbar = toolbar;
    }

    public AuthRequestUseCase getAuthRequestUseCase() {
        return this.mAuthRequestUseCase;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main, root, attachToRoot, component);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMainBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_main, null, false, component);
    }

    public static ActivityMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding bind(View view, Object component) {
        return (ActivityMainBinding) bind(component, view, R.layout.activity_main);
    }
}
