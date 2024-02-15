package org.informatika.sirekap.ui.dashboard;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ActiveProfile;
import org.informatika.sirekap.model.User;

/* compiled from: DashboardFragment.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Landroidx/appcompat/app/AlertDialog;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.dashboard.DashboardFragment$onResume$1$1$1", f = "DashboardFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class DashboardFragment$onResume$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AlertDialog>, Object> {
    final /* synthetic */ ActiveProfile $it;
    final /* synthetic */ User $otherActiveProfileEncrypted;
    int label;
    final /* synthetic */ DashboardFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashboardFragment$onResume$1$1$1(User user, DashboardFragment dashboardFragment, ActiveProfile activeProfile, Continuation<? super DashboardFragment$onResume$1$1$1> continuation) {
        super(2, continuation);
        this.$otherActiveProfileEncrypted = user;
        this.this$0 = dashboardFragment;
        this.$it = activeProfile;
    }

    public static final void invokeSuspend$lambda$0(DialogInterface dialogInterface, int i) {
    }

    public static final void invokeSuspend$lambda$2(DialogInterface dialogInterface, int i) {
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashboardFragment$onResume$1$1$1(this.$otherActiveProfileEncrypted, this.this$0, this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AlertDialog> continuation) {
        return ((DashboardFragment$onResume$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (this.$otherActiveProfileEncrypted == null) {
            MaterialAlertDialogBuilder positiveButton = new MaterialAlertDialogBuilder(this.this$0.requireContext()).setTitle((CharSequence) "Tidak Ada Profil yang Aktif").setMessage((CharSequence) "Anda tidak dapat melanjutkan menggunakan aplikasi.").setPositiveButton((CharSequence) "Keluar Aplikasi", new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$onResume$1$1$1$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DashboardFragment$onResume$1$1$1.invokeSuspend$lambda$0(dialogInterface, i);
                }
            });
            final DashboardFragment dashboardFragment = this.this$0;
            return positiveButton.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$onResume$1$1$1$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    DashboardFragment$onResume$1$1$1.invokeSuspend$lambda$1(DashboardFragment.this, dialogInterface);
                }
            }).show();
        }
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this.this$0.requireContext());
        String nama_profil = this.$it.getNama_profil();
        MaterialAlertDialogBuilder positiveButton2 = materialAlertDialogBuilder.setTitle((CharSequence) ("Masa Berlaku Profil '" + nama_profil + " - " + this.$it.getKode_wilayah() + "' Sudah Habis")).setMessage((CharSequence) ("Anda akan dipindahkan ke profil yang masih aktif: '" + this.$otherActiveProfileEncrypted.getTps().getKodeTpsFormattedProfile() + "'.")).setPositiveButton((CharSequence) this.this$0.getString(R.string.action_ok), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$onResume$1$1$1$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DashboardFragment$onResume$1$1$1.invokeSuspend$lambda$2(dialogInterface, i);
            }
        });
        final DashboardFragment dashboardFragment2 = this.this$0;
        final User user = this.$otherActiveProfileEncrypted;
        return positiveButton2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: org.informatika.sirekap.ui.dashboard.DashboardFragment$onResume$1$1$1$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                DashboardFragment$onResume$1$1$1.invokeSuspend$lambda$3(DashboardFragment.this, user, dialogInterface);
            }
        }).show();
    }

    public static final void invokeSuspend$lambda$1(DashboardFragment dashboardFragment, DialogInterface dialogInterface) {
        dashboardFragment.requireActivity().finish();
    }

    public static final void invokeSuspend$lambda$3(DashboardFragment dashboardFragment, User user, DialogInterface dialogInterface) {
        MainViewModel mainViewModel;
        mainViewModel = dashboardFragment.getMainViewModel();
        mainViewModel.confirmSelectedProfile(user, user);
    }
}
