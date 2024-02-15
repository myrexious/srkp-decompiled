package org.informatika.sirekap.ui;

import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.snackbar.Snackbar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.ResourceEventType;
import org.informatika.sirekap.R;

/* compiled from: BaseFragment.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000fH\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lorg/informatika/sirekap/ui/BaseFragment;", "Landroidx/fragment/app/Fragment;", "()V", "snackBar", "Lcom/google/android/material/snackbar/Snackbar;", "getSnackBar", "()Lcom/google/android/material/snackbar/Snackbar;", "setSnackBar", "(Lcom/google/android/material/snackbar/Snackbar;)V", "showSnackBar", "", "message", "", ResourceEventType.ACTION, "onAction", "Lkotlin/Function0;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public class BaseFragment extends Fragment {
    private Snackbar snackBar;

    public final Snackbar getSnackBar() {
        return this.snackBar;
    }

    protected final void setSnackBar(Snackbar snackbar) {
        this.snackBar = snackbar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showSnackBar$default(BaseFragment baseFragment, String str, String str2, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showSnackBar");
        }
        if ((i & 2) != 0) {
            str2 = baseFragment.getString(R.string.action_ok);
            Intrinsics.checkNotNullExpressionValue(str2, "getString(R.string.action_ok)");
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        baseFragment.showSnackBar(str, str2, function0);
    }

    public final void showSnackBar(String message, String action, final Function0<Unit> function0) {
        View view;
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(action, "action");
        Snackbar snackbar = this.snackBar;
        if (snackbar != null) {
            snackbar.dismiss();
        }
        Snackbar make = Snackbar.make(requireView(), message, -2);
        this.snackBar = make;
        if (make != null) {
            make.setAction(action, new View.OnClickListener() { // from class: org.informatika.sirekap.ui.BaseFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    BaseFragment.showSnackBar$lambda$0(Function0.this, view2);
                }
            });
        }
        Snackbar snackbar2 = this.snackBar;
        TextView textView = (snackbar2 == null || (view = snackbar2.getView()) == null) ? null : (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        if (textView != null) {
            textView.setMaxLines(10);
        }
        Snackbar snackbar3 = this.snackBar;
        if (snackbar3 != null) {
            snackbar3.show();
        }
    }

    public static final void showSnackBar$lambda$0(Function0 function0, View view) {
        if (function0 != null) {
            function0.invoke();
        }
    }
}
