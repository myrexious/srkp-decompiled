package org.informatika.sirekap.support.security.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;

/* compiled from: SHA1DigestCalculator.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/informatika/sirekap/support/security/util/SHA1DigestCalculator;", "Lorg/bouncycastle/operator/DigestCalculator;", "()V", "result", "Ljava/io/ByteArrayOutputStream;", "getAlgorithmIdentifier", "Lorg/bouncycastle/asn1/x509/AlgorithmIdentifier;", "getDigest", "", "getOutputStream", "Ljava/io/OutputStream;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SHA1DigestCalculator implements DigestCalculator {
    private final ByteArrayOutputStream result = new ByteArrayOutputStream();

    @Override // org.bouncycastle.operator.DigestCalculator
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
    }

    @Override // org.bouncycastle.operator.DigestCalculator
    public OutputStream getOutputStream() {
        return this.result;
    }

    @Override // org.bouncycastle.operator.DigestCalculator
    public byte[] getDigest() {
        byte[] byteArray = this.result.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "result.toByteArray()");
        this.result.reset();
        try {
            byte[] digest = MessageDigest.getInstance("SHA-1").digest(byteArray);
            Intrinsics.checkNotNullExpressionValue(digest, "{\n            val md = M…d.digest(bytes)\n        }");
            return digest;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
