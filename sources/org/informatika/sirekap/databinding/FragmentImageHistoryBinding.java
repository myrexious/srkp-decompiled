package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.imageHistory.ImageHistoryViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentImageHistoryBinding extends ViewDataBinding {
    public final AutoCompleteTextView autocompleteFilterElectionPemilihan;
    public final Chip chipFilterInvalid;
    public final Chip chipFilterNotSent;
    public final Chip chipFilterProcessed;
    public final Chip chipFilterScanned;
    public final Chip chipFilterValid;
    public final HorizontalScrollView filterData;
    public final HorizontalScrollView filterData2;
    public final TextInputLayout filterElectionPemilihan;
    public final ConstraintLayout layoutFilterData;
    public final RecyclerView listData;
    @Bindable
    protected ImageHistoryViewModel mViewModel;
    public final TextView textDataEmptyTitle;
    public final TextView titleFilterDeliveryStatus;
    public final TextView titleFilterElectionPemilihan;
    public final TextView titleFilterScanResult;

    public abstract void setViewModel(ImageHistoryViewModel viewModel);

    public FragmentImageHistoryBinding(Object _bindingComponent, View _root, int _localFieldCount, AutoCompleteTextView autocompleteFilterElectionPemilihan, Chip chipFilterInvalid, Chip chipFilterNotSent, Chip chipFilterProcessed, Chip chipFilterScanned, Chip chipFilterValid, HorizontalScrollView filterData, HorizontalScrollView filterData2, TextInputLayout filterElectionPemilihan, ConstraintLayout layoutFilterData, RecyclerView listData, TextView textDataEmptyTitle, TextView titleFilterDeliveryStatus, TextView titleFilterElectionPemilihan, TextView titleFilterScanResult) {
        super(_bindingComponent, _root, _localFieldCount);
        this.autocompleteFilterElectionPemilihan = autocompleteFilterElectionPemilihan;
        this.chipFilterInvalid = chipFilterInvalid;
        this.chipFilterNotSent = chipFilterNotSent;
        this.chipFilterProcessed = chipFilterProcessed;
        this.chipFilterScanned = chipFilterScanned;
        this.chipFilterValid = chipFilterValid;
        this.filterData = filterData;
        this.filterData2 = filterData2;
        this.filterElectionPemilihan = filterElectionPemilihan;
        this.layoutFilterData = layoutFilterData;
        this.listData = listData;
        this.textDataEmptyTitle = textDataEmptyTitle;
        this.titleFilterDeliveryStatus = titleFilterDeliveryStatus;
        this.titleFilterElectionPemilihan = titleFilterElectionPemilihan;
        this.titleFilterScanResult = titleFilterScanResult;
    }

    public ImageHistoryViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentImageHistoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentImageHistoryBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentImageHistoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_image_history, root, attachToRoot, component);
    }

    public static FragmentImageHistoryBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentImageHistoryBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentImageHistoryBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_image_history, null, false, component);
    }

    public static FragmentImageHistoryBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentImageHistoryBinding bind(View view, Object component) {
        return (FragmentImageHistoryBinding) bind(component, view, R.layout.fragment_image_history);
    }
}
