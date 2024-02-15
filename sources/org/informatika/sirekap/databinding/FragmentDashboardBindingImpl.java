package org.informatika.sirekap.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.tabs.TabLayout;
import org.informatika.sirekap.BuildConfig;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Kecamatan;
import org.informatika.sirekap.model.Kelurahan;
import org.informatika.sirekap.model.KotaKabupaten;
import org.informatika.sirekap.model.Provinsi;
import org.informatika.sirekap.model.Tps;
import org.informatika.sirekap.model.User;
import org.informatika.sirekap.support.DataBindingAdaptersKt;
import org.informatika.sirekap.ui.dashboard.DashboardViewModel;

/* loaded from: classes2.dex */
public class FragmentDashboardBindingImpl extends FragmentDashboardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ConstraintLayout mboundView1;
    private final RelativeLayout mboundView2;
    private final TextView mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.dashboardFragment_loading, 12);
        sparseIntArray.put(R.id.card_user_info, 13);
        sparseIntArray.put(R.id.account_icon, 14);
        sparseIntArray.put(R.id.login_as_text, 15);
        sparseIntArray.put(R.id.button_settings, 16);
        sparseIntArray.put(R.id.label_tps, 17);
        sparseIntArray.put(R.id.label_kode_tps, 18);
        sparseIntArray.put(R.id.button_copy_kode_tps, 19);
        sparseIntArray.put(R.id.label_kelurahan, 20);
        sparseIntArray.put(R.id.label_kecamatan, 21);
        sparseIntArray.put(R.id.label_kabko, 22);
        sparseIntArray.put(R.id.label_provinsi, 23);
        sparseIntArray.put(R.id.button_manage_witness_attendance, 24);
        sparseIntArray.put(R.id.button_manage_special_occurrence, 25);
        sparseIntArray.put(R.id.button_manage_time, 26);
        sparseIntArray.put(R.id.card_image_history, 27);
        sparseIntArray.put(R.id.label_riwayat_gambar, 28);
        sparseIntArray.put(R.id.label_processing_count, 29);
        sparseIntArray.put(R.id.content_processing_count, 30);
        sparseIntArray.put(R.id.label_scanned_count, 31);
        sparseIntArray.put(R.id.button_image_history, 32);
        sparseIntArray.put(R.id.carousel_election, 33);
        sparseIntArray.put(R.id.tab_pager_layout, 34);
        sparseIntArray.put(R.id.text_app_version_label, 35);
        sparseIntArray.put(R.id.scan_button, 36);
    }

    public FragmentDashboardBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 37, sIncludes, sViewsWithIds));
    }

    private FragmentDashboardBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ImageView) bindings[14], (Button) bindings[10], (Button) bindings[19], (Button) bindings[32], (Button) bindings[25], (Button) bindings[26], (Button) bindings[24], (Button) bindings[16], (MaterialCardView) bindings[27], (MaterialCardView) bindings[13], (ViewPager2) bindings[33], (TextView) bindings[8], (TextView) bindings[7], (TextView) bindings[6], (TextView) bindings[5], (TextView) bindings[30], (TextView) bindings[9], (TextView) bindings[4], (CircularProgressIndicator) bindings[12], (TextView) bindings[22], (TextView) bindings[21], (TextView) bindings[20], (TextView) bindings[18], (TextView) bindings[29], (TextView) bindings[23], (TextView) bindings[28], (TextView) bindings[31], (TextView) bindings[17], (TextView) bindings[15], (FloatingActionButton) bindings[36], (TabLayout) bindings[34], (TextView) bindings[11], (TextView) bindings[35]);
        this.mDirtyFlags = -1L;
        this.buttonChangeTps.setTag(null);
        this.contentKabko.setTag(null);
        this.contentKecamatan.setTag(null);
        this.contentKelurahan.setTag(null);
        this.contentKodeTps.setTag(null);
        this.contentProvinsi.setTag(null);
        this.contentTps.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout2;
        constraintLayout2.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[2];
        this.mboundView2 = relativeLayout;
        relativeLayout.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        this.textAppVersion.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (85 == variableId) {
            setUser((User) variable);
        } else if (87 != variableId) {
            return false;
        } else {
            setViewModel((DashboardViewModel) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.FragmentDashboardBinding
    public void setUser(User User) {
        this.mUser = User;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(85);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.FragmentDashboardBinding
    public void setViewModel(DashboardViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeViewModelLoadImageProcessing((MutableLiveData) object, fieldId);
    }

    private boolean onChangeViewModelLoadImageProcessing(MutableLiveData<Boolean> ViewModelLoadImageProcessing, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        boolean z2;
        boolean z3;
        Tps tps;
        KotaKabupaten kotaKabupaten;
        Provinsi provinsi;
        Kecamatan kecamatan;
        Kelurahan kelurahan;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        User user = this.mUser;
        DashboardViewModel dashboardViewModel = this.mViewModel;
        int i = ((10 & j) > 0L ? 1 : ((10 & j) == 0L ? 0 : -1));
        boolean z4 = false;
        if (i != 0) {
            if (user != null) {
                tps = user.getTps();
                z3 = user.canChangeTps();
                str = user.getFullName();
            } else {
                z3 = false;
                str = null;
                tps = null;
            }
            if (tps != null) {
                provinsi = tps.getProvinsi();
                kecamatan = tps.getKecamatan();
                str6 = tps.getName();
                str7 = tps.getKodeTpsFormatted();
                kelurahan = tps.getKelurahan();
                kotaKabupaten = tps.getKabko();
            } else {
                kotaKabupaten = null;
                provinsi = null;
                kecamatan = null;
                str6 = null;
                str7 = null;
                kelurahan = null;
            }
            z = !z3;
            str4 = provinsi != null ? provinsi.getName() : null;
            str5 = kecamatan != null ? kecamatan.getName() : null;
            String name = kelurahan != null ? kelurahan.getName() : null;
            if (kotaKabupaten != null) {
                str3 = kotaKabupaten.getName();
                str2 = name;
            } else {
                str2 = name;
                str3 = null;
            }
        } else {
            z = false;
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            str6 = null;
            str7 = null;
        }
        int i2 = ((j & 13) > 0L ? 1 : ((j & 13) == 0L ? 0 : -1));
        if (i2 != 0) {
            MutableLiveData<Boolean> loadImageProcessing = dashboardViewModel != null ? dashboardViewModel.getLoadImageProcessing() : null;
            updateLiveDataRegistration(0, loadImageProcessing);
            boolean safeUnbox = ViewDataBinding.safeUnbox(loadImageProcessing != null ? loadImageProcessing.getValue() : null);
            z4 = ViewDataBinding.safeUnbox(Boolean.valueOf(!safeUnbox));
            z2 = safeUnbox;
        } else {
            z2 = false;
        }
        if (i != 0) {
            DataBindingAdaptersKt.hidden(this.buttonChangeTps, z);
            TextViewBindingAdapter.setText(this.contentKabko, str3);
            TextViewBindingAdapter.setText(this.contentKecamatan, str5);
            TextViewBindingAdapter.setText(this.contentKelurahan, str2);
            TextViewBindingAdapter.setText(this.contentKodeTps, str7);
            TextViewBindingAdapter.setText(this.contentProvinsi, str4);
            TextViewBindingAdapter.setText(this.contentTps, str6);
            TextViewBindingAdapter.setText(this.mboundView3, str);
        }
        if (i2 != 0) {
            DataBindingAdaptersKt.hidden(this.mboundView1, z4);
            DataBindingAdaptersKt.hidden(this.mboundView2, z2);
        }
        if ((j & 8) != 0) {
            TextViewBindingAdapter.setText(this.textAppVersion, BuildConfig.VERSION_NAME);
        }
    }
}
