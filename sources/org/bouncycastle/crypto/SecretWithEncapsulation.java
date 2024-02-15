package org.bouncycastle.crypto;

import javax.security.auth.Destroyable;

/* loaded from: classes2.dex */
public interface SecretWithEncapsulation extends Destroyable {
    byte[] getEncapsulation();

    byte[] getSecret();
}
