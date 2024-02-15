package org.apache.commons.text.lookup;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class LocalHostStringLookup extends AbstractStringLookup {
    static final LocalHostStringLookup INSTANCE = new LocalHostStringLookup();

    private LocalHostStringLookup() {
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0057 A[Catch: UnknownHostException -> 0x0060, TRY_LEAVE, TryCatch #0 {UnknownHostException -> 0x0060, blocks: (B:40:0x0004, B:60:0x003f, B:62:0x0048, B:63:0x004d, B:64:0x004e, B:66:0x0057, B:47:0x001a, B:50:0x0024, B:53:0x002e), top: B:69:0x0004 }] */
    @Override // org.apache.commons.text.lookup.StringLookup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String lookup(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            if (r6 != 0) goto L4
            return r0
        L4:
            int r1 = r6.hashCode()     // Catch: java.net.UnknownHostException -> L60
            r2 = -1147692044(0xffffffffbb979bf4, float:-0.0046267454)
            r3 = 2
            r4 = 1
            if (r1 == r2) goto L2e
            r2 = 3373707(0x337a8b, float:4.72757E-39)
            if (r1 == r2) goto L24
            r2 = 1339224004(0x4fd2efc4, float:7.0778573E9)
            if (r1 == r2) goto L1a
            goto L38
        L1a:
            java.lang.String r1 = "canonical-name"
            boolean r1 = r6.equals(r1)     // Catch: java.net.UnknownHostException -> L60
            if (r1 == 0) goto L38
            r1 = r4
            goto L39
        L24:
            java.lang.String r1 = "name"
            boolean r1 = r6.equals(r1)     // Catch: java.net.UnknownHostException -> L60
            if (r1 == 0) goto L38
            r1 = 0
            goto L39
        L2e:
            java.lang.String r1 = "address"
            boolean r1 = r6.equals(r1)     // Catch: java.net.UnknownHostException -> L60
            if (r1 == 0) goto L38
            r1 = r3
            goto L39
        L38:
            r1 = -1
        L39:
            if (r1 == 0) goto L57
            if (r1 == r4) goto L4e
            if (r1 != r3) goto L48
            java.net.InetAddress r6 = java.net.InetAddress.getLocalHost()     // Catch: java.net.UnknownHostException -> L60
            java.lang.String r6 = r6.getHostAddress()     // Catch: java.net.UnknownHostException -> L60
            return r6
        L48:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch: java.net.UnknownHostException -> L60
            r1.<init>(r6)     // Catch: java.net.UnknownHostException -> L60
            throw r1     // Catch: java.net.UnknownHostException -> L60
        L4e:
            java.net.InetAddress r6 = java.net.InetAddress.getLocalHost()     // Catch: java.net.UnknownHostException -> L60
            java.lang.String r6 = r6.getCanonicalHostName()     // Catch: java.net.UnknownHostException -> L60
            return r6
        L57:
            java.net.InetAddress r6 = java.net.InetAddress.getLocalHost()     // Catch: java.net.UnknownHostException -> L60
            java.lang.String r6 = r6.getHostName()     // Catch: java.net.UnknownHostException -> L60
            return r6
        L60:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.text.lookup.LocalHostStringLookup.lookup(java.lang.String):java.lang.String");
    }
}
