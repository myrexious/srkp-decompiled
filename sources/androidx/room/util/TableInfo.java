package androidx.room.util;

import android.database.Cursor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.xmpbox.type.JobType;

/* loaded from: classes.dex */
public final class TableInfo {
    public static final int CREATED_FROM_DATABASE = 2;
    public static final int CREATED_FROM_ENTITY = 1;
    public static final int CREATED_FROM_UNKNOWN = 0;
    public final Map<String, Column> columns;
    public final Set<ForeignKey> foreignKeys;
    public final Set<Index> indices;
    public final String name;

    public TableInfo(String name, Map<String, Column> columns, Set<ForeignKey> foreignKeys, Set<Index> indices) {
        this.name = name;
        this.columns = Collections.unmodifiableMap(columns);
        this.foreignKeys = Collections.unmodifiableSet(foreignKeys);
        this.indices = indices == null ? null : Collections.unmodifiableSet(indices);
    }

    public TableInfo(String name, Map<String, Column> columns, Set<ForeignKey> foreignKeys) {
        this(name, columns, foreignKeys, Collections.emptySet());
    }

    public boolean equals(Object o) {
        Set<Index> set;
        if (this == o) {
            return true;
        }
        if (o instanceof TableInfo) {
            TableInfo tableInfo = (TableInfo) o;
            String str = this.name;
            if (str == null ? tableInfo.name == null : str.equals(tableInfo.name)) {
                Map<String, Column> map = this.columns;
                if (map == null ? tableInfo.columns == null : map.equals(tableInfo.columns)) {
                    Set<ForeignKey> set2 = this.foreignKeys;
                    if (set2 == null ? tableInfo.foreignKeys == null : set2.equals(tableInfo.foreignKeys)) {
                        Set<Index> set3 = this.indices;
                        if (set3 == null || (set = tableInfo.indices) == null) {
                            return true;
                        }
                        return set3.equals(set);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Map<String, Column> map = this.columns;
        int hashCode2 = (hashCode + (map != null ? map.hashCode() : 0)) * 31;
        Set<ForeignKey> set = this.foreignKeys;
        return hashCode2 + (set != null ? set.hashCode() : 0);
    }

    public String toString() {
        return "TableInfo{name='" + this.name + "', columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + ", indices=" + this.indices + '}';
    }

    public static TableInfo read(SupportSQLiteDatabase database, String tableName) {
        return new TableInfo(tableName, readColumns(database, tableName), readForeignKeys(database, tableName), readIndices(database, tableName));
    }

    private static Set<ForeignKey> readForeignKeys(SupportSQLiteDatabase database, String tableName) {
        HashSet hashSet = new HashSet();
        Cursor query = database.query("PRAGMA foreign_key_list(`" + tableName + "`)");
        try {
            int columnIndex = query.getColumnIndex(JobType.ID);
            int columnIndex2 = query.getColumnIndex("seq");
            int columnIndex3 = query.getColumnIndex("table");
            int columnIndex4 = query.getColumnIndex("on_delete");
            int columnIndex5 = query.getColumnIndex("on_update");
            List<ForeignKeyWithSequence> readForeignKeyFieldMappings = readForeignKeyFieldMappings(query);
            int count = query.getCount();
            for (int i = 0; i < count; i++) {
                query.moveToPosition(i);
                if (query.getInt(columnIndex2) == 0) {
                    int i2 = query.getInt(columnIndex);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (ForeignKeyWithSequence foreignKeyWithSequence : readForeignKeyFieldMappings) {
                        if (foreignKeyWithSequence.mId == i2) {
                            arrayList.add(foreignKeyWithSequence.mFrom);
                            arrayList2.add(foreignKeyWithSequence.mTo);
                        }
                    }
                    hashSet.add(new ForeignKey(query.getString(columnIndex3), query.getString(columnIndex4), query.getString(columnIndex5), arrayList, arrayList2));
                }
            }
            return hashSet;
        } finally {
            query.close();
        }
    }

    private static List<ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex(JobType.ID);
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex("from");
        int columnIndex4 = cursor.getColumnIndex(TypedValues.TransitionType.S_TO);
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            arrayList.add(new ForeignKeyWithSequence(cursor.getInt(columnIndex), cursor.getInt(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4)));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static Map<String, Column> readColumns(SupportSQLiteDatabase database, String tableName) {
        Cursor query = database.query("PRAGMA table_info(`" + tableName + "`)");
        HashMap hashMap = new HashMap();
        try {
            if (query.getColumnCount() > 0) {
                int columnIndex = query.getColumnIndex("name");
                int columnIndex2 = query.getColumnIndex("type");
                int columnIndex3 = query.getColumnIndex("notnull");
                int columnIndex4 = query.getColumnIndex("pk");
                int columnIndex5 = query.getColumnIndex("dflt_value");
                while (query.moveToNext()) {
                    String string = query.getString(columnIndex);
                    hashMap.put(string, new Column(string, query.getString(columnIndex2), query.getInt(columnIndex3) != 0, query.getInt(columnIndex4), query.getString(columnIndex5), 2));
                }
            }
            return hashMap;
        } finally {
            query.close();
        }
    }

    private static Set<Index> readIndices(SupportSQLiteDatabase database, String tableName) {
        Cursor query = database.query("PRAGMA index_list(`" + tableName + "`)");
        try {
            int columnIndex = query.getColumnIndex("name");
            int columnIndex2 = query.getColumnIndex("origin");
            int columnIndex3 = query.getColumnIndex("unique");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                HashSet hashSet = new HashSet();
                while (query.moveToNext()) {
                    if (OperatorName.CURVE_TO.equals(query.getString(columnIndex2))) {
                        String string = query.getString(columnIndex);
                        boolean z = true;
                        if (query.getInt(columnIndex3) != 1) {
                            z = false;
                        }
                        Index readIndex = readIndex(database, string, z);
                        if (readIndex == null) {
                            return null;
                        }
                        hashSet.add(readIndex);
                    }
                }
                return hashSet;
            }
            return null;
        } finally {
            query.close();
        }
    }

    private static Index readIndex(SupportSQLiteDatabase database, String name, boolean unique) {
        Cursor query = database.query("PRAGMA index_xinfo(`" + name + "`)");
        try {
            int columnIndex = query.getColumnIndex("seqno");
            int columnIndex2 = query.getColumnIndex("cid");
            int columnIndex3 = query.getColumnIndex("name");
            if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                TreeMap treeMap = new TreeMap();
                while (query.moveToNext()) {
                    if (query.getInt(columnIndex2) >= 0) {
                        int i = query.getInt(columnIndex);
                        treeMap.put(Integer.valueOf(i), query.getString(columnIndex3));
                    }
                }
                ArrayList arrayList = new ArrayList(treeMap.size());
                arrayList.addAll(treeMap.values());
                return new Index(name, unique, arrayList);
            }
            query.close();
            return null;
        } finally {
            query.close();
        }
    }

    /* loaded from: classes.dex */
    public static final class Column {
        public final int affinity;
        public final String defaultValue;
        private final int mCreatedFrom;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        @Deprecated
        public Column(String name, String type, boolean notNull, int primaryKeyPosition) {
            this(name, type, notNull, primaryKeyPosition, null, 0);
        }

        public Column(String name, String type, boolean notNull, int primaryKeyPosition, String defaultValue, int createdFrom) {
            this.name = name;
            this.type = type;
            this.notNull = notNull;
            this.primaryKeyPosition = primaryKeyPosition;
            this.affinity = findAffinity(type);
            this.defaultValue = defaultValue;
            this.mCreatedFrom = createdFrom;
        }

        private static int findAffinity(String type) {
            if (type == null) {
                return 5;
            }
            String upperCase = type.toUpperCase(Locale.US);
            if (upperCase.contains("INT")) {
                return 3;
            }
            if (upperCase.contains("CHAR") || upperCase.contains("CLOB") || upperCase.contains("TEXT")) {
                return 2;
            }
            if (upperCase.contains("BLOB")) {
                return 5;
            }
            return (upperCase.contains("REAL") || upperCase.contains("FLOA") || upperCase.contains("DOUB")) ? 4 : 1;
        }

        public boolean equals(Object o) {
            String str;
            String str2;
            String str3;
            if (this == o) {
                return true;
            }
            if (o instanceof Column) {
                Column column = (Column) o;
                if (this.primaryKeyPosition == column.primaryKeyPosition && this.name.equals(column.name) && this.notNull == column.notNull) {
                    if (this.mCreatedFrom != 1 || column.mCreatedFrom != 2 || (str3 = this.defaultValue) == null || defaultValueEquals(str3, column.defaultValue)) {
                        if (this.mCreatedFrom != 2 || column.mCreatedFrom != 1 || (str2 = column.defaultValue) == null || defaultValueEquals(str2, this.defaultValue)) {
                            int i = this.mCreatedFrom;
                            return (i == 0 || i != column.mCreatedFrom || ((str = this.defaultValue) == null ? column.defaultValue == null : defaultValueEquals(str, column.defaultValue))) && this.affinity == column.affinity;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public static boolean defaultValueEquals(String actual, String other) {
            if (other == null) {
                return false;
            }
            if (actual.equals(other)) {
                return true;
            }
            if (containsSurroundingParenthesis(actual)) {
                return actual.substring(1, actual.length() - 1).trim().equals(other);
            }
            return false;
        }

        private static boolean containsSurroundingParenthesis(String actual) {
            if (actual.length() == 0) {
                return false;
            }
            int i = 0;
            for (int i2 = 0; i2 < actual.length(); i2++) {
                char charAt = actual.charAt(i2);
                if (i2 == 0 && charAt != '(') {
                    return false;
                }
                if (charAt == '(') {
                    i++;
                } else if (charAt == ')' && i - 1 == 0 && i2 != actual.length() - 1) {
                    return false;
                }
            }
            return i == 0;
        }

        public boolean isPrimaryKey() {
            return this.primaryKeyPosition > 0;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.affinity) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public String toString() {
            return "Column{name='" + this.name + "', type='" + this.type + "', affinity='" + this.affinity + "', notNull=" + this.notNull + ", primaryKeyPosition=" + this.primaryKeyPosition + ", defaultValue='" + this.defaultValue + "'}";
        }
    }

    /* loaded from: classes.dex */
    public static final class ForeignKey {
        public final List<String> columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List<String> referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String referenceTable, String onDelete, String onUpdate, List<String> columnNames, List<String> referenceColumnNames) {
            this.referenceTable = referenceTable;
            this.onDelete = onDelete;
            this.onUpdate = onUpdate;
            this.columnNames = Collections.unmodifiableList(columnNames);
            this.referenceColumnNames = Collections.unmodifiableList(referenceColumnNames);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof ForeignKey) {
                ForeignKey foreignKey = (ForeignKey) o;
                if (this.referenceTable.equals(foreignKey.referenceTable) && this.onDelete.equals(foreignKey.onDelete) && this.onUpdate.equals(foreignKey.onUpdate) && this.columnNames.equals(foreignKey.columnNames)) {
                    return this.referenceColumnNames.equals(foreignKey.referenceColumnNames);
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.referenceTable.hashCode() * 31) + this.onDelete.hashCode()) * 31) + this.onUpdate.hashCode()) * 31) + this.columnNames.hashCode()) * 31) + this.referenceColumnNames.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.referenceTable + "', onDelete='" + this.onDelete + "', onUpdate='" + this.onUpdate + "', columnNames=" + this.columnNames + ", referenceColumnNames=" + this.referenceColumnNames + '}';
        }
    }

    /* loaded from: classes.dex */
    public static class ForeignKeyWithSequence implements Comparable<ForeignKeyWithSequence> {
        final String mFrom;
        final int mId;
        final int mSequence;
        final String mTo;

        ForeignKeyWithSequence(int id2, int sequence, String from, String to) {
            this.mId = id2;
            this.mSequence = sequence;
            this.mFrom = from;
            this.mTo = to;
        }

        @Override // java.lang.Comparable
        public int compareTo(ForeignKeyWithSequence o) {
            int i = this.mId - o.mId;
            return i == 0 ? this.mSequence - o.mSequence : i;
        }
    }

    /* loaded from: classes.dex */
    public static final class Index {
        public static final String DEFAULT_PREFIX = "index_";
        public final List<String> columns;
        public final String name;
        public final boolean unique;

        public Index(String name, boolean unique, List<String> columns) {
            this.name = name;
            this.unique = unique;
            this.columns = columns;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof Index) {
                Index index = (Index) o;
                if (this.unique == index.unique && this.columns.equals(index.columns)) {
                    if (this.name.startsWith(DEFAULT_PREFIX)) {
                        return index.name.startsWith(DEFAULT_PREFIX);
                    }
                    return this.name.equals(index.name);
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return ((((this.name.startsWith(DEFAULT_PREFIX) ? -1184239155 : this.name.hashCode()) * 31) + (this.unique ? 1 : 0)) * 31) + this.columns.hashCode();
        }

        public String toString() {
            return "Index{name='" + this.name + "', unique=" + this.unique + ", columns=" + this.columns + '}';
        }
    }
}
