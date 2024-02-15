package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.chip.Chip;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.ElectionImage;

/* loaded from: classes2.dex */
public abstract class ItemElectionImageBinding extends ViewDataBinding {
    public final Chip chipStatus;
    public final ImageView image;
    public final TextView labelElectionPageNumber;
    public final TextView labelElectionPemilihan;
    public final TextView labelInvalidReason;
    public final TextView labelName;
    @Bindable
    protected ElectionImage mElectionImage;

    public abstract void setElectionImage(ElectionImage electionImage);

    public ItemElectionImageBinding(Object _bindingComponent, View _root, int _localFieldCount, Chip chipStatus, ImageView image, TextView labelElectionPageNumber, TextView labelElectionPemilihan, TextView labelInvalidReason, TextView labelName) {
        super(_bindingComponent, _root, _localFieldCount);
        this.chipStatus = chipStatus;
        this.image = image;
        this.labelElectionPageNumber = labelElectionPageNumber;
        this.labelElectionPemilihan = labelElectionPemilihan;
        this.labelInvalidReason = labelInvalidReason;
        this.labelName = labelName;
    }

    public ElectionImage getElectionImage() {
        return this.mElectionImage;
    }

    public static ItemElectionImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemElectionImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemElectionImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_election_image, root, attachToRoot, component);
    }

    public static ItemElectionImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemElectionImageBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemElectionImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_election_image, null, false, component);
    }

    public static ItemElectionImageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemElectionImageBinding bind(View view, Object component) {
        return (ItemElectionImageBinding) bind(component, view, R.layout.item_election_image);
    }
}
