package org.informatika.sirekap.databinding;

import android.graphics.Bitmap;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.Witness;
import org.informatika.sirekap.model.WitnessShare;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.DataBindingAdaptersKt;

/* loaded from: classes2.dex */
public class ViewWitnessSharePpsBindingImpl extends ViewWitnessSharePpsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView13;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.button_online, 15);
        sparseIntArray.put(R.id.button_offline, 16);
        sparseIntArray.put(R.id.witness_share_pps_election_type, 17);
        sparseIntArray.put(R.id.witness_share_pps_separator, 18);
    }

    public ViewWitnessSharePpsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }

    private ViewWitnessSharePpsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (Button) bindings[14], (MaterialButton) bindings[16], (MaterialButton) bindings[15], (Button) bindings[10], (MaterialButtonToggleGroup) bindings[2], (MaterialCardView) bindings[1], (MaterialCardView) bindings[12], (MaterialCardView) bindings[3], (ImageView) bindings[11], (TextView) bindings[17], (TextView) bindings[4], (TextView) bindings[9], (TextView) bindings[7], (TextView) bindings[8], (TextView) bindings[5], (TextView) bindings[6], (View) bindings[18]);
        this.mDirtyFlags = -1L;
        this.buttonCopyUrl.setTag(null);
        this.buttonShareUrl.setTag(null);
        this.buttonToggleImage.setTag(null);
        this.cardAlert2.setTag(null);
        this.cardUrl.setTag(null);
        this.materialCardView2.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        TextView textView = (TextView) bindings[13];
        this.mboundView13 = textView;
        textView.setTag(null);
        this.witnessImageViewQr.setTag(null);
        this.witnessSharePpsElectionTypeValue.setTag(null);
        this.witnessSharePpsGetUrl.setTag(null);
        this.witnessSharePpsPartai.setTag(null);
        this.witnessSharePpsPartaiValue.setTag(null);
        this.witnessSharePpsPaslon.setTag(null);
        this.witnessSharePpsPaslonValue.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128L;
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
        if (77 == variableId) {
            setPaslon((String) variable);
        } else if (13 == variableId) {
            setFilterJenisPemilihan((String) variable);
        } else if (47 == variableId) {
            setIsZipped((Boolean) variable);
        } else if (78 == variableId) {
            setQrCode((Bitmap) variable);
        } else if (84 == variableId) {
            setUrl((String) variable);
        } else if (88 == variableId) {
            setWitness((WitnessWithShare) variable);
        } else if (76 != variableId) {
            return false;
        } else {
            setPartai((String) variable);
        }
        return true;
    }

    @Override // org.informatika.sirekap.databinding.ViewWitnessSharePpsBinding
    public void setPaslon(String Paslon) {
        this.mPaslon = Paslon;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(77);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ViewWitnessSharePpsBinding
    public void setFilterJenisPemilihan(String FilterJenisPemilihan) {
        this.mFilterJenisPemilihan = FilterJenisPemilihan;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(13);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ViewWitnessSharePpsBinding
    public void setIsZipped(Boolean IsZipped) {
        this.mIsZipped = IsZipped;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(47);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ViewWitnessSharePpsBinding
    public void setQrCode(Bitmap QrCode) {
        this.mQrCode = QrCode;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(78);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ViewWitnessSharePpsBinding
    public void setUrl(String Url) {
        this.mUrl = Url;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(84);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ViewWitnessSharePpsBinding
    public void setWitness(WitnessWithShare Witness) {
        this.mWitness = Witness;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(88);
        super.requestRebind();
    }

    @Override // org.informatika.sirekap.databinding.ViewWitnessSharePpsBinding
    public void setPartai(String Partai) {
        this.mPartai = Partai;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(76);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        boolean z3;
        String str;
        boolean z4;
        boolean z5;
        byte b;
        byte b2;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        String str2;
        boolean z10;
        boolean z11;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str3 = this.mPaslon;
        String str4 = this.mFilterJenisPemilihan;
        Boolean bool = this.mIsZipped;
        Bitmap bitmap = this.mQrCode;
        String str5 = this.mUrl;
        WitnessWithShare witnessWithShare = this.mWitness;
        String str6 = this.mPartai;
        boolean z12 = (j & 129) != 0 && str3 == null;
        int i = ((j & 166) > 0L ? 1 : ((j & 166) == 0L ? 0 : -1));
        WitnessShare witnessShare = null;
        r32 = null;
        String str7 = null;
        if (i != 0) {
            if ((j & 162) != 0) {
                z11 = !(witnessWithShare != null ? witnessWithShare.isJenisPemilihan(str4) : false);
            } else {
                z11 = false;
            }
            WitnessShare witnessShare2 = witnessWithShare != null ? witnessWithShare.getWitnessShare(str4) : null;
            z3 = witnessShare2 != null ? witnessShare2.isShared() : false;
            if (i != 0) {
                j = z3 ? j | 512 : j | 256;
            }
            z2 = !z3;
            if ((j & 166) != 0) {
                j = z2 ? j | 8192 : j | 4096;
            }
            if ((j & 160) != 0) {
                Witness witness = witnessWithShare != null ? witnessWithShare.getWitness() : null;
                if (witness != null) {
                    str7 = witness.getNama();
                }
            }
            str = str7;
            z = z11;
            witnessShare = witnessShare2;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            str = null;
        }
        if ((j & 132) != 0) {
            z4 = !ViewDataBinding.safeUnbox(bool);
            z5 = ViewDataBinding.safeUnbox(Boolean.valueOf(z4));
        } else {
            z4 = false;
            z5 = false;
        }
        int i2 = ((j & 170) > 0L ? 1 : ((j & 170) == 0L ? 0 : -1));
        if (i2 != 0) {
            b = bitmap == null ? (byte) 1 : (byte) 0;
            if (i2 != 0) {
                j |= b != 0 ? 32768L : 16384L;
            }
        } else {
            b = 0;
        }
        int i3 = ((j & 178) > 0L ? 1 : ((j & 178) == 0L ? 0 : -1));
        if (i3 != 0) {
            b2 = str5 == null ? (byte) 1 : (byte) 0;
            if (i3 != 0) {
                j |= b2 != 0 ? 2048L : FileUtils.ONE_KB;
            }
        } else {
            b2 = 0;
        }
        if ((j & 192) != 0) {
            z6 = str6 == null;
        } else {
            z6 = false;
        }
        if ((j & 17408) != 0) {
            if (witnessWithShare != null) {
                witnessShare = witnessWithShare.getWitnessShare(str4);
            }
            if (witnessShare != null) {
                z3 = witnessShare.isShared();
            }
            if ((j & 166) != 0) {
                j = z3 ? j | 512 : j | 256;
            }
            z2 = !z3;
            if ((j & 166) != 0) {
                j = z2 ? j | 8192 : j | 4096;
            }
        }
        boolean z13 = z2;
        boolean z14 = z3;
        int i4 = ((j & 178) > 0L ? 1 : ((j & 178) == 0L ? 0 : -1));
        if (i4 != 0) {
            z7 = b2 != 0 ? true : z13;
        } else {
            z7 = false;
        }
        int i5 = ((j & 170) > 0L ? 1 : ((j & 170) == 0L ? 0 : -1));
        if (i5 != 0) {
            z8 = b != 0 ? true : z13;
        } else {
            z8 = false;
        }
        if ((j & 4352) != 0) {
            z4 = !ViewDataBinding.safeUnbox(bool);
        }
        int i6 = ((j & 166) > 0L ? 1 : ((j & 166) == 0L ? 0 : -1));
        if (i6 != 0) {
            boolean z15 = z14 ? true : z4;
            z9 = z13 ? true : z4;
            boolean z16 = z15;
            str2 = str3;
            z10 = z16;
        } else {
            z9 = false;
            str2 = str3;
            z10 = false;
        }
        if ((j & 162) != 0) {
            DataBindingAdaptersKt.hidden(this.buttonCopyUrl, z13);
            DataBindingAdaptersKt.hidden(this.buttonShareUrl, z14);
            DataBindingAdaptersKt.hidden(this.mboundView0, z);
            DataBindingAdaptersKt.hidden(this.witnessSharePpsGetUrl, z14);
        }
        if (i6 != 0) {
            DataBindingAdaptersKt.hidden(this.buttonToggleImage, z9);
            DataBindingAdaptersKt.hidden(this.cardAlert2, z10);
        }
        if (i4 != 0) {
            DataBindingAdaptersKt.hidden(this.cardUrl, z7);
        }
        if ((j & 132) != 0) {
            DataBindingAdaptersKt.hidden(this.materialCardView2, z5);
        }
        if ((144 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView13, str5);
        }
        if ((136 & j) != 0) {
            DataBindingAdaptersKt.bitmap(this.witnessImageViewQr, bitmap);
        }
        if (i5 != 0) {
            DataBindingAdaptersKt.hidden(this.witnessImageViewQr, z8);
        }
        if ((j & 160) != 0) {
            TextViewBindingAdapter.setText(this.witnessSharePpsElectionTypeValue, str);
        }
        if ((j & 192) != 0) {
            boolean z17 = z6;
            DataBindingAdaptersKt.hidden(this.witnessSharePpsPartai, z17);
            DataBindingAdaptersKt.hidden(this.witnessSharePpsPartaiValue, z17);
            TextViewBindingAdapter.setText(this.witnessSharePpsPartaiValue, str6);
        }
        if ((j & 129) != 0) {
            DataBindingAdaptersKt.hidden(this.witnessSharePpsPaslon, z12);
            DataBindingAdaptersKt.hidden(this.witnessSharePpsPaslonValue, z12);
            TextViewBindingAdapter.setText(this.witnessSharePpsPaslonValue, str2);
        }
    }
}
