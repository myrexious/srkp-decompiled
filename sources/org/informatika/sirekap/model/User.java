package org.informatika.sirekap.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xmpbox.type.JobType;

/* compiled from: User.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 12\u00020\u0001:\u00011B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\u0006\u0010#\u001a\u00020\u0018J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003JO\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010,\u001a\u00020\u00182\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020/HÖ\u0001J\t\u00100\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\rR\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\rR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0014R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\rR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00062"}, d2 = {"Lorg/informatika/sirekap/model/User;", "", JobType.ID, "", "fullName", "tps", "Lorg/informatika/sirekap/model/Tps;", "role", "namaProfil", "profilStart", "profilEnd", "(Ljava/lang/String;Ljava/lang/String;Lorg/informatika/sirekap/model/Tps;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFullName", "()Ljava/lang/String;", "getId", "getNamaProfil", "getProfilEnd", "profilEndDate", "Ljava/util/Calendar;", "getProfilEndDate", "()Ljava/util/Calendar;", "profilEndDateFormatted", "getProfilEndDateFormatted", "profilIsValid", "", "getProfilIsValid", "()Z", "getProfilStart", "profilStartDate", "getProfilStartDate", "profilStartDateFormatted", "getProfilStartDateFormatted", "getRole", "getTps", "()Lorg/informatika/sirekap/model/Tps;", "canChangeTps", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class User {
    public static final Companion Companion = new Companion(null);
    public static final String USER_ROLE_KECAMATAN = "ppk";
    public static final String USER_ROLE_KPPS = "kpps";
    public static final String USER_ROLE_KPPS_LN = "kpps_ln";
    public static final String USER_ROLE_PPLN = "ppln";
    private final String fullName;

    /* renamed from: id */
    private final String f71id;
    private final String namaProfil;
    private final String profilEnd;
    private final String profilStart;
    private final String role;
    private final Tps tps;

    public static /* synthetic */ User copy$default(User user, String str, String str2, Tps tps, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = user.f71id;
        }
        if ((i & 2) != 0) {
            str2 = user.fullName;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            tps = user.tps;
        }
        Tps tps2 = tps;
        if ((i & 8) != 0) {
            str3 = user.role;
        }
        String str8 = str3;
        if ((i & 16) != 0) {
            str4 = user.namaProfil;
        }
        String str9 = str4;
        if ((i & 32) != 0) {
            str5 = user.profilStart;
        }
        String str10 = str5;
        if ((i & 64) != 0) {
            str6 = user.profilEnd;
        }
        return user.copy(str, str7, tps2, str8, str9, str10, str6);
    }

    public final String component1() {
        return this.f71id;
    }

    public final String component2() {
        return this.fullName;
    }

    public final Tps component3() {
        return this.tps;
    }

    public final String component4() {
        return this.role;
    }

    public final String component5() {
        return this.namaProfil;
    }

    public final String component6() {
        return this.profilStart;
    }

    public final String component7() {
        return this.profilEnd;
    }

    public final User copy(String id2, String fullName, Tps tps, String role, String namaProfil, String profilStart, String profilEnd) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(tps, "tps");
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(namaProfil, "namaProfil");
        Intrinsics.checkNotNullParameter(profilStart, "profilStart");
        Intrinsics.checkNotNullParameter(profilEnd, "profilEnd");
        return new User(id2, fullName, tps, role, namaProfil, profilStart, profilEnd);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof User) {
            User user = (User) obj;
            return Intrinsics.areEqual(this.f71id, user.f71id) && Intrinsics.areEqual(this.fullName, user.fullName) && Intrinsics.areEqual(this.tps, user.tps) && Intrinsics.areEqual(this.role, user.role) && Intrinsics.areEqual(this.namaProfil, user.namaProfil) && Intrinsics.areEqual(this.profilStart, user.profilStart) && Intrinsics.areEqual(this.profilEnd, user.profilEnd);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((this.f71id.hashCode() * 31) + this.fullName.hashCode()) * 31) + this.tps.hashCode()) * 31) + this.role.hashCode()) * 31) + this.namaProfil.hashCode()) * 31) + this.profilStart.hashCode()) * 31) + this.profilEnd.hashCode();
    }

    public String toString() {
        String str = this.f71id;
        String str2 = this.fullName;
        Tps tps = this.tps;
        String str3 = this.role;
        String str4 = this.namaProfil;
        String str5 = this.profilStart;
        return "User(id=" + str + ", fullName=" + str2 + ", tps=" + tps + ", role=" + str3 + ", namaProfil=" + str4 + ", profilStart=" + str5 + ", profilEnd=" + this.profilEnd + ")";
    }

    public User(String id2, String fullName, Tps tps, String role, String namaProfil, String profilStart, String profilEnd) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(tps, "tps");
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(namaProfil, "namaProfil");
        Intrinsics.checkNotNullParameter(profilStart, "profilStart");
        Intrinsics.checkNotNullParameter(profilEnd, "profilEnd");
        this.f71id = id2;
        this.fullName = fullName;
        this.tps = tps;
        this.role = role;
        this.namaProfil = namaProfil;
        this.profilStart = profilStart;
        this.profilEnd = profilEnd;
    }

    public final String getId() {
        return this.f71id;
    }

    public final String getFullName() {
        return this.fullName;
    }

    public final Tps getTps() {
        return this.tps;
    }

    public final String getRole() {
        return this.role;
    }

    public final String getNamaProfil() {
        return this.namaProfil;
    }

    public final String getProfilStart() {
        return this.profilStart;
    }

    public final String getProfilEnd() {
        return this.profilEnd;
    }

    public final Calendar getProfilStartDate() {
        return ActiveProfile.Companion.parseDate(this.profilStart + "T00:00:00");
    }

    public final Calendar getProfilEndDate() {
        return ActiveProfile.Companion.parseDate(this.profilEnd + "T23:59:59");
    }

    public final String getProfilStartDateFormatted() {
        if (getProfilStartDate() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale(JobType.ID));
            Calendar profilStartDate = getProfilStartDate();
            Intrinsics.checkNotNull(profilStartDate);
            return simpleDateFormat.format(profilStartDate.getTime());
        }
        return null;
    }

    public final String getProfilEndDateFormatted() {
        if (getProfilEndDate() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale(JobType.ID));
            Calendar profilEndDate = getProfilEndDate();
            Intrinsics.checkNotNull(profilEndDate);
            return simpleDateFormat.format(profilEndDate.getTime());
        }
        return null;
    }

    public final boolean getProfilIsValid() {
        if (getProfilStartDate() == null || getProfilEndDate() == null) {
            return true;
        }
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        Calendar profilStartDate = getProfilStartDate();
        Intrinsics.checkNotNull(profilStartDate);
        if (timeInMillis >= profilStartDate.getTimeInMillis()) {
            long timeInMillis2 = calendar.getTimeInMillis();
            Calendar profilEndDate = getProfilEndDate();
            Intrinsics.checkNotNull(profilEndDate);
            if (timeInMillis2 <= profilEndDate.getTimeInMillis()) {
                return true;
            }
        }
        return false;
    }

    public final boolean canChangeTps() {
        return Intrinsics.areEqual(this.role, USER_ROLE_KECAMATAN) || Intrinsics.areEqual(this.role, USER_ROLE_PPLN);
    }

    /* compiled from: User.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/informatika/sirekap/model/User$Companion;", "", "()V", "USER_ROLE_KECAMATAN", "", "USER_ROLE_KPPS", "USER_ROLE_KPPS_LN", "USER_ROLE_PPLN", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
