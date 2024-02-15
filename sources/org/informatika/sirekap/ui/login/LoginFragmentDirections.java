package org.informatika.sirekap.ui.login;

import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.informatika.sirekap.R;

/* compiled from: LoginFragmentDirections.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lorg/informatika/sirekap/ui/login/LoginFragmentDirections;", "", "()V", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class LoginFragmentDirections {
    public static final Companion Companion = new Companion(null);

    /* compiled from: LoginFragmentDirections.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/login/LoginFragmentDirections$Companion;", "", "()V", "actionLoginFragmentToCertificateFragment", "Landroidx/navigation/NavDirections;", "actionLoginFragmentToLoginTpsFragment", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NavDirections actionLoginFragmentToLoginTpsFragment() {
            return new ActionOnlyNavDirections(R.id.action_loginFragment_to_loginTpsFragment);
        }

        public final NavDirections actionLoginFragmentToCertificateFragment() {
            return new ActionOnlyNavDirections(R.id.action_loginFragment_to_certificateFragment);
        }
    }

    private LoginFragmentDirections() {
    }
}
