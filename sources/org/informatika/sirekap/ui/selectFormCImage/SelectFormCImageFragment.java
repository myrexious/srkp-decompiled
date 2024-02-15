package org.informatika.sirekap.ui.selectFormCImage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.constraintlayout.widget.R;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.fragment.FragmentKt;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.apache.commons.lang3.StringUtils;
import org.informatika.sirekap.MainViewModel;
import org.informatika.sirekap.databinding.FragmentSelectFormCImageBinding;
import org.informatika.sirekap.model.Election;
import org.informatika.sirekap.model.ElectionPage;
import org.informatika.sirekap.model.ElectionWithRelation;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.support.Resource;
import org.informatika.sirekap.support.ResourceStatus;
import org.informatika.sirekap.usecase.GetLoggedInUserUseCase;

/* compiled from: SelectFormCImageFragment.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010!\u001a\u00020\u001fH\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lorg/informatika/sirekap/ui/selectFormCImage/SelectFormCImageFragment;", "Lorg/informatika/sirekap/ui/BaseFragment;", "()V", "args", "Lorg/informatika/sirekap/ui/selectFormCImage/SelectFormCImageFragmentArgs;", "getArgs", "()Lorg/informatika/sirekap/ui/selectFormCImage/SelectFormCImageFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lorg/informatika/sirekap/databinding/FragmentSelectFormCImageBinding;", "fragmentViewModel", "Lorg/informatika/sirekap/ui/selectFormCImage/SelectFormCImageViewModel;", "getFragmentViewModel", "()Lorg/informatika/sirekap/ui/selectFormCImage/SelectFormCImageViewModel;", "fragmentViewModel$delegate", "Lkotlin/Lazy;", "mainViewModel", "Lorg/informatika/sirekap/MainViewModel;", "getMainViewModel", "()Lorg/informatika/sirekap/MainViewModel;", "mainViewModel$delegate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setupBinding", "setupViewModel", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes4.dex */
public final class SelectFormCImageFragment extends Hilt_SelectFormCImageFragment {
    public static final Companion Companion = new Companion(null);
    public static final String SELECT_IMAGE_FRAGMENT_RESULT = "SELECT_IMAGE_FRAGMENT_RESULT";
    public static final String SELECT_IMAGE_FRAGMENT_RESULT_ELECTION_PAGE_ID = "election_page_id";
    private static final String TAG = "SelectFormCImageF";
    private final NavArgsLazy args$delegate;
    private FragmentSelectFormCImageBinding binding;
    private final Lazy fragmentViewModel$delegate;
    private final Lazy mainViewModel$delegate;

    public SelectFormCImageFragment() {
        final SelectFormCImageFragment selectFormCImageFragment = this;
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) Function0.this.invoke();
            }
        });
        this.fragmentViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(selectFormCImageFragment, Reflection.getOrCreateKotlinClass(SelectFormCImageViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$special$$inlined$viewModels$default$4
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$special$$inlined$viewModels$default$5
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
        this.mainViewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(selectFormCImageFragment, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = Fragment.this.requireActivity().getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$special$$inlined$activityViewModels$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                if (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) {
                    CreationExtras defaultViewModelCreationExtras = selectFormCImageFragment.requireActivity().getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "requireActivity().defaultViewModelCreationExtras");
                    return defaultViewModelCreationExtras;
                }
                return creationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$special$$inlined$activityViewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = Fragment.this.requireActivity().getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(SelectFormCImageFragmentArgs.class), new Function0<Bundle>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$special$$inlined$navArgs$1
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

    public final SelectFormCImageViewModel getFragmentViewModel() {
        return (SelectFormCImageViewModel) this.fragmentViewModel$delegate.getValue();
    }

    public final MainViewModel getMainViewModel() {
        return (MainViewModel) this.mainViewModel$delegate.getValue();
    }

    public final SelectFormCImageFragmentArgs getArgs() {
        return (SelectFormCImageFragmentArgs) this.args$delegate.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSelectFormCImageBinding inflate = FragmentSelectFormCImageBinding.inflate(inflater, viewGroup, false);
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
    }

    private final void setupBinding() {
        FragmentSelectFormCImageBinding fragmentSelectFormCImageBinding = this.binding;
        if (fragmentSelectFormCImageBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSelectFormCImageBinding = null;
        }
        fragmentSelectFormCImageBinding.setLifecycleOwner(getViewLifecycleOwner());
        fragmentSelectFormCImageBinding.setViewModel(getFragmentViewModel());
        fragmentSelectFormCImageBinding.c1ImageViewCard.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectFormCImageFragment.setupBinding$lambda$3$lambda$1(SelectFormCImageFragment.this, view);
            }
        });
        fragmentSelectFormCImageBinding.buttonContinue.setOnClickListener(new View.OnClickListener() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectFormCImageFragment.setupBinding$lambda$3$lambda$2(SelectFormCImageFragment.this, view);
            }
        });
    }

    public static final void setupBinding$lambda$3$lambda$1(SelectFormCImageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackBar = this$0.getSnackBar();
        if (snackBar != null) {
            snackBar.dismiss();
        }
        String value = this$0.getFragmentViewModel().getPreviewImagePath().getValue();
        if (value != null) {
            try {
                FragmentKt.findNavController(this$0).navigate(SelectFormCImageFragmentDirections.Companion.actionSelectFormCImageFragmentToPreviewImageFragment(value));
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public static final void setupBinding$lambda$3$lambda$2(SelectFormCImageFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual((Object) this$0.getFragmentViewModel().getCanSubmit().getValue(), (Object) true)) {
            Integer value = this$0.getFragmentViewModel().getSelectedJenisPemilihanId().getValue();
            Intrinsics.checkNotNull(value);
            int intValue = value.intValue();
            String value2 = this$0.getFragmentViewModel().getElectionPageId().getValue();
            Intrinsics.checkNotNull(value2);
            String str = value2;
            Log.wtf(TAG, intValue + StringUtils.SPACE + str);
            SelectFormCImageFragment selectFormCImageFragment = this$0;
            androidx.fragment.app.FragmentKt.setFragmentResult(selectFormCImageFragment, SELECT_IMAGE_FRAGMENT_RESULT, BundleKt.bundleOf(TuplesKt.to("election_page_id", str)));
            FragmentKt.findNavController(selectFormCImageFragment).navigateUp();
        }
    }

    private final void setupViewModel() {
        GetLoggedInUserUseCase getLoggedInUserUseCase = getMainViewModel().getGetLoggedInUserUseCase();
        getLoggedInUserUseCase.getLoggedInUserResource().observe(getViewLifecycleOwner(), new SelectFormCImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<Resource<? extends User>, Unit>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$setupViewModel$1$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Resource<? extends User> resource) {
                invoke2((Resource<User>) resource);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Resource<User> resource) {
                MainViewModel mainViewModel;
                SelectFormCImageViewModel fragmentViewModel;
                SelectFormCImageFragmentArgs args;
                if (resource != null) {
                    SelectFormCImageFragment selectFormCImageFragment = SelectFormCImageFragment.this;
                    if (resource.getSuccess() == ResourceStatus.SUCCESS) {
                        if (resource.getPayload() != null) {
                            fragmentViewModel = selectFormCImageFragment.getFragmentViewModel();
                            String kodeTps = resource.getPayload().getTps().getKodeTps();
                            args = selectFormCImageFragment.getArgs();
                            fragmentViewModel.setup(kodeTps, args.getImagePath());
                            return;
                        }
                        mainViewModel = selectFormCImageFragment.getMainViewModel();
                        mainViewModel.logout();
                    }
                }
            }
        }));
        getLoggedInUserUseCase.getLoggedInUser().observe(getViewLifecycleOwner(), new SelectFormCImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<User, Unit>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$setupViewModel$1$1$2
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(User user) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(User user) {
                invoke2(user);
                return Unit.INSTANCE;
            }
        }));
        SelectFormCImageViewModel fragmentViewModel = getFragmentViewModel();
        fragmentViewModel.getGetListElectionUseCase().getElections().observe(getViewLifecycleOwner(), new SelectFormCImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends ElectionWithRelation>, Unit>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$setupViewModel$2$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends ElectionWithRelation> list) {
                invoke2((List<ElectionWithRelation>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(List<ElectionWithRelation> list) {
                FragmentSelectFormCImageBinding fragmentSelectFormCImageBinding;
                fragmentSelectFormCImageBinding = SelectFormCImageFragment.this.binding;
                if (fragmentSelectFormCImageBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentSelectFormCImageBinding = null;
                }
                RadioGroup radioGroup = fragmentSelectFormCImageBinding.radioGroup;
                Intrinsics.checkNotNullExpressionValue(radioGroup, "binding.radioGroup");
                radioGroup.removeAllViews();
                if (list != null) {
                    SelectFormCImageFragment selectFormCImageFragment = SelectFormCImageFragment.this;
                    int i = 0;
                    for (Object obj : list) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        ElectionWithRelation electionWithRelation = (ElectionWithRelation) obj;
                        RadioButton radioButton = new RadioButton(selectFormCImageFragment.requireContext());
                        Election election = electionWithRelation.getElection();
                        Context requireContext = selectFormCImageFragment.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        radioButton.setText(election.getDescription(requireContext));
                        Integer num = SelectFormCImageViewModelKt.getJenisPemilihanIdMap().get(electionWithRelation.getElection().getPemilihan());
                        radioButton.setId(num != null ? num.intValue() : -1);
                        radioButton.setEnabled(!electionWithRelation.getElection().isZipped());
                        radioGroup.addView(radioButton);
                        i = i2;
                    }
                }
            }
        }));
        fragmentViewModel.getSelectedElection().observe(getViewLifecycleOwner(), new SelectFormCImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<ElectionWithRelation, Unit>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$setupViewModel$2$2
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
                SelectFormCImageViewModel fragmentViewModel2;
                if (electionWithRelation != null) {
                    fragmentViewModel2 = SelectFormCImageFragment.this.getFragmentViewModel();
                    fragmentViewModel2.getSelectedPage().setValue("");
                }
            }
        }));
        fragmentViewModel.getSelectedElection().observe(getViewLifecycleOwner(), new SelectFormCImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<ElectionWithRelation, Unit>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$setupViewModel$2$3
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
                FragmentSelectFormCImageBinding fragmentSelectFormCImageBinding;
                MaterialAutoCompleteTextView materialAutoCompleteTextView;
                FragmentSelectFormCImageBinding fragmentSelectFormCImageBinding2;
                SelectFormCImageViewModel fragmentViewModel2;
                String str;
                SelectFormCImageFragment selectFormCImageFragment = SelectFormCImageFragment.this;
                if (electionWithRelation != null) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    ArrayList<ElectionPage> arrayList = new ArrayList();
                    for (Object obj : electionWithRelation.getPages()) {
                        if (!((ElectionPage) obj).isPhotoTaken()) {
                            arrayList.add(obj);
                        }
                    }
                    for (ElectionPage electionPage : arrayList) {
                        linkedHashMap.put("Halaman " + electionPage.getPageNumber(electionWithRelation.getElection().getJmlLembar()), electionPage.getId());
                    }
                    ArrayList<ElectionPage> arrayList2 = new ArrayList();
                    for (Object obj2 : electionWithRelation.getPages()) {
                        if (((ElectionPage) obj2).isPhotoTaken()) {
                            arrayList2.add(obj2);
                        }
                    }
                    for (ElectionPage electionPage2 : arrayList2) {
                        String str2 = "Halaman " + electionPage2.getPageNumber(electionWithRelation.getElection().getJmlLembar());
                        if (electionPage2.isVerified()) {
                            str = "(Sudah Diverifikasi)";
                        } else if (electionPage2.isPhotoSent()) {
                            str = "(Pernah Dikirim)";
                        } else {
                            str = electionPage2.isPhotoTaken() ? "(Pernah Difoto)" : null;
                        }
                        if (str != null) {
                            str2 = str + StringUtils.SPACE + str2;
                        }
                        linkedHashMap.put(str2, electionPage2.getId());
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(selectFormCImageFragment.requireContext(), R.layout.support_simple_spinner_dropdown_item, CollectionsKt.toList(linkedHashMap.keySet()));
                    fragmentSelectFormCImageBinding2 = selectFormCImageFragment.binding;
                    if (fragmentSelectFormCImageBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentSelectFormCImageBinding2 = null;
                    }
                    MaterialAutoCompleteTextView materialAutoCompleteTextView2 = fragmentSelectFormCImageBinding2.inputFieldPage;
                    materialAutoCompleteTextView = materialAutoCompleteTextView2 instanceof AutoCompleteTextView ? materialAutoCompleteTextView2 : null;
                    if (materialAutoCompleteTextView != null) {
                        materialAutoCompleteTextView.setAdapter(arrayAdapter);
                    }
                    fragmentViewModel2 = selectFormCImageFragment.getFragmentViewModel();
                    fragmentViewModel2.updateOptionsMapping(linkedHashMap);
                    return;
                }
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(selectFormCImageFragment.requireContext(), R.layout.support_simple_spinner_dropdown_item, CollectionsKt.emptyList());
                fragmentSelectFormCImageBinding = selectFormCImageFragment.binding;
                if (fragmentSelectFormCImageBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentSelectFormCImageBinding = null;
                }
                MaterialAutoCompleteTextView materialAutoCompleteTextView3 = fragmentSelectFormCImageBinding.inputFieldPage;
                materialAutoCompleteTextView = materialAutoCompleteTextView3 instanceof AutoCompleteTextView ? materialAutoCompleteTextView3 : null;
                if (materialAutoCompleteTextView != null) {
                    materialAutoCompleteTextView.setAdapter(arrayAdapter2);
                }
            }
        }));
        fragmentViewModel.getElectionPageId().observe(getViewLifecycleOwner(), new SelectFormCImageFragment$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment$setupViewModel$2$4
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }
        }));
    }

    /* compiled from: SelectFormCImageFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/ui/selectFormCImage/SelectFormCImageFragment$Companion;", "", "()V", SelectFormCImageFragment.SELECT_IMAGE_FRAGMENT_RESULT, "", "SELECT_IMAGE_FRAGMENT_RESULT_ELECTION_PAGE_ID", "TAG", "getTAG$annotations", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
