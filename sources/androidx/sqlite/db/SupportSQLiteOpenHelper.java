package androidx.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.sqlite.db.SupportSQLiteCompat;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* loaded from: classes.dex */
public interface SupportSQLiteOpenHelper extends Closeable {

    /* loaded from: classes.dex */
    public interface Factory {
        SupportSQLiteOpenHelper create(Configuration configuration);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    String getDatabaseName();

    SupportSQLiteDatabase getReadableDatabase();

    SupportSQLiteDatabase getWritableDatabase();

    void setWriteAheadLoggingEnabled(boolean enabled);

    /* loaded from: classes.dex */
    public static abstract class Callback {
        private static final String TAG = "SupportSQLite";
        public final int version;

        public void onConfigure(SupportSQLiteDatabase db) {
        }

        public abstract void onCreate(SupportSQLiteDatabase db);

        public void onOpen(SupportSQLiteDatabase db) {
        }

        public abstract void onUpgrade(SupportSQLiteDatabase db, int oldVersion, int newVersion);

        public Callback(int version) {
            this.version = version;
        }

        public void onDowngrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
            throw new SQLiteException("Can't downgrade database from version " + oldVersion + " to " + newVersion);
        }

        public void onCorruption(SupportSQLiteDatabase db) {
            Log.e(TAG, "Corruption reported by sqlite on database: " + db.getPath());
            if (!db.isOpen()) {
                deleteDatabaseFile(db.getPath());
                return;
            }
            List<Pair<String, String>> list = null;
            try {
                try {
                    list = db.getAttachedDbs();
                } catch (SQLiteException unused) {
                }
                try {
                    db.close();
                } catch (IOException unused2) {
                }
            } finally {
                if (list != null) {
                    for (Pair<String, String> next : list) {
                        deleteDatabaseFile((String) next.second);
                    }
                } else {
                    deleteDatabaseFile(db.getPath());
                }
            }
        }

        private void deleteDatabaseFile(String fileName) {
            if (fileName.equalsIgnoreCase(":memory:") || fileName.trim().length() == 0) {
                return;
            }
            Log.w(TAG, "deleting the database file: " + fileName);
            try {
                SupportSQLiteCompat.Api16Impl.deleteDatabase(new File(fileName));
            } catch (Exception e) {
                Log.w(TAG, "delete failed: ", e);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Configuration {
        public final Callback callback;
        public final Context context;
        public final String name;
        public final boolean useNoBackupDirectory;

        Configuration(Context context, String name, Callback callback) {
            this(context, name, callback, false);
        }

        Configuration(Context context, String name, Callback callback, boolean useNoBackupDirectory) {
            this.context = context;
            this.name = name;
            this.callback = callback;
            this.useNoBackupDirectory = useNoBackupDirectory;
        }

        public static Builder builder(Context context) {
            return new Builder(context);
        }

        /* loaded from: classes.dex */
        public static class Builder {
            Callback mCallback;
            Context mContext;
            String mName;
            boolean mUseNoBackupDirectory;

            public Configuration build() {
                if (this.mCallback == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                }
                if (this.mContext == null) {
                    throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
                }
                if (this.mUseNoBackupDirectory && TextUtils.isEmpty(this.mName)) {
                    throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
                }
                return new Configuration(this.mContext, this.mName, this.mCallback, this.mUseNoBackupDirectory);
            }

            Builder(Context context) {
                this.mContext = context;
            }

            public Builder name(String name) {
                this.mName = name;
                return this;
            }

            public Builder callback(Callback callback) {
                this.mCallback = callback;
                return this;
            }

            public Builder noBackupDirectory(boolean useNoBackupDirectory) {
                this.mUseNoBackupDirectory = useNoBackupDirectory;
                return this;
            }
        }
    }
}
