package org.informatika.sirekap.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.messaging.Constants;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.db.dao.SecurityDao;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.repository.CertmanRepository;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.support.security.AndroidKeystoreFacade;
import org.informatika.sirekap.support.security.PKIFacade;
import org.informatika.sirekap.usecase.GetNotificationPermissionUseCase;

/* compiled from: DeviceInitializationViewModel.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!H\u0002J\u0011\u0010\"\u001a\u00020#H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0006\u0010%\u001a\u00020&J\u000e\u0010'\u001a\u00020&2\u0006\u0010 \u001a\u00020!J\u0006\u0010(\u001a\u00020\u001aJ\u0018\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020-H\u0002J\u000e\u0010.\u001a\u00020&2\u0006\u0010 \u001a\u00020!J \u0010/\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\r2\u0006\u00100\u001a\u00020*2\u0006\u00101\u001a\u000202H\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000fR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"Lorg/informatika/sirekap/ui/DeviceInitializationViewModel;", "Landroidx/lifecycle/ViewModel;", "sharedPreferences", "Landroid/content/SharedPreferences;", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "certmanRepository", "Lorg/informatika/sirekap/repository/CertmanRepository;", "(Landroid/content/SharedPreferences;Lorg/informatika/sirekap/db/AppDatabase;Lorg/informatika/sirekap/repository/CertmanRepository;)V", "getCertmanRepository", "()Lorg/informatika/sirekap/repository/CertmanRepository;", "error", "Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "", "getError", "()Lorg/informatika/sirekap/support/livedata/CombinedLiveData;", "getFcmTokenUseCase", "Lorg/informatika/sirekap/ui/GetFcmTokenUseCase;", "getGetFcmTokenUseCase", "()Lorg/informatika/sirekap/ui/GetFcmTokenUseCase;", "getNotificationPermissionUseCase", "Lorg/informatika/sirekap/usecase/GetNotificationPermissionUseCase;", "getGetNotificationPermissionUseCase", "()Lorg/informatika/sirekap/usecase/GetNotificationPermissionUseCase;", "isDeviceCheckFailed", "Landroidx/lifecycle/MutableLiveData;", "", "()Landroidx/lifecycle/MutableLiveData;", "isLoading", "securityPropertiesDao", "Lorg/informatika/sirekap/db/dao/SecurityDao;", "checkChainKeystore", "context", "Landroid/content/Context;", "checkSymmetricStatus", "Lorg/informatika/sirekap/model/KeyState;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanupBsreCertificate", "", "generateKeyPair", "isExpired", "signData", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "privateKey", "Ljava/security/PrivateKey;", "symmetricKeyGeneration", "verifySignature", "signature", "publicKey", "Ljava/security/PublicKey;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class DeviceInitializationViewModel extends ViewModel {
    private final CertmanRepository certmanRepository;
    private final CombinedLiveData<String> error;
    private final GetFcmTokenUseCase getFcmTokenUseCase;
    private final GetNotificationPermissionUseCase getNotificationPermissionUseCase;
    private final MutableLiveData<Boolean> isDeviceCheckFailed;
    private final CombinedLiveData<Boolean> isLoading;
    private final SecurityDao securityPropertiesDao;
    private final SharedPreferences sharedPreferences;

    public final CertmanRepository getCertmanRepository() {
        return this.certmanRepository;
    }

    @Inject
    public DeviceInitializationViewModel(SharedPreferences sharedPreferences, AppDatabase appDatabase, CertmanRepository certmanRepository) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        Intrinsics.checkNotNullParameter(appDatabase, "appDatabase");
        Intrinsics.checkNotNullParameter(certmanRepository, "certmanRepository");
        this.sharedPreferences = sharedPreferences;
        this.certmanRepository = certmanRepository;
        this.securityPropertiesDao = appDatabase.securityDao();
        GetFcmTokenUseCase getFcmTokenUseCase = new GetFcmTokenUseCase(sharedPreferences);
        this.getFcmTokenUseCase = getFcmTokenUseCase;
        GetNotificationPermissionUseCase getNotificationPermissionUseCase = new GetNotificationPermissionUseCase();
        this.getNotificationPermissionUseCase = getNotificationPermissionUseCase;
        this.isLoading = new CombinedLiveData<>(new LiveData[]{getFcmTokenUseCase.isLoading(), getNotificationPermissionUseCase.isLoading()}, new Function1<List<? extends Object>, Boolean>() { // from class: org.informatika.sirekap.ui.DeviceInitializationViewModel$isLoading$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<? extends Object> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                List<? extends Object> list = data;
                boolean z = false;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        } else if (Intrinsics.areEqual((Object) ((Boolean) it.next()), (Object) true)) {
                            z = true;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        });
        this.isDeviceCheckFailed = new MutableLiveData<>(false);
        this.error = new CombinedLiveData<>(new LiveData[]{getFcmTokenUseCase.getError(), getNotificationPermissionUseCase.getError()}, new Function1<List<? extends Object>, String>() { // from class: org.informatika.sirekap.ui.DeviceInitializationViewModel$error$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(List<? extends Object> data) {
                Object obj;
                boolean z;
                Intrinsics.checkNotNullParameter(data, "data");
                Iterator<T> it = data.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((String) obj) != null) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        break;
                    }
                }
                return (String) obj;
            }
        });
    }

    public final GetFcmTokenUseCase getGetFcmTokenUseCase() {
        return this.getFcmTokenUseCase;
    }

    public final GetNotificationPermissionUseCase getGetNotificationPermissionUseCase() {
        return this.getNotificationPermissionUseCase;
    }

    public final CombinedLiveData<Boolean> isLoading() {
        return this.isLoading;
    }

    public final MutableLiveData<Boolean> isDeviceCheckFailed() {
        return this.isDeviceCheckFailed;
    }

    public final CombinedLiveData<String> getError() {
        return this.error;
    }

    public final boolean isExpired() {
        Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(BuildConfig.EXPIRED_AT);
        Intrinsics.checkNotNullExpressionValue(parse, "SimpleDateFormat(\"yyyy-M…e(BuildConfig.EXPIRED_AT)");
        return parse.before(new Date());
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object checkSymmetricStatus(kotlin.coroutines.Continuation<? super org.informatika.sirekap.model.KeyState> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof org.informatika.sirekap.ui.DeviceInitializationViewModel$checkSymmetricStatus$1
            if (r0 == 0) goto L14
            r0 = r5
            org.informatika.sirekap.ui.DeviceInitializationViewModel$checkSymmetricStatus$1 r0 = (org.informatika.sirekap.ui.DeviceInitializationViewModel$checkSymmetricStatus$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            org.informatika.sirekap.ui.DeviceInitializationViewModel$checkSymmetricStatus$1 r0 = new org.informatika.sirekap.ui.DeviceInitializationViewModel$checkSymmetricStatus$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            kotlin.ResultKt.throwOnFailure(r5)
            goto L42
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            org.informatika.sirekap.db.dao.SecurityDao r5 = r4.securityPropertiesDao
            r0.label = r3
            java.lang.String r2 = "symmetric_key_status"
            java.lang.Object r5 = r5.getValue(r2, r0)
            if (r5 != r1) goto L42
            return r1
        L42:
            org.informatika.sirekap.model.SecurityProperties r5 = (org.informatika.sirekap.model.SecurityProperties) r5
            if (r5 == 0) goto L4b
            java.lang.String r5 = r5.getValue()
            goto L4c
        L4b:
            r5 = 0
        L4c:
            if (r5 == 0) goto La9
            int r0 = r5.hashCode()
            switch(r0) {
                case -1770733785: goto L9d;
                case -1221416593: goto L91;
                case -814643900: goto L85;
                case -390487084: goto L79;
                case -65580248: goto L6e;
                case 790795506: goto L62;
                case 2123979850: goto L56;
                default: goto L55;
            }
        L55:
            goto La9
        L56:
            java.lang.String r0 = "KEY_PAIR_GENERATED"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L5f
            goto La9
        L5f:
            org.informatika.sirekap.model.KeyState r5 = org.informatika.sirekap.model.KeyState.KEY_PAIR_GENERATED
            goto Lab
        L62:
            java.lang.String r0 = "GENERATING"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L6b
            goto La9
        L6b:
            org.informatika.sirekap.model.KeyState r5 = org.informatika.sirekap.model.KeyState.GENERATING
            goto Lab
        L6e:
            java.lang.String r0 = "NOT_INITIALIZED"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto La9
            org.informatika.sirekap.model.KeyState r5 = org.informatika.sirekap.model.KeyState.NOT_INITIALIZED
            goto Lab
        L79:
            java.lang.String r0 = "DOWNLOAD_FAILED"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L82
            goto La9
        L82:
            org.informatika.sirekap.model.KeyState r5 = org.informatika.sirekap.model.KeyState.DOWNLOAD_FAILED
            goto Lab
        L85:
            java.lang.String r0 = "GENERATION_FAILED"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L8e
            goto La9
        L8e:
            org.informatika.sirekap.model.KeyState r5 = org.informatika.sirekap.model.KeyState.GENERATION_FAILED
            goto Lab
        L91:
            java.lang.String r0 = "GENERATED"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L9a
            goto La9
        L9a:
            org.informatika.sirekap.model.KeyState r5 = org.informatika.sirekap.model.KeyState.GENERATED
            goto Lab
        L9d:
            java.lang.String r0 = "DOWNLOADED"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto La6
            goto La9
        La6:
            org.informatika.sirekap.model.KeyState r5 = org.informatika.sirekap.model.KeyState.DOWNLOADED
            goto Lab
        La9:
            org.informatika.sirekap.model.KeyState r5 = org.informatika.sirekap.model.KeyState.NOT_INITIALIZED
        Lab:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.ui.DeviceInitializationViewModel.checkSymmetricStatus(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void symmetricKeyGeneration(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Exception e = null;
        for (int i = 1; i < 4; i++) {
            try {
                if (AndroidKeystoreFacade.INSTANCE.isSymetricKeyExist(context)) {
                    return;
                }
                AndroidKeystoreFacade.INSTANCE.generateSymmetricKey(context);
                this.sharedPreferences.edit().putBoolean(SharedPreferencesModule.SP_DONT_HAVE_PRIVATE_KEY, false).apply();
                return;
            } catch (Exception e2) {
                e = e2;
            }
        }
        if (e != null) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }
        this.sharedPreferences.edit().putBoolean(SharedPreferencesModule.SP_DONT_HAVE_PRIVATE_KEY, true).apply();
    }

    public final void generateKeyPair(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (checkChainKeystore(context)) {
            this.sharedPreferences.edit().putBoolean(SharedPreferencesModule.SP_DONT_HAVE_PAIR_KEY, false).apply();
            this.sharedPreferences.edit().putBoolean(SharedPreferencesModule.SP_APP_INITIALIZATION, true).apply();
            this.sharedPreferences.edit().putInt(SharedPreferencesModule.SP_STATUS_INITIALIZATION, 7).apply();
            return;
        }
        Exception e = null;
        for (int i = 1; i < 4; i++) {
            try {
                if (AndroidKeystoreFacade.INSTANCE.isKeyEntryExist(AndroidKeystoreFacade.LOCAL_ALIAS_NAME)) {
                    AndroidKeystoreFacade.INSTANCE.deleteEntry(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
                }
                AndroidKeystoreFacade.generatePairKey$default(AndroidKeystoreFacade.INSTANCE, context, null, 2, null);
                this.sharedPreferences.edit().putBoolean(SharedPreferencesModule.SP_DONT_HAVE_PAIR_KEY, false).apply();
                return;
            } catch (Exception e2) {
                e = e2;
            }
        }
        if (e != null) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }
        this.sharedPreferences.edit().putBoolean(SharedPreferencesModule.SP_DONT_HAVE_PAIR_KEY, true).apply();
    }

    public final void cleanupBsreCertificate() {
        AndroidKeystoreFacade.INSTANCE.deleteEntry(AndroidKeystoreFacade.BSRE_ALIAS_NAME);
    }

    private final boolean checkChainKeystore(Context context) {
        try {
            Certificate certificate = AndroidKeystoreFacade.INSTANCE.getCertificate(AndroidKeystoreFacade.LOCAL_ALIAS_NAME);
            Intrinsics.checkNotNull(certificate, "null cannot be cast to non-null type java.security.cert.Certificate");
            PKIFacade.INSTANCE.checkCertificateChainOnly(PKIFacade.INSTANCE.getTrustAnchor(context), CollectionsKt.listOf(certificate));
            Key key$default = AndroidKeystoreFacade.getKey$default(AndroidKeystoreFacade.INSTANCE, null, 1, null);
            Intrinsics.checkNotNull(key$default, "null cannot be cast to non-null type java.security.PrivateKey");
            byte[] signData = signData("test", (PrivateKey) key$default);
            PublicKey publicKey = certificate.getPublicKey();
            Intrinsics.checkNotNullExpressionValue(publicKey, "publicKey");
            verifySignature("test", signData, publicKey);
            return true;
        } catch (Exception e) {
            Log.d("DeviceInitialization", "checkChainKeystore: " + e.getMessage());
            return false;
        }
    }

    private final byte[] signData(String str, PrivateKey privateKey) {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        Intrinsics.checkNotNullExpressionValue(signature, "getInstance(\"SHA256withECDSA\")");
        signature.initSign(privateKey);
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        signature.update(bytes);
        byte[] sign = signature.sign();
        Intrinsics.checkNotNullExpressionValue(sign, "signature.sign()");
        return sign;
    }

    private final boolean verifySignature(String str, byte[] bArr, PublicKey publicKey) {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        Intrinsics.checkNotNullExpressionValue(signature, "getInstance(\"SHA256withECDSA\")");
        signature.initVerify(publicKey);
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        signature.update(bytes);
        return signature.verify(bArr);
    }
}
