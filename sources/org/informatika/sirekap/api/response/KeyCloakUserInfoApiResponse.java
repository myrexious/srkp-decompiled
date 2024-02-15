package org.informatika.sirekap.api.response;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KeyCloakUserInfoApiResponse.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0011B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\t\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/informatika/sirekap/api/response/KeyCloakUserInfoApiResponse;", "", "profilelist", "", "Lorg/informatika/sirekap/api/response/KeyCloakUserInfoApiResponse$KeyCloakUserInfoProfileApiResponse;", "(Ljava/util/List;)V", "getProfilelist", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "KeyCloakUserInfoProfileApiResponse", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class KeyCloakUserInfoApiResponse {
    private final List<KeyCloakUserInfoProfileApiResponse> profilelist;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KeyCloakUserInfoApiResponse copy$default(KeyCloakUserInfoApiResponse keyCloakUserInfoApiResponse, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = keyCloakUserInfoApiResponse.profilelist;
        }
        return keyCloakUserInfoApiResponse.copy(list);
    }

    public final List<KeyCloakUserInfoProfileApiResponse> component1() {
        return this.profilelist;
    }

    public final KeyCloakUserInfoApiResponse copy(List<KeyCloakUserInfoProfileApiResponse> list) {
        return new KeyCloakUserInfoApiResponse(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof KeyCloakUserInfoApiResponse) && Intrinsics.areEqual(this.profilelist, ((KeyCloakUserInfoApiResponse) obj).profilelist);
    }

    public int hashCode() {
        List<KeyCloakUserInfoProfileApiResponse> list = this.profilelist;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public String toString() {
        return "KeyCloakUserInfoApiResponse(profilelist=" + this.profilelist + ")";
    }

    public KeyCloakUserInfoApiResponse(List<KeyCloakUserInfoProfileApiResponse> list) {
        this.profilelist = list;
    }

    public final List<KeyCloakUserInfoProfileApiResponse> getProfilelist() {
        return this.profilelist;
    }

    /* compiled from: KeyCloakUserInfoApiResponse.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lorg/informatika/sirekap/api/response/KeyCloakUserInfoApiResponse$KeyCloakUserInfoProfileApiResponse;", "", "nama_profil", "", "kode_wilayah", "role", "start", "end", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEnd", "()Ljava/lang/String;", "getKode_wilayah", "getNama_profil", "getRole", "getStart", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class KeyCloakUserInfoProfileApiResponse {
        private final String end;
        private final String kode_wilayah;
        private final String nama_profil;
        private final String role;
        private final String start;

        public static /* synthetic */ KeyCloakUserInfoProfileApiResponse copy$default(KeyCloakUserInfoProfileApiResponse keyCloakUserInfoProfileApiResponse, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = keyCloakUserInfoProfileApiResponse.nama_profil;
            }
            if ((i & 2) != 0) {
                str2 = keyCloakUserInfoProfileApiResponse.kode_wilayah;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = keyCloakUserInfoProfileApiResponse.role;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = keyCloakUserInfoProfileApiResponse.start;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                str5 = keyCloakUserInfoProfileApiResponse.end;
            }
            return keyCloakUserInfoProfileApiResponse.copy(str, str6, str7, str8, str5);
        }

        public final String component1() {
            return this.nama_profil;
        }

        public final String component2() {
            return this.kode_wilayah;
        }

        public final String component3() {
            return this.role;
        }

        public final String component4() {
            return this.start;
        }

        public final String component5() {
            return this.end;
        }

        public final KeyCloakUserInfoProfileApiResponse copy(String nama_profil, String kode_wilayah, String role, String start, String end) {
            Intrinsics.checkNotNullParameter(nama_profil, "nama_profil");
            Intrinsics.checkNotNullParameter(kode_wilayah, "kode_wilayah");
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(end, "end");
            return new KeyCloakUserInfoProfileApiResponse(nama_profil, kode_wilayah, role, start, end);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof KeyCloakUserInfoProfileApiResponse) {
                KeyCloakUserInfoProfileApiResponse keyCloakUserInfoProfileApiResponse = (KeyCloakUserInfoProfileApiResponse) obj;
                return Intrinsics.areEqual(this.nama_profil, keyCloakUserInfoProfileApiResponse.nama_profil) && Intrinsics.areEqual(this.kode_wilayah, keyCloakUserInfoProfileApiResponse.kode_wilayah) && Intrinsics.areEqual(this.role, keyCloakUserInfoProfileApiResponse.role) && Intrinsics.areEqual(this.start, keyCloakUserInfoProfileApiResponse.start) && Intrinsics.areEqual(this.end, keyCloakUserInfoProfileApiResponse.end);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.nama_profil.hashCode() * 31) + this.kode_wilayah.hashCode()) * 31) + this.role.hashCode()) * 31) + this.start.hashCode()) * 31) + this.end.hashCode();
        }

        public String toString() {
            String str = this.nama_profil;
            String str2 = this.kode_wilayah;
            String str3 = this.role;
            String str4 = this.start;
            return "KeyCloakUserInfoProfileApiResponse(nama_profil=" + str + ", kode_wilayah=" + str2 + ", role=" + str3 + ", start=" + str4 + ", end=" + this.end + ")";
        }

        public KeyCloakUserInfoProfileApiResponse(String nama_profil, String kode_wilayah, String role, String start, String end) {
            Intrinsics.checkNotNullParameter(nama_profil, "nama_profil");
            Intrinsics.checkNotNullParameter(kode_wilayah, "kode_wilayah");
            Intrinsics.checkNotNullParameter(role, "role");
            Intrinsics.checkNotNullParameter(start, "start");
            Intrinsics.checkNotNullParameter(end, "end");
            this.nama_profil = nama_profil;
            this.kode_wilayah = kode_wilayah;
            this.role = role;
            this.start = start;
            this.end = end;
        }

        public final String getNama_profil() {
            return this.nama_profil;
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
    }
}
