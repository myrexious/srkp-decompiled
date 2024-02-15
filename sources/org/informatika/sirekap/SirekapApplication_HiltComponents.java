package org.informatika.sirekap;

import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import javax.inject.Singleton;
import org.informatika.sirekap.MainViewModel_HiltModules;
import org.informatika.sirekap.di.ApiModule;
import org.informatika.sirekap.di.AuthModule;
import org.informatika.sirekap.di.DatabaseModule;
import org.informatika.sirekap.di.ElectionModule;
import org.informatika.sirekap.di.FormC1Module;
import org.informatika.sirekap.di.PKIModule;
import org.informatika.sirekap.di.PdfModule;
import org.informatika.sirekap.di.SecurityModule;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.di.SpecialOccurrenceModule;
import org.informatika.sirekap.di.TpsTimeModule;
import org.informatika.sirekap.di.UserModule;
import org.informatika.sirekap.di.WitnessModule;
import org.informatika.sirekap.di.WorkerModule;
import org.informatika.sirekap.support.messaging.SirekapMessagingService;
import org.informatika.sirekap.support.worker.login.LoginWorker;
import org.informatika.sirekap.support.worker.refreshToken.RefreshTokenWorker;
import org.informatika.sirekap.support.worker.registerWitness.RegisterWitnessWorker;
import org.informatika.sirekap.support.worker.security.CertificateCheckerWorker;
import org.informatika.sirekap.support.worker.uploadAttendancePdf.UploadAttendancePdfWorker;
import org.informatika.sirekap.support.worker.uploadFormCImage.UploadFormCImageWorker;
import org.informatika.sirekap.support.worker.uploadFormCImageRekap.UploadFormCImageRekapWorker;
import org.informatika.sirekap.support.worker.uploadSpecialOccurrence.UploadSpecialOccurrenceWorker;
import org.informatika.sirekap.support.worker.zipAttendance.ZipAttendanceWorker;
import org.informatika.sirekap.support.worker.zipElection.ZipElectionWorker;
import org.informatika.sirekap.support.worker.zipSpecialOccurrence.ZipSpecialOccurrenceWorker;
import org.informatika.sirekap.ui.BaseActivity_GeneratedInjector;
import org.informatika.sirekap.ui.DeviceInitializationActivity_GeneratedInjector;
import org.informatika.sirekap.ui.DeviceInitializationViewModel_HiltModules;
import org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictFragment_GeneratedInjector;
import org.informatika.sirekap.ui.aprilTagConflict.AprilTagConflictViewModel_HiltModules;
import org.informatika.sirekap.ui.autocapture.AutoCaptureFragment_GeneratedInjector;
import org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel_HiltModules;
import org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageFragment_GeneratedInjector;
import org.informatika.sirekap.ui.confirmSaveFormCImage.ConfirmSaveFormCImageViewModel_HiltModules;
import org.informatika.sirekap.ui.dashboard.DashboardFragment_GeneratedInjector;
import org.informatika.sirekap.ui.dashboard.DashboardViewModel_HiltModules;
import org.informatika.sirekap.ui.electionDetail.ElectionDetailFragment_GeneratedInjector;
import org.informatika.sirekap.ui.electionDetail.ElectionDetailViewModel_HiltModules;
import org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel_HiltModules;
import org.informatika.sirekap.ui.login.LoginFragment_GeneratedInjector;
import org.informatika.sirekap.ui.login.LoginViewModel_HiltModules;
import org.informatika.sirekap.ui.loginTps.LoginTpsFragment_GeneratedInjector;
import org.informatika.sirekap.ui.loginTps.LoginTpsViewModel_HiltModules;
import org.informatika.sirekap.ui.previewImage.PreviewImageViewModel_HiltModules;
import org.informatika.sirekap.ui.security.CertificateFragment_GeneratedInjector;
import org.informatika.sirekap.ui.security.CertificateViewModel_HiltModules;
import org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageFragment_GeneratedInjector;
import org.informatika.sirekap.ui.selectFormCImage.SelectFormCImageViewModel_HiltModules;
import org.informatika.sirekap.ui.sendImage.SendImageFragment_GeneratedInjector;
import org.informatika.sirekap.ui.sendImage.SendImageViewModel_HiltModules;
import org.informatika.sirekap.ui.settings.SettingsFragment_GeneratedInjector;
import org.informatika.sirekap.ui.settings.SettingsViewModel_HiltModules;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceFragment_GeneratedInjector;
import org.informatika.sirekap.ui.specialOccurrence.SpecialOccurrenceViewModel_HiltModules;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceFragment_GeneratedInjector;
import org.informatika.sirekap.ui.specialOccurrence.verify.VerifySpecialOccurrenceViewModel_HiltModules;
import org.informatika.sirekap.ui.tpsTime.TpsTimeFragment_GeneratedInjector;
import org.informatika.sirekap.ui.tpsTime.TpsTimeViewModel_HiltModules;
import org.informatika.sirekap.ui.verify.administration.VerifyAdministrationFragment_GeneratedInjector;
import org.informatika.sirekap.ui.verify.administration.VerifyAdministrationViewModel_HiltModules;
import org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2Fragment_GeneratedInjector;
import org.informatika.sirekap.ui.verify.administrationHal2.VerifyAdministrationHal2ViewModel_HiltModules;
import org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpFragment_GeneratedInjector;
import org.informatika.sirekap.ui.verify.administrationHal2Ppwp.VerifyAdministrationHal2PpwpViewModel_HiltModules;
import org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationFragment_GeneratedInjector;
import org.informatika.sirekap.ui.verify.tabulation.VerifyTabulationViewModel_HiltModules;
import org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiFragment_GeneratedInjector;
import org.informatika.sirekap.ui.verify.tabulationPartai.VerifyTabulationPartaiViewModel_HiltModules;
import org.informatika.sirekap.ui.verify.wizard.VerifyWizardFragment_GeneratedInjector;
import org.informatika.sirekap.ui.verify.wizard.VerifyWizardViewModel_HiltModules;
import org.informatika.sirekap.ui.witness.WitnessFragment_GeneratedInjector;
import org.informatika.sirekap.ui.witness.WitnessViewModel_HiltModules;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListFragment_GeneratedInjector;
import org.informatika.sirekap.ui.witness.attendanceList.WitnessAttendanceListViewModel_HiltModules;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListFragment_GeneratedInjector;
import org.informatika.sirekap.ui.witness.attendanceList.verify.VerifyWitnessAttendanceListViewModel_HiltModules;
import org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeFragment_GeneratedInjector;
import org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel_HiltModules;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListFragment_GeneratedInjector;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel_HiltModules;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterFragment_GeneratedInjector;
import org.informatika.sirekap.ui.witness.register.WitnessRegisterViewModel_HiltModules;

/* loaded from: classes2.dex */
public final class SirekapApplication_HiltComponents {

    @Subcomponent(modules = {HiltWrapper_ActivityModule.class, HiltWrapper_DefaultViewModelFactories_ActivityModule.class, FragmentCBuilderModule.class, ViewCBuilderModule.class})
    /* loaded from: classes2.dex */
    public static abstract class ActivityC implements ActivityComponent, DefaultViewModelFactories.ActivityEntryPoint, HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint, FragmentComponentManager.FragmentComponentBuilderEntryPoint, ViewComponentManager.ViewComponentBuilderEntryPoint, GeneratedComponent, MainActivity_GeneratedInjector, BaseActivity_GeneratedInjector, DeviceInitializationActivity_GeneratedInjector {

        @Subcomponent.Builder
        /* loaded from: classes2.dex */
        interface Builder extends ActivityComponentBuilder {
        }
    }

    @Module(subcomponents = {ActivityC.class})
    /* loaded from: classes2.dex */
    interface ActivityCBuilderModule {
        @Binds
        ActivityComponentBuilder bind(ActivityC.Builder builder);
    }

    @Subcomponent(modules = {AprilTagConflictViewModel_HiltModules.KeyModule.class, AutoCaptureViewModel_HiltModules.KeyModule.class, CertificateViewModel_HiltModules.KeyModule.class, ConfirmSaveFormCImageViewModel_HiltModules.KeyModule.class, DashboardViewModel_HiltModules.KeyModule.class, DeviceInitializationViewModel_HiltModules.KeyModule.class, ElectionDetailViewModel_HiltModules.KeyModule.class, HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class, ImageHistoryViewModel_HiltModules.KeyModule.class, LoginTpsViewModel_HiltModules.KeyModule.class, LoginViewModel_HiltModules.KeyModule.class, MainViewModel_HiltModules.KeyModule.class, PreviewImageViewModel_HiltModules.KeyModule.class, SelectFormCImageViewModel_HiltModules.KeyModule.class, SendImageViewModel_HiltModules.KeyModule.class, SettingsViewModel_HiltModules.KeyModule.class, ActivityCBuilderModule.class, ViewModelCBuilderModule.class, SpecialOccurrenceViewModel_HiltModules.KeyModule.class, TpsTimeViewModel_HiltModules.KeyModule.class, VerifyAdministrationHal2PpwpViewModel_HiltModules.KeyModule.class, VerifyAdministrationHal2ViewModel_HiltModules.KeyModule.class, VerifyAdministrationViewModel_HiltModules.KeyModule.class, VerifySpecialOccurrenceViewModel_HiltModules.KeyModule.class, VerifyTabulationPartaiViewModel_HiltModules.KeyModule.class, VerifyTabulationViewModel_HiltModules.KeyModule.class, VerifyWitnessAttendanceListViewModel_HiltModules.KeyModule.class, VerifyWizardViewModel_HiltModules.KeyModule.class, WitnessAttendanceListViewModel_HiltModules.KeyModule.class, WitnessQrCodeViewModel_HiltModules.KeyModule.class, WitnessRegisterViewModel_HiltModules.KeyModule.class, WitnessUrlListViewModel_HiltModules.KeyModule.class, WitnessViewModel_HiltModules.KeyModule.class})
    /* loaded from: classes2.dex */
    public static abstract class ActivityRetainedC implements ActivityRetainedComponent, ActivityComponentManager.ActivityComponentBuilderEntryPoint, HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint, GeneratedComponent {

        @Subcomponent.Builder
        /* loaded from: classes2.dex */
        interface Builder extends ActivityRetainedComponentBuilder {
        }
    }

    @Module(subcomponents = {ActivityRetainedC.class})
    /* loaded from: classes2.dex */
    interface ActivityRetainedCBuilderModule {
        @Binds
        ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
    }

    @Subcomponent(modules = {ViewWithFragmentCBuilderModule.class})
    /* loaded from: classes2.dex */
    public static abstract class FragmentC implements FragmentComponent, DefaultViewModelFactories.FragmentEntryPoint, ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint, GeneratedComponent, AprilTagConflictFragment_GeneratedInjector, AutoCaptureFragment_GeneratedInjector, ConfirmSaveFormCImageFragment_GeneratedInjector, DashboardFragment_GeneratedInjector, ElectionDetailFragment_GeneratedInjector, LoginFragment_GeneratedInjector, LoginTpsFragment_GeneratedInjector, CertificateFragment_GeneratedInjector, SelectFormCImageFragment_GeneratedInjector, SendImageFragment_GeneratedInjector, SettingsFragment_GeneratedInjector, SpecialOccurrenceFragment_GeneratedInjector, VerifySpecialOccurrenceFragment_GeneratedInjector, TpsTimeFragment_GeneratedInjector, VerifyAdministrationFragment_GeneratedInjector, VerifyAdministrationHal2Fragment_GeneratedInjector, VerifyAdministrationHal2PpwpFragment_GeneratedInjector, VerifyTabulationFragment_GeneratedInjector, VerifyTabulationPartaiFragment_GeneratedInjector, VerifyWizardFragment_GeneratedInjector, WitnessFragment_GeneratedInjector, WitnessAttendanceListFragment_GeneratedInjector, VerifyWitnessAttendanceListFragment_GeneratedInjector, WitnessQrCodeFragment_GeneratedInjector, WitnessUrlListFragment_GeneratedInjector, WitnessRegisterFragment_GeneratedInjector {

        @Subcomponent.Builder
        /* loaded from: classes2.dex */
        interface Builder extends FragmentComponentBuilder {
        }
    }

    @Module(subcomponents = {FragmentC.class})
    /* loaded from: classes2.dex */
    interface FragmentCBuilderModule {
        @Binds
        FragmentComponentBuilder bind(FragmentC.Builder builder);
    }

    @Subcomponent
    /* loaded from: classes2.dex */
    public static abstract class ServiceC implements ServiceComponent, GeneratedComponent {

        @Subcomponent.Builder
        /* loaded from: classes2.dex */
        interface Builder extends ServiceComponentBuilder {
        }
    }

    @Module(subcomponents = {ServiceC.class})
    /* loaded from: classes2.dex */
    interface ServiceCBuilderModule {
        @Binds
        ServiceComponentBuilder bind(ServiceC.Builder builder);
    }

    @Component(modules = {ApiModule.class, ApplicationContextModule.class, AuthModule.class, DatabaseModule.class, ElectionModule.class, FormC1Module.class, HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule.class, PKIModule.class, PdfModule.class, SecurityModule.class, SharedPreferencesModule.class, ActivityRetainedCBuilderModule.class, ServiceCBuilderModule.class, SpecialOccurrenceModule.class, TpsTimeModule.class, UserModule.class, WitnessModule.class, WorkerModule.class})
    @Singleton
    /* loaded from: classes2.dex */
    public static abstract class SingletonC implements FragmentGetContextFix.FragmentGetContextFixEntryPoint, HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint, ServiceComponentManager.ServiceComponentBuilderEntryPoint, SingletonComponent, GeneratedComponent, SirekapApplication_GeneratedInjector, SirekapMessagingService.SirekapMessagingServiceEntryPoint, LoginWorker.LoginWorkerEntryPoint, RefreshTokenWorker.RefreshTokenWorkerEntryPoint, RegisterWitnessWorker.RegisterWitnessWorkerEntryPoint, CertificateCheckerWorker.CertificateCheckerWorkerEntryPoint, UploadAttendancePdfWorker.UploadAttendancePdfWorkerEntryPoint, UploadFormCImageWorker.UploadWorkerEntryPoint, UploadFormCImageRekapWorker.UploadFormCImageRekapWorkerEntryPoint, UploadSpecialOccurrenceWorker.UploadSpecialOccurrenceWorkerEntryPoint, ZipAttendanceWorker.ZipAttendanceWorkerEntryPoint, ZipElectionWorker.ZipElectionWorkerEntryPoint, ZipSpecialOccurrenceWorker.ZipSpecialOccurrenceWorkerEntryPoint {
    }

    @Subcomponent
    /* loaded from: classes2.dex */
    public static abstract class ViewC implements ViewComponent, GeneratedComponent {

        @Subcomponent.Builder
        /* loaded from: classes2.dex */
        interface Builder extends ViewComponentBuilder {
        }
    }

    @Module(subcomponents = {ViewC.class})
    /* loaded from: classes2.dex */
    interface ViewCBuilderModule {
        @Binds
        ViewComponentBuilder bind(ViewC.Builder builder);
    }

    @Subcomponent(modules = {AprilTagConflictViewModel_HiltModules.BindsModule.class, AutoCaptureViewModel_HiltModules.BindsModule.class, CertificateViewModel_HiltModules.BindsModule.class, ConfirmSaveFormCImageViewModel_HiltModules.BindsModule.class, DashboardViewModel_HiltModules.BindsModule.class, DeviceInitializationViewModel_HiltModules.BindsModule.class, ElectionDetailViewModel_HiltModules.BindsModule.class, HiltWrapper_HiltViewModelFactory_ViewModelModule.class, ImageHistoryViewModel_HiltModules.BindsModule.class, LoginTpsViewModel_HiltModules.BindsModule.class, LoginViewModel_HiltModules.BindsModule.class, MainViewModel_HiltModules.BindsModule.class, PreviewImageViewModel_HiltModules.BindsModule.class, SelectFormCImageViewModel_HiltModules.BindsModule.class, SendImageViewModel_HiltModules.BindsModule.class, SettingsViewModel_HiltModules.BindsModule.class, SpecialOccurrenceViewModel_HiltModules.BindsModule.class, TpsTimeViewModel_HiltModules.BindsModule.class, VerifyAdministrationHal2PpwpViewModel_HiltModules.BindsModule.class, VerifyAdministrationHal2ViewModel_HiltModules.BindsModule.class, VerifyAdministrationViewModel_HiltModules.BindsModule.class, VerifySpecialOccurrenceViewModel_HiltModules.BindsModule.class, VerifyTabulationPartaiViewModel_HiltModules.BindsModule.class, VerifyTabulationViewModel_HiltModules.BindsModule.class, VerifyWitnessAttendanceListViewModel_HiltModules.BindsModule.class, VerifyWizardViewModel_HiltModules.BindsModule.class, WitnessAttendanceListViewModel_HiltModules.BindsModule.class, WitnessQrCodeViewModel_HiltModules.BindsModule.class, WitnessRegisterViewModel_HiltModules.BindsModule.class, WitnessUrlListViewModel_HiltModules.BindsModule.class, WitnessViewModel_HiltModules.BindsModule.class})
    /* loaded from: classes2.dex */
    public static abstract class ViewModelC implements ViewModelComponent, HiltViewModelFactory.ViewModelFactoriesEntryPoint, GeneratedComponent {

        @Subcomponent.Builder
        /* loaded from: classes2.dex */
        interface Builder extends ViewModelComponentBuilder {
        }
    }

    @Module(subcomponents = {ViewModelC.class})
    /* loaded from: classes2.dex */
    interface ViewModelCBuilderModule {
        @Binds
        ViewModelComponentBuilder bind(ViewModelC.Builder builder);
    }

    @Subcomponent
    /* loaded from: classes2.dex */
    public static abstract class ViewWithFragmentC implements ViewWithFragmentComponent, GeneratedComponent {

        @Subcomponent.Builder
        /* loaded from: classes2.dex */
        interface Builder extends ViewWithFragmentComponentBuilder {
        }
    }

    @Module(subcomponents = {ViewWithFragmentC.class})
    /* loaded from: classes2.dex */
    interface ViewWithFragmentCBuilderModule {
        @Binds
        ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
    }

    private SirekapApplication_HiltComponents() {
    }
}
