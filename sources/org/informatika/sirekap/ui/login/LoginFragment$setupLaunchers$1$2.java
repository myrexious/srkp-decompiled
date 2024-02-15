package org.informatika.sirekap.ui.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.informatika.sirekap.R;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.ui.BaseFragment;

/* compiled from: LoginFragment.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class LoginFragment$setupLaunchers$1$2 extends Lambda implements Function1<Exception, Unit> {
    final /* synthetic */ LoginFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoginFragment$setupLaunchers$1$2(LoginFragment loginFragment) {
        super(1);
        this.this$0 = loginFragment;
    }

    public static final void invoke$lambda$1(DialogInterface dialogInterface, int i) {
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
        invoke2(exc);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke */
    public final void invoke2(Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        if (StringsKt.contains$default((CharSequence) String.valueOf(e.getMessage()), (CharSequence) "Invalid ID Token", false, 2, (Object) null)) {
            Context requireContext = this.this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (!ElectionUtil.isTimeAutomatic(requireContext)) {
                final LoginFragment loginFragment = this.this$0;
                new MaterialAlertDialogBuilder(this.this$0.requireContext()).setTitle((CharSequence) "Peringatan Gagal Login").setMessage((CharSequence) "Silakan buka Pengaturan pada perangkat Anda dan pastikan perangkat Anda menggunakan tanggal dan waktu secara otomatis.").setPositiveButton((CharSequence) "Buka Pengaturan", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.login.LoginFragment$setupLaunchers$1$2$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        LoginFragment$setupLaunchers$1$2.invoke$lambda$0(LoginFragment.this, dialogInterface, i);
                    }
                }).setNegativeButton((CharSequence) this.this$0.getString(R.string.action_close), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.login.LoginFragment$setupLaunchers$1$2$$ExternalSyntheticLambda1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        LoginFragment$setupLaunchers$1$2.invoke$lambda$1(dialogInterface, i);
                    }
                }).show();
            }
        }
        BaseFragment.showSnackBar$default(this.this$0, String.valueOf(e.getMessage()), null, null, 6, null);
    }

    public static final void invoke$lambda$0(LoginFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivityForResult(new Intent("android.settings.DATE_SETTINGS"), 0);
    }
}
