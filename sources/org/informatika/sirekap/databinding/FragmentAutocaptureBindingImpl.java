package org.informatika.sirekap.databinding;

import android.graphics.Bitmap;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.autocapture.AutoCaptureViewModel;
import org.informatika.sirekap.ui.autocapture.PolygonView;
import org.opencv.core.Point;

/* loaded from: classes2.dex */
public class FragmentAutocaptureBindingImpl extends FragmentAutocaptureBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.camera_preview, 12);
        sparseIntArray.put(R.id.button_toggle_capture, 13);
        sparseIntArray.put(R.id.buttonCropReject, 14);
    }

    public FragmentAutocaptureBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }

    private FragmentAutocaptureBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 10, (TextView) bindings[5], (CircularProgressIndicator) bindings[11], (ConstraintLayout) bindings[0], (MaterialButton) bindings[2], (ImageView) bindings[9], (ImageView) bindings[14], (MaterialButton) bindings[3], (MaterialButtonToggleGroup) bindings[13], (FrameLayout) bindings[12], (ImageView) bindings[7], (ConstraintLayout) bindings[6], (TextView) bindings[10], (PolygonView) bindings[8], (ImageView) bindings[4]);
        this.mDirtyFlags = -1L;
        this.aprilTagHintText.setTag(null);
        this.autocaptureLoading.setTag(null);
        this.autocropContainer.setTag(null);
        this.buttonAuto.setTag(null);
        this.buttonCropOk.setTag(null);
        this.buttonManual.setTag(null);
        this.cropImageView.setTag(null);
        this.cropLayout.setTag(null);
        this.hintText.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag(null);
        this.omrPolygonView.setTag(null);
        this.shutter.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2048L;
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
        if (87 == variableId) {
            setViewModel((AutoCaptureViewModel) variable);
            return true;
        }
        return false;
    }

    @Override // org.informatika.sirekap.databinding.FragmentAutocaptureBinding
    public void setViewModel(AutoCaptureViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized (this) {
            this.mDirtyFlags |= FileUtils.ONE_KB;
        }
        notifyPropertyChanged(87);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeViewModelAprilTagValid((MutableLiveData) object, fieldId);
            case 1:
                return onChangeViewModelHintText((MutableLiveData) object, fieldId);
            case 2:
                return onChangeViewModelImageProcessingUseCasePolygonPoints((MutableLiveData) object, fieldId);
            case 3:
                return onChangeViewModelHintValid((MutableLiveData) object, fieldId);
            case 4:
                return onChangeViewModelHintAprilTag((MutableLiveData) object, fieldId);
            case 5:
                return onChangeViewModelShutterShown((MutableLiveData) object, fieldId);
            case 6:
                return onChangeViewModelImageProcessingUseCaseDisplayImageBitmap((MutableLiveData) object, fieldId);
            case 7:
                return onChangeViewModelIsProcessingImage((MutableLiveData) object, fieldId);
            case 8:
                return onChangeViewModelUseAutoCapture((MutableLiveData) object, fieldId);
            case 9:
                return onChangeViewModelImageProcessingUseCaseIsPictureTaken((MutableLiveData) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelAprilTagValid(MutableLiveData<Boolean> ViewModelAprilTagValid, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelHintText(MutableLiveData<String> ViewModelHintText, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelImageProcessingUseCasePolygonPoints(MutableLiveData<Map<Integer, Point>> ViewModelImageProcessingUseCasePolygonPoints, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelHintValid(MutableLiveData<Boolean> ViewModelHintValid, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelHintAprilTag(MutableLiveData<String> ViewModelHintAprilTag, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelShutterShown(MutableLiveData<Boolean> ViewModelShutterShown, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelImageProcessingUseCaseDisplayImageBitmap(MutableLiveData<Bitmap> ViewModelImageProcessingUseCaseDisplayImageBitmap, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelIsProcessingImage(MutableLiveData<Boolean> ViewModelIsProcessingImage, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelUseAutoCapture(MutableLiveData<Boolean> ViewModelUseAutoCapture, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewModelImageProcessingUseCaseIsPictureTaken(MutableLiveData<Boolean> ViewModelImageProcessingUseCaseIsPictureTaken, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:177:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x01c7  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 639
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.informatika.sirekap.databinding.FragmentAutocaptureBindingImpl.executeBindings():void");
    }
}
