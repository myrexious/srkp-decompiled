package org.informatika.sirekap.ui.witness.qrCode;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.List;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.informatika.sirekap.R;
import org.informatika.sirekap.databinding.FragmentWitnessUrlsBinding;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.ui.BaseFragment;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;

/* compiled from: WitnessUrlListFragment.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u001a\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010'\u001a\u00020\u001aH\u0002J\b\u0010(\u001a\u00020\u001aH\u0002J\u0010\u0010)\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016¨\u0006+"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "adapter", "Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListAdapter;", "args", "Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lorg/informatika/sirekap/databinding/FragmentWitnessUrlsBinding;", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "getEncryptedSharedPreferences", "()Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "setEncryptedSharedPreferences", "(Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;)V", "viewModel", "Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListViewModel;", "getViewModel", "()Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "copyUrlToClipboard", "", "url", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "setupBinding", "setupViewModel", "shareUrl", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class WitnessUrlListFragment extends Hilt_WitnessUrlListFragment {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "WitnessUrlListFragment";
    private WitnessUrlListAdapter adapter;
    private final NavArgsLazy args$delegate;
    private FragmentWitnessUrlsBinding binding;
    @Inject
    public EncryptedSharedPreferences encryptedSharedPreferences;
    private final Lazy viewModel$delegate;

    public WitnessUrlListFragment() {
        final WitnessUrlListFragment witnessUrlListFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(witnessUrlListFragment, Reflection.getOrCreateKotlinClass(WitnessUrlListViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$special$$inlined$viewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStoreOwner m68viewModels$lambda1;
                m68viewModels$lambda1 = FragmentViewModelLazyKt.m68viewModels$lambda1(Lazy.this);
                return m68viewModels$lambda1.getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                ViewModelStoreOwner m68viewModels$lambda1;
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) {
                    m68viewModels$lambda1 = FragmentViewModelLazyKt.m68viewModels$lambda1(lazy);
                    HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = m68viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) m68viewModels$lambda1 : null;
                    return hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$special$$inlined$viewModels$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelStoreOwner m68viewModels$lambda1;
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                m68viewModels$lambda1 = FragmentViewModelLazyKt.m68viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = m68viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) m68viewModels$lambda1 : null;
                if (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) {
                    ViewModelProvider.Factory defaultViewModelProviderFactory2 = Fragment.this.getDefaultViewModelProviderFactory();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory2, "defaultViewModelProviderFactory");
                    return defaultViewModelProviderFactory2;
                }
                return defaultViewModelProviderFactory;
            }
        });
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(WitnessUrlListFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$special$$inlined$navArgs$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Bundle invoke() {
                Bundle arguments = Fragment.this.getArguments();
                if (arguments != null) {
                    return arguments;
                }
                throw new IllegalStateException("Fragment " + Fragment.this + " has null arguments");
            }
        });
    }

    public final WitnessUrlListViewModel getViewModel() {
        return (WitnessUrlListViewModel) this.viewModel$delegate.getValue();
    }

    private final WitnessUrlListFragmentArgs getArgs() {
        return (WitnessUrlListFragmentArgs) this.args$delegate.getValue();
    }

    public final EncryptedSharedPreferences getEncryptedSharedPreferences() {
        EncryptedSharedPreferences encryptedSharedPreferences = this.encryptedSharedPreferences;
        if (encryptedSharedPreferences != null) {
            return encryptedSharedPreferences;
        }
        Intrinsics.throwUninitializedPropertyAccessException("encryptedSharedPreferences");
        return null;
    }

    public final void setEncryptedSharedPreferences(EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "<set-?>");
        this.encryptedSharedPreferences = encryptedSharedPreferences;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWitnessUrlsBinding inflate = FragmentWitnessUrlsBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setupBinding();
        setupViewModel();
        getViewModel().setup(getArgs().getKodeTps(), getArgs().getJenisPemilihan());
    }

    private final void setupBinding() {
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding = this.binding;
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding2 = null;
        if (fragmentWitnessUrlsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessUrlsBinding = null;
        }
        fragmentWitnessUrlsBinding.setLifecycleOwner(getViewLifecycleOwner());
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding3 = this.binding;
        if (fragmentWitnessUrlsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessUrlsBinding3 = null;
        }
        fragmentWitnessUrlsBinding3.setViewModel(getViewModel());
        this.adapter = new WitnessUrlListAdapter(getArgs().getJenisPemilihan(), getEncryptedSharedPreferences(), new Function2<String, String, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$setupBinding$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String jenisPemilihan, String witnessId) {
                Snackbar snackBar;
                WitnessUrlListViewModel viewModel;
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                Intrinsics.checkNotNullParameter(witnessId, "witnessId");
                snackBar = WitnessUrlListFragment.this.getSnackBar();
                if (snackBar != null) {
                    snackBar.dismiss();
                }
                FirebaseCrashlytics.getInstance().log("Click 'Dapatkan Salinan Form " + jenisPemilihan + OperatorName.SHOW_TEXT_LINE);
                viewModel = WitnessUrlListFragment.this.getViewModel();
                viewModel.markWitnessAsShared(jenisPemilihan, witnessId);
            }
        }, new Function2<String, String, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$setupBinding$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String url, String jenisPemilihan) {
                Intrinsics.checkNotNullParameter(url, "url");
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form " + jenisPemilihan + OperatorName.SHOW_TEXT_LINE);
                WitnessUrlListFragment.this.copyUrlToClipboard(url);
            }
        }, new Function2<String, String, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$setupBinding$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String url, String jenisPemilihan) {
                Intrinsics.checkNotNullParameter(url, "url");
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                FirebaseCrashlytics.getInstance().log("Click 'Bagikan Salinan Form " + jenisPemilihan + OperatorName.SHOW_TEXT_LINE);
                WitnessUrlListFragment.this.shareUrl(url);
            }
        }, new Function2<String, String, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$setupBinding$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String url, String jenisPemilihan) {
                Intrinsics.checkNotNullParameter(url, "url");
                Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
                FirebaseCrashlytics.getInstance().log("Click 'Salin Salinan Form " + jenisPemilihan + "' (long click)");
                WitnessUrlListFragment.this.copyUrlToClipboard(url);
            }
        });
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding4 = this.binding;
        if (fragmentWitnessUrlsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessUrlsBinding4 = null;
        }
        RecyclerView recyclerView = fragmentWitnessUrlsBinding4.listWitness;
        WitnessUrlListAdapter witnessUrlListAdapter = this.adapter;
        if (witnessUrlListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            witnessUrlListAdapter = null;
        }
        recyclerView.setAdapter(witnessUrlListAdapter);
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding5 = this.binding;
        if (fragmentWitnessUrlsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessUrlsBinding5 = null;
        }
        fragmentWitnessUrlsBinding5.chipFilterPilpres.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessUrlListFragment.setupBinding$lambda$0(WitnessUrlListFragment.this, compoundButton, z);
            }
        });
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding6 = this.binding;
        if (fragmentWitnessUrlsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessUrlsBinding6 = null;
        }
        fragmentWitnessUrlsBinding6.chipFilterPilgub.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessUrlListFragment.setupBinding$lambda$1(WitnessUrlListFragment.this, compoundButton, z);
            }
        });
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding7 = this.binding;
        if (fragmentWitnessUrlsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessUrlsBinding7 = null;
        }
        fragmentWitnessUrlsBinding7.chipFilterPilwabup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessUrlListFragment.setupBinding$lambda$2(WitnessUrlListFragment.this, compoundButton, z);
            }
        });
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding8 = this.binding;
        if (fragmentWitnessUrlsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessUrlsBinding8 = null;
        }
        fragmentWitnessUrlsBinding8.chipFilterDpr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessUrlListFragment.setupBinding$lambda$3(WitnessUrlListFragment.this, compoundButton, z);
            }
        });
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding9 = this.binding;
        if (fragmentWitnessUrlsBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessUrlsBinding9 = null;
        }
        fragmentWitnessUrlsBinding9.chipFilterDprdp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$$ExternalSyntheticLambda4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessUrlListFragment.setupBinding$lambda$4(WitnessUrlListFragment.this, compoundButton, z);
            }
        });
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding10 = this.binding;
        if (fragmentWitnessUrlsBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWitnessUrlsBinding10 = null;
        }
        fragmentWitnessUrlsBinding10.chipFilterDprdk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$$ExternalSyntheticLambda5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessUrlListFragment.setupBinding$lambda$5(WitnessUrlListFragment.this, compoundButton, z);
            }
        });
        FragmentWitnessUrlsBinding fragmentWitnessUrlsBinding11 = this.binding;
        if (fragmentWitnessUrlsBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWitnessUrlsBinding2 = fragmentWitnessUrlsBinding11;
        }
        fragmentWitnessUrlsBinding2.chipFilterDpd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$$ExternalSyntheticLambda6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WitnessUrlListFragment.setupBinding$lambda$6(WitnessUrlListFragment.this, compoundButton, z);
            }
        });
    }

    public static final void setupBinding$lambda$0(WitnessUrlListFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterPilpres(z);
    }

    public static final void setupBinding$lambda$1(WitnessUrlListFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterPilgub(z);
    }

    public static final void setupBinding$lambda$2(WitnessUrlListFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterPilwabup(z);
    }

    public static final void setupBinding$lambda$3(WitnessUrlListFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterDpr(z);
    }

    public static final void setupBinding$lambda$4(WitnessUrlListFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterDprdp(z);
    }

    public static final void setupBinding$lambda$5(WitnessUrlListFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterDprdk(z);
    }

    public static final void setupBinding$lambda$6(WitnessUrlListFragment this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().updateFilterDpd(z);
    }

    private final void setupViewModel() {
        getViewModel().getWitnesses().observe(getViewLifecycleOwner(), new WitnessUrlListFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends WitnessWithShare>, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$setupViewModel$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends WitnessWithShare> list) {
                invoke2((List<WitnessWithShare>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<WitnessWithShare> list) {
                WitnessUrlListAdapter witnessUrlListAdapter;
                if (list != null) {
                    witnessUrlListAdapter = WitnessUrlListFragment.this.adapter;
                    if (witnessUrlListAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        witnessUrlListAdapter = null;
                    }
                    witnessUrlListAdapter.submitList(list);
                }
            }
        }));
        getViewModel().getFilterJenisPemilihan().observe(getViewLifecycleOwner(), new WitnessUrlListFragment$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$setupViewModel$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                WitnessUrlListAdapter witnessUrlListAdapter;
                if (str != null) {
                    witnessUrlListAdapter = WitnessUrlListFragment.this.adapter;
                    if (witnessUrlListAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        witnessUrlListAdapter = null;
                    }
                    witnessUrlListAdapter.setFilterJenisPemilihan(str);
                }
            }
        }));
        getViewModel().getMarkWitnessResource().observe(getViewLifecycleOwner(), new WitnessUrlListFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends WitnessWithShare>, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$setupViewModel$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends WitnessWithShare> resource) {
                invoke2((Resource<WitnessWithShare>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<WitnessWithShare> resource) {
                Log.wtf("WitnessUrlListFragment", String.valueOf(resource));
            }
        }));
        getViewModel().isZipped().observe(getViewLifecycleOwner(), new WitnessUrlListFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$setupViewModel$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean it) {
                WitnessUrlListAdapter witnessUrlListAdapter;
                witnessUrlListAdapter = WitnessUrlListFragment.this.adapter;
                if (witnessUrlListAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    witnessUrlListAdapter = null;
                }
                Intrinsics.checkNotNullExpressionValue(it, "it");
                witnessUrlListAdapter.setZipped(it.booleanValue());
            }
        }));
        getViewModel().getElection().observe(getViewLifecycleOwner(), new WitnessUrlListFragment$sam$androidx_lifecycle_Observer$0(new Function1<ElectionWithRelation, Unit>() { // from class: org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment$setupViewModel$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ElectionWithRelation electionWithRelation) {
                invoke2(electionWithRelation);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(ElectionWithRelation electionWithRelation) {
                WitnessUrlListAdapter witnessUrlListAdapter;
                if (electionWithRelation != null) {
                    witnessUrlListAdapter = WitnessUrlListFragment.this.adapter;
                    if (witnessUrlListAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        witnessUrlListAdapter = null;
                    }
                    witnessUrlListAdapter.setPaslons(electionWithRelation.getCandidates());
                }
            }
        }));
    }

    public final void shareUrl(String str) {
        Snackbar snackBar = getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getString(R.string.share_url_witness_via_pps)));
    }

    public final void copyUrlToClipboard(String str) {
        Snackbar snackBar = getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        ClipboardManager clipboardManager = (ClipboardManager) requireContext().getSystemService("clipboard");
        ClipData newPlainText = ClipData.newPlainText(getString(R.string.url_saksi_pps), str);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(newPlainText);
        }
        String string = getString(R.string.url_copied);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.url_copied)");
        BaseFragment.showSnackBar$default(this, string, null, null, 6, null);
    }

    /* compiled from: WitnessUrlListFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lorg/informatika/sirekap/ui/witness/qrCode/WitnessUrlListFragment$Companion;", "", "()V", "TAG", "", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getTAG$annotations() {
        }

        private Companion() {
        }
    }
}
