package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes2.dex */
public class SPHINCSPlusParameters {
    public static final SPHINCSPlusParameters haraka_128f;
    public static final SPHINCSPlusParameters haraka_128f_simple;
    public static final SPHINCSPlusParameters haraka_128s;
    public static final SPHINCSPlusParameters haraka_128s_simple;
    public static final SPHINCSPlusParameters haraka_192f;
    public static final SPHINCSPlusParameters haraka_192f_simple;
    public static final SPHINCSPlusParameters haraka_192s;
    public static final SPHINCSPlusParameters haraka_192s_simple;
    public static final SPHINCSPlusParameters haraka_256f;
    public static final SPHINCSPlusParameters haraka_256f_simple;
    public static final SPHINCSPlusParameters haraka_256s;
    public static final SPHINCSPlusParameters haraka_256s_simple;
    private static final Map<Integer, SPHINCSPlusParameters> oidToParams;
    private static final Map<SPHINCSPlusParameters, Integer> paramsToOid;
    public static final SPHINCSPlusParameters sha2_128f;
    public static final SPHINCSPlusParameters sha2_128f_simple;
    public static final SPHINCSPlusParameters sha2_128s;
    public static final SPHINCSPlusParameters sha2_128s_simple;
    public static final SPHINCSPlusParameters sha2_192f;
    public static final SPHINCSPlusParameters sha2_192f_simple;
    public static final SPHINCSPlusParameters sha2_192s;
    public static final SPHINCSPlusParameters sha2_192s_simple;
    public static final SPHINCSPlusParameters sha2_256f;
    public static final SPHINCSPlusParameters sha2_256f_simple;
    public static final SPHINCSPlusParameters sha2_256s;
    public static final SPHINCSPlusParameters sha2_256s_simple;
    public static final SPHINCSPlusParameters shake_128f;
    public static final SPHINCSPlusParameters shake_128f_simple;
    public static final SPHINCSPlusParameters shake_128s;
    public static final SPHINCSPlusParameters shake_128s_simple;
    public static final SPHINCSPlusParameters shake_192f;
    public static final SPHINCSPlusParameters shake_192f_simple;
    public static final SPHINCSPlusParameters shake_192s;
    public static final SPHINCSPlusParameters shake_192s_simple;
    public static final SPHINCSPlusParameters shake_256f;
    public static final SPHINCSPlusParameters shake_256f_simple;
    public static final SPHINCSPlusParameters shake_256s;
    public static final SPHINCSPlusParameters shake_256s_simple;
    private static final Integer sphincsPlus_haraka_128f_robust;
    private static final Integer sphincsPlus_haraka_128f_simple;
    private static final Integer sphincsPlus_haraka_128s_robust;
    private static final Integer sphincsPlus_haraka_128s_simple;
    private static final Integer sphincsPlus_haraka_192f_robust;
    private static final Integer sphincsPlus_haraka_192f_simple;
    private static final Integer sphincsPlus_haraka_192s_robust;
    private static final Integer sphincsPlus_haraka_192s_simple;
    private static final Integer sphincsPlus_haraka_256f_robust;
    private static final Integer sphincsPlus_haraka_256f_simple;
    private static final Integer sphincsPlus_haraka_256s_robust;
    private static final Integer sphincsPlus_haraka_256s_simple;
    private static final Integer sphincsPlus_sha2_128f_robust;
    private static final Integer sphincsPlus_sha2_128f_simple;
    private static final Integer sphincsPlus_sha2_128s_robust;
    private static final Integer sphincsPlus_sha2_128s_simple;
    private static final Integer sphincsPlus_sha2_192f_robust;
    private static final Integer sphincsPlus_sha2_192f_simple;
    private static final Integer sphincsPlus_sha2_192s_robust;
    private static final Integer sphincsPlus_sha2_192s_simple;
    private static final Integer sphincsPlus_sha2_256f_robust;
    private static final Integer sphincsPlus_sha2_256f_simple;
    private static final Integer sphincsPlus_sha2_256s_robust;
    private static final Integer sphincsPlus_sha2_256s_simple;
    private static final Integer sphincsPlus_shake_128f_robust;
    private static final Integer sphincsPlus_shake_128f_simple;
    private static final Integer sphincsPlus_shake_128s_robust;
    private static final Integer sphincsPlus_shake_128s_simple;
    private static final Integer sphincsPlus_shake_192f_robust;
    private static final Integer sphincsPlus_shake_192f_simple;
    private static final Integer sphincsPlus_shake_192s_robust;
    private static final Integer sphincsPlus_shake_192s_simple;
    private static final Integer sphincsPlus_shake_256f_robust;
    private static final Integer sphincsPlus_shake_256f_simple;
    private static final Integer sphincsPlus_shake_256s_robust;
    private static final Integer sphincsPlus_shake_256s_simple;
    private final SPHINCSPlusEngineProvider engineProvider;
    private final String name;

    /* loaded from: classes2.dex */
    private static class HarakaSEngineProvider implements SPHINCSPlusEngineProvider {
        private final int a;
        private final int d;
        private final int h;
        private final int k;
        private final int n;
        private final boolean robust;
        private final int w;

        public HarakaSEngineProvider(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.robust = z;
            this.n = i;
            this.w = i2;
            this.d = i3;
            this.a = i4;
            this.k = i5;
            this.h = i6;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public SPHINCSPlusEngine get() {
            return new SPHINCSPlusEngine.HarakaSEngine(this.robust, this.n, this.w, this.d, this.a, this.k, this.h);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public int getN() {
            return this.n;
        }
    }

    /* loaded from: classes2.dex */
    private static class Sha2EngineProvider implements SPHINCSPlusEngineProvider {
        private final int a;
        private final int d;
        private final int h;
        private final int k;
        private final int n;
        private final boolean robust;
        private final int w;

        public Sha2EngineProvider(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.robust = z;
            this.n = i;
            this.w = i2;
            this.d = i3;
            this.a = i4;
            this.k = i5;
            this.h = i6;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public SPHINCSPlusEngine get() {
            return new SPHINCSPlusEngine.Sha2Engine(this.robust, this.n, this.w, this.d, this.a, this.k, this.h);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public int getN() {
            return this.n;
        }
    }

    /* loaded from: classes2.dex */
    private static class Shake256EngineProvider implements SPHINCSPlusEngineProvider {
        private final int a;
        private final int d;
        private final int h;
        private final int k;
        private final int n;
        private final boolean robust;
        private final int w;

        public Shake256EngineProvider(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            this.robust = z;
            this.n = i;
            this.w = i2;
            this.d = i3;
            this.a = i4;
            this.k = i5;
            this.h = i6;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public SPHINCSPlusEngine get() {
            return new SPHINCSPlusEngine.Shake256Engine(this.robust, this.n, this.w, this.d, this.a, this.k, this.h);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngineProvider
        public int getN() {
            return this.n;
        }
    }

    static {
        SPHINCSPlusParameters sPHINCSPlusParameters = new SPHINCSPlusParameters("sha2-128f-robust", new Sha2EngineProvider(true, 16, 16, 22, 6, 33, 66));
        sha2_128f = sPHINCSPlusParameters;
        SPHINCSPlusParameters sPHINCSPlusParameters2 = new SPHINCSPlusParameters("sha2-128s-robust", new Sha2EngineProvider(true, 16, 16, 7, 12, 14, 63));
        sha2_128s = sPHINCSPlusParameters2;
        SPHINCSPlusParameters sPHINCSPlusParameters3 = new SPHINCSPlusParameters("sha2-192f-robust", new Sha2EngineProvider(true, 24, 16, 22, 8, 33, 66));
        sha2_192f = sPHINCSPlusParameters3;
        SPHINCSPlusParameters sPHINCSPlusParameters4 = new SPHINCSPlusParameters("sha2-192s-robust", new Sha2EngineProvider(true, 24, 16, 7, 14, 17, 63));
        sha2_192s = sPHINCSPlusParameters4;
        SPHINCSPlusParameters sPHINCSPlusParameters5 = new SPHINCSPlusParameters("sha2-256f-robust", new Sha2EngineProvider(true, 32, 16, 17, 9, 35, 68));
        sha2_256f = sPHINCSPlusParameters5;
        SPHINCSPlusParameters sPHINCSPlusParameters6 = new SPHINCSPlusParameters("sha2-256s-robust", new Sha2EngineProvider(true, 32, 16, 8, 14, 22, 64));
        sha2_256s = sPHINCSPlusParameters6;
        SPHINCSPlusParameters sPHINCSPlusParameters7 = new SPHINCSPlusParameters("sha2-128f-simple", new Sha2EngineProvider(false, 16, 16, 22, 6, 33, 66));
        sha2_128f_simple = sPHINCSPlusParameters7;
        SPHINCSPlusParameters sPHINCSPlusParameters8 = new SPHINCSPlusParameters("sha2-128s-simple", new Sha2EngineProvider(false, 16, 16, 7, 12, 14, 63));
        sha2_128s_simple = sPHINCSPlusParameters8;
        SPHINCSPlusParameters sPHINCSPlusParameters9 = new SPHINCSPlusParameters("sha2-192f-simple", new Sha2EngineProvider(false, 24, 16, 22, 8, 33, 66));
        sha2_192f_simple = sPHINCSPlusParameters9;
        SPHINCSPlusParameters sPHINCSPlusParameters10 = new SPHINCSPlusParameters("sha2-192s-simple", new Sha2EngineProvider(false, 24, 16, 7, 14, 17, 63));
        sha2_192s_simple = sPHINCSPlusParameters10;
        SPHINCSPlusParameters sPHINCSPlusParameters11 = new SPHINCSPlusParameters("sha2-256f-simple", new Sha2EngineProvider(false, 32, 16, 17, 9, 35, 68));
        sha2_256f_simple = sPHINCSPlusParameters11;
        SPHINCSPlusParameters sPHINCSPlusParameters12 = new SPHINCSPlusParameters("sha2-256s-simple", new Sha2EngineProvider(false, 32, 16, 8, 14, 22, 64));
        sha2_256s_simple = sPHINCSPlusParameters12;
        SPHINCSPlusParameters sPHINCSPlusParameters13 = new SPHINCSPlusParameters("shake-128f-robust", new Shake256EngineProvider(true, 16, 16, 22, 6, 33, 66));
        shake_128f = sPHINCSPlusParameters13;
        SPHINCSPlusParameters sPHINCSPlusParameters14 = new SPHINCSPlusParameters("shake-128s-robust", new Shake256EngineProvider(true, 16, 16, 7, 12, 14, 63));
        shake_128s = sPHINCSPlusParameters14;
        SPHINCSPlusParameters sPHINCSPlusParameters15 = new SPHINCSPlusParameters("shake-192f-robust", new Shake256EngineProvider(true, 24, 16, 22, 8, 33, 66));
        shake_192f = sPHINCSPlusParameters15;
        SPHINCSPlusParameters sPHINCSPlusParameters16 = new SPHINCSPlusParameters("shake-192s-robust", new Shake256EngineProvider(true, 24, 16, 7, 14, 17, 63));
        shake_192s = sPHINCSPlusParameters16;
        SPHINCSPlusParameters sPHINCSPlusParameters17 = new SPHINCSPlusParameters("shake-256f-robust", new Shake256EngineProvider(true, 32, 16, 17, 9, 35, 68));
        shake_256f = sPHINCSPlusParameters17;
        SPHINCSPlusParameters sPHINCSPlusParameters18 = new SPHINCSPlusParameters("shake-256s-robust", new Shake256EngineProvider(true, 32, 16, 8, 14, 22, 64));
        shake_256s = sPHINCSPlusParameters18;
        SPHINCSPlusParameters sPHINCSPlusParameters19 = new SPHINCSPlusParameters("shake-128f-simple", new Shake256EngineProvider(false, 16, 16, 22, 6, 33, 66));
        shake_128f_simple = sPHINCSPlusParameters19;
        SPHINCSPlusParameters sPHINCSPlusParameters20 = new SPHINCSPlusParameters("shake-128s-simple", new Shake256EngineProvider(false, 16, 16, 7, 12, 14, 63));
        shake_128s_simple = sPHINCSPlusParameters20;
        SPHINCSPlusParameters sPHINCSPlusParameters21 = new SPHINCSPlusParameters("shake-192f-simple", new Shake256EngineProvider(false, 24, 16, 22, 8, 33, 66));
        shake_192f_simple = sPHINCSPlusParameters21;
        SPHINCSPlusParameters sPHINCSPlusParameters22 = new SPHINCSPlusParameters("shake-192s-simple", new Shake256EngineProvider(false, 24, 16, 7, 14, 17, 63));
        shake_192s_simple = sPHINCSPlusParameters22;
        SPHINCSPlusParameters sPHINCSPlusParameters23 = new SPHINCSPlusParameters("shake-256f-simple", new Shake256EngineProvider(false, 32, 16, 17, 9, 35, 68));
        shake_256f_simple = sPHINCSPlusParameters23;
        SPHINCSPlusParameters sPHINCSPlusParameters24 = new SPHINCSPlusParameters("shake-256s-simple", new Shake256EngineProvider(false, 32, 16, 8, 14, 22, 64));
        shake_256s_simple = sPHINCSPlusParameters24;
        SPHINCSPlusParameters sPHINCSPlusParameters25 = new SPHINCSPlusParameters("haraka-128f-robust", new HarakaSEngineProvider(true, 16, 16, 22, 6, 33, 66));
        haraka_128f = sPHINCSPlusParameters25;
        SPHINCSPlusParameters sPHINCSPlusParameters26 = new SPHINCSPlusParameters("haraka-128s-robust", new HarakaSEngineProvider(true, 16, 16, 7, 12, 14, 63));
        haraka_128s = sPHINCSPlusParameters26;
        SPHINCSPlusParameters sPHINCSPlusParameters27 = new SPHINCSPlusParameters("haraka-256f-robust", new HarakaSEngineProvider(true, 32, 16, 17, 9, 35, 68));
        haraka_256f = sPHINCSPlusParameters27;
        SPHINCSPlusParameters sPHINCSPlusParameters28 = new SPHINCSPlusParameters("haraka-256s-robust", new HarakaSEngineProvider(true, 32, 16, 8, 14, 22, 64));
        haraka_256s = sPHINCSPlusParameters28;
        SPHINCSPlusParameters sPHINCSPlusParameters29 = new SPHINCSPlusParameters("haraka-192f-robust", new HarakaSEngineProvider(true, 24, 16, 22, 8, 33, 66));
        haraka_192f = sPHINCSPlusParameters29;
        SPHINCSPlusParameters sPHINCSPlusParameters30 = new SPHINCSPlusParameters("haraka-192s-robust", new HarakaSEngineProvider(true, 24, 16, 7, 14, 17, 63));
        haraka_192s = sPHINCSPlusParameters30;
        SPHINCSPlusParameters sPHINCSPlusParameters31 = new SPHINCSPlusParameters("haraka-128f-simple", new HarakaSEngineProvider(false, 16, 16, 22, 6, 33, 66));
        haraka_128f_simple = sPHINCSPlusParameters31;
        SPHINCSPlusParameters sPHINCSPlusParameters32 = new SPHINCSPlusParameters("haraka-128s-simple", new HarakaSEngineProvider(false, 16, 16, 7, 12, 14, 63));
        haraka_128s_simple = sPHINCSPlusParameters32;
        SPHINCSPlusParameters sPHINCSPlusParameters33 = new SPHINCSPlusParameters("haraka-192f-simple", new HarakaSEngineProvider(false, 24, 16, 22, 8, 33, 66));
        haraka_192f_simple = sPHINCSPlusParameters33;
        SPHINCSPlusParameters sPHINCSPlusParameters34 = new SPHINCSPlusParameters("haraka-192s-simple", new HarakaSEngineProvider(false, 24, 16, 7, 14, 17, 63));
        haraka_192s_simple = sPHINCSPlusParameters34;
        SPHINCSPlusParameters sPHINCSPlusParameters35 = new SPHINCSPlusParameters("haraka-256f-simple", new HarakaSEngineProvider(false, 32, 16, 17, 9, 35, 68));
        haraka_256f_simple = sPHINCSPlusParameters35;
        SPHINCSPlusParameters sPHINCSPlusParameters36 = new SPHINCSPlusParameters("haraka-256s-simple", new HarakaSEngineProvider(false, 32, 16, 8, 14, 22, 64));
        haraka_256s_simple = sPHINCSPlusParameters36;
        Integer valueOf = Integers.valueOf(65793);
        sphincsPlus_sha2_128f_robust = valueOf;
        Integer valueOf2 = Integers.valueOf(65794);
        sphincsPlus_sha2_128s_robust = valueOf2;
        Integer valueOf3 = Integers.valueOf(65795);
        sphincsPlus_sha2_192f_robust = valueOf3;
        Integer valueOf4 = Integers.valueOf(65796);
        sphincsPlus_sha2_192s_robust = valueOf4;
        Integer valueOf5 = Integers.valueOf(65797);
        sphincsPlus_sha2_256f_robust = valueOf5;
        Integer valueOf6 = Integers.valueOf(65798);
        sphincsPlus_sha2_256s_robust = valueOf6;
        Integer valueOf7 = Integers.valueOf(66049);
        sphincsPlus_sha2_128f_simple = valueOf7;
        Integer valueOf8 = Integers.valueOf(66050);
        sphincsPlus_sha2_128s_simple = valueOf8;
        Integer valueOf9 = Integers.valueOf(66051);
        sphincsPlus_sha2_192f_simple = valueOf9;
        Integer valueOf10 = Integers.valueOf(66052);
        sphincsPlus_sha2_192s_simple = valueOf10;
        Integer valueOf11 = Integers.valueOf(66053);
        sphincsPlus_sha2_256f_simple = valueOf11;
        Integer valueOf12 = Integers.valueOf(66054);
        sphincsPlus_sha2_256s_simple = valueOf12;
        Integer valueOf13 = Integers.valueOf(131329);
        sphincsPlus_shake_128f_robust = valueOf13;
        Integer valueOf14 = Integers.valueOf(131330);
        sphincsPlus_shake_128s_robust = valueOf14;
        Integer valueOf15 = Integers.valueOf(131331);
        sphincsPlus_shake_192f_robust = valueOf15;
        Integer valueOf16 = Integers.valueOf(131332);
        sphincsPlus_shake_192s_robust = valueOf16;
        Integer valueOf17 = Integers.valueOf(131333);
        sphincsPlus_shake_256f_robust = valueOf17;
        Integer valueOf18 = Integers.valueOf(131334);
        sphincsPlus_shake_256s_robust = valueOf18;
        Integer valueOf19 = Integers.valueOf(131585);
        sphincsPlus_shake_128f_simple = valueOf19;
        Integer valueOf20 = Integers.valueOf(131586);
        sphincsPlus_shake_128s_simple = valueOf20;
        Integer valueOf21 = Integers.valueOf(131587);
        sphincsPlus_shake_192f_simple = valueOf21;
        Integer valueOf22 = Integers.valueOf(131588);
        sphincsPlus_shake_192s_simple = valueOf22;
        Integer valueOf23 = Integers.valueOf(131589);
        sphincsPlus_shake_256f_simple = valueOf23;
        Integer valueOf24 = Integers.valueOf(131590);
        sphincsPlus_shake_256s_simple = valueOf24;
        Integer valueOf25 = Integers.valueOf(196865);
        sphincsPlus_haraka_128f_robust = valueOf25;
        Integer valueOf26 = Integers.valueOf(196866);
        sphincsPlus_haraka_128s_robust = valueOf26;
        Integer valueOf27 = Integers.valueOf(196867);
        sphincsPlus_haraka_192f_robust = valueOf27;
        Integer valueOf28 = Integers.valueOf(196868);
        sphincsPlus_haraka_192s_robust = valueOf28;
        Integer valueOf29 = Integers.valueOf(196869);
        sphincsPlus_haraka_256f_robust = valueOf29;
        Integer valueOf30 = Integers.valueOf(196870);
        sphincsPlus_haraka_256s_robust = valueOf30;
        Integer valueOf31 = Integers.valueOf(197121);
        sphincsPlus_haraka_128f_simple = valueOf31;
        Integer valueOf32 = Integers.valueOf(197122);
        sphincsPlus_haraka_128s_simple = valueOf32;
        Integer valueOf33 = Integers.valueOf(197123);
        sphincsPlus_haraka_192f_simple = valueOf33;
        Integer valueOf34 = Integers.valueOf(197124);
        sphincsPlus_haraka_192s_simple = valueOf34;
        Integer valueOf35 = Integers.valueOf(197125);
        sphincsPlus_haraka_256f_simple = valueOf35;
        Integer valueOf36 = Integers.valueOf(197126);
        sphincsPlus_haraka_256s_simple = valueOf36;
        HashMap hashMap = new HashMap();
        oidToParams = hashMap;
        HashMap hashMap2 = new HashMap();
        paramsToOid = hashMap2;
        hashMap.put(valueOf, sPHINCSPlusParameters);
        hashMap.put(valueOf2, sPHINCSPlusParameters2);
        hashMap.put(valueOf3, sPHINCSPlusParameters3);
        hashMap.put(valueOf4, sPHINCSPlusParameters4);
        hashMap.put(valueOf5, sPHINCSPlusParameters5);
        hashMap.put(valueOf6, sPHINCSPlusParameters6);
        hashMap.put(valueOf7, sPHINCSPlusParameters7);
        hashMap.put(valueOf8, sPHINCSPlusParameters8);
        hashMap.put(valueOf9, sPHINCSPlusParameters9);
        hashMap.put(valueOf10, sPHINCSPlusParameters10);
        hashMap.put(valueOf11, sPHINCSPlusParameters11);
        hashMap.put(valueOf12, sPHINCSPlusParameters12);
        hashMap.put(valueOf13, sPHINCSPlusParameters13);
        hashMap.put(valueOf14, sPHINCSPlusParameters14);
        hashMap.put(valueOf15, sPHINCSPlusParameters15);
        hashMap.put(valueOf16, sPHINCSPlusParameters16);
        hashMap.put(valueOf17, sPHINCSPlusParameters17);
        hashMap.put(valueOf18, sPHINCSPlusParameters18);
        hashMap.put(valueOf19, sPHINCSPlusParameters19);
        hashMap.put(valueOf20, sPHINCSPlusParameters20);
        hashMap.put(valueOf21, sPHINCSPlusParameters21);
        hashMap.put(valueOf22, sPHINCSPlusParameters22);
        hashMap.put(valueOf23, sPHINCSPlusParameters23);
        hashMap.put(valueOf24, sPHINCSPlusParameters24);
        hashMap.put(valueOf31, sPHINCSPlusParameters31);
        hashMap.put(valueOf25, sPHINCSPlusParameters25);
        hashMap.put(valueOf33, sPHINCSPlusParameters33);
        hashMap.put(valueOf27, sPHINCSPlusParameters29);
        hashMap.put(valueOf35, sPHINCSPlusParameters35);
        hashMap.put(valueOf29, sPHINCSPlusParameters27);
        hashMap.put(valueOf32, sPHINCSPlusParameters32);
        hashMap.put(valueOf26, sPHINCSPlusParameters26);
        hashMap.put(valueOf34, sPHINCSPlusParameters34);
        hashMap.put(valueOf28, sPHINCSPlusParameters30);
        hashMap.put(valueOf36, sPHINCSPlusParameters36);
        hashMap.put(valueOf30, sPHINCSPlusParameters28);
        hashMap2.put(sPHINCSPlusParameters, valueOf);
        hashMap2.put(sPHINCSPlusParameters2, valueOf2);
        hashMap2.put(sPHINCSPlusParameters3, valueOf3);
        hashMap2.put(sPHINCSPlusParameters4, valueOf4);
        hashMap2.put(sPHINCSPlusParameters5, valueOf5);
        hashMap2.put(sPHINCSPlusParameters6, valueOf6);
        hashMap2.put(sPHINCSPlusParameters7, valueOf7);
        hashMap2.put(sPHINCSPlusParameters8, valueOf8);
        hashMap2.put(sPHINCSPlusParameters9, valueOf9);
        hashMap2.put(sPHINCSPlusParameters10, valueOf10);
        hashMap2.put(sPHINCSPlusParameters11, valueOf11);
        hashMap2.put(sPHINCSPlusParameters12, valueOf12);
        hashMap2.put(sPHINCSPlusParameters13, valueOf13);
        hashMap2.put(sPHINCSPlusParameters14, valueOf14);
        hashMap2.put(sPHINCSPlusParameters15, valueOf15);
        hashMap2.put(sPHINCSPlusParameters16, valueOf16);
        hashMap2.put(sPHINCSPlusParameters17, valueOf17);
        hashMap2.put(sPHINCSPlusParameters18, valueOf18);
        hashMap2.put(sPHINCSPlusParameters19, valueOf19);
        hashMap2.put(sPHINCSPlusParameters20, valueOf20);
        hashMap2.put(sPHINCSPlusParameters21, valueOf21);
        hashMap2.put(sPHINCSPlusParameters22, valueOf22);
        hashMap2.put(sPHINCSPlusParameters23, valueOf23);
        hashMap2.put(sPHINCSPlusParameters24, valueOf24);
        hashMap2.put(sPHINCSPlusParameters25, valueOf25);
        hashMap2.put(sPHINCSPlusParameters29, valueOf27);
        hashMap2.put(sPHINCSPlusParameters27, valueOf29);
        hashMap2.put(sPHINCSPlusParameters26, valueOf26);
        hashMap2.put(sPHINCSPlusParameters30, valueOf28);
        hashMap2.put(sPHINCSPlusParameters28, valueOf30);
        hashMap2.put(sPHINCSPlusParameters31, valueOf31);
        hashMap2.put(sPHINCSPlusParameters33, valueOf33);
        hashMap2.put(sPHINCSPlusParameters35, valueOf35);
        hashMap2.put(sPHINCSPlusParameters32, valueOf32);
        hashMap2.put(sPHINCSPlusParameters34, valueOf34);
        hashMap2.put(sPHINCSPlusParameters36, valueOf36);
    }

    private SPHINCSPlusParameters(String str, SPHINCSPlusEngineProvider sPHINCSPlusEngineProvider) {
        this.name = str;
        this.engineProvider = sPHINCSPlusEngineProvider;
    }

    public static Integer getID(SPHINCSPlusParameters sPHINCSPlusParameters) {
        return paramsToOid.get(sPHINCSPlusParameters);
    }

    public static SPHINCSPlusParameters getParams(Integer num) {
        return oidToParams.get(num);
    }

    public byte[] getEncoded() {
        return Pack.intToBigEndian(getID(this).intValue());
    }

    public SPHINCSPlusEngine getEngine() {
        return this.engineProvider.get();
    }

    public int getN() {
        return this.engineProvider.getN();
    }

    public String getName() {
        return this.name;
    }
}
