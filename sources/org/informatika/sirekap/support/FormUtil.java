package org.informatika.sirekap.support;

import androidx.core.util.PatternsCompat;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.openid.appauth.AuthorizationRequest;

/* compiled from: FormUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lorg/informatika/sirekap/support/FormUtil;", "", "()V", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FormUtil {
    public static final Companion Companion = new Companion(null);

    @JvmStatic
    public static final boolean isTestEmail(String str) {
        return Companion.isTestEmail(str);
    }

    @JvmStatic
    public static final boolean isValidEmail(String str) {
        return Companion.isValidEmail(str);
    }

    @JvmStatic
    public static final boolean isValidKodeTps(String str) {
        return Companion.isValidKodeTps(str);
    }

    @JvmStatic
    public static final boolean isValidNik(String str) {
        return Companion.isValidNik(str);
    }

    @JvmStatic
    public static final boolean isValidPhoneNumber(String str) {
        return Companion.isValidPhoneNumber(str);
    }

    @JvmStatic
    public static final boolean isValidPin(String str) {
        return Companion.isValidPin(str);
    }

    /* compiled from: FormUtil.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u000f"}, d2 = {"Lorg/informatika/sirekap/support/FormUtil$Companion;", "", "()V", "isTestEmail", "", "email", "", "isValidEmail", "isValidKodeTps", "pin", "isValidNik", "nik", "isValidPhoneNumber", AuthorizationRequest.Scope.PHONE, "isValidPin", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final boolean isValidPhoneNumber(String str) {
            String str2 = str;
            return ((str2 == null || StringsKt.isBlank(str2)) || StringsKt.toBigIntegerOrNull(str) == null || StringsKt.trim((CharSequence) str2).toString().length() < 10) ? false : true;
        }

        @JvmStatic
        public final boolean isValidNik(String str) {
            String str2 = str;
            return ((str2 == null || StringsKt.isBlank(str2)) || StringsKt.toBigIntegerOrNull(str) == null || StringsKt.trim((CharSequence) str2).toString().length() != 16) ? false : true;
        }

        @JvmStatic
        public final boolean isValidEmail(String str) {
            if (str != null) {
                return PatternsCompat.EMAIL_ADDRESS.matcher(StringsKt.trim((CharSequence) str).toString()).matches();
            }
            return false;
        }

        @JvmStatic
        public final boolean isTestEmail(String email) {
            Intrinsics.checkNotNullParameter(email, "email");
            String substringAfter = StringsKt.substringAfter(StringsKt.trim((CharSequence) email).toString(), "@", "missingDelimiterValue");
            if (Intrinsics.areEqual(substringAfter, "missingDelimiterValue")) {
                throw new IllegalStateException();
            }
            return Intrinsics.areEqual(substringAfter, "t.t");
        }

        @JvmStatic
        public final boolean isValidPin(String str) {
            String str2 = str;
            return !(str2 == null || StringsKt.isBlank(str2)) && Pattern.compile("([0-9])").matcher(str2).find() && Pattern.compile("([A-Za-z])").matcher(str2).find() && StringsKt.trim((CharSequence) str2).toString().length() == 6;
        }

        @JvmStatic
        public final boolean isValidKodeTps(String str) {
            String str2 = str;
            if ((str2 == null || StringsKt.isBlank(str2)) || StringsKt.toBigIntegerOrNull(str) == null) {
                return false;
            }
            return StringsKt.trim((CharSequence) str2).toString().length() > 0;
        }
    }
}
