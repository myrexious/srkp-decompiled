package org.informatika.sirekap.model;

import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.informatika.sirekap.model.FormC1AdministrationComplete;

/* compiled from: ElectionPdfMetadata.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u001e\u001fB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003JA\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006 "}, d2 = {"Lorg/informatika/sirekap/model/ElectionPdfMetadata;", "", "sectionI", "Lorg/informatika/sirekap/model/ElectionPdfMetadata$SectionDataPdfMetadata;", "sectionII", "sectionIII", "sectionIVs", "", "sectionV", "(Lorg/informatika/sirekap/model/ElectionPdfMetadata$SectionDataPdfMetadata;Lorg/informatika/sirekap/model/ElectionPdfMetadata$SectionDataPdfMetadata;Lorg/informatika/sirekap/model/ElectionPdfMetadata$SectionDataPdfMetadata;Ljava/util/List;Lorg/informatika/sirekap/model/ElectionPdfMetadata$SectionDataPdfMetadata;)V", "getSectionI", "()Lorg/informatika/sirekap/model/ElectionPdfMetadata$SectionDataPdfMetadata;", "getSectionII", "getSectionIII", "getSectionIVs", "()Ljava/util/List;", "getSectionV", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "RowDataPdfMetadata", "SectionDataPdfMetadata", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ElectionPdfMetadata {
    private final SectionDataPdfMetadata sectionI;
    private final SectionDataPdfMetadata sectionII;
    private final SectionDataPdfMetadata sectionIII;
    private final List<SectionDataPdfMetadata> sectionIVs;
    private final SectionDataPdfMetadata sectionV;

    public static /* synthetic */ ElectionPdfMetadata copy$default(ElectionPdfMetadata electionPdfMetadata, SectionDataPdfMetadata sectionDataPdfMetadata, SectionDataPdfMetadata sectionDataPdfMetadata2, SectionDataPdfMetadata sectionDataPdfMetadata3, List list, SectionDataPdfMetadata sectionDataPdfMetadata4, int i, Object obj) {
        if ((i & 1) != 0) {
            sectionDataPdfMetadata = electionPdfMetadata.sectionI;
        }
        if ((i & 2) != 0) {
            sectionDataPdfMetadata2 = electionPdfMetadata.sectionII;
        }
        SectionDataPdfMetadata sectionDataPdfMetadata5 = sectionDataPdfMetadata2;
        if ((i & 4) != 0) {
            sectionDataPdfMetadata3 = electionPdfMetadata.sectionIII;
        }
        SectionDataPdfMetadata sectionDataPdfMetadata6 = sectionDataPdfMetadata3;
        List<SectionDataPdfMetadata> list2 = list;
        if ((i & 8) != 0) {
            list2 = electionPdfMetadata.sectionIVs;
        }
        List list3 = list2;
        if ((i & 16) != 0) {
            sectionDataPdfMetadata4 = electionPdfMetadata.sectionV;
        }
        return electionPdfMetadata.copy(sectionDataPdfMetadata, sectionDataPdfMetadata5, sectionDataPdfMetadata6, list3, sectionDataPdfMetadata4);
    }

    public final SectionDataPdfMetadata component1() {
        return this.sectionI;
    }

    public final SectionDataPdfMetadata component2() {
        return this.sectionII;
    }

    public final SectionDataPdfMetadata component3() {
        return this.sectionIII;
    }

    public final List<SectionDataPdfMetadata> component4() {
        return this.sectionIVs;
    }

    public final SectionDataPdfMetadata component5() {
        return this.sectionV;
    }

    public final ElectionPdfMetadata copy(SectionDataPdfMetadata sectionI, SectionDataPdfMetadata sectionII, SectionDataPdfMetadata sectionIII, List<SectionDataPdfMetadata> sectionIVs, SectionDataPdfMetadata sectionV) {
        Intrinsics.checkNotNullParameter(sectionI, "sectionI");
        Intrinsics.checkNotNullParameter(sectionII, "sectionII");
        Intrinsics.checkNotNullParameter(sectionIII, "sectionIII");
        Intrinsics.checkNotNullParameter(sectionIVs, "sectionIVs");
        Intrinsics.checkNotNullParameter(sectionV, "sectionV");
        return new ElectionPdfMetadata(sectionI, sectionII, sectionIII, sectionIVs, sectionV);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ElectionPdfMetadata) {
            ElectionPdfMetadata electionPdfMetadata = (ElectionPdfMetadata) obj;
            return Intrinsics.areEqual(this.sectionI, electionPdfMetadata.sectionI) && Intrinsics.areEqual(this.sectionII, electionPdfMetadata.sectionII) && Intrinsics.areEqual(this.sectionIII, electionPdfMetadata.sectionIII) && Intrinsics.areEqual(this.sectionIVs, electionPdfMetadata.sectionIVs) && Intrinsics.areEqual(this.sectionV, electionPdfMetadata.sectionV);
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.sectionI.hashCode() * 31) + this.sectionII.hashCode()) * 31) + this.sectionIII.hashCode()) * 31) + this.sectionIVs.hashCode()) * 31) + this.sectionV.hashCode();
    }

    public String toString() {
        SectionDataPdfMetadata sectionDataPdfMetadata = this.sectionI;
        SectionDataPdfMetadata sectionDataPdfMetadata2 = this.sectionII;
        SectionDataPdfMetadata sectionDataPdfMetadata3 = this.sectionIII;
        List<SectionDataPdfMetadata> list = this.sectionIVs;
        return "ElectionPdfMetadata(sectionI=" + sectionDataPdfMetadata + ", sectionII=" + sectionDataPdfMetadata2 + ", sectionIII=" + sectionDataPdfMetadata3 + ", sectionIVs=" + list + ", sectionV=" + this.sectionV + ")";
    }

    public ElectionPdfMetadata(SectionDataPdfMetadata sectionI, SectionDataPdfMetadata sectionII, SectionDataPdfMetadata sectionIII, List<SectionDataPdfMetadata> sectionIVs, SectionDataPdfMetadata sectionV) {
        Intrinsics.checkNotNullParameter(sectionI, "sectionI");
        Intrinsics.checkNotNullParameter(sectionII, "sectionII");
        Intrinsics.checkNotNullParameter(sectionIII, "sectionIII");
        Intrinsics.checkNotNullParameter(sectionIVs, "sectionIVs");
        Intrinsics.checkNotNullParameter(sectionV, "sectionV");
        this.sectionI = sectionI;
        this.sectionII = sectionII;
        this.sectionIII = sectionIII;
        this.sectionIVs = sectionIVs;
        this.sectionV = sectionV;
    }

    public final SectionDataPdfMetadata getSectionI() {
        return this.sectionI;
    }

    public final SectionDataPdfMetadata getSectionII() {
        return this.sectionII;
    }

    public final SectionDataPdfMetadata getSectionIII() {
        return this.sectionIII;
    }

    public final List<SectionDataPdfMetadata> getSectionIVs() {
        return this.sectionIVs;
    }

    public final SectionDataPdfMetadata getSectionV() {
        return this.sectionV;
    }

    /* compiled from: ElectionPdfMetadata.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/informatika/sirekap/model/ElectionPdfMetadata$SectionDataPdfMetadata;", "", "number", "", "rows", "", "Lorg/informatika/sirekap/model/ElectionPdfMetadata$RowDataPdfMetadata;", "(Ljava/lang/String;Ljava/util/List;)V", "getNumber", "()Ljava/lang/String;", "getRows", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class SectionDataPdfMetadata {
        public static final Companion Companion = new Companion(null);
        private final String number;
        private final List<RowDataPdfMetadata> rows;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SectionDataPdfMetadata copy$default(SectionDataPdfMetadata sectionDataPdfMetadata, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = sectionDataPdfMetadata.number;
            }
            if ((i & 2) != 0) {
                list = sectionDataPdfMetadata.rows;
            }
            return sectionDataPdfMetadata.copy(str, list);
        }

        public final String component1() {
            return this.number;
        }

        public final List<RowDataPdfMetadata> component2() {
            return this.rows;
        }

        public final SectionDataPdfMetadata copy(String number, List<RowDataPdfMetadata> rows) {
            Intrinsics.checkNotNullParameter(number, "number");
            Intrinsics.checkNotNullParameter(rows, "rows");
            return new SectionDataPdfMetadata(number, rows);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SectionDataPdfMetadata) {
                SectionDataPdfMetadata sectionDataPdfMetadata = (SectionDataPdfMetadata) obj;
                return Intrinsics.areEqual(this.number, sectionDataPdfMetadata.number) && Intrinsics.areEqual(this.rows, sectionDataPdfMetadata.rows);
            }
            return false;
        }

        public int hashCode() {
            return (this.number.hashCode() * 31) + this.rows.hashCode();
        }

        public String toString() {
            String str = this.number;
            return "SectionDataPdfMetadata(number=" + str + ", rows=" + this.rows + ")";
        }

        public SectionDataPdfMetadata(String number, List<RowDataPdfMetadata> rows) {
            Intrinsics.checkNotNullParameter(number, "number");
            Intrinsics.checkNotNullParameter(rows, "rows");
            this.number = number;
            this.rows = rows;
        }

        public final String getNumber() {
            return this.number;
        }

        public /* synthetic */ SectionDataPdfMetadata(String str, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? new ArrayList() : arrayList);
        }

        public final List<RowDataPdfMetadata> getRows() {
            return this.rows;
        }

        /* compiled from: ElectionPdfMetadata.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/model/ElectionPdfMetadata$SectionDataPdfMetadata$Companion;", "", "()V", "fromModel", "Lorg/informatika/sirekap/model/ElectionPdfMetadata$SectionDataPdfMetadata;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lorg/informatika/sirekap/model/FormC1AdministrationComplete$SectionDataPdf;", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes2.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final SectionDataPdfMetadata fromModel(FormC1AdministrationComplete.SectionDataPdf data) {
                Intrinsics.checkNotNullParameter(data, "data");
                ArrayList arrayList = new ArrayList();
                while (true) {
                    String str = "";
                    for (FormC1AdministrationComplete.RowDataPdf rowDataPdf : data.getRows()) {
                        if (rowDataPdf.getType() == FormC1AdministrationComplete.RowDataPdfType.header) {
                            str = rowDataPdf.getNumber();
                            if (str == null) {
                                break;
                            }
                        } else if (rowDataPdf.getType() == FormC1AdministrationComplete.RowDataPdfType.content) {
                            String number = rowDataPdf.getNumber();
                            if (number == null) {
                                number = "";
                            }
                            if (!StringsKt.isBlank(str)) {
                                number = ((Object) str) + number;
                            }
                            arrayList.add(new RowDataPdfMetadata(number, rowDataPdf.getMale(), rowDataPdf.getFemale(), rowDataPdf.getTotal()));
                        }
                    }
                    return new SectionDataPdfMetadata(data.getNumber(), arrayList);
                }
            }
        }
    }

    /* compiled from: ElectionPdfMetadata.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ>\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000f\u0010\n¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/model/ElectionPdfMetadata$RowDataPdfMetadata;", "", "number", "", "male", "", "female", "total", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getFemale", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMale", "getNumber", "()Ljava/lang/String;", "getTotal", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/informatika/sirekap/model/ElectionPdfMetadata$RowDataPdfMetadata;", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class RowDataPdfMetadata {
        private final Integer female;
        private final Integer male;
        private final String number;
        private final Integer total;

        public static /* synthetic */ RowDataPdfMetadata copy$default(RowDataPdfMetadata rowDataPdfMetadata, String str, Integer num, Integer num2, Integer num3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = rowDataPdfMetadata.number;
            }
            if ((i & 2) != 0) {
                num = rowDataPdfMetadata.male;
            }
            if ((i & 4) != 0) {
                num2 = rowDataPdfMetadata.female;
            }
            if ((i & 8) != 0) {
                num3 = rowDataPdfMetadata.total;
            }
            return rowDataPdfMetadata.copy(str, num, num2, num3);
        }

        public final String component1() {
            return this.number;
        }

        public final Integer component2() {
            return this.male;
        }

        public final Integer component3() {
            return this.female;
        }

        public final Integer component4() {
            return this.total;
        }

        public final RowDataPdfMetadata copy(String str, Integer num, Integer num2, Integer num3) {
            return new RowDataPdfMetadata(str, num, num2, num3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof RowDataPdfMetadata) {
                RowDataPdfMetadata rowDataPdfMetadata = (RowDataPdfMetadata) obj;
                return Intrinsics.areEqual(this.number, rowDataPdfMetadata.number) && Intrinsics.areEqual(this.male, rowDataPdfMetadata.male) && Intrinsics.areEqual(this.female, rowDataPdfMetadata.female) && Intrinsics.areEqual(this.total, rowDataPdfMetadata.total);
            }
            return false;
        }

        public int hashCode() {
            String str = this.number;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            Integer num = this.male;
            int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.female;
            int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.total;
            return hashCode3 + (num3 != null ? num3.hashCode() : 0);
        }

        public String toString() {
            String str = this.number;
            Integer num = this.male;
            Integer num2 = this.female;
            return "RowDataPdfMetadata(number=" + str + ", male=" + num + ", female=" + num2 + ", total=" + this.total + ")";
        }

        public RowDataPdfMetadata(String str, Integer num, Integer num2, Integer num3) {
            this.number = str;
            this.male = num;
            this.female = num2;
            this.total = num3;
        }

        public /* synthetic */ RowDataPdfMetadata(String str, Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3);
        }

        public final String getNumber() {
            return this.number;
        }

        public final Integer getMale() {
            return this.male;
        }

        public final Integer getFemale() {
            return this.female;
        }

        public final Integer getTotal() {
            return this.total;
        }
    }
}
