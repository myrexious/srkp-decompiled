package com.labters.documentscanner;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.labters.documentscanner.ImageCropActivity;
import com.labters.documentscanner.base.CropperErrorType;
import com.labters.documentscanner.base.DocumentScanActivity;
import com.labters.documentscanner.helpers.ScannerConstants;
import com.labters.documentscanner.libraries.PolygonView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

/* loaded from: classes3.dex */
public class ImageCropActivity extends DocumentScanActivity {
    private Bitmap cropImage;
    private FrameLayout holderImageCrop;
    private ImageView imageView;
    private boolean isInverted;
    private PolygonView polygonView;
    private ProgressBar progressBar;
    private View.OnClickListener btnImageEnhanceClick = new AnonymousClass1();
    private View.OnClickListener btnRebase = new View.OnClickListener() { // from class: com.labters.documentscanner.ImageCropActivity$$ExternalSyntheticLambda0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ImageCropActivity.this.m255lambda$new$0$comlabtersdocumentscannerImageCropActivity(view);
        }
    };
    private View.OnClickListener btnCloseClick = new View.OnClickListener() { // from class: com.labters.documentscanner.ImageCropActivity$$ExternalSyntheticLambda1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ImageCropActivity.this.m256lambda$new$1$comlabtersdocumentscannerImageCropActivity(view);
        }
    };
    private View.OnClickListener btnInvertColor = new AnonymousClass2();
    private View.OnClickListener onRotateClick = new AnonymousClass3();

    /* renamed from: com.labters.documentscanner.ImageCropActivity$1 */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
            ImageCropActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageCropActivity.this.showProgressBar();
            ImageCropActivity.this.disposable.add(Observable.fromCallable(new Callable() { // from class: com.labters.documentscanner.ImageCropActivity$1$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return ImageCropActivity.AnonymousClass1.this.m257lambda$onClick$0$comlabtersdocumentscannerImageCropActivity$1();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.labters.documentscanner.ImageCropActivity$1$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ImageCropActivity.AnonymousClass1.this.m258lambda$onClick$1$comlabtersdocumentscannerImageCropActivity$1((Boolean) obj);
                }
            }));
        }

        /* renamed from: lambda$onClick$0$com-labters-documentscanner-ImageCropActivity$1 */
        public /* synthetic */ Boolean m257lambda$onClick$0$comlabtersdocumentscannerImageCropActivity$1() throws Exception {
            ImageCropActivity imageCropActivity = ImageCropActivity.this;
            imageCropActivity.cropImage = imageCropActivity.getCroppedImage();
            if (ImageCropActivity.this.cropImage != null && ScannerConstants.saveStorage) {
                ImageCropActivity imageCropActivity2 = ImageCropActivity.this;
                imageCropActivity2.saveToInternalStorage(imageCropActivity2.cropImage);
            }
            return false;
        }

        /* renamed from: lambda$onClick$1$com-labters-documentscanner-ImageCropActivity$1 */
        public /* synthetic */ void m258lambda$onClick$1$comlabtersdocumentscannerImageCropActivity$1(Boolean bool) throws Exception {
            ImageCropActivity.this.hideProgressBar();
            if (ImageCropActivity.this.cropImage != null) {
                ScannerConstants.selectedImageBitmap = ImageCropActivity.this.cropImage;
                ImageCropActivity.this.setResult(-1);
                ImageCropActivity.this.finish();
            }
        }
    }

    /* renamed from: lambda$new$0$com-labters-documentscanner-ImageCropActivity */
    public /* synthetic */ void m255lambda$new$0$comlabtersdocumentscannerImageCropActivity(View view) {
        this.cropImage = ScannerConstants.selectedImageBitmap.copy(ScannerConstants.selectedImageBitmap.getConfig(), true);
        this.isInverted = false;
        startCropping();
    }

    /* renamed from: com.labters.documentscanner.ImageCropActivity$2 */
    /* loaded from: classes3.dex */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
            ImageCropActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageCropActivity.this.showProgressBar();
            ImageCropActivity.this.disposable.add(Observable.fromCallable(new Callable() { // from class: com.labters.documentscanner.ImageCropActivity$2$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return ImageCropActivity.AnonymousClass2.this.m259lambda$onClick$0$comlabtersdocumentscannerImageCropActivity$2();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.labters.documentscanner.ImageCropActivity$2$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ImageCropActivity.AnonymousClass2.this.m260lambda$onClick$1$comlabtersdocumentscannerImageCropActivity$2((Boolean) obj);
                }
            }));
        }

        /* renamed from: lambda$onClick$0$com-labters-documentscanner-ImageCropActivity$2 */
        public /* synthetic */ Boolean m259lambda$onClick$0$comlabtersdocumentscannerImageCropActivity$2() throws Exception {
            ImageCropActivity.this.invertColor();
            return false;
        }

        /* renamed from: lambda$onClick$1$com-labters-documentscanner-ImageCropActivity$2 */
        public /* synthetic */ void m260lambda$onClick$1$comlabtersdocumentscannerImageCropActivity$2(Boolean bool) throws Exception {
            ImageCropActivity.this.hideProgressBar();
            ImageCropActivity imageCropActivity = ImageCropActivity.this;
            ImageCropActivity.this.imageView.setImageBitmap(imageCropActivity.scaledBitmap(imageCropActivity.cropImage, ImageCropActivity.this.holderImageCrop.getWidth(), ImageCropActivity.this.holderImageCrop.getHeight()));
        }
    }

    /* renamed from: lambda$new$1$com-labters-documentscanner-ImageCropActivity */
    public /* synthetic */ void m256lambda$new$1$comlabtersdocumentscannerImageCropActivity(View view) {
        finish();
    }

    /* renamed from: com.labters.documentscanner.ImageCropActivity$3 */
    /* loaded from: classes3.dex */
    public class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
            ImageCropActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageCropActivity.this.showProgressBar();
            ImageCropActivity.this.disposable.add(Observable.fromCallable(new Callable() { // from class: com.labters.documentscanner.ImageCropActivity$3$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return ImageCropActivity.AnonymousClass3.this.m261lambda$onClick$0$comlabtersdocumentscannerImageCropActivity$3();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.labters.documentscanner.ImageCropActivity$3$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ImageCropActivity.AnonymousClass3.this.m262lambda$onClick$1$comlabtersdocumentscannerImageCropActivity$3((Boolean) obj);
                }
            }));
        }

        /* renamed from: lambda$onClick$0$com-labters-documentscanner-ImageCropActivity$3 */
        public /* synthetic */ Boolean m261lambda$onClick$0$comlabtersdocumentscannerImageCropActivity$3() throws Exception {
            if (ImageCropActivity.this.isInverted) {
                ImageCropActivity.this.invertColor();
            }
            ImageCropActivity imageCropActivity = ImageCropActivity.this;
            imageCropActivity.cropImage = imageCropActivity.rotateBitmap(imageCropActivity.cropImage, 90.0f);
            return false;
        }

        /* renamed from: lambda$onClick$1$com-labters-documentscanner-ImageCropActivity$3 */
        public /* synthetic */ void m262lambda$onClick$1$comlabtersdocumentscannerImageCropActivity$3(Boolean bool) throws Exception {
            ImageCropActivity.this.hideProgressBar();
            ImageCropActivity.this.startCropping();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_image_crop);
        this.cropImage = ScannerConstants.selectedImageBitmap;
        this.isInverted = false;
        if (ScannerConstants.selectedImageBitmap != null) {
            initView();
            return;
        }
        Toast.makeText(this, ScannerConstants.imageError, 1).show();
        finish();
    }

    @Override // com.labters.documentscanner.base.DocumentScanActivity
    protected FrameLayout getHolderImageCrop() {
        return this.holderImageCrop;
    }

    @Override // com.labters.documentscanner.base.DocumentScanActivity
    protected ImageView getImageView() {
        return this.imageView;
    }

    @Override // com.labters.documentscanner.base.DocumentScanActivity
    protected PolygonView getPolygonView() {
        return this.polygonView;
    }

    @Override // com.labters.documentscanner.base.DocumentScanActivity
    protected void showProgressBar() {
        setViewInteract((RelativeLayout) findViewById(R.id.rlContainer), false);
        this.progressBar.setVisibility(0);
    }

    @Override // com.labters.documentscanner.base.DocumentScanActivity
    protected void hideProgressBar() {
        setViewInteract((RelativeLayout) findViewById(R.id.rlContainer), true);
        this.progressBar.setVisibility(8);
    }

    /* renamed from: com.labters.documentscanner.ImageCropActivity$4 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$labters$documentscanner$base$CropperErrorType;

        static {
            int[] iArr = new int[CropperErrorType.values().length];
            $SwitchMap$com$labters$documentscanner$base$CropperErrorType = iArr;
            try {
                iArr[CropperErrorType.CROP_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override // com.labters.documentscanner.base.DocumentScanActivity
    protected void showError(CropperErrorType cropperErrorType) {
        if (AnonymousClass4.$SwitchMap$com$labters$documentscanner$base$CropperErrorType[cropperErrorType.ordinal()] != 1) {
            return;
        }
        Toast.makeText(this, ScannerConstants.cropError, 1).show();
    }

    @Override // com.labters.documentscanner.base.DocumentScanActivity
    protected Bitmap getBitmapImage() {
        return this.cropImage;
    }

    private void setViewInteract(View view, boolean z) {
        view.setEnabled(z);
        if (!(view instanceof ViewGroup)) {
            return;
        }
        int i = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i >= viewGroup.getChildCount()) {
                return;
            }
            setViewInteract(viewGroup.getChildAt(i), z);
            i++;
        }
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.btnImageCrop);
        Button button2 = (Button) findViewById(R.id.btnClose);
        this.holderImageCrop = (FrameLayout) findViewById(R.id.holderImageCrop);
        this.imageView = (ImageView) findViewById(R.id.imageView);
        ImageView imageView = (ImageView) findViewById(R.id.ivRotate);
        ImageView imageView2 = (ImageView) findViewById(R.id.ivInvert);
        ImageView imageView3 = (ImageView) findViewById(R.id.ivRebase);
        button.setText(ScannerConstants.cropText);
        button2.setText(ScannerConstants.backText);
        this.polygonView = (PolygonView) findViewById(R.id.polygonView);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.progressBar = progressBar;
        if (progressBar.getIndeterminateDrawable() != null && ScannerConstants.progressColor != null) {
            this.progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor(ScannerConstants.progressColor), PorterDuff.Mode.MULTIPLY);
        } else if (this.progressBar.getProgressDrawable() != null && ScannerConstants.progressColor != null) {
            this.progressBar.getProgressDrawable().setColorFilter(Color.parseColor(ScannerConstants.progressColor), PorterDuff.Mode.MULTIPLY);
        }
        button.setBackgroundColor(Color.parseColor(ScannerConstants.cropColor));
        button2.setBackgroundColor(Color.parseColor(ScannerConstants.backColor));
        button.setOnClickListener(this.btnImageEnhanceClick);
        button2.setOnClickListener(this.btnCloseClick);
        imageView.setOnClickListener(this.onRotateClick);
        imageView2.setOnClickListener(this.btnInvertColor);
        imageView3.setOnClickListener(this.btnRebase);
        startCropping();
    }

    public void invertColor() {
        if (!this.isInverted) {
            Bitmap createBitmap = Bitmap.createBitmap(this.cropImage.getWidth(), this.cropImage.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            Paint paint = new Paint();
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            canvas.drawBitmap(this.cropImage, 0.0f, 0.0f, paint);
            this.cropImage = createBitmap.copy(createBitmap.getConfig(), true);
        } else {
            Bitmap bitmap = this.cropImage;
            this.cropImage = bitmap.copy(bitmap.getConfig(), true);
        }
        this.isInverted = !this.isInverted;
    }

    public String saveToInternalStorage(Bitmap bitmap) {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(externalStoragePublicDirectory, "cropped_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".png"));
                    try {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        fileOutputStream.close();
                        return externalStoragePublicDirectory.getAbsolutePath();
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        try {
                            fileOutputStream.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return externalStoragePublicDirectory.getAbsolutePath();
    }
}
