package org.informatika.sirekap.support;

import android.content.SharedPreferences;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: CrashlyticsUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lorg/informatika/sirekap/support/CrashlyticsUtil;", "", "()V", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CrashlyticsUtil {
    public static final Companion Companion = new Companion(null);

    @JvmStatic
    public static final void setCrashlyticsUser(SharedPreferences sharedPreferences) {
        Companion.setCrashlyticsUser(sharedPreferences);
    }

    @JvmStatic
    public static final void setCrashlyticsUserEncrypted(EncryptedSharedPreferences encryptedSharedPreferences) {
        Companion.setCrashlyticsUserEncrypted(encryptedSharedPreferences);
    }

    @JvmStatic
    public static final void setElectionPageStatus(String str, int i) {
        Companion.setElectionPageStatus(str, i);
    }

    /* compiled from: CrashlyticsUtil.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/support/CrashlyticsUtil$Companion;", "", "()V", "setCrashlyticsUser", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "setCrashlyticsUserEncrypted", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "setElectionPageStatus", "electionPageId", "", "currentStageIndex", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void setCrashlyticsUser(SharedPreferences sharedPreferences) {
            Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
            FirebaseCrashlytics.getInstance().setUserId(String.valueOf(sharedPreferences.getString(SharedPreferencesModule.SP_KEY_JWT_SUBJECT, null)));
        }

        @JvmStatic
        public final void setCrashlyticsUserEncrypted(EncryptedSharedPreferences encryptedSharedPreferences) {
            Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
            FirebaseCrashlytics.getInstance().setUserId(String.valueOf(encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_JWT_SUBJECT, null)));
        }

        @JvmStatic
        public final void setElectionPageStatus(String electionPageId, int i) {
            Intrinsics.checkNotNullParameter(electionPageId, "electionPageId");
            FirebaseCrashlytics.getInstance().setCustomKey(electionPageId, i != 0 ? i != 1 ? i != 2 ? i != 3 ? "Incorrect state" : "Image verified" : "Image sent" : "Image taken" : "Not started");
        }
    }
}
