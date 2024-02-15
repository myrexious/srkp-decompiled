package org.informatika.sirekap.ui;

import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes4.dex */
public final class EncryptedSharedPreferences_Factory implements Factory<EncryptedSharedPreferences> {
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public EncryptedSharedPreferences_Factory(Provider<SharedPreferences> sharedPreferencesProvider) {
        this.sharedPreferencesProvider = sharedPreferencesProvider;
    }

    @Override // javax.inject.Provider
    public EncryptedSharedPreferences get() {
        return newInstance(this.sharedPreferencesProvider.get());
    }

    public static EncryptedSharedPreferences_Factory create(Provider<SharedPreferences> sharedPreferencesProvider) {
        return new EncryptedSharedPreferences_Factory(sharedPreferencesProvider);
    }

    public static EncryptedSharedPreferences newInstance(SharedPreferences sharedPreferences) {
        return new EncryptedSharedPreferences(sharedPreferences);
    }
}
