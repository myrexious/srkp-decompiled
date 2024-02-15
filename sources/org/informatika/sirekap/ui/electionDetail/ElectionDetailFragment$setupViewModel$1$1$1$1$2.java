package org.informatika.sirekap.ui.electionDetail;

import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.PopupMenu;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentElectionDetailBinding;
import org.informatika.sirekap.model.ElectionPage;

/* compiled from: ElectionDetailFragment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "Landroid/view/View;", "electionPage", "Lorg/informatika/sirekap/model/ElectionPage;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ElectionDetailFragment$setupViewModel$1$1$1$1$2 extends Lambda implements Function2<View, ElectionPage, Unit> {
    final /* synthetic */ ElectionDetailFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElectionDetailFragment$setupViewModel$1$1$1$1$2(ElectionDetailFragment electionDetailFragment) {
        super(2);
        this.this$0 = electionDetailFragment;
    }

    public static final void invoke$lambda$3$lambda$2(PopupMenu popupMenu) {
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(View view, ElectionPage electionPage) {
        invoke2(view, electionPage);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke */
    public final void invoke2(View v, final ElectionPage electionPage) {
        FragmentElectionDetailBinding fragmentElectionDetailBinding;
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(electionPage, "electionPage");
        fragmentElectionDetailBinding = this.this$0.binding;
        if (fragmentElectionDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentElectionDetailBinding = null;
        }
        PopupMenu popupMenu = new PopupMenu(fragmentElectionDetailBinding.getRoot().getContext(), v);
        final ElectionDetailFragment electionDetailFragment = this.this$0;
        if (!electionPage.isPhotoTaken()) {
            popupMenu.getMenuInflater().inflate(R.menu.item_election_page_capture_c1_menu, popupMenu.getMenu());
            popupMenu.getMenu().findItem(R.id.action_capture_manual).setEnabled(true);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$1$1$2$$ExternalSyntheticLambda0
                @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    boolean invoke$lambda$3$lambda$0;
                    invoke$lambda$3$lambda$0 = ElectionDetailFragment$setupViewModel$1$1$1$1$2.invoke$lambda$3$lambda$0(ElectionDetailFragment.this, electionPage, menuItem);
                    return invoke$lambda$3$lambda$0;
                }
            });
        } else {
            popupMenu.getMenuInflater().inflate(R.menu.item_election_page_recapture_c1_menu, popupMenu.getMenu());
            popupMenu.getMenu().findItem(R.id.action_recapture_manual).setEnabled(true ^ electionPage.isVerified());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$1$1$2$$ExternalSyntheticLambda1
                @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    boolean invoke$lambda$3$lambda$1;
                    invoke$lambda$3$lambda$1 = ElectionDetailFragment$setupViewModel$1$1$1$1$2.invoke$lambda$3$lambda$1(ElectionDetailFragment.this, electionPage, menuItem);
                    return invoke$lambda$3$lambda$1;
                }
            });
        }
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() { // from class: org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment$setupViewModel$1$1$1$1$2$$ExternalSyntheticLambda2
            @Override // androidx.appcompat.widget.PopupMenu.OnDismissListener
            public final void onDismiss(PopupMenu popupMenu2) {
                ElectionDetailFragment$setupViewModel$1$1$1$1$2.invoke$lambda$3$lambda$2(popupMenu2);
            }
        });
        popupMenu.show();
    }

    public static final boolean invoke$lambda$3$lambda$0(ElectionDetailFragment this$0, ElectionPage electionPage, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPage, "$electionPage");
        if (menuItem.getItemId() == R.id.action_capture_manual) {
            this$0.requestPermissionTakePhoto(electionPage);
            return true;
        }
        return true;
    }

    public static final boolean invoke$lambda$3$lambda$1(ElectionDetailFragment this$0, ElectionPage electionPage, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(electionPage, "$electionPage");
        if (menuItem.getItemId() == R.id.action_recapture_manual) {
            this$0.requestPermissionTakePhoto(electionPage);
            return true;
        }
        return true;
    }
}
