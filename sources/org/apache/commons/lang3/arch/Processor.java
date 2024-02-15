package org.apache.commons.lang3.arch;

import com.tom_roush.fontbox.ttf.OpenTypeScript;

/* loaded from: classes2.dex */
public class Processor {
    private final Arch arch;
    private final Type type;

    /* loaded from: classes2.dex */
    public enum Arch {
        BIT_32("32-bit"),
        BIT_64("64-bit"),
        UNKNOWN(OpenTypeScript.UNKNOWN);
        
        private final String label;

        Arch(String str) {
            this.label = str;
        }

        public String getLabel() {
            return this.label;
        }
    }

    /* loaded from: classes2.dex */
    public enum Type {
        AARCH_64("AArch64"),
        X86("x86"),
        IA_64("IA-64"),
        PPC("PPC"),
        UNKNOWN(OpenTypeScript.UNKNOWN);
        
        private final String label;

        Type(String str) {
            this.label = str;
        }

        public String getLabel() {
            return this.label;
        }
    }

    public Processor(Arch arch, Type type) {
        this.arch = arch;
        this.type = type;
    }

    public Arch getArch() {
        return this.arch;
    }

    public Type getType() {
        return this.type;
    }

    public boolean is32Bit() {
        return Arch.BIT_32 == this.arch;
    }

    public boolean is64Bit() {
        return Arch.BIT_64 == this.arch;
    }

    public boolean isAarch64() {
        return Type.AARCH_64 == this.type;
    }

    public boolean isIA64() {
        return Type.IA_64 == this.type;
    }

    public boolean isPPC() {
        return Type.PPC == this.type;
    }

    public boolean isX86() {
        return Type.X86 == this.type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type.getLabel()).append(' ').append(this.arch.getLabel());
        return sb.toString();
    }
}
