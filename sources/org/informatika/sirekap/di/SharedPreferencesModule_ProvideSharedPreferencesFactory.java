package org.informatika.sirekap.di;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SharedPreferencesModule_ProvideSharedPreferencesFactory implements Factory<SharedPreferences> {
    private final Provider<Context> contextProvider;

    public SharedPreferencesModule_ProvideSharedPreferencesFactory(Provider<Context> contextProvider) {
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public SharedPreferences get() {
        return provideSharedPreferences(this.contextProvider.get());
    }

    public static SharedPreferencesModule_ProvideSharedPreferencesFactory create(Provider<Context> contextProvider) {
        return new SharedPreferencesModule_ProvideSharedPreferencesFactory(contextProvider);
    }

    public static SharedPreferences provideSharedPreferences(Context context) {
        return (SharedPreferences) Preconditions.checkNotNullFromProvides(SharedPreferencesModule.INSTANCE.provideSharedPreferences(context));
    }
}
