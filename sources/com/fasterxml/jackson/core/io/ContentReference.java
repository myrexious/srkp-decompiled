package com.fasterxml.jackson.core.io;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

/* loaded from: classes.dex */
public class ContentReference implements Serializable {
    public static final int DEFAULT_MAX_CONTENT_SNIPPET = 500;
    protected static final ContentReference UNKNOWN_CONTENT = new ContentReference(false, null);
    private static final long serialVersionUID = 1;
    protected final boolean _isContentTextual;
    protected final int _length;
    protected final int _offset;
    protected final transient Object _rawContent;

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
    }

    protected int maxContentSnippetLength() {
        return 500;
    }

    protected ContentReference(boolean z, Object obj) {
        this(z, obj, -1, -1);
    }

    protected ContentReference(boolean z, Object obj, int i, int i2) {
        this._isContentTextual = z;
        this._rawContent = obj;
        this._offset = i;
        this._length = i2;
    }

    public static ContentReference unknown() {
        return UNKNOWN_CONTENT;
    }

    public static ContentReference construct(boolean z, Object obj) {
        return new ContentReference(z, obj);
    }

    public static ContentReference construct(boolean z, Object obj, int i, int i2) {
        return new ContentReference(z, obj, i, i2);
    }

    public static ContentReference rawReference(boolean z, Object obj) {
        if (obj instanceof ContentReference) {
            return (ContentReference) obj;
        }
        return new ContentReference(z, obj);
    }

    public static ContentReference rawReference(Object obj) {
        return rawReference(false, obj);
    }

    protected Object readResolve() {
        return UNKNOWN_CONTENT;
    }

    public boolean hasTextualContent() {
        return this._isContentTextual;
    }

    public Object getRawContent() {
        return this._rawContent;
    }

    public int contentOffset() {
        return this._offset;
    }

    public int contentLength() {
        return this._length;
    }

    public String buildSourceDescription() {
        return appendSourceDescription(new StringBuilder(200)).toString();
    }

    public StringBuilder appendSourceDescription(StringBuilder sb) {
        String str;
        Object rawContent = getRawContent();
        if (rawContent == null) {
            sb.append("UNKNOWN");
            return sb;
        }
        Class<?> cls = rawContent instanceof Class ? (Class) rawContent : rawContent.getClass();
        String name = cls.getName();
        if (name.startsWith("java.")) {
            name = cls.getSimpleName();
        } else if (rawContent instanceof byte[]) {
            name = "byte[]";
        } else if (rawContent instanceof char[]) {
            name = "char[]";
        }
        sb.append('(').append(name).append(')');
        if (hasTextualContent()) {
            int maxContentSnippetLength = maxContentSnippetLength();
            int[] iArr = {contentOffset(), contentLength()};
            String str2 = " chars";
            if (rawContent instanceof CharSequence) {
                str = _truncate((CharSequence) rawContent, iArr, maxContentSnippetLength);
            } else if (rawContent instanceof char[]) {
                str = _truncate((char[]) rawContent, iArr, maxContentSnippetLength);
            } else if (rawContent instanceof byte[]) {
                str = _truncate((byte[]) rawContent, iArr, maxContentSnippetLength);
                str2 = " bytes";
            } else {
                str = null;
            }
            if (str != null) {
                _append(sb, str);
                if (iArr[1] > maxContentSnippetLength) {
                    sb.append("[truncated ").append(iArr[1] - maxContentSnippetLength).append(str2).append(']');
                }
            }
        } else if (rawContent instanceof byte[]) {
            int contentLength = contentLength();
            if (contentLength < 0) {
                contentLength = ((byte[]) rawContent).length;
            }
            sb.append('[').append(contentLength).append(" bytes]");
        }
        return sb;
    }

    protected String _truncate(CharSequence charSequence, int[] iArr, int i) {
        _truncateOffsets(iArr, charSequence.length());
        int i2 = iArr[0];
        return charSequence.subSequence(i2, Math.min(iArr[1], i) + i2).toString();
    }

    protected String _truncate(char[] cArr, int[] iArr, int i) {
        _truncateOffsets(iArr, cArr.length);
        return new String(cArr, iArr[0], Math.min(iArr[1], i));
    }

    protected String _truncate(byte[] bArr, int[] iArr, int i) {
        _truncateOffsets(iArr, bArr.length);
        return new String(bArr, iArr[0], Math.min(iArr[1], i), StandardCharsets.UTF_8);
    }

    protected void _truncateOffsets(int[] iArr, int i) {
        int i2 = iArr[0];
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= i) {
            i2 = i;
        }
        iArr[0] = i2;
        int i3 = iArr[1];
        int i4 = i - i2;
        if (i3 < 0 || i3 > i4) {
            iArr[1] = i4;
        }
    }

    protected int _append(StringBuilder sb, String str) {
        sb.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (!Character.isISOControl(charAt) || !_appendEscaped(sb, charAt)) {
                sb.append(charAt);
            }
        }
        sb.append('\"');
        return str.length();
    }

    protected boolean _appendEscaped(StringBuilder sb, int i) {
        if (i == 13 || i == 10) {
            return false;
        }
        sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
        sb.append('u');
        sb.append(CharTypes.hexToChar((i >> 12) & 15));
        sb.append(CharTypes.hexToChar((i >> 8) & 15));
        sb.append(CharTypes.hexToChar((i >> 4) & 15));
        sb.append(CharTypes.hexToChar(i & 15));
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof ContentReference)) {
            ContentReference contentReference = (ContentReference) obj;
            if (this._offset == contentReference._offset && this._length == contentReference._length) {
                Object obj2 = contentReference._rawContent;
                Object obj3 = this._rawContent;
                if (obj3 == null) {
                    return obj2 == null;
                } else if (obj2 == null) {
                    return false;
                } else {
                    if ((obj3 instanceof File) || (obj3 instanceof URL) || (obj3 instanceof URI)) {
                        return obj3.equals(obj2);
                    }
                    return obj3 == obj2;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this._rawContent);
    }
}
