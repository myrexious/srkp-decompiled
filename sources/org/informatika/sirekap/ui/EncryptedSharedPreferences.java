package org.informatika.sirekap.ui;

import android.content.SharedPreferences;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.support.security.SecurityFacade;

/* compiled from: EncryptedSharedPreferences.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "getStringEncrypted", "", "key", "defaultValue", "putStringEncrypted", "", "value", "removeEncrypted", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class EncryptedSharedPreferences {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "EncryptedSharedPreferences";
    private final SharedPreferences sharedPreferences;

    @Inject
    public EncryptedSharedPreferences(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        this.sharedPreferences = sharedPreferences;
    }

    /* compiled from: EncryptedSharedPreferences.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/EncryptedSharedPreferences$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }
    }

    public final void putStringEncrypted(String key, String str) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (str != null) {
            String encryptCode = SecurityFacade.INSTANCE.encryptCode(str);
            if (StringsKt.isBlank(encryptCode)) {
                return;
            }
            SharedPreferences.Editor edit = this.sharedPreferences.edit();
            edit.putString(key, encryptCode);
            edit.commit();
        }
    }

    public final String getStringEncrypted(String key, String str) {
        Intrinsics.checkNotNullParameter(key, "key");
        String string = this.sharedPreferences.getString(key, null);
        String str2 = string;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return str;
        }
        String decryptCode = SecurityFacade.INSTANCE.decryptCode(string);
        return !StringsKt.isBlank(decryptCode) ? decryptCode : string;
    }

    public final void removeEncrypted(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        edit.remove(key);
        edit.commit();
    }
}
