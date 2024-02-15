package org.informatika.sirekap;

import android.app.Activity;
import android.app.Service;
import android.content.SharedPreferences;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
import org.informatika.sirekap.SirekapApplication_HiltComponents;
import org.informatika.sirekap.api.CertmanAPIInterface;
import org.informatika.sirekap.api.CertmanCertificateBucketAPIInterface;
import org.informatika.sirekap.api.KeyCloakApiInterface;
import org.informatika.sirekap.api.PKIApiInterface;
import org.informatika.sirekap.api.SirekapApiInterface;
import org.informatika.sirekap.api.SirekapApiInterfaceUpload;
import org.informatika.sirekap.db.AppDatabase;
import org.informatika.sirekap.di.ApiModule;
import org.informatika.sirekap.di.ApiModule_ProvidesAppServiceFactory;
import org.informatika.sirekap.di.ApiModule_ProvidesAppServiceUploadFactory;
import org.informatika.sirekap.di.ApiModule_ProvidesBucketCertificateApiInterfaceFactory;
import org.informatika.sirekap.di.ApiModule_ProvidesCertmanAPIInterfaceFactory;
import org.informatika.sirekap.di.ApiModule_ProvidesKeyCloakApiFactory;
import org.informatika.sirekap.di.ApiModule_ProvidesOkHttpAuthInterceptorFactory;
import org.informatika.sirekap.di.ApiModule_ProvidesOkHttpLoggingInterceptorFactory;
import org.informatika.sirekap.di.DatabaseModule;
import org.informatika.sirekap.di.DatabaseModule_ProvideRoomDatabaseFactory;
import org.informatika.sirekap.di.PKIModule;
import org.informatika.sirekap.di.PKIModule_ProtectedSirekapPKIFactory;
import org.informatika.sirekap.di.PKIModule_ProvideBsreTimestampAuthorityFactoryFactory;
import org.informatika.sirekap.di.PKIModule_ProvideCRLUtilityFactory;
import org.informatika.sirekap.di.PKIModule_ProvideOCSPUtilityFactory;
import org.informatika.sirekap.di.PKIModule_ProvidePublicRepositoryFactory;
import org.informatika.sirekap.di.PKIModule_ProvideSirekapProtectedRepositoryFactory;
import org.informatika.sirekap.di.PKIModule_ProvidesPublicPKIFactory;
import org.informatika.sirekap.di.PdfModule;
import org.informatika.sirekap.di.PdfModule_ProvidesPdfLtvFactoryFactory;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.di.SharedPreferencesModule_ProvideSharedPreferencesFactory;
import org.informatika.sirekap.repository.AuthRepository;
import org.informatika.sirekap.repository.BackgroundProcessRepository;
import org.informatika.sirekap.repository.CertmanRepository;
import org.informatika.sirekap.repository.DefaultAuthRepository;
import org.informatika.sirekap.repository.DefaultBackgroundProcessRepository;
import org.informatika.sirekap.repository.DefaultCertmanRepository;
import org.informatika.sirekap.repository.DefaultElectionRepository;
import org.informatika.sirekap.repository.DefaultFormC1Repository;
import org.informatika.sirekap.repository.DefaultSpecialOccurrenceRepository;
import org.informatika.sirekap.repository.DefaultTpsTimeRepository;
import org.informatika.sirekap.repository.DefaultUploadFileAttemptRepository;
import org.informatika.sirekap.repository.DefaultUploadImageAttemptRepository;
import org.informatika.sirekap.repository.DefaultUserRepository;
import org.informatika.sirekap.repository.DefaultWitnessRepository;
import org.informatika.sirekap.repository.FormC1Repository;
import org.informatika.sirekap.repository.PKIRepository;
import org.informatika.sirekap.repository.SpecialOccurrenceRepository;
import org.informatika.sirekap.repository.TpsTimeRepository;
import org.informatika.sirekap.repository.UploadFileAttemptRepository;
import org.informatika.sirekap.repository.UploadImageAttemptRepository;
import org.informatika.sirekap.repository.UserRepository;
import org.informatika.sirekap.repository.fake.FakeElectionRepository;
import org.informatika.sirekap.support.AppExecutors;
import org.informatika.sirekap.support.security.pdf.PdfLtv;
import org.informatika.sirekap.support.security.pki.CRLUtility;
import org.informatika.sirekap.support.security.pki.OCSPUtility;
import org.informatika.sirekap.support.security.tsa.BasicTimestampAuthority;
import org.informatika.sirekap.support.worker.login.DefaultLoginTask;
import org.informatika.sirekap.support.worker.login.LoginTask;
import org.informatika.sirekap.support.worker.refreshToken.DefaultRefreshTokenTask;
import org.informatika.sirekap.support.worker.refreshToken.RefreshTokenTask;
import org.informatika.sirekap.support.worker.registerWitness.DefaultRegisterWitnessTask;
import org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessTask;
import org.informatika.sirekap.support.worker.security.CertificateCheckerTask;
import org.informatika.sirekap.support.worker.security.DefaultCertificateCheckerTask;
import org.informatika.sirekap.support.worker.uploadAttendancePdf.DefaultUploadAttendanceTask;
import org.informatika.sirekap.support.worker.uploadAttendancePdf.UploadAttendancePdfTask;
import org.informatika.sirekap.support.worker.uploadFormCImage.DefaultUploadFormCImageTask;
import org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageTask;
import org.informatika.sirekap.support.worker.uploadFormCImageRekap.DefaultUploadFormCImageRekapTask;
import org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapTask;
import org.informatika.sirekap.support.worker.uploadSpecialOccurrence.DefaultUploadSpecialOccurrenceTask;
import org.informatika.sirekap.support.worker.uploadSpecialOccurrence.UploadSpecialOccurrenceTask;
import org.informatika.sirekap.support.worker.zipAttendance.DefaultZipAttendanceTask;
import org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceTask;
import org.informatika.sirekap.support.worker.zipElection.DefaultZipElectionTask;
import org.informatika.sirekap.support.worker.zipElection.ZipElectionTask;
import org.informatika.sirekap.support.worker.zipSpecialOccurrence.DefaultZipSpecialOccurrenceTask;
import org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceTask;
import org.informatika.sirekap.ui.BaseActivity;
import org.informatika.sirekap.ui.DeviceInitializationActivity;
import org.informatika.sirekap.ui.DeviceInitializationViewModel;
import org.informatika.sirekap.ui.DeviceInitializationViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment;
import org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictViewModel;
import org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.autocapture.AutoCaptureFragment;
import org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel;
import org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment;
import org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageViewModel;
import org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.dashboard.DashboardFragment;
import org.informatika.sirekap.ui.dashboard.DashboardViewModel;
import org.informatika.sirekap.ui.dashboard.DashboardViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment;
import org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel;
import org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel;
import org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.login.LoginFragment;
import org.informatika.sirekap.ui.login.LoginViewModel;
import org.informatika.sirekap.ui.login.LoginViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.loginTps.LoginTpsFragment;
import org.informatika.sirekap.ui.loginTps.LoginTpsViewModel;
import org.informatika.sirekap.ui.loginTps.LoginTpsViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.previewImage.PreviewImageViewModel;
import org.informatika.sirekap.ui.previewImage.PreviewImageViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.security.CertificateFragment;
import org.informatika.sirekap.ui.security.CertificateViewModel;
import org.informatika.sirekap.ui.security.CertificateViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment;
import org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel;
import org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.sendImage.SendImageFragment;
import org.informatika.sirekap.ui.sendImage.SendImageViewModel;
import org.informatika.sirekap.ui.sendImage.SendImageViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.settings.SettingsFragment;
import org.informatika.sirekap.ui.settings.SettingsViewModel;
import org.informatika.sirekap.ui.settings.SettingsViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.tpsTime.TpsTimeFragment;
import org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel;
import org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment;
import org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel;
import org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2Fragment;
import org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel;
import org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment;
import org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel;
import org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationFragment;
import org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel;
import org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment;
import org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel;
import org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment;
import org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel;
import org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.witness.WitnessFragment;
import org.informatika.sirekap.ui.witness.WitnessViewModel;
import org.informatika.sirekap.ui.witness.WitnessViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListFragment;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment;
import org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel;
import org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment_MembersInjector;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel_HiltModules_KeyModule_ProvideFactory;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel_HiltModules_KeyModule_ProvideFactory;

/* loaded from: classes2.dex */
public final class DaggerSirekapApplication_HiltComponents_SingletonC {
    private DaggerSirekapApplication_HiltComponents_SingletonC() {
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private ApiModule apiModule;
        private ApplicationContextModule applicationContextModule;
        private PKIModule pKIModule;
        private PdfModule pdfModule;

        private Builder() {
        }

        public Builder apiModule(ApiModule apiModule) {
            this.apiModule = (ApiModule) Preconditions.checkNotNull(apiModule);
            return this;
        }

        public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
            this.applicationContextModule = (ApplicationContextModule) Preconditions.checkNotNull(applicationContextModule);
            return this;
        }

        @Deprecated
        public Builder databaseModule(DatabaseModule databaseModule) {
            Preconditions.checkNotNull(databaseModule);
            return this;
        }

        @Deprecated
        public Builder hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule(HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule) {
            Preconditions.checkNotNull(hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule);
            return this;
        }

        public Builder pKIModule(PKIModule pKIModule) {
            this.pKIModule = (PKIModule) Preconditions.checkNotNull(pKIModule);
            return this;
        }

        public Builder pdfModule(PdfModule pdfModule) {
            this.pdfModule = (PdfModule) Preconditions.checkNotNull(pdfModule);
            return this;
        }

        @Deprecated
        public Builder sharedPreferencesModule(SharedPreferencesModule sharedPreferencesModule) {
            Preconditions.checkNotNull(sharedPreferencesModule);
            return this;
        }

        public SirekapApplication_HiltComponents.SingletonC build() {
            if (this.apiModule == null) {
                this.apiModule = new ApiModule();
            }
            Preconditions.checkBuilderRequirement(this.applicationContextModule, ApplicationContextModule.class);
            if (this.pKIModule == null) {
                this.pKIModule = new PKIModule();
            }
            if (this.pdfModule == null) {
                this.pdfModule = new PdfModule();
            }
            return new SingletonCImpl(this.apiModule, this.applicationContextModule, this.pKIModule, this.pdfModule);
        }
    }

    /* loaded from: classes2.dex */
    private static final class ActivityRetainedCBuilder implements SirekapApplication_HiltComponents.ActivityRetainedC.Builder {
        private final SingletonCImpl singletonCImpl;

        private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
            this.singletonCImpl = singletonCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder
        public SirekapApplication_HiltComponents.ActivityRetainedC build() {
            return new ActivityRetainedCImpl(this.singletonCImpl);
        }
    }

    /* loaded from: classes2.dex */
    private static final class ActivityCBuilder implements SirekapApplication_HiltComponents.ActivityC.Builder {
        private Activity activity;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;

        private ActivityCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityComponentBuilder
        public ActivityCBuilder activity(Activity activity) {
            this.activity = (Activity) Preconditions.checkNotNull(activity);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityComponentBuilder
        public SirekapApplication_HiltComponents.ActivityC build() {
            Preconditions.checkBuilderRequirement(this.activity, Activity.class);
            return new ActivityCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activity);
        }
    }

    /* loaded from: classes2.dex */
    private static final class FragmentCBuilder implements SirekapApplication_HiltComponents.FragmentC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private Fragment fragment;
        private final SingletonCImpl singletonCImpl;

        private FragmentCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.FragmentComponentBuilder
        public FragmentCBuilder fragment(Fragment fragment) {
            this.fragment = (Fragment) Preconditions.checkNotNull(fragment);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.FragmentComponentBuilder
        public SirekapApplication_HiltComponents.FragmentC build() {
            Preconditions.checkBuilderRequirement(this.fragment, Fragment.class);
            return new FragmentCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragment);
        }
    }

    /* loaded from: classes2.dex */
    private static final class ViewWithFragmentCBuilder implements SirekapApplication_HiltComponents.ViewWithFragmentC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final SingletonCImpl singletonCImpl;
        private View view;

        private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, FragmentCImpl fragmentCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
            this.fragmentCImpl = fragmentCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder
        public ViewWithFragmentCBuilder view(View view) {
            this.view = (View) Preconditions.checkNotNull(view);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder
        public SirekapApplication_HiltComponents.ViewWithFragmentC build() {
            Preconditions.checkBuilderRequirement(this.view, View.class);
            return new ViewWithFragmentCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragmentCImpl, this.view);
        }
    }

    /* loaded from: classes2.dex */
    private static final class ViewCBuilder implements SirekapApplication_HiltComponents.ViewC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;
        private View view;

        private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewComponentBuilder
        public ViewCBuilder view(View view) {
            this.view = (View) Preconditions.checkNotNull(view);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewComponentBuilder
        public SirekapApplication_HiltComponents.ViewC build() {
            Preconditions.checkBuilderRequirement(this.view, View.class);
            return new ViewCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.view);
        }
    }

    /* loaded from: classes2.dex */
    public static final class ViewModelCBuilder implements SirekapApplication_HiltComponents.ViewModelC.Builder {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private SavedStateHandle savedStateHandle;
        private final SingletonCImpl singletonCImpl;
        private ViewModelLifecycle viewModelLifecycle;

        private ViewModelCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
            this.savedStateHandle = (SavedStateHandle) Preconditions.checkNotNull(handle);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
            this.viewModelLifecycle = (ViewModelLifecycle) Preconditions.checkNotNull(viewModelLifecycle);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public SirekapApplication_HiltComponents.ViewModelC build() {
            Preconditions.checkBuilderRequirement(this.savedStateHandle, SavedStateHandle.class);
            Preconditions.checkBuilderRequirement(this.viewModelLifecycle, ViewModelLifecycle.class);
            return new ViewModelCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.savedStateHandle, this.viewModelLifecycle);
        }
    }

    /* loaded from: classes2.dex */
    private static final class ServiceCBuilder implements SirekapApplication_HiltComponents.ServiceC.Builder {
        private Service service;
        private final SingletonCImpl singletonCImpl;

        private ServiceCBuilder(SingletonCImpl singletonCImpl) {
            this.singletonCImpl = singletonCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ServiceComponentBuilder
        public ServiceCBuilder service(Service service) {
            this.service = (Service) Preconditions.checkNotNull(service);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ServiceComponentBuilder
        public SirekapApplication_HiltComponents.ServiceC build() {
            Preconditions.checkBuilderRequirement(this.service, Service.class);
            return new ServiceCImpl(this.singletonCImpl, this.service);
        }
    }

    /* loaded from: classes2.dex */
    public static final class ViewWithFragmentCImpl extends SirekapApplication_HiltComponents.ViewWithFragmentC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final SingletonCImpl singletonCImpl;
        private final ViewWithFragmentCImpl viewWithFragmentCImpl;

        private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, FragmentCImpl fragmentCImpl, View viewParam) {
            this.viewWithFragmentCImpl = this;
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
            this.fragmentCImpl = fragmentCImpl;
        }
    }

    /* loaded from: classes2.dex */
    public static final class FragmentCImpl extends SirekapApplication_HiltComponents.FragmentC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final SingletonCImpl singletonCImpl;

        @Override // org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment_GeneratedInjector
        public void injectAprilTagConflictFragment(AprilTagConflictFragment aprilTagConflictFragment) {
        }

        @Override // org.informatika.sirekap.ui.autocapture.AutoCaptureFragment_GeneratedInjector
        public void injectAutoCaptureFragment(AutoCaptureFragment autoCaptureFragment) {
        }

        @Override // org.informatika.sirekap.ui.security.CertificateFragment_GeneratedInjector
        public void injectCertificateFragment(CertificateFragment certificateFragment) {
        }

        @Override // org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment_GeneratedInjector
        public void injectConfirmSaveFormCImageFragment(ConfirmSaveFormCImageFragment confirmSaveFormCImageFragment) {
        }

        @Override // org.informatika.sirekap.ui.dashboard.DashboardFragment_GeneratedInjector
        public void injectDashboardFragment(DashboardFragment dashboardFragment) {
        }

        @Override // org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment_GeneratedInjector
        public void injectElectionDetailFragment(ElectionDetailFragment electionDetailFragment) {
        }

        @Override // org.informatika.sirekap.ui.login.LoginFragment_GeneratedInjector
        public void injectLoginFragment(LoginFragment loginFragment) {
        }

        @Override // org.informatika.sirekap.ui.loginTps.LoginTpsFragment_GeneratedInjector
        public void injectLoginTpsFragment(LoginTpsFragment loginTpsFragment) {
        }

        @Override // org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment_GeneratedInjector
        public void injectSelectFormCImageFragment(SelectFormCImageFragment selectFormCImageFragment) {
        }

        @Override // org.informatika.sirekap.ui.sendImage.SendImageFragment_GeneratedInjector
        public void injectSendImageFragment(SendImageFragment sendImageFragment) {
        }

        @Override // org.informatika.sirekap.ui.settings.SettingsFragment_GeneratedInjector
        public void injectSettingsFragment(SettingsFragment settingsFragment) {
        }

        @Override // org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment_GeneratedInjector
        public void injectSpecialOccurrenceFragment(SpecialOccurrenceFragment specialOccurrenceFragment) {
        }

        @Override // org.informatika.sirekap.ui.tpsTime.TpsTimeFragment_GeneratedInjector
        public void injectTpsTimeFragment(TpsTimeFragment tpsTimeFragment) {
        }

        @Override // org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment_GeneratedInjector
        public void injectVerifyAdministrationFragment(VerifyAdministrationFragment verifyAdministrationFragment) {
        }

        @Override // org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2Fragment_GeneratedInjector
        public void injectVerifyAdministrationHal2Fragment(VerifyAdministrationHal2Fragment verifyAdministrationHal2Fragment) {
        }

        @Override // org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment_GeneratedInjector
        public void injectVerifyAdministrationHal2PpwpFragment(VerifyAdministrationHal2PpwpFragment verifyAdministrationHal2PpwpFragment) {
        }

        @Override // org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment_GeneratedInjector
        public void injectVerifySpecialOccurrenceFragment(VerifySpecialOccurrenceFragment verifySpecialOccurrenceFragment) {
        }

        @Override // org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationFragment_GeneratedInjector
        public void injectVerifyTabulationFragment(VerifyTabulationFragment verifyTabulationFragment) {
        }

        @Override // org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment_GeneratedInjector
        public void injectVerifyTabulationPartaiFragment(VerifyTabulationPartaiFragment verifyTabulationPartaiFragment) {
        }

        @Override // org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListFragment_GeneratedInjector
        public void injectVerifyWitnessAttendanceListFragment(VerifyWitnessAttendanceListFragment verifyWitnessAttendanceListFragment) {
        }

        @Override // org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment_GeneratedInjector
        public void injectVerifyWizardFragment(VerifyWizardFragment verifyWizardFragment) {
        }

        @Override // org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment_GeneratedInjector
        public void injectWitnessAttendanceListFragment(WitnessAttendanceListFragment witnessAttendanceListFragment) {
        }

        @Override // org.informatika.sirekap.ui.witness.WitnessFragment_GeneratedInjector
        public void injectWitnessFragment(WitnessFragment witnessFragment) {
        }

        @Override // org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment_GeneratedInjector
        public void injectWitnessQrCodeFragment(WitnessQrCodeFragment witnessQrCodeFragment) {
        }

        @Override // org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment_GeneratedInjector
        public void injectWitnessRegisterFragment(WitnessRegisterFragment witnessRegisterFragment) {
        }

        private FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, Fragment fragmentParam) {
            this.fragmentCImpl = this;
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories.FragmentEntryPoint
        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
            return this.activityCImpl.getHiltInternalFactoryFactory();
        }

        @Override // dagger.hilt.android.internal.managers.ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint
        public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
            return new ViewWithFragmentCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragmentCImpl);
        }

        @Override // org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment_GeneratedInjector
        public void injectWitnessUrlListFragment(WitnessUrlListFragment witnessUrlListFragment) {
            injectWitnessUrlListFragment2(witnessUrlListFragment);
        }

        private WitnessUrlListFragment injectWitnessUrlListFragment2(WitnessUrlListFragment instance) {
            WitnessUrlListFragment_MembersInjector.injectEncryptedSharedPreferences(instance, this.singletonCImpl.encryptedSharedPreferences());
            return instance;
        }
    }

    /* loaded from: classes2.dex */
    public static final class ViewCImpl extends SirekapApplication_HiltComponents.ViewC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;
        private final ViewCImpl viewCImpl;

        private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, View viewParam) {
            this.viewCImpl = this;
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }
    }

    /* loaded from: classes2.dex */
    public static final class ActivityCImpl extends SirekapApplication_HiltComponents.ActivityC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;

        @Override // org.informatika.sirekap.ui.BaseActivity_GeneratedInjector
        public void injectBaseActivity(BaseActivity baseActivity) {
        }

        @Override // org.informatika.sirekap.ui.DeviceInitializationActivity_GeneratedInjector
        public void injectDeviceInitializationActivity(DeviceInitializationActivity deviceInitializationActivity) {
        }

        @Override // org.informatika.sirekap.MainActivity_GeneratedInjector
        public void injectMainActivity(MainActivity mainActivity) {
        }

        private ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
            this.activityCImpl = this;
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories.ActivityEntryPoint
        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
            return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(this.singletonCImpl, this.activityRetainedCImpl));
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ActivityCreatorEntryPoint
        public Set<String> getViewModelKeys() {
            return SetBuilder.newSetBuilder(30).add(AprilTagConflictViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AutoCaptureViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(CertificateViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ConfirmSaveFormCImageViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(DashboardViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(DeviceInitializationViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ElectionDetailViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ImageHistoryViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(LoginTpsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(LoginViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(MainViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(PreviewImageViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SelectFormCImageViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SendImageViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SettingsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(TpsTimeViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VerifyAdministrationHal2PpwpViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VerifyAdministrationHal2ViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VerifyAdministrationViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VerifySpecialOccurrenceViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VerifyTabulationPartaiViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VerifyTabulationViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VerifyWitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VerifyWizardViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(WitnessAttendanceListViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(WitnessQrCodeViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(WitnessRegisterViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(WitnessUrlListViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(WitnessViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ActivityCreatorEntryPoint
        public ViewModelComponentBuilder getViewModelComponentBuilder() {
            return new ViewModelCBuilder(this.singletonCImpl, this.activityRetainedCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.FragmentComponentManager.FragmentComponentBuilderEntryPoint
        public FragmentComponentBuilder fragmentComponentBuilder() {
            return new FragmentCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.ViewComponentManager.ViewComponentBuilderEntryPoint
        public ViewComponentBuilder viewComponentBuilder() {
            return new ViewCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl);
        }
    }

    /* loaded from: classes2.dex */
    public static final class ViewModelCImpl extends SirekapApplication_HiltComponents.ViewModelC {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private Provider<AprilTagConflictViewModel> aprilTagConflictViewModelProvider;
        private Provider<AutoCaptureViewModel> autoCaptureViewModelProvider;
        private Provider<CertificateViewModel> certificateViewModelProvider;
        private Provider<ConfirmSaveFormCImageViewModel> confirmSaveFormCImageViewModelProvider;
        private Provider<DashboardViewModel> dashboardViewModelProvider;
        private Provider<DeviceInitializationViewModel> deviceInitializationViewModelProvider;
        private Provider<ElectionDetailViewModel> electionDetailViewModelProvider;
        private Provider<ImageHistoryViewModel> imageHistoryViewModelProvider;
        private Provider<LoginTpsViewModel> loginTpsViewModelProvider;
        private Provider<LoginViewModel> loginViewModelProvider;
        private Provider<MainViewModel> mainViewModelProvider;
        private Provider<PreviewImageViewModel> previewImageViewModelProvider;
        private Provider<SelectFormCImageViewModel> selectFormCImageViewModelProvider;
        private Provider<SendImageViewModel> sendImageViewModelProvider;
        private Provider<SettingsViewModel> settingsViewModelProvider;
        private final SingletonCImpl singletonCImpl;
        private Provider<SpecialOccurrenceViewModel> specialOccurrenceViewModelProvider;
        private Provider<TpsTimeViewModel> tpsTimeViewModelProvider;
        private Provider<VerifyAdministrationHal2PpwpViewModel> verifyAdministrationHal2PpwpViewModelProvider;
        private Provider<VerifyAdministrationHal2ViewModel> verifyAdministrationHal2ViewModelProvider;
        private Provider<VerifyAdministrationViewModel> verifyAdministrationViewModelProvider;
        private Provider<VerifySpecialOccurrenceViewModel> verifySpecialOccurrenceViewModelProvider;
        private Provider<VerifyTabulationPartaiViewModel> verifyTabulationPartaiViewModelProvider;
        private Provider<VerifyTabulationViewModel> verifyTabulationViewModelProvider;
        private Provider<VerifyWitnessAttendanceListViewModel> verifyWitnessAttendanceListViewModelProvider;
        private Provider<VerifyWizardViewModel> verifyWizardViewModelProvider;
        private final ViewModelCImpl viewModelCImpl;
        private Provider<WitnessAttendanceListViewModel> witnessAttendanceListViewModelProvider;
        private Provider<WitnessQrCodeViewModel> witnessQrCodeViewModelProvider;
        private Provider<WitnessRegisterViewModel> witnessRegisterViewModelProvider;
        private Provider<WitnessUrlListViewModel> witnessUrlListViewModelProvider;
        private Provider<WitnessViewModel> witnessViewModelProvider;

        private ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
            this.viewModelCImpl = this;
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            initialize(savedStateHandleParam, viewModelLifecycleParam);
        }

        private void initialize(final SavedStateHandle savedStateHandleParam, final ViewModelLifecycle viewModelLifecycleParam) {
            this.aprilTagConflictViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 0);
            this.autoCaptureViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 1);
            this.certificateViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 2);
            this.confirmSaveFormCImageViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 3);
            this.dashboardViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 4);
            this.deviceInitializationViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 5);
            this.electionDetailViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 6);
            this.imageHistoryViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 7);
            this.loginTpsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 8);
            this.loginViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 9);
            this.mainViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 10);
            this.previewImageViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 11);
            this.selectFormCImageViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 12);
            this.sendImageViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 13);
            this.settingsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 14);
            this.specialOccurrenceViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 15);
            this.tpsTimeViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 16);
            this.verifyAdministrationHal2PpwpViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 17);
            this.verifyAdministrationHal2ViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 18);
            this.verifyAdministrationViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 19);
            this.verifySpecialOccurrenceViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 20);
            this.verifyTabulationPartaiViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 21);
            this.verifyTabulationViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 22);
            this.verifyWitnessAttendanceListViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 23);
            this.verifyWizardViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 24);
            this.witnessAttendanceListViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 25);
            this.witnessQrCodeViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 26);
            this.witnessRegisterViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 27);
            this.witnessUrlListViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 28);
            this.witnessViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 29);
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ViewModelFactoriesEntryPoint
        public Map<String, Provider<ViewModel>> getHiltViewModelMap() {
            return MapBuilder.newMapBuilder(30).put("org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictViewModel", this.aprilTagConflictViewModelProvider).put("org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel", this.autoCaptureViewModelProvider).put("org.informatika.sirekap.ui.security.CertificateViewModel", this.certificateViewModelProvider).put("org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageViewModel", this.confirmSaveFormCImageViewModelProvider).put("org.informatika.sirekap.ui.dashboard.DashboardViewModel", this.dashboardViewModelProvider).put("org.informatika.sirekap.ui.DeviceInitializationViewModel", this.deviceInitializationViewModelProvider).put("org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel", this.electionDetailViewModelProvider).put("org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel", this.imageHistoryViewModelProvider).put("org.informatika.sirekap.ui.loginTps.LoginTpsViewModel", this.loginTpsViewModelProvider).put("org.informatika.sirekap.ui.login.LoginViewModel", this.loginViewModelProvider).put("org.informatika.sirekap.MainViewModel", this.mainViewModelProvider).put("org.informatika.sirekap.ui.previewImage.PreviewImageViewModel", this.previewImageViewModelProvider).put("org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel", this.selectFormCImageViewModelProvider).put("org.informatika.sirekap.ui.sendImage.SendImageViewModel", this.sendImageViewModelProvider).put("org.informatika.sirekap.ui.settings.SettingsViewModel", this.settingsViewModelProvider).put("org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel", this.specialOccurrenceViewModelProvider).put("org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel", this.tpsTimeViewModelProvider).put("org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel", this.verifyAdministrationHal2PpwpViewModelProvider).put("org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel", this.verifyAdministrationHal2ViewModelProvider).put("org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel", this.verifyAdministrationViewModelProvider).put("org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel", this.verifySpecialOccurrenceViewModelProvider).put("org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel", this.verifyTabulationPartaiViewModelProvider).put("org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel", this.verifyTabulationViewModelProvider).put("org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel", this.verifyWitnessAttendanceListViewModelProvider).put("org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel", this.verifyWizardViewModelProvider).put("org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel", this.witnessAttendanceListViewModelProvider).put("org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel", this.witnessQrCodeViewModelProvider).put("org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel", this.witnessRegisterViewModelProvider).put("org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel", this.witnessUrlListViewModelProvider).put("org.informatika.sirekap.ui.witness.WitnessViewModel", this.witnessViewModelProvider).build();
        }

        /* loaded from: classes2.dex */
        public static final class SwitchingProvider<T> implements Provider<T> {
            private final ActivityRetainedCImpl activityRetainedCImpl;

            /* renamed from: id */
            private final int f58id;
            private final SingletonCImpl singletonCImpl;
            private final ViewModelCImpl viewModelCImpl;

            SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ViewModelCImpl viewModelCImpl, int id2) {
                this.singletonCImpl = singletonCImpl;
                this.activityRetainedCImpl = activityRetainedCImpl;
                this.viewModelCImpl = viewModelCImpl;
                this.f58id = id2;
            }

            @Override // javax.inject.Provider
            public T get() {
                switch (this.f58id) {
                    case 0:
                        return (T) new AprilTagConflictViewModel(this.singletonCImpl.electionRepository());
                    case 1:
                        return (T) new AutoCaptureViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 2:
                        return (T) new CertificateViewModel((CertmanRepository) this.singletonCImpl.provideSirekapSecurityRepositoryProvider.get(), (AuthRepository) this.singletonCImpl.bindAuthRepositoryProvider.get(), (AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get(), (SharedPreferences) this.singletonCImpl.provideSharedPreferencesProvider.get(), (UserRepository) this.singletonCImpl.bindUserRepositoryProvider.get(), this.singletonCImpl.encryptedSharedPreferences(), (CertificateCheckerTask) this.singletonCImpl.bindCertificateCheckerTaskProvider.get());
                    case 3:
                        return (T) new ConfirmSaveFormCImageViewModel(this.singletonCImpl.electionRepository());
                    case 4:
                        return (T) new DashboardViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.electionRepository(), (AuthRepository) this.singletonCImpl.bindAuthRepositoryProvider.get(), (CertmanRepository) this.singletonCImpl.provideSirekapSecurityRepositoryProvider.get(), this.singletonCImpl.encryptedSharedPreferences(), (SharedPreferences) this.singletonCImpl.provideSharedPreferencesProvider.get(), (UserRepository) this.singletonCImpl.bindUserRepositoryProvider.get());
                    case 5:
                        return (T) new DeviceInitializationViewModel((SharedPreferences) this.singletonCImpl.provideSharedPreferencesProvider.get(), (AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get(), (CertmanRepository) this.singletonCImpl.provideSirekapSecurityRepositoryProvider.get());
                    case 6:
                        return (T) new ElectionDetailViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.electionRepository(), this.singletonCImpl.encryptedSharedPreferences(), (UploadFormCImageRekapTask) this.singletonCImpl.bindUploadFormCImageRekapTaskProvider.get(), (FormC1Repository) this.singletonCImpl.bindFormC1RepositoryProvider.get(), (AuthRepository) this.singletonCImpl.bindAuthRepositoryProvider.get(), this.singletonCImpl.witnessRepository(), this.singletonCImpl.pdfLtvFactory(), (TpsTimeRepository) this.singletonCImpl.bindTpsTimeRepositoryProvider.get(), (ZipElectionTask) this.singletonCImpl.bindZipElectionTaskProvider.get(), (BackgroundProcessRepository) this.singletonCImpl.bindBackgroundProcessRepositoryProvider.get());
                    case 7:
                        return (T) new ImageHistoryViewModel();
                    case 8:
                        return (T) new LoginTpsViewModel((AuthRepository) this.singletonCImpl.bindAuthRepositoryProvider.get(), (LoginTask) this.singletonCImpl.bindLoginTaskProvider.get(), (BackgroundProcessRepository) this.singletonCImpl.bindBackgroundProcessRepositoryProvider.get());
                    case 9:
                        return (T) new LoginViewModel((AuthRepository) this.singletonCImpl.bindAuthRepositoryProvider.get(), (LoginTask) this.singletonCImpl.bindLoginTaskProvider.get(), (BackgroundProcessRepository) this.singletonCImpl.bindBackgroundProcessRepositoryProvider.get(), this.singletonCImpl.encryptedSharedPreferences(), (SharedPreferences) this.singletonCImpl.provideSharedPreferencesProvider.get(), (UserRepository) this.singletonCImpl.bindUserRepositoryProvider.get(), (CertmanRepository) this.singletonCImpl.provideSirekapSecurityRepositoryProvider.get());
                    case 10:
                        return (T) new MainViewModel((AuthRepository) this.singletonCImpl.bindAuthRepositoryProvider.get(), (UserRepository) this.singletonCImpl.bindUserRepositoryProvider.get(), (RefreshTokenTask) this.singletonCImpl.bindRefreshTokenTaskProvider.get(), this.singletonCImpl.encryptedSharedPreferences(), (AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get());
                    case 11:
                        return (T) new PreviewImageViewModel();
                    case 12:
                        return (T) new SelectFormCImageViewModel(this.singletonCImpl.electionRepository());
                    case 13:
                        return (T) new SendImageViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), (UploadFormCImageTask) this.singletonCImpl.bindUploadTaskProvider.get(), this.singletonCImpl.electionRepository(), this.singletonCImpl.encryptedSharedPreferences());
                    case 14:
                        return (T) new SettingsViewModel(this.singletonCImpl.electionRepository(), this.singletonCImpl.encryptedSharedPreferences(), (AuthRepository) this.singletonCImpl.bindAuthRepositoryProvider.get(), (AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get());
                    case 15:
                        return (T) new SpecialOccurrenceViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.electionRepository(), this.singletonCImpl.encryptedSharedPreferences(), (UploadSpecialOccurrenceTask) this.singletonCImpl.bindUploadSpecialOccurrenceTaskProvider.get(), (SpecialOccurrenceRepository) this.singletonCImpl.bindSpecialOccurrenceRepositoryProvider.get(), (AuthRepository) this.singletonCImpl.bindAuthRepositoryProvider.get(), this.singletonCImpl.pdfLtvFactory(), (ZipSpecialOccurrenceTask) this.singletonCImpl.bindZipSpecialOccurrenceTaskProvider.get(), (BackgroundProcessRepository) this.singletonCImpl.bindBackgroundProcessRepositoryProvider.get());
                    case 16:
                        return (T) new TpsTimeViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), (TpsTimeRepository) this.singletonCImpl.bindTpsTimeRepositoryProvider.get());
                    case 17:
                        return (T) new VerifyAdministrationHal2PpwpViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), (FormC1Repository) this.singletonCImpl.bindFormC1RepositoryProvider.get(), this.singletonCImpl.electionRepository());
                    case 18:
                        return (T) new VerifyAdministrationHal2ViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), (FormC1Repository) this.singletonCImpl.bindFormC1RepositoryProvider.get(), this.singletonCImpl.electionRepository());
                    case 19:
                        return (T) new VerifyAdministrationViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), (FormC1Repository) this.singletonCImpl.bindFormC1RepositoryProvider.get(), this.singletonCImpl.electionRepository());
                    case 20:
                        return (T) new VerifySpecialOccurrenceViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.electionRepository(), this.singletonCImpl.encryptedSharedPreferences());
                    case 21:
                        return (T) new VerifyTabulationPartaiViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), (FormC1Repository) this.singletonCImpl.bindFormC1RepositoryProvider.get(), this.singletonCImpl.electionRepository());
                    case 22:
                        return (T) new VerifyTabulationViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), (FormC1Repository) this.singletonCImpl.bindFormC1RepositoryProvider.get(), this.singletonCImpl.electionRepository());
                    case 23:
                        return (T) new VerifyWitnessAttendanceListViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.electionRepository(), this.singletonCImpl.encryptedSharedPreferences(), this.singletonCImpl.witnessRepository());
                    case 24:
                        return (T) new VerifyWizardViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), (FormC1Repository) this.singletonCImpl.bindFormC1RepositoryProvider.get(), this.singletonCImpl.electionRepository());
                    case 25:
                        return (T) new WitnessAttendanceListViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.electionRepository(), this.singletonCImpl.encryptedSharedPreferences(), this.singletonCImpl.witnessRepository(), (UploadAttendancePdfTask) this.singletonCImpl.bindUploadAttendancePdfTaskProvider.get(), this.singletonCImpl.pdfLtvFactory(), (AuthRepository) this.singletonCImpl.bindAuthRepositoryProvider.get(), (ZipAttendanceTask) this.singletonCImpl.bindZipAttendanceTaskProvider.get(), (BackgroundProcessRepository) this.singletonCImpl.bindBackgroundProcessRepositoryProvider.get());
                    case 26:
                        return (T) new WitnessQrCodeViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.witnessRepository(), this.singletonCImpl.electionRepository(), this.singletonCImpl.encryptedSharedPreferences(), (RegisterWitnessTask) this.singletonCImpl.bindRegisterWitnessTaskProvider.get(), (BackgroundProcessRepository) this.singletonCImpl.bindBackgroundProcessRepositoryProvider.get());
                    case 27:
                        return (T) new WitnessRegisterViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.electionRepository(), this.singletonCImpl.witnessRepository(), this.singletonCImpl.encryptedSharedPreferences(), (BackgroundProcessRepository) this.singletonCImpl.bindBackgroundProcessRepositoryProvider.get(), (RegisterWitnessTask) this.singletonCImpl.bindRegisterWitnessTaskProvider.get());
                    case 28:
                        return (T) new WitnessUrlListViewModel(this.singletonCImpl.witnessRepository(), this.singletonCImpl.electionRepository());
                    case 29:
                        return (T) new WitnessViewModel(this.singletonCImpl.witnessRepository(), this.singletonCImpl.electionRepository());
                    default:
                        throw new AssertionError(this.f58id);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class ActivityRetainedCImpl extends SirekapApplication_HiltComponents.ActivityRetainedC {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;
        private final SingletonCImpl singletonCImpl;

        private ActivityRetainedCImpl(SingletonCImpl singletonCImpl) {
            this.activityRetainedCImpl = this;
            this.singletonCImpl = singletonCImpl;
            initialize();
        }

        private void initialize() {
            this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, 0));
        }

        @Override // dagger.hilt.android.internal.managers.ActivityComponentManager.ActivityComponentBuilderEntryPoint
        public ActivityComponentBuilder activityComponentBuilder() {
            return new ActivityCBuilder(this.singletonCImpl, this.activityRetainedCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.ActivityRetainedLifecycleEntryPoint
        public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
            return this.provideActivityRetainedLifecycleProvider.get();
        }

        /* loaded from: classes2.dex */
        public static final class SwitchingProvider<T> implements Provider<T> {
            private final ActivityRetainedCImpl activityRetainedCImpl;

            /* renamed from: id */
            private final int f56id;
            private final SingletonCImpl singletonCImpl;

            SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, int id2) {
                this.singletonCImpl = singletonCImpl;
                this.activityRetainedCImpl = activityRetainedCImpl;
                this.f56id = id2;
            }

            @Override // javax.inject.Provider
            public T get() {
                if (this.f56id == 0) {
                    return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();
                }
                throw new AssertionError(this.f56id);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class ServiceCImpl extends SirekapApplication_HiltComponents.ServiceC {
        private final ServiceCImpl serviceCImpl;
        private final SingletonCImpl singletonCImpl;

        private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
            this.serviceCImpl = this;
            this.singletonCImpl = singletonCImpl;
        }
    }

    /* loaded from: classes2.dex */
    public static final class SingletonCImpl extends SirekapApplication_HiltComponents.SingletonC {
        private final ApiModule apiModule;
        private Provider<AppExecutors> appExecutorsProvider;
        private final ApplicationContextModule applicationContextModule;
        private Provider<AuthRepository> bindAuthRepositoryProvider;
        private Provider<BackgroundProcessRepository> bindBackgroundProcessRepositoryProvider;
        private Provider<CertificateCheckerTask> bindCertificateCheckerTaskProvider;
        private Provider<FormC1Repository> bindFormC1RepositoryProvider;
        private Provider<LoginTask> bindLoginTaskProvider;
        private Provider<RefreshTokenTask> bindRefreshTokenTaskProvider;
        private Provider<RegisterWitnessTask> bindRegisterWitnessTaskProvider;
        private Provider<SpecialOccurrenceRepository> bindSpecialOccurrenceRepositoryProvider;
        private Provider<TpsTimeRepository> bindTpsTimeRepositoryProvider;
        private Provider<UploadAttendancePdfTask> bindUploadAttendancePdfTaskProvider;
        private Provider<UploadFileAttemptRepository> bindUploadFileAttemptRepositoryProvider;
        private Provider<UploadFormCImageRekapTask> bindUploadFormCImageRekapTaskProvider;
        private Provider<UploadImageAttemptRepository> bindUploadImageAttemptRepositoryProvider;
        private Provider<UploadSpecialOccurrenceTask> bindUploadSpecialOccurrenceTaskProvider;
        private Provider<UploadFormCImageTask> bindUploadTaskProvider;
        private Provider<UserRepository> bindUserRepositoryProvider;
        private Provider<ZipAttendanceTask> bindZipAttendanceTaskProvider;
        private Provider<ZipElectionTask> bindZipElectionTaskProvider;
        private Provider<ZipSpecialOccurrenceTask> bindZipSpecialOccurrenceTaskProvider;
        private Provider<DefaultAuthRepository> defaultAuthRepositoryProvider;
        private Provider<DefaultBackgroundProcessRepository> defaultBackgroundProcessRepositoryProvider;
        private Provider<DefaultCertificateCheckerTask> defaultCertificateCheckerTaskProvider;
        private Provider<DefaultCertmanRepository> defaultCertmanRepositoryProvider;
        private Provider<DefaultFormC1Repository> defaultFormC1RepositoryProvider;
        private Provider<DefaultLoginTask> defaultLoginTaskProvider;
        private Provider<DefaultRefreshTokenTask> defaultRefreshTokenTaskProvider;
        private Provider<DefaultRegisterWitnessTask> defaultRegisterWitnessTaskProvider;
        private Provider<DefaultSpecialOccurrenceRepository> defaultSpecialOccurrenceRepositoryProvider;
        private Provider<DefaultTpsTimeRepository> defaultTpsTimeRepositoryProvider;
        private Provider<DefaultUploadAttendanceTask> defaultUploadAttendanceTaskProvider;
        private Provider<DefaultUploadFileAttemptRepository> defaultUploadFileAttemptRepositoryProvider;
        private Provider<DefaultUploadFormCImageRekapTask> defaultUploadFormCImageRekapTaskProvider;
        private Provider<DefaultUploadFormCImageTask> defaultUploadFormCImageTaskProvider;
        private Provider<DefaultUploadImageAttemptRepository> defaultUploadImageAttemptRepositoryProvider;
        private Provider<DefaultUploadSpecialOccurrenceTask> defaultUploadSpecialOccurrenceTaskProvider;
        private Provider<DefaultUserRepository> defaultUserRepositoryProvider;
        private Provider<DefaultZipAttendanceTask> defaultZipAttendanceTaskProvider;
        private Provider<DefaultZipElectionTask> defaultZipElectionTaskProvider;
        private Provider<DefaultZipSpecialOccurrenceTask> defaultZipSpecialOccurrenceTaskProvider;
        private final PKIModule pKIModule;
        private final PdfModule pdfModule;
        private Provider<AppDatabase> provideRoomDatabaseProvider;
        private Provider<SharedPreferences> provideSharedPreferencesProvider;
        private Provider<CertmanRepository> provideSirekapSecurityRepositoryProvider;
        private final SingletonCImpl singletonCImpl;

        @Override // org.informatika.sirekap.SirekapApplication_GeneratedInjector
        public void injectSirekapApplication(SirekapApplication sirekapApplication) {
        }

        private SingletonCImpl(ApiModule apiModuleParam, ApplicationContextModule applicationContextModuleParam, PKIModule pKIModuleParam, PdfModule pdfModuleParam) {
            this.singletonCImpl = this;
            this.applicationContextModule = applicationContextModuleParam;
            this.apiModule = apiModuleParam;
            this.pdfModule = pdfModuleParam;
            this.pKIModule = pKIModuleParam;
            initialize(apiModuleParam, applicationContextModuleParam, pKIModuleParam, pdfModuleParam);
        }

        public SirekapApiInterface sirekapApiInterface() {
            ApiModule apiModule = this.apiModule;
            return ApiModule_ProvidesAppServiceFactory.providesAppService(apiModule, ApiModule_ProvidesOkHttpAuthInterceptorFactory.providesOkHttpAuthInterceptor(apiModule), ApiModule_ProvidesOkHttpLoggingInterceptorFactory.providesOkHttpLoggingInterceptor(this.apiModule), encryptedSharedPreferences());
        }

        public KeyCloakApiInterface keyCloakApiInterface() {
            ApiModule apiModule = this.apiModule;
            return ApiModule_ProvidesKeyCloakApiFactory.providesKeyCloakApi(apiModule, ApiModule_ProvidesOkHttpLoggingInterceptorFactory.providesOkHttpLoggingInterceptor(apiModule), encryptedSharedPreferences());
        }

        public SirekapApiInterfaceUpload sirekapApiInterfaceUpload() {
            ApiModule apiModule = this.apiModule;
            return ApiModule_ProvidesAppServiceUploadFactory.providesAppServiceUpload(apiModule, ApiModule_ProvidesOkHttpAuthInterceptorFactory.providesOkHttpAuthInterceptor(apiModule), ApiModule_ProvidesOkHttpLoggingInterceptorFactory.providesOkHttpLoggingInterceptor(this.apiModule), encryptedSharedPreferences());
        }

        public CertmanAPIInterface certmanAPIInterface() {
            ApiModule apiModule = this.apiModule;
            return ApiModule_ProvidesCertmanAPIInterfaceFactory.providesCertmanAPIInterface(apiModule, ApiModule_ProvidesOkHttpLoggingInterceptorFactory.providesOkHttpLoggingInterceptor(apiModule), encryptedSharedPreferences());
        }

        public CertmanCertificateBucketAPIInterface certmanCertificateBucketAPIInterface() {
            ApiModule apiModule = this.apiModule;
            return ApiModule_ProvidesBucketCertificateApiInterfaceFactory.providesBucketCertificateApiInterface(apiModule, ApiModule_ProvidesOkHttpLoggingInterceptorFactory.providesOkHttpLoggingInterceptor(apiModule));
        }

        private PKIApiInterface sirekapProtectedPKIPKIApiInterface() {
            return PKIModule_ProtectedSirekapPKIFactory.protectedSirekapPKI(this.pKIModule, ApiModule_ProvidesOkHttpLoggingInterceptorFactory.providesOkHttpLoggingInterceptor(this.apiModule), encryptedSharedPreferences());
        }

        private PKIRepository sirekapProtectedPKIPKIRepository() {
            return PKIModule_ProvideSirekapProtectedRepositoryFactory.provideSirekapProtectedRepository(this.pKIModule, sirekapProtectedPKIPKIApiInterface());
        }

        private BasicTimestampAuthority.Factory bsreTimestampBasicTimestampAuthorityFactory() {
            return PKIModule_ProvideBsreTimestampAuthorityFactoryFactory.provideBsreTimestampAuthorityFactory(this.pKIModule, sirekapProtectedPKIPKIRepository());
        }

        private PKIApiInterface publicPKIPKIApiInterface() {
            return PKIModule_ProvidesPublicPKIFactory.providesPublicPKI(this.pKIModule, ApiModule_ProvidesOkHttpLoggingInterceptorFactory.providesOkHttpLoggingInterceptor(this.apiModule));
        }

        private PKIRepository publicPKIPKIRepository() {
            return PKIModule_ProvidePublicRepositoryFactory.providePublicRepository(this.pKIModule, publicPKIPKIApiInterface());
        }

        private OCSPUtility oCSPUtility() {
            return PKIModule_ProvideOCSPUtilityFactory.provideOCSPUtility(this.pKIModule, publicPKIPKIRepository());
        }

        private CRLUtility cRLUtility() {
            return PKIModule_ProvideCRLUtilityFactory.provideCRLUtility(this.pKIModule, publicPKIPKIRepository());
        }

        private void initialize(final ApiModule apiModuleParam, final ApplicationContextModule applicationContextModuleParam, final PKIModule pKIModuleParam, final PdfModule pdfModuleParam) {
            this.provideSharedPreferencesProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonCImpl, 0));
            this.provideRoomDatabaseProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonCImpl, 1));
            this.appExecutorsProvider = DoubleCheck.provider(new SwitchingProvider(this.singletonCImpl, 2));
            SwitchingProvider switchingProvider = new SwitchingProvider(this.singletonCImpl, 3);
            this.defaultAuthRepositoryProvider = switchingProvider;
            this.bindAuthRepositoryProvider = DoubleCheck.provider(switchingProvider);
            SwitchingProvider switchingProvider2 = new SwitchingProvider(this.singletonCImpl, 4);
            this.defaultUploadFileAttemptRepositoryProvider = switchingProvider2;
            this.bindUploadFileAttemptRepositoryProvider = DoubleCheck.provider(switchingProvider2);
            SwitchingProvider switchingProvider3 = new SwitchingProvider(this.singletonCImpl, 5);
            this.defaultUploadImageAttemptRepositoryProvider = switchingProvider3;
            this.bindUploadImageAttemptRepositoryProvider = DoubleCheck.provider(switchingProvider3);
            SwitchingProvider switchingProvider4 = new SwitchingProvider(this.singletonCImpl, 6);
            this.defaultSpecialOccurrenceRepositoryProvider = switchingProvider4;
            this.bindSpecialOccurrenceRepositoryProvider = DoubleCheck.provider(switchingProvider4);
            SwitchingProvider switchingProvider5 = new SwitchingProvider(this.singletonCImpl, 7);
            this.defaultCertmanRepositoryProvider = switchingProvider5;
            this.provideSirekapSecurityRepositoryProvider = DoubleCheck.provider(switchingProvider5);
            SwitchingProvider switchingProvider6 = new SwitchingProvider(this.singletonCImpl, 8);
            this.defaultUserRepositoryProvider = switchingProvider6;
            this.bindUserRepositoryProvider = DoubleCheck.provider(switchingProvider6);
            SwitchingProvider switchingProvider7 = new SwitchingProvider(this.singletonCImpl, 9);
            this.defaultCertificateCheckerTaskProvider = switchingProvider7;
            this.bindCertificateCheckerTaskProvider = DoubleCheck.provider(switchingProvider7);
            SwitchingProvider switchingProvider8 = new SwitchingProvider(this.singletonCImpl, 10);
            this.defaultUploadFormCImageRekapTaskProvider = switchingProvider8;
            this.bindUploadFormCImageRekapTaskProvider = DoubleCheck.provider(switchingProvider8);
            SwitchingProvider switchingProvider9 = new SwitchingProvider(this.singletonCImpl, 11);
            this.defaultFormC1RepositoryProvider = switchingProvider9;
            this.bindFormC1RepositoryProvider = DoubleCheck.provider(switchingProvider9);
            SwitchingProvider switchingProvider10 = new SwitchingProvider(this.singletonCImpl, 12);
            this.defaultTpsTimeRepositoryProvider = switchingProvider10;
            this.bindTpsTimeRepositoryProvider = DoubleCheck.provider(switchingProvider10);
            SwitchingProvider switchingProvider11 = new SwitchingProvider(this.singletonCImpl, 13);
            this.defaultZipElectionTaskProvider = switchingProvider11;
            this.bindZipElectionTaskProvider = DoubleCheck.provider(switchingProvider11);
            SwitchingProvider switchingProvider12 = new SwitchingProvider(this.singletonCImpl, 14);
            this.defaultBackgroundProcessRepositoryProvider = switchingProvider12;
            this.bindBackgroundProcessRepositoryProvider = DoubleCheck.provider(switchingProvider12);
            SwitchingProvider switchingProvider13 = new SwitchingProvider(this.singletonCImpl, 15);
            this.defaultLoginTaskProvider = switchingProvider13;
            this.bindLoginTaskProvider = DoubleCheck.provider(switchingProvider13);
            SwitchingProvider switchingProvider14 = new SwitchingProvider(this.singletonCImpl, 16);
            this.defaultRefreshTokenTaskProvider = switchingProvider14;
            this.bindRefreshTokenTaskProvider = DoubleCheck.provider(switchingProvider14);
            SwitchingProvider switchingProvider15 = new SwitchingProvider(this.singletonCImpl, 17);
            this.defaultUploadFormCImageTaskProvider = switchingProvider15;
            this.bindUploadTaskProvider = DoubleCheck.provider(switchingProvider15);
            SwitchingProvider switchingProvider16 = new SwitchingProvider(this.singletonCImpl, 18);
            this.defaultUploadSpecialOccurrenceTaskProvider = switchingProvider16;
            this.bindUploadSpecialOccurrenceTaskProvider = DoubleCheck.provider(switchingProvider16);
            SwitchingProvider switchingProvider17 = new SwitchingProvider(this.singletonCImpl, 19);
            this.defaultZipSpecialOccurrenceTaskProvider = switchingProvider17;
            this.bindZipSpecialOccurrenceTaskProvider = DoubleCheck.provider(switchingProvider17);
            SwitchingProvider switchingProvider18 = new SwitchingProvider(this.singletonCImpl, 20);
            this.defaultUploadAttendanceTaskProvider = switchingProvider18;
            this.bindUploadAttendancePdfTaskProvider = DoubleCheck.provider(switchingProvider18);
            SwitchingProvider switchingProvider19 = new SwitchingProvider(this.singletonCImpl, 21);
            this.defaultZipAttendanceTaskProvider = switchingProvider19;
            this.bindZipAttendanceTaskProvider = DoubleCheck.provider(switchingProvider19);
            SwitchingProvider switchingProvider20 = new SwitchingProvider(this.singletonCImpl, 22);
            this.defaultRegisterWitnessTaskProvider = switchingProvider20;
            this.bindRegisterWitnessTaskProvider = DoubleCheck.provider(switchingProvider20);
        }

        @Override // dagger.hilt.android.flags.FragmentGetContextFix.FragmentGetContextFixEntryPoint
        public Set<Boolean> getDisableFragmentGetContextFix() {
            return Collections.emptySet();
        }

        @Override // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.ActivityRetainedComponentBuilderEntryPoint
        public ActivityRetainedComponentBuilder retainedComponentBuilder() {
            return new ActivityRetainedCBuilder(this.singletonCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.ServiceComponentManager.ServiceComponentBuilderEntryPoint
        public ServiceComponentBuilder serviceComponentBuilder() {
            return new ServiceCBuilder(this.singletonCImpl);
        }

        @Override // org.informatika.sirekap.support.messaging.SirekapMessagingService.SirekapMessagingServiceEntryPoint, org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageWorker.UploadWorkerEntryPoint, org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapWorker.UploadFormCImageRekapWorkerEntryPoint
        public SharedPreferences sharedPreferences() {
            return this.provideSharedPreferencesProvider.get();
        }

        @Override // org.informatika.sirekap.support.messaging.SirekapMessagingService.SirekapMessagingServiceEntryPoint, org.informatika.sirekap.support.worker.login.LoginWorker.LoginWorkerEntryPoint, org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessWorker.RegisterWitnessWorkerEntryPoint, org.informatika.sirekap.support.worker.uploadAttendancePdf.UploadAttendancePdfWorker.UploadAttendancePdfWorkerEntryPoint, org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageWorker.UploadWorkerEntryPoint, org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapWorker.UploadFormCImageRekapWorkerEntryPoint, org.informatika.sirekap.support.worker.uploadSpecialOccurrence.UploadSpecialOccurrenceWorker.UploadSpecialOccurrenceWorkerEntryPoint, org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceWorker.ZipAttendanceWorkerEntryPoint, org.informatika.sirekap.support.worker.zipElection.ZipElectionWorker.ZipElectionWorkerEntryPoint, org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceWorker.ZipSpecialOccurrenceWorkerEntryPoint
        public EncryptedSharedPreferences encryptedSharedPreferences() {
            return new EncryptedSharedPreferences(this.provideSharedPreferencesProvider.get());
        }

        @Override // org.informatika.sirekap.support.messaging.SirekapMessagingService.SirekapMessagingServiceEntryPoint, org.informatika.sirekap.support.worker.login.LoginWorker.LoginWorkerEntryPoint, org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessWorker.RegisterWitnessWorkerEntryPoint, org.informatika.sirekap.support.worker.security.CertificateCheckerWorker.CertificateCheckerWorkerEntryPoint, org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceWorker.ZipAttendanceWorkerEntryPoint, org.informatika.sirekap.support.worker.zipElection.ZipElectionWorker.ZipElectionWorkerEntryPoint, org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceWorker.ZipSpecialOccurrenceWorkerEntryPoint
        public AppDatabase appDatabase() {
            return this.provideRoomDatabaseProvider.get();
        }

        @Override // org.informatika.sirekap.support.worker.login.LoginWorker.LoginWorkerEntryPoint, org.informatika.sirekap.support.worker.refreshToken.RefreshTokenWorker.RefreshTokenWorkerEntryPoint, org.informatika.sirekap.support.worker.security.CertificateCheckerWorker.CertificateCheckerWorkerEntryPoint, org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceWorker.ZipAttendanceWorkerEntryPoint, org.informatika.sirekap.support.worker.zipElection.ZipElectionWorker.ZipElectionWorkerEntryPoint, org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceWorker.ZipSpecialOccurrenceWorkerEntryPoint
        public DefaultAuthRepository authRepository() {
            return new DefaultAuthRepository(this.provideRoomDatabaseProvider.get(), this.appExecutorsProvider.get(), sirekapApiInterface(), keyCloakApiInterface(), encryptedSharedPreferences());
        }

        @Override // org.informatika.sirekap.support.worker.login.LoginWorker.LoginWorkerEntryPoint, org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessWorker.RegisterWitnessWorkerEntryPoint, org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceWorker.ZipAttendanceWorkerEntryPoint, org.informatika.sirekap.support.worker.zipElection.ZipElectionWorker.ZipElectionWorkerEntryPoint, org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceWorker.ZipSpecialOccurrenceWorkerEntryPoint
        public DefaultBackgroundProcessRepository backgroundProcessRepository() {
            return new DefaultBackgroundProcessRepository(sirekapApiInterface(), this.provideRoomDatabaseProvider.get(), this.appExecutorsProvider.get(), encryptedSharedPreferences());
        }

        @Override // org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessWorker.RegisterWitnessWorkerEntryPoint, org.informatika.sirekap.support.worker.uploadAttendancePdf.UploadAttendancePdfWorker.UploadAttendancePdfWorkerEntryPoint, org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceWorker.ZipAttendanceWorkerEntryPoint
        public DefaultWitnessRepository witnessRepository() {
            return new DefaultWitnessRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.applicationContextModule), this.provideRoomDatabaseProvider.get(), sirekapApiInterface(), sirekapApiInterfaceUpload(), this.appExecutorsProvider.get(), encryptedSharedPreferences());
        }

        @Override // org.informatika.sirekap.support.worker.security.CertificateCheckerWorker.CertificateCheckerWorkerEntryPoint
        public DefaultCertmanRepository certmanRepository() {
            return new DefaultCertmanRepository(certmanAPIInterface(), certmanCertificateBucketAPIInterface(), this.bindAuthRepositoryProvider.get());
        }

        @Override // org.informatika.sirekap.support.worker.uploadAttendancePdf.UploadAttendancePdfWorker.UploadAttendancePdfWorkerEntryPoint, org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapWorker.UploadFormCImageRekapWorkerEntryPoint, org.informatika.sirekap.support.worker.uploadSpecialOccurrence.UploadSpecialOccurrenceWorker.UploadSpecialOccurrenceWorkerEntryPoint
        public UploadFileAttemptRepository uploadFileAttemptRepository() {
            return this.bindUploadFileAttemptRepositoryProvider.get();
        }

        @Override // org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageWorker.UploadWorkerEntryPoint, org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapWorker.UploadFormCImageRekapWorkerEntryPoint, org.informatika.sirekap.support.worker.zipElection.ZipElectionWorker.ZipElectionWorkerEntryPoint, org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceWorker.ZipSpecialOccurrenceWorkerEntryPoint
        public DefaultElectionRepository electionRepository() {
            return new DefaultElectionRepository(this.appExecutorsProvider.get(), this.provideRoomDatabaseProvider.get(), sirekapApiInterfaceUpload(), encryptedSharedPreferences());
        }

        @Override // org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageWorker.UploadWorkerEntryPoint
        public DefaultFormC1Repository formC1Repository() {
            return new DefaultFormC1Repository(ApplicationContextModule_ProvideContextFactory.provideContext(this.applicationContextModule), sirekapApiInterface(), this.provideRoomDatabaseProvider.get(), this.appExecutorsProvider.get(), encryptedSharedPreferences(), this.provideSharedPreferencesProvider.get());
        }

        @Override // org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageWorker.UploadWorkerEntryPoint
        public FakeElectionRepository fakeElectionRepository() {
            return new FakeElectionRepository(this.appExecutorsProvider.get(), this.provideRoomDatabaseProvider.get(), sirekapApiInterfaceUpload(), encryptedSharedPreferences());
        }

        @Override // org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageWorker.UploadWorkerEntryPoint
        public UploadImageAttemptRepository uploadImageAttemptRepository() {
            return this.bindUploadImageAttemptRepositoryProvider.get();
        }

        @Override // org.informatika.sirekap.support.worker.uploadSpecialOccurrence.UploadSpecialOccurrenceWorker.UploadSpecialOccurrenceWorkerEntryPoint
        public SpecialOccurrenceRepository specialOccurrenceRepository() {
            return this.bindSpecialOccurrenceRepositoryProvider.get();
        }

        @Override // org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceWorker.ZipAttendanceWorkerEntryPoint, org.informatika.sirekap.support.worker.zipElection.ZipElectionWorker.ZipElectionWorkerEntryPoint, org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceWorker.ZipSpecialOccurrenceWorkerEntryPoint
        public PdfLtv.Factory pdfLtvFactory() {
            return PdfModule_ProvidesPdfLtvFactoryFactory.providesPdfLtvFactory(this.pdfModule, bsreTimestampBasicTimestampAuthorityFactory(), oCSPUtility(), cRLUtility());
        }

        /* loaded from: classes2.dex */
        public static final class SwitchingProvider<T> implements Provider<T> {

            /* renamed from: id */
            private final int f57id;
            private final SingletonCImpl singletonCImpl;

            SwitchingProvider(SingletonCImpl singletonCImpl, int id2) {
                this.singletonCImpl = singletonCImpl;
                this.f57id = id2;
            }

            @Override // javax.inject.Provider
            public T get() {
                switch (this.f57id) {
                    case 0:
                        return (T) SharedPreferencesModule_ProvideSharedPreferencesFactory.provideSharedPreferences(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 1:
                        return (T) DatabaseModule_ProvideRoomDatabaseFactory.provideRoomDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 2:
                        return (T) new AppExecutors();
                    case 3:
                        return (T) new DefaultAuthRepository((AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get(), (AppExecutors) this.singletonCImpl.appExecutorsProvider.get(), this.singletonCImpl.sirekapApiInterface(), this.singletonCImpl.keyCloakApiInterface(), this.singletonCImpl.encryptedSharedPreferences());
                    case 4:
                        return (T) new DefaultUploadFileAttemptRepository((AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get(), (AppExecutors) this.singletonCImpl.appExecutorsProvider.get());
                    case 5:
                        return (T) new DefaultUploadImageAttemptRepository((AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get(), (AppExecutors) this.singletonCImpl.appExecutorsProvider.get());
                    case 6:
                        return (T) new DefaultSpecialOccurrenceRepository((AppExecutors) this.singletonCImpl.appExecutorsProvider.get(), (AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get(), this.singletonCImpl.sirekapApiInterfaceUpload(), this.singletonCImpl.encryptedSharedPreferences());
                    case 7:
                        return (T) new DefaultCertmanRepository(this.singletonCImpl.certmanAPIInterface(), this.singletonCImpl.certmanCertificateBucketAPIInterface(), (AuthRepository) this.singletonCImpl.bindAuthRepositoryProvider.get());
                    case 8:
                        return (T) new DefaultUserRepository((AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get(), (AppExecutors) this.singletonCImpl.appExecutorsProvider.get());
                    case 9:
                        return (T) new DefaultCertificateCheckerTask();
                    case 10:
                        return (T) new DefaultUploadFormCImageRekapTask();
                    case 11:
                        return (T) new DefaultFormC1Repository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.sirekapApiInterface(), (AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get(), (AppExecutors) this.singletonCImpl.appExecutorsProvider.get(), this.singletonCImpl.encryptedSharedPreferences(), (SharedPreferences) this.singletonCImpl.provideSharedPreferencesProvider.get());
                    case 12:
                        return (T) new DefaultTpsTimeRepository((AppExecutors) this.singletonCImpl.appExecutorsProvider.get(), (AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get(), this.singletonCImpl.sirekapApiInterface(), this.singletonCImpl.encryptedSharedPreferences());
                    case 13:
                        return (T) new DefaultZipElectionTask();
                    case 14:
                        return (T) new DefaultBackgroundProcessRepository(this.singletonCImpl.sirekapApiInterface(), (AppDatabase) this.singletonCImpl.provideRoomDatabaseProvider.get(), (AppExecutors) this.singletonCImpl.appExecutorsProvider.get(), this.singletonCImpl.encryptedSharedPreferences());
                    case 15:
                        return (T) new DefaultLoginTask();
                    case 16:
                        return (T) new DefaultRefreshTokenTask();
                    case 17:
                        return (T) new DefaultUploadFormCImageTask();
                    case 18:
                        return (T) new DefaultUploadSpecialOccurrenceTask();
                    case 19:
                        return (T) new DefaultZipSpecialOccurrenceTask();
                    case 20:
                        return (T) new DefaultUploadAttendanceTask();
                    case 21:
                        return (T) new DefaultZipAttendanceTask();
                    case 22:
                        return (T) new DefaultRegisterWitnessTask();
                    default:
                        throw new AssertionError(this.f57id);
                }
            }
        }
    }
}
