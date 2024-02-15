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
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;

/* compiled from: GenericNotificationUtil.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/informatika/sirekap/support/notification/GenericNotificationUtil;", "", "()V", "CHANNEL_DESCRIPTION", "", "CHANNEL_ID", "CHANNEL_NAME", "GROUP_KEY_IMAGE_NOTIFICATION", "sendNotification", "", "context", "Landroid/content/Context;", "notificationId", "", "title", "message", "summaryMessage", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GenericNotificationUtil {
    private static final String CHANNEL_DESCRIPTION = "SiRekap Notification Channel";
    private static final String CHANNEL_ID = "sirekap";
    private static final String CHANNEL_NAME = "SiRekap";
    private static final String GROUP_KEY_IMAGE_NOTIFICATION = "id.go.kpu.sirekap2024.IMAGE_NOTIFICATION_GROUP";
    public static final GenericNotificationUtil INSTANCE = new GenericNotificationUtil();

    private GenericNotificationUtil() {
    }

    public static /* synthetic */ void sendNotification$default(Context context, int i, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            str2 = null;
        }
        if ((i2 & 16) != 0) {
            str3 = null;
        }
        sendNotification(context, i, str, str2, str3);
    }

    @JvmStatic
    public static final void sendNotification(Context context, int i, String title, String str, String str2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        String str3 = title;
        NotificationCompat.Builder group = new NotificationCompat.Builder(context, CHANNEL_ID).setContentTitle(str3).setContentText(str).setPriority(0).setStyle(new NotificationCompat.BigTextStyle()).setSound(RingtoneManager.getDefaultUri(2)).setSmallIcon(R.drawable.kpu_logo_svg_notification).setGroup(GROUP_KEY_IMAGE_NOTIFICATION);
        Intrinsics.checkNotNullExpressionValue(group, "Builder(context, CHANNEL…P_KEY_IMAGE_NOTIFICATION)");
        Notification build = new NotificationCompat.Builder(context, CHANNEL_ID).setContentTitle(str3).setContentText(str2).setSmallIcon(R.drawable.kpu_logo_svg_notification).setGroup(GROUP_KEY_IMAGE_NOTIFICATION).setGroupSummary(true).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(context, CHANNEL…rue)\n            .build()");
        Object systemService = context.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, 3);
            notificationChannel.setDescription(CHANNEL_DESCRIPTION);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(i, group.build());
        notificationManager.notify(0, build);
    }
}
