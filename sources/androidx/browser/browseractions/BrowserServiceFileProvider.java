package androidx.browser.browseractions;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.content.FileProvider;
import androidx.core.util.AtomicFile;
import com.google.common.util.concurrent.ListenableFuture;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Deprecated
/* loaded from: classes.dex */
public final class BrowserServiceFileProvider extends FileProvider {
    private static final String AUTHORITY_SUFFIX = ".image_provider";
    private static final String CLIP_DATA_LABEL = "image_provider_uris";
    private static final String CONTENT_SCHEME = "content";
    private static final String FILE_EXTENSION = ".png";
    private static final String FILE_SUB_DIR = "image_provider";
    private static final String FILE_SUB_DIR_NAME = "image_provider_images/";
    private static final String LAST_CLEANUP_TIME_KEY = "last_cleanup_time";
    private static final String TAG = "BrowserServiceFP";
    static Object sFileCleanupLock = new Object();

    /* loaded from: classes.dex */
    public static class FileCleanupTask extends AsyncTask<Void, Void, Void> {
        private final Context mAppContext;
        private static final long IMAGE_RETENTION_DURATION = TimeUnit.DAYS.toMillis(7);
        private static final long CLEANUP_REQUIRED_TIME_SPAN = TimeUnit.DAYS.toMillis(7);
        private static final long DELETION_FAILED_REATTEMPT_DURATION = TimeUnit.DAYS.toMillis(1);

        FileCleanupTask(Context context) {
            this.mAppContext = context.getApplicationContext();
        }

        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            long currentTimeMillis;
            SharedPreferences sharedPreferences = this.mAppContext.getSharedPreferences(this.mAppContext.getPackageName() + BrowserServiceFileProvider.AUTHORITY_SUFFIX, 0);
            if (shouldCleanUp(sharedPreferences)) {
                synchronized (BrowserServiceFileProvider.sFileCleanupLock) {
                    File file = new File(this.mAppContext.getFilesDir(), BrowserServiceFileProvider.FILE_SUB_DIR);
                    if (file.exists()) {
                        File[] listFiles = file.listFiles();
                        long currentTimeMillis2 = System.currentTimeMillis() - IMAGE_RETENTION_DURATION;
                        boolean z = true;
                        for (File file2 : listFiles) {
                            if (isImageFile(file2) && file2.lastModified() < currentTimeMillis2 && !file2.delete()) {
                                Log.e(BrowserServiceFileProvider.TAG, "Fail to delete image: " + file2.getAbsoluteFile());
                                z = false;
                            }
                        }
                        if (z) {
                            currentTimeMillis = System.currentTimeMillis();
                        } else {
                            currentTimeMillis = (System.currentTimeMillis() - CLEANUP_REQUIRED_TIME_SPAN) + DELETION_FAILED_REATTEMPT_DURATION;
                        }
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putLong(BrowserServiceFileProvider.LAST_CLEANUP_TIME_KEY, currentTimeMillis);
                        edit.apply();
                        return null;
                    }
                    return null;
                }
            }
            return null;
        }

        private static boolean isImageFile(File file) {
            return file.getName().endsWith("..png");
        }

        private static boolean shouldCleanUp(SharedPreferences prefs) {
            return System.currentTimeMillis() > prefs.getLong(BrowserServiceFileProvider.LAST_CLEANUP_TIME_KEY, System.currentTimeMillis()) + CLEANUP_REQUIRED_TIME_SPAN;
        }
    }

    /* loaded from: classes.dex */
    private static class FileSaveTask extends AsyncTask<String, Void, Void> {
        private final Context mAppContext;
        private final Bitmap mBitmap;
        private final Uri mFileUri;
        private final String mFilename;
        private final ResolvableFuture<Uri> mResultFuture;

        FileSaveTask(Context context, String filename, Bitmap bitmap, Uri fileUri, ResolvableFuture<Uri> resultFuture) {
            this.mAppContext = context.getApplicationContext();
            this.mFilename = filename;
            this.mBitmap = bitmap;
            this.mFileUri = fileUri;
            this.mResultFuture = resultFuture;
        }

        @Override // android.os.AsyncTask
        public Void doInBackground(String... params) {
            saveFileIfNeededBlocking();
            return null;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Void result) {
            new FileCleanupTask(this.mAppContext).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }

        private void saveFileIfNeededBlocking() {
            File file = new File(this.mAppContext.getFilesDir(), BrowserServiceFileProvider.FILE_SUB_DIR);
            synchronized (BrowserServiceFileProvider.sFileCleanupLock) {
                if (!file.exists() && !file.mkdir()) {
                    this.mResultFuture.setException(new IOException("Could not create file directory."));
                    return;
                }
                File file2 = new File(file, this.mFilename + BrowserServiceFileProvider.FILE_EXTENSION);
                if (file2.exists()) {
                    this.mResultFuture.set(this.mFileUri);
                } else {
                    saveFileBlocking(file2);
                }
                file2.setLastModified(System.currentTimeMillis());
            }
        }

        private void saveFileBlocking(File img) {
            FileOutputStream fileOutputStream;
            AtomicFile atomicFile = new AtomicFile(img);
            try {
                fileOutputStream = atomicFile.startWrite();
                try {
                    this.mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.close();
                    atomicFile.finishWrite(fileOutputStream);
                    this.mResultFuture.set(this.mFileUri);
                } catch (IOException e) {
                    e = e;
                    atomicFile.failWrite(fileOutputStream);
                    this.mResultFuture.setException(e);
                }
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = null;
            }
        }
    }

    public static ResolvableFuture<Uri> saveBitmap(Context context, Bitmap bitmap, String name, int version) {
        String str = name + "_" + Integer.toString(version);
        Uri generateUri = generateUri(context, str);
        ResolvableFuture<Uri> create = ResolvableFuture.create();
        new FileSaveTask(context, str, bitmap, generateUri, create).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
        return create;
    }

    private static Uri generateUri(Context context, String filename) {
        return new Uri.Builder().scheme("content").authority(context.getPackageName() + AUTHORITY_SUFFIX).path(FILE_SUB_DIR_NAME + filename + FILE_EXTENSION).build();
    }

    public static void grantReadPermission(Intent intent, List<Uri> uris, Context context) {
        if (uris == null || uris.size() == 0) {
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        intent.addFlags(1);
        ClipData newUri = ClipData.newUri(contentResolver, CLIP_DATA_LABEL, uris.get(0));
        for (int i = 1; i < uris.size(); i++) {
            newUri.addItem(new ClipData.Item(uris.get(i)));
        }
        intent.setClipData(newUri);
    }

    public static ListenableFuture<Bitmap> loadBitmap(final ContentResolver resolver, final Uri uri) {
        final ResolvableFuture create = ResolvableFuture.create();
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() { // from class: androidx.browser.browseractions.BrowserServiceFileProvider.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ParcelFileDescriptor openFileDescriptor = resolver.openFileDescriptor(uri, PDPageLabelRange.STYLE_ROMAN_LOWER);
                    if (openFileDescriptor == null) {
                        create.setException(new FileNotFoundException());
                        return;
                    }
                    Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(openFileDescriptor.getFileDescriptor());
                    openFileDescriptor.close();
                    if (decodeFileDescriptor == null) {
                        create.setException(new IOException("File could not be decoded."));
                    } else {
                        create.set(decodeFileDescriptor);
                    }
                } catch (IOException e) {
                    create.setException(e);
                }
            }
        });
        return create;
    }
}
