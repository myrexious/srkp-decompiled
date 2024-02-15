package org.informatika.sirekap.ui.witness.qrCode;

import dagger.MembersInjector;
import javax.inject.Provider;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* loaded from: classes4.dex */
public final class WitnessUrlListFragment_MembersInjector implements MembersInjector<WitnessUrlListFragment> {
    private final Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider;

    public WitnessUrlListFragment_MembersInjector(Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        this.encryptedSharedPreferencesProvider = encryptedSharedPreferencesProvider;
    }

    public static MembersInjector<WitnessUrlListFragment> create(Provider<EncryptedSharedPreferences> encryptedSharedPreferencesProvider) {
        return new WitnessUrlListFragment_MembersInjector(encryptedSharedPreferencesProvider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(WitnessUrlListFragment instance) {
        injectEncryptedSharedPreferences(instance, this.encryptedSharedPreferencesProvider.get());
    }

    public static void injectEncryptedSharedPreferences(WitnessUrlListFragment instance, EncryptedSharedPreferences encryptedSharedPreferences) {
        instance.encryptedSharedPreferences = encryptedSharedPreferences;
    }
}
