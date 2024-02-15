package org.informatika.sirekap.ui.imageCrop;

import android.content.Context;
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
import com.labters.documentscanner.base.CropperErrorType;
import com.labters.documentscanner.base.DocumentScanActivity;
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
import org.informatika.sirekap.R;
import org.informatika.sirekap.ui.imageCrop.ImageCropActivity;

/* loaded from: classes4.dex */
public class ImageCropActivity extends DocumentScanActivity {
    private static final String backColor = "#ff0000";
    private static final String cropColor = "#6666ff";
    private static final String cropError = "You have not selected a valid field. Please make corrections until the lines are blue.";
    private static final String imageError = "Gagal mengambil gambar";
    private static final String progressColor = "#331199";
    private Bitmap cropImage;
    private FrameLayout holderImageCrop;
    private ImageView imageView;
    private boolean isInverted;
    private PolygonView polygonView;
    private ProgressBar progressBar;
    private final boolean saveStorage = false;
    private View.OnClickListener btnImageEnhanceClick = new AnonymousClass1();
    private View.OnClickListener btnRebase = new View.OnClickListener() { // from class: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$$ExternalSyntheticLambda1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ImageCropActivity.this.lambda$new$0(view);
        }
    };
    private View.OnClickListener btnCloseClick = new View.OnClickListener() { // from class: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$$ExternalSyntheticLambda2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ImageCropActivity.this.lambda$new$1(view);
        }
    };
    private View.OnClickListener btnInvertColor = new AnonymousClass2();
    private View.OnClickListener onRotateClick = new AnonymousClass3();

    /* renamed from: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$1 */
    /* loaded from: classes4.dex */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
            ImageCropActivity.this = this$0;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            ImageCropActivity.this.showProgressBar();
            ImageCropActivity.this.disposable.add(Observable.fromCallable(new Callable() { // from class: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$1$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Boolean lambda$onClick$0;
                    lambda$onClick$0 = ImageCropActivity.AnonymousClass1.this.lambda$onClick$0();
                    return lambda$onClick$0;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$1$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ImageCropActivity.AnonymousClass1.this.lambda$onClick$1((Boolean) obj);
                }
            }));
        }

        public /* synthetic */ Boolean lambda$onClick$0() throws Exception {
            ImageCropActivity imageCropActivity = ImageCropActivity.this;
            imageCropActivity.cropImage = imageCropActivity.getCroppedImage();
            Bitmap unused = ImageCropActivity.this.cropImage;
            return false;
        }

        public /* synthetic */ void lambda$onClick$1(Boolean bool) throws Exception {
            ImageCropActivity.this.hideProgressBar();
            if (ImageCropActivity.this.cropImage != null) {
                ImageCropConstants.croppedImageBitmap = ImageCropActivity.this.cropImage;
                ImageCropActivity.this.setResult(-1);
                ImageCropActivity.this.finish();
            }
        }
    }

    public /* synthetic */ void lambda$new$0(View view) {
        this.cropImage = ImageCropConstants.selectedImageBitmap.copy(ImageCropConstants.selectedImageBitmap.getConfig(), true);
        this.isInverted = false;
        startCropping();
    }

    /* renamed from: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$2 */
    /* loaded from: classes4.dex */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
            ImageCropActivity.this = this$0;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            ImageCropActivity.this.showProgressBar();
            ImageCropActivity.this.disposable.add(Observable.fromCallable(new Callable() { // from class: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$2$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Boolean lambda$onClick$0;
                    lambda$onClick$0 = ImageCropActivity.AnonymousClass2.this.lambda$onClick$0();
                    return lambda$onClick$0;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$2$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ImageCropActivity.AnonymousClass2.this.lambda$onClick$1((Boolean) obj);
                }
            }));
        }

        public /* synthetic */ Boolean lambda$onClick$0() throws Exception {
            ImageCropActivity.this.invertColor();
            return false;
        }

        public /* synthetic */ void lambda$onClick$1(Boolean bool) throws Exception {
            ImageCropActivity.this.hideProgressBar();
            ImageCropActivity imageCropActivity = ImageCropActivity.this;
            ImageCropActivity.this.imageView.setImageBitmap(imageCropActivity.scaledBitmap(imageCropActivity.cropImage, ImageCropActivity.this.holderImageCrop.getWidth(), ImageCropActivity.this.holderImageCrop.getHeight()));
        }
    }

    public /* synthetic */ void lambda$new$1(View view) {
        finish();
    }

    /* renamed from: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$3 */
    /* loaded from: classes4.dex */
    public class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
            ImageCropActivity.this = this$0;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            ImageCropActivity.this.showProgressBar();
            ImageCropActivity.this.disposable.add(Observable.fromCallable(new Callable() { // from class: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$3$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Boolean lambda$onClick$0;
                    lambda$onClick$0 = ImageCropActivity.AnonymousClass3.this.lambda$onClick$0();
                    return lambda$onClick$0;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$3$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ImageCropActivity.AnonymousClass3.this.lambda$onClick$1((Boolean) obj);
                }
            }));
        }

        public /* synthetic */ Boolean lambda$onClick$0() throws Exception {
            if (ImageCropActivity.this.isInverted) {
                ImageCropActivity.this.invertColor();
            }
            ImageCropActivity imageCropActivity = ImageCropActivity.this;
            imageCropActivity.cropImage = imageCropActivity.rotateBitmap(imageCropActivity.cropImage, 90.0f);
            return false;
        }

        public /* synthetic */ void lambda$onClick$1(Boolean bool) throws Exception {
            ImageCropActivity.this.hideProgressBar();
            ImageCropActivity.this.startCropping();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_crop);
        this.cropImage = ImageCropConstants.selectedImageBitmap;
        this.isInverted = false;
        if (ImageCropConstants.selectedImageBitmap != null) {
            initView();
            return;
        }
        Toast.makeText(this, imageError, 1).show();
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

    /* renamed from: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$4 */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass4 {
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
    protected void showError(final CropperErrorType errorType) {
        runOnUiThread(new Runnable() { // from class: org.informatika.sirekap.ui.imageCrop.ImageCropActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ImageCropActivity.lambda$showError$2(CropperErrorType.this, this);
            }
        });
    }

    public static /* synthetic */ void lambda$showError$2(CropperErrorType cropperErrorType, Context context) {
        if (AnonymousClass4.$SwitchMap$com$labters$documentscanner$base$CropperErrorType[cropperErrorType.ordinal()] != 1) {
            return;
        }
        Toast.makeText(context, cropError, 1).show();
    }

    @Override // com.labters.documentscanner.base.DocumentScanActivity
    protected Bitmap getBitmapImage() {
        return this.cropImage;
    }

    private void setViewInteract(View view, boolean canDo) {
        view.setEnabled(canDo);
        if (!(view instanceof ViewGroup)) {
            return;
        }
        int i = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i >= viewGroup.getChildCount()) {
                return;
            }
            setViewInteract(viewGroup.getChildAt(i), canDo);
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
        this.polygonView = (PolygonView) findViewById(R.id.polygonView);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.progressBar = progressBar;
        if (progressBar.getIndeterminateDrawable() != null) {
            this.progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor(progressColor), PorterDuff.Mode.MULTIPLY);
        } else if (this.progressBar.getProgressDrawable() != null) {
            this.progressBar.getProgressDrawable().setColorFilter(Color.parseColor(progressColor), PorterDuff.Mode.MULTIPLY);
        }
        button.setBackgroundColor(Color.parseColor(cropColor));
        button2.setBackgroundColor(Color.parseColor(backColor));
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

    private String saveToInternalStorage(Bitmap bitmapImage) {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(externalStoragePublicDirectory, "cropped_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".jpg"));
                    try {
                        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream2);
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
