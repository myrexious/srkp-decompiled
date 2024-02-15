package org.bouncycastle.cms;

/* loaded from: classes2.dex */
public class CMSRuntimeException extends RuntimeException {
    Exception e;

    public CMSRuntimeException(String str) {
        super(str);
    }

    public CMSRuntimeException(String str, Exception exc) {
        super(str);
        this.e = exc;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.e;
    }

    public Exception getUnderlyingException() {
        return this.e;
    }
}
