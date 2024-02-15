package org.informatika.sirekap.ui.witness.attendanceList;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.ItemWitnessAttendanceBinding;
import org.informatika.sirekap.model.AttendancePage;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceAdapter;

/* compiled from: WitnessAttendanceAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000  2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002 !B¦\u0001\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005\u0012!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005\u00126\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\t0\f\u0012!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\u0002\u0010\u0012J\u0018\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001bH\u0016R$\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R)\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\t0\fX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/informatika/sirekap/model/AttendancePage;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onDelete", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "attendancePage", "", "onRetake", "onMove", "Lkotlin/Function2;", "", "isUp", "onClickPicture", "", "croppedPhotoPath", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "value", "isVerified", "()Z", "setVerified", "(Z)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "WitnessViewHolder", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WitnessAttendanceAdapter extends ListAdapter<AttendancePage, RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final WitnessAttendanceAdapter$Companion$WITNESS_ATTENDANCE_COMPARATOR$1 WITNESS_ATTENDANCE_COMPARATOR = new DiffUtil.ItemCallback<AttendancePage>() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceAdapter$Companion$WITNESS_ATTENDANCE_COMPARATOR$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(AttendancePage oldItem, AttendancePage newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return oldItem.getId() == newItem.getId();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(AttendancePage oldItem, AttendancePage newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }
    };
    private boolean isVerified;
    private final Function1<String, Unit> onClickPicture;
    private final Function1<AttendancePage, Unit> onDelete;
    private final Function2<AttendancePage, Boolean, Unit> onMove;
    private final Function1<AttendancePage, Unit> onRetake;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WitnessAttendanceAdapter(Function1<? super AttendancePage, Unit> onDelete, Function1<? super AttendancePage, Unit> onRetake, Function2<? super AttendancePage, ? super Boolean, Unit> onMove, Function1<? super String, Unit> onClickPicture) {
        super(WITNESS_ATTENDANCE_COMPARATOR);
        Intrinsics.checkNotNullParameter(onDelete, "onDelete");
        Intrinsics.checkNotNullParameter(onRetake, "onRetake");
        Intrinsics.checkNotNullParameter(onMove, "onMove");
        Intrinsics.checkNotNullParameter(onClickPicture, "onClickPicture");
        this.onDelete = onDelete;
        this.onRetake = onRetake;
        this.onMove = onMove;
        this.onClickPicture = onClickPicture;
    }

    /* compiled from: WitnessAttendanceAdapter.kt */
    @Metadata(d1 = {"\u0000\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceAdapter$Companion;", "", "()V", "WITNESS_ATTENDANCE_COMPARATOR", "org/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceAdapter$Companion$WITNESS_ATTENDANCE_COMPARATOR$1", "Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceAdapter$Companion$WITNESS_ATTENDANCE_COMPARATOR$1;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final boolean isVerified() {
        return this.isVerified;
    }

    public final void setVerified(boolean z) {
        this.isVerified = z;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        WitnessViewHolder witnessViewHolder = (WitnessViewHolder) holder;
        AttendancePage item = getItem(i);
        Intrinsics.checkNotNullExpressionValue(item, "getItem(position)");
        witnessViewHolder.bind(item, this.onDelete, this.onRetake, this.onMove, this.isVerified, this.onClickPicture);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return WitnessViewHolder.Companion.create(parent);
    }

    /* compiled from: WitnessAttendanceAdapter.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J·\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\f2!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\f26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0015\u001a\u00020\u00132!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\b0\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001a"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceAdapter$WitnessViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/informatika/sirekap/databinding/ItemWitnessAttendanceBinding;", "(Lorg/informatika/sirekap/databinding/ItemWitnessAttendanceBinding;)V", "getBinding", "()Lorg/informatika/sirekap/databinding/ItemWitnessAttendanceBinding;", "bind", "", "item", "Lorg/informatika/sirekap/model/AttendancePage;", "onDelete", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "attendancePage", "onRetake", "onMove", "Lkotlin/Function2;", "", "isUp", "isVerified", "onClickPicture", "", "croppedPhotoPath", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class WitnessViewHolder extends RecyclerView.ViewHolder {
        public static final Companion Companion = new Companion(null);
        private final ItemWitnessAttendanceBinding binding;

        public static final void bind$lambda$8$lambda$7$lambda$3$lambda$2$lambda$1(PopupMenu popupMenu) {
        }

        public final ItemWitnessAttendanceBinding getBinding() {
            return this.binding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WitnessViewHolder(ItemWitnessAttendanceBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.binding = binding;
        }

        public final void bind(final AttendancePage item, final Function1<? super AttendancePage, Unit> onDelete, final Function1<? super AttendancePage, Unit> onRetake, final Function2<? super AttendancePage, ? super Boolean, Unit> onMove, boolean z, final Function1<? super String, Unit> onClickPicture) {
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(onDelete, "onDelete");
            Intrinsics.checkNotNullParameter(onRetake, "onRetake");
            Intrinsics.checkNotNullParameter(onMove, "onMove");
            Intrinsics.checkNotNullParameter(onClickPicture, "onClickPicture");
            final ItemWitnessAttendanceBinding itemWitnessAttendanceBinding = this.binding;
            itemWitnessAttendanceBinding.buttonMoreMenu.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceAdapter$WitnessViewHolder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WitnessAttendanceAdapter.WitnessViewHolder.bind$lambda$8$lambda$7$lambda$3(WitnessAttendanceAdapter.WitnessViewHolder.this, onMove, item, view);
                }
            });
            itemWitnessAttendanceBinding.setCroppedPhotoPath(item.getCroppedPhotoPath());
            itemWitnessAttendanceBinding.setNomorHalaman("Halaman " + item.getUrutan());
            itemWitnessAttendanceBinding.buttonDeletePhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceAdapter$WitnessViewHolder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WitnessAttendanceAdapter.WitnessViewHolder.bind$lambda$8$lambda$7$lambda$4(Function1.this, item, view);
                }
            });
            itemWitnessAttendanceBinding.buttonRetakePhoto.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceAdapter$WitnessViewHolder$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WitnessAttendanceAdapter.WitnessViewHolder.bind$lambda$8$lambda$7$lambda$5(Function1.this, item, view);
                }
            });
            if (z) {
                itemWitnessAttendanceBinding.buttonMoreMenu.setVisibility(8);
                itemWitnessAttendanceBinding.buttonRetakePhoto.setEnabled(false);
                itemWitnessAttendanceBinding.buttonDeletePhoto.setEnabled(false);
            }
            itemWitnessAttendanceBinding.image.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceAdapter$WitnessViewHolder$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WitnessAttendanceAdapter.WitnessViewHolder.bind$lambda$8$lambda$7$lambda$6(Function1.this, itemWitnessAttendanceBinding, view);
                }
            });
        }

        public static final void bind$lambda$8$lambda$7$lambda$3(WitnessViewHolder this$0, final Function2 onMove, final AttendancePage currentItem, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(onMove, "$onMove");
            Intrinsics.checkNotNullParameter(currentItem, "$currentItem");
            PopupMenu popupMenu = new PopupMenu(this$0.binding.getRoot().getContext(), view);
            popupMenu.getMenuInflater().inflate(R.menu.item_witness_attendance_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceAdapter$WitnessViewHolder$$ExternalSyntheticLambda0
                @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    boolean bind$lambda$8$lambda$7$lambda$3$lambda$2$lambda$0;
                    bind$lambda$8$lambda$7$lambda$3$lambda$2$lambda$0 = WitnessAttendanceAdapter.WitnessViewHolder.bind$lambda$8$lambda$7$lambda$3$lambda$2$lambda$0(Function2.this, currentItem, menuItem);
                    return bind$lambda$8$lambda$7$lambda$3$lambda$2$lambda$0;
                }
            });
            popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceAdapter$WitnessViewHolder$$ExternalSyntheticLambda1
                @Override // androidx.appcompat.widget.PopupMenu.OnDismissListener
                public final void onDismiss(PopupMenu popupMenu2) {
                    WitnessAttendanceAdapter.WitnessViewHolder.bind$lambda$8$lambda$7$lambda$3$lambda$2$lambda$1(popupMenu2);
                }
            });
            popupMenu.show();
        }

        public static final boolean bind$lambda$8$lambda$7$lambda$3$lambda$2$lambda$0(Function2 onMove, AttendancePage currentItem, MenuItem menuItem) {
            Intrinsics.checkNotNullParameter(onMove, "$onMove");
            Intrinsics.checkNotNullParameter(currentItem, "$currentItem");
            int itemId = menuItem.getItemId();
            if (itemId == R.id.move_up) {
                onMove.invoke(currentItem, true);
            } else if (itemId == R.id.move_down) {
                onMove.invoke(currentItem, false);
            }
            return true;
        }

        public static final void bind$lambda$8$lambda$7$lambda$4(Function1 onDelete, AttendancePage currentItem, View view) {
            Intrinsics.checkNotNullParameter(onDelete, "$onDelete");
            Intrinsics.checkNotNullParameter(currentItem, "$currentItem");
            onDelete.invoke(currentItem);
        }

        public static final void bind$lambda$8$lambda$7$lambda$5(Function1 onRetake, AttendancePage currentItem, View view) {
            Intrinsics.checkNotNullParameter(onRetake, "$onRetake");
            Intrinsics.checkNotNullParameter(currentItem, "$currentItem");
            onRetake.invoke(currentItem);
        }

        public static final void bind$lambda$8$lambda$7$lambda$6(Function1 onClickPicture, ItemWitnessAttendanceBinding this_apply, View view) {
            Intrinsics.checkNotNullParameter(onClickPicture, "$onClickPicture");
            Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
            String croppedPhotoPath = this_apply.getCroppedPhotoPath();
            if (croppedPhotoPath == null) {
                croppedPhotoPath = "";
            }
            onClickPicture.invoke(croppedPhotoPath);
        }

        /* compiled from: WitnessAttendanceAdapter.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceAdapter$WitnessViewHolder$Companion;", "", "()V", "create", "Lorg/informatika/sirekap/ui/witness/attendanceList/WitnessAttendanceAdapter$WitnessViewHolder;", "parent", "Landroid/view/ViewGroup;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes4.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final WitnessViewHolder create(ViewGroup parent) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                ItemWitnessAttendanceBinding inflate = ItemWitnessAttendanceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …  false\n                )");
                return new WitnessViewHolder(inflate);
            }
        }
    }
}
