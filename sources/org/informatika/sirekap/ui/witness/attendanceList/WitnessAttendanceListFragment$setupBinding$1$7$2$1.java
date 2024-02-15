package org.informatika.sirekap.ui.witness.attendanceList;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.databinding.DialogSelectRetakePhotoMethodBinding;
import org.informatika.sirekap.support.ConnectivityUtil;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$7$2$1;

/* compiled from: WitnessAttendanceListFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$7$2$1", f = "WitnessAttendanceListFragment.kt", i = {}, l = {242, 247}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class WitnessAttendanceListFragment$setupBinding$1$7$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ WitnessAttendanceListFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WitnessAttendanceListFragment$setupBinding$1$7$2$1(WitnessAttendanceListFragment witnessAttendanceListFragment, Continuation<? super WitnessAttendanceListFragment$setupBinding$1$7$2$1> continuation) {
        super(2, continuation);
        this.this$0 = witnessAttendanceListFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WitnessAttendanceListFragment$setupBinding$1$7$2$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WitnessAttendanceListFragment$setupBinding$1$7$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Context requireContext = this.this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (ConnectivityUtil.isConnectedToNetwork(requireContext) && ConnectivityUtil.isUserHasInternetConnection()) {
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.this$0, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                this.label = 2;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.this$0, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i != 1 && i != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* compiled from: WitnessAttendanceListFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$7$2$1$1", f = "WitnessAttendanceListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$7$2$1$1 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ WitnessAttendanceListFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(WitnessAttendanceListFragment witnessAttendanceListFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = witnessAttendanceListFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MainViewModel mainViewModel;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                mainViewModel = this.this$0.getMainViewModel();
                mainViewModel.getAuthRequestUseCase().isLoading().postValue(Boxing.boxBoolean(false));
                this.this$0.trySubmitOnline();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: WitnessAttendanceListFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$7$2$1$2", f = "WitnessAttendanceListFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$7$2$1$2 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ WitnessAttendanceListFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(WitnessAttendanceListFragment witnessAttendanceListFragment, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = witnessAttendanceListFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MainViewModel mainViewModel;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                mainViewModel = this.this$0.getMainViewModel();
                mainViewModel.getAuthRequestUseCase().isLoading().postValue(Boxing.boxBoolean(false));
                DialogSelectRetakePhotoMethodBinding inflate = DialogSelectRetakePhotoMethodBinding.inflate(this.this$0.getLayoutInflater(), null, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …                        )");
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this.this$0.requireContext());
                inflate.setLifecycleOwner(this.this$0.getViewLifecycleOwner());
                materialAlertDialogBuilder.setView(inflate.getRoot()).setMessage((CharSequence) "Anda sedang tidak terhubung dengan Internet sehingga masuk ke mode offline.");
                final AlertDialog create = materialAlertDialogBuilder.create();
                Intrinsics.checkNotNullExpressionValue(create, "MaterialAlertDialogBuild…               }.create()");
                create.show();
                Button button = inflate.offlineButton;
                final WitnessAttendanceListFragment witnessAttendanceListFragment = this.this$0;
                button.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$7$2$1$2$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WitnessAttendanceListFragment$setupBinding$1$7$2$1.AnonymousClass2.invokeSuspend$lambda$1(AlertDialog.this, witnessAttendanceListFragment, view);
                    }
                });
                inflate.cancelButton.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment$setupBinding$1$7$2$1$2$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AlertDialog.this.dismiss();
                    }
                });
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        public static final void invokeSuspend$lambda$1(AlertDialog alertDialog, WitnessAttendanceListFragment witnessAttendanceListFragment, View view) {
            WitnessAttendanceListViewModel witnessAttendanceListViewModel;
            WitnessAttendanceListFragmentArgs args;
            alertDialog.dismiss();
            witnessAttendanceListViewModel = witnessAttendanceListFragment.getWitnessAttendanceListViewModel();
            args = witnessAttendanceListFragment.getArgs();
            witnessAttendanceListViewModel.finishUploadPdfOffline(args.getKodeTps());
            BaseFragment.showSnackBar$default(witnessAttendanceListFragment, "Berkas Daftar Hadir berhasil disimpan", null, null, 6, null);
        }
    }
}
