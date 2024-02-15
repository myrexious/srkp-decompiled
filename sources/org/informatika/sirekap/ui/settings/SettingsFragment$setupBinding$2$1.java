package org.informatika.sirekap.ui.settings;

import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.lang3.StringUtils;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.support.ElectionUtil;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.settings.SettingsFragment$setupBinding$2$1;

/* compiled from: SettingsFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "org.informatika.sirekap.ui.settings.SettingsFragment$setupBinding$2$1", f = "SettingsFragment.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class SettingsFragment$setupBinding$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SettingsFragment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsFragment$setupBinding$2$1(SettingsFragment settingsFragment, Continuation<? super SettingsFragment$setupBinding$2$1> continuation) {
        super(2, continuation);
        this.this$0 = settingsFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SettingsFragment$setupBinding$2$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SettingsFragment$setupBinding$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SettingsViewModel viewModel;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            viewModel = this.this$0.getViewModel();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : viewModel.getAppDatabase().userDao().getAllSync()) {
                if (((User) obj2).getProfilIsValid()) {
                    arrayList.add(obj2);
                }
            }
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(arrayList, this.this$0, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* compiled from: SettingsFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "org.informatika.sirekap.ui.settings.SettingsFragment$setupBinding$2$1$1", f = "SettingsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.informatika.sirekap.ui.settings.SettingsFragment$setupBinding$2$1$1 */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<User> $allProfiles;
        int label;
        final /* synthetic */ SettingsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<User> list, SettingsFragment settingsFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$allProfiles = list;
            this.this$0 = settingsFragment;
        }

        public static final void invokeSuspend$lambda$2(DialogInterface dialogInterface, int i) {
        }

        public static final void invokeSuspend$lambda$5$lambda$3(DialogInterface dialogInterface, int i) {
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$allProfiles, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MainViewModel mainViewModel;
            SettingsViewModel viewModel;
            Tps tps;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<User> list = this.$allProfiles;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (User user : list) {
                arrayList.add(StringsKt.replace$default(user.getId(), ElectionUtil.KODE_TPS_SEPARATOR, " - ", false, 4, (Object) null));
            }
            String[] strArr = (String[]) arrayList.toArray(new String[0]);
            if (!(strArr.length == 0)) {
                mainViewModel = this.this$0.getMainViewModel();
                User value = mainViewModel.getGetLoggedInUserUseCase().getLoggedInUser().getValue();
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        i = -1;
                        break;
                    }
                    if (Intrinsics.areEqual(strArr[i], (value == null || (tps = value.getTps()) == null) ? null : tps.getKodeTpsFormattedProfile())) {
                        break;
                    }
                    i++;
                }
                int i2 = i != -1 ? i : 0;
                viewModel = this.this$0.getViewModel();
                viewModel.saveSelectedProfile(i2);
                final SettingsFragment settingsFragment = this.this$0;
                final List<User> list2 = this.$allProfiles;
                final SettingsFragment settingsFragment2 = this.this$0;
                new MaterialAlertDialogBuilder(this.this$0.requireContext()).setTitle((CharSequence) "Ganti Profil").setNeutralButton((CharSequence) this.this$0.getResources().getString(R.string.action_cancel), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$setupBinding$2$1$1$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        SettingsFragment$setupBinding$2$1.AnonymousClass1.invokeSuspend$lambda$2(dialogInterface, i3);
                    }
                }).setPositiveButton((CharSequence) this.this$0.getResources().getString(R.string.action_ok), new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$setupBinding$2$1$1$$ExternalSyntheticLambda1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        SettingsFragment$setupBinding$2$1.AnonymousClass1.invokeSuspend$lambda$5(SettingsFragment.this, list2, dialogInterface, i3);
                    }
                }).setSingleChoiceItems((CharSequence[]) strArr, i2, new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$setupBinding$2$1$1$$ExternalSyntheticLambda2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        SettingsFragment$setupBinding$2$1.AnonymousClass1.invokeSuspend$lambda$6(SettingsFragment.this, dialogInterface, i3);
                    }
                }).show();
                return Unit.INSTANCE;
            }
            FirebaseCrashlytics.getInstance().recordException(new Exception("SettingsFragment Tidak ada profil yang aktif"));
            BaseFragment.showSnackBar$default(this.this$0, "Tidak ada profil yang aktif", null, null, 6, null);
            return Unit.INSTANCE;
        }

        public static final void invokeSuspend$lambda$5(final SettingsFragment settingsFragment, List list, DialogInterface dialogInterface, int i) {
            SettingsViewModel viewModel;
            viewModel = settingsFragment.getViewModel();
            Integer value = viewModel.getProfileIndex().getValue();
            if (value == null) {
                value = -1;
            }
            int intValue = value.intValue();
            if (intValue == -1 || intValue > CollectionsKt.getLastIndex(list)) {
                FirebaseCrashlytics.getInstance().recordException(new Exception("SettingsFragment selectedProfileIndex: " + intValue));
                BaseFragment.showSnackBar$default(settingsFragment, "Gagal mengubah profil: " + intValue + StringUtils.SPACE + CollectionsKt.getLastIndex(list), null, null, 6, null);
                return;
            }
            final User user = (User) list.get(intValue);
            new MaterialAlertDialogBuilder(settingsFragment.requireContext()).setMessage((CharSequence) ("Apakah Anda yakin ingin mengubah profil ke " + user.getTps().getKodeTpsFormattedProfile() + "?")).setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$setupBinding$2$1$1$$ExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface2, int i2) {
                    SettingsFragment$setupBinding$2$1.AnonymousClass1.invokeSuspend$lambda$5$lambda$3(dialogInterface2, i2);
                }
            }).setPositiveButton(R.string.action_yes, new DialogInterface.OnClickListener() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$setupBinding$2$1$1$$ExternalSyntheticLambda4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface2, int i2) {
                    SettingsFragment$setupBinding$2$1.AnonymousClass1.invokeSuspend$lambda$5$lambda$4(SettingsFragment.this, user, dialogInterface2, i2);
                }
            }).show();
        }

        public static final void invokeSuspend$lambda$5$lambda$4(final SettingsFragment settingsFragment, User user, DialogInterface dialogInterface, int i) {
            SettingsViewModel viewModel;
            viewModel = settingsFragment.getViewModel();
            viewModel.confirmSelectedProfile(user, new Function0<Unit>() { // from class: org.informatika.sirekap.ui.settings.SettingsFragment$setupBinding$2$1$1$2$2$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    MainViewModel mainViewModel;
                    mainViewModel = SettingsFragment.this.getMainViewModel();
                    mainViewModel.getGetLoggedInUserUseCase().refresh();
                }
            });
        }

        public static final void invokeSuspend$lambda$6(SettingsFragment settingsFragment, DialogInterface dialogInterface, int i) {
            SettingsViewModel viewModel;
            viewModel = settingsFragment.getViewModel();
            viewModel.saveSelectedProfile(i);
        }
    }
}
