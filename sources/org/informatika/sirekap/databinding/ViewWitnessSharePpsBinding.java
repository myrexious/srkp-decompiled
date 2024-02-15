package org.informatika.sirekap.databinding;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.WitnessWithShare;

/* loaded from: classes2.dex */
public abstract class ViewWitnessSharePpsBinding extends ViewDataBinding {
    public final Button buttonCopyUrl;
    public final MaterialButton buttonOffline;
    public final MaterialButton buttonOnline;
    public final Button buttonShareUrl;
    public final MaterialButtonToggleGroup buttonToggleImage;
    public final MaterialCardView cardAlert2;
    public final MaterialCardView cardUrl;
    @Bindable
    protected String mFilterJenisPemilihan;
    @Bindable
    protected Boolean mIsZipped;
    @Bindable
    protected String mPartai;
    @Bindable
    protected String mPaslon;
    @Bindable
    protected Bitmap mQrCode;
    @Bindable
    protected String mUrl;
    @Bindable
    protected WitnessWithShare mWitness;
    public final MaterialCardView materialCardView2;
    public final ImageView witnessImageViewQr;
    public final TextView witnessSharePpsElectionType;
    public final TextView witnessSharePpsElectionTypeValue;
    public final TextView witnessSharePpsGetUrl;
    public final TextView witnessSharePpsPartai;
    public final TextView witnessSharePpsPartaiValue;
    public final TextView witnessSharePpsPaslon;
    public final TextView witnessSharePpsPaslonValue;
    public final View witnessSharePpsSeparator;

    public abstract void setFilterJenisPemilihan(String filterJenisPemilihan);

    public abstract void setIsZipped(Boolean isZipped);

    public abstract void setPartai(String partai);

    public abstract void setPaslon(String paslon);

    public abstract void setQrCode(Bitmap qrCode);

    public abstract void setUrl(String url);

    public abstract void setWitness(WitnessWithShare witness);

    public ViewWitnessSharePpsBinding(Object _bindingComponent, View _root, int _localFieldCount, Button buttonCopyUrl, MaterialButton buttonOffline, MaterialButton buttonOnline, Button buttonShareUrl, MaterialButtonToggleGroup buttonToggleImage, MaterialCardView cardAlert2, MaterialCardView cardUrl, MaterialCardView materialCardView2, ImageView witnessImageViewQr, TextView witnessSharePpsElectionType, TextView witnessSharePpsElectionTypeValue, TextView witnessSharePpsGetUrl, TextView witnessSharePpsPartai, TextView witnessSharePpsPartaiValue, TextView witnessSharePpsPaslon, TextView witnessSharePpsPaslonValue, View witnessSharePpsSeparator) {
        super(_bindingComponent, _root, _localFieldCount);
        this.buttonCopyUrl = buttonCopyUrl;
        this.buttonOffline = buttonOffline;
        this.buttonOnline = buttonOnline;
        this.buttonShareUrl = buttonShareUrl;
        this.buttonToggleImage = buttonToggleImage;
        this.cardAlert2 = cardAlert2;
        this.cardUrl = cardUrl;
        this.materialCardView2 = materialCardView2;
        this.witnessImageViewQr = witnessImageViewQr;
        this.witnessSharePpsElectionType = witnessSharePpsElectionType;
        this.witnessSharePpsElectionTypeValue = witnessSharePpsElectionTypeValue;
        this.witnessSharePpsGetUrl = witnessSharePpsGetUrl;
        this.witnessSharePpsPartai = witnessSharePpsPartai;
        this.witnessSharePpsPartaiValue = witnessSharePpsPartaiValue;
        this.witnessSharePpsPaslon = witnessSharePpsPaslon;
        this.witnessSharePpsPaslonValue = witnessSharePpsPaslonValue;
        this.witnessSharePpsSeparator = witnessSharePpsSeparator;
    }

    public WitnessWithShare getWitness() {
        return this.mWitness;
    }

    public String getFilterJenisPemilihan() {
        return this.mFilterJenisPemilihan;
    }

    public Boolean getIsZipped() {
        return this.mIsZipped;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getPaslon() {
        return this.mPaslon;
    }

    public String getPartai() {
        return this.mPartai;
    }

    public Bitmap getQrCode() {
        return this.mQrCode;
    }

    public static ViewWitnessSharePpsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessSharePpsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ViewWitnessSharePpsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_witness_share_pps, root, attachToRoot, component);
    }

    public static ViewWitnessSharePpsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessSharePpsBinding inflate(LayoutInflater inflater, Object component) {
        return (ViewWitnessSharePpsBinding) ViewDataBinding.inflateInternal(inflater, R.layout.view_witness_share_pps, null, false, component);
    }

    public static ViewWitnessSharePpsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ViewWitnessSharePpsBinding bind(View view, Object component) {
        return (ViewWitnessSharePpsBinding) bind(component, view, R.layout.view_witness_share_pps);
    }
}
