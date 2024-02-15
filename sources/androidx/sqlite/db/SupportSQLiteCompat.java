package androidx.sqlite.db;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import java.io.File;
import java.util.List;

/* loaded from: classes.dex */
public final class SupportSQLiteCompat {
    private SupportSQLiteCompat() {
    }

    /* loaded from: classes.dex */
    public static final class Api16Impl {
        public static void cancel(CancellationSignal cancellationSignal) {
            cancellationSignal.cancel();
        }

        public static CancellationSignal createCancellationSignal() {
            return new CancellationSignal();
        }

        public static boolean deleteDatabase(File file) {
            return SQLiteDatabase.deleteDatabase(file);
        }

        public static Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase, String sql, String[] selectionArgs, String editTable, CancellationSignal cancellationSignal, SQLiteDatabase.CursorFactory cursorFactory) {
            return sQLiteDatabase.rawQueryWithFactory(cursorFactory, sql, selectionArgs, editTable, cancellationSignal);
        }

        public static void setForeignKeyConstraintsEnabled(SQLiteDatabase sQLiteDatabase, boolean enable) {
            sQLiteDatabase.setForeignKeyConstraintsEnabled(enable);
        }

        public static void disableWriteAheadLogging(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.disableWriteAheadLogging();
        }

        public static boolean isWriteAheadLoggingEnabled(SQLiteDatabase sQLiteDatabase) {
            return sQLiteDatabase.isWriteAheadLoggingEnabled();
        }

        public static void setWriteAheadLoggingEnabled(SQLiteOpenHelper sQLiteOpenHelper, boolean enabled) {
            sQLiteOpenHelper.setWriteAheadLoggingEnabled(enabled);
        }

        private Api16Impl() {
        }
    }

    /* loaded from: classes.dex */
    public static final class Api19Impl {
        public static Uri getNotificationUri(Cursor cursor) {
            return cursor.getNotificationUri();
        }

        public static boolean isLowRamDevice(ActivityManager activityManager) {
            return activityManager.isLowRamDevice();
        }

        private Api19Impl() {
        }
    }

    /* loaded from: classes.dex */
    public static final class Api21Impl {
        public static File getNoBackupFilesDir(Context context) {
            return context.getNoBackupFilesDir();
        }

        private Api21Impl() {
        }
    }

    /* loaded from: classes.dex */
    public static final class Api23Impl {
        public static void setExtras(Cursor cursor, Bundle extras) {
            cursor.setExtras(extras);
        }

        private Api23Impl() {
        }
    }

    /* loaded from: classes.dex */
    public static final class Api29Impl {
        public static void setNotificationUris(Cursor cursor, ContentResolver cr, List<Uri> uris) {
            cursor.setNotificationUris(cr, uris);
        }

        public static List<Uri> getNotificationUris(Cursor cursor) {
            return cursor.getNotificationUris();
        }

        private Api29Impl() {
        }
    }
}
