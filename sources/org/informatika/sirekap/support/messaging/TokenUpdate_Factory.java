package org.informatika.sirekap.support.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public final class TokenUpdate_Factory implements Factory<TokenUpdate> {
    private final Provider<Context> contextProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public TokenUpdate_Factory(Provider<Context> contextProvider, Provider<SharedPreferences> sharedPreferencesProvider) {
        this.contextProvider = contextProvider;
        this.sharedPreferencesProvider = sharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public TokenUpdate get() {
        return newInstance(this.contextProvider.get(), this.sharedPreferencesProvider.get());
    }

    public static TokenUpdate_Factory create(Provider<Context> contextProvider, Provider<SharedPreferences> sharedPreferencesProvider) {
        return new TokenUpdate_Factory(contextProvider, sharedPreferencesProvider);
    }

    public static TokenUpdate newInstance(Context context, SharedPreferences sharedPreferences) {
        return new TokenUpdate(context, sharedPreferences);
    }
}
