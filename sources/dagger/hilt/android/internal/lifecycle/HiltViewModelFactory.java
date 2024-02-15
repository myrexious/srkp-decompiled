package dagger.hilt.android.internal.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistryOwner;
import dagger.Module;
import dagger.hilt.EntryPoints;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.multibindings.Multibinds;
import java.io.Closeable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.inject.Provider;

/* loaded from: classes3.dex */
public final class HiltViewModelFactory implements ViewModelProvider.Factory {
    private final ViewModelProvider.Factory delegateFactory;
    private final AbstractSavedStateViewModelFactory hiltViewModelFactory;
    private final Set<String> hiltViewModelKeys;

    /* loaded from: classes3.dex */
    interface ActivityCreatorEntryPoint {
        ViewModelComponentBuilder getViewModelComponentBuilder();

        Set<String> getViewModelKeys();
    }

    /* loaded from: classes3.dex */
    public interface ViewModelFactoriesEntryPoint {
        Map<String, Provider<ViewModel>> getHiltViewModelMap();
    }

    @Module
    /* loaded from: classes3.dex */
    interface ViewModelModule {
        @Multibinds
        Map<String, ViewModel> hiltViewModelMap();
    }

    public HiltViewModelFactory(SavedStateRegistryOwner owner, Bundle defaultArgs, Set<String> hiltViewModelKeys, ViewModelProvider.Factory delegateFactory, final ViewModelComponentBuilder viewModelComponentBuilder) {
        this.hiltViewModelKeys = hiltViewModelKeys;
        this.delegateFactory = delegateFactory;
        this.hiltViewModelFactory = new AbstractSavedStateViewModelFactory() { // from class: dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.1
            @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
            protected <T extends ViewModel> T create(String key, Class<T> modelClass, SavedStateHandle handle) {
                final RetainedLifecycleImpl retainedLifecycleImpl = new RetainedLifecycleImpl();
                Provider<ViewModel> provider = ((ViewModelFactoriesEntryPoint) EntryPoints.get(viewModelComponentBuilder.savedStateHandle(handle).viewModelLifecycle(retainedLifecycleImpl).build(), ViewModelFactoriesEntryPoint.class)).getHiltViewModelMap().get(modelClass.getName());
                if (provider == null) {
                    throw new IllegalStateException("Expected the @HiltViewModel-annotated class '" + modelClass.getName() + "' to be available in the multi-binding of @HiltViewModelMap but none was found.");
                }
                T t = (T) provider.get();
                Objects.requireNonNull(retainedLifecycleImpl);
                t.addCloseable(new Closeable() { // from class: dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$1$$ExternalSyntheticLambda0
                    @Override // java.io.Closeable, java.lang.AutoCloseable
                    public final void close() {
                        RetainedLifecycleImpl.this.dispatchOnCleared();
                    }
                });
                return t;
            }
        };
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass, CreationExtras extras) {
        if (this.hiltViewModelKeys.contains(modelClass.getName())) {
            return (T) this.hiltViewModelFactory.create(modelClass, extras);
        }
        return (T) this.delegateFactory.create(modelClass, extras);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (this.hiltViewModelKeys.contains(modelClass.getName())) {
            return (T) this.hiltViewModelFactory.create(modelClass);
        }
        return (T) this.delegateFactory.create(modelClass);
    }

    public static ViewModelProvider.Factory createInternal(Activity activity, SavedStateRegistryOwner owner, Bundle defaultArgs, ViewModelProvider.Factory delegateFactory) {
        ActivityCreatorEntryPoint activityCreatorEntryPoint = (ActivityCreatorEntryPoint) EntryPoints.get(activity, ActivityCreatorEntryPoint.class);
        return new HiltViewModelFactory(owner, defaultArgs, activityCreatorEntryPoint.getViewModelKeys(), delegateFactory, activityCreatorEntryPoint.getViewModelComponentBuilder());
    }
}
