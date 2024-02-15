package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.Key;
import org.bouncycastle.pqc.jcajce.spec.SIKEParameterSpec;

/* loaded from: classes2.dex */
public interface SIKEKey extends Key {
    SIKEParameterSpec getParameterSpec();
}
