package org.informatika.sirekap.ui.witness.attendanceList.verify;

import android.content.DialogInterface;
import androidx.activity.OnBackPressedCallback;
import androidx.navigation.fragment.FragmentKt;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;

/* compiled from: VerifyWitnessAttendanceListFragment.kt */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"org/informatika/sirekap/ui/witness/attendanceList/verify/VerifyWitnessAttendanceListFragment$setupNavigation$1", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class VerifyWitnessAttendanceListFragment$setupNavigation$1 extends OnBackPressedCallback {
    final /* synthetic */ VerifyWitnessAttendanceListFragment this$0;

    public static final void handleOnBackPressed$lambda$2$lambda$0(DialogInterface dialogInterface, int i) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerifyWitnessAttendanceListFragment$setupNavigation$1(VerifyWitnessAttendanceListFragment verifyWitnessAttendanceListFragment) {
        super(true);
        this.this$0 = verifyWitnessAttendanceListFragment;
    }

    @Override // androidx.activity.OnBackPressedCallback
    public void handleOnBackPressed() {
        VerifyWitnessAttendanceListViewModel verifyWitnessAttendanceListViewModel;
        VerifyWitnessAttendanceListViewModel verifyWitnessAttendanceListViewModel2;
        verifyWitnessAttendanceListViewModel = this.this$0.getVerifyWitnessAttendanceListViewModel();
        Boolean value = verifyWitnessAttendanceListViewModel.isFirstItem().getValue();
        if (value != null) {
            final VerifyWitnessAttendanceListFragment verifyWitnessAttendanceListFragment = this.this$0;
            if (!value.booleanValue()) {
                verifyWitnessAttendanceListViewModel2 = verifyWitnessAttendanceListFragment.getVerifyWitnessAttendanceListViewModel();
                verifyWitnessAttendanceListViewModel2.backItem();
                return;
            }
            new MaterialAlertDialogBuilder(verifyWitnessAttendanceListFragment.requireContext()).setMessage((CharSequence) verifyWitnessAttendanceListFragment.getString(R.string.verify_attendees_cancel_dialog_message)).setNegativeButton((CharSequence) verifyWitnessAttendanceListFragment.getString(R.string.action_no), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListFragment$setupNavigation$1$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    VerifyWitnessAttendanceListFragment$setupNavigation$1.handleOnBackPressed$lambda$2$lambda$0(dialogInterface, i);
                }
            }).setPositiveButton((CharSequence) verifyWitnessAttendanceListFragment.getString(R.string.action_yes), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListFragment$setupNavigation$1$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    VerifyWitnessAttendanceListFragment$setupNavigation$1.handleOnBackPressed$lambda$2$lambda$1(VerifyWitnessAttendanceListFragment.this, dialogInterface, i);
                }
            }).show();
        }
    }

    public static final void handleOnBackPressed$lambda$2$lambda$1(VerifyWitnessAttendanceListFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            FragmentKt.findNavController(this$0).navigateUp();
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }
}
