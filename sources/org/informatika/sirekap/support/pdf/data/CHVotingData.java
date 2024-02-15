package org.informatika.sirekap.support.pdf.data;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.informatika.sirekap.support.security.keystore.KeystoreManager;

/* compiled from: CHVotingData.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\f\u0012\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\f¢\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\u0019\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\fHÆ\u0003J\u0019\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\fHÆ\u0003Jo\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\f2\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0003HÖ\u0001J\t\u0010&\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R!\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R!\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000bj\b\u0012\u0004\u0012\u00020\u0007`\f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006'"}, d2 = {"Lorg/informatika/sirekap/support/pdf/data/CHVotingData;", "", "dateOfPenetapan", "", "monthOfPenetapan", "yearOfPenetapan", "tempatPenetapan", "", "keystoreManager", "Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;", "listImage", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "listSaksi", "(IIILjava/lang/String;Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getDateOfPenetapan", "()I", "getKeystoreManager", "()Lorg/informatika/sirekap/support/security/keystore/KeystoreManager;", "getListImage", "()Ljava/util/ArrayList;", "getListSaksi", "getMonthOfPenetapan", "getTempatPenetapan", "()Ljava/lang/String;", "getYearOfPenetapan", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CHVotingData {
    private final int dateOfPenetapan;
    private final KeystoreManager keystoreManager;
    private final ArrayList<String> listImage;
    private final ArrayList<String> listSaksi;
    private final int monthOfPenetapan;
    private final String tempatPenetapan;
    private final int yearOfPenetapan;

    public static /* synthetic */ CHVotingData copy$default(CHVotingData cHVotingData, int i, int i2, int i3, String str, KeystoreManager keystoreManager, ArrayList arrayList, ArrayList arrayList2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = cHVotingData.dateOfPenetapan;
        }
        if ((i4 & 2) != 0) {
            i2 = cHVotingData.monthOfPenetapan;
        }
        int i5 = i2;
        if ((i4 & 4) != 0) {
            i3 = cHVotingData.yearOfPenetapan;
        }
        int i6 = i3;
        if ((i4 & 8) != 0) {
            str = cHVotingData.tempatPenetapan;
        }
        String str2 = str;
        if ((i4 & 16) != 0) {
            keystoreManager = cHVotingData.keystoreManager;
        }
        KeystoreManager keystoreManager2 = keystoreManager;
        ArrayList<String> arrayList3 = arrayList;
        if ((i4 & 32) != 0) {
            arrayList3 = cHVotingData.listImage;
        }
        ArrayList arrayList4 = arrayList3;
        ArrayList<String> arrayList5 = arrayList2;
        if ((i4 & 64) != 0) {
            arrayList5 = cHVotingData.listSaksi;
        }
        return cHVotingData.copy(i, i5, i6, str2, keystoreManager2, arrayList4, arrayList5);
    }

    public final int component1() {
        return this.dateOfPenetapan;
    }

    public final int component2() {
        return this.monthOfPenetapan;
    }

    public final int component3() {
        return this.yearOfPenetapan;
    }

    public final String component4() {
        return this.tempatPenetapan;
    }

    public final KeystoreManager component5() {
        return this.keystoreManager;
    }

    public final ArrayList<String> component6() {
        return this.listImage;
    }

    public final ArrayList<String> component7() {
        return this.listSaksi;
    }

    public final CHVotingData copy(int i, int i2, int i3, String tempatPenetapan, KeystoreManager keystoreManager, ArrayList<String> listImage, ArrayList<String> listSaksi) {
        Intrinsics.checkNotNullParameter(tempatPenetapan, "tempatPenetapan");
        Intrinsics.checkNotNullParameter(keystoreManager, "keystoreManager");
        Intrinsics.checkNotNullParameter(listImage, "listImage");
        Intrinsics.checkNotNullParameter(listSaksi, "listSaksi");
        return new CHVotingData(i, i2, i3, tempatPenetapan, keystoreManager, listImage, listSaksi);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CHVotingData) {
            CHVotingData cHVotingData = (CHVotingData) obj;
            return this.dateOfPenetapan == cHVotingData.dateOfPenetapan && this.monthOfPenetapan == cHVotingData.monthOfPenetapan && this.yearOfPenetapan == cHVotingData.yearOfPenetapan && Intrinsics.areEqual(this.tempatPenetapan, cHVotingData.tempatPenetapan) && Intrinsics.areEqual(this.keystoreManager, cHVotingData.keystoreManager) && Intrinsics.areEqual(this.listImage, cHVotingData.listImage) && Intrinsics.areEqual(this.listSaksi, cHVotingData.listSaksi);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((Integer.hashCode(this.dateOfPenetapan) * 31) + Integer.hashCode(this.monthOfPenetapan)) * 31) + Integer.hashCode(this.yearOfPenetapan)) * 31) + this.tempatPenetapan.hashCode()) * 31) + this.keystoreManager.hashCode()) * 31) + this.listImage.hashCode()) * 31) + this.listSaksi.hashCode();
    }

    public String toString() {
        int i = this.dateOfPenetapan;
        int i2 = this.monthOfPenetapan;
        int i3 = this.yearOfPenetapan;
        String str = this.tempatPenetapan;
        KeystoreManager keystoreManager = this.keystoreManager;
        ArrayList<String> arrayList = this.listImage;
        return "CHVotingData(dateOfPenetapan=" + i + ", monthOfPenetapan=" + i2 + ", yearOfPenetapan=" + i3 + ", tempatPenetapan=" + str + ", keystoreManager=" + keystoreManager + ", listImage=" + arrayList + ", listSaksi=" + this.listSaksi + ")";
    }

    public CHVotingData(int i, int i2, int i3, String tempatPenetapan, KeystoreManager keystoreManager, ArrayList<String> listImage, ArrayList<String> listSaksi) {
        Intrinsics.checkNotNullParameter(tempatPenetapan, "tempatPenetapan");
        Intrinsics.checkNotNullParameter(keystoreManager, "keystoreManager");
        Intrinsics.checkNotNullParameter(listImage, "listImage");
        Intrinsics.checkNotNullParameter(listSaksi, "listSaksi");
        this.dateOfPenetapan = i;
        this.monthOfPenetapan = i2;
        this.yearOfPenetapan = i3;
        this.tempatPenetapan = tempatPenetapan;
        this.keystoreManager = keystoreManager;
        this.listImage = listImage;
        this.listSaksi = listSaksi;
    }

    public final int getDateOfPenetapan() {
        return this.dateOfPenetapan;
    }

    public final int getMonthOfPenetapan() {
        return this.monthOfPenetapan;
    }

    public final int getYearOfPenetapan() {
        return this.yearOfPenetapan;
    }

    public final String getTempatPenetapan() {
        return this.tempatPenetapan;
    }

    public final KeystoreManager getKeystoreManager() {
        return this.keystoreManager;
    }

    public /* synthetic */ CHVotingData(int i, int i2, int i3, String str, KeystoreManager keystoreManager, ArrayList arrayList, ArrayList arrayList2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, str, keystoreManager, (i4 & 32) != 0 ? new ArrayList() : arrayList, (i4 & 64) != 0 ? new ArrayList() : arrayList2);
    }

    public final ArrayList<String> getListImage() {
        return this.listImage;
    }

    public final ArrayList<String> getListSaksi() {
        return this.listSaksi;
    }
}
