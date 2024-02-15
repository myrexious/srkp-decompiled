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
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.witness.WitnessViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentWitnessBinding extends ViewDataBinding {
    public final FloatingActionButton addWitnessButton;
    public final Chip chipFilterDpd;
    public final Chip chipFilterDpr;
    public final Chip chipFilterDprdk;
    public final Chip chipFilterDprdp;
    public final Chip chipFilterPilpres;
    public final HorizontalScrollView filterPemilihan;
    public final ConstraintLayout layoutFilterPemilihan;
    public final RecyclerView listWitness;
    @Bindable
    protected WitnessViewModel mViewModel;
    public final View saksiDivider;
    public final TextView textView;
    public final TextView textView2;
    public final TextView textWitnessEmptyTitle;
    public final TextView textWitnessEmptyTitle2;
    public final TextView titleFilterPemilihan;

    public abstract void setViewModel(WitnessViewModel viewModel);

    public FragmentWitnessBinding(Object _bindingComponent, View _root, int _localFieldCount, FloatingActionButton addWitnessButton, Chip chipFilterDpd, Chip chipFilterDpr, Chip chipFilterDprdk, Chip chipFilterDprdp, Chip chipFilterPilpres, HorizontalScrollView filterPemilihan, ConstraintLayout layoutFilterPemilihan, RecyclerView listWitness, View saksiDivider, TextView textView, TextView textView2, TextView textWitnessEmptyTitle, TextView textWitnessEmptyTitle2, TextView titleFilterPemilihan) {
        super(_bindingComponent, _root, _localFieldCount);
        this.addWitnessButton = addWitnessButton;
        this.chipFilterDpd = chipFilterDpd;
        this.chipFilterDpr = chipFilterDpr;
        this.chipFilterDprdk = chipFilterDprdk;
        this.chipFilterDprdp = chipFilterDprdp;
        this.chipFilterPilpres = chipFilterPilpres;
        this.filterPemilihan = filterPemilihan;
        this.layoutFilterPemilihan = layoutFilterPemilihan;
        this.listWitness = listWitness;
        this.saksiDivider = saksiDivider;
        this.textView = textView;
        this.textView2 = textView2;
        this.textWitnessEmptyTitle = textWitnessEmptyTitle;
        this.textWitnessEmptyTitle2 = textWitnessEmptyTitle2;
        this.titleFilterPemilihan = titleFilterPemilihan;
    }

    public WitnessViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentWitnessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWitnessBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness, root, attachToRoot, component);
    }

    public static FragmentWitnessBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWitnessBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness, null, false, component);
    }

    public static FragmentWitnessBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessBinding bind(View view, Object component) {
        return (FragmentWitnessBinding) bind(component, view, R.layout.fragment_witness);
    }
}
