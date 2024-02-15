package androidx.sqlite.db;

/* loaded from: classes.dex */
public interface SupportSQLiteQuery {
    void bindTo(SupportSQLiteProgram statement);

    int getArgCount();

    String getSql();
}
