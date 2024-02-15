package net.openid.appauth;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class SystemClock implements Clock {
    public static final SystemClock INSTANCE = new SystemClock();

    private SystemClock() {
    }

    @Override // net.openid.appauth.Clock
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
