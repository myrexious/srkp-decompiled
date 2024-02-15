package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.tabs.TabLayout;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.ui.dashboard.DashboardViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentDashboardBinding extends ViewDataBinding {
    public final ImageView accountIcon;
    public final Button buttonChangeTps;
    public final Button buttonCopyKodeTps;
    public final Button buttonImageHistory;
    public final Button buttonManageSpecialOccurrence;
    public final Button buttonManageTime;
    public final Button buttonManageWitnessAttendance;
    public final Button buttonSettings;
    public final MaterialCardView cardImageHistory;
    public final MaterialCardView cardUserInfo;
    public final ViewPager2 carouselElection;
    public final TextView contentKabko;
    public final TextView contentKecamatan;
    public final TextView contentKelurahan;
    public final TextView contentKodeTps;
    public final TextView contentProcessingCount;
    public final TextView contentProvinsi;
    public final TextView contentTps;
    public final CircularProgressIndicator dashboardFragmentLoading;
    public final TextView labelKabko;
    public final TextView labelKecamatan;
    public final TextView labelKelurahan;
    public final TextView labelKodeTps;
    public final TextView labelProcessingCount;
    public final TextView labelProvinsi;
    public final TextView labelRiwayatGambar;
    public final TextView labelScannedCount;
    public final TextView labelTps;
    public final TextView loginAsText;
    @Bindable
    protected User mUser;
    @Bindable
    protected DashboardViewModel mViewModel;
    public final FloatingActionButton scanButton;
    public final TabLayout tabPagerLayout;
    public final TextView textAppVersion;
    public final TextView textAppVersionLabel;

    public abstract void setUser(User user);

    public abstract void setViewModel(DashboardViewModel viewModel);

    public FragmentDashboardBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView accountIcon, Button buttonChangeTps, Button buttonCopyKodeTps, Button buttonImageHistory, Button buttonManageSpecialOccurrence, Button buttonManageTime, Button buttonManageWitnessAttendance, Button buttonSettings, MaterialCardView cardImageHistory, MaterialCardView cardUserInfo, ViewPager2 carouselElection, TextView contentKabko, TextView contentKecamatan, TextView contentKelurahan, TextView contentKodeTps, TextView contentProcessingCount, TextView contentProvinsi, TextView contentTps, CircularProgressIndicator dashboardFragmentLoading, TextView labelKabko, TextView labelKecamatan, TextView labelKelurahan, TextView labelKodeTps, TextView labelProcessingCount, TextView labelProvinsi, TextView labelRiwayatGambar, TextView labelScannedCount, TextView labelTps, TextView loginAsText, FloatingActionButton scanButton, TabLayout tabPagerLayout, TextView textAppVersion, TextView textAppVersionLabel) {
        super(_bindingComponent, _root, _localFieldCount);
        this.accountIcon = accountIcon;
        this.buttonChangeTps = buttonChangeTps;
        this.buttonCopyKodeTps = buttonCopyKodeTps;
        this.buttonImageHistory = buttonImageHistory;
        this.buttonManageSpecialOccurrence = buttonManageSpecialOccurrence;
        this.buttonManageTime = buttonManageTime;
        this.buttonManageWitnessAttendance = buttonManageWitnessAttendance;
        this.buttonSettings = buttonSettings;
        this.cardImageHistory = cardImageHistory;
        this.cardUserInfo = cardUserInfo;
        this.carouselElection = carouselElection;
        this.contentKabko = contentKabko;
        this.contentKecamatan = contentKecamatan;
        this.contentKelurahan = contentKelurahan;
        this.contentKodeTps = contentKodeTps;
        this.contentProcessingCount = contentProcessingCount;
        this.contentProvinsi = contentProvinsi;
        this.contentTps = contentTps;
        this.dashboardFragmentLoading = dashboardFragmentLoading;
        this.labelKabko = labelKabko;
        this.labelKecamatan = labelKecamatan;
        this.labelKelurahan = labelKelurahan;
        this.labelKodeTps = labelKodeTps;
        this.labelProcessingCount = labelProcessingCount;
        this.labelProvinsi = labelProvinsi;
        this.labelRiwayatGambar = labelRiwayatGambar;
        this.labelScannedCount = labelScannedCount;
        this.labelTps = labelTps;
        this.loginAsText = loginAsText;
        this.scanButton = scanButton;
        this.tabPagerLayout = tabPagerLayout;
        this.textAppVersion = textAppVersion;
        this.textAppVersionLabel = textAppVersionLabel;
    }

    public User getUser() {
        return this.mUser;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentDashboardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDashboardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentDashboardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_dashboard, root, attachToRoot, component);
    }

    public static FragmentDashboardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDashboardBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentDashboardBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_dashboard, null, false, component);
    }

    public static FragmentDashboardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDashboardBinding bind(View view, Object component) {
        return (FragmentDashboardBinding) bind(component, view, R.layout.fragment_dashboard);
    }
}
