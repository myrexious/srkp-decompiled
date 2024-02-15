package org.informatika.sirekap.support;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.xmpbox.type.ThumbnailType;
import org.informatika.sirekap.di.SharedPreferencesModule;
import org.informatika.sirekap.ui.EncryptedSharedPreferences;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/* compiled from: FileUtil.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0004H\u0007J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rH\u0003J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rH\u0003J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rH\u0007J\"\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0007J\u001a\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0007J\u001a\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001dH\u0003J:\u0010\u001e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0007J:\u0010!\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#H\u0007J:\u0010$\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020#H\u0007J \u0010%\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0007J(\u0010%\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0007J(\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0007J(\u0010(\u001a\u00020\r2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/informatika/sirekap/support/FileUtil;", "", "()V", "TAG", "", "adjustImageRotation", "Landroid/graphics/Bitmap;", "bitmap", "path", "deleteFile", "", "deleteRecursive", "fileOrDirectory", "Ljava/io/File;", "getCorrectedImageFilename", "originalImage", "getCroppedImageFilename", "getDebugCorrectedImageFilename", "getOriginalImageFilenamePrefix", "kodeTps", "jenisPemilihan", "electionType", "resizeImage", ThumbnailType.IMAGE, "maxWidth", "", "rotateImage", "source", "angle", "", "saveAdjustedImage", "context", "Landroid/content/Context;", "saveCorrectedImage", "encryptedSharedPreferences", "Lorg/informatika/sirekap/ui/EncryptedSharedPreferences;", "saveCroppedImage", "saveOriginalImage", "saveTempCorrectedImage", "originalImagePath", "saveTempCroppedImage", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FileUtil {
    public static final FileUtil INSTANCE = new FileUtil();
    private static final String TAG = "FileUtil";

    private FileUtil() {
    }

    @JvmStatic
    public static final String getOriginalImageFilenamePrefix(String kodeTps, String jenisPemilihan, String str) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        String format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        Intrinsics.checkNotNullExpressionValue(format, "SimpleDateFormat(\"yyyy-M…HH-mm-ss\").format(Date())");
        return kodeTps + "_" + jenisPemilihan + "_" + str + "_" + format + "_";
    }

    @JvmStatic
    public static final String getOriginalImageFilenamePrefix(String kodeTps, String str) {
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        String format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        Intrinsics.checkNotNullExpressionValue(format, "SimpleDateFormat(\"yyyy-M…HH-mm-ss\").format(Date())");
        return kodeTps + "_" + str + "_" + format + "_";
    }

    @JvmStatic
    private static final String getCroppedImageFilename(File file) {
        return "crop_" + file.getName();
    }

    @JvmStatic
    private static final String getCorrectedImageFilename(File file) {
        return "corr_" + file.getName();
    }

    @JvmStatic
    public static final File saveOriginalImage(Context context, String kodeTps, String jenisPemilihan, String electionType) throws IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(electionType, "electionType");
        File createTempFile = File.createTempFile(getOriginalImageFilenamePrefix(kodeTps, jenisPemilihan, electionType), ".jpg", context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_PICTURES + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + electionType));
        Intrinsics.checkNotNullExpressionValue(createTempFile, "createTempFile(\n        …     storageDir\n        )");
        return createTempFile;
    }

    @JvmStatic
    public static final File saveOriginalImage(Context context, String kodeTps, String electionType) throws IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(electionType, "electionType");
        File createTempFile = File.createTempFile(getOriginalImageFilenamePrefix(kodeTps, electionType), ".jpg", context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_PICTURES + "/temp"));
        Intrinsics.checkNotNullExpressionValue(createTempFile, "createTempFile(\n        …     storageDir\n        )");
        return createTempFile;
    }

    @JvmStatic
    public static final File saveTempCroppedImage(String originalImagePath, Context context, String kodeTps, String electionType) throws IOException {
        Intrinsics.checkNotNullParameter(originalImagePath, "originalImagePath");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(electionType, "electionType");
        File createTempFile = File.createTempFile("crop_" + originalImagePath, ".jpg", context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_PICTURES + "/temp"));
        Intrinsics.checkNotNullExpressionValue(createTempFile, "createTempFile(\n        …     storageDir\n        )");
        return createTempFile;
    }

    @JvmStatic
    public static final File saveTempCorrectedImage(String originalImagePath, Context context, String kodeTps, String electionType) throws IOException {
        Intrinsics.checkNotNullParameter(originalImagePath, "originalImagePath");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(electionType, "electionType");
        File createTempFile = File.createTempFile("corrected_" + originalImagePath, ".jpg", context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_PICTURES + "/temp"));
        Intrinsics.checkNotNullExpressionValue(createTempFile, "createTempFile(\n        …     storageDir\n        )");
        return createTempFile;
    }

    @JvmStatic
    public static final Bitmap adjustImageRotation(Bitmap bitmap, String path) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(path, "path");
        int attributeInt = new ExifInterface(path).getAttributeInt("Orientation", 0);
        if (attributeInt != 3) {
            if (attributeInt != 6) {
                return attributeInt != 8 ? bitmap : rotateImage(bitmap, 270.0f);
            }
            return rotateImage(bitmap, 90.0f);
        }
        return rotateImage(bitmap, 180.0f);
    }

    @JvmStatic
    private static final Bitmap rotateImage(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static /* synthetic */ Bitmap resizeImage$default(Bitmap bitmap, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1000;
        }
        return resizeImage(bitmap, i);
    }

    @JvmStatic
    public static final Bitmap resizeImage(Bitmap image, int i) {
        Intrinsics.checkNotNullParameter(image, "image");
        OpenCVLoader.initDebug();
        Mat mat = new Mat();
        Utils.bitmapToMat(image, mat);
        Imgproc.resize(mat, mat, new Size(i, (int) ((i / mat.cols()) * mat.rows())), 0.0d, 0.0d, 3);
        Bitmap resultBitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat, resultBitmap);
        Intrinsics.checkNotNullExpressionValue(resultBitmap, "resultBitmap");
        return resultBitmap;
    }

    @JvmStatic
    public static final File saveAdjustedImage(Context context, File originalImage, Bitmap bitmap, String jenisPemilihan, String kodeTps, String electionType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(originalImage, "originalImage");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(electionType, "electionType");
        String name = originalImage.getName();
        Intrinsics.checkNotNullExpressionValue(name, "originalImage.name");
        File file = new File(context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_PICTURES + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + electionType), StringsKt.replace$default(name, ".jpg", ".png", false, 4, (Object) null));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            CloseableKt.closeFinally(fileOutputStream, null);
            Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
            File file2 = new File(context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_PICTURES + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + electionType), originalImage.getName());
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream2);
                CloseableKt.closeFinally(fileOutputStream2, null);
                return file2;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    public static final File saveCroppedImage(Context context, File originalImage, Bitmap bitmap, String jenisPemilihan, String kodeTps, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(originalImage, "originalImage");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
        String str = stringEncrypted;
        String str2 = str == null || StringsKt.isBlank(str) ? "R" : stringEncrypted;
        File file = new File(context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_PICTURES + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + str2), StringsKt.replace$default(getCroppedImageFilename(originalImage), ".jpg", ".png", false, 4, (Object) null));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            CloseableKt.closeFinally(fileOutputStream, null);
            Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
            File file2 = new File(context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_PICTURES + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + str2), getCroppedImageFilename(originalImage));
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream2);
                CloseableKt.closeFinally(fileOutputStream2, null);
                return file2;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    public static final File saveCorrectedImage(Context context, File originalImage, Bitmap bitmap, String jenisPemilihan, String kodeTps, EncryptedSharedPreferences encryptedSharedPreferences) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(originalImage, "originalImage");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        Intrinsics.checkNotNullParameter(kodeTps, "kodeTps");
        Intrinsics.checkNotNullParameter(encryptedSharedPreferences, "encryptedSharedPreferences");
        String stringEncrypted = encryptedSharedPreferences.getStringEncrypted(SharedPreferencesModule.SP_KEY_ELECTION_TYPE, "R");
        String str = stringEncrypted;
        String str2 = str == null || StringsKt.isBlank(str) ? "R" : stringEncrypted;
        File file = new File(context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_PICTURES + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + str2), StringsKt.replace$default(getCorrectedImageFilename(originalImage), ".jpg", ".png", false, 4, (Object) null));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            CloseableKt.closeFinally(fileOutputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
        File file2 = new File(context.getExternalFilesDir(kodeTps + RemoteSettings.FORWARD_SLASH_STRING + Environment.DIRECTORY_PICTURES + RemoteSettings.FORWARD_SLASH_STRING + jenisPemilihan + str2), getCorrectedImageFilename(originalImage));
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
            decodeFile.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream2);
            CloseableKt.closeFinally(fileOutputStream2, null);
            return file2;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    public static final void deleteFile(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        if (new File(path).delete()) {
            return;
        }
        Log.wtf(TAG, "Failed deleting file: " + path);
    }

    @JvmStatic
    public static final void deleteRecursive(File fileOrDirectory) {
        Intrinsics.checkNotNullParameter(fileOrDirectory, "fileOrDirectory");
        if (fileOrDirectory.isDirectory()) {
            File[] listFiles = fileOrDirectory.listFiles();
            Intrinsics.checkNotNullExpressionValue(listFiles, "fileOrDirectory.listFiles()");
            for (File child : listFiles) {
                Intrinsics.checkNotNullExpressionValue(child, "child");
                deleteRecursive(child);
            }
        }
        fileOrDirectory.delete();
    }

    @JvmStatic
    public static final String getDebugCorrectedImageFilename(File originalImage) {
        Intrinsics.checkNotNullParameter(originalImage, "originalImage");
        return "debug_corr_" + originalImage.getName();
    }
}
