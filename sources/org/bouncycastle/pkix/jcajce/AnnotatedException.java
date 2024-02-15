package org.bouncycastle.pkix.jcajce;

/* loaded from: classes2.dex */
class AnnotatedException extends Exception {
    private Throwable _underlyingException;

    public AnnotatedException(String str) {
        this(str, null);
    }

    public AnnotatedException(String str, Throwable th) {
        super(str);
        this._underlyingException = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this._underlyingException;
    }
}
