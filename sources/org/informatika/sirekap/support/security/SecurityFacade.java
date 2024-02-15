package org.informatika.sirekap.support.security;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.encryption.AccessPermission;
import com.tom_roush.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.COSFilterInputStream;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.support.security.encrypt.Encryptor;
import org.informatika.sirekap.support.security.exceptions.ImageValidationException;
import org.informatika.sirekap.support.security.img.ImageSignatureUtil;
import org.informatika.sirekap.support.security.img.ImageSigner;
import org.informatika.sirekap.support.security.img.ImageValidator;
import org.informatika.sirekap.support.security.keystore.AndroidKeystoreManager;
import org.informatika.sirekap.support.security.keystore.KeystoreManager;
import org.informatika.sirekap.support.security.keystore.PKCS12KeystoreManager;
import org.informatika.sirekap.support.security.pdf.PdfEncrypt;
import org.informatika.sirekap.support.security.pdf.PdfMDPPermission;
import org.informatika.sirekap.support.security.pdf.PdfSigner;
import org.informatika.sirekap.support.security.signature.LocalVerifier;
import org.informatika.sirekap.support.security.signature.Signer;

/* compiled from: SecurityFacade.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015J\"\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00152\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0015J\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0015J\"\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00152\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0015J\u000e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010!\u001a\u0004\u0018\u00010\u00152\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u000fJ\u000e\u0010\"\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u0015J\u0010\u0010%\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u000e\u001a\u00020\u000fJ,\u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150(0'2\u0006\u0010\u0003\u001a\u00020\u00122\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0015J\u000e\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020+J\u000e\u0010,\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u0015J\u000e\u0010-\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u0015J\u000e\u0010.\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010/\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u000fJ\u0016\u00100\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000fJ\u001c\u00101\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00150'R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"Lorg/informatika/sirekap/support/security/SecurityFacade;", "", "()V", "keystore", "Ljava/security/KeyStore;", "kotlin.jvm.PlatformType", "addPdfVerificationInfo", "", "context", "Landroid/content/Context;", "authRequestUseCase", "Lorg/informatika/sirekap/usecase/AuthRequestUseCase;", "pdfLtv", "Lorg/informatika/sirekap/support/security/pdf/PdfLtv;", "pdfFile", "Ljava/io/File;", "(Landroid/content/Context;Lorg/informatika/sirekap/usecase/AuthRequestUseCase;Lorg/informatika/sirekap/support/security/pdf/PdfLtv;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildKeystoreManager", "Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;", "appContext", "decryptCode", "", "encrypted", "decryptString", "encryptedText", "keyAlias", "encryptCode", "plainText", "encryptString", "getCMSSignature", "signedData", "Lorg/bouncycastle/cms/CMSSignedData;", "getDeviceId", "getGsfAndroidId", "getImageSignature", "imageFile", "imagePath", "getPDFSignature", "getPublicKeyInformation", "", "Lkotlin/Pair;", "hashByte", "input", "", "hashString", "md5String", "protectPdfWithRandomKey", "signJpgImage", "signPdf", "validateImages", "imagePaths", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SecurityFacade {
    public static final SecurityFacade INSTANCE = new SecurityFacade();
    private static final KeyStore keystore;

    private SecurityFacade() {
    }

    static {
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.addProvider(new BouncyCastleProvider());
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        keystore = keyStore;
    }

    public final KeystoreManager buildKeystoreManager(Context appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        try {
            Boolean USE_SAMPLE_KEYSTORE = BuildConfig.USE_SAMPLE_KEYSTORE;
            Intrinsics.checkNotNullExpressionValue(USE_SAMPLE_KEYSTORE, "USE_SAMPLE_KEYSTORE");
            if (USE_SAMPLE_KEYSTORE.booleanValue()) {
                InputStream open = appContext.getAssets().open("keystore/Operator.p12");
                Intrinsics.checkNotNullExpressionValue(open, "assets.open(\"keystore/Operator.p12\")");
                return new PKCS12KeystoreManager(open, BuildConfig.KEYSTORE_PASSWORD);
            }
            return new AndroidKeystoreManager();
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            throw e;
        }
    }

    public final String md5String(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        byte[] bytes = input.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] digest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(digest, "digest");
        int length = digest.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(digest[i])}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
            str = str + format;
        }
        return str;
    }

    public final String hashString(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = input.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        byte[] digest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(digest, "digest");
        int length = digest.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(digest[i])}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
            str = str + format;
        }
        return str;
    }

    public final String hashByte(byte[] input) {
        Intrinsics.checkNotNullParameter(input, "input");
        byte[] digest = MessageDigest.getInstance("SHA-256").digest(input);
        Intrinsics.checkNotNullExpressionValue(digest, "digest");
        int length = digest.length;
        String str = "";
        for (int i = 0; i < length; i++) {
            String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(digest[i])}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
            str = str + format;
        }
        return str;
    }

    public static /* synthetic */ String encryptString$default(SecurityFacade securityFacade, KeystoreManager keystoreManager, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return securityFacade.encryptString(keystoreManager, str, str2);
    }

    public final String encryptString(KeystoreManager keystore2, String plainText, String str) {
        Encryptor buildEncryptor;
        Intrinsics.checkNotNullParameter(keystore2, "keystore");
        Intrinsics.checkNotNullParameter(plainText, "plainText");
        try {
            if (str == null) {
                buildEncryptor = keystore2.buildEncryptor();
            } else {
                buildEncryptor = keystore2.buildEncryptor(str);
            }
            byte[] bytes = plainText.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            String encodeToString = Base64.encodeToString(buildEncryptor.encrypt(bytes), 2);
            Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(encrypted, Base64.NO_WRAP)");
            return encodeToString;
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(new Exception(e));
            e.printStackTrace();
            throw e;
        }
    }

    public static /* synthetic */ String decryptString$default(SecurityFacade securityFacade, KeystoreManager keystoreManager, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return securityFacade.decryptString(keystoreManager, str, str2);
    }

    public final String decryptString(KeystoreManager keystore2, String encryptedText, String str) {
        Encryptor buildEncryptor;
        Intrinsics.checkNotNullParameter(keystore2, "keystore");
        Intrinsics.checkNotNullParameter(encryptedText, "encryptedText");
        if (str == null) {
            buildEncryptor = keystore2.buildEncryptor();
        } else {
            buildEncryptor = keystore2.buildEncryptor(str);
        }
        byte[] data = Base64.decode(encryptedText, 0);
        Intrinsics.checkNotNullExpressionValue(data, "data");
        return StringsKt.decodeToString(buildEncryptor.decrypt(data));
    }

    public final String encryptCode(String plainText) {
        Intrinsics.checkNotNullParameter(plainText, "plainText");
        try {
            Key key = keystore.getKey(AndroidKeystoreFacade.SYMETRIC_ALIAS_NAME, null);
            if (key == null) {
                return plainText;
            }
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, key);
            byte[] bytes = plainText.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            byte[] doFinal = cipher.doFinal(bytes);
            String encodeToString = Base64.encodeToString(cipher.getIV(), 2);
            return encodeToString + "." + Base64.encodeToString(doFinal, 2);
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            return plainText;
        }
    }

    public final String decryptCode(String encrypted) {
        Intrinsics.checkNotNullParameter(encrypted, "encrypted");
        try {
            Key key = keystore.getKey(AndroidKeystoreFacade.SYMETRIC_ALIAS_NAME, null);
            if (key == null) {
                return encrypted;
            }
            List split$default = StringsKt.split$default((CharSequence) encrypted, new String[]{"."}, false, 0, 6, (Object) null);
            byte[] decode = Base64.decode((String) split$default.get(0), 2);
            byte[] decode2 = Base64.decode((String) split$default.get(1), 2);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(2, key, new GCMParameterSpec(128, decode));
            byte[] doFinal = cipher.doFinal(decode2);
            Intrinsics.checkNotNullExpressionValue(doFinal, "cipher.doFinal(encryptedData)");
            return StringsKt.decodeToString(doFinal);
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            return encrypted;
        }
    }

    public final String getDeviceId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        Intrinsics.checkNotNullExpressionValue(string, "getString(context.conten…ttings.Secure.ANDROID_ID)");
        return string;
    }

    public final String getGsfAndroidId(Context context) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Cursor query = context.getContentResolver().query(Uri.parse("content://com.google.android.gsf.gservices"), null, null, new String[]{"android_id"}, null);
            Intrinsics.checkNotNull(query);
            if (!query.moveToFirst() || query.getColumnCount() < 2) {
                str = null;
            } else {
                try {
                    String string = query.getString(1);
                    Intrinsics.checkNotNullExpressionValue(string, "c.getString(1)");
                    str = Long.toHexString(Long.parseLong(string));
                } catch (NumberFormatException e) {
                    throw e;
                }
            }
            query.close();
            return str;
        } catch (Exception unused) {
            return null;
        }
    }

    public final void signPdf(KeystoreManager keystore2, File pdfFile) {
        Intrinsics.checkNotNullParameter(keystore2, "keystore");
        Intrinsics.checkNotNullParameter(pdfFile, "pdfFile");
        try {
            boolean isKeyEntry = keystore2.getKeystore().isKeyEntry(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
            Boolean BYPASS_SIGNING = BuildConfig.BYPASS_SIGNING;
            Intrinsics.checkNotNullExpressionValue(BYPASS_SIGNING, "BYPASS_SIGNING");
            if (!BYPASS_SIGNING.booleanValue() && isKeyEntry) {
                Boolean USE_SAMPLE_KEYSTORE = BuildConfig.USE_SAMPLE_KEYSTORE;
                Intrinsics.checkNotNullExpressionValue(USE_SAMPLE_KEYSTORE, "USE_SAMPLE_KEYSTORE");
                if (USE_SAMPLE_KEYSTORE.booleanValue()) {
                    PdfSigner pdfSigner = new PdfSigner(keystore2.buildSigner());
                    File tmpResult = File.createTempFile("sign_", ".pdf");
                    PdfMDPPermission pdfMDPPermission = PdfMDPPermission.NoChange;
                    Intrinsics.checkNotNullExpressionValue(tmpResult, "tmpResult");
                    pdfSigner.sign(new FileInputStream(pdfFile), pdfMDPPermission, new FileOutputStream(tmpResult));
                    new FileOutputStream(pdfFile).write(FilesKt.readBytes(tmpResult));
                    return;
                }
                PdfSigner pdfSigner2 = new PdfSigner(keystore2.buildSigner(AndroidKeystoreFacade.LOCAL_ALIAS_NAME));
                File tmpResult2 = File.createTempFile("sign_", ".pdf");
                PdfMDPPermission pdfMDPPermission2 = PdfMDPPermission.AllowSignatureOrFormChange;
                Intrinsics.checkNotNullExpressionValue(tmpResult2, "tmpResult");
                pdfSigner2.sign(new FileInputStream(pdfFile), pdfMDPPermission2, new FileOutputStream(tmpResult2));
                String absolutePath = pdfFile.getAbsolutePath();
                Log.i("SIGN", "signPdf using Local Key: " + absolutePath + ", hash: " + hashByte(FilesKt.readBytes(pdfFile)));
                new FileOutputStream(pdfFile).write(FilesKt.readBytes(tmpResult2));
                return;
            }
            byte[] readBytes = FilesKt.readBytes(pdfFile);
            FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
            FileOutputStream fileOutputStream2 = fileOutputStream;
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(readBytes);
            fileOutputStream2.write(readBytes);
            fileOutputStream2.write(0);
            fileOutputStream2.write(0);
            fileOutputStream2.write(81);
            fileOutputStream2.write(82);
            fileOutputStream2.write(83);
            fileOutputStream2.write(0);
            fileOutputStream2.write(0);
            fileOutputStream2.write(digest);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
            throw e;
        }
    }

    public final void protectPdfWithRandomKey(File pdfFile) {
        Intrinsics.checkNotNullParameter(pdfFile, "pdfFile");
        byte[] bArr = new byte[32];
        new SecureRandom().nextBytes(bArr);
        String str = new String(bArr, Charsets.UTF_8);
        AccessPermission accessPermission = new AccessPermission();
        accessPermission.setReadOnly();
        Unit unit = Unit.INSTANCE;
        StandardProtectionPolicy standardProtectionPolicy = new StandardProtectionPolicy(str, "", accessPermission);
        PDDocument document = PDDocument.load(pdfFile);
        PdfEncrypt pdfEncrypt = new PdfEncrypt(standardProtectionPolicy);
        Intrinsics.checkNotNullExpressionValue(document, "document");
        pdfEncrypt.encrypt(document);
        document.save(pdfFile);
    }

    public final void signJpgImage(KeystoreManager keystore2, File imageFile) {
        Signer buildSigner;
        Intrinsics.checkNotNullParameter(keystore2, "keystore");
        Intrinsics.checkNotNullParameter(imageFile, "imageFile");
        boolean isKeyEntry = keystore2.getKeystore().isKeyEntry(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
        Boolean BYPASS_SIGNING = BuildConfig.BYPASS_SIGNING;
        Intrinsics.checkNotNullExpressionValue(BYPASS_SIGNING, "BYPASS_SIGNING");
        if (BYPASS_SIGNING.booleanValue() || !isKeyEntry) {
            byte[] readBytes = FilesKt.readBytes(imageFile);
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            try {
                FileOutputStream fileOutputStream2 = fileOutputStream;
                byte[] digest = MessageDigest.getInstance("SHA-256").digest(readBytes);
                fileOutputStream2.write(readBytes);
                fileOutputStream2.write(0);
                fileOutputStream2.write(0);
                fileOutputStream2.write(96);
                fileOutputStream2.write(97);
                fileOutputStream2.write(98);
                fileOutputStream2.write(0);
                fileOutputStream2.write(0);
                fileOutputStream2.write(digest);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, null);
            } finally {
            }
        } else {
            try {
                Boolean USE_SAMPLE_KEYSTORE = BuildConfig.USE_SAMPLE_KEYSTORE;
                Intrinsics.checkNotNullExpressionValue(USE_SAMPLE_KEYSTORE, "USE_SAMPLE_KEYSTORE");
                if (USE_SAMPLE_KEYSTORE.booleanValue()) {
                    buildSigner = keystore2.buildSigner();
                } else {
                    buildSigner = keystore2.buildSigner(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
                }
                new ImageSigner(buildSigner).sign(imageFile);
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
                throw e;
            }
        }
    }

    public final void validateImages(Context context, List<String> imagePaths) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imagePaths, "imagePaths");
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        boolean isKeyEntry = keyStore.isKeyEntry(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
        Boolean BYPASS_SIGNING = BuildConfig.BYPASS_SIGNING;
        Intrinsics.checkNotNullExpressionValue(BYPASS_SIGNING, "BYPASS_SIGNING");
        if (BYPASS_SIGNING.booleanValue() || !isKeyEntry) {
            return;
        }
        ImageValidator imageValidator = new ImageValidator(new LocalVerifier());
        int i = 0;
        for (Object obj : imagePaths) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            try {
                imageValidator.validate(context, new File((String) obj));
                i = i2;
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
                throw new ImageValidationException(e.getMessage(), i);
            }
        }
    }

    public final String getImageSignature(String imagePath) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        boolean isKeyEntry = keyStore.isKeyEntry(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
        Boolean BYPASS_SIGNING = BuildConfig.BYPASS_SIGNING;
        Intrinsics.checkNotNullExpressionValue(BYPASS_SIGNING, "BYPASS_SIGNING");
        return (BYPASS_SIGNING.booleanValue() || !isKeyEntry) ? "Tidak ada tanda tangan digital" : getImageSignature(new File(imagePath));
    }

    public final String getImageSignature(File imageFile) {
        Intrinsics.checkNotNullParameter(imageFile, "imageFile");
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        boolean isKeyEntry = keyStore.isKeyEntry(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
        Boolean BYPASS_SIGNING = BuildConfig.BYPASS_SIGNING;
        Intrinsics.checkNotNullExpressionValue(BYPASS_SIGNING, "BYPASS_SIGNING");
        if (BYPASS_SIGNING.booleanValue() || !isKeyEntry) {
            return "Tidak ada tanda tangan digital";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        ImageSignatureUtil.INSTANCE.splitSignatureAndData(new FileInputStream(imageFile), byteArrayOutputStream, byteArrayOutputStream2);
        return getCMSSignature(new CMSSignedData(new CMSProcessableByteArray(byteArrayOutputStream.toByteArray()), byteArrayOutputStream2.toByteArray()));
    }

    public static /* synthetic */ List getPublicKeyInformation$default(SecurityFacade securityFacade, KeystoreManager keystoreManager, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return securityFacade.getPublicKeyInformation(keystoreManager, str);
    }

    public final List<Pair<String, String>> getPublicKeyInformation(KeystoreManager keystore2, String str) {
        List<Certificate> certificateChain;
        Intrinsics.checkNotNullParameter(keystore2, "keystore");
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        boolean isKeyEntry = keyStore.isKeyEntry(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
        Boolean BYPASS_SIGNING = BuildConfig.BYPASS_SIGNING;
        Intrinsics.checkNotNullExpressionValue(BYPASS_SIGNING, "BYPASS_SIGNING");
        if (BYPASS_SIGNING.booleanValue() || !isKeyEntry) {
            return CollectionsKt.emptyList();
        }
        if (str == null) {
            certificateChain = keystore2.getCertificateChain();
        } else {
            certificateChain = keystore2.getCertificateChain(str);
        }
        List<Certificate> list = certificateChain;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Certificate certificate : list) {
            Intrinsics.checkNotNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            X509Certificate x509Certificate = (X509Certificate) certificate;
            arrayList.add(new Pair(x509Certificate.getSubjectDN().getName(), Base64.encodeToString(x509Certificate.getPublicKey().getEncoded(), 2)));
        }
        return arrayList;
    }

    public final String getPDFSignature(File pdfFile) {
        PDSignature pDSignature;
        Intrinsics.checkNotNullParameter(pdfFile, "pdfFile");
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        boolean isKeyEntry = keyStore.isKeyEntry(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
        Boolean BYPASS_SIGNING = BuildConfig.BYPASS_SIGNING;
        Intrinsics.checkNotNullExpressionValue(BYPASS_SIGNING, "BYPASS_SIGNING");
        if (BYPASS_SIGNING.booleanValue() || !isKeyEntry) {
            return "Tidak ada tanda tangan digital";
        }
        PDDocument load = PDDocument.load(pdfFile);
        if (load.getSignatureDictionaries().size() == 1 && (pDSignature = load.getSignatureDictionaries().get(0)) != null) {
            return getCMSSignature(new CMSSignedData(new CMSProcessableByteArray(ByteStreamsKt.readBytes(new COSFilterInputStream(new FileInputStream(pdfFile), pDSignature.getByteRange()))), pDSignature.getContents()));
        }
        return null;
    }

    public final String getCMSSignature(CMSSignedData signedData) {
        Intrinsics.checkNotNullParameter(signedData, "signedData");
        if (signedData.getSignerInfos().size() != 1) {
            throw new Exception("Only support to take single signer");
        }
        SignerInformationStore signerInfos = signedData.getSignerInfos();
        Intrinsics.checkNotNullExpressionValue(signerInfos, "signedData.signerInfos");
        String encodeToString = Base64.encodeToString(((SignerInformation) CollectionsKt.first(signerInfos)).getSignature(), 2);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(signerInf…ignature, Base64.NO_WRAP)");
        return encodeToString;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0091 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object addPdfVerificationInfo(android.content.Context r8, org.informatika.sirekap.usecase.AuthRequestUseCase r9, org.informatika.sirekap.support.security.pdf.PdfLtv r10, java.io.File r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r7 = this;
            boolean r0 = r12 instanceof org.informatika.sirekap.support.security.SecurityFacade$addPdfVerificationInfo$1
            if (r0 == 0) goto L14
            r0 = r12
            org.informatika.sirekap.support.security.SecurityFacade$addPdfVerificationInfo$1 r0 = (org.informatika.sirekap.support.security.SecurityFacade$addPdfVerificationInfo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            org.informatika.sirekap.support.security.SecurityFacade$addPdfVerificationInfo$1 r0 = new org.informatika.sirekap.support.security.SecurityFacade$addPdfVerificationInfo$1
            r0.<init>(r7, r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L46
            if (r2 == r4) goto L38
            if (r2 != r3) goto L30
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L2e
            goto L92
        L2e:
            r8 = move-exception
            goto L95
        L30:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L38:
            java.lang.Object r8 = r0.L$1
            java.io.File r8 = (java.io.File) r8
            java.lang.Object r9 = r0.L$0
            org.informatika.sirekap.support.security.pdf.PdfLtv r9 = (org.informatika.sirekap.support.security.pdf.PdfLtv) r9
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L2e
            r11 = r8
            r10 = r9
            goto L85
        L46:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.String r12 = "AndroidKeyStore"
            java.security.KeyStore r12 = java.security.KeyStore.getInstance(r12)
            r12.load(r5)
            java.lang.String r2 = "sirekap-sign"
            boolean r12 = r12.isKeyEntry(r2)
            java.lang.Boolean r2 = org.informatika.sirekap.BuildConfig.BYPASS_SIGNING
            java.lang.String r6 = "BYPASS_SIGNING"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto La0
            if (r12 != 0) goto L68
            goto La0
        L68:
            org.informatika.sirekap.support.security.DeviceSecurityFacade r12 = org.informatika.sirekap.support.security.DeviceSecurityFacade.INSTANCE     // Catch: java.lang.Exception -> L2e
            org.informatika.sirekap.support.security.DeviceSecurityFacade$DeviceNetworkStatus r12 = r12.getNetworkStatus(r8)     // Catch: java.lang.Exception -> L2e
            org.informatika.sirekap.support.security.DeviceSecurityFacade$DeviceNetworkStatus r2 = org.informatika.sirekap.support.security.DeviceSecurityFacade.DeviceNetworkStatus.NOT_CONNECTED     // Catch: java.lang.Exception -> L2e
            if (r12 == r2) goto L92
            boolean r12 = r9.isLocalTokenExpired()     // Catch: java.lang.Exception -> L2e
            if (r12 == 0) goto L85
            r0.L$0 = r10     // Catch: java.lang.Exception -> L2e
            r0.L$1 = r11     // Catch: java.lang.Exception -> L2e
            r0.label = r4     // Catch: java.lang.Exception -> L2e
            java.lang.Object r8 = r9.startRefreshToken(r8, r0)     // Catch: java.lang.Exception -> L2e
            if (r8 != r1) goto L85
            return r1
        L85:
            r0.L$0 = r5     // Catch: java.lang.Exception -> L2e
            r0.L$1 = r5     // Catch: java.lang.Exception -> L2e
            r0.label = r3     // Catch: java.lang.Exception -> L2e
            java.lang.Object r8 = r10.addLtv(r11, r0)     // Catch: java.lang.Exception -> L2e
            if (r8 != r1) goto L92
            return r1
        L92:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L95:
            com.google.firebase.crashlytics.FirebaseCrashlytics r9 = com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance()
            r10 = r8
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            r9.recordException(r10)
            throw r8
        La0:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.support.security.SecurityFacade.addPdfVerificationInfo(android.content.Context, org.informatika.sirekap.usecase.AuthRequestUseCase, org.informatika.sirekap.support.security.pdf.PdfLtv, java.io.File, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
