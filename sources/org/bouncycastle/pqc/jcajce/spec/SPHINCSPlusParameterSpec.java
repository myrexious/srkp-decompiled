package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.util.Strings;

/* loaded from: classes2.dex */
public class SPHINCSPlusParameterSpec implements AlgorithmParameterSpec {
    public static final SPHINCSPlusParameterSpec haraka_128f;
    public static final SPHINCSPlusParameterSpec haraka_128f_simple;
    public static final SPHINCSPlusParameterSpec haraka_128s;
    public static final SPHINCSPlusParameterSpec haraka_128s_simple;
    public static final SPHINCSPlusParameterSpec haraka_192f;
    public static final SPHINCSPlusParameterSpec haraka_192f_simple;
    public static final SPHINCSPlusParameterSpec haraka_192s;
    public static final SPHINCSPlusParameterSpec haraka_192s_simple;
    public static final SPHINCSPlusParameterSpec haraka_256f;
    public static final SPHINCSPlusParameterSpec haraka_256f_simple;
    public static final SPHINCSPlusParameterSpec haraka_256s;
    public static final SPHINCSPlusParameterSpec haraka_256s_simple;
    private static Map parameters;
    public static final SPHINCSPlusParameterSpec sha2_128f;
    public static final SPHINCSPlusParameterSpec sha2_128f_simple;
    public static final SPHINCSPlusParameterSpec sha2_128s;
    public static final SPHINCSPlusParameterSpec sha2_128s_simple;
    public static final SPHINCSPlusParameterSpec sha2_192f;
    public static final SPHINCSPlusParameterSpec sha2_192f_simple;
    public static final SPHINCSPlusParameterSpec sha2_192s;
    public static final SPHINCSPlusParameterSpec sha2_192s_simple;
    public static final SPHINCSPlusParameterSpec sha2_256f;
    public static final SPHINCSPlusParameterSpec sha2_256f_simple;
    public static final SPHINCSPlusParameterSpec sha2_256s;
    public static final SPHINCSPlusParameterSpec sha2_256s_simple;
    public static final SPHINCSPlusParameterSpec shake_128f;
    public static final SPHINCSPlusParameterSpec shake_128f_simple;
    public static final SPHINCSPlusParameterSpec shake_128s;
    public static final SPHINCSPlusParameterSpec shake_128s_simple;
    public static final SPHINCSPlusParameterSpec shake_192f;
    public static final SPHINCSPlusParameterSpec shake_192f_simple;
    public static final SPHINCSPlusParameterSpec shake_192s;
    public static final SPHINCSPlusParameterSpec shake_192s_simple;
    public static final SPHINCSPlusParameterSpec shake_256f;
    public static final SPHINCSPlusParameterSpec shake_256f_simple;
    public static final SPHINCSPlusParameterSpec shake_256s;
    public static final SPHINCSPlusParameterSpec shake_256s_simple;
    private final String name;

    static {
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec = new SPHINCSPlusParameterSpec("sha2-128f-robust");
        sha2_128f = sPHINCSPlusParameterSpec;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec2 = new SPHINCSPlusParameterSpec("sha2-128s-robust");
        sha2_128s = sPHINCSPlusParameterSpec2;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec3 = new SPHINCSPlusParameterSpec("sha2-192f-robust");
        sha2_192f = sPHINCSPlusParameterSpec3;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec4 = new SPHINCSPlusParameterSpec("sha2-192s-robust");
        sha2_192s = sPHINCSPlusParameterSpec4;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec5 = new SPHINCSPlusParameterSpec("sha2-256f-robust");
        sha2_256f = sPHINCSPlusParameterSpec5;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec6 = new SPHINCSPlusParameterSpec("sha2-256s-robust");
        sha2_256s = sPHINCSPlusParameterSpec6;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec7 = new SPHINCSPlusParameterSpec("sha2-128s-simple");
        sha2_128f_simple = sPHINCSPlusParameterSpec7;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec8 = new SPHINCSPlusParameterSpec("sha2-128f-simple");
        sha2_128s_simple = sPHINCSPlusParameterSpec8;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec9 = new SPHINCSPlusParameterSpec("sha2-192f-simple");
        sha2_192f_simple = sPHINCSPlusParameterSpec9;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec10 = new SPHINCSPlusParameterSpec("sha2-192s-simple");
        sha2_192s_simple = sPHINCSPlusParameterSpec10;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec11 = new SPHINCSPlusParameterSpec("sha2-256f-simple");
        sha2_256f_simple = sPHINCSPlusParameterSpec11;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec12 = new SPHINCSPlusParameterSpec("sha2-256s-simple");
        sha2_256s_simple = sPHINCSPlusParameterSpec12;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec13 = new SPHINCSPlusParameterSpec("shake-128f-robust");
        shake_128f = sPHINCSPlusParameterSpec13;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec14 = new SPHINCSPlusParameterSpec("shake-128s-robust");
        shake_128s = sPHINCSPlusParameterSpec14;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec15 = new SPHINCSPlusParameterSpec("shake-192f-robust");
        shake_192f = sPHINCSPlusParameterSpec15;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec16 = new SPHINCSPlusParameterSpec("shake-192s-robust");
        shake_192s = sPHINCSPlusParameterSpec16;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec17 = new SPHINCSPlusParameterSpec("shake-256f-robust");
        shake_256f = sPHINCSPlusParameterSpec17;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec18 = new SPHINCSPlusParameterSpec("shake-256s-robust");
        shake_256s = sPHINCSPlusParameterSpec18;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec19 = new SPHINCSPlusParameterSpec("shake-128f-simple");
        shake_128f_simple = sPHINCSPlusParameterSpec19;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec20 = new SPHINCSPlusParameterSpec("shake-128s-simple");
        shake_128s_simple = sPHINCSPlusParameterSpec20;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec21 = new SPHINCSPlusParameterSpec("shake-192f-simple");
        shake_192f_simple = sPHINCSPlusParameterSpec21;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec22 = new SPHINCSPlusParameterSpec("shake-192s-simple");
        shake_192s_simple = sPHINCSPlusParameterSpec22;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec23 = new SPHINCSPlusParameterSpec("shake-256f-simple");
        shake_256f_simple = sPHINCSPlusParameterSpec23;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec24 = new SPHINCSPlusParameterSpec("shake-256s-simple");
        shake_256s_simple = sPHINCSPlusParameterSpec24;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec25 = new SPHINCSPlusParameterSpec("haraka-128f-robust");
        haraka_128f = sPHINCSPlusParameterSpec25;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec26 = new SPHINCSPlusParameterSpec("haraka-128s-robust");
        haraka_128s = sPHINCSPlusParameterSpec26;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec27 = new SPHINCSPlusParameterSpec("haraka-256f-robust");
        haraka_256f = sPHINCSPlusParameterSpec27;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec28 = new SPHINCSPlusParameterSpec("haraka-256s-robust");
        haraka_256s = sPHINCSPlusParameterSpec28;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec29 = new SPHINCSPlusParameterSpec("haraka-192f-robust");
        haraka_192f = sPHINCSPlusParameterSpec29;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec30 = new SPHINCSPlusParameterSpec("haraka-192s-robust");
        haraka_192s = sPHINCSPlusParameterSpec30;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec31 = new SPHINCSPlusParameterSpec("haraka-128f-simple");
        haraka_128f_simple = sPHINCSPlusParameterSpec31;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec32 = new SPHINCSPlusParameterSpec("haraka-128s-simple");
        haraka_128s_simple = sPHINCSPlusParameterSpec32;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec33 = new SPHINCSPlusParameterSpec("haraka-192f-simple");
        haraka_192f_simple = sPHINCSPlusParameterSpec33;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec34 = new SPHINCSPlusParameterSpec("haraka-192s-simple");
        haraka_192s_simple = sPHINCSPlusParameterSpec34;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec35 = new SPHINCSPlusParameterSpec("haraka-256f-simple");
        haraka_256f_simple = sPHINCSPlusParameterSpec35;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec36 = new SPHINCSPlusParameterSpec("haraka-256s-simple");
        haraka_256s_simple = sPHINCSPlusParameterSpec36;
        HashMap hashMap = new HashMap();
        parameters = hashMap;
        hashMap.put(sPHINCSPlusParameterSpec.getName(), sPHINCSPlusParameterSpec);
        parameters.put(sPHINCSPlusParameterSpec2.getName(), sPHINCSPlusParameterSpec2);
        parameters.put(sPHINCSPlusParameterSpec3.getName(), sPHINCSPlusParameterSpec3);
        parameters.put(sPHINCSPlusParameterSpec4.getName(), sPHINCSPlusParameterSpec4);
        parameters.put(sPHINCSPlusParameterSpec5.getName(), sPHINCSPlusParameterSpec5);
        parameters.put(sPHINCSPlusParameterSpec6.getName(), sPHINCSPlusParameterSpec6);
        parameters.put(sPHINCSPlusParameterSpec7.getName(), sPHINCSPlusParameterSpec7);
        parameters.put(sPHINCSPlusParameterSpec8.getName(), sPHINCSPlusParameterSpec8);
        parameters.put(sPHINCSPlusParameterSpec9.getName(), sPHINCSPlusParameterSpec9);
        parameters.put(sPHINCSPlusParameterSpec10.getName(), sPHINCSPlusParameterSpec10);
        parameters.put(sPHINCSPlusParameterSpec11.getName(), sPHINCSPlusParameterSpec11);
        parameters.put(sPHINCSPlusParameterSpec12.getName(), sPHINCSPlusParameterSpec12);
        parameters.put(sPHINCSPlusParameterSpec13.getName(), sPHINCSPlusParameterSpec13);
        parameters.put(sPHINCSPlusParameterSpec14.getName(), sPHINCSPlusParameterSpec14);
        parameters.put(sPHINCSPlusParameterSpec15.getName(), sPHINCSPlusParameterSpec15);
        parameters.put(sPHINCSPlusParameterSpec16.getName(), sPHINCSPlusParameterSpec16);
        parameters.put(sPHINCSPlusParameterSpec17.getName(), sPHINCSPlusParameterSpec17);
        parameters.put(sPHINCSPlusParameterSpec18.getName(), sPHINCSPlusParameterSpec18);
        parameters.put(sPHINCSPlusParameterSpec19.getName(), sPHINCSPlusParameterSpec19);
        parameters.put(sPHINCSPlusParameterSpec20.getName(), sPHINCSPlusParameterSpec20);
        parameters.put(sPHINCSPlusParameterSpec21.getName(), sPHINCSPlusParameterSpec21);
        parameters.put(sPHINCSPlusParameterSpec22.getName(), sPHINCSPlusParameterSpec22);
        parameters.put(sPHINCSPlusParameterSpec23.getName(), sPHINCSPlusParameterSpec23);
        parameters.put(sPHINCSPlusParameterSpec24.getName(), sPHINCSPlusParameterSpec24);
        parameters.put(sPHINCSPlusParameterSpec25.getName(), sPHINCSPlusParameterSpec25);
        parameters.put(sPHINCSPlusParameterSpec26.getName(), sPHINCSPlusParameterSpec26);
        parameters.put(sPHINCSPlusParameterSpec29.getName(), sPHINCSPlusParameterSpec29);
        parameters.put(sPHINCSPlusParameterSpec30.getName(), sPHINCSPlusParameterSpec30);
        parameters.put(sPHINCSPlusParameterSpec27.getName(), sPHINCSPlusParameterSpec27);
        parameters.put(sPHINCSPlusParameterSpec28.getName(), sPHINCSPlusParameterSpec28);
        parameters.put(sPHINCSPlusParameterSpec31.getName(), sPHINCSPlusParameterSpec31);
        parameters.put(sPHINCSPlusParameterSpec32.getName(), sPHINCSPlusParameterSpec32);
        parameters.put(sPHINCSPlusParameterSpec33.getName(), sPHINCSPlusParameterSpec33);
        parameters.put(sPHINCSPlusParameterSpec34.getName(), sPHINCSPlusParameterSpec34);
        parameters.put(sPHINCSPlusParameterSpec35.getName(), sPHINCSPlusParameterSpec35);
        parameters.put(sPHINCSPlusParameterSpec36.getName(), sPHINCSPlusParameterSpec36);
    }

    private SPHINCSPlusParameterSpec(String str) {
        this.name = str;
    }

    public static SPHINCSPlusParameterSpec fromName(String str) {
        return (SPHINCSPlusParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
