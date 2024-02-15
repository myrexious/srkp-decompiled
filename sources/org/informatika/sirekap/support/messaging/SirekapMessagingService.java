package org.informatika.sirekap.support.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import dagger.hilt.android.EntryPointAccessors;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import net.openid.appauth.ResponseTypeValues;
import org.apache.commons.lang3.StringUtils;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.db.dao.SecurityDao;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPageWithRelation;
import org.informatika.sirekap.model.FormC1Error;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.support.notification.GenericNotificationUtil;
import org.informatika.sirekap.support.notification.NotificationUtil;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: SirekapMessagingService.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0002\u0010\u0011B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/support/messaging/SirekapMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "getAppDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "getEncryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "getSharedPreferences", "Landroid/content/SharedPreferences;", "onMessageReceived", "", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", ResponseTypeValues.TOKEN, "", "Companion", "SirekapMessagingServiceEntryPoint", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SirekapMessagingService extends FirebaseMessagingService {
    public static final Companion Companion = new Companion(null);
    private static final int SECURITY_MESSAGING_ID = 9999;
    private static final String TAG = "SirekapMessagingService";

    /* compiled from: SirekapMessagingService.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/support/messaging/SirekapMessagingService$SirekapMessagingServiceEntryPoint;", "", "appDatabase", "Lorg/informatika/sirekap/db/AppDatabase;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "sharedPreferences", "Landroid/content/SharedPreferences;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public interface SirekapMessagingServiceEntryPoint {
        AppDatabase appDatabase();

        EncryptedSharedPreferences encryptedSharedPreferences();

        SharedPreferences sharedPreferences();
    }

    private final SharedPreferences getSharedPreferences() {
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        return ((SirekapMessagingServiceEntryPoint) EntryPointAccessors.fromApplication(applicationContext, SirekapMessagingServiceEntryPoint.class)).sharedPreferences();
    }

    private final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        return ((SirekapMessagingServiceEntryPoint) EntryPointAccessors.fromApplication(applicationContext, SirekapMessagingServiceEntryPoint.class)).encryptedSharedPreferences();
    }

    private final AppDatabase getAppDatabase() {
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        return ((SirekapMessagingServiceEntryPoint) EntryPointAccessors.fromApplication(applicationContext, SirekapMessagingServiceEntryPoint.class)).appDatabase();
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Boolean bool;
        String generateIdImage;
        ElectionPageWithRelation byIdImageNullable;
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        super.onMessageReceived(remoteMessage);
        AppDatabase appDatabase = getAppDatabase();
        int i = 1;
        if (Intrinsics.areEqual(remoteMessage.getData().get("type"), "certificate_generation")) {
            CertificateGenerationMessage certificateGenerationMessage = CertificateGenerationMessage.INSTANCE;
            String str = remoteMessage.getData().get(NotificationCompat.CATEGORY_STATUS);
            if (str == null) {
                str = "failed";
            }
            certificateGenerationMessage.changeState(new CertificateGenerationResponse(str, remoteMessage.getData().get(FirebaseAnalytics.Param.TRANSACTION_ID)));
            SecurityDao securityDao = appDatabase.securityDao();
            if (Intrinsics.areEqual(remoteMessage.getData().get(NotificationCompat.CATEGORY_STATUS), "ok")) {
                BuildersKt__BuildersKt.runBlocking$default(null, new SirekapMessagingService$onMessageReceived$1(securityDao, null), 1, null);
                GenericNotificationUtil.sendNotification$default(this, SECURITY_MESSAGING_ID, "Proses Aktivasi Hampir Selesai", "Silahkan lanjutkan proses aktivasi dengan membuka aplikasi SiRekap", null, 16, null);
                return;
            }
            BuildersKt__BuildersKt.runBlocking$default(null, new SirekapMessagingService$onMessageReceived$2(securityDao, null), 1, null);
            GenericNotificationUtil.sendNotification$default(this, SECURITY_MESSAGING_ID, "Proses Aktivasi Gagal", "Terjadi kegagalan saat melakukan proses aktivasi", null, 16, null);
            return;
        }
        String str2 = remoteMessage.getData().get("idImage");
        Long longOrNull = str2 != null ? StringsKt.toLongOrNull(str2) : null;
        String str3 = remoteMessage.getData().get("jenisPemilihan");
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String title = notification != null ? notification.getTitle() : null;
        RemoteMessage.Notification notification2 = remoteMessage.getNotification();
        String body = notification2 != null ? notification2.getBody() : null;
        Log.wtf("TAG✅", remoteMessage.getMessageId());
        Log.wtf("idImage✅", String.valueOf(longOrNull));
        Log.wtf("data✅", remoteMessage.getData().toString());
        RemoteMessage.Notification notification3 = remoteMessage.getNotification();
        Log.wtf("title✅", notification3 != null ? notification3.getTitle() : null);
        RemoteMessage.Notification notification4 = remoteMessage.getNotification();
        Log.wtf("body✅", notification4 != null ? notification4.getBody() : null);
        if (title != null && StringsKt.contains$default((CharSequence) title, (CharSequence) "Selesai", false, 2, (Object) null)) {
            bool = true;
        } else {
            bool = title != null && StringsKt.contains$default((CharSequence) title, (CharSequence) "Gagal", false, 2, (Object) null) ? false : null;
        }
        String stringEncrypted = getEncryptedSharedPreferences().getStringEncrypted(SharedPreferencesModule.SP_KEY_PROFILE, null);
        if (longOrNull == null || str3 == null || stringEncrypted == null || (byIdImageNullable = getAppDatabase().electionPageDao().getByIdImageNullable((generateIdImage = ElectionUtil.generateIdImage((int) longOrNull.longValue(), str3, stringEncrypted)))) == null || bool == null) {
            return;
        }
        SirekapMessagingService sirekapMessagingService = this;
        String imageDescription = byIdImageNullable.getImageDescription(sirekapMessagingService);
        if (!bool.booleanValue()) {
            int kind = byIdImageNullable.getElectionPage().getKind();
            if (kind != 10) {
                if (kind == 20) {
                    i = (Intrinsics.areEqual(byIdImageNullable.getElection().getPemilihan(), Election.ELECTION_PEMILIHAN_PRESIDEN) || Intrinsics.areEqual(byIdImageNullable.getElection().getPemilihan(), Election.ELECTION_PEMILIHAN_DPD)) ? 2 : 4;
                } else if (kind != 30) {
                    throw new Exception("Unknown electionPageKind: " + byIdImageNullable.getElectionPage().getKind());
                } else {
                    i = 3;
                }
            }
            appDatabase.formC1ErrorDao().insertAll(CollectionsKt.listOf(new FormC1Error(generateIdImage, i, 20, body == null ? "" : body)));
            appDatabase.electionPageDao().addVisionError(generateIdImage);
            NotificationUtil.Companion.sendNotification(sirekapMessagingService, (int) longOrNull.longValue(), "Gambar Gagal Diproses", body + StringUtils.SPACE + imageDescription);
            return;
        }
        getEncryptedSharedPreferences().removeEncrypted("visionWaitUntil25_" + generateIdImage);
        NotificationUtil.Companion.sendNotification(sirekapMessagingService, (int) longOrNull.longValue(), "Gambar Selesai Diproses", body + StringUtils.SPACE + imageDescription);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putString(SharedPreferencesModule.SP_KEY_FCM_TOKEN, token);
        edit.commit();
    }

    /* compiled from: SirekapMessagingService.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/support/messaging/SirekapMessagingService$Companion;", "", "()V", "SECURITY_MESSAGING_ID", "", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }
    }
}
