package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class GlyphSubstitutionTable extends TTFTable {
    public static final String TAG = "GSUB";
    private FeatureRecord[] featureList;
    private String lastUsedSupportedScript;
    private final Map<Integer, Integer> lookupCache;
    private LookupTable[] lookupList;
    private final Map<Integer, Integer> reverseLookup;
    private LinkedHashMap<String, ScriptTable> scriptList;

    public GlyphSubstitutionTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
        this.lookupCache = new HashMap();
        this.reverseLookup = new HashMap();
    }

    @Override // com.tom_roush.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        long currentPosition = tTFDataStream.getCurrentPosition();
        tTFDataStream.readUnsignedShort();
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
        int readUnsignedShort3 = tTFDataStream.readUnsignedShort();
        int readUnsignedShort4 = tTFDataStream.readUnsignedShort();
        if (readUnsignedShort == 1) {
            tTFDataStream.readUnsignedInt();
        }
        this.scriptList = readScriptList(tTFDataStream, readUnsignedShort2 + currentPosition);
        this.featureList = readFeatureList(tTFDataStream, readUnsignedShort3 + currentPosition);
        this.lookupList = readLookupList(tTFDataStream, currentPosition + readUnsignedShort4);
    }

    LinkedHashMap<String, ScriptTable> readScriptList(TTFDataStream tTFDataStream, long j) throws IOException {
        tTFDataStream.seek(j);
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        ScriptRecord[] scriptRecordArr = new ScriptRecord[readUnsignedShort];
        int[] iArr = new int[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            ScriptRecord scriptRecord = new ScriptRecord();
            scriptRecord.scriptTag = tTFDataStream.readString(4);
            iArr[i] = tTFDataStream.readUnsignedShort();
            scriptRecordArr[i] = scriptRecord;
        }
        for (int i2 = 0; i2 < readUnsignedShort; i2++) {
            scriptRecordArr[i2].scriptTable = readScriptTable(tTFDataStream, iArr[i2] + j);
        }
        LinkedHashMap<String, ScriptTable> linkedHashMap = new LinkedHashMap<>(readUnsignedShort);
        for (int i3 = 0; i3 < readUnsignedShort; i3++) {
            ScriptRecord scriptRecord2 = scriptRecordArr[i3];
            linkedHashMap.put(scriptRecord2.scriptTag, scriptRecord2.scriptTable);
        }
        return linkedHashMap;
    }

    ScriptTable readScriptTable(TTFDataStream tTFDataStream, long j) throws IOException {
        tTFDataStream.seek(j);
        ScriptTable scriptTable = new ScriptTable();
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
        LangSysRecord[] langSysRecordArr = new LangSysRecord[readUnsignedShort2];
        int[] iArr = new int[readUnsignedShort2];
        String str = "";
        for (int i = 0; i < readUnsignedShort2; i++) {
            LangSysRecord langSysRecord = new LangSysRecord();
            langSysRecord.langSysTag = tTFDataStream.readString(4);
            if (i > 0 && langSysRecord.langSysTag.compareTo(str) <= 0) {
                throw new IOException("LangSysRecords not alphabetically sorted by LangSys tag: " + langSysRecord.langSysTag + " <= " + str);
            }
            iArr[i] = tTFDataStream.readUnsignedShort();
            langSysRecordArr[i] = langSysRecord;
            str = langSysRecord.langSysTag;
        }
        if (readUnsignedShort != 0) {
            scriptTable.defaultLangSysTable = readLangSysTable(tTFDataStream, readUnsignedShort + j);
        }
        for (int i2 = 0; i2 < readUnsignedShort2; i2++) {
            langSysRecordArr[i2].langSysTable = readLangSysTable(tTFDataStream, iArr[i2] + j);
        }
        scriptTable.langSysTables = new LinkedHashMap<>(readUnsignedShort2);
        for (int i3 = 0; i3 < readUnsignedShort2; i3++) {
            LangSysRecord langSysRecord2 = langSysRecordArr[i3];
            scriptTable.langSysTables.put(langSysRecord2.langSysTag, langSysRecord2.langSysTable);
        }
        return scriptTable;
    }

    LangSysTable readLangSysTable(TTFDataStream tTFDataStream, long j) throws IOException {
        tTFDataStream.seek(j);
        LangSysTable langSysTable = new LangSysTable();
        tTFDataStream.readUnsignedShort();
        langSysTable.requiredFeatureIndex = tTFDataStream.readUnsignedShort();
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        langSysTable.featureIndices = new int[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            langSysTable.featureIndices[i] = tTFDataStream.readUnsignedShort();
        }
        return langSysTable;
    }

    FeatureRecord[] readFeatureList(TTFDataStream tTFDataStream, long j) throws IOException {
        tTFDataStream.seek(j);
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        FeatureRecord[] featureRecordArr = new FeatureRecord[readUnsignedShort];
        int[] iArr = new int[readUnsignedShort];
        String str = "";
        for (int i = 0; i < readUnsignedShort; i++) {
            FeatureRecord featureRecord = new FeatureRecord();
            featureRecord.featureTag = tTFDataStream.readString(4);
            if (i > 0 && featureRecord.featureTag.compareTo(str) < 0) {
                if (featureRecord.featureTag.matches("\\w{4}") && str.matches("\\w{4}")) {
                    Log.d("PdfBox-Android", "FeatureRecord array not alphabetically sorted by FeatureTag: " + featureRecord.featureTag + " < " + str);
                } else {
                    Log.w("PdfBox-Android", "FeatureRecord array not alphabetically sorted by FeatureTag: " + featureRecord.featureTag + " < " + str);
                    return new FeatureRecord[0];
                }
            }
            iArr[i] = tTFDataStream.readUnsignedShort();
            featureRecordArr[i] = featureRecord;
            str = featureRecord.featureTag;
        }
        for (int i2 = 0; i2 < readUnsignedShort; i2++) {
            featureRecordArr[i2].featureTable = readFeatureTable(tTFDataStream, iArr[i2] + j);
        }
        return featureRecordArr;
    }

    FeatureTable readFeatureTable(TTFDataStream tTFDataStream, long j) throws IOException {
        tTFDataStream.seek(j);
        FeatureTable featureTable = new FeatureTable();
        tTFDataStream.readUnsignedShort();
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        featureTable.lookupListIndices = new int[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            featureTable.lookupListIndices[i] = tTFDataStream.readUnsignedShort();
        }
        return featureTable;
    }

    LookupTable[] readLookupList(TTFDataStream tTFDataStream, long j) throws IOException {
        tTFDataStream.seek(j);
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        int[] iArr = new int[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            iArr[i] = tTFDataStream.readUnsignedShort();
        }
        LookupTable[] lookupTableArr = new LookupTable[readUnsignedShort];
        for (int i2 = 0; i2 < readUnsignedShort; i2++) {
            lookupTableArr[i2] = readLookupTable(tTFDataStream, iArr[i2] + j);
        }
        return lookupTableArr;
    }

    LookupTable readLookupTable(TTFDataStream tTFDataStream, long j) throws IOException {
        tTFDataStream.seek(j);
        LookupTable lookupTable = new LookupTable();
        lookupTable.lookupType = tTFDataStream.readUnsignedShort();
        lookupTable.lookupFlag = tTFDataStream.readUnsignedShort();
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        int[] iArr = new int[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            iArr[i] = tTFDataStream.readUnsignedShort();
        }
        if ((lookupTable.lookupFlag & 16) != 0) {
            lookupTable.markFilteringSet = tTFDataStream.readUnsignedShort();
        }
        lookupTable.subTables = new LookupSubTable[readUnsignedShort];
        if (lookupTable.lookupType != 1) {
            Log.d("PdfBox-Android", "Type " + lookupTable.lookupType + " GSUB lookup table is not supported and will be ignored");
        } else {
            for (int i2 = 0; i2 < readUnsignedShort; i2++) {
                lookupTable.subTables[i2] = readLookupSubTable(tTFDataStream, iArr[i2] + j);
            }
        }
        return lookupTable;
    }

    LookupSubTable readLookupSubTable(TTFDataStream tTFDataStream, long j) throws IOException {
        tTFDataStream.seek(j);
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        if (readUnsignedShort == 1) {
            LookupTypeSingleSubstFormat1 lookupTypeSingleSubstFormat1 = new LookupTypeSingleSubstFormat1();
            lookupTypeSingleSubstFormat1.substFormat = readUnsignedShort;
            int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
            lookupTypeSingleSubstFormat1.deltaGlyphID = tTFDataStream.readSignedShort();
            lookupTypeSingleSubstFormat1.coverageTable = readCoverageTable(tTFDataStream, j + readUnsignedShort2);
            return lookupTypeSingleSubstFormat1;
        } else if (readUnsignedShort == 2) {
            LookupTypeSingleSubstFormat2 lookupTypeSingleSubstFormat2 = new LookupTypeSingleSubstFormat2();
            lookupTypeSingleSubstFormat2.substFormat = readUnsignedShort;
            int readUnsignedShort3 = tTFDataStream.readUnsignedShort();
            int readUnsignedShort4 = tTFDataStream.readUnsignedShort();
            lookupTypeSingleSubstFormat2.substituteGlyphIDs = new int[readUnsignedShort4];
            for (int i = 0; i < readUnsignedShort4; i++) {
                lookupTypeSingleSubstFormat2.substituteGlyphIDs[i] = tTFDataStream.readUnsignedShort();
            }
            lookupTypeSingleSubstFormat2.coverageTable = readCoverageTable(tTFDataStream, j + readUnsignedShort3);
            return lookupTypeSingleSubstFormat2;
        } else {
            throw new IOException("Unknown substFormat: " + readUnsignedShort);
        }
    }

    CoverageTable readCoverageTable(TTFDataStream tTFDataStream, long j) throws IOException {
        tTFDataStream.seek(j);
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        int i = 0;
        if (readUnsignedShort == 1) {
            CoverageTableFormat1 coverageTableFormat1 = new CoverageTableFormat1();
            coverageTableFormat1.coverageFormat = readUnsignedShort;
            int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
            coverageTableFormat1.glyphArray = new int[readUnsignedShort2];
            while (i < readUnsignedShort2) {
                coverageTableFormat1.glyphArray[i] = tTFDataStream.readUnsignedShort();
                i++;
            }
            return coverageTableFormat1;
        } else if (readUnsignedShort == 2) {
            CoverageTableFormat2 coverageTableFormat2 = new CoverageTableFormat2();
            coverageTableFormat2.coverageFormat = readUnsignedShort;
            int readUnsignedShort3 = tTFDataStream.readUnsignedShort();
            coverageTableFormat2.rangeRecords = new RangeRecord[readUnsignedShort3];
            while (i < readUnsignedShort3) {
                coverageTableFormat2.rangeRecords[i] = readRangeRecord(tTFDataStream);
                i++;
            }
            return coverageTableFormat2;
        } else {
            throw new IOException("Unknown coverage format: " + readUnsignedShort);
        }
    }

    private String selectScriptTag(String[] strArr) {
        if (strArr.length == 1) {
            String str = strArr[0];
            if (OpenTypeScript.INHERITED.equals(str) || (OpenTypeScript.TAG_DEFAULT.equals(str) && !this.scriptList.containsKey(str))) {
                if (this.lastUsedSupportedScript == null) {
                    this.lastUsedSupportedScript = this.scriptList.keySet().iterator().next();
                }
                return this.lastUsedSupportedScript;
            }
        }
        for (String str2 : strArr) {
            if (this.scriptList.containsKey(str2)) {
                this.lastUsedSupportedScript = str2;
                return str2;
            }
        }
        return strArr[0];
    }

    private Collection<LangSysTable> getLangSysTables(String str) {
        List emptyList = Collections.emptyList();
        ScriptTable scriptTable = this.scriptList.get(str);
        if (scriptTable != null) {
            if (scriptTable.defaultLangSysTable == null) {
                return scriptTable.langSysTables.values();
            }
            ArrayList arrayList = new ArrayList(scriptTable.langSysTables.values());
            arrayList.add(scriptTable.defaultLangSysTable);
            return arrayList;
        }
        return emptyList;
    }

    private List<FeatureRecord> getFeatureRecords(Collection<LangSysTable> collection, final List<String> list) {
        int[] iArr;
        if (collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (LangSysTable langSysTable : collection) {
            int i = langSysTable.requiredFeatureIndex;
            if (i != 65535) {
                FeatureRecord[] featureRecordArr = this.featureList;
                if (i < featureRecordArr.length) {
                    arrayList.add(featureRecordArr[i]);
                }
            }
            for (int i2 : langSysTable.featureIndices) {
                FeatureRecord[] featureRecordArr2 = this.featureList;
                if (i2 < featureRecordArr2.length && (list == null || list.contains(featureRecordArr2[i2].featureTag))) {
                    arrayList.add(this.featureList[i2]);
                }
            }
        }
        if (containsFeature(arrayList, "vrt2")) {
            removeFeature(arrayList, "vert");
        }
        if (list != null && arrayList.size() > 1) {
            Collections.sort(arrayList, new Comparator<FeatureRecord>() { // from class: com.tom_roush.fontbox.ttf.GlyphSubstitutionTable.1
                @Override // java.util.Comparator
                public int compare(FeatureRecord featureRecord, FeatureRecord featureRecord2) {
                    int indexOf = list.indexOf(featureRecord.featureTag);
                    int indexOf2 = list.indexOf(featureRecord2.featureTag);
                    if (indexOf < indexOf2) {
                        return -1;
                    }
                    return indexOf == indexOf2 ? 0 : 1;
                }
            });
        }
        return arrayList;
    }

    private boolean containsFeature(List<FeatureRecord> list, String str) {
        for (FeatureRecord featureRecord : list) {
            if (featureRecord.featureTag.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void removeFeature(List<FeatureRecord> list, String str) {
        Iterator<FeatureRecord> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().featureTag.equals(str)) {
                it.remove();
            }
        }
    }

    private int applyFeature(FeatureRecord featureRecord, int i) {
        for (int i2 : featureRecord.featureTable.lookupListIndices) {
            LookupTable lookupTable = this.lookupList[i2];
            if (lookupTable.lookupType != 1) {
                Log.d("PdfBox-Android", "Skipping GSUB feature '" + featureRecord.featureTag + "' because it requires unsupported lookup table type " + lookupTable.lookupType);
            } else {
                i = doLookup(lookupTable, i);
            }
        }
        return i;
    }

    private int doLookup(LookupTable lookupTable, int i) {
        LookupSubTable[] lookupSubTableArr;
        for (LookupSubTable lookupSubTable : lookupTable.subTables) {
            int coverageIndex = lookupSubTable.coverageTable.getCoverageIndex(i);
            if (coverageIndex >= 0) {
                return lookupSubTable.doSubstitution(i, coverageIndex);
            }
        }
        return i;
    }

    public int getSubstitution(int i, String[] strArr, List<String> list) {
        if (i == -1) {
            return -1;
        }
        Integer num = this.lookupCache.get(Integer.valueOf(i));
        if (num != null) {
            return num.intValue();
        }
        int i2 = i;
        for (FeatureRecord featureRecord : getFeatureRecords(getLangSysTables(selectScriptTag(strArr)), list)) {
            i2 = applyFeature(featureRecord, i2);
        }
        this.lookupCache.put(Integer.valueOf(i), Integer.valueOf(i2));
        this.reverseLookup.put(Integer.valueOf(i2), Integer.valueOf(i));
        return i2;
    }

    public int getUnsubstitution(int i) {
        Integer num = this.reverseLookup.get(Integer.valueOf(i));
        if (num == null) {
            Log.w("PdfBox-Android", "Trying to un-substitute a never-before-seen gid: " + i);
            return i;
        }
        return num.intValue();
    }

    RangeRecord readRangeRecord(TTFDataStream tTFDataStream) throws IOException {
        RangeRecord rangeRecord = new RangeRecord();
        rangeRecord.startGlyphID = tTFDataStream.readUnsignedShort();
        rangeRecord.endGlyphID = tTFDataStream.readUnsignedShort();
        rangeRecord.startCoverageIndex = tTFDataStream.readUnsignedShort();
        return rangeRecord;
    }

    /* loaded from: classes3.dex */
    public static class ScriptRecord {
        ScriptTable scriptTable;
        String scriptTag;

        ScriptRecord() {
        }

        public String toString() {
            return String.format("ScriptRecord[scriptTag=%s]", this.scriptTag);
        }
    }

    /* loaded from: classes3.dex */
    public static class ScriptTable {
        LangSysTable defaultLangSysTable;
        LinkedHashMap<String, LangSysTable> langSysTables;

        ScriptTable() {
        }

        public String toString() {
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(this.defaultLangSysTable != null);
            objArr[1] = Integer.valueOf(this.langSysTables.size());
            return String.format("ScriptTable[hasDefault=%s,langSysRecordsCount=%d]", objArr);
        }
    }

    /* loaded from: classes3.dex */
    public static class LangSysRecord {
        LangSysTable langSysTable;
        String langSysTag;

        LangSysRecord() {
        }

        public String toString() {
            return String.format("LangSysRecord[langSysTag=%s]", this.langSysTag);
        }
    }

    /* loaded from: classes3.dex */
    public static class LangSysTable {
        int[] featureIndices;
        int requiredFeatureIndex;

        LangSysTable() {
        }

        public String toString() {
            return String.format("LangSysTable[requiredFeatureIndex=%d]", Integer.valueOf(this.requiredFeatureIndex));
        }
    }

    /* loaded from: classes3.dex */
    public static class FeatureRecord {
        FeatureTable featureTable;
        String featureTag;

        FeatureRecord() {
        }

        public String toString() {
            return String.format("FeatureRecord[featureTag=%s]", this.featureTag);
        }
    }

    /* loaded from: classes3.dex */
    public static class FeatureTable {
        int[] lookupListIndices;

        FeatureTable() {
        }

        public String toString() {
            return String.format("FeatureTable[lookupListIndiciesCount=%d]", Integer.valueOf(this.lookupListIndices.length));
        }
    }

    /* loaded from: classes3.dex */
    public static class LookupTable {
        int lookupFlag;
        int lookupType;
        int markFilteringSet;
        LookupSubTable[] subTables;

        LookupTable() {
        }

        public String toString() {
            return String.format("LookupTable[lookupType=%d,lookupFlag=%d,markFilteringSet=%d]", Integer.valueOf(this.lookupType), Integer.valueOf(this.lookupFlag), Integer.valueOf(this.markFilteringSet));
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class LookupSubTable {
        CoverageTable coverageTable;
        int substFormat;

        abstract int doSubstitution(int i, int i2);

        LookupSubTable() {
        }
    }

    /* loaded from: classes3.dex */
    public static class LookupTypeSingleSubstFormat1 extends LookupSubTable {
        short deltaGlyphID;

        LookupTypeSingleSubstFormat1() {
        }

        @Override // com.tom_roush.fontbox.ttf.GlyphSubstitutionTable.LookupSubTable
        int doSubstitution(int i, int i2) {
            return i2 < 0 ? i : i + this.deltaGlyphID;
        }

        public String toString() {
            return String.format("LookupTypeSingleSubstFormat1[substFormat=%d,deltaGlyphID=%d]", Integer.valueOf(this.substFormat), Short.valueOf(this.deltaGlyphID));
        }
    }

    /* loaded from: classes3.dex */
    public static class LookupTypeSingleSubstFormat2 extends LookupSubTable {
        int[] substituteGlyphIDs;

        LookupTypeSingleSubstFormat2() {
        }

        @Override // com.tom_roush.fontbox.ttf.GlyphSubstitutionTable.LookupSubTable
        int doSubstitution(int i, int i2) {
            return i2 < 0 ? i : this.substituteGlyphIDs[i2];
        }

        public String toString() {
            return String.format("LookupTypeSingleSubstFormat2[substFormat=%d,substituteGlyphIDs=%s]", Integer.valueOf(this.substFormat), Arrays.toString(this.substituteGlyphIDs));
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class CoverageTable {
        int coverageFormat;

        abstract int getCoverageIndex(int i);

        CoverageTable() {
        }
    }

    /* loaded from: classes3.dex */
    public static class CoverageTableFormat1 extends CoverageTable {
        int[] glyphArray;

        CoverageTableFormat1() {
        }

        @Override // com.tom_roush.fontbox.ttf.GlyphSubstitutionTable.CoverageTable
        int getCoverageIndex(int i) {
            return Arrays.binarySearch(this.glyphArray, i);
        }

        public String toString() {
            return String.format("CoverageTableFormat1[coverageFormat=%d,glyphArray=%s]", Integer.valueOf(this.coverageFormat), Arrays.toString(this.glyphArray));
        }
    }

    /* loaded from: classes3.dex */
    public static class CoverageTableFormat2 extends CoverageTable {
        RangeRecord[] rangeRecords;

        CoverageTableFormat2() {
        }

        @Override // com.tom_roush.fontbox.ttf.GlyphSubstitutionTable.CoverageTable
        int getCoverageIndex(int i) {
            RangeRecord[] rangeRecordArr;
            for (RangeRecord rangeRecord : this.rangeRecords) {
                if (rangeRecord.startGlyphID <= i && i <= rangeRecord.endGlyphID) {
                    return (rangeRecord.startCoverageIndex + i) - rangeRecord.startGlyphID;
                }
            }
            return -1;
        }

        public String toString() {
            return String.format("CoverageTableFormat2[coverageFormat=%d]", Integer.valueOf(this.coverageFormat));
        }
    }

    /* loaded from: classes3.dex */
    public static class RangeRecord {
        int endGlyphID;
        int startCoverageIndex;
        int startGlyphID;

        RangeRecord() {
        }

        public String toString() {
            return String.format("RangeRecord[startGlyphID=%d,endGlyphID=%d,startCoverageIndex=%d]", Integer.valueOf(this.startGlyphID), Integer.valueOf(this.endGlyphID), Integer.valueOf(this.startCoverageIndex));
        }
    }
}
