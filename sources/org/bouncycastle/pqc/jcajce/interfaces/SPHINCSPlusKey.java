package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.Key;
import org.bouncycastle.pqc.jcajce.spec.SPHINCSPlusParameterSpec;

/* loaded from: classes2.dex */
public interface SPHINCSPlusKey extends Key {
    SPHINCSPlusParameterSpec getParameterSpec();
}
