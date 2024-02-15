package com.tom_roush.pdfbox.pdmodel.encryption;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* loaded from: classes3.dex */
public class SecurityProvider {
    private static Provider provider;

    private SecurityProvider() {
    }

    public static Provider getProvider() throws IOException {
        if (provider == null) {
            try {
                Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
                Security.addProvider(new BouncyCastleProvider());
                provider = (Provider) Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (ClassNotFoundException e) {
                throw new IOException(e);
            } catch (IllegalAccessException e2) {
                throw new IOException(e2);
            } catch (InstantiationException e3) {
                throw new IOException(e3);
            } catch (NoSuchMethodException e4) {
                throw new IOException(e4);
            } catch (InvocationTargetException e5) {
                throw new IOException(e5);
            }
        }
        return provider;
    }

    public static void setProvider(Provider provider2) {
        provider = provider2;
    }
}
