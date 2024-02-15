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
import androidx.lifecycle.LiveData;
import com.google.android.material.card.MaterialCardView;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.model.WitnessWithShare;
import org.informatika.sirekap.support.livedata.CombinedLiveData;
import org.informatika.sirekap.ui.witness.qrCode.ShareWitnessUrlUseCase;

/* loaded from: classes2.dex */
public class ViewWitnessShareBindingImpl extends ViewWitnessShareBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView16;
    private final TextView mboundView2;
    private final TextView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.materialCardView, 18);
        sparseIntArray.put(R.id.witness_share_election_type, 19);
    }

    public ViewWitnessShareBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }

    private ViewWitnessShareBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 11, (Button) bindings[17], (Button) bindings[13], (MaterialCardView) bindings[1], (MaterialCardView) bindings[4], (MaterialCardView) bindings[15], (ConstraintLayout) bindings[3], (MaterialCardView) bindings[18], (ImageView) bindings[14], (TextView) bindings[19], (TextView) bindings[6], (TextView) bindings[12], (TextView) bindings[9], (TextView) bindings[10], (TextView) bindings[7], (TextView) bindings[8], (View) bindings[11]);
        this.mDirtyFlags = -1L;
        this.buttonCopyUrl.setTag(null);
        this.buttonShareUrl.setTag(null);
        this.cardAlert.setTag(null);
        this.cardAlert2.setTag(null);
        this.cardUrl.setTag(null);
        this.layoutContent.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        TextView textView = (TextView) bindings[16];
        this.mboundView16 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
        this.witnessImageViewQr.setTag(null);
        this.witnessShareElectionTypeValue.setTag(null);
        this.witnessShareGetLinkHint.setTag(null);
        this.witnessSharePartai.setTag(null);
        this.witnessSharePartaiValue.setTag(null);
        this.witnessSharePaslon.setTag(null);
        this.witnessSharePaslonValue.setTag(null);
        this.witnessShareSeperator.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4096L;
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
        if (79 == variableId) {
            setShareWitnessUrlUseCase((ShareWitnessUrlUseCase) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.ViewWitnessShareBinding
    public void setShareWitnessUrlUseCase(ShareWitnessUrlUseCase ShareWitnessUrlUseCase) {
        this.mShareWitnessUrlUseCase = ShareWitnessUrlUseCase;
        synchronized (this) {
            this.mDirtyFlags |= 2048;
        }
        notifyPropertyChanged(79);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeShareWitnessUrlUseCaseIsMultipleElections((LiveData) object, fieldId);
            case 1:
                return onChangeShareWitnessUrlUseCaseQrCodeBitmap((LiveData) object, fieldId);
            case 2:
                return onChangeShareWitnessUrlUseCaseWitness((LiveData) object, fieldId);
            case 3:
                return onChangeShareWitnessUrlUseCasePartaiString((LiveData) object, fieldId);
            case 4:
                return onChangeShareWitnessUrlUseCaseStringElectionTypeName((LiveData) object, fieldId);
            case 5:
                return onChangeShareWitnessUrlUseCaseStringWarningUnzipped((LiveData) object, fieldId);
            case 6:
                return onChangeShareWitnessUrlUseCasePaslonString((LiveData) object, fieldId);
            case 7:
                return onChangeShareWitnessUrlUseCaseIsUploadedPdf((LiveData) object, fieldId);
            case 8:
                return onChangeShareWitnessUrlUseCaseStringShareInstruction((LiveData) object, fieldId);
            case 9:
                return onChangeShareWitnessUrlUseCaseStringWarningUnshared((LiveData) object, fieldId);
            case 10:
                return onChangeShareWitnessUrlUseCaseUrl((CombinedLiveData) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeShareWitnessUrlUseCaseIsMultipleElections(LiveData<Boolean> ShareWitnessUrlUseCaseIsMultipleElections, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareWitnessUrlUseCaseQrCodeBitmap(LiveData<Bitmap> ShareWitnessUrlUseCaseQrCodeBitmap, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareWitnessUrlUseCaseWitness(LiveData<WitnessWithShare> ShareWitnessUrlUseCaseWitness, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareWitnessUrlUseCasePartaiString(LiveData<String> ShareWitnessUrlUseCasePartaiString, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareWitnessUrlUseCaseStringElectionTypeName(LiveData<String> ShareWitnessUrlUseCaseStringElectionTypeName, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareWitnessUrlUseCaseStringWarningUnzipped(LiveData<String> ShareWitnessUrlUseCaseStringWarningUnzipped, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareWitnessUrlUseCasePaslonString(LiveData<String> ShareWitnessUrlUseCasePaslonString, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareWitnessUrlUseCaseIsUploadedPdf(LiveData<Boolean> ShareWitnessUrlUseCaseIsUploadedPdf, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareWitnessUrlUseCaseStringShareInstruction(LiveData<String> ShareWitnessUrlUseCaseStringShareInstruction, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareWitnessUrlUseCaseStringWarningUnshared(LiveData<String> ShareWitnessUrlUseCaseStringWarningUnshared, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeShareWitnessUrlUseCaseUrl(CombinedLiveData<String> ShareWitnessUrlUseCaseUrl, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= FileUtils.ONE_KB;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:329:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:502:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:509:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 1125
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.ViewWitnessShareBindingImpl.executeBindings():void");
    }
}
