package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.witness.qrCode.WitnessQrCodeViewModel;

/* loaded from: classes2.dex */
public abstract class FragmentWitnessQrCodeBinding extends ViewDataBinding {
    public final Button buttonSync;
    public final MaterialCardView cardWitnessInfo;
    public final MaterialCardView cardWitnessSync;
    public final TextView labelWitnessName;
    public final TextView labelWitnessNik;
    public final TextView labelWitnessPhone;
    public final TextView labelWitnessType;
    @Bindable
    protected WitnessQrCodeViewModel mViewModel;
    public final ViewWitnessShareBinding shareDpdCard;
    public final ViewWitnessShareBinding shareDprCard;
    public final ViewWitnessShareBinding shareDprdkCard;
    public final ViewWitnessShareBinding shareDprdpCard;
    public final ViewWitnessShareBinding sharePilpresCard;
    public final ViewWitnessShareBinding sharePkwkkCard;
    public final ViewWitnessShareBinding sharePkwkpCard;
    public final TextView titleInfo;
    public final TextView valueWitnessName;
    public final TextView valueWitnessNik;
    public final TextView valueWitnessPhone;
    public final TextView valueWitnessType;
    public final LinearLayout witnessShareView;

    public abstract void setViewModel(WitnessQrCodeViewModel viewModel);

    public FragmentWitnessQrCodeBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonSync, MaterialCardView cardWitnessInfo, MaterialCardView cardWitnessSync, TextView labelWitnessName, TextView labelWitnessNik, TextView labelWitnessPhone, TextView labelWitnessType, ViewWitnessShareBinding shareDpdCard, ViewWitnessShareBinding shareDprCard, ViewWitnessShareBinding shareDprdkCard, ViewWitnessShareBinding shareDprdpCard, ViewWitnessShareBinding sharePilpresCard, ViewWitnessShareBinding sharePkwkkCard, ViewWitnessShareBinding sharePkwkpCard, TextView titleInfo, TextView valueWitnessName, TextView valueWitnessNik, TextView valueWitnessPhone, TextView valueWitnessType, LinearLayout witnessShareView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonSync = buttonSync;
        this.cardWitnessInfo = cardWitnessInfo;
        this.cardWitnessSync = cardWitnessSync;
        this.labelWitnessName = labelWitnessName;
        this.labelWitnessNik = labelWitnessNik;
        this.labelWitnessPhone = labelWitnessPhone;
        this.labelWitnessType = labelWitnessType;
        this.shareDpdCard = shareDpdCard;
        this.shareDprCard = shareDprCard;
        this.shareDprdkCard = shareDprdkCard;
        this.shareDprdpCard = shareDprdpCard;
        this.sharePilpresCard = sharePilpresCard;
        this.sharePkwkkCard = sharePkwkkCard;
        this.sharePkwkpCard = sharePkwkpCard;
        this.titleInfo = titleInfo;
        this.valueWitnessName = valueWitnessName;
        this.valueWitnessNik = valueWitnessNik;
        this.valueWitnessPhone = valueWitnessPhone;
        this.valueWitnessType = valueWitnessType;
        this.witnessShareView = witnessShareView;
    }

    public WitnessQrCodeViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentWitnessQrCodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessQrCodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWitnessQrCodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness_qr_code, root, attachToRoot, component);
    }

    public static FragmentWitnessQrCodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessQrCodeBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWitnessQrCodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_witness_qr_code, null, false, component);
    }

    public static FragmentWitnessQrCodeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWitnessQrCodeBinding bind(View view, Object component) {
        return (FragmentWitnessQrCodeBinding) bind(component, view, R.layout.fragment_witness_qr_code);
    }
}
