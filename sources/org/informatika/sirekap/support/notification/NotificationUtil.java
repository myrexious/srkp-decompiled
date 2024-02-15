package org.informatika.sirekap.support.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;

/* compiled from: NotificationUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lorg/informatika/sirekap/support/notification/NotificationUtil;", "", "()V", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NotificationUtil {
    private static final String CHANNEL_DESCRIPTION = "SiRekap Notification Channel";
    private static final String CHANNEL_ID = "sirekap";
    private static final String CHANNEL_NAME = "SiRekap";
    public static final Companion Companion = new Companion(null);
    private static final String GROUP_KEY_IMAGE_NOTIFICATION = "id.go.kpu.sirekap2024.IMAGE_NOTIFICATION_GROUP";
    public static final int NOTIFICATION_ID_REGISTER_WITNESS = 4;
    public static final int NOTIFICATION_ID_ZIP_ATTENDANCE = 1;
    public static final int NOTIFICATION_ID_ZIP_SALINAN_FORMC = 3;
    public static final int NOTIFICATION_ID_ZIP_SPECIAL_OCCURRENCE = 2;

    @JvmStatic
    public static final void sendNotification(Context context, int i, String str, String str2) {
        Companion.sendNotification(context, i, str, str2);
    }

    /* compiled from: NotificationUtil.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/informatika/sirekap/support/notification/NotificationUtil$Companion;", "", "()V", "CHANNEL_DESCRIPTION", "", "CHANNEL_ID", "CHANNEL_NAME", "GROUP_KEY_IMAGE_NOTIFICATION", "NOTIFICATION_ID_REGISTER_WITNESS", "", "NOTIFICATION_ID_ZIP_ATTENDANCE", "NOTIFICATION_ID_ZIP_SALINAN_FORMC", "NOTIFICATION_ID_ZIP_SPECIAL_OCCURRENCE", "sendNotification", "", "context", "Landroid/content/Context;", "notificationId", "title", "message", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void sendNotification(Context context, int i, String title, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(title, "title");
            NotificationCompat.Builder group = new NotificationCompat.Builder(context, NotificationUtil.CHANNEL_ID).setContentTitle(title).setContentText(str).setPriority(0).setStyle(new NotificationCompat.BigTextStyle()).setSound(RingtoneManager.getDefaultUri(2)).setSmallIcon(R.drawable.kpu_logo_svg_notification).setGroup(NotificationUtil.GROUP_KEY_IMAGE_NOTIFICATION);
            Intrinsics.checkNotNullExpressionValue(group, "Builder(context, CHANNEL…P_KEY_IMAGE_NOTIFICATION)");
            Notification build = new NotificationCompat.Builder(context, NotificationUtil.CHANNEL_ID).setContentTitle("Notifikasi Gambar").setContentText("Terdapat notifikasi gambar SiRekap").setSmallIcon(R.drawable.kpu_logo_svg_notification).setGroup(NotificationUtil.GROUP_KEY_IMAGE_NOTIFICATION).setGroupSummary(true).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(context, CHANNEL…\n                .build()");
            Object systemService = context.getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationManager notificationManager = (NotificationManager) systemService;
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel(NotificationUtil.CHANNEL_ID, NotificationUtil.CHANNEL_NAME, 3);
                notificationChannel.setDescription(NotificationUtil.CHANNEL_DESCRIPTION);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            notificationManager.notify(i, group.build());
            notificationManager.notify(0, build);
        }
    }
}
