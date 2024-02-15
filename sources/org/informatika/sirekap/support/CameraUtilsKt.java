package org.informatika.sirekap.support;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: CameraUtils.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"configureCameraAngle", "", "Landroid/app/Activity;", "dp2px", "Landroid/content/Context;", "dp", "", "app_productionRelease"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraUtilsKt {
    public static final int configureCameraAngle(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation != 0) {
            if (rotation != 1) {
                if (rotation != 2) {
                    return rotation != 3 ? 90 : 180;
                }
                return 270;
            }
            return 0;
        }
        return 90;
    }

    public static final int dp2px(Context context, float f) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return MathKt.roundToInt(TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics()));
    }
}
