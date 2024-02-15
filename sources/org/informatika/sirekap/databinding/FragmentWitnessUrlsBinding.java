package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.witness.qrCode.WitnessUrlListViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentWitnessUrlsBinding extends ViewDataBinding {
    public final MaterialCardView cardAlert;
    public final Chip chipFilterDpd;
    public final Chip chipFilterDpr;
    public final Chip chipFilterDprdk;
    public final Chip chipFilterDprdp;
    public final Chip chipFilterPilgub;
    public final Chip chipFilterPilpres;
    public final Chip chipFilterPilwabup;
    public final HorizontalScrollView filterPemilihan;
    public final ConstraintLayout layoutFilterPemilihan;
    public final RecyclerView listWitness;
    @Bindable
    protected WitnessUrlListViewModel mViewModel;
    public final View saksiDivider;
    public final TextView textView;
    public final TextView textWitnessEmptyTitle;
    public final TextView textWitnessEmptyTitle2;
    public final TextView titleFilterPemilihan;

    public abstract void setViewModel(WitnessUrlListViewModel viewModel);

    public FragmentWitnessUrlsBinding(Object _bindingComponent, View _root, int _localFieldCount, MaterialCardView cardAlert, Chip chipFilterDpd, Chip chipFilterDpr, Chip chipFilterDprdk, Chip chipFilterDprdp, Chip chipFilterPilgub, Chip chipFilterPilpres, Chip chipFilterPilwabup, HorizontalScrollView filterPemilihan, ConstraintLayout layoutFilterPemilihan, RecyclerView listWitness, View saksiDivider, TextView textView, TextView textWitnessEmptyTitle, TextView textWitnessEmptyTitle2, TextView titleFilterPemilihan) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cardAlert = cardAlert;
        this.chipFilterDpd = chipFilterDpd;
        this.chipFilterDpr = chipFilterDpr;
        this.chipFilterDprdk = chipFilterDprdk;
        this.chipFilterDprdp = chipFilterDprdp;
        this.chipFilterPilgub = chipFilterPilgub;
        this.chipFilterPilpres = chipFilterPilpres;
        this.chipFilterPilwabup = chipFilterPilwabup;
        this.filterPemilihan = filterPemilihan;
        this.layoutFilterPemilihan = layoutFilterPemilihan;
        this.listWitness = listWitness;
        this.saksiDivider = saksiDivider;
        this.textView = textView;
        this.textWitnessEmptyTitle = textWitnessEmptyTitle;
        this.textWitnessEmptyTitle2 = textWitnessEmptyTitle2;
        this.titleFilterPemilihan = titleFilterPemilihan;
    }

    public WitnessUrlListViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentWitnessUrlsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessUrlsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWitnessUrlsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness_urls, root, attachToRoot, component);
    }

    public static FragmentWitnessUrlsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessUrlsBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWitnessUrlsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness_urls, null, false, component);
    }

    public static FragmentWitnessUrlsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessUrlsBinding bind(View view, Object component) {
        return (FragmentWitnessUrlsBinding) bind(component, view, R.layout.fragment_witness_urls);
    }
}
