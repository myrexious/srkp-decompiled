package com.labters.documentscanner.base;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.labters.documentscanner.R;
import com.labters.documentscanner.libraries.NativeClass;
import com.labters.documentscanner.libraries.PolygonView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;

/* loaded from: classes3.dex */
public abstract class DocumentScanActivity extends AppCompatActivity {
    protected CompositeDisposable disposable = new CompositeDisposable();
    private NativeClass nativeClass = new NativeClass();
    private Bitmap selectedImage;

    protected abstract Bitmap getBitmapImage();

    protected abstract FrameLayout getHolderImageCrop();

    protected abstract ImageView getImageView();

    protected abstract PolygonView getPolygonView();

    protected abstract void hideProgressBar();

    protected abstract void showError(CropperErrorType cropperErrorType);

    protected abstract void showProgressBar();

    private void setImageRotation() {
        Bitmap bitmap = this.selectedImage;
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        for (int i = 1; i <= 4; i++) {
            if (this.nativeClass.getPoint(copy) == null) {
                copy = rotateBitmap(copy, i * 90);
            } else {
                this.selectedImage = copy.copy(this.selectedImage.getConfig(), true);
                return;
            }
        }
    }

    public Bitmap rotateBitmap(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private void setProgressBar(boolean z) {
        if (z) {
            showProgressBar();
        } else {
            hideProgressBar();
        }
    }

    public void startCropping() {
        this.selectedImage = getBitmapImage();
        setProgressBar(true);
        this.disposable.add(Observable.fromCallable(new Callable() { // from class: com.labters.documentscanner.base.DocumentScanActivity$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DocumentScanActivity.this.m263x95ed896e();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.labters.documentscanner.base.DocumentScanActivity$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                DocumentScanActivity.this.m264xbf41deaf((Boolean) obj);
            }
        }));
    }

    /* renamed from: lambda$startCropping$0$com-labters-documentscanner-base-DocumentScanActivity */
    public /* synthetic */ Boolean m263x95ed896e() throws Exception {
        setImageRotation();
        return false;
    }

    /* renamed from: lambda$startCropping$1$com-labters-documentscanner-base-DocumentScanActivity */
    public /* synthetic */ void m264xbf41deaf(Boolean bool) throws Exception {
        initializeCropping();
        setProgressBar(false);
    }

    private void initializeCropping() {
        getImageView().setImageBitmap(scaledBitmap(this.selectedImage, getHolderImageCrop().getWidth(), getHolderImageCrop().getHeight()));
        Bitmap bitmap = ((BitmapDrawable) getImageView().getDrawable()).getBitmap();
        try {
            getPolygonView().setPoints(getEdgePoints(bitmap));
            getPolygonView().setVisibility(0);
            int dimension = ((int) getResources().getDimension(R.dimen.scanPadding)) * 2;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(bitmap.getWidth() + dimension, bitmap.getHeight() + dimension);
            layoutParams.gravity = 17;
            getPolygonView().setLayoutParams(layoutParams);
            getPolygonView().setPointColor(getResources().getColor(R.color.blue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getCroppedImage() {
        try {
            Map<Integer, PointF> points = getPolygonView().getPoints();
            float width = this.selectedImage.getWidth() / getImageView().getWidth();
            float height = this.selectedImage.getHeight() / getImageView().getHeight();
            float f = ((PointF) Objects.requireNonNull(points.get(1))).x * width;
            float f2 = ((PointF) Objects.requireNonNull(points.get(2))).x * width;
            float f3 = ((PointF) Objects.requireNonNull(points.get(3))).x * width;
            return this.nativeClass.getScannedBitmap(this.selectedImage, ((PointF) Objects.requireNonNull(points.get(0))).x * width, ((PointF) Objects.requireNonNull(points.get(0))).y * height, f, ((PointF) Objects.requireNonNull(points.get(1))).y * height, f2, ((PointF) Objects.requireNonNull(points.get(2))).y * height, f3, ((PointF) Objects.requireNonNull(points.get(3))).y * height);
        } catch (Exception unused) {
            showError(CropperErrorType.CROP_ERROR);
            return null;
        }
    }

    public Bitmap scaledBitmap(Bitmap bitmap, int i, int i2) {
        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight()), new RectF(0.0f, 0.0f, i, i2), Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private Map<Integer, PointF> getEdgePoints(Bitmap bitmap) throws Exception {
        return orderedValidEdgePoints(bitmap, getContourEdgePoints(bitmap));
    }

    private List<PointF> getContourEdgePoints(Bitmap bitmap) {
        MatOfPoint2f point = this.nativeClass.getPoint(bitmap);
        if (point == null) {
            point = new MatOfPoint2f();
        }
        List asList = Arrays.asList(point.toArray());
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < asList.size(); i++) {
            arrayList.add(new PointF((float) ((Point) asList.get(i)).x, (float) ((Point) asList.get(i)).y));
        }
        return arrayList;
    }

    private Map<Integer, PointF> getOutlinePoints(Bitmap bitmap) {
        HashMap hashMap = new HashMap();
        hashMap.put(0, new PointF(0.0f, 0.0f));
        hashMap.put(1, new PointF(bitmap.getWidth(), 0.0f));
        hashMap.put(2, new PointF(0.0f, bitmap.getHeight()));
        hashMap.put(3, new PointF(bitmap.getWidth(), bitmap.getHeight()));
        return hashMap;
    }

    private Map<Integer, PointF> orderedValidEdgePoints(Bitmap bitmap, List<PointF> list) {
        Map<Integer, PointF> orderedPoints = getPolygonView().getOrderedPoints(list);
        return !getPolygonView().isValidShape(orderedPoints) ? getOutlinePoints(bitmap) : orderedPoints;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.disposable.clear();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.disposable.dispose();
    }
}
