package com.tom_roush.fontbox.type1;

import android.util.Log;
import com.tom_roush.fontbox.type1.Token;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import org.apache.commons.io.IOUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class Type1Lexer {
    private final ByteBuffer buffer;
    private int openParens = 0;
    private Token aheadToken = readToken(null);

    public Type1Lexer(byte[] bArr) throws IOException {
        this.buffer = ByteBuffer.wrap(bArr);
    }

    public Token nextToken() throws IOException {
        Token token = this.aheadToken;
        this.aheadToken = readToken(token);
        return token;
    }

    public Token peekToken() {
        return this.aheadToken;
    }

    public boolean peekKind(Token.Kind kind) {
        Token token = this.aheadToken;
        return token != null && token.getKind() == kind;
    }

    private char getChar() throws IOException {
        try {
            return (char) this.buffer.get();
        } catch (BufferUnderflowException unused) {
            throw new IOException("Premature end of buffer reached");
        }
    }

    private Token readToken(Token token) throws IOException {
        boolean z;
        do {
            z = false;
            while (this.buffer.hasRemaining()) {
                char c = getChar();
                if (c == '%') {
                    readComment();
                } else if (c == '(') {
                    return readString();
                } else {
                    if (c == ')') {
                        throw new IOException("unexpected closing parenthesis");
                    }
                    if (c == '[') {
                        return new Token(c, Token.START_ARRAY);
                    }
                    if (c == '{') {
                        return new Token(c, Token.START_PROC);
                    }
                    if (c == ']') {
                        return new Token(c, Token.END_ARRAY);
                    }
                    if (c == '}') {
                        return new Token(c, Token.END_PROC);
                    }
                    if (c == '/') {
                        String readRegular = readRegular();
                        if (readRegular == null) {
                            throw new DamagedFontException("Could not read token at position " + this.buffer.position());
                        }
                        return new Token(readRegular, Token.LITERAL);
                    } else if (c == '<') {
                        if (getChar() == c) {
                            return new Token("<<", Token.START_DICT);
                        }
                        ByteBuffer byteBuffer = this.buffer;
                        byteBuffer.position(byteBuffer.position() - 1);
                        return new Token(c, Token.NAME);
                    } else if (c == '>') {
                        if (getChar() == c) {
                            return new Token(">>", Token.END_DICT);
                        }
                        ByteBuffer byteBuffer2 = this.buffer;
                        byteBuffer2.position(byteBuffer2.position() - 1);
                        return new Token(c, Token.NAME);
                    } else {
                        if (!Character.isWhitespace(c)) {
                            if (c == 0) {
                                Log.w("PdfBox-Android", "NULL byte in font, skipped");
                            } else {
                                ByteBuffer byteBuffer3 = this.buffer;
                                byteBuffer3.position(byteBuffer3.position() - 1);
                                Token tryReadNumber = tryReadNumber();
                                if (tryReadNumber != null) {
                                    return tryReadNumber;
                                }
                                String readRegular2 = readRegular();
                                if (readRegular2 == null) {
                                    throw new DamagedFontException("Could not read token at position " + this.buffer.position());
                                }
                                if (readRegular2.equals("RD") || readRegular2.equals("-|")) {
                                    if (token != null && token.getKind() == Token.INTEGER) {
                                        return readCharString(token.intValue());
                                    }
                                    throw new IOException("expected INTEGER before -| or RD");
                                }
                                return new Token(readRegular2, Token.NAME);
                            }
                        }
                        z = true;
                    }
                }
            }
        } while (z);
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0094 A[LOOP:1: B:97:0x008e->B:99:0x0094, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.tom_roush.fontbox.type1.Token tryReadNumber() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.fontbox.type1.Type1Lexer.tryReadNumber():com.tom_roush.fontbox.type1.Token");
    }

    private String readRegular() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (this.buffer.hasRemaining()) {
            this.buffer.mark();
            char c = getChar();
            if (Character.isWhitespace(c) || c == '(' || c == ')' || c == '<' || c == '>' || c == '[' || c == ']' || c == '{' || c == '}' || c == '/' || c == '%') {
                this.buffer.reset();
                break;
            }
            sb.append(c);
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    private String readComment() throws IOException {
        char c;
        StringBuilder sb = new StringBuilder();
        while (this.buffer.hasRemaining() && (c = getChar()) != '\r' && c != '\n') {
            sb.append(c);
        }
        return sb.toString();
    }

    private Token readString() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (this.buffer.hasRemaining()) {
            char c = getChar();
            if (c == '\n' || c == '\r') {
                sb.append("\n");
            } else if (c == '\\') {
                char c2 = getChar();
                if (c2 == '(') {
                    sb.append('(');
                } else if (c2 == ')') {
                    sb.append(')');
                } else if (c2 == '\\') {
                    sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                } else if (c2 == 'b') {
                    sb.append('\b');
                } else if (c2 == 'f') {
                    sb.append('\f');
                } else if (c2 == 'n' || c2 == 'r') {
                    sb.append("\n");
                } else if (c2 == 't') {
                    sb.append('\t');
                }
                if (Character.isDigit(c2)) {
                    try {
                        sb.append((char) Integer.parseInt(String.valueOf(new char[]{c2, getChar(), getChar()}), 8));
                    } catch (NumberFormatException e) {
                        throw new IOException(e);
                    }
                } else {
                    continue;
                }
            } else if (c == '(') {
                this.openParens++;
                sb.append('(');
            } else if (c == ')') {
                if (this.openParens == 0) {
                    return new Token(sb.toString(), Token.STRING);
                }
                sb.append(')');
                this.openParens--;
            } else {
                sb.append(c);
            }
        }
        return null;
    }

    private Token readCharString(int i) throws IOException {
        try {
            this.buffer.get();
            byte[] bArr = new byte[i];
            this.buffer.get(bArr);
            return new Token(bArr, Token.CHARSTRING);
        } catch (BufferUnderflowException unused) {
            throw new IOException("Premature end of buffer reached");
        }
    }
}
