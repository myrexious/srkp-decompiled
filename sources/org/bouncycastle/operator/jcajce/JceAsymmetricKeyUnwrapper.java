package org.bouncycastle.operator.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyUnwrapper;

/* loaded from: classes2.dex */
public class JceAsymmetricKeyUnwrapper extends AsymmetricKeyUnwrapper {
    private Map extraMappings;
    private OperatorHelper helper;
    private PrivateKey privKey;
    private boolean unwrappedKeyMustBeEncodable;

    public JceAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        super(algorithmIdentifier);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.privKey = privateKey;
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x0043, code lost:
        if (r4.length == 0) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0049  */
    @Override // org.bouncycastle.operator.KeyUnwrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.bouncycastle.operator.GenericKey generateUnwrappedKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier r6, byte[] r7) throws org.bouncycastle.operator.OperatorException {
        /*
            r5 = this;
            org.bouncycastle.operator.jcajce.OperatorHelper r0 = r5.helper     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r1 = r5.getAlgorithmIdentifier()     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = r1.getAlgorithm()     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            java.util.Map r2 = r5.extraMappings     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            javax.crypto.Cipher r0 = r0.createAsymmetricWrapper(r1, r2)     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            org.bouncycastle.operator.jcajce.OperatorHelper r1 = r5.helper     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r2 = r5.getAlgorithmIdentifier()     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            java.security.AlgorithmParameters r1 = r1.createAlgorithmParameters(r2)     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            r2 = 4
            r3 = 0
            if (r1 == 0) goto L24
            java.security.PrivateKey r4 = r5.privKey     // Catch: java.lang.Throwable -> L47 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            r0.init(r2, r4, r1)     // Catch: java.lang.Throwable -> L47 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            goto L29
        L24:
            java.security.PrivateKey r4 = r5.privKey     // Catch: java.lang.Throwable -> L47 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            r0.init(r2, r4)     // Catch: java.lang.Throwable -> L47 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
        L29:
            org.bouncycastle.operator.jcajce.OperatorHelper r2 = r5.helper     // Catch: java.lang.Throwable -> L47 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = r6.getAlgorithm()     // Catch: java.lang.Throwable -> L47 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            java.lang.String r2 = r2.getKeyAlgorithmName(r4)     // Catch: java.lang.Throwable -> L47 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            r4 = 3
            java.security.Key r2 = r0.unwrap(r7, r2, r4)     // Catch: java.lang.Throwable -> L47 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            boolean r4 = r5.unwrappedKeyMustBeEncodable     // Catch: java.lang.Throwable -> L46 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            if (r4 == 0) goto L46
            byte[] r4 = r2.getEncoded()     // Catch: java.lang.Throwable -> L46 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            if (r4 == 0) goto L47
            int r4 = r4.length     // Catch: java.lang.Throwable -> L46 java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            if (r4 != 0) goto L46
            goto L47
        L46:
            r3 = r2
        L47:
            if (r3 != 0) goto L68
            r2 = 2
            if (r1 == 0) goto L52
            java.security.PrivateKey r3 = r5.privKey     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            r0.init(r2, r3, r1)     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            goto L57
        L52:
            java.security.PrivateKey r1 = r5.privKey     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            r0.init(r2, r1)     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
        L57:
            javax.crypto.spec.SecretKeySpec r3 = new javax.crypto.spec.SecretKeySpec     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            byte[] r7 = r0.doFinal(r7)     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = r6.getAlgorithm()     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            java.lang.String r0 = r0.getId()     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            r3.<init>(r7, r0)     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
        L68:
            org.bouncycastle.operator.jcajce.JceGenericKey r7 = new org.bouncycastle.operator.jcajce.JceGenericKey     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            r7.<init>(r6, r3)     // Catch: java.security.InvalidAlgorithmParameterException -> L6e javax.crypto.BadPaddingException -> L88 javax.crypto.IllegalBlockSizeException -> La2 java.security.InvalidKeyException -> Lbc
            return r7
        L6e:
            r6 = move-exception
            org.bouncycastle.operator.OperatorException r7 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "invalid algorithm parameters: "
            r0.<init>(r1)
            java.lang.String r1 = r6.getMessage()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        L88:
            r6 = move-exception
            org.bouncycastle.operator.OperatorException r7 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "bad padding: "
            r0.<init>(r1)
            java.lang.String r1 = r6.getMessage()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        La2:
            r6 = move-exception
            org.bouncycastle.operator.OperatorException r7 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "illegal blocksize: "
            r0.<init>(r1)
            java.lang.String r1 = r6.getMessage()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        Lbc:
            r6 = move-exception
            org.bouncycastle.operator.OperatorException r7 = new org.bouncycastle.operator.OperatorException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "key invalid: "
            r0.<init>(r1)
            java.lang.String r1 = r6.getMessage()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper.generateUnwrappedKey(org.bouncycastle.asn1.x509.AlgorithmIdentifier, byte[]):org.bouncycastle.operator.GenericKey");
    }

    public JceAsymmetricKeyUnwrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceAsymmetricKeyUnwrapper setMustProduceEncodableUnwrappedKey(boolean z) {
        this.unwrappedKeyMustBeEncodable = z;
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JceAsymmetricKeyUnwrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }
}
