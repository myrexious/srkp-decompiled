package org.bouncycastle.operator.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;

/* loaded from: classes2.dex */
public class BcDigestCalculatorProvider implements DigestCalculatorProvider {
    private BcDigestProvider digestProvider = BcDefaultDigestProvider.INSTANCE;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DigestOutputStream extends OutputStream {
        private Digest dig;

        DigestOutputStream(Digest digest) {
            this.dig = digest;
        }

        byte[] getDigest() {
            byte[] bArr = new byte[this.dig.getDigestSize()];
            this.dig.doFinal(bArr, 0);
            return bArr;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.dig.update((byte) i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.dig.update(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.dig.update(bArr, i, i2);
        }
    }

    @Override // org.bouncycastle.operator.DigestCalculatorProvider
    public DigestCalculator get(final AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
        final DigestOutputStream digestOutputStream = new DigestOutputStream(this.digestProvider.get(algorithmIdentifier));
        return new DigestCalculator() { // from class: org.bouncycastle.operator.bc.BcDigestCalculatorProvider.1
            @Override // org.bouncycastle.operator.DigestCalculator
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier;
            }

            @Override // org.bouncycastle.operator.DigestCalculator
            public byte[] getDigest() {
                return digestOutputStream.getDigest();
            }

            @Override // org.bouncycastle.operator.DigestCalculator
            public OutputStream getOutputStream() {
                return digestOutputStream;
            }
        };
    }
}
