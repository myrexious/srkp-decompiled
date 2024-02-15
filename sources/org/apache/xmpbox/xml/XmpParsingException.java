package org.apache.xmpbox.xml;

/* loaded from: classes2.dex */
public class XmpParsingException extends Exception {
    private static final long serialVersionUID = -8843096358184702908L;
    private ErrorType errorType;

    /* loaded from: classes2.dex */
    public enum ErrorType {
        Undefined,
        Configuration,
        XpacketBadStart,
        XpacketBadEnd,
        NoRootElement,
        NoSchema,
        InvalidPdfaSchema,
        NoType,
        InvalidType,
        Format,
        NoValueType,
        RequiredProperty,
        InvalidPrefix
    }

    public XmpParsingException(ErrorType errorType, String str, Throwable th) {
        super(str, th);
        this.errorType = errorType;
    }

    public XmpParsingException(ErrorType errorType, String str) {
        super(str);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }
}
