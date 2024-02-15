package org.informatika.sirekap.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Witness.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0003J7\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u001c\u001a\u00020\u0000J\t\u0010\u001d\u001a\u00020\u0018HÖ\u0001R\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001e"}, d2 = {"Lorg/informatika/sirekap/model/WitnessWithShare;", "", "witness", "Lorg/informatika/sirekap/model/Witness;", "witnessShares", "", "Lorg/informatika/sirekap/model/WitnessShare;", "pemeriksas", "Lorg/informatika/sirekap/model/WitnessPemeriksa;", "(Lorg/informatika/sirekap/model/Witness;Ljava/util/List;Ljava/util/List;)V", "getPemeriksas", "()Ljava/util/List;", "getWitness", "()Lorg/informatika/sirekap/model/Witness;", "getWitnessShares", "component1", "component2", "component3", "copy", "equals", "", "other", "getWitnessShare", "jenisPemilihan", "", "hashCode", "", "isJenisPemilihan", "toDecrypted", "toString", "app_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WitnessWithShare {
    private final List<WitnessPemeriksa> pemeriksas;
    private final Witness witness;
    private final List<WitnessShare> witnessShares;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WitnessWithShare copy$default(WitnessWithShare witnessWithShare, Witness witness, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            witness = witnessWithShare.witness;
        }
        if ((i & 2) != 0) {
            list = witnessWithShare.witnessShares;
        }
        if ((i & 4) != 0) {
            list2 = witnessWithShare.pemeriksas;
        }
        return witnessWithShare.copy(witness, list, list2);
    }

    public final Witness component1() {
        return this.witness;
    }

    public final List<WitnessShare> component2() {
        return this.witnessShares;
    }

    public final List<WitnessPemeriksa> component3() {
        return this.pemeriksas;
    }

    public final WitnessWithShare copy(Witness witness, List<WitnessShare> list, List<WitnessPemeriksa> list2) {
        Intrinsics.checkNotNullParameter(witness, "witness");
        return new WitnessWithShare(witness, list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WitnessWithShare) {
            WitnessWithShare witnessWithShare = (WitnessWithShare) obj;
            return Intrinsics.areEqual(this.witness, witnessWithShare.witness) && Intrinsics.areEqual(this.witnessShares, witnessWithShare.witnessShares) && Intrinsics.areEqual(this.pemeriksas, witnessWithShare.pemeriksas);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.witness.hashCode() * 31;
        List<WitnessShare> list = this.witnessShares;
        int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
        List<WitnessPemeriksa> list2 = this.pemeriksas;
        return hashCode2 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        Witness witness = this.witness;
        List<WitnessShare> list = this.witnessShares;
        return "WitnessWithShare(witness=" + witness + ", witnessShares=" + list + ", pemeriksas=" + this.pemeriksas + ")";
    }

    public WitnessWithShare(Witness witness, List<WitnessShare> list, List<WitnessPemeriksa> list2) {
        Intrinsics.checkNotNullParameter(witness, "witness");
        this.witness = witness;
        this.witnessShares = list;
        this.pemeriksas = list2;
    }

    public final Witness getWitness() {
        return this.witness;
    }

    public final List<WitnessShare> getWitnessShares() {
        return this.witnessShares;
    }

    public final List<WitnessPemeriksa> getPemeriksas() {
        return this.pemeriksas;
    }

    public final boolean isJenisPemilihan(String jenisPemilihan) {
        Intrinsics.checkNotNullParameter(jenisPemilihan, "jenisPemilihan");
        List<WitnessPemeriksa> list = this.pemeriksas;
        if (list != null) {
            List<WitnessPemeriksa> list2 = list;
            if ((list2 instanceof Collection) && list2.isEmpty()) {
                return false;
            }
            for (WitnessPemeriksa witnessPemeriksa : list2) {
                if (Intrinsics.areEqual(witnessPemeriksa.getJenisPemilihan(), jenisPemilihan)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final WitnessShare getWitnessShare(String str) {
        List<WitnessShare> list = this.witnessShares;
        Object obj = null;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (Intrinsics.areEqual(((WitnessShare) next).getJenisPemilihan(), str)) {
                    obj = next;
                    break;
                }
            }
            return (WitnessShare) obj;
        }
        return null;
    }

    public final WitnessWithShare toDecrypted() {
        ArrayList arrayList;
        Witness decrypted = this.witness.toDecrypted();
        List<WitnessShare> list = this.witnessShares;
        ArrayList arrayList2 = null;
        if (list != null) {
            List<WitnessShare> list2 = list;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (WitnessShare witnessShare : list2) {
                arrayList3.add(witnessShare.toDecrypted());
            }
            arrayList = arrayList3;
        } else {
            arrayList = null;
        }
        List<WitnessPemeriksa> list3 = this.pemeriksas;
        if (list3 != null) {
            List<WitnessPemeriksa> list4 = list3;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
            for (WitnessPemeriksa witnessPemeriksa : list4) {
                arrayList4.add(witnessPemeriksa.toDecrypted());
            }
            arrayList2 = arrayList4;
        }
        return copy(decrypted, arrayList, arrayList2);
    }
}
