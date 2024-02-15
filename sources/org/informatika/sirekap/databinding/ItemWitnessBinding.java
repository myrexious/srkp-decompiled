package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.WitnessWithShare;

/* loaded from: classes2.dex */
public abstract class ItemWitnessBinding extends ViewDataBinding {
    public final TextView candidateName;
    public final LinearLayout linearLayout;
    @Bindable
    protected Boolean mIsEmpty;
    @Bindable
    protected WitnessWithShare mWitness;
    public final ImageView saksiIcon;
    public final TextView saksiJenis;
    public final TextView saksiName;
    public final ImageView syncFailedIcon;

    public abstract void setIsEmpty(Boolean isEmpty);

    public abstract void setWitness(WitnessWithShare witness);

    public ItemWitnessBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView candidateName, LinearLayout linearLayout, ImageView saksiIcon, TextView saksiJenis, TextView saksiName, ImageView syncFailedIcon) {
        super(_bindingComponent, _root, _localFieldCount);
        this.candidateName = candidateName;
        this.linearLayout = linearLayout;
        this.saksiIcon = saksiIcon;
        this.saksiJenis = saksiJenis;
        this.saksiName = saksiName;
        this.syncFailedIcon = syncFailedIcon;
    }

    public Boolean getIsEmpty() {
        return this.mIsEmpty;
    }

    public WitnessWithShare getWitness() {
        return this.mWitness;
    }

    public static ItemWitnessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWitnessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWitnessBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_witness, root, attachToRoot, component);
    }

    public static ItemWitnessBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWitnessBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemWitnessBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_witness, null, false, component);
    }

    public static ItemWitnessBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWitnessBinding bind(View view, Object component) {
        return (ItemWitnessBinding) bind(component, view, R.layout.item_witness);
    }
}
