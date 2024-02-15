package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;

/* loaded from: classes.dex */
public abstract class EntityDeletionOrUpdateAdapter<T> extends SharedSQLiteStatement {
    protected abstract void bind(SupportSQLiteStatement statement, T entity);

    @Override // androidx.room.SharedSQLiteStatement
    protected abstract String createQuery();

    public EntityDeletionOrUpdateAdapter(RoomDatabase database) {
        super(database);
    }

    public final int handle(T entity) {
        SupportSQLiteStatement acquire = acquire();
        try {
            bind(acquire, entity);
            return acquire.executeUpdateDelete();
        } finally {
            release(acquire);
        }
    }

    public final int handleMultiple(Iterable<? extends T> entities) {
        SupportSQLiteStatement acquire = acquire();
        try {
            int i = 0;
            for (T t : entities) {
                bind(acquire, t);
                i += acquire.executeUpdateDelete();
            }
            return i;
        } finally {
            release(acquire);
        }
    }

    public final int handleMultiple(T[] entities) {
        SupportSQLiteStatement acquire = acquire();
        try {
            int i = 0;
            for (T t : entities) {
                bind(acquire, t);
                i += acquire.executeUpdateDelete();
            }
            return i;
        } finally {
            release(acquire);
        }
    }
}
