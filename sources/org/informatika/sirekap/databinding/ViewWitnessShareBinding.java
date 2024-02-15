package org.informatika.sirekap.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase;

/* loaded from: classes2.dex */
public abstract class ViewWitnessShareBinding extends ViewDataBinding {
    public final Button buttonCopyUrl;
    public final Button buttonShareUrl;
    public final MaterialCardView cardAlert;
    public final MaterialCardView cardAlert2;
    public final MaterialCardView cardUrl;
    public final ConstraintLayout layoutContent;
    @Bindable
    protected ShareWitnessUrlUseCase mShareWitnessUrlUseCase;
    public final MaterialCardView materialCardView;
    public final ImageView witnessImageViewQr;
    public final TextView witnessShareElectionType;
    public final TextView witnessShareElectionTypeValue;
    public final TextView witnessShareGetLinkHint;
    public final TextView witnessSharePartai;
    public final TextView witnessSharePartaiValue;
    public final TextView witnessSharePaslon;
    public final TextView witnessSharePaslonValue;
    public final View witnessShareSeperator;

    public abstract void setShareWitnessUrlUseCase(ShareWitnessUrlUseCase shareWitnessUrlUseCase);

    public ViewWitnessShareBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonCopyUrl, Button buttonShareUrl, MaterialCardView cardAlert, MaterialCardView cardAlert2, MaterialCardView cardUrl, ConstraintLayout layoutContent, MaterialCardView materialCardView, ImageView witnessImageViewQr, TextView witnessShareElectionType, TextView witnessShareElectionTypeValue, TextView witnessShareGetLinkHint, TextView witnessSharePartai, TextView witnessSharePartaiValue, TextView witnessSharePaslon, TextView witnessSharePaslonValue, View witnessShareSeperator) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonCopyUrl = buttonCopyUrl;
        this.buttonShareUrl = buttonShareUrl;
        this.cardAlert = cardAlert;
        this.cardAlert2 = cardAlert2;
        this.cardUrl = cardUrl;
        this.layoutContent = layoutContent;
        this.materialCardView = materialCardView;
        this.witnessImageViewQr = witnessImageViewQr;
        this.witnessShareElectionType = witnessShareElectionType;
        this.witnessShareElectionTypeValue = witnessShareElectionTypeValue;
        this.witnessShareGetLinkHint = witnessShareGetLinkHint;
        this.witnessSharePartai = witnessSharePartai;
        this.witnessSharePartaiValue = witnessSharePartaiValue;
        this.witnessSharePaslon = witnessSharePaslon;
        this.witnessSharePaslonValue = witnessSharePaslonValue;
        this.witnessShareSeperator = witnessShareSeperator;
    }

    public ShareWitnessUrlUseCase getShareWitnessUrlUseCase() {
        return this.mShareWitnessUrlUseCase;
    }

    public static ViewWitnessShareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessShareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewWitnessShareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_witness_share, root, attachToRoot, component);
    }

    public static ViewWitnessShareBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessShareBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewWitnessShareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_witness_share, null, false, component);
    }

    public static ViewWitnessShareBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessShareBinding bind(View view, Object component) {
        return (ViewWitnessShareBinding) bind(component, view, R.layout.view_witness_share);
    }
}
