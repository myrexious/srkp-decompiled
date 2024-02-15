package org.informatika.sirekap.model;

import android.util.Log;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: ActiveProfile.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 .2\u00020\u0001:\u0001.B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003JK\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010%\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010'\u001a\u00020\u0003J\u0006\u0010(\u001a\u00020\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\u0006\u0010+\u001a\u00020\u0014J\u0006\u0010,\u001a\u00020\u0014J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000bR\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000bR\u0013\u0010\u001a\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000fR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u000b¨\u0006/"}, d2 = {"Lorg/informatika/sirekap/model/ActiveProfile;", "", "nama_profil", "", "id_wilayah", "kode_wilayah", "role", "start", "end", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEnd", "()Ljava/lang/String;", "endDate", "Ljava/util/Calendar;", "getEndDate", "()Ljava/util/Calendar;", "endDateFormatted", "getEndDateFormatted", "getId_wilayah", "isValid", "", "()Z", "getKode_wilayah", "getNama_profil", "getRole", "getStart", "startDate", "getStartDate", "startDateFormatted", "getStartDateFormatted", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "getKodeWilayah", "getRoleUser", "hashCode", "", "isKodeWilayahValid", "isRoleValid", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ActiveProfile {
    public static final Companion Companion = new Companion(null);
    private final String end;
    private final String id_wilayah;
    private final String kode_wilayah;
    private final String nama_profil;
    private final String role;
    private final String start;

    public static /* synthetic */ ActiveProfile copy$default(ActiveProfile activeProfile, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = activeProfile.nama_profil;
        }
        if ((i & 2) != 0) {
            str2 = activeProfile.id_wilayah;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = activeProfile.kode_wilayah;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = activeProfile.role;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = activeProfile.start;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = activeProfile.end;
        }
        return activeProfile.copy(str, str7, str8, str9, str10, str6);
    }

    public final String component1() {
        return this.nama_profil;
    }

    public final String component2() {
        return this.id_wilayah;
    }

    public final String component3() {
        return this.kode_wilayah;
    }

    public final String component4() {
        return this.role;
    }

    public final String component5() {
        return this.start;
    }

    public final String component6() {
        return this.end;
    }

    public final ActiveProfile copy(String nama_profil, String str, String str2, String str3, String start, String end) {
        Intrinsics.checkNotNullParameter(nama_profil, "nama_profil");
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        return new ActiveProfile(nama_profil, str, str2, str3, start, end);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActiveProfile) {
            ActiveProfile activeProfile = (ActiveProfile) obj;
            return Intrinsics.areEqual(this.nama_profil, activeProfile.nama_profil) && Intrinsics.areEqual(this.id_wilayah, activeProfile.id_wilayah) && Intrinsics.areEqual(this.kode_wilayah, activeProfile.kode_wilayah) && Intrinsics.areEqual(this.role, activeProfile.role) && Intrinsics.areEqual(this.start, activeProfile.start) && Intrinsics.areEqual(this.end, activeProfile.end);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.nama_profil.hashCode() * 31;
        String str = this.id_wilayah;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.kode_wilayah;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.role;
        return ((((hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.start.hashCode()) * 31) + this.end.hashCode();
    }

    public String toString() {
        String str = this.nama_profil;
        String str2 = this.id_wilayah;
        String str3 = this.kode_wilayah;
        String str4 = this.role;
        String str5 = this.start;
        return "ActiveProfile(nama_profil=" + str + ", id_wilayah=" + str2 + ", kode_wilayah=" + str3 + ", role=" + str4 + ", start=" + str5 + ", end=" + this.end + ")";
    }

    public ActiveProfile(String nama_profil, String str, String str2, String str3, String start, String end) {
        Intrinsics.checkNotNullParameter(nama_profil, "nama_profil");
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        this.nama_profil = nama_profil;
        this.id_wilayah = str;
        this.kode_wilayah = str2;
        this.role = str3;
        this.start = start;
        this.end = end;
    }

    public final String getNama_profil() {
        return this.nama_profil;
    }

    public final String getId_wilayah() {
        return this.id_wilayah;
    }

    public final String getKode_wilayah() {
        return this.kode_wilayah;
    }

    public final String getRole() {
        return this.role;
    }

    public final String getStart() {
        return this.start;
    }

    public final String getEnd() {
        return this.end;
    }

    public final Calendar getStartDate() {
        return Companion.parseDate(this.start + "T00:00:00");
    }

    public final Calendar getEndDate() {
        return Companion.parseDate(this.end + "T23:59:59");
    }

    public final String getStartDateFormatted() {
        if (getStartDate() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale(JobType.ID));
            Calendar startDate = getStartDate();
            Intrinsics.checkNotNull(startDate);
            return simpleDateFormat.format(startDate.getTime());
        }
        return null;
    }

    public final String getEndDateFormatted() {
        if (getEndDate() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale(JobType.ID));
            Calendar endDate = getEndDate();
            Intrinsics.checkNotNull(endDate);
            return simpleDateFormat.format(endDate.getTime());
        }
        return null;
    }

    public final boolean isValid() {
        if (getStartDate() == null || getEndDate() == null) {
            return true;
        }
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        Calendar startDate = getStartDate();
        Intrinsics.checkNotNull(startDate);
        if (timeInMillis >= startDate.getTimeInMillis()) {
            long timeInMillis2 = calendar.getTimeInMillis();
            Calendar endDate = getEndDate();
            Intrinsics.checkNotNull(endDate);
            if (timeInMillis2 <= endDate.getTimeInMillis()) {
                return true;
            }
        }
        return false;
    }

    public final String getKodeWilayah() {
        String str = this.kode_wilayah;
        Intrinsics.checkNotNull(str);
        return str;
    }

    public final boolean isKodeWilayahValid() {
        String str = this.kode_wilayah;
        return str != null && (str.length() == 6 || this.kode_wilayah.length() == 13);
    }

    public final boolean isRoleValid() {
        return Intrinsics.areEqual(this.role, "kpps") || Intrinsics.areEqual(this.role, User.USER_ROLE_KECAMATAN) || Intrinsics.areEqual(this.role, User.USER_ROLE_KPPS_LN) || Intrinsics.areEqual(this.role, User.USER_ROLE_PPLN);
    }

    public final String getRoleUser() {
        return getKodeWilayah().length() == 6 ? User.USER_ROLE_KECAMATAN : "kpps";
    }

    /* compiled from: ActiveProfile.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/informatika/sirekap/model/ActiveProfile$Companion;", "", "()V", "parseDate", "Ljava/util/Calendar;", "waktuString", "", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Calendar parseDate(String waktuString) {
            Intrinsics.checkNotNullParameter(waktuString, "waktuString");
            try {
                Date parse = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse(waktuString);
                Calendar calendar = Calendar.getInstance();
                if (parse != null) {
                    calendar.setTime(parse);
                    return calendar;
                }
                return calendar;
            } catch (Exception e) {
                FirebaseCrashlytics.getInstance().recordException(e);
                Log.wtf("ERR", e.getMessage());
                return null;
            }
        }
    }
}
